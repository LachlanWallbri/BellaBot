package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpConstants;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.nio.charset.Charset;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DiskAttribute extends AbstractDiskHttpData implements Attribute {
    public static String baseDirectory = null;
    public static boolean deleteOnExitTemporaryFile = true;
    public static final String postfix = ".att";
    public static final String prefix = "Attr_";
    private String baseDir;
    private boolean deleteOnExit;

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractDiskHttpData
    protected String getPostfix() {
        return ".att";
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractDiskHttpData
    protected String getPrefix() {
        return "Attr_";
    }

    public DiskAttribute(String str) {
        this(str, HttpConstants.DEFAULT_CHARSET);
    }

    public DiskAttribute(String str, String str2, boolean z) {
        this(str, HttpConstants.DEFAULT_CHARSET);
        this.baseDir = str2 == null ? baseDirectory : str2;
        this.deleteOnExit = z;
    }

    public DiskAttribute(String str, long j) {
        this(str, j, HttpConstants.DEFAULT_CHARSET, baseDirectory, deleteOnExitTemporaryFile);
    }

    public DiskAttribute(String str, long j, String str2, boolean z) {
        this(str, j, HttpConstants.DEFAULT_CHARSET);
        this.baseDir = str2 == null ? baseDirectory : str2;
        this.deleteOnExit = z;
    }

    public DiskAttribute(String str, Charset charset) {
        this(str, charset, baseDirectory, deleteOnExitTemporaryFile);
    }

    public DiskAttribute(String str, Charset charset, String str2, boolean z) {
        super(str, charset, 0L);
        this.baseDir = str2 == null ? baseDirectory : str2;
        this.deleteOnExit = z;
    }

    public DiskAttribute(String str, long j, Charset charset) {
        this(str, j, charset, baseDirectory, deleteOnExitTemporaryFile);
    }

    public DiskAttribute(String str, long j, Charset charset, String str2, boolean z) {
        super(str, charset, j);
        this.baseDir = str2 == null ? baseDirectory : str2;
        this.deleteOnExit = z;
    }

    public DiskAttribute(String str, String str2) throws IOException {
        this(str, str2, HttpConstants.DEFAULT_CHARSET);
    }

    public DiskAttribute(String str, String str2, Charset charset) throws IOException {
        this(str, str2, charset, baseDirectory, deleteOnExitTemporaryFile);
    }

    public DiskAttribute(String str, String str2, Charset charset, String str3, boolean z) throws IOException {
        super(str, charset, 0L);
        setValue(str2);
        this.baseDir = str3 == null ? baseDirectory : str3;
        this.deleteOnExit = z;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData
    public InterfaceHttpData.HttpDataType getHttpDataType() {
        return InterfaceHttpData.HttpDataType.Attribute;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.Attribute
    public String getValue() throws IOException {
        return new String(get(), getCharset());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.Attribute
    public void setValue(String str) throws IOException {
        ObjectUtil.checkNotNull(str, ES6Iterator.VALUE_PROPERTY);
        byte[] bytes = str.getBytes(getCharset());
        checkSize(bytes.length);
        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(bytes);
        if (this.definedSize > 0) {
            this.definedSize = wrappedBuffer.readableBytes();
        }
        setContent(wrappedBuffer);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractDiskHttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void addContent(ByteBuf byteBuf, boolean z) throws IOException {
        long readableBytes = this.size + byteBuf.readableBytes();
        checkSize(readableBytes);
        if (this.definedSize > 0 && this.definedSize < readableBytes) {
            this.definedSize = readableBytes;
        }
        super.addContent(byteBuf, z);
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
            return getName() + '=' + getValue();
        } catch (IOException e) {
            return getName() + '=' + e;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractDiskHttpData
    protected boolean deleteOnExit() {
        return this.deleteOnExit;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractDiskHttpData
    protected String getBaseDirectory() {
        return this.baseDir;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractDiskHttpData
    protected String getDiskFilename() {
        return getName() + ".att";
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public Attribute copy() {
        ByteBuf content = content();
        return replace(content != null ? content.copy() : null);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public Attribute duplicate() {
        ByteBuf content = content();
        return replace(content != null ? content.duplicate() : null);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public Attribute retainedDuplicate() {
        ByteBuf content = content();
        if (content != null) {
            ByteBuf retainedDuplicate = content.retainedDuplicate();
            try {
                return replace(retainedDuplicate);
            } catch (Throwable th) {
                retainedDuplicate.release();
                throw th;
            }
        }
        return replace((ByteBuf) null);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public Attribute replace(ByteBuf byteBuf) {
        DiskAttribute diskAttribute = new DiskAttribute(getName(), this.baseDir, this.deleteOnExit);
        diskAttribute.setCharset(getCharset());
        if (byteBuf != null) {
            try {
                diskAttribute.setContent(byteBuf);
            } catch (IOException e) {
                throw new ChannelException(e);
            }
        }
        return diskAttribute;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractHttpData, io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public Attribute retain(int i) {
        super.retain(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractHttpData, io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public Attribute retain() {
        super.retain();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractDiskHttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractHttpData, io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public Attribute touch() {
        super.touch();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractDiskHttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public Attribute touch(Object obj) {
        super.touch(obj);
        return this;
    }
}
