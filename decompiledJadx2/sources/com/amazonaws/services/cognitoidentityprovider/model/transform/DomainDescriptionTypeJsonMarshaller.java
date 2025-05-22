package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.amazonaws.services.cognitoidentityprovider.model.CustomDomainConfigType;
import com.amazonaws.services.cognitoidentityprovider.model.DomainDescriptionType;
import com.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class DomainDescriptionTypeJsonMarshaller {
    private static DomainDescriptionTypeJsonMarshaller instance;

    DomainDescriptionTypeJsonMarshaller() {
    }

    public void marshall(DomainDescriptionType domainDescriptionType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (domainDescriptionType.getUserPoolId() != null) {
            String userPoolId = domainDescriptionType.getUserPoolId();
            awsJsonWriter.name("UserPoolId");
            awsJsonWriter.value(userPoolId);
        }
        if (domainDescriptionType.getAWSAccountId() != null) {
            String aWSAccountId = domainDescriptionType.getAWSAccountId();
            awsJsonWriter.name("AWSAccountId");
            awsJsonWriter.value(aWSAccountId);
        }
        if (domainDescriptionType.getDomain() != null) {
            String domain = domainDescriptionType.getDomain();
            awsJsonWriter.name("Domain");
            awsJsonWriter.value(domain);
        }
        if (domainDescriptionType.getS3Bucket() != null) {
            String s3Bucket = domainDescriptionType.getS3Bucket();
            awsJsonWriter.name("S3Bucket");
            awsJsonWriter.value(s3Bucket);
        }
        if (domainDescriptionType.getCloudFrontDistribution() != null) {
            String cloudFrontDistribution = domainDescriptionType.getCloudFrontDistribution();
            awsJsonWriter.name("CloudFrontDistribution");
            awsJsonWriter.value(cloudFrontDistribution);
        }
        if (domainDescriptionType.getVersion() != null) {
            String version = domainDescriptionType.getVersion();
            awsJsonWriter.name(JsonDocumentFields.VERSION);
            awsJsonWriter.value(version);
        }
        if (domainDescriptionType.getStatus() != null) {
            String status = domainDescriptionType.getStatus();
            awsJsonWriter.name("Status");
            awsJsonWriter.value(status);
        }
        if (domainDescriptionType.getCustomDomainConfig() != null) {
            CustomDomainConfigType customDomainConfig = domainDescriptionType.getCustomDomainConfig();
            awsJsonWriter.name("CustomDomainConfig");
            CustomDomainConfigTypeJsonMarshaller.getInstance().marshall(customDomainConfig, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static DomainDescriptionTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new DomainDescriptionTypeJsonMarshaller();
        }
        return instance;
    }
}
