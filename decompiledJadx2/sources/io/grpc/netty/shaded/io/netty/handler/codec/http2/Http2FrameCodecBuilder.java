package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersEncoder;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Http2FrameCodecBuilder extends AbstractHttp2ConnectionHandlerBuilder<Http2FrameCodec, Http2FrameCodecBuilder> {
    private Http2FrameWriter frameWriter;

    Http2FrameCodecBuilder(boolean z) {
        server(z);
        gracefulShutdownTimeoutMillis(0L);
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

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2Settings initialSettings() {
        return super.initialSettings();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder initialSettings(Http2Settings http2Settings) {
        return (Http2FrameCodecBuilder) super.initialSettings(http2Settings);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public long gracefulShutdownTimeoutMillis() {
        return super.gracefulShutdownTimeoutMillis();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder gracefulShutdownTimeoutMillis(long j) {
        return (Http2FrameCodecBuilder) super.gracefulShutdownTimeoutMillis(j);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public boolean isServer() {
        return super.isServer();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public int maxReservedStreams() {
        return super.maxReservedStreams();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder maxReservedStreams(int i) {
        return (Http2FrameCodecBuilder) super.maxReservedStreams(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public boolean isValidateHeaders() {
        return super.isValidateHeaders();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder validateHeaders(boolean z) {
        return (Http2FrameCodecBuilder) super.validateHeaders(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameLogger frameLogger() {
        return super.frameLogger();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder frameLogger(Http2FrameLogger http2FrameLogger) {
        return (Http2FrameCodecBuilder) super.frameLogger(http2FrameLogger);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public boolean encoderEnforceMaxConcurrentStreams() {
        return super.encoderEnforceMaxConcurrentStreams();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder encoderEnforceMaxConcurrentStreams(boolean z) {
        return (Http2FrameCodecBuilder) super.encoderEnforceMaxConcurrentStreams(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public int encoderEnforceMaxQueuedControlFrames() {
        return super.encoderEnforceMaxQueuedControlFrames();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder encoderEnforceMaxQueuedControlFrames(int i) {
        return (Http2FrameCodecBuilder) super.encoderEnforceMaxQueuedControlFrames(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2HeadersEncoder.SensitivityDetector headerSensitivityDetector() {
        return super.headerSensitivityDetector();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder headerSensitivityDetector(Http2HeadersEncoder.SensitivityDetector sensitivityDetector) {
        return (Http2FrameCodecBuilder) super.headerSensitivityDetector(sensitivityDetector);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder encoderIgnoreMaxHeaderListSize(boolean z) {
        return (Http2FrameCodecBuilder) super.encoderIgnoreMaxHeaderListSize(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    @Deprecated
    public Http2FrameCodecBuilder initialHuffmanDecodeCapacity(int i) {
        return (Http2FrameCodecBuilder) super.initialHuffmanDecodeCapacity(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder autoAckSettingsFrame(boolean z) {
        return (Http2FrameCodecBuilder) super.autoAckSettingsFrame(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder autoAckPingFrame(boolean z) {
        return (Http2FrameCodecBuilder) super.autoAckPingFrame(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder decoupleCloseAndGoAway(boolean z) {
        return (Http2FrameCodecBuilder) super.decoupleCloseAndGoAway(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public int decoderEnforceMaxConsecutiveEmptyDataFrames() {
        return super.decoderEnforceMaxConsecutiveEmptyDataFrames();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodecBuilder decoderEnforceMaxConsecutiveEmptyDataFrames(int i) {
        return (Http2FrameCodecBuilder) super.decoderEnforceMaxConsecutiveEmptyDataFrames(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodec build() {
        DefaultHttp2HeadersDecoder defaultHttp2HeadersDecoder;
        Http2FrameReader http2FrameReader;
        Http2FrameWriter http2FrameWriter = this.frameWriter;
        if (http2FrameWriter != null) {
            DefaultHttp2Connection defaultHttp2Connection = new DefaultHttp2Connection(isServer(), maxReservedStreams());
            Long maxHeaderListSize = initialSettings().maxHeaderListSize();
            if (maxHeaderListSize == null) {
                defaultHttp2HeadersDecoder = new DefaultHttp2HeadersDecoder(isValidateHeaders());
            } else {
                defaultHttp2HeadersDecoder = new DefaultHttp2HeadersDecoder(isValidateHeaders(), maxHeaderListSize.longValue());
            }
            Http2FrameReader defaultHttp2FrameReader = new DefaultHttp2FrameReader(defaultHttp2HeadersDecoder);
            if (frameLogger() != null) {
                Http2OutboundFrameLogger http2OutboundFrameLogger = new Http2OutboundFrameLogger(http2FrameWriter, frameLogger());
                http2FrameReader = new Http2InboundFrameLogger(defaultHttp2FrameReader, frameLogger());
                http2FrameWriter = http2OutboundFrameLogger;
            } else {
                http2FrameReader = defaultHttp2FrameReader;
            }
            DefaultHttp2ConnectionEncoder defaultHttp2ConnectionEncoder = new DefaultHttp2ConnectionEncoder(defaultHttp2Connection, http2FrameWriter);
            Http2ConnectionEncoder streamBufferingEncoder = encoderEnforceMaxConcurrentStreams() ? new StreamBufferingEncoder(defaultHttp2ConnectionEncoder) : defaultHttp2ConnectionEncoder;
            DefaultHttp2ConnectionDecoder defaultHttp2ConnectionDecoder = new DefaultHttp2ConnectionDecoder(defaultHttp2Connection, streamBufferingEncoder, http2FrameReader, promisedRequestVerifier(), isAutoAckSettingsFrame(), isAutoAckPingFrame());
            int decoderEnforceMaxConsecutiveEmptyDataFrames = decoderEnforceMaxConsecutiveEmptyDataFrames();
            return build(decoderEnforceMaxConsecutiveEmptyDataFrames > 0 ? new Http2EmptyDataFrameConnectionDecoder(defaultHttp2ConnectionDecoder, decoderEnforceMaxConsecutiveEmptyDataFrames) : defaultHttp2ConnectionDecoder, streamBufferingEncoder, initialSettings());
        }
        return (Http2FrameCodec) super.build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameCodec build(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings) {
        Http2FrameCodec http2FrameCodec = new Http2FrameCodec(http2ConnectionEncoder, http2ConnectionDecoder, http2Settings, decoupleCloseAndGoAway());
        http2FrameCodec.gracefulShutdownTimeoutMillis(gracefulShutdownTimeoutMillis());
        return http2FrameCodec;
    }
}
