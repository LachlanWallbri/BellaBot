package com.pudutech.peanut.robot_ui.manager;

import android.content.Context;
import android.util.ArrayMap;
import androidx.core.app.NotificationCompat;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_wrapper.MapingFuntionManager;
import com.pudutech.freeinstall_wrapper.MappingServiceConnectionListener;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.mirsdkwrap.lib.interf.ServiceConnectionListener;
import com.pudutech.mirsdkwrap.lib.robot.MachineInfoHelper;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.extend.RobotPeripheralsFactoryExtKt;
import com.pudutech.peanut.robot_ui.manager.InitRobotAppManager;
import com.pudutech.remotemaintenance.config.IoTConfig;
import com.pudutech.resources.language.LanguageUtils;
import com.pudutech.robot.module.report.task.ReportSelfCheck;
import com.pudutech.robot.peripherals.RobotPeripheralsFactory;
import com.pudutech.robot.peripherals.interf.IShutdownEventListener;
import com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: InitRobotAppManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f*\u0004&),1\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002ABB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u00105\u001a\u00020\u00152!\u00106\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u0010J\u000e\u00107\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u0018J\u0016\u00109\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u000fj\b\u0012\u0004\u0012\u00020\u0004`\u0016J\u0006\u0010:\u001a\u00020\u0011J\u000e\u0010;\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010<\u001a\u00020\u0015H\u0002J)\u0010=\u001a\u00020\u00152!\u00106\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u0010J\u000e\u0010>\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u0018J&\u0010.\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\r2\b\u0010?\u001a\u0004\u0018\u00010\u001b2\b\u0010@\u001a\u0004\u0018\u00010\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u00040\fX\u0082\u0004¢\u0006\u0002\n\u0000RT\u0010\u000e\u001aH\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u00100\u000fj#\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u0010`\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00180\u000fj\b\u0012\u0004\u0012\u00020\u0018`\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u001b0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001e\"\u0004\b\u001f\u0010 R \u0010\"\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u001d8F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u001a\u0010#\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001e\"\u0004\b$\u0010 R\u0010\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0004\n\u0002\u0010'R\u0010\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0004\n\u0002\u0010*R\u0010\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0004\n\u0002\u0010-R\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0004\n\u0002\u00102R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006C"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/InitRobotAppManager;", "", "()V", "TAG", "", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", IoTConfig.PARAM_ERROR_MSG, "Landroid/util/ArrayMap;", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "initResultListener", "Ljava/util/ArrayList;", "Lkotlin/Function1;", "Lcom/pudutech/peanut/robot_ui/manager/InitRobotAppManager$InitStatus;", "Lkotlin/ParameterName;", "name", "step", "", "Lkotlin/collections/ArrayList;", "initShutDownListener", "Lcom/pudutech/peanut/robot_ui/manager/InitRobotAppManager$ShutDownListener;", "initStatus", "initSteps", "Lcom/pudutech/mirsdk/aidl/serialize/StepState;", "isMeetJump", "", "()Z", "setMeetJump", "(Z)V", "<set-?>", "isNeedSetLanguage", "isSyncFinish", "setSyncFinish", "mappingServiceConnectionListener", "com/pudutech/peanut/robot_ui/manager/InitRobotAppManager$mappingServiceConnectionListener$1", "Lcom/pudutech/peanut/robot_ui/manager/InitRobotAppManager$mappingServiceConnectionListener$1;", "mirInitListener", "com/pudutech/peanut/robot_ui/manager/InitRobotAppManager$mirInitListener$1", "Lcom/pudutech/peanut/robot_ui/manager/InitRobotAppManager$mirInitListener$1;", "mirsdkConnectListener", "com/pudutech/peanut/robot_ui/manager/InitRobotAppManager$mirsdkConnectListener$1", "Lcom/pudutech/peanut/robot_ui/manager/InitRobotAppManager$mirsdkConnectListener$1;", "report", "Lcom/pudutech/robot/module/report/task/ReportSelfCheck;", "shutdownEventListener", "com/pudutech/peanut/robot_ui/manager/InitRobotAppManager$shutdownEventListener$1", "Lcom/pudutech/peanut/robot_ui/manager/InitRobotAppManager$shutdownEventListener$1;", "timeoutJob", "Lkotlinx/coroutines/Job;", "addInitResultListener", "l", "addShutDownListener", "listener", "getErrors", "getInitResult", "init", "notifyResult", "removeInitResultListener", "removeShotDownListener", "state", NotificationCompat.CATEGORY_MESSAGE, "InitStatus", "ShutDownListener", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class InitRobotAppManager {
    public static Context context;
    private static boolean isMeetJump;
    private static boolean isNeedSetLanguage;
    private static boolean isSyncFinish;
    private static Job timeoutJob;
    public static final InitRobotAppManager INSTANCE = new InitRobotAppManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static InitStatus initStatus = InitStatus.IDLE;
    private static final ArrayMap<InitStep, StepState> initSteps = new ArrayMap<>();
    private static final ArrayMap<InitStep, String> errorMsg = new ArrayMap<>();
    private static final InitRobotAppManager$mirsdkConnectListener$1 mirsdkConnectListener = new ServiceConnectionListener() { // from class: com.pudutech.peanut.robot_ui.manager.InitRobotAppManager$mirsdkConnectListener$1
        @Override // com.pudutech.mirsdkwrap.lib.interf.ServiceConnectionListener
        public void onServiceConnected() {
            String str;
            InitRobotAppManager initRobotAppManager = InitRobotAppManager.INSTANCE;
            str = InitRobotAppManager.TAG;
            Pdlog.m3273d(str, "onServiceConnected ");
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.ServiceConnectionListener
        public void onServiceDisconnected() {
            String str;
            InitRobotAppManager initRobotAppManager = InitRobotAppManager.INSTANCE;
            str = InitRobotAppManager.TAG;
            Pdlog.m3274e(str, "onServiceDisconnected ");
        }
    };
    private static final InitRobotAppManager$mappingServiceConnectionListener$1 mappingServiceConnectionListener = new MappingServiceConnectionListener() { // from class: com.pudutech.peanut.robot_ui.manager.InitRobotAppManager$mappingServiceConnectionListener$1
        @Override // com.pudutech.freeinstall_wrapper.MappingServiceConnectionListener
        public void onMappingServiceConnected() {
            String str;
            InitRobotAppManager initRobotAppManager = InitRobotAppManager.INSTANCE;
            str = InitRobotAppManager.TAG;
            Pdlog.m3273d(str, "onMappingServiceConnected ");
        }

        @Override // com.pudutech.freeinstall_wrapper.MappingServiceConnectionListener
        public void onMappingServiceDisconnected() {
            String str;
            InitRobotAppManager initRobotAppManager = InitRobotAppManager.INSTANCE;
            str = InitRobotAppManager.TAG;
            Pdlog.m3274e(str, "onMappingServiceDisconnected ");
        }
    };
    private static final InitRobotAppManager$shutdownEventListener$1 shutdownEventListener = new IShutdownEventListener() { // from class: com.pudutech.peanut.robot_ui.manager.InitRobotAppManager$shutdownEventListener$1
        @Override // com.pudutech.robot.peripherals.interf.IShutdownEventListener
        public void onShutdownConfirm() {
            ArrayList arrayList;
            InitRobotAppManager initRobotAppManager = InitRobotAppManager.INSTANCE;
            arrayList = InitRobotAppManager.initShutDownListener;
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((InitRobotAppManager.ShutDownListener) it.next()).shutDownNotify();
                }
            }
        }
    };
    private static final ArrayList<ShutDownListener> initShutDownListener = new ArrayList<>();
    private static final ArrayList<Function1<InitStatus, Unit>> initResultListener = new ArrayList<>();
    private static final InitRobotAppManager$mirInitListener$1 mirInitListener = new Function3<InitStep, StepState, String, Unit>() { // from class: com.pudutech.peanut.robot_ui.manager.InitRobotAppManager$mirInitListener$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(InitStep initStep, StepState stepState, String str) {
            invoke2(initStep, stepState, str);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(InitStep step, StepState state, String msg) {
            String str;
            InitRobotAppManager.InitStatus initStatus2;
            String str2;
            Job job;
            String str3;
            Job job2;
            String str4;
            String str5;
            Job job3;
            InitRobotAppManager$shutdownEventListener$1 initRobotAppManager$shutdownEventListener$1;
            Job job4;
            Job job5;
            Job job6;
            Job launch$default;
            String str6;
            Job job7;
            ArrayMap arrayMap;
            ArrayMap arrayMap2;
            InitRobotAppManager initRobotAppManager = InitRobotAppManager.INSTANCE;
            str = InitRobotAppManager.TAG;
            Pdlog.m3273d(str, "invoke : step = " + step + "; state = " + state + "; msg = " + msg + "; isActive=" + MirSdkManager.INSTANCE.isActive());
            InitRobotAppManager initRobotAppManager2 = InitRobotAppManager.INSTANCE;
            initStatus2 = InitRobotAppManager.initStatus;
            if (initStatus2 == InitRobotAppManager.InitStatus.TIMEOUT) {
                return;
            }
            InitRobotAppManager.INSTANCE.report(step, state, msg);
            if (step != null) {
                InitRobotAppManager initRobotAppManager3 = InitRobotAppManager.INSTANCE;
                arrayMap2 = InitRobotAppManager.initSteps;
            }
            if (state == StepState.Fail) {
                InitRobotAppManager initRobotAppManager4 = InitRobotAppManager.INSTANCE;
                arrayMap = InitRobotAppManager.errorMsg;
                arrayMap.put(step, msg);
            }
            InitRobotAppManager initRobotAppManager5 = InitRobotAppManager.INSTANCE;
            str2 = InitRobotAppManager.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("time job = ");
            InitRobotAppManager initRobotAppManager6 = InitRobotAppManager.INSTANCE;
            job = InitRobotAppManager.timeoutJob;
            sb.append(job);
            Pdlog.m3273d(str2, sb.toString());
            if (MirSdkManager.INSTANCE.isActive() != null) {
                Boolean isActive = MirSdkManager.INSTANCE.isActive();
                if (isActive == null) {
                    Intrinsics.throwNpe();
                }
                if (isActive.booleanValue() && !InitRobotAppManager.INSTANCE.isSyncFinish()) {
                    InitRobotAppManager initRobotAppManager7 = InitRobotAppManager.INSTANCE;
                    job6 = InitRobotAppManager.timeoutJob;
                    if (job6 == null) {
                        InitRobotAppManager initRobotAppManager8 = InitRobotAppManager.INSTANCE;
                        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new InitRobotAppManager$mirInitListener$1$invoke$2(null), 2, null);
                        InitRobotAppManager.timeoutJob = launch$default;
                        InitRobotAppManager initRobotAppManager9 = InitRobotAppManager.INSTANCE;
                        str6 = InitRobotAppManager.TAG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("time job2 = ");
                        InitRobotAppManager initRobotAppManager10 = InitRobotAppManager.INSTANCE;
                        job7 = InitRobotAppManager.timeoutJob;
                        sb2.append(job7);
                        Pdlog.m3273d(str6, sb2.toString());
                    }
                }
            }
            if (step == InitStep.Finish && state == StepState.Success) {
                InitRobotAppManager initRobotAppManager11 = InitRobotAppManager.INSTANCE;
                job5 = InitRobotAppManager.timeoutJob;
                if (job5 != null) {
                    Job.DefaultImpls.cancel$default(job5, (CancellationException) null, 1, (Object) null);
                }
                InitRobotAppManager initRobotAppManager12 = InitRobotAppManager.INSTANCE;
                InitRobotAppManager.initStatus = InitRobotAppManager.InitStatus.SUCCESS;
                if (!InitRobotAppManager.INSTANCE.isSyncFinish()) {
                    InitRobotAppManager.INSTANCE.setSyncFinish(true);
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new InitRobotAppManager$mirInitListener$1$invoke$3(null), 2, null);
                }
                InitRobotAppManager.INSTANCE.notifyResult();
                return;
            }
            if (step == InitStep.Finish && state == StepState.Fail) {
                InitRobotAppManager.INSTANCE.setSyncFinish(true);
                InitRobotAppManager initRobotAppManager13 = InitRobotAppManager.INSTANCE;
                job4 = InitRobotAppManager.timeoutJob;
                if (job4 != null) {
                    Job.DefaultImpls.cancel$default(job4, (CancellationException) null, 1, (Object) null);
                }
                InitRobotAppManager initRobotAppManager14 = InitRobotAppManager.INSTANCE;
                InitRobotAppManager.initStatus = InitRobotAppManager.InitStatus.FAILED;
                InitRobotAppManager.INSTANCE.notifyResult();
                return;
            }
            if (step == InitStep.CheckCAN && state == StepState.Success) {
                if (Intrinsics.areEqual("peanut", "mock")) {
                    RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).init(RobotContext.INSTANCE.getContext(), true);
                } else {
                    RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).init(RobotContext.INSTANCE.getContext(), false);
                }
                IPeanutRobotPeripherals iPeanutRobotPeripherals = RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE);
                InitRobotAppManager initRobotAppManager15 = InitRobotAppManager.INSTANCE;
                initRobotAppManager$shutdownEventListener$1 = InitRobotAppManager.shutdownEventListener;
                iPeanutRobotPeripherals.addShutdownEventListener(initRobotAppManager$shutdownEventListener$1);
                RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).addHardWareConnectListener(new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.manager.InitRobotAppManager$mirInitListener$1$invoke$4
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z) {
                        RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).removeHardWareConnectListener();
                        LightPlayManager.INSTANCE.playInit();
                    }
                });
                return;
            }
            if (step == InitStep.EmptyMap && state == StepState.Success) {
                InitRobotAppManager initRobotAppManager16 = InitRobotAppManager.INSTANCE;
                str5 = InitRobotAppManager.TAG;
                Pdlog.m3273d(str5, "invoke : step = " + step);
                InitRobotAppManager.INSTANCE.setSyncFinish(true);
                InitRobotAppManager initRobotAppManager17 = InitRobotAppManager.INSTANCE;
                job3 = InitRobotAppManager.timeoutJob;
                if (job3 != null) {
                    Job.DefaultImpls.cancel$default(job3, (CancellationException) null, 1, (Object) null);
                }
                InitRobotAppManager initRobotAppManager18 = InitRobotAppManager.INSTANCE;
                InitRobotAppManager.initStatus = InitRobotAppManager.InitStatus.EmptyMap;
                InitRobotAppManager.INSTANCE.notifyResult();
                return;
            }
            if (step == InitStep.NoToPoMap && state == StepState.Success) {
                InitRobotAppManager initRobotAppManager19 = InitRobotAppManager.INSTANCE;
                str4 = InitRobotAppManager.TAG;
                Pdlog.m3273d(str4, "invoke : step = " + step);
                InitRobotAppManager initRobotAppManager20 = InitRobotAppManager.INSTANCE;
                InitRobotAppManager.initStatus = InitRobotAppManager.InitStatus.NoTopMap;
                InitRobotAppManager.INSTANCE.setMeetJump(true);
                return;
            }
            if (step == InitStep.ConnectCoreService && state == StepState.Success) {
                InitRobotAppManager initRobotAppManager21 = InitRobotAppManager.INSTANCE;
                str3 = InitRobotAppManager.TAG;
                Pdlog.m3273d(str3, "invoke : step = 呵呵呵呵------" + step);
                if (InitRobotAppManager.INSTANCE.isMeetJump()) {
                    InitRobotAppManager.INSTANCE.setSyncFinish(true);
                    InitRobotAppManager initRobotAppManager22 = InitRobotAppManager.INSTANCE;
                    job2 = InitRobotAppManager.timeoutJob;
                    if (job2 != null) {
                        Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
                    }
                    InitRobotAppManager.INSTANCE.setMeetJump(false);
                    InitRobotAppManager.INSTANCE.notifyResult();
                }
            }
        }
    };
    private static final ReportSelfCheck report = new ReportSelfCheck();

    /* compiled from: InitRobotAppManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/InitRobotAppManager$InitStatus;", "", "(Ljava/lang/String;I)V", "IDLE", "INIT", "FAILED", "SUCCESS", "TIMEOUT", "EmptyMap", "NoTopMap", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum InitStatus {
        IDLE,
        INIT,
        FAILED,
        SUCCESS,
        TIMEOUT,
        EmptyMap,
        NoTopMap
    }

    /* compiled from: InitRobotAppManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/InitRobotAppManager$ShutDownListener;", "", "shutDownNotify", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface ShutDownListener {
        void shutDownNotify();
    }

    private InitRobotAppManager() {
    }

    public final boolean isNeedSetLanguage() {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return new LanguageUtils(context2).getNeedSet();
    }

    public final Context getContext() {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public final void setContext(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "<set-?>");
        context = context2;
    }

    public final boolean isMeetJump() {
        return isMeetJump;
    }

    public final void setMeetJump(boolean z) {
        isMeetJump = z;
    }

    public final void addShutDownListener(ShutDownListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (initShutDownListener.contains(listener)) {
            return;
        }
        initShutDownListener.add(listener);
    }

    public final void removeShotDownListener(ShutDownListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        initShutDownListener.remove(listener);
    }

    public final boolean isSyncFinish() {
        return isSyncFinish;
    }

    public final void setSyncFinish(boolean z) {
        isSyncFinish = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void report(InitStep step, StepState state, String msg) {
        if (step != null) {
            if (state == StepState.Fail) {
                report.addStep(step.name(), false, msg);
            } else if (state == StepState.Success) {
                report.addStep(step.name(), true, msg);
            }
            if (step == InitStep.Finish) {
                if (state == StepState.Fail || state == StepState.Success) {
                    MachineInfoHelper.MachineInfos machineInfo = MachineInfoHelper.INSTANCE.getMachineInfo();
                    if (machineInfo != null) {
                        ReportSelfCheck reportSelfCheck = report;
                        String json = new Gson().toJson(machineInfo);
                        Intrinsics.checkExpressionValueIsNotNull(json, "Gson().toJson(m)");
                        reportSelfCheck.setMachineInfo(json);
                    }
                    report.report();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyResult() {
        Iterator<T> it = initResultListener.iterator();
        while (it.hasNext()) {
            Function1 function1 = (Function1) it.next();
            Pdlog.m3273d(TAG, "invoke : step = notifyResult " + function1);
            function1.invoke(initStatus);
        }
    }

    public final void init(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        if (initStatus != InitStatus.IDLE) {
            Pdlog.m3273d(TAG, "is Init do not be init once more ");
            return;
        }
        context = context2;
        initStatus = InitStatus.INIT;
        MirSdkManager.INSTANCE.chooseLaserBeforeConnectService(LocateCase.LaserMark);
        if (Intrinsics.areEqual("peanut", "mock")) {
            MirSdkManager.INSTANCE.init(context2, true);
        } else {
            MirSdkManager.INSTANCE.init(context2, false);
        }
        MapingFuntionManager.INSTANCE.init(context2, mappingServiceConnectionListener, true);
        MirSdkManager.INSTANCE.connectService(mirsdkConnectListener, mirInitListener);
    }

    public final void addInitResultListener(Function1<? super InitStatus, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        if (initResultListener.contains(l)) {
            return;
        }
        initResultListener.add(l);
    }

    public final void removeInitResultListener(Function1<? super InitStatus, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        initResultListener.remove(l);
    }

    public final InitStatus getInitResult() {
        return initStatus;
    }

    public final ArrayList<String> getErrors() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Map.Entry<InitStep, String> entry : errorMsg.entrySet()) {
            String value = entry.getValue();
            if (!(value == null || StringsKt.isBlank(value))) {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("errorMsg ");
                sb.append((entry.getKey().name() + " : ") + entry.getValue());
                Pdlog.m3273d(str, sb.toString());
                arrayList.add((entry.getKey().name() + " : ") + entry.getValue());
            }
        }
        return arrayList;
    }
}
