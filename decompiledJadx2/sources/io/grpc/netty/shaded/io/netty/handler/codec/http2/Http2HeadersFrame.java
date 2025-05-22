package io.grpc.netty.shaded.io.netty.handler.codec.http2;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Http2HeadersFrame extends Http2StreamFrame {
    Http2Headers headers();

    boolean isEndStream();

    int padding();
}
