package com.pudutech.disinfect.baselib.network.response;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkPlanResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\nJ4\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\u0007\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006 "}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/WorkPlanItem;", "", "name", "", "schedule", "", "Lcom/pudutech/disinfect/baselib/network/response/WorkPlanItemDetail;", "isOpen", "", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;)V", "()Ljava/lang/Boolean;", "setOpen", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getSchedule", "()Ljava/util/List;", "setSchedule", "(Ljava/util/List;)V", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;)Lcom/pudutech/disinfect/baselib/network/response/WorkPlanItem;", "equals", "other", "hashCode", "", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class WorkPlanItem {
    private Boolean isOpen;
    private String name;
    private List<WorkPlanItemDetail> schedule;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ WorkPlanItem copy$default(WorkPlanItem workPlanItem, String str, List list, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = workPlanItem.name;
        }
        if ((i & 2) != 0) {
            list = workPlanItem.schedule;
        }
        if ((i & 4) != 0) {
            bool = workPlanItem.isOpen;
        }
        return workPlanItem.copy(str, list, bool);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<WorkPlanItemDetail> component2() {
        return this.schedule;
    }

    /* renamed from: component3, reason: from getter */
    public final Boolean getIsOpen() {
        return this.isOpen;
    }

    public final WorkPlanItem copy(String name, List<WorkPlanItemDetail> schedule, Boolean isOpen) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(schedule, "schedule");
        return new WorkPlanItem(name, schedule, isOpen);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WorkPlanItem)) {
            return false;
        }
        WorkPlanItem workPlanItem = (WorkPlanItem) other;
        return Intrinsics.areEqual(this.name, workPlanItem.name) && Intrinsics.areEqual(this.schedule, workPlanItem.schedule) && Intrinsics.areEqual(this.isOpen, workPlanItem.isOpen);
    }

    public int hashCode() {
        String str = this.name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        List<WorkPlanItemDetail> list = this.schedule;
        int hashCode2 = (hashCode + (list != null ? list.hashCode() : 0)) * 31;
        Boolean bool = this.isOpen;
        return hashCode2 + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        return "WorkPlanItem(name=" + this.name + ", schedule=" + this.schedule + ", isOpen=" + this.isOpen + ")";
    }

    public WorkPlanItem(String name, List<WorkPlanItemDetail> schedule, Boolean bool) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(schedule, "schedule");
        this.name = name;
        this.schedule = schedule;
        this.isOpen = bool;
    }

    public /* synthetic */ WorkPlanItem(String str, List list, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, (i & 4) != 0 ? true : bool);
    }

    public final String getName() {
        return this.name;
    }

    public final List<WorkPlanItemDetail> getSchedule() {
        return this.schedule;
    }

    public final Boolean isOpen() {
        return this.isOpen;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final void setOpen(Boolean bool) {
        this.isOpen = bool;
    }

    public final void setSchedule(List<WorkPlanItemDetail> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.schedule = list;
    }
}
