package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.ChallengeResponseType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class ChallengeResponseTypeJsonUnmarshaller implements Unmarshaller<ChallengeResponseType, JsonUnmarshallerContext> {
    private static ChallengeResponseTypeJsonUnmarshaller instance;

    ChallengeResponseTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ChallengeResponseType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ChallengeResponseType challengeResponseType = new ChallengeResponseType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("ChallengeName")) {
                challengeResponseType.setChallengeName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ChallengeResponse")) {
                challengeResponseType.setChallengeResponse(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return challengeResponseType;
    }

    public static ChallengeResponseTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ChallengeResponseTypeJsonUnmarshaller();
        }
        return instance;
    }
}
