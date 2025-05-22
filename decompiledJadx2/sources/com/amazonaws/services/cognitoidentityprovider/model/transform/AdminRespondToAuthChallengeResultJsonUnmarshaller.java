package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class AdminRespondToAuthChallengeResultJsonUnmarshaller implements Unmarshaller<AdminRespondToAuthChallengeResult, JsonUnmarshallerContext> {
    private static AdminRespondToAuthChallengeResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public AdminRespondToAuthChallengeResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AdminRespondToAuthChallengeResult adminRespondToAuthChallengeResult = new AdminRespondToAuthChallengeResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("ChallengeName")) {
                adminRespondToAuthChallengeResult.setChallengeName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("Session")) {
                adminRespondToAuthChallengeResult.setSession(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ChallengeParameters")) {
                adminRespondToAuthChallengeResult.setChallengeParameters(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("AuthenticationResult")) {
                adminRespondToAuthChallengeResult.setAuthenticationResult(AuthenticationResultTypeJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return adminRespondToAuthChallengeResult;
    }

    public static AdminRespondToAuthChallengeResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AdminRespondToAuthChallengeResultJsonUnmarshaller();
        }
        return instance;
    }
}
