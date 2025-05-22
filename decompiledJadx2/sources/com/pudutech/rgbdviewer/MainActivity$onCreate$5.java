package com.pudutech.rgbdviewer;

import android.graphics.Color;
import android.view.View;
import com.pudutech.recycle.robot.p064ui.widget.CFullScreenConfirmDialogFragment;
import com.pudutech.rgbdviewer.RGBDView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: MainActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
final class MainActivity$onCreate$5 implements View.OnClickListener {
    final /* synthetic */ Ref.ObjectRef $ctx;
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MainActivity$onCreate$5(MainActivity mainActivity, Ref.ObjectRef objectRef) {
        this.this$0 = mainActivity;
        this.$ctx = objectRef;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean z;
        z = this.this$0.inited;
        if (z && (MainActivity.access$getLeftView$p(this.this$0).getConfigState() != RGBDView.ConfigState.Configured || MainActivity.access$getRightView$p(this.this$0).getConfigState() != RGBDView.ConfigState.Configured)) {
            CFullScreenConfirmDialogFragment.Builder builder = new CFullScreenConfirmDialogFragment.Builder();
            String string = this.this$0.getResources().getString(2131558499);
            Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.string.unsave_config)");
            CFullScreenConfirmDialogFragment.Builder tips = builder.setTips(string);
            String string2 = this.this$0.getResources().getString(2131558457);
            Intrinsics.checkExpressionValueIsNotNull(string2, "resources.getString(R.string.main_ok)");
            CFullScreenConfirmDialogFragment.Builder positiveButton = tips.setPositiveButton(string2, new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$5$dialogFragment$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    MainActivity$onCreate$5.this.this$0.saveConfig(false);
                    MainActivity$onCreate$5.this.this$0.jump2Robot((MainActivity) MainActivity$onCreate$5.this.$ctx.element);
                }
            });
            String string3 = this.this$0.getResources().getString(2131558452);
            Intrinsics.checkExpressionValueIsNotNull(string3, "resources.getString(R.string.main_cancel)");
            positiveButton.setNegativeButton(string3, new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$5$dialogFragment$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    MainActivity$onCreate$5.this.this$0.jump2Robot(MainActivity$onCreate$5.this.this$0);
                }
            }).setBackgroundColor(Color.parseColor("#30000000")).build().show(this.this$0.getSupportFragmentManager(), MainActivity.class.getSimpleName());
            return;
        }
        MainActivity mainActivity = this.this$0;
        mainActivity.jump2Robot(mainActivity);
    }
}
