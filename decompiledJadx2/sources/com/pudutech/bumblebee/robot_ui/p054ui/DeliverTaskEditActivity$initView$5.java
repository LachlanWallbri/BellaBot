package com.pudutech.bumblebee.robot_ui.p054ui;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: DeliverTaskEditActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/DeliverTaskEditActivity$initView$5", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyVoiceClickListener;", "onSingleClick", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DeliverTaskEditActivity$initView$5 extends OnLazyVoiceClickListener {
    final /* synthetic */ DeliverTaskEditActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeliverTaskEditActivity$initView$5(DeliverTaskEditActivity deliverTaskEditActivity) {
        super(null, 0, 3, null);
        this.this$0 = deliverTaskEditActivity;
    }

    @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
    public void onSingleClick() {
        if (!Constans.INSTANCE.getDeliverExitSwitch()) {
            this.this$0.gotoquitFillIn();
        } else {
            this.this$0.showPasswordDialog(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initView$5$onSingleClick$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    String str;
                    String str2;
                    if (z) {
                        DeliverTaskEditActivity$initView$5.this.this$0.gotoquitFillIn();
                        str2 = DeliverTaskEditActivity$initView$5.this.this$0.TAG;
                        Pdlog.m3273d(str2, "btn_go_to_task_ui  password sure cancel");
                    } else {
                        str = DeliverTaskEditActivity$initView$5.this.this$0.TAG;
                        Pdlog.m3273d(str, "btn_go_to_task_ui  password nosure");
                    }
                }
            }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initView$5$onSingleClick$2
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
                    str = DeliverTaskEditActivity$initView$5.this.this$0.TAG;
                    Pdlog.m3273d(str, "btn_go_to_task_ui  cancel");
                }
            });
        }
    }
}
