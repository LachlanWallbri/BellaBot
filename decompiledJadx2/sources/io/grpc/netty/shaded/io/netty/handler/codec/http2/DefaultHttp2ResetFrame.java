package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class DefaultHttp2ResetFrame extends AbstractHttp2StreamFrame implements Http2ResetFrame {
    private final long errorCode;

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Frame
    public String name() {
        return "RST_STREAM";
    }

    public DefaultHttp2ResetFrame(Http2Error http2Error) {
        this.errorCode = ((Http2Error) ObjectUtil.checkNotNull(http2Error, "error")).code();
    }

    public DefaultHttp2ResetFrame(long j) {
        this.errorCode = j;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamFrame
    public DefaultHttp2ResetFrame stream(Http2FrameStream http2FrameStream) {
        super.stream(http2FrameStream);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ResetFrame
    public long errorCode() {
        return this.errorCode;
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "(stream=" + stream() + ", errorCode=" + this.errorCode + ')';
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamFrame
    public boolean equals(Object obj) {
        if (obj instanceof DefaultHttp2ResetFrame) {
            return super.equals(obj) && this.errorCode == ((DefaultHttp2ResetFrame) obj).errorCode;
        }
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamFrame
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        long j = this.errorCode;
        return hashCode + ((int) (j ^ (j >>> 32)));
    }
}
