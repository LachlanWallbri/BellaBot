package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.CustomEmailLambdaVersionConfigType;
import com.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class CustomEmailLambdaVersionConfigTypeJsonMarshaller {
    private static CustomEmailLambdaVersionConfigTypeJsonMarshaller instance;

    CustomEmailLambdaVersionConfigTypeJsonMarshaller() {
    }

    public void marshall(CustomEmailLambdaVersionConfigType customEmailLambdaVersionConfigType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (customEmailLambdaVersionConfigType.getLambdaVersion() != null) {
            String lambdaVersion = customEmailLambdaVersionConfigType.getLambdaVersion();
            awsJsonWriter.name("LambdaVersion");
            awsJsonWriter.value(lambdaVersion);
        }
        if (customEmailLambdaVersionConfigType.getLambdaArn() != null) {
            String lambdaArn = customEmailLambdaVersionConfigType.getLambdaArn();
            awsJsonWriter.name("LambdaArn");
            awsJsonWriter.value(lambdaArn);
        }
        awsJsonWriter.endObject();
    }

    public static CustomEmailLambdaVersionConfigTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new CustomEmailLambdaVersionConfigTypeJsonMarshaller();
        }
        return instance;
    }
}
