package com.pudutech.bumblebee.business.robotsdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.base.ListenerList;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.business.behavior.DelayResumeActive;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface;
import com.pudutech.bumblebee.business.movementInterface.CruiseInterface;
import com.pudutech.bumblebee.business.movementInterface.DeliverInterface;
import com.pudutech.bumblebee.business.movementInterface.GoHomeInterface;
import com.pudutech.bumblebee.business.movementInterface.HangOutInterface;
import com.pudutech.bumblebee.business.movementInterface.IdleInterface;
import com.pudutech.bumblebee.business.movementInterface.RecycleInterface;
import com.pudutech.bumblebee.business.movementInterface.TempMoveInterface;
import com.pudutech.bumblebee.business.movementTask.CruiseTask;
import com.pudutech.bumblebee.business.movementTask.DeliverTask;
import com.pudutech.bumblebee.business.movementTask.GoHomeTask;
import com.pudutech.bumblebee.business.movementTask.HangoutTask;
import com.pudutech.bumblebee.business.movementTask.IdleTask;
import com.pudutech.bumblebee.business.movementTask.RecycleTask;
import com.pudutech.bumblebee.business.movementTask.TempTask;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.FaceDetectListener;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.InitStepListener;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.PoseListener;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SafeModeListener;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.ScheduleFillInListener;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SchedulerInfoListener;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener;
import com.pudutech.freeinstall_wrapper.Constant;
import com.pudutech.mirsdk.aidl.FillInStateListener;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import com.pudutech.mirsdk.mircore.coreparcel.SmoothMode;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.mirsdkwrap.lib.interf.ServiceConnectionListener;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.BaseMove;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import com.pudutech.mirsdkwrap.lib.robot.RobotConfig;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: SDK.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000æ\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b*\b\u001b\u001e14@GJ[\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u008f\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010q\u001a\u00020\r2\u0006\u0010r\u001a\u00020\u0004J\b\u0010s\u001a\u00020tH\u0007J\u000e\u0010u\u001a\u00020t2\u0006\u0010v\u001a\u00020\u0004J\u0006\u0010w\u001a\u00020\rJ\u0006\u0010x\u001a\u00020\rJ\u000e\u0010y\u001a\u00020\r2\u0006\u0010z\u001a\u00020-J\u000e\u0010{\u001a\u00020\r2\u0006\u0010|\u001a\u00020\u0007J\b\u0010}\u001a\u0004\u0018\u00010~J\u0007\u0010\u007f\u001a\u00030\u0080\u0001J\u0012\u0010\u007f\u001a\u00030\u0080\u00012\u0007\u0010\u0081\u0001\u001a\u00020-H\u0002J\u000e\u0010\u0082\u0001\u001a\t\u0012\u0004\u0012\u00020\u00040\u0083\u0001J\b\u0010\u0084\u0001\u001a\u00030\u0085\u0001J\u0007\u0010\u0086\u0001\u001a\u00020tJ\u0010\u0010\u0087\u0001\u001a\u00020\r2\u0007\u0010\u0088\u0001\u001a\u00020tJ\u0007\u0010\u0089\u0001\u001a\u00020\rJ\u0007\u0010\u008a\u0001\u001a\u00020\rJ\u0010\u0010\u008b\u0001\u001a\u00020\r2\u0007\u0010\u008c\u0001\u001a\u00020\u0004J\u0011\u0010\u008d\u0001\u001a\u00020\r2\b\u0010\u008e\u0001\u001a\u00030\u0085\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R@\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u00168FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u0017\u0010\u0018R\u0010\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001cR\u0010\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001fR \u0010!\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00048F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R!\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\u0014\u001a\u0004\b&\u0010\u0012R!\u0010(\u001a\b\u0012\u0004\u0012\u00020%0\u00168FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\u0014\u001a\u0004\b)\u0010\u0018R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0004\n\u0002\u00102R\u0010\u00103\u001a\u000204X\u0082\u0004¢\u0006\u0004\n\u0002\u00105R!\u00106\u001a\b\u0012\u0004\u0012\u0002070\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b9\u0010\u0014\u001a\u0004\b8\u0010\u0012R!\u0010:\u001a\b\u0012\u0004\u0012\u0002070\u00168FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b<\u0010\u0014\u001a\u0004\b;\u0010\u0018R\u0010\u0010=\u001a\u0004\u0018\u00010>X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010?\u001a\u00020@X\u0082\u0004¢\u0006\u0004\n\u0002\u0010AR\u001e\u0010C\u001a\u00020B2\u0006\u0010 \u001a\u00020B@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0010\u0010F\u001a\u00020GX\u0082\u0004¢\u0006\u0004\n\u0002\u0010HR\u0010\u0010I\u001a\u00020JX\u0082\u0004¢\u0006\u0004\n\u0002\u0010KR!\u0010L\u001a\b\u0012\u0004\u0012\u00020M0\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bO\u0010\u0014\u001a\u0004\bN\u0010\u0012R!\u0010P\u001a\b\u0012\u0004\u0012\u00020M0\u00168FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bR\u0010\u0014\u001a\u0004\bQ\u0010\u0018R!\u0010S\u001a\b\u0012\u0004\u0012\u00020T0\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bV\u0010\u0014\u001a\u0004\bU\u0010\u0012R!\u0010W\u001a\b\u0012\u0004\u0012\u00020T0\u00168FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bY\u0010\u0014\u001a\u0004\bX\u0010\u0018R\u0010\u0010Z\u001a\u00020[X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\\R!\u0010]\u001a\b\u0012\u0004\u0012\u00020^0\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b`\u0010\u0014\u001a\u0004\b_\u0010\u0012R!\u0010a\u001a\b\u0012\u0004\u0012\u00020^0\u00168FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bc\u0010\u0014\u001a\u0004\bb\u0010\u0018R\u000e\u0010d\u001a\u00020eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010#\"\u0004\bh\u0010iR!\u0010j\u001a\b\u0012\u0004\u0012\u00020k0\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bm\u0010\u0014\u001a\u0004\bl\u0010\u0012R!\u0010n\u001a\b\u0012\u0004\u0012\u00020k0\u00168FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bp\u0010\u0014\u001a\u0004\bo\u0010\u0018¨\u0006\u0090\u0001"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/robotsdk/SDK;", "", "()V", "TAG", "", "batteryListener", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "power", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "chargeState", "", "faceDetectListener", "Lcom/pudutech/bumblebee/business/base/ListenerList;", "Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/FaceDetectListener;", "getFaceDetectListener", "()Lcom/pudutech/bumblebee/business/base/ListenerList;", "faceDetectListener$delegate", "Lkotlin/Lazy;", "faceDetectListeners", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "getFaceDetectListeners", "()Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "faceDetectListeners$delegate", "faceDetectResultListener", "com/pudutech/bumblebee/business/robotsdk/SDK$faceDetectResultListener$1", "Lcom/pudutech/bumblebee/business/robotsdk/SDK$faceDetectResultListener$1;", "fillInStateListener", "com/pudutech/bumblebee/business/robotsdk/SDK$fillInStateListener$1", "Lcom/pudutech/bumblebee/business/robotsdk/SDK$fillInStateListener$1;", "<set-?>", "hardwareVersion", "getHardwareVersion", "()Ljava/lang/String;", "initStepListenerList", "Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/InitStepListener;", "getInitStepListenerList", "initStepListenerList$delegate", "initStepListeners", "getInitStepListeners", "initStepListeners$delegate", "mAppContext", "Ljava/lang/ref/WeakReference;", "Landroid/content/Context;", "mainHandler", "Landroid/os/Handler;", "mirsdkConnectListener", "com/pudutech/bumblebee/business/robotsdk/SDK$mirsdkConnectListener$1", "Lcom/pudutech/bumblebee/business/robotsdk/SDK$mirsdkConnectListener$1;", "onBatteryFloorLevelLimitResultListener", "com/pudutech/bumblebee/business/robotsdk/SDK$onBatteryFloorLevelLimitResultListener$1", "Lcom/pudutech/bumblebee/business/robotsdk/SDK$onBatteryFloorLevelLimitResultListener$1;", "poseListenerList", "Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/PoseListener;", "getPoseListenerList", "poseListenerList$delegate", "poseListeners", "getPoseListeners", "poseListeners$delegate", "robotJob", "Lkotlinx/coroutines/Job;", "robotMoveManager", "com/pudutech/bumblebee/business/robotsdk/SDK$robotMoveManager$1", "Lcom/pudutech/bumblebee/business/robotsdk/SDK$robotMoveManager$1;", "Lcom/pudutech/bumblebee/business/robotsdk/SDK$Pose2D;", "robotPose2D", "getRobotPose2D", "()Lcom/pudutech/bumblebee/business/robotsdk/SDK$Pose2D;", "robotPoseListener", "com/pudutech/bumblebee/business/robotsdk/SDK$robotPoseListener$1", "Lcom/pudutech/bumblebee/business/robotsdk/SDK$robotPoseListener$1;", "robotSpeedListener", "com/pudutech/bumblebee/business/robotsdk/SDK$robotSpeedListener$1", "Lcom/pudutech/bumblebee/business/robotsdk/SDK$robotSpeedListener$1;", "safeModeListener", "Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/SafeModeListener;", "getSafeModeListener", "safeModeListener$delegate", "safeModeListeners", "getSafeModeListeners", "safeModeListeners$delegate", "scheduleFillInListenerList", "Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/ScheduleFillInListener;", "getScheduleFillInListenerList", "scheduleFillInListenerList$delegate", "scheduleFillInListeners", "getScheduleFillInListeners", "scheduleFillInListeners$delegate", "schedulerCountChangeListener", "com/pudutech/bumblebee/business/robotsdk/SDK$schedulerCountChangeListener$1", "Lcom/pudutech/bumblebee/business/robotsdk/SDK$schedulerCountChangeListener$1;", "schedulerInfoListenerList", "Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/SchedulerInfoListener;", "getSchedulerInfoListenerList", "schedulerInfoListenerList$delegate", "schedulerInfoListeners", "getSchedulerInfoListeners", "schedulerInfoListeners$delegate", "sdkWorker", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "servicePath", "getServicePath", "setServicePath", "(Ljava/lang/String;)V", "speedListenerList", "Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/SpeedListener;", "getSpeedListenerList", "speedListenerList$delegate", "speedListeners", "getSpeedListeners", "speedListeners$delegate", "addRelocationPoint", "relocatePoint", "checkLocalizationFinishInitialization", "", "checkMapRule", "path", "clearDropEvent", "closeMirsdkCheck", MqttServiceConstants.CONNECT_ACTION, "appContext", "controlBatteryLevel", "level", "getLocalizationStatus", "Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatus;", "getLocateCase", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "context", "getRelocationPoints", "", "getSmoothRunAndStopMode", "Lcom/pudutech/mirsdk/mircore/coreparcel/SmoothMode;", "isLocalizationSuccess", "lockMotor", "state", "quitFillIn", "reloadLocalization", "reloadMap", "mapName", "setSmoothRunAndStopMode", "smoothMode", "Pose2D", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SDK {
    private static final String TAG = "SDK";
    private static WeakReference<Context> mAppContext;
    private static Job robotJob;
    public static final SDK INSTANCE = new SDK();
    private static String servicePath = Constant.mirsdkServer;
    private static final ExecutorCoroutineDispatcher sdkWorker = ThreadPoolDispatcherKt.newSingleThreadContext("SDKWorker");
    private static final SDK$mirsdkConnectListener$1 mirsdkConnectListener = new ServiceConnectionListener() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$mirsdkConnectListener$1
        @Override // com.pudutech.mirsdkwrap.lib.interf.ServiceConnectionListener
        public void onServiceConnected() {
            Pdlog.m3273d("SDK", "onServiceConnected");
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.ServiceConnectionListener
        public void onServiceDisconnected() {
            Pdlog.m3274e("SDK", "onServiceDisconnected ");
        }
    };
    private static String hardwareVersion = "";
    private static Pose2D robotPose2D = new Pose2D(0.0d, 0.0d, 0.0d);
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());

    /* renamed from: speedListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy speedListenerList = LazyKt.lazy(new Function0<ListenerList<SpeedListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$speedListenerList$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<SpeedListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: poseListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy poseListenerList = LazyKt.lazy(new Function0<ListenerList<PoseListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$poseListenerList$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<PoseListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: initStepListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy initStepListenerList = LazyKt.lazy(new Function0<ListenerList<InitStepListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$initStepListenerList$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<InitStepListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: schedulerInfoListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy schedulerInfoListenerList = LazyKt.lazy(new Function0<ListenerList<SchedulerInfoListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$schedulerInfoListenerList$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<SchedulerInfoListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: scheduleFillInListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy scheduleFillInListenerList = LazyKt.lazy(new Function0<ListenerList<ScheduleFillInListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$scheduleFillInListenerList$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<ScheduleFillInListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: faceDetectListener$delegate, reason: from kotlin metadata */
    private static final Lazy faceDetectListener = LazyKt.lazy(new Function0<ListenerList<FaceDetectListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$faceDetectListener$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<FaceDetectListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: safeModeListener$delegate, reason: from kotlin metadata */
    private static final Lazy safeModeListener = LazyKt.lazy(new Function0<ListenerList<SafeModeListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$safeModeListener$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<SafeModeListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: speedListeners$delegate, reason: from kotlin metadata */
    private static final Lazy speedListeners = LazyKt.lazy(new Function0<BaseMultiListenerImpl<SpeedListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$speedListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<SpeedListener> invoke() {
            ListenerList speedListenerList2;
            speedListenerList2 = SDK.INSTANCE.getSpeedListenerList();
            return new BaseMultiListenerImpl<>(speedListenerList2);
        }
    });

    /* renamed from: poseListeners$delegate, reason: from kotlin metadata */
    private static final Lazy poseListeners = LazyKt.lazy(new Function0<BaseMultiListenerImpl<PoseListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$poseListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<PoseListener> invoke() {
            ListenerList poseListenerList2;
            poseListenerList2 = SDK.INSTANCE.getPoseListenerList();
            return new BaseMultiListenerImpl<>(poseListenerList2);
        }
    });

    /* renamed from: initStepListeners$delegate, reason: from kotlin metadata */
    private static final Lazy initStepListeners = LazyKt.lazy(new Function0<BaseMultiListenerImpl<InitStepListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$initStepListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<InitStepListener> invoke() {
            ListenerList initStepListenerList2;
            initStepListenerList2 = SDK.INSTANCE.getInitStepListenerList();
            return new BaseMultiListenerImpl<>(initStepListenerList2);
        }
    });

    /* renamed from: schedulerInfoListeners$delegate, reason: from kotlin metadata */
    private static final Lazy schedulerInfoListeners = LazyKt.lazy(new Function0<BaseMultiListenerImpl<SchedulerInfoListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$schedulerInfoListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<SchedulerInfoListener> invoke() {
            ListenerList schedulerInfoListenerList2;
            schedulerInfoListenerList2 = SDK.INSTANCE.getSchedulerInfoListenerList();
            return new BaseMultiListenerImpl<>(schedulerInfoListenerList2);
        }
    });

    /* renamed from: scheduleFillInListeners$delegate, reason: from kotlin metadata */
    private static final Lazy scheduleFillInListeners = LazyKt.lazy(new Function0<BaseMultiListenerImpl<ScheduleFillInListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$scheduleFillInListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<ScheduleFillInListener> invoke() {
            ListenerList scheduleFillInListenerList2;
            scheduleFillInListenerList2 = SDK.INSTANCE.getScheduleFillInListenerList();
            return new BaseMultiListenerImpl<>(scheduleFillInListenerList2);
        }
    });

    /* renamed from: faceDetectListeners$delegate, reason: from kotlin metadata */
    private static final Lazy faceDetectListeners = LazyKt.lazy(new Function0<BaseMultiListenerImpl<FaceDetectListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$faceDetectListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<FaceDetectListener> invoke() {
            ListenerList faceDetectListener2;
            faceDetectListener2 = SDK.INSTANCE.getFaceDetectListener();
            return new BaseMultiListenerImpl<>(faceDetectListener2);
        }
    });

    /* renamed from: safeModeListeners$delegate, reason: from kotlin metadata */
    private static final Lazy safeModeListeners = LazyKt.lazy(new Function0<BaseMultiListenerImpl<SafeModeListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$safeModeListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<SafeModeListener> invoke() {
            ListenerList safeModeListener2;
            safeModeListener2 = SDK.INSTANCE.getSafeModeListener();
            return new BaseMultiListenerImpl<>(safeModeListener2);
        }
    });
    private static final SDK$faceDetectResultListener$1 faceDetectResultListener = new Function4<Integer, Double, Double, Double, Unit>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$faceDetectResultListener$1
        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(Integer num, Double d, Double d2, Double d3) {
            invoke(num.intValue(), d.doubleValue(), d2.doubleValue(), d3.doubleValue());
            return Unit.INSTANCE;
        }

        public void invoke(final int p0, final double p1, final double p2, final double p3) {
            ListenerList faceDetectListener2;
            ListenerList faceDetectListener3;
            StringBuilder sb = new StringBuilder();
            sb.append("faceDetectResultListener size = ");
            faceDetectListener2 = SDK.INSTANCE.getFaceDetectListener();
            sb.append(faceDetectListener2.size());
            sb.append(" p0 = ");
            sb.append(p0);
            sb.append("  p1=");
            sb.append(p1);
            sb.append(" p2 = ");
            sb.append(p2);
            sb.append(" p3 =");
            sb.append(p3);
            Pdlog.m3273d("SDK", sb.toString());
            faceDetectListener3 = SDK.INSTANCE.getFaceDetectListener();
            faceDetectListener3.forEach(new Function1<FaceDetectListener, Unit>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$faceDetectResultListener$1$invoke$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(FaceDetectListener faceDetectListener4) {
                    invoke2(faceDetectListener4);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(FaceDetectListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onFaceDetectResult(p0, p1, p2, p3);
                }
            });
        }
    };
    private static final Function2<Integer, ChargeState, Unit> batteryListener = new Function2<Integer, ChargeState, Unit>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$batteryListener$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Integer num, ChargeState chargeState) {
            invoke(num.intValue(), chargeState);
            return Unit.INSTANCE;
        }

        public final void invoke(int i, ChargeState chargeState) {
            CoreDevices.INSTANCE.getBattery().onBattery(i, chargeState);
        }
    };
    private static final SDK$robotSpeedListener$1 robotSpeedListener = new RobotMoveManager.RobotSpeedListener() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$robotSpeedListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotSpeedListener
        public void onMove(final double left, final double right) {
            ListenerList speedListenerList2;
            speedListenerList2 = SDK.INSTANCE.getSpeedListenerList();
            speedListenerList2.forEach(new Function1<SpeedListener, Unit>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$robotSpeedListener$1$onMove$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SpeedListener speedListener) {
                    invoke2(speedListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SpeedListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onSpeed(left, right);
                }
            });
        }
    };
    private static final SDK$robotPoseListener$1 robotPoseListener = new RobotMoveManager.RobotPoseListener() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$robotPoseListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotPoseListener
        public void onPose(final double x, final double y, final double yaw) {
            ListenerList poseListenerList2;
            SDK sdk = SDK.INSTANCE;
            SDK.robotPose2D = new SDK.Pose2D(x, y, yaw);
            poseListenerList2 = SDK.INSTANCE.getPoseListenerList();
            poseListenerList2.forEach(new Function1<PoseListener, Unit>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$robotPoseListener$1$onPose$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PoseListener poseListener) {
                    invoke2(poseListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PoseListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onPose(x, y, yaw);
                }
            });
        }
    };
    private static final SDK$schedulerCountChangeListener$1 schedulerCountChangeListener = new RobotMoveManager.SchedulerCountChangeListener() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$schedulerCountChangeListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.SchedulerCountChangeListener
        public void onCountChange(final int r3) {
            ListenerList schedulerInfoListenerList2;
            schedulerInfoListenerList2 = SDK.INSTANCE.getSchedulerInfoListenerList();
            schedulerInfoListenerList2.forEach(new Function1<SchedulerInfoListener, Unit>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$schedulerCountChangeListener$1$onCountChange$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SchedulerInfoListener schedulerInfoListener) {
                    invoke2(schedulerInfoListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SchedulerInfoListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onSchedulerCount(r3);
                }
            });
        }
    };
    private static final SDK$onBatteryFloorLevelLimitResultListener$1 onBatteryFloorLevelLimitResultListener = new BatteryInfoManager.OnBatteryFloorLevelLimitResultListener() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$onBatteryFloorLevelLimitResultListener$1
        @Override // com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager.OnBatteryFloorLevelLimitResultListener
        public void onBatteryFloorLevelLimitResultListener(int p0, int p1) {
            CoreDevices.INSTANCE.getBattery().onBatteryFloorLevelLimitResult(p0, p1);
        }
    };
    private static final SDK$robotMoveManager$1 robotMoveManager = new RobotMoveManager.OnRobotMoveTaskChangeListener() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$robotMoveManager$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.OnRobotMoveTaskChangeListener
        public void onMovingTask(BaseMove<?> moveTask) {
            if (moveTask != null) {
                Behavior behavior = Behavior.INSTANCE;
                TypeToken<TempTask> typeToken = new TypeToken<TempTask>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$robotMoveManager$1$onMovingTask$$inlined$changeMovementTask$1
                };
                Class<? super TempTask> rawType = typeToken.getRawType();
                Pdlog.m3275i("Behavior", "change task=" + rawType + ' ' + typeToken.getType() + ' ' + typeToken.getClass() + ' ' + typeToken);
                TempTask tempTask = null;
                if (!behavior.getInitDone()) {
                    Pdlog.m3274e("Behavior", "not init yet");
                } else {
                    DelayResumeActive.INSTANCE.cancelTask();
                    if (Intrinsics.areEqual(rawType, CruiseInterface.class)) {
                        behavior.setMovementTask(new CruiseTask());
                    } else if (Intrinsics.areEqual(rawType, DeliverInterface.class)) {
                        behavior.setMovementTask(new DeliverTask());
                    } else if (Intrinsics.areEqual(rawType, GoHomeInterface.class)) {
                        behavior.setMovementTask(new GoHomeTask());
                    } else if (Intrinsics.areEqual(rawType, HangOutInterface.class)) {
                        behavior.setMovementTask(new HangoutTask());
                    } else if (Intrinsics.areEqual(rawType, RecycleInterface.class)) {
                        behavior.setMovementTask(new RecycleTask());
                    } else if (Intrinsics.areEqual(rawType, TempMoveInterface.class)) {
                        behavior.setMovementTask(new TempTask());
                    } else if (Intrinsics.areEqual(rawType, IdleInterface.class)) {
                        behavior.setMovementTask(new IdleTask());
                    } else {
                        Pdlog.m3274e("Behavior", rawType + " not supported");
                    }
                    Pdlog.m3275i("Behavior", "change task done");
                    BaseTaskInterface movementTask = behavior.getMovementTask();
                    if (movementTask == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.business.movementTask.TempTask");
                    }
                    tempTask = (TempTask) movementTask;
                }
            }
        }
    };
    private static final SDK$fillInStateListener$1 fillInStateListener = new FillInStateListener.Stub() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$fillInStateListener$1
        @Override // com.pudutech.mirsdk.aidl.FillInStateListener
        public void onFillIn(final boolean p0, final Destination p1) {
            ListenerList scheduleFillInListenerList2;
            scheduleFillInListenerList2 = SDK.INSTANCE.getScheduleFillInListenerList();
            scheduleFillInListenerList2.forEach(new Function1<ScheduleFillInListener, Unit>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$fillInStateListener$1$onFillIn$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ScheduleFillInListener scheduleFillInListener) {
                    invoke2(scheduleFillInListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ScheduleFillInListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onScheduleFillIn(Destination.this, p0);
                }
            });
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<FaceDetectListener> getFaceDetectListener() {
        return (ListenerList) faceDetectListener.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<InitStepListener> getInitStepListenerList() {
        return (ListenerList) initStepListenerList.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<PoseListener> getPoseListenerList() {
        return (ListenerList) poseListenerList.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<SafeModeListener> getSafeModeListener() {
        return (ListenerList) safeModeListener.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<ScheduleFillInListener> getScheduleFillInListenerList() {
        return (ListenerList) scheduleFillInListenerList.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<SchedulerInfoListener> getSchedulerInfoListenerList() {
        return (ListenerList) schedulerInfoListenerList.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<SpeedListener> getSpeedListenerList() {
        return (ListenerList) speedListenerList.getValue();
    }

    public final BaseMultiListenerImpl<FaceDetectListener> getFaceDetectListeners() {
        return (BaseMultiListenerImpl) faceDetectListeners.getValue();
    }

    public final BaseMultiListenerImpl<InitStepListener> getInitStepListeners() {
        return (BaseMultiListenerImpl) initStepListeners.getValue();
    }

    public final BaseMultiListenerImpl<PoseListener> getPoseListeners() {
        return (BaseMultiListenerImpl) poseListeners.getValue();
    }

    public final BaseMultiListenerImpl<SafeModeListener> getSafeModeListeners() {
        return (BaseMultiListenerImpl) safeModeListeners.getValue();
    }

    public final BaseMultiListenerImpl<ScheduleFillInListener> getScheduleFillInListeners() {
        return (BaseMultiListenerImpl) scheduleFillInListeners.getValue();
    }

    public final BaseMultiListenerImpl<SchedulerInfoListener> getSchedulerInfoListeners() {
        return (BaseMultiListenerImpl) schedulerInfoListeners.getValue();
    }

    public final BaseMultiListenerImpl<SpeedListener> getSpeedListeners() {
        return (BaseMultiListenerImpl) speedListeners.getValue();
    }

    private SDK() {
    }

    public final String getServicePath() {
        return servicePath;
    }

    public final void setServicePath(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        servicePath = str;
    }

    public final String getHardwareVersion() {
        return RobotConfig.INSTANCE.getHardwareVersion();
    }

    /* compiled from: SDK.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/robotsdk/SDK$Pose2D;", "", "x", "", "y", "yaw", "(DDD)V", "getX", "()D", "getY", "getYaw", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class Pose2D {
        private final double x;
        private final double y;
        private final double yaw;

        public static /* synthetic */ Pose2D copy$default(Pose2D pose2D, double d, double d2, double d3, int i, Object obj) {
            if ((i & 1) != 0) {
                d = pose2D.x;
            }
            double d4 = d;
            if ((i & 2) != 0) {
                d2 = pose2D.y;
            }
            double d5 = d2;
            if ((i & 4) != 0) {
                d3 = pose2D.yaw;
            }
            return pose2D.copy(d4, d5, d3);
        }

        /* renamed from: component1, reason: from getter */
        public final double getX() {
            return this.x;
        }

        /* renamed from: component2, reason: from getter */
        public final double getY() {
            return this.y;
        }

        /* renamed from: component3, reason: from getter */
        public final double getYaw() {
            return this.yaw;
        }

        public final Pose2D copy(double x, double y, double yaw) {
            return new Pose2D(x, y, yaw);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Pose2D)) {
                return false;
            }
            Pose2D pose2D = (Pose2D) other;
            return Double.compare(this.x, pose2D.x) == 0 && Double.compare(this.y, pose2D.y) == 0 && Double.compare(this.yaw, pose2D.yaw) == 0;
        }

        public int hashCode() {
            long doubleToLongBits = Double.doubleToLongBits(this.x);
            long doubleToLongBits2 = Double.doubleToLongBits(this.y);
            int i = ((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31;
            long doubleToLongBits3 = Double.doubleToLongBits(this.yaw);
            return i + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3));
        }

        public String toString() {
            return "Pose2D(x=" + this.x + ", y=" + this.y + ", yaw=" + this.yaw + ")";
        }

        public Pose2D(double d, double d2, double d3) {
            this.x = d;
            this.y = d2;
            this.yaw = d3;
        }

        public final double getX() {
            return this.x;
        }

        public final double getY() {
            return this.y;
        }

        public final double getYaw() {
            return this.yaw;
        }
    }

    public final Pose2D getRobotPose2D() {
        return robotPose2D;
    }

    public final void connect(Context appContext) {
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        CoreDevices.INSTANCE.getMileageTask().loadRecord(appContext);
        mAppContext = new WeakReference<>(appContext);
        WeakReference<Context> weakReference = mAppContext;
        if (weakReference == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAppContext");
        }
        Context it = weakReference.get();
        if (it != null) {
            if (Intrinsics.areEqual("robot", "mock")) {
                MirSdkManager mirSdkManager = MirSdkManager.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                mirSdkManager.init(it, true);
            } else {
                MirSdkManager mirSdkManager2 = MirSdkManager.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                mirSdkManager2.init(it, false);
            }
        }
        MirSdkManager.INSTANCE.chooseLaserBeforeConnectService(getLocateCase(appContext));
        RobotMoveManager.INSTANCE.addRobotSpeedListener(robotSpeedListener);
        RobotMoveManager.INSTANCE.addRobotPoseListener(robotPoseListener);
        RobotMoveManager.INSTANCE.addSchedulerCountChangeListener(schedulerCountChangeListener);
        BatteryInfoManager.INSTANCE.addOnBatteryFloorLevelLimitResultListener(onBatteryFloorLevelLimitResultListener);
        BatteryInfoManager.INSTANCE.addOnFaceDetectListener(faceDetectResultListener);
        RobotMoveManager.INSTANCE.addOnRobotMoveTaskChangeListener(robotMoveManager);
        MirSdkManager.INSTANCE.connectService(mirsdkConnectListener, new Function3<InitStep, StepState, String, Unit>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$connect$2
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(InitStep initStep, StepState stepState, String str) {
                invoke2(initStep, stepState, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final InitStep initStep, final StepState stepState, final String str) {
                ListenerList initStepListenerList2;
                ExecutorCoroutineDispatcher executorCoroutineDispatcher;
                SDK$fillInStateListener$1 sDK$fillInStateListener$1;
                Function2<? super Integer, ? super ChargeState, Unit> function2;
                Job job;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("InitStep:");
                sb.append(initStep != null ? initStep.name() : null);
                sb.append("###StepState:");
                sb.append(stepState != null ? stepState.name() : null);
                objArr[0] = sb.toString();
                Pdlog.m3273d("SDK", objArr);
                if (initStep == InitStep.CheckCAN && stepState == StepState.Success) {
                    Pdlog.m3275i("SDK", "check can success. add batteryListeners");
                    BatteryInfoManager batteryInfoManager = BatteryInfoManager.INSTANCE;
                    SDK sdk = SDK.INSTANCE;
                    function2 = SDK.batteryListener;
                    batteryInfoManager.addBatteryDataListener(function2);
                    BatteryInfoManager.INSTANCE.checkBatteryLevel();
                    SDK sdk2 = SDK.INSTANCE;
                    job = SDK.robotJob;
                    if (job != null) {
                        job.start();
                    }
                }
                if (initStep != InitStep.Finish) {
                    initStepListenerList2 = SDK.INSTANCE.getInitStepListenerList();
                    initStepListenerList2.forEach(new Function1<InitStepListener, Unit>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK$connect$2.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(InitStepListener initStepListener) {
                            invoke2(initStepListener);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(InitStepListener it2) {
                            Intrinsics.checkParameterIsNotNull(it2, "it");
                            it2.onInitStep(InitStep.this, stepState, str);
                        }
                    });
                    return;
                }
                Pdlog.m3273d("SDK", "connectService()finish");
                SDK.INSTANCE.getSpeedListeners().addListener(CoreDevices.INSTANCE.getMotionTask());
                SDK.INSTANCE.getSpeedListeners().addListener(CoreDevices.INSTANCE.getMileageTask());
                if (RobotMoveManager.INSTANCE.getMoveActionInterface() != null) {
                    CoreDevices.INSTANCE.setMoveAction$module_bumblebee_business_robotRelease(RobotMoveManager.INSTANCE.getMoveActionInterface());
                    Behavior.INSTANCE.init(CoreDevices.INSTANCE.getMoveAction());
                    RobotMoveInterfaceDecorator moveAction = CoreDevices.INSTANCE.getMoveAction();
                    if (moveAction != null) {
                        Pdlog.m3273d("SDK", "moveAction() addListeners");
                        moveAction.addOnStateChangeListeners(Behavior.INSTANCE.getStateListener$module_bumblebee_business_robotRelease());
                        moveAction.addOnStateChangeListeners(CoreDevices.INSTANCE.getMonitorTask());
                        moveAction.addOnStateChangeListeners(CoreDevices.INSTANCE.getMotionTask());
                        moveAction.addOnStateChangeListeners(Peripherals.INSTANCE.getTouchSensor());
                        SDK sdk3 = SDK.INSTANCE;
                        sDK$fillInStateListener$1 = SDK.fillInStateListener;
                        moveAction.addFillInStateListener("SDK", sDK$fillInStateListener$1);
                    }
                } else {
                    Pdlog.m3274e("SDK", "move action is null");
                }
                GlobalScope globalScope = GlobalScope.INSTANCE;
                SDK sdk4 = SDK.INSTANCE;
                executorCoroutineDispatcher = SDK.sdkWorker;
                BuildersKt__Builders_commonKt.launch$default(globalScope, executorCoroutineDispatcher, null, new C40262(initStep, stepState, str, null), 2, null);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: SDK.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.business.robotsdk.SDK$connect$2$2", m3970f = "SDK.kt", m3971i = {0}, m3972l = {132}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.bumblebee.business.robotsdk.SDK$connect$2$2 */
            /* loaded from: classes4.dex */
            public static final class C40262 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $msg;
                final /* synthetic */ StepState $state;
                final /* synthetic */ InitStep $step;
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4608p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C40262(InitStep initStep, StepState stepState, String str, Continuation continuation) {
                    super(2, continuation);
                    this.$step = initStep;
                    this.$state = stepState;
                    this.$msg = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C40262 c40262 = new C40262(this.$step, this.$state, this.$msg, completion);
                    c40262.f4608p$ = (CoroutineScope) obj;
                    return c40262;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C40262) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Job job;
                    ListenerList initStepListenerList;
                    ExecutorCoroutineDispatcher executorCoroutineDispatcher;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f4608p$;
                        SDK sdk = SDK.INSTANCE;
                        job = SDK.robotJob;
                        if (job != null) {
                            this.L$0 = coroutineScope;
                            this.label = 1;
                            if (job.join(this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    SDK sdk2 = SDK.INSTANCE;
                    SDK.robotJob = (Job) null;
                    initStepListenerList = SDK.INSTANCE.getInitStepListenerList();
                    initStepListenerList.forEach(new Function1<InitStepListener, Unit>() { // from class: com.pudutech.bumblebee.business.robotsdk.SDK.connect.2.2.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(InitStepListener initStepListener) {
                            invoke2(initStepListener);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(InitStepListener it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            it.onInitStep(C40262.this.$step, C40262.this.$state, C40262.this.$msg);
                        }
                    });
                    SDK sdk3 = SDK.INSTANCE;
                    executorCoroutineDispatcher = SDK.sdkWorker;
                    executorCoroutineDispatcher.close();
                    return Unit.INSTANCE;
                }
            }
        });
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, sdkWorker, null, new SDK$connect$3(appContext, null), 2, null);
    }

    private final LocateCase getLocateCase(Context context) {
        int i = SpUtils.get(context, "key_authorization_position_config", LocateCase.Marker.ordinal());
        Pdlog.m3273d(TAG, "sdkInterface position=" + i);
        LocateCase locateCase = LocateCase.Marker;
        if (i == 0) {
            return LocateCase.Marker;
        }
        if (i == 1) {
            return LocateCase.Laser;
        }
        if (i != 2) {
            return i != 3 ? locateCase : LocateCase.Slamware;
        }
        return LocateCase.LaserMark;
    }

    @Deprecated(message = "此方法在算法层已经失效", replaceWith = @ReplaceWith(expression = "SDK.isLocalizationSuccess()", imports = {}))
    public final boolean checkLocalizationFinishInitialization() {
        return RobotMapManager.INSTANCE.isLocalizationSuccess();
    }

    public final LocalizationStatus getLocalizationStatus() {
        return RobotMapManager.INSTANCE.getLocationStatus();
    }

    public final boolean isLocalizationSuccess() {
        return RobotMapManager.INSTANCE.isLocalizationSuccess();
    }

    public final void closeMirsdkCheck() {
        try {
            MirSdkManager.INSTANCE.closeAuthMirSdk();
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3273d(TAG, Unit.INSTANCE);
        }
    }

    public final LocateCase getLocateCase() {
        return RobotMapManager.INSTANCE.getLocationCase();
    }

    public final List<String> getRelocationPoints() {
        return RobotMapManager.INSTANCE.getRelocationPoints();
    }

    public final void addRelocationPoint(String relocatePoint) {
        Intrinsics.checkParameterIsNotNull(relocatePoint, "relocatePoint");
        RobotMapManager.INSTANCE.addRelocationPoint(relocatePoint);
    }

    public final void reloadMap(String mapName) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        RobotMapManager.INSTANCE.reloadMapByName(mapName);
    }

    public final void reloadLocalization() {
        RobotMapManager.INSTANCE.reloadLocation();
    }

    public final void quitFillIn() {
        Pdlog.m3273d(TAG, "quitFillIn");
        RobotMoveInterfaceDecorator moveActionInterface = RobotMoveManager.INSTANCE.getMoveActionInterface();
        if (moveActionInterface != null) {
            moveActionInterface.quitFillIn();
        }
    }

    public final SmoothMode getSmoothRunAndStopMode() {
        return RobotConfig.INSTANCE.getSmoothRunAndStopMode();
    }

    public final void setSmoothRunAndStopMode(SmoothMode smoothMode) {
        Intrinsics.checkParameterIsNotNull(smoothMode, "smoothMode");
        RobotConfig.INSTANCE.setSmoothRunAndStopMode(smoothMode);
    }

    public final boolean checkMapRule(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        return RobotMapManager.INSTANCE.checkLegalMap(path);
    }

    public final void controlBatteryLevel(int level) {
        BatteryInfoManager.INSTANCE.controlBatteryLevel(level);
    }

    public final void clearDropEvent() {
        RobotMoveInterfaceDecorator moveActionInterface = RobotMoveManager.INSTANCE.getMoveActionInterface();
        if (moveActionInterface != null) {
            moveActionInterface.clearDropEvent();
        }
    }

    public final void lockMotor(boolean state) {
        DevicesControlHelper deviceControlHelper = MirSdkManager.INSTANCE.getDeviceControlHelper();
        if (deviceControlHelper != null) {
            deviceControlHelper.lockMotor(state);
        }
    }
}
