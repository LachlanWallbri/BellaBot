package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.UserPoolClientDescription;
import com.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class UserPoolClientDescriptionJsonMarshaller {
    private static UserPoolClientDescriptionJsonMarshaller instance;

    UserPoolClientDescriptionJsonMarshaller() {
    }

    public void marshall(UserPoolClientDescription userPoolClientDescription, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (userPoolClientDescription.getClientId() != null) {
            String clientId = userPoolClientDescription.getClientId();
            awsJsonWriter.name("ClientId");
            awsJsonWriter.value(clientId);
        }
        if (userPoolClientDescription.getUserPoolId() != null) {
            String userPoolId = userPoolClientDescription.getUserPoolId();
            awsJsonWriter.name("UserPoolId");
            awsJsonWriter.value(userPoolId);
        }
        if (userPoolClientDescription.getClientName() != null) {
            String clientName = userPoolClientDescription.getClientName();
            awsJsonWriter.name("ClientName");
            awsJsonWriter.value(clientName);
        }
        awsJsonWriter.endObject();
    }

    public static UserPoolClientDescriptionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new UserPoolClientDescriptionJsonMarshaller();
        }
        return instance;
    }
}
