package com.pudutech.robot.peripherals.disinfection.device;

import com.iflytek.speech.UtilityConfig;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.disinfection.DeviceName;
import com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.disinfection.OpenState;
import com.pudutech.robot.peripherals.disinfection.SprayDeviceError;
import com.pudutech.robot.peripherals.disinfection.UvcLampDeviceError;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: DisinfectDeviceHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u0000 ~2\u00020\u0001:\u0001~B\u0005¢\u0006\u0002\u0010\u0002J-\u0010/\u001a\u00020\n2%\u0010\u0015\u001a!\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000bJ+\u00100\u001a\u00020\n2#\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005J+\u00101\u001a\u00020\n2#\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005J+\u00103\u001a\u00020\n2#\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005J+\u00104\u001a\u00020\n2#\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(5\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005J9\u00106\u001a\u00020\n21\u0010\u0015\u001a-\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00060\r¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u0010J5\u00107\u001a\u00020\n2-\u0010\u0015\u001a)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u001cJ-\u00108\u001a\u00020\n2%\u0010\u0015\u001a!\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u001fJ5\u00109\u001a\u00020\n2-\u0010\u0015\u001a)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020!\u0018\u00010\u001a¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\"J-\u0010:\u001a\u00020\n2%\u0010\u0015\u001a!\u0012\u0013\u0012\u00110$¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\n0\u0005j\u0002`&J-\u0010;\u001a\u00020\n2%\u0010\u0015\u001a!\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u0016J\u0010\u0010<\u001a\u00020\n2\b\b\u0002\u0010=\u001a\u00020>J\u0006\u0010?\u001a\u00020\nJ\u0016\u0010@\u001a\u00020\n2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020BJ\u001f\u0010D\u001a\u00020\n2\u0012\u0010E\u001a\n\u0012\u0006\b\u0001\u0012\u00020F0\u001a\"\u00020F¢\u0006\u0002\u0010GJ\u0006\u0010H\u001a\u00020\nJ\u0018\u0010I\u001a\u00020\n2\u0006\u0010J\u001a\u00020Kø\u0001\u0000¢\u0006\u0004\bL\u0010MJ\u0006\u0010N\u001a\u00020\nJ\u0016\u0010O\u001a\u0012\u0012\u0004\u0012\u00020\u001b0Pj\b\u0012\u0004\u0012\u00020\u001b`QJ\u0006\u0010R\u001a\u00020\nJ\u0010\u0010S\u001a\u00020\n2\b\b\u0002\u0010T\u001a\u00020\u0006J\u0010\u0010U\u001a\u00020\n2\b\b\u0002\u0010V\u001a\u00020\u0006J-\u0010W\u001a\u00020\n2%\u0010\u0015\u001a!\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000bJ9\u0010X\u001a\u00020\n21\u0010\u0015\u001a-\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00060\r¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u0010J+\u0010Y\u001a\u00020\n2#\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005J\u0006\u0010Z\u001a\u00020\nJ\u0006\u0010[\u001a\u00020\nJ\u0006\u0010\\\u001a\u00020\nJ5\u0010]\u001a\u00020\n2-\u0010\u0015\u001a)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u001cJ-\u0010^\u001a\u00020\n2%\u0010\u0015\u001a!\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u001fJ5\u0010_\u001a\u00020\n2-\u0010\u0015\u001a)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020!\u0018\u00010\u001a¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\"J-\u0010`\u001a\u00020\n2%\u0010\u0015\u001a!\u0012\u0013\u0012\u00110$¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\n0\u0005j\u0002`&J-\u0010a\u001a\u00020\n2%\u0010\u0015\u001a!\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u0016J\u0006\u0010b\u001a\u00020\nJ\u0006\u0010c\u001a\u00020\nJ\u0006\u0010d\u001a\u00020\nJ\u0006\u0010e\u001a\u00020\nJ\u0006\u0010f\u001a\u00020\nJ\u0018\u0010g\u001a\u00020\n2\u0006\u0010J\u001a\u00020Kø\u0001\u0000¢\u0006\u0004\bh\u0010MJ6\u0010i\u001a\u00020\n2\u0006\u0010j\u001a\u00020k2\b\b\u0002\u0010l\u001a\u00020K2\b\b\u0002\u0010m\u001a\u00020K2\b\b\u0002\u0010n\u001a\u00020Kø\u0001\u0000¢\u0006\u0004\bo\u0010pJ\u000e\u0010q\u001a\u00020\n2\u0006\u0010r\u001a\u00020BJ\u000e\u0010s\u001a\u00020\n2\u0006\u0010t\u001a\u00020BJ\u001d\u0010u\u001a\u00020\n2\u0010\u0010v\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u001b\u0018\u00010\u001a¢\u0006\u0002\u0010wJ\u000e\u0010x\u001a\u00020\n2\u0006\u0010v\u001a\u00020\u0006J\u000e\u0010y\u001a\u00020\n2\u0006\u0010v\u001a\u00020\u0006J\u001b\u0010z\u001a\u00020\n2\u000e\u0010v\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u001a¢\u0006\u0002\u0010{J\u000e\u0010|\u001a\u00020\n2\u0006\u0010v\u001a\u00020\u0006J\u000e\u0010}\u001a\u00020\n2\u0006\u0010v\u001a\u00020$R3\u0010\u0003\u001a'\u0012#\u0012!\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R?\u0010\f\u001a3\u0012/\u0012-\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00060\r¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u00100\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R3\u0010\u0011\u001a'\u0012#\u0012!\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u00120\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R3\u0010\u0013\u001a'\u0012#\u0012!\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u00160\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R3\u0010\u0017\u001a'\u0012#\u0012!\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u00180\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R;\u0010\u0019\u001a/\u0012+\u0012)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u001c0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R3\u0010\u001d\u001a'\u0012#\u0012!\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u001f0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R;\u0010 \u001a/\u0012+\u0012)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020!\u0018\u00010\u001a¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\"0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R3\u0010#\u001a'\u0012#\u0012!\u0012\u0013\u0012\u00110$¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\n0\u0005j\u0002`&0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010,\u001a\u00020$2\u0006\u0010+\u001a\u00020$@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u007f"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/disinfection/device/DisinfectDeviceHelper;", "", "()V", "onBatteryBoxOpenStatusListeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "arg", "", "Lcom/pudutech/robot/peripherals/disinfection/device/OnBatteryBoxOpenStatusListener;", "onCheckWaterPumpFailedListeners", "Lkotlin/Pair;", "", "lowerWater", "Lcom/pudutech/robot/peripherals/disinfection/device/OnCheckWaterPumpFailedListener;", "onHumanSensorDetectSignalListeners", "Lcom/pudutech/robot/peripherals/disinfection/device/OnHumanSensorDetectSignalListener;", "onLiquidLevelChangeListeners", "", "l", "Lcom/pudutech/robot/peripherals/disinfection/device/OnLiquidLevelChangeListener;", "onSprayChamberLevelChangeListeners", "Lcom/pudutech/robot/peripherals/disinfection/device/OnSprayChamberLevelChangeListener;", "onSprayDeviceErrorListeners", "", "Lcom/pudutech/robot/peripherals/disinfection/SprayDeviceError;", "Lcom/pudutech/robot/peripherals/disinfection/device/OnSprayDeviceErrorListener;", "onSprayLiquidLevelStatusChangeListeners", "b", "Lcom/pudutech/robot/peripherals/disinfection/device/OnSprayLiquidLevelStatusChangeListener;", "onUvLampDeviceErrorChangeListeners", "Lcom/pudutech/robot/peripherals/disinfection/UvcLampDeviceError;", "Lcom/pudutech/robot/peripherals/disinfection/device/OnUvLampDeviceErrorChangeListener;", "onUvLampPlateOpenStateListeners", "Lcom/pudutech/robot/peripherals/disinfection/OpenState;", "state", "Lcom/pudutech/robot/peripherals/disinfection/device/OnUvLampPlateOpenStatusListener;", "sprayPeripheral", "Lcom/pudutech/robot/peripherals/disinfection/device/SprayPeripheral;", "uvcLampPeripheral", "Lcom/pudutech/robot/peripherals/disinfection/device/UvcLampPeripheral;", "<set-?>", "uvcLampPlateOpenState", "getUvcLampPlateOpenState", "()Lcom/pudutech/robot/peripherals/disinfection/OpenState;", "addBatteryBoxOpenStatusListener", "addHumanSensorDetectSignalListener", "addMagneticConfigStatusListener", "success", "addMagneticTypeListener", "addModuleOpenStatusListener", "openStatus", "addOnCheckWaterPumpFailedListener", "addSprayDeviceErrorListener", "addSprayLiquidStatusListener", "addUvcLampDeviceErrorListener", "addUvcLampPlateOpenStatusListener", "addWaterBoxLiquidLevelListener", "backFlowSprayLiquid", "delayTime", "", "bootModule", "calibrationSpray", "water", "", "fog", "closeDevice", UtilityConfig.KEY_DEVICE_INFO, "Lcom/pudutech/robot/peripherals/disinfection/DeviceName;", "([Lcom/pudutech/robot/peripherals/disinfection/DeviceName;)V", "closeModule", "configMagnetic", "byte", "Lkotlin/UByte;", "configMagnetic-7apg3OU", "(B)V", "getMagneticType", "getSprayErrors", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "initListener", "openSprayDevice", "spray", "openUvcLampDevice", "light", "removeBatteryBoxOpenStatusListener", "removeCheckWaterPumpFailedListener", "removeHumanSensorDetectSignalListener", "removeMagneticConfigStatusListener", "removeMagneticTypeListener", "removeModuleOpenStatusListener", "removeSprayDeviceErrorListener", "removeSprayLiquidStatusListener", "removeUvcLampDeviceErrorListener", "removeUvcLampPlateOpenStatusListener", "removeWaterBoxLiquidLevelListener", "resetCustomCore", "resetRetryOpenSprayDevice", "resetRetryOpenUvcLampDevice", "resetSlamCore", "resetSprayPumpingTime", "selectSpringType", "selectSpringType-7apg3OU", "setLight", "led", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", "red", "green", "blue", "setLight-Fh2MPcY", "(Lcom/pudutech/robot/peripherals/config/LightBeltType;BBB)V", "setMotorRotateAngle", "angle", "setSprayPower", "power", "sprayDeviceOnErrorWrap", "p0", "([Lcom/pudutech/robot/peripherals/disinfection/SprayDeviceError;)V", "sprayDeviceSwitchWrap", "sprayLiquidStatusWrap", "uvcLampDeviceErrorWrap", "([Lcom/pudutech/robot/peripherals/disinfection/UvcLampDeviceError;)V", "uvcLampDeviceSwitchWrap", "uvcLampPlateOpenStatus", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DisinfectDeviceHelper {
    private static final String TAG = "DisinfectDeviceHelper";
    private OpenState uvcLampPlateOpenState = OpenState.CLOSED;
    private final SprayPeripheral sprayPeripheral = new SprayPeripheral();
    private final UvcLampPeripheral uvcLampPeripheral = new UvcLampPeripheral();
    private final CopyOnWriteArrayList<Function1<Double, Unit>> onLiquidLevelChangeListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Function1<Double, Unit>> onSprayChamberLevelChangeListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Function1<SprayDeviceError[], Unit>> onSprayDeviceErrorListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Function1<UvcLampDeviceError[], Unit>> onUvLampDeviceErrorChangeListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Function1<Boolean, Unit>> onSprayLiquidLevelStatusChangeListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Function1<OpenState, Unit>> onUvLampPlateOpenStateListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Function1<Pair<String, Boolean>, Unit>> onCheckWaterPumpFailedListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Function1<Boolean, Unit>> onHumanSensorDetectSignalListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Function1<Boolean, Unit>> onBatteryBoxOpenStatusListeners = new CopyOnWriteArrayList<>();

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[DeviceName.values().length];

        static {
            $EnumSwitchMapping$0[DeviceName.UvCLampDevice.ordinal()] = 1;
            $EnumSwitchMapping$0[DeviceName.SprayDevice.ordinal()] = 2;
        }
    }

    public DisinfectDeviceHelper() {
        Pdlog.m3273d(TAG, "DisinfectDeviceHelper init ");
        this.sprayPeripheral.setOnCheckWaterPumpFailed$module_robot_peripherals_release(new Function1<Pair<? extends String, ? extends Boolean>, Unit>() { // from class: com.pudutech.robot.peripherals.disinfection.device.DisinfectDeviceHelper.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends String, ? extends Boolean> pair) {
                invoke2((Pair<String, Boolean>) pair);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: DisinfectDeviceHelper.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.robot.peripherals.disinfection.device.DisinfectDeviceHelper$1$1", m3970f = "DisinfectDeviceHelper.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.robot.peripherals.disinfection.device.DisinfectDeviceHelper$1$1, reason: invalid class name */
            /* loaded from: classes6.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Pair $lowerWaterError;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f7351p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(Pair pair, Continuation continuation) {
                    super(2, continuation);
                    this.$lowerWaterError = pair;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$lowerWaterError, completion);
                    anonymousClass1.f7351p$ = (CoroutineScope) obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f7351p$;
                    for (Function1 function1 : DisinfectDeviceHelper.this.onCheckWaterPumpFailedListeners) {
                        Pdlog.m3273d(DisinfectDeviceHelper.TAG, "when check the extract water time out ");
                        function1.invoke(this.$lowerWaterError);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Pair<String, Boolean> lowerWaterError) {
                Intrinsics.checkParameterIsNotNull(lowerWaterError, "lowerWaterError");
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new AnonymousClass1(lowerWaterError, null), 3, null);
            }
        });
    }

    public final OpenState getUvcLampPlateOpenState() {
        return this.uvcLampPlateOpenState;
    }

    public final void initListener() {
        Pdlog.m3273d(TAG, "DisinfectDeviceRobotPeripherals " + DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals());
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.addSprayDeviceOpenListener(new Function1<Boolean, Unit>() { // from class: com.pudutech.robot.peripherals.disinfection.device.DisinfectDeviceHelper$initListener$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    SprayPeripheral sprayPeripheral;
                    Pdlog.m3273d("DisinfectDeviceHelper", "SprayDevice Open Status " + z);
                    sprayPeripheral = DisinfectDeviceHelper.this.sprayPeripheral;
                    sprayPeripheral.switchResult$module_robot_peripherals_release(z);
                }
            });
        }
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals2 = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals2 != null) {
            disinfectDeviceRobotPeripherals2.addUvcLampDeviceOpenListener(new Function1<Boolean, Unit>() { // from class: com.pudutech.robot.peripherals.disinfection.device.DisinfectDeviceHelper$initListener$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    UvcLampPeripheral uvcLampPeripheral;
                    Pdlog.m3273d("DisinfectDeviceHelper", "UvcLampDevice Open Status is " + z);
                    uvcLampPeripheral = DisinfectDeviceHelper.this.uvcLampPeripheral;
                    uvcLampPeripheral.switchResult$module_robot_peripherals_release(z);
                }
            });
        }
        addSprayLiquidStatusListener(new Function1<Boolean, Unit>() { // from class: com.pudutech.robot.peripherals.disinfection.device.DisinfectDeviceHelper$initListener$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: DisinfectDeviceHelper.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.robot.peripherals.disinfection.device.DisinfectDeviceHelper$initListener$3$1", m3970f = "DisinfectDeviceHelper.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.robot.peripherals.disinfection.device.DisinfectDeviceHelper$initListener$3$1 */
            /* loaded from: classes6.dex */
            public static final class C57171 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ boolean $it;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f7353p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C57171(boolean z, Continuation continuation) {
                    super(2, continuation);
                    this.$it = z;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C57171 c57171 = new C57171(this.$it, completion);
                    c57171.f7353p$ = (CoroutineScope) obj;
                    return c57171;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C57171) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    SprayPeripheral sprayPeripheral;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f7353p$;
                    sprayPeripheral = DisinfectDeviceHelper.this.sprayPeripheral;
                    sprayPeripheral.onSprayLiquidStatus$module_robot_peripherals_release(this.$it);
                    return Unit.INSTANCE;
                }
            }

            public final void invoke(boolean z) {
                Pdlog.m3273d("DisinfectDeviceHelper", "peripherals SprayLiquidStatus is " + z);
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C57171(z, null), 2, null);
            }
        });
        addSprayDeviceErrorListener((Function1) new Function1<SprayDeviceError[], Unit>() { // from class: com.pudutech.robot.peripherals.disinfection.device.DisinfectDeviceHelper$initListener$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SprayDeviceError[] sprayDeviceErrorArr) {
                invoke2(sprayDeviceErrorArr);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SprayDeviceError[] sprayDeviceErrorArr) {
                SprayPeripheral sprayPeripheral;
                Pdlog.m3273d("DisinfectDeviceHelper", "current sprayDevice Error " + sprayDeviceErrorArr);
                sprayPeripheral = DisinfectDeviceHelper.this.sprayPeripheral;
                sprayPeripheral.onError$module_robot_peripherals_release(sprayDeviceErrorArr);
            }
        });
        addUvcLampDeviceErrorListener(new Function1<UvcLampDeviceError[], Unit>() { // from class: com.pudutech.robot.peripherals.disinfection.device.DisinfectDeviceHelper$initListener$5
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UvcLampDeviceError[] uvcLampDeviceErrorArr) {
                invoke2(uvcLampDeviceErrorArr);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UvcLampDeviceError[] uvcLampDeviceErrorArr) {
                Pdlog.m3273d("DisinfectDeviceHelper", "current uvcLampDevice Error " + uvcLampDeviceErrorArr);
            }
        });
        addUvcLampPlateOpenStatusListener(new Function1<OpenState, Unit>() { // from class: com.pudutech.robot.peripherals.disinfection.device.DisinfectDeviceHelper$initListener$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(OpenState openState) {
                invoke2(openState);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(OpenState it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Pdlog.m3273d("DisinfectDeviceHelper", "current state open state is " + it);
                DisinfectDeviceHelper.this.uvcLampPlateOpenState = it;
            }
        });
    }

    public final void uvcLampDeviceErrorWrap(UvcLampDeviceError[] p0) {
        Pdlog.m3273d(TAG, "uvcLampDeviceErrorWrap " + p0);
    }

    public final void sprayDeviceOnErrorWrap(SprayDeviceError[] p0) {
        Pdlog.m3273d(TAG, "sprayDeviceOnErrorWrap " + p0);
        this.sprayPeripheral.onError$module_robot_peripherals_release(p0);
    }

    public final void sprayDeviceSwitchWrap(boolean p0) {
        Pdlog.m3273d(TAG, "sprayDeviceSwitchWrap " + p0);
        this.sprayPeripheral.switchResult$module_robot_peripherals_release(p0);
    }

    public final void uvcLampDeviceSwitchWrap(boolean p0) {
        Pdlog.m3273d(TAG, "uvcLampDeviceSwitchWrap " + p0);
        this.uvcLampPeripheral.switchResult$module_robot_peripherals_release(p0);
    }

    public final void sprayLiquidStatusWrap(boolean p0) {
        Pdlog.m3273d(TAG, "sprayLiquidStatusWrap " + p0);
        this.sprayPeripheral.onSprayLiquidStatus$module_robot_peripherals_release(p0);
    }

    public final void uvcLampPlateOpenStatus(OpenState p0) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        Pdlog.m3273d(TAG, "uvcLampPlateOpenStatus " + p0);
        this.uvcLampPlateOpenState = p0;
    }

    public final void addUvcLampDeviceErrorListener(Function1<? super UvcLampDeviceError[], Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        if (!this.onUvLampDeviceErrorChangeListeners.contains(l)) {
            this.onUvLampDeviceErrorChangeListeners.add(l);
        }
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.addUvcLampDeviceOccurErrorListener(this.onUvLampDeviceErrorChangeListeners);
        }
    }

    public final void removeUvcLampDeviceErrorListener(Function1<? super UvcLampDeviceError[], Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        this.onUvLampDeviceErrorChangeListeners.remove(l);
    }

    public final void addSprayDeviceErrorListener(Function1<? super SprayDeviceError[], Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        if (!this.onSprayDeviceErrorListeners.contains(l)) {
            this.onSprayDeviceErrorListeners.add(l);
        }
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.addSprayDeviceOccurErrorListener(this.onSprayDeviceErrorListeners);
        }
    }

    public final void removeSprayDeviceErrorListener(Function1<? super SprayDeviceError[], Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        this.onSprayDeviceErrorListeners.remove(l);
    }

    public final void addUvcLampPlateOpenStatusListener(Function1<? super OpenState, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        if (!this.onUvLampPlateOpenStateListeners.contains(l)) {
            this.onUvLampPlateOpenStateListeners.add(l);
        }
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.addUvcLampDevicePlateOpenStateListener(this.onUvLampPlateOpenStateListeners);
        }
    }

    public final void removeUvcLampPlateOpenStatusListener(Function1<? super OpenState, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        this.onUvLampPlateOpenStateListeners.remove(l);
    }

    public final void addSprayLiquidStatusListener(Function1<? super Boolean, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "before addSprayLiquidStatusListener " + l + " list " + this.onSprayLiquidLevelStatusChangeListeners.size() + ' ');
        if (!this.onSprayLiquidLevelStatusChangeListeners.contains(l)) {
            this.onSprayLiquidLevelStatusChangeListeners.add(l);
        }
        Pdlog.m3273d(TAG, "after addSprayLiquidStatusListener " + this.onSprayLiquidLevelStatusChangeListeners + " list.size is " + this.onSprayLiquidLevelStatusChangeListeners.size());
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.addOnSprayLiquidLevelStatusListener(this.onSprayLiquidLevelStatusChangeListeners);
        }
    }

    public final void removeSprayLiquidStatusListener(Function1<? super Boolean, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        this.onSprayLiquidLevelStatusChangeListeners.remove(l);
    }

    public final void addOnCheckWaterPumpFailedListener(Function1<? super Pair<String, Boolean>, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        if (this.onCheckWaterPumpFailedListeners.contains(l)) {
            return;
        }
        this.onCheckWaterPumpFailedListeners.add(l);
    }

    public final void addWaterBoxLiquidLevelListener(Function1<? super Double, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        if (!this.onLiquidLevelChangeListeners.contains(l)) {
            this.onLiquidLevelChangeListeners.add(l);
        }
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.addWaterBoxLiquidLevelListener(this.onLiquidLevelChangeListeners);
        }
    }

    public final void removeWaterBoxLiquidLevelListener(Function1<? super Double, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        this.onLiquidLevelChangeListeners.remove(l);
    }

    public final void addBatteryBoxOpenStatusListener(Function1<? super Boolean, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        if (!this.onBatteryBoxOpenStatusListeners.contains(l)) {
            this.onBatteryBoxOpenStatusListeners.add(l);
        }
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.addBatteryBoxOpenStatusListener(this.onBatteryBoxOpenStatusListeners);
        }
    }

    public final void removeBatteryBoxOpenStatusListener(Function1<? super Boolean, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        this.onBatteryBoxOpenStatusListeners.remove(l);
    }

    public final void removeCheckWaterPumpFailedListener(Function1<? super Pair<String, Boolean>, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        this.onCheckWaterPumpFailedListeners.remove(l);
    }

    public static /* synthetic */ void openSprayDevice$default(DisinfectDeviceHelper disinfectDeviceHelper, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        disinfectDeviceHelper.openSprayDevice(z);
    }

    public final void openSprayDevice(boolean spray) {
        Pdlog.m3273d(TAG, "openSprayDevice " + spray);
        this.sprayPeripheral.setSpray$module_robot_peripherals_release(spray);
        this.sprayPeripheral.open();
    }

    public static /* synthetic */ void openUvcLampDevice$default(DisinfectDeviceHelper disinfectDeviceHelper, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        disinfectDeviceHelper.openUvcLampDevice(z);
    }

    public final void openUvcLampDevice(boolean light) {
        this.uvcLampPeripheral.setLightUvcLamp(light);
        this.uvcLampPeripheral.open();
    }

    public final void closeDevice(DeviceName... device) {
        Intrinsics.checkParameterIsNotNull(device, "device");
        Pdlog.m3273d(TAG, "closeDevice :device " + device);
        for (DeviceName deviceName : device) {
            int i = WhenMappings.$EnumSwitchMapping$0[deviceName.ordinal()];
            if (i == 1) {
                this.uvcLampPeripheral.close();
            } else if (i == 2) {
                this.sprayPeripheral.close();
            }
        }
    }

    public final void resetSprayPumpingTime() {
        this.sprayPeripheral.resetPumpingTime();
    }

    public final void resetRetryOpenUvcLampDevice() {
        Pdlog.m3273d(TAG, "UvcLamp Device resetRetryOpenDevice");
        this.uvcLampPeripheral.resetRetry();
    }

    public final void resetRetryOpenSprayDevice() {
        Pdlog.m3273d(TAG, "resetRetryOpenSprayDevice");
        this.sprayPeripheral.resetRetry();
    }

    public static /* synthetic */ void backFlowSprayLiquid$default(DisinfectDeviceHelper disinfectDeviceHelper, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 0;
        }
        disinfectDeviceHelper.backFlowSprayLiquid(j);
    }

    public final void backFlowSprayLiquid(long delayTime) {
        Pdlog.m3273d(TAG, "backFlowSprayLiquid");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectDeviceHelper$backFlowSprayLiquid$1(delayTime, null), 3, null);
    }

    public final void setSprayPower(int power) {
        Pdlog.m3273d(TAG, "setSprayPower " + power);
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.setSpraySpeedRate(power);
        }
    }

    public final ArrayList<SprayDeviceError> getSprayErrors() {
        return this.sprayPeripheral.getErrorList$module_robot_peripherals_release();
    }

    public final void bootModule() {
        Pdlog.m3274e(TAG, "start up the module");
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.bootModule();
        }
    }

    public final void resetSlamCore() {
        Pdlog.m3273d(TAG, "resetSlamCore");
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.resetSlamCore();
        }
    }

    public final void resetCustomCore() {
        Pdlog.m3273d(TAG, "resetCustomCore");
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.resetCustomCore();
        }
    }

    public final void closeModule() {
        Pdlog.m3273d(TAG, "close the module");
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.closeModule();
        }
    }

    public final void addModuleOpenStatusListener(Function1<? super Boolean, Unit> l) {
        Pdlog.m3273d(TAG, "addModuleOpenStatusListener ");
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.addModuleOpenStatusListener(l);
        }
    }

    public final void removeModuleOpenStatusListener() {
        Pdlog.m3273d(TAG, "removeModuleOpenStatusListener");
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.removeModuleOpenStatusListener();
        }
    }

    /* renamed from: selectSpringType-7apg3OU, reason: not valid java name */
    public final void m4490selectSpringType7apg3OU(byte r4) {
        Pdlog.m3273d(TAG, "selectSpringType " + UByte.m4563toStringimpl(r4));
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.mo4484selectSpringType7apg3OU(r4);
        }
    }

    /* renamed from: configMagnetic-7apg3OU, reason: not valid java name */
    public final void m4489configMagnetic7apg3OU(byte r4) {
        Pdlog.m3273d(TAG, "configMagnetic " + UByte.m4563toStringimpl(r4));
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.mo4483configMagnetic7apg3OU(r4);
        }
    }

    public final void getMagneticType() {
        Pdlog.m3273d(TAG, "getMagneticType");
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.getMagneticType();
        }
    }

    public final void addMagneticConfigStatusListener(Function1<? super Boolean, Unit> l) {
        Pdlog.m3273d(TAG, "addMagneticConfigStatusListener");
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.addMagneticConfigStatusListener(l);
        }
    }

    public final void removeMagneticConfigStatusListener() {
        Pdlog.m3273d(TAG, "removeMagneticConfigStatusListener");
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.removeMagneticConfigStatusListener();
        }
    }

    public final void addMagneticTypeListener(Function1<? super String, Unit> l) {
        Pdlog.m3273d(TAG, "addMagneticTypeListener");
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.addMagneticTypeListener(l);
        }
    }

    public final void removeMagneticTypeListener() {
        Pdlog.m3273d(TAG, "removeMagneticTypeListener");
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.removeMagneticTypeListener();
        }
    }

    public final void calibrationSpray(int water, int fog) {
        Pdlog.m3273d(TAG, "calibrationSpray water " + water + "  fog  " + fog);
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.calibrationSpray(water, fog);
        }
    }

    public final void setMotorRotateAngle(int angle) {
        Pdlog.m3273d(TAG, "setMotorRotateAngle " + angle);
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.setMotorAngle(angle);
        }
    }

    /* renamed from: setLight-Fh2MPcY$default, reason: not valid java name */
    public static /* synthetic */ void m4488setLightFh2MPcY$default(DisinfectDeviceHelper disinfectDeviceHelper, LightBeltType lightBeltType, byte b, byte b2, byte b3, int i, Object obj) {
        if ((i & 2) != 0) {
            b = 0;
        }
        if ((i & 4) != 0) {
            b2 = 0;
        }
        if ((i & 8) != 0) {
            b3 = 0;
        }
        disinfectDeviceHelper.m4491setLightFh2MPcY(lightBeltType, b, b2, b3);
    }

    /* renamed from: setLight-Fh2MPcY, reason: not valid java name */
    public final void m4491setLightFh2MPcY(LightBeltType led, byte red, byte green, byte blue) {
        Intrinsics.checkParameterIsNotNull(led, "led");
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.mo4485setLightFh2MPcY(led, red, green, blue);
        }
    }

    public final void addHumanSensorDetectSignalListener(Function1<? super Boolean, Unit> l) {
        Pdlog.m3273d(TAG, "addHumanSensorDetectSignalListener");
        if (!this.onHumanSensorDetectSignalListeners.contains(l)) {
            this.onHumanSensorDetectSignalListeners.add(l);
        }
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.addHumanSensorDetectSignalListener(this.onHumanSensorDetectSignalListeners);
        }
    }

    public final void removeHumanSensorDetectSignalListener(Function1<? super Boolean, Unit> l) {
        Pdlog.m3273d(TAG, "removeHumanSensorDetectSignalListener");
        this.onHumanSensorDetectSignalListeners.remove(l);
    }
}
