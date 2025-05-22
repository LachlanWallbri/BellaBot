package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpConstants;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class MixedAttribute implements Attribute {
    private Attribute attribute;
    private String baseDir;
    private boolean deleteOnExit;
    private final long limitSize;
    private long maxSize;

    public MixedAttribute(String str, long j) {
        this(str, j, HttpConstants.DEFAULT_CHARSET);
    }

    public MixedAttribute(String str, long j, long j2) {
        this(str, j, j2, HttpConstants.DEFAULT_CHARSET);
    }

    public MixedAttribute(String str, long j, Charset charset) {
        this(str, j, charset, DiskAttribute.baseDirectory, DiskAttribute.deleteOnExitTemporaryFile);
    }

    public MixedAttribute(String str, long j, Charset charset, String str2, boolean z) {
        this.maxSize = -1L;
        this.limitSize = j;
        this.attribute = new MemoryAttribute(str, charset);
        this.baseDir = str2;
        this.deleteOnExit = z;
    }

    public MixedAttribute(String str, long j, long j2, Charset charset) {
        this(str, j, j2, charset, DiskAttribute.baseDirectory, DiskAttribute.deleteOnExitTemporaryFile);
    }

    public MixedAttribute(String str, long j, long j2, Charset charset, String str2, boolean z) {
        this.maxSize = -1L;
        this.limitSize = j2;
        this.attribute = new MemoryAttribute(str, j, charset);
        this.baseDir = str2;
        this.deleteOnExit = z;
    }

    public MixedAttribute(String str, String str2, long j) {
        this(str, str2, j, HttpConstants.DEFAULT_CHARSET, DiskAttribute.baseDirectory, DiskFileUpload.deleteOnExitTemporaryFile);
    }

    public MixedAttribute(String str, String str2, long j, Charset charset) {
        this(str, str2, j, charset, DiskAttribute.baseDirectory, DiskFileUpload.deleteOnExitTemporaryFile);
    }

    public MixedAttribute(String str, String str2, long j, Charset charset, String str3, boolean z) {
        this.maxSize = -1L;
        this.limitSize = j;
        if (str2.length() > this.limitSize) {
            try {
                this.attribute = new DiskAttribute(str, str2, charset, str3, z);
            } catch (IOException e) {
                try {
                    this.attribute = new MemoryAttribute(str, str2, charset);
                } catch (IOException unused) {
                    throw new IllegalArgumentException(e);
                }
            }
        } else {
            try {
                this.attribute = new MemoryAttribute(str, str2, charset);
            } catch (IOException e2) {
                throw new IllegalArgumentException(e2);
            }
        }
        this.baseDir = str3;
        this.deleteOnExit = z;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public long getMaxSize() {
        return this.maxSize;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void setMaxSize(long j) {
        this.maxSize = j;
        this.attribute.setMaxSize(j);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void checkSize(long j) throws IOException {
        long j2 = this.maxSize;
        if (j2 >= 0 && j > j2) {
            throw new IOException("Size exceed allowed maximum capacity");
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void addContent(ByteBuf byteBuf, boolean z) throws IOException {
        Attribute attribute = this.attribute;
        if (attribute instanceof MemoryAttribute) {
            checkSize(attribute.length() + byteBuf.readableBytes());
            if (this.attribute.length() + byteBuf.readableBytes() > this.limitSize) {
                DiskAttribute diskAttribute = new DiskAttribute(this.attribute.getName(), this.attribute.definedLength(), this.baseDir, this.deleteOnExit);
                diskAttribute.setMaxSize(this.maxSize);
                if (((MemoryAttribute) this.attribute).getByteBuf() != null) {
                    diskAttribute.addContent(((MemoryAttribute) this.attribute).getByteBuf(), false);
                }
                this.attribute = diskAttribute;
            }
        }
        this.attribute.addContent(byteBuf, z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void delete() {
        this.attribute.delete();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public byte[] get() throws IOException {
        return this.attribute.get();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public ByteBuf getByteBuf() throws IOException {
        return this.attribute.getByteBuf();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public Charset getCharset() {
        return this.attribute.getCharset();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public String getString() throws IOException {
        return this.attribute.getString();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public String getString(Charset charset) throws IOException {
        return this.attribute.getString(charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public boolean isCompleted() {
        return this.attribute.isCompleted();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public boolean isInMemory() {
        return this.attribute.isInMemory();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public long length() {
        return this.attribute.length();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public long definedLength() {
        return this.attribute.definedLength();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public boolean renameTo(File file) throws IOException {
        return this.attribute.renameTo(file);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void setCharset(Charset charset) {
        this.attribute.setCharset(charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void setContent(ByteBuf byteBuf) throws IOException {
        checkSize(byteBuf.readableBytes());
        if (byteBuf.readableBytes() > this.limitSize) {
            Attribute attribute = this.attribute;
            if (attribute instanceof MemoryAttribute) {
                this.attribute = new DiskAttribute(attribute.getName(), this.attribute.definedLength(), this.baseDir, this.deleteOnExit);
                this.attribute.setMaxSize(this.maxSize);
            }
        }
        this.attribute.setContent(byteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void setContent(File file) throws IOException {
        checkSize(file.length());
        if (file.length() > this.limitSize) {
            Attribute attribute = this.attribute;
            if (attribute instanceof MemoryAttribute) {
                this.attribute = new DiskAttribute(attribute.getName(), this.attribute.definedLength(), this.baseDir, this.deleteOnExit);
                this.attribute.setMaxSize(this.maxSize);
            }
        }
        this.attribute.setContent(file);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void setContent(InputStream inputStream) throws IOException {
        Attribute attribute = this.attribute;
        if (attribute instanceof MemoryAttribute) {
            this.attribute = new DiskAttribute(attribute.getName(), this.attribute.definedLength(), this.baseDir, this.deleteOnExit);
            this.attribute.setMaxSize(this.maxSize);
        }
        this.attribute.setContent(inputStream);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData
    public InterfaceHttpData.HttpDataType getHttpDataType() {
        return this.attribute.getHttpDataType();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData
    public String getName() {
        return this.attribute.getName();
    }

    public int hashCode() {
        return this.attribute.hashCode();
    }

    public boolean equals(Object obj) {
        return this.attribute.equals(obj);
    }

    @Override // java.lang.Comparable
    public int compareTo(InterfaceHttpData interfaceHttpData) {
        return this.attribute.compareTo(interfaceHttpData);
    }

    public String toString() {
        return "Mixed: " + this.attribute;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.Attribute
    public String getValue() throws IOException {
        return this.attribute.getValue();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.Attribute
    public void setValue(String str) throws IOException {
        if (str != null) {
            checkSize(str.getBytes().length);
        }
        this.attribute.setValue(str);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public ByteBuf getChunk(int i) throws IOException {
        return this.attribute.getChunk(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public File getFile() throws IOException {
        return this.attribute.getFile();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public Attribute copy() {
        return this.attribute.copy();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public Attribute duplicate() {
        return this.attribute.duplicate();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public Attribute retainedDuplicate() {
        return this.attribute.retainedDuplicate();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public Attribute replace(ByteBuf byteBuf) {
        return this.attribute.replace(byteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public ByteBuf content() {
        return this.attribute.content();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public int refCnt() {
        return this.attribute.refCnt();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public Attribute retain() {
        this.attribute.retain();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public Attribute retain(int i) {
        this.attribute.retain(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public Attribute touch() {
        this.attribute.touch();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public Attribute touch(Object obj) {
        this.attribute.touch(obj);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release() {
        return this.attribute.release();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release(int i) {
        return this.attribute.release(i);
    }
}
