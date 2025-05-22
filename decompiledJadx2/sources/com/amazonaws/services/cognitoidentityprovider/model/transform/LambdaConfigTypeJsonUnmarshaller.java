package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.LambdaConfigType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class LambdaConfigTypeJsonUnmarshaller implements Unmarshaller<LambdaConfigType, JsonUnmarshallerContext> {
    private static LambdaConfigTypeJsonUnmarshaller instance;

    LambdaConfigTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public LambdaConfigType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        LambdaConfigType lambdaConfigType = new LambdaConfigType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("PreSignUp")) {
                lambdaConfigType.setPreSignUp(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("CustomMessage")) {
                lambdaConfigType.setCustomMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("PostConfirmation")) {
                lambdaConfigType.setPostConfirmation(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("PreAuthentication")) {
                lambdaConfigType.setPreAuthentication(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("PostAuthentication")) {
                lambdaConfigType.setPostAuthentication(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("DefineAuthChallenge")) {
                lambdaConfigType.setDefineAuthChallenge(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("CreateAuthChallenge")) {
                lambdaConfigType.setCreateAuthChallenge(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("VerifyAuthChallengeResponse")) {
                lambdaConfigType.setVerifyAuthChallengeResponse(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("PreTokenGeneration")) {
                lambdaConfigType.setPreTokenGeneration(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("UserMigration")) {
                lambdaConfigType.setUserMigration(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("CustomSMSSender")) {
                lambdaConfigType.setCustomSMSSender(CustomSMSLambdaVersionConfigTypeJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("CustomEmailSender")) {
                lambdaConfigType.setCustomEmailSender(CustomEmailLambdaVersionConfigTypeJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("KMSKeyID")) {
                lambdaConfigType.setKMSKeyID(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return lambdaConfigType;
    }

    public static LambdaConfigTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new LambdaConfigTypeJsonUnmarshaller();
        }
        return instance;
    }
}
