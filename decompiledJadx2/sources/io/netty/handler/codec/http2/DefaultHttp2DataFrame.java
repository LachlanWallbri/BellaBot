package io.netty.handler.codec.http2;

import com.iflytek.aiui.AIUIConstant;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.IllegalReferenceCountException;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class DefaultHttp2DataFrame extends AbstractHttp2StreamFrame implements Http2DataFrame {
    private final ByteBuf content;
    private final boolean endStream;
    private final int initialFlowControlledBytes;
    private final int padding;

    @Override // io.netty.handler.codec.http2.Http2Frame
    public String name() {
        return "DATA";
    }

    public DefaultHttp2DataFrame(ByteBuf byteBuf) {
        this(byteBuf, false);
    }

    public DefaultHttp2DataFrame(boolean z) {
        this(Unpooled.EMPTY_BUFFER, z);
    }

    public DefaultHttp2DataFrame(ByteBuf byteBuf, boolean z) {
        this(byteBuf, z, 0);
    }

    public DefaultHttp2DataFrame(ByteBuf byteBuf, boolean z, int i) {
        this.content = (ByteBuf) ObjectUtil.checkNotNull(byteBuf, AIUIConstant.KEY_CONTENT);
        this.endStream = z;
        Http2CodecUtil.verifyPadding(i);
        this.padding = i;
        if (content().readableBytes() + i > 2147483647L) {
            throw new IllegalArgumentException("content + padding must be <= Integer.MAX_VALUE");
        }
        this.initialFlowControlledBytes = content().readableBytes() + i;
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2StreamFrame, io.netty.handler.codec.http2.Http2StreamFrame
    public DefaultHttp2DataFrame stream(Http2FrameStream http2FrameStream) {
        super.stream(http2FrameStream);
        return this;
    }

    @Override // io.netty.handler.codec.http2.Http2DataFrame
    public boolean isEndStream() {
        return this.endStream;
    }

    @Override // io.netty.handler.codec.http2.Http2DataFrame
    public int padding() {
        return this.padding;
    }

    @Override // io.netty.handler.codec.http2.Http2DataFrame, io.netty.buffer.ByteBufHolder
    public ByteBuf content() {
        if (this.content.refCnt() <= 0) {
            throw new IllegalReferenceCountException(this.content.refCnt());
        }
        return this.content;
    }

    @Override // io.netty.handler.codec.http2.Http2DataFrame
    public int initialFlowControlledBytes() {
        return this.initialFlowControlledBytes;
    }

    @Override // io.netty.handler.codec.http2.Http2DataFrame, io.netty.buffer.ByteBufHolder
    public DefaultHttp2DataFrame copy() {
        return replace(content().copy());
    }

    @Override // io.netty.handler.codec.http2.Http2DataFrame, io.netty.buffer.ByteBufHolder
    public DefaultHttp2DataFrame duplicate() {
        return replace(content().duplicate());
    }

    @Override // io.netty.handler.codec.http2.Http2DataFrame, io.netty.buffer.ByteBufHolder
    public DefaultHttp2DataFrame retainedDuplicate() {
        return replace(content().retainedDuplicate());
    }

    @Override // io.netty.handler.codec.http2.Http2DataFrame, io.netty.buffer.ByteBufHolder
    public DefaultHttp2DataFrame replace(ByteBuf byteBuf) {
        return new DefaultHttp2DataFrame(byteBuf, this.endStream, this.padding);
    }

    @Override // io.netty.util.ReferenceCounted
    public int refCnt() {
        return this.content.refCnt();
    }

    @Override // io.netty.util.ReferenceCounted
    public boolean release() {
        return this.content.release();
    }

    @Override // io.netty.util.ReferenceCounted
    public boolean release(int i) {
        return this.content.release(i);
    }

    @Override // io.netty.util.ReferenceCounted
    public DefaultHttp2DataFrame retain() {
        this.content.retain();
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public DefaultHttp2DataFrame retain(int i) {
        this.content.retain(i);
        return this;
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "(stream=" + stream() + ", content=" + this.content + ", endStream=" + this.endStream + ", padding=" + this.padding + ')';
    }

    @Override // io.netty.util.ReferenceCounted
    public DefaultHttp2DataFrame touch() {
        this.content.touch();
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public DefaultHttp2DataFrame touch(Object obj) {
        this.content.touch(obj);
        return this;
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2StreamFrame
    public boolean equals(Object obj) {
        if (!(obj instanceof DefaultHttp2DataFrame)) {
            return false;
        }
        DefaultHttp2DataFrame defaultHttp2DataFrame = (DefaultHttp2DataFrame) obj;
        return super.equals(defaultHttp2DataFrame) && this.content.equals(defaultHttp2DataFrame.content()) && this.endStream == defaultHttp2DataFrame.endStream && this.padding == defaultHttp2DataFrame.padding;
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2StreamFrame
    public int hashCode() {
        return (((((super.hashCode() * 31) + this.content.hashCode()) * 31) + (!this.endStream ? 1 : 0)) * 31) + this.padding;
    }
}
