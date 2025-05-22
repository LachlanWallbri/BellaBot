package com.amazonaws.mobile.client.internal.oauth2;

/* loaded from: classes.dex */
public class OAuth2Tokens {
    String accessToken;
    Long createDate;
    Long expiresIn;
    String idToken;
    String refreshToken;
    String scopes;
    String tokenType;

    public OAuth2Tokens(String str, String str2, String str3, String str4, Long l, Long l2, String str5) {
        this.accessToken = str;
        this.idToken = str2;
        this.refreshToken = str3;
        this.tokenType = str4;
        this.expiresIn = l;
        this.createDate = l2;
        this.scopes = str5;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getIdToken() {
        return this.idToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public String getScopes() {
        return this.scopes;
    }

    public Long getExpiresIn() {
        return this.expiresIn;
    }

    public Long getCreateDate() {
        return this.createDate;
    }
}
