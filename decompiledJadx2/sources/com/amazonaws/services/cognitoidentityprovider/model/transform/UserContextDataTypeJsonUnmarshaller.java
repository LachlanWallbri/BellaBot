package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.UserContextDataType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class UserContextDataTypeJsonUnmarshaller implements Unmarshaller<UserContextDataType, JsonUnmarshallerContext> {
    private static UserContextDataTypeJsonUnmarshaller instance;

    UserContextDataTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public UserContextDataType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        UserContextDataType userContextDataType = new UserContextDataType();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("EncodedData")) {
                userContextDataType.setEncodedData(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return userContextDataType;
    }

    public static UserContextDataTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UserContextDataTypeJsonUnmarshaller();
        }
        return instance;
    }
}
