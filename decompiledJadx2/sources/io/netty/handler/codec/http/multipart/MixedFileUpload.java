package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class MixedFileUpload implements FileUpload {
    private final long definedSize;
    private FileUpload fileUpload;
    private final long limitSize;
    private long maxSize = -1;

    public MixedFileUpload(String str, String str2, String str3, String str4, Charset charset, long j, long j2) {
        this.limitSize = j2;
        if (j > j2) {
            this.fileUpload = new DiskFileUpload(str, str2, str3, str4, charset, j);
        } else {
            this.fileUpload = new MemoryFileUpload(str, str2, str3, str4, charset, j);
        }
        this.definedSize = j;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public long getMaxSize() {
        return this.maxSize;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void setMaxSize(long j) {
        this.maxSize = j;
        this.fileUpload.setMaxSize(j);
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void checkSize(long j) throws IOException {
        long j2 = this.maxSize;
        if (j2 >= 0 && j > j2) {
            throw new IOException("Size exceed allowed maximum capacity");
        }
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void addContent(ByteBuf byteBuf, boolean z) throws IOException {
        FileUpload fileUpload = this.fileUpload;
        if (fileUpload instanceof MemoryFileUpload) {
            checkSize(fileUpload.length() + byteBuf.readableBytes());
            if (this.fileUpload.length() + byteBuf.readableBytes() > this.limitSize) {
                DiskFileUpload diskFileUpload = new DiskFileUpload(this.fileUpload.getName(), this.fileUpload.getFilename(), this.fileUpload.getContentType(), this.fileUpload.getContentTransferEncoding(), this.fileUpload.getCharset(), this.definedSize);
                diskFileUpload.setMaxSize(this.maxSize);
                ByteBuf byteBuf2 = this.fileUpload.getByteBuf();
                if (byteBuf2 != null && byteBuf2.isReadable()) {
                    diskFileUpload.addContent(byteBuf2.retain(), false);
                }
                this.fileUpload.release();
                this.fileUpload = diskFileUpload;
            }
        }
        this.fileUpload.addContent(byteBuf, z);
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void delete() {
        this.fileUpload.delete();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public byte[] get() throws IOException {
        return this.fileUpload.get();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public ByteBuf getByteBuf() throws IOException {
        return this.fileUpload.getByteBuf();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public Charset getCharset() {
        return this.fileUpload.getCharset();
    }

    @Override // io.netty.handler.codec.http.multipart.FileUpload
    public String getContentType() {
        return this.fileUpload.getContentType();
    }

    @Override // io.netty.handler.codec.http.multipart.FileUpload
    public String getContentTransferEncoding() {
        return this.fileUpload.getContentTransferEncoding();
    }

    @Override // io.netty.handler.codec.http.multipart.FileUpload
    public String getFilename() {
        return this.fileUpload.getFilename();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public String getString() throws IOException {
        return this.fileUpload.getString();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public String getString(Charset charset) throws IOException {
        return this.fileUpload.getString(charset);
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public boolean isCompleted() {
        return this.fileUpload.isCompleted();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public boolean isInMemory() {
        return this.fileUpload.isInMemory();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public long length() {
        return this.fileUpload.length();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public long definedLength() {
        return this.fileUpload.definedLength();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public boolean renameTo(File file) throws IOException {
        return this.fileUpload.renameTo(file);
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void setCharset(Charset charset) {
        this.fileUpload.setCharset(charset);
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void setContent(ByteBuf byteBuf) throws IOException {
        checkSize(byteBuf.readableBytes());
        if (byteBuf.readableBytes() > this.limitSize) {
            FileUpload fileUpload = this.fileUpload;
            if (fileUpload instanceof MemoryFileUpload) {
                DiskFileUpload diskFileUpload = new DiskFileUpload(fileUpload.getName(), fileUpload.getFilename(), fileUpload.getContentType(), fileUpload.getContentTransferEncoding(), fileUpload.getCharset(), this.definedSize);
                this.fileUpload = diskFileUpload;
                diskFileUpload.setMaxSize(this.maxSize);
                fileUpload.release();
            }
        }
        this.fileUpload.setContent(byteBuf);
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void setContent(File file) throws IOException {
        checkSize(file.length());
        if (file.length() > this.limitSize) {
            FileUpload fileUpload = this.fileUpload;
            if (fileUpload instanceof MemoryFileUpload) {
                DiskFileUpload diskFileUpload = new DiskFileUpload(fileUpload.getName(), fileUpload.getFilename(), fileUpload.getContentType(), fileUpload.getContentTransferEncoding(), fileUpload.getCharset(), this.definedSize);
                this.fileUpload = diskFileUpload;
                diskFileUpload.setMaxSize(this.maxSize);
                fileUpload.release();
            }
        }
        this.fileUpload.setContent(file);
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public void setContent(InputStream inputStream) throws IOException {
        FileUpload fileUpload = this.fileUpload;
        if (fileUpload instanceof MemoryFileUpload) {
            DiskFileUpload diskFileUpload = new DiskFileUpload(this.fileUpload.getName(), this.fileUpload.getFilename(), this.fileUpload.getContentType(), this.fileUpload.getContentTransferEncoding(), this.fileUpload.getCharset(), this.definedSize);
            this.fileUpload = diskFileUpload;
            diskFileUpload.setMaxSize(this.maxSize);
            fileUpload.release();
        }
        this.fileUpload.setContent(inputStream);
    }

    @Override // io.netty.handler.codec.http.multipart.FileUpload
    public void setContentType(String str) {
        this.fileUpload.setContentType(str);
    }

    @Override // io.netty.handler.codec.http.multipart.FileUpload
    public void setContentTransferEncoding(String str) {
        this.fileUpload.setContentTransferEncoding(str);
    }

    @Override // io.netty.handler.codec.http.multipart.FileUpload
    public void setFilename(String str) {
        this.fileUpload.setFilename(str);
    }

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpData
    public InterfaceHttpData.HttpDataType getHttpDataType() {
        return this.fileUpload.getHttpDataType();
    }

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpData
    public String getName() {
        return this.fileUpload.getName();
    }

    public int hashCode() {
        return this.fileUpload.hashCode();
    }

    public boolean equals(Object obj) {
        return this.fileUpload.equals(obj);
    }

    @Override // java.lang.Comparable
    public int compareTo(InterfaceHttpData interfaceHttpData) {
        return this.fileUpload.compareTo(interfaceHttpData);
    }

    public String toString() {
        return "Mixed: " + this.fileUpload;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public ByteBuf getChunk(int i) throws IOException {
        return this.fileUpload.getChunk(i);
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData
    public File getFile() throws IOException {
        return this.fileUpload.getFile();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData, io.netty.buffer.ByteBufHolder
    public FileUpload copy() {
        return this.fileUpload.copy();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData, io.netty.buffer.ByteBufHolder
    public FileUpload duplicate() {
        return this.fileUpload.duplicate();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData, io.netty.buffer.ByteBufHolder
    public FileUpload retainedDuplicate() {
        return this.fileUpload.retainedDuplicate();
    }

    @Override // io.netty.handler.codec.http.multipart.HttpData, io.netty.buffer.ByteBufHolder
    public FileUpload replace(ByteBuf byteBuf) {
        return this.fileUpload.replace(byteBuf);
    }

    @Override // io.netty.buffer.ByteBufHolder
    public ByteBuf content() {
        return this.fileUpload.content();
    }

    @Override // io.netty.util.ReferenceCounted
    public int refCnt() {
        return this.fileUpload.refCnt();
    }

    @Override // io.netty.util.ReferenceCounted
    public FileUpload retain() {
        this.fileUpload.retain();
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public FileUpload retain(int i) {
        this.fileUpload.retain(i);
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public FileUpload touch() {
        this.fileUpload.touch();
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public FileUpload touch(Object obj) {
        this.fileUpload.touch(obj);
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public boolean release() {
        return this.fileUpload.release();
    }

    @Override // io.netty.util.ReferenceCounted
    public boolean release(int i) {
        return this.fileUpload.release(i);
    }
}
