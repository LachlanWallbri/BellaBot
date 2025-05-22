package com.pudutech.mirsdk;

import androidx.core.app.NotificationCompat;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.aidl.ISolicitListener;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.LidarInterface;
import com.pudutech.mirsdk.hardware.serialize.DeviceType;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.FaceDetectListener;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusInfo;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusLevel;
import com.pudutech.mirsdk.mircore.coreparcel.RotateResult;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import com.pudutech.mirsdk.mirhardware.RobotStatus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.TimeoutKt;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* compiled from: SolicitService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\b\u0005\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0002\u001e(\b\u0000\u0018\u0000 ^2\u00020\u0001:\u0002^_Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00126\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00130\f¢\u0006\u0002\u0010\u0014J\u0016\u0010:\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u00112\u0006\u0010;\u001a\u000202J\b\u0010<\u001a\u00020\u0013H\u0002J\u0011\u0010=\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010>J\b\u0010?\u001a\u00020\u0013H\u0002J\u0010\u0010@\u001a\u00020\u00132\u0006\u0010A\u001a\u00020\u0016H\u0002J\b\u0010B\u001a\u00020\u0013H\u0002J\u0018\u0010C\u001a\u00020\u00132\u0006\u0010D\u001a\u00020\u001c2\u0006\u0010E\u001a\u00020/H\u0002J\u0006\u0010F\u001a\u00020\u0013J\u0010\u0010G\u001a\u00020/2\u0006\u0010H\u001a\u00020/H\u0002J\b\u0010I\u001a\u00020\u0013H\u0002J\u0010\u0010J\u001a\u00020/2\u0006\u0010E\u001a\u00020/H\u0002J\u000e\u0010K\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0011J\b\u0010L\u001a\u00020\u0013H\u0002J\u0018\u0010M\u001a\u00020\u00132\u0006\u0010H\u001a\u00020/2\u0006\u0010N\u001a\u00020/H\u0002J\u0006\u0010O\u001a\u00020\u001cJ\b\u0010P\u001a\u00020\u0013H\u0002J\u0011\u0010Q\u001a\u00020\u0013H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010>J\u0006\u0010R\u001a\u00020\u0013J\u001c\u0010S\u001a\u00020\u00132\u0014\u0010T\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00110UJ\u000e\u0010V\u001a\u00020\u00132\u0006\u0010W\u001a\u00020\u0011J\u001e\u0010X\u001a\u00020\u00132\u0006\u0010Y\u001a\u00020/2\u0006\u0010Z\u001a\u00020/2\u0006\u0010[\u001a\u00020/J\u0014\u0010\\\u001a\u000e\u0012\u0004\u0012\u00020]\u0012\u0004\u0012\u00020\u00110UH\u0002R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0011X\u0082D¢\u0006\u0002\n\u0000R>\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00130\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001fR\u0010\u0010 \u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00110#X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0004\n\u0002\u0010)R\u000e\u0010*\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00100\u001a\b\u0012\u0004\u0012\u00020201X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00105\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006`"}, m3961d2 = {"Lcom/pudutech/mirsdk/SolicitService;", "", "robotHardware", "Lcom/pudutech/mirsdk/mirhardware/RobotHardware;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "robotStatus", "Lcom/pudutech/mirsdk/mirhardware/RobotStatus;", "watchDog", "Lcom/pudutech/mirsdk/WatchDog;", "_onStateChange", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lkotlin/ParameterName;", "name", "state", "", TmpConstant.SERVICE_DESC, "", "(Lcom/pudutech/mirsdk/mirhardware/RobotHardware;Lcom/pudutech/base/architecture/AIDLConnection;Lcom/pudutech/mirsdk/mirhardware/RobotStatus;Lcom/pudutech/mirsdk/WatchDog;Lkotlin/jvm/functions/Function2;)V", "NPUFlag", "", "TAG", "brakeJob", "Lkotlinx/coroutines/Job;", "errorJob", "faceDetectCount", "", "faceDetectListener", "com/pudutech/mirsdk/SolicitService$faceDetectListener$1", "Lcom/pudutech/mirsdk/SolicitService$faceDetectListener$1;", "fixAngarJob", "fixLinarJob", "lastSensorError", "", "localizationStatus", "Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatus;", "moveJob", "personDetectListener", "com/pudutech/mirsdk/SolicitService$personDetectListener$1", "Lcom/pudutech/mirsdk/SolicitService$personDetectListener$1;", "personDetectTime", "robotPose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "rotateVector", "solicitDegree", "", "solicitListeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/aidl/ISolicitListener;", "solicitState", "", "startPose", "getStartPose", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setStartPose", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "addSolicitListener", "l", "brakeUntilStop", "checkAndClearWheelError", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "closeFaceDetectNoNPU", "controlFaceDetect", "p0", "doSolicit", "faceDetectSolicit", "flag", "angle", "fixDegree", "getExtraAngleSpeed", "degree", NotificationCompat.CATEGORY_NAVIGATION, "normalAngle", "removeSolicitListener", "retriveMCU", "rotateDegree", "extraAngleSpeed", "startSolicit", "stop", "stopAndWaitBrake", "stopSolicit", "suspendWarningWelfunction", "warning", "Lkotlin/Pair;", "triggerError", "error", "updateRobotPos", "x", "y", CompressorStreamFactory.f8930Z, "watchDogCheck", "Lcom/pudutech/mirsdk/SolicitService$WatchLevel;", "Companion", "WatchLevel", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SolicitService {
    public static final double ANGULAR_ACCELERATION = 1.1d;
    public static final long CAMERA_OPEN_OR_CLOSE_INTERVAL = 500;
    public static final long CAMERA_OPEN_TIME_OUT = 3000;

    /* renamed from: Companion, reason: from kotlin metadata */
    @Deprecated
    public static final Companion INSTANCE = new Companion(null);
    public static final int NO_PERSON_COUNT = 30;
    public static final double PERSON_DISTANCE = 1.2d;
    public static final int SOLICIT_ALREADY = 1;
    public static final int SOLICIT_FAIL = -1;
    public static final int SOLICIT_SUCCESS = 0;
    public static final byte STATE_DETECT_FACE = 2;
    public static final byte STATE_DETECT_FACE_SUCCESS = 3;
    public static final byte STATE_DETECT_PERSON = 1;
    public static final byte STATE_IDLE = 0;
    private boolean NPUFlag;
    private final String TAG;
    private final Function2<RobotState, String, Unit> _onStateChange;
    private Job brakeJob;
    private final AIDLConnection<MirCoreInterface> coreService;
    private Job errorJob;
    private int faceDetectCount;
    private final SolicitService$faceDetectListener$1 faceDetectListener;
    private Job fixAngarJob;
    private Job fixLinarJob;
    private final List<String> lastSensorError;
    private LocalizationStatus localizationStatus;
    private Job moveJob;
    private final SolicitService$personDetectListener$1 personDetectListener;
    private int personDetectTime;
    private final RobotHardware robotHardware;
    private Vector3d robotPose;
    private final RobotStatus robotStatus;
    private final Vector3d rotateVector;
    private double solicitDegree;
    private ThreadSafeListener<ISolicitListener> solicitListeners;
    private byte solicitState;
    private Vector3d startPose;
    private final WatchDog watchDog;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MachineInfo.MonocularType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;

        static {
            $EnumSwitchMapping$0[MachineInfo.MonocularType.NoDevice.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[WatchLevel.values().length];
            $EnumSwitchMapping$1[WatchLevel.Over.ordinal()] = 1;
            $EnumSwitchMapping$1[WatchLevel.Pause.ordinal()] = 2;
            $EnumSwitchMapping$2 = new int[LocalizationStatusInfo.values().length];
            $EnumSwitchMapping$2[LocalizationStatusInfo.NoMarker.ordinal()] = 1;
            $EnumSwitchMapping$2[LocalizationStatusInfo.MarkerError.ordinal()] = 2;
            $EnumSwitchMapping$2[LocalizationStatusInfo.LaserLostRecovering.ordinal()] = 3;
            $EnumSwitchMapping$3 = new int[LocalizationStatusInfo.values().length];
            $EnumSwitchMapping$3[LocalizationStatusInfo.NoParam.ordinal()] = 1;
            $EnumSwitchMapping$3[LocalizationStatusInfo.NoInit.ordinal()] = 2;
            $EnumSwitchMapping$3[LocalizationStatusInfo.ParamError.ordinal()] = 3;
            $EnumSwitchMapping$3[LocalizationStatusInfo.MapError.ordinal()] = 4;
            $EnumSwitchMapping$3[LocalizationStatusInfo.NoMarker.ordinal()] = 5;
            $EnumSwitchMapping$3[LocalizationStatusInfo.MarkerError.ordinal()] = 6;
            $EnumSwitchMapping$3[LocalizationStatusInfo.LaserLocateLose.ordinal()] = 7;
            $EnumSwitchMapping$4 = new int[LocalizationStatusLevel.values().length];
            $EnumSwitchMapping$4[LocalizationStatusLevel.Normal.ordinal()] = 1;
            $EnumSwitchMapping$4[LocalizationStatusLevel.Warning.ordinal()] = 2;
            $EnumSwitchMapping$4[LocalizationStatusLevel.Error.ordinal()] = 3;
        }
    }

    private final double normalAngle(double angle) {
        while (angle < 0) {
            angle += 6.283185307179586d;
        }
        while (angle > 6.283185307179586d) {
            angle -= 6.283185307179586d;
        }
        return angle;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.pudutech.mirsdk.SolicitService$faceDetectListener$1] */
    public SolicitService(RobotHardware robotHardware, AIDLConnection<MirCoreInterface> coreService, RobotStatus robotStatus, WatchDog watchDog, Function2<? super RobotState, ? super String, Unit> _onStateChange) {
        Intrinsics.checkParameterIsNotNull(robotHardware, "robotHardware");
        Intrinsics.checkParameterIsNotNull(coreService, "coreService");
        Intrinsics.checkParameterIsNotNull(robotStatus, "robotStatus");
        Intrinsics.checkParameterIsNotNull(watchDog, "watchDog");
        Intrinsics.checkParameterIsNotNull(_onStateChange, "_onStateChange");
        this.robotHardware = robotHardware;
        this.coreService = coreService;
        this.robotStatus = robotStatus;
        this.watchDog = watchDog;
        this._onStateChange = _onStateChange;
        this.TAG = "SolicitService";
        this.solicitListeners = new ThreadSafeListener<>();
        this.robotPose = new Vector3d(-100.0d, -100.0d, -100.0d);
        this.rotateVector = new Vector3d(0.0d, 0.0d, 0.0d);
        this.startPose = new Vector3d(0.0d, 0.0d, 0.0d);
        this.localizationStatus = new LocalizationStatus(null, null, null, 7, null);
        this.personDetectListener = new SolicitService$personDetectListener$1(this);
        this.faceDetectListener = new FaceDetectListener.Stub() { // from class: com.pudutech.mirsdk.SolicitService$faceDetectListener$1
            public void onDetection(final int flag, final double yaw, final double pitch, final double distance, double angle) {
                ThreadSafeListener threadSafeListener;
                String str;
                threadSafeListener = SolicitService.this.solicitListeners;
                threadSafeListener.notify(new Function2<ISolicitListener, String, Unit>() { // from class: com.pudutech.mirsdk.SolicitService$faceDetectListener$1$onDetection$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ISolicitListener iSolicitListener, String str2) {
                        invoke2(iSolicitListener, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ISolicitListener it, String str2) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        it.onFaceDetectResult(flag, yaw, pitch, distance);
                    }
                });
                str = SolicitService.this.TAG;
                Pdlog.m3273d(str, "controlFaceDetect faceDetecListener-->flag=" + flag + ",yaw=" + yaw + ",pitch=" + pitch + ",distance=" + distance);
                SolicitService.this.faceDetectSolicit(flag, angle);
            }
        };
        this.lastSensorError = new ArrayList();
    }

    public final Vector3d getStartPose() {
        return this.startPose;
    }

    public final void setStartPose(Vector3d vector3d) {
        Intrinsics.checkParameterIsNotNull(vector3d, "<set-?>");
        this.startPose = vector3d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SolicitService.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/mirsdk/SolicitService$Companion;", "", "()V", "ANGULAR_ACCELERATION", "", "CAMERA_OPEN_OR_CLOSE_INTERVAL", "", "CAMERA_OPEN_TIME_OUT", "NO_PERSON_COUNT", "", "PERSON_DISTANCE", "SOLICIT_ALREADY", "SOLICIT_FAIL", "SOLICIT_SUCCESS", "STATE_DETECT_FACE", "", "STATE_DETECT_FACE_SUCCESS", "STATE_DETECT_PERSON", "STATE_IDLE", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double getExtraAngleSpeed(double degree) {
        double abs = Math.abs((degree * 180) / 3.141592653589793d);
        double d = 2;
        if (abs < d) {
            return 0.0d;
        }
        double d2 = 4;
        if (abs < d2) {
            return 0.18d / d2;
        }
        if (abs < 8) {
            return 0.18d / d;
        }
        if (abs < 16) {
            return 0.18d;
        }
        if (abs < 32) {
            return 0.27d;
        }
        return 0.18d * d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void rotateDegree(double degree, double extraAngleSpeed) {
        NavigationInterface navigator;
        NavigationInterface navigator2;
        NavigationInterface navigator3;
        double z = this.startPose.getZ() - 1.5707963267948966d;
        double z2 = this.startPose.getZ() + 1.5707963267948966d;
        Pdlog.m3273d(this.TAG, "BEFORE MIN ANGLE_: " + z + ", MAX ANGLE: " + z2 + ", DEST ANGLE: " + degree);
        double normalAngle = normalAngle(z);
        double normalAngle2 = normalAngle(z2);
        double normalAngle3 = normalAngle(degree);
        while (normalAngle2 < normalAngle) {
            normalAngle2 += 6.283185307179586d;
        }
        while (normalAngle3 < normalAngle) {
            normalAngle3 += 6.283185307179586d;
        }
        if (normalAngle3 > normalAngle2 && normalAngle3 < normalAngle2 + 1.5707963267948966d) {
            normalAngle3 = normalAngle2;
        } else if (normalAngle3 > 1.5707963267948966d + normalAngle2) {
            normalAngle3 = normalAngle;
        }
        Pdlog.m3273d(this.TAG, "AFTER MIN ANGLE_: " + normalAngle + ", MAX ANGLE: " + normalAngle2 + ", DEST ANGLE: " + normalAngle3);
        if (Math.abs(this.robotPose.getZ() - normalAngle3) < 0.01d) {
            Pdlog.m3273d(this.TAG, "has rotate degree=" + normalAngle3 + "  finish, current orientation " + this.robotPose.getZ());
            MirCoreInterface mirCoreInterface = this.coreService.getInterface();
            if (mirCoreInterface != null && (navigator3 = mirCoreInterface.getNavigator()) != null) {
                navigator3.resetNavigationFlag();
            }
            brakeUntilStop();
            return;
        }
        this.rotateVector.setX(Math.cos(normalAngle3));
        this.rotateVector.setY(Math.sin(normalAngle3));
        this.rotateVector.setZ(0.0d);
        Pdlog.m3273d(this.TAG, "rotate degree=" + normalAngle3);
        MirCoreInterface mirCoreInterface2 = this.coreService.getInterface();
        if (mirCoreInterface2 != null && (navigator2 = mirCoreInterface2.getNavigator()) != null) {
            navigator2.resetNavigationFlag();
        }
        MirCoreInterface mirCoreInterface3 = this.coreService.getInterface();
        RotateResult rotate = (mirCoreInterface3 == null || (navigator = mirCoreInterface3.getNavigator()) == null) ? null : navigator.rotate(this.rotateVector, 1.1d);
        double angularspeed = rotate != null ? rotate.getAngularspeed() : 0.0d;
        double d = Double.compare(angularspeed, 1.0E-9d) < 0 ? angularspeed - extraAngleSpeed : angularspeed + extraAngleSpeed;
        Pdlog.m3273d(this.TAG, "rotate angleSpeed = " + d + " , extraAngleSpeed = " + extraAngleSpeed);
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.controlWheel(rotate != null ? rotate.getLinespeed() : 0.0d, d, true);
        }
    }

    public final void fixDegree() {
        Job launch$default;
        Job job = this.fixLinarJob;
        if (job == null || !job.isActive()) {
            Job job2 = this.fixAngarJob;
            if (job2 == null || !job2.isActive()) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SolicitService$fixDegree$1(this, null), 3, null);
                this.fixLinarJob = launch$default;
            }
        }
    }

    public final void addSolicitListener(String name, ISolicitListener l) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(this.TAG, "addSolicitListener name=" + name);
        this.solicitListeners.add(name, l);
    }

    public final void removeSolicitListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(this.TAG, "removeSolicitListener name=" + name);
        this.solicitListeners.remove(name);
    }

    public final void updateRobotPos(double x, double y, double z) {
        this.robotPose.setX(x);
        this.robotPose.setY(y);
        this.robotPose.setZ(z);
    }

    public final int startSolicit() {
        MirCoreInterface mirCoreInterface;
        HardwareInterface hardwareInterface;
        MachineInfo machineInfo;
        this.solicitDegree = this.robotPose.getZ();
        RobotHardware robotHardware = this.robotHardware;
        Boolean bool = null;
        MachineInfo.MonocularType monocularDeviceType = (robotHardware == null || (hardwareInterface = robotHardware.getInterface()) == null || (machineInfo = hardwareInterface.getMachineInfo()) == null) ? null : machineInfo.getMonocularDeviceType();
        if (monocularDeviceType != null) {
            if (WhenMappings.$EnumSwitchMapping$0[monocularDeviceType.ordinal()] == 1) {
                Pdlog.m3273d(this.TAG, "getMonocularDeviceType is NoDevice can not solicit");
                return -1;
            }
            AIDLConnection<MirCoreInterface> aIDLConnection = this.coreService;
            if (aIDLConnection != null && (mirCoreInterface = aIDLConnection.getInterface()) != null) {
                bool = Boolean.valueOf(mirCoreInterface.checkNPU());
            }
            if (bool == null) {
                Intrinsics.throwNpe();
            }
            this.NPUFlag = bool.booleanValue();
            Pdlog.m3273d(this.TAG, "coreService?.getInterface()?.checkNPU() is " + this.NPUFlag + ' ');
            Pdlog.m3273d(this.TAG, "startSolicit  solicitDegree=" + this.solicitDegree + ",robotPosee=" + this.robotPose + " speed is 1.1\"");
            doSolicit();
            return 0;
        }
        Pdlog.m3273d(this.TAG, "getMonocularDeviceType is null has no face camera can not solicit");
        return -1;
    }

    private final void doSolicit() {
        this.personDetectTime = 0;
        navigation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void controlFaceDetect(boolean p0) {
        Pdlog.m3273d(this.TAG, "controlFaceDetect:" + p0);
        if (p0) {
            MirCoreInterface mirCoreInterface = this.coreService.getInterface();
            if (mirCoreInterface != null) {
                mirCoreInterface.addFaceDetectListener("solicitFaceControl", this.faceDetectListener);
            }
            MirCoreInterface mirCoreInterface2 = this.coreService.getInterface();
            if (mirCoreInterface2 != null) {
                mirCoreInterface2.enableFaceDetect(p0);
                return;
            }
            return;
        }
        MirCoreInterface mirCoreInterface3 = this.coreService.getInterface();
        if (mirCoreInterface3 != null) {
            mirCoreInterface3.enableFaceDetect(p0);
        }
        MirCoreInterface mirCoreInterface4 = this.coreService.getInterface();
        if (mirCoreInterface4 != null) {
            mirCoreInterface4.removeFaceDetectListener("solicitFaceControl");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void faceDetectSolicit(int flag, double angle) {
        if (flag == -1) {
            Pdlog.m3273d(this.TAG, "camera open fail reSolicit");
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SolicitService$faceDetectSolicit$2(this, null), 3, null);
            return;
        }
        if (flag == 0) {
            this.faceDetectCount++;
            if (this.faceDetectCount >= 10) {
                this.faceDetectCount = 0;
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SolicitService$faceDetectSolicit$3(this, null), 3, null);
                return;
            }
            return;
        }
        if (flag != 1) {
            return;
        }
        Pdlog.m3273d(this.TAG, "faceDetectSolicit solicitState=" + ((int) this.solicitState));
        if (this.solicitState != 2) {
            return;
        }
        this.faceDetectCount = 0;
        this.solicitState = (byte) 3;
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SolicitService$faceDetectSolicit$1(this, angle, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closeFaceDetectNoNPU() {
        MirCoreInterface mirCoreInterface;
        if (this.NPUFlag || (mirCoreInterface = this.coreService.getInterface()) == null) {
            return;
        }
        mirCoreInterface.enableFaceDetect(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object checkAndClearWheelError(Continuation<? super Boolean> continuation) {
        SolicitService$checkAndClearWheelError$1 solicitService$checkAndClearWheelError$1;
        int i;
        SolicitService solicitService;
        Boolean bool;
        HardwareInterface hardwareInterface;
        if (continuation instanceof SolicitService$checkAndClearWheelError$1) {
            solicitService$checkAndClearWheelError$1 = (SolicitService$checkAndClearWheelError$1) continuation;
            if ((solicitService$checkAndClearWheelError$1.label & Integer.MIN_VALUE) != 0) {
                solicitService$checkAndClearWheelError$1.label -= Integer.MIN_VALUE;
                Object obj = solicitService$checkAndClearWheelError$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = solicitService$checkAndClearWheelError$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.robotHardware.getWheelMulfunctionState()) {
                        SolicitService$checkAndClearWheelError$result$1 solicitService$checkAndClearWheelError$result$1 = new SolicitService$checkAndClearWheelError$result$1(this, null);
                        solicitService$checkAndClearWheelError$1.L$0 = this;
                        solicitService$checkAndClearWheelError$1.label = 1;
                        obj = TimeoutKt.withTimeoutOrNull(500L, solicitService$checkAndClearWheelError$result$1, solicitService$checkAndClearWheelError$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        solicitService = this;
                    } else {
                        return Boxing.boxBoolean(true);
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    solicitService = (SolicitService) solicitService$checkAndClearWheelError$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                bool = (Boolean) obj;
                hardwareInterface = solicitService.robotHardware.getInterface();
                if (hardwareInterface != null) {
                    hardwareInterface.controlWheel(0.0d, 0.0d, false);
                }
                boolean z = false;
                if (!(!Intrinsics.areEqual(bool, Boxing.boxBoolean(true)))) {
                    Pdlog.m3277w(solicitService.TAG, "clear wheel error fail");
                    solicitService._onStateChange.invoke(RobotState.Error, solicitService.robotHardware.getWheelInWarning() ? solicitService.robotHardware.getLastWarnMulfunction() : solicitService.robotHardware.getLastErrorMulfunction());
                } else {
                    solicitService.robotHardware.setWheelInError(false);
                    solicitService.robotHardware.setWheelInWarning(false);
                    Pdlog.m3273d(solicitService.TAG, "wheel error being cleared");
                    z = true;
                }
                return Boxing.boxBoolean(z);
            }
        }
        solicitService$checkAndClearWheelError$1 = new SolicitService$checkAndClearWheelError$1(this, continuation);
        Object obj2 = solicitService$checkAndClearWheelError$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = solicitService$checkAndClearWheelError$1.label;
        if (i != 0) {
        }
        bool = (Boolean) obj2;
        hardwareInterface = solicitService.robotHardware.getInterface();
        if (hardwareInterface != null) {
        }
        boolean z2 = false;
        if (!(!Intrinsics.areEqual(bool, Boxing.boxBoolean(true)))) {
        }
        return Boxing.boxBoolean(z2);
    }

    public final void stopSolicit() {
        BuildersKt__BuildersKt.runBlocking$default(null, new SolicitService$stopSolicit$1(this, null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void navigation() {
        Job launch$default;
        Pdlog.m3273d(this.TAG, "navigation enter");
        BuildersKt__BuildersKt.runBlocking$default(null, new SolicitService$navigation$1(this, null), 1, null);
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getMoveActionWorker(), null, new SolicitService$navigation$2(this, null), 2, null);
        this.moveJob = launch$default;
    }

    public final void triggerError(String error) {
        Intrinsics.checkParameterIsNotNull(error, "error");
        Job job = this.errorJob;
        if (job != null && job.isActive()) {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SolicitService$triggerError$1(this, error, null), 3, null);
            return;
        }
        Job job2 = this.moveJob;
        if (job2 == null || !job2.isActive()) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SolicitService$triggerError$2(this, error, null), 3, null);
    }

    public final void suspendWarningWelfunction(Pair<Boolean, String> warning) {
        Job job;
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(warning, "warning");
        if (warning.getFirst().booleanValue()) {
            Job job2 = this.errorJob;
            if ((job2 == null || !job2.isActive()) && (job = this.moveJob) != null && job.isActive()) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SolicitService$suspendWarningWelfunction$1(this, warning, null), 3, null);
                this.errorJob = launch$default;
                return;
            }
            return;
        }
        Job job3 = this.errorJob;
        if (job3 == null || !job3.isActive()) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SolicitService$suspendWarningWelfunction$2(this, null), 3, null);
    }

    /* compiled from: SolicitService.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u001f\u0010\u0002\u001a\u00020\u0003X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bj\u0002\b\nj\u0002\b\u000bj\u0002\b\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/mirsdk/SolicitService$WatchLevel;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "setId-7apg3OU", "(B)V", "B", "Normal", "Pause", "Over", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum WatchLevel {
        Normal((byte) 0),
        Pause((byte) 1),
        Over((byte) 2);

        private byte id;

        WatchLevel(byte b) {
            this.id = b;
        }

        public final byte getId() {
            return this.id;
        }

        /* renamed from: setId-7apg3OU, reason: not valid java name */
        public final void m4409setId7apg3OU(byte b) {
            this.id = b;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<WatchLevel, String> watchDogCheck() {
        Iterator<String> it;
        String str;
        DeviceType deviceType;
        Iterator<String> it2;
        String str2;
        DeviceType deviceType2;
        Pair<List<String>, List<String>> check = this.watchDog.check();
        int i = 1;
        String str3 = "[";
        if (!check.getSecond().isEmpty()) {
            brakeUntilStop();
            this.lastSensorError.clear();
            Pdlog.m3274e(this.TAG, "navigation fail, Watch dog timeout:" + CollectionsKt.joinToString$default(check.getSecond(), null, null, null, 0, null, null, 63, null));
            Iterator<String> it3 = check.getSecond().iterator();
            while (it3.hasNext()) {
                String next = it3.next();
                this.lastSensorError.add(next);
                HardwareInterface hardwareInterface = this.robotHardware.getInterface();
                if (hardwareInterface != null) {
                    DeviceType[] deviceTypeArr = new DeviceType[i];
                    int hashCode = next.hashCode();
                    it2 = it3;
                    if (hashCode == -2018805884) {
                        if (next.equals("Lindar")) {
                            deviceType2 = DeviceType.Lidar;
                            deviceTypeArr[0] = deviceType2;
                            str2 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                        }
                        deviceType2 = DeviceType.CameraIRDLED;
                        deviceTypeArr[0] = deviceType2;
                        str2 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                    } else if (hashCode != 2513207) {
                        if (hashCode == 2011082565 && next.equals("Camera")) {
                            deviceType2 = DeviceType.Camera;
                            deviceTypeArr[0] = deviceType2;
                            str2 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                        }
                        deviceType2 = DeviceType.CameraIRDLED;
                        deviceTypeArr[0] = deviceType2;
                        str2 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                    } else {
                        if (next.equals("RGBD")) {
                            deviceType2 = DeviceType.RGBD;
                            deviceTypeArr[0] = deviceType2;
                            str2 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                        }
                        deviceType2 = DeviceType.CameraIRDLED;
                        deviceTypeArr[0] = deviceType2;
                        str2 = hardwareInterface.getLastError(CollectionsKt.mutableListOf(deviceTypeArr));
                    }
                } else {
                    it2 = it3;
                    str2 = null;
                }
                str3 = str3 + "{\"error_type\":\"Lost" + next + "\", \"level\":\"Error\",\"detail\":\"" + str2 + "\"},";
                it3 = it2;
                i = 1;
            }
            String str4 = str3 + "]";
            if (check.getSecond().contains("CAN")) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SolicitService$watchDogCheck$1(this, null), 3, null);
            }
            return new Pair<>(WatchLevel.Over, str4);
        }
        if (!check.getFirst().isEmpty()) {
            if (check.getFirst().contains("Encoder") || check.getFirst().contains("IMU") || check.getFirst().contains("CAN")) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SolicitService$watchDogCheck$2(this, null), 3, null);
            }
            Iterator<String> it4 = check.getFirst().iterator();
            String str5 = "[";
            boolean z = false;
            while (it4.hasNext()) {
                String next2 = it4.next();
                HardwareInterface hardwareInterface2 = this.robotHardware.getInterface();
                if (hardwareInterface2 != null) {
                    DeviceType[] deviceTypeArr2 = new DeviceType[1];
                    int hashCode2 = next2.hashCode();
                    it = it4;
                    if (hashCode2 == -2018805884) {
                        if (next2.equals("Lindar")) {
                            deviceType = DeviceType.Lidar;
                            deviceTypeArr2[0] = deviceType;
                            str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                        }
                        deviceType = DeviceType.CameraIRDLED;
                        deviceTypeArr2[0] = deviceType;
                        str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                    } else if (hashCode2 != 2513207) {
                        if (hashCode2 == 2011082565 && next2.equals("Camera")) {
                            deviceType = DeviceType.Camera;
                            deviceTypeArr2[0] = deviceType;
                            str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                        }
                        deviceType = DeviceType.CameraIRDLED;
                        deviceTypeArr2[0] = deviceType;
                        str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                    } else {
                        if (next2.equals("RGBD")) {
                            deviceType = DeviceType.RGBD;
                            deviceTypeArr2[0] = deviceType;
                            str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                        }
                        deviceType = DeviceType.CameraIRDLED;
                        deviceTypeArr2[0] = deviceType;
                        str = hardwareInterface2.getLastError(CollectionsKt.mutableListOf(deviceTypeArr2));
                    }
                } else {
                    it = it4;
                    str = null;
                }
                str5 = str5 + "{\"error_type\":\"Lost" + next2 + "\", \"level\":\"Warning\",\"detail\":\"" + str + "\"},";
                if (this.lastSensorError.contains(next2)) {
                    str3 = str3 + "{\"error_type\":\"Lost" + next2 + "\", \"level\":\"Error\",\"detail\":\"" + str + "\"},";
                    z = true;
                }
                it4 = it;
            }
            String str6 = str3 + "]";
            String str7 = str5 + "]";
            if (z) {
                return new Pair<>(WatchLevel.Over, str6);
            }
            return new Pair<>(WatchLevel.Pause, str7);
        }
        this.lastSensorError.clear();
        return new Pair<>(WatchLevel.Normal, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void retriveMCU() {
        LidarInterface lidarInterface;
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.sendCAN(new byte[]{22, 2, 4, 1, 0, 0, (byte) 233});
        }
        HardwareInterface hardwareInterface2 = this.robotHardware.getInterface();
        if (hardwareInterface2 != null) {
            hardwareInterface2.sendCAN(new byte[]{22, 4, 4, 1, 0, 0, (byte) 233});
        }
        HardwareInterface hardwareInterface3 = this.robotHardware.getInterface();
        if (hardwareInterface3 != null) {
            hardwareInterface3.sendCAN(new byte[]{22, 6, 4, 1, 0, 0, (byte) 233});
        }
        HardwareInterface hardwareInterface4 = this.robotHardware.getInterface();
        if (hardwareInterface4 != null) {
            hardwareInterface4.sendCAN(new byte[]{22, (byte) 255, (byte) 225, 4, 0, 0, (byte) 233});
        }
        HardwareInterface hardwareInterface5 = this.robotHardware.getInterface();
        if (hardwareInterface5 == null || (lidarInterface = hardwareInterface5.getLidarInterface()) == null) {
            return;
        }
        lidarInterface.open();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0037 A[Catch: all -> 0x0055, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x000f, B:8:0x001a, B:11:0x0027, B:12:0x004b, B:16:0x002f, B:17:0x0036, B:18:0x0037, B:20:0x003e, B:25:0x0015), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized /* synthetic */ Object stopAndWaitBrake(Continuation<? super Unit> continuation) {
        SolicitService$stopAndWaitBrake$1 solicitService$stopAndWaitBrake$1;
        int i;
        SolicitService solicitService;
        if (continuation instanceof SolicitService$stopAndWaitBrake$1) {
            solicitService$stopAndWaitBrake$1 = (SolicitService$stopAndWaitBrake$1) continuation;
            if ((solicitService$stopAndWaitBrake$1.label & Integer.MIN_VALUE) != 0) {
                solicitService$stopAndWaitBrake$1.label -= Integer.MIN_VALUE;
                Object obj = solicitService$stopAndWaitBrake$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = solicitService$stopAndWaitBrake$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Job job = this.moveJob;
                    if (job != null) {
                        solicitService$stopAndWaitBrake$1.L$0 = this;
                        solicitService$stopAndWaitBrake$1.label = 1;
                        if (JobKt.cancelAndJoin(job, solicitService$stopAndWaitBrake$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    solicitService = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    solicitService = (SolicitService) solicitService$stopAndWaitBrake$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                solicitService.stop();
                solicitService.brakeUntilStop();
                return Unit.INSTANCE;
            }
        }
        solicitService$stopAndWaitBrake$1 = new SolicitService$stopAndWaitBrake$1(this, continuation);
        Object obj2 = solicitService$stopAndWaitBrake$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = solicitService$stopAndWaitBrake$1.label;
        if (i != 0) {
        }
        solicitService.stop();
        solicitService.brakeUntilStop();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stop() {
        MirCoreInterface mirCoreInterface = this.coreService.getInterface();
        if (mirCoreInterface != null) {
            mirCoreInterface.enablePersonDetect(false);
        }
        MirCoreInterface mirCoreInterface2 = this.coreService.getInterface();
        if (mirCoreInterface2 != null) {
            mirCoreInterface2.removePersonListener("personDetect");
        }
        this.solicitState = (byte) 0;
        this.faceDetectCount = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void brakeUntilStop() {
        Job launch$default;
        Job job = this.brakeJob;
        if (job == null || job == null || !job.isActive()) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SolicitService$brakeUntilStop$1(this, null), 3, null);
            this.brakeJob = launch$default;
        } else {
            Pdlog.m3277w(this.TAG, "quit brake because previous brake still running");
        }
    }
}
