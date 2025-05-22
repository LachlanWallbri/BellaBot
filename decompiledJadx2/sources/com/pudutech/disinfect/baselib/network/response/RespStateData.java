package com.pudutech.disinfect.baselib.network.response;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RespStateData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/RespStateData;", "", "state", "", "(Z)V", "getState", "()Z", "setState", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class RespStateData {
    private boolean state;

    public static /* synthetic */ RespStateData copy$default(RespStateData respStateData, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = respStateData.state;
        }
        return respStateData.copy(z);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getState() {
        return this.state;
    }

    public final RespStateData copy(boolean state) {
        return new RespStateData(state);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof RespStateData) && this.state == ((RespStateData) other).state;
        }
        return true;
    }

    public int hashCode() {
        boolean z = this.state;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public String toString() {
        return "RespStateData(state=" + this.state + ")";
    }

    public RespStateData(boolean z) {
        this.state = z;
    }

    public final boolean getState() {
        return this.state;
    }

    public final void setState(boolean z) {
        this.state = z;
    }
}
