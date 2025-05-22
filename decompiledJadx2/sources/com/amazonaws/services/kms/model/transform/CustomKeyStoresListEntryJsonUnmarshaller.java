package com.amazonaws.services.kms.model.transform;

import com.amazonaws.services.kms.model.CustomKeyStoresListEntry;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class CustomKeyStoresListEntryJsonUnmarshaller implements Unmarshaller<CustomKeyStoresListEntry, JsonUnmarshallerContext> {
    private static CustomKeyStoresListEntryJsonUnmarshaller instance;

    CustomKeyStoresListEntryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CustomKeyStoresListEntry unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        CustomKeyStoresListEntry customKeyStoresListEntry = new CustomKeyStoresListEntry();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("CustomKeyStoreId")) {
                customKeyStoresListEntry.setCustomKeyStoreId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("CustomKeyStoreName")) {
                customKeyStoresListEntry.setCustomKeyStoreName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("CloudHsmClusterId")) {
                customKeyStoresListEntry.setCloudHsmClusterId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("TrustAnchorCertificate")) {
                customKeyStoresListEntry.setTrustAnchorCertificate(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ConnectionState")) {
                customKeyStoresListEntry.setConnectionState(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("ConnectionErrorCode")) {
                customKeyStoresListEntry.setConnectionErrorCode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("CreationDate")) {
                customKeyStoresListEntry.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return customKeyStoresListEntry;
    }

    public static CustomKeyStoresListEntryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CustomKeyStoresListEntryJsonUnmarshaller();
        }
        return instance;
    }
}
