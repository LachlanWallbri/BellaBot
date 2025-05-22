package com.google.auth.oauth2;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.auth.http.HttpTransportFactory;
import com.google.auth.oauth2.OAuth2Credentials;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class GoogleCredentials extends OAuth2Credentials {
    static final String QUOTA_PROJECT_ID_HEADER_KEY = "x-goog-user-project";
    static final String SERVICE_ACCOUNT_FILE_TYPE = "service_account";
    static final String USER_FILE_TYPE = "authorized_user";
    private static final DefaultCredentialsProvider defaultCredentialsProvider = new DefaultCredentialsProvider();
    private static final long serialVersionUID = -1522852442442473691L;

    public GoogleCredentials createDelegated(String str) {
        return this;
    }

    public GoogleCredentials createScoped(Collection<String> collection) {
        return this;
    }

    public GoogleCredentials createScoped(Collection<String> collection, Collection<String> collection2) {
        return this;
    }

    public boolean createScopedRequired() {
        return false;
    }

    public static GoogleCredentials create(AccessToken accessToken) {
        return newBuilder().setAccessToken(accessToken).build();
    }

    public static GoogleCredentials getApplicationDefault() throws IOException {
        return getApplicationDefault(OAuth2Utils.HTTP_TRANSPORT_FACTORY);
    }

    public static GoogleCredentials getApplicationDefault(HttpTransportFactory httpTransportFactory) throws IOException {
        Preconditions.checkNotNull(httpTransportFactory);
        return defaultCredentialsProvider.getDefaultCredentials(httpTransportFactory);
    }

    public static GoogleCredentials fromStream(InputStream inputStream) throws IOException {
        return fromStream(inputStream, OAuth2Utils.HTTP_TRANSPORT_FACTORY);
    }

    public static GoogleCredentials fromStream(InputStream inputStream, HttpTransportFactory httpTransportFactory) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(httpTransportFactory);
        GenericJson genericJson = (GenericJson) new JsonObjectParser(OAuth2Utils.JSON_FACTORY).parseAndClose(inputStream, StandardCharsets.UTF_8, GenericJson.class);
        String str = (String) genericJson.get("type");
        if (str == null) {
            throw new IOException("Error reading credentials from stream, 'type' field not specified.");
        }
        if (USER_FILE_TYPE.equals(str)) {
            return UserCredentials.fromJson(genericJson, httpTransportFactory);
        }
        if ("service_account".equals(str)) {
            return ServiceAccountCredentials.fromJson(genericJson, httpTransportFactory);
        }
        if ("external_account".equals(str)) {
            return ExternalAccountCredentials.fromJson(genericJson, httpTransportFactory);
        }
        throw new IOException(String.format("Error reading credentials from stream, 'type' value '%s' not recognized. Expecting '%s' or '%s'.", str, USER_FILE_TYPE, "service_account"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, List<String>> addQuotaProjectIdToRequestMetadata(String str, Map<String, List<String>> map) {
        Preconditions.checkNotNull(map);
        HashMap hashMap = new HashMap(map);
        if (str != null && !map.containsKey(QUOTA_PROJECT_ID_HEADER_KEY)) {
            hashMap.put(QUOTA_PROJECT_ID_HEADER_KEY, Collections.singletonList(str));
        }
        return Collections.unmodifiableMap(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GoogleCredentials() {
        this(null);
    }

    public GoogleCredentials(AccessToken accessToken) {
        super(accessToken);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public Builder toBuilder() {
        return new Builder(this);
    }

    public GoogleCredentials createScoped(String... strArr) {
        return createScoped(ImmutableList.copyOf(strArr));
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder extends OAuth2Credentials.Builder {
        /* JADX INFO: Access modifiers changed from: protected */
        public Builder() {
        }

        protected Builder(GoogleCredentials googleCredentials) {
            setAccessToken(googleCredentials.getAccessToken());
        }

        @Override // com.google.auth.oauth2.OAuth2Credentials.Builder
        public GoogleCredentials build() {
            return new GoogleCredentials(getAccessToken());
        }

        @Override // com.google.auth.oauth2.OAuth2Credentials.Builder
        public Builder setAccessToken(AccessToken accessToken) {
            super.setAccessToken(accessToken);
            return this;
        }
    }
}
