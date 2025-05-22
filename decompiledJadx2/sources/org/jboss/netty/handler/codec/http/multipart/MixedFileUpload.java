package org.jboss.netty.handler.codec.http.multipart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData;

/* loaded from: classes7.dex */
public class MixedFileUpload implements FileUpload {
    private final long definedSize;
    private FileUpload fileUpload;
    private final long limitSize;

    public MixedFileUpload(String str, String str2, String str3, String str4, Charset charset, long j, long j2) {
        this.limitSize = j2;
        if (j > this.limitSize) {
            this.fileUpload = new DiskFileUpload(str, str2, str3, str4, charset, j);
        } else {
            this.fileUpload = new MemoryFileUpload(str, str2, str3, str4, charset, j);
        }
        this.definedSize = j;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void addContent(ChannelBuffer channelBuffer, boolean z) throws IOException {
        FileUpload fileUpload = this.fileUpload;
        if ((fileUpload instanceof MemoryFileUpload) && fileUpload.length() + channelBuffer.readableBytes() > this.limitSize) {
            DiskFileUpload diskFileUpload = new DiskFileUpload(this.fileUpload.getName(), this.fileUpload.getFilename(), this.fileUpload.getContentType(), this.fileUpload.getContentTransferEncoding(), this.fileUpload.getCharset(), this.definedSize);
            if (((MemoryFileUpload) this.fileUpload).getChannelBuffer() != null) {
                diskFileUpload.addContent(((MemoryFileUpload) this.fileUpload).getChannelBuffer(), false);
            }
            this.fileUpload = diskFileUpload;
        }
        this.fileUpload.addContent(channelBuffer, z);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void delete() {
        this.fileUpload.delete();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public byte[] get() throws IOException {
        return this.fileUpload.get();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public ChannelBuffer getChannelBuffer() throws IOException {
        return this.fileUpload.getChannelBuffer();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public Charset getCharset() {
        return this.fileUpload.getCharset();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.FileUpload
    public String getContentType() {
        return this.fileUpload.getContentType();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.FileUpload
    public String getContentTransferEncoding() {
        return this.fileUpload.getContentTransferEncoding();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.FileUpload
    public String getFilename() {
        return this.fileUpload.getFilename();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public String getString() throws IOException {
        return this.fileUpload.getString();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public String getString(Charset charset) throws IOException {
        return this.fileUpload.getString(charset);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public boolean isCompleted() {
        return this.fileUpload.isCompleted();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public boolean isInMemory() {
        return this.fileUpload.isInMemory();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public long length() {
        return this.fileUpload.length();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public boolean renameTo(File file) throws IOException {
        return this.fileUpload.renameTo(file);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void setCharset(Charset charset) {
        this.fileUpload.setCharset(charset);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void setContent(ChannelBuffer channelBuffer) throws IOException {
        if (channelBuffer.readableBytes() > this.limitSize) {
            FileUpload fileUpload = this.fileUpload;
            if (fileUpload instanceof MemoryFileUpload) {
                this.fileUpload = new DiskFileUpload(fileUpload.getName(), this.fileUpload.getFilename(), this.fileUpload.getContentType(), this.fileUpload.getContentTransferEncoding(), this.fileUpload.getCharset(), this.definedSize);
            }
        }
        this.fileUpload.setContent(channelBuffer);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void setContent(File file) throws IOException {
        if (file.length() > this.limitSize) {
            FileUpload fileUpload = this.fileUpload;
            if (fileUpload instanceof MemoryFileUpload) {
                this.fileUpload = new DiskFileUpload(fileUpload.getName(), this.fileUpload.getFilename(), this.fileUpload.getContentType(), this.fileUpload.getContentTransferEncoding(), this.fileUpload.getCharset(), this.definedSize);
            }
        }
        this.fileUpload.setContent(file);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void setContent(InputStream inputStream) throws IOException {
        FileUpload fileUpload = this.fileUpload;
        if (fileUpload instanceof MemoryFileUpload) {
            this.fileUpload = new DiskFileUpload(fileUpload.getName(), this.fileUpload.getFilename(), this.fileUpload.getContentType(), this.fileUpload.getContentTransferEncoding(), this.fileUpload.getCharset(), this.definedSize);
        }
        this.fileUpload.setContent(inputStream);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.FileUpload
    public void setContentType(String str) {
        this.fileUpload.setContentType(str);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.FileUpload
    public void setContentTransferEncoding(String str) {
        this.fileUpload.setContentTransferEncoding(str);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.FileUpload
    public void setFilename(String str) {
        this.fileUpload.setFilename(str);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData
    public InterfaceHttpData.HttpDataType getHttpDataType() {
        return this.fileUpload.getHttpDataType();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData
    public String getName() {
        return this.fileUpload.getName();
    }

    @Override // java.lang.Comparable
    public int compareTo(InterfaceHttpData interfaceHttpData) {
        return this.fileUpload.compareTo(interfaceHttpData);
    }

    public String toString() {
        return "Mixed: " + this.fileUpload.toString();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public ChannelBuffer getChunk(int i) throws IOException {
        return this.fileUpload.getChunk(i);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public File getFile() throws IOException {
        return this.fileUpload.getFile();
    }
}
