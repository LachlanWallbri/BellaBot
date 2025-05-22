package com.amazonaws.mobile.client;

import java.util.Map;

/* loaded from: classes.dex */
public class HostedUIOptions {
    private final Builder builder;

    HostedUIOptions(Builder builder) {
        this.builder = builder;
    }

    public String[] getScopes() {
        return this.builder.scopes;
    }

    public String getIdentityProvider() {
        return this.builder.identityProvider;
    }

    public String getIdpIdentifier() {
        return this.builder.idpIdentifier;
    }

    public Boolean getFederationEnabled() {
        return this.builder.disableFederation;
    }

    public String getFederationProviderName() {
        return this.builder.federationProviderName;
    }

    public Map<String, String> getSignInQueryParameters() {
        return this.builder.signInQueryParameters;
    }

    public Map<String, String> getSignOutQueryParameters() {
        return this.builder.signOutQueryParameters;
    }

    public Map<String, String> getTokenQueryParameters() {
        return this.builder.tokenQueryParameters;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private Boolean disableFederation;
        private String federationProviderName;
        private String identityProvider;
        private String idpIdentifier;
        private String[] scopes;
        private Map<String, String> signInQueryParameters;
        private Map<String, String> signOutQueryParameters;
        private Map<String, String> tokenQueryParameters;

        public Builder signInQueryParameters(Map<String, String> map) {
            this.signInQueryParameters = map;
            return this;
        }

        public Builder signOutQueryParameters(Map<String, String> map) {
            this.signOutQueryParameters = map;
            return this;
        }

        public Builder tokenQueryParameters(Map<String, String> map) {
            this.tokenQueryParameters = map;
            return this;
        }

        public Builder scopes(String... strArr) {
            this.scopes = strArr;
            return this;
        }

        public Builder identityProvider(String str) {
            this.identityProvider = str;
            return this;
        }

        public Builder idpIdentifier(String str) {
            this.idpIdentifier = str;
            return this;
        }

        public Builder disableFederation(boolean z) {
            this.disableFederation = Boolean.valueOf(z);
            return this;
        }

        public Builder federationProviderName(String str) {
            this.federationProviderName = str;
            return this;
        }

        public HostedUIOptions build() {
            return new HostedUIOptions(this);
        }
    }
}
