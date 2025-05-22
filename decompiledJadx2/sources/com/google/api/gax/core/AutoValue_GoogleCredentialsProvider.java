package com.google.api.gax.core;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import java.util.List;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class AutoValue_GoogleCredentialsProvider extends GoogleCredentialsProvider {
    private final GoogleCredentials OAuth2Credentials;
    private final List<String> jwtEnabledScopes;
    private final List<String> scopesToApply;

    private AutoValue_GoogleCredentialsProvider(List<String> list, List<String> list2, @Nullable GoogleCredentials googleCredentials) {
        this.scopesToApply = list;
        this.jwtEnabledScopes = list2;
        this.OAuth2Credentials = googleCredentials;
    }

    @Override // com.google.api.gax.core.GoogleCredentialsProvider
    public List<String> getScopesToApply() {
        return this.scopesToApply;
    }

    @Override // com.google.api.gax.core.GoogleCredentialsProvider
    @BetaApi
    public List<String> getJwtEnabledScopes() {
        return this.jwtEnabledScopes;
    }

    @Override // com.google.api.gax.core.GoogleCredentialsProvider
    @Nullable
    GoogleCredentials getOAuth2Credentials() {
        return this.OAuth2Credentials;
    }

    public String toString() {
        return "GoogleCredentialsProvider{scopesToApply=" + this.scopesToApply + ", jwtEnabledScopes=" + this.jwtEnabledScopes + ", OAuth2Credentials=" + this.OAuth2Credentials + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GoogleCredentialsProvider)) {
            return false;
        }
        GoogleCredentialsProvider googleCredentialsProvider = (GoogleCredentialsProvider) obj;
        if (this.scopesToApply.equals(googleCredentialsProvider.getScopesToApply()) && this.jwtEnabledScopes.equals(googleCredentialsProvider.getJwtEnabledScopes())) {
            GoogleCredentials googleCredentials = this.OAuth2Credentials;
            if (googleCredentials == null) {
                if (googleCredentialsProvider.getOAuth2Credentials() == null) {
                    return true;
                }
            } else if (googleCredentials.equals(googleCredentialsProvider.getOAuth2Credentials())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.scopesToApply.hashCode() ^ 1000003) * 1000003) ^ this.jwtEnabledScopes.hashCode()) * 1000003;
        GoogleCredentials googleCredentials = this.OAuth2Credentials;
        return hashCode ^ (googleCredentials == null ? 0 : googleCredentials.hashCode());
    }

    @Override // com.google.api.gax.core.GoogleCredentialsProvider
    public GoogleCredentialsProvider.Builder toBuilder() {
        return new Builder(this);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static final class Builder extends GoogleCredentialsProvider.Builder {
        private GoogleCredentials OAuth2Credentials;
        private List<String> jwtEnabledScopes;
        private List<String> scopesToApply;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(GoogleCredentialsProvider googleCredentialsProvider) {
            this.scopesToApply = googleCredentialsProvider.getScopesToApply();
            this.jwtEnabledScopes = googleCredentialsProvider.getJwtEnabledScopes();
            this.OAuth2Credentials = googleCredentialsProvider.getOAuth2Credentials();
        }

        @Override // com.google.api.gax.core.GoogleCredentialsProvider.Builder
        public GoogleCredentialsProvider.Builder setScopesToApply(List<String> list) {
            if (list == null) {
                throw new NullPointerException("Null scopesToApply");
            }
            this.scopesToApply = list;
            return this;
        }

        @Override // com.google.api.gax.core.GoogleCredentialsProvider.Builder
        public List<String> getScopesToApply() {
            List<String> list = this.scopesToApply;
            if (list != null) {
                return list;
            }
            throw new IllegalStateException("Property \"scopesToApply\" has not been set");
        }

        @Override // com.google.api.gax.core.GoogleCredentialsProvider.Builder
        public GoogleCredentialsProvider.Builder setJwtEnabledScopes(List<String> list) {
            if (list == null) {
                throw new NullPointerException("Null jwtEnabledScopes");
            }
            this.jwtEnabledScopes = list;
            return this;
        }

        @Override // com.google.api.gax.core.GoogleCredentialsProvider.Builder
        public List<String> getJwtEnabledScopes() {
            List<String> list = this.jwtEnabledScopes;
            if (list != null) {
                return list;
            }
            throw new IllegalStateException("Property \"jwtEnabledScopes\" has not been set");
        }

        @Override // com.google.api.gax.core.GoogleCredentialsProvider.Builder
        GoogleCredentialsProvider.Builder setOAuth2Credentials(@Nullable GoogleCredentials googleCredentials) {
            this.OAuth2Credentials = googleCredentials;
            return this;
        }

        @Override // com.google.api.gax.core.GoogleCredentialsProvider.Builder
        GoogleCredentialsProvider autoBuild() {
            String str = "";
            if (this.scopesToApply == null) {
                str = " scopesToApply";
            }
            if (this.jwtEnabledScopes == null) {
                str = str + " jwtEnabledScopes";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_GoogleCredentialsProvider(this.scopesToApply, this.jwtEnabledScopes, this.OAuth2Credentials);
        }
    }
}
