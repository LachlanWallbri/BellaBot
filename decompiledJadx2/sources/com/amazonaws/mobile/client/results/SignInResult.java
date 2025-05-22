package com.amazonaws.mobile.client.results;

import java.util.Map;

/* loaded from: classes.dex */
public class SignInResult {
    public static final SignInResult DONE = new SignInResult(SignInState.DONE);
    private final UserCodeDeliveryDetails codeDetails;
    private final Map<String, String> parameters;
    private final SignInState signInState;

    private SignInResult(SignInState signInState) {
        this.signInState = signInState;
        this.parameters = null;
        this.codeDetails = null;
    }

    public SignInResult(SignInState signInState, Map<String, String> map) {
        this.signInState = signInState;
        this.parameters = map;
        this.codeDetails = null;
    }

    public SignInResult(SignInState signInState, UserCodeDeliveryDetails userCodeDeliveryDetails) {
        this.signInState = signInState;
        this.parameters = null;
        this.codeDetails = userCodeDeliveryDetails;
    }

    public SignInState getSignInState() {
        return this.signInState;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public UserCodeDeliveryDetails getCodeDetails() {
        return this.codeDetails;
    }
}
