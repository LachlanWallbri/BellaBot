package com.pudutech.mirsdk.sdksafe;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes6.dex
 */
/* compiled from: NetResponse.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/mirsdk/sdksafe/LoginResponse;", "", "ticket", "", "(Ljava/lang/String;)V", "getTicket", "()Ljava/lang/String;", "setTicket", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "sdksafe_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final /* data */ class LoginResponse {
    private String ticket;

    public static /* synthetic */ LoginResponse copy$default(LoginResponse loginResponse, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = loginResponse.ticket;
        }
        return loginResponse.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTicket() {
        return this.ticket;
    }

    public final LoginResponse copy(String ticket) {
        Intrinsics.checkParameterIsNotNull(ticket, "ticket");
        return new LoginResponse(ticket);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof LoginResponse) && Intrinsics.areEqual(this.ticket, ((LoginResponse) other).ticket);
        }
        return true;
    }

    public int hashCode() {
        String str = this.ticket;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "LoginResponse(ticket=" + this.ticket + ")";
    }

    public LoginResponse(String ticket) {
        Intrinsics.checkParameterIsNotNull(ticket, "ticket");
        this.ticket = ticket;
    }

    public final String getTicket() {
        return this.ticket;
    }

    public final void setTicket(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.ticket = str;
    }
}
