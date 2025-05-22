package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.TokenValidityUnitsType;
import com.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class TokenValidityUnitsTypeJsonMarshaller {
    private static TokenValidityUnitsTypeJsonMarshaller instance;

    TokenValidityUnitsTypeJsonMarshaller() {
    }

    public void marshall(TokenValidityUnitsType tokenValidityUnitsType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (tokenValidityUnitsType.getAccessToken() != null) {
            String accessToken = tokenValidityUnitsType.getAccessToken();
            awsJsonWriter.name("AccessToken");
            awsJsonWriter.value(accessToken);
        }
        if (tokenValidityUnitsType.getIdToken() != null) {
            String idToken = tokenValidityUnitsType.getIdToken();
            awsJsonWriter.name("IdToken");
            awsJsonWriter.value(idToken);
        }
        if (tokenValidityUnitsType.getRefreshToken() != null) {
            String refreshToken = tokenValidityUnitsType.getRefreshToken();
            awsJsonWriter.name("RefreshToken");
            awsJsonWriter.value(refreshToken);
        }
        awsJsonWriter.endObject();
    }

    public static TokenValidityUnitsTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new TokenValidityUnitsTypeJsonMarshaller();
        }
        return instance;
    }
}
