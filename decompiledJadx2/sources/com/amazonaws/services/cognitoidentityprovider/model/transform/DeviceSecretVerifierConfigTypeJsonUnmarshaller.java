package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.DeviceSecretVerifierConfigType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class DeviceSecretVerifierConfigTypeJsonUnmarshaller implements Unmarshaller<DeviceSecretVerifierConfigType, JsonUnmarshallerContext> {
    private static DeviceSecretVerifierConfigTypeJsonUnmarshaller instance;

    DeviceSecretVerifierConfigTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DeviceSecretVerifierConfigType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        DeviceSecretVerifierConfigType deviceSecretVerifierConfigType = new DeviceSecretVerifierConfigType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("PasswordVerifier")) {
                deviceSecretVerifierConfigType.setPasswordVerifier(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("Salt")) {
                deviceSecretVerifierConfigType.setSalt(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return deviceSecretVerifierConfigType;
    }

    public static DeviceSecretVerifierConfigTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeviceSecretVerifierConfigTypeJsonUnmarshaller();
        }
        return instance;
    }
}
