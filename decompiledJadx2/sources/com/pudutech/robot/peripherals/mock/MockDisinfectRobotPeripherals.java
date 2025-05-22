package com.pudutech.robot.peripherals.mock;

import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.iflytek.speech.UtilityConfig;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.peripherals.common.CommonRobotPeripherals;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.disinfection.BatteryChargeError;
import com.pudutech.robot.peripherals.disinfection.DeviceName;
import com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.disinfection.OpenState;
import com.pudutech.robot.peripherals.disinfection.SprayDeviceError;
import com.pudutech.robot.peripherals.disinfection.UvcLampDeviceError;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MockDisinfectRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \u0083\u00012\u00020\u00012\u00020\u0002:\u0002\u0083\u0001B\u0005¢\u0006\u0002\u0010\u0003J-\u0010\u001d\u001a\u00020\f2#\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006H\u0016J7\u0010\u001f\u001a\u00020\f2-\u0010\u000b\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\f0\u0006j\u0002`!\u0018\u00010\u0005H\u0016J3\u0010\"\u001a\u00020\f2)\u0010\u000b\u001a%\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020#0\u0007¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006H\u0016JI\u0010%\u001a\u00020\f2?\u0010\u000b\u001a;\u0012/\u0012-\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u00130&j\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u0013`(¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006H\u0016J7\u0010*\u001a\u00020\f2-\u0010\u000b\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\f0\u0006j\u0002`+\u0018\u00010\u0005H\u0016J-\u0010,\u001a\u00020\f2#\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006H\u0016J-\u0010.\u001a\u00020\f2#\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110'¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006H\u0016J-\u0010/\u001a\u00020\f2#\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006H\u0016J7\u00101\u001a\u00020\f2-\u0010\u000b\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\u0015\u0018\u00010\u0005H\u0016JB\u00102\u001a\u00020\f28\u0010\u000b\u001a4\u0012\u0013\u0012\u001104¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(5\u0012\u0013\u0012\u001104¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\f\u0018\u000103H\u0016J?\u00107\u001a\u00020\f25\u0010\u000b\u001a1\u0012+\u0012)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r\u0018\u00010\u0005H\u0016J-\u00108\u001a\u00020\f2#\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006H\u0016J-\u0010:\u001a\u00020\f2#\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006H\u0016J-\u0010;\u001a\u00020\f2#\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110<¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(=\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006H\u0016J-\u0010>\u001a\u00020\f2#\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110<¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(=\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006H\u0016J?\u0010?\u001a\u00020\f25\u0010\u000b\u001a1\u0012+\u0012)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0007¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\u001a\u0018\u00010\u0005H\u0016J-\u0010@\u001a\u00020\f2#\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006H\u0016J7\u0010A\u001a\u00020\f2-\u0010\u000b\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110B¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(C\u0012\u0004\u0012\u00020\f0\u0006j\u0002`D\u0018\u00010\u0005H\u0016J7\u0010E\u001a\u00020\f2-\u0010\u000b\u001a)\u0012#\u0012!\u0012\u0013\u0012\u001104¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006j\u0002`F\u0018\u00010\u0005H\u0016J\b\u0010G\u001a\u00020\fH\u0016J\b\u0010H\u001a\u00020\fH\u0016J\u0018\u0010I\u001a\u00020\f2\u0006\u0010J\u001a\u00020<2\u0006\u0010K\u001a\u00020<H\u0016J!\u0010L\u001a\u00020\f2\u0012\u0010M\u001a\n\u0012\u0006\b\u0001\u0012\u00020N0\u0007\"\u00020NH\u0016¢\u0006\u0002\u0010OJ\b\u0010P\u001a\u00020\fH\u0016J\u001a\u0010Q\u001a\u00020\f2\u0006\u0010R\u001a\u00020SH\u0017ø\u0001\u0000¢\u0006\u0004\bT\u0010UJ\b\u0010V\u001a\u00020\fH\u0016J\u0010\u0010W\u001a\u00020XH\u0016ø\u0001\u0000¢\u0006\u0002\u0010YJ\u0010\u0010Z\u001a\u00020\f2\u0006\u00109\u001a\u00020\u0013H\u0016J\u0018\u0010[\u001a\u00020\f2\u0006\u00109\u001a\u00020\u00132\u0006\u0010\\\u001a\u00020\u0013H\u0016J\u0018\u0010]\u001a\u00020\f2\u0006\u00109\u001a\u00020\u00132\u0006\u0010^\u001a\u00020\u0013H\u0016J\b\u0010_\u001a\u00020\fH\u0016J\b\u0010`\u001a\u00020\fH\u0016J\b\u0010a\u001a\u00020\fH\u0016J\b\u0010b\u001a\u00020\fH\u0016J\b\u0010c\u001a\u00020\fH\u0016J\b\u0010d\u001a\u00020\fH\u0016J\b\u0010e\u001a\u00020\fH\u0016J\b\u0010f\u001a\u00020\fH\u0016J\b\u0010g\u001a\u00020\fH\u0016J\b\u0010h\u001a\u00020\fH\u0016J\b\u0010i\u001a\u00020\fH\u0016J\b\u0010j\u001a\u00020\fH\u0016J\b\u0010k\u001a\u00020\fH\u0016J\b\u0010l\u001a\u00020\fH\u0016J\b\u0010m\u001a\u00020\fH\u0016J\b\u0010n\u001a\u00020\fH\u0016J\b\u0010o\u001a\u00020\fH\u0016J\b\u0010p\u001a\u00020\fH\u0016J\b\u0010q\u001a\u00020\fH\u0016J\b\u0010r\u001a\u00020\fH\u0016J\b\u0010s\u001a\u00020\fH\u0016J\u001a\u0010t\u001a\u00020\f2\u0006\u0010R\u001a\u00020SH\u0016ø\u0001\u0000¢\u0006\u0004\bu\u0010UJ\b\u0010v\u001a\u00020\fH\u0016J2\u0010w\u001a\u00020\f2\u0006\u0010x\u001a\u00020y2\u0006\u0010z\u001a\u00020S2\u0006\u0010{\u001a\u00020S2\u0006\u0010|\u001a\u00020SH\u0016ø\u0001\u0000¢\u0006\u0004\b}\u0010~J\u0011\u0010\u007f\u001a\u00020\f2\u0007\u0010\u0080\u0001\u001a\u00020<H\u0016J\u0012\u0010\u0081\u0001\u001a\u00020\f2\u0007\u0010\u0082\u0001\u001a\u00020<H\u0016RI\u0010\u0004\u001a1\u0012+\u0012)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011RA\u0010\u0012\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\u0015\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011RI\u0010\u0018\u001a1\u0012+\u0012)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0007¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\u001a\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000f\"\u0004\b\u001c\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0084\u0001"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/mock/MockDisinfectRobotPeripherals;", "Lcom/pudutech/robot/peripherals/common/CommonRobotPeripherals;", "Lcom/pudutech/robot/peripherals/disinfection/IDisinfectRobotPeripherals;", "()V", "onSprayDeviceErrorListener", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlin/Function1;", "", "Lcom/pudutech/robot/peripherals/disinfection/SprayDeviceError;", "Lkotlin/ParameterName;", "name", "l", "", "Lcom/pudutech/robot/peripherals/disinfection/device/OnSprayDeviceErrorListener;", "getOnSprayDeviceErrorListener", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "setOnSprayDeviceErrorListener", "(Ljava/util/concurrent/CopyOnWriteArrayList;)V", "onSprayLiquidLevelStatusChangeListeners", "", "b", "Lcom/pudutech/robot/peripherals/disinfection/device/OnSprayLiquidLevelStatusChangeListener;", "getOnSprayLiquidLevelStatusChangeListeners", "setOnSprayLiquidLevelStatusChangeListeners", "onUvDeviceErrorListener", "Lcom/pudutech/robot/peripherals/disinfection/UvcLampDeviceError;", "Lcom/pudutech/robot/peripherals/disinfection/device/OnUvLampDeviceErrorChangeListener;", "getOnUvDeviceErrorListener", "setOnUvDeviceErrorListener", "addBatteryBoxOpenStatus", "p0", "addBatteryBoxOpenStatusListener", "arg", "Lcom/pudutech/robot/peripherals/disinfection/device/OnBatteryBoxOpenStatusListener;", "addBatteryChargeErrorListener", "Lcom/pudutech/robot/peripherals/disinfection/BatteryChargeError;", "list", "addBatteryCommunicateErrorListener", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "batteryError", "addHumanSensorDetectSignalListener", "Lcom/pudutech/robot/peripherals/disinfection/device/OnHumanSensorDetectSignalListener;", "addMagneticConfigStatusListener", "success", "addMagneticTypeListener", "addModuleOpenStatusListener", "openStatus", "addOnSprayLiquidLevelStatusListener", "addSprayDeviceLiquidLevelListener", "Lkotlin/Function2;", "", "waterBoxLevel", "spayBoxLevel", "addSprayDeviceOccurErrorListener", "addSprayDeviceOpenListener", "open", "addSpringOpenStatusListener", "addTheFirstPowerListener", "", "battery", "addTheZeroPowerListener", "addUvcLampDeviceOccurErrorListener", "addUvcLampDeviceOpenListener", "addUvcLampDevicePlateOpenStateListener", "Lcom/pudutech/robot/peripherals/disinfection/OpenState;", "state", "Lcom/pudutech/robot/peripherals/disinfection/device/OnUvLampPlateOpenStatusListener;", "addWaterBoxLiquidLevelListener", "Lcom/pudutech/robot/peripherals/disinfection/device/OnLiquidLevelChangeListener;", "backFlowSprayLiquid", "bootModule", "calibrationSpray", "water", "fog", "closeDevice", UtilityConfig.KEY_DEVICE_INFO, "Lcom/pudutech/robot/peripherals/disinfection/DeviceName;", "([Lcom/pudutech/robot/peripherals/disinfection/DeviceName;)V", "closeModule", "configMagnetic", "byte", "Lkotlin/UByte;", "configMagnetic-7apg3OU", "(B)V", "getMagneticType", "getRecvCmds", "Lkotlin/UByteArray;", "()[B", "openHumanSensorDevice", "openSprayDevice", "spray", "openUvcLampDevice", "openUvc", "removeAndClearSprayDeviceErrorListener", "removeAndClearSprayLiquidLevelStatusListener", "removeAndClearUvcLampDeviceErrorListener", "removeAndClearUvcLampDevicePlateOpenStateListener", "removeBatteryBoxOpenStatusListener", "removeBatteryBoxOpenStatusListeners", "removeBatteryChargeErrorListener", "removeBatteryCommunicateErrorListener", "removeHumanSensorDetectSignalListener", "removeMagneticConfigStatusListener", "removeMagneticTypeListener", "removeModuleOpenStatusListener", "removeSprayDeviceLiquidLevelListener", "removeSprayDeviceOpenListener", "removeSpringOpenStatusListener", "removeTheFirstPowerListener", "removeTheZeroPowerListener", "removeUvcLampDeviceOpenListener", "removeWaterBoxLiquidLevelListener", "resetCustomCore", "resetSlamCore", "selectSpringType", "selectSpringType-7apg3OU", "sendQueryHumanSensorSignalCmd", "setLight", "led", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", "red", "green", "blue", "setLight-Fh2MPcY", "(Lcom/pudutech/robot/peripherals/config/LightBeltType;BBB)V", "setMotorAngle", LinkFormat.DOMAIN, "setSpraySpeedRate", "rate", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MockDisinfectRobotPeripherals extends CommonRobotPeripherals implements IDisinfectRobotPeripherals {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<MockDisinfectRobotPeripherals>() { // from class: com.pudutech.robot.peripherals.mock.MockDisinfectRobotPeripherals$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MockDisinfectRobotPeripherals invoke() {
            return new MockDisinfectRobotPeripherals();
        }
    });
    private static final String TAG = "MockDisinfectRobotPeripherals";
    private CopyOnWriteArrayList<Function1<SprayDeviceError[], Unit>> onSprayDeviceErrorListener;
    private CopyOnWriteArrayList<Function1<Boolean, Unit>> onSprayLiquidLevelStatusChangeListeners;
    private CopyOnWriteArrayList<Function1<UvcLampDeviceError[], Unit>> onUvDeviceErrorListener;

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addBatteryBoxOpenStatus(Function1<? super Boolean, Unit> l) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addBatteryBoxOpenStatusListener(CopyOnWriteArrayList<Function1<Boolean, Unit>> l) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addBatteryChargeErrorListener(Function1<? super BatteryChargeError[], Unit> l) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addBatteryCommunicateErrorListener(Function1<? super HashMap<String, Boolean>, Unit> l) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addHumanSensorDetectSignalListener(CopyOnWriteArrayList<Function1<Boolean, Unit>> l) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addMagneticConfigStatusListener(Function1<? super Boolean, Unit> l) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addMagneticTypeListener(Function1<? super String, Unit> l) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addModuleOpenStatusListener(Function1<? super Boolean, Unit> l) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addSprayDeviceLiquidLevelListener(Function2<? super Double, ? super Double, Unit> l) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addSprayDeviceOpenListener(Function1<? super Boolean, Unit> l) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addSpringOpenStatusListener(Function1<? super Boolean, Unit> l) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addTheFirstPowerListener(Function1<? super Integer, Unit> l) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addTheZeroPowerListener(Function1<? super Integer, Unit> l) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addUvcLampDeviceOpenListener(Function1<? super Boolean, Unit> l) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addUvcLampDevicePlateOpenStateListener(CopyOnWriteArrayList<Function1<OpenState, Unit>> l) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addWaterBoxLiquidLevelListener(CopyOnWriteArrayList<Function1<Double, Unit>> l) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void backFlowSprayLiquid() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void bootModule() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void calibrationSpray(int water, int fog) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void closeModule() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    /* renamed from: configMagnetic-7apg3OU */
    public void mo4483configMagnetic7apg3OU(byte r1) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void getMagneticType() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void openHumanSensorDevice(boolean open) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void openSprayDevice(boolean open, boolean spray) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void openUvcLampDevice(boolean open, boolean openUvc) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeAndClearSprayDeviceErrorListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeAndClearSprayLiquidLevelStatusListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeAndClearUvcLampDeviceErrorListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeAndClearUvcLampDevicePlateOpenStateListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeBatteryBoxOpenStatusListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeBatteryBoxOpenStatusListeners() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeBatteryChargeErrorListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeBatteryCommunicateErrorListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeHumanSensorDetectSignalListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeMagneticConfigStatusListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeMagneticTypeListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeModuleOpenStatusListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeSprayDeviceLiquidLevelListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeSprayDeviceOpenListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeSpringOpenStatusListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeTheFirstPowerListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeTheZeroPowerListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeUvcLampDeviceOpenListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeWaterBoxLiquidLevelListener() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void resetCustomCore() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void resetSlamCore() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    /* renamed from: selectSpringType-7apg3OU */
    public void mo4484selectSpringType7apg3OU(byte r1) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void sendQueryHumanSensorSignalCmd() {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    /* renamed from: setLight-Fh2MPcY */
    public void mo4485setLightFh2MPcY(LightBeltType led, byte red, byte green, byte blue) {
        Intrinsics.checkParameterIsNotNull(led, "led");
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void setMotorAngle(int d) {
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void setSpraySpeedRate(int rate) {
    }

    /* compiled from: MockDisinfectRobotPeripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/mock/MockDisinfectRobotPeripherals$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/robot/peripherals/mock/MockDisinfectRobotPeripherals;", "getINSTANCE", "()Lcom/pudutech/robot/peripherals/mock/MockDisinfectRobotPeripherals;", "INSTANCE$delegate", "Lkotlin/Lazy;", "TAG", "", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        public final MockDisinfectRobotPeripherals getINSTANCE() {
            Lazy lazy = MockDisinfectRobotPeripherals.INSTANCE$delegate;
            Companion companion = MockDisinfectRobotPeripherals.INSTANCE;
            return (MockDisinfectRobotPeripherals) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final CopyOnWriteArrayList<Function1<SprayDeviceError[], Unit>> getOnSprayDeviceErrorListener() {
        return this.onSprayDeviceErrorListener;
    }

    public final void setOnSprayDeviceErrorListener(CopyOnWriteArrayList<Function1<SprayDeviceError[], Unit>> copyOnWriteArrayList) {
        this.onSprayDeviceErrorListener = copyOnWriteArrayList;
    }

    public final CopyOnWriteArrayList<Function1<UvcLampDeviceError[], Unit>> getOnUvDeviceErrorListener() {
        return this.onUvDeviceErrorListener;
    }

    public final void setOnUvDeviceErrorListener(CopyOnWriteArrayList<Function1<UvcLampDeviceError[], Unit>> copyOnWriteArrayList) {
        this.onUvDeviceErrorListener = copyOnWriteArrayList;
    }

    public final CopyOnWriteArrayList<Function1<Boolean, Unit>> getOnSprayLiquidLevelStatusChangeListeners() {
        return this.onSprayLiquidLevelStatusChangeListeners;
    }

    public final void setOnSprayLiquidLevelStatusChangeListeners(CopyOnWriteArrayList<Function1<Boolean, Unit>> copyOnWriteArrayList) {
        this.onSprayLiquidLevelStatusChangeListeners = copyOnWriteArrayList;
    }

    @Override // com.pudutech.robot.peripherals.common.CommonRobotPeripherals
    public byte[] getRecvCmds() {
        return UByteArray.m4571constructorimpl(0);
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void closeDevice(DeviceName... device) {
        Intrinsics.checkParameterIsNotNull(device, "device");
        Pdlog.m3273d(TAG, "close Device " + device);
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addUvcLampDeviceOccurErrorListener(CopyOnWriteArrayList<Function1<UvcLampDeviceError[], Unit>> l) {
        this.onUvDeviceErrorListener = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addOnSprayLiquidLevelStatusListener(CopyOnWriteArrayList<Function1<Boolean, Unit>> l) {
        this.onSprayLiquidLevelStatusChangeListeners = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addSprayDeviceOccurErrorListener(CopyOnWriteArrayList<Function1<SprayDeviceError[], Unit>> l) {
        this.onSprayDeviceErrorListener = l;
    }
}
