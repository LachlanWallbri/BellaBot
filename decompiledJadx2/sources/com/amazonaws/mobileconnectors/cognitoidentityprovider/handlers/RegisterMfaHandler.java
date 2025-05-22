package com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.VerifyMfaContinuation;

/* loaded from: classes.dex */
public interface RegisterMfaHandler {
    void onFailure(Exception exc);

    void onSuccess(String str);

    void onVerify(VerifyMfaContinuation verifyMfaContinuation);
}
