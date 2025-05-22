package com.pudutech.bumblebee.presenter.delivery_task;

import com.pudutech.bumblebee.business.movementInterface.SortType;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import com.pudutech.bumblebee.presenter.performance.MovePerformance;
import java.util.ArrayList;
import kotlin.Metadata;

/* compiled from: DeliveryContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryContract;", "", "()V", "DeliveryEvent", "DeliveryTaskSortType", "PresenterInterface", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DeliveryContract {

    /* compiled from: DeliveryContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0011\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryContract$DeliveryEvent;", "", "(Ljava/lang/String;I)V", "ON_THE_WAY", "PAUSE", "ACTIVE", "APPROACHING", "ARRIVAL", "DONE", "DONE_BEFORE_ARRIVAL", "ALL_DONE", "ALL_LEFT_CANCEL", "MODIFY", "NOT_FIND_TARGET", "PALLET_CHANGE", "PALLET_TAKE", "PALLET_PLACE", "EMPTY", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum DeliveryEvent {
        ON_THE_WAY,
        PAUSE,
        ACTIVE,
        APPROACHING,
        ARRIVAL,
        DONE,
        DONE_BEFORE_ARRIVAL,
        ALL_DONE,
        ALL_LEFT_CANCEL,
        MODIFY,
        NOT_FIND_TARGET,
        PALLET_CHANGE,
        PALLET_TAKE,
        PALLET_PLACE,
        EMPTY
    }

    /* compiled from: DeliveryContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryContract$DeliveryTaskSortType;", "", "(Ljava/lang/String;I)V", "AUTO", "FIXED", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum DeliveryTaskSortType {
        AUTO,
        FIXED
    }

    /* compiled from: DeliveryContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J \u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "showCountdownFinish", "", "millisUntilFinished", "", "showDelayAutoFinish", "showPalletChanged", "event", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryContract$DeliveryEvent;", "model", "Lcom/pudutech/bumblebee/presenter/delivery_task/ViewModel;", "index", "", "showViewModelChanged", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
        void showCountdownFinish(long millisUntilFinished);

        void showDelayAutoFinish();

        void showPalletChanged(DeliveryEvent event, ViewModel model, int index);

        void showViewModelChanged(DeliveryEvent event, ViewModel model);
    }

    /* compiled from: DeliveryContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J<\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0003H&J\b\u0010\u0013\u001a\u00020\u0003H&J\b\u0010\u0014\u001a\u00020\u0003H&¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "actionActive", "", "actionCancelAll", "actionFinish", "actionFinishBeforeArrival", "actionInitTask", "mode", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryMode;", "allTrays", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/delivery_task/TrayModel;", "Lkotlin/collections/ArrayList;", "sort", "Lcom/pudutech/bumblebee/business/movementInterface/SortType;", "performance", "Lcom/pudutech/bumblebee/presenter/performance/MovePerformance;", "actionModify", "actionPause", "actionPauseNoTimer", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        void actionActive();

        void actionCancelAll();

        void actionFinish();

        void actionFinishBeforeArrival();

        void actionInitTask(DeliveryMode mode, ArrayList<TrayModel> allTrays, SortType sort, MovePerformance performance);

        void actionModify();

        void actionPause();

        void actionPauseNoTimer();

        /* compiled from: DeliveryContract.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ void actionInitTask$default(PresenterInterface presenterInterface, DeliveryMode deliveryMode, ArrayList arrayList, SortType sortType, MovePerformance movePerformance, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: actionInitTask");
                }
                if ((i & 4) != 0) {
                    sortType = SortType.AUTO;
                }
                if ((i & 8) != 0) {
                    movePerformance = MovePerformance.NORMAL;
                }
                presenterInterface.actionInitTask(deliveryMode, arrayList, sortType, movePerformance);
            }
        }
    }
}
