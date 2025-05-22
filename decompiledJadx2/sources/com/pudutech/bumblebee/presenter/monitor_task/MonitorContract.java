package com.pudutech.bumblebee.presenter.monitor_task;

import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Error;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Errors;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;

/* compiled from: MonitorContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorContract;", "", "PresenterInterface", "Suggestion", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface MonitorContract {

    /* compiled from: MonitorContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\nH&R\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "all", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Error;", "getAll", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "setAll", "(Ljava/util/concurrent/CopyOnWriteArrayList;)V", "cancelThisTime", "", "tryResume", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        void cancelThisTime();

        CopyOnWriteArrayList<Error> getAll();

        void setAll(CopyOnWriteArrayList<Error> copyOnWriteArrayList);

        void tryResume();
    }

    /* compiled from: MonitorContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorContract$Suggestion;", "", "(Ljava/lang/String;I)V", "USER_TRY", "WAIT", "NO_ERROR_LEFT", "RESTART", "REBOOT", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum Suggestion {
        USER_TRY,
        WAIT,
        NO_ERROR_LEFT,
        RESTART,
        REBOOT
    }

    /* compiled from: MonitorContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J(\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fH&¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "collectError", "", "errors", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Errors;", "showSuggestion", "s", "Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorContract$Suggestion;", "list", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Error;", "Lkotlin/collections/ArrayList;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
        void collectError(Errors errors);

        void showSuggestion(Suggestion s, ArrayList<Error> list);
    }
}
