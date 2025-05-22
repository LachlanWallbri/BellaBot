package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.NewDeviceMetadataType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class NewDeviceMetadataTypeJsonUnmarshaller implements Unmarshaller<NewDeviceMetadataType, JsonUnmarshallerContext> {
    private static NewDeviceMetadataTypeJsonUnmarshaller instance;

    NewDeviceMetadataTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public NewDeviceMetadataType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        NewDeviceMetadataType newDeviceMetadataType = new NewDeviceMetadataType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("DeviceKey")) {
                newDeviceMetadataType.setDeviceKey(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("DeviceGroupKey")) {
                newDeviceMetadataType.setDeviceGroupKey(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return newDeviceMetadataType;
    }

    public static NewDeviceMetadataTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new NewDeviceMetadataTypeJsonUnmarshaller();
        }
        return instance;
    }
}
