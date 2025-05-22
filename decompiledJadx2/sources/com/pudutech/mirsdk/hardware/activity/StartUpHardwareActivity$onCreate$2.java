package com.pudutech.mirsdk.hardware.activity;

import android.widget.Button;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.activity.StartUpHardwareActivity;
import com.pudutech.mirsdk.hardware.library.C5193R;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: StartUpHardwareActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000I\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0005H\u0016J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0005H\u0016J\u0012\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J \u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0015H\u0016J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0005H\u0016J1\u0010\u001d\u001a\u00020\u00032\u0010\u0010\u0007\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u001f\u0018\u00010\u001e2\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u001f\u0018\u00010\u001eH\u0016¢\u0006\u0002\u0010 ¨\u0006!"}, m3961d2 = {"com/pudutech/mirsdk/hardware/activity/StartUpHardwareActivity$onCreate$2", "Lcom/pudutech/mirsdk/hardware/IHardware$Stub;", "onAntiCollisionSwitch", "", "pressed", "", "onBattery", "p0", "", "p1", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "onBatterySOH", ES6Iterator.VALUE_PROPERTY, "onCameraIRDLED", "onConnectedChargingPile", "onDisinfectionPower", "powerOn", "onHarewareEmergencyBrake", "state", "onHarewareInfoReport", "info", "", "onOpenStep", "step", "Lcom/pudutech/mirsdk/hardware/serialize/HardwareOpenStep;", "stepState", "Lcom/pudutech/mirsdk/hardware/serialize/StepState;", "description", "onSlamCorePower", "onWheelError", "", "Lcom/pudutech/mirsdk/hardware/serialize/WheelError;", "([Lcom/pudutech/mirsdk/hardware/serialize/WheelError;[Lcom/pudutech/mirsdk/hardware/serialize/WheelError;)V", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class StartUpHardwareActivity$onCreate$2 extends IHardware.Stub {
    final /* synthetic */ StartUpHardwareActivity this$0;

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onAntiCollisionSwitch(boolean pressed) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onBattery(int p0, ChargeState p1) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onBatterySOH(int value) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onCameraIRDLED(boolean p0) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onConnectedChargingPile() {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onDisinfectionPower(boolean powerOn) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onHarewareEmergencyBrake(boolean state) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onHarewareInfoReport(String info) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onSlamCorePower(boolean powerOn) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onWheelError(WheelError[] p0, WheelError[] p1) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StartUpHardwareActivity$onCreate$2(StartUpHardwareActivity startUpHardwareActivity) {
        this.this$0 = startUpHardwareActivity;
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onOpenStep(final HardwareOpenStep step, final StepState stepState, final String description) {
        Intrinsics.checkParameterIsNotNull(step, "step");
        Intrinsics.checkParameterIsNotNull(stepState, "stepState");
        Intrinsics.checkParameterIsNotNull(description, "description");
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.hardware.activity.StartUpHardwareActivity$onCreate$2$onOpenStep$1
            @Override // java.lang.Runnable
            public final void run() {
                Object obj;
                Iterator<T> it = StartUpHardwareActivity$onCreate$2.this.this$0.getStepStateList().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    } else {
                        obj = it.next();
                        if (Intrinsics.areEqual(((StartUpHardwareActivity.InitState) obj).getStep(), step.toString())) {
                            break;
                        }
                    }
                }
                StartUpHardwareActivity.InitState initState = (StartUpHardwareActivity.InitState) obj;
                if (initState == null) {
                    StartUpHardwareActivity$onCreate$2.this.this$0.getStepStateList().add(0, new StartUpHardwareActivity.InitState(step.toString(), stepState, description));
                } else {
                    initState.setState(stepState);
                    initState.setDescription(description);
                }
                StartUpHardwareActivity$onCreate$2.this.this$0.getListAdapter().setData(StartUpHardwareActivity$onCreate$2.this.this$0.getStepStateList());
                StartUpHardwareActivity$onCreate$2.this.this$0.getListAdapter().notifyDataSetChanged();
                if (step == HardwareOpenStep.Finish) {
                    if (stepState == StepState.Success) {
                        StartUpHardwareActivity$onCreate$2.this.this$0.setCurrentState(StartUpHardwareActivity.StartUpState.Finish);
                        Button buttonAction = (Button) StartUpHardwareActivity$onCreate$2.this.this$0._$_findCachedViewById(C5193R.id.buttonAction);
                        Intrinsics.checkExpressionValueIsNotNull(buttonAction, "buttonAction");
                        buttonAction.setText("Finish");
                    } else {
                        StartUpHardwareActivity$onCreate$2.this.this$0.setCurrentState(StartUpHardwareActivity.StartUpState.NeedStartUp);
                        Button buttonAction2 = (Button) StartUpHardwareActivity$onCreate$2.this.this$0._$_findCachedViewById(C5193R.id.buttonAction);
                        Intrinsics.checkExpressionValueIsNotNull(buttonAction2, "buttonAction");
                        buttonAction2.setText("StartUp");
                    }
                    Button buttonAction3 = (Button) StartUpHardwareActivity$onCreate$2.this.this$0._$_findCachedViewById(C5193R.id.buttonAction);
                    Intrinsics.checkExpressionValueIsNotNull(buttonAction3, "buttonAction");
                    buttonAction3.setEnabled(true);
                }
            }
        });
    }
}
