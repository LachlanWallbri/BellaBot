package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersEncoder;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public class Http2MultiplexCodecBuilder extends AbstractHttp2ConnectionHandlerBuilder<Http2MultiplexCodec, Http2MultiplexCodecBuilder> {
    final ChannelHandler childHandler;
    private Http2FrameWriter frameWriter;
    private ChannelHandler upgradeStreamHandler;

    Http2MultiplexCodecBuilder(boolean z, ChannelHandler channelHandler) {
        server(z);
        this.childHandler = checkSharable((ChannelHandler) ObjectUtil.checkNotNull(channelHandler, "childHandler"));
        gracefulShutdownTimeoutMillis(0L);
    }

    private static ChannelHandler checkSharable(ChannelHandler channelHandler) {
        if (!(channelHandler instanceof ChannelHandlerAdapter) || ((ChannelHandlerAdapter) channelHandler).isSharable() || channelHandler.getClass().isAnnotationPresent(ChannelHandler.Sharable.class)) {
            return channelHandler;
        }
        throw new IllegalArgumentException("The handler must be Sharable");
    }

    Http2MultiplexCodecBuilder frameWriter(Http2FrameWriter http2FrameWriter) {
        this.frameWriter = (Http2FrameWriter) ObjectUtil.checkNotNull(http2FrameWriter, "frameWriter");
        return this;
    }

    public static Http2MultiplexCodecBuilder forClient(ChannelHandler channelHandler) {
        return new Http2MultiplexCodecBuilder(false, channelHandler);
    }

    public static Http2MultiplexCodecBuilder forServer(ChannelHandler channelHandler) {
        return new Http2MultiplexCodecBuilder(true, channelHandler);
    }

    public Http2MultiplexCodecBuilder withUpgradeStreamHandler(ChannelHandler channelHandler) {
        if (isServer()) {
            throw new IllegalArgumentException("Server codecs don't use an extra handler for the upgrade stream");
        }
        this.upgradeStreamHandler = channelHandler;
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2Settings initialSettings() {
        return super.initialSettings();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder initialSettings(Http2Settings http2Settings) {
        return (Http2MultiplexCodecBuilder) super.initialSettings(http2Settings);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public long gracefulShutdownTimeoutMillis() {
        return super.gracefulShutdownTimeoutMillis();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder gracefulShutdownTimeoutMillis(long j) {
        return (Http2MultiplexCodecBuilder) super.gracefulShutdownTimeoutMillis(j);
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
    public Http2MultiplexCodecBuilder maxReservedStreams(int i) {
        return (Http2MultiplexCodecBuilder) super.maxReservedStreams(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public boolean isValidateHeaders() {
        return super.isValidateHeaders();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder validateHeaders(boolean z) {
        return (Http2MultiplexCodecBuilder) super.validateHeaders(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameLogger frameLogger() {
        return super.frameLogger();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder frameLogger(Http2FrameLogger http2FrameLogger) {
        return (Http2MultiplexCodecBuilder) super.frameLogger(http2FrameLogger);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public boolean encoderEnforceMaxConcurrentStreams() {
        return super.encoderEnforceMaxConcurrentStreams();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder encoderEnforceMaxConcurrentStreams(boolean z) {
        return (Http2MultiplexCodecBuilder) super.encoderEnforceMaxConcurrentStreams(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public int encoderEnforceMaxQueuedControlFrames() {
        return super.encoderEnforceMaxQueuedControlFrames();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder encoderEnforceMaxQueuedControlFrames(int i) {
        return (Http2MultiplexCodecBuilder) super.encoderEnforceMaxQueuedControlFrames(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2HeadersEncoder.SensitivityDetector headerSensitivityDetector() {
        return super.headerSensitivityDetector();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder headerSensitivityDetector(Http2HeadersEncoder.SensitivityDetector sensitivityDetector) {
        return (Http2MultiplexCodecBuilder) super.headerSensitivityDetector(sensitivityDetector);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder encoderIgnoreMaxHeaderListSize(boolean z) {
        return (Http2MultiplexCodecBuilder) super.encoderIgnoreMaxHeaderListSize(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    @Deprecated
    public Http2MultiplexCodecBuilder initialHuffmanDecodeCapacity(int i) {
        return (Http2MultiplexCodecBuilder) super.initialHuffmanDecodeCapacity(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder autoAckSettingsFrame(boolean z) {
        return (Http2MultiplexCodecBuilder) super.autoAckSettingsFrame(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder autoAckPingFrame(boolean z) {
        return (Http2MultiplexCodecBuilder) super.autoAckPingFrame(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder decoupleCloseAndGoAway(boolean z) {
        return (Http2MultiplexCodecBuilder) super.decoupleCloseAndGoAway(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public int decoderEnforceMaxConsecutiveEmptyDataFrames() {
        return super.decoderEnforceMaxConsecutiveEmptyDataFrames();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder decoderEnforceMaxConsecutiveEmptyDataFrames(int i) {
        return (Http2MultiplexCodecBuilder) super.decoderEnforceMaxConsecutiveEmptyDataFrames(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodec build() {
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
        return (Http2MultiplexCodec) super.build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodec build(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings) {
        Http2MultiplexCodec http2MultiplexCodec = new Http2MultiplexCodec(http2ConnectionEncoder, http2ConnectionDecoder, http2Settings, this.childHandler, this.upgradeStreamHandler, decoupleCloseAndGoAway());
        http2MultiplexCodec.gracefulShutdownTimeoutMillis(gracefulShutdownTimeoutMillis());
        return http2MultiplexCodec;
    }
}
