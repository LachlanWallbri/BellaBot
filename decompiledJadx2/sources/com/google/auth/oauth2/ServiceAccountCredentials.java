package com.google.auth.oauth2;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobile.client.internal.oauth2.OAuth2Constants;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpBackOffIOExceptionHandler;
import com.google.api.client.http.HttpBackOffUnsuccessfulResponseHandler;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.json.webtoken.JsonWebToken;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Joiner;
import com.google.api.client.util.PemReader;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.SecurityUtils;
import com.google.auth.RequestMetadataCallback;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.http.HttpTransportFactory;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.IdTokenProvider;
import com.google.auth.oauth2.ServiceAccountJwtAccessCredentials;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import io.grpc.alts.internal.AltsTsiHandshaker;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class ServiceAccountCredentials extends GoogleCredentials implements ServiceAccountSigner, IdTokenProvider, JwtProvider, QuotaProjectIdProvider {
    private static final int DEFAULT_LIFETIME_IN_SECONDS = 3600;
    private static final String GRANT_TYPE = "urn:ietf:params:oauth:grant-type:jwt-bearer";
    private static final String PARSE_ERROR_PREFIX = "Error parsing token refresh response. ";
    private static final int TWELVE_HOURS_IN_SECONDS = 43200;
    private static final long serialVersionUID = 7807543542681217978L;
    private final String clientEmail;
    private final String clientId;
    private final Collection<String> defaultScopes;
    private transient ServiceAccountJwtAccessCredentials jwtCredentials;
    private final int lifetime;
    private final PrivateKey privateKey;
    private final String privateKeyId;
    private final String projectId;
    private final String quotaProjectId;
    private final Collection<String> scopes;
    private final String serviceAccountUser;
    private final URI tokenServerUri;
    private transient HttpTransportFactory transportFactory;
    private final String transportFactoryClassName;

    ServiceAccountCredentials(String str, String str2, PrivateKey privateKey, String str3, Collection<String> collection, Collection<String> collection2, HttpTransportFactory httpTransportFactory, URI uri, String str4, String str5, String str6, int i) {
        this.jwtCredentials = null;
        this.clientId = str;
        this.clientEmail = (String) Preconditions.checkNotNull(str2);
        this.privateKey = (PrivateKey) Preconditions.checkNotNull(privateKey);
        this.privateKeyId = str3;
        this.scopes = collection == null ? ImmutableSet.m676of() : ImmutableSet.copyOf((Collection) collection);
        this.defaultScopes = collection2 == null ? ImmutableSet.m676of() : ImmutableSet.copyOf((Collection) collection2);
        this.transportFactory = (HttpTransportFactory) MoreObjects.firstNonNull(httpTransportFactory, getFromServiceLoader(HttpTransportFactory.class, OAuth2Utils.HTTP_TRANSPORT_FACTORY));
        this.transportFactoryClassName = this.transportFactory.getClass().getName();
        this.tokenServerUri = uri == null ? OAuth2Utils.TOKEN_SERVER_URI : uri;
        this.serviceAccountUser = str4;
        this.projectId = str5;
        this.quotaProjectId = str6;
        if (i > TWELVE_HOURS_IN_SECONDS) {
            throw new IllegalStateException("lifetime must be less than or equal to 43200");
        }
        this.lifetime = i;
        if (this.scopes.isEmpty()) {
            this.jwtCredentials = new ServiceAccountJwtAccessCredentials.Builder().setClientEmail(str2).setClientId(str).setPrivateKey(privateKey).setPrivateKeyId(str3).setQuotaProjectId(str6).build();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ServiceAccountCredentials fromJson(Map<String, Object> map, HttpTransportFactory httpTransportFactory) throws IOException {
        URI uri;
        String str = (String) map.get("client_id");
        String str2 = (String) map.get("client_email");
        String str3 = (String) map.get("private_key");
        String str4 = (String) map.get("private_key_id");
        String str5 = (String) map.get("project_id");
        String str6 = (String) map.get("token_uri");
        String str7 = (String) map.get("quota_project_id");
        if (str6 != null) {
            try {
                uri = new URI(str6);
            } catch (URISyntaxException unused) {
                throw new IOException("Token server URI specified in 'token_uri' could not be parsed.");
            }
        } else {
            uri = null;
        }
        URI uri2 = uri;
        if (str == null || str2 == null || str3 == null || str4 == null) {
            throw new IOException("Error reading service account credential from JSON, expecting  'client_id', 'client_email', 'private_key' and 'private_key_id'.");
        }
        return fromPkcs8(str, str2, str3, str4, null, null, httpTransportFactory, uri2, null, str5, str7);
    }

    public static ServiceAccountCredentials fromPkcs8(String str, String str2, String str3, String str4, Collection<String> collection) throws IOException {
        return fromPkcs8(str, str2, str3, str4, collection, null, null, null, null, null, null);
    }

    public static ServiceAccountCredentials fromPkcs8(String str, String str2, String str3, String str4, Collection<String> collection, Collection<String> collection2) throws IOException {
        return fromPkcs8(str, str2, str3, str4, collection, collection2, null, null, null, null, null);
    }

    public static ServiceAccountCredentials fromPkcs8(String str, String str2, String str3, String str4, Collection<String> collection, HttpTransportFactory httpTransportFactory, URI uri) throws IOException {
        return fromPkcs8(str, str2, str3, str4, collection, null, httpTransportFactory, uri, null, null, null);
    }

    public static ServiceAccountCredentials fromPkcs8(String str, String str2, String str3, String str4, Collection<String> collection, Collection<String> collection2, HttpTransportFactory httpTransportFactory, URI uri) throws IOException {
        return fromPkcs8(str, str2, str3, str4, collection, collection2, httpTransportFactory, uri, null, null, null);
    }

    public static ServiceAccountCredentials fromPkcs8(String str, String str2, String str3, String str4, Collection<String> collection, HttpTransportFactory httpTransportFactory, URI uri, String str5) throws IOException {
        return fromPkcs8(str, str2, str3, str4, collection, null, httpTransportFactory, uri, str5, null, null);
    }

    public static ServiceAccountCredentials fromPkcs8(String str, String str2, String str3, String str4, Collection<String> collection, Collection<String> collection2, HttpTransportFactory httpTransportFactory, URI uri, String str5) throws IOException {
        return fromPkcs8(str, str2, str3, str4, collection, collection2, httpTransportFactory, uri, str5, null, null);
    }

    static ServiceAccountCredentials fromPkcs8(String str, String str2, String str3, String str4, Collection<String> collection, Collection<String> collection2, HttpTransportFactory httpTransportFactory, URI uri, String str5, String str6, String str7) throws IOException {
        return new ServiceAccountCredentials(str, str2, privateKeyFromPkcs8(str3), str4, collection, collection2, httpTransportFactory, uri, str5, str6, str7, 3600);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PrivateKey privateKeyFromPkcs8(String str) throws IOException {
        PemReader.Section readFirstSectionAndClose = PemReader.readFirstSectionAndClose(new StringReader(str), "PRIVATE KEY");
        if (readFirstSectionAndClose == null) {
            throw new IOException("Invalid PKCS#8 data.");
        }
        try {
            return SecurityUtils.getRsaKeyFactory().generatePrivate(new PKCS8EncodedKeySpec(readFirstSectionAndClose.getBase64DecodedBytes()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IOException("Unexpected exception reading PKCS#8 data", e);
        }
    }

    public static ServiceAccountCredentials fromStream(InputStream inputStream) throws IOException {
        return fromStream(inputStream, OAuth2Utils.HTTP_TRANSPORT_FACTORY);
    }

    public static ServiceAccountCredentials fromStream(InputStream inputStream, HttpTransportFactory httpTransportFactory) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(httpTransportFactory);
        GenericJson genericJson = (GenericJson) new JsonObjectParser(OAuth2Utils.JSON_FACTORY).parseAndClose(inputStream, StandardCharsets.UTF_8, GenericJson.class);
        String str = (String) genericJson.get("type");
        if (str == null) {
            throw new IOException("Error reading credentials from stream, 'type' field not specified.");
        }
        if (AltsTsiHandshaker.TSI_SERVICE_ACCOUNT_PEER_PROPERTY.equals(str)) {
            return fromJson(genericJson, httpTransportFactory);
        }
        throw new IOException(String.format("Error reading credentials from stream, 'type' value '%s' not recognized. Expecting '%s'.", str, AltsTsiHandshaker.TSI_SERVICE_ACCOUNT_PEER_PROPERTY));
    }

    @Override // com.google.auth.oauth2.GoogleCredentials
    public boolean createScopedRequired() {
        return this.scopes.isEmpty() && this.defaultScopes.isEmpty();
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public AccessToken refreshAccessToken() throws IOException {
        JsonFactory jsonFactory = OAuth2Utils.JSON_FACTORY;
        String createAssertion = createAssertion(jsonFactory, this.clock.currentTimeMillis(), this.tokenServerUri.toString());
        GenericData genericData = new GenericData();
        genericData.set(OAuth2Constants.GRANT_TYPE, GRANT_TYPE);
        genericData.set("assertion", createAssertion);
        HttpRequest buildPostRequest = this.transportFactory.create().createRequestFactory().buildPostRequest(new GenericUrl(this.tokenServerUri), new UrlEncodedContent(genericData));
        buildPostRequest.setParser(new JsonObjectParser(jsonFactory));
        buildPostRequest.setIOExceptionHandler(new HttpBackOffIOExceptionHandler(new ExponentialBackOff()));
        buildPostRequest.setUnsuccessfulResponseHandler(new HttpBackOffUnsuccessfulResponseHandler(new ExponentialBackOff()).setBackOffRequired(new HttpBackOffUnsuccessfulResponseHandler.BackOffRequired() { // from class: com.google.auth.oauth2.ServiceAccountCredentials.1
            @Override // com.google.api.client.http.HttpBackOffUnsuccessfulResponseHandler.BackOffRequired
            public boolean isRequired(HttpResponse httpResponse) {
                int statusCode = httpResponse.getStatusCode();
                return statusCode / 100 == 5 || statusCode == 403;
            }
        }));
        try {
            return new AccessToken(OAuth2Utils.validateString((GenericData) buildPostRequest.execute().parseAs(GenericData.class), "access_token", PARSE_ERROR_PREFIX), new Date(this.clock.currentTimeMillis() + (OAuth2Utils.validateInt32(r0, "expires_in", PARSE_ERROR_PREFIX) * 1000)));
        } catch (IOException e) {
            throw new IOException(String.format("Error getting access token for service account: %s, iss: %s", e.getMessage(), getIssuer()), e);
        }
    }

    @Override // com.google.auth.oauth2.IdTokenProvider
    public IdToken idTokenWithAudience(String str, List<IdTokenProvider.Option> list) throws IOException {
        JsonFactory jsonFactory = OAuth2Utils.JSON_FACTORY;
        String createAssertionForIdToken = createAssertionForIdToken(jsonFactory, this.clock.currentTimeMillis(), this.tokenServerUri.toString(), str);
        GenericData genericData = new GenericData();
        genericData.set(OAuth2Constants.GRANT_TYPE, GRANT_TYPE);
        genericData.set("assertion", createAssertionForIdToken);
        HttpRequest buildPostRequest = this.transportFactory.create().createRequestFactory().buildPostRequest(new GenericUrl(this.tokenServerUri), new UrlEncodedContent(genericData));
        buildPostRequest.setParser(new JsonObjectParser(jsonFactory));
        try {
            return IdToken.create(OAuth2Utils.validateString((GenericData) buildPostRequest.execute().parseAs(GenericData.class), "id_token", PARSE_ERROR_PREFIX));
        } catch (IOException e) {
            throw new IOException(String.format("Error getting id token for service account: %s, iss: %s", e.getMessage(), getIssuer()), e);
        }
    }

    @Override // com.google.auth.oauth2.GoogleCredentials
    public GoogleCredentials createScoped(Collection<String> collection) {
        return createScoped(collection, null);
    }

    @Override // com.google.auth.oauth2.GoogleCredentials
    public GoogleCredentials createScoped(Collection<String> collection, Collection<String> collection2) {
        return new ServiceAccountCredentials(this.clientId, this.clientEmail, this.privateKey, this.privateKeyId, collection, collection2, this.transportFactory, this.tokenServerUri, this.serviceAccountUser, this.projectId, this.quotaProjectId, this.lifetime);
    }

    public ServiceAccountCredentials createWithCustomLifetime(int i) {
        return toBuilder().setLifetime(i).build();
    }

    @Override // com.google.auth.oauth2.GoogleCredentials
    public GoogleCredentials createDelegated(String str) {
        return new ServiceAccountCredentials(this.clientId, this.clientEmail, this.privateKey, this.privateKeyId, this.scopes, this.defaultScopes, this.transportFactory, this.tokenServerUri, str, this.projectId, this.quotaProjectId, this.lifetime);
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getClientEmail() {
        return this.clientEmail;
    }

    public final PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public final String getPrivateKeyId() {
        return this.privateKeyId;
    }

    public final Collection<String> getScopes() {
        return this.scopes;
    }

    public final Collection<String> getDefaultScopes() {
        return this.defaultScopes;
    }

    public final String getServiceAccountUser() {
        return this.serviceAccountUser;
    }

    public final String getProjectId() {
        return this.projectId;
    }

    public final URI getTokenServerUri() {
        return this.tokenServerUri;
    }

    private String getIssuer() {
        return this.clientEmail;
    }

    int getLifetime() {
        return this.lifetime;
    }

    @Override // com.google.auth.ServiceAccountSigner
    public String getAccount() {
        return getClientEmail();
    }

    @Override // com.google.auth.ServiceAccountSigner
    public byte[] sign(byte[] bArr) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(getPrivateKey());
            signature.update(bArr);
            return signature.sign();
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException e) {
            throw new ServiceAccountSigner.SigningException("Failed to sign the provided bytes", e);
        }
    }

    @Override // com.google.auth.oauth2.JwtProvider
    public JwtCredentials jwtWithClaims(JwtClaims jwtClaims) {
        return JwtCredentials.newBuilder().setPrivateKey(this.privateKey).setPrivateKeyId(this.privateKeyId).setJwtClaims(JwtClaims.newBuilder().setIssuer(getIssuer()).setSubject(this.clientEmail).build().merge(jwtClaims)).setClock(this.clock).build();
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
        return Objects.hash(this.clientId, this.clientEmail, this.privateKey, this.privateKeyId, this.transportFactoryClassName, this.tokenServerUri, this.scopes, this.defaultScopes, this.quotaProjectId, Integer.valueOf(this.lifetime));
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public String toString() {
        return MoreObjects.toStringHelper(this).add(TmpConstant.KEY_CLIENT_ID, this.clientId).add("clientEmail", this.clientEmail).add("privateKeyId", this.privateKeyId).add("transportFactoryClassName", this.transportFactoryClassName).add("tokenServerUri", this.tokenServerUri).add(OAuth2Constants.SCOPES, this.scopes).add("defaultScopes", this.defaultScopes).add("serviceAccountUser", this.serviceAccountUser).add("quotaProjectId", this.quotaProjectId).add("lifetime", this.lifetime).toString();
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public boolean equals(Object obj) {
        if (!(obj instanceof ServiceAccountCredentials)) {
            return false;
        }
        ServiceAccountCredentials serviceAccountCredentials = (ServiceAccountCredentials) obj;
        return Objects.equals(this.clientId, serviceAccountCredentials.clientId) && Objects.equals(this.clientEmail, serviceAccountCredentials.clientEmail) && Objects.equals(this.privateKey, serviceAccountCredentials.privateKey) && Objects.equals(this.privateKeyId, serviceAccountCredentials.privateKeyId) && Objects.equals(this.transportFactoryClassName, serviceAccountCredentials.transportFactoryClassName) && Objects.equals(this.tokenServerUri, serviceAccountCredentials.tokenServerUri) && Objects.equals(this.scopes, serviceAccountCredentials.scopes) && Objects.equals(this.defaultScopes, serviceAccountCredentials.defaultScopes) && Objects.equals(this.quotaProjectId, serviceAccountCredentials.quotaProjectId) && Objects.equals(Integer.valueOf(this.lifetime), Integer.valueOf(serviceAccountCredentials.lifetime));
    }

    String createAssertion(JsonFactory jsonFactory, long j, String str) throws IOException {
        JsonWebSignature.Header header = new JsonWebSignature.Header();
        header.setAlgorithm("RS256");
        header.setType("JWT");
        header.setKeyId(this.privateKeyId);
        JsonWebToken.Payload payload = new JsonWebToken.Payload();
        payload.setIssuer(getIssuer());
        long j2 = j / 1000;
        payload.setIssuedAtTimeSeconds(Long.valueOf(j2));
        payload.setExpirationTimeSeconds(Long.valueOf(j2 + this.lifetime));
        payload.setSubject(this.serviceAccountUser);
        if (this.scopes.isEmpty()) {
            payload.put("scope", (Object) Joiner.m576on(' ').join(this.defaultScopes));
        } else {
            payload.put("scope", (Object) Joiner.m576on(' ').join(this.scopes));
        }
        if (str == null) {
            payload.setAudience(OAuth2Utils.TOKEN_SERVER_URI.toString());
        } else {
            payload.setAudience(str);
        }
        try {
            return JsonWebSignature.signUsingRsaSha256(this.privateKey, jsonFactory, header, payload);
        } catch (GeneralSecurityException e) {
            throw new IOException("Error signing service account access token request with private key.", e);
        }
    }

    String createAssertionForIdToken(JsonFactory jsonFactory, long j, String str, String str2) throws IOException {
        JsonWebSignature.Header header = new JsonWebSignature.Header();
        header.setAlgorithm("RS256");
        header.setType("JWT");
        header.setKeyId(this.privateKeyId);
        JsonWebToken.Payload payload = new JsonWebToken.Payload();
        payload.setIssuer(getIssuer());
        long j2 = j / 1000;
        payload.setIssuedAtTimeSeconds(Long.valueOf(j2));
        payload.setExpirationTimeSeconds(Long.valueOf(j2 + this.lifetime));
        payload.setSubject(this.serviceAccountUser);
        if (str == null) {
            payload.setAudience(OAuth2Utils.TOKEN_SERVER_URI.toString());
        } else {
            payload.setAudience(str);
        }
        try {
            payload.set("target_audience", (Object) str2);
            return JsonWebSignature.signUsingRsaSha256(this.privateKey, jsonFactory, header, payload);
        } catch (GeneralSecurityException e) {
            throw new IOException("Error signing service account access token request with private key.", e);
        }
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials, com.google.auth.Credentials
    public void getRequestMetadata(URI uri, Executor executor, RequestMetadataCallback requestMetadataCallback) {
        ServiceAccountJwtAccessCredentials serviceAccountJwtAccessCredentials = this.jwtCredentials;
        if (serviceAccountJwtAccessCredentials != null && uri != null) {
            serviceAccountJwtAccessCredentials.getRequestMetadata(uri, executor, requestMetadataCallback);
        } else {
            super.getRequestMetadata(uri, executor, requestMetadataCallback);
        }
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials, com.google.auth.Credentials
    public Map<String, List<String>> getRequestMetadata(URI uri) throws IOException {
        if (this.scopes.isEmpty() && this.defaultScopes.isEmpty() && uri == null) {
            throw new IOException("Scopes and uri are not configured for service account. Either pass uri to getRequestMetadata to use self signed JWT, or specify the scopes by calling createScoped or passing scopes to constructor.");
        }
        ServiceAccountJwtAccessCredentials serviceAccountJwtAccessCredentials = this.jwtCredentials;
        if (serviceAccountJwtAccessCredentials != null && uri != null) {
            return serviceAccountJwtAccessCredentials.getRequestMetadata(uri);
        }
        return super.getRequestMetadata(uri);
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
        private String clientEmail;
        private String clientId;
        private Collection<String> defaultScopes;
        private int lifetime;
        private PrivateKey privateKey;
        private String privateKeyId;
        private String projectId;
        private String quotaProjectId;
        private Collection<String> scopes;
        private String serviceAccountUser;
        private URI tokenServerUri;
        private HttpTransportFactory transportFactory;

        protected Builder() {
            this.lifetime = 3600;
        }

        protected Builder(ServiceAccountCredentials serviceAccountCredentials) {
            this.lifetime = 3600;
            this.clientId = serviceAccountCredentials.clientId;
            this.clientEmail = serviceAccountCredentials.clientEmail;
            this.privateKey = serviceAccountCredentials.privateKey;
            this.privateKeyId = serviceAccountCredentials.privateKeyId;
            this.scopes = serviceAccountCredentials.scopes;
            this.defaultScopes = serviceAccountCredentials.defaultScopes;
            this.transportFactory = serviceAccountCredentials.transportFactory;
            this.tokenServerUri = serviceAccountCredentials.tokenServerUri;
            this.serviceAccountUser = serviceAccountCredentials.serviceAccountUser;
            this.projectId = serviceAccountCredentials.projectId;
            this.quotaProjectId = serviceAccountCredentials.quotaProjectId;
            this.lifetime = serviceAccountCredentials.lifetime;
        }

        public Builder setClientId(String str) {
            this.clientId = str;
            return this;
        }

        public Builder setClientEmail(String str) {
            this.clientEmail = str;
            return this;
        }

        public Builder setPrivateKey(PrivateKey privateKey) {
            this.privateKey = privateKey;
            return this;
        }

        public Builder setPrivateKeyId(String str) {
            this.privateKeyId = str;
            return this;
        }

        public Builder setScopes(Collection<String> collection) {
            this.scopes = collection;
            this.defaultScopes = ImmutableSet.m676of();
            return this;
        }

        public Builder setScopes(Collection<String> collection, Collection<String> collection2) {
            this.scopes = collection;
            this.defaultScopes = collection2;
            return this;
        }

        public Builder setServiceAccountUser(String str) {
            this.serviceAccountUser = str;
            return this;
        }

        public Builder setProjectId(String str) {
            this.projectId = str;
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

        public Builder setQuotaProjectId(String str) {
            this.quotaProjectId = str;
            return this;
        }

        public Builder setLifetime(int i) {
            if (i == 0) {
                i = 3600;
            }
            this.lifetime = i;
            return this;
        }

        public String getClientId() {
            return this.clientId;
        }

        public String getClientEmail() {
            return this.clientEmail;
        }

        public PrivateKey getPrivateKey() {
            return this.privateKey;
        }

        public String getPrivateKeyId() {
            return this.privateKeyId;
        }

        public Collection<String> getScopes() {
            return this.scopes;
        }

        public Collection<String> getDefaultScopes() {
            return this.defaultScopes;
        }

        public String getServiceAccountUser() {
            return this.serviceAccountUser;
        }

        public String getProjectId() {
            return this.projectId;
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

        public int getLifetime() {
            return this.lifetime;
        }

        @Override // com.google.auth.oauth2.GoogleCredentials.Builder, com.google.auth.oauth2.OAuth2Credentials.Builder
        public ServiceAccountCredentials build() {
            return new ServiceAccountCredentials(this.clientId, this.clientEmail, this.privateKey, this.privateKeyId, this.scopes, this.defaultScopes, this.transportFactory, this.tokenServerUri, this.serviceAccountUser, this.projectId, this.quotaProjectId, this.lifetime);
        }
    }
}
