package com.pudutech.bumblebee.robot.disinfection_device;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.aidl.serialize.SprayDeviceError;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.base.CommonKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: SprayDevice.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\b\n\u0002\u0010!\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010/\u001a\u00020\u000e2\u0006\u00100\u001a\u00020(2\u0006\u00101\u001a\u00020(J\u0006\u00102\u001a\u00020\u000eJ\u001a\u00103\u001a\u00020\u000e2\u0006\u00104\u001a\u000205H\u0002ø\u0001\u0000¢\u0006\u0004\b6\u00107J\u001a\u00108\u001a\u00020\u000e2\u0006\u00104\u001a\u000205H\u0002ø\u0001\u0000¢\u0006\u0004\b9\u00107J\u0010\u0010:\u001a\u00020\u000e2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0018\u0010;\u001a\u00020\u000e2\u0006\u00104\u001a\u000205ø\u0001\u0000¢\u0006\u0004\b<\u00107J\u0016\u0010=\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010>\u001a\u00020\u0014J\u000e\u0010?\u001a\u00020\u000e2\u0006\u0010@\u001a\u00020(R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R=\u0010\u0007\u001a%\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\n0\t¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R7\u0010\u0013\u001a\u001f\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u000e\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0010\"\u0004\b\u0017\u0010\u0012RL\u0010\u0018\u001a4\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0019X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R7\u0010!\u001a\u001f\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u000e\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0010\"\u0004\b#\u0010\u0012R7\u0010$\u001a\u001f\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u000e\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0010\"\u0004\b&\u0010\u0012R&\u0010'\u001a\u001a\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020(\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0)0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010*\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006A"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/disinfection_device/SprayDevice;", "", "()V", "TAG", "", "hardware", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "onSprayDeviceErrorListener", "Lkotlin/Function1;", "", "Lcom/pudutech/bumblebee/robot/aidl/serialize/SprayDeviceError;", "Lkotlin/ParameterName;", "name", "error", "", "getOnSprayDeviceErrorListener$Robot_release", "()Lkotlin/jvm/functions/Function1;", "setOnSprayDeviceErrorListener$Robot_release", "(Lkotlin/jvm/functions/Function1;)V", "onSprayDeviceOpenListener", "", "b", "getOnSprayDeviceOpenListener$Robot_release", "setOnSprayDeviceOpenListener$Robot_release", "onSprayLiquidLevelListener", "Lkotlin/Function2;", "", "liquid", "chamberLevel", "getOnSprayLiquidLevelListener$Robot_release", "()Lkotlin/jvm/functions/Function2;", "setOnSprayLiquidLevelListener$Robot_release", "(Lkotlin/jvm/functions/Function2;)V", "onSprayLiquidLevelStatusListener", "getOnSprayLiquidLevelStatusListener$Robot_release", "setOnSprayLiquidLevelStatusListener$Robot_release", "onSpraySpringOpenStatusListener", "getOnSpraySpringOpenStatusListener$Robot_release", "setOnSpraySpringOpenStatusListener$Robot_release", "parseError", "", "", "switch", "getSwitch", "()Z", "setSwitch", "(Z)V", "calibrationSpray", "water", "fog", "drain", "handleReceiveSysData", "bytes", "Lkotlin/UByteArray;", "handleReceiveSysData-GBYM_sE", "([B)V", "handleSpringReceiveData", "handleSpringReceiveData-GBYM_sE", "init", "onDataReceive", "onDataReceive-GBYM_sE", "open", "b1", "powerControl", "int", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SprayDevice {
    private HardwareInterface hardware;
    private Function1<? super SprayDeviceError[], Unit> onSprayDeviceErrorListener;
    private Function1<? super Boolean, Unit> onSprayDeviceOpenListener;
    private Function2<? super Double, ? super Double, Unit> onSprayLiquidLevelListener;
    private Function1<? super Boolean, Unit> onSprayLiquidLevelStatusListener;
    private Function1<? super Boolean, Unit> onSpraySpringOpenStatusListener;
    private boolean switch;
    private final String TAG = "SprayDevice";
    private final Function2<Integer, Integer, List<SprayDeviceError>> parseError = new Function2<Integer, Integer, List<SprayDeviceError>>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.SprayDevice$parseError$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ List<SprayDeviceError> invoke(Integer num, Integer num2) {
            return invoke(num.intValue(), num2.intValue());
        }

        public final List<SprayDeviceError> invoke(int i, int i2) {
            ArrayList arrayList = new ArrayList();
            if ((i2 & 1) != 0) {
                arrayList.add(SprayDeviceError.WATER_TANK_SENSOR_ERROR);
            }
            if ((i2 & 2) != 0) {
                arrayList.add(SprayDeviceError.SPRAY_CHAMBER_SENSOR_ERROR);
            }
            if ((i2 & 4) != 0) {
                arrayList.add(SprayDeviceError.PUMP_ERROR);
            }
            if ((i2 & 8) != 0) {
                arrayList.add(SprayDeviceError.SOLENOID_VALVE_ERROR);
            }
            if ((i2 & 16) != 0) {
                arrayList.add(SprayDeviceError.BLOWER_CURRENT_ERROR);
            }
            if ((i2 & 32) != 0) {
                arrayList.add(SprayDeviceError.SPRAY_DRIVE_CURRENT_ERROR_1);
            }
            if ((i2 & 64) != 0) {
                arrayList.add(SprayDeviceError.SPRAY_DRIVE_CURRENT_ERROR_2);
            }
            if ((i2 & 128) != 0) {
                arrayList.add(SprayDeviceError.SPRAY_DRIVE_CURRENT_ERROR_3);
            }
            if ((i & 1) != 0) {
                arrayList.add(SprayDeviceError.SPRAY_DRIVE_CURRENT_ERROR_4);
            }
            if ((i & 2) != 0) {
                arrayList.add(SprayDeviceError.SPRAY_DRIVE_VOLTAGE_ERROR_1);
            }
            if ((i & 4) != 0) {
                arrayList.add(SprayDeviceError.SPRAY_DRIVE_VOLTAGE_ERROR_2);
            }
            if ((i & 8) != 0) {
                arrayList.add(SprayDeviceError.SPRAY_DRIVE_VOLTAGE_ERROR_3);
            }
            if ((i & 16) != 0) {
                arrayList.add(SprayDeviceError.SPRAY_DRIVE_VOLTAGE_ERROR_4);
            }
            if ((i & 32) != 0) {
                arrayList.add(SprayDeviceError.LOWER_VOLTAGE);
            }
            if ((i & 64) != 0) {
                arrayList.add(SprayDeviceError.LOWER_LIQUID);
            }
            return arrayList;
        }
    };

    public final Function1<Boolean, Unit> getOnSprayDeviceOpenListener$Robot_release() {
        return this.onSprayDeviceOpenListener;
    }

    public final void setOnSprayDeviceOpenListener$Robot_release(Function1<? super Boolean, Unit> function1) {
        this.onSprayDeviceOpenListener = function1;
    }

    public final Function1<Boolean, Unit> getOnSpraySpringOpenStatusListener$Robot_release() {
        return this.onSpraySpringOpenStatusListener;
    }

    public final void setOnSpraySpringOpenStatusListener$Robot_release(Function1<? super Boolean, Unit> function1) {
        this.onSpraySpringOpenStatusListener = function1;
    }

    public final Function1<SprayDeviceError[], Unit> getOnSprayDeviceErrorListener$Robot_release() {
        return this.onSprayDeviceErrorListener;
    }

    public final void setOnSprayDeviceErrorListener$Robot_release(Function1<? super SprayDeviceError[], Unit> function1) {
        this.onSprayDeviceErrorListener = function1;
    }

    public final Function2<Double, Double, Unit> getOnSprayLiquidLevelListener$Robot_release() {
        return this.onSprayLiquidLevelListener;
    }

    public final void setOnSprayLiquidLevelListener$Robot_release(Function2<? super Double, ? super Double, Unit> function2) {
        this.onSprayLiquidLevelListener = function2;
    }

    public final Function1<Boolean, Unit> getOnSprayLiquidLevelStatusListener$Robot_release() {
        return this.onSprayLiquidLevelStatusListener;
    }

    public final void setOnSprayLiquidLevelStatusListener$Robot_release(Function1<? super Boolean, Unit> function1) {
        this.onSprayLiquidLevelStatusListener = function1;
    }

    public final boolean getSwitch() {
        return this.switch;
    }

    public final void setSwitch(boolean z) {
        this.switch = z;
    }

    public final void init(HardwareInterface hardware) {
        this.hardware = hardware;
    }

    /* renamed from: onDataReceive-GBYM_sE, reason: not valid java name */
    public final void m4325onDataReceiveGBYM_sE(byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        if (UByteArray.m4577getimpl(bytes, 0) == -121 && UByteArray.m4577getimpl(bytes, 1) == UByte.m4528constructorimpl((byte) 7)) {
            m4323handleReceiveSysDataGBYM_sE(bytes);
        } else if (UByteArray.m4577getimpl(bytes, 0) == -120 && UByteArray.m4577getimpl(bytes, 1) == UByte.m4528constructorimpl((byte) 9)) {
            m4324handleSpringReceiveDataGBYM_sE(bytes);
        }
    }

    /* renamed from: handleSpringReceiveData-GBYM_sE, reason: not valid java name */
    private final void m4324handleSpringReceiveDataGBYM_sE(byte[] bytes) {
        Pdlog.m3273d(this.TAG, "handle the spring data from the stm32 devices " + UByteArray.m4583toStringimpl(bytes));
        if (UByteArray.m4577getimpl(bytes, 2) == UByte.m4528constructorimpl((byte) 2)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SprayDevice$handleSpringReceiveData$1(this, bytes, null), 3, null);
        }
    }

    /* renamed from: handleReceiveSysData-GBYM_sE, reason: not valid java name */
    private final void m4323handleReceiveSysDataGBYM_sE(byte[] bytes) {
        byte m4577getimpl = UByteArray.m4577getimpl(bytes, 2);
        if (m4577getimpl == UByte.m4528constructorimpl((byte) 3)) {
            Pdlog.m3273d(this.TAG, "handleReceiveData : device open status bytes = " + CommonKt.m4421toHexStringGBYM_sE(bytes) + "; ");
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SprayDevice$handleReceiveSysData$1(this, bytes, null), 3, null);
            return;
        }
        if (m4577getimpl == UByte.m4528constructorimpl((byte) 4)) {
            Pdlog.m3274e(this.TAG, "handleReceiveData : device err = " + CommonKt.m4421toHexStringGBYM_sE(bytes) + "; ");
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SprayDevice$handleReceiveSysData$2(this, bytes, null), 3, null);
            return;
        }
        if (m4577getimpl == UByte.m4528constructorimpl((byte) 5)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SprayDevice$handleReceiveSysData$3(this, bytes, null), 3, null);
        }
    }

    public final void open(boolean b, boolean b1) {
        if (this.hardware != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SprayDevice$open$1(this, b, b1, null), 3, null);
        } else {
            Pdlog.m3273d(this.TAG, "open hardware is null");
        }
    }

    public final void calibrationSpray(int water, int fog) {
        Pdlog.m3273d(this.TAG, "calibrationSpray : water = " + water + "; fog = " + fog + "; ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SprayDevice$calibrationSpray$1(this, water, fog, null), 3, null);
    }

    public final void drain() {
        Pdlog.m3273d(this.TAG, "drain ");
        if (this.hardware != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SprayDevice$drain$1(this, null), 3, null);
        } else {
            Pdlog.m3273d(this.TAG, "open hardware is null");
        }
    }

    public final void powerControl(int r8) {
        Pdlog.m3273d(this.TAG, "powerControl : int = " + r8 + "; ");
        if (this.hardware == null) {
            Pdlog.m3273d(this.TAG, "powerControl hardware is null");
            return;
        }
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = r8;
        if (intRef.element < 1) {
            intRef.element = 1;
        } else if (intRef.element > 4) {
            intRef.element = 4;
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SprayDevice$powerControl$1(this, intRef, null), 3, null);
    }
}
