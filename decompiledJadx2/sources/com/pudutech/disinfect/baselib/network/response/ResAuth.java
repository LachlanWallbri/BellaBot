package com.pudutech.disinfect.baselib.network.response;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: ResAuth.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\u0003HÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/ResAuth;", "", "code", "", "(I)V", "getCode", "()I", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class ResAuth {
    private final int code;

    public static /* synthetic */ ResAuth copy$default(ResAuth resAuth, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = resAuth.code;
        }
        return resAuth.copy(i);
    }

    /* renamed from: component1, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    public final ResAuth copy(int code) {
        return new ResAuth(code);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof ResAuth) && this.code == ((ResAuth) other).code;
        }
        return true;
    }

    public int hashCode() {
        return Integer.hashCode(this.code);
    }

    public String toString() {
        return "ResAuth(code=" + this.code + ")";
    }

    public ResAuth(int i) {
        this.code = i;
    }

    public final int getCode() {
        return this.code;
    }
}
