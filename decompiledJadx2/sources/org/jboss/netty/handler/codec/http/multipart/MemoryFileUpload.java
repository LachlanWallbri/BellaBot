package org.jboss.netty.handler.codec.http.multipart;

import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;
import org.apache.http.protocol.HTTP;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData;

/* loaded from: classes7.dex */
public class MemoryFileUpload extends AbstractMemoryHttpData implements FileUpload {
    private String contentTransferEncoding;
    private String contentType;
    private String filename;

    public MemoryFileUpload(String str, String str2, String str3, String str4, Charset charset, long j) {
        super(str, charset, j);
        setFilename(str2);
        setContentType(str3);
        setContentTransferEncoding(str4);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData
    public InterfaceHttpData.HttpDataType getHttpDataType() {
        return InterfaceHttpData.HttpDataType.FileUpload;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.FileUpload
    public String getFilename() {
        return this.filename;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.FileUpload
    public void setFilename(String str) {
        if (str == null) {
            throw new NullPointerException("filename");
        }
        this.filename = str;
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Attribute) {
            return getName().equalsIgnoreCase(((Attribute) obj).getName());
        }
        return false;
    }

    @Override // java.lang.Comparable
    public int compareTo(InterfaceHttpData interfaceHttpData) {
        if (!(interfaceHttpData instanceof FileUpload)) {
            throw new ClassCastException("Cannot compare " + getHttpDataType() + " with " + interfaceHttpData.getHttpDataType());
        }
        return compareTo((FileUpload) interfaceHttpData);
    }

    public int compareTo(FileUpload fileUpload) {
        int compareToIgnoreCase = getName().compareToIgnoreCase(fileUpload.getName());
        if (compareToIgnoreCase != 0) {
        }
        return compareToIgnoreCase;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.FileUpload
    public void setContentType(String str) {
        if (str == null) {
            throw new NullPointerException(CMSAttributeTableGenerator.CONTENT_TYPE);
        }
        this.contentType = str;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.FileUpload
    public String getContentType() {
        return this.contentType;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.FileUpload
    public String getContentTransferEncoding() {
        return this.contentTransferEncoding;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.FileUpload
    public void setContentTransferEncoding(String str) {
        this.contentTransferEncoding = str;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Content-Disposition: form-data; name=\"");
        sb.append(getName());
        sb.append("\"; ");
        sb.append("filename");
        sb.append("=\"");
        sb.append(this.filename);
        sb.append("\"\r\n");
        sb.append("Content-Type");
        sb.append(": ");
        sb.append(this.contentType);
        if (this.charset != null) {
            str = HTTP.CHARSET_PARAM + this.charset + IOUtils.LINE_SEPARATOR_WINDOWS;
        } else {
            str = IOUtils.LINE_SEPARATOR_WINDOWS;
        }
        sb.append(str);
        sb.append("Content-Length");
        sb.append(": ");
        sb.append(length());
        sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
        sb.append("Completed: ");
        sb.append(isCompleted());
        sb.append("\r\nIsInMemory: ");
        sb.append(isInMemory());
        return sb.toString();
    }
}
