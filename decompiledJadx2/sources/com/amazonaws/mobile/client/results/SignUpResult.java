package com.amazonaws.mobile.client.results;

/* loaded from: classes.dex */
public final class SignUpResult {
    private final UserCodeDeliveryDetails cognitoUserCodeDeliveryDetails;
    private final boolean signUpConfirmationState;
    private final String userSub;

    public SignUpResult(boolean z, UserCodeDeliveryDetails userCodeDeliveryDetails, String str) {
        this.signUpConfirmationState = z;
        this.cognitoUserCodeDeliveryDetails = userCodeDeliveryDetails;
        this.userSub = str;
    }

    public String getUserSub() {
        return this.userSub;
    }

    public boolean getConfirmationState() {
        return this.signUpConfirmationState;
    }

    public UserCodeDeliveryDetails getUserCodeDeliveryDetails() {
        return this.cognitoUserCodeDeliveryDetails;
    }
}
