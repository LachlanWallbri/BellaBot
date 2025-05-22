package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "CompleteMultipartUploadOutput")
/* loaded from: classes7.dex */
public class CompleteMultipartUploadOutput {

    @Element(name = "Bucket")
    private String bucket;

    @Element(name = "ETag")
    private String etag;

    @Element(name = "Location")
    private String location;

    @Element(name = "Key")
    private String object;

    public String location() {
        return this.location;
    }

    public String bucket() {
        return this.bucket;
    }

    public String object() {
        return this.object;
    }

    public String etag() {
        return this.etag;
    }
}
