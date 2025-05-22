package com.pudutech.mirsdk.sdksafe;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.cookie.ClientCookie;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes6.dex
 */
/* compiled from: NetResponse.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdk/sdksafe/TokenResponse;", "", "token", "", ClientCookie.EXPIRES_ATTR, "(Ljava/lang/String;Ljava/lang/String;)V", "getExpires", "()Ljava/lang/String;", "setExpires", "(Ljava/lang/String;)V", "getToken", "setToken", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "sdksafe_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final /* data */ class TokenResponse {
    private String expires;
    private String token;

    public static /* synthetic */ TokenResponse copy$default(TokenResponse tokenResponse, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tokenResponse.token;
        }
        if ((i & 2) != 0) {
            str2 = tokenResponse.expires;
        }
        return tokenResponse.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getToken() {
        return this.token;
    }

    /* renamed from: component2, reason: from getter */
    public final String getExpires() {
        return this.expires;
    }

    public final TokenResponse copy(String token, String expires) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        Intrinsics.checkParameterIsNotNull(expires, "expires");
        return new TokenResponse(token, expires);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TokenResponse)) {
            return false;
        }
        TokenResponse tokenResponse = (TokenResponse) other;
        return Intrinsics.areEqual(this.token, tokenResponse.token) && Intrinsics.areEqual(this.expires, tokenResponse.expires);
    }

    public int hashCode() {
        String str = this.token;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.expires;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "TokenResponse(token=" + this.token + ", expires=" + this.expires + ")";
    }

    public TokenResponse(String token, String expires) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        Intrinsics.checkParameterIsNotNull(expires, "expires");
        this.token = token;
        this.expires = expires;
    }

    public final String getExpires() {
        return this.expires;
    }

    public final String getToken() {
        return this.token;
    }

    public final void setExpires(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.expires = str;
    }

    public final void setToken(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.token = str;
    }
}
