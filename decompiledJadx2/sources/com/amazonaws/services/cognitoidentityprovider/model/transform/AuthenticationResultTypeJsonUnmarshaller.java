package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.AuthenticationResultType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class AuthenticationResultTypeJsonUnmarshaller implements Unmarshaller<AuthenticationResultType, JsonUnmarshallerContext> {
    private static AuthenticationResultTypeJsonUnmarshaller instance;

    AuthenticationResultTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuthenticationResultType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuthenticationResultType authenticationResultType = new AuthenticationResultType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("AccessToken")) {
                authenticationResultType.setAccessToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ExpiresIn")) {
                authenticationResultType.setExpiresIn(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("TokenType")) {
                authenticationResultType.setTokenType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("RefreshToken")) {
                authenticationResultType.setRefreshToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("IdToken")) {
                authenticationResultType.setIdToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("NewDeviceMetadata")) {
                authenticationResultType.setNewDeviceMetadata(NewDeviceMetadataTypeJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return authenticationResultType;
    }

    public static AuthenticationResultTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuthenticationResultTypeJsonUnmarshaller();
        }
        return instance;
    }
}
