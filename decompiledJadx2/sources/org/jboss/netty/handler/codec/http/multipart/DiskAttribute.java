package org.jboss.netty.handler.codec.http.multipart;

import java.io.IOException;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.HttpConstants;
import org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData;
import org.mozilla.javascript.ES6Iterator;

/* loaded from: classes7.dex */
public class DiskAttribute extends AbstractDiskHttpData implements Attribute {
    public static String baseDirectory = null;
    public static boolean deleteOnExitTemporaryFile = true;
    public static String postfix = ".att";
    public static String prefix = "Attr_";

    public DiskAttribute(String str) {
        super(str, HttpConstants.DEFAULT_CHARSET, 0L);
    }

    public DiskAttribute(String str, String str2) throws IOException {
        super(str, HttpConstants.DEFAULT_CHARSET, 0L);
        setValue(str2);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData
    public InterfaceHttpData.HttpDataType getHttpDataType() {
        return InterfaceHttpData.HttpDataType.Attribute;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.Attribute
    public String getValue() throws IOException {
        return new String(get(), this.charset.name());
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.Attribute
    public void setValue(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException(ES6Iterator.VALUE_PROPERTY);
        }
        ChannelBuffer wrappedBuffer = ChannelBuffers.wrappedBuffer(str.getBytes(this.charset.name()));
        if (this.definedSize > 0) {
            this.definedSize = wrappedBuffer.readableBytes();
        }
        setContent(wrappedBuffer);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.AbstractDiskHttpData, org.jboss.netty.handler.codec.http.multipart.HttpData
    public void addContent(ChannelBuffer channelBuffer, boolean z) throws IOException {
        int readableBytes = channelBuffer.readableBytes();
        if (this.definedSize > 0) {
            long j = readableBytes;
            if (this.definedSize < this.size + j) {
                this.definedSize = this.size + j;
            }
        }
        super.addContent(channelBuffer, z);
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
        if (!(interfaceHttpData instanceof Attribute)) {
            throw new ClassCastException("Cannot compare " + getHttpDataType() + " with " + interfaceHttpData.getHttpDataType());
        }
        return compareTo((Attribute) interfaceHttpData);
    }

    public int compareTo(Attribute attribute) {
        return getName().compareToIgnoreCase(attribute.getName());
    }

    public String toString() {
        try {
            return getName() + "=" + getValue();
        } catch (IOException unused) {
            return getName() + "=IoException";
        }
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.AbstractDiskHttpData
    protected boolean deleteOnExit() {
        return deleteOnExitTemporaryFile;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.AbstractDiskHttpData
    protected String getBaseDirectory() {
        return baseDirectory;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.AbstractDiskHttpData
    protected String getDiskFilename() {
        return getName() + postfix;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.AbstractDiskHttpData
    protected String getPostfix() {
        return postfix;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.AbstractDiskHttpData
    protected String getPrefix() {
        return prefix;
    }
}
