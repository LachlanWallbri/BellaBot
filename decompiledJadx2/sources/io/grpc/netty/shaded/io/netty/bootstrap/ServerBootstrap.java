package io.grpc.netty.shaded.io.netty.bootstrap;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.channel.ChannelInitializer;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.ServerChannel;
import io.grpc.netty.shaded.io.netty.util.AttributeKey;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class ServerBootstrap extends AbstractBootstrap<ServerBootstrap, ServerChannel> {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ServerBootstrap.class);
    private final Map<AttributeKey<?>, Object> childAttrs;
    private volatile EventLoopGroup childGroup;
    private volatile ChannelHandler childHandler;
    private final Map<ChannelOption<?>, Object> childOptions;
    private final ServerBootstrapConfig config;

    public ServerBootstrap() {
        this.childOptions = new LinkedHashMap();
        this.childAttrs = new ConcurrentHashMap();
        this.config = new ServerBootstrapConfig(this);
    }

    private ServerBootstrap(ServerBootstrap serverBootstrap) {
        super(serverBootstrap);
        this.childOptions = new LinkedHashMap();
        this.childAttrs = new ConcurrentHashMap();
        this.config = new ServerBootstrapConfig(this);
        this.childGroup = serverBootstrap.childGroup;
        this.childHandler = serverBootstrap.childHandler;
        synchronized (serverBootstrap.childOptions) {
            this.childOptions.putAll(serverBootstrap.childOptions);
        }
        this.childAttrs.putAll(serverBootstrap.childAttrs);
    }

    @Override // io.grpc.netty.shaded.io.netty.bootstrap.AbstractBootstrap
    public ServerBootstrap group(EventLoopGroup eventLoopGroup) {
        return group(eventLoopGroup, eventLoopGroup);
    }

    public ServerBootstrap group(EventLoopGroup eventLoopGroup, EventLoopGroup eventLoopGroup2) {
        super.group(eventLoopGroup);
        if (this.childGroup != null) {
            throw new IllegalStateException("childGroup set already");
        }
        this.childGroup = (EventLoopGroup) ObjectUtil.checkNotNull(eventLoopGroup2, "childGroup");
        return this;
    }

    public <T> ServerBootstrap childOption(ChannelOption<T> channelOption, T t) {
        ObjectUtil.checkNotNull(channelOption, "childOption");
        synchronized (this.childOptions) {
            if (t == null) {
                this.childOptions.remove(channelOption);
            } else {
                this.childOptions.put(channelOption, t);
            }
        }
        return this;
    }

    public <T> ServerBootstrap childAttr(AttributeKey<T> attributeKey, T t) {
        ObjectUtil.checkNotNull(attributeKey, "childKey");
        if (t == null) {
            this.childAttrs.remove(attributeKey);
        } else {
            this.childAttrs.put(attributeKey, t);
        }
        return this;
    }

    public ServerBootstrap childHandler(ChannelHandler channelHandler) {
        this.childHandler = (ChannelHandler) ObjectUtil.checkNotNull(channelHandler, "childHandler");
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.bootstrap.AbstractBootstrap
    void init(Channel channel) {
        final Map.Entry[] entryArr;
        setChannelOptions(channel, newOptionsArray(), logger);
        setAttributes(channel, (Map.Entry[]) attrs0().entrySet().toArray(EMPTY_ATTRIBUTE_ARRAY));
        ChannelPipeline pipeline = channel.pipeline();
        final EventLoopGroup eventLoopGroup = this.childGroup;
        final ChannelHandler channelHandler = this.childHandler;
        synchronized (this.childOptions) {
            entryArr = (Map.Entry[]) this.childOptions.entrySet().toArray(EMPTY_OPTION_ARRAY);
        }
        final Map.Entry[] entryArr2 = (Map.Entry[]) this.childAttrs.entrySet().toArray(EMPTY_ATTRIBUTE_ARRAY);
        pipeline.addLast(new ChannelInitializer<Channel>() { // from class: io.grpc.netty.shaded.io.netty.bootstrap.ServerBootstrap.1
            @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInitializer
            public void initChannel(final Channel channel2) {
                final ChannelPipeline pipeline2 = channel2.pipeline();
                ChannelHandler handler = ServerBootstrap.this.config.handler();
                if (handler != null) {
                    pipeline2.addLast(handler);
                }
                channel2.eventLoop().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.bootstrap.ServerBootstrap.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        pipeline2.addLast(new ServerBootstrapAcceptor(channel2, eventLoopGroup, channelHandler, entryArr, entryArr2));
                    }
                });
            }
        });
    }

    @Override // io.grpc.netty.shaded.io.netty.bootstrap.AbstractBootstrap
    public ServerBootstrap validate() {
        super.validate();
        if (this.childHandler == null) {
            throw new IllegalStateException("childHandler not set");
        }
        if (this.childGroup == null) {
            logger.warn("childGroup is not set. Using parentGroup instead.");
            this.childGroup = this.config.group();
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class ServerBootstrapAcceptor extends ChannelInboundHandlerAdapter {
        private final Map.Entry<AttributeKey<?>, Object>[] childAttrs;
        private final EventLoopGroup childGroup;
        private final ChannelHandler childHandler;
        private final Map.Entry<ChannelOption<?>, Object>[] childOptions;
        private final Runnable enableAutoReadTask;

        ServerBootstrapAcceptor(final Channel channel, EventLoopGroup eventLoopGroup, ChannelHandler channelHandler, Map.Entry<ChannelOption<?>, Object>[] entryArr, Map.Entry<AttributeKey<?>, Object>[] entryArr2) {
            this.childGroup = eventLoopGroup;
            this.childHandler = channelHandler;
            this.childOptions = entryArr;
            this.childAttrs = entryArr2;
            this.enableAutoReadTask = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.bootstrap.ServerBootstrap.ServerBootstrapAcceptor.1
                @Override // java.lang.Runnable
                public void run() {
                    channel.config().setAutoRead(true);
                }
            };
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) {
            final Channel channel = (Channel) obj;
            channel.pipeline().addLast(this.childHandler);
            AbstractBootstrap.setChannelOptions(channel, this.childOptions, ServerBootstrap.logger);
            AbstractBootstrap.setAttributes(channel, this.childAttrs);
            try {
                this.childGroup.register(channel).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.bootstrap.ServerBootstrap.ServerBootstrapAcceptor.2
                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (channelFuture.isSuccess()) {
                            return;
                        }
                        ServerBootstrapAcceptor.forceClose(channel, channelFuture.cause());
                    }
                });
            } catch (Throwable th) {
                forceClose(channel, th);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void forceClose(Channel channel, Throwable th) {
            channel.unsafe().closeForcibly();
            ServerBootstrap.logger.warn("Failed to register an accepted channel: {}", channel, th);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
            ChannelConfig config = channelHandlerContext.channel().config();
            if (config.isAutoRead()) {
                config.setAutoRead(false);
                channelHandlerContext.channel().eventLoop().schedule(this.enableAutoReadTask, 1L, TimeUnit.SECONDS);
            }
            channelHandlerContext.fireExceptionCaught(th);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.bootstrap.AbstractBootstrap
    /* renamed from: clone, reason: avoid collision after fix types in other method */
    public ServerBootstrap mo4504clone() {
        return new ServerBootstrap(this);
    }

    @Deprecated
    public EventLoopGroup childGroup() {
        return this.childGroup;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ChannelHandler childHandler() {
        return this.childHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map<ChannelOption<?>, Object> childOptions() {
        Map<ChannelOption<?>, Object> copiedMap;
        synchronized (this.childOptions) {
            copiedMap = copiedMap(this.childOptions);
        }
        return copiedMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map<AttributeKey<?>, Object> childAttrs() {
        return copiedMap(this.childAttrs);
    }

    @Override // io.grpc.netty.shaded.io.netty.bootstrap.AbstractBootstrap
    public final AbstractBootstrapConfig<ServerBootstrap, ServerChannel> config() {
        return this.config;
    }
}
