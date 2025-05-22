package com.google.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonObjectParser;
import com.google.auth.http.HttpTransportFactory;
import com.google.auth.oauth2.ExternalAccountCredentials;
import com.google.auth.oauth2.StsTokenExchangeRequest;
import com.google.common.io.CharStreams;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class IdentityPoolCredentials extends ExternalAccountCredentials {
    private final IdentityPoolCredentialSource identityPoolCredentialSource;

    @Override // com.google.auth.oauth2.GoogleCredentials
    public /* bridge */ /* synthetic */ GoogleCredentials createScoped(Collection collection) {
        return createScoped((Collection<String>) collection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class IdentityPoolCredentialSource extends ExternalAccountCredentials.CredentialSource {
        private CredentialFormatType credentialFormatType;
        private String credentialLocation;
        private IdentityPoolCredentialSourceType credentialSourceType;

        @Nullable
        private Map<String, String> headers;

        @Nullable
        private String subjectTokenFieldName;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes2.dex */
        public enum CredentialFormatType {
            TEXT,
            JSON
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes2.dex */
        public enum IdentityPoolCredentialSourceType {
            FILE,
            URL
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public IdentityPoolCredentialSource(Map<String, Object> map) {
            super(map);
            if (map.containsKey("file") && map.containsKey("url")) {
                throw new IllegalArgumentException("Only one credential source type can be set, either file or url.");
            }
            if (map.containsKey("file")) {
                this.credentialLocation = (String) map.get("file");
                this.credentialSourceType = IdentityPoolCredentialSourceType.FILE;
            } else if (map.containsKey("url")) {
                this.credentialLocation = (String) map.get("url");
                this.credentialSourceType = IdentityPoolCredentialSourceType.URL;
            } else {
                throw new IllegalArgumentException("Missing credential source file location or URL. At least one must be specified.");
            }
            Map<? extends String, ? extends String> map2 = (Map) map.get("headers");
            if (map2 != null && !map2.isEmpty()) {
                this.headers = new HashMap();
                this.headers.putAll(map2);
            }
            this.credentialFormatType = CredentialFormatType.TEXT;
            Map map3 = (Map) map.get("format");
            if (map3 == null || !map3.containsKey("type")) {
                return;
            }
            String str = (String) map3.get("type");
            if (!"text".equals(str) && !"json".equals(str)) {
                throw new IllegalArgumentException(String.format("Invalid credential source format type: %s.", str));
            }
            this.credentialFormatType = str.equals("text") ? CredentialFormatType.TEXT : CredentialFormatType.JSON;
            if (!map3.containsKey("subject_token_field_name")) {
                throw new IllegalArgumentException("When specifying a JSON credential type, the subject_token_field_name must be set.");
            }
            this.subjectTokenFieldName = (String) map3.get("subject_token_field_name");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasHeaders() {
            Map<String, String> map = this.headers;
            return (map == null || map.isEmpty()) ? false : true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IdentityPoolCredentials(HttpTransportFactory httpTransportFactory, String str, String str2, String str3, IdentityPoolCredentialSource identityPoolCredentialSource, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable Collection<String> collection, @Nullable EnvironmentProvider environmentProvider) {
        super(httpTransportFactory, str, str2, str3, identityPoolCredentialSource, str4, str5, str6, str7, str8, collection, environmentProvider);
        this.identityPoolCredentialSource = identityPoolCredentialSource;
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public AccessToken refreshAccessToken() throws IOException {
        StsTokenExchangeRequest.Builder audience = StsTokenExchangeRequest.newBuilder(retrieveSubjectToken(), getSubjectTokenType()).setAudience(getAudience());
        Collection<String> scopes = getScopes();
        if (scopes != null && !scopes.isEmpty()) {
            audience.setScopes(new ArrayList(scopes));
        }
        return exchangeExternalCredentialForAccessToken(audience.build());
    }

    @Override // com.google.auth.oauth2.ExternalAccountCredentials
    public String retrieveSubjectToken() throws IOException {
        if (this.identityPoolCredentialSource.credentialSourceType == IdentityPoolCredentialSource.IdentityPoolCredentialSourceType.FILE) {
            return retrieveSubjectTokenFromCredentialFile();
        }
        return getSubjectTokenFromMetadataServer();
    }

    private String retrieveSubjectTokenFromCredentialFile() throws IOException {
        String str = this.identityPoolCredentialSource.credentialLocation;
        if (!Files.exists(Paths.get(str, new String[0]), LinkOption.NOFOLLOW_LINKS)) {
            throw new IOException(String.format("Invalid credential location. The file at %s does not exist.", str));
        }
        try {
            return parseToken(new FileInputStream(new File(str)));
        } catch (IOException e) {
            throw new IOException("Error when attempting to read the subject token from the credential file.", e);
        }
    }

    private String parseToken(InputStream inputStream) throws IOException {
        if (this.identityPoolCredentialSource.credentialFormatType == IdentityPoolCredentialSource.CredentialFormatType.TEXT) {
            return CharStreams.toString(new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)));
        }
        GenericJson genericJson = (GenericJson) new JsonObjectParser(OAuth2Utils.JSON_FACTORY).parseAndClose(inputStream, StandardCharsets.UTF_8, GenericJson.class);
        if (genericJson.containsKey(this.identityPoolCredentialSource.subjectTokenFieldName)) {
            return (String) genericJson.get(this.identityPoolCredentialSource.subjectTokenFieldName);
        }
        throw new IOException("Invalid subject token field name. No subject token was found.");
    }

    private String getSubjectTokenFromMetadataServer() throws IOException {
        HttpRequest buildGetRequest = this.transportFactory.create().createRequestFactory().buildGetRequest(new GenericUrl(this.identityPoolCredentialSource.credentialLocation));
        buildGetRequest.setParser(new JsonObjectParser(OAuth2Utils.JSON_FACTORY));
        if (this.identityPoolCredentialSource.hasHeaders()) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.putAll(this.identityPoolCredentialSource.headers);
            buildGetRequest.setHeaders(httpHeaders);
        }
        try {
            return parseToken(buildGetRequest.execute().getContent());
        } catch (IOException e) {
            throw new IOException(String.format("Error getting subject token from metadata server: %s", e.getMessage()), e);
        }
    }

    @Override // com.google.auth.oauth2.GoogleCredentials
    public IdentityPoolCredentials createScoped(Collection<String> collection) {
        return new IdentityPoolCredentials(this.transportFactory, getAudience(), getSubjectTokenType(), getTokenUrl(), this.identityPoolCredentialSource, getTokenInfoUrl(), getServiceAccountImpersonationUrl(), getQuotaProjectId(), getClientId(), getClientSecret(), collection, getEnvironmentProvider());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(IdentityPoolCredentials identityPoolCredentials) {
        return new Builder(identityPoolCredentials);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder extends ExternalAccountCredentials.Builder {
        Builder() {
        }

        Builder(IdentityPoolCredentials identityPoolCredentials) {
            super(identityPoolCredentials);
        }

        @Override // com.google.auth.oauth2.ExternalAccountCredentials.Builder, com.google.auth.oauth2.GoogleCredentials.Builder, com.google.auth.oauth2.OAuth2Credentials.Builder
        public IdentityPoolCredentials build() {
            return new IdentityPoolCredentials(this.transportFactory, this.audience, this.subjectTokenType, this.tokenUrl, (IdentityPoolCredentialSource) this.credentialSource, this.tokenInfoUrl, this.serviceAccountImpersonationUrl, this.quotaProjectId, this.clientId, this.clientSecret, this.scopes, this.environmentProvider);
        }
    }
}
