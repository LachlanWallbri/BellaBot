package com.pudutech.peanut.robot_ui.p063ui.greeter;

import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.viewmodel.WelComeVm;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GotoWelcomeAreaActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/peanut/robot_ui/ui/greeter/GotoWelcomeAreaActivity$onCancelGreeterBackClick$1", "Lcom/pudutech/peanut/robot_ui/listener/OnLazyVoiceClickListener;", "onSingleClick", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class GotoWelcomeAreaActivity$onCancelGreeterBackClick$1 extends OnLazyVoiceClickListener {
    final /* synthetic */ GotoWelcomeAreaActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GotoWelcomeAreaActivity$onCancelGreeterBackClick$1(GotoWelcomeAreaActivity gotoWelcomeAreaActivity) {
        this.this$0 = gotoWelcomeAreaActivity;
    }

    @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
    public void onSingleClick() {
        String str;
        WelComeVm.WelComeStatus welComeStatus;
        int i;
        str = this.this$0.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onCancelGreeterBackClick current status = ");
        welComeStatus = this.this$0.currentEventStatus;
        sb.append(welComeStatus);
        Pdlog.m3273d(str, sb.toString());
        GotoWelcomeAreaActivity gotoWelcomeAreaActivity = this.this$0;
        i = gotoWelcomeAreaActivity.TYPE_PAUSE_FEATURE_DIALOG;
        gotoWelcomeAreaActivity.onPauseFeatureChange(i);
        String text = this.this$0.getString(C5508R.string.pdStr25_9);
        this.this$0.functionClickTime = System.currentTimeMillis();
        GotoWelcomeAreaActivity gotoWelcomeAreaActivity2 = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(text, "text");
        gotoWelcomeAreaActivity2.showConfirmDialog(text, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$onCancelGreeterBackClick$1$onSingleClick$1
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
                WelComeVm welComeVm;
                str2 = GotoWelcomeAreaActivity$onCancelGreeterBackClick$1.this.this$0.TAG;
                Pdlog.m3273d(str2, "onCancelGreeterBackClick dialog onSure");
                welComeVm = GotoWelcomeAreaActivity$onCancelGreeterBackClick$1.this.this$0.getWelComeVm();
                welComeVm.cancel();
            }
        }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$onCancelGreeterBackClick$1$onSingleClick$2
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
                GotoWelcomeAreaActivity gotoWelcomeAreaActivity3 = GotoWelcomeAreaActivity$onCancelGreeterBackClick$1.this.this$0;
                i2 = GotoWelcomeAreaActivity$onCancelGreeterBackClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                gotoWelcomeAreaActivity3.onPauseFeatureChange(i2);
            }
        });
    }
}
