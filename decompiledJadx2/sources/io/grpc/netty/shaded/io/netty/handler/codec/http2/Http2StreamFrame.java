package io.grpc.netty.shaded.io.netty.handler.codec.http2;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Http2StreamFrame extends Http2Frame {
    Http2FrameStream stream();

    Http2StreamFrame stream(Http2FrameStream http2FrameStream);
}
