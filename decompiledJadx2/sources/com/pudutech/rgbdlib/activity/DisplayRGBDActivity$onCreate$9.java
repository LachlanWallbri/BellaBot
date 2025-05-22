package com.pudutech.rgbdlib.activity;

import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import com.pudutech.rgbdlib.C5657R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: DisplayRGBDActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class DisplayRGBDActivity$onCreate$9 implements View.OnClickListener {
    final /* synthetic */ DisplayRGBDActivity this$0;

    DisplayRGBDActivity$onCreate$9(DisplayRGBDActivity displayRGBDActivity) {
        this.this$0 = displayRGBDActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean z;
        boolean z2;
        if (DisplayRGBDActivity.access$getEnableSheild$p(this.this$0)) {
            DisplayRGBDActivity displayRGBDActivity = this.this$0;
            z = displayRGBDActivity.sheildDownRgbd;
            displayRGBDActivity.sheildDownRgbd = !z;
            z2 = this.this$0.sheildDownRgbd;
            if (z2) {
                AppCompatButton button_down = (AppCompatButton) this.this$0._$_findCachedViewById(C5657R.id.button_down);
                Intrinsics.checkExpressionValueIsNotNull(button_down, "button_down");
                button_down.setText(this.this$0.getString(C5657R.string.sheild));
                ((AppCompatButton) this.this$0._$_findCachedViewById(C5657R.id.button_down)).setTextColor(SupportMenu.CATEGORY_MASK);
                return;
            }
            AppCompatButton button_down2 = (AppCompatButton) this.this$0._$_findCachedViewById(C5657R.id.button_down);
            Intrinsics.checkExpressionValueIsNotNull(button_down2, "button_down");
            button_down2.setText(this.this$0.getString(C5657R.string.dButton));
            ((AppCompatButton) this.this$0._$_findCachedViewById(C5657R.id.button_down)).setTextColor(ViewCompat.MEASURED_STATE_MASK);
        }
    }
}
