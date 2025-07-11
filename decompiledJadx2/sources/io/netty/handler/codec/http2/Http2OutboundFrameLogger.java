package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http2.Http2FrameLogger;
import io.netty.handler.codec.http2.Http2FrameWriter;
import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class Http2OutboundFrameLogger implements Http2FrameWriter {
    private final Http2FrameLogger logger;
    private final Http2FrameWriter writer;

    public Http2OutboundFrameLogger(Http2FrameWriter http2FrameWriter, Http2FrameLogger http2FrameLogger) {
        this.writer = (Http2FrameWriter) ObjectUtil.checkNotNull(http2FrameWriter, "writer");
        this.logger = (Http2FrameLogger) ObjectUtil.checkNotNull(http2FrameLogger, "logger");
    }

    @Override // io.netty.handler.codec.http2.Http2DataWriter
    public ChannelFuture writeData(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z, ChannelPromise channelPromise) {
        this.logger.logData(Http2FrameLogger.Direction.OUTBOUND, channelHandlerContext, i, byteBuf, i2, z);
        return this.writer.writeData(channelHandlerContext, i, byteBuf, i2, z, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z, ChannelPromise channelPromise) {
        this.logger.logHeaders(Http2FrameLogger.Direction.OUTBOUND, channelHandlerContext, i, http2Headers, i2, z);
        return this.writer.writeHeaders(channelHandlerContext, i, http2Headers, i2, z, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2, ChannelPromise channelPromise) {
        this.logger.logHeaders(Http2FrameLogger.Direction.OUTBOUND, channelHandlerContext, i, http2Headers, i2, s, z, i3, z2);
        return this.writer.writeHeaders(channelHandlerContext, i, http2Headers, i2, s, z, i3, z2, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writePriority(ChannelHandlerContext channelHandlerContext, int i, int i2, short s, boolean z, ChannelPromise channelPromise) {
        this.logger.logPriority(Http2FrameLogger.Direction.OUTBOUND, channelHandlerContext, i, i2, s, z);
        return this.writer.writePriority(channelHandlerContext, i, i2, s, z, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeRstStream(ChannelHandlerContext channelHandlerContext, int i, long j, ChannelPromise channelPromise) {
        this.logger.logRstStream(Http2FrameLogger.Direction.OUTBOUND, channelHandlerContext, i, j);
        return this.writer.writeRstStream(channelHandlerContext, i, j, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeSettings(ChannelHandlerContext channelHandlerContext, Http2Settings http2Settings, ChannelPromise channelPromise) {
        this.logger.logSettings(Http2FrameLogger.Direction.OUTBOUND, channelHandlerContext, http2Settings);
        return this.writer.writeSettings(channelHandlerContext, http2Settings, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeSettingsAck(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
        this.logger.logSettingsAck(Http2FrameLogger.Direction.OUTBOUND, channelHandlerContext);
        return this.writer.writeSettingsAck(channelHandlerContext, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writePing(ChannelHandlerContext channelHandlerContext, boolean z, long j, ChannelPromise channelPromise) {
        if (z) {
            this.logger.logPingAck(Http2FrameLogger.Direction.OUTBOUND, channelHandlerContext, j);
        } else {
            this.logger.logPing(Http2FrameLogger.Direction.OUTBOUND, channelHandlerContext, j);
        }
        return this.writer.writePing(channelHandlerContext, z, j, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writePushPromise(ChannelHandlerContext channelHandlerContext, int i, int i2, Http2Headers http2Headers, int i3, ChannelPromise channelPromise) {
        this.logger.logPushPromise(Http2FrameLogger.Direction.OUTBOUND, channelHandlerContext, i, i2, http2Headers, i3);
        return this.writer.writePushPromise(channelHandlerContext, i, i2, http2Headers, i3, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeGoAway(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf, ChannelPromise channelPromise) {
        this.logger.logGoAway(Http2FrameLogger.Direction.OUTBOUND, channelHandlerContext, i, j, byteBuf);
        return this.writer.writeGoAway(channelHandlerContext, i, j, byteBuf, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeWindowUpdate(ChannelHandlerContext channelHandlerContext, int i, int i2, ChannelPromise channelPromise) {
        this.logger.logWindowsUpdate(Http2FrameLogger.Direction.OUTBOUND, channelHandlerContext, i, i2);
        return this.writer.writeWindowUpdate(channelHandlerContext, i, i2, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeFrame(ChannelHandlerContext channelHandlerContext, byte b, int i, Http2Flags http2Flags, ByteBuf byteBuf, ChannelPromise channelPromise) {
        this.logger.logUnknownFrame(Http2FrameLogger.Direction.OUTBOUND, channelHandlerContext, b, i, http2Flags, byteBuf);
        return this.writer.writeFrame(channelHandlerContext, b, i, http2Flags, byteBuf, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.writer.close();
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public Http2FrameWriter.Configuration configuration() {
        return this.writer.configuration();
    }
}
