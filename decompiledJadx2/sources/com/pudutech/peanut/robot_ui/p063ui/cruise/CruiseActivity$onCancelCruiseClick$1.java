package com.pudutech.peanut.robot_ui.p063ui.cruise;

import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.viewmodel.CruiseVm;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CruiseActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/peanut/robot_ui/ui/cruise/CruiseActivity$onCancelCruiseClick$1", "Lcom/pudutech/peanut/robot_ui/listener/OnLazyVoiceClickListener;", "onSingleClick", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CruiseActivity$onCancelCruiseClick$1 extends OnLazyVoiceClickListener {
    final /* synthetic */ CruiseActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CruiseActivity$onCancelCruiseClick$1(CruiseActivity cruiseActivity) {
        this.this$0 = cruiseActivity;
    }

    @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
    public void onSingleClick() {
        CruiseVm.CruiseStatus cruiseStatus;
        boolean z;
        int i;
        String str = this.this$0.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onCancelCruiseClick current status = ");
        cruiseStatus = this.this$0.currentEventStatus;
        sb.append(cruiseStatus);
        Pdlog.m3273d(str, sb.toString());
        z = this.this$0.isRelease;
        if (z) {
            Pdlog.m3274e(this.this$0.TAG, "goToCruise failed isRelease ");
            return;
        }
        CruiseActivity cruiseActivity = this.this$0;
        i = cruiseActivity.TYPE_PAUSE_FEATURE_DIALOG;
        cruiseActivity.onPauseFeatureChange(i);
        CruiseActivity cruiseActivity2 = this.this$0;
        String string = cruiseActivity2.getString(C5508R.string.pdStr3_9);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr3_9)");
        cruiseActivity2.showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$onCancelCruiseClick$1$onSingleClick$1
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
                Pdlog.m3273d(CruiseActivity$onCancelCruiseClick$1.this.this$0.TAG, "onCancelCruiseClick dialog onSure");
                CruiseActivity$onCancelCruiseClick$1.this.this$0.getCruiseVm().cancel();
            }
        }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$onCancelCruiseClick$1$onSingleClick$2
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
                CruiseActivity cruiseActivity3 = CruiseActivity$onCancelCruiseClick$1.this.this$0;
                i2 = CruiseActivity$onCancelCruiseClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                cruiseActivity3.onPauseFeatureChange(i2);
            }
        });
    }
}
