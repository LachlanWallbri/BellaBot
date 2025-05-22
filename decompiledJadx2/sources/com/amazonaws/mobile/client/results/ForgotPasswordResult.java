package com.amazonaws.mobile.client.results;

/* loaded from: classes.dex */
public class ForgotPasswordResult {
    private UserCodeDeliveryDetails parameters;
    private final ForgotPasswordState state;

    public ForgotPasswordResult(ForgotPasswordState forgotPasswordState) {
        this.state = forgotPasswordState;
    }

    public ForgotPasswordState getState() {
        return this.state;
    }

    public void setParameters(UserCodeDeliveryDetails userCodeDeliveryDetails) {
        this.parameters = userCodeDeliveryDetails;
    }

    public UserCodeDeliveryDetails getParameters() {
        return this.parameters;
    }
}
