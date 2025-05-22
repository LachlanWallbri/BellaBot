package com.pudutech.bumblebee.robot_ui.p054ui.custom_call;

import android.content.Intent;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallTargetBean;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.DeliverTaskEditActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.TurnBackActivity;
import com.pudutech.bumblebee.robot_ui.track.ext.IntentExtKt;
import com.pudutech.bumblebee.robot_ui.track.task.DeliveryTrack;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import com.pudutech.robot.module.report.track2.TrackType;
import com.pudutech.robot.opensdk.bean.pub.CustomCallOperationType;
import com.pudutech.robot.opensdk.bean.pub.CustomCallState;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: WalkFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "Landroid/widget/RelativeLayout;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
final class WalkFragment$pauseLayout$2 extends Lambda implements Function0<RelativeLayout> {
    final /* synthetic */ WalkFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WalkFragment$pauseLayout$2(WalkFragment walkFragment) {
        super(0);
        this.this$0 = walkFragment;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final RelativeLayout invoke() {
        View inflate = ((ViewStub) this.this$0.getView().findViewById(C4188R.id.pause_rl_stub)).inflate();
        if (inflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        final RelativeLayout relativeLayout = (RelativeLayout) inflate;
        relativeLayout.setOnClickListener(new SingleClickListener(null, 0, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$pauseLayout$2$$special$$inlined$apply$lambda$1
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
                CustomCallTargetBean customCallTargetBean;
                customCallTargetBean = WalkFragment$pauseLayout$2.this.this$0.customCallTargetBean;
                if (customCallTargetBean != null) {
                    WalkFragment$pauseLayout$2.this.this$0.showWalkLayout(customCallTargetBean.getDestination());
                }
                WalkFragment$pauseLayout$2.this.this$0.resumeTask();
            }
        }, 3, null));
        ((LinearLayout) this.this$0._$_findCachedViewById(C4188R.id.custom_call_cancel_ll)).setOnClickListener(new SingleClickListener(null, 0, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$pauseLayout$2$$special$$inlined$apply$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                String str;
                str = this.this$0.TAG;
                Pdlog.m3273d(str, "custom_call_cancel_ll setOnClickListener");
                DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                this.this$0.isUserCancel = true;
                this.this$0.cancelTask();
                Function2<CustomCallState, CustomCallOperationType, Unit> onActionState = this.this$0.getOnActionState();
                if (onActionState != null) {
                    onActionState.invoke(CustomCallState.Cancel, CustomCallOperationType.user);
                }
                Intent intent = new Intent(relativeLayout.getContext(), (Class<?>) DeliverTaskEditActivity.class);
                intent.putExtra("MODE_TYPE", 0);
                this.this$0.jump(intent);
            }
        }, 3, null));
        ((LinearLayout) this.this$0._$_findCachedViewById(C4188R.id.custom_call_back_ll)).setOnClickListener(new SingleClickListener(null, 0, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$pauseLayout$2$$special$$inlined$apply$lambda$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                String str;
                str = this.this$0.TAG;
                Pdlog.m3273d(str, "custom_call_back_ll setOnClickListener");
                DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                this.this$0.isUserCancel = true;
                this.this$0.cancelTask();
                Function2<CustomCallState, CustomCallOperationType, Unit> onActionState = this.this$0.getOnActionState();
                if (onActionState != null) {
                    onActionState.invoke(CustomCallState.Cancel, CustomCallOperationType.user);
                }
                Intent intent = new Intent(relativeLayout.getContext(), (Class<?>) TurnBackActivity.class);
                Intent putExtra = intent.putExtra("SHOW_THANKS", true);
                Intrinsics.checkExpressionValueIsNotNull(putExtra, "intent.putExtra(\n       …   true\n                )");
                IntentExtKt.saveSceneId$default(putExtra, TrackType.CALL_DIRECT, false, 2, null);
                this.this$0.jump(intent);
            }
        }, 3, null));
        return relativeLayout;
    }
}
