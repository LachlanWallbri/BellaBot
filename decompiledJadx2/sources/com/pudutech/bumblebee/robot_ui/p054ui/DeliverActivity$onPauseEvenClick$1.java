package com.pudutech.bumblebee.robot_ui.p054ui;

import android.content.Intent;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.TableTaskManager;
import com.pudutech.bumblebee.robot_ui.p054ui.view.DeliverPauseLayout;
import com.pudutech.bumblebee.robot_ui.track.ext.IntentExtKt;
import com.pudutech.bumblebee.robot_ui.track.task.DeliveryTrack;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeliverActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/DeliverActivity$onPauseEvenClick$1", "Lcom/pudutech/bumblebee/robot_ui/ui/view/DeliverPauseLayout$OnEvenClickListener;", "onCancelAllClick", "", "onCancelOneClick", "onLayoutClick", "onModifyClick", "onTurnBackClick", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DeliverActivity$onPauseEvenClick$1 implements DeliverPauseLayout.OnEvenClickListener {
    final /* synthetic */ DeliverActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeliverActivity$onPauseEvenClick$1(DeliverActivity deliverActivity) {
        this.this$0 = deliverActivity;
    }

    @Override // com.pudutech.bumblebee.robot_ui.ui.view.DeliverPauseLayout.OnEvenClickListener
    public void onModifyClick() {
        String str;
        DeliveryPresenter deliverPresenter;
        int i;
        int i2;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onPauseEvenClick onModifyClick");
        if (Constans.INSTANCE.getDeliverExitSwitch()) {
            i = this.this$0.currentMode;
            if (i == 0) {
                DeliverActivity deliverActivity = this.this$0;
                i2 = deliverActivity.TYPE_PAUSE_FEATURE_DIALOG;
                DeliverActivity.onPauseFeatureChange$default(deliverActivity, i2, false, 2, null);
                this.this$0.showPasswordDialog(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onModifyClick$1
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
                        DeliveryPresenter deliverPresenter2;
                        if (z) {
                            str2 = DeliverActivity$onPauseEvenClick$1.this.this$0.TAG;
                            Pdlog.m3273d(str2, "onPauseEvenClick onModifyClick showPasswordDialog onSure");
                            DeliverActivity$onPauseEvenClick$1.this.this$0.dismissPasswordDialog();
                            deliverPresenter2 = DeliverActivity$onPauseEvenClick$1.this.this$0.getDeliverPresenter();
                            deliverPresenter2.actionModify();
                            DeliverActivity$onPauseEvenClick$1.this.this$0.resetMusicProgress();
                        }
                    }
                }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onModifyClick$2
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
                        int i3;
                        DeliverActivity deliverActivity2 = DeliverActivity$onPauseEvenClick$1.this.this$0;
                        i3 = DeliverActivity$onPauseEvenClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                        DeliverActivity.onPauseFeatureChange$default(deliverActivity2, i3, false, 2, null);
                    }
                });
                return;
            }
        }
        deliverPresenter = this.this$0.getDeliverPresenter();
        deliverPresenter.actionModify();
    }

    @Override // com.pudutech.bumblebee.robot_ui.ui.view.DeliverPauseLayout.OnEvenClickListener
    public void onCancelOneClick() {
        String str;
        int i;
        int i2;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onPauseEvenClick onCancelOneClick");
        DeliverActivity deliverActivity = this.this$0;
        i = deliverActivity.TYPE_PAUSE_FEATURE_DIALOG;
        DeliverActivity.onPauseFeatureChange$default(deliverActivity, i, false, 2, null);
        if (Constans.INSTANCE.getDeliverExitSwitch()) {
            i2 = this.this$0.currentMode;
            if (i2 == 0) {
                this.this$0.showPasswordDialog(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onCancelOneClick$1
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
                        DeliveryPresenter deliverPresenter;
                        if (z) {
                            str2 = DeliverActivity$onPauseEvenClick$1.this.this$0.TAG;
                            Pdlog.m3273d(str2, "onPauseEvenClick onCancelOneClick showPasswordDialog onSure");
                            DeliverActivity$onPauseEvenClick$1.this.this$0.dismissPasswordDialog();
                            deliverPresenter = DeliverActivity$onPauseEvenClick$1.this.this$0.getDeliverPresenter();
                            deliverPresenter.actionFinishBeforeArrival();
                        }
                    }
                }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onCancelOneClick$2
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
                        int i3;
                        DeliverActivity deliverActivity2 = DeliverActivity$onPauseEvenClick$1.this.this$0;
                        i3 = DeliverActivity$onPauseEvenClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                        DeliverActivity.onPauseFeatureChange$default(deliverActivity2, i3, false, 2, null);
                    }
                });
                return;
            }
        }
        DeliverActivity deliverActivity2 = this.this$0;
        String string = deliverActivity2.getString(C4188R.string.pdStr2_10);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr2_10)");
        deliverActivity2.showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onCancelOneClick$3
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
                DeliveryPresenter deliverPresenter;
                str2 = DeliverActivity$onPauseEvenClick$1.this.this$0.TAG;
                Pdlog.m3273d(str2, "onPauseEvenClick onCancelOneClick dialog onSure");
                deliverPresenter = DeliverActivity$onPauseEvenClick$1.this.this$0.getDeliverPresenter();
                deliverPresenter.actionFinishBeforeArrival();
            }
        }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onCancelOneClick$4
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
                int i3;
                DeliverActivity deliverActivity3 = DeliverActivity$onPauseEvenClick$1.this.this$0;
                i3 = DeliverActivity$onPauseEvenClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                DeliverActivity.onPauseFeatureChange$default(deliverActivity3, i3, false, 2, null);
            }
        });
    }

    @Override // com.pudutech.bumblebee.robot_ui.ui.view.DeliverPauseLayout.OnEvenClickListener
    public void onCancelAllClick() {
        String str;
        int i;
        int i2;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onPauseEvenClick onCancelAllClick");
        DeliverActivity deliverActivity = this.this$0;
        i = deliverActivity.TYPE_PAUSE_FEATURE_DIALOG;
        DeliverActivity.onPauseFeatureChange$default(deliverActivity, i, false, 2, null);
        if (Constans.INSTANCE.getDeliverExitSwitch()) {
            i2 = this.this$0.currentMode;
            if (i2 == 0) {
                this.this$0.showPasswordDialog(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onCancelAllClick$1
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
                        DeliveryPresenter deliverPresenter;
                        if (z) {
                            str2 = DeliverActivity$onPauseEvenClick$1.this.this$0.TAG;
                            Pdlog.m3273d(str2, "onPauseEvenClick onCancelAllClick showPasswordDialog onSure");
                            DeliverActivity$onPauseEvenClick$1.this.this$0.dismissPasswordDialog();
                            DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                            deliverPresenter = DeliverActivity$onPauseEvenClick$1.this.this$0.getDeliverPresenter();
                            deliverPresenter.actionCancelAll();
                        }
                    }
                }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onCancelAllClick$2
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
                        int i3;
                        DeliverActivity deliverActivity2 = DeliverActivity$onPauseEvenClick$1.this.this$0;
                        i3 = DeliverActivity$onPauseEvenClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                        DeliverActivity.onPauseFeatureChange$default(deliverActivity2, i3, false, 2, null);
                    }
                });
                return;
            }
        }
        DeliverActivity deliverActivity2 = this.this$0;
        String string = deliverActivity2.getString(C4188R.string.pdStr2_11);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr2_11)");
        deliverActivity2.showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onCancelAllClick$3
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
                DeliveryPresenter deliverPresenter;
                str2 = DeliverActivity$onPauseEvenClick$1.this.this$0.TAG;
                Pdlog.m3273d(str2, "onPauseEvenClick onCancelAllClick dialog onSure");
                DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                deliverPresenter = DeliverActivity$onPauseEvenClick$1.this.this$0.getDeliverPresenter();
                deliverPresenter.actionCancelAll();
                DeliverActivity$onPauseEvenClick$1.this.this$0.resetMusicProgress();
            }
        }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onCancelAllClick$4
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
                int i3;
                DeliverActivity deliverActivity3 = DeliverActivity$onPauseEvenClick$1.this.this$0;
                i3 = DeliverActivity$onPauseEvenClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                DeliverActivity.onPauseFeatureChange$default(deliverActivity3, i3, false, 2, null);
            }
        });
    }

    @Override // com.pudutech.bumblebee.robot_ui.ui.view.DeliverPauseLayout.OnEvenClickListener
    public void onTurnBackClick() {
        String str;
        int i;
        int i2;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onPauseEvenClick onTurnBackClick");
        DeliverActivity deliverActivity = this.this$0;
        i = deliverActivity.TYPE_PAUSE_FEATURE_DIALOG;
        DeliverActivity.onPauseFeatureChange$default(deliverActivity, i, false, 2, null);
        if (Constans.INSTANCE.getDeliverExitSwitch()) {
            i2 = this.this$0.currentMode;
            if (i2 == 0) {
                this.this$0.showPasswordDialog(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onTurnBackClick$1
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
                        int i3;
                        String str3;
                        DeliveryPresenter deliverPresenter;
                        if (z) {
                            str2 = DeliverActivity$onPauseEvenClick$1.this.this$0.TAG;
                            Pdlog.m3273d(str2, "onPauseEvenClick onTurnBackClick showPasswordDialog onSure");
                            DeliverActivity$onPauseEvenClick$1.this.this$0.dismissPasswordDialog();
                            TableTaskManager.INSTANCE.clearAll();
                            DeliverActivity deliverActivity2 = DeliverActivity$onPauseEvenClick$1.this.this$0;
                            Intent intent = new Intent(DeliverActivity$onPauseEvenClick$1.this.this$0, (Class<?>) TurnBackActivity.class);
                            i3 = DeliverActivity$onPauseEvenClick$1.this.this$0.currentMode;
                            Intent putExtra = intent.putExtra(TurnBackActivity.KEY_MODE, i3);
                            str3 = DeliverActivity$onPauseEvenClick$1.this.this$0.customOutlet;
                            Intent putExtra2 = putExtra.putExtra(TurnBackActivity.CUSTOM_OUTLET, str3);
                            Intrinsics.checkExpressionValueIsNotNull(putExtra2, "Intent(this@DeliverActiv…TOM_OUTLET, customOutlet)");
                            deliverActivity2.jumpAndFinish(IntentExtKt.saveSceneId(putExtra2, DeliveryTrack.INSTANCE.getSessionId()));
                            DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                            deliverPresenter = DeliverActivity$onPauseEvenClick$1.this.this$0.getDeliverPresenter();
                            deliverPresenter.actionCancelAll();
                        }
                    }
                }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onTurnBackClick$2
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
                        int i3;
                        DeliverActivity deliverActivity2 = DeliverActivity$onPauseEvenClick$1.this.this$0;
                        i3 = DeliverActivity$onPauseEvenClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                        DeliverActivity.onPauseFeatureChange$default(deliverActivity2, i3, false, 2, null);
                    }
                });
                return;
            }
        }
        DeliverActivity deliverActivity2 = this.this$0;
        String string = deliverActivity2.getString(C4188R.string.pdStr2_12);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr2_12)");
        deliverActivity2.showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onTurnBackClick$3
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
                int i3;
                String str3;
                DeliveryPresenter deliverPresenter;
                str2 = DeliverActivity$onPauseEvenClick$1.this.this$0.TAG;
                Pdlog.m3273d(str2, "onPauseEvenClick onTurnBackClick dialog onSure");
                TableTaskManager.INSTANCE.clearAll();
                DeliverActivity deliverActivity3 = DeliverActivity$onPauseEvenClick$1.this.this$0;
                Intent intent = new Intent(DeliverActivity$onPauseEvenClick$1.this.this$0, (Class<?>) TurnBackActivity.class);
                i3 = DeliverActivity$onPauseEvenClick$1.this.this$0.currentMode;
                Intent putExtra = intent.putExtra(TurnBackActivity.KEY_MODE, i3);
                str3 = DeliverActivity$onPauseEvenClick$1.this.this$0.customOutlet;
                Intent putExtra2 = putExtra.putExtra(TurnBackActivity.CUSTOM_OUTLET, str3);
                Intrinsics.checkExpressionValueIsNotNull(putExtra2, "Intent(this@DeliverActiv…TOM_OUTLET, customOutlet)");
                deliverActivity3.jumpAndFinish(IntentExtKt.saveSceneId(putExtra2, DeliveryTrack.INSTANCE.getSessionId()));
                DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                deliverPresenter = DeliverActivity$onPauseEvenClick$1.this.this$0.getDeliverPresenter();
                deliverPresenter.actionCancelAll();
            }
        }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onTurnBackClick$4
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
                int i3;
                DeliverActivity deliverActivity3 = DeliverActivity$onPauseEvenClick$1.this.this$0;
                i3 = DeliverActivity$onPauseEvenClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                DeliverActivity.onPauseFeatureChange$default(deliverActivity3, i3, false, 2, null);
            }
        });
    }

    @Override // com.pudutech.bumblebee.robot_ui.ui.view.DeliverPauseLayout.OnEvenClickListener
    public void onLayoutClick() {
        String str;
        boolean z;
        DeliveryPresenter deliverPresenter;
        String str2;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onPauseEvenClick onLayoutClick");
        z = this.this$0.isRelease;
        if (z) {
            str2 = this.this$0.TAG;
            Pdlog.m3274e(str2, "goToCruise failed isRelease ");
        } else {
            deliverPresenter = this.this$0.getDeliverPresenter();
            deliverPresenter.actionActive();
        }
    }
}
