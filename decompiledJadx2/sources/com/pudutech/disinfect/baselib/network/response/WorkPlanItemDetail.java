package com.pudutech.disinfect.baselib.network.response;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkPlanResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J3\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\n\"\u0004\b\u0014\u0010\f¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/WorkPlanItemDetail;", "", "start", "", "end", "mode", "", "state", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getEnd", "()Ljava/lang/String;", "setEnd", "(Ljava/lang/String;)V", "getMode", "()I", "setMode", "(I)V", "getStart", "setStart", "getState", "setState", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class WorkPlanItemDetail {
    private String end;
    private int mode;
    private String start;
    private String state;

    public static /* synthetic */ WorkPlanItemDetail copy$default(WorkPlanItemDetail workPlanItemDetail, String str, String str2, int i, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = workPlanItemDetail.start;
        }
        if ((i2 & 2) != 0) {
            str2 = workPlanItemDetail.end;
        }
        if ((i2 & 4) != 0) {
            i = workPlanItemDetail.mode;
        }
        if ((i2 & 8) != 0) {
            str3 = workPlanItemDetail.state;
        }
        return workPlanItemDetail.copy(str, str2, i, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getStart() {
        return this.start;
    }

    /* renamed from: component2, reason: from getter */
    public final String getEnd() {
        return this.end;
    }

    /* renamed from: component3, reason: from getter */
    public final int getMode() {
        return this.mode;
    }

    /* renamed from: component4, reason: from getter */
    public final String getState() {
        return this.state;
    }

    public final WorkPlanItemDetail copy(String start, String end, int mode, String state) {
        Intrinsics.checkParameterIsNotNull(start, "start");
        Intrinsics.checkParameterIsNotNull(end, "end");
        return new WorkPlanItemDetail(start, end, mode, state);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WorkPlanItemDetail)) {
            return false;
        }
        WorkPlanItemDetail workPlanItemDetail = (WorkPlanItemDetail) other;
        return Intrinsics.areEqual(this.start, workPlanItemDetail.start) && Intrinsics.areEqual(this.end, workPlanItemDetail.end) && this.mode == workPlanItemDetail.mode && Intrinsics.areEqual(this.state, workPlanItemDetail.state);
    }

    public int hashCode() {
        String str = this.start;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.end;
        int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.mode)) * 31;
        String str3 = this.state;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "WorkPlanItemDetail(start=" + this.start + ", end=" + this.end + ", mode=" + this.mode + ", state=" + this.state + ")";
    }

    public WorkPlanItemDetail(String start, String end, int i, String str) {
        Intrinsics.checkParameterIsNotNull(start, "start");
        Intrinsics.checkParameterIsNotNull(end, "end");
        this.start = start;
        this.end = end;
        this.mode = i;
        this.state = str;
    }

    public /* synthetic */ WorkPlanItemDetail(String str, String str2, int i, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, (i2 & 8) != 0 ? (String) null : str3);
    }

    public final String getEnd() {
        return this.end;
    }

    public final int getMode() {
        return this.mode;
    }

    public final String getStart() {
        return this.start;
    }

    public final String getState() {
        return this.state;
    }

    public final void setEnd(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.end = str;
    }

    public final void setMode(int i) {
        this.mode = i;
    }

    public final void setStart(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.start = str;
    }

    public final void setState(String str) {
        this.state = str;
    }
}
