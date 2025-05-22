package com.pudutech.bumblebee.presenter.net.resp;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TokenResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/net/resp/TokenBean;", "", "accessToken", "", "accessTokenExpiresAt", "(Ljava/lang/String;Ljava/lang/String;)V", "getAccessToken", "()Ljava/lang/String;", "setAccessToken", "(Ljava/lang/String;)V", "getAccessTokenExpiresAt", "setAccessTokenExpiresAt", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class TokenBean {

    @SerializedName("accessToken")
    private String accessToken;

    @SerializedName("accessTokenExpiresAt")
    private String accessTokenExpiresAt;

    public static /* synthetic */ TokenBean copy$default(TokenBean tokenBean, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tokenBean.accessToken;
        }
        if ((i & 2) != 0) {
            str2 = tokenBean.accessTokenExpiresAt;
        }
        return tokenBean.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAccessToken() {
        return this.accessToken;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAccessTokenExpiresAt() {
        return this.accessTokenExpiresAt;
    }

    public final TokenBean copy(String accessToken, String accessTokenExpiresAt) {
        Intrinsics.checkParameterIsNotNull(accessToken, "accessToken");
        Intrinsics.checkParameterIsNotNull(accessTokenExpiresAt, "accessTokenExpiresAt");
        return new TokenBean(accessToken, accessTokenExpiresAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TokenBean)) {
            return false;
        }
        TokenBean tokenBean = (TokenBean) other;
        return Intrinsics.areEqual(this.accessToken, tokenBean.accessToken) && Intrinsics.areEqual(this.accessTokenExpiresAt, tokenBean.accessTokenExpiresAt);
    }

    public int hashCode() {
        String str = this.accessToken;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.accessTokenExpiresAt;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "TokenBean(accessToken=" + this.accessToken + ", accessTokenExpiresAt=" + this.accessTokenExpiresAt + ")";
    }

    public TokenBean(String accessToken, String accessTokenExpiresAt) {
        Intrinsics.checkParameterIsNotNull(accessToken, "accessToken");
        Intrinsics.checkParameterIsNotNull(accessTokenExpiresAt, "accessTokenExpiresAt");
        this.accessToken = accessToken;
        this.accessTokenExpiresAt = accessTokenExpiresAt;
    }

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final void setAccessToken(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.accessToken = str;
    }

    public final String getAccessTokenExpiresAt() {
        return this.accessTokenExpiresAt;
    }

    public final void setAccessTokenExpiresAt(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.accessTokenExpiresAt = str;
    }
}
