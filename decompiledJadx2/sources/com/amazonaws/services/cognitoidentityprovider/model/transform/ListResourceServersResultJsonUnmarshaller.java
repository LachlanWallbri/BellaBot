package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.ListResourceServersResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class ListResourceServersResultJsonUnmarshaller implements Unmarshaller<ListResourceServersResult, JsonUnmarshallerContext> {
    private static ListResourceServersResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListResourceServersResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListResourceServersResult listResourceServersResult = new ListResourceServersResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("ResourceServers")) {
                listResourceServersResult.setResourceServers(new ListUnmarshaller(ResourceServerTypeJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("NextToken")) {
                listResourceServersResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listResourceServersResult;
    }

    public static ListResourceServersResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListResourceServersResultJsonUnmarshaller();
        }
        return instance;
    }
}
