package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.UserContextDataType;
import com.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class UserContextDataTypeJsonMarshaller {
    private static UserContextDataTypeJsonMarshaller instance;

    UserContextDataTypeJsonMarshaller() {
    }

    public void marshall(UserContextDataType userContextDataType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (userContextDataType.getEncodedData() != null) {
            String encodedData = userContextDataType.getEncodedData();
            awsJsonWriter.name("EncodedData");
            awsJsonWriter.value(encodedData);
        }
        awsJsonWriter.endObject();
    }

    public static UserContextDataTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new UserContextDataTypeJsonMarshaller();
        }
        return instance;
    }
}
