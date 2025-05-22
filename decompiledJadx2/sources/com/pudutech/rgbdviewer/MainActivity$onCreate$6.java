package com.pudutech.rgbdviewer;

import android.graphics.Color;
import android.view.View;
import com.pudutech.recycle.robot.p064ui.widget.CFullScreenConfirmDialogFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
final class MainActivity$onCreate$6 implements View.OnClickListener {
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MainActivity$onCreate$6(MainActivity mainActivity) {
        this.this$0 = mainActivity;
    }

    /* compiled from: MainActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "run"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.rgbdviewer.MainActivity$onCreate$6$1 */
    /* loaded from: classes.dex */
    static final class RunnableC56901 implements Runnable {
        RunnableC56901() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            CFullScreenConfirmDialogFragment.Builder builder = new CFullScreenConfirmDialogFragment.Builder();
            String string = MainActivity$onCreate$6.this.this$0.getResources().getString(2131558460);
            Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.st…ng.make_sure_save_config)");
            CFullScreenConfirmDialogFragment.Builder tips = builder.setTips(string);
            String string2 = MainActivity$onCreate$6.this.this$0.getResources().getString(2131558457);
            Intrinsics.checkExpressionValueIsNotNull(string2, "resources.getString(R.string.main_ok)");
            CFullScreenConfirmDialogFragment.Builder positiveButton = tips.setPositiveButton(string2, new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$6$1$dialogFragment$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity$onCreate$6.this.this$0.saveConfig(true);
                }
            });
            String string3 = MainActivity$onCreate$6.this.this$0.getResources().getString(2131558452);
            Intrinsics.checkExpressionValueIsNotNull(string3, "resources.getString(R.string.main_cancel)");
            positiveButton.setNegativeButton(string3, new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$6$1$dialogFragment$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                }
            }).setBackgroundColor(Color.parseColor("#30000000")).build().show(MainActivity$onCreate$6.this.this$0.getSupportFragmentManager(), MainActivity.class.getSimpleName());
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.this$0.runOnUiThread(new RunnableC56901());
    }
}
