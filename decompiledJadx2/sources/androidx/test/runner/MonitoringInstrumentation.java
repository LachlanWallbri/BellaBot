package androidx.test.runner;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.UserHandle;
import android.util.Log;
import androidx.test.internal.platform.app.ActivityLifecycleTimeout;
import androidx.test.internal.runner.InstrumentationConnection;
import androidx.test.internal.runner.hidden.ExposedInstrumentationApi;
import androidx.test.internal.runner.intent.IntentMonitorImpl;
import androidx.test.internal.runner.intercepting.DefaultInterceptingActivityFactory;
import androidx.test.internal.runner.lifecycle.ActivityLifecycleMonitorImpl;
import androidx.test.internal.runner.lifecycle.ApplicationLifecycleMonitorImpl;
import androidx.test.internal.util.Checks;
import androidx.test.internal.util.ProcSummary;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.intent.IntentMonitorRegistry;
import androidx.test.runner.intent.IntentStubberRegistry;
import androidx.test.runner.intercepting.InterceptingActivityFactory;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.ApplicationLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.ApplicationStage;
import androidx.test.runner.lifecycle.Stage;
import java.lang.Thread;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
public class MonitoringInstrumentation extends ExposedInstrumentationApi {
    private static final String TAG = "MonitoringInstr";
    private ExecutorService executorService;
    private Handler handlerForMainLooper;
    private volatile InterceptingActivityFactory interceptingActivityFactory;
    private String jsBridgeClassName;
    private Thread.UncaughtExceptionHandler standardHandler;
    private static final long MILLIS_TO_WAIT_FOR_ACTIVITY_TO_STOP = TimeUnit.SECONDS.toMillis(2);
    private static final long MILLIS_TO_POLL_FOR_ACTIVITY_STOP = MILLIS_TO_WAIT_FOR_ACTIVITY_TO_STOP / 40;
    private ActivityLifecycleMonitorImpl lifecycleMonitor = new ActivityLifecycleMonitorImpl();
    private ApplicationLifecycleMonitorImpl applicationMonitor = new ApplicationLifecycleMonitorImpl();
    private IntentMonitorImpl intentMonitor = new IntentMonitorImpl();
    private AtomicBoolean anActivityHasBeenLaunched = new AtomicBoolean(false);
    private AtomicLong lastIdleTime = new AtomicLong(0);
    private AtomicInteger startedActivityCounter = new AtomicInteger(0);
    private AtomicBoolean isJsBridgeLoaded = new AtomicBoolean(false);
    private volatile Boolean isOriginalInstr = null;
    private ThreadLocal<Boolean> isDexmakerClassLoaderInitialized = new ThreadLocal<>();
    private MessageQueue.IdleHandler idleHandler = new MessageQueue.IdleHandler() { // from class: androidx.test.runner.MonitoringInstrumentation.1
        @Override // android.os.MessageQueue.IdleHandler
        public boolean queueIdle() {
            MonitoringInstrumentation.this.lastIdleTime.set(System.currentTimeMillis());
            return true;
        }
    };
    private volatile boolean finished = false;

    @Override // android.app.Instrumentation
    public void onCreate(Bundle arguments) {
        Log.i(TAG, "Instrumentation started!");
        logUncaughtExceptions();
        installMultidex();
        InstrumentationRegistry.registerInstance(this, arguments);
        androidx.test.InstrumentationRegistry.registerInstance(this, arguments);
        ActivityLifecycleMonitorRegistry.registerInstance(this.lifecycleMonitor);
        ApplicationLifecycleMonitorRegistry.registerInstance(this.applicationMonitor);
        IntentMonitorRegistry.registerInstance(this.intentMonitor);
        this.handlerForMainLooper = new Handler(Looper.getMainLooper());
        this.executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactory(this) { // from class: androidx.test.runner.MonitoringInstrumentation.2
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread newThread = Executors.defaultThreadFactory().newThread(runnable);
                newThread.setName(MonitoringInstrumentation.class.getSimpleName());
                return newThread;
            }
        });
        Looper.myQueue().addIdleHandler(this.idleHandler);
        super.onCreate(arguments);
        specifyDexMakerCacheProperty();
        setupDexmakerClassloader();
        useDefaultInterceptingActivityFactory();
    }

    protected void installMultidex() {
        if (Build.VERSION.SDK_INT < 21) {
            try {
                try {
                    Class<?> cls = Class.forName("androidx.multidex.MultiDex");
                    try {
                        cls.getDeclaredMethod("installInstrumentation", Context.class, Context.class).invoke(null, getContext(), getTargetContext());
                    } catch (NoSuchMethodException unused) {
                        installOldMultiDex(cls);
                    }
                } catch (ClassNotFoundException unused2) {
                    Log.i(TAG, "No multidex.");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("multidex is available at runtime, but calling it failed.", e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException("multidex is available at runtime, but calling it failed.", e2);
                }
            } catch (NoSuchMethodException e3) {
                Log.i(TAG, "No multidex.", e3);
            }
        }
    }

    protected void installOldMultiDex(Class<?> multidexClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        multidexClass.getDeclaredMethod("install", Context.class).invoke(null, getTargetContext());
    }

    protected void specifyDexMakerCacheProperty() {
        System.getProperties().put("dexmaker.dexcache", getTargetContext().getDir("dxmaker_cache", 0).getAbsolutePath());
    }

    protected final void setJsBridgeClassName(final String className) {
        if (className == null) {
            throw new NullPointerException("JsBridge class name cannot be null!");
        }
        if (this.isJsBridgeLoaded.get()) {
            throw new IllegalStateException("JsBridge is already loaded!");
        }
        this.jsBridgeClassName = className;
    }

    private void setupDexmakerClassloader() {
        if (Boolean.TRUE.equals(this.isDexmakerClassLoaderInitialized.get())) {
            return;
        }
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader = getTargetContext().getClassLoader();
        if (contextClassLoader != classLoader) {
            Log.i(TAG, String.format("Setting context classloader to '%s', Original: '%s'", classLoader, contextClassLoader));
            Thread.currentThread().setContextClassLoader(classLoader);
        }
        this.isDexmakerClassLoaderInitialized.set(Boolean.TRUE);
    }

    private void logUncaughtExceptions() {
        this.standardHandler = Thread.currentThread().getUncaughtExceptionHandler();
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: androidx.test.runner.MonitoringInstrumentation.3
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread t, Throwable e) {
                MonitoringInstrumentation.this.onException(t, e);
                if (MonitoringInstrumentation.this.standardHandler != null) {
                    Log.w(MonitoringInstrumentation.TAG, String.format("Invoking uncaught exception handler %s (a %s)", MonitoringInstrumentation.this.standardHandler, MonitoringInstrumentation.this.standardHandler.getClass()));
                    MonitoringInstrumentation.this.standardHandler.uncaughtException(t, e);
                } else {
                    String valueOf = String.valueOf(t.getName());
                    Log.w(MonitoringInstrumentation.TAG, valueOf.length() != 0 ? "Invoking uncaught exception handler for thread: ".concat(valueOf) : new String("Invoking uncaught exception handler for thread: "));
                    t.getThreadGroup().uncaughtException(t, e);
                }
                if ("robolectric".equals(Build.FINGERPRINT) || !Looper.getMainLooper().getThread().equals(t)) {
                    return;
                }
                Log.e(MonitoringInstrumentation.TAG, "The main thread has died and the handlers didn't care, exiting");
                System.exit(-10);
            }
        });
    }

    protected void restoreUncaughtExceptionHandler() {
        Thread.currentThread().setUncaughtExceptionHandler(this.standardHandler);
    }

    @Override // android.app.Instrumentation
    public void onStart() {
        super.onStart();
        String str = this.jsBridgeClassName;
        if (str != null) {
            tryLoadingJsBridge(str);
        }
        waitForIdleSync();
        setupDexmakerClassloader();
        InstrumentationConnection.getInstance().init(this, new ActivityFinisher());
    }

    @Override // android.app.Instrumentation
    public void finish(int resultCode, Bundle results) {
        if (this.finished) {
            Log.w(TAG, "finish called 2x!");
            return;
        }
        this.finished = true;
        if (shouldWaitForActivitiesToComplete()) {
            this.handlerForMainLooper.post(new ActivityFinisher());
            long currentTimeMillis = System.currentTimeMillis();
            waitForActivitiesToComplete();
            Log.i(TAG, String.format("waitForActivitiesToComplete() took: %sms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis)));
        }
        ActivityLifecycleMonitorRegistry.registerInstance(null);
        restoreUncaughtExceptionHandler();
        super.finish(resultCode, results);
    }

    protected boolean shouldWaitForActivitiesToComplete() {
        return Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("waitForActivitiesToComplete", "true"));
    }

    protected void waitForActivitiesToComplete() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IllegalStateException("Cannot be called from main thread!");
        }
        long currentTimeMillis = System.currentTimeMillis() + MILLIS_TO_WAIT_FOR_ACTIVITY_TO_STOP;
        int i = this.startedActivityCounter.get();
        while (i > 0 && System.currentTimeMillis() < currentTimeMillis) {
            try {
                StringBuilder sb = new StringBuilder(37);
                sb.append("Unstopped activity count: ");
                sb.append(i);
                Log.i(TAG, sb.toString());
                Thread.sleep(MILLIS_TO_POLL_FOR_ACTIVITY_STOP);
                i = this.startedActivityCounter.get();
            } catch (InterruptedException e) {
                Log.i(TAG, "Abandoning activity wait due to interruption.", e);
            }
        }
        if (i > 0) {
            dumpThreadStateToOutputs("ThreadState-unstopped.txt");
            Log.w(TAG, String.format("Still %s activities active after waiting %s ms.", Integer.valueOf(i), Long.valueOf(MILLIS_TO_WAIT_FOR_ACTIVITY_TO_STOP)));
        }
    }

    @Override // android.app.Instrumentation
    public void onDestroy() {
        Log.i(TAG, "Instrumentation Finished!");
        Looper.myQueue().removeIdleHandler(this.idleHandler);
        InstrumentationConnection.getInstance().terminate();
        super.onDestroy();
    }

    @Override // android.app.Instrumentation
    public void callApplicationOnCreate(Application app) {
        this.applicationMonitor.signalLifecycleChange(app, ApplicationStage.PRE_ON_CREATE);
        super.callApplicationOnCreate(app);
        this.applicationMonitor.signalLifecycleChange(app, ApplicationStage.CREATED);
    }

    @Override // android.app.Instrumentation
    public void runOnMainSync(Runnable runnable) {
        FutureTask futureTask = new FutureTask(runnable, null);
        super.runOnMainSync(futureTask);
        try {
            futureTask.get();
        } catch (InterruptedException e) {
            Log.e(TAG, "An execution is interrupted", e);
            throw new RuntimeException(e);
        } catch (ExecutionException e2) {
            Log.e(TAG, "An exception is thrown from the runnable posted to the main thread", e2);
            throw new RuntimeException(e2.getCause());
        }
    }

    @Override // android.app.Instrumentation
    public Activity startActivitySync(final Intent intent) {
        Checks.checkNotMainThread();
        long j = this.lastIdleTime.get();
        if (this.anActivityHasBeenLaunched.compareAndSet(false, true)) {
            intent.addFlags(67108864);
        }
        Future submit = this.executorService.submit(new Callable<Activity>() { // from class: androidx.test.runner.MonitoringInstrumentation.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Activity call() {
                return MonitoringInstrumentation.super.startActivitySync(intent);
            }
        });
        try {
            return (Activity) submit.get(ActivityLifecycleTimeout.getMillis(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("interrupted", e);
        } catch (ExecutionException e2) {
            throw new RuntimeException("Could not launch activity", e2.getCause());
        } catch (TimeoutException unused) {
            dumpThreadStateToOutputs("ThreadState-startActivityTimeout.txt");
            submit.cancel(true);
            throw new RuntimeException(String.format("Could not launch intent %s within %s milliseconds. Perhaps the main thread has not gone idle within a reasonable amount of time? There could be an animation or something constantly repainting the screen. Or the activity is doing network calls on creation? See the threaddump logs. For your reference the last time the event queue was idle before your activity launch request was %s and now the last time the queue went idle was: %s. If these numbers are the same your activity might be hogging the event queue.", intent, Long.valueOf(ActivityLifecycleTimeout.getMillis()), Long.valueOf(j), Long.valueOf(this.lastIdleTime.get())));
        }
    }

    public Instrumentation.ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode) {
        this.intentMonitor.signalIntent(intent);
        Instrumentation.ActivityResult stubResultFor = stubResultFor(intent);
        if (stubResultFor != null) {
            Log.i(TAG, String.format("Stubbing intent %s", intent));
            return stubResultFor;
        }
        return super.execStartActivity(who, contextThread, token, target, intent, requestCode);
    }

    public Instrumentation.ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode, Bundle options) {
        this.intentMonitor.signalIntent(intent);
        Instrumentation.ActivityResult stubResultFor = stubResultFor(intent);
        if (stubResultFor != null) {
            Log.i(TAG, String.format("Stubbing intent %s", intent));
            return stubResultFor;
        }
        return super.execStartActivity(who, contextThread, token, target, intent, requestCode, options);
    }

    public Instrumentation.ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, String target, Intent intent, int requestCode, Bundle options) {
        this.intentMonitor.signalIntent(intent);
        Instrumentation.ActivityResult stubResultFor = stubResultFor(intent);
        if (stubResultFor != null) {
            Log.i(TAG, String.format("Stubbing intent %s", intent));
            return stubResultFor;
        }
        return super.execStartActivity(who, contextThread, token, target, intent, requestCode, options);
    }

    public Instrumentation.ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode, Bundle options, UserHandle user) {
        return super.execStartActivity(who, contextThread, token, target, intent, requestCode, options, user);
    }

    public void execStartActivities(Context who, IBinder contextThread, IBinder token, Activity target, Intent[] intents, Bundle options) {
        Log.d(TAG, "execStartActivities(context, ibinder, ibinder, activity, intent[], bundle)");
        for (Intent intent : intents) {
            execStartActivity(who, contextThread, token, target, intent, -1, options);
        }
    }

    public Instrumentation.ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Fragment target, Intent intent, int requestCode, Bundle options) {
        Log.d(TAG, "execStartActivity(context, IBinder, IBinder, Fragment, Intent, int, Bundle)");
        this.intentMonitor.signalIntent(intent);
        Instrumentation.ActivityResult stubResultFor = stubResultFor(intent);
        if (stubResultFor != null) {
            Log.i(TAG, String.format("Stubbing intent %s", intent));
            return stubResultFor;
        }
        return super.execStartActivity(who, contextThread, token, target, intent, requestCode, options);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class StubResultCallable implements Callable<Instrumentation.ActivityResult> {
        private final Intent intent;

        StubResultCallable(Intent intent) {
            this.intent = intent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Instrumentation.ActivityResult call() {
            return IntentStubberRegistry.getInstance().getActivityResultForIntent(this.intent);
        }
    }

    private Instrumentation.ActivityResult stubResultFor(Intent intent) {
        if (!IntentStubberRegistry.isLoaded()) {
            return null;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            FutureTask futureTask = new FutureTask(new StubResultCallable(intent));
            runOnMainSync(futureTask);
            try {
                return (Instrumentation.ActivityResult) futureTask.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } catch (ExecutionException e2) {
                throw new RuntimeException(String.format("Could not retrieve stub result for intent %s", intent), e2);
            }
        }
        return IntentStubberRegistry.getInstance().getActivityResultForIntent(intent);
    }

    @Override // android.app.Instrumentation
    public boolean onException(Object obj, Throwable e) {
        Log.e(TAG, String.format("Exception encountered by: %s. Dumping thread state to outputs and pining for the fjords.", obj), e);
        dumpThreadStateToOutputs("ThreadState-onException.txt");
        Log.e(TAG, "Dying now...");
        return super.onException(obj, e);
    }

    protected void dumpThreadStateToOutputs(String outputFileName) {
        Log.e("THREAD_STATE", getThreadState());
    }

    protected String getThreadState() {
        Set<Map.Entry<Thread, StackTraceElement[]>> entrySet = Thread.getAllStackTraces().entrySet();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Thread, StackTraceElement[]> entry : entrySet) {
            StringBuilder sb2 = new StringBuilder("  ");
            sb2.append(entry.getKey());
            sb2.append("\n");
            for (StackTraceElement stackTraceElement : entry.getValue()) {
                sb2.append("    ");
                sb2.append(stackTraceElement.toString());
                sb2.append("\n");
            }
            sb2.append("\n");
            sb.append(sb2.toString());
        }
        return sb.toString();
    }

    @Override // android.app.Instrumentation
    public void callActivityOnDestroy(Activity activity) {
        super.callActivityOnDestroy(activity);
        this.lifecycleMonitor.signalLifecycleChange(Stage.DESTROYED, activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnRestart(Activity activity) {
        super.callActivityOnRestart(activity);
        this.lifecycleMonitor.signalLifecycleChange(Stage.RESTARTED, activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnCreate(Activity activity, Bundle bundle) {
        this.lifecycleMonitor.signalLifecycleChange(Stage.PRE_ON_CREATE, activity);
        super.callActivityOnCreate(activity, bundle);
        this.lifecycleMonitor.signalLifecycleChange(Stage.CREATED, activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnStart(Activity activity) {
        this.startedActivityCounter.incrementAndGet();
        try {
            super.callActivityOnStart(activity);
            this.lifecycleMonitor.signalLifecycleChange(Stage.STARTED, activity);
        } catch (RuntimeException e) {
            this.startedActivityCounter.decrementAndGet();
            throw e;
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnStop(Activity activity) {
        try {
            super.callActivityOnStop(activity);
            this.lifecycleMonitor.signalLifecycleChange(Stage.STOPPED, activity);
        } finally {
            this.startedActivityCounter.decrementAndGet();
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnResume(Activity activity) {
        super.callActivityOnResume(activity);
        this.lifecycleMonitor.signalLifecycleChange(Stage.RESUMED, activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnPause(Activity activity) {
        super.callActivityOnPause(activity);
        this.lifecycleMonitor.signalLifecycleChange(Stage.PAUSED, activity);
    }

    @Override // android.app.Instrumentation
    public Activity newActivity(Class<?> clazz, Context context, IBinder token, Application application, Intent intent, ActivityInfo info, CharSequence title, Activity parent, String id, Object lastNonConfigurationInstance) throws InstantiationException, IllegalAccessException {
        String name = clazz.getPackage().getName();
        String packageName = context.getPackageName();
        ComponentName component = intent.getComponent();
        if (!packageName.equals(component.getPackageName()) && name.equals(component.getPackageName())) {
            intent.setComponent(new ComponentName(packageName, component.getClassName()));
        }
        return super.newActivity(clazz, context, token, application, intent, info, title, parent, id, lastNonConfigurationInstance);
    }

    @Override // android.app.Instrumentation
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (this.interceptingActivityFactory.shouldIntercept(cl, className, intent)) {
            return this.interceptingActivityFactory.create(cl, className, intent);
        }
        return super.newActivity(cl, className, intent);
    }

    public void interceptActivityUsing(InterceptingActivityFactory interceptingActivityFactory) {
        Checks.checkNotNull(interceptingActivityFactory);
        this.interceptingActivityFactory = interceptingActivityFactory;
    }

    public void useDefaultInterceptingActivityFactory() {
        this.interceptingActivityFactory = new DefaultInterceptingActivityFactory();
    }

    private void tryLoadingJsBridge(final String className) {
        if (className == null) {
            throw new NullPointerException("JsBridge class name cannot be null!");
        }
        runOnMainSync(new Runnable() { // from class: androidx.test.runner.MonitoringInstrumentation.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Class.forName(className).getDeclaredMethod("installBridge", new Class[0]).invoke(null, new Object[0]);
                    MonitoringInstrumentation.this.isJsBridgeLoaded.set(true);
                } catch (ClassNotFoundException | NoSuchMethodException unused) {
                    Log.i(MonitoringInstrumentation.TAG, "No JSBridge.");
                } catch (IllegalAccessException e) {
                    e = e;
                    throw new RuntimeException("JSbridge is available at runtime, but calling it failed.", e);
                } catch (InvocationTargetException e2) {
                    e = e2;
                    throw new RuntimeException("JSbridge is available at runtime, but calling it failed.", e);
                }
            }
        });
    }

    /* loaded from: classes.dex */
    public class ActivityFinisher implements Runnable {
        public ActivityFinisher() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList<Activity> arrayList = new ArrayList();
            Iterator it = EnumSet.range(Stage.CREATED, Stage.STOPPED).iterator();
            while (it.hasNext()) {
                arrayList.addAll(MonitoringInstrumentation.this.lifecycleMonitor.getActivitiesInStage((Stage) it.next()));
            }
            if (arrayList.size() > 0) {
                int size = arrayList.size();
                StringBuilder sb = new StringBuilder(60);
                sb.append("Activities that are still in CREATED to STOPPED: ");
                sb.append(size);
                Log.i(MonitoringInstrumentation.TAG, sb.toString());
            }
            for (Activity activity : arrayList) {
                if (!activity.isFinishing()) {
                    try {
                        String valueOf = String.valueOf(activity);
                        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 20);
                        sb2.append("Finishing activity: ");
                        sb2.append(valueOf);
                        Log.i(MonitoringInstrumentation.TAG, sb2.toString());
                        activity.finish();
                    } catch (RuntimeException e) {
                        Log.e(MonitoringInstrumentation.TAG, "Failed to finish activity.", e);
                    }
                }
            }
        }
    }

    @Deprecated
    protected boolean isPrimaryInstrProcess(String argsProcessName) {
        return isPrimaryInstrProcess();
    }

    protected final boolean isPrimaryInstrProcess() {
        return isOriginalInstrumentationProcess();
    }

    private boolean isHostingProcess(String wantName, ProcSummary ps) {
        int length = wantName.length();
        int length2 = ps.cmdline.length();
        if (length == length2) {
            return wantName.equals(ps.cmdline);
        }
        if (length < length2 || !wantName.startsWith(ps.cmdline) || !wantName.endsWith(ps.name)) {
            return false;
        }
        String valueOf = String.valueOf(ps);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 165 + String.valueOf(wantName).length());
        sb.append("Use smaller processNames in AndroidManifest.xml. Long names are truncated. This process's cmdline is a prefix of the processName and suffix of comm - assuming: ");
        sb.append(valueOf);
        sb.append(" is: ");
        sb.append(wantName);
        Log.w(TAG, sb.toString());
        return true;
    }

    private boolean isOriginalInstrumentationProcess() {
        Boolean bool = this.isOriginalInstr;
        if (bool == null) {
            bool = Boolean.valueOf(isOriginalUncached());
            this.isOriginalInstr = bool;
        }
        return bool.booleanValue();
    }

    private List<String> getTargetProcessValues() {
        if (Build.VERSION.SDK_INT < 26) {
            return Collections.emptyList();
        }
        try {
            String str = getContext().getPackageManager().getInstrumentationInfo(getComponentName(), 0).targetProcesses;
            if (str == null) {
                str = "";
            }
            String trim = str.trim();
            if (trim.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            for (String str2 : trim.split(",", -1)) {
                String trim2 = str2.trim();
                if (trim2.length() > 0) {
                    arrayList.add(trim2);
                }
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(getComponentName());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 25);
            sb.append("Cannot locate ourselves: ");
            sb.append(valueOf);
            Log.wtf(TAG, sb.toString(), e);
            String valueOf2 = String.valueOf(getComponentName());
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 25);
            sb2.append("Cannot locate ourselves: ");
            sb2.append(valueOf2);
            throw new IllegalStateException(sb2.toString(), e);
        }
    }

    private boolean isOriginalUncached() {
        if (Build.VERSION.SDK_INT < 26) {
            return true;
        }
        List<String> targetProcessValues = getTargetProcessValues();
        if (targetProcessValues.isEmpty()) {
            return true;
        }
        boolean equals = "*".equals(targetProcessValues.get(0));
        if (targetProcessValues.size() == 1 && !equals) {
            return true;
        }
        try {
            ProcSummary summarize = ProcSummary.summarize("self");
            if (equals) {
                String str = getTargetContext().getApplicationInfo().processName;
                if (str == null) {
                    str = getTargetContext().getPackageName();
                }
                return isHostingProcess(str, summarize);
            }
            return isHostingProcess(targetProcessValues.get(0), summarize);
        } catch (ProcSummary.SummaryException e) {
            Log.w(TAG, "Could not list apps for this user, running in sandbox? Assuming primary", e);
            return false;
        }
    }
}
