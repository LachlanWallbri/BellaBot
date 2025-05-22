package com.pudutech.bumblebee.presenter.schedule_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleContract;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: SchedulePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002:\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0001j\u0002`\tJ\u001d\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u0006H\u0096\u0002¨\u0006\r"}, m3961d2 = {"com/pudutech/bumblebee/presenter/schedule_task/SchedulePresenter$listener$1", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lkotlin/ParameterName;", "name", "p0", "", "p1", "", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotStateListener;", "invoke", "state", "description", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SchedulePresenter$listener$1 implements Function2<RobotState, String, Unit> {
    final /* synthetic */ SchedulePresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchedulePresenter$listener$1(SchedulePresenter schedulePresenter) {
        this.this$0 = schedulePresenter;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(RobotState robotState, String str) {
        invoke2(robotState, str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(final RobotState state, String description) {
        ScheduleContract.ViewInterface theView;
        String tag = this.this$0.getTAG();
        StringBuilder sb = new StringBuilder();
        sb.append("onStateChange ");
        sb.append(state);
        sb.append(' ');
        sb.append(description);
        sb.append(" view=");
        theView = this.this$0.getTheView();
        sb.append(theView);
        Pdlog.m3273d(tag, sb.toString());
        synchronized (this) {
            if (state == RobotState.Avoid) {
                final ScheduleContract.Model copy$default = ScheduleContract.Model.copy$default(this.this$0.getMModel(), ScheduleContract.TriggerEvent.AVOID, 0, 2, null);
                this.this$0.setMModel(copy$default);
                this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.schedule_task.SchedulePresenter$listener$1$invoke$$inlined$synchronized$lambda$1
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
                        ScheduleContract.ViewInterface theView2;
                        theView2 = this.this$0.getTheView();
                        if (theView2 != null) {
                            theView2.showScheduleEvent(ScheduleContract.TriggerEvent.AVOID, ScheduleContract.Model.this);
                        }
                    }
                });
            } else {
                final ScheduleContract.Model copy$default2 = ScheduleContract.Model.copy$default(this.this$0.getMModel(), ScheduleContract.TriggerEvent.NORMAL, 0, 2, null);
                this.this$0.setMModel(copy$default2);
                this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.schedule_task.SchedulePresenter$listener$1$invoke$$inlined$synchronized$lambda$2
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
                        ScheduleContract.ViewInterface theView2;
                        theView2 = this.this$0.getTheView();
                        if (theView2 != null) {
                            theView2.showScheduleEvent(ScheduleContract.TriggerEvent.NORMAL, ScheduleContract.Model.this);
                        }
                    }
                });
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
