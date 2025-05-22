package com.amazonaws.mobile.client;

/* loaded from: classes.dex */
public class FederatedSignInOptions {
    private final Builder builder;

    FederatedSignInOptions(Builder builder) {
        this.builder = builder;
    }

    public String getCustomRoleARN() {
        return this.builder.customRoleARN;
    }

    public String getCognitoIdentityId() {
        return this.builder.cognitoIdentityId;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String cognitoIdentityId;
        private String customRoleARN;

        public Builder cognitoIdentityId(String str) {
            this.cognitoIdentityId = str;
            return this;
        }

        public Builder customRoleARN(String str) {
            this.customRoleARN = str;
            return this;
        }

        public FederatedSignInOptions build() {
            return new FederatedSignInOptions(this);
        }
    }
}
