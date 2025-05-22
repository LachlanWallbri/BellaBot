package com.google.auth.oauth2;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobile.client.internal.oauth2.OAuth2Constants;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.auth.http.HttpTransportFactory;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.base.MoreObjects;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class UserCredentials extends GoogleCredentials implements QuotaProjectIdProvider {
    private static final String GRANT_TYPE = "refresh_token";
    private static final String PARSE_ERROR_PREFIX = "Error parsing token refresh response. ";
    private static final long serialVersionUID = -4800758775038679176L;
    private final String clientId;
    private final String clientSecret;
    private final String quotaProjectId;
    private final String refreshToken;
    private final URI tokenServerUri;
    private transient HttpTransportFactory transportFactory;
    private final String transportFactoryClassName;

    private UserCredentials(String str, String str2, String str3, AccessToken accessToken, HttpTransportFactory httpTransportFactory, URI uri, String str4) {
        super(accessToken);
        this.clientId = (String) Preconditions.checkNotNull(str);
        this.clientSecret = (String) Preconditions.checkNotNull(str2);
        this.refreshToken = str3;
        this.transportFactory = (HttpTransportFactory) MoreObjects.firstNonNull(httpTransportFactory, getFromServiceLoader(HttpTransportFactory.class, OAuth2Utils.HTTP_TRANSPORT_FACTORY));
        this.tokenServerUri = uri == null ? OAuth2Utils.TOKEN_SERVER_URI : uri;
        this.transportFactoryClassName = this.transportFactory.getClass().getName();
        this.quotaProjectId = str4;
        Preconditions.checkState((accessToken == null && str3 == null) ? false : true, "Either accessToken or refreshToken must not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UserCredentials fromJson(Map<String, Object> map, HttpTransportFactory httpTransportFactory) throws IOException {
        String str = (String) map.get("client_id");
        String str2 = (String) map.get("client_secret");
        String str3 = (String) map.get(GRANT_TYPE);
        String str4 = (String) map.get("quota_project_id");
        if (str == null || str2 == null || str3 == null) {
            throw new IOException("Error reading user credential from JSON,  expecting 'client_id', 'client_secret' and 'refresh_token'.");
        }
        return newBuilder().setClientId(str).setClientSecret(str2).setRefreshToken(str3).setAccessToken((AccessToken) null).setHttpTransportFactory(httpTransportFactory).setTokenServerUri(null).setQuotaProjectId(str4).build();
    }

    public static UserCredentials fromStream(InputStream inputStream) throws IOException {
        return fromStream(inputStream, OAuth2Utils.HTTP_TRANSPORT_FACTORY);
    }

    public static UserCredentials fromStream(InputStream inputStream, HttpTransportFactory httpTransportFactory) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(httpTransportFactory);
        GenericJson genericJson = (GenericJson) new JsonObjectParser(OAuth2Utils.JSON_FACTORY).parseAndClose(inputStream, StandardCharsets.UTF_8, GenericJson.class);
        String str = (String) genericJson.get("type");
        if (str == null) {
            throw new IOException("Error reading credentials from stream, 'type' field not specified.");
        }
        if ("authorized_user".equals(str)) {
            return fromJson(genericJson, httpTransportFactory);
        }
        throw new IOException(String.format("Error reading credentials from stream, 'type' value '%s' not recognized. Expecting '%s'.", str, "authorized_user"));
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public AccessToken refreshAccessToken() throws IOException {
        if (this.refreshToken == null) {
            throw new IllegalStateException("UserCredentials instance cannot refresh because there is no refresh token.");
        }
        GenericData genericData = new GenericData();
        genericData.set("client_id", this.clientId);
        genericData.set("client_secret", this.clientSecret);
        genericData.set(GRANT_TYPE, this.refreshToken);
        genericData.set(OAuth2Constants.GRANT_TYPE, GRANT_TYPE);
        HttpRequest buildPostRequest = this.transportFactory.create().createRequestFactory().buildPostRequest(new GenericUrl(this.tokenServerUri), new UrlEncodedContent(genericData));
        buildPostRequest.setParser(new JsonObjectParser(OAuth2Utils.JSON_FACTORY));
        return new AccessToken(OAuth2Utils.validateString((GenericData) buildPostRequest.execute().parseAs(GenericData.class), "access_token", PARSE_ERROR_PREFIX), new Date(this.clock.currentTimeMillis() + (OAuth2Utils.validateInt32(r0, "expires_in", PARSE_ERROR_PREFIX) * 1000)));
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getClientSecret() {
        return this.clientSecret;
    }

    public final String getRefreshToken() {
        return this.refreshToken;
    }

    private InputStream getUserCredentialsStream() throws IOException {
        GenericJson genericJson = new GenericJson();
        genericJson.put("type", (Object) "authorized_user");
        String str = this.refreshToken;
        if (str != null) {
            genericJson.put(GRANT_TYPE, (Object) str);
        }
        URI uri = this.tokenServerUri;
        if (uri != null) {
            genericJson.put("token_server_uri", (Object) uri);
        }
        String str2 = this.clientId;
        if (str2 != null) {
            genericJson.put("client_id", (Object) str2);
        }
        String str3 = this.clientSecret;
        if (str3 != null) {
            genericJson.put("client_secret", (Object) str3);
        }
        if (this.quotaProjectId != null) {
            genericJson.put("quota_project", (Object) this.clientSecret);
        }
        genericJson.setFactory(OAuth2Utils.JSON_FACTORY);
        return new ByteArrayInputStream(genericJson.toPrettyString().getBytes(StandardCharsets.UTF_8));
    }

    public void save(String str) throws IOException {
        OAuth2Utils.writeInputStreamToFile(getUserCredentialsStream(), str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.auth.oauth2.OAuth2Credentials
    public Map<String, List<String>> getAdditionalHeaders() {
        Map<String, List<String>> additionalHeaders = super.getAdditionalHeaders();
        String str = this.quotaProjectId;
        return str != null ? addQuotaProjectIdToRequestMetadata(str, additionalHeaders) : additionalHeaders;
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.clientId, this.clientSecret, this.refreshToken, this.tokenServerUri, this.transportFactoryClassName, this.quotaProjectId);
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public String toString() {
        return MoreObjects.toStringHelper(this).add("requestMetadata", getRequestMetadataInternal()).add("temporaryAccess", getAccessToken()).add(TmpConstant.KEY_CLIENT_ID, this.clientId).add("refreshToken", this.refreshToken).add("tokenServerUri", this.tokenServerUri).add("transportFactoryClassName", this.transportFactoryClassName).add("quotaProjectId", this.quotaProjectId).toString();
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public boolean equals(Object obj) {
        if (!(obj instanceof UserCredentials)) {
            return false;
        }
        UserCredentials userCredentials = (UserCredentials) obj;
        return super.equals(userCredentials) && Objects.equals(this.clientId, userCredentials.clientId) && Objects.equals(this.clientSecret, userCredentials.clientSecret) && Objects.equals(this.refreshToken, userCredentials.refreshToken) && Objects.equals(this.tokenServerUri, userCredentials.tokenServerUri) && Objects.equals(this.transportFactoryClassName, userCredentials.transportFactoryClassName) && Objects.equals(this.quotaProjectId, userCredentials.quotaProjectId);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.transportFactory = (HttpTransportFactory) newInstance(this.transportFactoryClassName);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // com.google.auth.oauth2.GoogleCredentials, com.google.auth.oauth2.OAuth2Credentials
    public Builder toBuilder() {
        return new Builder(this);
    }

    @Override // com.google.auth.oauth2.QuotaProjectIdProvider
    public String getQuotaProjectId() {
        return this.quotaProjectId;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder extends GoogleCredentials.Builder {
        private String clientId;
        private String clientSecret;
        private String quotaProjectId;
        private String refreshToken;
        private URI tokenServerUri;
        private HttpTransportFactory transportFactory;

        protected Builder() {
        }

        protected Builder(UserCredentials userCredentials) {
            this.clientId = userCredentials.clientId;
            this.clientSecret = userCredentials.clientSecret;
            this.refreshToken = userCredentials.refreshToken;
            this.transportFactory = userCredentials.transportFactory;
            this.tokenServerUri = userCredentials.tokenServerUri;
            this.quotaProjectId = userCredentials.quotaProjectId;
        }

        public Builder setClientId(String str) {
            this.clientId = str;
            return this;
        }

        public Builder setClientSecret(String str) {
            this.clientSecret = str;
            return this;
        }

        public Builder setRefreshToken(String str) {
            this.refreshToken = str;
            return this;
        }

        public Builder setTokenServerUri(URI uri) {
            this.tokenServerUri = uri;
            return this;
        }

        public Builder setHttpTransportFactory(HttpTransportFactory httpTransportFactory) {
            this.transportFactory = httpTransportFactory;
            return this;
        }

        @Override // com.google.auth.oauth2.GoogleCredentials.Builder, com.google.auth.oauth2.OAuth2Credentials.Builder
        public Builder setAccessToken(AccessToken accessToken) {
            super.setAccessToken(accessToken);
            return this;
        }

        public Builder setQuotaProjectId(String str) {
            this.quotaProjectId = str;
            return this;
        }

        public String getClientId() {
            return this.clientId;
        }

        public String getClientSecret() {
            return this.clientSecret;
        }

        public String getRefreshToken() {
            return this.refreshToken;
        }

        public URI getTokenServerUri() {
            return this.tokenServerUri;
        }

        public HttpTransportFactory getHttpTransportFactory() {
            return this.transportFactory;
        }

        public String getQuotaProjectId() {
            return this.quotaProjectId;
        }

        @Override // com.google.auth.oauth2.GoogleCredentials.Builder, com.google.auth.oauth2.OAuth2Credentials.Builder
        public UserCredentials build() {
            return new UserCredentials(this.clientId, this.clientSecret, this.refreshToken, getAccessToken(), this.transportFactory, this.tokenServerUri, this.quotaProjectId);
        }
    }
}
