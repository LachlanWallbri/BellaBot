package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.MessageTemplateType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class MessageTemplateTypeJsonUnmarshaller implements Unmarshaller<MessageTemplateType, JsonUnmarshallerContext> {
    private static MessageTemplateTypeJsonUnmarshaller instance;

    MessageTemplateTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public MessageTemplateType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        MessageTemplateType messageTemplateType = new MessageTemplateType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("SMSMessage")) {
                messageTemplateType.setSMSMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("EmailMessage")) {
                messageTemplateType.setEmailMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("EmailSubject")) {
                messageTemplateType.setEmailSubject(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return messageTemplateType;
    }

    public static MessageTemplateTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MessageTemplateTypeJsonUnmarshaller();
        }
        return instance;
    }
}
