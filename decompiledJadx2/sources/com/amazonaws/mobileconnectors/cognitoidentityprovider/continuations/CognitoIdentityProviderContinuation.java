package com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations;

/* loaded from: classes.dex */
public interface CognitoIdentityProviderContinuation<T> {
    void continueTask();

    T getParameters();
}
