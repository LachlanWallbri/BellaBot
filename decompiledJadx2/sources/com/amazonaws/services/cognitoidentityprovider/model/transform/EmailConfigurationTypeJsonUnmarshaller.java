package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.EmailConfigurationType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class EmailConfigurationTypeJsonUnmarshaller implements Unmarshaller<EmailConfigurationType, JsonUnmarshallerContext> {
    private static EmailConfigurationTypeJsonUnmarshaller instance;

    EmailConfigurationTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public EmailConfigurationType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        EmailConfigurationType emailConfigurationType = new EmailConfigurationType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("SourceArn")) {
                emailConfigurationType.setSourceArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ReplyToEmailAddress")) {
                emailConfigurationType.setReplyToEmailAddress(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("EmailSendingAccount")) {
                emailConfigurationType.setEmailSendingAccount(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("From")) {
                emailConfigurationType.setFrom(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ConfigurationSet")) {
                emailConfigurationType.setConfigurationSet(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return emailConfigurationType;
    }

    public static EmailConfigurationTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new EmailConfigurationTypeJsonUnmarshaller();
        }
        return instance;
    }
}
