package com.amazonaws.services.kms.model.transform;

import com.amazonaws.services.kms.model.KeyListEntry;
import com.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class KeyListEntryJsonMarshaller {
    private static KeyListEntryJsonMarshaller instance;

    KeyListEntryJsonMarshaller() {
    }

    public void marshall(KeyListEntry keyListEntry, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (keyListEntry.getKeyId() != null) {
            String keyId = keyListEntry.getKeyId();
            awsJsonWriter.name("KeyId");
            awsJsonWriter.value(keyId);
        }
        if (keyListEntry.getKeyArn() != null) {
            String keyArn = keyListEntry.getKeyArn();
            awsJsonWriter.name("KeyArn");
            awsJsonWriter.value(keyArn);
        }
        awsJsonWriter.endObject();
    }

    public static KeyListEntryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new KeyListEntryJsonMarshaller();
        }
        return instance;
    }
}
