package com.pudutech.mirsdk.cloud;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SDKCloudReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/mirsdk/cloud/CloudMsgHead;", "", "mac", "", "timestamp", "", "(Ljava/lang/String;J)V", "getMac", "()Ljava/lang/String;", "setMac", "(Ljava/lang/String;)V", "getTimestamp", "()J", "setTimestamp", "(J)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CloudMsgHead {
    private String mac;
    private long timestamp;

    public CloudMsgHead() {
        this(null, 0L, 3, null);
    }

    public CloudMsgHead(String mac, long j) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        this.mac = mac;
        this.timestamp = j;
    }

    public /* synthetic */ CloudMsgHead(String str, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "00:00:00:00:00:00" : str, (i & 2) != 0 ? 0L : j);
    }

    public final String getMac() {
        return this.mac;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mac = str;
    }

    public final void setTimestamp(long j) {
        this.timestamp = j;
    }
}
