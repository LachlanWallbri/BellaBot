package com.amazonaws.services.kms.model.transform;

import com.amazonaws.services.kms.model.AliasListEntry;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* loaded from: classes.dex */
class AliasListEntryJsonMarshaller {
    private static AliasListEntryJsonMarshaller instance;

    AliasListEntryJsonMarshaller() {
    }

    public void marshall(AliasListEntry aliasListEntry, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (aliasListEntry.getAliasName() != null) {
            String aliasName = aliasListEntry.getAliasName();
            awsJsonWriter.name("AliasName");
            awsJsonWriter.value(aliasName);
        }
        if (aliasListEntry.getAliasArn() != null) {
            String aliasArn = aliasListEntry.getAliasArn();
            awsJsonWriter.name("AliasArn");
            awsJsonWriter.value(aliasArn);
        }
        if (aliasListEntry.getTargetKeyId() != null) {
            String targetKeyId = aliasListEntry.getTargetKeyId();
            awsJsonWriter.name("TargetKeyId");
            awsJsonWriter.value(targetKeyId);
        }
        if (aliasListEntry.getCreationDate() != null) {
            Date creationDate = aliasListEntry.getCreationDate();
            awsJsonWriter.name("CreationDate");
            awsJsonWriter.value(creationDate);
        }
        if (aliasListEntry.getLastUpdatedDate() != null) {
            Date lastUpdatedDate = aliasListEntry.getLastUpdatedDate();
            awsJsonWriter.name("LastUpdatedDate");
            awsJsonWriter.value(lastUpdatedDate);
        }
        awsJsonWriter.endObject();
    }

    public static AliasListEntryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AliasListEntryJsonMarshaller();
        }
        return instance;
    }
}
