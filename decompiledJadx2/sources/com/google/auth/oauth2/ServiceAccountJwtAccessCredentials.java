package com.google.auth.oauth2;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Preconditions;
import com.google.auth.Credentials;
import com.google.auth.RequestMetadataCallback;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.JwtClaims;
import com.google.common.base.MoreObjects;
import com.google.common.base.Throwables;
import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.UncheckedExecutionException;
import io.grpc.alts.internal.AltsTsiHandshaker;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class ServiceAccountJwtAccessCredentials extends Credentials implements JwtProvider, ServiceAccountSigner, QuotaProjectIdProvider {
    static final String JWT_ACCESS_PREFIX = "Bearer ";
    private static final long serialVersionUID = -7274955171379494197L;
    private final String clientEmail;
    private final String clientId;
    transient Clock clock;
    private transient LoadingCache<JwtClaims, JwtCredentials> credentialsCache;
    private final URI defaultAudience;
    private final PrivateKey privateKey;
    private final String privateKeyId;
    private final String quotaProjectId;
    static final long LIFE_SPAN_SECS = TimeUnit.HOURS.toSeconds(1);
    private static final long CLOCK_SKEW = TimeUnit.MINUTES.toSeconds(5);

    @Override // com.google.auth.Credentials
    public String getAuthenticationType() {
        return "JWTAccess";
    }

    @Override // com.google.auth.Credentials
    public boolean hasRequestMetadata() {
        return true;
    }

    @Override // com.google.auth.Credentials
    public boolean hasRequestMetadataOnly() {
        return true;
    }

    private ServiceAccountJwtAccessCredentials(String str, String str2, PrivateKey privateKey, String str3) {
        this(str, str2, privateKey, str3, null, null);
    }

    private ServiceAccountJwtAccessCredentials(String str, String str2, PrivateKey privateKey, String str3, URI uri, String str4) {
        this.clock = Clock.SYSTEM;
        this.clientId = str;
        this.clientEmail = (String) Preconditions.checkNotNull(str2);
        this.privateKey = (PrivateKey) Preconditions.checkNotNull(privateKey);
        this.privateKeyId = str3;
        this.defaultAudience = uri;
        this.credentialsCache = createCache();
        this.quotaProjectId = str4;
    }

    static ServiceAccountJwtAccessCredentials fromJson(Map<String, Object> map) throws IOException {
        return fromJson(map, null);
    }

    static ServiceAccountJwtAccessCredentials fromJson(Map<String, Object> map, URI uri) throws IOException {
        String str = (String) map.get("client_id");
        String str2 = (String) map.get("client_email");
        String str3 = (String) map.get("private_key");
        String str4 = (String) map.get("private_key_id");
        String str5 = (String) map.get("quota_project_id");
        if (str == null || str2 == null || str3 == null || str4 == null) {
            throw new IOException("Error reading service account credential from JSON, expecting  'client_id', 'client_email', 'private_key' and 'private_key_id'.");
        }
        return fromPkcs8(str, str2, str3, str4, uri, str5);
    }

    public static ServiceAccountJwtAccessCredentials fromPkcs8(String str, String str2, String str3, String str4) throws IOException {
        return fromPkcs8(str, str2, str3, str4, null);
    }

    public static ServiceAccountJwtAccessCredentials fromPkcs8(String str, String str2, String str3, String str4, URI uri) throws IOException {
        return fromPkcs8(str, str2, str3, str4, uri, null);
    }

    static ServiceAccountJwtAccessCredentials fromPkcs8(String str, String str2, String str3, String str4, URI uri, String str5) throws IOException {
        return new ServiceAccountJwtAccessCredentials(str, str2, ServiceAccountCredentials.privateKeyFromPkcs8(str3), str4, uri, str5);
    }

    public static ServiceAccountJwtAccessCredentials fromStream(InputStream inputStream) throws IOException {
        return fromStream(inputStream, null);
    }

    public static ServiceAccountJwtAccessCredentials fromStream(InputStream inputStream, URI uri) throws IOException {
        Preconditions.checkNotNull(inputStream);
        GenericJson genericJson = (GenericJson) new JsonObjectParser(OAuth2Utils.JSON_FACTORY).parseAndClose(inputStream, StandardCharsets.UTF_8, GenericJson.class);
        String str = (String) genericJson.get("type");
        if (str == null) {
            throw new IOException("Error reading credentials from stream, 'type' field not specified.");
        }
        if (AltsTsiHandshaker.TSI_SERVICE_ACCOUNT_PEER_PROPERTY.equals(str)) {
            return fromJson(genericJson, uri);
        }
        throw new IOException(String.format("Error reading credentials from stream, 'type' value '%s' not recognized. Expecting '%s'.", str, AltsTsiHandshaker.TSI_SERVICE_ACCOUNT_PEER_PROPERTY));
    }

    private LoadingCache<JwtClaims, JwtCredentials> createCache() {
        return CacheBuilder.newBuilder().maximumSize(100L).expireAfterWrite(LIFE_SPAN_SECS - CLOCK_SKEW, TimeUnit.SECONDS).ticker(new Ticker() { // from class: com.google.auth.oauth2.ServiceAccountJwtAccessCredentials.2
            @Override // com.google.common.base.Ticker
            public long read() {
                return TimeUnit.MILLISECONDS.toNanos(ServiceAccountJwtAccessCredentials.this.clock.currentTimeMillis());
            }
        }).build(new CacheLoader<JwtClaims, JwtCredentials>() { // from class: com.google.auth.oauth2.ServiceAccountJwtAccessCredentials.1
            @Override // com.google.common.cache.CacheLoader
            public JwtCredentials load(JwtClaims jwtClaims) throws Exception {
                return JwtCredentials.newBuilder().setPrivateKey(ServiceAccountJwtAccessCredentials.this.privateKey).setPrivateKeyId(ServiceAccountJwtAccessCredentials.this.privateKeyId).setJwtClaims(jwtClaims).setLifeSpanSeconds(Long.valueOf(ServiceAccountJwtAccessCredentials.LIFE_SPAN_SECS)).setClock(ServiceAccountJwtAccessCredentials.this.clock).build();
            }
        });
    }

    @Override // com.google.auth.oauth2.JwtProvider
    public JwtCredentials jwtWithClaims(JwtClaims jwtClaims) {
        JwtClaims.Builder subject = JwtClaims.newBuilder().setIssuer(this.clientEmail).setSubject(this.clientEmail);
        URI uri = this.defaultAudience;
        if (uri != null) {
            subject.setAudience(uri.toString());
        }
        return JwtCredentials.newBuilder().setPrivateKey(this.privateKey).setPrivateKeyId(this.privateKeyId).setJwtClaims(subject.build().merge(jwtClaims)).setLifeSpanSeconds(Long.valueOf(LIFE_SPAN_SECS)).setClock(this.clock).build();
    }

    static URI getUriForSelfSignedJWT(URI uri) {
        if (uri != null && uri.getScheme() != null && uri.getHost() != null) {
            try {
                return new URI(uri.getScheme(), uri.getHost(), "/", null);
            } catch (URISyntaxException unused) {
            }
        }
        return uri;
    }

    @Override // com.google.auth.Credentials
    public void getRequestMetadata(URI uri, Executor executor, RequestMetadataCallback requestMetadataCallback) {
        blockingGetToCallback(getUriForSelfSignedJWT(uri), requestMetadataCallback);
    }

    @Override // com.google.auth.Credentials
    public Map<String, List<String>> getRequestMetadata(URI uri) throws IOException {
        URI uriForSelfSignedJWT = getUriForSelfSignedJWT(uri);
        if (uriForSelfSignedJWT == null && (uriForSelfSignedJWT = this.defaultAudience) == null) {
            throw new IOException("JwtAccess requires Audience uri to be passed in or the defaultAudience to be specified");
        }
        try {
            return GoogleCredentials.addQuotaProjectIdToRequestMetadata(this.quotaProjectId, this.credentialsCache.get(JwtClaims.newBuilder().setAudience(uriForSelfSignedJWT.toString()).setIssuer(this.clientEmail).setSubject(this.clientEmail).build()).getRequestMetadata(uriForSelfSignedJWT));
        } catch (UncheckedExecutionException e) {
            Throwables.throwIfUnchecked(e);
            throw new IllegalStateException("generateJwtAccess threw an unchecked exception that couldn't be rethrown", e);
        } catch (ExecutionException e2) {
            Throwables.propagateIfPossible(e2.getCause(), IOException.class);
            throw new IllegalStateException("generateJwtAccess threw an unexpected checked exception", e2.getCause());
        }
    }

    @Override // com.google.auth.Credentials
    public void refresh() {
        this.credentialsCache.invalidateAll();
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

    public int hashCode() {
        return Objects.hash(this.clientId, this.clientEmail, this.privateKey, this.privateKeyId, this.defaultAudience, this.quotaProjectId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(TmpConstant.KEY_CLIENT_ID, this.clientId).add("clientEmail", this.clientEmail).add("privateKeyId", this.privateKeyId).add("defaultAudience", this.defaultAudience).add("quotaProjectId", this.quotaProjectId).toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ServiceAccountJwtAccessCredentials)) {
            return false;
        }
        ServiceAccountJwtAccessCredentials serviceAccountJwtAccessCredentials = (ServiceAccountJwtAccessCredentials) obj;
        return Objects.equals(this.clientId, serviceAccountJwtAccessCredentials.clientId) && Objects.equals(this.clientEmail, serviceAccountJwtAccessCredentials.clientEmail) && Objects.equals(this.privateKey, serviceAccountJwtAccessCredentials.privateKey) && Objects.equals(this.privateKeyId, serviceAccountJwtAccessCredentials.privateKeyId) && Objects.equals(this.defaultAudience, serviceAccountJwtAccessCredentials.defaultAudience) && Objects.equals(this.quotaProjectId, serviceAccountJwtAccessCredentials.quotaProjectId);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.clock = Clock.SYSTEM;
        this.credentialsCache = createCache();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

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
    public static class Builder {
        private String clientEmail;
        private String clientId;
        private URI defaultAudience;
        private PrivateKey privateKey;
        private String privateKeyId;
        private String quotaProjectId;

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder() {
        }

        protected Builder(ServiceAccountJwtAccessCredentials serviceAccountJwtAccessCredentials) {
            this.clientId = serviceAccountJwtAccessCredentials.clientId;
            this.clientEmail = serviceAccountJwtAccessCredentials.clientEmail;
            this.privateKey = serviceAccountJwtAccessCredentials.privateKey;
            this.privateKeyId = serviceAccountJwtAccessCredentials.privateKeyId;
            this.defaultAudience = serviceAccountJwtAccessCredentials.defaultAudience;
            this.quotaProjectId = serviceAccountJwtAccessCredentials.quotaProjectId;
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

        public Builder setDefaultAudience(URI uri) {
            this.defaultAudience = uri;
            return this;
        }

        public Builder setQuotaProjectId(String str) {
            this.quotaProjectId = str;
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

        public URI getDefaultAudience() {
            return this.defaultAudience;
        }

        public String getQuotaProjectId() {
            return this.quotaProjectId;
        }

        public ServiceAccountJwtAccessCredentials build() {
            return new ServiceAccountJwtAccessCredentials(this.clientId, this.clientEmail, this.privateKey, this.privateKeyId, this.defaultAudience, this.quotaProjectId);
        }
    }
}
