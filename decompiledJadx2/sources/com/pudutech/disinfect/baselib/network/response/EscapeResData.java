package com.pudutech.disinfect.baselib.network.response;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;

/* compiled from: EscapeResData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/EscapeResData;", "", "lock", "", "(Z)V", "getLock", "()Z", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class EscapeResData {

    @SerializedName("lockStatus")
    private final boolean lock;

    public static /* synthetic */ EscapeResData copy$default(EscapeResData escapeResData, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = escapeResData.lock;
        }
        return escapeResData.copy(z);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getLock() {
        return this.lock;
    }

    public final EscapeResData copy(boolean lock) {
        return new EscapeResData(lock);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof EscapeResData) && this.lock == ((EscapeResData) other).lock;
        }
        return true;
    }

    public int hashCode() {
        boolean z = this.lock;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public String toString() {
        return "EscapeResData(lock=" + this.lock + ")";
    }

    public EscapeResData(boolean z) {
        this.lock = z;
    }

    public final boolean getLock() {
        return this.lock;
    }
}
