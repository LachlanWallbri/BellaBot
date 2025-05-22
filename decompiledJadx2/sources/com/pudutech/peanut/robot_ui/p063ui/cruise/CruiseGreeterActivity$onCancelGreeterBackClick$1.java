package com.pudutech.peanut.robot_ui.p063ui.cruise;

import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.viewmodel.GreeterVm;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CruiseGreeterActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/peanut/robot_ui/ui/cruise/CruiseGreeterActivity$onCancelGreeterBackClick$1", "Lcom/pudutech/peanut/robot_ui/listener/OnLazyVoiceClickListener;", "onSingleClick", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CruiseGreeterActivity$onCancelGreeterBackClick$1 extends OnLazyVoiceClickListener {
    final /* synthetic */ CruiseGreeterActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CruiseGreeterActivity$onCancelGreeterBackClick$1(CruiseGreeterActivity cruiseGreeterActivity) {
        this.this$0 = cruiseGreeterActivity;
    }

    @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
    public void onSingleClick() {
        String str;
        GreeterVm.GreeterStatus greeterStatus;
        int i;
        str = this.this$0.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onCancelGreeterBackClick current status = ");
        greeterStatus = this.this$0.currentEventStatus;
        sb.append(greeterStatus);
        Pdlog.m3273d(str, sb.toString());
        this.this$0.functionClickTime = System.currentTimeMillis();
        CruiseGreeterActivity cruiseGreeterActivity = this.this$0;
        i = cruiseGreeterActivity.TYPE_PAUSE_FEATURE_DIALOG;
        cruiseGreeterActivity.onPauseFeatureChange(i);
        String text = this.this$0.getString(C5508R.string.pdStr25_8);
        CruiseGreeterActivity cruiseGreeterActivity2 = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(text, "text");
        cruiseGreeterActivity2.showConfirmDialog(text, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$onCancelGreeterBackClick$1$onSingleClick$1
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
                String str2;
                GreeterVm greeterVm;
                str2 = CruiseGreeterActivity$onCancelGreeterBackClick$1.this.this$0.TAG;
                Pdlog.m3273d(str2, "onCancelGreeterBackClick dialog onSure");
                greeterVm = CruiseGreeterActivity$onCancelGreeterBackClick$1.this.this$0.getGreeterVm();
                greeterVm.cancel();
            }
        }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$onCancelGreeterBackClick$1$onSingleClick$2
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
                CruiseGreeterActivity cruiseGreeterActivity3 = CruiseGreeterActivity$onCancelGreeterBackClick$1.this.this$0;
                i2 = CruiseGreeterActivity$onCancelGreeterBackClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                cruiseGreeterActivity3.onPauseFeatureChange(i2);
            }
        });
    }
}
