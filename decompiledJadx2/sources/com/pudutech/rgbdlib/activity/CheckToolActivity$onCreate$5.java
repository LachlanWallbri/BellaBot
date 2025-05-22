package com.pudutech.rgbdlib.activity;

import android.view.View;
import android.widget.Button;
import com.pudutech.rgbdlib.C5657R;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: CheckToolActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class CheckToolActivity$onCreate$5 implements View.OnClickListener {
    final /* synthetic */ CheckToolActivity this$0;

    CheckToolActivity$onCreate$5(CheckToolActivity checkToolActivity) {
        this.this$0 = checkToolActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean z;
        boolean z2;
        CheckToolActivity checkToolActivity = this.this$0;
        z = checkToolActivity.retSaveImage;
        CheckToolActivity.access$setRetSaveImage$p(checkToolActivity, !z);
        z2 = this.this$0.retSaveImage;
        if (z2) {
            ((Button) this.this$0._$_findCachedViewById(C5657R.id.saveImg_button)).setText(C5657R.string.savingImgButton);
        } else {
            ((Button) this.this$0._$_findCachedViewById(C5657R.id.saveImg_button)).setText(C5657R.string.saveImgButton);
        }
    }
}
