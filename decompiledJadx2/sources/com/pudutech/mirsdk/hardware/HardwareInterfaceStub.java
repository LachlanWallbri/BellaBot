package com.pudutech.mirsdk.hardware;

import android.content.Context;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.compat.topo.ConfigJson;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.ILidarData;
import com.pudutech.mirsdk.hardware.ISensorData;
import com.pudutech.mirsdk.hardware.MachineInfoProcess;
import com.pudutech.mirsdk.hardware.base.CommonKt;
import com.pudutech.mirsdk.hardware.can.CANBus;
import com.pudutech.mirsdk.hardware.mcuupdate.UpdateMCUTask;
import com.pudutech.mirsdk.hardware.serialize.AIDLGitHash;
import com.pudutech.mirsdk.hardware.serialize.AccelerationType;
import com.pudutech.mirsdk.hardware.serialize.DeviceType;
import com.pudutech.mirsdk.hardware.serialize.HardwareVersion;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.PolarCoordinates;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import com.pudutech.mirsdk.update.ApiConstants;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000ô\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0010\bÇ\u0002\u0018\u00002\u00020\u0001:\u0003}~\u007fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010#\u001a\u00020$H\u0002J\u001c\u0010%\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010\u00042\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J&\u0010)\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010\u00042\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010'\u001a\u0004\u0018\u00010,H\u0016J\u001c\u0010-\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010\u00042\b\u0010'\u001a\u0004\u0018\u00010\u0014H\u0016J\u001c\u0010.\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010\u00042\b\u0010'\u001a\u0004\u0018\u00010/H\u0016J\b\u00100\u001a\u00020$H\u0002J\u001f\u00101\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00102\u001a\u0004\u0018\u000103H\u0082@ø\u0001\u0000¢\u0006\u0002\u00104J\u001f\u00105\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00102\u001a\u0004\u0018\u000103H\u0082@ø\u0001\u0000¢\u0006\u0002\u00104J\u001f\u00106\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00102\u001a\u0004\u0018\u000103H\u0082@ø\u0001\u0000¢\u0006\u0002\u00104J\u001f\u00107\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00102\u001a\u0004\u0018\u000103H\u0082@ø\u0001\u0000¢\u0006\u0002\u00104J\u001f\u00108\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00102\u001a\u0004\u0018\u000103H\u0082@ø\u0001\u0000¢\u0006\u0002\u00104J\b\u00109\u001a\u00020$H\u0016J\u0010\u0010:\u001a\u00020$2\u0006\u0010;\u001a\u00020<H\u0016J\u001a\u0010=\u001a\u00020$2\b\u0010>\u001a\u0004\u0018\u00010?2\u0006\u0010@\u001a\u00020<H\u0016J\u0010\u0010A\u001a\u00020$2\u0006\u0010@\u001a\u00020<H\u0016J\u0010\u0010B\u001a\u00020$2\u0006\u0010C\u001a\u00020<H\u0016J\u0010\u0010D\u001a\u00020$2\u0006\u0010@\u001a\u00020<H\u0016J \u0010E\u001a\u00020$2\u0006\u0010F\u001a\u00020\u000e2\u0006\u0010G\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020<H\u0016J\u0011\u0010I\u001a\u00020JH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010KJ\u0012\u0010L\u001a\u00020$2\b\u0010>\u001a\u0004\u0018\u00010MH\u0016J\b\u0010N\u001a\u00020\bH\u0016J\b\u0010O\u001a\u00020<H\u0016J\b\u0010P\u001a\u00020\u0004H\u0016J\u0013\u0010Q\u001a\b\u0012\u0004\u0012\u00020S0RH\u0016¢\u0006\u0002\u0010TJ\u0016\u0010U\u001a\u00020\u00042\f\u0010V\u001a\b\u0012\u0004\u0012\u00020?0WH\u0016J\b\u0010X\u001a\u00020\u0016H\u0016J\n\u0010Y\u001a\u0004\u0018\u00010ZH\u0016J\n\u0010[\u001a\u0004\u0018\u00010\\H\u0016J\n\u0010]\u001a\u0004\u0018\u00010^H\u0016J\b\u0010_\u001a\u00020`H\u0016J\b\u0010a\u001a\u00020\u0004H\u0002J\u000e\u0010b\u001a\u00020$2\u0006\u0010c\u001a\u00020dJ\u0011\u0010e\u001a\u00020JH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010KJ\b\u0010f\u001a\u00020<H\u0016J\b\u0010g\u001a\u00020$H\u0016J\b\u0010h\u001a\u00020$H\u0016J\u0018\u0010i\u001a\u00020$2\u000e\u0010V\u001a\n\u0012\u0004\u0012\u00020?\u0018\u00010WH\u0016J\b\u0010j\u001a\u00020$H\u0016J\u0012\u0010k\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010l\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010m\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010n\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010\u0004H\u0016J!\u0010o\u001a\b\u0012\u0004\u0012\u00020p0R2\f\u0010q\u001a\b\u0012\u0004\u0012\u00020p0RH\u0002¢\u0006\u0002\u0010rJ\u0012\u0010s\u001a\u00020$2\b\u0010t\u001a\u0004\u0018\u00010+H\u0016J\u001a\u0010u\u001a\u00020$2\b\u0010>\u001a\u0004\u0018\u00010M2\u0006\u0010v\u001a\u00020\u000eH\u0016J\u0010\u0010w\u001a\u00020$2\u0006\u0010x\u001a\u00020<H\u0016J\b\u0010y\u001a\u00020$H\u0016J\u0010\u0010z\u001a\u00020$2\u0006\u0010{\u001a\u00020<H\u0016J\u0011\u0010|\u001a\u00020JH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010KR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0080\u0001"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/HardwareInterfaceStub;", "Lcom/pudutech/mirsdk/hardware/HardwareInterface$Stub;", "()V", "TAG", "", "brakeJob", "Lkotlinx/coroutines/Job;", "cameraImpl", "Lcom/pudutech/mirsdk/hardware/CameraImpl;", "canBus", "Lcom/pudutech/mirsdk/hardware/can/CANBus;", "carpetModeConfig", "Lcom/pudutech/mirsdk/hardware/CarpetModeConfig;", "currentLineSpeed", "", "enableBrake", "Ljava/util/concurrent/atomic/AtomicBoolean;", "filter_threshold", "hardwareListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/hardware/IHardware;", "lidarInterfaceImpl", "Lcom/pudutech/mirsdk/hardware/LidarInterfaceImpl;", "mUsbController", "Lcom/pudutech/mirsdk/hardware/USBController;", "machineInfoProcess", "Lcom/pudutech/mirsdk/hardware/MachineInfoProcess;", "openJob", "rgbdInterfaceImpl", "Lcom/pudutech/mirsdk/hardware/RGBDInterfaceImpl;", "scheduler", "Lcom/pudutech/mirsdk/hardware/ScheduleCommunicationImpl;", "updateMCUTask", "Lcom/pudutech/mirsdk/hardware/mcuupdate/UpdateMCUTask;", "waitSelfCheck", "SafetyMonitor", "", "addAccelerationListener", "name", "listener", "Lcom/pudutech/mirsdk/hardware/IAccelerationData;", "addCANDataListener", "idList", "", "Lcom/pudutech/mirsdk/hardware/ICANData;", "addListener", "addSensorListener", "Lcom/pudutech/mirsdk/hardware/ISensorData;", "brake", "checkCamera", "selfCheckSensorInfo", "Lcom/pudutech/mirsdk/hardware/HardwareInterfaceStub$SelfCheckSensorInfo;", "(Lcom/pudutech/mirsdk/hardware/HardwareInterfaceStub$SelfCheckSensorInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkEncoder", "checkIMU", "checkLidar", "checkRGBD", "clearWheelError", "controlCameraIRDLED", "lightOn", "", "controlDevice", "type", "Lcom/pudutech/mirsdk/hardware/serialize/DeviceType;", "powerOn", "controlDisinfectionPower", "controlRGBD", DebugKt.DEBUG_PROPERTY_VALUE_ON, "controlSlamCorePower", "controlWheel", "linearSpeed", "angularSpeed", "isCloseLoop", "fetchMachineInfo", "Lcom/pudutech/mirsdk/hardware/HardwareInterfaceStub$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccelerationData", "Lcom/pudutech/mirsdk/hardware/serialize/AccelerationType;", "getCamera", "getCarpetMode", "getCommitList", "getHardwareVersion", "", "Lcom/pudutech/mirsdk/hardware/serialize/HardwareVersion;", "()[Lcom/pudutech/mirsdk/hardware/serialize/HardwareVersion;", "getLastError", "deviceTypes", "", "getLidarInterface", "getMachineInfo", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo;", "getRGBDInterface", "Lcom/pudutech/mirsdk/hardware/RGBDInterface;", "getScheduler", "Lcom/pudutech/mirsdk/hardware/ScheduleCommunication;", "getUsbControlInterface", "Lcom/pudutech/mirsdk/hardware/UsbControlInterface;", "getWIFIMac", "init", "context", "Landroid/content/Context;", "mcuUpdateInIAPMode", "oilStainCheck", "open", "openCAN", "openCANEx", "refreshMachineInfo", "removeAccelerationListener", "removeCANDataListener", "removeListener", "removeSensorListener", "scan_filtered", "Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;", "scan_origin", "([Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;)[Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;", "sendCAN", "p0", "setAccelerationData", "data", "setRgbdParkingMode", "enable", "suspendChargingUsePile", "switchCarpetMode", "mode", "updateMCU", "Result", "SelfCheckSensor", "SelfCheckSensorInfo", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class HardwareInterfaceStub extends HardwareInterface.Stub {
    private static Job brakeJob;
    private static CANBus canBus;
    private static CarpetModeConfig carpetModeConfig;
    private static double currentLineSpeed;
    private static LidarInterfaceImpl lidarInterfaceImpl;
    private static Job openJob;
    private static RGBDInterfaceImpl rgbdInterfaceImpl;
    private static ScheduleCommunicationImpl scheduler;
    private static UpdateMCUTask updateMCUTask;
    public static final HardwareInterfaceStub INSTANCE = new HardwareInterfaceStub();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static ThreadSafeListener<IHardware> hardwareListener = new ThreadSafeListener<>();
    private static final MachineInfoProcess machineInfoProcess = new MachineInfoProcess();
    private static final CameraImpl cameraImpl = new CameraImpl(machineInfoProcess);
    private static AtomicBoolean waitSelfCheck = new AtomicBoolean(false);
    private static USBController mUsbController = new USBController();
    private static AtomicBoolean enableBrake = new AtomicBoolean(false);
    private static double filter_threshold = 0.08d;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/HardwareInterfaceStub$SelfCheckSensor;", "", "(Ljava/lang/String;I)V", "IMU", "Encoder", "MarkerCamera", "RGBD", "Lidar", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum SelfCheckSensor {
        IMU,
        Encoder,
        MarkerCamera,
        RGBD,
        Lidar
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bb\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/HardwareInterfaceStub$SelfCheckSensorInfo;", "", "onState", "", ConfigJson.SENSOR, "Lcom/pudutech/mirsdk/hardware/HardwareInterfaceStub$SelfCheckSensor;", SpeechUtility.TAG_RESOURCE_RESULT, "", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public interface SelfCheckSensorInfo {
        void onState(SelfCheckSensor sensor, boolean result);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;

        static {
            int[] iArr = new int[MachineInfoProcess.FetchResult.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[MachineInfoProcess.FetchResult.Fail.ordinal()] = 1;
            $EnumSwitchMapping$0[MachineInfoProcess.FetchResult.NotSetup.ordinal()] = 2;
            int[] iArr2 = new int[MachineModel.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[MachineModel.Hls.ordinal()] = 1;
            $EnumSwitchMapping$1[MachineModel.Puductor.ordinal()] = 2;
            $EnumSwitchMapping$1[MachineModel.Ninetales.ordinal()] = 3;
            $EnumSwitchMapping$1[MachineModel.Firefox.ordinal()] = 4;
            int[] iArr3 = new int[MachineInfoProcess.FetchResult.values().length];
            $EnumSwitchMapping$2 = iArr3;
            iArr3[MachineInfoProcess.FetchResult.Fail.ordinal()] = 1;
            $EnumSwitchMapping$2[MachineInfoProcess.FetchResult.NotSetup.ordinal()] = 2;
            int[] iArr4 = new int[DeviceType.values().length];
            $EnumSwitchMapping$3 = iArr4;
            iArr4[DeviceType.ESP.ordinal()] = 1;
            $EnumSwitchMapping$3[DeviceType.RGBD.ordinal()] = 2;
            $EnumSwitchMapping$3[DeviceType.Disinfection.ordinal()] = 3;
            $EnumSwitchMapping$3[DeviceType.CameraIRDLED.ordinal()] = 4;
            int[] iArr5 = new int[MachineInfoProcess.FetchResult.values().length];
            $EnumSwitchMapping$4 = iArr5;
            iArr5[MachineInfoProcess.FetchResult.Fail.ordinal()] = 1;
            $EnumSwitchMapping$4[MachineInfoProcess.FetchResult.NotSetup.ordinal()] = 2;
            int[] iArr6 = new int[DeviceType.values().length];
            $EnumSwitchMapping$5 = iArr6;
            iArr6[DeviceType.RGBD.ordinal()] = 1;
            $EnumSwitchMapping$5[DeviceType.Camera.ordinal()] = 2;
            $EnumSwitchMapping$5[DeviceType.Lidar.ordinal()] = 3;
        }
    }

    private HardwareInterfaceStub() {
    }

    public static final /* synthetic */ CANBus access$getCanBus$p(HardwareInterfaceStub hardwareInterfaceStub) {
        CANBus cANBus = canBus;
        if (cANBus == null) {
            Intrinsics.throwUninitializedPropertyAccessException("canBus");
        }
        return cANBus;
    }

    public static final /* synthetic */ CarpetModeConfig access$getCarpetModeConfig$p(HardwareInterfaceStub hardwareInterfaceStub) {
        CarpetModeConfig carpetModeConfig2 = carpetModeConfig;
        if (carpetModeConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carpetModeConfig");
        }
        return carpetModeConfig2;
    }

    public static final /* synthetic */ LidarInterfaceImpl access$getLidarInterfaceImpl$p(HardwareInterfaceStub hardwareInterfaceStub) {
        LidarInterfaceImpl lidarInterfaceImpl2 = lidarInterfaceImpl;
        if (lidarInterfaceImpl2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidarInterfaceImpl");
        }
        return lidarInterfaceImpl2;
    }

    public static final /* synthetic */ RGBDInterfaceImpl access$getRgbdInterfaceImpl$p(HardwareInterfaceStub hardwareInterfaceStub) {
        RGBDInterfaceImpl rGBDInterfaceImpl = rgbdInterfaceImpl;
        if (rGBDInterfaceImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rgbdInterfaceImpl");
        }
        return rGBDInterfaceImpl;
    }

    public static final /* synthetic */ ScheduleCommunicationImpl access$getScheduler$p(HardwareInterfaceStub hardwareInterfaceStub) {
        ScheduleCommunicationImpl scheduleCommunicationImpl = scheduler;
        if (scheduleCommunicationImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduler");
        }
        return scheduleCommunicationImpl;
    }

    public static final /* synthetic */ UpdateMCUTask access$getUpdateMCUTask$p(HardwareInterfaceStub hardwareInterfaceStub) {
        UpdateMCUTask updateMCUTask2 = updateMCUTask;
        if (updateMCUTask2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateMCUTask");
        }
        return updateMCUTask2;
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public HardwareVersion[] getHardwareVersion() {
        CANBus cANBus = canBus;
        if (cANBus == null) {
            Intrinsics.throwUninitializedPropertyAccessException("canBus");
        }
        Object[] array = cANBus.getHardwareVersions().toArray(new HardwareVersion[0]);
        if (array != null) {
            return (HardwareVersion[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void sendCAN(byte[] p0) {
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("sendCAN ");
        sb.append(p0 != null ? CommonKt.toHexString(p0) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if (p0 != null) {
            CANBus cANBus = canBus;
            if (cANBus == null) {
                Intrinsics.throwUninitializedPropertyAccessException("canBus");
            }
            byte[] copyOf = Arrays.copyOf(p0, p0.length);
            Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
            cANBus.m4425sendGBYM_sE(UByteArray.m4572constructorimpl(copyOf));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\t¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/HardwareInterfaceStub$Result;", "", "isSuccess", "", "description", "", "(ZLjava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final /* data */ class Result {
        private final String description;
        private final boolean isSuccess;

        public static /* synthetic */ Result copy$default(Result result, boolean z, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                z = result.isSuccess;
            }
            if ((i & 2) != 0) {
                str = result.description;
            }
            return result.copy(z, str);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsSuccess() {
            return this.isSuccess;
        }

        /* renamed from: component2, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        public final Result copy(boolean isSuccess, String description) {
            Intrinsics.checkParameterIsNotNull(description, "description");
            return new Result(isSuccess, description);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return this.isSuccess == result.isSuccess && Intrinsics.areEqual(this.description, result.description);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.isSuccess;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            String str = this.description;
            return i + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Result(isSuccess=" + this.isSuccess + ", description=" + this.description + ")";
        }

        public Result(boolean z, String description) {
            Intrinsics.checkParameterIsNotNull(description, "description");
            this.isSuccess = z;
            this.description = description;
        }

        public final String getDescription() {
            return this.description;
        }

        public final boolean isSuccess() {
            return this.isSuccess;
        }
    }

    public final synchronized void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Pdlog.m3275i(TAG, "init rgbd");
        if (rgbdInterfaceImpl == null) {
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareInterfaceStub$init$2(context, null), 3, null);
        }
        if (lidarInterfaceImpl == null) {
            lidarInterfaceImpl = new LidarInterfaceImpl(context);
        }
        if (canBus == null) {
            canBus = new CANBus(context, hardwareListener);
        }
        if (updateMCUTask == null) {
            CANBus cANBus = canBus;
            if (cANBus == null) {
                Intrinsics.throwUninitializedPropertyAccessException("canBus");
            }
            updateMCUTask = new UpdateMCUTask(context, new HardwareInterfaceStub$init$6(cANBus), new HardwareInterfaceStub$init$7(INSTANCE));
        }
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareInterfaceStub$init$8(context, null), 3, null);
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public synchronized void open() {
        Job launch$default;
        Pdlog.m3275i(TAG, "open hardware");
        if (openJob == null) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareInterfaceStub$open$1(null), 3, null);
            openJob = launch$default;
        } else {
            Pdlog.m3277w(TAG, "hardware is opening, please wait first task finish");
        }
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public synchronized void openCAN() {
        Job launch$default;
        Pdlog.m3275i(TAG, "open CAN");
        if (openJob == null) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareInterfaceStub$openCAN$1(null), 3, null);
            openJob = launch$default;
        } else {
            Pdlog.m3277w(TAG, "hardware is opening, please wait first task finish");
        }
    }

    static /* synthetic */ Object checkIMU$default(HardwareInterfaceStub hardwareInterfaceStub, SelfCheckSensorInfo selfCheckSensorInfo, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            selfCheckSensorInfo = (SelfCheckSensorInfo) null;
        }
        return hardwareInterfaceStub.checkIMU(selfCheckSensorInfo, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object checkIMU(SelfCheckSensorInfo selfCheckSensorInfo, Continuation<? super Job> continuation) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareInterfaceStub$checkIMU$2(selfCheckSensorInfo, null), 3, null);
        return launch$default;
    }

    static /* synthetic */ Object checkEncoder$default(HardwareInterfaceStub hardwareInterfaceStub, SelfCheckSensorInfo selfCheckSensorInfo, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            selfCheckSensorInfo = (SelfCheckSensorInfo) null;
        }
        return hardwareInterfaceStub.checkEncoder(selfCheckSensorInfo, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object checkEncoder(SelfCheckSensorInfo selfCheckSensorInfo, Continuation<? super Job> continuation) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareInterfaceStub$checkEncoder$2(selfCheckSensorInfo, null), 3, null);
        return launch$default;
    }

    static /* synthetic */ Object checkLidar$default(HardwareInterfaceStub hardwareInterfaceStub, SelfCheckSensorInfo selfCheckSensorInfo, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            selfCheckSensorInfo = (SelfCheckSensorInfo) null;
        }
        return hardwareInterfaceStub.checkLidar(selfCheckSensorInfo, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object checkLidar(SelfCheckSensorInfo selfCheckSensorInfo, Continuation<? super Job> continuation) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareInterfaceStub$checkLidar$2(selfCheckSensorInfo, null), 3, null);
        return launch$default;
    }

    static /* synthetic */ Object checkCamera$default(HardwareInterfaceStub hardwareInterfaceStub, SelfCheckSensorInfo selfCheckSensorInfo, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            selfCheckSensorInfo = (SelfCheckSensorInfo) null;
        }
        return hardwareInterfaceStub.checkCamera(selfCheckSensorInfo, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object checkCamera(SelfCheckSensorInfo selfCheckSensorInfo, Continuation<? super Job> continuation) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareInterfaceStub$checkCamera$2(selfCheckSensorInfo, null), 3, null);
        return launch$default;
    }

    static /* synthetic */ Object checkRGBD$default(HardwareInterfaceStub hardwareInterfaceStub, SelfCheckSensorInfo selfCheckSensorInfo, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            selfCheckSensorInfo = (SelfCheckSensorInfo) null;
        }
        return hardwareInterfaceStub.checkRGBD(selfCheckSensorInfo, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object checkRGBD(SelfCheckSensorInfo selfCheckSensorInfo, Continuation<? super Job> continuation) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareInterfaceStub$checkRGBD$2(selfCheckSensorInfo, null), 3, null);
        return launch$default;
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void addListener(String name, IHardware listener) {
        if (name == null || listener == null) {
            return;
        }
        hardwareListener.add(name, listener);
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void removeListener(String name) {
        if (name != null) {
            hardwareListener.remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public MachineInfo getMachineInfo() {
        Pdlog.m3275i(TAG, "getMachineInfo fetchSuccess:" + machineInfoProcess.getFetchSuccess());
        if (!machineInfoProcess.getFetchSuccess()) {
            return null;
        }
        Pdlog.m3275i(TAG, "getMachineInfo size:" + machineInfoProcess.getMachineInfo().getFloatData().size() + " $" + machineInfoProcess.getMachineInfo().getIntData().size());
        return machineInfoProcess.getMachineInfo();
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public LidarInterfaceImpl getLidarInterface() {
        LidarInterfaceImpl lidarInterfaceImpl2 = lidarInterfaceImpl;
        if (lidarInterfaceImpl2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidarInterfaceImpl");
        }
        return lidarInterfaceImpl2;
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public RGBDInterface getRGBDInterface() {
        RGBDInterfaceImpl rGBDInterfaceImpl = rgbdInterfaceImpl;
        if (rGBDInterfaceImpl == null) {
            return null;
        }
        if (rGBDInterfaceImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rgbdInterfaceImpl");
        }
        return rGBDInterfaceImpl;
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public CameraImpl getCamera() {
        return cameraImpl;
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public ScheduleCommunication getScheduler() {
        ScheduleCommunicationImpl scheduleCommunicationImpl = scheduler;
        if (scheduleCommunicationImpl == null) {
            return null;
        }
        if (scheduleCommunicationImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduler");
        }
        return scheduleCommunicationImpl;
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void addSensorListener(String name, ISensorData listener) {
        if (name == null || listener == null) {
            return;
        }
        CANBus cANBus = canBus;
        if (cANBus == null) {
            Intrinsics.throwUninitializedPropertyAccessException("canBus");
        }
        cANBus.getSensorDataProvider().add(name, listener);
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void removeSensorListener(String name) {
        if (name != null) {
            CANBus cANBus = canBus;
            if (cANBus == null) {
                Intrinsics.throwUninitializedPropertyAccessException("canBus");
            }
            cANBus.getSensorDataProvider().remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void controlCameraIRDLED(final boolean lightOn) {
        Pdlog.m3275i(TAG, "controlCameraIRDLED");
        int i = WhenMappings.$EnumSwitchMapping$1[machineInfoProcess.getMachineInfo().getProductMachineType().getModel().ordinal()];
        if (i == 1 || i == 2) {
            CANBus cANBus = canBus;
            if (cANBus == null) {
                Intrinsics.throwUninitializedPropertyAccessException("canBus");
            }
            cANBus.controlCameraIRDLED(lightOn);
            return;
        }
        if (i == 3 || i == 4) {
            hardwareListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$controlCameraIRDLED$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                    invoke2(iHardware, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IHardware l, String str) {
                    Intrinsics.checkParameterIsNotNull(l, "l");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    l.onCameraIRDLED(lightOn);
                }
            });
            return;
        }
        GpioCtrl.INSTANCE.controlCameraIRDLED(lightOn);
        hardwareListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$controlCameraIRDLED$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                invoke2(iHardware, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IHardware l, String str) {
                Intrinsics.checkParameterIsNotNull(l, "l");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                l.onCameraIRDLED(lightOn);
            }
        });
        Pdlog.m3273d(TAG, "GpioCtrl.controlCameraIRDLED " + lightOn);
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void controlWheel(double linearSpeed, double angularSpeed, boolean isCloseLoop) {
        Pdlog.m3275i(TAG, "send vel l:" + com.pudutech.base.CommonKt.format(linearSpeed, 3) + " a:" + com.pudutech.base.CommonKt.format(angularSpeed, 3) + " isCloseLoop:" + isCloseLoop);
        CANBus cANBus = canBus;
        if (cANBus == null) {
            Intrinsics.throwUninitializedPropertyAccessException("canBus");
        }
        cANBus.controlWheel(linearSpeed, angularSpeed, isCloseLoop);
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void clearWheelError() {
        CANBus cANBus = canBus;
        if (cANBus == null) {
            Intrinsics.throwUninitializedPropertyAccessException("canBus");
        }
        cANBus.clearWheelError();
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void addCANDataListener(String name, byte[] idList, ICANData listener) {
        if (name == null || listener == null || idList == null) {
            return;
        }
        CANBus cANBus = canBus;
        if (cANBus == null) {
            Intrinsics.throwUninitializedPropertyAccessException("canBus");
        }
        cANBus.getCanListener().add(name, new CANBus.CANListener(idList, listener));
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void removeCANDataListener(String name) {
        if (name != null) {
            CANBus cANBus = canBus;
            if (cANBus == null) {
                Intrinsics.throwUninitializedPropertyAccessException("canBus");
            }
            cANBus.getCanListener().remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void refreshMachineInfo() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new HardwareInterfaceStub$refreshMachineInfo$1(null), 3, null);
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void switchCarpetMode(boolean mode) {
        CarpetModeConfig carpetModeConfig2 = carpetModeConfig;
        if (carpetModeConfig2 != null) {
            if (carpetModeConfig2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("carpetModeConfig");
            }
            carpetModeConfig2.switchMode(mode);
        }
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public boolean getCarpetMode() {
        CarpetModeConfig carpetModeConfig2 = carpetModeConfig;
        if (carpetModeConfig2 == null) {
            return false;
        }
        if (carpetModeConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carpetModeConfig");
        }
        return carpetModeConfig2.getCarpetmode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object updateMCU(Continuation<? super Result> continuation) {
        HardwareInterfaceStub$updateMCU$1 hardwareInterfaceStub$updateMCU$1;
        int i;
        boolean isSuccess;
        String description;
        String str;
        if (continuation instanceof HardwareInterfaceStub$updateMCU$1) {
            hardwareInterfaceStub$updateMCU$1 = (HardwareInterfaceStub$updateMCU$1) continuation;
            if ((hardwareInterfaceStub$updateMCU$1.label & Integer.MIN_VALUE) != 0) {
                hardwareInterfaceStub$updateMCU$1.label -= Integer.MIN_VALUE;
                Object obj = hardwareInterfaceStub$updateMCU$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hardwareInterfaceStub$updateMCU$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Pdlog.m3273d(TAG, "start updateMCU");
                    CANBus cANBus = canBus;
                    if (cANBus == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("canBus");
                    }
                    List<HardwareVersion> hardwareVersions = cANBus.getHardwareVersions();
                    String str2 = "";
                    for (HardwareVersion hardwareVersion : hardwareVersions) {
                        str2 = (((((((str2 + hardwareVersion.getBoard().toString()) + ": ") + String.valueOf(hardwareVersion.getVer0())) + ".") + String.valueOf(hardwareVersion.getVer1())) + ".") + String.valueOf(hardwareVersion.getVer2())) + " ";
                    }
                    Pdlog.m3273d(TAG, "hardware version sets: " + str2);
                    UpdateMCUTask updateMCUTask2 = updateMCUTask;
                    if (updateMCUTask2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("updateMCUTask");
                    }
                    ProductMachineType productType = machineInfoProcess.getMachineInfo().getProductType();
                    CANBus cANBus2 = canBus;
                    if (cANBus2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("canBus");
                    }
                    UpdateMCUTask.Result tryTo = updateMCUTask2.tryTo(productType, cANBus2.getHardwareVersions());
                    isSuccess = tryTo.isSuccess();
                    description = tryTo.getDescription();
                    if (isSuccess) {
                        CANBus cANBus3 = canBus;
                        if (cANBus3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("canBus");
                        }
                        hardwareInterfaceStub$updateMCU$1.L$0 = this;
                        hardwareInterfaceStub$updateMCU$1.L$1 = hardwareVersions;
                        hardwareInterfaceStub$updateMCU$1.L$2 = str2;
                        hardwareInterfaceStub$updateMCU$1.L$3 = tryTo;
                        hardwareInterfaceStub$updateMCU$1.Z$0 = isSuccess;
                        hardwareInterfaceStub$updateMCU$1.L$4 = description;
                        hardwareInterfaceStub$updateMCU$1.label = 1;
                        obj = cANBus3.getHardwareVersion(hardwareInterfaceStub$updateMCU$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        str = description;
                    }
                    return new Result(isSuccess, description);
                }
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                str = (String) hardwareInterfaceStub$updateMCU$1.L$4;
                boolean z = hardwareInterfaceStub$updateMCU$1.Z$0;
                ResultKt.throwOnFailure(obj);
                isSuccess = ((Boolean) obj).booleanValue();
                description = isSuccess ? "mcu update success,but get hardware version error" : str;
                return new Result(isSuccess, description);
            }
        }
        hardwareInterfaceStub$updateMCU$1 = new HardwareInterfaceStub$updateMCU$1(this, continuation);
        Object obj2 = hardwareInterfaceStub$updateMCU$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hardwareInterfaceStub$updateMCU$1.label;
        if (i != 0) {
        }
        isSuccess = ((Boolean) obj2).booleanValue();
        if (isSuccess) {
        }
        return new Result(isSuccess, description);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object fetchMachineInfo(Continuation<? super Result> continuation) {
        HardwareInterfaceStub$fetchMachineInfo$1 hardwareInterfaceStub$fetchMachineInfo$1;
        int i;
        int i2;
        if (continuation instanceof HardwareInterfaceStub$fetchMachineInfo$1) {
            hardwareInterfaceStub$fetchMachineInfo$1 = (HardwareInterfaceStub$fetchMachineInfo$1) continuation;
            if ((hardwareInterfaceStub$fetchMachineInfo$1.label & Integer.MIN_VALUE) != 0) {
                hardwareInterfaceStub$fetchMachineInfo$1.label -= Integer.MIN_VALUE;
                Object obj = hardwareInterfaceStub$fetchMachineInfo$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hardwareInterfaceStub$fetchMachineInfo$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    MachineInfoProcess machineInfoProcess2 = machineInfoProcess;
                    CANBus cANBus = canBus;
                    if (cANBus == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("canBus");
                    }
                    HardwareInterfaceStub$fetchMachineInfo$2 hardwareInterfaceStub$fetchMachineInfo$2 = new HardwareInterfaceStub$fetchMachineInfo$2(cANBus.getCanParserManager());
                    CANBus cANBus2 = canBus;
                    if (cANBus2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("canBus");
                    }
                    HardwareInterfaceStub$fetchMachineInfo$3 hardwareInterfaceStub$fetchMachineInfo$3 = new HardwareInterfaceStub$fetchMachineInfo$3(cANBus2.getCanParserManager());
                    CANBus cANBus3 = canBus;
                    if (cANBus3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("canBus");
                    }
                    HardwareInterfaceStub$fetchMachineInfo$4 hardwareInterfaceStub$fetchMachineInfo$4 = new HardwareInterfaceStub$fetchMachineInfo$4(cANBus3);
                    hardwareInterfaceStub$fetchMachineInfo$1.L$0 = this;
                    hardwareInterfaceStub$fetchMachineInfo$1.label = 1;
                    obj = machineInfoProcess2.fetchFromHardware(hardwareInterfaceStub$fetchMachineInfo$2, hardwareInterfaceStub$fetchMachineInfo$3, hardwareInterfaceStub$fetchMachineInfo$4, hardwareInterfaceStub$fetchMachineInfo$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                i2 = WhenMappings.$EnumSwitchMapping$2[((MachineInfoProcess.FetchResult) obj).ordinal()];
                if (i2 != 1) {
                    return new Result(false, "Getting MachineInfo failed");
                }
                if (i2 == 2) {
                    return new Result(false, "MachineInfo is not set\n");
                }
                return new Result(true, "");
            }
        }
        hardwareInterfaceStub$fetchMachineInfo$1 = new HardwareInterfaceStub$fetchMachineInfo$1(this, continuation);
        Object obj2 = hardwareInterfaceStub$fetchMachineInfo$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hardwareInterfaceStub$fetchMachineInfo$1.label;
        if (i != 0) {
        }
        i2 = WhenMappings.$EnumSwitchMapping$2[((MachineInfoProcess.FetchResult) obj2).ordinal()];
        if (i2 != 1) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0075 A[PHI: r9
      0x0075: PHI (r9v6 java.lang.Object) = (r9v5 java.lang.Object), (r9v1 java.lang.Object) binds: [B:20:0x0072, B:10:0x0029] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0074 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object mcuUpdateInIAPMode(Continuation<? super Result> continuation) {
        HardwareInterfaceStub$mcuUpdateInIAPMode$1 hardwareInterfaceStub$mcuUpdateInIAPMode$1;
        int i;
        HardwareInterfaceStub hardwareInterfaceStub;
        Result result;
        if (continuation instanceof HardwareInterfaceStub$mcuUpdateInIAPMode$1) {
            hardwareInterfaceStub$mcuUpdateInIAPMode$1 = (HardwareInterfaceStub$mcuUpdateInIAPMode$1) continuation;
            if ((hardwareInterfaceStub$mcuUpdateInIAPMode$1.label & Integer.MIN_VALUE) != 0) {
                hardwareInterfaceStub$mcuUpdateInIAPMode$1.label -= Integer.MIN_VALUE;
                Object obj = hardwareInterfaceStub$mcuUpdateInIAPMode$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hardwareInterfaceStub$mcuUpdateInIAPMode$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    hardwareInterfaceStub$mcuUpdateInIAPMode$1.L$0 = this;
                    hardwareInterfaceStub$mcuUpdateInIAPMode$1.label = 1;
                    obj = fetchMachineInfo(hardwareInterfaceStub$mcuUpdateInIAPMode$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    hardwareInterfaceStub = this;
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    hardwareInterfaceStub = (HardwareInterfaceStub) hardwareInterfaceStub$mcuUpdateInIAPMode$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                result = (Result) obj;
                if (!result.isSuccess()) {
                    Pdlog.m3275i(TAG, "fetch machine info fail in IAP mode,machine type uses hls by default");
                }
                hardwareInterfaceStub$mcuUpdateInIAPMode$1.L$0 = hardwareInterfaceStub;
                hardwareInterfaceStub$mcuUpdateInIAPMode$1.L$1 = result;
                hardwareInterfaceStub$mcuUpdateInIAPMode$1.label = 2;
                obj = hardwareInterfaceStub.updateMCU(hardwareInterfaceStub$mcuUpdateInIAPMode$1);
                return obj != coroutine_suspended ? coroutine_suspended : obj;
            }
        }
        hardwareInterfaceStub$mcuUpdateInIAPMode$1 = new HardwareInterfaceStub$mcuUpdateInIAPMode$1(this, continuation);
        Object obj2 = hardwareInterfaceStub$mcuUpdateInIAPMode$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hardwareInterfaceStub$mcuUpdateInIAPMode$1.label;
        if (i != 0) {
        }
        result = (Result) obj2;
        if (!result.isSuccess()) {
        }
        hardwareInterfaceStub$mcuUpdateInIAPMode$1.L$0 = hardwareInterfaceStub;
        hardwareInterfaceStub$mcuUpdateInIAPMode$1.L$1 = result;
        hardwareInterfaceStub$mcuUpdateInIAPMode$1.label = 2;
        obj2 = hardwareInterfaceStub.updateMCU(hardwareInterfaceStub$mcuUpdateInIAPMode$1);
        if (obj2 != coroutine_suspended2) {
        }
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void controlRGBD(boolean on) {
        RGBDInterfaceImpl rGBDInterfaceImpl = rgbdInterfaceImpl;
        if (rGBDInterfaceImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rgbdInterfaceImpl");
        }
        rGBDInterfaceImpl.controlRGBD(on);
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public boolean oilStainCheck() {
        UByte uByte = machineInfoProcess.getMachineInfo().getByte23Data().get(MachineInfo.Byte23Info.RGBDVersion);
        boolean m4535equalsimpl0 = uByte != null ? true ^ UByte.m4535equalsimpl0(uByte.getData(), MachineInfo.RGBDType.NODevice.getId()) : true;
        RGBDInterfaceImpl rGBDInterfaceImpl = rgbdInterfaceImpl;
        if (rGBDInterfaceImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rgbdInterfaceImpl");
        }
        return rGBDInterfaceImpl.oilStainCheck(m4535equalsimpl0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getWIFIMac() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface nif = (NetworkInterface) it.next();
                Intrinsics.checkExpressionValueIsNotNull(nif, "nif");
                if (StringsKt.equals(nif.getName(), "wlan0", true)) {
                    byte[] hardwareAddress = nif.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return ApiConstants.MAC_ADDRESS;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String format = String.format("%02X:", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
                        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                        sb.append(format);
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    String sb2 = sb.toString();
                    Intrinsics.checkExpressionValueIsNotNull(sb2, "mac_str.toString()");
                    return sb2;
                }
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "get mac address fail:" + e.getMessage());
        }
        return ApiConstants.MAC_ADDRESS;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void brake() {
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareInterfaceStub$brake$1(null), 3, null);
    }

    private final void SafetyMonitor() {
        LidarInterfaceImpl lidarInterfaceImpl2 = lidarInterfaceImpl;
        if (lidarInterfaceImpl2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidarInterfaceImpl");
        }
        if (lidarInterfaceImpl2 != null) {
            lidarInterfaceImpl2.addDataListener("SafetyMonitor", new ILidarData.Stub() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$SafetyMonitor$1
                @Override // com.pudutech.mirsdk.hardware.ILidarData
                public void onFrame(PolarCoordinates[] scan_origin, long p1) {
                    double d;
                    PolarCoordinates[] scan_filtered;
                    double d2;
                    AtomicBoolean atomicBoolean;
                    AtomicBoolean atomicBoolean2;
                    double d3;
                    if (scan_origin != null) {
                        HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
                        d = HardwareInterfaceStub.currentLineSpeed;
                        double d4 = 0;
                        if (d > d4) {
                            scan_filtered = HardwareInterfaceStub.INSTANCE.scan_filtered(scan_origin);
                            double max_value = DoubleCompanionObject.INSTANCE.getMAX_VALUE();
                            int length = scan_filtered.length;
                            int i = 0;
                            while (i < length) {
                                PolarCoordinates polarCoordinates = scan_filtered[i];
                                if (polarCoordinates.getDistance_m() > d4) {
                                    double distance_m = polarCoordinates.getDistance_m() * 100;
                                    double angle_rad = polarCoordinates.getAngle_rad();
                                    double sin = Math.sin(polarCoordinates.getAngle_rad());
                                    if (angle_rad <= d4) {
                                        sin = -sin;
                                    }
                                    d3 = d4;
                                    if (sin * distance_m < 20) {
                                        double cos = Math.cos(polarCoordinates.getAngle_rad()) * distance_m;
                                        if (cos < max_value) {
                                            max_value = cos;
                                        }
                                    }
                                } else {
                                    d3 = d4;
                                }
                                i++;
                                d4 = d3;
                            }
                            HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
                            d2 = HardwareInterfaceStub.currentLineSpeed;
                            double d5 = d2 * 100;
                            double d6 = (d5 * d5) / 300;
                            if (max_value < d6) {
                                Pdlog.m3273d("SafetyMonitor", "brake minDistance:" + max_value + " brakingDistance:" + d6);
                                HardwareInterfaceStub hardwareInterfaceStub3 = HardwareInterfaceStub.INSTANCE;
                                atomicBoolean2 = HardwareInterfaceStub.enableBrake;
                                atomicBoolean2.set(true);
                                HardwareInterfaceStub.INSTANCE.brake();
                                return;
                            }
                            HardwareInterfaceStub hardwareInterfaceStub4 = HardwareInterfaceStub.INSTANCE;
                            atomicBoolean = HardwareInterfaceStub.enableBrake;
                            atomicBoolean.set(false);
                        }
                    }
                }
            });
        }
        addSensorListener("SafetyMonitor", new ISensorData.Stub() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$SafetyMonitor$2
            @Override // com.pudutech.mirsdk.hardware.ISensorData
            public void onEncoder(double p0, double p1, double p2) {
            }

            @Override // com.pudutech.mirsdk.hardware.ISensorData
            public void onIMU(double p0, double p1, double p2, double p3) {
            }

            @Override // com.pudutech.mirsdk.hardware.ISensorData
            public void onSpeed(double lineSpeed, double angularSpeed, double interval) {
                HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
                HardwareInterfaceStub.currentLineSpeed = lineSpeed;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PolarCoordinates[] scan_filtered(PolarCoordinates[] scan_origin) {
        int length = scan_origin.length - 3;
        for (int i = 2; i < length; i++) {
            double distance_m = scan_origin[i].getDistance_m();
            if (distance_m < 3.0d && Math.abs(distance_m - scan_origin[i - 2].getDistance_m()) > filter_threshold && Math.abs(distance_m - scan_origin[i - 1].getDistance_m()) > filter_threshold && Math.abs(distance_m - scan_origin[i + 1].getDistance_m()) > filter_threshold && Math.abs(distance_m - scan_origin[i + 2].getDistance_m()) > filter_threshold) {
                scan_origin[i].setDistance_m(0.0d);
            }
        }
        return scan_origin;
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void controlDisinfectionPower(boolean powerOn) {
        CANBus cANBus = canBus;
        if (cANBus == null) {
            Intrinsics.throwUninitializedPropertyAccessException("canBus");
        }
        cANBus.controlDisinfectionPower(powerOn);
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public String getCommitList() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"hardware\":{\"Hardware\":\"commit: 976c077, auth: “songleiquan”<“songleiquan@pudutech.com”>, time: “Fri May 14 10:31:29 2021 -0400”\",");
        sb.append("\"relays\":[");
        sb.append(cameraImpl.getCameraGit());
        sb.append(",");
        RGBDInterfaceImpl rGBDInterfaceImpl = rgbdInterfaceImpl;
        if (rGBDInterfaceImpl != null) {
            if (rGBDInterfaceImpl == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rgbdInterfaceImpl");
            }
            sb.append(rGBDInterfaceImpl.getRGBDGit());
            sb.append(",");
        }
        ScheduleCommunicationImpl scheduleCommunicationImpl = scheduler;
        if (scheduleCommunicationImpl != null) {
            if (scheduleCommunicationImpl == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scheduler");
            }
            sb.append(scheduleCommunicationImpl.getScheduleGit());
            sb.append(",");
        }
        sb.append(AIDLGitHash.INSTANCE.getGitHash());
        sb.append(",");
        sb.append("{\"CANBinary\":\"8c18ea6\"}");
        sb.append("]}}");
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "string.toString()");
        return sb2;
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void setRgbdParkingMode(boolean enable) {
        UByte uByte = machineInfoProcess.getMachineInfo().getByte23Data().get(MachineInfo.Byte23Info.RGBDVersion);
        boolean m4535equalsimpl0 = uByte != null ? true ^ UByte.m4535equalsimpl0(uByte.getData(), MachineInfo.RGBDType.NODevice.getId()) : true;
        RGBDInterfaceImpl rGBDInterfaceImpl = rgbdInterfaceImpl;
        if (rGBDInterfaceImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rgbdInterfaceImpl");
        }
        rGBDInterfaceImpl.setParkingMode(m4535equalsimpl0, enable);
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void addAccelerationListener(String name, IAccelerationData listener) {
        if (name == null || listener == null) {
            return;
        }
        CANBus cANBus = canBus;
        if (cANBus == null) {
            Intrinsics.throwUninitializedPropertyAccessException("canBus");
        }
        cANBus.getAccelerationDataProvider().add(name, listener);
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void removeAccelerationListener(String name) {
        if (name != null) {
            CANBus cANBus = canBus;
            if (cANBus == null) {
                Intrinsics.throwUninitializedPropertyAccessException("canBus");
            }
            cANBus.getAccelerationDataProvider().remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void getAccelerationData(AccelerationType type) {
        Pdlog.m3275i(TAG, "get acc type:" + type);
        if (type != null) {
            CANBus cANBus = canBus;
            if (cANBus == null) {
                Intrinsics.throwUninitializedPropertyAccessException("canBus");
            }
            cANBus.getAccelerationData(type);
        }
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void setAccelerationData(AccelerationType type, double data) {
        Pdlog.m3275i(TAG, "set acc type:" + type + " ,data: " + data);
        if (type != null) {
            CANBus cANBus = canBus;
            if (cANBus == null) {
                Intrinsics.throwUninitializedPropertyAccessException("canBus");
            }
            cANBus.setAccelerationData(type, data);
        }
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public UsbControlInterface getUsbControlInterface() {
        return mUsbController;
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void controlDevice(DeviceType type, boolean powerOn) {
        if (type == null) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$3[type.ordinal()];
        if (i == 1) {
            if (powerOn) {
                return;
            }
            ScheduleCommunicationImpl scheduleCommunicationImpl = scheduler;
            if (scheduleCommunicationImpl == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scheduler");
            }
            scheduleCommunicationImpl.close();
            return;
        }
        if (i == 2) {
            controlRGBD(powerOn);
        } else if (i == 3) {
            controlDisinfectionPower(powerOn);
        } else {
            if (i != 4) {
                return;
            }
            controlCameraIRDLED(powerOn);
        }
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void suspendChargingUsePile() {
        CANBus cANBus = canBus;
        if (cANBus == null) {
            Intrinsics.throwUninitializedPropertyAccessException("canBus");
        }
        cANBus.suspendUsingCharingPile();
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public synchronized void openCANEx(List<DeviceType> deviceTypes) {
        Job launch$default;
        Pdlog.m3275i(TAG, "openCANEx");
        if (openJob == null) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareInterfaceStub$openCANEx$1(deviceTypes, null), 3, null);
            openJob = launch$default;
        } else {
            Pdlog.m3277w(TAG, "hardware is opening, please wait first task finish");
        }
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public String getLastError(List<DeviceType> deviceTypes) {
        String str;
        Intrinsics.checkParameterIsNotNull(deviceTypes, "deviceTypes");
        String str2 = "";
        for (DeviceType deviceType : deviceTypes) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            int i = WhenMappings.$EnumSwitchMapping$5[deviceType.ordinal()];
            if (i == 1) {
                RGBDInterfaceImpl rGBDInterfaceImpl = rgbdInterfaceImpl;
                if (rGBDInterfaceImpl == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rgbdInterfaceImpl");
                }
                str = rGBDInterfaceImpl.getLastError() + "\n";
            } else if (i == 2) {
                str = cameraImpl.getLastRunningErrorMsg() + "\n";
            } else if (i != 3) {
                str = "";
            } else {
                LidarInterfaceImpl lidarInterfaceImpl2 = lidarInterfaceImpl;
                if (lidarInterfaceImpl2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("lidarInterfaceImpl");
                }
                str = lidarInterfaceImpl2.getMLastErrorMsg() + "\n";
            }
            sb.append(str);
            str2 = sb.toString();
        }
        return str2;
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterface
    public void controlSlamCorePower(boolean powerOn) {
        CANBus cANBus = canBus;
        if (cANBus == null) {
            Intrinsics.throwUninitializedPropertyAccessException("canBus");
        }
        cANBus.m4425sendGBYM_sE(new byte[]{-123, powerOn ? (byte) 1 : (byte) 0, 0, 0, 0, 0});
    }
}
