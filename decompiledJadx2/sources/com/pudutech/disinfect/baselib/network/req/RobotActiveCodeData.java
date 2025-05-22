package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RobotActiveCodeReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/RobotActiveCodeData;", "", "name", "", "pid", "code", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "setCode", "(Ljava/lang/String;)V", "getName", "setName", "getPid", "setPid", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class RobotActiveCodeData {
    private String code;
    private String name;
    private String pid;

    public RobotActiveCodeData() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ RobotActiveCodeData copy$default(RobotActiveCodeData robotActiveCodeData, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = robotActiveCodeData.name;
        }
        if ((i & 2) != 0) {
            str2 = robotActiveCodeData.pid;
        }
        if ((i & 4) != 0) {
            str3 = robotActiveCodeData.code;
        }
        return robotActiveCodeData.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPid() {
        return this.pid;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    public final RobotActiveCodeData copy(String name, String pid, String code) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(code, "code");
        return new RobotActiveCodeData(name, pid, code);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RobotActiveCodeData)) {
            return false;
        }
        RobotActiveCodeData robotActiveCodeData = (RobotActiveCodeData) other;
        return Intrinsics.areEqual(this.name, robotActiveCodeData.name) && Intrinsics.areEqual(this.pid, robotActiveCodeData.pid) && Intrinsics.areEqual(this.code, robotActiveCodeData.code);
    }

    public int hashCode() {
        String str = this.name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.pid;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.code;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "RobotActiveCodeData(name=" + this.name + ", pid=" + this.pid + ", code=" + this.code + ")";
    }

    public RobotActiveCodeData(String name, String pid, String code) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(code, "code");
        this.name = name;
        this.pid = pid;
        this.code = code;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final String getPid() {
        return this.pid;
    }

    public final void setPid(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.pid = str;
    }

    public /* synthetic */ RobotActiveCodeData(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3);
    }

    public final String getCode() {
        return this.code;
    }

    public final void setCode(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.code = str;
    }
}
