package com.pudutech.bumblebee.presenter.robot_open_task;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_open_task.BeeperCallContract;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CallFromType;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.BeeperAction;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.BeeperTaskListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BeeperCallPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/bumblebee/presenter/robot_open_task/BeeperCallPresenter$onBeeperCallListener$1", "Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/BeeperTaskListener;", "onTask", "", "action", "Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/BeeperAction;", TypedValues.Attributes.S_TARGET, "", "type", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CallFromType;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BeeperCallPresenter$onBeeperCallListener$1 implements BeeperTaskListener {
    final /* synthetic */ BeeperCallPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BeeperCallPresenter$onBeeperCallListener$1(BeeperCallPresenter beeperCallPresenter) {
        this.this$0 = beeperCallPresenter;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.listener.BeeperTaskListener
    public void onTask(BeeperAction action, final String target, final CallFromType type) {
        boolean z;
        boolean z2;
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Pdlog.m3273d(this.this$0.getTAG(), "onTask action=" + action + "  target=" + target);
        if (action == BeeperAction.CALL) {
            z = this.this$0.canReceiveTask;
            if (!z) {
                String tag = this.this$0.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("onTask : canReceiveTask = ");
                z2 = this.this$0.canReceiveTask;
                sb.append(z2);
                sb.append("; can not do task ");
                Pdlog.m3273d(tag, sb.toString());
                return;
            }
            this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.BeeperCallPresenter$onBeeperCallListener$1$onTask$1
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
                    BeeperCallContract.ViewInterface theView;
                    theView = BeeperCallPresenter$onBeeperCallListener$1.this.this$0.getTheView();
                    if (theView != null) {
                        theView.onCallTask(target, type);
                    }
                }
            });
            return;
        }
        if (action == BeeperAction.CANCEL) {
            this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.BeeperCallPresenter$onBeeperCallListener$1$onTask$2
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
                    BeeperCallContract.ViewInterface theView;
                    theView = BeeperCallPresenter$onBeeperCallListener$1.this.this$0.getTheView();
                    if (theView != null) {
                        theView.onCancelTask(target);
                    }
                }
            });
        }
    }
}
