package com.pudutech.bumblebee.robot_ui.p054ui.greeter;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.greeter_task.GreeterContract;
import com.pudutech.bumblebee.presenter.greeter_task.GreeterPresenter;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.track.task.GreeterTrack;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GreeterActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/greeter/GreeterActivity$onCancelGreeterBackClick$1", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyVoiceClickListener;", "onSingleClick", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class GreeterActivity$onCancelGreeterBackClick$1 extends OnLazyVoiceClickListener {
    final /* synthetic */ GreeterActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GreeterActivity$onCancelGreeterBackClick$1(GreeterActivity greeterActivity) {
        super(null, 0, 3, null);
        this.this$0 = greeterActivity;
    }

    @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
    public void onSingleClick() {
        String str;
        GreeterContract.ViewEvent viewEvent;
        int i;
        str = this.this$0.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onCancelGreeterBackClick current status = ");
        viewEvent = this.this$0.currentEventStatus;
        sb.append(viewEvent);
        Pdlog.m3273d(str, sb.toString());
        GreeterActivity greeterActivity = this.this$0;
        i = greeterActivity.TYPE_PAUSE_FEATURE_DIALOG;
        greeterActivity.onPauseFeatureChange(i);
        if (Constans.INSTANCE.getGreeterExitSwitch()) {
            this.this$0.showPasswordDialog(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$onCancelGreeterBackClick$1$onSingleClick$1
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
                    String str2;
                    GreeterPresenter greeterPresenter;
                    if (z) {
                        str2 = GreeterActivity$onCancelGreeterBackClick$1.this.this$0.TAG;
                        Pdlog.m3273d(str2, "onCancelGreeterBackClick showPasswordDialog onSure");
                        GreeterTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                        GreeterActivity$onCancelGreeterBackClick$1.this.this$0.dismissPasswordDialog();
                        greeterPresenter = GreeterActivity$onCancelGreeterBackClick$1.this.this$0.getGreeterPresenter();
                        greeterPresenter.actionCancelTask();
                    }
                }
            }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$onCancelGreeterBackClick$1$onSingleClick$2
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
                    GreeterActivity greeterActivity2 = GreeterActivity$onCancelGreeterBackClick$1.this.this$0;
                    i2 = GreeterActivity$onCancelGreeterBackClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                    greeterActivity2.onPauseFeatureChange(i2);
                }
            });
            return;
        }
        String text = this.this$0.getString(C4188R.string.pdStr25_8);
        GreeterActivity greeterActivity2 = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(text, "text");
        greeterActivity2.showConfirmDialog(text, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$onCancelGreeterBackClick$1$onSingleClick$3
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
                GreeterPresenter greeterPresenter;
                GreeterTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                str2 = GreeterActivity$onCancelGreeterBackClick$1.this.this$0.TAG;
                Pdlog.m3273d(str2, "onCancelGreeterBackClick dialog onSure");
                greeterPresenter = GreeterActivity$onCancelGreeterBackClick$1.this.this$0.getGreeterPresenter();
                greeterPresenter.actionCancelTask();
            }
        }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$onCancelGreeterBackClick$1$onSingleClick$4
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
                GreeterActivity greeterActivity3 = GreeterActivity$onCancelGreeterBackClick$1.this.this$0;
                i2 = GreeterActivity$onCancelGreeterBackClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                greeterActivity3.onPauseFeatureChange(i2);
            }
        });
    }
}
