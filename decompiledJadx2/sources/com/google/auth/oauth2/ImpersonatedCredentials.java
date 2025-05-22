package com.google.auth.oauth2;

import com.amazonaws.mobile.client.internal.oauth2.OAuth2Constants;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.GenericData;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.http.HttpTransportFactory;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.IdTokenProvider;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class ImpersonatedCredentials extends GoogleCredentials implements ServiceAccountSigner, IdTokenProvider {
    private static final String CLOUD_PLATFORM_SCOPE = "https://www.googleapis.com/auth/cloud-platform";
    private static final int DEFAULT_LIFETIME_IN_SECONDS = 3600;
    private static final String IAM_ACCESS_TOKEN_ENDPOINT = "https://iamcredentials.googleapis.com/v1/projects/-/serviceAccounts/%s:generateAccessToken";
    private static final String RFC3339 = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final int TWELVE_HOURS_IN_SECONDS = 43200;
    private static final long serialVersionUID = -2133257318957488431L;
    private List<String> delegates;
    private int lifetime;
    private List<String> scopes;
    private GoogleCredentials sourceCredentials;
    private String targetPrincipal;
    private transient HttpTransportFactory transportFactory;
    private final String transportFactoryClassName;

    public static ImpersonatedCredentials create(GoogleCredentials googleCredentials, String str, List<String> list, List<String> list2, int i, HttpTransportFactory httpTransportFactory) {
        return newBuilder().setSourceCredentials(googleCredentials).setTargetPrincipal(str).setDelegates(list).setScopes(list2).setLifetime(i).setHttpTransportFactory(httpTransportFactory).build();
    }

    public static ImpersonatedCredentials create(GoogleCredentials googleCredentials, String str, List<String> list, List<String> list2, int i) {
        return newBuilder().setSourceCredentials(googleCredentials).setTargetPrincipal(str).setDelegates(list).setScopes(list2).setLifetime(i).build();
    }

    @Override // com.google.auth.ServiceAccountSigner
    public String getAccount() {
        return this.targetPrincipal;
    }

    int getLifetime() {
        return this.lifetime;
    }

    @Override // com.google.auth.ServiceAccountSigner
    public byte[] sign(byte[] bArr) {
        return IamUtils.sign(getAccount(), this.sourceCredentials, this.transportFactory.create(), bArr, ImmutableMap.m654of("delegates", this.delegates));
    }

    private ImpersonatedCredentials(Builder builder) {
        this.sourceCredentials = builder.getSourceCredentials();
        this.targetPrincipal = builder.getTargetPrincipal();
        this.delegates = builder.getDelegates();
        this.scopes = builder.getScopes();
        this.lifetime = builder.getLifetime();
        this.transportFactory = (HttpTransportFactory) MoreObjects.firstNonNull(builder.getHttpTransportFactory(), getFromServiceLoader(HttpTransportFactory.class, OAuth2Utils.HTTP_TRANSPORT_FACTORY));
        this.transportFactoryClassName = this.transportFactory.getClass().getName();
        if (this.delegates == null) {
            this.delegates = new ArrayList();
        }
        if (this.scopes == null) {
            throw new IllegalStateException("Scopes cannot be null");
        }
        if (this.lifetime > TWELVE_HOURS_IN_SECONDS) {
            throw new IllegalStateException("lifetime must be less than or equal to 43200");
        }
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public AccessToken refreshAccessToken() throws IOException {
        if (this.sourceCredentials.getAccessToken() == null) {
            this.sourceCredentials = this.sourceCredentials.createScoped(Arrays.asList(CLOUD_PLATFORM_SCOPE));
        }
        try {
            this.sourceCredentials.refreshIfExpired();
            HttpTransport create = this.transportFactory.create();
            JsonObjectParser jsonObjectParser = new JsonObjectParser(OAuth2Utils.JSON_FACTORY);
            HttpCredentialsAdapter httpCredentialsAdapter = new HttpCredentialsAdapter(this.sourceCredentials);
            HttpRequest buildPostRequest = create.createRequestFactory().buildPostRequest(new GenericUrl(String.format(IAM_ACCESS_TOKEN_ENDPOINT, this.targetPrincipal)), new JsonHttpContent(jsonObjectParser.getJsonFactory(), ImmutableMap.m656of("delegates", (String) this.delegates, "scope", (String) this.scopes, "lifetime", this.lifetime + "s")));
            httpCredentialsAdapter.initialize(buildPostRequest);
            buildPostRequest.setParser(jsonObjectParser);
            try {
                HttpResponse execute = buildPostRequest.execute();
                GenericData genericData = (GenericData) execute.parseAs(GenericData.class);
                execute.disconnect();
                try {
                    return new AccessToken(OAuth2Utils.validateString(genericData, "accessToken", "Expected to find an accessToken"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(OAuth2Utils.validateString(genericData, "expireTime", "Expected to find an expireTime")));
                } catch (ParseException e) {
                    throw new IOException("Error parsing expireTime: " + e.getMessage());
                }
            } catch (IOException e2) {
                throw new IOException("Error requesting access token", e2);
            }
        } catch (IOException e3) {
            throw new IOException("Unable to refresh sourceCredentials", e3);
        }
    }

    @Override // com.google.auth.oauth2.IdTokenProvider
    public IdToken idTokenWithAudience(String str, List<IdTokenProvider.Option> list) throws IOException {
        return IamUtils.getIdToken(getAccount(), this.sourceCredentials, this.transportFactory.create(), str, list != null && list.contains(IdTokenProvider.Option.INCLUDE_EMAIL), ImmutableMap.m654of("delegates", this.delegates));
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public int hashCode() {
        return Objects.hash(this.sourceCredentials, this.targetPrincipal, this.delegates, this.scopes, Integer.valueOf(this.lifetime));
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public String toString() {
        return MoreObjects.toStringHelper(this).add("sourceCredentials", this.sourceCredentials).add("targetPrincipal", this.targetPrincipal).add("delegates", this.delegates).add(OAuth2Constants.SCOPES, this.scopes).add("lifetime", this.lifetime).add("transportFactoryClassName", this.transportFactoryClassName).toString();
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public boolean equals(Object obj) {
        if (!(obj instanceof ImpersonatedCredentials)) {
            return false;
        }
        ImpersonatedCredentials impersonatedCredentials = (ImpersonatedCredentials) obj;
        return Objects.equals(this.sourceCredentials, impersonatedCredentials.sourceCredentials) && Objects.equals(this.targetPrincipal, impersonatedCredentials.targetPrincipal) && Objects.equals(this.delegates, impersonatedCredentials.delegates) && Objects.equals(this.scopes, impersonatedCredentials.scopes) && Objects.equals(Integer.valueOf(this.lifetime), Integer.valueOf(impersonatedCredentials.lifetime)) && Objects.equals(this.transportFactoryClassName, impersonatedCredentials.transportFactoryClassName);
    }

    @Override // com.google.auth.oauth2.GoogleCredentials, com.google.auth.oauth2.OAuth2Credentials
    public Builder toBuilder() {
        return new Builder(this.sourceCredentials, this.targetPrincipal);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder extends GoogleCredentials.Builder {
        private List<String> delegates;
        private int lifetime = 3600;
        private List<String> scopes;
        private GoogleCredentials sourceCredentials;
        private String targetPrincipal;
        private HttpTransportFactory transportFactory;

        protected Builder() {
        }

        protected Builder(GoogleCredentials googleCredentials, String str) {
            this.sourceCredentials = googleCredentials;
            this.targetPrincipal = str;
        }

        public Builder setSourceCredentials(GoogleCredentials googleCredentials) {
            this.sourceCredentials = googleCredentials;
            return this;
        }

        public GoogleCredentials getSourceCredentials() {
            return this.sourceCredentials;
        }

        public Builder setTargetPrincipal(String str) {
            this.targetPrincipal = str;
            return this;
        }

        public String getTargetPrincipal() {
            return this.targetPrincipal;
        }

        public Builder setDelegates(List<String> list) {
            this.delegates = list;
            return this;
        }

        public List<String> getDelegates() {
            return this.delegates;
        }

        public Builder setScopes(List<String> list) {
            this.scopes = list;
            return this;
        }

        public List<String> getScopes() {
            return this.scopes;
        }

        public Builder setLifetime(int i) {
            if (i == 0) {
                i = 3600;
            }
            this.lifetime = i;
            return this;
        }

        public int getLifetime() {
            return this.lifetime;
        }

        public Builder setHttpTransportFactory(HttpTransportFactory httpTransportFactory) {
            this.transportFactory = httpTransportFactory;
            return this;
        }

        public HttpTransportFactory getHttpTransportFactory() {
            return this.transportFactory;
        }

        @Override // com.google.auth.oauth2.GoogleCredentials.Builder, com.google.auth.oauth2.OAuth2Credentials.Builder
        public ImpersonatedCredentials build() {
            return new ImpersonatedCredentials(this);
        }
    }
}
