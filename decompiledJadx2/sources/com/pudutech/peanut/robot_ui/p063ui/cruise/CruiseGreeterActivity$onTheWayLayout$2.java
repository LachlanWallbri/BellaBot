package com.pudutech.peanut.robot_ui.p063ui.cruise;

import android.view.View;
import android.view.ViewStub;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyClickListener;
import com.pudutech.peanut.robot_ui.p063ui.view.OnTheWayLayout;
import com.pudutech.peanut.robot_ui.viewmodel.GreeterVm;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: CruiseGreeterActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/peanut/robot_ui/ui/view/OnTheWayLayout;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class CruiseGreeterActivity$onTheWayLayout$2 extends Lambda implements Function0<OnTheWayLayout> {
    final /* synthetic */ CruiseGreeterActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CruiseGreeterActivity$onTheWayLayout$2(CruiseGreeterActivity cruiseGreeterActivity) {
        super(0);
        this.this$0 = cruiseGreeterActivity;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final OnTheWayLayout invoke() {
        View inflate = ((ViewStub) this.this$0.findViewById(C5508R.id.on_the_way_layout)).inflate();
        if (inflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.view.OnTheWayLayout");
        }
        OnTheWayLayout onTheWayLayout = (OnTheWayLayout) inflate;
        onTheWayLayout.setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$onTheWayLayout$2$$special$$inlined$apply$lambda$1
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyClickListener
            public void onSingleClick() {
                String str;
                GreeterVm greeterVm;
                str = CruiseGreeterActivity$onTheWayLayout$2.this.this$0.TAG;
                Pdlog.m3273d(str, " on_the_way_layout onSingleClick");
                greeterVm = CruiseGreeterActivity$onTheWayLayout$2.this.this$0.getGreeterVm();
                if (greeterVm != null) {
                    greeterVm.pause();
                }
            }
        });
        return onTheWayLayout;
    }
}
