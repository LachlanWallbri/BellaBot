package com.pudutech.bumblebee.presenter.delivery_task.remote_task;

import com.pudutech.bumblebee.business.movementInterface.SortType;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.presenter.delivery_task.remote_task.RemoteDeliveryContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.RemoteTaskListener;
import com.pudutech.robot.opensdk.bean.DeliverySort;
import com.pudutech.robot.opensdk.bean.DeliveryTaskBody;
import com.pudutech.robot.opensdk.bean.DeliveryTaskType;
import com.pudutech.robot.opensdk.bean.DestinationTask;
import com.pudutech.robot.opensdk.bean.TrayDestinationTasks;
import com.pudutech.robot.opensdk.bean.TrayTaskChangeType;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RemoteDeliveryPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000+\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\rH\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/remote_task/RemoteDeliveryPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/delivery_task/remote_task/RemoteDeliveryContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/delivery_task/remote_task/RemoteDeliveryContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "remoteListener", "com/pudutech/bumblebee/presenter/delivery_task/remote_task/RemoteDeliveryPresenter$remoteListener$1", "Lcom/pudutech/bumblebee/presenter/delivery_task/remote_task/RemoteDeliveryPresenter$remoteListener$1;", "onViewAttach", "", "onViewRemoved", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RemoteDeliveryPresenter extends BaseOneViewPresenter<RemoteDeliveryContract.ViewInterface> implements RemoteDeliveryContract.PresenterInterface {
    private final String TAG = "RemoteDeliveryPresenter";
    private final RemoteDeliveryPresenter$remoteListener$1 remoteListener = new RemoteTaskListener() { // from class: com.pudutech.bumblebee.presenter.delivery_task.remote_task.RemoteDeliveryPresenter$remoteListener$1
        @Override // com.pudutech.bumblebee.presenter.robot_open_task.listener.RemoteTaskListener
        public boolean onDeliveryTask(DeliveryTaskBody deliveryTaskBody) {
            RemoteDeliveryContract.ViewInterface theView;
            Intrinsics.checkParameterIsNotNull(deliveryTaskBody, "deliveryTaskBody");
            if (!Intrinsics.areEqual(deliveryTaskBody.getType(), TrayTaskChangeType.MODIFY.getId())) {
                return false;
            }
            ArrayList<TrayModel> arrayList = new ArrayList<>();
            for (TrayDestinationTasks trayDestinationTasks : deliveryTaskBody.getTrays()) {
                TrayModel trayModel = new TrayModel();
                for (DestinationTask destinationTask : trayDestinationTasks.getDestinations()) {
                    trayModel.getAllDestinations().add(new DeliveryModel(destinationTask.getDestination(), null, destinationTask.getId(), DeliveryTaskType.REMOTE.getType()));
                }
                arrayList.add(trayModel);
            }
            SortType sortType = Intrinsics.areEqual(deliveryTaskBody.getDeliverySort(), DeliverySort.FIXED.getId()) ? SortType.FIXED : SortType.AUTO;
            theView = RemoteDeliveryPresenter.this.getTheView();
            if (theView != null) {
                return theView.onReceiveRemoteModifyTask(sortType, arrayList);
            }
            return false;
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewAttach() {
        super.onViewAttach();
        RobotOpenManager.INSTANCE.getRemoteTaskListener().addListener(this.remoteListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        RobotOpenManager.INSTANCE.getRemoteTaskListener().removeListener(this.remoteListener);
    }
}
