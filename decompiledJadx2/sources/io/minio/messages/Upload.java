package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "Upload", strict = false)
/* loaded from: classes7.dex */
public class Upload {
    private long aggregatedPartSize;
    private String encodingType = null;

    @Element(name = "Initiated")
    private ResponseDate initiated;

    @Element(name = "Initiator")
    private Initiator initiator;

    @Element(name = "Key")
    private String objectName;

    @Element(name = "Owner")
    private Owner owner;

    @Element(name = "StorageClass")
    private String storageClass;

    @Element(name = "UploadId")
    private String uploadId;

    public String objectName() {
        try {
            if ("url".equals(this.encodingType)) {
                return URLDecoder.decode(this.objectName, StandardCharsets.UTF_8.name());
            }
            return this.objectName;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String uploadId() {
        return this.uploadId;
    }

    public Initiator initiator() {
        return this.initiator;
    }

    public Owner owner() {
        return this.owner;
    }

    public String storageClass() {
        return this.storageClass;
    }

    public ZonedDateTime initiated() {
        return this.initiated.zonedDateTime();
    }

    public long aggregatedPartSize() {
        return this.aggregatedPartSize;
    }

    public void setAggregatedPartSize(long j) {
        this.aggregatedPartSize = j;
    }

    public void setEncodingType(String str) {
        this.encodingType = str;
    }
}
