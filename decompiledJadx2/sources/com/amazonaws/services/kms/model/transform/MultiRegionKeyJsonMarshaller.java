package com.amazonaws.services.kms.model.transform;

import com.amazonaws.services.kms.model.MultiRegionKey;
import com.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class MultiRegionKeyJsonMarshaller {
    private static MultiRegionKeyJsonMarshaller instance;

    MultiRegionKeyJsonMarshaller() {
    }

    public void marshall(MultiRegionKey multiRegionKey, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (multiRegionKey.getArn() != null) {
            String arn = multiRegionKey.getArn();
            awsJsonWriter.name("Arn");
            awsJsonWriter.value(arn);
        }
        if (multiRegionKey.getRegion() != null) {
            String region = multiRegionKey.getRegion();
            awsJsonWriter.name("Region");
            awsJsonWriter.value(region);
        }
        awsJsonWriter.endObject();
    }

    public static MultiRegionKeyJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new MultiRegionKeyJsonMarshaller();
        }
        return instance;
    }
}
