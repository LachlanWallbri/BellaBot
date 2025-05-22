package io.minio.messages;

import com.fasterxml.jackson.core.JsonFactory;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = JsonFactory.FORMAT_NAME_JSON)
/* loaded from: classes7.dex */
public class JsonInputSerialization {

    @Element(name = "Type", required = false)
    private JsonType type;

    public JsonInputSerialization(JsonType jsonType) {
        this.type = jsonType;
    }
}
