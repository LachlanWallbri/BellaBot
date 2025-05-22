package io.minio.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "CSV")
/* loaded from: classes7.dex */
public class CsvInputSerialization {

    @Element(name = "AllowQuotedRecordDelimiter", required = false)
    private boolean allowQuotedRecordDelimiter;

    @Element(name = "Comments", required = false)
    private Character comments;

    @Element(name = "FieldDelimiter", required = false)
    private Character fieldDelimiter;

    @Element(name = "FileHeaderInfo", required = false)
    private FileHeaderInfo fileHeaderInfo;

    @Element(name = "QuoteCharacter", required = false)
    private Character quoteCharacter;

    @Element(name = "QuoteEscapeCharacter", required = false)
    private Character quoteEscapeCharacter;

    @Element(name = "RecordDelimiter", required = false)
    private Character recordDelimiter;

    public CsvInputSerialization(boolean z, Character ch, Character ch2, FileHeaderInfo fileHeaderInfo, Character ch3, Character ch4, Character ch5) {
        this.allowQuotedRecordDelimiter = z;
        this.comments = ch;
        this.fieldDelimiter = ch2;
        this.fileHeaderInfo = fileHeaderInfo;
        this.quoteCharacter = ch3;
        this.quoteEscapeCharacter = ch4;
        this.recordDelimiter = ch5;
    }
}
