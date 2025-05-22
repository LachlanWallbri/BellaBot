package com.pudutech.bumblebee.robot_ui.advertise;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdverTaskDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0014R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdverTaskDialog;", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/BumbleBaseDialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onYesBack", "Lkotlin/Function0;", "", "getOnYesBack", "()Lkotlin/jvm/functions/Function0;", "setOnYesBack", "(Lkotlin/jvm/functions/Function0;)V", "getLayoutId", "", "initView", "view", "Landroid/view/View;", "isOpenReset", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AdverTaskDialog extends BumbleBaseDialog {
    private Function0<Unit> onYesBack;

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    protected boolean isOpenReset() {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdverTaskDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    public final Function0<Unit> getOnYesBack() {
        return this.onYesBack;
    }

    public final void setOnYesBack(Function0<Unit> function0) {
        this.onYesBack = function0;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public int getLayoutId() {
        return C4188R.layout.dialog_adver_task;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public void initView(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.initView(view);
        ((TextView) findViewById(C4188R.id.adver_dialog_no)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdverTaskDialog$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                AdverTaskDialog.this.dismiss();
            }
        }, 3, null));
        ((TextView) findViewById(C4188R.id.adver_dialog_yes)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdverTaskDialog$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function0<Unit> onYesBack = AdverTaskDialog.this.getOnYesBack();
                if (onYesBack != null) {
                    onYesBack.invoke();
                }
                AdverTaskDialog.this.dismiss();
            }
        }, 3, null));
    }
}
