package com.google.auth.oauth2;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonObjectParser;
import com.google.auth.RequestMetadataCallback;
import com.google.auth.http.HttpTransportFactory;
import com.google.auth.oauth2.AwsCredentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.IdentityPoolCredentials;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class ExternalAccountCredentials extends GoogleCredentials implements QuotaProjectIdProvider {
    private static final String CLOUD_PLATFORM_SCOPE = "https://www.googleapis.com/auth/cloud-platform";
    static final String EXTERNAL_ACCOUNT_FILE_TYPE = "external_account";
    private final String audience;

    @Nullable
    private final String clientId;

    @Nullable
    private final String clientSecret;
    private final CredentialSource credentialSource;
    private EnvironmentProvider environmentProvider;

    @Nullable
    protected final ImpersonatedCredentials impersonatedCredentials;

    @Nullable
    private final String quotaProjectId;
    private final Collection<String> scopes;

    @Nullable
    private final String serviceAccountImpersonationUrl;
    private final String subjectTokenType;

    @Nullable
    private final String tokenInfoUrl;
    private final String tokenUrl;
    protected transient HttpTransportFactory transportFactory;
    private final String transportFactoryClassName;

    public abstract String retrieveSubjectToken() throws IOException;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static abstract class CredentialSource {
        /* JADX INFO: Access modifiers changed from: package-private */
        public CredentialSource(Map<String, Object> map) {
            Preconditions.checkNotNull(map);
        }
    }

    protected ExternalAccountCredentials(HttpTransportFactory httpTransportFactory, String str, String str2, String str3, CredentialSource credentialSource, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable Collection<String> collection) {
        this(httpTransportFactory, str, str2, str3, credentialSource, str4, str5, str6, str7, str8, collection, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ExternalAccountCredentials(HttpTransportFactory httpTransportFactory, String str, String str2, String str3, CredentialSource credentialSource, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable Collection<String> collection, @Nullable EnvironmentProvider environmentProvider) {
        this.transportFactory = (HttpTransportFactory) MoreObjects.firstNonNull(httpTransportFactory, getFromServiceLoader(HttpTransportFactory.class, OAuth2Utils.HTTP_TRANSPORT_FACTORY));
        this.transportFactoryClassName = (String) Preconditions.checkNotNull(this.transportFactory.getClass().getName());
        this.audience = (String) Preconditions.checkNotNull(str);
        this.subjectTokenType = (String) Preconditions.checkNotNull(str2);
        this.tokenUrl = (String) Preconditions.checkNotNull(str3);
        this.credentialSource = (CredentialSource) Preconditions.checkNotNull(credentialSource);
        this.tokenInfoUrl = str4;
        this.serviceAccountImpersonationUrl = str5;
        this.quotaProjectId = str6;
        this.clientId = str7;
        this.clientSecret = str8;
        this.scopes = (collection == null || collection.isEmpty()) ? Arrays.asList(CLOUD_PLATFORM_SCOPE) : collection;
        this.environmentProvider = environmentProvider == null ? SystemEnvironmentProvider.getInstance() : environmentProvider;
        this.impersonatedCredentials = initializeImpersonatedCredentials();
    }

    private ImpersonatedCredentials initializeImpersonatedCredentials() {
        ExternalAccountCredentials build;
        if (this.serviceAccountImpersonationUrl == null) {
            return null;
        }
        if (this instanceof AwsCredentials) {
            build = AwsCredentials.newBuilder((AwsCredentials) this).setServiceAccountImpersonationUrl(null).build();
        } else {
            build = IdentityPoolCredentials.newBuilder((IdentityPoolCredentials) this).setServiceAccountImpersonationUrl(null).build();
        }
        return ImpersonatedCredentials.newBuilder().setSourceCredentials(build).setHttpTransportFactory(this.transportFactory).setTargetPrincipal(extractTargetPrincipal(this.serviceAccountImpersonationUrl)).setScopes(new ArrayList(this.scopes)).setLifetime(3600).build();
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials, com.google.auth.Credentials
    public void getRequestMetadata(URI uri, Executor executor, final RequestMetadataCallback requestMetadataCallback) {
        super.getRequestMetadata(uri, executor, new RequestMetadataCallback() { // from class: com.google.auth.oauth2.ExternalAccountCredentials.1
            @Override // com.google.auth.RequestMetadataCallback
            public void onSuccess(Map<String, List<String>> map) {
                requestMetadataCallback.onSuccess(GoogleCredentials.addQuotaProjectIdToRequestMetadata(ExternalAccountCredentials.this.quotaProjectId, map));
            }

            @Override // com.google.auth.RequestMetadataCallback
            public void onFailure(Throwable th) {
                requestMetadataCallback.onFailure(th);
            }
        });
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials, com.google.auth.Credentials
    public Map<String, List<String>> getRequestMetadata(URI uri) throws IOException {
        return addQuotaProjectIdToRequestMetadata(this.quotaProjectId, super.getRequestMetadata(uri));
    }

    public static ExternalAccountCredentials fromStream(InputStream inputStream) throws IOException {
        return fromStream(inputStream, OAuth2Utils.HTTP_TRANSPORT_FACTORY);
    }

    public static ExternalAccountCredentials fromStream(InputStream inputStream, HttpTransportFactory httpTransportFactory) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(httpTransportFactory);
        try {
            return fromJson((GenericJson) new JsonObjectParser(OAuth2Utils.JSON_FACTORY).parseAndClose(inputStream, StandardCharsets.UTF_8, GenericJson.class), httpTransportFactory);
        } catch (ClassCastException e) {
            throw new CredentialFormatException("An invalid input stream was provided.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ExternalAccountCredentials fromJson(Map<String, Object> map, HttpTransportFactory httpTransportFactory) {
        Preconditions.checkNotNull(map);
        Preconditions.checkNotNull(httpTransportFactory);
        String str = (String) map.get("audience");
        String str2 = (String) map.get("subject_token_type");
        String str3 = (String) map.get("token_url");
        String str4 = (String) map.get("service_account_impersonation_url");
        Map map2 = (Map) map.get("credential_source");
        String str5 = (String) map.get("token_info_url");
        String str6 = (String) map.get("client_id");
        String str7 = (String) map.get("client_secret");
        String str8 = (String) map.get("quota_project_id");
        if (isAwsCredential(map2)) {
            return new AwsCredentials(httpTransportFactory, str, str2, str3, new AwsCredentials.AwsCredentialSource(map2), str5, str4, str8, str6, str7, null, null);
        }
        return new IdentityPoolCredentials(httpTransportFactory, str, str2, str3, new IdentityPoolCredentials.IdentityPoolCredentialSource(map2), str5, str4, str8, str6, str7, null, null);
    }

    private static boolean isAwsCredential(Map<String, Object> map) {
        return map.containsKey("environment_id") && ((String) map.get("environment_id")).startsWith("aws");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AccessToken exchangeExternalCredentialForAccessToken(StsTokenExchangeRequest stsTokenExchangeRequest) throws IOException {
        ImpersonatedCredentials impersonatedCredentials = this.impersonatedCredentials;
        if (impersonatedCredentials != null) {
            return impersonatedCredentials.refreshAccessToken();
        }
        return StsRequestHandler.newBuilder(this.tokenUrl, stsTokenExchangeRequest, this.transportFactory.create().createRequestFactory()).build().exchangeToken().getAccessToken();
    }

    private static String extractTargetPrincipal(String str) {
        int lastIndexOf = str.lastIndexOf(47);
        int indexOf = str.indexOf(":generateAccessToken");
        if (lastIndexOf != -1 && indexOf != -1 && lastIndexOf < indexOf) {
            return str.substring(lastIndexOf + 1, indexOf);
        }
        throw new IllegalArgumentException("Unable to determine target principal from service account impersonation URL.");
    }

    public String getAudience() {
        return this.audience;
    }

    public String getSubjectTokenType() {
        return this.subjectTokenType;
    }

    public String getTokenUrl() {
        return this.tokenUrl;
    }

    public String getTokenInfoUrl() {
        return this.tokenInfoUrl;
    }

    public CredentialSource getCredentialSource() {
        return this.credentialSource;
    }

    @Nullable
    public String getServiceAccountImpersonationUrl() {
        return this.serviceAccountImpersonationUrl;
    }

    @Override // com.google.auth.oauth2.QuotaProjectIdProvider
    @Nullable
    public String getQuotaProjectId() {
        return this.quotaProjectId;
    }

    @Nullable
    public String getClientId() {
        return this.clientId;
    }

    @Nullable
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Nullable
    public Collection<String> getScopes() {
        return this.scopes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EnvironmentProvider getEnvironmentProvider() {
        return this.environmentProvider;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static abstract class Builder extends GoogleCredentials.Builder {
        protected String audience;

        @Nullable
        protected String clientId;

        @Nullable
        protected String clientSecret;
        protected CredentialSource credentialSource;
        protected EnvironmentProvider environmentProvider;

        @Nullable
        protected String quotaProjectId;

        @Nullable
        protected Collection<String> scopes;

        @Nullable
        protected String serviceAccountImpersonationUrl;
        protected String subjectTokenType;
        protected String tokenInfoUrl;
        protected String tokenUrl;
        protected HttpTransportFactory transportFactory;

        @Override // com.google.auth.oauth2.GoogleCredentials.Builder, com.google.auth.oauth2.OAuth2Credentials.Builder
        public abstract ExternalAccountCredentials build();

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder(ExternalAccountCredentials externalAccountCredentials) {
            this.transportFactory = externalAccountCredentials.transportFactory;
            this.audience = externalAccountCredentials.audience;
            this.subjectTokenType = externalAccountCredentials.subjectTokenType;
            this.tokenUrl = externalAccountCredentials.tokenUrl;
            this.tokenInfoUrl = externalAccountCredentials.tokenInfoUrl;
            this.serviceAccountImpersonationUrl = externalAccountCredentials.serviceAccountImpersonationUrl;
            this.credentialSource = externalAccountCredentials.credentialSource;
            this.quotaProjectId = externalAccountCredentials.quotaProjectId;
            this.clientId = externalAccountCredentials.clientId;
            this.clientSecret = externalAccountCredentials.clientSecret;
            this.scopes = externalAccountCredentials.scopes;
            this.environmentProvider = externalAccountCredentials.environmentProvider;
        }

        public Builder setAudience(String str) {
            this.audience = str;
            return this;
        }

        public Builder setSubjectTokenType(String str) {
            this.subjectTokenType = str;
            return this;
        }

        public Builder setTokenUrl(String str) {
            this.tokenUrl = str;
            return this;
        }

        public Builder setTokenInfoUrl(String str) {
            this.tokenInfoUrl = str;
            return this;
        }

        public Builder setServiceAccountImpersonationUrl(String str) {
            this.serviceAccountImpersonationUrl = str;
            return this;
        }

        public Builder setCredentialSource(CredentialSource credentialSource) {
            this.credentialSource = credentialSource;
            return this;
        }

        public Builder setScopes(Collection<String> collection) {
            this.scopes = collection;
            return this;
        }

        public Builder setQuotaProjectId(String str) {
            this.quotaProjectId = str;
            return this;
        }

        public Builder setClientId(String str) {
            this.clientId = str;
            return this;
        }

        public Builder setClientSecret(String str) {
            this.clientSecret = str;
            return this;
        }

        public Builder setHttpTransportFactory(HttpTransportFactory httpTransportFactory) {
            this.transportFactory = httpTransportFactory;
            return this;
        }

        Builder setEnvironmentProvider(EnvironmentProvider environmentProvider) {
            this.environmentProvider = environmentProvider;
            return this;
        }
    }
}
