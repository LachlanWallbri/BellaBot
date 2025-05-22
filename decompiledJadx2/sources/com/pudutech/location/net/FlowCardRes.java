package com.pudutech.location.net;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FlowCardBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/location/net/FlowCardRes;", "", "code", "", NotificationCompat.CATEGORY_MESSAGE, "", "(ILjava/lang/String;)V", "getCode", "()I", "getMsg", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class FlowCardRes {
    private final int code;
    private final String msg;

    public static /* synthetic */ FlowCardRes copy$default(FlowCardRes flowCardRes, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = flowCardRes.code;
        }
        if ((i2 & 2) != 0) {
            str = flowCardRes.msg;
        }
        return flowCardRes.copy(i, str);
    }

    /* renamed from: component1, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMsg() {
        return this.msg;
    }

    public final FlowCardRes copy(int code, String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        return new FlowCardRes(code, msg);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FlowCardRes)) {
            return false;
        }
        FlowCardRes flowCardRes = (FlowCardRes) other;
        return this.code == flowCardRes.code && Intrinsics.areEqual(this.msg, flowCardRes.msg);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.code) * 31;
        String str = this.msg;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "FlowCardRes(code=" + this.code + ", msg=" + this.msg + ")";
    }

    public FlowCardRes(int i, String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        this.code = i;
        this.msg = msg;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getMsg() {
        return this.msg;
    }
}
