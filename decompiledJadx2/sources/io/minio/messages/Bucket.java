package io.minio.messages;

import java.time.ZonedDateTime;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Bucket", strict = false)
/* loaded from: classes7.dex */
public class Bucket {

    @Element(name = "CreationDate")
    private ResponseDate creationDate;

    @Element(name = "Name")
    private String name;

    public String name() {
        return this.name;
    }

    public ZonedDateTime creationDate() {
        return this.creationDate.zonedDateTime();
    }
}
