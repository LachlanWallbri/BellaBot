package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.GetUserResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class GetUserResultJsonUnmarshaller implements Unmarshaller<GetUserResult, JsonUnmarshallerContext> {
    private static GetUserResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public GetUserResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetUserResult getUserResult = new GetUserResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("Username")) {
                getUserResult.setUsername(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("UserAttributes")) {
                getUserResult.setUserAttributes(new ListUnmarshaller(AttributeTypeJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("MFAOptions")) {
                getUserResult.setMFAOptions(new ListUnmarshaller(MFAOptionTypeJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("PreferredMfaSetting")) {
                getUserResult.setPreferredMfaSetting(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("UserMFASettingList")) {
                getUserResult.setUserMFASettingList(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getUserResult;
    }

    public static GetUserResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetUserResultJsonUnmarshaller();
        }
        return instance;
    }
}
