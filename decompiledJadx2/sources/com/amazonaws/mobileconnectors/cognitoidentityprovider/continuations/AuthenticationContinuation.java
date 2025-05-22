package com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations;

import android.content.Context;
import android.os.Handler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class AuthenticationContinuation implements CognitoIdentityProviderContinuation<String> {
    public static final boolean RUN_IN_BACKGROUND = true;
    public static final boolean RUN_IN_CURRENT = false;
    private final AuthenticationHandler callback;
    private final Context context;
    private final boolean runInBackground;
    private final CognitoUser user;
    private AuthenticationDetails authenticationDetails = null;
    private final Map<String, String> clientMetadata = new HashMap();

    @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.CognitoIdentityProviderContinuation
    public String getParameters() {
        return "AuthenticationDetails";
    }

    public AuthenticationContinuation(CognitoUser cognitoUser, Context context, boolean z, AuthenticationHandler authenticationHandler) {
        this.user = cognitoUser;
        this.context = context;
        this.runInBackground = z;
        this.callback = authenticationHandler;
    }

    public Map<String, String> getClientMetaData() {
        return Collections.unmodifiableMap(this.clientMetadata);
    }

    public void setClientMetaData(Map<String, String> map) {
        this.clientMetadata.clear();
        if (map != null) {
            this.clientMetadata.putAll(map);
        }
    }

    @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.CognitoIdentityProviderContinuation
    public void continueTask() {
        Runnable runnable;
        if (this.runInBackground) {
            new Thread(new Runnable() { // from class: com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation.1
                @Override // java.lang.Runnable
                public void run() {
                    Runnable runnable2;
                    Handler handler = new Handler(AuthenticationContinuation.this.context.getMainLooper());
                    try {
                        runnable2 = AuthenticationContinuation.this.user.initiateUserAuthentication(AuthenticationContinuation.this.clientMetadata, AuthenticationContinuation.this.authenticationDetails, AuthenticationContinuation.this.callback, true);
                    } catch (Exception e) {
                        runnable2 = new Runnable() { // from class: com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                AuthenticationContinuation.this.callback.onFailure(e);
                            }
                        };
                    }
                    handler.post(runnable2);
                }
            }).start();
            return;
        }
        try {
            runnable = this.user.initiateUserAuthentication(this.clientMetadata, this.authenticationDetails, this.callback, false);
        } catch (Exception e) {
            runnable = new Runnable() { // from class: com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation.2
                @Override // java.lang.Runnable
                public void run() {
                    AuthenticationContinuation.this.callback.onFailure(e);
                }
            };
        }
        runnable.run();
    }

    public void setAuthenticationDetails(AuthenticationDetails authenticationDetails) {
        this.authenticationDetails = authenticationDetails;
    }
}
