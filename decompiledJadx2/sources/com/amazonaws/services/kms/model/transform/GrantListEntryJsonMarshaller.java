package com.amazonaws.services.kms.model.transform;

import androidx.constraintlayout.widget.Constraints;
import com.amazonaws.services.kms.model.GrantConstraints;
import com.amazonaws.services.kms.model.GrantListEntry;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
class GrantListEntryJsonMarshaller {
    private static GrantListEntryJsonMarshaller instance;

    GrantListEntryJsonMarshaller() {
    }

    public void marshall(GrantListEntry grantListEntry, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (grantListEntry.getKeyId() != null) {
            String keyId = grantListEntry.getKeyId();
            awsJsonWriter.name("KeyId");
            awsJsonWriter.value(keyId);
        }
        if (grantListEntry.getGrantId() != null) {
            String grantId = grantListEntry.getGrantId();
            awsJsonWriter.name("GrantId");
            awsJsonWriter.value(grantId);
        }
        if (grantListEntry.getName() != null) {
            String name = grantListEntry.getName();
            awsJsonWriter.name("Name");
            awsJsonWriter.value(name);
        }
        if (grantListEntry.getCreationDate() != null) {
            Date creationDate = grantListEntry.getCreationDate();
            awsJsonWriter.name("CreationDate");
            awsJsonWriter.value(creationDate);
        }
        if (grantListEntry.getGranteePrincipal() != null) {
            String granteePrincipal = grantListEntry.getGranteePrincipal();
            awsJsonWriter.name("GranteePrincipal");
            awsJsonWriter.value(granteePrincipal);
        }
        if (grantListEntry.getRetiringPrincipal() != null) {
            String retiringPrincipal = grantListEntry.getRetiringPrincipal();
            awsJsonWriter.name("RetiringPrincipal");
            awsJsonWriter.value(retiringPrincipal);
        }
        if (grantListEntry.getIssuingAccount() != null) {
            String issuingAccount = grantListEntry.getIssuingAccount();
            awsJsonWriter.name("IssuingAccount");
            awsJsonWriter.value(issuingAccount);
        }
        if (grantListEntry.getOperations() != null) {
            List<String> operations = grantListEntry.getOperations();
            awsJsonWriter.name("Operations");
            awsJsonWriter.beginArray();
            for (String str : operations) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        if (grantListEntry.getConstraints() != null) {
            GrantConstraints constraints = grantListEntry.getConstraints();
            awsJsonWriter.name(Constraints.TAG);
            GrantConstraintsJsonMarshaller.getInstance().marshall(constraints, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static GrantListEntryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new GrantListEntryJsonMarshaller();
        }
        return instance;
    }
}
