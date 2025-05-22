package io.netty.handler.codec.http2;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface StreamByteDistributor {

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public interface StreamState {
        boolean hasFrame();

        long pendingBytes();

        Http2Stream stream();

        int windowSize();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public interface Writer {
        void write(Http2Stream http2Stream, int i);
    }

    boolean distribute(int i, Writer writer) throws Http2Exception;

    void updateDependencyTree(int i, int i2, short s, boolean z);

    void updateStreamableBytes(StreamState streamState);
}
