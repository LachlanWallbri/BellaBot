package com.pudutech.rgbdlib.activity;

import android.view.View;
import android.widget.Toast;
import com.pudutech.base.Pdlog;
import com.pudutech.rgbdlib.RGBDSensor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DisplayRGBDActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class DisplayRGBDActivity$onCreate$12 implements View.OnClickListener {
    final /* synthetic */ DisplayRGBDActivity this$0;

    DisplayRGBDActivity$onCreate$12(DisplayRGBDActivity displayRGBDActivity) {
        this.this$0 = displayRGBDActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        String str2;
        if (DisplayRGBDActivity.access$getUseNewAlgorithm$p(this.this$0)) {
            str2 = this.this$0.TAG;
            Pdlog.m3273d(str2, "新算法 line seg");
            Toast.makeText(this.this$0, "using line seg", 1).show();
            RGBDSensor rgbdSensor = DisplayRGBDActivity.INSTANCE.getRgbdSensor();
            if (rgbdSensor != null) {
                DisplayRGBDActivity displayRGBDActivity = this.this$0;
                RGBDSensor rgbdSensor2 = DisplayRGBDActivity.INSTANCE.getRgbdSensor();
                if (rgbdSensor2 == null) {
                    Intrinsics.throwNpe();
                }
                rgbdSensor.startCheckToolActivity(displayRGBDActivity, rgbdSensor2, true);
                return;
            }
            return;
        }
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "旧算法 cape seg");
        Toast.makeText(this.this$0, "using cape seg", 1).show();
        RGBDSensor rgbdSensor3 = DisplayRGBDActivity.INSTANCE.getRgbdSensor();
        if (rgbdSensor3 != null) {
            DisplayRGBDActivity displayRGBDActivity2 = this.this$0;
            RGBDSensor rgbdSensor4 = DisplayRGBDActivity.INSTANCE.getRgbdSensor();
            if (rgbdSensor4 == null) {
                Intrinsics.throwNpe();
            }
            RGBDSensor.startCheckToolActivity$default(rgbdSensor3, displayRGBDActivity2, rgbdSensor4, false, 4, null);
        }
    }
}
