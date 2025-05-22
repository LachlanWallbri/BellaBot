package com.pudutech.mirsdkwrap.lib;

import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.CrossProcessTask;
import com.pudutech.mirsdk.aidl.DeviceInterface;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.serialize.BoardInfo;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.LidarDeviceInfo;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.RgbdDeviceInfo;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import com.pudutech.mirsdkwrap.lib.constant.Constant;
import com.pudutech.mirsdkwrap.lib.interf.ListenerList;
import com.pudutech.mirsdkwrap.lib.interf.ServiceConnectionListener;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import com.pudutech.mirsdkwrap.lib.robot.MachineInfoHelper;
import com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap;
import com.pudutech.mirsdkwrap.lib.robot.RobotConfig;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSdkManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010$\u001a\u00020\u00152\b\b\u0002\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010%\u001a\u00020\u0015Je\u0010&\u001a\u00020\u00152\u0006\u0010'\u001a\u00020(2U\u0010)\u001aQ\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\rj\u0002`\u0016J\u0016\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0018J\u0006\u0010/\u001a\u00020\u0004J\f\u00100\u001a\b\u0012\u0004\u0012\u00020201J\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u000401J\u0006\u00104\u001a\u00020\u0018J\f\u00105\u001a\b\u0012\u0004\u0012\u00020601J\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u000401J\f\u00108\u001a\b\u0012\u0004\u0012\u00020901J\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u000401J\b\u0010;\u001a\u0004\u0018\u00010\u0012J\b\u0010<\u001a\u0004\u0018\u00010\u000eJ\u0016\u0010=\u001a\u00020\u00152\u0006\u0010>\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0018J\b\u0010?\u001a\u00020\u0015H\u0002J_\u0010@\u001a\u00020\u00152U\u0010)\u001aQ\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\rj\u0002`\u0016H\u0002J\u0006\u0010A\u001a\u00020\u0018J\u000e\u0010B\u001a\u00020\u00182\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010C\u001a\u00020\u00152\u0006\u0010D\u001a\u00020\u0018J\u0006\u0010E\u001a\u00020\u0015J\u0016\u0010F\u001a\u00020\u00152\u0006\u0010G\u001a\u00020\u00182\u0006\u0010H\u001a\u00020\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bRa\u0010\f\u001aU\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0015\u0018\u00010\rj\u0004\u0018\u0001`\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010 \u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006I"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/MirSdkManager;", "", "()V", "TAG", "", "context", "Landroid/content/Context;", "<set-?>", "Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;", "deviceControlHelper", "getDeviceControlHelper", "()Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;", "initStepListener", "Lkotlin/Function3;", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "Lkotlin/ParameterName;", "name", "step", "Lcom/pudutech/mirsdk/aidl/serialize/StepState;", "state", NotificationCompat.CATEGORY_MESSAGE, "", "Lcom/pudutech/mirsdkwrap/lib/interf/InitStepListener;", "isConnecting", "", "isMock", "locateCase", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "mirSdk", "Lcom/pudutech/mirsdk/aidl/SDKInterface;", "mirSdkListenerWrap", "Lcom/pudutech/mirsdkwrap/lib/robot/MirSdkListenerWrap;", "mirsdkConnection", "Lcom/pudutech/base/architecture/AIDLConnection;", "sdkWorker", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "chooseLaserBeforeConnectService", "closeAuthMirSdk", "connectService", "connectionListener", "Lcom/pudutech/mirsdkwrap/lib/interf/ServiceConnectionListener;", "initListener", "controlWheelErrorEvent", "", "var1", "Lcom/pudutech/mirsdk/hardware/serialize/WheelError;", "var2", "getBatteryInfo", "getBoardInfo", "", "Lcom/pudutech/mirsdk/aidl/serialize/BoardInfo;", "getCameraInfo", "getDropDetStatus", "getLidarInfo", "Lcom/pudutech/mirsdk/aidl/serialize/LidarDeviceInfo;", "getLidarSn", "getRgbdInfo", "Lcom/pudutech/mirsdk/aidl/serialize/RgbdDeviceInfo;", "getRgbdSn", "getState", "getStep", "init", "appContext", "initMachineInfoHelper", "initSubModule", "isLocated", "jumpToDebugAc", "setEnableDropDet", "flag", "suspendChargingUsingPile", "switchSecurity", "oldState", "expectState", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MirSdkManager {
    private static Context context;
    private static DevicesControlHelper deviceControlHelper;
    private static Function3<? super InitStep, ? super StepState, ? super String, Unit> initStepListener;
    private static boolean isConnecting;
    private static boolean isMock;
    private static SDKInterface mirSdk;
    private static AIDLConnection<SDKInterface> mirsdkConnection;
    private static StepState state;
    private static InitStep step;
    public static final MirSdkManager INSTANCE = new MirSdkManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final ExecutorCoroutineDispatcher sdkWorker = ThreadPoolDispatcherKt.newSingleThreadContext("SDKWorker");
    private static LocateCase locateCase = LocateCase.Laser;
    private static final MirSdkListenerWrap mirSdkListenerWrap = new MirSdkListenerWrap();

    private MirSdkManager() {
    }

    public static final /* synthetic */ Context access$getContext$p(MirSdkManager mirSdkManager) {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public final DevicesControlHelper getDeviceControlHelper() {
        return deviceControlHelper;
    }

    public final InitStep getStep() {
        return step;
    }

    public final StepState getState() {
        return state;
    }

    public final void init(Context appContext, boolean isMock2) {
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        context = appContext;
        isMock = isMock2;
        Pdlog.m3273d(TAG, "init() isMock:" + isMock2);
    }

    public static /* synthetic */ void chooseLaserBeforeConnectService$default(MirSdkManager mirSdkManager, LocateCase locateCase2, int i, Object obj) {
        if ((i & 1) != 0) {
            locateCase2 = LocateCase.Laser;
        }
        mirSdkManager.chooseLaserBeforeConnectService(locateCase2);
    }

    public final void chooseLaserBeforeConnectService(LocateCase locateCase2) {
        Intrinsics.checkParameterIsNotNull(locateCase2, "locateCase");
        locateCase = locateCase2;
    }

    public final void connectService(final ServiceConnectionListener connectionListener, final Function3<? super InitStep, ? super StepState, ? super String, Unit> initListener) {
        Intrinsics.checkParameterIsNotNull(connectionListener, "connectionListener");
        Intrinsics.checkParameterIsNotNull(initListener, "initListener");
        if (isConnecting) {
            Pdlog.m3273d(TAG, "connectService : isConnecting ");
            return;
        }
        if (mirsdkConnection != null) {
            Pdlog.m3277w(TAG, "connectService mirsdkConnection not null ??");
            AIDLConnection<SDKInterface> aIDLConnection = mirsdkConnection;
            if (aIDLConnection != null) {
                Context context2 = context;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                aIDLConnection.disconnect(context2);
            }
            mirsdkConnection = (AIDLConnection) null;
        }
        final String mirsdkServer = Constant.INSTANCE.getMirsdkServer(isMock);
        final MirSdkManager$connectService$2 mirSdkManager$connectService$2 = MirSdkManager$connectService$2.INSTANCE;
        mirsdkConnection = new AIDLConnection<SDKInterface>(mirsdkServer, mirSdkManager$connectService$2) { // from class: com.pudutech.mirsdkwrap.lib.MirSdkManager$connectService$1
            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                String str;
                super.onServiceConnected(name, service);
                MirSdkManager mirSdkManager = MirSdkManager.INSTANCE;
                str = MirSdkManager.TAG;
                Pdlog.m3273d(str, "onServiceConnected : name = " + name + "; service = " + service + "; ");
                connectionListener.onServiceConnected();
                MirSdkManager.INSTANCE.initSubModule(initListener);
                MirSdkManager mirSdkManager2 = MirSdkManager.INSTANCE;
                MirSdkManager.isConnecting = false;
            }

            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName name) {
                String str;
                super.onBindingDied(name);
                MirSdkManager mirSdkManager = MirSdkManager.INSTANCE;
                str = MirSdkManager.TAG;
                Pdlog.m3273d(str, "onBindingDied name=" + name);
                MirSdkManager mirSdkManager2 = MirSdkManager.INSTANCE;
                MirSdkManager.isConnecting = false;
            }

            @Override // android.content.ServiceConnection
            public void onNullBinding(ComponentName name) {
                String str;
                super.onNullBinding(name);
                MirSdkManager mirSdkManager = MirSdkManager.INSTANCE;
                str = MirSdkManager.TAG;
                Pdlog.m3273d(str, "onNullBinding name=" + name);
                MirSdkManager mirSdkManager2 = MirSdkManager.INSTANCE;
                MirSdkManager.isConnecting = false;
            }

            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                String str;
                super.onServiceDisconnected(name);
                MirSdkManager mirSdkManager = MirSdkManager.INSTANCE;
                str = MirSdkManager.TAG;
                Pdlog.m3273d(str, "onServiceDisconnected name=" + name);
                MirSdkManager mirSdkManager2 = MirSdkManager.INSTANCE;
                MirSdkManager.isConnecting = false;
                connectionListener.onServiceDisconnected();
            }
        };
        isConnecting = true;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, sdkWorker, null, new MirSdkManager$connectService$3(null), 2, null);
    }

    public final boolean jumpToDebugAc(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        try {
            CrossProcessTask.INSTANCE.jumpActivity(context2, "com.pudutech.mirsdk", "com.pudutech.mirsdk.activity.MirSDKActivity");
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public final void suspendChargingUsingPile() {
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            sDKInterface.suspendCharingUsingPile();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void initSubModule(final Function3<? super InitStep, ? super StepState, ? super String, Unit> initListener) {
        AIDLConnection<SDKInterface> aIDLConnection = mirsdkConnection;
        mirSdk = aIDLConnection != null ? aIDLConnection.getInterface() : null;
        final SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            Pdlog.m3273d(TAG, "initSubModule sdk init start");
            sDKInterface.addLisener(Constant.KEY_MIR_SDK_LISTENER, mirSdkListenerWrap);
            initStepListener = new Function3<InitStep, StepState, String, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.MirSdkManager$initSubModule$$inlined$let$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(InitStep initStep, StepState stepState, String str) {
                    invoke2(initStep, stepState, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(InitStep initStep, StepState stepState, String str) {
                    String str2;
                    MirSdkListenerWrap mirSdkListenerWrap2;
                    MirSdkManager mirSdkManager = MirSdkManager.INSTANCE;
                    MirSdkManager.step = initStep;
                    MirSdkManager mirSdkManager2 = MirSdkManager.INSTANCE;
                    MirSdkManager.state = stepState;
                    if (initStep == InitStep.CheckMap && stepState == StepState.Success) {
                        MirSdkManager mirSdkManager3 = MirSdkManager.INSTANCE;
                        str2 = MirSdkManager.TAG;
                        Pdlog.m3273d(str2, "RobotMapManager.init()");
                        RobotMapManager robotMapManager = RobotMapManager.INSTANCE;
                        SDKInterface sDKInterface2 = SDKInterface.this;
                        MirSdkManager mirSdkManager4 = MirSdkManager.INSTANCE;
                        mirSdkListenerWrap2 = MirSdkManager.mirSdkListenerWrap;
                        robotMapManager.init$module_robot_mirsdk_wrapper_release(sDKInterface2, mirSdkListenerWrap2, MirSdkManager.access$getContext$p(MirSdkManager.INSTANCE));
                    }
                    if (initStep == InitStep.Finish && (stepState == StepState.Fail || stepState == StepState.Success)) {
                        MirSdkManager.INSTANCE.initMachineInfoHelper();
                        RobotConfig.INSTANCE.initBumperState();
                        RobotConfig.INSTANCE.initBlockRoadSetting();
                    }
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C53151(initStep, stepState, str, null), 2, null);
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: Classes with same name are omitted:
                  classes4.dex
                 */
                /* compiled from: MirSdkManager.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/mirsdkwrap/lib/MirSdkManager$initSubModule$1$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
                /* renamed from: com.pudutech.mirsdkwrap.lib.MirSdkManager$initSubModule$$inlined$let$lambda$1$1 */
                /* loaded from: classes6.dex */
                public static final class C53151 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ String $msg;
                    final /* synthetic */ StepState $state;
                    final /* synthetic */ InitStep $step;
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f6464p$;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C53151(InitStep initStep, StepState stepState, String str, Continuation continuation) {
                        super(2, continuation);
                        this.$step = initStep;
                        this.$state = stepState;
                        this.$msg = str;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C53151 c53151 = new C53151(this.$step, this.$state, this.$msg, completion);
                        c53151.f6464p$ = (CoroutineScope) obj;
                        return c53151;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C53151) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f6464p$;
                        initListener.invoke(this.$step, this.$state, this.$msg);
                        return Unit.INSTANCE;
                    }
                }
            };
            ListenerList<Function3<InitStep, StepState, String, Unit>> initStepListeners = mirSdkListenerWrap.getInitStepListeners();
            Function3<? super InitStep, ? super StepState, ? super String, Unit> function3 = initStepListener;
            if (function3 == null) {
                Intrinsics.throwNpe();
            }
            initStepListeners.addNotSame$module_robot_mirsdk_wrapper_release(function3);
            sDKInterface.init(locateCase);
            Pdlog.m3273d(TAG, "initSubModule sdk init end");
            BatteryInfoManager.INSTANCE.init$module_robot_mirsdk_wrapper_release(mirSdkListenerWrap, sDKInterface);
            DeviceInterface deviceInterface = sDKInterface.getDeviceInterface();
            Intrinsics.checkExpressionValueIsNotNull(deviceInterface, "it.deviceInterface");
            deviceControlHelper = new DevicesControlHelper(deviceInterface);
            RobotMoveManager robotMoveManager = RobotMoveManager.INSTANCE;
            MoveActionInterface moveActionInterface = sDKInterface.getMoveActionInterface();
            Intrinsics.checkExpressionValueIsNotNull(moveActionInterface, "it.moveActionInterface");
            MirSdkListenerWrap mirSdkListenerWrap2 = mirSdkListenerWrap;
            DevicesControlHelper devicesControlHelper = deviceControlHelper;
            if (devicesControlHelper == null) {
                Intrinsics.throwNpe();
            }
            robotMoveManager.init$module_robot_mirsdk_wrapper_release(moveActionInterface, mirSdkListenerWrap2, devicesControlHelper);
            RobotConfig.INSTANCE.init$module_robot_mirsdk_wrapper_release(sDKInterface);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initMachineInfoHelper() {
        try {
            SDKInterface sDKInterface = mirSdk;
            if (sDKInterface != null) {
                MachineInfoHelper machineInfoHelper = MachineInfoHelper.INSTANCE;
                MachineInfo machineInfo = sDKInterface.getMachineInfo();
                Intrinsics.checkExpressionValueIsNotNull(machineInfo, "it.machineInfo");
                machineInfoHelper.init$module_robot_mirsdk_wrapper_release(machineInfo);
            }
        } catch (Exception e) {
            Pdlog.m3273d(TAG, "initSubModule  MachineInfoHelper.init SDKInterface get machineInfo e:{" + e.getCause() + '}');
        }
    }

    public final void closeAuthMirSdk() {
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            sDKInterface.closeAuthCheck();
        }
    }

    public final boolean getDropDetStatus() {
        SDKInterface sDKInterface = mirSdk;
        return sDKInterface != null && sDKInterface.getDropDetStatus();
    }

    public final void setEnableDropDet(boolean flag) {
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            sDKInterface.enableDropDet(flag);
        }
    }

    public final boolean isLocated() {
        SDKInterface sDKInterface = mirSdk;
        return sDKInterface != null && sDKInterface.isLocated();
    }

    public final int controlWheelErrorEvent(WheelError var1, boolean var2) {
        Intrinsics.checkParameterIsNotNull(var1, "var1");
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            return sDKInterface.controlWheelErrorEvent(var1, var2);
        }
        return -4;
    }

    public final void switchSecurity(boolean oldState, boolean expectState) {
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            sDKInterface.securitySwitch(oldState, expectState);
        }
    }

    public final List<String> getRgbdSn() {
        DeviceInterface deviceInterface;
        List<String> rgbdSnList;
        SDKInterface sDKInterface = mirSdk;
        return (sDKInterface == null || (deviceInterface = sDKInterface.getDeviceInterface()) == null || (rgbdSnList = deviceInterface.getRgbdSnList()) == null) ? CollectionsKt.emptyList() : rgbdSnList;
    }

    public final List<String> getLidarSn() {
        DeviceInterface deviceInterface;
        List<String> lidarSnList;
        SDKInterface sDKInterface = mirSdk;
        return (sDKInterface == null || (deviceInterface = sDKInterface.getDeviceInterface()) == null || (lidarSnList = deviceInterface.getLidarSnList()) == null) ? CollectionsKt.emptyList() : lidarSnList;
    }

    public final List<RgbdDeviceInfo> getRgbdInfo() {
        DeviceInterface deviceInterface;
        List<RgbdDeviceInfo> rgbdDeviceList;
        SDKInterface sDKInterface = mirSdk;
        return (sDKInterface == null || (deviceInterface = sDKInterface.getDeviceInterface()) == null || (rgbdDeviceList = deviceInterface.getRgbdDeviceList()) == null) ? CollectionsKt.emptyList() : rgbdDeviceList;
    }

    public final List<LidarDeviceInfo> getLidarInfo() {
        DeviceInterface deviceInterface;
        List<LidarDeviceInfo> lidarDeviceList;
        SDKInterface sDKInterface = mirSdk;
        return (sDKInterface == null || (deviceInterface = sDKInterface.getDeviceInterface()) == null || (lidarDeviceList = deviceInterface.getLidarDeviceList()) == null) ? CollectionsKt.emptyList() : lidarDeviceList;
    }

    public final List<String> getCameraInfo() {
        DeviceInterface deviceInterface;
        List<String> cameraSnList;
        SDKInterface sDKInterface = mirSdk;
        return (sDKInterface == null || (deviceInterface = sDKInterface.getDeviceInterface()) == null || (cameraSnList = deviceInterface.getCameraSnList()) == null) ? CollectionsKt.emptyList() : cameraSnList;
    }

    public final String getBatteryInfo() {
        DeviceInterface deviceInterface;
        String batterySn;
        SDKInterface sDKInterface = mirSdk;
        return (sDKInterface == null || (deviceInterface = sDKInterface.getDeviceInterface()) == null || (batterySn = deviceInterface.getBatterySn()) == null) ? "" : batterySn;
    }

    public final List<BoardInfo> getBoardInfo() {
        DeviceInterface deviceInterface;
        List<BoardInfo> boardUidList;
        SDKInterface sDKInterface = mirSdk;
        return (sDKInterface == null || (deviceInterface = sDKInterface.getDeviceInterface()) == null || (boardUidList = deviceInterface.getBoardUidList()) == null) ? CollectionsKt.emptyList() : boardUidList;
    }
}
