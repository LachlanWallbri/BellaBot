package com.pudutech.location.net;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FlowCardBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/location/net/FlowCardReportReq;", "", "data", "Lcom/pudutech/location/net/Data;", "hardver", "", "mac", "softver", "timestamp", "", "(Lcom/pudutech/location/net/Data;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getData", "()Lcom/pudutech/location/net/Data;", "getHardver", "()Ljava/lang/String;", "getMac", "getSoftver", "getTimestamp", "()J", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class FlowCardReportReq {
    private final Data data;
    private final String hardver;
    private final String mac;
    private final String softver;
    private final long timestamp;

    public static /* synthetic */ FlowCardReportReq copy$default(FlowCardReportReq flowCardReportReq, Data data, String str, String str2, String str3, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            data = flowCardReportReq.data;
        }
        if ((i & 2) != 0) {
            str = flowCardReportReq.hardver;
        }
        String str4 = str;
        if ((i & 4) != 0) {
            str2 = flowCardReportReq.mac;
        }
        String str5 = str2;
        if ((i & 8) != 0) {
            str3 = flowCardReportReq.softver;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            j = flowCardReportReq.timestamp;
        }
        return flowCardReportReq.copy(data, str4, str5, str6, j);
    }

    /* renamed from: component1, reason: from getter */
    public final Data getData() {
        return this.data;
    }

    /* renamed from: component2, reason: from getter */
    public final String getHardver() {
        return this.hardver;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component4, reason: from getter */
    public final String getSoftver() {
        return this.softver;
    }

    /* renamed from: component5, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    public final FlowCardReportReq copy(Data data, String hardver, String mac, String softver, long timestamp) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Intrinsics.checkParameterIsNotNull(hardver, "hardver");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(softver, "softver");
        return new FlowCardReportReq(data, hardver, mac, softver, timestamp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FlowCardReportReq)) {
            return false;
        }
        FlowCardReportReq flowCardReportReq = (FlowCardReportReq) other;
        return Intrinsics.areEqual(this.data, flowCardReportReq.data) && Intrinsics.areEqual(this.hardver, flowCardReportReq.hardver) && Intrinsics.areEqual(this.mac, flowCardReportReq.mac) && Intrinsics.areEqual(this.softver, flowCardReportReq.softver) && this.timestamp == flowCardReportReq.timestamp;
    }

    public int hashCode() {
        Data data = this.data;
        int hashCode = (data != null ? data.hashCode() : 0) * 31;
        String str = this.hardver;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.mac;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.softver;
        return ((hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + Long.hashCode(this.timestamp);
    }

    public String toString() {
        return "FlowCardReportReq(data=" + this.data + ", hardver=" + this.hardver + ", mac=" + this.mac + ", softver=" + this.softver + ", timestamp=" + this.timestamp + ")";
    }

    public FlowCardReportReq(Data data, String hardver, String mac, String softver, long j) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Intrinsics.checkParameterIsNotNull(hardver, "hardver");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(softver, "softver");
        this.data = data;
        this.hardver = hardver;
        this.mac = mac;
        this.softver = softver;
        this.timestamp = j;
    }

    public final Data getData() {
        return this.data;
    }

    public final String getHardver() {
        return this.hardver;
    }

    public final String getMac() {
        return this.mac;
    }

    public final String getSoftver() {
        return this.softver;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }
}
