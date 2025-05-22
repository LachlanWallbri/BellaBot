package io.grpc.netty.shaded.io.netty.handler.codec.http2;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Http2FrameSizePolicy {
    int maxFrameSize();

    void maxFrameSize(int i) throws Http2Exception;
}
