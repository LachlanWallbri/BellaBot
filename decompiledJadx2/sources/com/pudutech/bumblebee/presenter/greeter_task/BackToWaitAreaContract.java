package com.pudutech.bumblebee.presenter.greeter_task;

import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import com.pudutech.bumblebee.presenter.performance.MovePerformance;
import kotlin.Metadata;

/* compiled from: BackToWaitAreaContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/greeter_task/BackToWaitAreaContract;", "", "()V", "PresenterInterface", "ViewEvent", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BackToWaitAreaContract {

    /* compiled from: BackToWaitAreaContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/greeter_task/BackToWaitAreaContract$ViewEvent;", "", "(Ljava/lang/String;I)V", "NO_DINING_OUTLET", "TEMPORARY_POINT", "ON_THE_WAY", "ACTIVE", "PAUSE", "DONE", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum ViewEvent {
        NO_DINING_OUTLET,
        TEMPORARY_POINT,
        ON_THE_WAY,
        ACTIVE,
        PAUSE,
        DONE
    }

    /* compiled from: BackToWaitAreaContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/greeter_task/BackToWaitAreaContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "showGoHomeEvent", "", "event", "Lcom/pudutech/bumblebee/presenter/greeter_task/BackToWaitAreaContract$ViewEvent;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
        void showGoHomeEvent(ViewEvent event);
    }

    /* compiled from: BackToWaitAreaContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0012\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/greeter_task/BackToWaitAreaContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "actionActive", "", "actionCancelTask", "actionGoWaitArea", "performance", "Lcom/pudutech/bumblebee/presenter/performance/MovePerformance;", "actionPause", "actionPauseNoTimer", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        void actionActive();

        void actionCancelTask();

        void actionGoWaitArea(MovePerformance performance);

        void actionPause();

        void actionPauseNoTimer();

        /* compiled from: BackToWaitAreaContract.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ void actionGoWaitArea$default(PresenterInterface presenterInterface, MovePerformance movePerformance, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: actionGoWaitArea");
                }
                if ((i & 1) != 0) {
                    movePerformance = MovePerformance.NORMAL;
                }
                presenterInterface.actionGoWaitArea(movePerformance);
            }
        }
    }
}
