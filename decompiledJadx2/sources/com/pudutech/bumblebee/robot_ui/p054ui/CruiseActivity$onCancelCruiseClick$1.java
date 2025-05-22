package com.pudutech.bumblebee.robot_ui.p054ui;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.cruise_task.CruiseContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ValidationDialog;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CruiseActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/CruiseActivity$onCancelCruiseClick$1", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyVoiceClickListener;", "onSingleClick", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CruiseActivity$onCancelCruiseClick$1 extends OnLazyVoiceClickListener {
    final /* synthetic */ CruiseActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CruiseActivity$onCancelCruiseClick$1(CruiseActivity cruiseActivity) {
        super(null, 0, 3, null);
        this.this$0 = cruiseActivity;
    }

    @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
    public void onSingleClick() {
        String str;
        CruiseContract.ViewEvent viewEvent;
        boolean z;
        int i;
        String str2;
        str = this.this$0.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onCancelCruiseClick current status = ");
        viewEvent = this.this$0.currentEventStatus;
        sb.append(viewEvent);
        Pdlog.m3273d(str, sb.toString());
        z = this.this$0.isRelease;
        if (z) {
            str2 = this.this$0.TAG;
            Pdlog.m3274e(str2, "goToCruise failed isRelease ");
            return;
        }
        CruiseActivity cruiseActivity = this.this$0;
        i = cruiseActivity.TYPE_PAUSE_FEATURE_DIALOG;
        cruiseActivity.onPauseFeatureChange(i);
        if (Constans.INSTANCE.getCruiseExitSwitch()) {
            final ValidationDialog validationDialog = new ValidationDialog(this.this$0);
            validationDialog.setMPasswordConfig(CollectionsKt.arrayListOf(Constans.INSTANCE.getSettingPassword(), "pudupw"));
            validationDialog.setOnPermissionCheckResult(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$onCancelCruiseClick$1$onSingleClick$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z2) {
                    if (z2) {
                        CruiseActivity$onCancelCruiseClick$1.this.this$0.getCruisePresenter().actionCancelTask();
                        validationDialog.dismiss();
                    }
                }
            });
            validationDialog.setMClickCall(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$onCancelCruiseClick$1$onSingleClick$2
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
                    CruiseActivity cruiseActivity2 = CruiseActivity$onCancelCruiseClick$1.this.this$0;
                    i2 = CruiseActivity$onCancelCruiseClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                    cruiseActivity2.onPauseFeatureChange(i2);
                }
            });
            validationDialog.show();
            return;
        }
        CruiseActivity cruiseActivity2 = this.this$0;
        String string = cruiseActivity2.getString(C4188R.string.pdStr3_9);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr3_9)");
        cruiseActivity2.showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$onCancelCruiseClick$1$onSingleClick$3
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
                String str3;
                str3 = CruiseActivity$onCancelCruiseClick$1.this.this$0.TAG;
                Pdlog.m3273d(str3, "onCancelCruiseClick dialog onSure");
                CruiseActivity$onCancelCruiseClick$1.this.this$0.getCruisePresenter().actionCancelTask();
            }
        }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$onCancelCruiseClick$1$onSingleClick$4
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
