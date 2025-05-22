package org.jboss.netty.channel.socket.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;

/* loaded from: classes7.dex */
class NioDatagramPipelineSink extends AbstractNioChannelSink {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final WorkerPool<NioDatagramWorker> workerPool;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NioDatagramPipelineSink(WorkerPool<NioDatagramWorker> workerPool) {
        this.workerPool = workerPool;
    }

    @Override // org.jboss.netty.channel.ChannelSink
    public void eventSunk(ChannelPipeline channelPipeline, ChannelEvent channelEvent) throws Exception {
        NioDatagramChannel nioDatagramChannel = (NioDatagramChannel) channelEvent.getChannel();
        ChannelFuture future = channelEvent.getFuture();
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            ChannelState state = channelStateEvent.getState();
            Object value = channelStateEvent.getValue();
            int i = C86961.$SwitchMap$org$jboss$netty$channel$ChannelState[state.ordinal()];
            if (i == 1) {
                if (Boolean.FALSE.equals(value)) {
                    nioDatagramChannel.worker.close(nioDatagramChannel, future);
                    return;
                }
                return;
            }
            if (i == 2) {
                if (value != null) {
                    bind(nioDatagramChannel, future, (InetSocketAddress) value);
                    return;
                } else {
                    nioDatagramChannel.worker.close(nioDatagramChannel, future);
                    return;
                }
            }
            if (i != 3) {
                if (i != 4) {
                    return;
                }
                nioDatagramChannel.worker.setInterestOps(nioDatagramChannel, future, ((Integer) value).intValue());
                return;
            } else if (value != null) {
                connect(nioDatagramChannel, future, (InetSocketAddress) value);
                return;
            } else {
                NioDatagramWorker.disconnect(nioDatagramChannel, future);
                return;
            }
        }
        if (channelEvent instanceof MessageEvent) {
            nioDatagramChannel.writeBufferQueue.offer((MessageEvent) channelEvent);
            nioDatagramChannel.worker.writeFromUserCode(nioDatagramChannel);
        }
    }

    /* renamed from: org.jboss.netty.channel.socket.nio.NioDatagramPipelineSink$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C86961 {
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$channel$ChannelState = new int[ChannelState.values().length];

        static {
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.BOUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.CONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.INTEREST_OPS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static void close(NioDatagramChannel nioDatagramChannel, ChannelFuture channelFuture) {
        try {
            nioDatagramChannel.getDatagramChannel().socket().close();
            if (nioDatagramChannel.setClosed()) {
                channelFuture.setSuccess();
                if (nioDatagramChannel.isBound()) {
                    Channels.fireChannelUnbound(nioDatagramChannel);
                }
                Channels.fireChannelClosed(nioDatagramChannel);
                return;
            }
            channelFuture.setSuccess();
        } catch (Throwable th) {
            channelFuture.setFailure(th);
            Channels.fireExceptionCaught(nioDatagramChannel, th);
        }
    }

    private static void bind(NioDatagramChannel nioDatagramChannel, ChannelFuture channelFuture, InetSocketAddress inetSocketAddress) {
        boolean z = false;
        try {
            nioDatagramChannel.getDatagramChannel().socket().bind(inetSocketAddress);
            z = true;
            channelFuture.setSuccess();
            Channels.fireChannelBound(nioDatagramChannel, inetSocketAddress);
            nioDatagramChannel.worker.register(nioDatagramChannel, null);
        } catch (Throwable th) {
            try {
                channelFuture.setFailure(th);
                Channels.fireExceptionCaught(nioDatagramChannel, th);
            } finally {
                if (z) {
                    close(nioDatagramChannel, channelFuture);
                }
            }
        }
    }

    private static void connect(NioDatagramChannel nioDatagramChannel, ChannelFuture channelFuture, SocketAddress socketAddress) {
        boolean isBound = nioDatagramChannel.isBound();
        channelFuture.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
        nioDatagramChannel.remoteAddress = null;
        boolean z = false;
        try {
            nioDatagramChannel.getDatagramChannel().connect(socketAddress);
            z = true;
            channelFuture.setSuccess();
            if (!isBound) {
                Channels.fireChannelBound(nioDatagramChannel, nioDatagramChannel.getLocalAddress());
            }
            Channels.fireChannelConnected(nioDatagramChannel, nioDatagramChannel.getRemoteAddress());
            if (isBound) {
                return;
            }
            nioDatagramChannel.worker.register(nioDatagramChannel, channelFuture);
        } catch (Throwable th) {
            try {
                channelFuture.setFailure(th);
                Channels.fireExceptionCaught(nioDatagramChannel, th);
            } finally {
                if (z) {
                    nioDatagramChannel.worker.close(nioDatagramChannel, channelFuture);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NioDatagramWorker nextWorker() {
        return this.workerPool.nextWorker();
    }
}
