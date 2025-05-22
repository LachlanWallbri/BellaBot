package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "ListMultipartUploadsResult", strict = false)
/* loaded from: classes7.dex */
public class ListMultipartUploadsResult {

    @Element(name = "Bucket")
    private String bucketName;

    @Element(name = "EncodingType", required = false)
    private String encodingType;

    @Element(name = "IsTruncated", required = false)
    private boolean isTruncated;

    @Element(name = "KeyMarker", required = false)
    private String keyMarker;

    @Element(name = "MaxUploads")
    private int maxUploads;

    @Element(name = "NextKeyMarker", required = false)
    private String nextKeyMarker;

    @Element(name = "NextUploadIdMarker", required = false)
    private String nextUploadIdMarker;

    @Element(name = "UploadIdMarker", required = false)
    private String uploadIdMarker;

    @ElementList(inline = true, name = "Upload", required = false)
    List<Upload> uploads;

    private String decodeIfNeeded(String str) {
        if (str == null) {
            return str;
        }
        try {
            return "url".equals(this.encodingType) ? URLDecoder.decode(str, StandardCharsets.UTF_8.name()) : str;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isTruncated() {
        return this.isTruncated;
    }

    public String bucketName() {
        return this.bucketName;
    }

    public String keyMarker() {
        return decodeIfNeeded(this.keyMarker);
    }

    public String uploadIdMarker() {
        return this.uploadIdMarker;
    }

    public String nextKeyMarker() {
        return decodeIfNeeded(this.nextKeyMarker);
    }

    public String nextUploadIdMarker() {
        return this.nextUploadIdMarker;
    }

    public int maxUploads() {
        return this.maxUploads;
    }

    public String encodingType() {
        return this.encodingType;
    }

    public List<Upload> uploads() {
        List<Upload> list = this.uploads;
        if (list == null) {
            return Collections.unmodifiableList(new LinkedList());
        }
        return Collections.unmodifiableList(list);
    }
}
