package com.pudutech.robot.opensdk.aliyun.bean;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ShadowData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowUpdateTime;", "", "timestamp", "", "(J)V", "getTimestamp", "()J", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class ShadowUpdateTime {
    private final long timestamp;

    public static /* synthetic */ ShadowUpdateTime copy$default(ShadowUpdateTime shadowUpdateTime, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = shadowUpdateTime.timestamp;
        }
        return shadowUpdateTime.copy(j);
    }

    /* renamed from: component1, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    public final ShadowUpdateTime copy(long timestamp) {
        return new ShadowUpdateTime(timestamp);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof ShadowUpdateTime) && this.timestamp == ((ShadowUpdateTime) other).timestamp;
        }
        return true;
    }

    public int hashCode() {
        return Long.hashCode(this.timestamp);
    }

    public String toString() {
        return "ShadowUpdateTime(timestamp=" + this.timestamp + ")";
    }

    public ShadowUpdateTime(long j) {
        this.timestamp = j;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }
}
