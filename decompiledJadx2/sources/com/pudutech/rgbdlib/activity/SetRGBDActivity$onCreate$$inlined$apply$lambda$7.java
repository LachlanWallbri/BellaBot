package com.pudutech.rgbdlib.activity;

import android.os.ParcelFileDescriptor;
import android.view.View;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.rgbdlib.RGBDSensor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: SetRGBDActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/pudutech/rgbdlib/activity/SetRGBDActivity$onCreate$Ninetales2RGBD$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class SetRGBDActivity$onCreate$$inlined$apply$lambda$7 implements View.OnClickListener {
    final /* synthetic */ SetRGBDActivity this$0;

    SetRGBDActivity$onCreate$$inlined$apply$lambda$7(SetRGBDActivity setRGBDActivity) {
        this.this$0 = setRGBDActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function4;
        Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function42;
        Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function43;
        Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function44;
        if (SetRGBDActivity.INSTANCE.getRgbdSensor() == null) {
            SetRGBDActivity.INSTANCE.setRgbdSensor(new RGBDSensor(this.this$0));
        }
        RGBDSensor rgbdSensor = SetRGBDActivity.INSTANCE.getRgbdSensor();
        if (rgbdSensor != null) {
            MachineInfo.RGBDType rGBDType = MachineInfo.RGBDType.TwoDevice;
            int id = MachineModel.Ninetales.getId();
            function4 = this.this$0.leftRGBDSubscription;
            function42 = this.this$0.rightRGBDSubscription;
            function43 = this.this$0.centerRGBDSubscription;
            function44 = this.this$0.downRGBDSubscription;
            rgbdSensor.start(rGBDType, id, function4, function42, function43, function44);
        }
        RGBDSensor rgbdSensor2 = SetRGBDActivity.INSTANCE.getRgbdSensor();
        if (rgbdSensor2 != null) {
            SetRGBDActivity setRGBDActivity = this.this$0;
            RGBDSensor rgbdSensor3 = SetRGBDActivity.INSTANCE.getRgbdSensor();
            if (rgbdSensor3 == null) {
                Intrinsics.throwNpe();
            }
            rgbdSensor2.startPreviewActivity(setRGBDActivity, rgbdSensor3);
        }
    }
}
