package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.UserImportJobType;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* loaded from: classes.dex */
class UserImportJobTypeJsonMarshaller {
    private static UserImportJobTypeJsonMarshaller instance;

    UserImportJobTypeJsonMarshaller() {
    }

    public void marshall(UserImportJobType userImportJobType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (userImportJobType.getJobName() != null) {
            String jobName = userImportJobType.getJobName();
            awsJsonWriter.name("JobName");
            awsJsonWriter.value(jobName);
        }
        if (userImportJobType.getJobId() != null) {
            String jobId = userImportJobType.getJobId();
            awsJsonWriter.name("JobId");
            awsJsonWriter.value(jobId);
        }
        if (userImportJobType.getUserPoolId() != null) {
            String userPoolId = userImportJobType.getUserPoolId();
            awsJsonWriter.name("UserPoolId");
            awsJsonWriter.value(userPoolId);
        }
        if (userImportJobType.getPreSignedUrl() != null) {
            String preSignedUrl = userImportJobType.getPreSignedUrl();
            awsJsonWriter.name("PreSignedUrl");
            awsJsonWriter.value(preSignedUrl);
        }
        if (userImportJobType.getCreationDate() != null) {
            Date creationDate = userImportJobType.getCreationDate();
            awsJsonWriter.name("CreationDate");
            awsJsonWriter.value(creationDate);
        }
        if (userImportJobType.getStartDate() != null) {
            Date startDate = userImportJobType.getStartDate();
            awsJsonWriter.name("StartDate");
            awsJsonWriter.value(startDate);
        }
        if (userImportJobType.getCompletionDate() != null) {
            Date completionDate = userImportJobType.getCompletionDate();
            awsJsonWriter.name("CompletionDate");
            awsJsonWriter.value(completionDate);
        }
        if (userImportJobType.getStatus() != null) {
            String status = userImportJobType.getStatus();
            awsJsonWriter.name("Status");
            awsJsonWriter.value(status);
        }
        if (userImportJobType.getCloudWatchLogsRoleArn() != null) {
            String cloudWatchLogsRoleArn = userImportJobType.getCloudWatchLogsRoleArn();
            awsJsonWriter.name("CloudWatchLogsRoleArn");
            awsJsonWriter.value(cloudWatchLogsRoleArn);
        }
        if (userImportJobType.getImportedUsers() != null) {
            Long importedUsers = userImportJobType.getImportedUsers();
            awsJsonWriter.name("ImportedUsers");
            awsJsonWriter.value(importedUsers);
        }
        if (userImportJobType.getSkippedUsers() != null) {
            Long skippedUsers = userImportJobType.getSkippedUsers();
            awsJsonWriter.name("SkippedUsers");
            awsJsonWriter.value(skippedUsers);
        }
        if (userImportJobType.getFailedUsers() != null) {
            Long failedUsers = userImportJobType.getFailedUsers();
            awsJsonWriter.name("FailedUsers");
            awsJsonWriter.value(failedUsers);
        }
        if (userImportJobType.getCompletionMessage() != null) {
            String completionMessage = userImportJobType.getCompletionMessage();
            awsJsonWriter.name("CompletionMessage");
            awsJsonWriter.value(completionMessage);
        }
        awsJsonWriter.endObject();
    }

    public static UserImportJobTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new UserImportJobTypeJsonMarshaller();
        }
        return instance;
    }
}
