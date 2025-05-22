package com.amazonaws.mobile.client;

import com.amazonaws.auth.AWSAbstractCognitoIdentityProvider;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AWSMobileClient.java */
/* loaded from: classes.dex */
public class AWSMobileClientCognitoIdentityProvider extends AWSAbstractCognitoIdentityProvider {
    boolean isDeveloperAuthenticated;

    @Override // com.amazonaws.auth.AWSAbstractCognitoIdentityProvider
    public String getProviderName() {
        return "Cognito";
    }

    @Override // com.amazonaws.auth.AWSAbstractCognitoIdentityProvider
    protected String getUserAgent() {
        return AWSMobileClient.DEFAULT_USER_AGENT;
    }

    public AWSMobileClientCognitoIdentityProvider(String str, String str2, AmazonCognitoIdentity amazonCognitoIdentity) {
        super(str, str2, amazonCognitoIdentity);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDeveloperAuthenticated(String str, String str2) {
        super.setIdentityId(str);
        super.setToken(str2);
        this.isDeveloperAuthenticated = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNotDeveloperAuthenticated() {
        this.isDeveloperAuthenticated = false;
    }

    @Override // com.amazonaws.auth.AWSAbstractCognitoIdentityProvider, com.amazonaws.auth.AWSIdentityProvider
    public String refresh() {
        if (this.isDeveloperAuthenticated) {
            return this.token;
        }
        getIdentityId();
        return null;
    }
}
