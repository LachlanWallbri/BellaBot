package com.amazonaws.services.cognitoidentity.model.transform;

import com.amazonaws.services.cognitoidentity.model.IdentityDescription;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
class IdentityDescriptionJsonMarshaller {
    private static IdentityDescriptionJsonMarshaller instance;

    IdentityDescriptionJsonMarshaller() {
    }

    public void marshall(IdentityDescription identityDescription, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (identityDescription.getIdentityId() != null) {
            String identityId = identityDescription.getIdentityId();
            awsJsonWriter.name("IdentityId");
            awsJsonWriter.value(identityId);
        }
        if (identityDescription.getLogins() != null) {
            List<String> logins = identityDescription.getLogins();
            awsJsonWriter.name("Logins");
            awsJsonWriter.beginArray();
            for (String str : logins) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        if (identityDescription.getCreationDate() != null) {
            Date creationDate = identityDescription.getCreationDate();
            awsJsonWriter.name("CreationDate");
            awsJsonWriter.value(creationDate);
        }
        if (identityDescription.getLastModifiedDate() != null) {
            Date lastModifiedDate = identityDescription.getLastModifiedDate();
            awsJsonWriter.name("LastModifiedDate");
            awsJsonWriter.value(lastModifiedDate);
        }
        awsJsonWriter.endObject();
    }

    public static IdentityDescriptionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new IdentityDescriptionJsonMarshaller();
        }
        return instance;
    }
}
