package com.amazonaws.transform;

import com.amazonaws.util.json.AwsJsonReader;
import com.amazonaws.util.json.AwsJsonToken;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class MapUnmarshaller<V> implements Unmarshaller<Map<String, V>, JsonUnmarshallerContext> {
    private final Unmarshaller<V, JsonUnmarshallerContext> valueUnmarshaller;

    public MapUnmarshaller(Unmarshaller<V, JsonUnmarshallerContext> unmarshaller) {
        this.valueUnmarshaller = unmarshaller;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Map<String, V> unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (reader.peek() == AwsJsonToken.VALUE_NULL) {
            reader.skipValue();
            return null;
        }
        HashMap hashMap = new HashMap();
        reader.beginObject();
        while (reader.hasNext()) {
            hashMap.put(reader.nextName(), this.valueUnmarshaller.unmarshall(jsonUnmarshallerContext));
        }
        reader.endObject();
        return hashMap;
    }
}
