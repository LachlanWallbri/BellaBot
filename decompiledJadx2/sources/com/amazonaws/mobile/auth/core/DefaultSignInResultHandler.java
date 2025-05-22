package com.amazonaws.mobile.auth.core;

import android.app.Activity;
import android.util.Log;

/* loaded from: classes.dex */
public abstract class DefaultSignInResultHandler implements SignInResultHandler {
    private static final String LOG_TAG = DefaultSignInResultHandler.class.getSimpleName();

    @Override // com.amazonaws.mobile.auth.core.SignInResultHandler
    public void onIntermediateProviderCancel(Activity activity, IdentityProvider identityProvider) {
        Log.d(LOG_TAG, String.format("%s Sign-In flow is canceled", identityProvider.getDisplayName()));
    }

    @Override // com.amazonaws.mobile.auth.core.SignInResultHandler
    public void onIntermediateProviderError(Activity activity, IdentityProvider identityProvider, Exception exc) {
        Log.e(LOG_TAG, String.format(activity.getString(C1175R.string.sign_in_failure_message_format), identityProvider.getDisplayName(), exc.getMessage()), exc);
    }
}
