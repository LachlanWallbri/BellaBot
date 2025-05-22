package com.pudutech.bumblebee.presenter.schedule_task;

import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleContract;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SchedulePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0002\n\r\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001a\u00020\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0018H\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0012¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/schedule_task/SchedulePresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "countListener", "com/pudutech/bumblebee/presenter/schedule_task/SchedulePresenter$countListener$1", "Lcom/pudutech/bumblebee/presenter/schedule_task/SchedulePresenter$countListener$1;", "listener", "com/pudutech/bumblebee/presenter/schedule_task/SchedulePresenter$listener$1", "Lcom/pudutech/bumblebee/presenter/schedule_task/SchedulePresenter$listener$1;", "mModel", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$Model;", "getMModel", "()Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$Model;", "setMModel", "(Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$Model;)V", "model", "getModel", "onViewAttach", "", "onViewRemoved", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SchedulePresenter extends BaseOneViewPresenter<ScheduleContract.ViewInterface> implements ScheduleContract.PresenterInterface {
    private final String TAG = "SchedulePresenter";
    private ScheduleContract.Model mModel = new ScheduleContract.Model(ScheduleContract.TriggerEvent.NORMAL, 0);
    private final SchedulePresenter$listener$1 listener = new SchedulePresenter$listener$1(this);
    private final SchedulePresenter$countListener$1 countListener = new SchedulePresenter$countListener$1(this);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.schedule_task.ScheduleContract.PresenterInterface
    public ScheduleContract.Model getModel() {
        return this.mModel;
    }

    public final ScheduleContract.Model getMModel() {
        return this.mModel;
    }

    public final void setMModel(ScheduleContract.Model model) {
        Intrinsics.checkParameterIsNotNull(model, "<set-?>");
        this.mModel = model;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewAttach() {
        super.onViewAttach();
        RobotMoveInterfaceDecorator moveAction = CoreDevices.INSTANCE.getMoveAction();
        if (moveAction != null) {
            moveAction.addOnStateChangeListeners(this.listener);
        }
        SDK.INSTANCE.getSchedulerInfoListeners().addListener(this.countListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        RobotMoveInterfaceDecorator moveAction = CoreDevices.INSTANCE.getMoveAction();
        if (moveAction != null) {
            moveAction.removeOnStateChangeListeners(this.listener);
        }
    }
}
