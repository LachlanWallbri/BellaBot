package io.netty.handler.codec.http2;

import io.netty.handler.codec.http2.Http2HeadersEncoder;
import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class Http2FrameCodecBuilder extends AbstractHttp2ConnectionHandlerBuilder<Http2FrameCodec, Http2FrameCodecBuilder> {
    private Http2FrameWriter frameWriter;

    Http2FrameCodecBuilder(boolean z) {
        server(z);
    }

    public static Http2FrameCodecBuilder forClient() {
        return new Http2FrameCodecBuilder(false);
    }

    public static Http2FrameCodecBuilder forServer() {
        return new Http2FrameCodecBuilder(true);
    }

    Http2FrameCodecBuilder frameWriter(Http2FrameWriter http2FrameWriter) {
        this.frameWriter = (Http2FrameWriter) ObjectUtil.checkNotNull(http2FrameWriter, "frameWriter");
        return this;
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2Settings initialSettings() {
        return super.initialSettings();
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder initialSettings(Http2Settings http2Settings) {
        return (Http2FrameCodecBuilder) super.initialSettings(http2Settings);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public long gracefulShutdownTimeoutMillis() {
        return super.gracefulShutdownTimeoutMillis();
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder gracefulShutdownTimeoutMillis(long j) {
        return (Http2FrameCodecBuilder) super.gracefulShutdownTimeoutMillis(j);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public boolean isServer() {
        return super.isServer();
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public int maxReservedStreams() {
        return super.maxReservedStreams();
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder maxReservedStreams(int i) {
        return (Http2FrameCodecBuilder) super.maxReservedStreams(i);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public boolean isValidateHeaders() {
        return super.isValidateHeaders();
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder validateHeaders(boolean z) {
        return (Http2FrameCodecBuilder) super.validateHeaders(z);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameLogger frameLogger() {
        return super.frameLogger();
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder frameLogger(Http2FrameLogger http2FrameLogger) {
        return (Http2FrameCodecBuilder) super.frameLogger(http2FrameLogger);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public boolean encoderEnforceMaxConcurrentStreams() {
        return super.encoderEnforceMaxConcurrentStreams();
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder encoderEnforceMaxConcurrentStreams(boolean z) {
        return (Http2FrameCodecBuilder) super.encoderEnforceMaxConcurrentStreams(z);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2HeadersEncoder.SensitivityDetector headerSensitivityDetector() {
        return super.headerSensitivityDetector();
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder headerSensitivityDetector(Http2HeadersEncoder.SensitivityDetector sensitivityDetector) {
        return (Http2FrameCodecBuilder) super.headerSensitivityDetector(sensitivityDetector);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder encoderIgnoreMaxHeaderListSize(boolean z) {
        return (Http2FrameCodecBuilder) super.encoderIgnoreMaxHeaderListSize(z);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder initialHuffmanDecodeCapacity(int i) {
        return (Http2FrameCodecBuilder) super.initialHuffmanDecodeCapacity(i);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodec build() {
        Http2FrameWriter http2FrameWriter = this.frameWriter;
        if (http2FrameWriter != null) {
            DefaultHttp2Connection defaultHttp2Connection = new DefaultHttp2Connection(isServer(), maxReservedStreams());
            Long maxHeaderListSize = initialSettings().maxHeaderListSize();
            Http2FrameReader defaultHttp2FrameReader = new DefaultHttp2FrameReader(maxHeaderListSize == null ? new DefaultHttp2HeadersDecoder(true) : new DefaultHttp2HeadersDecoder(true, maxHeaderListSize.longValue()));
            if (frameLogger() != null) {
                Http2OutboundFrameLogger http2OutboundFrameLogger = new Http2OutboundFrameLogger(http2FrameWriter, frameLogger());
                defaultHttp2FrameReader = new Http2InboundFrameLogger(defaultHttp2FrameReader, frameLogger());
                http2FrameWriter = http2OutboundFrameLogger;
            }
            Http2ConnectionEncoder defaultHttp2ConnectionEncoder = new DefaultHttp2ConnectionEncoder(defaultHttp2Connection, http2FrameWriter);
            if (encoderEnforceMaxConcurrentStreams()) {
                defaultHttp2ConnectionEncoder = new StreamBufferingEncoder(defaultHttp2ConnectionEncoder);
            }
            return build((Http2ConnectionDecoder) new DefaultHttp2ConnectionDecoder(defaultHttp2Connection, defaultHttp2ConnectionEncoder, defaultHttp2FrameReader), defaultHttp2ConnectionEncoder, initialSettings());
        }
        return (Http2FrameCodec) super.build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodec build(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings) {
        return new Http2FrameCodec(http2ConnectionEncoder, http2ConnectionDecoder, http2Settings);
    }
}
