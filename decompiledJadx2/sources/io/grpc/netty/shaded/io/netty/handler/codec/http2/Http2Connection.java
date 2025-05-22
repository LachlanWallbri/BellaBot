package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.Promise;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Http2Connection {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface Endpoint<F extends Http2FlowController> {
        void allowPushTo(boolean z);

        boolean allowPushTo();

        boolean canOpenStream();

        Http2Stream createStream(int i, boolean z) throws Http2Exception;

        boolean created(Http2Stream http2Stream);

        F flowController();

        void flowController(F f);

        int incrementAndGetNextStreamId();

        boolean isServer();

        boolean isValidStreamId(int i);

        int lastStreamCreated();

        int lastStreamKnownByPeer();

        int maxActiveStreams();

        void maxActiveStreams(int i);

        boolean mayHaveCreatedStream(int i);

        int numActiveStreams();

        Endpoint<? extends Http2FlowController> opposite();

        Http2Stream reservePushStream(int i, Http2Stream http2Stream) throws Http2Exception;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface Listener {
        void onGoAwayReceived(int i, long j, ByteBuf byteBuf);

        void onGoAwaySent(int i, long j, ByteBuf byteBuf);

        void onStreamActive(Http2Stream http2Stream);

        void onStreamAdded(Http2Stream http2Stream);

        void onStreamClosed(Http2Stream http2Stream);

        void onStreamHalfClosed(Http2Stream http2Stream);

        void onStreamRemoved(Http2Stream http2Stream);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface PropertyKey {
    }

    void addListener(Listener listener);

    Future<Void> close(Promise<Void> promise);

    Http2Stream connectionStream();

    Http2Stream forEachActiveStream(Http2StreamVisitor http2StreamVisitor) throws Http2Exception;

    void goAwayReceived(int i, long j, ByteBuf byteBuf) throws Http2Exception;

    boolean goAwayReceived();

    boolean goAwaySent();

    boolean goAwaySent(int i, long j, ByteBuf byteBuf) throws Http2Exception;

    boolean isServer();

    Endpoint<Http2LocalFlowController> local();

    PropertyKey newKey();

    int numActiveStreams();

    Endpoint<Http2RemoteFlowController> remote();

    void removeListener(Listener listener);

    Http2Stream stream(int i);

    boolean streamMayHaveExisted(int i);
}
