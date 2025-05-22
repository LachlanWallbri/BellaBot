package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.AdminGetUserResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class AdminGetUserResultJsonUnmarshaller implements Unmarshaller<AdminGetUserResult, JsonUnmarshallerContext> {
    private static AdminGetUserResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public AdminGetUserResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AdminGetUserResult adminGetUserResult = new AdminGetUserResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("Username")) {
                adminGetUserResult.setUsername(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("UserAttributes")) {
                adminGetUserResult.setUserAttributes(new ListUnmarshaller(AttributeTypeJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("UserCreateDate")) {
                adminGetUserResult.setUserCreateDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("UserLastModifiedDate")) {
                adminGetUserResult.setUserLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("Enabled")) {
                adminGetUserResult.setEnabled(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("UserStatus")) {
                adminGetUserResult.setUserStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("MFAOptions")) {
                adminGetUserResult.setMFAOptions(new ListUnmarshaller(MFAOptionTypeJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("PreferredMfaSetting")) {
                adminGetUserResult.setPreferredMfaSetting(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("UserMFASettingList")) {
                adminGetUserResult.setUserMFASettingList(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return adminGetUserResult;
    }

    public static AdminGetUserResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AdminGetUserResultJsonUnmarshaller();
        }
        return instance;
    }
}
