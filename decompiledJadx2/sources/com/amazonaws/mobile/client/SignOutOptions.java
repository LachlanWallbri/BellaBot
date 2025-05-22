package com.amazonaws.mobile.client;

/* loaded from: classes.dex */
public class SignOutOptions {
    private final Builder builder;

    SignOutOptions(Builder builder) {
        this.builder = builder;
    }

    public boolean isSignOutGlobally() {
        return this.builder.signOutGlobally;
    }

    public boolean isInvalidateTokens() {
        return this.builder.invalidateTokens;
    }

    public String getBrowserPackage() {
        return this.builder.browserPackage;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String browserPackage;
        private boolean invalidateTokens;
        private boolean signOutGlobally;

        public Builder signOutGlobally(boolean z) {
            this.signOutGlobally = z;
            return this;
        }

        public Builder browserPackage(String str) {
            this.browserPackage = str;
            return this;
        }

        public Builder invalidateTokens(boolean z) {
            this.invalidateTokens = z;
            return this;
        }

        public SignOutOptions build() {
            return new SignOutOptions(this);
        }
    }
}
