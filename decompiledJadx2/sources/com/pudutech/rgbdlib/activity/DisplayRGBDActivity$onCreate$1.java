package com.pudutech.rgbdlib.activity;

import android.view.View;
import com.pudutech.rgbdlib.RGBDSensor;
import kotlin.Metadata;

/* compiled from: DisplayRGBDActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class DisplayRGBDActivity$onCreate$1 implements View.OnClickListener {
    public static final DisplayRGBDActivity$onCreate$1 INSTANCE = new DisplayRGBDActivity$onCreate$1();

    DisplayRGBDActivity$onCreate$1() {
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        RGBDSensor rgbdSensor = DisplayRGBDActivity.INSTANCE.getRgbdSensor();
        if (rgbdSensor != null) {
            rgbdSensor.openRGBD();
        }
    }
}
