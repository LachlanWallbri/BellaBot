package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.AnalyticsMetadataType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class AnalyticsMetadataTypeJsonUnmarshaller implements Unmarshaller<AnalyticsMetadataType, JsonUnmarshallerContext> {
    private static AnalyticsMetadataTypeJsonUnmarshaller instance;

    AnalyticsMetadataTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AnalyticsMetadataType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AnalyticsMetadataType analyticsMetadataType = new AnalyticsMetadataType();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("AnalyticsEndpointId")) {
                analyticsMetadataType.setAnalyticsEndpointId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return analyticsMetadataType;
    }

    public static AnalyticsMetadataTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AnalyticsMetadataTypeJsonUnmarshaller();
        }
        return instance;
    }
}
