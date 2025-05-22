package com.google.api.gax.core;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.AutoValue_GoogleCredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.auth.oauth2.ServiceAccountJwtAccessCredentials;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class GoogleCredentialsProvider implements CredentialsProvider {
    @BetaApi
    public abstract List<String> getJwtEnabledScopes();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract GoogleCredentials getOAuth2Credentials();

    public abstract List<String> getScopesToApply();

    public abstract Builder toBuilder();

    @Override // com.google.api.gax.core.CredentialsProvider
    public Credentials getCredentials() throws IOException {
        GoogleCredentials oAuth2Credentials = getOAuth2Credentials();
        if (oAuth2Credentials == null) {
            oAuth2Credentials = GoogleCredentials.getApplicationDefault();
        }
        boolean z = false;
        Iterator<String> it = getJwtEnabledScopes().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (getScopesToApply().contains(it.next())) {
                z = true;
                break;
            }
        }
        if (!(oAuth2Credentials instanceof ServiceAccountCredentials) || !z) {
            return oAuth2Credentials.createScopedRequired() ? oAuth2Credentials.createScoped(getScopesToApply()) : oAuth2Credentials;
        }
        ServiceAccountCredentials serviceAccountCredentials = (ServiceAccountCredentials) oAuth2Credentials;
        return ServiceAccountJwtAccessCredentials.newBuilder().setClientEmail(serviceAccountCredentials.getClientEmail()).setClientId(serviceAccountCredentials.getClientId()).setPrivateKey(serviceAccountCredentials.getPrivateKey()).setPrivateKeyId(serviceAccountCredentials.getPrivateKeyId()).setQuotaProjectId(serviceAccountCredentials.getQuotaProjectId()).build();
    }

    public static Builder newBuilder() {
        return new AutoValue_GoogleCredentialsProvider.Builder().setJwtEnabledScopes(ImmutableList.m634of());
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    @BetaApi
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        abstract GoogleCredentialsProvider autoBuild();

        @BetaApi
        public abstract List<String> getJwtEnabledScopes();

        public abstract List<String> getScopesToApply();

        @BetaApi
        public abstract Builder setJwtEnabledScopes(List<String> list);

        abstract Builder setOAuth2Credentials(GoogleCredentials googleCredentials);

        public abstract Builder setScopesToApply(List<String> list);

        public GoogleCredentialsProvider build() {
            setScopesToApply(ImmutableList.copyOf((Collection) getScopesToApply()));
            setJwtEnabledScopes(ImmutableList.copyOf((Collection) getJwtEnabledScopes()));
            return autoBuild();
        }
    }
}
