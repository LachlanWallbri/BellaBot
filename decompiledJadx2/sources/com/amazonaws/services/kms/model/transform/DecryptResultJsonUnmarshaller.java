package com.amazonaws.services.kms.model.transform;

import com.amazonaws.services.kms.model.DecryptResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class DecryptResultJsonUnmarshaller implements Unmarshaller<DecryptResult, JsonUnmarshallerContext> {
    private static DecryptResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DecryptResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DecryptResult decryptResult = new DecryptResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("KeyId")) {
                decryptResult.setKeyId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("Plaintext")) {
                decryptResult.setPlaintext(SimpleTypeJsonUnmarshallers.ByteBufferJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("EncryptionAlgorithm")) {
                decryptResult.setEncryptionAlgorithm(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return decryptResult;
    }

    public static DecryptResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DecryptResultJsonUnmarshaller();
        }
        return instance;
    }
}
