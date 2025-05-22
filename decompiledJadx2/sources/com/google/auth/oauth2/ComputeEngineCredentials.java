package com.google.auth.oauth2;

import com.amazonaws.mobile.client.internal.oauth2.OAuth2Constants;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.GenericData;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.http.HttpTransportFactory;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.IdTokenProvider;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class ComputeEngineCredentials extends GoogleCredentials implements ServiceAccountSigner, IdTokenProvider {
    static final int COMPUTE_PING_CONNECTION_TIMEOUT_MS = 500;
    static final String DEFAULT_METADATA_SERVER_URL = "http://metadata.google.internal";
    private static final String GOOGLE = "Google";
    private static final Logger LOGGER = Logger.getLogger(ComputeEngineCredentials.class.getName());
    static final int MAX_COMPUTE_PING_TRIES = 3;
    private static final String METADATA_FLAVOR = "Metadata-Flavor";
    private static final String PARSE_ERROR_ACCOUNT = "Error parsing service account response. ";
    private static final String PARSE_ERROR_PREFIX = "Error parsing token refresh response. ";
    static final String SIGN_BLOB_URL_FORMAT = "https://iamcredentials.googleapis.com/v1/projects/-/serviceAccounts/%s:signBlob";
    private static final long serialVersionUID = -4113476462526554235L;
    private final Collection<String> scopes;
    private transient String serviceAccountEmail;
    private transient HttpTransportFactory transportFactory;
    private final String transportFactoryClassName;

    private ComputeEngineCredentials(HttpTransportFactory httpTransportFactory, Collection<String> collection, Collection<String> collection2) {
        this.transportFactory = (HttpTransportFactory) MoreObjects.firstNonNull(httpTransportFactory, getFromServiceLoader(HttpTransportFactory.class, OAuth2Utils.HTTP_TRANSPORT_FACTORY));
        this.transportFactoryClassName = this.transportFactory.getClass().getName();
        collection = (collection == null || collection.isEmpty()) ? collection2 : collection;
        if (collection == null) {
            this.scopes = ImmutableSet.m676of();
            return;
        }
        ArrayList arrayList = new ArrayList(collection);
        arrayList.removeAll(Arrays.asList("", null));
        this.scopes = ImmutableSet.copyOf((Collection) arrayList);
    }

    @Override // com.google.auth.oauth2.GoogleCredentials
    public GoogleCredentials createScoped(Collection<String> collection) {
        return new ComputeEngineCredentials(this.transportFactory, collection, null);
    }

    @Override // com.google.auth.oauth2.GoogleCredentials
    public GoogleCredentials createScoped(Collection<String> collection, Collection<String> collection2) {
        return new ComputeEngineCredentials(this.transportFactory, collection, collection2);
    }

    public static ComputeEngineCredentials create() {
        return new ComputeEngineCredentials(null, null, null);
    }

    public final Collection<String> getScopes() {
        return this.scopes;
    }

    String createTokenUrlWithScopes() {
        GenericUrl genericUrl = new GenericUrl(getTokenServerEncodedUrl());
        if (!this.scopes.isEmpty()) {
            genericUrl.set(OAuth2Constants.SCOPES, (Object) Joiner.m608on(',').join(this.scopes));
        }
        return genericUrl.toString();
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public AccessToken refreshAccessToken() throws IOException {
        HttpResponse metadataResponse = getMetadataResponse(createTokenUrlWithScopes());
        int statusCode = metadataResponse.getStatusCode();
        if (statusCode == 404) {
            throw new IOException(String.format("Error code %s trying to get security access token from Compute Engine metadata for the default service account. This may be because the virtual machine instance does not have permission scopes specified. It is possible to skip checking for Compute Engine metadata by specifying the environment  variable NO_GCE_CHECK=true.", Integer.valueOf(statusCode)));
        }
        if (statusCode != 200) {
            throw new IOException(String.format("Unexpected Error code %s trying to get security access token from Compute Engine metadata for the default service account: %s", Integer.valueOf(statusCode), metadataResponse.parseAsString()));
        }
        if (metadataResponse.getContent() == null) {
            throw new IOException("Empty content from metadata token server request.");
        }
        return new AccessToken(OAuth2Utils.validateString((GenericData) metadataResponse.parseAs(GenericData.class), "access_token", PARSE_ERROR_PREFIX), new Date(this.clock.currentTimeMillis() + (OAuth2Utils.validateInt32(r0, "expires_in", PARSE_ERROR_PREFIX) * 1000)));
    }

    @Override // com.google.auth.oauth2.IdTokenProvider
    public IdToken idTokenWithAudience(String str, List<IdTokenProvider.Option> list) throws IOException {
        GenericUrl genericUrl = new GenericUrl(getIdentityDocumentUrl());
        if (list != null) {
            if (list.contains(IdTokenProvider.Option.FORMAT_FULL)) {
                genericUrl.set("format", (Object) "full");
            }
            if (list.contains(IdTokenProvider.Option.LICENSES_TRUE)) {
                genericUrl.set("format", (Object) "full");
                genericUrl.set("license", (Object) "TRUE");
            }
        }
        genericUrl.set("audience", (Object) str);
        HttpResponse metadataResponse = getMetadataResponse(genericUrl.toString());
        if (metadataResponse.getContent() == null) {
            throw new IOException("Empty content from metadata token server request.");
        }
        return IdToken.create(metadataResponse.parseAsString());
    }

    private HttpResponse getMetadataResponse(String str) throws IOException {
        HttpRequest buildGetRequest = this.transportFactory.create().createRequestFactory().buildGetRequest(new GenericUrl(str));
        buildGetRequest.setParser(new JsonObjectParser(OAuth2Utils.JSON_FACTORY));
        buildGetRequest.getHeaders().set(METADATA_FLAVOR, (Object) GOOGLE);
        buildGetRequest.setThrowExceptionOnExecuteError(false);
        try {
            return buildGetRequest.execute();
        } catch (UnknownHostException e) {
            throw new IOException("ComputeEngineCredentials cannot find the metadata server. This is likely because code is not running on Google Compute Engine.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean runningOnComputeEngine(HttpTransportFactory httpTransportFactory, DefaultCredentialsProvider defaultCredentialsProvider) {
        if (Boolean.parseBoolean(defaultCredentialsProvider.getEnv("NO_GCE_CHECK"))) {
            return false;
        }
        GenericUrl genericUrl = new GenericUrl(getMetadataServerUrl(defaultCredentialsProvider));
        for (int i = 1; i <= 3; i++) {
            try {
                HttpRequest buildGetRequest = httpTransportFactory.create().createRequestFactory().buildGetRequest(genericUrl);
                buildGetRequest.setConnectTimeout(500);
                buildGetRequest.getHeaders().set(METADATA_FLAVOR, GOOGLE);
                HttpResponse execute = buildGetRequest.execute();
                try {
                    return OAuth2Utils.headersContainValue(execute.getHeaders(), METADATA_FLAVOR, GOOGLE);
                } finally {
                    execute.disconnect();
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException e) {
                LOGGER.log(Level.FINE, "Encountered an unexpected exception when determining if we are running on Google Compute Engine.", (Throwable) e);
            }
        }
        LOGGER.log(Level.FINE, "Failed to detect whether we are running on Google Compute Engine.");
        return false;
    }

    public static String getMetadataServerUrl(DefaultCredentialsProvider defaultCredentialsProvider) {
        String env = defaultCredentialsProvider.getEnv("GCE_METADATA_HOST");
        if (env == null) {
            return DEFAULT_METADATA_SERVER_URL;
        }
        return "http://" + env;
    }

    public static String getMetadataServerUrl() {
        return getMetadataServerUrl(DefaultCredentialsProvider.DEFAULT);
    }

    public static String getTokenServerEncodedUrl(DefaultCredentialsProvider defaultCredentialsProvider) {
        return getMetadataServerUrl(defaultCredentialsProvider) + "/computeMetadata/v1/instance/service-accounts/default/token";
    }

    public static String getTokenServerEncodedUrl() {
        return getTokenServerEncodedUrl(DefaultCredentialsProvider.DEFAULT);
    }

    public static String getServiceAccountsUrl() {
        return getMetadataServerUrl(DefaultCredentialsProvider.DEFAULT) + "/computeMetadata/v1/instance/service-accounts/?recursive=true";
    }

    public static String getIdentityDocumentUrl() {
        return getMetadataServerUrl(DefaultCredentialsProvider.DEFAULT) + "/computeMetadata/v1/instance/service-accounts/default/identity";
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public int hashCode() {
        return Objects.hash(this.transportFactoryClassName);
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public String toString() {
        return MoreObjects.toStringHelper(this).add("transportFactoryClassName", this.transportFactoryClassName).toString();
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public boolean equals(Object obj) {
        if (!(obj instanceof ComputeEngineCredentials)) {
            return false;
        }
        ComputeEngineCredentials computeEngineCredentials = (ComputeEngineCredentials) obj;
        return Objects.equals(this.transportFactoryClassName, computeEngineCredentials.transportFactoryClassName) && Objects.equals(this.scopes, computeEngineCredentials.scopes);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.transportFactory = (HttpTransportFactory) newInstance(this.transportFactoryClassName);
    }

    @Override // com.google.auth.oauth2.GoogleCredentials, com.google.auth.oauth2.OAuth2Credentials
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // com.google.auth.ServiceAccountSigner
    public String getAccount() {
        if (this.serviceAccountEmail == null) {
            try {
                this.serviceAccountEmail = getDefaultServiceAccount();
            } catch (IOException e) {
                throw new RuntimeException("Failed to get service account", e);
            }
        }
        return this.serviceAccountEmail;
    }

    @Override // com.google.auth.ServiceAccountSigner
    public byte[] sign(byte[] bArr) {
        try {
            return IamUtils.sign(getAccount(), this, this.transportFactory.create(), bArr, Collections.emptyMap());
        } catch (ServiceAccountSigner.SigningException e) {
            throw e;
        } catch (RuntimeException e2) {
            throw new ServiceAccountSigner.SigningException("Signing failed", e2);
        }
    }

    private String getDefaultServiceAccount() throws IOException {
        HttpResponse metadataResponse = getMetadataResponse(getServiceAccountsUrl());
        int statusCode = metadataResponse.getStatusCode();
        if (statusCode == 404) {
            throw new IOException(String.format("Error code %s trying to get service accounts from Compute Engine metadata. This may be because the virtual machine instance does not have permission scopes specified.", Integer.valueOf(statusCode)));
        }
        if (statusCode != 200) {
            throw new IOException(String.format("Unexpected Error code %s trying to get service accounts from Compute Engine metadata: %s", Integer.valueOf(statusCode), metadataResponse.parseAsString()));
        }
        if (metadataResponse.getContent() == null) {
            throw new IOException("Empty content from metadata token server request.");
        }
        return OAuth2Utils.validateString(OAuth2Utils.validateMap((GenericData) metadataResponse.parseAs(GenericData.class), "default", PARSE_ERROR_ACCOUNT), "email", PARSE_ERROR_ACCOUNT);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder extends GoogleCredentials.Builder {
        private Collection<String> scopes;
        private HttpTransportFactory transportFactory;

        protected Builder() {
        }

        protected Builder(ComputeEngineCredentials computeEngineCredentials) {
            this.transportFactory = computeEngineCredentials.transportFactory;
            this.scopes = computeEngineCredentials.scopes;
        }

        public Builder setHttpTransportFactory(HttpTransportFactory httpTransportFactory) {
            this.transportFactory = httpTransportFactory;
            return this;
        }

        public Builder setScopes(Collection<String> collection) {
            this.scopes = collection;
            return this;
        }

        public HttpTransportFactory getHttpTransportFactory() {
            return this.transportFactory;
        }

        public Collection<String> getScopes() {
            return this.scopes;
        }

        @Override // com.google.auth.oauth2.GoogleCredentials.Builder, com.google.auth.oauth2.OAuth2Credentials.Builder
        public ComputeEngineCredentials build() {
            return new ComputeEngineCredentials(this.transportFactory, this.scopes, null);
        }
    }
}
