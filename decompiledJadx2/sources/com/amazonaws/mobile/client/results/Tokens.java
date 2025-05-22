package com.amazonaws.mobile.client.results;

/* loaded from: classes.dex */
public class Tokens {
    private final Token accessToken;
    private final Token idToken;
    private final Token refreshToken;

    public Tokens(String str, String str2, String str3) {
        this.accessToken = new Token(str);
        this.idToken = new Token(str2);
        this.refreshToken = new Token(str3);
    }

    public Token getAccessToken() {
        return this.accessToken;
    }

    public Token getIdToken() {
        return this.idToken;
    }

    public Token getRefreshToken() {
        return this.refreshToken;
    }
}
