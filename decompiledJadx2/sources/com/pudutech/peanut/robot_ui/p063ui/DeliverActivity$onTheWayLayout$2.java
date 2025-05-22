package com.pudutech.peanut.robot_ui.p063ui;

import android.view.View;
import android.view.ViewStub;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyClickListener;
import com.pudutech.peanut.robot_ui.p063ui.view.OnTheWayLayout;
import com.pudutech.peanut.robot_ui.viewmodel.DeliverVm;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: DeliverActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/peanut/robot_ui/ui/view/OnTheWayLayout;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class DeliverActivity$onTheWayLayout$2 extends Lambda implements Function0<OnTheWayLayout> {
    final /* synthetic */ DeliverActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeliverActivity$onTheWayLayout$2(DeliverActivity deliverActivity) {
        super(0);
        this.this$0 = deliverActivity;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final OnTheWayLayout invoke() {
        int i;
        View inflate = ((ViewStub) this.this$0.findViewById(C5508R.id.on_the_way_layout)).inflate();
        if (inflate != null) {
            OnTheWayLayout onTheWayLayout = (OnTheWayLayout) inflate;
            i = this.this$0.currentMode;
            if (i == 4) {
                onTheWayLayout.setType(onTheWayLayout.getTYPE_CALL());
            }
            onTheWayLayout.setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onTheWayLayout$2$$special$$inlined$apply$lambda$1
                @Override // com.pudutech.peanut.robot_ui.listener.OnLazyClickListener
                public void onSingleClick() {
                    String str;
                    DeliverVm.DeliverStatus deliverStatus;
                    DeliverVm deliverVm;
                    str = DeliverActivity$onTheWayLayout$2.this.this$0.TAG;
                    Pdlog.m3273d(str, " on_the_way_layout onSingleClick");
                    deliverStatus = DeliverActivity$onTheWayLayout$2.this.this$0.currentEventStatus;
                    if (deliverStatus == DeliverVm.DeliverStatus.Moving) {
                        deliverVm = DeliverActivity$onTheWayLayout$2.this.this$0.getDeliverVm();
                        deliverVm.pause();
                    }
                }
            });
            return onTheWayLayout;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.view.OnTheWayLayout");
    }
}
