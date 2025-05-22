package com.amazonaws.mobile.client.results;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.util.CognitoServiceConstants;

/* loaded from: classes.dex */
public enum SignInState {
    SMS_MFA("CONFIRMATION_CODE"),
    PASSWORD_VERIFIER(CognitoServiceConstants.CHLG_TYPE_USER_PASSWORD_VERIFIER),
    CUSTOM_CHALLENGE(CognitoServiceConstants.CHLG_TYPE_CUSTOM_CHALLENGE),
    DEVICE_SRP_AUTH(CognitoServiceConstants.CHLG_TYPE_DEVICE_SRP_AUTH),
    DEVICE_PASSWORD_VERIFIER(CognitoServiceConstants.CHLG_TYPE_DEVICE_PASSWORD_VERIFIER),
    ADMIN_NO_SRP_AUTH("ADMIN_NO_SRP_AUTH"),
    NEW_PASSWORD_REQUIRED(CognitoServiceConstants.CHLG_TYPE_NEW_PASSWORD_REQUIRED),
    DONE("This means the flow is complete."),
    UNKNOWN("UNKNOWN");

    private final String serviceText;

    SignInState(String str) {
        this.serviceText = str;
    }

    public boolean equals(String str) {
        return this.serviceText.equals(str);
    }
}
