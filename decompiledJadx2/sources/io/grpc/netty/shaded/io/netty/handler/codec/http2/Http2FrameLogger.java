package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.logging.LogLevel;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogLevel;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Http2FrameLogger extends ChannelHandlerAdapter {
    private static final int BUFFER_LENGTH_THRESHOLD = 64;
    private final InternalLogLevel level;
    private final InternalLogger logger;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum Direction {
        INBOUND,
        OUTBOUND
    }

    public Http2FrameLogger(LogLevel logLevel) {
        this(checkAndConvertLevel(logLevel), InternalLoggerFactory.getInstance((Class<?>) Http2FrameLogger.class));
    }

    public Http2FrameLogger(LogLevel logLevel, String str) {
        this(checkAndConvertLevel(logLevel), InternalLoggerFactory.getInstance((String) ObjectUtil.checkNotNull(str, "name")));
    }

    public Http2FrameLogger(LogLevel logLevel, Class<?> cls) {
        this(checkAndConvertLevel(logLevel), InternalLoggerFactory.getInstance((Class<?>) ObjectUtil.checkNotNull(cls, "clazz")));
    }

    private Http2FrameLogger(InternalLogLevel internalLogLevel, InternalLogger internalLogger) {
        this.level = internalLogLevel;
        this.logger = internalLogger;
    }

    private static InternalLogLevel checkAndConvertLevel(LogLevel logLevel) {
        return ((LogLevel) ObjectUtil.checkNotNull(logLevel, "level")).toInternalLevel();
    }

    public boolean isEnabled() {
        return this.logger.isEnabled(this.level);
    }

    public void logData(Direction direction, ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z) {
        if (isEnabled()) {
            this.logger.log(this.level, "{} {} DATA: streamId={} padding={} endStream={} length={} bytes={}", channelHandlerContext.channel(), direction.name(), Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(z), Integer.valueOf(byteBuf.readableBytes()), toString(byteBuf));
        }
    }

    public void logHeaders(Direction direction, ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z) {
        if (isEnabled()) {
            this.logger.log(this.level, "{} {} HEADERS: streamId={} headers={} padding={} endStream={}", channelHandlerContext.channel(), direction.name(), Integer.valueOf(i), http2Headers, Integer.valueOf(i2), Boolean.valueOf(z));
        }
    }

    public void logHeaders(Direction direction, ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2) {
        if (isEnabled()) {
            this.logger.log(this.level, "{} {} HEADERS: streamId={} headers={} streamDependency={} weight={} exclusive={} padding={} endStream={}", channelHandlerContext.channel(), direction.name(), Integer.valueOf(i), http2Headers, Integer.valueOf(i2), Short.valueOf(s), Boolean.valueOf(z), Integer.valueOf(i3), Boolean.valueOf(z2));
        }
    }

    public void logPriority(Direction direction, ChannelHandlerContext channelHandlerContext, int i, int i2, short s, boolean z) {
        if (isEnabled()) {
            this.logger.log(this.level, "{} {} PRIORITY: streamId={} streamDependency={} weight={} exclusive={}", channelHandlerContext.channel(), direction.name(), Integer.valueOf(i), Integer.valueOf(i2), Short.valueOf(s), Boolean.valueOf(z));
        }
    }

    public void logRstStream(Direction direction, ChannelHandlerContext channelHandlerContext, int i, long j) {
        if (isEnabled()) {
            this.logger.log(this.level, "{} {} RST_STREAM: streamId={} errorCode={}", channelHandlerContext.channel(), direction.name(), Integer.valueOf(i), Long.valueOf(j));
        }
    }

    public void logSettingsAck(Direction direction, ChannelHandlerContext channelHandlerContext) {
        this.logger.log(this.level, "{} {} SETTINGS: ack=true", channelHandlerContext.channel(), direction.name());
    }

    public void logSettings(Direction direction, ChannelHandlerContext channelHandlerContext, Http2Settings http2Settings) {
        if (isEnabled()) {
            this.logger.log(this.level, "{} {} SETTINGS: ack=false settings={}", channelHandlerContext.channel(), direction.name(), http2Settings);
        }
    }

    public void logPing(Direction direction, ChannelHandlerContext channelHandlerContext, long j) {
        if (isEnabled()) {
            this.logger.log(this.level, "{} {} PING: ack=false bytes={}", channelHandlerContext.channel(), direction.name(), Long.valueOf(j));
        }
    }

    public void logPingAck(Direction direction, ChannelHandlerContext channelHandlerContext, long j) {
        if (isEnabled()) {
            this.logger.log(this.level, "{} {} PING: ack=true bytes={}", channelHandlerContext.channel(), direction.name(), Long.valueOf(j));
        }
    }

    public void logPushPromise(Direction direction, ChannelHandlerContext channelHandlerContext, int i, int i2, Http2Headers http2Headers, int i3) {
        if (isEnabled()) {
            this.logger.log(this.level, "{} {} PUSH_PROMISE: streamId={} promisedStreamId={} headers={} padding={}", channelHandlerContext.channel(), direction.name(), Integer.valueOf(i), Integer.valueOf(i2), http2Headers, Integer.valueOf(i3));
        }
    }

    public void logGoAway(Direction direction, ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf) {
        if (isEnabled()) {
            this.logger.log(this.level, "{} {} GO_AWAY: lastStreamId={} errorCode={} length={} bytes={}", channelHandlerContext.channel(), direction.name(), Integer.valueOf(i), Long.valueOf(j), Integer.valueOf(byteBuf.readableBytes()), toString(byteBuf));
        }
    }

    public void logWindowsUpdate(Direction direction, ChannelHandlerContext channelHandlerContext, int i, int i2) {
        if (isEnabled()) {
            this.logger.log(this.level, "{} {} WINDOW_UPDATE: streamId={} windowSizeIncrement={}", channelHandlerContext.channel(), direction.name(), Integer.valueOf(i), Integer.valueOf(i2));
        }
    }

    public void logUnknownFrame(Direction direction, ChannelHandlerContext channelHandlerContext, byte b, int i, Http2Flags http2Flags, ByteBuf byteBuf) {
        if (isEnabled()) {
            this.logger.log(this.level, "{} {} UNKNOWN: frameType={} streamId={} flags={} length={} bytes={}", channelHandlerContext.channel(), direction.name(), Integer.valueOf(b & 255), Integer.valueOf(i), Short.valueOf(http2Flags.value()), Integer.valueOf(byteBuf.readableBytes()), toString(byteBuf));
        }
    }

    private String toString(ByteBuf byteBuf) {
        if (this.level == InternalLogLevel.TRACE || byteBuf.readableBytes() <= 64) {
            return ByteBufUtil.hexDump(byteBuf);
        }
        return ByteBufUtil.hexDump(byteBuf, byteBuf.readerIndex(), Math.min(byteBuf.readableBytes(), 64)) + "...";
    }
}
