package io.minio.messages;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.amazonaws.services.p048s3.internal.Constants;
import java.io.Serializable;
import org.apache.commons.compress.harmony.unpack200.AttributeLayout;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "ErrorResponse", strict = false)
/* loaded from: classes7.dex */
public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = 1905162041950251407L;

    @Element(name = "BucketName", required = false)
    protected String bucketName;

    @Element(name = AttributeLayout.ATTRIBUTE_CODE)
    protected String code;

    @Element(name = "HostId", required = false)
    protected String hostId;

    @Element(name = "Message", required = false)
    protected String message;

    @Element(name = "Key", required = false)
    protected String objectName;

    @Element(name = "RequestId", required = false)
    protected String requestId;

    @Element(name = JsonDocumentFields.RESOURCE, required = false)
    protected String resource;

    public ErrorResponse() {
    }

    public ErrorResponse(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.code = str;
        this.message = str2;
        this.bucketName = str3;
        this.objectName = str4;
        this.resource = str5;
        this.requestId = str6;
        this.hostId = str7;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public String bucketName() {
        return this.bucketName;
    }

    public String objectName() {
        return this.objectName;
    }

    public String hostId() {
        return this.hostId;
    }

    public String requestId() {
        return this.requestId;
    }

    public String resource() {
        return this.resource;
    }

    public String toString() {
        return "ErrorResponse(code = " + this.code + ", message = " + this.message + ", bucketName = " + this.bucketName + ", objectName = " + this.objectName + ", resource = " + this.resource + ", requestId = " + this.requestId + ", hostId = " + this.hostId + ")";
    }
}
