package com.google.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.GenericData;
import com.google.auth.Credentials;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.common.io.BaseEncoding;
import java.io.IOException;
import java.util.Map;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class IamUtils {
    private static final String ID_TOKEN_URL_FORMAT = "https://iamcredentials.googleapis.com/v1/projects/-/serviceAccounts/%s:generateIdToken";
    private static final String PARSE_ERROR_MESSAGE = "Error parsing error message response. ";
    private static final String PARSE_ERROR_SIGNATURE = "Error parsing signature response. ";
    private static final String SIGN_BLOB_URL_FORMAT = "https://iamcredentials.googleapis.com/v1/projects/-/serviceAccounts/%s:signBlob";

    IamUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] sign(String str, Credentials credentials, HttpTransport httpTransport, byte[] bArr, Map<String, ?> map) {
        BaseEncoding base64 = BaseEncoding.base64();
        try {
            return base64.decode(getSignature(str, credentials, httpTransport, base64.encode(bArr), map));
        } catch (IOException e) {
            throw new ServiceAccountSigner.SigningException("Failed to sign the provided bytes", e);
        }
    }

    private static String getSignature(String str, Credentials credentials, HttpTransport httpTransport, String str2, Map<String, ?> map) throws IOException {
        GenericUrl genericUrl = new GenericUrl(String.format(SIGN_BLOB_URL_FORMAT, str));
        GenericData genericData = new GenericData();
        genericData.set(MqttServiceConstants.PAYLOAD, str2);
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            genericData.set(entry.getKey(), entry.getValue());
        }
        HttpRequest buildPostRequest = httpTransport.createRequestFactory(new HttpCredentialsAdapter(credentials)).buildPostRequest(genericUrl, new JsonHttpContent(OAuth2Utils.JSON_FACTORY, genericData));
        buildPostRequest.setParser(new JsonObjectParser(OAuth2Utils.JSON_FACTORY));
        buildPostRequest.setThrowExceptionOnExecuteError(false);
        HttpResponse execute = buildPostRequest.execute();
        int statusCode = execute.getStatusCode();
        if (statusCode >= 400 && statusCode < 500) {
            throw new IOException(String.format("Error code %s trying to sign provided bytes: %s", Integer.valueOf(statusCode), OAuth2Utils.validateString(OAuth2Utils.validateMap((GenericData) execute.parseAs(GenericData.class), "error", PARSE_ERROR_MESSAGE), "message", PARSE_ERROR_MESSAGE)));
        }
        if (statusCode != 200) {
            throw new IOException(String.format("Unexpected Error code %s trying to sign provided bytes: %s", Integer.valueOf(statusCode), execute.parseAsString()));
        }
        if (execute.getContent() == null) {
            throw new IOException("Empty content from sign blob server request.");
        }
        return OAuth2Utils.validateString((GenericData) execute.parseAs(GenericData.class), "signedBlob", PARSE_ERROR_SIGNATURE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static IdToken getIdToken(String str, Credentials credentials, HttpTransport httpTransport, String str2, boolean z, Map<String, ?> map) throws IOException {
        GenericUrl genericUrl = new GenericUrl(String.format(ID_TOKEN_URL_FORMAT, str));
        GenericData genericData = new GenericData();
        genericData.set("audience", str2);
        genericData.set("includeEmail", Boolean.valueOf(z));
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            genericData.set(entry.getKey(), entry.getValue());
        }
        HttpRequest buildPostRequest = httpTransport.createRequestFactory(new HttpCredentialsAdapter(credentials)).buildPostRequest(genericUrl, new JsonHttpContent(OAuth2Utils.JSON_FACTORY, genericData));
        buildPostRequest.setParser(new JsonObjectParser(OAuth2Utils.JSON_FACTORY));
        buildPostRequest.setThrowExceptionOnExecuteError(false);
        HttpResponse execute = buildPostRequest.execute();
        int statusCode = execute.getStatusCode();
        if (statusCode >= 400 && statusCode < 500) {
            throw new IOException(String.format("Error code %s trying to getIDToken: %s", Integer.valueOf(statusCode), OAuth2Utils.validateString(OAuth2Utils.validateMap((GenericData) execute.parseAs(GenericData.class), "error", PARSE_ERROR_MESSAGE), "message", PARSE_ERROR_MESSAGE)));
        }
        if (statusCode != 200) {
            throw new IOException(String.format("Unexpected Error code %s trying to getIDToken: %s", Integer.valueOf(statusCode), execute.parseAsString()));
        }
        if (execute.getContent() == null) {
            throw new IOException("Empty content from generateIDToken server request.");
        }
        return IdToken.create(OAuth2Utils.validateString((GenericJson) execute.parseAs(GenericJson.class), "token", PARSE_ERROR_MESSAGE));
    }
}
