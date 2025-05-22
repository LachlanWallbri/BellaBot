package com.pudutech.bumblebee.presenter.schedule_task;

import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleFillInContract;
import kotlin.Metadata;

/* compiled from: ScheduleFillInPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\tH\u0014J\b\u0010\u000b\u001a\u00020\tH\u0016R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleFillInPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleFillInContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleFillInContract$PresenterInterface;", "()V", "schedulerFillInListener", "com/pudutech/bumblebee/presenter/schedule_task/ScheduleFillInPresenter$schedulerFillInListener$1", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleFillInPresenter$schedulerFillInListener$1;", "onViewAttach", "", "onViewRemoved", "quitFillIn", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ScheduleFillInPresenter extends BaseOneViewPresenter<ScheduleFillInContract.ViewInterface> implements ScheduleFillInContract.PresenterInterface {
    private final ScheduleFillInPresenter$schedulerFillInListener$1 schedulerFillInListener = new ScheduleFillInPresenter$schedulerFillInListener$1(this);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        SDK.INSTANCE.getScheduleFillInListeners().removeListener(this.schedulerFillInListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewAttach() {
        super.onViewAttach();
        SDK.INSTANCE.getScheduleFillInListeners().addListener(this.schedulerFillInListener);
    }

    @Override // com.pudutech.bumblebee.presenter.schedule_task.ScheduleFillInContract.PresenterInterface
    public void quitFillIn() {
        SDK.INSTANCE.quitFillIn();
    }
}
