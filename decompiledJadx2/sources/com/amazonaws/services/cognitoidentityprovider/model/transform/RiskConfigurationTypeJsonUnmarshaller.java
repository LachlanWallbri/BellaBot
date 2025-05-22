package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.RiskConfigurationType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class RiskConfigurationTypeJsonUnmarshaller implements Unmarshaller<RiskConfigurationType, JsonUnmarshallerContext> {
    private static RiskConfigurationTypeJsonUnmarshaller instance;

    RiskConfigurationTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public RiskConfigurationType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        RiskConfigurationType riskConfigurationType = new RiskConfigurationType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("UserPoolId")) {
                riskConfigurationType.setUserPoolId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ClientId")) {
                riskConfigurationType.setClientId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("CompromisedCredentialsRiskConfiguration")) {
                riskConfigurationType.setCompromisedCredentialsRiskConfiguration(CompromisedCredentialsRiskConfigurationTypeJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("AccountTakeoverRiskConfiguration")) {
                riskConfigurationType.setAccountTakeoverRiskConfiguration(AccountTakeoverRiskConfigurationTypeJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("RiskExceptionConfiguration")) {
                riskConfigurationType.setRiskExceptionConfiguration(RiskExceptionConfigurationTypeJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("LastModifiedDate")) {
                riskConfigurationType.setLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return riskConfigurationType;
    }

    public static RiskConfigurationTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RiskConfigurationTypeJsonUnmarshaller();
        }
        return instance;
    }
}
