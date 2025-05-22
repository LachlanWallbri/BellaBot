package com.pudutech.bumblebee.presenter.greeter_task;

import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import java.util.ArrayList;
import kotlin.Metadata;

/* compiled from: GreeterContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/greeter_task/GreeterContract;", "", "()V", "Presenter", "ViewEvent", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class GreeterContract {

    /* compiled from: GreeterContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J \u0010\u0007\u001a\u00020\u00032\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bH&J\b\u0010\f\u001a\u00020\u0003H&¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/greeter_task/GreeterContract$Presenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "actionActive", "", "actionCancelTask", "actionPause", "actionPauseNoTimer", "actionUsher", "desList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "actionUsherDone", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface Presenter extends BasePresenterInterface {
        void actionActive();

        void actionCancelTask();

        void actionPause();

        void actionPauseNoTimer();

        void actionUsher(ArrayList<String> desList);

        void actionUsherDone();
    }

    /* compiled from: GreeterContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/greeter_task/GreeterContract$ViewEvent;", "", "(Ljava/lang/String;I)V", "ON_THE_WAY", "ACTIVE", "PAUSE", "CANCEL", "ARRIVAL_DESTINATION", "ARRIVAL_DESTINATION_DONE", "ARRIVAL_DESTINATION_COUNTDOWN_DONE", "NOT_FIND_TARGET", "DONE_ALL", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum ViewEvent {
        ON_THE_WAY,
        ACTIVE,
        PAUSE,
        CANCEL,
        ARRIVAL_DESTINATION,
        ARRIVAL_DESTINATION_DONE,
        ARRIVAL_DESTINATION_COUNTDOWN_DONE,
        NOT_FIND_TARGET,
        DONE_ALL
    }

    /* compiled from: GreeterContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/greeter_task/GreeterContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "showMovingEvent", "", "des", "", "event", "Lcom/pudutech/bumblebee/presenter/greeter_task/GreeterContract$ViewEvent;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
        void showMovingEvent(String des, ViewEvent event);
    }
}
