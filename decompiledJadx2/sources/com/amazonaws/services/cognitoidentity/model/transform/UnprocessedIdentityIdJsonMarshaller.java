package com.amazonaws.services.cognitoidentity.model.transform;

import com.amazonaws.services.cognitoidentity.model.UnprocessedIdentityId;
import com.amazonaws.util.json.AwsJsonWriter;

/* loaded from: classes.dex */
class UnprocessedIdentityIdJsonMarshaller {
    private static UnprocessedIdentityIdJsonMarshaller instance;

    UnprocessedIdentityIdJsonMarshaller() {
    }

    public void marshall(UnprocessedIdentityId unprocessedIdentityId, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (unprocessedIdentityId.getIdentityId() != null) {
            String identityId = unprocessedIdentityId.getIdentityId();
            awsJsonWriter.name("IdentityId");
            awsJsonWriter.value(identityId);
        }
        if (unprocessedIdentityId.getErrorCode() != null) {
            String errorCode = unprocessedIdentityId.getErrorCode();
            awsJsonWriter.name("ErrorCode");
            awsJsonWriter.value(errorCode);
        }
        awsJsonWriter.endObject();
    }

    public static UnprocessedIdentityIdJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new UnprocessedIdentityIdJsonMarshaller();
        }
        return instance;
    }
}
