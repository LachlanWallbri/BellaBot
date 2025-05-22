package com.pudutech.bumblebee.presenter.recycle_task;

import com.pudutech.bumblebee.business.movementInterface.SortType;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: RecycleContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/recycle_task/RecycleContract;", "", "PresenterInterface", "RecycleEvent", "RecycleViewModel", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface RecycleContract {

    /* compiled from: RecycleContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/recycle_task/RecycleContract$RecycleEvent;", "", "(Ljava/lang/String;I)V", "ON_THE_WAY", "ARRIVAL", "ACTIVE", "PAUSE", "DONE", "ALL_DONE", "DONE_BEFORE_ARRIVAL", "ALL_LEFT_CANCEL", "MODIFY", "APPROACHING", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum RecycleEvent {
        ON_THE_WAY,
        ARRIVAL,
        ACTIVE,
        PAUSE,
        DONE,
        ALL_DONE,
        DONE_BEFORE_ARRIVAL,
        ALL_LEFT_CANCEL,
        MODIFY,
        APPROACHING
    }

    /* compiled from: RecycleContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J<\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\"\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n`\u000bH&¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/recycle_task/RecycleContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "showRecycleEvent", "", "current", "", "event", "Lcom/pudutech/bumblebee/presenter/recycle_task/RecycleContract$RecycleEvent;", "all", "Ljava/util/LinkedHashMap;", "Lcom/pudutech/bumblebee/presenter/recycle_task/RecycleContract$RecycleViewModel;", "Lkotlin/collections/LinkedHashMap;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
        void showRecycleEvent(String current, RecycleEvent event, LinkedHashMap<String, RecycleViewModel> all);
    }

    /* compiled from: RecycleContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/recycle_task/RecycleContract$RecycleViewModel;", "", "name", "", ES6Iterator.DONE_PROPERTY, "", "(Ljava/lang/String;Z)V", "getDone", "()Z", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class RecycleViewModel {
        private final boolean done;
        private final String name;

        public static /* synthetic */ RecycleViewModel copy$default(RecycleViewModel recycleViewModel, String str, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = recycleViewModel.name;
            }
            if ((i & 2) != 0) {
                z = recycleViewModel.done;
            }
            return recycleViewModel.copy(str, z);
        }

        /* renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getDone() {
            return this.done;
        }

        public final RecycleViewModel copy(String name, boolean done) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            return new RecycleViewModel(name, done);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RecycleViewModel)) {
                return false;
            }
            RecycleViewModel recycleViewModel = (RecycleViewModel) other;
            return Intrinsics.areEqual(this.name, recycleViewModel.name) && this.done == recycleViewModel.done;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            String str = this.name;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            boolean z = this.done;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return hashCode + i;
        }

        public String toString() {
            return "RecycleViewModel(name=" + this.name + ", done=" + this.done + ")";
        }

        public RecycleViewModel(String name, boolean z) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.name = name;
            this.done = z;
        }

        public final boolean getDone() {
            return this.done;
        }

        public final String getName() {
            return this.name;
        }
    }

    /* compiled from: RecycleContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J.\u0010\b\u001a\u00020\u00032\u001a\u0010\t\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nj\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b`\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0003H&J\b\u0010\u0010\u001a\u00020\u0003H&J\b\u0010\u0011\u001a\u00020\u0003H&¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/recycle_task/RecycleContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "actionActive", "", "actionArrived", "actionCancelAllTask", "actionFinish", "actionFinishBeforeArrival", "actionGo", "list", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "sortType", "Lcom/pudutech/bumblebee/business/movementInterface/SortType;", "actionModify", "actionPause", "actionPauseNoTimer", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        void actionActive();

        void actionArrived();

        void actionCancelAllTask();

        void actionFinish();

        void actionFinishBeforeArrival();

        void actionGo(ArrayList<String> list, SortType sortType);

        void actionModify();

        void actionPause();

        void actionPauseNoTimer();

        /* compiled from: RecycleContract.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ void actionGo$default(PresenterInterface presenterInterface, ArrayList arrayList, SortType sortType, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: actionGo");
                }
                if ((i & 2) != 0) {
                    sortType = SortType.AUTO;
                }
                presenterInterface.actionGo(arrayList, sortType);
            }
        }
    }
}
