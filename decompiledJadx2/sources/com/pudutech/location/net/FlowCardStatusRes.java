package com.pudutech.location.net;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FlowCardBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/location/net/FlowCardStatusRes;", "", "code", "", "data", "Lcom/pudutech/location/net/DataCardStatus;", NotificationCompat.CATEGORY_MESSAGE, "", "(ILcom/pudutech/location/net/DataCardStatus;Ljava/lang/String;)V", "getCode", "()I", "getData", "()Lcom/pudutech/location/net/DataCardStatus;", "getMsg", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class FlowCardStatusRes {
    private final int code;
    private final DataCardStatus data;
    private final String msg;

    public static /* synthetic */ FlowCardStatusRes copy$default(FlowCardStatusRes flowCardStatusRes, int i, DataCardStatus dataCardStatus, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = flowCardStatusRes.code;
        }
        if ((i2 & 2) != 0) {
            dataCardStatus = flowCardStatusRes.data;
        }
        if ((i2 & 4) != 0) {
            str = flowCardStatusRes.msg;
        }
        return flowCardStatusRes.copy(i, dataCardStatus, str);
    }

    /* renamed from: component1, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    /* renamed from: component2, reason: from getter */
    public final DataCardStatus getData() {
        return this.data;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMsg() {
        return this.msg;
    }

    public final FlowCardStatusRes copy(int code, DataCardStatus data, String msg) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        return new FlowCardStatusRes(code, data, msg);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FlowCardStatusRes)) {
            return false;
        }
        FlowCardStatusRes flowCardStatusRes = (FlowCardStatusRes) other;
        return this.code == flowCardStatusRes.code && Intrinsics.areEqual(this.data, flowCardStatusRes.data) && Intrinsics.areEqual(this.msg, flowCardStatusRes.msg);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.code) * 31;
        DataCardStatus dataCardStatus = this.data;
        int hashCode2 = (hashCode + (dataCardStatus != null ? dataCardStatus.hashCode() : 0)) * 31;
        String str = this.msg;
        return hashCode2 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "FlowCardStatusRes(code=" + this.code + ", data=" + this.data + ", msg=" + this.msg + ")";
    }

    public FlowCardStatusRes(int i, DataCardStatus data, String msg) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        this.code = i;
        this.data = data;
        this.msg = msg;
    }

    public final int getCode() {
        return this.code;
    }

    public final DataCardStatus getData() {
        return this.data;
    }

    public final String getMsg() {
        return this.msg;
    }
}
