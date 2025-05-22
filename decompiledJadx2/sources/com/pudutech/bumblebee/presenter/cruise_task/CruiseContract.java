package com.pudutech.bumblebee.presenter.cruise_task;

import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import com.pudutech.bumblebee.presenter.performance.MovePerformance;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CruiseContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseContract;", "", "PresenterInterface", "ViewEvent", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface CruiseContract {

    /* compiled from: CruiseContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseContract$ViewEvent;", "", "(Ljava/lang/String;I)V", "ACTIVE", "PAUSE", "ARRIVAL_STOP_DOT", "EXIT", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum ViewEvent {
        ACTIVE,
        PAUSE,
        ARRIVAL_STOP_DOT,
        EXIT
    }

    /* compiled from: CruiseContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "showCruiseEvent", "", "event", "Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseContract$ViewEvent;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
        void showCruiseEvent(ViewEvent event);
    }

    /* compiled from: CruiseContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J*\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "actionActive", "", "actionCancelTask", "actionCruise", "id", "", "performance", "Lcom/pudutech/bumblebee/presenter/performance/MovePerformance;", "stops", "", "", "actionPause", "actionPauseNoTimer", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        void actionActive();

        void actionCancelTask();

        void actionCruise(int id, MovePerformance performance, List<String> stops);

        void actionPause();

        void actionPauseNoTimer();

        /* compiled from: CruiseContract.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class DefaultImpls {
            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ void actionCruise$default(PresenterInterface presenterInterface, int i, MovePerformance movePerformance, List list, int i2, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: actionCruise");
                }
                if ((i2 & 2) != 0) {
                    movePerformance = MovePerformance.NORMAL;
                }
                if ((i2 & 4) != 0) {
                    list = CollectionsKt.emptyList();
                }
                presenterInterface.actionCruise(i, movePerformance, list);
            }
        }
    }
}
