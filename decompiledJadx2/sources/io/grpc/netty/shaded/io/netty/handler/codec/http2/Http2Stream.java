package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Http2Stream {
    Http2Stream close();

    Http2Stream closeLocalSide();

    Http2Stream closeRemoteSide();

    <V> V getProperty(Http2Connection.PropertyKey propertyKey);

    Http2Stream headersReceived(boolean z);

    Http2Stream headersSent(boolean z);

    /* renamed from: id */
    int mo3916id();

    boolean isHeadersReceived();

    boolean isHeadersSent();

    boolean isPushPromiseSent();

    boolean isResetSent();

    boolean isTrailersReceived();

    boolean isTrailersSent();

    Http2Stream open(boolean z) throws Http2Exception;

    Http2Stream pushPromiseSent();

    <V> V removeProperty(Http2Connection.PropertyKey propertyKey);

    Http2Stream resetSent();

    <V> V setProperty(Http2Connection.PropertyKey propertyKey, V v);

    State state();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum State {
        IDLE(false, false),
        RESERVED_LOCAL(false, false),
        RESERVED_REMOTE(false, false),
        OPEN(true, true),
        HALF_CLOSED_LOCAL(false, true),
        HALF_CLOSED_REMOTE(true, false),
        CLOSED(false, false);

        private final boolean localSideOpen;
        private final boolean remoteSideOpen;

        State(boolean z, boolean z2) {
            this.localSideOpen = z;
            this.remoteSideOpen = z2;
        }

        public boolean localSideOpen() {
            return this.localSideOpen;
        }

        public boolean remoteSideOpen() {
            return this.remoteSideOpen;
        }
    }
}
