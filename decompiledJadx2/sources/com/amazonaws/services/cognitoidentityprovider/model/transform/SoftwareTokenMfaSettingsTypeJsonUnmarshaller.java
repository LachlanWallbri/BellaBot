package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.SoftwareTokenMfaSettingsType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class SoftwareTokenMfaSettingsTypeJsonUnmarshaller implements Unmarshaller<SoftwareTokenMfaSettingsType, JsonUnmarshallerContext> {
    private static SoftwareTokenMfaSettingsTypeJsonUnmarshaller instance;

    SoftwareTokenMfaSettingsTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public SoftwareTokenMfaSettingsType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        SoftwareTokenMfaSettingsType softwareTokenMfaSettingsType = new SoftwareTokenMfaSettingsType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("Enabled")) {
                softwareTokenMfaSettingsType.setEnabled(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("PreferredMfa")) {
                softwareTokenMfaSettingsType.setPreferredMfa(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return softwareTokenMfaSettingsType;
    }

    public static SoftwareTokenMfaSettingsTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SoftwareTokenMfaSettingsTypeJsonUnmarshaller();
        }
        return instance;
    }
}
