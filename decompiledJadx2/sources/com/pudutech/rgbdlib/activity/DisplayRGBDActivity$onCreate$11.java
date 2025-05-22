package com.pudutech.rgbdlib.activity;

import android.widget.CompoundButton;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: DisplayRGBDActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, m3961d2 = {"<anonymous>", "", "buttonView", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class DisplayRGBDActivity$onCreate$11 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ DisplayRGBDActivity this$0;

    DisplayRGBDActivity$onCreate$11(DisplayRGBDActivity displayRGBDActivity) {
        this.this$0 = displayRGBDActivity;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        String str;
        DisplayRGBDActivity.access$setUseNewAlgorithm$p(this.this$0, !z);
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "cb_use_cape_line : ischecked = " + z);
    }
}
