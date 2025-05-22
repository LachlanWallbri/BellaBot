package com.pudutech.bumblebee.presenter.delivery_task.remote_task;

import com.pudutech.bumblebee.business.movementInterface.SortType;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import java.util.ArrayList;
import kotlin.Metadata;

/* compiled from: RemoteDeliveryContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/remote_task/RemoteDeliveryContract;", "", "()V", "PresenterInterface", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RemoteDeliveryContract {

    /* compiled from: RemoteDeliveryContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/remote_task/RemoteDeliveryContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
    }

    /* compiled from: RemoteDeliveryContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tH&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/remote_task/RemoteDeliveryContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "onReceiveRemoteModifyTask", "", "sortType", "Lcom/pudutech/bumblebee/business/movementInterface/SortType;", "allTrays", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/delivery_task/TrayModel;", "Lkotlin/collections/ArrayList;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
        boolean onReceiveRemoteModifyTask(SortType sortType, ArrayList<TrayModel> allTrays);
    }
}
