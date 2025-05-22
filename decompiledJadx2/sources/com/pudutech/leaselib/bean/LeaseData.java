package com.pudutech.leaselib.bean;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LeaseData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001e\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/leaselib/bean/LeaseData;", "", "()V", "remainingTime", "", "getRemainingTime", "()J", "setRemainingTime", "(J)V", "useEndTime", "getUseEndTime", "setUseEndTime", "useType", "", "getUseType", "()I", "setUseType", "(I)V", "toString", "", "lib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LeaseData {

    @SerializedName("remaining_time")
    private long remainingTime;

    @SerializedName("use_end_time")
    private long useEndTime;

    @SerializedName("use_type")
    private int useType;

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

    public String toString() {
        return "LeaseData(useType=" + this.useType + ", useEndTime=" + this.useEndTime + ", remainingTime=" + this.remainingTime + ')';
    }
}
