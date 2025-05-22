package com.google.auth.oauth2;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class ClientId {
    private static final String FIELD_CLIENT_ID = "client_id";
    private static final String FIELD_CLIENT_SECRET = "client_secret";
    private static final String FIELD_TYPE_INSTALLED = "installed";
    private static final String FIELD_TYPE_WEB = "web";
    private static final String JSON_PARSE_ERROR = "Error parsing Client ID JSON: ";
    private final String clientId;
    private final String clientSecret;

    /* renamed from: of */
    public static ClientId m581of(String str, String str2) {
        return new ClientId(str, str2);
    }

    public static ClientId fromJson(Map<String, Object> map) throws IOException {
        Object obj = map.get(FIELD_TYPE_INSTALLED);
        if (obj == null) {
            obj = map.get(FIELD_TYPE_WEB);
        }
        if (obj == null || !(obj instanceof Map)) {
            throw new IOException("Unable to parse Client ID JSON. Expecting top-level field 'web' or 'installed' of collection type");
        }
        Map map2 = (Map) obj;
        String validateString = OAuth2Utils.validateString(map2, FIELD_CLIENT_ID, JSON_PARSE_ERROR);
        if (validateString == null || validateString.length() == 0) {
            throw new IOException("Unable to parse ClientId. Field 'client_id' is required.");
        }
        return new ClientId(validateString, OAuth2Utils.validateOptionalString(map2, FIELD_CLIENT_SECRET, JSON_PARSE_ERROR));
    }

    public static ClientId fromResource(Class<?> cls, String str) throws IOException {
        return fromStream(cls.getResourceAsStream(str));
    }

    public static ClientId fromStream(InputStream inputStream) throws IOException {
        Preconditions.checkNotNull(inputStream);
        return fromJson((GenericJson) new JsonObjectParser(OAuth2Utils.JSON_FACTORY).parseAndClose(inputStream, StandardCharsets.UTF_8, GenericJson.class));
    }

    private ClientId(String str, String str2) {
        this.clientId = (String) Preconditions.checkNotNull(str);
        this.clientSecret = str2;
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getClientSecret() {
        return this.clientSecret;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder {
        private String clientId;
        private String clientSecret;

        protected Builder() {
        }

        protected Builder(ClientId clientId) {
            this.clientId = clientId.getClientId();
            this.clientSecret = clientId.getClientSecret();
        }

        public Builder setClientId(String str) {
            this.clientId = str;
            return this;
        }

        public Builder setClientSecret(String str) {
            this.clientSecret = str;
            return this;
        }

        public String getClientSecret() {
            return this.clientSecret;
        }

        public ClientId build() {
            return new ClientId(this.clientId, this.clientSecret);
        }
    }
}
