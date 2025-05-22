package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.services.cognitoidentityprovider.model.CodeDeliveryDetailsType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import org.apache.http.HttpHeaders;

/* loaded from: classes.dex */
class CodeDeliveryDetailsTypeJsonUnmarshaller implements Unmarshaller<CodeDeliveryDetailsType, JsonUnmarshallerContext> {
    private static CodeDeliveryDetailsTypeJsonUnmarshaller instance;

    CodeDeliveryDetailsTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CodeDeliveryDetailsType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        CodeDeliveryDetailsType codeDeliveryDetailsType = new CodeDeliveryDetailsType();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals(HttpHeaders.DESTINATION)) {
                codeDeliveryDetailsType.setDestination(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("DeliveryMedium")) {
                codeDeliveryDetailsType.setDeliveryMedium(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("AttributeName")) {
                codeDeliveryDetailsType.setAttributeName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return codeDeliveryDetailsType;
    }

    public static CodeDeliveryDetailsTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CodeDeliveryDetailsTypeJsonUnmarshaller();
        }
        return instance;
    }
}
