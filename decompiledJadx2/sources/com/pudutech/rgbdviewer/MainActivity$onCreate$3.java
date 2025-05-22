package com.pudutech.rgbdviewer;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.pudutech.base.Pdlog;
import com.pudutech.recycle.robot.p064ui.widget.CFullScreenConfirmDialogFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
final class MainActivity$onCreate$3 implements View.OnClickListener {
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MainActivity$onCreate$3(MainActivity mainActivity) {
        this.this$0 = mainActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "errorCheckButton on click");
        MainActivity.access$getRgbdSensor$p(this.this$0).startErrorCheck();
        View findViewById = this.this$0.findViewById(2131230923);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<LinearLayout>(R.id.mainLayout)");
        ((LinearLayout) findViewById).setVisibility(8);
        View findViewById2 = this.this$0.findViewById(2131230878);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById<LinearLayout>(R.id.errorCheckLayout)");
        ((LinearLayout) findViewById2).setVisibility(0);
        ((Button) this.this$0.findViewById(2131230879)).setOnClickListener(new ViewOnClickListenerC56891());
    }

    /* compiled from: MainActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.rgbdviewer.MainActivity$onCreate$3$1 */
    /* loaded from: classes.dex */
    static final class ViewOnClickListenerC56891 implements View.OnClickListener {
        ViewOnClickListenerC56891() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            MainActivity.access$getRgbdSensor$p(MainActivity$onCreate$3.this.this$0).stopErrorCheck();
            new CFullScreenConfirmDialogFragment.Builder().setTips("是否保存误差检测结果图片？").setPositiveButton("是", new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$3$1$dialogFragment$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    MainActivity$onCreate$3.this.this$0.saveErrorDetectPic();
                    View findViewById = MainActivity$onCreate$3.this.this$0.findViewById(2131230878);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<LinearLayout>(R.id.errorCheckLayout)");
                    ((LinearLayout) findViewById).setVisibility(8);
                    View findViewById2 = MainActivity$onCreate$3.this.this$0.findViewById(2131230923);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById<LinearLayout>(R.id.mainLayout)");
                    ((LinearLayout) findViewById2).setVisibility(0);
                }
            }).setNegativeButton("否", new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$3$1$dialogFragment$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    View findViewById = MainActivity$onCreate$3.this.this$0.findViewById(2131230878);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<LinearLayout>(R.id.errorCheckLayout)");
                    ((LinearLayout) findViewById).setVisibility(8);
                    View findViewById2 = MainActivity$onCreate$3.this.this$0.findViewById(2131230923);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById<LinearLayout>(R.id.mainLayout)");
                    ((LinearLayout) findViewById2).setVisibility(0);
                }
            }).setBackgroundColor(Color.parseColor("#30000000")).build().show(MainActivity$onCreate$3.this.this$0.getSupportFragmentManager(), MainActivity.class.getSimpleName());
        }
    }
}
