package io.minio.messages;

import com.fasterxml.jackson.core.JsonFactory;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = JsonFactory.FORMAT_NAME_JSON)
/* loaded from: classes7.dex */
public class JsonOutputSerialization {

    @Element(name = "RecordDelimiter", required = false)
    private Character recordDelimiter;

    public JsonOutputSerialization(Character ch) {
        this.recordDelimiter = ch;
    }
}
