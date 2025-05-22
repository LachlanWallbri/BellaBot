package com.pudutech.mirsdkwrap.lib.robot;

import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.ISDKListener;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.RobotAction;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdk.aidl.serialize.SwitchMapResult;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.interf.ListenerList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSdkListenerWrap.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010P\u001a\u00020\r2\b\u0010\u000b\u001a\u0004\u0018\u00010Q2\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010R\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020E2\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010S\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020E2\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010T\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\u0015H\u0016J\u0018\u0010U\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0016J(\u0010V\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH\u0016J\b\u0010W\u001a\u00020\rH\u0016J$\u0010X\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020)2\b\u0010\f\u001a\u0004\u0018\u00010+2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004H\u0016J \u0010Y\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016J\u0010\u0010Z\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\bH\u0016J\u0018\u0010[\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00020\u001cH\u0016J\u001c\u0010\\\u001a\u00020\r2\b\u0010\u000b\u001a\u0004\u0018\u00010@2\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010]\u001a\u00020\rH\u0016J\u001c\u0010^\u001a\u00020\r2\b\u0010\u000b\u001a\u0004\u0018\u00010K2\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010_\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020EH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000RU\u0010\u0005\u001a<\u00128\u00126\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0007j\u0002`\u000e0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010RW\u0010\u0013\u001a>\u0012:\u00128\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u00010\u0015¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\r0\u0007j\u0002`\u00170\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u0012\u001a\u0004\b\u0018\u0010\u0010R\u007f\u0010\u001a\u001af\u0012b\u0012`\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\r0\u001bj\u0002`\u001f0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u0012\u001a\u0004\b \u0010\u0010R+\u0010\"\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\r0#j\u0002`$0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\u0012\u001a\u0004\b%\u0010\u0010Rp\u0010'\u001aW\u0012S\u0012Q\u0012\u0015\u0012\u0013\u0018\u00010)¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(*\u0012\u0015\u0012\u0013\u0018\u00010+¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(,\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\r0(j\u0002`.0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b0\u0010\u0012\u001a\u0004\b/\u0010\u0010Rj\u00101\u001aQ\u0012M\u0012K\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\r0(j\u0002`20\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b4\u0010\u0012\u001a\u0004\b3\u0010\u0010R@\u00105\u001a'\u0012#\u0012!\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(7\u0012\u0004\u0012\u00020\r06j\u0002`80\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b:\u0010\u0012\u001a\u0004\b9\u0010\u0010RU\u0010;\u001a<\u00128\u00126\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0007j\u0002`<0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b>\u0010\u0012\u001a\u0004\b=\u0010\u0010RY\u0010?\u001a@\u0012<\u0012:\u0012\u0015\u0012\u0013\u0018\u00010@¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0007j\u0002`A0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bC\u0010\u0012\u001a\u0004\bB\u0010\u0010R@\u0010D\u001a'\u0012#\u0012!\u0012\u0013\u0012\u00110E¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\r06j\u0002`G0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bI\u0010\u0012\u001a\u0004\bH\u0010\u0010RY\u0010J\u001a@\u0012<\u0012:\u0012\u0015\u0012\u0013\u0018\u00010K¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(L\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\r0\u0007j\u0002`M0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bO\u0010\u0012\u001a\u0004\bN\u0010\u0010¨\u0006`"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/MirSdkListenerWrap;", "Lcom/pudutech/mirsdk/aidl/ISDKListener$Stub;", "()V", "TAG", "", "batteryFloorLevelLimitResultListeners", "Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "p0", "p1", "", "Lcom/pudutech/mirsdkwrap/lib/interf/BatteryFloorLevelLimitResultListener;", "getBatteryFloorLevelLimitResultListeners", "()Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "batteryFloorLevelLimitResultListeners$delegate", "Lkotlin/Lazy;", "batteryListeners", "power", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "chargeState", "Lcom/pudutech/mirsdkwrap/lib/interf/BatteryListener;", "getBatteryListeners", "batteryListeners$delegate", "faceDetectResultListeners", "Lkotlin/Function4;", "", "p2", "p3", "Lcom/pudutech/mirsdkwrap/lib/interf/FaceDetectResultListener;", "getFaceDetectResultListeners", "faceDetectResultListeners$delegate", "goalCruiseListeners", "Lkotlin/Function0;", "Lcom/pudutech/mirsdkwrap/lib/interf/GoalCruiseListener;", "getGoalCruiseListeners", "goalCruiseListeners$delegate", "initStepListeners", "Lkotlin/Function3;", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "step", "Lcom/pudutech/mirsdk/aidl/serialize/StepState;", "state", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/mirsdkwrap/lib/interf/InitStepListener;", "getInitStepListeners", "initStepListeners$delegate", "robotPoseListeners", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotPoseListener;", "getRobotPoseListeners", "robotPoseListeners$delegate", "robotSchedulerCountListeners", "Lkotlin/Function1;", "count", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotSchedulerCountListener;", "getRobotSchedulerCountListeners", "robotSchedulerCountListeners$delegate", "robotSpeedListeners", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotSpeedListener;", "getRobotSpeedListeners", "robotSpeedListeners$delegate", "robotStateListeners", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotStateListener;", "getRobotStateListeners", "robotStateListeners$delegate", "securityFeedBackListeners", "", "isOpen", "Lcom/pudutech/mirsdkwrap/lib/interf/SecurityFeedBackListener;", "getSecurityFeedBackListeners", "securityFeedBackListeners$delegate", "switchPdmapResultListeners", "Lcom/pudutech/mirsdk/aidl/serialize/SwitchMapResult;", SpeechUtility.TAG_RESOURCE_RESULT, "Lcom/pudutech/mirsdkwrap/lib/interf/SwitchPdmapResultListener;", "getSwitchPdmapResultListeners", "switchPdmapResultListeners$delegate", "onAction", "Lcom/pudutech/mirsdk/aidl/serialize/RobotAction;", "onAddChargePointResult", "onAddRelocationPointResult", "onBattery", "onBatteryFloorLevelLimitResult", "onFaceDetectResult", "onGoalCruisePath", "onInitStep", "onPose", "onSchedulerCount", "onSpeed", "onStateChange", "onStuckReplan", "onSwitchPdmapResult", "securityFeedback", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MirSdkListenerWrap extends ISDKListener.Stub {
    private final String TAG = "MirSdkListenerWrap";

    /* renamed from: initStepListeners$delegate, reason: from kotlin metadata */
    private final Lazy initStepListeners = LazyKt.lazy(new Function0<ListenerList<Function3<? super InitStep, ? super StepState, ? super String, ? extends Unit>>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$initStepListeners$2
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<Function3<? super InitStep, ? super StepState, ? super String, ? extends Unit>> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: batteryListeners$delegate, reason: from kotlin metadata */
    private final Lazy batteryListeners = LazyKt.lazy(new Function0<ListenerList<Function2<? super Integer, ? super ChargeState, ? extends Unit>>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$batteryListeners$2
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<Function2<? super Integer, ? super ChargeState, ? extends Unit>> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: switchPdmapResultListeners$delegate, reason: from kotlin metadata */
    private final Lazy switchPdmapResultListeners = LazyKt.lazy(new Function0<ListenerList<Function2<? super SwitchMapResult, ? super String, ? extends Unit>>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$switchPdmapResultListeners$2
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<Function2<? super SwitchMapResult, ? super String, ? extends Unit>> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: robotSpeedListeners$delegate, reason: from kotlin metadata */
    private final Lazy robotSpeedListeners = LazyKt.lazy(new Function0<ListenerList<Function2<? super Double, ? super Double, ? extends Unit>>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$robotSpeedListeners$2
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<Function2<? super Double, ? super Double, ? extends Unit>> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: robotPoseListeners$delegate, reason: from kotlin metadata */
    private final Lazy robotPoseListeners = LazyKt.lazy(new Function0<ListenerList<Function3<? super Double, ? super Double, ? super Double, ? extends Unit>>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$robotPoseListeners$2
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<Function3<? super Double, ? super Double, ? super Double, ? extends Unit>> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: robotStateListeners$delegate, reason: from kotlin metadata */
    private final Lazy robotStateListeners = LazyKt.lazy(new Function0<ListenerList<Function2<? super RobotState, ? super String, ? extends Unit>>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$robotStateListeners$2
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<Function2<? super RobotState, ? super String, ? extends Unit>> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: robotSchedulerCountListeners$delegate, reason: from kotlin metadata */
    private final Lazy robotSchedulerCountListeners = LazyKt.lazy(new Function0<ListenerList<Function1<? super Integer, ? extends Unit>>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$robotSchedulerCountListeners$2
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<Function1<? super Integer, ? extends Unit>> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: goalCruiseListeners$delegate, reason: from kotlin metadata */
    private final Lazy goalCruiseListeners = LazyKt.lazy(new Function0<ListenerList<Function0<? extends Unit>>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$goalCruiseListeners$2
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<Function0<? extends Unit>> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: faceDetectResultListeners$delegate, reason: from kotlin metadata */
    private final Lazy faceDetectResultListeners = LazyKt.lazy(new Function0<ListenerList<Function4<? super Integer, ? super Double, ? super Double, ? super Double, ? extends Unit>>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$faceDetectResultListeners$2
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<Function4<? super Integer, ? super Double, ? super Double, ? super Double, ? extends Unit>> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: batteryFloorLevelLimitResultListeners$delegate, reason: from kotlin metadata */
    private final Lazy batteryFloorLevelLimitResultListeners = LazyKt.lazy(new Function0<ListenerList<Function2<? super Integer, ? super Integer, ? extends Unit>>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$batteryFloorLevelLimitResultListeners$2
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<Function2<? super Integer, ? super Integer, ? extends Unit>> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: securityFeedBackListeners$delegate, reason: from kotlin metadata */
    private final Lazy securityFeedBackListeners = LazyKt.lazy(new Function0<ListenerList<Function1<? super Boolean, ? extends Unit>>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$securityFeedBackListeners$2
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<Function1<? super Boolean, ? extends Unit>> invoke() {
            return new ListenerList<>();
        }
    });

    public final ListenerList<Function2<Integer, Integer, Unit>> getBatteryFloorLevelLimitResultListeners() {
        return (ListenerList) this.batteryFloorLevelLimitResultListeners.getValue();
    }

    public final ListenerList<Function2<Integer, ChargeState, Unit>> getBatteryListeners() {
        return (ListenerList) this.batteryListeners.getValue();
    }

    public final ListenerList<Function4<Integer, Double, Double, Double, Unit>> getFaceDetectResultListeners() {
        return (ListenerList) this.faceDetectResultListeners.getValue();
    }

    public final ListenerList<Function0<Unit>> getGoalCruiseListeners() {
        return (ListenerList) this.goalCruiseListeners.getValue();
    }

    public final ListenerList<Function3<InitStep, StepState, String, Unit>> getInitStepListeners() {
        return (ListenerList) this.initStepListeners.getValue();
    }

    public final ListenerList<Function3<Double, Double, Double, Unit>> getRobotPoseListeners() {
        return (ListenerList) this.robotPoseListeners.getValue();
    }

    public final ListenerList<Function1<Integer, Unit>> getRobotSchedulerCountListeners() {
        return (ListenerList) this.robotSchedulerCountListeners.getValue();
    }

    public final ListenerList<Function2<Double, Double, Unit>> getRobotSpeedListeners() {
        return (ListenerList) this.robotSpeedListeners.getValue();
    }

    public final ListenerList<Function2<RobotState, String, Unit>> getRobotStateListeners() {
        return (ListenerList) this.robotStateListeners.getValue();
    }

    public final ListenerList<Function1<Boolean, Unit>> getSecurityFeedBackListeners() {
        return (ListenerList) this.securityFeedBackListeners.getValue();
    }

    public final ListenerList<Function2<SwitchMapResult, String, Unit>> getSwitchPdmapResultListeners() {
        return (ListenerList) this.switchPdmapResultListeners.getValue();
    }

    @Override // com.pudutech.mirsdk.aidl.ISDKListener
    public void onStuckReplan() {
    }

    @Override // com.pudutech.mirsdk.aidl.ISDKListener
    public void onInitStep(InitStep p0, StepState p1, String p2) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        Pdlog.m3273d(this.TAG, "onInitStep : p0 = " + p0 + "; p1 = " + p1 + "; p2 = " + p2 + "; ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MirSdkListenerWrap$onInitStep$1(this, p0, p1, p2, null), 3, null);
    }

    @Override // com.pudutech.mirsdk.aidl.ISDKListener
    public void onGoalCruisePath() {
        Pdlog.m3273d(this.TAG, "onGoalCruisePath()");
        getGoalCruiseListeners().forEach(new Function1<Function0<? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$onGoalCruisePath$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function0<? extends Unit> function0) {
                invoke2((Function0<Unit>) function0);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function0<Unit> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.invoke();
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.ISDKListener
    public void onAction(RobotAction p0, String p1) {
        Pdlog.m3273d(this.TAG, "onAction()：p0=" + p0 + "  p1=" + p1);
    }

    @Override // com.pudutech.mirsdk.aidl.ISDKListener
    public void onSpeed(final double p0, final double p1) {
        Pdlog.m3273d(this.TAG, "onSpeed()：p0=" + p0 + "  p1=" + p1);
        getRobotSpeedListeners().forEach(new Function1<Function2<? super Double, ? super Double, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$onSpeed$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Double, ? super Double, ? extends Unit> function2) {
                invoke2((Function2<? super Double, ? super Double, Unit>) function2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function2<? super Double, ? super Double, Unit> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.invoke(Double.valueOf(p0), Double.valueOf(p1));
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.ISDKListener
    public void onPose(final double p0, final double p1, final double p2) {
        Pdlog.m3273d(this.TAG, "onPose()：p0=" + p0 + "  p1=" + p1 + "  p2=" + p2);
        getRobotPoseListeners().forEach(new Function1<Function3<? super Double, ? super Double, ? super Double, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$onPose$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function3<? super Double, ? super Double, ? super Double, ? extends Unit> function3) {
                invoke2((Function3<? super Double, ? super Double, ? super Double, Unit>) function3);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function3<? super Double, ? super Double, ? super Double, Unit> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.invoke(Double.valueOf(p0), Double.valueOf(p1), Double.valueOf(p2));
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.ISDKListener
    public void onSchedulerCount(final int p0) {
        Pdlog.m3273d(this.TAG, "onSchedulerCount()：p0=" + p0);
        getRobotSchedulerCountListeners().forEach(new Function1<Function1<? super Integer, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$onSchedulerCount$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function1<? super Integer, ? extends Unit> function1) {
                invoke2((Function1<? super Integer, Unit>) function1);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function1<? super Integer, Unit> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.invoke(Integer.valueOf(p0));
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.ISDKListener
    public void onSwitchPdmapResult(final SwitchMapResult p0, final String p1) {
        Pdlog.m3273d(this.TAG, "onSwitchPdmapResult()：p0=" + p0 + "  p1=" + p1);
        getSwitchPdmapResultListeners().forEach(new Function1<Function2<? super SwitchMapResult, ? super String, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$onSwitchPdmapResult$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super SwitchMapResult, ? super String, ? extends Unit> function2) {
                invoke2((Function2<? super SwitchMapResult, ? super String, Unit>) function2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function2<? super SwitchMapResult, ? super String, Unit> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.invoke(SwitchMapResult.this, p1);
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.ISDKListener
    public void onBattery(final int p0, final ChargeState p1) {
        Pdlog.m3273d(this.TAG, "onBattery()：p0=" + p0 + "  p1=" + p1);
        getBatteryListeners().forEach(new Function1<Function2<? super Integer, ? super ChargeState, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$onBattery$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Integer, ? super ChargeState, ? extends Unit> function2) {
                invoke2((Function2<? super Integer, ? super ChargeState, Unit>) function2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function2<? super Integer, ? super ChargeState, Unit> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.invoke(Integer.valueOf(p0), p1);
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.ISDKListener
    public void onStateChange(final RobotState p0, final String p1) {
        Pdlog.m3273d(this.TAG, "onStateChange()：p0=" + p0 + "  p1=" + p1);
        getRobotStateListeners().forEach(new Function1<Function2<? super RobotState, ? super String, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$onStateChange$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super RobotState, ? super String, ? extends Unit> function2) {
                invoke2((Function2<? super RobotState, ? super String, Unit>) function2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function2<? super RobotState, ? super String, Unit> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.invoke(RobotState.this, p1);
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.ISDKListener
    public void onFaceDetectResult(final int p0, final double p1, final double p2, final double p3) {
        Pdlog.m3273d(this.TAG, "onFaceDetectResult: p0=" + p0 + "  p1=" + p1 + "  p2=" + p2 + "  p3=" + p3);
        getFaceDetectResultListeners().forEach(new Function1<Function4<? super Integer, ? super Double, ? super Double, ? super Double, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$onFaceDetectResult$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function4<? super Integer, ? super Double, ? super Double, ? super Double, ? extends Unit> function4) {
                invoke2((Function4<? super Integer, ? super Double, ? super Double, ? super Double, Unit>) function4);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function4<? super Integer, ? super Double, ? super Double, ? super Double, Unit> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.invoke(Integer.valueOf(p0), Double.valueOf(p1), Double.valueOf(p2), Double.valueOf(p3));
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.ISDKListener
    public void onAddRelocationPointResult(boolean p0, String p1) {
        Pdlog.m3273d(this.TAG, "onAddRelocationPointResult: p0=" + p0 + "  p1=" + p1);
    }

    @Override // com.pudutech.mirsdk.aidl.ISDKListener
    public void onBatteryFloorLevelLimitResult(final int p0, final int p1) {
        Pdlog.m3273d(this.TAG, "onBatteryFloorLevelLimitResult: p0=" + p0 + "  p1=" + p1);
        getBatteryFloorLevelLimitResultListeners().forEach(new Function1<Function2<? super Integer, ? super Integer, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$onBatteryFloorLevelLimitResult$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Integer, ? super Integer, ? extends Unit> function2) {
                invoke2((Function2<? super Integer, ? super Integer, Unit>) function2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function2<? super Integer, ? super Integer, Unit> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.invoke(Integer.valueOf(p0), Integer.valueOf(p1));
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.ISDKListener
    public void securityFeedback(final boolean p0) {
        Pdlog.m3273d(this.TAG, "securityFeedback");
        getSecurityFeedBackListeners().forEach(new Function1<Function1<? super Boolean, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$securityFeedback$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function1<? super Boolean, ? extends Unit> function1) {
                invoke2((Function1<? super Boolean, Unit>) function1);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function1<? super Boolean, Unit> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.invoke(Boolean.valueOf(p0));
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.ISDKListener
    public void onAddChargePointResult(boolean p0, String p1) {
        Pdlog.m3273d(this.TAG, "onAddChargePointResult: p0=" + p0 + "  p1=" + p1);
    }
}
