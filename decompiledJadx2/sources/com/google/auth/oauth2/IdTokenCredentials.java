package com.google.auth.oauth2;

import com.google.api.client.util.Preconditions;
import com.google.auth.oauth2.IdTokenProvider;
import com.google.auth.oauth2.OAuth2Credentials;
import com.google.common.base.MoreObjects;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class IdTokenCredentials extends OAuth2Credentials {
    private static final long serialVersionUID = -2133257318957588431L;
    private IdTokenProvider idTokenProvider;
    private List<IdTokenProvider.Option> options;
    private String targetAudience;

    private IdTokenCredentials(Builder builder) {
        this.idTokenProvider = (IdTokenProvider) Preconditions.checkNotNull(builder.getIdTokenProvider());
        this.targetAudience = (String) Preconditions.checkNotNull(builder.getTargetAudience());
        this.options = builder.getOptions();
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public AccessToken refreshAccessToken() throws IOException {
        return this.idTokenProvider.idTokenWithAudience(this.targetAudience, this.options);
    }

    public IdToken getIdToken() {
        return (IdToken) getAccessToken();
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public int hashCode() {
        return Objects.hash(this.options, this.targetAudience);
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public boolean equals(Object obj) {
        if (!(obj instanceof IdTokenCredentials)) {
            return false;
        }
        IdTokenCredentials idTokenCredentials = (IdTokenCredentials) obj;
        return Objects.equals(this.idTokenProvider, idTokenCredentials.idTokenProvider) && Objects.equals(this.targetAudience, idTokenCredentials.targetAudience);
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public Builder toBuilder() {
        return new Builder().setIdTokenProvider(this.idTokenProvider).setTargetAudience(this.targetAudience).setOptions(this.options);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder extends OAuth2Credentials.Builder {
        private IdTokenProvider idTokenProvider;
        private List<IdTokenProvider.Option> options;
        private String targetAudience;

        protected Builder() {
        }

        public Builder setIdTokenProvider(IdTokenProvider idTokenProvider) {
            this.idTokenProvider = idTokenProvider;
            return this;
        }

        public IdTokenProvider getIdTokenProvider() {
            return this.idTokenProvider;
        }

        public Builder setTargetAudience(String str) {
            this.targetAudience = str;
            return this;
        }

        public String getTargetAudience() {
            return this.targetAudience;
        }

        public Builder setOptions(List<IdTokenProvider.Option> list) {
            this.options = list;
            return this;
        }

        public List<IdTokenProvider.Option> getOptions() {
            return this.options;
        }

        @Override // com.google.auth.oauth2.OAuth2Credentials.Builder
        public IdTokenCredentials build() {
            return new IdTokenCredentials(this);
        }
    }
}
