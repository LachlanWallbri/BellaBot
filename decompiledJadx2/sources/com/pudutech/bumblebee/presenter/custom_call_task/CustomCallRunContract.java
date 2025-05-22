package com.pudutech.bumblebee.presenter.custom_call_task;

import com.pudutech.bumblebee.business.movementInterface.SortType;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import java.util.ArrayList;
import kotlin.Metadata;

/* compiled from: CustomCallRunContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/custom_call_task/CustomCallRunContract;", "", "PresenterInterface", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface CustomCallRunContract {

    /* compiled from: CustomCallRunContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/custom_call_task/CustomCallRunContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "showOnTheWayState", "", "state", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryContract$DeliveryEvent;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
        void showOnTheWayState(DeliveryContract.DeliveryEvent state);
    }

    /* compiled from: CustomCallRunContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J4\u0010\u0005\u001a\u00020\u00032\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/custom_call_task/CustomCallRunContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "actionActive", "", "actionCancelAll", "actionInitTask", "destinations", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "sortType", "Lcom/pudutech/bumblebee/business/movementInterface/SortType;", "performance", "Lcom/pudutech/mirsdk/aidl/serialize/MoveTaskMode;", "actionPause", "actionPauseNoTimer", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        void actionActive();

        void actionCancelAll();

        void actionInitTask(ArrayList<String> destinations, SortType sortType, MoveTaskMode performance);

        void actionPause();

        void actionPauseNoTimer();

        /* compiled from: CustomCallRunContract.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ void actionInitTask$default(PresenterInterface presenterInterface, ArrayList arrayList, SortType sortType, MoveTaskMode moveTaskMode, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: actionInitTask");
                }
                if ((i & 2) != 0) {
                    sortType = SortType.AUTO;
                }
                if ((i & 4) != 0) {
                    moveTaskMode = MoveTaskMode.Normal;
                }
                presenterInterface.actionInitTask(arrayList, sortType, moveTaskMode);
            }
        }
    }
}
