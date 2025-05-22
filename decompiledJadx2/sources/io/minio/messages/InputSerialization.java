package io.minio.messages;

import com.fasterxml.jackson.core.JsonFactory;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "InputSerialization")
/* loaded from: classes7.dex */
public class InputSerialization {

    @Element(name = "CompressionType", required = false)
    private CompressionType compressionType;

    @Element(name = "CSV", required = false)
    private CsvInputSerialization csv;

    @Element(name = JsonFactory.FORMAT_NAME_JSON, required = false)
    private JsonInputSerialization json;

    @Element(name = "Parquet", required = false)
    private ParquetInputSerialization parquet;

    public InputSerialization(CompressionType compressionType, boolean z, Character ch, Character ch2, FileHeaderInfo fileHeaderInfo, Character ch3, Character ch4, Character ch5) {
        this.compressionType = compressionType;
        this.csv = new CsvInputSerialization(z, ch, ch2, fileHeaderInfo, ch3, ch4, ch5);
    }

    public InputSerialization(CompressionType compressionType, JsonType jsonType) {
        this.compressionType = compressionType;
        this.json = new JsonInputSerialization(jsonType);
    }

    public InputSerialization() {
        this.parquet = new ParquetInputSerialization();
    }
}
