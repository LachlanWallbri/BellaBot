package com.pudutech.leaselib.bean;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ReqLeaseData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001e\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001e\u0010\u001b\u001a\u00020\r8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R\u001e\u0010\u001e\u001a\u00020\u001f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006$"}, m3961d2 = {"Lcom/pudutech/leaselib/bean/ReqLeaseData;", "", "()V", "hardver", "", "getHardver", "()Ljava/lang/String;", "setHardver", "(Ljava/lang/String;)V", "mac", "getMac", "setMac", "remainingTime", "", "getRemainingTime", "()J", "setRemainingTime", "(J)V", "softver", "getSoftver", "setSoftver", "timestamp", "getTimestamp", "setTimestamp", "type", "getType", "setType", "useEndTime", "getUseEndTime", "setUseEndTime", "useType", "", "getUseType", "()I", "setUseType", "(I)V", "lib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ReqLeaseData {

    @SerializedName("remaining_time")
    private long remainingTime;
    private long timestamp;

    @SerializedName("use_end_time")
    private long useEndTime;

    @SerializedName("use_type")
    private int useType;
    private String mac = "";
    private String type = "lease";
    private String softver = "";
    private String hardver = "";

    public final String getMac() {
        return this.mac;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mac = str;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final void setTimestamp(long j) {
        this.timestamp = j;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.type = str;
    }

    public final String getSoftver() {
        return this.softver;
    }

    public final void setSoftver(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.softver = str;
    }

    public final String getHardver() {
        return this.hardver;
    }

    public final void setHardver(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.hardver = str;
    }

    public final int getUseType() {
        return this.useType;
    }

    public final void setUseType(int i) {
        this.useType = i;
    }

    public final long getUseEndTime() {
        return this.useEndTime;
    }

    public final void setUseEndTime(long j) {
        this.useEndTime = j;
    }

    public final long getRemainingTime() {
        return this.remainingTime;
    }

    public final void setRemainingTime(long j) {
        this.remainingTime = j;
    }
}
