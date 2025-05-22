package com.pudutech.mirsdk.mirhardware;

import android.content.ComponentName;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.mirsdk.WatchDog;
import com.pudutech.mirsdk.aidl.ISDKListener;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdk.base.Event;
import com.pudutech.mirsdk.config.SDKConfig;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.ILidarData;
import com.pudutech.mirsdk.hardware.IMarkerCameraState;
import com.pudutech.mirsdk.hardware.IRgbdStatus;
import com.pudutech.mirsdk.hardware.LidarInterface;
import com.pudutech.mirsdk.hardware.ScheduleCommunication;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.PolarCoordinates;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import com.pudutech.mirsdk.update.ApiConstants;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlinx.coroutines.DelayKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: RobotHardware.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Ñ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003*\u0005\u0018':AJ\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002}~B\u0087\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012K\u0010\u0005\u001aG\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u0006\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012#\u0010\u0012\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000f0\u0013¢\u0006\u0002\u0010\u0015J\u0016\u0010f\u001a\u00020g2\u0006\u0010h\u001a\u00020+2\u0006\u0010i\u001a\u00020\u001cJ\u0006\u0010j\u001a\u00020\u000fJ \u0010k\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020D2\b\b\u0002\u0010\u000e\u001a\u00020\rJ\b\u0010l\u001a\u00020\u000fH\u0002J\u001c\u0010m\u001a\u00020\u000f2\b\u0010\t\u001a\u0004\u0018\u00010n2\b\u0010o\u001a\u0004\u0018\u00010pH\u0016J\b\u0010q\u001a\u00020\u000fH\u0002J\u0014\u0010r\u001a\u00020\u000f2\f\u0010s\u001a\b\u0012\u0004\u0012\u00020u0tJ\u0011\u0010v\u001a\u00020\u001cH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010wJ \u0010x\u001a\u00020\u000f2\u0006\u0010y\u001a\u00020+2\u0006\u0010z\u001a\u00020\r2\u0006\u0010{\u001a\u00020|H\u0002R\u000e\u0010\u0016\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0019R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\r0\u001b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u001b¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001eR\u0010\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0004\n\u0002\u0010(R\u001a\u0010)\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u001c0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R_\u0010\u0005\u001aG\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u00101\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001a\u00106\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00103\"\u0004\b8\u00105R\u0010\u00109\u001a\u00020:X\u0082\u0004¢\u0006\u0004\n\u0002\u0010;R\u001d\u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020>0=0\u001b¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u001eR\u0010\u0010@\u001a\u00020AX\u0082\u0004¢\u0006\u0004\n\u0002\u0010BR\u000e\u0010C\u001a\u00020DX\u0082\u000e¢\u0006\u0002\n\u0000R7\u0010\u0012\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000f0\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u0010\u0010I\u001a\u00020JX\u0082\u0004¢\u0006\u0004\n\u0002\u0010KR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010MR\u0017\u0010N\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b¢\u0006\b\n\u0000\u001a\u0004\bO\u0010\u001eR%\u0010P\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0006\u0012\u0004\u0018\u00010\r0Q0\u001b¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\u001eR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\bS\u0010TR$\u0010V\u001a\u00020\u001c2\u0006\u0010U\u001a\u00020\u001c@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR$\u0010[\u001a\u00020\u001c2\u0006\u0010U\u001a\u00020\u001c@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010X\"\u0004\b]\u0010ZR-\u0010^\u001a\u001e\u0012\u0004\u0012\u00020\r\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\r\u0012\b\u0012\u00060`R\u00020\u00000*0_¢\u0006\b\n\u0000\u001a\u0004\ba\u0010bR\u001a\u0010c\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010X\"\u0004\be\u0010Z\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u007f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mirhardware/RobotHardware;", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "robotStatus", "Lcom/pudutech/mirsdk/mirhardware/RobotStatus;", "initStepNotify", "Lkotlin/Function3;", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "Lkotlin/ParameterName;", "name", "step", "Lcom/pudutech/mirsdk/aidl/serialize/StepState;", "stepState", "", "description", "", "watchDog", "Lcom/pudutech/mirsdk/WatchDog;", "reportCloud", "Lkotlin/Function1;", NotificationCompat.CATEGORY_MESSAGE, "(Lcom/pudutech/mirsdk/mirhardware/RobotStatus;Lkotlin/jvm/functions/Function3;Lcom/pudutech/mirsdk/WatchDog;Lkotlin/jvm/functions/Function1;)V", "TAG", "cameraListener", "com/pudutech/mirsdk/mirhardware/RobotHardware$cameraListener$1", "Lcom/pudutech/mirsdk/mirhardware/RobotHardware$cameraListener$1;", "chargingPileConnectedEvent", "Lcom/pudutech/mirsdk/base/Event;", "", "getChargingPileConnectedEvent", "()Lcom/pudutech/mirsdk/base/Event;", "emergencyEvent", "getEmergencyEvent", "errorEvent", "getErrorEvent", "fallDrop", "Lcom/pudutech/mirsdk/mirhardware/GeomagneticData;", "getFallDrop", "iHardware", "com/pudutech/mirsdk/mirhardware/RobotHardware$iHardware$1", "Lcom/pudutech/mirsdk/mirhardware/RobotHardware$iHardware$1;", "ignoreWheelErrorMap", "Ljava/util/HashMap;", "Lcom/pudutech/mirsdk/hardware/serialize/WheelError;", "initStatus", "getInitStepNotify", "()Lkotlin/jvm/functions/Function3;", "setInitStepNotify", "(Lkotlin/jvm/functions/Function3;)V", "lastErrorMulfunction", "getLastErrorMulfunction", "()Ljava/lang/String;", "setLastErrorMulfunction", "(Ljava/lang/String;)V", "lastWarnMulfunction", "getLastWarnMulfunction", "setLastWarnMulfunction", "lidarDataListener", "com/pudutech/mirsdk/mirhardware/RobotHardware$lidarDataListener$1", "Lcom/pudutech/mirsdk/mirhardware/RobotHardware$lidarDataListener$1;", "lidarEvent", "", "Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;", "getLidarEvent", "lidarListener", "com/pudutech/mirsdk/mirhardware/RobotHardware$lidarListener$1", "Lcom/pudutech/mirsdk/mirhardware/RobotHardware$lidarListener$1;", "openStep", "Lcom/pudutech/mirsdk/hardware/serialize/StepState;", "getReportCloud", "()Lkotlin/jvm/functions/Function1;", "setReportCloud", "(Lkotlin/jvm/functions/Function1;)V", "rgbdStatusListener", "com/pudutech/mirsdk/mirhardware/RobotHardware$rgbdStatusListener$1", "Lcom/pudutech/mirsdk/mirhardware/RobotHardware$rgbdStatusListener$1;", "getRobotStatus", "()Lcom/pudutech/mirsdk/mirhardware/RobotStatus;", "securityEvent", "getSecurityEvent", "warnEvent", "Lkotlin/Pair;", "getWarnEvent", "getWatchDog", "()Lcom/pudutech/mirsdk/WatchDog;", ES6Iterator.VALUE_PROPERTY, "wheelInError", "getWheelInError", "()Z", "setWheelInError", "(Z)V", "wheelInWarning", "getWheelInWarning", "setWheelInWarning", "wheelMulfunction", "", "Lcom/pudutech/mirsdk/mirhardware/RobotHardware$Mulfunction;", "getWheelMulfunction", "()Ljava/util/Map;", "wheelMulfunctionState", "getWheelMulfunctionState", "setWheelMulfunctionState", "controlWheelErrorEvent", "", NotificationCompat.CATEGORY_ERROR, "response", "notifyInitDone", "onInitStepNotifyHardware", "onOpenSuccess", "onServiceConnected", "Landroid/content/ComponentName;", "service", "Landroid/os/IBinder;", "reportWheelError", "scheduleCommunicationListener", "listener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/aidl/ISDKListener;", "startUpAndWaitResult", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateWheelWelfunction", "error", "type", "currentTime", "", "ErrorLevel", "Mulfunction", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotHardware extends AIDLConnection<HardwareInterface> {
    private final String TAG;
    private final RobotHardware$cameraListener$1 cameraListener;
    private final Event<Boolean> chargingPileConnectedEvent;
    private final Event<Boolean> emergencyEvent;
    private final Event<String> errorEvent;
    private final Event<GeomagneticData> fallDrop;
    private RobotHardware$iHardware$1 iHardware;
    private final HashMap<WheelError, Boolean> ignoreWheelErrorMap;
    private boolean initStatus;
    private Function3<? super InitStep, ? super StepState, ? super String, Unit> initStepNotify;
    private String lastErrorMulfunction;
    private String lastWarnMulfunction;
    private final RobotHardware$lidarDataListener$1 lidarDataListener;
    private final Event<PolarCoordinates[]> lidarEvent;
    private final RobotHardware$lidarListener$1 lidarListener;
    private com.pudutech.mirsdk.hardware.serialize.StepState openStep;
    private Function1<? super String, Unit> reportCloud;
    private final RobotHardware$rgbdStatusListener$1 rgbdStatusListener;
    private final RobotStatus robotStatus;
    private final Event<Boolean> securityEvent;
    private final Event<Pair<Boolean, String>> warnEvent;
    private final WatchDog watchDog;
    private boolean wheelInError;
    private boolean wheelInWarning;
    private final Map<String, HashMap<String, Mulfunction>> wheelMulfunction;
    private boolean wheelMulfunctionState;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[com.pudutech.mirsdk.hardware.serialize.StepState.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;
        public static final /* synthetic */ int[] $EnumSwitchMapping$7;

        static {
            $EnumSwitchMapping$0[com.pudutech.mirsdk.hardware.serialize.StepState.Success.ordinal()] = 1;
            $EnumSwitchMapping$0[com.pudutech.mirsdk.hardware.serialize.StepState.Fail.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[HardwareOpenStep.values().length];
            $EnumSwitchMapping$1[HardwareOpenStep.MCUUpdate.ordinal()] = 1;
            $EnumSwitchMapping$2 = new int[com.pudutech.mirsdk.hardware.serialize.StepState.values().length];
            $EnumSwitchMapping$2[com.pudutech.mirsdk.hardware.serialize.StepState.Fail.ordinal()] = 1;
            $EnumSwitchMapping$2[com.pudutech.mirsdk.hardware.serialize.StepState.Success.ordinal()] = 2;
            $EnumSwitchMapping$3 = new int[HardwareOpenStep.values().length];
            $EnumSwitchMapping$3[HardwareOpenStep.RGBDDataCheck.ordinal()] = 1;
            $EnumSwitchMapping$4 = new int[com.pudutech.mirsdk.hardware.serialize.StepState.values().length];
            $EnumSwitchMapping$4[com.pudutech.mirsdk.hardware.serialize.StepState.Fail.ordinal()] = 1;
            $EnumSwitchMapping$4[com.pudutech.mirsdk.hardware.serialize.StepState.Success.ordinal()] = 2;
            $EnumSwitchMapping$5 = new int[HardwareOpenStep.values().length];
            $EnumSwitchMapping$5[HardwareOpenStep.Finish.ordinal()] = 1;
            $EnumSwitchMapping$5[HardwareOpenStep.FetchMachineInfo.ordinal()] = 2;
            $EnumSwitchMapping$5[HardwareOpenStep.ESPCheck.ordinal()] = 3;
            $EnumSwitchMapping$5[HardwareOpenStep.CanConnect.ordinal()] = 4;
            $EnumSwitchMapping$5[HardwareOpenStep.MCUUpdateInIAPMode.ordinal()] = 5;
            $EnumSwitchMapping$5[HardwareOpenStep.MCUUpdate.ordinal()] = 6;
            $EnumSwitchMapping$5[HardwareOpenStep.GetHardwareVersion.ordinal()] = 7;
            $EnumSwitchMapping$5[HardwareOpenStep.IMUCheck.ordinal()] = 8;
            $EnumSwitchMapping$5[HardwareOpenStep.EncoderCheck.ordinal()] = 9;
            $EnumSwitchMapping$5[HardwareOpenStep.BatteryCheck.ordinal()] = 10;
            $EnumSwitchMapping$5[HardwareOpenStep.LidarCheck.ordinal()] = 11;
            $EnumSwitchMapping$5[HardwareOpenStep.MagicSensorCheck.ordinal()] = 12;
            $EnumSwitchMapping$5[HardwareOpenStep.CameraCheck.ordinal()] = 13;
            $EnumSwitchMapping$5[HardwareOpenStep.RGBDConnectCheck.ordinal()] = 14;
            $EnumSwitchMapping$5[HardwareOpenStep.RGBDFwUpdate.ordinal()] = 15;
            $EnumSwitchMapping$5[HardwareOpenStep.RGBDConfigCheck.ordinal()] = 16;
            $EnumSwitchMapping$5[HardwareOpenStep.RGBDDataCheck.ordinal()] = 17;
            $EnumSwitchMapping$6 = new int[WheelError.values().length];
            $EnumSwitchMapping$6[WheelError.CANCmdLose.ordinal()] = 1;
            $EnumSwitchMapping$6[WheelError.CANBreak.ordinal()] = 2;
            $EnumSwitchMapping$6[WheelError.CoincidentAxisError.ordinal()] = 3;
            $EnumSwitchMapping$6[WheelError.InternalError.ordinal()] = 4;
            $EnumSwitchMapping$6[WheelError.SpeedOver.ordinal()] = 5;
            $EnumSwitchMapping$6[WheelError.MotorStuck.ordinal()] = 6;
            $EnumSwitchMapping$6[WheelError.MotorOver.ordinal()] = 7;
            $EnumSwitchMapping$6[WheelError.PhaseCurOver.ordinal()] = 8;
            $EnumSwitchMapping$6[WheelError.SpeedFlowDeviation.ordinal()] = 9;
            $EnumSwitchMapping$6[WheelError.BumpSwitchReset.ordinal()] = 10;
            $EnumSwitchMapping$6[WheelError.EmergencyKeyPressed.ordinal()] = 11;
            $EnumSwitchMapping$7 = new int[ErrorLevel.values().length];
            $EnumSwitchMapping$7[ErrorLevel.Warning.ordinal()] = 1;
        }
    }

    public final RobotStatus getRobotStatus() {
        return this.robotStatus;
    }

    public final Function3<InitStep, StepState, String, Unit> getInitStepNotify() {
        return this.initStepNotify;
    }

    public final void setInitStepNotify(Function3<? super InitStep, ? super StepState, ? super String, Unit> function3) {
        Intrinsics.checkParameterIsNotNull(function3, "<set-?>");
        this.initStepNotify = function3;
    }

    public final WatchDog getWatchDog() {
        return this.watchDog;
    }

    public final Function1<String, Unit> getReportCloud() {
        return this.reportCloud;
    }

    public final void setReportCloud(Function1<? super String, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "<set-?>");
        this.reportCloud = function1;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RobotHardware.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012,\u0010\u0003\u001a( \u0002*\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\b"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "kotlin.jvm.PlatformType", "p1", "Landroid/os/IBinder;", "Lkotlin/ParameterName;", "name", "p0", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mirhardware.RobotHardware$1 */
    /* loaded from: classes6.dex */
    static final /* synthetic */ class C52751 extends FunctionReference implements Function1<IBinder, HardwareInterface> {
        public static final C52751 INSTANCE = new C52751();

        C52751() {
            super(1);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "asInterface";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(HardwareInterface.Stub.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "asInterface(Landroid/os/IBinder;)Lcom/pudutech/mirsdk/hardware/HardwareInterface;";
        }

        @Override // kotlin.jvm.functions.Function1
        public final HardwareInterface invoke(IBinder iBinder) {
            return HardwareInterface.Stub.asInterface(iBinder);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r0v22, types: [com.pudutech.mirsdk.mirhardware.RobotHardware$lidarDataListener$1] */
    /* JADX WARN: Type inference failed for: r0v23, types: [com.pudutech.mirsdk.mirhardware.RobotHardware$rgbdStatusListener$1] */
    /* JADX WARN: Type inference failed for: r0v24, types: [com.pudutech.mirsdk.mirhardware.RobotHardware$cameraListener$1] */
    public RobotHardware(RobotStatus robotStatus, Function3<? super InitStep, ? super StepState, ? super String, Unit> initStepNotify, WatchDog watchDog, Function1<? super String, Unit> reportCloud) {
        super("com.pudutech.mirsdk.hardware.HardwareService", C52751.INSTANCE, null, 4, null);
        Intrinsics.checkParameterIsNotNull(robotStatus, "robotStatus");
        Intrinsics.checkParameterIsNotNull(initStepNotify, "initStepNotify");
        Intrinsics.checkParameterIsNotNull(watchDog, "watchDog");
        Intrinsics.checkParameterIsNotNull(reportCloud, "reportCloud");
        this.robotStatus = robotStatus;
        this.initStepNotify = initStepNotify;
        this.watchDog = watchDog;
        this.reportCloud = reportCloud;
        this.TAG = "RobotHardware";
        this.openStep = com.pudutech.mirsdk.hardware.serialize.StepState.Idle;
        this.errorEvent = new Event<>();
        this.warnEvent = new Event<>();
        this.lidarEvent = new Event<>();
        this.emergencyEvent = new Event<>();
        this.chargingPileConnectedEvent = new Event<>();
        this.fallDrop = new Event<>();
        this.securityEvent = new Event<>();
        this.ignoreWheelErrorMap = new HashMap<>();
        this.wheelMulfunction = new LinkedHashMap();
        this.lastErrorMulfunction = "{\"error_type\":\"UnknownError\", \"level\":\"Error\"}";
        this.lastWarnMulfunction = "{\"error_type\":\"UnknownError\", \"level\":\"Error\"}";
        this.watchDog.watch("CAN", 100L, SolicitService.CAMERA_OPEN_TIME_OUT);
        this.watchDog.watch("IMU", 200L, SolicitService.CAMERA_OPEN_TIME_OUT);
        this.watchDog.watch("Encoder", 200L, SolicitService.CAMERA_OPEN_TIME_OUT);
        this.watchDog.watch("Lidar", 300L, 1000L);
        this.watchDog.watch("Battery", 7000L, 10000L);
        this.lidarListener = new RobotHardware$lidarListener$1(this);
        this.lidarDataListener = new ILidarData.Stub() { // from class: com.pudutech.mirsdk.mirhardware.RobotHardware$lidarDataListener$1
            @Override // com.pudutech.mirsdk.hardware.ILidarData
            public void onFrame(PolarCoordinates[] p0, long p1) {
                Intrinsics.checkParameterIsNotNull(p0, "p0");
                RobotHardware.this.getLidarEvent().emit(p0);
            }
        };
        this.rgbdStatusListener = new IRgbdStatus.Stub() { // from class: com.pudutech.mirsdk.mirhardware.RobotHardware$rgbdStatusListener$1
            @Override // com.pudutech.mirsdk.hardware.IRgbdStatus
            public void onParkingMode(boolean p0) {
            }

            @Override // com.pudutech.mirsdk.hardware.IRgbdStatus
            public void onRGBDFrameTick() {
                RobotHardware.this.getWatchDog().tick("RGBD");
            }

            @Override // com.pudutech.mirsdk.hardware.IRgbdStatus
            public void onRGBDOpened(boolean p0) {
                String str;
                str = RobotHardware.this.TAG;
                Pdlog.m3275i(str, "RGBD device open status " + p0);
                RobotHardware.this.getRobotStatus().getRgbdOpened().setValue(Boolean.valueOf(p0));
            }
        };
        this.cameraListener = new IMarkerCameraState.Stub() { // from class: com.pudutech.mirsdk.mirhardware.RobotHardware$cameraListener$1
            @Override // com.pudutech.mirsdk.hardware.IMarkerCameraState
            public void onCameraFrameTick() {
                RobotHardware.this.getWatchDog().tick("Camera");
            }

            @Override // com.pudutech.mirsdk.hardware.IMarkerCameraState
            public void onOpened(boolean p0) {
                RobotHardware.this.getRobotStatus().getCameraOpened().setValue(Boolean.valueOf(p0));
            }
        };
        this.iHardware = new RobotHardware$iHardware$1(this);
    }

    public final Event<String> getErrorEvent() {
        return this.errorEvent;
    }

    public final Event<Pair<Boolean, String>> getWarnEvent() {
        return this.warnEvent;
    }

    public final Event<PolarCoordinates[]> getLidarEvent() {
        return this.lidarEvent;
    }

    public final Event<Boolean> getEmergencyEvent() {
        return this.emergencyEvent;
    }

    public final Event<Boolean> getChargingPileConnectedEvent() {
        return this.chargingPileConnectedEvent;
    }

    public final Event<GeomagneticData> getFallDrop() {
        return this.fallDrop;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RobotHardware.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0013\b\u0086\u0004\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000e¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/mirsdk/mirhardware/RobotHardware$Mulfunction;", "", "type", "", "level", "Lcom/pudutech/mirsdk/mirhardware/RobotHardware$ErrorLevel;", "detail", "retriveLimit", "", "occureTime", "(Lcom/pudutech/mirsdk/mirhardware/RobotHardware;Ljava/lang/String;Lcom/pudutech/mirsdk/mirhardware/RobotHardware$ErrorLevel;Ljava/lang/String;JJ)V", "getDetail", "()Ljava/lang/String;", "setDetail", "(Ljava/lang/String;)V", "getLevel", "()Lcom/pudutech/mirsdk/mirhardware/RobotHardware$ErrorLevel;", "setLevel", "(Lcom/pudutech/mirsdk/mirhardware/RobotHardware$ErrorLevel;)V", "getOccureTime", "()J", "setOccureTime", "(J)V", "getRetriveLimit", "setRetriveLimit", "getType", "setType", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class Mulfunction {
        private String detail;
        private ErrorLevel level;
        private long occureTime;
        private long retriveLimit;
        final /* synthetic */ RobotHardware this$0;
        private String type;

        public Mulfunction(RobotHardware robotHardware, String type, ErrorLevel level, String detail, long j, long j2) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(level, "level");
            Intrinsics.checkParameterIsNotNull(detail, "detail");
            this.this$0 = robotHardware;
            this.type = type;
            this.level = level;
            this.detail = detail;
            this.retriveLimit = j;
            this.occureTime = j2;
        }

        public final String getDetail() {
            return this.detail;
        }

        public final ErrorLevel getLevel() {
            return this.level;
        }

        public final long getOccureTime() {
            return this.occureTime;
        }

        public final long getRetriveLimit() {
            return this.retriveLimit;
        }

        public final String getType() {
            return this.type;
        }

        public final void setDetail(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.detail = str;
        }

        public final void setLevel(ErrorLevel errorLevel) {
            Intrinsics.checkParameterIsNotNull(errorLevel, "<set-?>");
            this.level = errorLevel;
        }

        public final void setOccureTime(long j) {
            this.occureTime = j;
        }

        public final void setRetriveLimit(long j) {
            this.retriveLimit = j;
        }

        public final void setType(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.type = str;
        }
    }

    public final Event<Boolean> getSecurityEvent() {
        return this.securityEvent;
    }

    public final Map<String, HashMap<String, Mulfunction>> getWheelMulfunction() {
        return this.wheelMulfunction;
    }

    public final boolean getWheelInError() {
        return this.wheelInError;
    }

    public final void setWheelInError(boolean z) {
        this.wheelInError = z;
        this.wheelMulfunctionState = this.wheelInError;
    }

    public final boolean getWheelInWarning() {
        return this.wheelInWarning;
    }

    public final void setWheelInWarning(boolean z) {
        this.wheelInWarning = z;
        this.wheelMulfunctionState = this.wheelInWarning;
    }

    public final boolean getWheelMulfunctionState() {
        return this.wheelMulfunctionState;
    }

    public final void setWheelMulfunctionState(boolean z) {
        this.wheelMulfunctionState = z;
    }

    public final String getLastErrorMulfunction() {
        return this.lastErrorMulfunction;
    }

    public final void setLastErrorMulfunction(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.lastErrorMulfunction = str;
    }

    public final String getLastWarnMulfunction() {
        return this.lastWarnMulfunction;
    }

    public final void setLastWarnMulfunction(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.lastWarnMulfunction = str;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RobotHardware.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u001f\u0010\u0002\u001a\u00020\u0003X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/mirsdk/mirhardware/RobotHardware$ErrorLevel;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "setId-7apg3OU", "(B)V", "B", MoveError.LEVEL_EVENT, "Warning", MoveError.LEVEL_ERROR, MoveError.LEVEL_FATAL, "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum ErrorLevel {
        Event((byte) 0),
        Warning((byte) 1),
        Error((byte) 2),
        Fatal((byte) 3);

        private byte id;

        ErrorLevel(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* renamed from: setId-7apg3OU, reason: not valid java name */
        public final void m4463setId7apg3OU(byte b) {
            this.id = b;
        }
    }

    public static /* synthetic */ void onInitStepNotifyHardware$default(RobotHardware robotHardware, InitStep initStep, com.pudutech.mirsdk.hardware.serialize.StepState stepState, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = "";
        }
        robotHardware.onInitStepNotifyHardware(initStep, stepState, str);
    }

    public final void onInitStepNotifyHardware(InitStep step, com.pudutech.mirsdk.hardware.serialize.StepState stepState, String description) {
        Intrinsics.checkParameterIsNotNull(step, "step");
        Intrinsics.checkParameterIsNotNull(stepState, "stepState");
        Intrinsics.checkParameterIsNotNull(description, "description");
        this.initStepNotify.invoke(step, StepState.valueOf(stepState.name()), description);
    }

    public final void scheduleCommunicationListener(ThreadSafeListener<ISDKListener> listener) {
        ScheduleCommunication scheduler;
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        HardwareInterface hardwareInterface = getInterface();
        if (hardwareInterface == null || (scheduler = hardwareInterface.getScheduler()) == null) {
            return;
        }
        scheduler.addSchCommInfoListener("function", new RobotHardware$scheduleCommunicationListener$1(listener));
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object startUpAndWaitResult(Continuation<? super Boolean> continuation) {
        RobotHardware$startUpAndWaitResult$1 robotHardware$startUpAndWaitResult$1;
        int i;
        RobotHardware robotHardware;
        HardwareInterface hardwareInterface;
        String str;
        HardwareInterface hardwareInterface2;
        HardwareInterface hardwareInterface3;
        String commitList;
        if (continuation instanceof RobotHardware$startUpAndWaitResult$1) {
            robotHardware$startUpAndWaitResult$1 = (RobotHardware$startUpAndWaitResult$1) continuation;
            if ((robotHardware$startUpAndWaitResult$1.label & Integer.MIN_VALUE) != 0) {
                robotHardware$startUpAndWaitResult$1.label -= Integer.MIN_VALUE;
                Object obj = robotHardware$startUpAndWaitResult$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = robotHardware$startUpAndWaitResult$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (getInterface() == null) {
                        return Boxing.boxBoolean(false);
                    }
                    HardwareInterface hardwareInterface4 = getInterface();
                    if (hardwareInterface4 != null) {
                        hardwareInterface4.open();
                    }
                    this.openStep = com.pudutech.mirsdk.hardware.serialize.StepState.Running;
                    robotHardware = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    RobotHardware robotHardware2 = (RobotHardware) robotHardware$startUpAndWaitResult$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    robotHardware = robotHardware2;
                }
                while (robotHardware.openStep == com.pudutech.mirsdk.hardware.serialize.StepState.Running) {
                    robotHardware$startUpAndWaitResult$1.L$0 = robotHardware;
                    robotHardware$startUpAndWaitResult$1.label = 1;
                    if (DelayKt.delay(10L, robotHardware$startUpAndWaitResult$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                boolean z = robotHardware.openStep != com.pudutech.mirsdk.hardware.serialize.StepState.Success;
                Pdlog.m3275i(robotHardware.TAG, "hardware start up " + z);
                hardwareInterface = robotHardware.getInterface();
                str = "{\"hardware\":\"unknow hardare\"}";
                if (hardwareInterface != null || (r1 = hardwareInterface.getCommitList()) == null) {
                    String str2 = "{\"hardware\":\"unknow hardare\"}";
                }
                Pdlog.resetGitHash(str2);
                String str3 = robotHardware.TAG;
                Object[] objArr = new Object[1];
                hardwareInterface2 = robotHardware.getInterface();
                if (hardwareInterface2 != null && (commitList = hardwareInterface2.getCommitList()) != null) {
                    str = commitList;
                }
                objArr[0] = str;
                Pdlog.m3273d(str3, objArr);
                SDKConfig sDKConfig = SDKConfig.INSTANCE;
                hardwareInterface3 = robotHardware.getInterface();
                if (hardwareInterface3 != null || (r8 = hardwareInterface3.getWifiMac()) == null) {
                    String str4 = ApiConstants.MAC_ADDRESS;
                }
                sDKConfig.setMAC(str4);
                return Boxing.boxBoolean(z);
            }
        }
        robotHardware$startUpAndWaitResult$1 = new RobotHardware$startUpAndWaitResult$1(this, continuation);
        Object obj2 = robotHardware$startUpAndWaitResult$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = robotHardware$startUpAndWaitResult$1.label;
        if (i != 0) {
        }
        while (robotHardware.openStep == com.pudutech.mirsdk.hardware.serialize.StepState.Running) {
        }
        if (robotHardware.openStep != com.pudutech.mirsdk.hardware.serialize.StepState.Success) {
        }
        Pdlog.m3275i(robotHardware.TAG, "hardware start up " + z);
        hardwareInterface = robotHardware.getInterface();
        str = "{\"hardware\":\"unknow hardare\"}";
        if (hardwareInterface != null) {
        }
        String str22 = "{\"hardware\":\"unknow hardare\"}";
        Pdlog.resetGitHash(str22);
        String str32 = robotHardware.TAG;
        Object[] objArr2 = new Object[1];
        hardwareInterface2 = robotHardware.getInterface();
        if (hardwareInterface2 != null) {
            str = commitList;
        }
        objArr2[0] = str;
        Pdlog.m3273d(str32, objArr2);
        SDKConfig sDKConfig2 = SDKConfig.INSTANCE;
        hardwareInterface3 = robotHardware.getInterface();
        if (hardwareInterface3 != null) {
        }
        String str42 = ApiConstants.MAC_ADDRESS;
        sDKConfig2.setMAC(str42);
        return Boxing.boxBoolean(z);
    }

    @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        super.onServiceConnected(name, service);
        HardwareInterface hardwareInterface = getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.addListener("function", this.iHardware);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onOpenSuccess() {
        CameraInterface camera;
        LidarInterface lidarInterface;
        LidarInterface lidarInterface2;
        HardwareInterface hardwareInterface = getInterface();
        if (hardwareInterface != null && (lidarInterface2 = hardwareInterface.getLidarInterface()) != null) {
            lidarInterface2.addStateListener("function", this.lidarListener);
        }
        this.lidarListener.onStartScan(true, "");
        HardwareInterface hardwareInterface2 = getInterface();
        if (hardwareInterface2 != null && (lidarInterface = hardwareInterface2.getLidarInterface()) != null) {
            lidarInterface.addDataListener("function", this.lidarDataListener);
        }
        HardwareInterface hardwareInterface3 = getInterface();
        if (hardwareInterface3 != null) {
            hardwareInterface3.controlCameraIRDLED(true);
        }
        HardwareInterface hardwareInterface4 = getInterface();
        if (hardwareInterface4 != null) {
            hardwareInterface4.addSensorListener("function", new RobotHardware$onOpenSuccess$1(this));
        }
        HardwareInterface hardwareInterface5 = getInterface();
        if (hardwareInterface5 == null || (camera = hardwareInterface5.getCamera()) == null) {
            return;
        }
        camera.addMarkerCameraStateListener("function", this.cameraListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateWheelWelfunction(WheelError error, String type, long currentTime) {
        switch (error) {
            case CANCmdLose:
            case CANBreak:
                if (this.wheelMulfunction.containsKey(error.name())) {
                    HashMap<String, Mulfunction> hashMap = this.wheelMulfunction.get(error.name());
                    if (hashMap == null) {
                        Intrinsics.throwNpe();
                    }
                    if (hashMap.containsKey(type)) {
                        HashMap<String, Mulfunction> hashMap2 = this.wheelMulfunction.get(error.name());
                        if (hashMap2 == null) {
                            Intrinsics.throwNpe();
                        }
                        Mulfunction mulfunction = hashMap2.get(type);
                        if (mulfunction == null) {
                            Intrinsics.throwNpe();
                        }
                        long occureTime = currentTime - mulfunction.getOccureTime();
                        HashMap<String, Mulfunction> hashMap3 = this.wheelMulfunction.get(error.name());
                        if (hashMap3 == null) {
                            Intrinsics.throwNpe();
                        }
                        Mulfunction mulfunction2 = hashMap3.get(type);
                        if (mulfunction2 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (occureTime > mulfunction2.getRetriveLimit()) {
                            HashMap<String, Mulfunction> hashMap4 = this.wheelMulfunction.get(error.name());
                            if (hashMap4 == null) {
                                Intrinsics.throwNpe();
                            }
                            Mulfunction mulfunction3 = hashMap4.get(type);
                            if (mulfunction3 == null) {
                                Intrinsics.throwNpe();
                            }
                            mulfunction3.setLevel(ErrorLevel.Error);
                            Pdlog.m3273d(this.TAG, "set error");
                            return;
                        }
                        return;
                    }
                    HashMap<String, Mulfunction> hashMap5 = this.wheelMulfunction.get(error.name());
                    if (hashMap5 == null) {
                        Intrinsics.throwNpe();
                    }
                    hashMap5.put(type, new Mulfunction(this, type, ErrorLevel.Warning, error.name(), 500L, currentTime));
                    return;
                }
                Map<String, HashMap<String, Mulfunction>> map = this.wheelMulfunction;
                String name = error.name();
                Map mutableMapOf = MapsKt.mutableMapOf(new Pair(type, new Mulfunction(this, type, ErrorLevel.Warning, error.name(), 500L, currentTime)));
                if (mutableMapOf == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.util.HashMap<kotlin.String, com.pudutech.mirsdk.mirhardware.RobotHardware.Mulfunction>");
                }
                map.put(name, (HashMap) mutableMapOf);
                return;
            case CoincidentAxisError:
            case InternalError:
                return;
            case SpeedOver:
            case MotorStuck:
            case MotorOver:
            case PhaseCurOver:
            case SpeedFlowDeviation:
            case BumpSwitchReset:
                Map<String, HashMap<String, Mulfunction>> map2 = this.wheelMulfunction;
                String name2 = error.name();
                Map mutableMapOf2 = MapsKt.mutableMapOf(new Pair(type, new Mulfunction(this, type, ErrorLevel.Error, error.name(), 0L, currentTime)));
                if (mutableMapOf2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.util.HashMap<kotlin.String, com.pudutech.mirsdk.mirhardware.RobotHardware.Mulfunction>");
                }
                map2.put(name2, (HashMap) mutableMapOf2);
                return;
            case EmergencyKeyPressed:
                Map<String, HashMap<String, Mulfunction>> map3 = this.wheelMulfunction;
                String name3 = error.name();
                Map mutableMapOf3 = MapsKt.mutableMapOf(new Pair(type, new Mulfunction(this, type, ErrorLevel.Event, error.name(), 0L, currentTime)));
                if (mutableMapOf3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.util.HashMap<kotlin.String, com.pudutech.mirsdk.mirhardware.RobotHardware.Mulfunction>");
                }
                map3.put(name3, (HashMap) mutableMapOf3);
                return;
            default:
                Map<String, HashMap<String, Mulfunction>> map4 = this.wheelMulfunction;
                String name4 = error.name();
                Map mutableMapOf4 = MapsKt.mutableMapOf(new Pair(type, new Mulfunction(this, type, ErrorLevel.Fatal, error.name(), 0L, currentTime)));
                if (mutableMapOf4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.util.HashMap<kotlin.String, com.pudutech.mirsdk.mirhardware.RobotHardware.Mulfunction>");
                }
                map4.put(name4, (HashMap) mutableMapOf4);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reportWheelError() {
        String str = "[";
        String str2 = "[";
        boolean z = false;
        boolean z2 = false;
        for (Map.Entry<String, HashMap<String, Mulfunction>> entry : this.wheelMulfunction.entrySet()) {
            for (Map.Entry<String, Mulfunction> entry2 : entry.getValue().entrySet()) {
                if (WhenMappings.$EnumSwitchMapping$7[entry2.getValue().getLevel().ordinal()] == 1) {
                    str2 = str2 + "{\"error_type\":\"" + entry2.getKey() + "\", \"level\":\"" + entry2.getValue().getLevel().name() + "\", \"detail\":\"" + entry.getKey() + "\"},";
                    z2 = true;
                } else {
                    str = str + "{\"error_type\":\"" + entry2.getKey() + "\", \"level\":\"" + entry2.getValue().getLevel().name() + "\", \"detail\":\"" + entry.getKey() + "\"},";
                    z = true;
                }
            }
        }
        String str3 = str + "]";
        String str4 = str2 + "]";
        if (z) {
            Pdlog.m3277w(this.TAG, "error to stop");
            setWheelInError(true);
            this.lastErrorMulfunction = str3;
            this.errorEvent.emit(str3);
            return;
        }
        if (!this.wheelInWarning && z2) {
            Pdlog.m3277w(this.TAG, "warning to stop");
            setWheelInWarning(true);
            this.lastWarnMulfunction = str4;
            this.warnEvent.emit(new Pair<>(true, str4));
            return;
        }
        if (!this.wheelInWarning || z2) {
            return;
        }
        Pdlog.m3273d(this.TAG, "clear warning");
        setWheelInWarning(false);
        this.wheelMulfunction.clear();
        this.warnEvent.emit(new Pair<>(false, null));
    }

    public final int controlWheelErrorEvent(WheelError err, boolean response) {
        Intrinsics.checkParameterIsNotNull(err, "err");
        if (err != WheelError.BumpSwitchReset) {
            Pdlog.m3277w(this.TAG, "controlWheelErrorEvent rejected, it is not support to control " + err + '!');
            return -1;
        }
        this.ignoreWheelErrorMap.put(err, Boolean.valueOf(response));
        Pdlog.m3277w(this.TAG, "controlWheelErrorEvent approved, control " + err + " response = " + response);
        HardwareInterface hardwareInterface = getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.setBumperSwitch(response);
        } else {
            Pdlog.m3277w(this.TAG, "controlWheelErrorEvent hardwareInterface is null");
        }
        return 0;
    }

    public final void notifyInitDone() {
        this.initStatus = true;
    }
}
