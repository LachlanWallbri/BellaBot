package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersEncoder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class HttpToHttp2ConnectionHandlerBuilder extends AbstractHttp2ConnectionHandlerBuilder<HttpToHttp2ConnectionHandler, HttpToHttp2ConnectionHandlerBuilder> {
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public HttpToHttp2ConnectionHandlerBuilder validateHeaders(boolean z) {
        return (HttpToHttp2ConnectionHandlerBuilder) super.validateHeaders(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public HttpToHttp2ConnectionHandlerBuilder initialSettings(Http2Settings http2Settings) {
        return (HttpToHttp2ConnectionHandlerBuilder) super.initialSettings(http2Settings);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public HttpToHttp2ConnectionHandlerBuilder frameListener(Http2FrameListener http2FrameListener) {
        return (HttpToHttp2ConnectionHandlerBuilder) super.frameListener(http2FrameListener);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public HttpToHttp2ConnectionHandlerBuilder gracefulShutdownTimeoutMillis(long j) {
        return (HttpToHttp2ConnectionHandlerBuilder) super.gracefulShutdownTimeoutMillis(j);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public HttpToHttp2ConnectionHandlerBuilder server(boolean z) {
        return (HttpToHttp2ConnectionHandlerBuilder) super.server(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public HttpToHttp2ConnectionHandlerBuilder connection(Http2Connection http2Connection) {
        return (HttpToHttp2ConnectionHandlerBuilder) super.connection(http2Connection);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public HttpToHttp2ConnectionHandlerBuilder codec(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder) {
        return (HttpToHttp2ConnectionHandlerBuilder) super.codec(http2ConnectionDecoder, http2ConnectionEncoder);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public HttpToHttp2ConnectionHandlerBuilder frameLogger(Http2FrameLogger http2FrameLogger) {
        return (HttpToHttp2ConnectionHandlerBuilder) super.frameLogger(http2FrameLogger);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public HttpToHttp2ConnectionHandlerBuilder encoderEnforceMaxConcurrentStreams(boolean z) {
        return (HttpToHttp2ConnectionHandlerBuilder) super.encoderEnforceMaxConcurrentStreams(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public HttpToHttp2ConnectionHandlerBuilder headerSensitivityDetector(Http2HeadersEncoder.SensitivityDetector sensitivityDetector) {
        return (HttpToHttp2ConnectionHandlerBuilder) super.headerSensitivityDetector(sensitivityDetector);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    @Deprecated
    public HttpToHttp2ConnectionHandlerBuilder initialHuffmanDecodeCapacity(int i) {
        return (HttpToHttp2ConnectionHandlerBuilder) super.initialHuffmanDecodeCapacity(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public HttpToHttp2ConnectionHandlerBuilder decoupleCloseAndGoAway(boolean z) {
        return (HttpToHttp2ConnectionHandlerBuilder) super.decoupleCloseAndGoAway(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public HttpToHttp2ConnectionHandler build() {
        return (HttpToHttp2ConnectionHandler) super.build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public HttpToHttp2ConnectionHandler build(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings) {
        return new HttpToHttp2ConnectionHandler(http2ConnectionDecoder, http2ConnectionEncoder, http2Settings, isValidateHeaders(), decoupleCloseAndGoAway());
    }
}
