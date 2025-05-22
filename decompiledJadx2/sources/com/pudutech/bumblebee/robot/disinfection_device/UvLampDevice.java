package com.pudutech.bumblebee.robot.disinfection_device;

import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.aidl.serialize.OpenState;
import com.pudutech.bumblebee.robot.aidl.serialize.UvLampDeviceError;
import com.pudutech.bumblebee.robot.protocol_utils.UByteArrayUtilsKt;
import com.pudutech.mirsdk.hardware.HardwareInterface;
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
/* compiled from: UvLampDevice.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#H\u0002ø\u0001\u0000¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\u000e2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0018\u0010'\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#ø\u0001\u0000¢\u0006\u0004\b(\u0010%J\u0018\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u00142\b\b\u0002\u0010+\u001a\u00020\u0014J\u0006\u0010,\u001a\u00020\u000eJ\u000e\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\u001fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R=\u0010\u0007\u001a%\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\n0\t¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R7\u0010\u0013\u001a\u001f\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u000e\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0010\"\u0004\b\u0017\u0010\u0012R7\u0010\u0018\u001a\u001f\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u000e\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0010\"\u0004\b\u001c\u0010\u0012R&\u0010\u001d\u001a\u001a\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0 0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006/"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/disinfection_device/UvLampDevice;", "", "()V", "TAG", "", "hardware", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "onUvDeviceErrorListener", "Lkotlin/Function1;", "", "Lcom/pudutech/bumblebee/robot/aidl/serialize/UvLampDeviceError;", "Lkotlin/ParameterName;", "name", "error", "", "getOnUvDeviceErrorListener$Robot_release", "()Lkotlin/jvm/functions/Function1;", "setOnUvDeviceErrorListener$Robot_release", "(Lkotlin/jvm/functions/Function1;)V", "onUvDeviceOpenListener", "", "b", "getOnUvDeviceOpenListener$Robot_release", "setOnUvDeviceOpenListener$Robot_release", "onUvDevicePlateOpenStateListener", "Lcom/pudutech/bumblebee/robot/aidl/serialize/OpenState;", "openState", "getOnUvDevicePlateOpenStateListener$Robot_release", "setOnUvDevicePlateOpenStateListener$Robot_release", "parseError", "Lkotlin/Function2;", "", "", "handleSysReceiveData", "bytes", "Lkotlin/UByteArray;", "handleSysReceiveData-GBYM_sE", "([B)V", "init", "onDataReceive", "onDataReceive-GBYM_sE", "open", "openUvLampDevice", "openUvLamp", "setMotor0", "setMotorAngle", LinkFormat.DOMAIN, "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class UvLampDevice {
    private HardwareInterface hardware;
    private Function1<? super UvLampDeviceError[], Unit> onUvDeviceErrorListener;
    private Function1<? super Boolean, Unit> onUvDeviceOpenListener;
    private Function1<? super OpenState, Unit> onUvDevicePlateOpenStateListener;
    private final String TAG = "UvLampDevice";
    private final Function2<Integer, Integer, List<UvLampDeviceError>> parseError = new Function2<Integer, Integer, List<UvLampDeviceError>>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.UvLampDevice$parseError$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ List<UvLampDeviceError> invoke(Integer num, Integer num2) {
            return invoke(num.intValue(), num2.intValue());
        }

        public final List<UvLampDeviceError> invoke(int i, int i2) {
            ArrayList arrayList = new ArrayList();
            if ((i2 & 1) != 0) {
                arrayList.add(UvLampDeviceError.DRIVER_BOARD_NO_RESPONSE);
            }
            if ((i2 & 2) != 0) {
                arrayList.add(UvLampDeviceError.DRIVER_BOARD_COMM_ERROR);
            }
            if ((i2 & 4) != 0) {
                arrayList.add(UvLampDeviceError.DRIVER_CURRENT_ERROR_1);
            }
            if ((i2 & 8) != 0) {
                arrayList.add(UvLampDeviceError.DRIVER_CURRENT_ERROR_2);
            }
            if ((i2 & 16) != 0) {
                arrayList.add(UvLampDeviceError.DRIVER_CURRENT_ERROR_3);
            }
            if ((i2 & 32) != 0) {
                arrayList.add(UvLampDeviceError.DRIVER_CURRENT_ERROR_4);
            }
            if ((i2 & 64) != 0) {
                arrayList.add(UvLampDeviceError.DRIVER_VOLTAGE_ERROR_1);
            }
            if ((i2 & 128) != 0) {
                arrayList.add(UvLampDeviceError.DRIVER_VOLTAGE_ERROR_2);
            }
            if ((i & 1) != 0) {
                arrayList.add(UvLampDeviceError.DRIVER_VOLTAGE_ERROR_3);
            }
            if ((i & 2) != 0) {
                arrayList.add(UvLampDeviceError.DRIVER_VOLTAGE_ERROR_4);
            }
            if ((i & 4) != 0) {
                arrayList.add(UvLampDeviceError.LOWER_VOLTAGE);
            }
            return arrayList;
        }
    };

    public final Function1<Boolean, Unit> getOnUvDeviceOpenListener$Robot_release() {
        return this.onUvDeviceOpenListener;
    }

    public final void setOnUvDeviceOpenListener$Robot_release(Function1<? super Boolean, Unit> function1) {
        this.onUvDeviceOpenListener = function1;
    }

    public final Function1<UvLampDeviceError[], Unit> getOnUvDeviceErrorListener$Robot_release() {
        return this.onUvDeviceErrorListener;
    }

    public final void setOnUvDeviceErrorListener$Robot_release(Function1<? super UvLampDeviceError[], Unit> function1) {
        this.onUvDeviceErrorListener = function1;
    }

    public final Function1<OpenState, Unit> getOnUvDevicePlateOpenStateListener$Robot_release() {
        return this.onUvDevicePlateOpenStateListener;
    }

    public final void setOnUvDevicePlateOpenStateListener$Robot_release(Function1<? super OpenState, Unit> function1) {
        this.onUvDevicePlateOpenStateListener = function1;
    }

    public final void init(HardwareInterface hardware) {
        this.hardware = hardware;
    }

    /* renamed from: onDataReceive-GBYM_sE, reason: not valid java name */
    public final void m4327onDataReceiveGBYM_sE(byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        if (UByteArray.m4577getimpl(bytes, 0) == -120) {
            m4326handleSysReceiveDataGBYM_sE(bytes);
        }
    }

    public final void setMotor0() {
        if (this.hardware != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new UvLampDevice$setMotor0$1(this, null), 3, null);
        } else {
            Pdlog.m3273d(this.TAG, "boot hardware is null");
        }
    }

    public final void setMotorAngle(int d) {
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = d;
        if (intRef.element > 360 || intRef.element < -360) {
            intRef.element %= 360;
        }
        Pdlog.m3273d(this.TAG, "setMotorAngle : d = " + d + "; a = " + intRef.element);
        if (this.hardware != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new UvLampDevice$setMotorAngle$1(this, intRef, null), 3, null);
        } else {
            Pdlog.m3273d(this.TAG, "boot hardware is null");
        }
    }

    /* renamed from: handleSysReceiveData-GBYM_sE, reason: not valid java name */
    private final void m4326handleSysReceiveDataGBYM_sE(byte[] bytes) {
        byte m4577getimpl = UByteArray.m4577getimpl(bytes, 2);
        if (m4577getimpl == UByte.m4528constructorimpl((byte) 3)) {
            Pdlog.m3273d(this.TAG, "handleReceiveData : device open status bytes = " + UByteArrayUtilsKt.m4334toHexStringGBYM_sE(bytes));
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new UvLampDevice$handleSysReceiveData$1(this, bytes, null), 3, null);
            return;
        }
        if (m4577getimpl == UByte.m4528constructorimpl((byte) 4)) {
            Pdlog.m3274e(this.TAG, "handleReceiveData : device err = " + UByteArrayUtilsKt.m4334toHexStringGBYM_sE(bytes));
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new UvLampDevice$handleSysReceiveData$2(this, bytes, null), 3, null);
        }
    }

    public static /* synthetic */ void open$default(UvLampDevice uvLampDevice, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = true;
        }
        uvLampDevice.open(z, z2);
    }

    public final void open(boolean openUvLampDevice, boolean openUvLamp) {
        if (this.hardware == null) {
            Pdlog.m3273d(this.TAG, "boot hardware is null when openUvLampDevice");
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new UvLampDevice$open$1(this, openUvLampDevice, openUvLamp, null), 3, null);
    }
}
