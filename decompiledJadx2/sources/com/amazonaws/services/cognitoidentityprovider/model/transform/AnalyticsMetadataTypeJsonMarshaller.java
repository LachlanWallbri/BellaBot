package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.AnalyticsMetadataType;
import com.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class AnalyticsMetadataTypeJsonMarshaller {
    private static AnalyticsMetadataTypeJsonMarshaller instance;

    AnalyticsMetadataTypeJsonMarshaller() {
    }

    public void marshall(AnalyticsMetadataType analyticsMetadataType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (analyticsMetadataType.getAnalyticsEndpointId() != null) {
            String analyticsEndpointId = analyticsMetadataType.getAnalyticsEndpointId();
            awsJsonWriter.name("AnalyticsEndpointId");
            awsJsonWriter.value(analyticsEndpointId);
        }
        awsJsonWriter.endObject();
    }

    public static AnalyticsMetadataTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AnalyticsMetadataTypeJsonMarshaller();
        }
        return instance;
    }
}
