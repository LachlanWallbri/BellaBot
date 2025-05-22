package com.amazonaws.mobile.client.internal.oauth2;

/* loaded from: classes.dex */
public class OAuth2Exception extends RuntimeException {
    String error;
    String errorDescription;
    String errorUri;

    public OAuth2Exception(String str, String str2, String str3, String str4) {
        super(str);
        this.error = str2;
        this.errorDescription = str3;
        this.errorUri = str4;
    }

    public String getError() {
        return this.error;
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

    public String getErrorUri() {
        return this.errorUri;
    }
}
