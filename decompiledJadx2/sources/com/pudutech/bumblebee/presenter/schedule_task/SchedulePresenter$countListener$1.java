package com.pudutech.bumblebee.presenter.schedule_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SchedulerInfoListener;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleContract;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: SchedulePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/bumblebee/presenter/schedule_task/SchedulePresenter$countListener$1", "Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/SchedulerInfoListener;", "onSchedulerCount", "", "p0", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SchedulePresenter$countListener$1 implements SchedulerInfoListener {
    final /* synthetic */ SchedulePresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchedulePresenter$countListener$1(SchedulePresenter schedulePresenter) {
        this.this$0 = schedulePresenter;
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SchedulerInfoListener
    public void onSchedulerCount(final int p0) {
        Pdlog.m3275i(this.this$0.getTAG(), "onSchedulerCount " + p0);
        synchronized (this) {
            final ScheduleContract.Model copy$default = ScheduleContract.Model.copy$default(this.this$0.getMModel(), null, p0, 1, null);
            this.this$0.setMModel(copy$default);
            this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.schedule_task.SchedulePresenter$countListener$1$onSchedulerCount$$inlined$synchronized$lambda$1
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
                    ScheduleContract.ViewInterface theView;
                    theView = this.this$0.getTheView();
                    if (theView != null) {
                        theView.showScheduleEvent(ScheduleContract.TriggerEvent.NUM_CHANGE, ScheduleContract.Model.this);
                    }
                }
            });
            Unit unit = Unit.INSTANCE;
        }
    }
}
