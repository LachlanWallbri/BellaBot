package com.amazonaws.mobileconnectors.cognitoidentityprovider.tokens;

/* loaded from: classes.dex */
public class CognitoUserToken {
    private final String token;

    public CognitoUserToken(String str) {
        this.token = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getToken() {
        return this.token;
    }
}
