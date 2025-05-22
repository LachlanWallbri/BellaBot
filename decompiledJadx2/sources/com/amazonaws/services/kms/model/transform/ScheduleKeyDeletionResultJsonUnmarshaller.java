package com.amazonaws.services.kms.model.transform;

import com.amazonaws.services.kms.model.ScheduleKeyDeletionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class ScheduleKeyDeletionResultJsonUnmarshaller implements Unmarshaller<ScheduleKeyDeletionResult, JsonUnmarshallerContext> {
    private static ScheduleKeyDeletionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ScheduleKeyDeletionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ScheduleKeyDeletionResult scheduleKeyDeletionResult = new ScheduleKeyDeletionResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("KeyId")) {
                scheduleKeyDeletionResult.setKeyId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("DeletionDate")) {
                scheduleKeyDeletionResult.setDeletionDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("KeyState")) {
                scheduleKeyDeletionResult.setKeyState(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("PendingWindowInDays")) {
                scheduleKeyDeletionResult.setPendingWindowInDays(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return scheduleKeyDeletionResult;
    }

    public static ScheduleKeyDeletionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ScheduleKeyDeletionResultJsonUnmarshaller();
        }
        return instance;
    }
}
