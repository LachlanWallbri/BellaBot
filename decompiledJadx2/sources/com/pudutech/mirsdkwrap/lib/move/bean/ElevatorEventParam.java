package com.pudutech.mirsdkwrap.lib.move.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElevatorEventParam.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/bean/ElevatorEventParam;", "", "curr_floor", "", "dst_floor", "ele_id", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCurr_floor", "()Ljava/lang/String;", "setCurr_floor", "(Ljava/lang/String;)V", "getDst_floor", "setDst_floor", "getEle_id", "setEle_id", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class ElevatorEventParam {
    private String curr_floor;
    private String dst_floor;
    private String ele_id;

    public static /* synthetic */ ElevatorEventParam copy$default(ElevatorEventParam elevatorEventParam, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = elevatorEventParam.curr_floor;
        }
        if ((i & 2) != 0) {
            str2 = elevatorEventParam.dst_floor;
        }
        if ((i & 4) != 0) {
            str3 = elevatorEventParam.ele_id;
        }
        return elevatorEventParam.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getCurr_floor() {
        return this.curr_floor;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDst_floor() {
        return this.dst_floor;
    }

    /* renamed from: component3, reason: from getter */
    public final String getEle_id() {
        return this.ele_id;
    }

    public final ElevatorEventParam copy(String curr_floor, String dst_floor, String ele_id) {
        Intrinsics.checkParameterIsNotNull(curr_floor, "curr_floor");
        Intrinsics.checkParameterIsNotNull(dst_floor, "dst_floor");
        Intrinsics.checkParameterIsNotNull(ele_id, "ele_id");
        return new ElevatorEventParam(curr_floor, dst_floor, ele_id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ElevatorEventParam)) {
            return false;
        }
        ElevatorEventParam elevatorEventParam = (ElevatorEventParam) other;
        return Intrinsics.areEqual(this.curr_floor, elevatorEventParam.curr_floor) && Intrinsics.areEqual(this.dst_floor, elevatorEventParam.dst_floor) && Intrinsics.areEqual(this.ele_id, elevatorEventParam.ele_id);
    }

    public int hashCode() {
        String str = this.curr_floor;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.dst_floor;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.ele_id;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "ElevatorEventParam(curr_floor=" + this.curr_floor + ", dst_floor=" + this.dst_floor + ", ele_id=" + this.ele_id + ")";
    }

    public ElevatorEventParam(String curr_floor, String dst_floor, String ele_id) {
        Intrinsics.checkParameterIsNotNull(curr_floor, "curr_floor");
        Intrinsics.checkParameterIsNotNull(dst_floor, "dst_floor");
        Intrinsics.checkParameterIsNotNull(ele_id, "ele_id");
        this.curr_floor = curr_floor;
        this.dst_floor = dst_floor;
        this.ele_id = ele_id;
    }

    public final String getCurr_floor() {
        return this.curr_floor;
    }

    public final String getDst_floor() {
        return this.dst_floor;
    }

    public final String getEle_id() {
        return this.ele_id;
    }

    public final void setCurr_floor(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.curr_floor = str;
    }

    public final void setDst_floor(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.dst_floor = str;
    }

    public final void setEle_id(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.ele_id = str;
    }
}
