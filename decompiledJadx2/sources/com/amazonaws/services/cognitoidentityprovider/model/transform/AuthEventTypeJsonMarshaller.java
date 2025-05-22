package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.AuthEventType;
import com.amazonaws.services.cognitoidentityprovider.model.ChallengeResponseType;
import com.amazonaws.services.cognitoidentityprovider.model.EventContextDataType;
import com.amazonaws.services.cognitoidentityprovider.model.EventFeedbackType;
import com.amazonaws.services.cognitoidentityprovider.model.EventRiskType;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
class AuthEventTypeJsonMarshaller {
    private static AuthEventTypeJsonMarshaller instance;

    AuthEventTypeJsonMarshaller() {
    }

    public void marshall(AuthEventType authEventType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (authEventType.getEventId() != null) {
            String eventId = authEventType.getEventId();
            awsJsonWriter.name("EventId");
            awsJsonWriter.value(eventId);
        }
        if (authEventType.getEventType() != null) {
            String eventType = authEventType.getEventType();
            awsJsonWriter.name("EventType");
            awsJsonWriter.value(eventType);
        }
        if (authEventType.getCreationDate() != null) {
            Date creationDate = authEventType.getCreationDate();
            awsJsonWriter.name("CreationDate");
            awsJsonWriter.value(creationDate);
        }
        if (authEventType.getEventResponse() != null) {
            String eventResponse = authEventType.getEventResponse();
            awsJsonWriter.name("EventResponse");
            awsJsonWriter.value(eventResponse);
        }
        if (authEventType.getEventRisk() != null) {
            EventRiskType eventRisk = authEventType.getEventRisk();
            awsJsonWriter.name("EventRisk");
            EventRiskTypeJsonMarshaller.getInstance().marshall(eventRisk, awsJsonWriter);
        }
        if (authEventType.getChallengeResponses() != null) {
            List<ChallengeResponseType> challengeResponses = authEventType.getChallengeResponses();
            awsJsonWriter.name("ChallengeResponses");
            awsJsonWriter.beginArray();
            for (ChallengeResponseType challengeResponseType : challengeResponses) {
                if (challengeResponseType != null) {
                    ChallengeResponseTypeJsonMarshaller.getInstance().marshall(challengeResponseType, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        if (authEventType.getEventContextData() != null) {
            EventContextDataType eventContextData = authEventType.getEventContextData();
            awsJsonWriter.name("EventContextData");
            EventContextDataTypeJsonMarshaller.getInstance().marshall(eventContextData, awsJsonWriter);
        }
        if (authEventType.getEventFeedback() != null) {
            EventFeedbackType eventFeedback = authEventType.getEventFeedback();
            awsJsonWriter.name("EventFeedback");
            EventFeedbackTypeJsonMarshaller.getInstance().marshall(eventFeedback, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static AuthEventTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AuthEventTypeJsonMarshaller();
        }
        return instance;
    }
}
