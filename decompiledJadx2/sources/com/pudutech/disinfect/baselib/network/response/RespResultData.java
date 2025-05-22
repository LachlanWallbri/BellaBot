package com.pudutech.disinfect.baselib.network.response;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RespResultData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/RespResultData;", "", "success", "", "(Z)V", "getSuccess", "()Z", "setSuccess", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class RespResultData {
    private boolean success;

    public static /* synthetic */ RespResultData copy$default(RespResultData respResultData, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = respResultData.success;
        }
        return respResultData.copy(z);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getSuccess() {
        return this.success;
    }

    public final RespResultData copy(boolean success) {
        return new RespResultData(success);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof RespResultData) && this.success == ((RespResultData) other).success;
        }
        return true;
    }

    public int hashCode() {
        boolean z = this.success;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public String toString() {
        return "RespResultData(success=" + this.success + ")";
    }

    public RespResultData(boolean z) {
        this.success = z;
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public final void setSuccess(boolean z) {
        this.success = z;
    }
}
