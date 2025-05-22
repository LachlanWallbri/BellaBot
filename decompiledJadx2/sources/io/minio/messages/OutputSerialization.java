package io.minio.messages;

import com.fasterxml.jackson.core.JsonFactory;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "OutputSerialization")
/* loaded from: classes7.dex */
public class OutputSerialization {

    @Element(name = "CSV", required = false)
    private CsvOutputSerialization csv;

    @Element(name = JsonFactory.FORMAT_NAME_JSON, required = false)
    private JsonOutputSerialization json;

    public OutputSerialization(Character ch, Character ch2, Character ch3, QuoteFields quoteFields, Character ch4) {
        this.csv = new CsvOutputSerialization(ch, ch2, ch3, quoteFields, ch4);
    }

    public OutputSerialization(Character ch) {
        this.json = new JsonOutputSerialization(ch);
    }
}
