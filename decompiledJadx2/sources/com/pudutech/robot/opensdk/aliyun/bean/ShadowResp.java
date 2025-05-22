package com.pudutech.robot.opensdk.aliyun.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: ShadowData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003J1\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0007HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006%"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowResp;", "", "method", "", MqttServiceConstants.PAYLOAD, "Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowData;", "version", "", "timestamp", "", "(Ljava/lang/String;Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowData;IJ)V", "getMethod", "()Ljava/lang/String;", "setMethod", "(Ljava/lang/String;)V", "getPayload", "()Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowData;", "setPayload", "(Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowData;)V", "getTimestamp", "()J", "setTimestamp", "(J)V", "getVersion", "()I", "setVersion", "(I)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class ShadowResp {
    private String method;
    private ShadowData payload;
    private long timestamp;
    private int version;

    public static /* synthetic */ ShadowResp copy$default(ShadowResp shadowResp, String str, ShadowData shadowData, int i, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = shadowResp.method;
        }
        if ((i2 & 2) != 0) {
            shadowData = shadowResp.payload;
        }
        ShadowData shadowData2 = shadowData;
        if ((i2 & 4) != 0) {
            i = shadowResp.version;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            j = shadowResp.timestamp;
        }
        return shadowResp.copy(str, shadowData2, i3, j);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMethod() {
        return this.method;
    }

    /* renamed from: component2, reason: from getter */
    public final ShadowData getPayload() {
        return this.payload;
    }

    /* renamed from: component3, reason: from getter */
    public final int getVersion() {
        return this.version;
    }

    /* renamed from: component4, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    public final ShadowResp copy(String method, ShadowData payload, int version, long timestamp) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        return new ShadowResp(method, payload, version, timestamp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShadowResp)) {
            return false;
        }
        ShadowResp shadowResp = (ShadowResp) other;
        return Intrinsics.areEqual(this.method, shadowResp.method) && Intrinsics.areEqual(this.payload, shadowResp.payload) && this.version == shadowResp.version && this.timestamp == shadowResp.timestamp;
    }

    public int hashCode() {
        String str = this.method;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        ShadowData shadowData = this.payload;
        return ((((hashCode + (shadowData != null ? shadowData.hashCode() : 0)) * 31) + Integer.hashCode(this.version)) * 31) + Long.hashCode(this.timestamp);
    }

    public String toString() {
        return "ShadowResp(method=" + this.method + ", payload=" + this.payload + ", version=" + this.version + ", timestamp=" + this.timestamp + ")";
    }

    public ShadowResp(String method, ShadowData payload, int i, long j) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        this.method = method;
        this.payload = payload;
        this.version = i;
        this.timestamp = j;
    }

    public final String getMethod() {
        return this.method;
    }

    public final void setMethod(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.method = str;
    }

    public final ShadowData getPayload() {
        return this.payload;
    }

    public final void setPayload(ShadowData shadowData) {
        Intrinsics.checkParameterIsNotNull(shadowData, "<set-?>");
        this.payload = shadowData;
    }

    public final int getVersion() {
        return this.version;
    }

    public final void setVersion(int i) {
        this.version = i;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final void setTimestamp(long j) {
        this.timestamp = j;
    }
}
