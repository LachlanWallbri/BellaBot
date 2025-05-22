package com.pudutech.bumblebee.presenter.net.resp;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TokenResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/net/resp/TokenResp;", "", "code", "", "token", "Lcom/pudutech/bumblebee/presenter/net/resp/TokenBean;", "(ILcom/pudutech/bumblebee/presenter/net/resp/TokenBean;)V", "getCode", "()I", "setCode", "(I)V", "getToken", "()Lcom/pudutech/bumblebee/presenter/net/resp/TokenBean;", "setToken", "(Lcom/pudutech/bumblebee/presenter/net/resp/TokenBean;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class TokenResp {

    @SerializedName("code")
    private int code;

    @SerializedName("token")
    private TokenBean token;

    public static /* synthetic */ TokenResp copy$default(TokenResp tokenResp, int i, TokenBean tokenBean, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = tokenResp.code;
        }
        if ((i2 & 2) != 0) {
            tokenBean = tokenResp.token;
        }
        return tokenResp.copy(i, tokenBean);
    }

    /* renamed from: component1, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    /* renamed from: component2, reason: from getter */
    public final TokenBean getToken() {
        return this.token;
    }

    public final TokenResp copy(int code, TokenBean token) {
        return new TokenResp(code, token);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TokenResp)) {
            return false;
        }
        TokenResp tokenResp = (TokenResp) other;
        return this.code == tokenResp.code && Intrinsics.areEqual(this.token, tokenResp.token);
    }

    public int hashCode() {
        int i = this.code * 31;
        TokenBean tokenBean = this.token;
        return i + (tokenBean != null ? tokenBean.hashCode() : 0);
    }

    public String toString() {
        return "TokenResp(code=" + this.code + ", token=" + this.token + ")";
    }

    public TokenResp(int i, TokenBean tokenBean) {
        this.code = i;
        this.token = tokenBean;
    }

    public final int getCode() {
        return this.code;
    }

    public final void setCode(int i) {
        this.code = i;
    }

    public final TokenBean getToken() {
        return this.token;
    }

    public final void setToken(TokenBean tokenBean) {
        this.token = tokenBean;
    }
}
