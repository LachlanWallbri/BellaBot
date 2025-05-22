package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.amazonaws.services.cognitoidentityprovider.model.AccountRecoverySettingType;
import com.amazonaws.services.cognitoidentityprovider.model.AdminCreateUserConfigType;
import com.amazonaws.services.cognitoidentityprovider.model.DeviceConfigurationType;
import com.amazonaws.services.cognitoidentityprovider.model.EmailConfigurationType;
import com.amazonaws.services.cognitoidentityprovider.model.LambdaConfigType;
import com.amazonaws.services.cognitoidentityprovider.model.SchemaAttributeType;
import com.amazonaws.services.cognitoidentityprovider.model.SmsConfigurationType;
import com.amazonaws.services.cognitoidentityprovider.model.UserPoolAddOnsType;
import com.amazonaws.services.cognitoidentityprovider.model.UserPoolPolicyType;
import com.amazonaws.services.cognitoidentityprovider.model.UserPoolType;
import com.amazonaws.services.cognitoidentityprovider.model.UsernameConfigurationType;
import com.amazonaws.services.cognitoidentityprovider.model.VerificationMessageTemplateType;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
class UserPoolTypeJsonMarshaller {
    private static UserPoolTypeJsonMarshaller instance;

    UserPoolTypeJsonMarshaller() {
    }

    public void marshall(UserPoolType userPoolType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (userPoolType.getId() != null) {
            String id = userPoolType.getId();
            awsJsonWriter.name(JsonDocumentFields.POLICY_ID);
            awsJsonWriter.value(id);
        }
        if (userPoolType.getName() != null) {
            String name = userPoolType.getName();
            awsJsonWriter.name("Name");
            awsJsonWriter.value(name);
        }
        if (userPoolType.getPolicies() != null) {
            UserPoolPolicyType policies = userPoolType.getPolicies();
            awsJsonWriter.name("Policies");
            UserPoolPolicyTypeJsonMarshaller.getInstance().marshall(policies, awsJsonWriter);
        }
        if (userPoolType.getLambdaConfig() != null) {
            LambdaConfigType lambdaConfig = userPoolType.getLambdaConfig();
            awsJsonWriter.name("LambdaConfig");
            LambdaConfigTypeJsonMarshaller.getInstance().marshall(lambdaConfig, awsJsonWriter);
        }
        if (userPoolType.getStatus() != null) {
            String status = userPoolType.getStatus();
            awsJsonWriter.name("Status");
            awsJsonWriter.value(status);
        }
        if (userPoolType.getLastModifiedDate() != null) {
            Date lastModifiedDate = userPoolType.getLastModifiedDate();
            awsJsonWriter.name("LastModifiedDate");
            awsJsonWriter.value(lastModifiedDate);
        }
        if (userPoolType.getCreationDate() != null) {
            Date creationDate = userPoolType.getCreationDate();
            awsJsonWriter.name("CreationDate");
            awsJsonWriter.value(creationDate);
        }
        if (userPoolType.getSchemaAttributes() != null) {
            List<SchemaAttributeType> schemaAttributes = userPoolType.getSchemaAttributes();
            awsJsonWriter.name("SchemaAttributes");
            awsJsonWriter.beginArray();
            for (SchemaAttributeType schemaAttributeType : schemaAttributes) {
                if (schemaAttributeType != null) {
                    SchemaAttributeTypeJsonMarshaller.getInstance().marshall(schemaAttributeType, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        if (userPoolType.getAutoVerifiedAttributes() != null) {
            List<String> autoVerifiedAttributes = userPoolType.getAutoVerifiedAttributes();
            awsJsonWriter.name("AutoVerifiedAttributes");
            awsJsonWriter.beginArray();
            for (String str : autoVerifiedAttributes) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        if (userPoolType.getAliasAttributes() != null) {
            List<String> aliasAttributes = userPoolType.getAliasAttributes();
            awsJsonWriter.name("AliasAttributes");
            awsJsonWriter.beginArray();
            for (String str2 : aliasAttributes) {
                if (str2 != null) {
                    awsJsonWriter.value(str2);
                }
            }
            awsJsonWriter.endArray();
        }
        if (userPoolType.getUsernameAttributes() != null) {
            List<String> usernameAttributes = userPoolType.getUsernameAttributes();
            awsJsonWriter.name("UsernameAttributes");
            awsJsonWriter.beginArray();
            for (String str3 : usernameAttributes) {
                if (str3 != null) {
                    awsJsonWriter.value(str3);
                }
            }
            awsJsonWriter.endArray();
        }
        if (userPoolType.getSmsVerificationMessage() != null) {
            String smsVerificationMessage = userPoolType.getSmsVerificationMessage();
            awsJsonWriter.name("SmsVerificationMessage");
            awsJsonWriter.value(smsVerificationMessage);
        }
        if (userPoolType.getEmailVerificationMessage() != null) {
            String emailVerificationMessage = userPoolType.getEmailVerificationMessage();
            awsJsonWriter.name("EmailVerificationMessage");
            awsJsonWriter.value(emailVerificationMessage);
        }
        if (userPoolType.getEmailVerificationSubject() != null) {
            String emailVerificationSubject = userPoolType.getEmailVerificationSubject();
            awsJsonWriter.name("EmailVerificationSubject");
            awsJsonWriter.value(emailVerificationSubject);
        }
        if (userPoolType.getVerificationMessageTemplate() != null) {
            VerificationMessageTemplateType verificationMessageTemplate = userPoolType.getVerificationMessageTemplate();
            awsJsonWriter.name("VerificationMessageTemplate");
            VerificationMessageTemplateTypeJsonMarshaller.getInstance().marshall(verificationMessageTemplate, awsJsonWriter);
        }
        if (userPoolType.getSmsAuthenticationMessage() != null) {
            String smsAuthenticationMessage = userPoolType.getSmsAuthenticationMessage();
            awsJsonWriter.name("SmsAuthenticationMessage");
            awsJsonWriter.value(smsAuthenticationMessage);
        }
        if (userPoolType.getMfaConfiguration() != null) {
            String mfaConfiguration = userPoolType.getMfaConfiguration();
            awsJsonWriter.name("MfaConfiguration");
            awsJsonWriter.value(mfaConfiguration);
        }
        if (userPoolType.getDeviceConfiguration() != null) {
            DeviceConfigurationType deviceConfiguration = userPoolType.getDeviceConfiguration();
            awsJsonWriter.name("DeviceConfiguration");
            DeviceConfigurationTypeJsonMarshaller.getInstance().marshall(deviceConfiguration, awsJsonWriter);
        }
        if (userPoolType.getEstimatedNumberOfUsers() != null) {
            Integer estimatedNumberOfUsers = userPoolType.getEstimatedNumberOfUsers();
            awsJsonWriter.name("EstimatedNumberOfUsers");
            awsJsonWriter.value(estimatedNumberOfUsers);
        }
        if (userPoolType.getEmailConfiguration() != null) {
            EmailConfigurationType emailConfiguration = userPoolType.getEmailConfiguration();
            awsJsonWriter.name("EmailConfiguration");
            EmailConfigurationTypeJsonMarshaller.getInstance().marshall(emailConfiguration, awsJsonWriter);
        }
        if (userPoolType.getSmsConfiguration() != null) {
            SmsConfigurationType smsConfiguration = userPoolType.getSmsConfiguration();
            awsJsonWriter.name("SmsConfiguration");
            SmsConfigurationTypeJsonMarshaller.getInstance().marshall(smsConfiguration, awsJsonWriter);
        }
        if (userPoolType.getUserPoolTags() != null) {
            Map<String, String> userPoolTags = userPoolType.getUserPoolTags();
            awsJsonWriter.name("UserPoolTags");
            awsJsonWriter.beginObject();
            for (Map.Entry<String, String> entry : userPoolTags.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    awsJsonWriter.name(entry.getKey());
                    awsJsonWriter.value(value);
                }
            }
            awsJsonWriter.endObject();
        }
        if (userPoolType.getSmsConfigurationFailure() != null) {
            String smsConfigurationFailure = userPoolType.getSmsConfigurationFailure();
            awsJsonWriter.name("SmsConfigurationFailure");
            awsJsonWriter.value(smsConfigurationFailure);
        }
        if (userPoolType.getEmailConfigurationFailure() != null) {
            String emailConfigurationFailure = userPoolType.getEmailConfigurationFailure();
            awsJsonWriter.name("EmailConfigurationFailure");
            awsJsonWriter.value(emailConfigurationFailure);
        }
        if (userPoolType.getDomain() != null) {
            String domain = userPoolType.getDomain();
            awsJsonWriter.name("Domain");
            awsJsonWriter.value(domain);
        }
        if (userPoolType.getCustomDomain() != null) {
            String customDomain = userPoolType.getCustomDomain();
            awsJsonWriter.name("CustomDomain");
            awsJsonWriter.value(customDomain);
        }
        if (userPoolType.getAdminCreateUserConfig() != null) {
            AdminCreateUserConfigType adminCreateUserConfig = userPoolType.getAdminCreateUserConfig();
            awsJsonWriter.name("AdminCreateUserConfig");
            AdminCreateUserConfigTypeJsonMarshaller.getInstance().marshall(adminCreateUserConfig, awsJsonWriter);
        }
        if (userPoolType.getUserPoolAddOns() != null) {
            UserPoolAddOnsType userPoolAddOns = userPoolType.getUserPoolAddOns();
            awsJsonWriter.name("UserPoolAddOns");
            UserPoolAddOnsTypeJsonMarshaller.getInstance().marshall(userPoolAddOns, awsJsonWriter);
        }
        if (userPoolType.getUsernameConfiguration() != null) {
            UsernameConfigurationType usernameConfiguration = userPoolType.getUsernameConfiguration();
            awsJsonWriter.name("UsernameConfiguration");
            UsernameConfigurationTypeJsonMarshaller.getInstance().marshall(usernameConfiguration, awsJsonWriter);
        }
        if (userPoolType.getArn() != null) {
            String arn = userPoolType.getArn();
            awsJsonWriter.name("Arn");
            awsJsonWriter.value(arn);
        }
        if (userPoolType.getAccountRecoverySetting() != null) {
            AccountRecoverySettingType accountRecoverySetting = userPoolType.getAccountRecoverySetting();
            awsJsonWriter.name("AccountRecoverySetting");
            AccountRecoverySettingTypeJsonMarshaller.getInstance().marshall(accountRecoverySetting, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static UserPoolTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new UserPoolTypeJsonMarshaller();
        }
        return instance;
    }
}
