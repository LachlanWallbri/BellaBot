package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.GroupType;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* loaded from: classes.dex */
class GroupTypeJsonMarshaller {
    private static GroupTypeJsonMarshaller instance;

    GroupTypeJsonMarshaller() {
    }

    public void marshall(GroupType groupType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (groupType.getGroupName() != null) {
            String groupName = groupType.getGroupName();
            awsJsonWriter.name("GroupName");
            awsJsonWriter.value(groupName);
        }
        if (groupType.getUserPoolId() != null) {
            String userPoolId = groupType.getUserPoolId();
            awsJsonWriter.name("UserPoolId");
            awsJsonWriter.value(userPoolId);
        }
        if (groupType.getDescription() != null) {
            String description = groupType.getDescription();
            awsJsonWriter.name("Description");
            awsJsonWriter.value(description);
        }
        if (groupType.getRoleArn() != null) {
            String roleArn = groupType.getRoleArn();
            awsJsonWriter.name("RoleArn");
            awsJsonWriter.value(roleArn);
        }
        if (groupType.getPrecedence() != null) {
            Integer precedence = groupType.getPrecedence();
            awsJsonWriter.name("Precedence");
            awsJsonWriter.value(precedence);
        }
        if (groupType.getLastModifiedDate() != null) {
            Date lastModifiedDate = groupType.getLastModifiedDate();
            awsJsonWriter.name("LastModifiedDate");
            awsJsonWriter.value(lastModifiedDate);
        }
        if (groupType.getCreationDate() != null) {
            Date creationDate = groupType.getCreationDate();
            awsJsonWriter.name("CreationDate");
            awsJsonWriter.value(creationDate);
        }
        awsJsonWriter.endObject();
    }

    public static GroupTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new GroupTypeJsonMarshaller();
        }
        return instance;
    }
}
