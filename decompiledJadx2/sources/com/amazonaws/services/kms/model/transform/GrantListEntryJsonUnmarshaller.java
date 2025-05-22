package com.amazonaws.services.kms.model.transform;

import androidx.constraintlayout.widget.Constraints;
import com.amazonaws.services.kms.model.GrantListEntry;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class GrantListEntryJsonUnmarshaller implements Unmarshaller<GrantListEntry, JsonUnmarshallerContext> {
    private static GrantListEntryJsonUnmarshaller instance;

    GrantListEntryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public GrantListEntry unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        GrantListEntry grantListEntry = new GrantListEntry();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("KeyId")) {
                grantListEntry.setKeyId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("GrantId")) {
                grantListEntry.setGrantId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("Name")) {
                grantListEntry.setName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("CreationDate")) {
                grantListEntry.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("GranteePrincipal")) {
                grantListEntry.setGranteePrincipal(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("RetiringPrincipal")) {
                grantListEntry.setRetiringPrincipal(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("IssuingAccount")) {
                grantListEntry.setIssuingAccount(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("Operations")) {
                grantListEntry.setOperations(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals(Constraints.TAG)) {
                grantListEntry.setConstraints(GrantConstraintsJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return grantListEntry;
    }

    public static GrantListEntryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GrantListEntryJsonUnmarshaller();
        }
        return instance;
    }
}
