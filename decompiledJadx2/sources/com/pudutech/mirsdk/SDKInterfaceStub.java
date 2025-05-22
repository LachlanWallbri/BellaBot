package com.pudutech.mirsdk;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.os.EnvironmentCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.installerserver.InstallerServer;
import com.pudutech.mirsdk.SDKInterfaceStub;
import com.pudutech.mirsdk.aidl.BluetoothChargeInterface;
import com.pudutech.mirsdk.aidl.DeviceInterface;
import com.pudutech.mirsdk.aidl.ILoadCoreListener;
import com.pudutech.mirsdk.aidl.ISDKListener;
import com.pudutech.mirsdk.aidl.LocateCameraCalibListener;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.serialize.AIDLGitHash;
import com.pudutech.mirsdk.aidl.serialize.AccessControlServer;
import com.pudutech.mirsdk.aidl.serialize.ChargingPileInfo;
import com.pudutech.mirsdk.aidl.serialize.CoreStepType;
import com.pudutech.mirsdk.aidl.serialize.ElevatorConnectionType;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.mirsdk.aidl.serialize.MapListConfig;
import com.pudutech.mirsdk.aidl.serialize.MapPackageConfig;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdk.aidl.serialize.SwitchMapResult;
import com.pudutech.mirsdk.base.Monitorable;
import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
import com.pudutech.mirsdk.charge.ChargeFinder;
import com.pudutech.mirsdk.charge.ChargeUpdateTaskManager;
import com.pudutech.mirsdk.cloud.GlobalReportClient;
import com.pudutech.mirsdk.cloud.ReportClient;
import com.pudutech.mirsdk.config.InstallationModeConfig;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.mirsdk.config.SDKConfig;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareConfig;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.hardware.serialize.HardwareBoard;
import com.pudutech.mirsdk.hardware.serialize.HardwareVersion;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.PolarCoordinates;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import com.pudutech.mirsdk.map.Atlas;
import com.pudutech.mirsdk.map.FileUtils;
import com.pudutech.mirsdk.map.MapNameWithFloor;
import com.pudutech.mirsdk.map.MapifyTools;
import com.pudutech.mirsdk.mapify.util.CamerConfigHelper;
import com.pudutech.mirsdk.mircore.FaceDetectListener;
import com.pudutech.mirsdk.mircore.InitServiceListener;
import com.pudutech.mirsdk.mircore.LocalizationInterface;
import com.pudutech.mirsdk.mircore.MarkerCameraCalibResultListener;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.ReloadMapResultListener;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitStep;
import com.pudutech.mirsdk.mircore.coreparcel.DockerDetectResult;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusLevel;
import com.pudutech.mirsdk.mirhardware.BatteryState;
import com.pudutech.mirsdk.mirhardware.GeomagneticData;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import com.pudutech.mirsdk.mirhardware.RobotStatus;
import com.pudutech.mirsdk.sdksafe.SDKSafe;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000f\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020&H\u0016J\u0018\u0010'\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00042\u0006\u0010%\u001a\u00020&H\u0016J\u0018\u0010(\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u001eH\u0016J\u0010\u0010*\u001a\u00020\"2\u0006\u0010+\u001a\u00020\u0004H\u0016J\u0018\u0010,\u001a\u00020\"2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0016J\u0010\u00101\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020\u000bH\u0002J\b\u00102\u001a\u00020\u000bH\u0002J\u0012\u00103\u001a\u00020\u000b2\b\u00104\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u00105\u001a\u00020\"H\u0016J\u0010\u00106\u001a\u00020\"2\u0006\u00107\u001a\u00020.H\u0016J\u0010\u00108\u001a\u00020\"2\u0006\u0010-\u001a\u00020\u000bH\u0016J\u0018\u00109\u001a\u00020.2\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\u000bH\u0016J\n\u0010=\u001a\u0004\u0018\u00010\u0004H\u0016J\n\u0010>\u001a\u0004\u0018\u00010&H\u0016J\u0006\u0010?\u001a\u00020\"J\b\u0010@\u001a\u00020\"H\u0002J\u0010\u0010A\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020\u000bH\u0016J\u0010\u0010B\u001a\u00020\"2\u0006\u0010-\u001a\u00020\u000bH\u0016J\n\u0010C\u001a\u0004\u0018\u00010DH\u0016J\b\u0010E\u001a\u00020\"H\u0016J\b\u0010F\u001a\u00020GH\u0016J\b\u0010H\u001a\u00020IH\u0016J\b\u0010J\u001a\u00020\u000bH\u0016J\n\u0010K\u001a\u0004\u0018\u00010LH\u0016J\b\u0010M\u001a\u00020\u0004H\u0016J\b\u0010N\u001a\u00020\u0004H\u0016J\b\u0010O\u001a\u00020\u000bH\u0016J\b\u0010P\u001a\u00020QH\u0016J\b\u0010R\u001a\u00020\u0013H\u0016J\n\u0010S\u001a\u0004\u0018\u00010TH\u0016J\u000e\u0010U\u001a\b\u0012\u0004\u0012\u00020W0VH\u0016J\b\u0010X\u001a\u00020YH\u0016J\b\u0010Z\u001a\u00020[H\u0016J\u0010\u0010\\\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010]H\u0016J\b\u0010^\u001a\u00020\u000bH\u0016J\u000e\u0010_\u001a\u00020\"2\u0006\u0010`\u001a\u00020\u0006J\u0010\u0010_\u001a\u00020\"2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001b\u0010a\u001a\u00020\u000b2\b\u00104\u001a\u0004\u0018\u00010\u0004H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010bJ\"\u0010c\u001a\u00020\"2\u0006\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020g2\b\b\u0002\u0010h\u001a\u00020\u0004H\u0002J\"\u0010c\u001a\u00020\"2\u0006\u0010d\u001a\u00020e2\u0006\u0010i\u001a\u00020\u000b2\b\b\u0002\u0010h\u001a\u00020\u0004H\u0002J\b\u0010j\u001a\u00020\u000bH\u0016J\b\u0010k\u001a\u00020\u000bH\u0016J\b\u0010l\u001a\u00020\u000bH\u0016J\b\u0010m\u001a\u00020\"H\u0002J\u0019\u0010n\u001a\u00020\"2\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010oJ\u0011\u0010p\u001a\u00020\u000bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010qJ\u0010\u0010r\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020\u000bH\u0016J\b\u0010s\u001a\u00020\"H\u0016J\u0012\u0010t\u001a\u00020\"2\b\u0010u\u001a\u0004\u0018\u00010vH\u0016J\u0010\u0010w\u001a\u00020\"2\u0006\u00104\u001a\u00020\u0004H\u0016J\u0012\u0010x\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010y\u001a\u00020\"2\b\u0010z\u001a\u0004\u0018\u00010\u0004H\u0002J\u0018\u0010{\u001a\u00020\"2\u0006\u0010|\u001a\u00020\u000b2\u0006\u0010}\u001a\u00020\u000bH\u0016J\u0010\u0010~\u001a\u00020\"2\u0006\u0010-\u001a\u00020DH\u0016J\u0010\u0010\u007f\u001a\u00020\"2\u0006\u0010-\u001a\u00020LH\u0016J\u0012\u0010\u0080\u0001\u001a\u00020\"2\u0007\u0010\u0081\u0001\u001a\u00020\u000bH\u0016J\t\u0010\u0082\u0001\u001a\u00020\"H\u0016J\u0011\u0010\u0083\u0001\u001a\u00020\"2\u0006\u00104\u001a\u00020\u0004H\u0016J\u0013\u0010\u0084\u0001\u001a\u00020\"2\b\u00104\u001a\u0004\u0018\u00010\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0085\u0001"}, m3961d2 = {"Lcom/pudutech/mirsdk/SDKInterfaceStub;", "Lcom/pudutech/mirsdk/aidl/SDKInterface$Stub;", "()V", "TAG", "", "context", "Landroid/content/Context;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "enabledFaceDetect", "", "installationModeConfig", "Lcom/pudutech/mirsdk/config/InstallationModeConfig;", "isIniting", "loadCoreListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/aidl/ILoadCoreListener;", "locateCase", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "moveActionInterface", "Lcom/pudutech/mirsdk/MoveAction;", "reloadingMap", "robot", "Lcom/pudutech/mirsdk/DeviceInterfaceImpl;", "robotHardware", "Lcom/pudutech/mirsdk/mirhardware/RobotHardware;", "robotStatus", "Lcom/pudutech/mirsdk/mirhardware/RobotStatus;", "sdkListeners", "Lcom/pudutech/mirsdk/aidl/ISDKListener;", "watchDog", "Lcom/pudutech/mirsdk/WatchDog;", "addBluetoothChargePile", "", "name", "mac", "docker", "Lcom/pudutech/mirsdk/mircore/coreparcel/DockerDetectResult;", "addChargePile", "addLisener", "callback", "addRelocationPoint", "id", "calibrationMarkerCamera", "p0", "", "p1", "Lcom/pudutech/mirsdk/aidl/LocateCameraCalibListener;", "changeDropDet", "checkDropDetStatus", "checkLegalMap", "pdmap", "closeAuthCheck", "controlBatteryLevel", "level", "controlFaceDetect", "controlWheelErrorEvent", NotificationCompat.CATEGORY_ERROR, "Lcom/pudutech/mirsdk/hardware/serialize/WheelError;", "response", "defaultDiningOutlet", "detectChargePile", MqttServiceConstants.DISCONNECT_ACTION, "disconnectSubService", "enableDropDet", "enableReflector", "getAccessControlServer", "Lcom/pudutech/mirsdk/aidl/serialize/AccessControlServer;", "getBatteryLevel", "getBluetoothChargeInterface", "Lcom/pudutech/mirsdk/aidl/BluetoothChargeInterface;", "getDeviceInterface", "Lcom/pudutech/mirsdk/aidl/DeviceInterface;", "getDropDetStatus", "getElevatorConnection", "Lcom/pudutech/mirsdk/aidl/serialize/ElevatorConnectionType;", "getGitHash", "getHardwareVersion", "getInstallMode", "getLocalizationStatus", "Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatus;", "getLocateCase", "getMachineInfo", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo;", "getMapInfoList", "", "Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "getMoveActionInterface", "Lcom/pudutech/mirsdk/aidl/MoveActionInterface;", "getPdmapNameList", "Lcom/pudutech/mirsdk/aidl/serialize/MapPackageConfig;", "getRelocationPoints", "", "getSlipControlStatus", "init", "_context", "initCore", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initStepNotify", "step", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "stepState", "Lcom/pudutech/mirsdk/aidl/serialize/StepState;", "description", "success", "isLocalizationFinishInitialization", "isLocated", "isRelocalizationSuccess", "magneticConfigJson", "openCore", "(Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openHardware", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openSlipControl", "reloadLocalization", "reloadLocalizationByChargingPile", "pile", "Lcom/pudutech/mirsdk/aidl/serialize/ChargingPileInfo;", "reloadMap", "removeListener", "reportCloud", NotificationCompat.CATEGORY_MESSAGE, "securitySwitch", "oldState", "expectState", "setAccessControlServer", "setElevatorConnection", "setInstallMode", "open", "suspendCharingUsingPile", "switchDefaultPdmap", "switchUsingPdmap", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SDKInterfaceStub extends SDKInterface.Stub {
    public static final SDKInterfaceStub INSTANCE;
    private static final String TAG;
    private static Context context;
    private static final AIDLConnection<MirCoreInterface> coreService;
    private static boolean enabledFaceDetect;
    private static final InstallationModeConfig installationModeConfig;
    private static boolean isIniting;
    private static ThreadSafeListener<ILoadCoreListener> loadCoreListener;
    private static LocateCase locateCase;
    private static MoveAction moveActionInterface;
    private static boolean reloadingMap;
    private static final DeviceInterfaceImpl robot;
    private static final RobotHardware robotHardware;
    private static final RobotStatus robotStatus;
    private static ThreadSafeListener<ISDKListener> sdkListeners;
    private static final WatchDog watchDog;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[LocateCase.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;

        static {
            $EnumSwitchMapping$0[LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[LocalizationStatusLevel.values().length];
            $EnumSwitchMapping$1[LocalizationStatusLevel.Error.ordinal()] = 1;
            $EnumSwitchMapping$2 = new int[CoreInitStep.values().length];
            $EnumSwitchMapping$2[CoreInitStep.Finish.ordinal()] = 1;
            $EnumSwitchMapping$3 = new int[CoreInitState.values().length];
            $EnumSwitchMapping$3[CoreInitState.Success.ordinal()] = 1;
            $EnumSwitchMapping$3[CoreInitState.Running.ordinal()] = 2;
            $EnumSwitchMapping$3[CoreInitState.Fail.ordinal()] = 3;
            $EnumSwitchMapping$4 = new int[LocalizationStatusLevel.values().length];
            $EnumSwitchMapping$4[LocalizationStatusLevel.Normal.ordinal()] = 1;
            $EnumSwitchMapping$5 = new int[MachineModel.values().length];
            $EnumSwitchMapping$5[MachineModel.Ninetales.ordinal()] = 1;
            $EnumSwitchMapping$5[MachineModel.Firefox.ordinal()] = 2;
        }
    }

    static {
        SDKInterfaceStub sDKInterfaceStub = new SDKInterfaceStub();
        INSTANCE = sDKInterfaceStub;
        TAG = TAG;
        coreService = new AIDLConnection<>("com.pudutech.mirsdk.mircore.MirCoreService", SDKInterfaceStub$coreService$1.INSTANCE, null, 4, null);
        sdkListeners = new ThreadSafeListener<>();
        loadCoreListener = new ThreadSafeListener<>();
        robotStatus = new RobotStatus();
        watchDog = new WatchDog();
        robotHardware = new RobotHardware(robotStatus, new SDKInterfaceStub$robotHardware$1(sDKInterfaceStub), watchDog, new SDKInterfaceStub$robotHardware$2(sDKInterfaceStub));
        robot = new DeviceInterfaceImpl(robotStatus, robotHardware, watchDog);
        installationModeConfig = new InstallationModeConfig();
        locateCase = LocateCase.Marker;
    }

    private SDKInterfaceStub() {
    }

    public static final /* synthetic */ MoveAction access$getMoveActionInterface$p(SDKInterfaceStub sDKInterfaceStub) {
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        return moveAction;
    }

    public final void init(Context _context) {
        Intrinsics.checkParameterIsNotNull(_context, "_context");
        Pdlog.m3273d(TAG, "Last bootTime is " + SystemClock.elapsedRealtime());
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SDKInterfaceStub$init$1(null), 3, null);
        context = _context;
        SDKConfig sDKConfig = SDKConfig.INSTANCE;
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        sDKConfig.init(context2);
        ReportClient.INSTANCE.init(_context, new File("/sdcard/TestServer").exists());
        GlobalReportClient.INSTANCE.init(_context);
        BluetoothBleHelper.INSTANCE.initHelper(_context);
        ChargeUpdateTaskManager.INSTANCE.init();
        ChargeFinder.INSTANCE.setSdkInterface(this);
        Pdlog.m3275i(TAG, "MirSDK initing, version:2.4.2-global");
        if (moveActionInterface == null) {
            moveActionInterface = new MoveAction(robotHardware, coreService, robotStatus, watchDog, new Function0<Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$3
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    String str;
                    ThreadSafeListener threadSafeListener;
                    SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                    str = SDKInterfaceStub.TAG;
                    Pdlog.m3273d(str, "onStuckReplan");
                    SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
                    threadSafeListener = SDKInterfaceStub.sdkListeners;
                    threadSafeListener.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$3.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str2) {
                            invoke2(iSDKListener, str2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ISDKListener l, String str2) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                            l.onStuckReplan();
                        }
                    });
                }
            }, new Function2<RobotState, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$4
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(RobotState robotState, String str) {
                    invoke2(robotState, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final RobotState state, final String desc) {
                    String str;
                    ThreadSafeListener threadSafeListener;
                    Intrinsics.checkParameterIsNotNull(state, "state");
                    Intrinsics.checkParameterIsNotNull(desc, "desc");
                    SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                    str = SDKInterfaceStub.TAG;
                    Pdlog.m3273d(str, "onStateChange:" + state + ' ' + desc);
                    SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
                    threadSafeListener = SDKInterfaceStub.sdkListeners;
                    threadSafeListener.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$4.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str2) {
                            invoke2(iSDKListener, str2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ISDKListener l, String str2) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                            l.onStateChange(RobotState.this, desc);
                        }
                    });
                }
            });
            MoveAction moveAction = moveActionInterface;
            if (moveAction == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
            }
            moveAction.getArrivedOnGoalCruise().add("business", new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$5
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    ThreadSafeListener threadSafeListener;
                    if (z) {
                        SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                        threadSafeListener = SDKInterfaceStub.sdkListeners;
                        threadSafeListener.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$5.1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str) {
                                invoke2(iSDKListener, str);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(ISDKListener l, String str) {
                                Intrinsics.checkParameterIsNotNull(l, "l");
                                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                                l.onGoalCruisePath();
                            }
                        });
                    }
                }
            });
        }
        InstallationModeConfig installationModeConfig2 = installationModeConfig;
        Context context3 = context;
        if (context3 == null) {
            Intrinsics.throwNpe();
        }
        installationModeConfig2.init(context3);
        robotStatus.getPose().onChange(new Function1<Vector3d, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Vector3d vector3d) {
                invoke2(vector3d);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final Vector3d it) {
                ThreadSafeListener threadSafeListener;
                InstallationModeConfig installationModeConfig3;
                Intrinsics.checkParameterIsNotNull(it, "it");
                SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                threadSafeListener = SDKInterfaceStub.sdkListeners;
                threadSafeListener.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$6.1
                    {
                        super(2);
                    }

                    /* compiled from: SDKService.kt */
                    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "l", "Lcom/pudutech/mirsdk/aidl/ISDKListener;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
                    /* renamed from: com.pudutech.mirsdk.SDKInterfaceStub$init$6$1$1, reason: invalid class name */
                    /* loaded from: classes4.dex */
                    static final class AnonymousClass1 extends Lambda implements Function2<ISDKListener, String, Unit> {
                        AnonymousClass1() {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str) {
                            invoke2(iSDKListener, str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ISDKListener l, String str) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                            l.onBattery(C48011.this.$it.getPercent(), C48011.this.$it.getChargeState());
                            SDKInterfaceStub.access$getBatteryCapacity$p(SDKInterfaceStub.INSTANCE).setPercent(C48011.this.$it.getPercent());
                            SDKInterfaceStub.access$getBatteryCapacity$p(SDKInterfaceStub.INSTANCE).setChargeState(C48011.this.$it.getChargeState());
                        }
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str) {
                        invoke2(iSDKListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ISDKListener l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onPose(Vector3d.this.getX(), Vector3d.this.getY(), Vector3d.this.getZ());
                    }
                });
                SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
                installationModeConfig3 = SDKInterfaceStub.installationModeConfig;
                InstallerServer installerService = installationModeConfig3.getInstallerService();
                if (installerService != null) {
                    installerService.sendRobotPose(new double[]{it.getX(), it.getY(), 0.0d}, new double[]{Math.cos(it.getZ()), Math.sin(it.getZ()), 0.0d}, new double[]{0.0d, 0.0d, 0.0d});
                }
            }
        });
        robotStatus.getBattery().onChange(new Function1<BatteryState, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$7
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BatteryState batteryState) {
                invoke2(batteryState);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: Classes with same name are omitted:
              classes4.dex
             */
            /* compiled from: SDKService.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.SDKInterfaceStub$init$7$1", m3970f = "SDKService.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.SDKInterfaceStub$init$7$1 */
            /* loaded from: classes5.dex */
            public static final class C48021 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ BatteryState $it;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5571p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C48021(BatteryState batteryState, Continuation continuation) {
                    super(2, continuation);
                    this.$it = batteryState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C48021 c48021 = new C48021(this.$it, completion);
                    c48021.f5571p$ = (CoroutineScope) obj;
                    return c48021;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C48021) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    ThreadSafeListener threadSafeListener;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5571p$;
                    SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                    threadSafeListener = SDKInterfaceStub.sdkListeners;
                    threadSafeListener.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub.init.7.1.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str) {
                            invoke2(iSDKListener, str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ISDKListener l, String str) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                            l.onBattery(C48021.this.$it.getPercent(), C48021.this.$it.getChargeState());
                        }
                    });
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BatteryState it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getListenerWorker(), null, new C48021(it, null), 2, null);
            }
        });
        robotStatus.getSpeed().onChange(new Function1<RobotStatus.RobotSpeed, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$8
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RobotStatus.RobotSpeed robotSpeed) {
                invoke2(robotSpeed);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final RobotStatus.RobotSpeed it) {
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(it, "it");
                SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                threadSafeListener = SDKInterfaceStub.sdkListeners;
                threadSafeListener.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$8.1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str) {
                        invoke2(iSDKListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ISDKListener l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onSpeed(RobotStatus.RobotSpeed.this.getLine(), RobotStatus.RobotSpeed.this.getAngular());
                    }
                });
            }
        });
        robotStatus.getBatteryFloorLevelLimit().onChange(new Function1<RobotStatus.RobotBatteryFloorLevelLimit, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$9
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RobotStatus.RobotBatteryFloorLevelLimit robotBatteryFloorLevelLimit) {
                invoke2(robotBatteryFloorLevelLimit);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final RobotStatus.RobotBatteryFloorLevelLimit it) {
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(it, "it");
                SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                threadSafeListener = SDKInterfaceStub.sdkListeners;
                threadSafeListener.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$9.1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str) {
                        invoke2(iSDKListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ISDKListener l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onBatteryFloorLevelLimitResult(RobotStatus.RobotBatteryFloorLevelLimit.this.getStatus(), RobotStatus.RobotBatteryFloorLevelLimit.this.getLevel());
                    }
                });
            }
        });
        robotHardware.getErrorEvent().plusAssign(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$10
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String errors) {
                Intrinsics.checkParameterIsNotNull(errors, "errors");
                SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).triggerError(errors);
            }
        });
        robotHardware.getWarnEvent().plusAssign(new Function1<Pair<? extends Boolean, ? extends String>, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$11
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends Boolean, ? extends String> pair) {
                invoke2((Pair<Boolean, String>) pair);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Pair<Boolean, String> warn) {
                Intrinsics.checkParameterIsNotNull(warn, "warn");
                SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).suspendWarningWelfunction(warn);
            }
        });
        robotHardware.getLidarEvent().plusAssign(new Function1<PolarCoordinates[], Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$12
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PolarCoordinates[] polarCoordinatesArr) {
                invoke2(polarCoordinatesArr);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PolarCoordinates[] data) {
                InstallationModeConfig installationModeConfig3;
                Intrinsics.checkParameterIsNotNull(data, "data");
                SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                installationModeConfig3 = SDKInterfaceStub.installationModeConfig;
                InstallerServer installerService = installationModeConfig3.getInstallerService();
                if (installerService != null) {
                    installerService.sendLidarData(data, 0.19d, 0.0d, 0.0d);
                }
            }
        });
        robotHardware.getEmergencyEvent().plusAssign(new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$13
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).hardwareEmergency(z);
            }
        });
        robotHardware.getFallDrop().plusAssign(new Function1<GeomagneticData, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$14
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GeomagneticData geomagneticData) {
                invoke2(geomagneticData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GeomagneticData it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if ("left".equals(it.getDirection()) && CamerConfigHelper.INSTANCE.getMagic_sensor_left() != 0 && (it.getX() >= CamerConfigHelper.INSTANCE.getMagic_sensor_left() + 500 || it.getY() >= CamerConfigHelper.INSTANCE.getMagic_sensor_left() + 500 || it.getZ() >= CamerConfigHelper.INSTANCE.getMagic_sensor_left() + 500)) {
                    SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).fallDropOccurEvent(it.toString());
                }
                if (!"right".equals(it.getDirection()) || CamerConfigHelper.INSTANCE.getMagic_sensor_right() == 0) {
                    return;
                }
                if (it.getX() >= CamerConfigHelper.INSTANCE.getMagic_sensor_right() + 500 || it.getY() >= CamerConfigHelper.INSTANCE.getMagic_sensor_right() + 500 || it.getZ() >= CamerConfigHelper.INSTANCE.getMagic_sensor_right() + 500) {
                    SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).fallDropOccurEvent(it.toString());
                }
            }
        });
        robotHardware.getChargingPileConnectedEvent().plusAssign(new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$15
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).onConnectedChargingPile();
            }
        });
        robotHardware.getSecurityEvent().plusAssign(new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$16
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z) {
                ThreadSafeListener threadSafeListener;
                SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                threadSafeListener = SDKInterfaceStub.sdkListeners;
                threadSafeListener.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$16.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str) {
                        invoke2(iSDKListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ISDKListener l, String str) {
                        String str2;
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
                        str2 = SDKInterfaceStub.TAG;
                        Pdlog.m3273d(str2, "securityFeedback callback " + z);
                        l.securityFeedback(z);
                    }
                });
            }
        });
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SDKInterfaceStub$init$17(null), 3, null);
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void addLisener(String name, ISDKListener callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        sdkListeners.add(name, callback);
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void removeListener(String name) {
        if (name != null) {
            sdkListeners.remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void init(LocateCase locateCase2) {
        Intrinsics.checkParameterIsNotNull(locateCase2, "locateCase");
        Pdlog.m3273d(TAG, TAG + " 初始化时 locType " + locateCase2);
        locateCase = locateCase2;
        StringBuilder sb = new StringBuilder();
        sb.append("{\"sdk\":{\"SDK\":\"commit: 8406db04, current user: yuanyi, push auth: “yuanyi”<“yuanyi@pudutech.com”>, git status: On branch feature/2022323_globalYour branch is up to date with 'origin/feature/2022323_global'.nothing to commit, working tree clean  build time 2023-30-11 15:30  diff encode string  time: “Mon Jul 10 14:59:52 2023 +0800”\", \"relays\":[");
        sb.append(AIDLGitHash.INSTANCE.getGitHash());
        sb.append(",");
        sb.append(installationModeConfig.getGitHash());
        sb.append(",");
        sb.append("]}}");
        Pdlog.resetGitHash(sb.toString());
        Pdlog.m3275i(TAG, "git", sb.toString());
        if (isIniting) {
            Pdlog.m3273d(TAG, "last init still running");
            return;
        }
        isIniting = true;
        disconnectSubService();
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SDKInterfaceStub$init$18(locateCase2, null), 3, null);
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void closeAuthCheck() {
        SDKSafe.INSTANCE.closeCheckFunc();
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public String getGitHash() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"sdk\":{\"SDK\":\"commit: 8406db04, current user: yuanyi, push auth: “yuanyi”<“yuanyi@pudutech.com”>, git status: On branch feature/2022323_globalYour branch is up to date with 'origin/feature/2022323_global'.nothing to commit, working tree clean  build time 2023-30-11 15:30  diff encode string  time: “Mon Jul 10 14:59:52 2023 +0800”\", \"relays\":[");
        sb.append(AIDLGitHash.INSTANCE.getGitHash());
        sb.append(",");
        sb.append(installationModeConfig.getGitHash());
        sb.append(",");
        if (robotHardware.getInterface() != null) {
            HardwareInterface hardwareInterface = robotHardware.getInterface();
            sb.append(hardwareInterface != null ? hardwareInterface.getCommitList() : null);
            sb.append(",");
        }
        if (coreService.getInterface() != null) {
            MirCoreInterface mirCoreInterface = coreService.getInterface();
            sb.append(mirCoreInterface != null ? mirCoreInterface.getGitHash() : null);
            sb.append(",");
        }
        sb.append("]}}");
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "string.toString()");
        return sb2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14, types: [java.lang.Object] */
    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void switchUsingPdmap(final String pdmap) {
        Object obj;
        String def;
        MapNameWithFloor mapNameWithFloor;
        MapNameWithFloor mapNameWithFloor2;
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("switch using map, current map ");
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        sb.append(moveAction.getAtlas().getMapFileName());
        sb.append(" goal name ");
        sb.append(pdmap);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if (pdmap == null) {
            Pdlog.m3274e(TAG, "cannot send null pdmap path");
            sdkListeners.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$switchUsingPdmap$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str2) {
                    invoke2(iSDKListener, str2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ISDKListener it, String str2) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                    it.onSwitchPdmapResult(SwitchMapResult.GoalNameNull, pdmap);
                }
            });
            return;
        }
        synchronized (Boolean.valueOf(reloadingMap)) {
            if (reloadingMap) {
                Pdlog.m3277w(TAG, "Last Reload Pdmap not finish");
                sdkListeners.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$switchUsingPdmap$$inlined$synchronized$lambda$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str2) {
                        invoke2(iSDKListener, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ISDKListener it, String str2) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        it.onSwitchPdmapResult(SwitchMapResult.LastSwitching, pdmap);
                    }
                });
                return;
            }
            reloadingMap = true;
            Unit unit = Unit.INSTANCE;
            JSONObject jSONObject = new JSONObject(pdmap);
            Object obj2 = jSONObject.get("floor");
            if (obj2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
            String str2 = (String) obj2;
            if (jSONObject.has("name")) {
                Object obj3 = jSONObject.get("name");
                if (obj3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                def = (String) obj3;
            } else {
                MoveAction moveAction2 = moveActionInterface;
                if (moveAction2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                }
                List<MapListConfig> list = moveAction2.getAtlas().getMapPackageConfig().getList();
                if (list == null) {
                    Intrinsics.throwNpe();
                }
                Iterator it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        obj = it.next();
                        if (Intrinsics.areEqual(((MapListConfig) obj).getFloor(), str2)) {
                            break;
                        }
                    } else {
                        obj = null;
                        break;
                    }
                }
                if (obj == null) {
                    Intrinsics.throwNpe();
                }
                def = ((MapListConfig) obj).getDef();
                if (def == null) {
                    Intrinsics.throwNpe();
                }
            }
            String str3 = def;
            Pdlog.m3273d(TAG, "switch goal floor " + str2 + ", goal map " + str3);
            MoveAction moveAction3 = moveActionInterface;
            if (moveAction3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
            }
            if (str3.equals(moveAction3.getAtlas().getMapFileName())) {
                Pdlog.m3273d(TAG, "goal map is same with cnt map");
                synchronized (Boolean.valueOf(reloadingMap)) {
                    reloadingMap = false;
                    Unit unit2 = Unit.INSTANCE;
                }
                sdkListeners.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$switchUsingPdmap$4
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str4) {
                        invoke2(iSDKListener, str4);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ISDKListener it2, String str4) {
                        Intrinsics.checkParameterIsNotNull(it2, "it");
                        Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                        it2.onSwitchPdmapResult(SwitchMapResult.GoalIsCurrent, pdmap);
                    }
                });
                return;
            }
            MoveAction moveAction4 = moveActionInterface;
            if (moveAction4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
            }
            for (Map.Entry<String, List<MapNameWithFloor>> entry : moveAction4.getAtlas().getMapNameList().entrySet()) {
                for (MapNameWithFloor mapNameWithFloor3 : entry.getValue()) {
                    Pdlog.m3273d(TAG, "print map list " + entry.getKey() + ' ' + mapNameWithFloor3.getName());
                }
            }
            MoveAction moveAction5 = moveActionInterface;
            if (moveAction5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
            }
            List<MapNameWithFloor> list2 = moveAction5.getAtlas().getMapNameList().get(str2);
            if (list2 != null) {
                Iterator it2 = list2.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        mapNameWithFloor2 = it2.next();
                        if (Intrinsics.areEqual(((MapNameWithFloor) mapNameWithFloor2).getName(), str3)) {
                            break;
                        }
                    } else {
                        mapNameWithFloor2 = 0;
                        break;
                    }
                }
                mapNameWithFloor = mapNameWithFloor2;
            } else {
                mapNameWithFloor = null;
            }
            if (mapNameWithFloor == null) {
                String str4 = TAG;
                Object[] objArr2 = new Object[1];
                StringBuilder sb2 = new StringBuilder();
                sb2.append("map name ");
                sb2.append(pdmap);
                sb2.append(" not element of map list ");
                MoveAction moveAction6 = moveActionInterface;
                if (moveAction6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                }
                sb2.append(moveAction6.getAtlas().getMapNameList());
                objArr2[0] = sb2.toString();
                Pdlog.m3274e(str4, objArr2);
                synchronized (Boolean.valueOf(reloadingMap)) {
                    reloadingMap = false;
                    Unit unit3 = Unit.INSTANCE;
                }
                sdkListeners.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$switchUsingPdmap$7
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str5) {
                        invoke2(iSDKListener, str5);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ISDKListener it3, String str5) {
                        Intrinsics.checkParameterIsNotNull(it3, "it");
                        Intrinsics.checkParameterIsNotNull(str5, "<anonymous parameter 1>");
                        it3.onSwitchPdmapResult(SwitchMapResult.GoalMapNotExist, pdmap);
                    }
                });
                return;
            }
            MirCoreInterface mirCoreInterface = coreService.getInterface();
            if (mirCoreInterface != null) {
                mirCoreInterface.switchAutoExposure(false);
            }
            MoveAction moveAction7 = moveActionInterface;
            if (moveAction7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
            }
            moveAction7.getAtlas().setMapFileName(str3);
            MoveAction moveAction8 = moveActionInterface;
            if (moveAction8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
            }
            String pdmapFullName = moveAction8.getAtlas().getPdmapFullName();
            MoveAction moveAction9 = moveActionInterface;
            if (moveAction9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
            }
            boolean init = moveAction9.init();
            Pdlog.m3273d(TAG, "switch result " + init);
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            booleanRef.element = true;
            final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
            booleanRef2.element = false;
            if (!init) {
                MoveAction moveAction10 = moveActionInterface;
                if (moveAction10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                }
                moveAction10.clearTopoData();
                booleanRef.element = false;
                booleanRef2.element = false;
                synchronized (Boolean.valueOf(reloadingMap)) {
                    reloadingMap = false;
                    Unit unit4 = Unit.INSTANCE;
                }
                sdkListeners.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$switchUsingPdmap$9
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    /* compiled from: SDKService.kt */
                    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "it", "Lcom/pudutech/mirsdk/aidl/ISDKListener;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
                    /* renamed from: com.pudutech.mirsdk.SDKInterfaceStub$switchUsingPdmap$9$1 */
                    /* loaded from: classes4.dex */
                    static final class C48091 extends Lambda implements Function2<ISDKListener, String, Unit> {
                        C48091() {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str) {
                            invoke2(iSDKListener, str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ISDKListener it, String str) {
                            SwitchMapResult switchMapResult;
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                            if (SDKInterfaceStub$switchUsingPdmap$9.this.$switchResult) {
                                switchMapResult = !SDKInterfaceStub$switchUsingPdmap$9.this.$coreLoadMapResult.element ? SwitchMapResult.MapReloadFailed : SwitchMapResult.Finish;
                            } else {
                                switchMapResult = SwitchMapResult.MapExtractFailed;
                            }
                            it.onSwitchPdmapResult(switchMapResult, pdmap);
                        }
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str5) {
                        invoke2(iSDKListener, str5);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ISDKListener it3, String str5) {
                        Intrinsics.checkParameterIsNotNull(it3, "it");
                        Intrinsics.checkParameterIsNotNull(str5, "<anonymous parameter 1>");
                        it3.onSwitchPdmapResult(SwitchMapResult.MapExtractFailed, pdmap);
                    }
                });
                return;
            }
            MirCoreInterface mirCoreInterface2 = coreService.getInterface();
            if (mirCoreInterface2 != null) {
                MoveAction moveAction11 = moveActionInterface;
                if (moveAction11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                }
                mirCoreInterface2.reloadPdmap(moveAction11.getAtlas().getDefaultFloorIndex(), pdmapFullName, null, new ReloadMapResultListener.Stub() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$switchUsingPdmap$10
                    @Override // com.pudutech.mirsdk.mircore.ReloadMapResultListener
                    public void reloadMapFail() {
                        Ref.BooleanRef.this.element = false;
                        booleanRef2.element = false;
                        SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).clearTopoData();
                    }

                    @Override // com.pudutech.mirsdk.mircore.ReloadMapResultListener
                    public void reloadMapSuccess() {
                        Ref.BooleanRef.this.element = false;
                        booleanRef2.element = true;
                    }
                });
            } else {
                booleanRef.element = false;
                booleanRef2.element = false;
                MoveAction moveAction12 = moveActionInterface;
                if (moveAction12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                }
                moveAction12.clearTopoData();
            }
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SDKInterfaceStub$switchUsingPdmap$11(booleanRef, str2, str3, booleanRef2, init, pdmap, null), 3, null);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void securitySwitch(final boolean oldState, boolean expectState) {
        Pdlog.m3273d(TAG, "securitySwitch oldState: " + oldState + ", expectState: " + expectState);
        if (robotStatus.getEmergencyKeyPressed().getValue().booleanValue()) {
            sdkListeners.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$securitySwitch$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str) {
                    invoke2(iSDKListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ISDKListener l, String str) {
                    String str2;
                    Intrinsics.checkParameterIsNotNull(l, "l");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                    str2 = SDKInterfaceStub.TAG;
                    Pdlog.m3273d(str2, "securityFeedback emergencyKeyPressed callback oldState: " + oldState);
                    l.securityFeedback(oldState);
                }
            });
            return;
        }
        HardwareInterface hardwareInterface = robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.securitySwitch(expectState);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void switchDefaultPdmap(final String pdmap) {
        Intrinsics.checkParameterIsNotNull(pdmap, "pdmap");
        Pdlog.m3273d(TAG, "switch specified default map " + pdmap);
        JSONObject jSONObject = new JSONObject(pdmap);
        Object obj = jSONObject.get("floor");
        if (obj == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        }
        String str = (String) obj;
        Object obj2 = jSONObject.get("name");
        if (obj2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        }
        String str2 = (String) obj2;
        synchronized (Boolean.valueOf(reloadingMap)) {
            if (reloadingMap) {
                sdkListeners.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$switchDefaultPdmap$$inlined$synchronized$lambda$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str3) {
                        invoke2(iSDKListener, str3);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ISDKListener it, String str3) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str3, "<anonymous parameter 1>");
                        it.onSwitchPdmapResult(SwitchMapResult.LastSwitching, pdmap);
                    }
                });
                return;
            }
            reloadingMap = true;
            Unit unit = Unit.INSTANCE;
            MoveAction moveAction = moveActionInterface;
            if (moveAction == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
            }
            boolean updateSpecifyDefaultMap = moveAction.updateSpecifyDefaultMap(str, str2);
            Pdlog.m3273d(TAG, "switchDefaultPdmap result " + updateSpecifyDefaultMap);
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            booleanRef.element = true;
            if (!updateSpecifyDefaultMap) {
                booleanRef.element = false;
                sdkListeners.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$switchDefaultPdmap$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str3) {
                        invoke2(iSDKListener, str3);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ISDKListener it, String str3) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str3, "<anonymous parameter 1>");
                        it.onSwitchPdmapResult(SwitchMapResult.MapExtractFailed, pdmap);
                    }
                });
            } else {
                MirCoreInterface mirCoreInterface = coreService.getInterface();
                if (mirCoreInterface != null) {
                    mirCoreInterface.switchAutoExposure(false);
                }
                MirCoreInterface mirCoreInterface2 = coreService.getInterface();
                if (mirCoreInterface2 != null) {
                    MoveAction moveAction2 = moveActionInterface;
                    if (moveAction2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                    }
                    mirCoreInterface2.reloadPdmap(0, null, moveAction2.getAtlas().topoFileList(), new SDKInterfaceStub$switchDefaultPdmap$3(booleanRef, pdmap));
                } else {
                    booleanRef.element = false;
                    sdkListeners.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$switchDefaultPdmap$4
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str3) {
                            invoke2(iSDKListener, str3);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ISDKListener it, String str3) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str3, "<anonymous parameter 1>");
                            it.onSwitchPdmapResult(SwitchMapResult.MapReloadFailed, pdmap);
                        }
                    });
                }
            }
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SDKInterfaceStub$switchDefaultPdmap$5(booleanRef, null), 3, null);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public String defaultDiningOutlet() {
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        return moveAction.getAtlas().getDefaultTakingId();
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public MapPackageConfig getPdmapNameList() {
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        return moveAction.getAtlas().getMapPackageConfig();
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public List<MapInfo> getMapInfoList() {
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        return moveAction.getAtlas().getMapInfoList();
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void reloadMap(String pdmap) {
        MachineInfo machineInfo;
        Intrinsics.checkParameterIsNotNull(pdmap, "pdmap");
        Pdlog.m3273d(TAG, "reload map, locType = " + locateCase);
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        Atlas atlas = moveAction.getAtlas();
        LocateCase locateCase2 = locateCase;
        HardwareInterface hardwareInterface = robotHardware.getInterface();
        ProductMachineType productMachineType = (hardwareInterface == null || (machineInfo = hardwareInterface.getMachineInfo()) == null) ? null : machineInfo.getProductMachineType();
        if (productMachineType == null) {
            Intrinsics.throwNpe();
        }
        atlas.extractMaps(locateCase2, productMachineType);
        MoveAction moveAction2 = moveActionInterface;
        if (moveAction2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        moveAction2.getAtlas().checkDefaultMap();
        MoveAction moveAction3 = moveActionInterface;
        if (moveAction3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        String pdmapFullName = moveAction3.getAtlas().getPdmapFullName();
        MoveAction moveAction4 = moveActionInterface;
        if (moveAction4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        moveAction4.init();
        MirCoreInterface mirCoreInterface = coreService.getInterface();
        if (mirCoreInterface != null) {
            MoveAction moveAction5 = moveActionInterface;
            if (moveAction5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
            }
            mirCoreInterface.reloadPdmap(moveAction5.getAtlas().getDefaultFloorIndex(), pdmapFullName, null, new ReloadMapResultListener.Stub() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$reloadMap$1
                @Override // com.pudutech.mirsdk.mircore.ReloadMapResultListener
                public void reloadMapFail() {
                    ThreadSafeListener threadSafeListener;
                    SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                    threadSafeListener = SDKInterfaceStub.loadCoreListener;
                    threadSafeListener.notify(new Function2<ILoadCoreListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$reloadMap$1$reloadMapFail$1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(ILoadCoreListener iLoadCoreListener, String str) {
                            invoke2(iLoadCoreListener, str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ILoadCoreListener it, String name) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(name, "name");
                            it.coreInitStepResult(CoreStepType.LoadLocateMap, CoreInitState.Fail);
                        }
                    });
                    SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).clearTopoData();
                    SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().checkDefaultMap();
                }

                @Override // com.pudutech.mirsdk.mircore.ReloadMapResultListener
                public void reloadMapSuccess() {
                    ThreadSafeListener threadSafeListener;
                    String str;
                    SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                    threadSafeListener = SDKInterfaceStub.loadCoreListener;
                    threadSafeListener.notify(new Function2<ILoadCoreListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$reloadMap$1$reloadMapSuccess$1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(ILoadCoreListener iLoadCoreListener, String str2) {
                            invoke2(iLoadCoreListener, str2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ILoadCoreListener it, String name) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(name, "name");
                            it.coreInitStepResult(CoreStepType.LoadLocateMap, CoreInitState.Success);
                        }
                    });
                    SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
                    str = SDKInterfaceStub.TAG;
                    Pdlog.m3273d(str, "coreInterface reloadMapSuccess ");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object openHardware(Continuation<? super Boolean> continuation) {
        SDKInterfaceStub$openHardware$1 sDKInterfaceStub$openHardware$1;
        Object obj;
        int i;
        int i2;
        SDKInterfaceStub sDKInterfaceStub;
        if (continuation instanceof SDKInterfaceStub$openHardware$1) {
            sDKInterfaceStub$openHardware$1 = (SDKInterfaceStub$openHardware$1) continuation;
            if ((sDKInterfaceStub$openHardware$1.label & Integer.MIN_VALUE) != 0) {
                sDKInterfaceStub$openHardware$1.label -= Integer.MIN_VALUE;
                SDKInterfaceStub$openHardware$1 sDKInterfaceStub$openHardware$12 = sDKInterfaceStub$openHardware$1;
                obj = sDKInterfaceStub$openHardware$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = sDKInterfaceStub$openHardware$12.label;
                boolean z = false;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    initStepNotify$default(this, InitStep.ConnectRobotHardwareService, StepState.Running, (String) null, 4, (Object) null);
                    Pdlog.m3276v(TAG, "connect hardware running");
                    RobotHardware robotHardware2 = robotHardware;
                    Context context2 = context;
                    if (context2 == null) {
                        Intrinsics.throwNpe();
                    }
                    sDKInterfaceStub$openHardware$12.L$0 = this;
                    sDKInterfaceStub$openHardware$12.label = 1;
                    i2 = 1;
                    obj = AIDLConnection.connect$default(robotHardware2, context2, null, sDKInterfaceStub$openHardware$12, 2, null);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sDKInterfaceStub = this;
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            ResultKt.throwOnFailure(obj);
                            z = ((Boolean) obj).booleanValue();
                            return Boxing.boxBoolean(z);
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    SDKInterfaceStub sDKInterfaceStub2 = (SDKInterfaceStub) sDKInterfaceStub$openHardware$12.L$0;
                    ResultKt.throwOnFailure(obj);
                    sDKInterfaceStub = sDKInterfaceStub2;
                    i2 = 1;
                }
                if (((Boolean) obj).booleanValue()) {
                    String str = TAG;
                    Object[] objArr = new Object[i2];
                    objArr[0] = "connect hardware fail";
                    Pdlog.m3277w(str, objArr);
                    sDKInterfaceStub.initStepNotify(InitStep.ConnectRobotHardwareService, StepState.Fail, "{\"error\":\"connect hardware fail\"}");
                    return Boxing.boxBoolean(z);
                }
                initStepNotify$default(sDKInterfaceStub, InitStep.ConnectRobotHardwareService, StepState.Success, (String) null, 4, (Object) null);
                String str2 = TAG;
                Object[] objArr2 = new Object[i2];
                objArr2[0] = "opening hardware";
                Pdlog.m3276v(str2, objArr2);
                RobotHardware robotHardware3 = robotHardware;
                sDKInterfaceStub$openHardware$12.L$0 = sDKInterfaceStub;
                sDKInterfaceStub$openHardware$12.label = 2;
                obj = robotHardware3.startUpAndWaitResult(sDKInterfaceStub$openHardware$12);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                z = ((Boolean) obj).booleanValue();
                return Boxing.boxBoolean(z);
            }
        }
        sDKInterfaceStub$openHardware$1 = new SDKInterfaceStub$openHardware$1(this, continuation);
        SDKInterfaceStub$openHardware$1 sDKInterfaceStub$openHardware$122 = sDKInterfaceStub$openHardware$1;
        obj = sDKInterfaceStub$openHardware$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = sDKInterfaceStub$openHardware$122.label;
        boolean z2 = false;
        if (i != 0) {
        }
        if (((Boolean) obj).booleanValue()) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
    /* JADX WARN: Type inference failed for: r2v28, types: [int] */
    /* JADX WARN: Type inference failed for: r4v15, types: [int] */
    /* JADX WARN: Type inference failed for: r9v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object openCore(LocateCase locateCase2, Continuation<? super Unit> continuation) {
        SDKInterfaceStub$openCore$1 sDKInterfaceStub$openCore$1;
        Object obj;
        int i;
        int i2;
        int i3;
        Integer num;
        ProductMachineType productMachineType;
        boolean z;
        LocateCase locateCase3;
        SDKInterfaceStub sDKInterfaceStub;
        CameraInterface camera;
        SDKInterfaceStub sDKInterfaceStub2;
        boolean z2;
        MachineInfo machineInfo;
        String str;
        String str2;
        boolean z3;
        HardwareInterface hardwareInterface;
        if (continuation instanceof SDKInterfaceStub$openCore$1) {
            sDKInterfaceStub$openCore$1 = (SDKInterfaceStub$openCore$1) continuation;
            if ((sDKInterfaceStub$openCore$1.label & Integer.MIN_VALUE) != 0) {
                sDKInterfaceStub$openCore$1.label -= Integer.MIN_VALUE;
                SDKInterfaceStub$openCore$1 sDKInterfaceStub$openCore$12 = sDKInterfaceStub$openCore$1;
                obj = sDKInterfaceStub$openCore$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = sDKInterfaceStub$openCore$12.label;
                boolean z4 = false;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Pdlog.m3276v(TAG, "coreService.connecting");
                    initStepNotify$default(this, InitStep.CheckMap, StepState.Running, (String) null, 4, (Object) null);
                    HardwareInterface hardwareInterface2 = robotHardware.getInterface();
                    ProductMachineType productMachineType2 = (hardwareInterface2 == null || (machineInfo = hardwareInterface2.getMachineInfo()) == null) ? null : machineInfo.getProductMachineType();
                    MoveAction moveAction = moveActionInterface;
                    if (moveAction == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                    }
                    Atlas atlas = moveAction.getAtlas();
                    if (productMachineType2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!atlas.extractMaps(locateCase2, productMachineType2)) {
                        Pdlog.m3274e(TAG, "extract map fail");
                        InitStep initStep = InitStep.CheckMap;
                        StepState stepState = StepState.Fail;
                        StringBuilder sb = new StringBuilder();
                        sb.append("{\"error\":\"");
                        MoveAction moveAction2 = moveActionInterface;
                        if (moveAction2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                        }
                        sb.append(moveAction2.getAtlas().getErrorReason());
                        sb.append("\"}");
                        initStepNotify(initStep, stepState, sb.toString());
                    } else {
                        MoveAction moveAction3 = moveActionInterface;
                        if (moveAction3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                        }
                        Integer checkDefaultMap = moveAction3.getAtlas().checkDefaultMap();
                        if (checkDefaultMap != null) {
                            Pdlog.m3275i(TAG, "switch camera exposure: " + checkDefaultMap);
                            HardwareInterface hardwareInterface3 = robotHardware.getInterface();
                            if (hardwareInterface3 != null && (camera = hardwareInterface3.getCamera()) != null) {
                                camera.setCameraExposure(checkDefaultMap.intValue());
                            }
                        }
                        HardwareInterface hardwareInterface4 = robotHardware.getInterface();
                        if (hardwareInterface4 != null) {
                            MoveAction moveAction4 = moveActionInterface;
                            if (moveAction4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                            }
                            hardwareInterface4.controlSlamCorePower(moveAction4.getAtlas().getLocateCase() == LocateCase.Slamware);
                        }
                        MoveAction moveAction5 = moveActionInterface;
                        if (moveAction5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                        }
                        boolean init = moveAction5.init();
                        if (!init) {
                            InitStep initStep2 = InitStep.CheckMap;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("{\"error\":\"");
                            MoveAction moveAction6 = moveActionInterface;
                            if (moveAction6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                            }
                            sb2.append(moveAction6.getAtlas().getErrorReason());
                            sb2.append("\"}");
                            initStepNotify(initStep2, init, sb2.toString());
                            Pdlog.m3274e(TAG, "map parse fail");
                        } else {
                            initStepNotify$default(this, InitStep.CheckMap, init, (String) null, 4, (Object) null);
                            initStepNotify$default(this, InitStep.ConnectCoreService, StepState.Running, (String) null, 4, (Object) null);
                            AIDLConnection<MirCoreInterface> aIDLConnection = coreService;
                            Context context2 = context;
                            if (context2 == null) {
                                Intrinsics.throwNpe();
                            }
                            sDKInterfaceStub$openCore$12.L$0 = this;
                            sDKInterfaceStub$openCore$12.L$1 = locateCase2;
                            sDKInterfaceStub$openCore$12.I$0 = 0;
                            sDKInterfaceStub$openCore$12.L$2 = productMachineType2;
                            sDKInterfaceStub$openCore$12.L$3 = checkDefaultMap;
                            sDKInterfaceStub$openCore$12.Z$0 = init;
                            sDKInterfaceStub$openCore$12.label = 1;
                            i2 = 1;
                            i3 = 2;
                            Object connect$default = AIDLConnection.connect$default(aIDLConnection, context2, null, sDKInterfaceStub$openCore$12, 2, null);
                            if (connect$default == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            num = checkDefaultMap;
                            obj = connect$default;
                            z4 = false;
                            productMachineType = productMachineType2;
                            z = init;
                            locateCase3 = locateCase2;
                            sDKInterfaceStub = this;
                        }
                    }
                    sDKInterfaceStub2 = this;
                    z2 = false;
                    if (!z4) {
                    }
                    return Unit.INSTANCE;
                }
                if (i != 1) {
                    if (i == 2) {
                        boolean z5 = sDKInterfaceStub$openCore$12.Z$0;
                        ?? r2 = sDKInterfaceStub$openCore$12.I$0;
                        SDKInterfaceStub sDKInterfaceStub3 = (SDKInterfaceStub) sDKInterfaceStub$openCore$12.L$0;
                        ResultKt.throwOnFailure(obj);
                        sDKInterfaceStub = sDKInterfaceStub3;
                        z2 = false;
                        i3 = 2;
                        z4 = z5;
                        z3 = r2;
                        i2 = 1;
                        if (((Boolean) obj).booleanValue()) {
                            z4 = z3;
                        } else {
                            MoveAction moveAction7 = moveActionInterface;
                            if (moveAction7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                            }
                            MoveAction moveAction8 = moveActionInterface;
                            if (moveAction8 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                            }
                            moveAction7.analyZonesData(moveAction8.getAtlas().getMap());
                            sDKInterfaceStub.reloadLocalization();
                            ChargeState chargeState = robotStatus.getBattery().getValue().getChargeState();
                            if ((chargeState == ChargeState.ChargeFullUsePile || chargeState == ChargeState.CharingUsePile) && (hardwareInterface = robotHardware.getInterface()) != null) {
                                byte[] bArr = new byte[8];
                                bArr[z2 ? 1 : 0] = (byte) 167;
                                bArr[i2] = (byte) i2;
                                byte b = z2 ? (byte) 1 : (byte) 0;
                                bArr[i3] = b;
                                bArr[3] = b;
                                bArr[4] = b;
                                bArr[5] = b;
                                bArr[6] = b;
                                bArr[7] = b;
                                hardwareInterface.sendCAN(bArr);
                            }
                        }
                        sDKInterfaceStub2 = sDKInterfaceStub;
                        if (!z4) {
                            initStepNotify$default(sDKInterfaceStub2, InitStep.Finish, true, (String) null, 4, (Object) null);
                            robotHardware.scheduleCommunicationListener(sdkListeners);
                        } else {
                            sDKInterfaceStub2.initStepNotify(InitStep.Finish, z2, "{\"error\":\"core init fail\"}");
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                z = sDKInterfaceStub$openCore$12.Z$0;
                Integer num2 = (Integer) sDKInterfaceStub$openCore$12.L$3;
                ProductMachineType productMachineType3 = (ProductMachineType) sDKInterfaceStub$openCore$12.L$2;
                ?? r4 = sDKInterfaceStub$openCore$12.I$0;
                locateCase3 = (LocateCase) sDKInterfaceStub$openCore$12.L$1;
                sDKInterfaceStub = (SDKInterfaceStub) sDKInterfaceStub$openCore$12.L$0;
                ResultKt.throwOnFailure(obj);
                num = num2;
                z4 = r4;
                i2 = 1;
                productMachineType = productMachineType3;
                i3 = 2;
                if (((Boolean) obj).booleanValue()) {
                    String str3 = TAG;
                    Object[] objArr = new Object[i2];
                    z2 = false;
                    objArr[0] = "coreService.connect fail";
                    Pdlog.m3274e(str3, objArr);
                    sDKInterfaceStub.initStepNotify(InitStep.ConnectCoreService, StepState.Fail, "{\"error\":\"coreService.connect fail\"}");
                    sDKInterfaceStub2 = sDKInterfaceStub;
                    if (!z4) {
                    }
                    return Unit.INSTANCE;
                }
                z2 = false;
                MoveAction moveAction9 = moveActionInterface;
                if (moveAction9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                }
                moveAction9.bindAreaDetectListener();
                MirCoreInterface mirCoreInterface = coreService.getInterface();
                if (mirCoreInterface == null || (str = mirCoreInterface.getGitHash()) == null) {
                    str = "{\"core\":\"unknown core\"}";
                }
                Pdlog.resetGitHash(str);
                String str4 = TAG;
                Object[] objArr2 = new Object[i2];
                MirCoreInterface mirCoreInterface2 = coreService.getInterface();
                if (mirCoreInterface2 == null || (str2 = mirCoreInterface2.getGitHash()) == null) {
                    str2 = "{\"core\":\"unknown core\"}";
                }
                objArr2[0] = str2;
                Pdlog.m3273d(str4, objArr2);
                String str5 = TAG;
                Object[] objArr3 = new Object[i2];
                objArr3[0] = "coreService.connected";
                Pdlog.m3276v(str5, objArr3);
                MoveAction moveAction10 = moveActionInterface;
                if (moveAction10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                }
                String pdmapFullName = moveAction10.getAtlas().getPdmapFullName();
                sDKInterfaceStub$openCore$12.L$0 = sDKInterfaceStub;
                sDKInterfaceStub$openCore$12.L$1 = locateCase3;
                sDKInterfaceStub$openCore$12.I$0 = z4 ? 1 : 0;
                sDKInterfaceStub$openCore$12.L$2 = productMachineType;
                sDKInterfaceStub$openCore$12.L$3 = num;
                sDKInterfaceStub$openCore$12.Z$0 = z;
                sDKInterfaceStub$openCore$12.label = i3;
                obj = sDKInterfaceStub.initCore(pdmapFullName, sDKInterfaceStub$openCore$12);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                boolean z6 = z4 ? 1 : 0;
                z4 = z;
                z3 = z6;
                if (((Boolean) obj).booleanValue()) {
                }
                sDKInterfaceStub2 = sDKInterfaceStub;
                if (!z4) {
                }
                return Unit.INSTANCE;
            }
        }
        sDKInterfaceStub$openCore$1 = new SDKInterfaceStub$openCore$1(this, continuation);
        SDKInterfaceStub$openCore$1 sDKInterfaceStub$openCore$122 = sDKInterfaceStub$openCore$1;
        obj = sDKInterfaceStub$openCore$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = sDKInterfaceStub$openCore$122.label;
        boolean z42 = false;
        if (i != 0) {
        }
        if (((Boolean) obj).booleanValue()) {
        }
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public MoveActionInterface getMoveActionInterface() {
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        return moveAction;
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public DeviceInterface getDeviceInterface() {
        return robot;
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public LocateCase getLocateCase() {
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        return moveAction.getAtlas().getLocateCase();
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public boolean isLocated() {
        LocalizationInterface localizer;
        LocalizationStatus localizationStatus;
        MirCoreInterface mirCoreInterface = coreService.getInterface();
        LocalizationStatusLevel status_level = (mirCoreInterface == null || (localizer = mirCoreInterface.getLocalizer()) == null || (localizationStatus = localizer.getLocalizationStatus()) == null) ? null : localizationStatus.getStatus_level();
        if (status_level != null && WhenMappings.$EnumSwitchMapping$1[status_level.ordinal()] != 1) {
            return true;
        }
        Pdlog.m3273d(TAG, "not located");
        return false;
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void reloadLocalization() {
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        moveAction.relocateAtPoints();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, com.pudutech.mirsdk.mircore.coreparcel.CoreInitState] */
    /* JADX WARN: Type inference failed for: r8v1, types: [T, com.pudutech.mirsdk.mircore.coreparcel.CoreInitStep] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object initCore(String str, Continuation<? super Boolean> continuation) {
        SDKInterfaceStub$initCore$1 sDKInterfaceStub$initCore$1;
        int i;
        final Ref.ObjectRef objectRef;
        SDKInterfaceStub sDKInterfaceStub;
        Ref.ObjectRef objectRef2;
        MirCoreInterface mirCoreInterface;
        MirCoreInterface mirCoreInterface2;
        MoveAction moveAction;
        MoveAction moveAction2;
        int i2;
        LocalizationInterface localizer;
        if (continuation instanceof SDKInterfaceStub$initCore$1) {
            sDKInterfaceStub$initCore$1 = (SDKInterfaceStub$initCore$1) continuation;
            if ((sDKInterfaceStub$initCore$1.label & Integer.MIN_VALUE) != 0) {
                sDKInterfaceStub$initCore$1.label -= Integer.MIN_VALUE;
                Object obj = sDKInterfaceStub$initCore$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = sDKInterfaceStub$initCore$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    objectRef = new Ref.ObjectRef();
                    objectRef.element = CoreInitState.Running;
                    final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                    objectRef3.element = (CoreInitStep) 0;
                    final Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                    objectRef4.element = "";
                    Pdlog.m3273d(TAG, "coreService.initModules");
                    MoveAction moveAction3 = moveActionInterface;
                    if (moveAction3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                    }
                    List<String> list = moveAction3.getAtlas().topoFileList();
                    MirCoreInterface mirCoreInterface3 = coreService.getInterface();
                    if (mirCoreInterface3 != null) {
                        MoveAction moveAction4 = moveActionInterface;
                        if (moveAction4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                        }
                        mirCoreInterface3.initModules(moveAction4.getAtlas().getDefaultFloorIndex(), str, list, new InitServiceListener.Stub() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$initCore$2
                            /* JADX WARN: Multi-variable type inference failed */
                            /* JADX WARN: Type inference failed for: r5v1, types: [T, java.lang.String] */
                            @Override // com.pudutech.mirsdk.mircore.InitServiceListener
                            public void initCoreServiceState(CoreInitStep p0, CoreInitState p1, String p2) {
                                if (p0 != 0 && SDKInterfaceStub.WhenMappings.$EnumSwitchMapping$2[p0.ordinal()] == 1) {
                                    if (p1 == CoreInitState.Success) {
                                        Ref.ObjectRef.this.element = p1;
                                    }
                                } else if (p1 == CoreInitState.Fail) {
                                    Ref.ObjectRef.this.element = p1;
                                    Ref.ObjectRef objectRef5 = objectRef4;
                                    objectRef5.element = ((String) objectRef5.element) + p2;
                                }
                                objectRef3.element = p0;
                            }
                        });
                    }
                    Pdlog.m3273d(TAG, "coreService.initModules call done, wait finish");
                    MoveAction moveAction5 = moveActionInterface;
                    if (moveAction5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                    }
                    moveAction5.getAtlas().getLocateCase();
                    LocateCase locateCase2 = LocateCase.Slamware;
                    SDKInterfaceStub$initCore$3 sDKInterfaceStub$initCore$3 = new SDKInterfaceStub$initCore$3(objectRef3, null);
                    sDKInterfaceStub$initCore$1.L$0 = this;
                    sDKInterfaceStub$initCore$1.L$1 = str;
                    sDKInterfaceStub$initCore$1.L$2 = objectRef;
                    sDKInterfaceStub$initCore$1.L$3 = objectRef3;
                    sDKInterfaceStub$initCore$1.L$4 = objectRef4;
                    sDKInterfaceStub$initCore$1.L$5 = list;
                    sDKInterfaceStub$initCore$1.J$0 = HardwareConfig.RGBDFwUpdateTimeOut;
                    sDKInterfaceStub$initCore$1.label = 1;
                    if (TimeoutKt.withTimeoutOrNull(HardwareConfig.RGBDFwUpdateTimeOut, sDKInterfaceStub$initCore$3, sDKInterfaceStub$initCore$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sDKInterfaceStub = this;
                    objectRef2 = objectRef4;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    long j = sDKInterfaceStub$initCore$1.J$0;
                    objectRef2 = (Ref.ObjectRef) sDKInterfaceStub$initCore$1.L$4;
                    Ref.ObjectRef objectRef5 = (Ref.ObjectRef) sDKInterfaceStub$initCore$1.L$2;
                    sDKInterfaceStub = (SDKInterfaceStub) sDKInterfaceStub$initCore$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    objectRef = objectRef5;
                }
                Pdlog.m3273d(TAG, "coreService.initModules finish status:" + ((CoreInitState) objectRef.element) + " result: " + ((String) objectRef2.element));
                mirCoreInterface = coreService.getInterface();
                if (mirCoreInterface != null) {
                    MoveAction moveAction6 = moveActionInterface;
                    if (moveAction6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                    }
                    mirCoreInterface.switchAutoExposure(moveAction6.getAtlas().getAutoexp());
                }
                mirCoreInterface2 = coreService.getInterface();
                if (mirCoreInterface2 != null && (localizer = mirCoreInterface2.getLocalizer()) != null) {
                    localizer.setLocateListener("function", robotStatus.getLocalizationListener());
                }
                moveAction = moveActionInterface;
                if (moveAction == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                }
                moveAction.addSchedulerListener();
                moveAction2 = moveActionInterface;
                if (moveAction2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
                }
                moveAction2.addPoseListener();
                i2 = WhenMappings.$EnumSwitchMapping$3[((CoreInitState) objectRef.element).ordinal()];
                if (i2 != 1) {
                    sDKInterfaceStub.initStepNotify(InitStep.ConnectCoreService, StepState.Success, "action 2");
                    String str2 = TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("coreService.initModules finish localizer:");
                    MirCoreInterface mirCoreInterface4 = coreService.getInterface();
                    sb.append(mirCoreInterface4 != null ? mirCoreInterface4.getLocalizer() : null);
                    objArr[0] = sb.toString();
                    Pdlog.m3276v(str2, objArr);
                } else if (i2 == 2) {
                    Pdlog.m3274e(TAG, "coreService.initModules Failed");
                    sDKInterfaceStub.initStepNotify(InitStep.ConnectCoreService, StepState.Fail, "Core init overtime");
                } else if (i2 == 3) {
                    Pdlog.m3274e(TAG, "coreService.initModules Failed");
                    InitStep initStep = InitStep.ConnectCoreService;
                    StepState stepState = StepState.Fail;
                    String str3 = (String) objectRef2.element;
                    if (str3 == null) {
                        str3 = "";
                    }
                    sDKInterfaceStub.initStepNotify(initStep, stepState, str3);
                }
                return Boxing.boxBoolean(((CoreInitState) objectRef.element) == CoreInitState.Success);
            }
        }
        sDKInterfaceStub$initCore$1 = new SDKInterfaceStub$initCore$1(this, continuation);
        Object obj2 = sDKInterfaceStub$initCore$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = sDKInterfaceStub$initCore$1.label;
        if (i != 0) {
        }
        Pdlog.m3273d(TAG, "coreService.initModules finish status:" + ((CoreInitState) objectRef.element) + " result: " + ((String) objectRef2.element));
        mirCoreInterface = coreService.getInterface();
        if (mirCoreInterface != null) {
        }
        mirCoreInterface2 = coreService.getInterface();
        if (mirCoreInterface2 != null) {
            localizer.setLocateListener("function", robotStatus.getLocalizationListener());
        }
        moveAction = moveActionInterface;
        if (moveAction == null) {
        }
        moveAction.addSchedulerListener();
        moveAction2 = moveActionInterface;
        if (moveAction2 == null) {
        }
        moveAction2.addPoseListener();
        i2 = WhenMappings.$EnumSwitchMapping$3[((CoreInitState) objectRef.element).ordinal()];
        if (i2 != 1) {
        }
        return Boxing.boxBoolean(((CoreInitState) objectRef.element) == CoreInitState.Success);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void initStepNotify$default(SDKInterfaceStub sDKInterfaceStub, InitStep initStep, StepState stepState, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = "";
        }
        sDKInterfaceStub.initStepNotify(initStep, stepState, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initStepNotify(final InitStep step, final StepState stepState, final String description) {
        Pdlog.m3275i(TAG, "onInitStep " + step + ' ' + stepState + ' ' + description);
        if (isIniting) {
            sdkListeners.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$initStepNotify$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str) {
                    invoke2(iSDKListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ISDKListener l, String str) {
                    Intrinsics.checkParameterIsNotNull(l, "l");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    l.onInitStep(InitStep.this, StepState.valueOf(stepState.name()), description);
                }
            });
            return;
        }
        Pdlog.m3277w(TAG, "initStepNotify " + step + " invalid, is not in Initing state");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reportCloud(String msg) {
        if (msg != null) {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SDKInterfaceStub$reportCloud$1(msg, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void initStepNotify$default(SDKInterfaceStub sDKInterfaceStub, InitStep initStep, boolean z, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = "";
        }
        sDKInterfaceStub.initStepNotify(initStep, z, str);
    }

    private final void initStepNotify(InitStep step, boolean success, String description) {
        initStepNotify(step, success ? StepState.Success : StepState.Fail, description);
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public boolean isLocalizationFinishInitialization() {
        LocalizationInterface localizer;
        MirCoreInterface mirCoreInterface = coreService.getInterface();
        return (mirCoreInterface == null || (localizer = mirCoreInterface.getLocalizer()) == null || !localizer.isLocalizationFinishInitialization()) ? false : true;
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public boolean isRelocalizationSuccess() {
        LocalizationInterface localizer;
        MirCoreInterface mirCoreInterface = coreService.getInterface();
        LocalizationStatus localizationStatus = (mirCoreInterface == null || (localizer = mirCoreInterface.getLocalizer()) == null) ? null : localizer.getLocalizationStatus();
        LocalizationStatusLevel status_level = localizationStatus != null ? localizationStatus.getStatus_level() : null;
        return status_level != null && WhenMappings.$EnumSwitchMapping$4[status_level.ordinal()] == 1;
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public LocalizationStatus getLocalizationStatus() {
        LocalizationInterface localizer;
        MirCoreInterface mirCoreInterface = coreService.getInterface();
        LocalizationStatus localizationStatus = (mirCoreInterface == null || (localizer = mirCoreInterface.getLocalizer()) == null) ? null : localizer.getLocalizationStatus();
        if (localizationStatus == null) {
            Intrinsics.throwNpe();
        }
        return localizationStatus;
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void setInstallMode(boolean open) {
        Pdlog.m3275i(TAG, "setInstallMode " + open);
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SDKInterfaceStub$setInstallMode$1(open, null), 3, null);
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public boolean getInstallMode() {
        return installationModeConfig.getIsOpen();
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public String getHardwareVersion() {
        HardwareVersion[] hardwareVersion;
        HardwareInterface hardwareInterface = robotHardware.getInterface();
        if (hardwareInterface != null && (hardwareVersion = hardwareInterface.getHardwareVersion()) != null) {
            for (HardwareVersion hardwareVersion2 : hardwareVersion) {
                if (hardwareVersion2.getBoard() == HardwareBoard.Main) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(hardwareVersion2.getVer0());
                    sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                    sb.append(hardwareVersion2.getVer1());
                    sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                    sb.append(hardwareVersion2.getVer2());
                    return sb.toString();
                }
            }
        }
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public MachineInfo getMachineInfo() {
        HardwareInterface hardwareInterface = robotHardware.getInterface();
        if (hardwareInterface != null) {
            return hardwareInterface.getMachineInfo();
        }
        return null;
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public AccessControlServer getAccessControlServer() {
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            return null;
        }
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        return moveAction.getAccessControlServer();
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void setAccessControlServer(AccessControlServer p0) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        MoveAction moveAction = moveActionInterface;
        if (moveAction != null) {
            if (moveAction == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
            }
            Context context2 = context;
            if (context2 == null) {
                Intrinsics.throwNpe();
            }
            moveAction.setAccessControlServer(p0, context2);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void controlFaceDetect(boolean p0) {
        CameraInterface camera;
        CameraInterface camera2;
        Pdlog.m3273d(TAG, "controlFaceDetect p0 = " + p0 + ", enabledFaceDetect = " + enabledFaceDetect);
        if (p0 == enabledFaceDetect) {
            return;
        }
        enabledFaceDetect = p0;
        if (p0) {
            Pdlog.m3273d(TAG, "controlFaceDetect open");
            MirCoreInterface mirCoreInterface = coreService.getInterface();
            if (mirCoreInterface != null) {
                mirCoreInterface.addFaceDetectListener("sdk", new FaceDetectListener.Stub() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$controlFaceDetect$1
                    @Override // com.pudutech.mirsdk.mircore.FaceDetectListener
                    public void onDetection(final int flag, final double yaw, final double pitch, final double distance) {
                        ThreadSafeListener threadSafeListener;
                        SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                        threadSafeListener = SDKInterfaceStub.sdkListeners;
                        threadSafeListener.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$controlFaceDetect$1$onDetection$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str) {
                                invoke2(iSDKListener, str);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(ISDKListener it, String str) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                                it.onFaceDetectResult(flag, yaw, pitch, distance);
                            }
                        });
                    }
                });
            }
            MirCoreInterface mirCoreInterface2 = coreService.getInterface();
            if (mirCoreInterface2 != null) {
                mirCoreInterface2.enableFaceDetect(enabledFaceDetect);
            }
            HardwareInterface hardwareInterface = robotHardware.getInterface();
            if (hardwareInterface == null || (camera2 = hardwareInterface.getCamera()) == null) {
                return;
            }
            camera2.openMonocularCamera();
            return;
        }
        Pdlog.m3273d(TAG, "controlFaceDetect close");
        HardwareInterface hardwareInterface2 = robotHardware.getInterface();
        if (hardwareInterface2 != null && (camera = hardwareInterface2.getCamera()) != null) {
            camera.closeMonocularCamera();
        }
        MirCoreInterface mirCoreInterface3 = coreService.getInterface();
        if (mirCoreInterface3 != null) {
            mirCoreInterface3.enableFaceDetect(enabledFaceDetect);
        }
        MirCoreInterface mirCoreInterface4 = coreService.getInterface();
        if (mirCoreInterface4 != null) {
            mirCoreInterface4.removeFaceDetectListener("sdk");
        }
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public boolean enableDropDet(boolean p0) {
        Pdlog.m3273d(TAG, "enableDropDet " + p0);
        MirCoreInterface mirCoreInterface = coreService.getInterface();
        if (mirCoreInterface != null) {
            mirCoreInterface.setDropDetSwitch(p0);
        }
        CamerConfigHelper.INSTANCE.setDetDropSwitch(p0);
        CamerConfigHelper.INSTANCE.setDetDropSwitch2(p0);
        return getDropDetStatus();
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public boolean getDropDetStatus() {
        int detDropSwitch2 = CamerConfigHelper.INSTANCE.getDetDropSwitch2();
        return detDropSwitch2 != -1 ? detDropSwitch2 == 1 : CamerConfigHelper.INSTANCE.getDetDropSwitch() == 1;
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public boolean openSlipControl(boolean p0) {
        CamerConfigHelper.INSTANCE.setSlipControlSwitch(p0);
        return CamerConfigHelper.INSTANCE.getSlipControlSwitch() == 1;
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public boolean getSlipControlStatus() {
        return CamerConfigHelper.INSTANCE.getSlipControlSwitch() == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void magneticConfigJson() {
        HardwareInterface hardwareInterface;
        Pdlog.m3273d(TAG, "enableMagic switch is  magic_sensor_left is" + CamerConfigHelper.INSTANCE.getMagic_sensor_left() + " andCamerConfigHelper.magic_sensor_right is " + CamerConfigHelper.INSTANCE.getMagic_sensor_right() + ' ');
        if (CamerConfigHelper.INSTANCE.getMagic_sensor_left() == 0 && CamerConfigHelper.INSTANCE.getMagic_sensor_right() == 0) {
            return;
        }
        boolean z = SpUtils.get(context, "mirhardware", "magic_enable", false);
        if (z && (hardwareInterface = robotHardware.getInterface()) != null) {
            hardwareInterface.geomagneticCalibration(CamerConfigHelper.INSTANCE.getMagic_sensor_left() + 500, CamerConfigHelper.INSTANCE.getMagic_sensor_right() + 500, true);
        }
        Pdlog.m3273d(TAG, "enableMagic switch is " + z);
    }

    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v3 */
    private final boolean changeDropDet(boolean p0) {
        File file = new File(MapFilePathConfig.PRE_PARAMLINE_PATH);
        boolean isFileExists = FileUtils.isFileExists(file);
        ?? r3 = 0;
        if (!isFileExists) {
            Pdlog.m3273d(TAG, "/sdcard/pudu/config/PreParamInfoLine.json is not exist, enableDropDet is not support!");
            return isFileExists;
        }
        File file2 = new File("/sdcard/pudu/config/PreParamInfoLine_tmp.json");
        file2.createNewFile();
        for (String str : FilesKt.readLines$default(file, null, 1, null)) {
            String str2 = str;
            if (!TextUtils.isEmpty(str2)) {
                if (StringsKt.contains$default(str2, "det_drop", (boolean) r3, 2, (Object) null)) {
                    String str3 = TAG;
                    Object[] objArr = new Object[1];
                    objArr[r3] = "changeDropDet before text --> " + str.toString() + ' ';
                    Pdlog.m3273d(str3, objArr);
                    if (str != null) {
                        StringsKt.trim((CharSequence) str2).toString();
                        char c = p0 ? '1' : '0';
                        FilesKt.appendText$default(file2, StringsKt.replace$default(str, str.charAt(str.length() - 2), c, false, 4, (Object) null), null, 2, null);
                        FilesKt.appendText$default(file2, "\n", null, 2, null);
                        Pdlog.m3273d(TAG, "changeDropDet after text --> " + StringsKt.replace$default(str, str.charAt(str.length() - 2), c, false, 4, (Object) null) + ' ');
                        isFileExists = true;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                } else {
                    FilesKt.appendText$default(file2, str, null, 2, null);
                    FilesKt.appendText$default(file2, "\n", null, 2, null);
                }
            }
            r3 = 0;
        }
        if (!isFileExists) {
            return isFileExists;
        }
        file.delete();
        boolean renameTo = file2.renameTo(new File(MapFilePathConfig.PRE_PARAMLINE_PATH));
        Pdlog.m3273d(TAG, "/sdcard/pudu/config/PreParamInfoLine.json is refresh, enableDropDet is success!");
        return renameTo;
    }

    private final boolean checkDropDetStatus() {
        File file = new File(MapFilePathConfig.PRE_PARAMLINE_PATH);
        boolean isFileExists = FileUtils.isFileExists(file);
        if (!isFileExists) {
            Pdlog.m3273d(TAG, "/sdcard/pudu/config/PreParamInfoLine.json is not exist, getDropDetStatus is not support!");
            return isFileExists;
        }
        for (String str : FilesKt.readLines$default(file, null, 1, null)) {
            String str2 = str;
            if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "det_drop", false, 2, (Object) null)) {
                if (str != null) {
                    StringsKt.trim((CharSequence) str2).toString();
                    Pdlog.m3273d(TAG, "checkDropDetStatus text --> " + str.toString() + ' ');
                    isFileExists = str.charAt(str.length() - 2) == '1';
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
            }
        }
        return isFileExists;
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void enableReflector(boolean p0) {
        Pdlog.m3273d(TAG, "enableReflector " + p0);
        MirCoreInterface mirCoreInterface = coreService.getInterface();
        if (mirCoreInterface != null) {
            mirCoreInterface.setRefletorSwitch(p0);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void calibrationMarkerCamera(int p0, final LocateCameraCalibListener p1) {
        int i;
        MirCoreInterface mirCoreInterface;
        LocalizationInterface localizer;
        MachineInfo machineInfo;
        ProductMachineType productMachineType;
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        Pdlog.m3273d(TAG, "start calibration locate camera");
        HardwareInterface hardwareInterface = robotHardware.getInterface();
        MachineModel model = (hardwareInterface == null || (machineInfo = hardwareInterface.getMachineInfo()) == null || (productMachineType = machineInfo.getProductMachineType()) == null) ? null : productMachineType.getModel();
        if (model == null || (i = WhenMappings.$EnumSwitchMapping$5[model.ordinal()]) == 1 || i == 2 || (mirCoreInterface = coreService.getInterface()) == null || (localizer = mirCoreInterface.getLocalizer()) == null) {
            return;
        }
        localizer.calibrationMarkerCamera(new MarkerCameraCalibResultListener.Stub() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$calibrationMarkerCamera$1
            @Override // com.pudutech.mirsdk.mircore.MarkerCameraCalibResultListener
            public void calibResult(boolean result, Vector3d rpx, Vector3d pose) {
                Intrinsics.checkParameterIsNotNull(rpx, "rpx");
                Intrinsics.checkParameterIsNotNull(pose, "pose");
                LocateCameraCalibListener.this.calibResult(result, rpx, pose);
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void suspendCharingUsingPile() {
        HardwareInterface hardwareInterface = robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.suspendChargingUsePile();
        }
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        moveAction.backToPile();
        Pdlog.m3273d(TAG, "suspendCharingUsingPile");
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void addRelocationPoint(String id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        final Pair<Boolean, String> addRelocationPoint = moveAction.addRelocationPoint(id);
        sdkListeners.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$addRelocationPoint$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str) {
                invoke2(iSDKListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ISDKListener l, String str) {
                Intrinsics.checkParameterIsNotNull(l, "l");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                l.onAddRelocationPointResult(((Boolean) Pair.this.getFirst()).booleanValue(), (String) Pair.this.getSecond());
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public List<String> getRelocationPoints() {
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        return moveAction.getRelocationPoints();
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public ElevatorConnectionType getElevatorConnection() {
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            return null;
        }
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        return moveAction.getElevatorConnection();
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void setElevatorConnection(ElevatorConnectionType p0) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        MoveAction moveAction = moveActionInterface;
        if (moveAction != null) {
            if (moveAction == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
            }
            Context context2 = context;
            if (context2 == null) {
                Intrinsics.throwNpe();
            }
            moveAction.setElevatorConnection(p0, context2);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public DockerDetectResult detectChargePile() {
        MirCoreInterface mirCoreInterface = coreService.getInterface();
        if (mirCoreInterface != null) {
            return mirCoreInterface.detectChargeDocker();
        }
        return null;
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void addChargePile(String name, DockerDetectResult docker) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(docker, "docker");
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        final Pair addChargePoint$default = MoveAction.addChargePoint$default(moveAction, name, docker, null, 4, null);
        sdkListeners.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$addChargePile$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str) {
                invoke2(iSDKListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ISDKListener l, String str) {
                Intrinsics.checkParameterIsNotNull(l, "l");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                l.onAddChargePointResult(((Boolean) Pair.this.getFirst()).booleanValue(), (String) Pair.this.getSecond());
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void addBluetoothChargePile(String name, String mac, DockerDetectResult docker) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(docker, "docker");
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        final Pair<Boolean, String> addChargePoint = moveAction.addChargePoint(name, docker, mac);
        sdkListeners.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$addBluetoothChargePile$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str) {
                invoke2(iSDKListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ISDKListener l, String str) {
                Intrinsics.checkParameterIsNotNull(l, "l");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                l.onAddChargePointResult(((Boolean) Pair.this.getFirst()).booleanValue(), (String) Pair.this.getSecond());
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public BluetoothChargeInterface getBluetoothChargeInterface() {
        return ChargeUpdateTaskManager.INSTANCE;
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void controlBatteryLevel(int level) {
        Monitorable<RobotStatus.RobotBatteryFloorLevelLimit> batteryFloorLevelLimit;
        RobotStatus.RobotBatteryFloorLevelLimit value;
        RobotStatus robotStatus2 = robotStatus;
        if (robotStatus2 != null && (batteryFloorLevelLimit = robotStatus2.getBatteryFloorLevelLimit()) != null && (value = batteryFloorLevelLimit.getValue()) != null) {
            value.setStatus(1);
        }
        HardwareInterface hardwareInterface = robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.sendCAN(new byte[]{(byte) 180, 0, (byte) level, 0, 0, 0, 0});
        }
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void getBatteryLevel() {
        Monitorable<RobotStatus.RobotBatteryFloorLevelLimit> batteryFloorLevelLimit;
        RobotStatus.RobotBatteryFloorLevelLimit value;
        RobotStatus robotStatus2 = robotStatus;
        if (robotStatus2 != null && (batteryFloorLevelLimit = robotStatus2.getBatteryFloorLevelLimit()) != null && (value = batteryFloorLevelLimit.getValue()) != null) {
            value.setStatus(0);
        }
        HardwareInterface hardwareInterface = robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.sendCAN(new byte[]{(byte) 180, 2, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public int controlWheelErrorEvent(WheelError err, boolean response) {
        Intrinsics.checkParameterIsNotNull(err, "err");
        return robotHardware.controlWheelErrorEvent(err, response);
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public boolean checkLegalMap(String pdmap) {
        File file = new File(pdmap);
        Pdlog.m3273d(TAG, "path is " + file.getAbsolutePath() + "  name is " + file.getName());
        MapifyTools mapifyTools = MapifyTools.INSTANCE;
        String name = file.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "file.name");
        if (!mapifyTools.isBase64(StringsKt.replace$default(StringsKt.replace$default(StringsKt.removeSuffix(name, (CharSequence) ".pdmap"), "_", "+", false, 4, (Object) null), "-", "/", false, 4, (Object) null))) {
            Pdlog.m3273d(TAG, " {" + pdmap + "} is not base64 string");
            return false;
        }
        return MapifyTools.INSTANCE.checkMapPackNotLegal(file);
    }

    @Override // com.pudutech.mirsdk.aidl.SDKInterface
    public void reloadLocalizationByChargingPile(ChargingPileInfo pile) {
        MoveAction moveAction = moveActionInterface;
        if (moveAction == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveActionInterface");
        }
        moveAction.reloadLocalizationByChargingPile(pile);
    }

    private final void disconnectSubService() {
        try {
            if (coreService.getInterface() != null) {
                AIDLConnection<MirCoreInterface> aIDLConnection = coreService;
                Context context2 = context;
                if (context2 == null) {
                    Intrinsics.throwNpe();
                }
                aIDLConnection.disconnect(context2);
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "disconnect core service exception:" + Log.getStackTraceString(e));
        }
        try {
            if (robotHardware.getInterface() != null) {
                RobotHardware robotHardware2 = robotHardware;
                Context context3 = context;
                if (context3 == null) {
                    Intrinsics.throwNpe();
                }
                robotHardware2.disconnect(context3);
            }
        } catch (Exception e2) {
            Pdlog.m3274e(TAG, "disconnect hardware service exception:" + Log.getStackTraceString(e2));
        }
    }

    public final void disconnect() {
        disconnectSubService();
        context = (Context) null;
    }
}
