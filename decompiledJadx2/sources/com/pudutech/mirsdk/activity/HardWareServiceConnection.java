package com.pudutech.mirsdk.activity;

import android.content.ComponentName;
import android.os.IBinder;
import com.iflytek.speech.UtilityConfig;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* compiled from: HardWareServiceConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000O\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0007\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0006\u0010\u001e\u001a\u00020\u0012J\u001c\u0010\u001f\u001a\u00020\u00122\b\u0010\f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0006\u0010#\u001a\u00020\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bRv\u0010\t\u001a^\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R7\u0010\u0017\u001a\u001f\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006$"}, m3961d2 = {"Lcom/pudutech/mirsdk/activity/HardWareServiceConnection;", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "()V", "TAG", "", "iHardware", "com/pudutech/mirsdk/activity/HardWareServiceConnection$iHardware$1", "Lcom/pudutech/mirsdk/activity/HardWareServiceConnection$iHardware$1;", "onGeomagneticResult", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "direction", "", "x", "y", CompressorStreamFactory.f8930Z, "", "getOnGeomagneticResult", "()Lkotlin/jvm/functions/Function4;", "setOnGeomagneticResult", "(Lkotlin/jvm/functions/Function4;)V", "onSensorMagneticResult", "Lkotlin/Function1;", UtilityConfig.KEY_DEVICE_INFO, "getOnSensorMagneticResult", "()Lkotlin/jvm/functions/Function1;", "setOnSensorMagneticResult", "(Lkotlin/jvm/functions/Function1;)V", "addHardwareListener", "onServiceConnected", "Landroid/content/ComponentName;", "service", "Landroid/os/IBinder;", "removeHardwareListener", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class HardWareServiceConnection extends AIDLConnection<HardwareInterface> {
    private static Function4<? super String, ? super Integer, ? super Integer, ? super Integer, Unit> onGeomagneticResult;
    private static Function1<? super Integer, Unit> onSensorMagneticResult;
    public static final HardWareServiceConnection INSTANCE = new HardWareServiceConnection();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static HardWareServiceConnection$iHardware$1 iHardware = new IHardware.Stub() { // from class: com.pudutech.mirsdk.activity.HardWareServiceConnection$iHardware$1
        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onAntiCollisionSwitch(boolean p0) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onBattery(int p0, ChargeState p1) {
        }

        public void onBatteryFloorLevelLimit(int p0) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onBatterySOH(int p0) {
        }

        public void onBumperSwitchStatus(boolean p0) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onCameraIRDLED(boolean p0) {
        }

        public void onChargingFail() {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onConnectedChargingPile() {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onDisinfectionPower(boolean p0) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onHarewareEmergencyBrake(boolean p0) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onHarewareInfoReport(String p0) {
        }

        public void onLockMotorStatus(boolean p0) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onOpenStep(HardwareOpenStep step, StepState stepState, String description) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onSlamCorePower(boolean p0) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onWheelError(WheelError[] left, WheelError[] rigtht) {
        }

        public void securityFeedback(boolean p0) {
        }

        public void geomagneticAntiDrop(String p0, int p1, int p2, int p3) {
            String str;
            Intrinsics.checkParameterIsNotNull(p0, "p0");
            if (HardWareServiceConnection.INSTANCE.getOnGeomagneticResult() == null) {
                HardWareServiceConnection hardWareServiceConnection = HardWareServiceConnection.INSTANCE;
                str = HardWareServiceConnection.TAG;
                Pdlog.m3273d(str, "onGeomagneticResult is null");
            }
            Function4<String, Integer, Integer, Integer, Unit> onGeomagneticResult2 = HardWareServiceConnection.INSTANCE.getOnGeomagneticResult();
            if (onGeomagneticResult2 != null) {
                onGeomagneticResult2.invoke(p0, Integer.valueOf(p1), Integer.valueOf(p2), Integer.valueOf(p3));
            }
        }

        public void sensormagnetic(int p0) {
            Function1<Integer, Unit> onSensorMagneticResult2 = HardWareServiceConnection.INSTANCE.getOnSensorMagneticResult();
            if (onSensorMagneticResult2 != null) {
                onSensorMagneticResult2.invoke(Integer.valueOf(p0));
            }
        }
    };

    /* compiled from: HardWareServiceConnection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012,\u0010\u0003\u001a( \u0002*\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\b"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "kotlin.jvm.PlatformType", "p1", "Landroid/os/IBinder;", "Lkotlin/ParameterName;", "name", "p0", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.activity.HardWareServiceConnection$1 */
    /* loaded from: classes5.dex */
    static final /* synthetic */ class C48151 extends FunctionReference implements Function1<IBinder, HardwareInterface> {
        public static final C48151 INSTANCE = new C48151();

        C48151() {
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

    private HardWareServiceConnection() {
        super("com.pudutech.mirsdk.hardware.HardwareService", C48151.INSTANCE, null, 4, null);
    }

    public final Function4<String, Integer, Integer, Integer, Unit> getOnGeomagneticResult() {
        return onGeomagneticResult;
    }

    public final void setOnGeomagneticResult(Function4<? super String, ? super Integer, ? super Integer, ? super Integer, Unit> function4) {
        onGeomagneticResult = function4;
    }

    public final Function1<Integer, Unit> getOnSensorMagneticResult() {
        return onSensorMagneticResult;
    }

    public final void setOnSensorMagneticResult(Function1<? super Integer, Unit> function1) {
        onSensorMagneticResult = function1;
    }

    public final void addHardwareListener() {
        HardwareInterface hardwareInterface = INSTANCE.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.addListener("hardwareConnection", iHardware);
        }
    }

    public final void removeHardwareListener() {
        HardwareInterface hardwareInterface = INSTANCE.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.removeListener("hardwareConnection");
        }
    }

    @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        super.onServiceConnected(name, service);
        Pdlog.m3273d(TAG, "onServiceConnected has connected");
    }
}
