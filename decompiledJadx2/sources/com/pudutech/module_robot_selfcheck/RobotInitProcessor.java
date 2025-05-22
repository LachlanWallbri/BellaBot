package com.pudutech.module_robot_selfcheck;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.disinfect.baselib.ext.util.StringExtKt;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.util.MMKVManager;
import com.pudutech.disinfect.baselib.util.PackageUtil;
import com.pudutech.freeinstall_wrapper.MapingFuntionManager;
import com.pudutech.freeinstall_wrapper.MappingServiceConnectionListener;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.mirsdkwrap.lib.interf.ServiceConnectionListener;
import com.pudutech.mirsdkwrap.lib.robot.MachineInfoHelper;
import com.pudutech.remotemaintenance.config.IoTConfig;
import com.pudutech.robot.module.report.task.ReportSelfCheck;
import com.pudutech.robot.update.AppUpdateManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: RobotInitProcessor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0089\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018*\u0001+\u0018\u0000 O2\u00020\u00012Q\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0002j\u0002`\f:\u0003OPQB\u0007\b\u0002¢\u0006\u0002\u0010\rJ\u000e\u0010;\u001a\u00020\u000b2\u0006\u0010<\u001a\u00020/J)\u0010=\u001a\u00020\u000b2!\u0010<\u001a\u001d\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u000b02J;\u0010>\u001a\u00020\u000b23\u0010<\u001a/\u0012!\u0012\u001f\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0015¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u000b02j\u0002`7J\u0010\u0010?\u001a\u00020\u000b2\u0006\u0010@\u001a\u00020\u001dH\u0002J\b\u0010A\u001a\u00020\u000bH\u0002J\u0010\u0010B\u001a\u00020\u000b2\u0006\u0010C\u001a\u00020\u0017H\u0002J\u0014\u0010D\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0015J\u0006\u0010E\u001a\u00020\u001dJ\u000e\u0010F\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fJ'\u0010G\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0096\u0002J\u000e\u0010H\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010I\u001a\u00020\u000bH\u0002J\b\u0010J\u001a\u00020\u000bH\u0016J\b\u0010K\u001a\u00020\u000bH\u0016J\u000e\u0010L\u001a\u00020\u000b2\u0006\u0010<\u001a\u00020/J)\u0010M\u001a\u00020\u000b2!\u0010<\u001a\u001d\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u000b02J;\u0010N\u001a\u00020\u000b23\u0010<\u001a/\u0012!\u0012\u001f\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0015¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u000b02j\u0002`7J&\u00103\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002R\u001a\u0010\u000e\u001a\u00020\u000fX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0019\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010#\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b#\u0010\u0019R\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0010\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0004\n\u0002\u0010,R\u001e\u0010-\u001a\u0012\u0012\u0004\u0012\u00020/0.j\b\u0012\u0004\u0012\u00020/`0X\u0082\u0004¢\u0006\u0002\n\u0000RT\u00101\u001aH\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u000b020.j#\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u000b02`0X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u0004¢\u0006\u0002\n\u0000Rx\u00105\u001al\u00121\u0012/\u0012!\u0012\u001f\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0015¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u000b02j\u0002`70.j5\u00121\u0012/\u0012!\u0012\u001f\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0015¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u000b02j\u0002`7`0X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0010\u00109\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006R"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/RobotInitProcessor;", "Lcom/pudutech/mirsdkwrap/lib/interf/ServiceConnectionListener;", "Lkotlin/Function3;", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "Lkotlin/ParameterName;", "name", "step", "Lcom/pudutech/mirsdk/aidl/serialize/StepState;", "state", "", NotificationCompat.CATEGORY_MESSAGE, "", "Lcom/pudutech/freeinstall_wrapper/InitStepListener;", "()V", "context", "Landroid/content/Context;", "getContext$module_robot_selfcheck_release", "()Landroid/content/Context;", "setContext$module_robot_selfcheck_release", "(Landroid/content/Context;)V", IoTConfig.PARAM_ERROR_MSG, "Landroidx/collection/ArrayMap;", "hasTestFile", "", "getHasTestFile", "()Z", "hasTestFile$delegate", "Lkotlin/Lazy;", "initStatus", "Lcom/pudutech/module_robot_selfcheck/RobotInitProcessor$InitStatus;", "initSteps", "isMeetJump", "setMeetJump", "(Z)V", "isSyncFinish", "isTest", "locationType", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "getLocationType", "()Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "setLocationType", "(Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;)V", "mappingServiceConnectionListener", "com/pudutech/module_robot_selfcheck/RobotInitProcessor$mappingServiceConnectionListener$1", "Lcom/pudutech/module_robot_selfcheck/RobotInitProcessor$mappingServiceConnectionListener$1;", "onInitProcessListeners", "Ljava/util/ArrayList;", "Lcom/pudutech/module_robot_selfcheck/RobotInitProcessor$OnInitProcessListener;", "Lkotlin/collections/ArrayList;", "onInitStateListeners", "Lkotlin/Function1;", "report", "Lcom/pudutech/robot/module/report/task/ReportSelfCheck;", "selfCheckFailureListeners", "errorMap", "Lcom/pudutech/module_robot_selfcheck/OnSelfCheckFailListener;", "testServerFile", "timeoutJob", "Lkotlinx/coroutines/Job;", "addOnInitProcessListener", "listener", "addOnInitStateListener", "addOnSelfCheckFailureListener", "callbackInitState", "initState", "callbackSelfCheckFail", "enableCPU", "onOrOff", "getErrorMsgs", "getInitState", "init", "invoke", "killMirSdk", "loadMap", "onServiceConnected", "onServiceDisconnected", "removeOnInitProcessListener", "removeOnInitStateListener", "removeSelfCheckFailureListener", "Companion", "InitStatus", "OnInitProcessListener", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RobotInitProcessor implements ServiceConnectionListener, Function3<InitStep, StepState, String, Unit> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<RobotInitProcessor>() { // from class: com.pudutech.module_robot_selfcheck.RobotInitProcessor$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final RobotInitProcessor invoke() {
            return new RobotInitProcessor(null);
        }
    });
    private static final String TAG = "RobotInitProcessor";
    public Context context;
    private final ArrayMap<InitStep, String> errorMsg;

    /* renamed from: hasTestFile$delegate, reason: from kotlin metadata */
    private final Lazy hasTestFile;
    private InitStatus initStatus;
    private final ArrayMap<InitStep, StepState> initSteps;
    private boolean isMeetJump;
    private boolean isSyncFinish;
    private LocateCase locationType;
    private final RobotInitProcessor$mappingServiceConnectionListener$1 mappingServiceConnectionListener;
    private final ArrayList<OnInitProcessListener> onInitProcessListeners;
    private final ArrayList<Function1<InitStatus, Unit>> onInitStateListeners;
    private final ReportSelfCheck report;
    private final ArrayList<Function1<ArrayMap<InitStep, String>, Unit>> selfCheckFailureListeners;
    private final String testServerFile;
    private Job timeoutJob;

    /* compiled from: RobotInitProcessor.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/RobotInitProcessor$InitStatus;", "", "(Ljava/lang/String;I)V", "IDLE", "INIT", "FAILED", "CHECKCAN", "SUCCESS", "TIMEOUT", "EmptyMap", "NoTopMap", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum InitStatus {
        IDLE,
        INIT,
        FAILED,
        CHECKCAN,
        SUCCESS,
        TIMEOUT,
        EmptyMap,
        NoTopMap
    }

    /* compiled from: RobotInitProcessor.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/RobotInitProcessor$OnInitProcessListener;", "", "onCanInitSuccess", "", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface OnInitProcessListener {
        void onCanInitSuccess();
    }

    private final boolean getHasTestFile() {
        return ((Boolean) this.hasTestFile.getValue()).booleanValue();
    }

    /* JADX WARN: Type inference failed for: r0v11, types: [com.pudutech.module_robot_selfcheck.RobotInitProcessor$mappingServiceConnectionListener$1] */
    private RobotInitProcessor() {
        this.testServerFile = "/sdcard/TestServer";
        this.hasTestFile = LazyKt.lazy(new Function0<Boolean>() { // from class: com.pudutech.module_robot_selfcheck.RobotInitProcessor$hasTestFile$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Boolean invoke() {
                return Boolean.valueOf(invoke2());
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2() {
                String str;
                str = RobotInitProcessor.this.testServerFile;
                return new File(str).exists();
            }
        });
        this.locationType = LocateCase.LaserMark;
        this.initStatus = InitStatus.IDLE;
        this.initSteps = new ArrayMap<>();
        this.errorMsg = new ArrayMap<>();
        this.onInitStateListeners = new ArrayList<>();
        this.onInitProcessListeners = new ArrayList<>();
        this.selfCheckFailureListeners = new ArrayList<>();
        this.mappingServiceConnectionListener = new MappingServiceConnectionListener() { // from class: com.pudutech.module_robot_selfcheck.RobotInitProcessor$mappingServiceConnectionListener$1
            @Override // com.pudutech.freeinstall_wrapper.MappingServiceConnectionListener
            public void onMappingServiceConnected() {
                Pdlog.m3273d("RobotInitProcessor", "onMappingServiceConnected ");
            }

            @Override // com.pudutech.freeinstall_wrapper.MappingServiceConnectionListener
            public void onMappingServiceDisconnected() {
                Pdlog.m3274e("RobotInitProcessor", "onMappingServiceDisconnected ");
            }
        };
        this.report = new ReportSelfCheck();
    }

    public /* synthetic */ RobotInitProcessor(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(InitStep initStep, StepState stepState, String str) {
        invoke2(initStep, stepState, str);
        return Unit.INSTANCE;
    }

    public final LocateCase getLocationType() {
        return this.locationType;
    }

    public final void setLocationType(LocateCase locateCase) {
        Intrinsics.checkParameterIsNotNull(locateCase, "<set-?>");
        this.locationType = locateCase;
    }

    public final boolean isTest() {
        try {
            return getHasTestFile();
        } catch (Exception unused) {
            return false;
        }
    }

    /* compiled from: RobotInitProcessor.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/RobotInitProcessor$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/module_robot_selfcheck/RobotInitProcessor;", "getINSTANCE", "()Lcom/pudutech/module_robot_selfcheck/RobotInitProcessor;", "INSTANCE$delegate", "Lkotlin/Lazy;", "TAG", "", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        public final RobotInitProcessor getINSTANCE() {
            Lazy lazy = RobotInitProcessor.INSTANCE$delegate;
            Companion companion = RobotInitProcessor.INSTANCE;
            return (RobotInitProcessor) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final Context getContext$module_robot_selfcheck_release() {
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context;
    }

    public final void setContext$module_robot_selfcheck_release(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (!PackageUtil.INSTANCE.isMainProcess(context)) {
            Pdlog.m3277w(TAG, "Is not the main process and cannot be init");
            return;
        }
        if (this.initStatus != InitStatus.IDLE) {
            Pdlog.m3277w(TAG, "The current state is not idle and cannot be init");
            return;
        }
        this.context = context;
        NetWorkApiManager.INSTANCE.setTestServer(isTest());
        AppUpdateManager.INSTANCE.init(context, isTest());
        MMKVManager.INSTANCE.getINSTANCE().init(context);
        Pdlog.m3273d(TAG, "Init begin");
        this.initStatus = InitStatus.INIT;
        MirSdkManager.INSTANCE.chooseLaserBeforeConnectService(this.locationType);
        MirSdkManager.INSTANCE.init(context, false);
        MapingFuntionManager.INSTANCE.init(context, this.mappingServiceConnectionListener, true);
        MirSdkManager.INSTANCE.connectService(this, this);
    }

    public final void killMirSdk(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) systemService).getRunningAppProcesses()) {
            Pdlog.m3273d("MyBaseActivity", "runningAppProcessInfo = " + runningAppProcessInfo.processName);
            if (Intrinsics.areEqual("com.pudutech.mirsdk", runningAppProcessInfo.processName)) {
                Pdlog.m3273d("MyBaseActivity", "kill = " + runningAppProcessInfo.processName + " ; pid = " + runningAppProcessInfo.pid);
                Process.killProcess(runningAppProcessInfo.pid);
            }
        }
    }

    private final void enableCPU(boolean onOrOff) {
        Pdlog.m3273d(TAG, "enableCPU onOrOff=" + onOrOff);
        try {
            Tools.execCommand("echo " + (onOrOff ? 1 : 0) + " > /sys/devices/system/cpu/cpu5/online", true);
            Tools.execCommand("echo " + (onOrOff ? 1 : 0) + " > /sys/devices/system/cpu/cpu4/online", true);
            Tools.execCommand("echo " + (onOrOff ? 1 : 0) + " > /sys/devices/system/cpu/cpu3/online", true);
        } catch (Exception e) {
            Pdlog.m3277w(TAG, "check your device support enableCPU or not. " + e);
        }
    }

    @Override // com.pudutech.mirsdkwrap.lib.interf.ServiceConnectionListener
    public void onServiceConnected() {
        Pdlog.m3273d(TAG, "MirSdk connection listener onServiceConnected()");
    }

    @Override // com.pudutech.mirsdkwrap.lib.interf.ServiceConnectionListener
    public void onServiceDisconnected() {
        Pdlog.m3273d(TAG, "MirSdk connection listener onServiceDisconnected()");
    }

    /* renamed from: isMeetJump, reason: from getter */
    public final boolean getIsMeetJump() {
        return this.isMeetJump;
    }

    public final void setMeetJump(boolean z) {
        this.isMeetJump = z;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(InitStep step, StepState state, String msg) {
        Job launch$default;
        Pdlog.m3273d(TAG, "invoke : step = " + step + "; state = " + state + "; msg = " + msg + "; isActive=" + MirSdkManager.INSTANCE.isActive());
        if (this.initStatus == InitStatus.TIMEOUT) {
            return;
        }
        report(step, state, msg);
        if (step != null) {
            this.initSteps.put(step, state);
        }
        if (state == StepState.Fail) {
            this.errorMsg.put(step, msg);
        }
        Pdlog.m3273d(TAG, "time job = " + this.timeoutJob);
        if (MirSdkManager.INSTANCE.isActive() != null) {
            Boolean isActive = MirSdkManager.INSTANCE.isActive();
            if (isActive == null) {
                Intrinsics.throwNpe();
            }
            if (isActive.booleanValue() && !this.isSyncFinish && this.timeoutJob == null) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new RobotInitProcessor$invoke$2(this, null), 2, null);
                this.timeoutJob = launch$default;
                Pdlog.m3273d(TAG, "time job2 = " + this.timeoutJob);
            }
        }
        if (step == InitStep.Finish && state == StepState.Success) {
            Job job = this.timeoutJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.initStatus = InitStatus.SUCCESS;
            callbackInitState(InitStatus.SUCCESS);
            loadMap();
            return;
        }
        if (step == InitStep.Finish && state == StepState.Fail) {
            Job job2 = this.timeoutJob;
            if (job2 != null) {
                Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
            }
            this.initStatus = InitStatus.FAILED;
            callbackInitState(InitStatus.FAILED);
            loadMap();
            return;
        }
        if (step == InitStep.CheckCAN && state == StepState.Success) {
            Pdlog.m3273d(TAG, "initStatus = " + step);
            this.initStatus = InitStatus.CHECKCAN;
            callbackInitState(InitStatus.CHECKCAN);
            return;
        }
        if (step == InitStep.EmptyMap && state == StepState.Success) {
            Pdlog.m3273d(TAG, "invoke : step = " + step);
            Job job3 = this.timeoutJob;
            if (job3 != null) {
                Job.DefaultImpls.cancel$default(job3, (CancellationException) null, 1, (Object) null);
            }
            this.initStatus = InitStatus.EmptyMap;
            callbackInitState(InitStatus.EmptyMap);
            loadMap();
            return;
        }
        if (step == InitStep.NoToPoMap && state == StepState.Success) {
            Pdlog.m3273d(TAG, "invoke : step = " + step);
            this.initStatus = InitStatus.NoTopMap;
            this.isMeetJump = true;
            return;
        }
        if (step == InitStep.ConnectCoreService && state == StepState.Success) {
            Pdlog.m3273d(TAG, "invoke : step = 呵呵呵呵------" + step);
            if (this.isMeetJump) {
                Job job4 = this.timeoutJob;
                if (job4 != null) {
                    Job.DefaultImpls.cancel$default(job4, (CancellationException) null, 1, (Object) null);
                }
                this.isMeetJump = false;
                callbackInitState(InitStatus.NoTopMap);
                loadMap();
            }
        }
    }

    private final void loadMap() {
        if (this.isSyncFinish) {
            return;
        }
        this.isSyncFinish = true;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new RobotInitProcessor$loadMap$1(null), 2, null);
    }

    private final void report(InitStep step, StepState state, String msg) {
        if (step != null) {
            if (state == StepState.Fail) {
                this.report.addStep(step.name(), false, msg);
            } else if (state == StepState.Success) {
                this.report.addStep(step.name(), true, msg);
            }
            if (step == InitStep.Finish) {
                if (state == StepState.Fail || state == StepState.Success) {
                    MachineInfoHelper.MachineInfos machineInfo = MachineInfoHelper.INSTANCE.getMachineInfo();
                    if (machineInfo != null) {
                        ReportSelfCheck reportSelfCheck = this.report;
                        String json = new Gson().toJson(machineInfo);
                        Intrinsics.checkExpressionValueIsNotNull(json, "Gson().toJson(m)");
                        reportSelfCheck.setMachineInfo(json);
                    }
                    this.report.report();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callbackInitState(InitStatus initState) {
        this.initStatus = initState;
        Pdlog.m3273d(TAG, "callbackInitState() initState = " + initState);
        Pdlog.json(TAG, StringExtKt.toJson(this.onInitStateListeners));
        callbackSelfCheckFail();
        Iterator<T> it = this.onInitStateListeners.iterator();
        while (it.hasNext()) {
            ((Function1) it.next()).invoke(initState);
        }
    }

    private final void callbackSelfCheckFail() {
        Iterator<T> it = this.selfCheckFailureListeners.iterator();
        while (it.hasNext()) {
            ((Function1) it.next()).invoke(this.errorMsg);
        }
    }

    public final ArrayMap<InitStep, String> getErrorMsgs() {
        return this.errorMsg;
    }

    public final void addOnSelfCheckFailureListener(Function1<? super ArrayMap<InitStep, String>, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (this.selfCheckFailureListeners.contains(listener)) {
            return;
        }
        this.selfCheckFailureListeners.add(listener);
    }

    public final void removeSelfCheckFailureListener(Function1<? super ArrayMap<InitStep, String>, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (this.selfCheckFailureListeners.contains(listener)) {
            this.selfCheckFailureListeners.remove(listener);
        }
    }

    public final void addOnInitStateListener(Function1<? super InitStatus, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (this.onInitStateListeners.contains(listener)) {
            return;
        }
        this.onInitStateListeners.add(listener);
    }

    public final void removeOnInitProcessListener(OnInitProcessListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (this.onInitProcessListeners.contains(listener)) {
            this.onInitProcessListeners.remove(listener);
        }
    }

    public final void addOnInitProcessListener(OnInitProcessListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (this.onInitProcessListeners.contains(listener)) {
            return;
        }
        this.onInitProcessListeners.add(listener);
    }

    public final void removeOnInitStateListener(Function1<? super InitStatus, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (this.onInitStateListeners.contains(listener)) {
            this.onInitStateListeners.remove(listener);
        }
    }

    /* renamed from: getInitState, reason: from getter */
    public final InitStatus getInitStatus() {
        return this.initStatus;
    }
}
