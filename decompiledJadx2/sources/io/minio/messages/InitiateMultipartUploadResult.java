package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "InitiateMultipartUploadResult", strict = false)
/* loaded from: classes7.dex */
public class InitiateMultipartUploadResult {

    @Element(name = "Bucket")
    private String bucketName;

    @Element(name = "Key")
    private String objectName;

    @Element(name = "UploadId")
    private String uploadId;

    public String bucketName() {
        return this.bucketName;
    }

    public String objectName() {
        return this.objectName;
    }

    public String uploadId() {
        return this.uploadId;
    }
}
