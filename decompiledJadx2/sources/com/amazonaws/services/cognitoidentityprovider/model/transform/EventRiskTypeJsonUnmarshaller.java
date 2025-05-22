package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.EventRiskType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class EventRiskTypeJsonUnmarshaller implements Unmarshaller<EventRiskType, JsonUnmarshallerContext> {
    private static EventRiskTypeJsonUnmarshaller instance;

    EventRiskTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public EventRiskType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        EventRiskType eventRiskType = new EventRiskType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("RiskDecision")) {
                eventRiskType.setRiskDecision(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("RiskLevel")) {
                eventRiskType.setRiskLevel(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("CompromisedCredentialsDetected")) {
                eventRiskType.setCompromisedCredentialsDetected(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return eventRiskType;
    }

    public static EventRiskTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new EventRiskTypeJsonUnmarshaller();
        }
        return instance;
    }
}
