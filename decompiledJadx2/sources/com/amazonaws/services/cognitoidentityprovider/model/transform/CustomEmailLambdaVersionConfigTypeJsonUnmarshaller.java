package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.CustomEmailLambdaVersionConfigType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class CustomEmailLambdaVersionConfigTypeJsonUnmarshaller implements Unmarshaller<CustomEmailLambdaVersionConfigType, JsonUnmarshallerContext> {
    private static CustomEmailLambdaVersionConfigTypeJsonUnmarshaller instance;

    CustomEmailLambdaVersionConfigTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CustomEmailLambdaVersionConfigType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        CustomEmailLambdaVersionConfigType customEmailLambdaVersionConfigType = new CustomEmailLambdaVersionConfigType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("LambdaVersion")) {
                customEmailLambdaVersionConfigType.setLambdaVersion(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("LambdaArn")) {
                customEmailLambdaVersionConfigType.setLambdaArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return customEmailLambdaVersionConfigType;
    }

    public static CustomEmailLambdaVersionConfigTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CustomEmailLambdaVersionConfigTypeJsonUnmarshaller();
        }
        return instance;
    }
}
