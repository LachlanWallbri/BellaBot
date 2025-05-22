package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Exception;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Http2NoMoreStreamIdsException extends Http2Exception {
    private static final String ERROR_MESSAGE = "No more streams can be created on this connection";
    private static final long serialVersionUID = -7756236161274851110L;

    public Http2NoMoreStreamIdsException() {
        super(Http2Error.PROTOCOL_ERROR, ERROR_MESSAGE, Http2Exception.ShutdownHint.GRACEFUL_SHUTDOWN);
    }

    public Http2NoMoreStreamIdsException(Throwable th) {
        super(Http2Error.PROTOCOL_ERROR, ERROR_MESSAGE, th, Http2Exception.ShutdownHint.GRACEFUL_SHUTDOWN);
    }
}
