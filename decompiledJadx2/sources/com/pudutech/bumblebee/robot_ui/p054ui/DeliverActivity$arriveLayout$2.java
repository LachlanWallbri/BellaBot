package com.pudutech.bumblebee.robot_ui.p054ui;

import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: DeliverActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "Landroidx/constraintlayout/widget/ConstraintLayout;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
final class DeliverActivity$arriveLayout$2 extends Lambda implements Function0<ConstraintLayout> {
    final /* synthetic */ DeliverActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeliverActivity$arriveLayout$2(DeliverActivity deliverActivity) {
        super(0);
        this.this$0 = deliverActivity;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final ConstraintLayout invoke() {
        String str;
        View inflate = ((ViewStub) this.this$0.findViewById(C4188R.id.arrive_cl_stub)).inflate();
        if (inflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) inflate;
        ConstraintLayout constraintLayout2 = constraintLayout;
        ViewExtKt.gone(constraintLayout2);
        TextView target_tv = (TextView) constraintLayout2.findViewById(C4188R.id.target_tv);
        Intrinsics.checkExpressionValueIsNotNull(target_tv, "target_tv");
        str = this.this$0.mTargetName;
        target_tv.setText(str);
        ((Button) constraintLayout2.findViewById(C4188R.id.complete_btn)).setOnClickListener(new SingleClickListener(null, 0, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$arriveLayout$2$$special$$inlined$apply$lambda$1
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
                DeliverActivity$arriveLayout$2.this.this$0.callReturn();
            }
        }, 3, null));
        return constraintLayout;
    }
}
