package com.pudutech.bumblebee.robot_ui.p054ui;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.recycle_task.TransferDishesPresenter;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TransferDishesActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/TransferDishesActivity$initView$1", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyVoiceClickListener;", "onSingleClick", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TransferDishesActivity$initView$1 extends OnLazyVoiceClickListener {
    final /* synthetic */ TransferDishesActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransferDishesActivity$initView$1(TransferDishesActivity transferDishesActivity) {
        super(null, 0, 3, null);
        this.this$0 = transferDishesActivity;
    }

    @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
    public void onSingleClick() {
        int i;
        TransferDishesActivity transferDishesActivity = this.this$0;
        i = transferDishesActivity.TYPE_PAUSE_FEATURE_DIALOG;
        transferDishesActivity.onPauseFeatureChange(i);
        TransferDishesActivity transferDishesActivity2 = this.this$0;
        String string = transferDishesActivity2.getString(C4188R.string.pdStr16_17);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr16_17)");
        transferDishesActivity2.showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TransferDishesActivity$initView$1$onSingleClick$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                String str;
                TransferDishesPresenter transferDishesPresenter;
                str = TransferDishesActivity$initView$1.this.this$0.TAG;
                Pdlog.m3273d(str, "onPauseEvenClick cancel_ll dialog onSure");
                TransferDishesActivity$initView$1.this.this$0.startDeliverTaskEditActivity(6);
                transferDishesPresenter = TransferDishesActivity$initView$1.this.this$0.getTransferDishesPresenter();
                transferDishesPresenter.actionCancelTask();
            }
        }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TransferDishesActivity$initView$1$onSingleClick$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                int i2;
                TransferDishesActivity transferDishesActivity3 = TransferDishesActivity$initView$1.this.this$0;
                i2 = TransferDishesActivity$initView$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                transferDishesActivity3.onPauseFeatureChange(i2);
            }
        });
    }
}
