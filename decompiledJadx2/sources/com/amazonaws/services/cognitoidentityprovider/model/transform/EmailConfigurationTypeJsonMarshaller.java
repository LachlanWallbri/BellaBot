package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.EmailConfigurationType;
import com.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class EmailConfigurationTypeJsonMarshaller {
    private static EmailConfigurationTypeJsonMarshaller instance;

    EmailConfigurationTypeJsonMarshaller() {
    }

    public void marshall(EmailConfigurationType emailConfigurationType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (emailConfigurationType.getSourceArn() != null) {
            String sourceArn = emailConfigurationType.getSourceArn();
            awsJsonWriter.name("SourceArn");
            awsJsonWriter.value(sourceArn);
        }
        if (emailConfigurationType.getReplyToEmailAddress() != null) {
            String replyToEmailAddress = emailConfigurationType.getReplyToEmailAddress();
            awsJsonWriter.name("ReplyToEmailAddress");
            awsJsonWriter.value(replyToEmailAddress);
        }
        if (emailConfigurationType.getEmailSendingAccount() != null) {
            String emailSendingAccount = emailConfigurationType.getEmailSendingAccount();
            awsJsonWriter.name("EmailSendingAccount");
            awsJsonWriter.value(emailSendingAccount);
        }
        if (emailConfigurationType.getFrom() != null) {
            String from = emailConfigurationType.getFrom();
            awsJsonWriter.name("From");
            awsJsonWriter.value(from);
        }
        if (emailConfigurationType.getConfigurationSet() != null) {
            String configurationSet = emailConfigurationType.getConfigurationSet();
            awsJsonWriter.name("ConfigurationSet");
            awsJsonWriter.value(configurationSet);
        }
        awsJsonWriter.endObject();
    }

    public static EmailConfigurationTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new EmailConfigurationTypeJsonMarshaller();
        }
        return instance;
    }
}
