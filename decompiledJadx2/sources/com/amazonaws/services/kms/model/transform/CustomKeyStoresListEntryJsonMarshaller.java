package com.amazonaws.services.kms.model.transform;

import com.amazonaws.services.kms.model.CustomKeyStoresListEntry;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* loaded from: classes.dex */
class CustomKeyStoresListEntryJsonMarshaller {
    private static CustomKeyStoresListEntryJsonMarshaller instance;

    CustomKeyStoresListEntryJsonMarshaller() {
    }

    public void marshall(CustomKeyStoresListEntry customKeyStoresListEntry, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (customKeyStoresListEntry.getCustomKeyStoreId() != null) {
            String customKeyStoreId = customKeyStoresListEntry.getCustomKeyStoreId();
            awsJsonWriter.name("CustomKeyStoreId");
            awsJsonWriter.value(customKeyStoreId);
        }
        if (customKeyStoresListEntry.getCustomKeyStoreName() != null) {
            String customKeyStoreName = customKeyStoresListEntry.getCustomKeyStoreName();
            awsJsonWriter.name("CustomKeyStoreName");
            awsJsonWriter.value(customKeyStoreName);
        }
        if (customKeyStoresListEntry.getCloudHsmClusterId() != null) {
            String cloudHsmClusterId = customKeyStoresListEntry.getCloudHsmClusterId();
            awsJsonWriter.name("CloudHsmClusterId");
            awsJsonWriter.value(cloudHsmClusterId);
        }
        if (customKeyStoresListEntry.getTrustAnchorCertificate() != null) {
            String trustAnchorCertificate = customKeyStoresListEntry.getTrustAnchorCertificate();
            awsJsonWriter.name("TrustAnchorCertificate");
            awsJsonWriter.value(trustAnchorCertificate);
        }
        if (customKeyStoresListEntry.getConnectionState() != null) {
            String connectionState = customKeyStoresListEntry.getConnectionState();
            awsJsonWriter.name("ConnectionState");
            awsJsonWriter.value(connectionState);
        }
        if (customKeyStoresListEntry.getConnectionErrorCode() != null) {
            String connectionErrorCode = customKeyStoresListEntry.getConnectionErrorCode();
            awsJsonWriter.name("ConnectionErrorCode");
            awsJsonWriter.value(connectionErrorCode);
        }
        if (customKeyStoresListEntry.getCreationDate() != null) {
            Date creationDate = customKeyStoresListEntry.getCreationDate();
            awsJsonWriter.name("CreationDate");
            awsJsonWriter.value(creationDate);
        }
        awsJsonWriter.endObject();
    }

    public static CustomKeyStoresListEntryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new CustomKeyStoresListEntryJsonMarshaller();
        }
        return instance;
    }
}
