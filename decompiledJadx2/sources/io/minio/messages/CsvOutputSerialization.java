package io.minio.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "CSV")
/* loaded from: classes7.dex */
public class CsvOutputSerialization {

    @Element(name = "FieldDelimiter", required = false)
    private Character fieldDelimiter;

    @Element(name = "QuoteCharacter", required = false)
    private Character quoteCharacter;

    @Element(name = "QuoteEscapeCharacter", required = false)
    private Character quoteEscapeCharacter;

    @Element(name = "QuoteFields", required = false)
    private QuoteFields quoteFields;

    @Element(name = "RecordDelimiter", required = false)
    private Character recordDelimiter;

    public CsvOutputSerialization(Character ch, Character ch2, Character ch3, QuoteFields quoteFields, Character ch4) {
        this.fieldDelimiter = ch;
        this.quoteCharacter = ch2;
        this.quoteEscapeCharacter = ch3;
        this.quoteFields = quoteFields;
        this.recordDelimiter = ch4;
    }
}
