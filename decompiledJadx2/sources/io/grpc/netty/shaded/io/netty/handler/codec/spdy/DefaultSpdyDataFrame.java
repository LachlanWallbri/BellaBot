package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultSpdyDataFrame extends DefaultSpdyStreamFrame implements SpdyDataFrame {
    private final ByteBuf data;

    public DefaultSpdyDataFrame(int i) {
        this(i, Unpooled.buffer(0));
    }

    public DefaultSpdyDataFrame(int i, ByteBuf byteBuf) {
        super(i);
        this.data = validate((ByteBuf) ObjectUtil.checkNotNull(byteBuf, "data"));
    }

    private static ByteBuf validate(ByteBuf byteBuf) {
        if (byteBuf.readableBytes() <= 16777215) {
            return byteBuf;
        }
        throw new IllegalArgumentException("data payload cannot exceed 16777215 bytes");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.DefaultSpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyDataFrame
    public SpdyDataFrame setStreamId(int i) {
        super.setStreamId(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.DefaultSpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyDataFrame
    public SpdyDataFrame setLast(boolean z) {
        super.setLast(z);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyDataFrame, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public ByteBuf content() {
        return ByteBufUtil.ensureAccessible(this.data);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public SpdyDataFrame copy() {
        return replace(content().copy());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public SpdyDataFrame duplicate() {
        return replace(content().duplicate());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public SpdyDataFrame retainedDuplicate() {
        return replace(content().retainedDuplicate());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public SpdyDataFrame replace(ByteBuf byteBuf) {
        DefaultSpdyDataFrame defaultSpdyDataFrame = new DefaultSpdyDataFrame(streamId(), byteBuf);
        defaultSpdyDataFrame.setLast(isLast());
        return defaultSpdyDataFrame;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public int refCnt() {
        return this.data.refCnt();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public SpdyDataFrame retain() {
        this.data.retain();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public SpdyDataFrame retain(int i) {
        this.data.retain(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public SpdyDataFrame touch() {
        this.data.touch();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public SpdyDataFrame touch(Object obj) {
        this.data.touch(obj);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release() {
        return this.data.release();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release(int i) {
        return this.data.release(i);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtil.simpleClassName(this));
        sb.append("(last: ");
        sb.append(isLast());
        sb.append(')');
        sb.append(StringUtil.NEWLINE);
        sb.append("--> Stream-ID = ");
        sb.append(streamId());
        sb.append(StringUtil.NEWLINE);
        sb.append("--> Size = ");
        if (refCnt() == 0) {
            sb.append("(freed)");
        } else {
            sb.append(content().readableBytes());
        }
        return sb.toString();
    }
}
