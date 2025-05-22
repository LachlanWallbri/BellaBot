package io.netty.handler.codec.http2;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.handler.codec.http2.Http2HeadersEncoder;
import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class Http2MultiplexCodecBuilder extends AbstractHttp2ConnectionHandlerBuilder<Http2MultiplexCodec, Http2MultiplexCodecBuilder> {
    final ChannelHandler childHandler;

    Http2MultiplexCodecBuilder(boolean z, ChannelHandler channelHandler) {
        server(z);
        this.childHandler = checkSharable((ChannelHandler) ObjectUtil.checkNotNull(channelHandler, "childHandler"));
    }

    private static ChannelHandler checkSharable(ChannelHandler channelHandler) {
        if (!(channelHandler instanceof ChannelHandlerAdapter) || ((ChannelHandlerAdapter) channelHandler).isSharable() || channelHandler.getClass().isAnnotationPresent(ChannelHandler.Sharable.class)) {
            return channelHandler;
        }
        throw new IllegalArgumentException("The handler must be Sharable");
    }

    public static Http2MultiplexCodecBuilder forClient(ChannelHandler channelHandler) {
        return new Http2MultiplexCodecBuilder(false, channelHandler);
    }

    public static Http2MultiplexCodecBuilder forServer(ChannelHandler channelHandler) {
        return new Http2MultiplexCodecBuilder(true, channelHandler);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2Settings initialSettings() {
        return super.initialSettings();
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder initialSettings(Http2Settings http2Settings) {
        return (Http2MultiplexCodecBuilder) super.initialSettings(http2Settings);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public long gracefulShutdownTimeoutMillis() {
        return super.gracefulShutdownTimeoutMillis();
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder gracefulShutdownTimeoutMillis(long j) {
        return (Http2MultiplexCodecBuilder) super.gracefulShutdownTimeoutMillis(j);
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
    public Http2MultiplexCodecBuilder maxReservedStreams(int i) {
        return (Http2MultiplexCodecBuilder) super.maxReservedStreams(i);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public boolean isValidateHeaders() {
        return super.isValidateHeaders();
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder validateHeaders(boolean z) {
        return (Http2MultiplexCodecBuilder) super.validateHeaders(z);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2FrameLogger frameLogger() {
        return super.frameLogger();
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder frameLogger(Http2FrameLogger http2FrameLogger) {
        return (Http2MultiplexCodecBuilder) super.frameLogger(http2FrameLogger);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public boolean encoderEnforceMaxConcurrentStreams() {
        return super.encoderEnforceMaxConcurrentStreams();
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder encoderEnforceMaxConcurrentStreams(boolean z) {
        return (Http2MultiplexCodecBuilder) super.encoderEnforceMaxConcurrentStreams(z);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2HeadersEncoder.SensitivityDetector headerSensitivityDetector() {
        return super.headerSensitivityDetector();
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder headerSensitivityDetector(Http2HeadersEncoder.SensitivityDetector sensitivityDetector) {
        return (Http2MultiplexCodecBuilder) super.headerSensitivityDetector(sensitivityDetector);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder encoderIgnoreMaxHeaderListSize(boolean z) {
        return (Http2MultiplexCodecBuilder) super.encoderIgnoreMaxHeaderListSize(z);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodecBuilder initialHuffmanDecodeCapacity(int i) {
        return (Http2MultiplexCodecBuilder) super.initialHuffmanDecodeCapacity(i);
    }

    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodec build() {
        return (Http2MultiplexCodec) super.build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public Http2MultiplexCodec build(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings) {
        return new Http2MultiplexCodec(http2ConnectionEncoder, http2ConnectionDecoder, http2Settings, this.childHandler);
    }
}
