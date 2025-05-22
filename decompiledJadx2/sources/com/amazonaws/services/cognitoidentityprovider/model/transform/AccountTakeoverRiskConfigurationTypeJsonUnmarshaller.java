package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.AccountTakeoverRiskConfigurationType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class AccountTakeoverRiskConfigurationTypeJsonUnmarshaller implements Unmarshaller<AccountTakeoverRiskConfigurationType, JsonUnmarshallerContext> {
    private static AccountTakeoverRiskConfigurationTypeJsonUnmarshaller instance;

    AccountTakeoverRiskConfigurationTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AccountTakeoverRiskConfigurationType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AccountTakeoverRiskConfigurationType accountTakeoverRiskConfigurationType = new AccountTakeoverRiskConfigurationType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("NotifyConfiguration")) {
                accountTakeoverRiskConfigurationType.setNotifyConfiguration(NotifyConfigurationTypeJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("Actions")) {
                accountTakeoverRiskConfigurationType.setActions(AccountTakeoverActionsTypeJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return accountTakeoverRiskConfigurationType;
    }

    public static AccountTakeoverRiskConfigurationTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AccountTakeoverRiskConfigurationTypeJsonUnmarshaller();
        }
        return instance;
    }
}
