package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.UICustomizationType;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* loaded from: classes.dex */
class UICustomizationTypeJsonMarshaller {
    private static UICustomizationTypeJsonMarshaller instance;

    UICustomizationTypeJsonMarshaller() {
    }

    public void marshall(UICustomizationType uICustomizationType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (uICustomizationType.getUserPoolId() != null) {
            String userPoolId = uICustomizationType.getUserPoolId();
            awsJsonWriter.name("UserPoolId");
            awsJsonWriter.value(userPoolId);
        }
        if (uICustomizationType.getClientId() != null) {
            String clientId = uICustomizationType.getClientId();
            awsJsonWriter.name("ClientId");
            awsJsonWriter.value(clientId);
        }
        if (uICustomizationType.getImageUrl() != null) {
            String imageUrl = uICustomizationType.getImageUrl();
            awsJsonWriter.name("ImageUrl");
            awsJsonWriter.value(imageUrl);
        }
        if (uICustomizationType.getCSS() != null) {
            String css = uICustomizationType.getCSS();
            awsJsonWriter.name("CSS");
            awsJsonWriter.value(css);
        }
        if (uICustomizationType.getCSSVersion() != null) {
            String cSSVersion = uICustomizationType.getCSSVersion();
            awsJsonWriter.name("CSSVersion");
            awsJsonWriter.value(cSSVersion);
        }
        if (uICustomizationType.getLastModifiedDate() != null) {
            Date lastModifiedDate = uICustomizationType.getLastModifiedDate();
            awsJsonWriter.name("LastModifiedDate");
            awsJsonWriter.value(lastModifiedDate);
        }
        if (uICustomizationType.getCreationDate() != null) {
            Date creationDate = uICustomizationType.getCreationDate();
            awsJsonWriter.name("CreationDate");
            awsJsonWriter.value(creationDate);
        }
        awsJsonWriter.endObject();
    }

    public static UICustomizationTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new UICustomizationTypeJsonMarshaller();
        }
        return instance;
    }
}
