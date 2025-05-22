package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.amazonaws.services.cognitoidentityprovider.model.LambdaConfigType;
import com.amazonaws.services.cognitoidentityprovider.model.UserPoolDescriptionType;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* loaded from: classes.dex */
class UserPoolDescriptionTypeJsonMarshaller {
    private static UserPoolDescriptionTypeJsonMarshaller instance;

    UserPoolDescriptionTypeJsonMarshaller() {
    }

    public void marshall(UserPoolDescriptionType userPoolDescriptionType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (userPoolDescriptionType.getId() != null) {
            String id = userPoolDescriptionType.getId();
            awsJsonWriter.name(JsonDocumentFields.POLICY_ID);
            awsJsonWriter.value(id);
        }
        if (userPoolDescriptionType.getName() != null) {
            String name = userPoolDescriptionType.getName();
            awsJsonWriter.name("Name");
            awsJsonWriter.value(name);
        }
        if (userPoolDescriptionType.getLambdaConfig() != null) {
            LambdaConfigType lambdaConfig = userPoolDescriptionType.getLambdaConfig();
            awsJsonWriter.name("LambdaConfig");
            LambdaConfigTypeJsonMarshaller.getInstance().marshall(lambdaConfig, awsJsonWriter);
        }
        if (userPoolDescriptionType.getStatus() != null) {
            String status = userPoolDescriptionType.getStatus();
            awsJsonWriter.name("Status");
            awsJsonWriter.value(status);
        }
        if (userPoolDescriptionType.getLastModifiedDate() != null) {
            Date lastModifiedDate = userPoolDescriptionType.getLastModifiedDate();
            awsJsonWriter.name("LastModifiedDate");
            awsJsonWriter.value(lastModifiedDate);
        }
        if (userPoolDescriptionType.getCreationDate() != null) {
            Date creationDate = userPoolDescriptionType.getCreationDate();
            awsJsonWriter.name("CreationDate");
            awsJsonWriter.value(creationDate);
        }
        awsJsonWriter.endObject();
    }

    public static UserPoolDescriptionTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new UserPoolDescriptionTypeJsonMarshaller();
        }
        return instance;
    }
}
