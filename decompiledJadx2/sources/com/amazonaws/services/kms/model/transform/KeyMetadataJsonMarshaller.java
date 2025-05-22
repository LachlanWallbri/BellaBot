package com.amazonaws.services.kms.model.transform;

import com.amazonaws.services.kms.model.KeyMetadata;
import com.amazonaws.services.kms.model.MultiRegionConfiguration;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
class KeyMetadataJsonMarshaller {
    private static KeyMetadataJsonMarshaller instance;

    KeyMetadataJsonMarshaller() {
    }

    public void marshall(KeyMetadata keyMetadata, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (keyMetadata.getAWSAccountId() != null) {
            String aWSAccountId = keyMetadata.getAWSAccountId();
            awsJsonWriter.name("AWSAccountId");
            awsJsonWriter.value(aWSAccountId);
        }
        if (keyMetadata.getKeyId() != null) {
            String keyId = keyMetadata.getKeyId();
            awsJsonWriter.name("KeyId");
            awsJsonWriter.value(keyId);
        }
        if (keyMetadata.getArn() != null) {
            String arn = keyMetadata.getArn();
            awsJsonWriter.name("Arn");
            awsJsonWriter.value(arn);
        }
        if (keyMetadata.getCreationDate() != null) {
            Date creationDate = keyMetadata.getCreationDate();
            awsJsonWriter.name("CreationDate");
            awsJsonWriter.value(creationDate);
        }
        if (keyMetadata.getEnabled() != null) {
            Boolean enabled = keyMetadata.getEnabled();
            awsJsonWriter.name("Enabled");
            awsJsonWriter.value(enabled.booleanValue());
        }
        if (keyMetadata.getDescription() != null) {
            String description = keyMetadata.getDescription();
            awsJsonWriter.name("Description");
            awsJsonWriter.value(description);
        }
        if (keyMetadata.getKeyUsage() != null) {
            String keyUsage = keyMetadata.getKeyUsage();
            awsJsonWriter.name("KeyUsage");
            awsJsonWriter.value(keyUsage);
        }
        if (keyMetadata.getKeyState() != null) {
            String keyState = keyMetadata.getKeyState();
            awsJsonWriter.name("KeyState");
            awsJsonWriter.value(keyState);
        }
        if (keyMetadata.getDeletionDate() != null) {
            Date deletionDate = keyMetadata.getDeletionDate();
            awsJsonWriter.name("DeletionDate");
            awsJsonWriter.value(deletionDate);
        }
        if (keyMetadata.getValidTo() != null) {
            Date validTo = keyMetadata.getValidTo();
            awsJsonWriter.name("ValidTo");
            awsJsonWriter.value(validTo);
        }
        if (keyMetadata.getOrigin() != null) {
            String origin = keyMetadata.getOrigin();
            awsJsonWriter.name("Origin");
            awsJsonWriter.value(origin);
        }
        if (keyMetadata.getCustomKeyStoreId() != null) {
            String customKeyStoreId = keyMetadata.getCustomKeyStoreId();
            awsJsonWriter.name("CustomKeyStoreId");
            awsJsonWriter.value(customKeyStoreId);
        }
        if (keyMetadata.getCloudHsmClusterId() != null) {
            String cloudHsmClusterId = keyMetadata.getCloudHsmClusterId();
            awsJsonWriter.name("CloudHsmClusterId");
            awsJsonWriter.value(cloudHsmClusterId);
        }
        if (keyMetadata.getExpirationModel() != null) {
            String expirationModel = keyMetadata.getExpirationModel();
            awsJsonWriter.name("ExpirationModel");
            awsJsonWriter.value(expirationModel);
        }
        if (keyMetadata.getKeyManager() != null) {
            String keyManager = keyMetadata.getKeyManager();
            awsJsonWriter.name("KeyManager");
            awsJsonWriter.value(keyManager);
        }
        if (keyMetadata.getCustomerMasterKeySpec() != null) {
            String customerMasterKeySpec = keyMetadata.getCustomerMasterKeySpec();
            awsJsonWriter.name("CustomerMasterKeySpec");
            awsJsonWriter.value(customerMasterKeySpec);
        }
        if (keyMetadata.getKeySpec() != null) {
            String keySpec = keyMetadata.getKeySpec();
            awsJsonWriter.name("KeySpec");
            awsJsonWriter.value(keySpec);
        }
        if (keyMetadata.getEncryptionAlgorithms() != null) {
            List<String> encryptionAlgorithms = keyMetadata.getEncryptionAlgorithms();
            awsJsonWriter.name("EncryptionAlgorithms");
            awsJsonWriter.beginArray();
            for (String str : encryptionAlgorithms) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        if (keyMetadata.getSigningAlgorithms() != null) {
            List<String> signingAlgorithms = keyMetadata.getSigningAlgorithms();
            awsJsonWriter.name("SigningAlgorithms");
            awsJsonWriter.beginArray();
            for (String str2 : signingAlgorithms) {
                if (str2 != null) {
                    awsJsonWriter.value(str2);
                }
            }
            awsJsonWriter.endArray();
        }
        if (keyMetadata.getMultiRegion() != null) {
            Boolean multiRegion = keyMetadata.getMultiRegion();
            awsJsonWriter.name("MultiRegion");
            awsJsonWriter.value(multiRegion.booleanValue());
        }
        if (keyMetadata.getMultiRegionConfiguration() != null) {
            MultiRegionConfiguration multiRegionConfiguration = keyMetadata.getMultiRegionConfiguration();
            awsJsonWriter.name("MultiRegionConfiguration");
            MultiRegionConfigurationJsonMarshaller.getInstance().marshall(multiRegionConfiguration, awsJsonWriter);
        }
        if (keyMetadata.getPendingDeletionWindowInDays() != null) {
            Integer pendingDeletionWindowInDays = keyMetadata.getPendingDeletionWindowInDays();
            awsJsonWriter.name("PendingDeletionWindowInDays");
            awsJsonWriter.value(pendingDeletionWindowInDays);
        }
        awsJsonWriter.endObject();
    }

    public static KeyMetadataJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new KeyMetadataJsonMarshaller();
        }
        return instance;
    }
}
