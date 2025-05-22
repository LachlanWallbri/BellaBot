package io.netty.channel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.concurrent.ConcurrentMap;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
@ChannelHandler.Sharable
/* loaded from: classes.dex */
public abstract class ChannelInitializer<C extends Channel> extends ChannelInboundHandlerAdapter {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ChannelInitializer.class);
    private final ConcurrentMap<ChannelHandlerContext, Boolean> initMap = PlatformDependent.newConcurrentHashMap();

    protected abstract void initChannel(C c) throws Exception;

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public final void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (initChannel(channelHandlerContext)) {
            channelHandlerContext.pipeline().fireChannelRegistered();
        } else {
            channelHandlerContext.fireChannelRegistered();
        }
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        logger.warn("Failed to initialize a channel. Closing: " + channelHandlerContext.channel(), th);
        channelHandlerContext.close();
    }

    @Override // io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (channelHandlerContext.channel().isRegistered()) {
            initChannel(channelHandlerContext);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean initChannel(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.initMap.putIfAbsent(channelHandlerContext, Boolean.TRUE) != null) {
            return false;
        }
        try {
            initChannel((ChannelInitializer<C>) channelHandlerContext.channel());
        } finally {
            try {
                return true;
            } finally {
            }
        }
        return true;
    }

    private void remove(ChannelHandlerContext channelHandlerContext) {
        try {
            ChannelPipeline pipeline = channelHandlerContext.pipeline();
            if (pipeline.context(this) != null) {
                pipeline.remove(this);
            }
        } finally {
            this.initMap.remove(channelHandlerContext);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* renamed from: io.netty.channel.ChannelInitializer$1 */
    /* loaded from: classes5.dex */
    class RunnableC69571 implements Runnable {
        final /* synthetic */ ChannelHandlerContext val$ctx;

        RunnableC69571(ChannelHandlerContext channelHandlerContext) {
            this.val$ctx = channelHandlerContext;
        }

        @Override // java.lang.Runnable
        public void run() {
            ChannelInitializer.access$000(ChannelInitializer.this).remove(this.val$ctx);
        }
    }
}
