package com.pudutech.bumblebee.robot_ui.p054ui;

import android.content.DialogInterface;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.DeliveryHistoryTaskDialog;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import kotlin.Metadata;

/* compiled from: DeliverTaskEditActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/DeliverTaskEditActivity$initView$8", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyVoiceClickListener;", "onSingleClick", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DeliverTaskEditActivity$initView$8 extends OnLazyVoiceClickListener {
    final /* synthetic */ DeliverTaskEditActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeliverTaskEditActivity$initView$8(DeliverTaskEditActivity deliverTaskEditActivity) {
        super(null, 0, 3, null);
        this.this$0 = deliverTaskEditActivity;
    }

    @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
    public void onSingleClick() {
        int i;
        super.onSingleClick();
        DeliveryHistoryTaskDialog deliveryHistoryTaskDialog = new DeliveryHistoryTaskDialog(this.this$0);
        deliveryHistoryTaskDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initView$8$onSingleClick$1
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                int i2;
                DeliverTaskEditActivity deliverTaskEditActivity = DeliverTaskEditActivity$initView$8.this.this$0;
                i2 = DeliverTaskEditActivity$initView$8.this.this$0.TYPE_FEATURE_NOMAL;
                deliverTaskEditActivity.onFeatureChange(i2);
            }
        });
        deliveryHistoryTaskDialog.show();
        DeliverTaskEditActivity deliverTaskEditActivity = this.this$0;
        i = deliverTaskEditActivity.TYPE_FEATURE_DIALOG;
        deliverTaskEditActivity.onFeatureChange(i);
    }
}
