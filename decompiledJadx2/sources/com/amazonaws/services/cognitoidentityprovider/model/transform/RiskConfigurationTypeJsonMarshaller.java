package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.AccountTakeoverRiskConfigurationType;
import com.amazonaws.services.cognitoidentityprovider.model.CompromisedCredentialsRiskConfigurationType;
import com.amazonaws.services.cognitoidentityprovider.model.RiskConfigurationType;
import com.amazonaws.services.cognitoidentityprovider.model.RiskExceptionConfigurationType;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* loaded from: classes.dex */
class RiskConfigurationTypeJsonMarshaller {
    private static RiskConfigurationTypeJsonMarshaller instance;

    RiskConfigurationTypeJsonMarshaller() {
    }

    public void marshall(RiskConfigurationType riskConfigurationType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (riskConfigurationType.getUserPoolId() != null) {
            String userPoolId = riskConfigurationType.getUserPoolId();
            awsJsonWriter.name("UserPoolId");
            awsJsonWriter.value(userPoolId);
        }
        if (riskConfigurationType.getClientId() != null) {
            String clientId = riskConfigurationType.getClientId();
            awsJsonWriter.name("ClientId");
            awsJsonWriter.value(clientId);
        }
        if (riskConfigurationType.getCompromisedCredentialsRiskConfiguration() != null) {
            CompromisedCredentialsRiskConfigurationType compromisedCredentialsRiskConfiguration = riskConfigurationType.getCompromisedCredentialsRiskConfiguration();
            awsJsonWriter.name("CompromisedCredentialsRiskConfiguration");
            CompromisedCredentialsRiskConfigurationTypeJsonMarshaller.getInstance().marshall(compromisedCredentialsRiskConfiguration, awsJsonWriter);
        }
        if (riskConfigurationType.getAccountTakeoverRiskConfiguration() != null) {
            AccountTakeoverRiskConfigurationType accountTakeoverRiskConfiguration = riskConfigurationType.getAccountTakeoverRiskConfiguration();
            awsJsonWriter.name("AccountTakeoverRiskConfiguration");
            AccountTakeoverRiskConfigurationTypeJsonMarshaller.getInstance().marshall(accountTakeoverRiskConfiguration, awsJsonWriter);
        }
        if (riskConfigurationType.getRiskExceptionConfiguration() != null) {
            RiskExceptionConfigurationType riskExceptionConfiguration = riskConfigurationType.getRiskExceptionConfiguration();
            awsJsonWriter.name("RiskExceptionConfiguration");
            RiskExceptionConfigurationTypeJsonMarshaller.getInstance().marshall(riskExceptionConfiguration, awsJsonWriter);
        }
        if (riskConfigurationType.getLastModifiedDate() != null) {
            Date lastModifiedDate = riskConfigurationType.getLastModifiedDate();
            awsJsonWriter.name("LastModifiedDate");
            awsJsonWriter.value(lastModifiedDate);
        }
        awsJsonWriter.endObject();
    }

    public static RiskConfigurationTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new RiskConfigurationTypeJsonMarshaller();
        }
        return instance;
    }
}
