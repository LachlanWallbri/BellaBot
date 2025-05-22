package com.pudutech.bumblebee.presenter.schedule_task;

import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleInfoContract;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import kotlin.Metadata;

/* compiled from: ScheduleInfoPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleInfoPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleInfoContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleInfoContract$PresenterInterface;", "()V", "num", "", "getNum", "()I", "schedulerCountListener", "com/pudutech/bumblebee/presenter/schedule_task/ScheduleInfoPresenter$schedulerCountListener$1", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleInfoPresenter$schedulerCountListener$1;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ScheduleInfoPresenter extends BaseOneViewPresenter<ScheduleInfoContract.ViewInterface> implements ScheduleInfoContract.PresenterInterface {
    private final ScheduleInfoPresenter$schedulerCountListener$1 schedulerCountListener = new ScheduleInfoPresenter$schedulerCountListener$1(this);

    public ScheduleInfoPresenter() {
        SDK.INSTANCE.getSchedulerInfoListeners().addListener(this.schedulerCountListener);
    }

    @Override // com.pudutech.bumblebee.presenter.schedule_task.ScheduleInfoContract.PresenterInterface
    public int getNum() {
        return RobotMoveManager.INSTANCE.getSchedulerCount();
    }
}
