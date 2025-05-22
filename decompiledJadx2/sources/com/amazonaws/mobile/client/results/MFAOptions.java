package com.amazonaws.mobile.client.results;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoMfaSettings;

/* loaded from: classes.dex */
public enum MFAOptions {
    SMS_MFA("SMS_MFA"),
    TOTP_MFA(CognitoMfaSettings.TOTP_MFA);

    private final String serviceText;

    MFAOptions(String str) {
        this.serviceText = str;
    }

    public boolean equals(String str) {
        return this.serviceText.equals(str);
    }

    public String getServiceText() {
        return this.serviceText;
    }
}
