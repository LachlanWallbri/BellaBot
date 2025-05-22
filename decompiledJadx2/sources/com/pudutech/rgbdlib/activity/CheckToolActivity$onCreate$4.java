package com.pudutech.rgbdlib.activity;

import android.view.View;
import android.widget.Button;
import com.pudutech.rgbdlib.C5657R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes6.dex
 */
/* compiled from: CheckToolActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
final class CheckToolActivity$onCreate$4 implements View.OnClickListener {
    final /* synthetic */ CheckToolActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CheckToolActivity$onCreate$4(CheckToolActivity checkToolActivity) {
        this.this$0 = checkToolActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Boolean bool;
        Boolean bool2;
        CheckToolActivity checkToolActivity = this.this$0;
        bool = checkToolActivity.retSaveImage;
        if (bool == null) {
            Intrinsics.throwNpe();
        }
        checkToolActivity.retSaveImage = Boolean.valueOf(!bool.booleanValue());
        bool2 = this.this$0.retSaveImage;
        if (Intrinsics.areEqual((Object) bool2, (Object) true)) {
            ((Button) this.this$0._$_findCachedViewById(C5657R.id.saveImg_button)).setText(C5657R.string.savingImgButton);
        } else {
            ((Button) this.this$0._$_findCachedViewById(C5657R.id.saveImg_button)).setText(C5657R.string.saveImgButton);
        }
    }
}
