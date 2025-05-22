package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.TokenValidityUnitsType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class TokenValidityUnitsTypeJsonUnmarshaller implements Unmarshaller<TokenValidityUnitsType, JsonUnmarshallerContext> {
    private static TokenValidityUnitsTypeJsonUnmarshaller instance;

    TokenValidityUnitsTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TokenValidityUnitsType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TokenValidityUnitsType tokenValidityUnitsType = new TokenValidityUnitsType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("AccessToken")) {
                tokenValidityUnitsType.setAccessToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("IdToken")) {
                tokenValidityUnitsType.setIdToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("RefreshToken")) {
                tokenValidityUnitsType.setRefreshToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return tokenValidityUnitsType;
    }

    public static TokenValidityUnitsTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TokenValidityUnitsTypeJsonUnmarshaller();
        }
        return instance;
    }
}
