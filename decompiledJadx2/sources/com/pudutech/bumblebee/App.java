package com.pudutech.bumblebee;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.Trace;
import android.util.Log;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.App;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.business.ims.IMSKit;
import com.pudutech.bumblebee.presenter.robot_open_task.NotCanCallActivityLifecycle;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.bean.CentralControl;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.config.UrlManager;
import com.pudutech.bumblebee.robot_ui.manager.LocalConfigManager;
import com.pudutech.bumblebee.robot_ui.manager.RobotStatusManager;
import com.pudutech.bumblebee.robot_ui.util.AppUtil;
import com.pudutech.bumblebee.robot_ui.util.finishapp.AppStatusTracker;
import com.pudutech.bumblebee.robot_ui.util.finishapp.CrashHandler;
import com.pudutech.bumblebee.robot_ui.util.finishapp.CrashReportConfig;
import com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting.ServerInfoBean;
import com.pudutech.disinfect.baselib.util.MMKVManager;
import com.pudutech.mirsdk.update.ApiConstants;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.PdNetworkManagerBuilder;
import com.pudutech.pd_network.log.ILog;
import com.pudutech.pd_network.report.utils.GsonUtils;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: App.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0001\"B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0012\u0010\u0017\u001a\u00020\u00112\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0014H\u0002J\u0010\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0011H\u0002J\b\u0010\u001d\u001a\u00020\u0014H\u0002J\b\u0010\u001e\u001a\u00020\u0014H\u0002J\b\u0010\u001f\u001a\u00020\u0014H\u0016J\n\u0010 \u001a\u0004\u0018\u00010\u0004H\u0002J\u0006\u0010!\u001a\u00020\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001f\u0010\n\u001a\u00060\u000bR\u00020\u00008BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, m3961d2 = {"Lcom/pudutech/bumblebee/App;", "Lcom/pudutech/bumblebee/business/base/BaseApplication;", "()V", TmpConstant.DATA_KEY_DEVICENAME, "", "getMAC", "()Ljava/lang/String;", "setMAC", "(Ljava/lang/String;)V", "TAG", "mDefActivityLifecycle", "Lcom/pudutech/bumblebee/App$DefActivityLifecycle;", "getMDefActivityLifecycle", "()Lcom/pudutech/bumblebee/App$DefActivityLifecycle;", "mDefActivityLifecycle$delegate", "Lkotlin/Lazy;", "permission_assigned", "", "quested_perssion", "attachBaseContext", "", "base", "Landroid/content/Context;", "checkNoCall", "activity", "Landroid/app/Activity;", "getMac", "initCrashHandler", "debug", "initLog", "initNet", "onCreate", "processName", "setQuestPerssionStatus", "DefActivityLifecycle", "Bumblebee-6.12.0.10-1-2023-07-12_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class App extends BaseApplication {
    private boolean permission_assigned;
    private boolean quested_perssion;
    private final String TAG = "App";
    private String MAC = ApiConstants.MAC_ADDRESS;

    /* renamed from: mDefActivityLifecycle$delegate, reason: from kotlin metadata */
    private final Lazy mDefActivityLifecycle = LazyKt.lazy(new Function0<DefActivityLifecycle>() { // from class: com.pudutech.bumblebee.App$mDefActivityLifecycle$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final App.DefActivityLifecycle invoke() {
            return new App.DefActivityLifecycle();
        }
    });

    private final DefActivityLifecycle getMDefActivityLifecycle() {
        return (DefActivityLifecycle) this.mDefActivityLifecycle.getValue();
    }

    public final String getMAC() {
        return this.MAC;
    }

    public final void setMAC(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.MAC = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.business.base.BaseApplication, com.pudutech.disinfect.baselib.base.BaseApp, android.content.ContextWrapper
    public void attachBaseContext(Context base) {
        Trace.beginSection("attachBaseContext to Welcome");
        super.attachBaseContext(base);
    }

    @Override // android.app.Application
    public void onCreate() {
        System.setProperty(CoroutineContextKt.COROUTINES_SCHEDULER_PROPERTY_NAME, "off");
        super.onCreate();
        initNet();
        App app = this;
        AppUtil.INSTANCE.initMain(app);
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
                Log.d("App", "read permission not assign");
            } else {
                Log.d("App", "read permission assignend");
                this.permission_assigned = true;
            }
            if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                Log.d("App", "write permission not assign");
            } else {
                Log.d("App", "read permission assignend");
                this.permission_assigned = true;
            }
        }
        CentralControl centralControl = LocalConfigManager.INSTANCE.getINSTANCE().getCentralControl();
        if (centralControl != null) {
            IMSKit.INSTANCE.getInstance().setCentralControlMac(centralControl.getMac());
        }
        initCrashHandler(false);
        AppStatusTracker.init(app);
        registerActivityLifecycleCallbacks(getMDefActivityLifecycle());
        registerActivityLifecycleCallbacks(new NotCanCallActivityLifecycle());
    }

    private final void initNet() {
        App app = this;
        MMKVManager.INSTANCE.getINSTANCE().init(app);
        PdNetworkManager.f10310INSTANCE.init(app, new Function1<PdNetworkManagerBuilder, Unit>() { // from class: com.pudutech.bumblebee.App$initNet$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PdNetworkManagerBuilder pdNetworkManagerBuilder) {
                invoke2(pdNetworkManagerBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PdNetworkManagerBuilder receiver) {
                String str;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                receiver.setEnvironment(UrlManager.INSTANCE.getTestType());
                if (Constans.INSTANCE.getServerInfo().length() > 0) {
                    ServerInfoBean serverInfoBean = (ServerInfoBean) GsonUtils.fromLocalJson(Constans.INSTANCE.getServerInfo(), ServerInfoBean.class);
                    if (serverInfoBean == null || (str = serverInfoBean.getHost()) == null) {
                        str = "";
                    }
                    receiver.setForceBaseUrl(str);
                }
                receiver.setProxyLog(new ILog() { // from class: com.pudutech.bumblebee.App$initNet$1.1
                    @Override // com.pudutech.pd_network.log.ILog
                    /* renamed from: d */
                    public void mo3278d(String tag, String content) {
                        Intrinsics.checkParameterIsNotNull(tag, "tag");
                        Intrinsics.checkParameterIsNotNull(content, "content");
                        Pdlog.m3273d(tag, content);
                        Log.d("initMain", content);
                    }

                    @Override // com.pudutech.pd_network.log.ILog
                    /* renamed from: e */
                    public void mo3279e(String tag, String content) {
                        Intrinsics.checkParameterIsNotNull(tag, "tag");
                        Intrinsics.checkParameterIsNotNull(content, "content");
                        Pdlog.m3274e(tag, content);
                        Log.e("initMain", content);
                    }

                    @Override // com.pudutech.pd_network.log.ILog
                    /* renamed from: i */
                    public void mo3280i(String tag, String content) {
                        Intrinsics.checkParameterIsNotNull(tag, "tag");
                        Intrinsics.checkParameterIsNotNull(content, "content");
                        Pdlog.m3275i(tag, content);
                        Log.i("initMain", content);
                    }

                    @Override // com.pudutech.pd_network.log.ILog
                    /* renamed from: w */
                    public void mo3281w(String tag, String content) {
                        Intrinsics.checkParameterIsNotNull(tag, "tag");
                        Intrinsics.checkParameterIsNotNull(content, "content");
                        Pdlog.m3277w(tag, content);
                        Log.w("initMain", content);
                    }
                });
            }
        });
    }

    public final void setQuestPerssionStatus() {
        this.quested_perssion = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: App.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u000b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u001c\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\u000e\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u000f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/App$DefActivityLifecycle;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "(Lcom/pudutech/bumblebee/App;)V", "onActivityCreated", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "Bumblebee-6.12.0.10-1-2023-07-12_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final class DefActivityLifecycle implements Application.ActivityLifecycleCallbacks {
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }

        public DefActivityLifecycle() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            if (App.this.checkNoCall(activity)) {
                Behavior behavior = Behavior.INSTANCE;
                behavior.setNoCanCall(behavior.isNoCanCall() + 1);
                RobotStatusManager.INSTANCE.isBusyState(true);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (App.this.checkNoCall(activity)) {
                Behavior.INSTANCE.setNoCanCall(r2.isNoCanCall() - 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkNoCall(Activity activity) {
        String localClassName;
        if (activity == null || (localClassName = activity.getLocalClassName()) == null) {
            return false;
        }
        String str = localClassName;
        return StringsKt.contains$default((CharSequence) str, (CharSequence) "mpmodule.HomeActivity", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "activity.MirSDKActivity", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "greeter.GreeterFaceActivity", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "greeter.GreeterMenuActivity", false, 2, (Object) null);
    }

    private final void initCrashHandler(boolean debug) {
        CrashHandler.getInstance().init(this, debug, false, 0L, Welcome.class);
        App app = this;
        CrashReportConfig.INSTANCE.initCrashReport(app, "0bd45c7138", debug);
        initLog();
        RobotContext.init(app);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String processName() {
        Object systemService = getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) systemService).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == Process.myPid()) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return null;
    }

    private final void initLog() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new App$initLog$1(this, null), 2, null);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new App$initLog$2(this, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getMac() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            Log.d(this.TAG, "interfaces " + networkInterfaces);
            Iterator it = Collections.list(networkInterfaces).iterator();
            while (it.hasNext()) {
                NetworkInterface nif = (NetworkInterface) it.next();
                String str = this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("nif name ");
                Intrinsics.checkExpressionValueIsNotNull(nif, "nif");
                sb.append(nif.getName());
                Log.d(str, sb.toString());
                if (StringsKt.equals(nif.getName(), "wlan0", true)) {
                    byte[] hardwareAddress = nif.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return;
                    }
                    StringBuilder sb2 = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        Object[] objArr = {Byte.valueOf(b)};
                        String format = String.format("%02X:", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                        sb2.append(format);
                    }
                    if (sb2.length() > 0) {
                        sb2.deleteCharAt(sb2.length() - 1);
                    }
                    String sb3 = sb2.toString();
                    Intrinsics.checkExpressionValueIsNotNull(sb3, "mac_str.toString()");
                    this.MAC = sb3;
                }
            }
        } catch (Exception e) {
            String str2 = this.TAG;
            StringBuilder sb4 = new StringBuilder();
            sb4.append("cannot get MAC, exception ");
            sb4.append(e.getMessage());
            sb4.append(' ');
            StackTraceElement[] stackTrace = e.getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "ex.stackTrace");
            sb4.append(ArraysKt.contentDeepToString(stackTrace));
            Log.e(str2, sb4.toString());
        }
    }
}
