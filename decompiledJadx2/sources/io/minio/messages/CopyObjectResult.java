package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import java.time.ZonedDateTime;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "CopyObjectResult", strict = false)
/* loaded from: classes7.dex */
public class CopyObjectResult {

    @Element(name = "ETag")
    private String etag;

    @Element(name = "LastModified")
    private ResponseDate lastModified;

    public String etag() {
        return this.etag;
    }

    public ZonedDateTime lastModified() {
        return this.lastModified.zonedDateTime();
    }
}
