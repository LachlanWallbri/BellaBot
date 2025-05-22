package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.CodeDeliveryDetailsType;
import com.amazonaws.util.json.AwsJsonWriter;
import org.apache.http.HttpHeaders;

/* loaded from: classes.dex */
class CodeDeliveryDetailsTypeJsonMarshaller {
    private static CodeDeliveryDetailsTypeJsonMarshaller instance;

    CodeDeliveryDetailsTypeJsonMarshaller() {
    }

    public void marshall(CodeDeliveryDetailsType codeDeliveryDetailsType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (codeDeliveryDetailsType.getDestination() != null) {
            String destination = codeDeliveryDetailsType.getDestination();
            awsJsonWriter.name(HttpHeaders.DESTINATION);
            awsJsonWriter.value(destination);
        }
        if (codeDeliveryDetailsType.getDeliveryMedium() != null) {
            String deliveryMedium = codeDeliveryDetailsType.getDeliveryMedium();
            awsJsonWriter.name("DeliveryMedium");
            awsJsonWriter.value(deliveryMedium);
        }
        if (codeDeliveryDetailsType.getAttributeName() != null) {
            String attributeName = codeDeliveryDetailsType.getAttributeName();
            awsJsonWriter.name("AttributeName");
            awsJsonWriter.value(attributeName);
        }
        awsJsonWriter.endObject();
    }

    public static CodeDeliveryDetailsTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new CodeDeliveryDetailsTypeJsonMarshaller();
        }
        return instance;
    }
}
