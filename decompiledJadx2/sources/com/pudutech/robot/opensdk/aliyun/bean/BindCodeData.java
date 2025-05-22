package com.pudutech.robot.opensdk.aliyun.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BindCodeData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/BindCodeData;", "", "code", "", "expireTime", "", "(Ljava/lang/String;J)V", "getCode", "()Ljava/lang/String;", "setCode", "(Ljava/lang/String;)V", "getExpireTime", "()J", "setExpireTime", "(J)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class BindCodeData {
    private String code;
    private long expireTime;

    public static /* synthetic */ BindCodeData copy$default(BindCodeData bindCodeData, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bindCodeData.code;
        }
        if ((i & 2) != 0) {
            j = bindCodeData.expireTime;
        }
        return bindCodeData.copy(str, j);
    }

    /* renamed from: component1, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    /* renamed from: component2, reason: from getter */
    public final long getExpireTime() {
        return this.expireTime;
    }

    public final BindCodeData copy(String code, long expireTime) {
        Intrinsics.checkParameterIsNotNull(code, "code");
        return new BindCodeData(code, expireTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BindCodeData)) {
            return false;
        }
        BindCodeData bindCodeData = (BindCodeData) other;
        return Intrinsics.areEqual(this.code, bindCodeData.code) && this.expireTime == bindCodeData.expireTime;
    }

    public int hashCode() {
        String str = this.code;
        return ((str != null ? str.hashCode() : 0) * 31) + Long.hashCode(this.expireTime);
    }

    public String toString() {
        return "BindCodeData(code=" + this.code + ", expireTime=" + this.expireTime + ")";
    }

    public BindCodeData(String code, long j) {
        Intrinsics.checkParameterIsNotNull(code, "code");
        this.code = code;
        this.expireTime = j;
    }

    public final String getCode() {
        return this.code;
    }

    public final long getExpireTime() {
        return this.expireTime;
    }

    public final void setCode(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.code = str;
    }

    public final void setExpireTime(long j) {
        this.expireTime = j;
    }
}
