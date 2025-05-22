package io.netty.handler.codec.http2;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.channels.ClosedChannelException;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class Http2StreamChannelBootstrap {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) Http2StreamChannelBootstrap.class);
    private final Channel channel;
    private volatile ChannelHandler handler;
    private final Map<ChannelOption<?>, Object> options = new LinkedHashMap();
    private final Map<AttributeKey<?>, Object> attrs = new LinkedHashMap();

    public Http2StreamChannelBootstrap(Channel channel) {
        this.channel = (Channel) ObjectUtil.checkNotNull(channel, "channel");
    }

    public <T> Http2StreamChannelBootstrap option(ChannelOption<T> channelOption, T t) {
        if (channelOption == null) {
            throw new NullPointerException("option");
        }
        if (t == null) {
            synchronized (this.options) {
                this.options.remove(channelOption);
            }
        } else {
            synchronized (this.options) {
                this.options.put(channelOption, t);
            }
        }
        return this;
    }

    public <T> Http2StreamChannelBootstrap attr(AttributeKey<T> attributeKey, T t) {
        if (attributeKey == null) {
            throw new NullPointerException(TransferTable.COLUMN_KEY);
        }
        if (t == null) {
            synchronized (this.attrs) {
                this.attrs.remove(attributeKey);
            }
        } else {
            synchronized (this.attrs) {
                this.attrs.put(attributeKey, t);
            }
        }
        return this;
    }

    public Http2StreamChannelBootstrap handler(ChannelHandler channelHandler) {
        this.handler = (ChannelHandler) ObjectUtil.checkNotNull(channelHandler, "handler");
        return this;
    }

    public Future<Http2StreamChannel> open() {
        return open(this.channel.eventLoop().newPromise());
    }

    public Future<Http2StreamChannel> open(final Promise<Http2StreamChannel> promise) {
        final ChannelHandlerContext context = this.channel.pipeline().context(Http2MultiplexCodec.class);
        if (context == null) {
            if (this.channel.isActive()) {
                promise.setFailure(new IllegalStateException(StringUtil.simpleClassName((Class<?>) Http2MultiplexCodec.class) + " must be in the ChannelPipeline of Channel " + this.channel));
            } else {
                promise.setFailure(new ClosedChannelException());
            }
        } else {
            EventExecutor executor = context.executor();
            if (executor.inEventLoop()) {
                open0(context, promise);
            } else {
                executor.execute(new Runnable() { // from class: io.netty.handler.codec.http2.Http2StreamChannelBootstrap.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Http2StreamChannelBootstrap.this.open0(context, promise);
                    }
                });
            }
        }
        return promise;
    }

    public void open0(ChannelHandlerContext channelHandlerContext, final Promise<Http2StreamChannel> promise) {
        final Http2StreamChannel newOutboundStream = ((Http2MultiplexCodec) channelHandlerContext.handler()).newOutboundStream();
        try {
            init(newOutboundStream);
            channelHandlerContext.channel().eventLoop().register(newOutboundStream).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.http2.Http2StreamChannelBootstrap.2
                @Override // io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        promise.setSuccess(newOutboundStream);
                        return;
                    }
                    if (channelFuture.isCancelled()) {
                        promise.cancel(false);
                        return;
                    }
                    if (newOutboundStream.isRegistered()) {
                        newOutboundStream.close();
                    } else {
                        newOutboundStream.unsafe().closeForcibly();
                    }
                    promise.setFailure(channelFuture.cause());
                }
            });
        } catch (Exception e) {
            newOutboundStream.unsafe().closeForcibly();
            promise.setFailure(e);
        }
    }

    private void init(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        ChannelHandler channelHandler = this.handler;
        if (channelHandler != null) {
            pipeline.addLast(channelHandler);
        }
        synchronized (this.options) {
            setChannelOptions(channel, this.options, logger);
        }
        synchronized (this.attrs) {
            for (Map.Entry<AttributeKey<?>, Object> entry : this.attrs.entrySet()) {
                channel.attr(entry.getKey()).set(entry.getValue());
            }
        }
    }

    private static void setChannelOptions(Channel channel, Map<ChannelOption<?>, Object> map, InternalLogger internalLogger) {
        for (Map.Entry<ChannelOption<?>, Object> entry : map.entrySet()) {
            setChannelOption(channel, entry.getKey(), entry.getValue(), internalLogger);
        }
    }

    private static void setChannelOption(Channel channel, ChannelOption<?> channelOption, Object obj, InternalLogger internalLogger) {
        try {
            if (channel.config().setOption(channelOption, obj)) {
                return;
            }
            internalLogger.warn("Unknown channel option '{}' for channel '{}'", channelOption, channel);
        } catch (Throwable th) {
            internalLogger.warn("Failed to set channel option '{}' with value '{}' for channel '{}'", channelOption, obj, channel, th);
        }
    }
}
