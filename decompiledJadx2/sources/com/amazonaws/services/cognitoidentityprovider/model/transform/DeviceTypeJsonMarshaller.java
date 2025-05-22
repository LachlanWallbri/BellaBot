package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.AttributeType;
import com.amazonaws.services.cognitoidentityprovider.model.DeviceType;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
class DeviceTypeJsonMarshaller {
    private static DeviceTypeJsonMarshaller instance;

    DeviceTypeJsonMarshaller() {
    }

    public void marshall(DeviceType deviceType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (deviceType.getDeviceKey() != null) {
            String deviceKey = deviceType.getDeviceKey();
            awsJsonWriter.name("DeviceKey");
            awsJsonWriter.value(deviceKey);
        }
        if (deviceType.getDeviceAttributes() != null) {
            List<AttributeType> deviceAttributes = deviceType.getDeviceAttributes();
            awsJsonWriter.name("DeviceAttributes");
            awsJsonWriter.beginArray();
            for (AttributeType attributeType : deviceAttributes) {
                if (attributeType != null) {
                    AttributeTypeJsonMarshaller.getInstance().marshall(attributeType, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        if (deviceType.getDeviceCreateDate() != null) {
            Date deviceCreateDate = deviceType.getDeviceCreateDate();
            awsJsonWriter.name("DeviceCreateDate");
            awsJsonWriter.value(deviceCreateDate);
        }
        if (deviceType.getDeviceLastModifiedDate() != null) {
            Date deviceLastModifiedDate = deviceType.getDeviceLastModifiedDate();
            awsJsonWriter.name("DeviceLastModifiedDate");
            awsJsonWriter.value(deviceLastModifiedDate);
        }
        if (deviceType.getDeviceLastAuthenticatedDate() != null) {
            Date deviceLastAuthenticatedDate = deviceType.getDeviceLastAuthenticatedDate();
            awsJsonWriter.name("DeviceLastAuthenticatedDate");
            awsJsonWriter.value(deviceLastAuthenticatedDate);
        }
        awsJsonWriter.endObject();
    }

    public static DeviceTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new DeviceTypeJsonMarshaller();
        }
        return instance;
    }
}
