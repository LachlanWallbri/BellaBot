package com.amazonaws.services.kms.model.transform;

import com.amazonaws.services.kms.model.AliasListEntry;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class AliasListEntryJsonUnmarshaller implements Unmarshaller<AliasListEntry, JsonUnmarshallerContext> {
    private static AliasListEntryJsonUnmarshaller instance;

    AliasListEntryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AliasListEntry unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AliasListEntry aliasListEntry = new AliasListEntry();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("AliasName")) {
                aliasListEntry.setAliasName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("AliasArn")) {
                aliasListEntry.setAliasArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("TargetKeyId")) {
                aliasListEntry.setTargetKeyId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("CreationDate")) {
                aliasListEntry.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("LastUpdatedDate")) {
                aliasListEntry.setLastUpdatedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return aliasListEntry;
    }

    public static AliasListEntryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AliasListEntryJsonUnmarshaller();
        }
        return instance;
    }
}
