package com.pudutech.factory_test.single_test;

import android.widget.Toast;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;

/* compiled from: MotorTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000C\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\f\u001a\u00020\u0003H\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u000f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0010H\u0016J&\u0010\u0011\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00122\b\u0010\b\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J1\u0010\u0016\u001a\u00020\u00032\u0010\u0010\u0004\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0018\u0018\u00010\u00172\u0010\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0018\u0018\u00010\u0017H\u0016¢\u0006\u0002\u0010\u0019¨\u0006\u001a"}, m3961d2 = {"com/pudutech/factory_test/single_test/MotorTestActivity$setListener2$1", "Lcom/pudutech/mirsdk/hardware/IHardware$Stub;", "onAntiCollisionSwitch", "", "p0", "", "onBattery", "", "p1", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "onBatterySOH", "onCameraIRDLED", "onConnectedChargingPile", "onDisinfectionPower", "onHarewareEmergencyBrake", "onHarewareInfoReport", "", "onOpenStep", "Lcom/pudutech/mirsdk/hardware/serialize/HardwareOpenStep;", "Lcom/pudutech/mirsdk/hardware/serialize/StepState;", "p2", "onSlamCorePower", "onWheelError", "", "Lcom/pudutech/mirsdk/hardware/serialize/WheelError;", "([Lcom/pudutech/mirsdk/hardware/serialize/WheelError;[Lcom/pudutech/mirsdk/hardware/serialize/WheelError;)V", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class MotorTestActivity$setListener2$1 extends IHardware.Stub {
    final /* synthetic */ MotorTestActivity this$0;

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onBattery(int p0, ChargeState p1) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onBatterySOH(int p0) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onCameraIRDLED(boolean p0) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onConnectedChargingPile() {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onDisinfectionPower(boolean p0) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onHarewareInfoReport(String p0) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onOpenStep(HardwareOpenStep p0, StepState p1, String p2) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onSlamCorePower(boolean p0) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MotorTestActivity$setListener2$1(MotorTestActivity motorTestActivity) {
        this.this$0 = motorTestActivity;
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onHarewareEmergencyBrake(boolean p0) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onHarewareEmergencyBrake p0=" + p0);
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onAntiCollisionSwitch(boolean p0) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onAntiCollisionSwitch p0=" + p0);
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onWheelError(WheelError[] p0, WheelError[] p1) {
        String str;
        str = this.this$0.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("onWheelError p0=");
        sb.append(p0 != null ? ArraysKt.toList(p0) : null);
        sb.append("  p1=");
        sb.append(p1 != null ? ArraysKt.toList(p1) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if ((p0 == null || !ArraysKt.contains(p0, WheelError.EmergencyKeyPressed)) && (p1 == null || !ArraysKt.contains(p1, WheelError.EmergencyKeyPressed))) {
            this.this$0.isLoaded = false;
        } else {
            System.currentTimeMillis();
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.single_test.MotorTestActivity$setListener2$1$onWheelError$1
                @Override // java.lang.Runnable
                public final void run() {
                    boolean z;
                    String str2;
                    boolean z2;
                    z = MotorTestActivity$setListener2$1.this.this$0.isLoaded;
                    if (z) {
                        return;
                    }
                    MotorTestActivity$setListener2$1.this.this$0.isLoaded = true;
                    str2 = MotorTestActivity$setListener2$1.this.this$0.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("isloaded: ");
                    z2 = MotorTestActivity$setListener2$1.this.this$0.isLoaded;
                    sb2.append(z2);
                    Pdlog.m3273d(str2, sb2.toString());
                    Toast.makeText(MotorTestActivity$setListener2$1.this.this$0, "紧急开关被按下", 0).show();
                }
            });
        }
    }
}
