package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.AttributeType;
import com.amazonaws.services.cognitoidentityprovider.model.MFAOptionType;
import com.amazonaws.services.cognitoidentityprovider.model.UserType;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
class UserTypeJsonMarshaller {
    private static UserTypeJsonMarshaller instance;

    UserTypeJsonMarshaller() {
    }

    public void marshall(UserType userType, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (userType.getUsername() != null) {
            String username = userType.getUsername();
            awsJsonWriter.name("Username");
            awsJsonWriter.value(username);
        }
        if (userType.getAttributes() != null) {
            List<AttributeType> attributes = userType.getAttributes();
            awsJsonWriter.name("Attributes");
            awsJsonWriter.beginArray();
            for (AttributeType attributeType : attributes) {
                if (attributeType != null) {
                    AttributeTypeJsonMarshaller.getInstance().marshall(attributeType, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        if (userType.getUserCreateDate() != null) {
            Date userCreateDate = userType.getUserCreateDate();
            awsJsonWriter.name("UserCreateDate");
            awsJsonWriter.value(userCreateDate);
        }
        if (userType.getUserLastModifiedDate() != null) {
            Date userLastModifiedDate = userType.getUserLastModifiedDate();
            awsJsonWriter.name("UserLastModifiedDate");
            awsJsonWriter.value(userLastModifiedDate);
        }
        if (userType.getEnabled() != null) {
            Boolean enabled = userType.getEnabled();
            awsJsonWriter.name("Enabled");
            awsJsonWriter.value(enabled.booleanValue());
        }
        if (userType.getUserStatus() != null) {
            String userStatus = userType.getUserStatus();
            awsJsonWriter.name("UserStatus");
            awsJsonWriter.value(userStatus);
        }
        if (userType.getMFAOptions() != null) {
            List<MFAOptionType> mFAOptions = userType.getMFAOptions();
            awsJsonWriter.name("MFAOptions");
            awsJsonWriter.beginArray();
            for (MFAOptionType mFAOptionType : mFAOptions) {
                if (mFAOptionType != null) {
                    MFAOptionTypeJsonMarshaller.getInstance().marshall(mFAOptionType, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }

    public static UserTypeJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new UserTypeJsonMarshaller();
        }
        return instance;
    }
}
