package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class DefaultHttp2HeadersFrame extends AbstractHttp2StreamFrame implements Http2HeadersFrame {
    private final boolean endStream;
    private final Http2Headers headers;
    private final int padding;

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Frame
    public String name() {
        return "HEADERS";
    }

    public DefaultHttp2HeadersFrame(Http2Headers http2Headers) {
        this(http2Headers, false);
    }

    public DefaultHttp2HeadersFrame(Http2Headers http2Headers, boolean z) {
        this(http2Headers, z, 0);
    }

    public DefaultHttp2HeadersFrame(Http2Headers http2Headers, boolean z, int i) {
        this.headers = (Http2Headers) ObjectUtil.checkNotNull(http2Headers, "headers");
        this.endStream = z;
        Http2CodecUtil.verifyPadding(i);
        this.padding = i;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamFrame
    public DefaultHttp2HeadersFrame stream(Http2FrameStream http2FrameStream) {
        super.stream(http2FrameStream);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersFrame
    public Http2Headers headers() {
        return this.headers;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersFrame
    public boolean isEndStream() {
        return this.endStream;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersFrame
    public int padding() {
        return this.padding;
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "(stream=" + stream() + ", headers=" + this.headers + ", endStream=" + this.endStream + ", padding=" + this.padding + ')';
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamFrame
    public boolean equals(Object obj) {
        if (!(obj instanceof DefaultHttp2HeadersFrame)) {
            return false;
        }
        DefaultHttp2HeadersFrame defaultHttp2HeadersFrame = (DefaultHttp2HeadersFrame) obj;
        return super.equals(defaultHttp2HeadersFrame) && this.headers.equals(defaultHttp2HeadersFrame.headers) && this.endStream == defaultHttp2HeadersFrame.endStream && this.padding == defaultHttp2HeadersFrame.padding;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamFrame
    public int hashCode() {
        return (((((super.hashCode() * 31) + this.headers.hashCode()) * 31) + (!this.endStream ? 1 : 0)) * 31) + this.padding;
    }
}
