package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpConstants;
import io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractHttpData extends AbstractReferenceCounted implements HttpData {
    private boolean completed;
    protected long definedSize;
    private final String name;
    protected long size;
    private static final Pattern STRIP_PATTERN = Pattern.compile("(?:^\\s+|\\s+$|\\n)");
    private static final Pattern REPLACE_PATTERN = Pattern.compile("[\\r\\t]");
    private Charset charset = HttpConstants.DEFAULT_CHARSET;
    private long maxSize = -1;

    @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public abstract HttpData touch();

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public abstract HttpData touch(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractHttpData(String str, Charset charset, long j) {
        ObjectUtil.checkNotNull(str, "name");
        String replaceAll = STRIP_PATTERN.matcher(REPLACE_PATTERN.matcher(str).replaceAll(" ")).replaceAll("");
        if (replaceAll.isEmpty()) {
            throw new IllegalArgumentException("empty name");
        }
        this.name = replaceAll;
        if (charset != null) {
            setCharset(charset);
        }
        this.definedSize = j;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public long getMaxSize() {
        return this.maxSize;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void setMaxSize(long j) {
        this.maxSize = j;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void checkSize(long j) throws IOException {
        long j2 = this.maxSize;
        if (j2 >= 0 && j > j2) {
            throw new IOException("Size exceed allowed maximum capacity");
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData
    public String getName() {
        return this.name;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public boolean isCompleted() {
        return this.completed;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setCompleted() {
        this.completed = true;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public Charset getCharset() {
        return this.charset;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void setCharset(Charset charset) {
        this.charset = (Charset) ObjectUtil.checkNotNull(charset, "charset");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public long length() {
        return this.size;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public long definedLength() {
        return this.definedSize;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public ByteBuf content() {
        try {
            return getByteBuf();
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted
    protected void deallocate() {
        delete();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public HttpData retain() {
        super.retain();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public HttpData retain(int i) {
        super.retain(i);
        return this;
    }
}
