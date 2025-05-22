package com.pudutech.bumblebee.robot_ui.p054ui;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.gohome_task.GoHomeContract;
import com.pudutech.bumblebee.presenter.gohome_task.GoHomePresenter;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.track.task.TurnBackTrack;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TurnBackActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/TurnBackActivity$onCancelTurnBackClick$1", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyVoiceClickListener;", "onSingleClick", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TurnBackActivity$onCancelTurnBackClick$1 extends OnLazyVoiceClickListener {
    final /* synthetic */ TurnBackActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TurnBackActivity$onCancelTurnBackClick$1(TurnBackActivity turnBackActivity) {
        super(null, 0, 3, null);
        this.this$0 = turnBackActivity;
    }

    @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
    public void onSingleClick() {
        String str;
        GoHomeContract.ViewEvent viewEvent;
        int i;
        boolean z;
        str = this.this$0.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onCancelTurnBackClick current status = ");
        viewEvent = this.this$0.currentEventStatus;
        sb.append(viewEvent);
        Pdlog.m3273d(str, sb.toString());
        this.this$0.functionClickTime = System.currentTimeMillis();
        TurnBackActivity turnBackActivity = this.this$0;
        i = turnBackActivity.TYPE_PAUSE_FEATURE_DIALOG;
        turnBackActivity.onPauseFeatureChange(i);
        z = this.this$0.isNeedPwdPro;
        if (z) {
            this.this$0.showPasswordDialog(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$onCancelTurnBackClick$1$onSingleClick$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z2) {
                    String str2;
                    GoHomePresenter goHomePresenter;
                    if (z2) {
                        str2 = TurnBackActivity$onCancelTurnBackClick$1.this.this$0.TAG;
                        Pdlog.m3273d(str2, "onCancelTurnBackClick showPasswordDialog onSure");
                        TurnBackTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                        TurnBackActivity$onCancelTurnBackClick$1.this.this$0.dismissPasswordDialog();
                        DeliverTaskEditActivity.INSTANCE.setCanFillIn(false);
                        goHomePresenter = TurnBackActivity$onCancelTurnBackClick$1.this.this$0.getGoHomePresenter();
                        goHomePresenter.actionCancelTask();
                    }
                }
            }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$onCancelTurnBackClick$1$onSingleClick$2
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
                    TurnBackActivity turnBackActivity2 = TurnBackActivity$onCancelTurnBackClick$1.this.this$0;
                    i2 = TurnBackActivity$onCancelTurnBackClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                    turnBackActivity2.onPauseFeatureChange(i2);
                }
            });
            return;
        }
        TurnBackActivity turnBackActivity2 = this.this$0;
        String string = turnBackActivity2.getString(C4188R.string.pdStr13_6);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr13_6)");
        turnBackActivity2.showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$onCancelTurnBackClick$1$onSingleClick$3
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
                GoHomePresenter goHomePresenter;
                str2 = TurnBackActivity$onCancelTurnBackClick$1.this.this$0.TAG;
                Pdlog.m3273d(str2, "onCancelTurnBackClick dialog onSure");
                TurnBackTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                DeliverTaskEditActivity.INSTANCE.setCanFillIn(false);
                goHomePresenter = TurnBackActivity$onCancelTurnBackClick$1.this.this$0.getGoHomePresenter();
                goHomePresenter.actionCancelTask();
            }
        }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$onCancelTurnBackClick$1$onSingleClick$4
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
                TurnBackActivity turnBackActivity3 = TurnBackActivity$onCancelTurnBackClick$1.this.this$0;
                i2 = TurnBackActivity$onCancelTurnBackClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                turnBackActivity3.onPauseFeatureChange(i2);
            }
        });
    }
}
