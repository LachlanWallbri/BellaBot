package com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;

/* loaded from: classes.dex */
public interface VerificationHandler {
    void onFailure(Exception exc);

    void onSuccess(CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails);
}
