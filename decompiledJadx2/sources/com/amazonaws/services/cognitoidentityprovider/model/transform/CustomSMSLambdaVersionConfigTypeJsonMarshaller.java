package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.CustomSMSLambdaVersionConfigType;
import com.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class CustomSMSLambdaVersionConfigTypeJsonMarshaller {
    private static CustomSMSLambdaVersionConfigTypeJsonMarshaller instance;

    CustomSMSLambdaVersionConfigTypeJsonMarshaller() {
    }

    public void marshall(CustomSMSLambdaVersionConfigType customSMSLambdaVersionConfigType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (customSMSLambdaVersionConfigType.getLambdaVersion() != null) {
            String lambdaVersion = customSMSLambdaVersionConfigType.getLambdaVersion();
            awsJsonWriter.name("LambdaVersion");
            awsJsonWriter.value(lambdaVersion);
        }
        if (customSMSLambdaVersionConfigType.getLambdaArn() != null) {
            String lambdaArn = customSMSLambdaVersionConfigType.getLambdaArn();
            awsJsonWriter.name("LambdaArn");
            awsJsonWriter.value(lambdaArn);
        }
        awsJsonWriter.endObject();
    }

    public static CustomSMSLambdaVersionConfigTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new CustomSMSLambdaVersionConfigTypeJsonMarshaller();
        }
        return instance;
    }
}
