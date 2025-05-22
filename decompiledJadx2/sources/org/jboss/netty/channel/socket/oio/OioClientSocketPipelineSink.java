package org.jboss.netty.channel.socket.oio;

import java.io.PushbackInputStream;
import java.net.SocketAddress;
import java.util.concurrent.Executor;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.util.ThreadRenamingRunnable;
import org.jboss.netty.util.internal.DeadLockProofWorker;

/* loaded from: classes7.dex */
class OioClientSocketPipelineSink extends AbstractOioChannelSink {
    private final Executor workerExecutor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OioClientSocketPipelineSink(Executor executor) {
        this.workerExecutor = executor;
    }

    @Override // org.jboss.netty.channel.ChannelSink
    public void eventSunk(ChannelPipeline channelPipeline, ChannelEvent channelEvent) throws Exception {
        OioClientSocketChannel oioClientSocketChannel = (OioClientSocketChannel) channelEvent.getChannel();
        ChannelFuture future = channelEvent.getFuture();
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            ChannelState state = channelStateEvent.getState();
            Object value = channelStateEvent.getValue();
            int i = C86991.$SwitchMap$org$jboss$netty$channel$ChannelState[state.ordinal()];
            if (i == 1) {
                if (Boolean.FALSE.equals(value)) {
                    AbstractOioWorker.close(oioClientSocketChannel, future);
                    return;
                }
                return;
            }
            if (i == 2) {
                if (value != null) {
                    bind(oioClientSocketChannel, future, (SocketAddress) value);
                    return;
                } else {
                    AbstractOioWorker.close(oioClientSocketChannel, future);
                    return;
                }
            }
            if (i != 3) {
                if (i != 4) {
                    return;
                }
                AbstractOioWorker.setInterestOps(oioClientSocketChannel, future, ((Integer) value).intValue());
                return;
            } else if (value != null) {
                connect(oioClientSocketChannel, future, (SocketAddress) value);
                return;
            } else {
                AbstractOioWorker.close(oioClientSocketChannel, future);
                return;
            }
        }
        if (channelEvent instanceof MessageEvent) {
            OioWorker.write(oioClientSocketChannel, future, ((MessageEvent) channelEvent).getMessage());
        }
    }

    /* renamed from: org.jboss.netty.channel.socket.oio.OioClientSocketPipelineSink$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C86991 {
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

    private static void bind(OioClientSocketChannel oioClientSocketChannel, ChannelFuture channelFuture, SocketAddress socketAddress) {
        try {
            oioClientSocketChannel.socket.bind(socketAddress);
            channelFuture.setSuccess();
            Channels.fireChannelBound(oioClientSocketChannel, oioClientSocketChannel.getLocalAddress());
        } catch (Throwable th) {
            channelFuture.setFailure(th);
            Channels.fireExceptionCaught(oioClientSocketChannel, th);
        }
    }

    private void connect(OioClientSocketChannel oioClientSocketChannel, ChannelFuture channelFuture, SocketAddress socketAddress) {
        boolean isBound = oioClientSocketChannel.isBound();
        channelFuture.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
        boolean z = true;
        try {
            oioClientSocketChannel.socket.connect(socketAddress, oioClientSocketChannel.getConfig().getConnectTimeoutMillis());
        } catch (Throwable th) {
            th = th;
            z = false;
        }
        try {
            oioClientSocketChannel.f10025in = new PushbackInputStream(oioClientSocketChannel.socket.getInputStream(), 1);
            oioClientSocketChannel.out = oioClientSocketChannel.socket.getOutputStream();
            channelFuture.setSuccess();
            if (!isBound) {
                Channels.fireChannelBound(oioClientSocketChannel, oioClientSocketChannel.getLocalAddress());
            }
            Channels.fireChannelConnected(oioClientSocketChannel, oioClientSocketChannel.getRemoteAddress());
            DeadLockProofWorker.start(this.workerExecutor, new ThreadRenamingRunnable(new OioWorker(oioClientSocketChannel), "Old I/O client worker (" + oioClientSocketChannel + ')'));
        } catch (Throwable th2) {
            th = th2;
            try {
                channelFuture.setFailure(th);
                Channels.fireExceptionCaught(oioClientSocketChannel, th);
            } finally {
                if (z) {
                    AbstractOioWorker.close(oioClientSocketChannel, channelFuture);
                }
            }
        }
    }
}
