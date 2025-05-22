package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.pudutech.bumblebee.robot_ui.C4188R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: App4GTipDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0010\u001a\u00020\u0007H\u0016J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0014R7\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/App4GTipDialog;", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/BumbleBaseDialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onClick", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "type", "", "getOnClick", "()Lkotlin/jvm/functions/Function1;", "setOnClick", "(Lkotlin/jvm/functions/Function1;)V", "getLayoutId", "initView", "view", "Landroid/view/View;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class App4GTipDialog extends BumbleBaseDialog {
    private Function1<? super Integer, Unit> onClick;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public App4GTipDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    public final Function1<Integer, Unit> getOnClick() {
        return this.onClick;
    }

    public final void setOnClick(Function1<? super Integer, Unit> function1) {
        this.onClick = function1;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public int getLayoutId() {
        return C4188R.layout.dialog_4g_tip;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public void initView(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.initView(view);
        ((TextView) findViewById(C4188R.id.tip_4g_sure)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.App4GTipDialog$initView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                Function1<Integer, Unit> onClick = App4GTipDialog.this.getOnClick();
                if (onClick != null) {
                    onClick.invoke(1);
                }
                App4GTipDialog.this.dismiss();
            }
        });
        ((ImageView) findViewById(C4188R.id.tip_4g_close)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.App4GTipDialog$initView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                Function1<Integer, Unit> onClick = App4GTipDialog.this.getOnClick();
                if (onClick != null) {
                    onClick.invoke(0);
                }
                App4GTipDialog.this.dismiss();
            }
        });
        ((TextView) findViewById(C4188R.id.tip_4g_Negative)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.App4GTipDialog$initView$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                Function1<Integer, Unit> onClick = App4GTipDialog.this.getOnClick();
                if (onClick != null) {
                    onClick.invoke(0);
                }
                App4GTipDialog.this.dismiss();
            }
        });
    }
}
