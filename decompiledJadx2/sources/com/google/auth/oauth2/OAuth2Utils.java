package com.google.auth.oauth2;

import com.amitshekhar.utils.DataType;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.gson.GsonFactory;
import com.google.auth.http.HttpTransportFactory;
import com.google.common.io.ByteStreams;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class OAuth2Utils {
    static final String BEARER_PREFIX = "Bearer ";
    static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    static final URI TOKEN_SERVER_URI = URI.create("https://oauth2.googleapis.com/token");
    static final URI TOKEN_REVOKE_URI = URI.create("https://oauth2.googleapis.com/revoke");
    static final URI USER_AUTH_URI = URI.create("https://accounts.google.com/o/oauth2/auth");
    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final HttpTransportFactory HTTP_TRANSPORT_FACTORY = new DefaultHttpTransportFactory();
    static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static String VALUE_NOT_FOUND_MESSAGE = "%sExpected value %s not found.";
    private static String VALUE_WRONG_TYPE_MESSAGE = "%sExpected %s value %s of wrong type.";

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static class DefaultHttpTransportFactory implements HttpTransportFactory {
        DefaultHttpTransportFactory() {
        }

        @Override // com.google.auth.http.HttpTransportFactory
        public HttpTransport create() {
            return OAuth2Utils.HTTP_TRANSPORT;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean headersContainValue(HttpHeaders httpHeaders, String str, String str2) {
        Object obj = httpHeaders.get(str);
        if (obj instanceof Collection) {
            return ((Collection) obj).contains(str2);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GenericJson parseJson(String str) throws IOException {
        return (GenericJson) new JsonObjectParser(JSON_FACTORY).parseAndClose((InputStream) new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8, GenericJson.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String validateString(Map<String, Object> map, String str, String str2) throws IOException {
        Object obj = map.get(str);
        if (obj == null) {
            throw new IOException(String.format(VALUE_NOT_FOUND_MESSAGE, str2, str));
        }
        if (!(obj instanceof String)) {
            throw new IOException(String.format(VALUE_WRONG_TYPE_MESSAGE, str2, "string", str));
        }
        return (String) obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeInputStreamToFile(InputStream inputStream, String str) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
        try {
            ByteStreams.copy(inputStream, fileOutputStream);
        } finally {
            fileOutputStream.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String validateOptionalString(Map<String, Object> map, String str, String str2) throws IOException {
        Object obj = map.get(str);
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof String)) {
            throw new IOException(String.format(VALUE_WRONG_TYPE_MESSAGE, str2, "string", str));
        }
        return (String) obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int validateInt32(Map<String, Object> map, String str, String str2) throws IOException {
        Object obj = map.get(str);
        if (obj == null) {
            throw new IOException(String.format(VALUE_NOT_FOUND_MESSAGE, str2, str));
        }
        if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).intValueExact();
        }
        if (!(obj instanceof Integer)) {
            throw new IOException(String.format(VALUE_WRONG_TYPE_MESSAGE, str2, "integer", str));
        }
        return ((Integer) obj).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long validateLong(Map<String, Object> map, String str, String str2) throws IOException {
        Object obj = map.get(str);
        if (obj == null) {
            throw new IOException(String.format(VALUE_NOT_FOUND_MESSAGE, str2, str));
        }
        if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).longValueExact();
        }
        if (!(obj instanceof Long)) {
            throw new IOException(String.format(VALUE_WRONG_TYPE_MESSAGE, str2, DataType.LONG, str));
        }
        return ((Long) obj).longValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> validateMap(Map<String, Object> map, String str, String str2) throws IOException {
        Object obj = map.get(str);
        if (obj == null) {
            throw new IOException(String.format(VALUE_NOT_FOUND_MESSAGE, str2, str));
        }
        if (!(obj instanceof Map)) {
            throw new IOException(String.format(VALUE_WRONG_TYPE_MESSAGE, str2, "Map", str));
        }
        return (Map) obj;
    }

    private OAuth2Utils() {
    }
}
