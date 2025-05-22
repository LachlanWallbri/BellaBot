package com.pudutech.peanut.robot_ui.p063ui.greeter;

import android.content.Intent;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.p063ui.SolicitCustomerActivity;
import com.pudutech.peanut.robot_ui.viewmodel.GreeterVm;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GreeterActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/peanut/robot_ui/ui/greeter/GreeterActivity$onGoToWelcomeAreaClick$1", "Lcom/pudutech/peanut/robot_ui/listener/OnLazyVoiceClickListener;", "onSingleClick", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class GreeterActivity$onGoToWelcomeAreaClick$1 extends OnLazyVoiceClickListener {
    final /* synthetic */ GreeterActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GreeterActivity$onGoToWelcomeAreaClick$1(GreeterActivity greeterActivity) {
        this.this$0 = greeterActivity;
    }

    @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
    public void onSingleClick() {
        String str;
        GreeterVm.GreeterStatus greeterStatus;
        int i;
        str = this.this$0.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onGoToWelcomeAreaClick current status = ");
        greeterStatus = this.this$0.currentEventStatus;
        sb.append(greeterStatus);
        Pdlog.m3273d(str, sb.toString());
        GreeterActivity greeterActivity = this.this$0;
        i = greeterActivity.TYPE_PAUSE_FEATURE_DIALOG;
        greeterActivity.onPauseFeatureChange(i);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.this$0.getString(C5508R.string.iscancel_and_go));
        TextView tvDesc = (TextView) this.this$0._$_findCachedViewById(C5508R.id.tvDesc);
        Intrinsics.checkExpressionValueIsNotNull(tvDesc, "tvDesc");
        sb2.append(tvDesc.getText());
        this.this$0.showConfirmDialog(sb2.toString(), new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterActivity$onGoToWelcomeAreaClick$1$onSingleClick$1
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
                str2 = GreeterActivity$onGoToWelcomeAreaClick$1.this.this$0.TAG;
                Pdlog.m3273d(str2, "onGoToWelcomeAreaClick dialog onSure");
                greeterVm = GreeterActivity$onGoToWelcomeAreaClick$1.this.this$0.getGreeterVm();
                greeterVm.cancel();
                GreeterActivity$onGoToWelcomeAreaClick$1.this.this$0.jumpMethod = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterActivity$onGoToWelcomeAreaClick$1$onSingleClick$1.1
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
                        Intent intent = new Intent(GreeterActivity$onGoToWelcomeAreaClick$1.this.this$0, (Class<?>) SolicitCustomerActivity.class);
                        i2 = GreeterActivity$onGoToWelcomeAreaClick$1.this.this$0.state;
                        if (i2 == 0) {
                            intent.putExtra("state", 1);
                            intent.putExtra(SolicitCustomerActivity.INTENT_JUMP_STATE, 1);
                        }
                        GreeterActivity$onGoToWelcomeAreaClick$1.this.this$0.jumpAndFinish(intent);
                    }
                };
            }
        }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GreeterActivity$onGoToWelcomeAreaClick$1$onSingleClick$2
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
                GreeterActivity greeterActivity2 = GreeterActivity$onGoToWelcomeAreaClick$1.this.this$0;
                i2 = GreeterActivity$onGoToWelcomeAreaClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                greeterActivity2.onPauseFeatureChange(i2);
            }
        });
    }
}
