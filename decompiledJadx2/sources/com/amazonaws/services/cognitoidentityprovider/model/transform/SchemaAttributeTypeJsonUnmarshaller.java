package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.SchemaAttributeType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
class SchemaAttributeTypeJsonUnmarshaller implements Unmarshaller<SchemaAttributeType, JsonUnmarshallerContext> {
    private static SchemaAttributeTypeJsonUnmarshaller instance;

    SchemaAttributeTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public SchemaAttributeType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        SchemaAttributeType schemaAttributeType = new SchemaAttributeType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("Name")) {
                schemaAttributeType.setName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("AttributeDataType")) {
                schemaAttributeType.setAttributeDataType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("DeveloperOnlyAttribute")) {
                schemaAttributeType.setDeveloperOnlyAttribute(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("Mutable")) {
                schemaAttributeType.setMutable(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("Required")) {
                schemaAttributeType.setRequired(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("NumberAttributeConstraints")) {
                schemaAttributeType.setNumberAttributeConstraints(NumberAttributeConstraintsTypeJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("StringAttributeConstraints")) {
                schemaAttributeType.setStringAttributeConstraints(StringAttributeConstraintsTypeJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return schemaAttributeType;
    }

    public static SchemaAttributeTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SchemaAttributeTypeJsonUnmarshaller();
        }
        return instance;
    }
}
