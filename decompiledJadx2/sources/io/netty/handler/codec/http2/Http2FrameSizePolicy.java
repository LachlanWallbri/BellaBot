package io.netty.handler.codec.http2;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface Http2FrameSizePolicy {
    int maxFrameSize();

    void maxFrameSize(int i) throws Http2Exception;
}
