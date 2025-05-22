package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.EventFeedbackType;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* loaded from: classes.dex */
class EventFeedbackTypeJsonMarshaller {
    private static EventFeedbackTypeJsonMarshaller instance;

    EventFeedbackTypeJsonMarshaller() {
    }

    public void marshall(EventFeedbackType eventFeedbackType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (eventFeedbackType.getFeedbackValue() != null) {
            String feedbackValue = eventFeedbackType.getFeedbackValue();
            awsJsonWriter.name("FeedbackValue");
            awsJsonWriter.value(feedbackValue);
        }
        if (eventFeedbackType.getProvider() != null) {
            String provider = eventFeedbackType.getProvider();
            awsJsonWriter.name("Provider");
            awsJsonWriter.value(provider);
        }
        if (eventFeedbackType.getFeedbackDate() != null) {
            Date feedbackDate = eventFeedbackType.getFeedbackDate();
            awsJsonWriter.name("FeedbackDate");
            awsJsonWriter.value(feedbackDate);
        }
        awsJsonWriter.endObject();
    }

    public static EventFeedbackTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new EventFeedbackTypeJsonMarshaller();
        }
        return instance;
    }
}
