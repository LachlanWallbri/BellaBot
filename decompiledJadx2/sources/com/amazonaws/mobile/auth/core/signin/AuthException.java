package com.amazonaws.mobile.auth.core.signin;

import com.amazonaws.mobile.auth.core.IdentityProvider;

/* loaded from: classes.dex */
public class AuthException extends Exception {
    protected final IdentityProvider provider;

    public AuthException(IdentityProvider identityProvider, Exception exc) {
        super(exc);
        this.provider = identityProvider;
    }

    public AuthException(Exception exc) {
        this(null, exc);
    }

    public IdentityProvider getProvider() {
        return this.provider;
    }
}
