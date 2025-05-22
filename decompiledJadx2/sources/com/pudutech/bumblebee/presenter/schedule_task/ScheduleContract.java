package com.pudutech.bumblebee.presenter.schedule_task;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScheduleContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract;", "", ExifInterface.TAG_MODEL, "PresenterInterface", "TriggerEvent", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface ScheduleContract {

    /* compiled from: ScheduleContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "model", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$Model;", "getModel", "()Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$Model;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        Model getModel();
    }

    /* compiled from: ScheduleContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$TriggerEvent;", "", "(Ljava/lang/String;I)V", "NORMAL", "AVOID", "NUM_CHANGE", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum TriggerEvent {
        NORMAL,
        AVOID,
        NUM_CHANGE
    }

    /* compiled from: ScheduleContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "showScheduleEvent", "", "triggerEvent", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$TriggerEvent;", "model", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$Model;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
        void showScheduleEvent(TriggerEvent triggerEvent, Model model);
    }

    /* compiled from: ScheduleContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$Model;", "", "event", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$TriggerEvent;", "communicationNum", "", "(Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$TriggerEvent;I)V", "getCommunicationNum", "()I", "getEvent", "()Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$TriggerEvent;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class Model {
        private final int communicationNum;
        private final TriggerEvent event;

        public static /* synthetic */ Model copy$default(Model model, TriggerEvent triggerEvent, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                triggerEvent = model.event;
            }
            if ((i2 & 2) != 0) {
                i = model.communicationNum;
            }
            return model.copy(triggerEvent, i);
        }

        /* renamed from: component1, reason: from getter */
        public final TriggerEvent getEvent() {
            return this.event;
        }

        /* renamed from: component2, reason: from getter */
        public final int getCommunicationNum() {
            return this.communicationNum;
        }

        public final Model copy(TriggerEvent event, int communicationNum) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            return new Model(event, communicationNum);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Model)) {
                return false;
            }
            Model model = (Model) other;
            return Intrinsics.areEqual(this.event, model.event) && this.communicationNum == model.communicationNum;
        }

        public int hashCode() {
            TriggerEvent triggerEvent = this.event;
            return ((triggerEvent != null ? triggerEvent.hashCode() : 0) * 31) + this.communicationNum;
        }

        public String toString() {
            return "Model(event=" + this.event + ", communicationNum=" + this.communicationNum + ")";
        }

        public Model(TriggerEvent event, int i) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            this.event = event;
            this.communicationNum = i;
        }

        public final int getCommunicationNum() {
            return this.communicationNum;
        }

        public final TriggerEvent getEvent() {
            return this.event;
        }
    }
}
