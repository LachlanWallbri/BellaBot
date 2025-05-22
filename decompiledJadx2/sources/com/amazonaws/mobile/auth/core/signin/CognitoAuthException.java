package com.amazonaws.mobile.auth.core.signin;

import com.amazonaws.mobile.auth.core.IdentityProvider;

/* loaded from: classes.dex */
public class CognitoAuthException extends ProviderAuthException {
    public CognitoAuthException(IdentityProvider identityProvider, Exception exc) {
        super(identityProvider, exc);
    }
}
