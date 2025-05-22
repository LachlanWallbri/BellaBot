package org.jboss.netty.handler.codec.http.multipart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData;

/* loaded from: classes7.dex */
public class MixedAttribute implements Attribute {
    private Attribute attribute;
    private final long limitSize;

    public MixedAttribute(String str, long j) {
        this.limitSize = j;
        this.attribute = new MemoryAttribute(str);
    }

    public MixedAttribute(String str, String str2, long j) {
        this.limitSize = j;
        if (str2.length() > this.limitSize) {
            try {
                this.attribute = new DiskAttribute(str, str2);
                return;
            } catch (IOException e) {
                try {
                    this.attribute = new MemoryAttribute(str, str2);
                    return;
                } catch (IOException unused) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
        try {
            this.attribute = new MemoryAttribute(str, str2);
        } catch (IOException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void addContent(ChannelBuffer channelBuffer, boolean z) throws IOException {
        Attribute attribute = this.attribute;
        if ((attribute instanceof MemoryAttribute) && attribute.length() + channelBuffer.readableBytes() > this.limitSize) {
            DiskAttribute diskAttribute = new DiskAttribute(this.attribute.getName());
            if (((MemoryAttribute) this.attribute).getChannelBuffer() != null) {
                diskAttribute.addContent(((MemoryAttribute) this.attribute).getChannelBuffer(), false);
            }
            this.attribute = diskAttribute;
        }
        this.attribute.addContent(channelBuffer, z);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void delete() {
        this.attribute.delete();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public byte[] get() throws IOException {
        return this.attribute.get();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public ChannelBuffer getChannelBuffer() throws IOException {
        return this.attribute.getChannelBuffer();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public Charset getCharset() {
        return this.attribute.getCharset();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public String getString() throws IOException {
        return this.attribute.getString();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public String getString(Charset charset) throws IOException {
        return this.attribute.getString(charset);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public boolean isCompleted() {
        return this.attribute.isCompleted();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public boolean isInMemory() {
        return this.attribute.isInMemory();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public long length() {
        return this.attribute.length();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public boolean renameTo(File file) throws IOException {
        return this.attribute.renameTo(file);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void setCharset(Charset charset) {
        this.attribute.setCharset(charset);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void setContent(ChannelBuffer channelBuffer) throws IOException {
        if (channelBuffer.readableBytes() > this.limitSize) {
            Attribute attribute = this.attribute;
            if (attribute instanceof MemoryAttribute) {
                this.attribute = new DiskAttribute(attribute.getName());
            }
        }
        this.attribute.setContent(channelBuffer);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void setContent(File file) throws IOException {
        if (file.length() > this.limitSize) {
            Attribute attribute = this.attribute;
            if (attribute instanceof MemoryAttribute) {
                this.attribute = new DiskAttribute(attribute.getName());
            }
        }
        this.attribute.setContent(file);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void setContent(InputStream inputStream) throws IOException {
        Attribute attribute = this.attribute;
        if (attribute instanceof MemoryAttribute) {
            this.attribute = new DiskAttribute(attribute.getName());
        }
        this.attribute.setContent(inputStream);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData
    public InterfaceHttpData.HttpDataType getHttpDataType() {
        return this.attribute.getHttpDataType();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData
    public String getName() {
        return this.attribute.getName();
    }

    @Override // java.lang.Comparable
    public int compareTo(InterfaceHttpData interfaceHttpData) {
        return this.attribute.compareTo(interfaceHttpData);
    }

    public String toString() {
        return "Mixed: " + this.attribute.toString();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.Attribute
    public String getValue() throws IOException {
        return this.attribute.getValue();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.Attribute
    public void setValue(String str) throws IOException {
        this.attribute.setValue(str);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public ChannelBuffer getChunk(int i) throws IOException {
        return this.attribute.getChunk(i);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public File getFile() throws IOException {
        return this.attribute.getFile();
    }
}
