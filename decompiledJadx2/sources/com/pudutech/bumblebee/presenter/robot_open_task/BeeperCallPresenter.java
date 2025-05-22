package com.pudutech.bumblebee.presenter.robot_open_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.robot_open_task.BeeperCallContract;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CallFromType;
import kotlin.Metadata;

/* compiled from: BeeperCallPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\f\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u0010\u001a\u00020\u000fH\u0014J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/BeeperCallPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/BeeperCallContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/BeeperCallContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "canReceiveTask", "", "onBeeperCallListener", "com/pudutech/bumblebee/presenter/robot_open_task/BeeperCallPresenter$onBeeperCallListener$1", "Lcom/pudutech/bumblebee/presenter/robot_open_task/BeeperCallPresenter$onBeeperCallListener$1;", "onViewAttach", "", "onViewRemoved", "resetCallCountdown", "startReceiveCallTask", "stopReceiveCallTask", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BeeperCallPresenter extends BaseOneViewPresenter<BeeperCallContract.ViewInterface> implements BeeperCallContract.PresenterInterface {
    private boolean canReceiveTask;
    private final String TAG = "BeeperCallPresenter";
    private final BeeperCallPresenter$onBeeperCallListener$1 onBeeperCallListener = new BeeperCallPresenter$onBeeperCallListener$1(this);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.BeeperCallContract.PresenterInterface
    public void startReceiveCallTask() {
        this.canReceiveTask = true;
        String canRunCallTask$module_bumblebee_presenter_robotRelease = RobotOpenManager.INSTANCE.getCanRunCallTask$module_bumblebee_presenter_robotRelease();
        String str = canRunCallTask$module_bumblebee_presenter_robotRelease;
        if (str == null || str.length() == 0) {
            return;
        }
        Pdlog.m3273d(getTAG(), "startReceiveCallTask : canRunCallTask = " + canRunCallTask$module_bumblebee_presenter_robotRelease);
        BeeperCallContract.ViewInterface theView = getTheView();
        if (theView != null) {
            theView.onCallTask(canRunCallTask$module_bumblebee_presenter_robotRelease, CallFromType.OPEN_API);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewAttach() {
        super.onViewAttach();
        RobotOpenManager.INSTANCE.getBeeperTaskListener().addListener(this.onBeeperCallListener);
        RobotNewOpenManager.INSTANCE.addBeeperListener(this.onBeeperCallListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        this.canReceiveTask = false;
        RobotOpenManager.INSTANCE.getBeeperTaskListener().removeListener(this.onBeeperCallListener);
        RobotNewOpenManager.INSTANCE.removeBeeperListener(this.onBeeperCallListener);
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.BeeperCallContract.PresenterInterface
    public void resetCallCountdown() {
        FreezeStateManger.INSTANCE.setLastFinishTime(0L);
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.BeeperCallContract.PresenterInterface
    public void stopReceiveCallTask() {
        Pdlog.m3274e(getTAG(), "stopReceiveCallTask ");
        this.canReceiveTask = false;
        RobotOpenManager.INSTANCE.stopTimeoutCall$module_bumblebee_presenter_robotRelease();
    }
}
