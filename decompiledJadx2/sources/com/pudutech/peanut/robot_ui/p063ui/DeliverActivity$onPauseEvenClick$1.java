package com.pudutech.peanut.robot_ui.p063ui;

import android.content.Intent;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.manager.TableTaskManager;
import com.pudutech.peanut.robot_ui.p063ui.view.DeliverPauseLayout;
import com.pudutech.peanut.robot_ui.viewmodel.DeliverVm;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeliverActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/peanut/robot_ui/ui/DeliverActivity$onPauseEvenClick$1", "Lcom/pudutech/peanut/robot_ui/ui/view/DeliverPauseLayout$OnEvenClickListener;", "onCancelAllClick", "", "onCancelOneClick", "onLayoutClick", "onModifyClick", "onTurnBackClick", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class DeliverActivity$onPauseEvenClick$1 implements DeliverPauseLayout.OnEvenClickListener {
    final /* synthetic */ DeliverActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeliverActivity$onPauseEvenClick$1(DeliverActivity deliverActivity) {
        this.this$0 = deliverActivity;
    }

    @Override // com.pudutech.peanut.robot_ui.ui.view.DeliverPauseLayout.OnEvenClickListener
    public void onModifyClick() {
        String str;
        int i;
        DeliverVm deliverVm;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onPauseEvenClick onModifyClick");
        DeliverActivity deliverActivity = this.this$0;
        i = deliverActivity.TYPE_PAUSE_FEATURE_DIALOG;
        DeliverActivity.onPauseFeatureChange$default(deliverActivity, i, false, 2, null);
        DeliverActivity.INSTANCE.setModifyState(1);
        deliverVm = this.this$0.getDeliverVm();
        deliverVm.cancel();
        this.this$0.isModify = true;
        this.this$0.jumpMethod = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onModifyClick$1
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
                i2 = DeliverActivity$onPauseEvenClick$1.this.this$0.currentMode;
                if (i2 == 1) {
                    DeliverActivity$onPauseEvenClick$1.this.this$0.startDeliverTaskEditActivity(3);
                    return;
                }
                if (i2 == 2) {
                    DeliverActivity$onPauseEvenClick$1.this.this$0.startDeliverTaskEditActivity(5);
                    return;
                }
                if (i2 == 3) {
                    DeliverActivity$onPauseEvenClick$1.this.this$0.startDeliverTaskEditActivity(9);
                } else if (i2 != 4) {
                    DeliverActivity$onPauseEvenClick$1.this.this$0.startDeliverTaskEditActivity(1);
                } else {
                    DeliverActivity$onPauseEvenClick$1.this.this$0.startDeliverTaskEditActivity(5);
                }
            }
        };
    }

    @Override // com.pudutech.peanut.robot_ui.ui.view.DeliverPauseLayout.OnEvenClickListener
    public void onCancelOneClick() {
        String str;
        int i;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onPauseEvenClick onCancelOneClick");
        DeliverActivity deliverActivity = this.this$0;
        i = deliverActivity.TYPE_PAUSE_FEATURE_DIALOG;
        DeliverActivity.onPauseFeatureChange$default(deliverActivity, i, false, 2, null);
        DeliverActivity deliverActivity2 = this.this$0;
        String string = deliverActivity2.getString(C5508R.string.pdStr2_10);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr2_10)");
        deliverActivity2.showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onCancelOneClick$1
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
                DeliverVm deliverVm;
                String str3;
                DeliverVm deliverVm2;
                str2 = DeliverActivity$onPauseEvenClick$1.this.this$0.TAG;
                Pdlog.m3273d(str2, "onPauseEvenClick onCancelOneClick dialog onSure");
                DeliverActivity$onPauseEvenClick$1.this.this$0.advance = 1;
                TableTaskManager tableTaskManager = TableTaskManager.INSTANCE;
                deliverVm = DeliverActivity$onPauseEvenClick$1.this.this$0.getDeliverVm();
                Destination value = deliverVm.getCurrentDes().getValue();
                if (value == null || (str3 = value.getName()) == null) {
                    str3 = "";
                }
                tableTaskManager.setFinishTask(str3);
                deliverVm2 = DeliverActivity$onPauseEvenClick$1.this.this$0.getDeliverVm();
                deliverVm2.finishOne();
            }
        }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onCancelOneClick$2
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
                DeliverActivity deliverActivity3 = DeliverActivity$onPauseEvenClick$1.this.this$0;
                i2 = DeliverActivity$onPauseEvenClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                DeliverActivity.onPauseFeatureChange$default(deliverActivity3, i2, false, 2, null);
            }
        });
    }

    @Override // com.pudutech.peanut.robot_ui.ui.view.DeliverPauseLayout.OnEvenClickListener
    public void onCancelAllClick() {
        String str;
        int i;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onPauseEvenClick onCancelAllClick");
        DeliverActivity deliverActivity = this.this$0;
        i = deliverActivity.TYPE_PAUSE_FEATURE_DIALOG;
        DeliverActivity.onPauseFeatureChange$default(deliverActivity, i, false, 2, null);
        DeliverActivity deliverActivity2 = this.this$0;
        String string = deliverActivity2.getString(C5508R.string.pdStr2_11);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr2_11)");
        deliverActivity2.showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onCancelAllClick$1
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
                DeliverVm deliverVm;
                str2 = DeliverActivity$onPauseEvenClick$1.this.this$0.TAG;
                Pdlog.m3273d(str2, "onPauseEvenClick onCancelAllClick dialog onSure");
                DeliverActivity$onPauseEvenClick$1.this.this$0.advance = 0;
                DeliverActivity$onPauseEvenClick$1.this.this$0.finishState = 1;
                deliverVm = DeliverActivity$onPauseEvenClick$1.this.this$0.getDeliverVm();
                deliverVm.cancel();
            }
        }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onCancelAllClick$2
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
                DeliverActivity deliverActivity3 = DeliverActivity$onPauseEvenClick$1.this.this$0;
                i2 = DeliverActivity$onPauseEvenClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                DeliverActivity.onPauseFeatureChange$default(deliverActivity3, i2, false, 2, null);
            }
        });
    }

    @Override // com.pudutech.peanut.robot_ui.ui.view.DeliverPauseLayout.OnEvenClickListener
    public void onTurnBackClick() {
        String str;
        int i;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onPauseEvenClick onTurnBackClick");
        DeliverActivity deliverActivity = this.this$0;
        i = deliverActivity.TYPE_PAUSE_FEATURE_DIALOG;
        DeliverActivity.onPauseFeatureChange$default(deliverActivity, i, false, 2, null);
        DeliverActivity deliverActivity2 = this.this$0;
        String string = deliverActivity2.getString(C5508R.string.pdStr2_12);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr2_12)");
        deliverActivity2.showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onTurnBackClick$1
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
                DeliverVm deliverVm;
                str2 = DeliverActivity$onPauseEvenClick$1.this.this$0.TAG;
                Pdlog.m3273d(str2, "onPauseEvenClick onTurnBackClick dialog onSure");
                DeliverActivity$onPauseEvenClick$1.this.this$0.advance = 0;
                DeliverActivity$onPauseEvenClick$1.this.this$0.finishState = 1;
                deliverVm = DeliverActivity$onPauseEvenClick$1.this.this$0.getDeliverVm();
                deliverVm.cancel();
                DeliverActivity$onPauseEvenClick$1.this.this$0.jumpMethod = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onTurnBackClick$1.1
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
                        TableTaskManager.INSTANCE.cancelAllTask();
                        DeliverActivity$onPauseEvenClick$1.this.this$0.jumpAndFinish(new Intent(DeliverActivity$onPauseEvenClick$1.this.this$0, (Class<?>) TurnBackActivity.class));
                    }
                };
            }
        }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onPauseEvenClick$1$onTurnBackClick$2
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
                DeliverActivity deliverActivity3 = DeliverActivity$onPauseEvenClick$1.this.this$0;
                i2 = DeliverActivity$onPauseEvenClick$1.this.this$0.TYPE_PAUSE_FEATURE_NORMAL;
                DeliverActivity.onPauseFeatureChange$default(deliverActivity3, i2, false, 2, null);
            }
        });
    }

    @Override // com.pudutech.peanut.robot_ui.ui.view.DeliverPauseLayout.OnEvenClickListener
    public void onLayoutClick() {
        String str;
        boolean z;
        DeliverVm deliverVm;
        String str2;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onPauseEvenClick onLayoutClick");
        z = this.this$0.isRelease;
        if (z) {
            str2 = this.this$0.TAG;
            Pdlog.m3274e(str2, "goToCruise failed isRelease ");
        } else {
            deliverVm = this.this$0.getDeliverVm();
            deliverVm.active();
        }
    }
}
