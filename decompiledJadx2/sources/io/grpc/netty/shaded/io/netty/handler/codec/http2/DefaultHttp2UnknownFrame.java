package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class DefaultHttp2UnknownFrame extends DefaultByteBufHolder implements Http2UnknownFrame {
    private final Http2Flags flags;
    private final byte frameType;
    private Http2FrameStream stream;

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Frame
    public String name() {
        return "UNKNOWN";
    }

    public DefaultHttp2UnknownFrame(byte b, Http2Flags http2Flags) {
        this(b, http2Flags, Unpooled.EMPTY_BUFFER);
    }

    public DefaultHttp2UnknownFrame(byte b, Http2Flags http2Flags, ByteBuf byteBuf) {
        super(byteBuf);
        this.frameType = b;
        this.flags = http2Flags;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2UnknownFrame, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamFrame
    public Http2FrameStream stream() {
        return this.stream;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2UnknownFrame, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamFrame
    public DefaultHttp2UnknownFrame stream(Http2FrameStream http2FrameStream) {
        this.stream = http2FrameStream;
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2UnknownFrame
    public byte frameType() {
        return this.frameType;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2UnknownFrame
    public Http2Flags flags() {
        return this.flags;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public DefaultHttp2UnknownFrame copy() {
        return replace(content().copy());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public DefaultHttp2UnknownFrame duplicate() {
        return replace(content().duplicate());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public DefaultHttp2UnknownFrame retainedDuplicate() {
        return replace(content().retainedDuplicate());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public DefaultHttp2UnknownFrame replace(ByteBuf byteBuf) {
        return new DefaultHttp2UnknownFrame(this.frameType, this.flags, byteBuf).stream(this.stream);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public DefaultHttp2UnknownFrame retain() {
        super.retain();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public DefaultHttp2UnknownFrame retain(int i) {
        super.retain(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder
    public String toString() {
        return StringUtil.simpleClassName(this) + "(frameType=" + ((int) this.frameType) + ", stream=" + this.stream + ", flags=" + this.flags + ", content=" + contentToString() + ')';
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public DefaultHttp2UnknownFrame touch() {
        super.touch();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public DefaultHttp2UnknownFrame touch(Object obj) {
        super.touch(obj);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder
    public boolean equals(Object obj) {
        if (!(obj instanceof DefaultHttp2UnknownFrame)) {
            return false;
        }
        DefaultHttp2UnknownFrame defaultHttp2UnknownFrame = (DefaultHttp2UnknownFrame) obj;
        Http2FrameStream stream = defaultHttp2UnknownFrame.stream();
        Http2FrameStream http2FrameStream = this.stream;
        return (http2FrameStream == stream || (stream != null && stream.equals(http2FrameStream))) && this.flags.equals(defaultHttp2UnknownFrame.flags()) && this.frameType == defaultHttp2UnknownFrame.frameType() && super.equals(defaultHttp2UnknownFrame);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder
    public int hashCode() {
        int hashCode = (((super.hashCode() * 31) + this.frameType) * 31) + this.flags.hashCode();
        Http2FrameStream http2FrameStream = this.stream;
        return http2FrameStream != null ? (hashCode * 31) + http2FrameStream.hashCode() : hashCode;
    }
}
