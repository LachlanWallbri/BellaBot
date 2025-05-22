package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.MFAOptionType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class MFAOptionTypeJsonUnmarshaller implements Unmarshaller<MFAOptionType, JsonUnmarshallerContext> {
    private static MFAOptionTypeJsonUnmarshaller instance;

    MFAOptionTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public MFAOptionType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        MFAOptionType mFAOptionType = new MFAOptionType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("DeliveryMedium")) {
                mFAOptionType.setDeliveryMedium(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("AttributeName")) {
                mFAOptionType.setAttributeName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return mFAOptionType;
    }

    public static MFAOptionTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MFAOptionTypeJsonUnmarshaller();
        }
        return instance;
    }
}
