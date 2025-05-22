package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class Http2EmptyDataFrameConnectionDecoder extends DecoratingHttp2ConnectionDecoder {
    private final int maxConsecutiveEmptyFrames;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Http2EmptyDataFrameConnectionDecoder(Http2ConnectionDecoder http2ConnectionDecoder, int i) {
        super(http2ConnectionDecoder);
        this.maxConsecutiveEmptyFrames = ObjectUtil.checkPositive(i, "maxConsecutiveEmptyFrames");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2ConnectionDecoder, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionDecoder
    public void frameListener(Http2FrameListener http2FrameListener) {
        if (http2FrameListener != null) {
            super.frameListener(new Http2EmptyDataFrameListener(http2FrameListener, this.maxConsecutiveEmptyFrames));
        } else {
            super.frameListener(null);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2ConnectionDecoder, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionDecoder
    public Http2FrameListener frameListener() {
        Http2FrameListener frameListener0 = frameListener0();
        return frameListener0 instanceof Http2EmptyDataFrameListener ? ((Http2EmptyDataFrameListener) frameListener0).listener : frameListener0;
    }

    Http2FrameListener frameListener0() {
        return super.frameListener();
    }
}
