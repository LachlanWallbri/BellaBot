package io.minio.messages;

import java.time.ZonedDateTime;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Part", strict = false)
/* loaded from: classes7.dex */
public class Part {

    @Element(name = "ETag")
    private String etag;

    @Element(name = "LastModified", required = false)
    private ResponseDate lastModified;

    @Element(name = "PartNumber")
    private int partNumber;

    @Element(name = "Size", required = false)
    private Long size;

    public Part() {
    }

    public Part(int i, String str) {
        this.partNumber = i;
        this.etag = str;
    }

    public int partNumber() {
        return this.partNumber;
    }

    public String etag() {
        return this.etag.replaceAll("\"", "");
    }

    public ZonedDateTime lastModified() {
        return this.lastModified.zonedDateTime();
    }

    public long partSize() {
        return this.size.longValue();
    }
}
