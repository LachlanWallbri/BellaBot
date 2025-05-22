package com.pudutech.disinfect.baselib.network.response;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkPlanResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\tHÆ\u0003J7\u0010\u001d\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0006HÖ\u0001R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012¨\u0006$"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/WorkPlan;", "", "list", "", "Lcom/pudutech/disinfect/baselib/network/response/WorkPlanItem;", "map_name", "", "time_zone", "srv_time", "", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;J)V", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "getMap_name", "()Ljava/lang/String;", "setMap_name", "(Ljava/lang/String;)V", "getSrv_time", "()J", "setSrv_time", "(J)V", "getTime_zone", "setTime_zone", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class WorkPlan {
    private List<WorkPlanItem> list;
    private String map_name;
    private long srv_time;
    private String time_zone;

    public static /* synthetic */ WorkPlan copy$default(WorkPlan workPlan, List list, String str, String str2, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            list = workPlan.list;
        }
        if ((i & 2) != 0) {
            str = workPlan.map_name;
        }
        String str3 = str;
        if ((i & 4) != 0) {
            str2 = workPlan.time_zone;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            j = workPlan.srv_time;
        }
        return workPlan.copy(list, str3, str4, j);
    }

    public final List<WorkPlanItem> component1() {
        return this.list;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMap_name() {
        return this.map_name;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTime_zone() {
        return this.time_zone;
    }

    /* renamed from: component4, reason: from getter */
    public final long getSrv_time() {
        return this.srv_time;
    }

    public final WorkPlan copy(List<WorkPlanItem> list, String map_name, String time_zone, long srv_time) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Intrinsics.checkParameterIsNotNull(map_name, "map_name");
        Intrinsics.checkParameterIsNotNull(time_zone, "time_zone");
        return new WorkPlan(list, map_name, time_zone, srv_time);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WorkPlan)) {
            return false;
        }
        WorkPlan workPlan = (WorkPlan) other;
        return Intrinsics.areEqual(this.list, workPlan.list) && Intrinsics.areEqual(this.map_name, workPlan.map_name) && Intrinsics.areEqual(this.time_zone, workPlan.time_zone) && this.srv_time == workPlan.srv_time;
    }

    public int hashCode() {
        List<WorkPlanItem> list = this.list;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        String str = this.map_name;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.time_zone;
        return ((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Long.hashCode(this.srv_time);
    }

    public String toString() {
        return "WorkPlan(list=" + this.list + ", map_name=" + this.map_name + ", time_zone=" + this.time_zone + ", srv_time=" + this.srv_time + ")";
    }

    public WorkPlan(List<WorkPlanItem> list, String map_name, String time_zone, long j) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Intrinsics.checkParameterIsNotNull(map_name, "map_name");
        Intrinsics.checkParameterIsNotNull(time_zone, "time_zone");
        this.list = list;
        this.map_name = map_name;
        this.time_zone = time_zone;
        this.srv_time = j;
    }

    public final List<WorkPlanItem> getList() {
        return this.list;
    }

    public final void setList(List<WorkPlanItem> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.list = list;
    }

    public final String getMap_name() {
        return this.map_name;
    }

    public final void setMap_name(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.map_name = str;
    }

    public final String getTime_zone() {
        return this.time_zone;
    }

    public final void setTime_zone(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.time_zone = str;
    }

    public final long getSrv_time() {
        return this.srv_time;
    }

    public final void setSrv_time(long j) {
        this.srv_time = j;
    }
}
