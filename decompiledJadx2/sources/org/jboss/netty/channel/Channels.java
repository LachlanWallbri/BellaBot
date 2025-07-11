package org.jboss.netty.channel;

import java.net.SocketAddress;
import java.util.Map;
import org.jboss.netty.util.internal.ConversionUtil;

/* loaded from: classes7.dex */
public final class Channels {
    private static int filterDownstreamInterestOps(int i) {
        return i & (-5);
    }

    public static ChannelPipeline pipeline() {
        return new DefaultChannelPipeline();
    }

    public static ChannelPipeline pipeline(ChannelHandler... channelHandlerArr) {
        ChannelHandler channelHandler;
        if (channelHandlerArr == null) {
            throw new NullPointerException("handlers");
        }
        ChannelPipeline pipeline = pipeline();
        for (int i = 0; i < channelHandlerArr.length && (channelHandler = channelHandlerArr[i]) != null; i++) {
            pipeline.addLast(ConversionUtil.toString(i), channelHandler);
        }
        return pipeline;
    }

    public static ChannelPipeline pipeline(ChannelPipeline channelPipeline) {
        ChannelPipeline pipeline = pipeline();
        for (Map.Entry<String, ChannelHandler> entry : channelPipeline.toMap().entrySet()) {
            pipeline.addLast(entry.getKey(), entry.getValue());
        }
        return pipeline;
    }

    public static ChannelPipelineFactory pipelineFactory(final ChannelPipeline channelPipeline) {
        return new ChannelPipelineFactory() { // from class: org.jboss.netty.channel.Channels.1
            @Override // org.jboss.netty.channel.ChannelPipelineFactory
            public ChannelPipeline getPipeline() {
                return Channels.pipeline(ChannelPipeline.this);
            }
        };
    }

    public static ChannelFuture future(Channel channel) {
        return future(channel, false);
    }

    public static ChannelFuture future(Channel channel, boolean z) {
        return new DefaultChannelFuture(channel, z);
    }

    public static ChannelFuture succeededFuture(Channel channel) {
        if (channel instanceof AbstractChannel) {
            return ((AbstractChannel) channel).getSucceededFuture();
        }
        return new SucceededChannelFuture(channel);
    }

    public static ChannelFuture failedFuture(Channel channel, Throwable th) {
        return new FailedChannelFuture(channel, th);
    }

    public static void fireChannelOpen(Channel channel) {
        if (channel.getParent() != null) {
            fireChildChannelStateChanged(channel.getParent(), channel);
        }
        channel.getPipeline().sendUpstream(new UpstreamChannelStateEvent(channel, ChannelState.OPEN, Boolean.TRUE));
    }

    public static void fireChannelOpen(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.sendUpstream(new UpstreamChannelStateEvent(channelHandlerContext.getChannel(), ChannelState.OPEN, Boolean.TRUE));
    }

    public static void fireChannelBound(Channel channel, SocketAddress socketAddress) {
        channel.getPipeline().sendUpstream(new UpstreamChannelStateEvent(channel, ChannelState.BOUND, socketAddress));
    }

    public static void fireChannelBound(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress) {
        channelHandlerContext.sendUpstream(new UpstreamChannelStateEvent(channelHandlerContext.getChannel(), ChannelState.BOUND, socketAddress));
    }

    public static void fireChannelConnected(Channel channel, SocketAddress socketAddress) {
        channel.getPipeline().sendUpstream(new UpstreamChannelStateEvent(channel, ChannelState.CONNECTED, socketAddress));
    }

    public static void fireChannelConnected(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress) {
        channelHandlerContext.sendUpstream(new UpstreamChannelStateEvent(channelHandlerContext.getChannel(), ChannelState.CONNECTED, socketAddress));
    }

    public static void fireMessageReceived(Channel channel, Object obj) {
        fireMessageReceived(channel, obj, (SocketAddress) null);
    }

    public static void fireMessageReceived(Channel channel, Object obj, SocketAddress socketAddress) {
        channel.getPipeline().sendUpstream(new UpstreamMessageEvent(channel, obj, socketAddress));
    }

    public static void fireMessageReceived(ChannelHandlerContext channelHandlerContext, Object obj) {
        channelHandlerContext.sendUpstream(new UpstreamMessageEvent(channelHandlerContext.getChannel(), obj, null));
    }

    public static void fireMessageReceived(ChannelHandlerContext channelHandlerContext, Object obj, SocketAddress socketAddress) {
        channelHandlerContext.sendUpstream(new UpstreamMessageEvent(channelHandlerContext.getChannel(), obj, socketAddress));
    }

    public static ChannelFuture fireWriteCompleteLater(final Channel channel, final long j) {
        return channel.getPipeline().execute(new Runnable() { // from class: org.jboss.netty.channel.Channels.2
            @Override // java.lang.Runnable
            public void run() {
                Channels.fireWriteComplete(Channel.this, j);
            }
        });
    }

    public static void fireWriteComplete(Channel channel, long j) {
        if (j == 0) {
            return;
        }
        channel.getPipeline().sendUpstream(new DefaultWriteCompletionEvent(channel, j));
    }

    public static void fireWriteComplete(ChannelHandlerContext channelHandlerContext, long j) {
        channelHandlerContext.sendUpstream(new DefaultWriteCompletionEvent(channelHandlerContext.getChannel(), j));
    }

    public static ChannelFuture fireChannelInterestChangedLater(final Channel channel) {
        return channel.getPipeline().execute(new Runnable() { // from class: org.jboss.netty.channel.Channels.3
            @Override // java.lang.Runnable
            public void run() {
                Channels.fireChannelInterestChanged(Channel.this);
            }
        });
    }

    public static void fireChannelInterestChanged(Channel channel) {
        channel.getPipeline().sendUpstream(new UpstreamChannelStateEvent(channel, ChannelState.INTEREST_OPS, 1));
    }

    public static void fireChannelInterestChanged(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.sendUpstream(new UpstreamChannelStateEvent(channelHandlerContext.getChannel(), ChannelState.INTEREST_OPS, 1));
    }

    public static ChannelFuture fireChannelDisconnectedLater(final Channel channel) {
        return channel.getPipeline().execute(new Runnable() { // from class: org.jboss.netty.channel.Channels.4
            @Override // java.lang.Runnable
            public void run() {
                Channels.fireChannelDisconnected(Channel.this);
            }
        });
    }

    public static void fireChannelDisconnected(Channel channel) {
        channel.getPipeline().sendUpstream(new UpstreamChannelStateEvent(channel, ChannelState.CONNECTED, null));
    }

    public static void fireChannelDisconnected(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.sendUpstream(new UpstreamChannelStateEvent(channelHandlerContext.getChannel(), ChannelState.CONNECTED, null));
    }

    public static ChannelFuture fireChannelUnboundLater(final Channel channel) {
        return channel.getPipeline().execute(new Runnable() { // from class: org.jboss.netty.channel.Channels.5
            @Override // java.lang.Runnable
            public void run() {
                Channels.fireChannelUnbound(Channel.this);
            }
        });
    }

    public static void fireChannelUnbound(Channel channel) {
        channel.getPipeline().sendUpstream(new UpstreamChannelStateEvent(channel, ChannelState.BOUND, null));
    }

    public static void fireChannelUnbound(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.sendUpstream(new UpstreamChannelStateEvent(channelHandlerContext.getChannel(), ChannelState.BOUND, null));
    }

    public static ChannelFuture fireChannelClosedLater(final Channel channel) {
        return channel.getPipeline().execute(new Runnable() { // from class: org.jboss.netty.channel.Channels.6
            @Override // java.lang.Runnable
            public void run() {
                Channels.fireChannelClosed(Channel.this);
            }
        });
    }

    public static void fireChannelClosed(Channel channel) {
        channel.getPipeline().sendUpstream(new UpstreamChannelStateEvent(channel, ChannelState.OPEN, Boolean.FALSE));
        if (channel.getParent() != null) {
            fireChildChannelStateChanged(channel.getParent(), channel);
        }
    }

    public static void fireChannelClosed(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.sendUpstream(new UpstreamChannelStateEvent(channelHandlerContext.getChannel(), ChannelState.OPEN, Boolean.FALSE));
    }

    public static ChannelFuture fireExceptionCaughtLater(final Channel channel, final Throwable th) {
        return channel.getPipeline().execute(new Runnable() { // from class: org.jboss.netty.channel.Channels.7
            @Override // java.lang.Runnable
            public void run() {
                Channels.fireExceptionCaught(Channel.this, th);
            }
        });
    }

    public static ChannelFuture fireExceptionCaughtLater(final ChannelHandlerContext channelHandlerContext, final Throwable th) {
        return channelHandlerContext.getPipeline().execute(new Runnable() { // from class: org.jboss.netty.channel.Channels.8
            @Override // java.lang.Runnable
            public void run() {
                Channels.fireExceptionCaught(ChannelHandlerContext.this, th);
            }
        });
    }

    public static void fireExceptionCaught(Channel channel, Throwable th) {
        channel.getPipeline().sendUpstream(new DefaultExceptionEvent(channel, th));
    }

    public static void fireExceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) {
        channelHandlerContext.sendUpstream(new DefaultExceptionEvent(channelHandlerContext.getChannel(), th));
    }

    private static void fireChildChannelStateChanged(Channel channel, Channel channel2) {
        channel.getPipeline().sendUpstream(new DefaultChildChannelStateEvent(channel, channel2));
    }

    public static ChannelFuture bind(Channel channel, SocketAddress socketAddress) {
        if (socketAddress == null) {
            throw new NullPointerException("localAddress");
        }
        ChannelFuture future = future(channel);
        channel.getPipeline().sendDownstream(new DownstreamChannelStateEvent(channel, future, ChannelState.BOUND, socketAddress));
        return future;
    }

    public static void bind(ChannelHandlerContext channelHandlerContext, ChannelFuture channelFuture, SocketAddress socketAddress) {
        if (socketAddress == null) {
            throw new NullPointerException("localAddress");
        }
        channelHandlerContext.sendDownstream(new DownstreamChannelStateEvent(channelHandlerContext.getChannel(), channelFuture, ChannelState.BOUND, socketAddress));
    }

    public static void unbind(ChannelHandlerContext channelHandlerContext, ChannelFuture channelFuture) {
        channelHandlerContext.sendDownstream(new DownstreamChannelStateEvent(channelHandlerContext.getChannel(), channelFuture, ChannelState.BOUND, null));
    }

    public static ChannelFuture unbind(Channel channel) {
        ChannelFuture future = future(channel);
        channel.getPipeline().sendDownstream(new DownstreamChannelStateEvent(channel, future, ChannelState.BOUND, null));
        return future;
    }

    public static ChannelFuture connect(Channel channel, SocketAddress socketAddress) {
        if (socketAddress == null) {
            throw new NullPointerException("remoteAddress");
        }
        ChannelFuture future = future(channel, true);
        channel.getPipeline().sendDownstream(new DownstreamChannelStateEvent(channel, future, ChannelState.CONNECTED, socketAddress));
        return future;
    }

    public static void connect(ChannelHandlerContext channelHandlerContext, ChannelFuture channelFuture, SocketAddress socketAddress) {
        if (socketAddress == null) {
            throw new NullPointerException("remoteAddress");
        }
        channelHandlerContext.sendDownstream(new DownstreamChannelStateEvent(channelHandlerContext.getChannel(), channelFuture, ChannelState.CONNECTED, socketAddress));
    }

    public static ChannelFuture write(Channel channel, Object obj) {
        return write(channel, obj, (SocketAddress) null);
    }

    public static void write(ChannelHandlerContext channelHandlerContext, ChannelFuture channelFuture, Object obj) {
        write(channelHandlerContext, channelFuture, obj, null);
    }

    public static ChannelFuture write(Channel channel, Object obj, SocketAddress socketAddress) {
        ChannelFuture future = future(channel);
        channel.getPipeline().sendDownstream(new DownstreamMessageEvent(channel, future, obj, socketAddress));
        return future;
    }

    public static void write(ChannelHandlerContext channelHandlerContext, ChannelFuture channelFuture, Object obj, SocketAddress socketAddress) {
        channelHandlerContext.sendDownstream(new DownstreamMessageEvent(channelHandlerContext.getChannel(), channelFuture, obj, socketAddress));
    }

    public static ChannelFuture setInterestOps(Channel channel, int i) {
        validateInterestOps(i);
        int filterDownstreamInterestOps = filterDownstreamInterestOps(i);
        ChannelFuture future = future(channel);
        channel.getPipeline().sendDownstream(new DownstreamChannelStateEvent(channel, future, ChannelState.INTEREST_OPS, Integer.valueOf(filterDownstreamInterestOps)));
        return future;
    }

    public static void setInterestOps(ChannelHandlerContext channelHandlerContext, ChannelFuture channelFuture, int i) {
        validateInterestOps(i);
        channelHandlerContext.sendDownstream(new DownstreamChannelStateEvent(channelHandlerContext.getChannel(), channelFuture, ChannelState.INTEREST_OPS, Integer.valueOf(filterDownstreamInterestOps(i))));
    }

    public static ChannelFuture disconnect(Channel channel) {
        ChannelFuture future = future(channel);
        channel.getPipeline().sendDownstream(new DownstreamChannelStateEvent(channel, future, ChannelState.CONNECTED, null));
        return future;
    }

    public static void disconnect(ChannelHandlerContext channelHandlerContext, ChannelFuture channelFuture) {
        channelHandlerContext.sendDownstream(new DownstreamChannelStateEvent(channelHandlerContext.getChannel(), channelFuture, ChannelState.CONNECTED, null));
    }

    public static ChannelFuture close(Channel channel) {
        ChannelFuture closeFuture = channel.getCloseFuture();
        channel.getPipeline().sendDownstream(new DownstreamChannelStateEvent(channel, closeFuture, ChannelState.OPEN, Boolean.FALSE));
        return closeFuture;
    }

    public static void close(ChannelHandlerContext channelHandlerContext, ChannelFuture channelFuture) {
        channelHandlerContext.sendDownstream(new DownstreamChannelStateEvent(channelHandlerContext.getChannel(), channelFuture, ChannelState.OPEN, Boolean.FALSE));
    }

    private static void validateInterestOps(int i) {
        if (i == 0 || i == 1 || i == 4 || i == 5) {
            return;
        }
        throw new IllegalArgumentException("Invalid interestOps: " + i);
    }

    private Channels() {
    }
}
