package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.AuthenticationResultType;
import com.amazonaws.services.cognitoidentityprovider.model.NewDeviceMetadataType;
import com.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class AuthenticationResultTypeJsonMarshaller {
    private static AuthenticationResultTypeJsonMarshaller instance;

    AuthenticationResultTypeJsonMarshaller() {
    }

    public void marshall(AuthenticationResultType authenticationResultType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (authenticationResultType.getAccessToken() != null) {
            String accessToken = authenticationResultType.getAccessToken();
            awsJsonWriter.name("AccessToken");
            awsJsonWriter.value(accessToken);
        }
        if (authenticationResultType.getExpiresIn() != null) {
            Integer expiresIn = authenticationResultType.getExpiresIn();
            awsJsonWriter.name("ExpiresIn");
            awsJsonWriter.value(expiresIn);
        }
        if (authenticationResultType.getTokenType() != null) {
            String tokenType = authenticationResultType.getTokenType();
            awsJsonWriter.name("TokenType");
            awsJsonWriter.value(tokenType);
        }
        if (authenticationResultType.getRefreshToken() != null) {
            String refreshToken = authenticationResultType.getRefreshToken();
            awsJsonWriter.name("RefreshToken");
            awsJsonWriter.value(refreshToken);
        }
        if (authenticationResultType.getIdToken() != null) {
            String idToken = authenticationResultType.getIdToken();
            awsJsonWriter.name("IdToken");
            awsJsonWriter.value(idToken);
        }
        if (authenticationResultType.getNewDeviceMetadata() != null) {
            NewDeviceMetadataType newDeviceMetadata = authenticationResultType.getNewDeviceMetadata();
            awsJsonWriter.name("NewDeviceMetadata");
            NewDeviceMetadataTypeJsonMarshaller.getInstance().marshall(newDeviceMetadata, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static AuthenticationResultTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AuthenticationResultTypeJsonMarshaller();
        }
        return instance;
    }
}
