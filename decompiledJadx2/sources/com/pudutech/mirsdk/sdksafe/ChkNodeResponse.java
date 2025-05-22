package com.pudutech.mirsdk.sdksafe;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes6.dex
 */
/* compiled from: NetResponse.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdk/sdksafe/ChkNodeResponse;", "", "token_status", "", "permission_status", "(ZZ)V", "getPermission_status", "()Z", "setPermission_status", "(Z)V", "getToken_status", "setToken_status", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "sdksafe_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final /* data */ class ChkNodeResponse {
    private boolean permission_status;
    private boolean token_status;

    public static /* synthetic */ ChkNodeResponse copy$default(ChkNodeResponse chkNodeResponse, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = chkNodeResponse.token_status;
        }
        if ((i & 2) != 0) {
            z2 = chkNodeResponse.permission_status;
        }
        return chkNodeResponse.copy(z, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getToken_status() {
        return this.token_status;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getPermission_status() {
        return this.permission_status;
    }

    public final ChkNodeResponse copy(boolean token_status, boolean permission_status) {
        return new ChkNodeResponse(token_status, permission_status);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChkNodeResponse)) {
            return false;
        }
        ChkNodeResponse chkNodeResponse = (ChkNodeResponse) other;
        return this.token_status == chkNodeResponse.token_status && this.permission_status == chkNodeResponse.permission_status;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public int hashCode() {
        boolean z = this.token_status;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.permission_status;
        return i + (z2 ? 1 : z2 ? 1 : 0);
    }

    public String toString() {
        return "ChkNodeResponse(token_status=" + this.token_status + ", permission_status=" + this.permission_status + ")";
    }

    public ChkNodeResponse(boolean z, boolean z2) {
        this.token_status = z;
        this.permission_status = z2;
    }

    public final boolean getPermission_status() {
        return this.permission_status;
    }

    public final boolean getToken_status() {
        return this.token_status;
    }

    public final void setPermission_status(boolean z) {
        this.permission_status = z;
    }

    public final void setToken_status(boolean z) {
        this.token_status = z;
    }
}
