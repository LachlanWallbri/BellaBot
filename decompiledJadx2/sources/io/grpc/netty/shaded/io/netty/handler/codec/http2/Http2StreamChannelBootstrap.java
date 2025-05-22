package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.util.AttributeKey;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.Promise;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.channels.ClosedChannelException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Http2StreamChannelBootstrap {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Channel channel;
    private volatile ChannelHandler handler;
    private volatile ChannelHandlerContext multiplexCtx;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) Http2StreamChannelBootstrap.class);
    private static final Map.Entry<ChannelOption<?>, Object>[] EMPTY_OPTION_ARRAY = new Map.Entry[0];
    private static final Map.Entry<AttributeKey<?>, Object>[] EMPTY_ATTRIBUTE_ARRAY = new Map.Entry[0];
    private final Map<ChannelOption<?>, Object> options = new LinkedHashMap();
    private final Map<AttributeKey<?>, Object> attrs = new ConcurrentHashMap();

    public Http2StreamChannelBootstrap(Channel channel) {
        this.channel = (Channel) ObjectUtil.checkNotNull(channel, "channel");
    }

    public <T> Http2StreamChannelBootstrap option(ChannelOption<T> channelOption, T t) {
        ObjectUtil.checkNotNull(channelOption, "option");
        synchronized (this.options) {
            if (t == null) {
                this.options.remove(channelOption);
            } else {
                this.options.put(channelOption, t);
            }
        }
        return this;
    }

    public <T> Http2StreamChannelBootstrap attr(AttributeKey<T> attributeKey, T t) {
        ObjectUtil.checkNotNull(attributeKey, TransferTable.COLUMN_KEY);
        if (t == null) {
            this.attrs.remove(attributeKey);
        } else {
            this.attrs.put(attributeKey, t);
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
        try {
            final ChannelHandlerContext findCtx = findCtx();
            EventExecutor executor = findCtx.executor();
            if (executor.inEventLoop()) {
                open0(findCtx, promise);
            } else {
                executor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamChannelBootstrap.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (Http2StreamChannelBootstrap.this.channel.isActive()) {
                            Http2StreamChannelBootstrap.this.open0(findCtx, promise);
                        } else {
                            promise.setFailure(new ClosedChannelException());
                        }
                    }
                });
            }
        } catch (Throwable th) {
            promise.setFailure(th);
        }
        return promise;
    }

    private ChannelHandlerContext findCtx() throws ClosedChannelException {
        ChannelHandlerContext channelHandlerContext = this.multiplexCtx;
        if (channelHandlerContext != null && !channelHandlerContext.isRemoved()) {
            return channelHandlerContext;
        }
        ChannelPipeline pipeline = this.channel.pipeline();
        ChannelHandlerContext context = pipeline.context(Http2MultiplexCodec.class);
        if (context == null) {
            context = pipeline.context(Http2MultiplexHandler.class);
        }
        if (context == null) {
            if (this.channel.isActive()) {
                throw new IllegalStateException(StringUtil.simpleClassName((Class<?>) Http2MultiplexCodec.class) + " or " + StringUtil.simpleClassName((Class<?>) Http2MultiplexHandler.class) + " must be in the ChannelPipeline of Channel " + this.channel);
            }
            throw new ClosedChannelException();
        }
        this.multiplexCtx = context;
        return context;
    }

    @Deprecated
    public void open0(ChannelHandlerContext channelHandlerContext, final Promise<Http2StreamChannel> promise) {
        final Http2StreamChannel newOutboundStream;
        if (promise.setUncancellable()) {
            try {
                if (channelHandlerContext.handler() instanceof Http2MultiplexCodec) {
                    newOutboundStream = ((Http2MultiplexCodec) channelHandlerContext.handler()).newOutboundStream();
                } else {
                    newOutboundStream = ((Http2MultiplexHandler) channelHandlerContext.handler()).newOutboundStream();
                }
                try {
                    init(newOutboundStream);
                    channelHandlerContext.channel().eventLoop().register(newOutboundStream).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamChannelBootstrap.2
                        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                        public void operationComplete(ChannelFuture channelFuture) {
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
            } catch (Exception e2) {
                promise.setFailure(e2);
            }
        }
    }

    private void init(Channel channel) {
        Map.Entry[] entryArr;
        ChannelPipeline pipeline = channel.pipeline();
        ChannelHandler channelHandler = this.handler;
        if (channelHandler != null) {
            pipeline.addLast(channelHandler);
        }
        synchronized (this.options) {
            entryArr = (Map.Entry[]) this.options.entrySet().toArray(EMPTY_OPTION_ARRAY);
        }
        setChannelOptions(channel, entryArr);
        setAttributes(channel, (Map.Entry[]) this.attrs.entrySet().toArray(EMPTY_ATTRIBUTE_ARRAY));
    }

    private static void setChannelOptions(Channel channel, Map.Entry<ChannelOption<?>, Object>[] entryArr) {
        for (Map.Entry<ChannelOption<?>, Object> entry : entryArr) {
            setChannelOption(channel, entry.getKey(), entry.getValue());
        }
    }

    private static void setChannelOption(Channel channel, ChannelOption<?> channelOption, Object obj) {
        try {
            if (channel.config().setOption(channelOption, obj)) {
                return;
            }
            logger.warn("Unknown channel option '{}' for channel '{}'", channelOption, channel);
        } catch (Throwable th) {
            logger.warn("Failed to set channel option '{}' with value '{}' for channel '{}'", channelOption, obj, channel, th);
        }
    }

    private static void setAttributes(Channel channel, Map.Entry<AttributeKey<?>, Object>[] entryArr) {
        for (Map.Entry<AttributeKey<?>, Object> entry : entryArr) {
            channel.attr(entry.getKey()).set(entry.getValue());
        }
    }
}
