package com.pudutech.robot.peripherals.disinfection;

import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.iflytek.speech.UtilityConfig;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.interf.IRobotPeripherals;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: IDisinfectRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J-\u0010\u0002\u001a\u00020\u00032#\u0010\u0004\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H&J7\u0010\n\u001a\u00020\u00032-\u0010\u0004\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\r\u0018\u00010\u000bH&J3\u0010\u000e\u001a\u00020\u00032)\u0010\u0004\u001a%\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H&JI\u0010\u0012\u001a\u00020\u00032?\u0010\u0004\u001a;\u0012/\u0012-\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00060\u0013j\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0006`\u0015¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H&J7\u0010\u0017\u001a\u00020\u00032-\u0010\u0004\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u0018\u0018\u00010\u000bH&J-\u0010\u0019\u001a\u00020\u00032#\u0010\u0004\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H&J-\u0010\u001b\u001a\u00020\u00032#\u0010\u0004\u001a\u001f\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H&J-\u0010\u001c\u001a\u00020\u00032#\u0010\u0004\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H&J7\u0010\u001e\u001a\u00020\u00032-\u0010\u0004\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002` \u0018\u00010\u000bH&JB\u0010!\u001a\u00020\u000328\u0010\u0004\u001a4\u0012\u0013\u0012\u00110#¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b($\u0012\u0013\u0012\u00110#¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u0003\u0018\u00010\"H&J?\u0010&\u001a\u00020\u000325\u0010\u0004\u001a1\u0012+\u0012)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020'\u0018\u00010\u000f¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`(\u0018\u00010\u000bH&J-\u0010)\u001a\u00020\u00032#\u0010\u0004\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H&J-\u0010+\u001a\u00020\u00032#\u0010\u0004\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H&J-\u0010,\u001a\u00020\u00032#\u0010\u0004\u001a\u001f\u0012\u0013\u0012\u00110-¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H&J-\u0010/\u001a\u00020\u00032#\u0010\u0004\u001a\u001f\u0012\u0013\u0012\u00110-¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H&J?\u00100\u001a\u00020\u000325\u0010\u0004\u001a1\u0012+\u0012)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u000201\u0018\u00010\u000f¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`2\u0018\u00010\u000bH&J-\u00103\u001a\u00020\u00032#\u0010\u0004\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H&J7\u00104\u001a\u00020\u00032-\u0010\u0004\u001a)\u0012#\u0012!\u0012\u0013\u0012\u001105¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`7\u0018\u00010\u000bH&J7\u00108\u001a\u00020\u00032-\u0010\u0004\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110#¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`9\u0018\u00010\u000bH&J\b\u0010:\u001a\u00020\u0003H&J\b\u0010;\u001a\u00020\u0003H&J\u0018\u0010<\u001a\u00020\u00032\u0006\u0010=\u001a\u00020-2\u0006\u0010>\u001a\u00020-H&J!\u0010?\u001a\u00020\u00032\u0012\u0010@\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u000f\"\u00020AH&¢\u0006\u0002\u0010BJ\b\u0010C\u001a\u00020\u0003H&J\u001a\u0010D\u001a\u00020\u00032\u0006\u0010E\u001a\u00020FH'ø\u0001\u0000¢\u0006\u0004\bG\u0010HJ\b\u0010I\u001a\u00020\u0003H&J\u0010\u0010J\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u0006H&J\u001a\u0010K\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u00062\b\b\u0002\u0010L\u001a\u00020\u0006H&J\u0018\u0010M\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u0006H&J\b\u0010O\u001a\u00020\u0003H&J\b\u0010P\u001a\u00020\u0003H&J\b\u0010Q\u001a\u00020\u0003H&J\b\u0010R\u001a\u00020\u0003H&J\b\u0010S\u001a\u00020\u0003H&J\b\u0010T\u001a\u00020\u0003H&J\b\u0010U\u001a\u00020\u0003H&J\b\u0010V\u001a\u00020\u0003H&J\b\u0010W\u001a\u00020\u0003H&J\b\u0010X\u001a\u00020\u0003H&J\b\u0010Y\u001a\u00020\u0003H&J\b\u0010Z\u001a\u00020\u0003H&J\b\u0010[\u001a\u00020\u0003H&J\b\u0010\\\u001a\u00020\u0003H&J\b\u0010]\u001a\u00020\u0003H&J\b\u0010^\u001a\u00020\u0003H&J\b\u0010_\u001a\u00020\u0003H&J\b\u0010`\u001a\u00020\u0003H&J\b\u0010a\u001a\u00020\u0003H&J\b\u0010b\u001a\u00020\u0003H&J\b\u0010c\u001a\u00020\u0003H&J\u001a\u0010d\u001a\u00020\u00032\u0006\u0010E\u001a\u00020FH&ø\u0001\u0000¢\u0006\u0004\be\u0010HJ\b\u0010f\u001a\u00020\u0003H&J8\u0010g\u001a\u00020\u00032\u0006\u0010h\u001a\u00020i2\b\b\u0002\u0010j\u001a\u00020F2\b\b\u0002\u0010k\u001a\u00020F2\b\b\u0002\u0010l\u001a\u00020FH&ø\u0001\u0000¢\u0006\u0004\bm\u0010nJ\u0010\u0010o\u001a\u00020\u00032\u0006\u0010p\u001a\u00020-H&J\u0010\u0010q\u001a\u00020\u00032\u0006\u0010r\u001a\u00020-H&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006s"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/disinfection/IDisinfectRobotPeripherals;", "Lcom/pudutech/robot/peripherals/interf/IRobotPeripherals;", "addBatteryBoxOpenStatus", "", "l", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "p0", "addBatteryBoxOpenStatusListener", "Ljava/util/concurrent/CopyOnWriteArrayList;", "arg", "Lcom/pudutech/robot/peripherals/disinfection/device/OnBatteryBoxOpenStatusListener;", "addBatteryChargeErrorListener", "", "Lcom/pudutech/robot/peripherals/disinfection/BatteryChargeError;", "list", "addBatteryCommunicateErrorListener", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "batteryError", "addHumanSensorDetectSignalListener", "Lcom/pudutech/robot/peripherals/disinfection/device/OnHumanSensorDetectSignalListener;", "addMagneticConfigStatusListener", "success", "addMagneticTypeListener", "addModuleOpenStatusListener", "openStatus", "addOnSprayLiquidLevelStatusListener", "b", "Lcom/pudutech/robot/peripherals/disinfection/device/OnSprayLiquidLevelStatusChangeListener;", "addSprayDeviceLiquidLevelListener", "Lkotlin/Function2;", "", "waterBoxLevel", "spayBoxLevel", "addSprayDeviceOccurErrorListener", "Lcom/pudutech/robot/peripherals/disinfection/SprayDeviceError;", "Lcom/pudutech/robot/peripherals/disinfection/device/OnSprayDeviceErrorListener;", "addSprayDeviceOpenListener", "open", "addSpringOpenStatusListener", "addTheFirstPowerListener", "", "battery", "addTheZeroPowerListener", "addUvcLampDeviceOccurErrorListener", "Lcom/pudutech/robot/peripherals/disinfection/UvcLampDeviceError;", "Lcom/pudutech/robot/peripherals/disinfection/device/OnUvLampDeviceErrorChangeListener;", "addUvcLampDeviceOpenListener", "addUvcLampDevicePlateOpenStateListener", "Lcom/pudutech/robot/peripherals/disinfection/OpenState;", "state", "Lcom/pudutech/robot/peripherals/disinfection/device/OnUvLampPlateOpenStatusListener;", "addWaterBoxLiquidLevelListener", "Lcom/pudutech/robot/peripherals/disinfection/device/OnLiquidLevelChangeListener;", "backFlowSprayLiquid", "bootModule", "calibrationSpray", "water", "fog", "closeDevice", UtilityConfig.KEY_DEVICE_INFO, "Lcom/pudutech/robot/peripherals/disinfection/DeviceName;", "([Lcom/pudutech/robot/peripherals/disinfection/DeviceName;)V", "closeModule", "configMagnetic", "byte", "Lkotlin/UByte;", "configMagnetic-7apg3OU", "(B)V", "getMagneticType", "openHumanSensorDevice", "openSprayDevice", "spray", "openUvcLampDevice", "openUvc", "removeAndClearSprayDeviceErrorListener", "removeAndClearSprayLiquidLevelStatusListener", "removeAndClearUvcLampDeviceErrorListener", "removeAndClearUvcLampDevicePlateOpenStateListener", "removeBatteryBoxOpenStatusListener", "removeBatteryBoxOpenStatusListeners", "removeBatteryChargeErrorListener", "removeBatteryCommunicateErrorListener", "removeHumanSensorDetectSignalListener", "removeMagneticConfigStatusListener", "removeMagneticTypeListener", "removeModuleOpenStatusListener", "removeSprayDeviceLiquidLevelListener", "removeSprayDeviceOpenListener", "removeSpringOpenStatusListener", "removeTheFirstPowerListener", "removeTheZeroPowerListener", "removeUvcLampDeviceOpenListener", "removeWaterBoxLiquidLevelListener", "resetCustomCore", "resetSlamCore", "selectSpringType", "selectSpringType-7apg3OU", "sendQueryHumanSensorSignalCmd", "setLight", "led", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", "red", "green", "blue", "setLight-Fh2MPcY", "(Lcom/pudutech/robot/peripherals/config/LightBeltType;BBB)V", "setMotorAngle", LinkFormat.DOMAIN, "setSpraySpeedRate", "rate", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IDisinfectRobotPeripherals extends IRobotPeripherals {
    void addBatteryBoxOpenStatus(Function1<? super Boolean, Unit> l);

    void addBatteryBoxOpenStatusListener(CopyOnWriteArrayList<Function1<Boolean, Unit>> l);

    void addBatteryChargeErrorListener(Function1<? super BatteryChargeError[], Unit> l);

    void addBatteryCommunicateErrorListener(Function1<? super HashMap<String, Boolean>, Unit> l);

    void addHumanSensorDetectSignalListener(CopyOnWriteArrayList<Function1<Boolean, Unit>> l);

    void addMagneticConfigStatusListener(Function1<? super Boolean, Unit> l);

    void addMagneticTypeListener(Function1<? super String, Unit> l);

    void addModuleOpenStatusListener(Function1<? super Boolean, Unit> l);

    void addOnSprayLiquidLevelStatusListener(CopyOnWriteArrayList<Function1<Boolean, Unit>> l);

    void addSprayDeviceLiquidLevelListener(Function2<? super Double, ? super Double, Unit> l);

    void addSprayDeviceOccurErrorListener(CopyOnWriteArrayList<Function1<SprayDeviceError[], Unit>> l);

    void addSprayDeviceOpenListener(Function1<? super Boolean, Unit> l);

    void addSpringOpenStatusListener(Function1<? super Boolean, Unit> l);

    void addTheFirstPowerListener(Function1<? super Integer, Unit> l);

    void addTheZeroPowerListener(Function1<? super Integer, Unit> l);

    void addUvcLampDeviceOccurErrorListener(CopyOnWriteArrayList<Function1<UvcLampDeviceError[], Unit>> l);

    void addUvcLampDeviceOpenListener(Function1<? super Boolean, Unit> l);

    void addUvcLampDevicePlateOpenStateListener(CopyOnWriteArrayList<Function1<OpenState, Unit>> l);

    void addWaterBoxLiquidLevelListener(CopyOnWriteArrayList<Function1<Double, Unit>> l);

    void backFlowSprayLiquid();

    void bootModule();

    void calibrationSpray(int water, int fog);

    void closeDevice(DeviceName... device);

    void closeModule();

    /* renamed from: configMagnetic-7apg3OU */
    void mo4483configMagnetic7apg3OU(byte r1);

    void getMagneticType();

    void openHumanSensorDevice(boolean open);

    void openSprayDevice(boolean open, boolean spray);

    void openUvcLampDevice(boolean open, boolean openUvc);

    void removeAndClearSprayDeviceErrorListener();

    void removeAndClearSprayLiquidLevelStatusListener();

    void removeAndClearUvcLampDeviceErrorListener();

    void removeAndClearUvcLampDevicePlateOpenStateListener();

    void removeBatteryBoxOpenStatusListener();

    void removeBatteryBoxOpenStatusListeners();

    void removeBatteryChargeErrorListener();

    void removeBatteryCommunicateErrorListener();

    void removeHumanSensorDetectSignalListener();

    void removeMagneticConfigStatusListener();

    void removeMagneticTypeListener();

    void removeModuleOpenStatusListener();

    void removeSprayDeviceLiquidLevelListener();

    void removeSprayDeviceOpenListener();

    void removeSpringOpenStatusListener();

    void removeTheFirstPowerListener();

    void removeTheZeroPowerListener();

    void removeUvcLampDeviceOpenListener();

    void removeWaterBoxLiquidLevelListener();

    void resetCustomCore();

    void resetSlamCore();

    /* renamed from: selectSpringType-7apg3OU */
    void mo4484selectSpringType7apg3OU(byte r1);

    void sendQueryHumanSensorSignalCmd();

    /* renamed from: setLight-Fh2MPcY */
    void mo4485setLightFh2MPcY(LightBeltType led, byte red, byte green, byte blue);

    void setMotorAngle(int d);

    void setSpraySpeedRate(int rate);

    /* compiled from: IDisinfectRobotPeripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void openSprayDevice$default(IDisinfectRobotPeripherals iDisinfectRobotPeripherals, boolean z, boolean z2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: openSprayDevice");
            }
            if ((i & 2) != 0) {
                z2 = true;
            }
            iDisinfectRobotPeripherals.openSprayDevice(z, z2);
        }

        /* renamed from: setLight-Fh2MPcY$default, reason: not valid java name */
        public static /* synthetic */ void m4486setLightFh2MPcY$default(IDisinfectRobotPeripherals iDisinfectRobotPeripherals, LightBeltType lightBeltType, byte b, byte b2, byte b3, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setLight");
            }
            if ((i & 2) != 0) {
                b = 0;
            }
            if ((i & 4) != 0) {
                b2 = 0;
            }
            if ((i & 8) != 0) {
                b3 = 0;
            }
            iDisinfectRobotPeripherals.mo4485setLightFh2MPcY(lightBeltType, b, b2, b3);
        }
    }
}
