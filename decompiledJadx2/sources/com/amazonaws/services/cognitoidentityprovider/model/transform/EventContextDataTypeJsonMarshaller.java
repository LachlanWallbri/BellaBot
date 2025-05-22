package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.cognito.clientcontext.datacollection.DataRecordKey;
import com.amazonaws.services.cognitoidentityprovider.model.EventContextDataType;
import com.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class EventContextDataTypeJsonMarshaller {
    private static EventContextDataTypeJsonMarshaller instance;

    EventContextDataTypeJsonMarshaller() {
    }

    public void marshall(EventContextDataType eventContextDataType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (eventContextDataType.getIpAddress() != null) {
            String ipAddress = eventContextDataType.getIpAddress();
            awsJsonWriter.name("IpAddress");
            awsJsonWriter.value(ipAddress);
        }
        if (eventContextDataType.getDeviceName() != null) {
            String deviceName = eventContextDataType.getDeviceName();
            awsJsonWriter.name(DataRecordKey.MODEL);
            awsJsonWriter.value(deviceName);
        }
        if (eventContextDataType.getTimezone() != null) {
            String timezone = eventContextDataType.getTimezone();
            awsJsonWriter.name("Timezone");
            awsJsonWriter.value(timezone);
        }
        if (eventContextDataType.getCity() != null) {
            String city = eventContextDataType.getCity();
            awsJsonWriter.name("City");
            awsJsonWriter.value(city);
        }
        if (eventContextDataType.getCountry() != null) {
            String country = eventContextDataType.getCountry();
            awsJsonWriter.name("Country");
            awsJsonWriter.value(country);
        }
        awsJsonWriter.endObject();
    }

    public static EventContextDataTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new EventContextDataTypeJsonMarshaller();
        }
        return instance;
    }
}
