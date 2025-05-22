package com.pudutech.rgbdlib.activity;

import android.view.View;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;

/* compiled from: CheckToolActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class CheckToolActivity$onCreate$6 implements View.OnClickListener {
    final /* synthetic */ CheckToolActivity this$0;

    CheckToolActivity$onCreate$6(CheckToolActivity checkToolActivity) {
        this.this$0 = checkToolActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Pdlog.m3273d(CheckToolActivity.access$getTAG$p(this.this$0), "force pass ");
        CheckToolActivity.access$getForcePass$p(this.this$0).set(true);
    }
}
