package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.PasswordPolicyType;
import com.amazonaws.services.cognitoidentityprovider.model.UserPoolPolicyType;
import com.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class UserPoolPolicyTypeJsonMarshaller {
    private static UserPoolPolicyTypeJsonMarshaller instance;

    UserPoolPolicyTypeJsonMarshaller() {
    }

    public void marshall(UserPoolPolicyType userPoolPolicyType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (userPoolPolicyType.getPasswordPolicy() != null) {
            PasswordPolicyType passwordPolicy = userPoolPolicyType.getPasswordPolicy();
            awsJsonWriter.name("PasswordPolicy");
            PasswordPolicyTypeJsonMarshaller.getInstance().marshall(passwordPolicy, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static UserPoolPolicyTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new UserPoolPolicyTypeJsonMarshaller();
        }
        return instance;
    }
}
