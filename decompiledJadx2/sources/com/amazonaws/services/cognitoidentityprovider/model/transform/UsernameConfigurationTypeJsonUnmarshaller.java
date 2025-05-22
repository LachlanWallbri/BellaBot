package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.UsernameConfigurationType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class UsernameConfigurationTypeJsonUnmarshaller implements Unmarshaller<UsernameConfigurationType, JsonUnmarshallerContext> {
    private static UsernameConfigurationTypeJsonUnmarshaller instance;

    UsernameConfigurationTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public UsernameConfigurationType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        UsernameConfigurationType usernameConfigurationType = new UsernameConfigurationType();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("CaseSensitive")) {
                usernameConfigurationType.setCaseSensitive(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return usernameConfigurationType;
    }

    public static UsernameConfigurationTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UsernameConfigurationTypeJsonUnmarshaller();
        }
        return instance;
    }
}
