package com.amazonaws.services.kms.model.transform;

import com.amazonaws.services.kms.model.ListGrantsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class ListGrantsResultJsonUnmarshaller implements Unmarshaller<ListGrantsResult, JsonUnmarshallerContext> {
    private static ListGrantsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListGrantsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListGrantsResult listGrantsResult = new ListGrantsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("Grants")) {
                listGrantsResult.setGrants(new ListUnmarshaller(GrantListEntryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("NextMarker")) {
                listGrantsResult.setNextMarker(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("Truncated")) {
                listGrantsResult.setTruncated(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listGrantsResult;
    }

    public static ListGrantsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListGrantsResultJsonUnmarshaller();
        }
        return instance;
    }
}
