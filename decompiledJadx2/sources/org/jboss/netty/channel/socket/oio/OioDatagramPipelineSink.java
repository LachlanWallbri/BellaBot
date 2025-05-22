package org.jboss.netty.channel.socket.oio;

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
class OioDatagramPipelineSink extends AbstractOioChannelSink {
    private final Executor workerExecutor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OioDatagramPipelineSink(Executor executor) {
        this.workerExecutor = executor;
    }

    @Override // org.jboss.netty.channel.ChannelSink
    public void eventSunk(ChannelPipeline channelPipeline, ChannelEvent channelEvent) throws Exception {
        OioDatagramChannel oioDatagramChannel = (OioDatagramChannel) channelEvent.getChannel();
        ChannelFuture future = channelEvent.getFuture();
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            ChannelState state = channelStateEvent.getState();
            Object value = channelStateEvent.getValue();
            int i = C87001.$SwitchMap$org$jboss$netty$channel$ChannelState[state.ordinal()];
            if (i == 1) {
                if (Boolean.FALSE.equals(value)) {
                    AbstractOioWorker.close(oioDatagramChannel, future);
                    return;
                }
                return;
            }
            if (i == 2) {
                if (value != null) {
                    bind(oioDatagramChannel, future, (SocketAddress) value);
                    return;
                } else {
                    AbstractOioWorker.close(oioDatagramChannel, future);
                    return;
                }
            }
            if (i != 3) {
                if (i != 4) {
                    return;
                }
                AbstractOioWorker.setInterestOps(oioDatagramChannel, future, ((Integer) value).intValue());
                return;
            } else if (value != null) {
                connect(oioDatagramChannel, future, (SocketAddress) value);
                return;
            } else {
                OioDatagramWorker.disconnect(oioDatagramChannel, future);
                return;
            }
        }
        if (channelEvent instanceof MessageEvent) {
            MessageEvent messageEvent = (MessageEvent) channelEvent;
            OioDatagramWorker.write(oioDatagramChannel, future, messageEvent.getMessage(), messageEvent.getRemoteAddress());
        }
    }

    /* renamed from: org.jboss.netty.channel.socket.oio.OioDatagramPipelineSink$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C87001 {
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

    private void bind(OioDatagramChannel oioDatagramChannel, ChannelFuture channelFuture, SocketAddress socketAddress) {
        boolean z = false;
        try {
            oioDatagramChannel.socket.bind(socketAddress);
            z = true;
            channelFuture.setSuccess();
            Channels.fireChannelBound(oioDatagramChannel, oioDatagramChannel.getLocalAddress());
            DeadLockProofWorker.start(this.workerExecutor, new ThreadRenamingRunnable(new OioDatagramWorker(oioDatagramChannel), "Old I/O datagram worker (" + oioDatagramChannel + ')'));
        } catch (Throwable th) {
            try {
                channelFuture.setFailure(th);
                Channels.fireExceptionCaught(oioDatagramChannel, th);
            } finally {
                if (z) {
                    AbstractOioWorker.close(oioDatagramChannel, channelFuture);
                }
            }
        }
    }

    private void connect(OioDatagramChannel oioDatagramChannel, ChannelFuture channelFuture, SocketAddress socketAddress) {
        boolean isBound = oioDatagramChannel.isBound();
        channelFuture.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
        oioDatagramChannel.remoteAddress = null;
        try {
            oioDatagramChannel.socket.connect(socketAddress);
            channelFuture.setSuccess();
            if (!isBound) {
                Channels.fireChannelBound(oioDatagramChannel, oioDatagramChannel.getLocalAddress());
            }
            Channels.fireChannelConnected(oioDatagramChannel, oioDatagramChannel.getRemoteAddress());
            String str = "Old I/O datagram worker (" + oioDatagramChannel + ')';
            if (!isBound) {
                DeadLockProofWorker.start(this.workerExecutor, new ThreadRenamingRunnable(new OioDatagramWorker(oioDatagramChannel), str));
                return;
            }
            Thread thread = oioDatagramChannel.workerThread;
            if (thread != null) {
                try {
                    thread.setName(str);
                } catch (SecurityException unused) {
                }
            }
        } catch (Throwable th) {
            try {
                channelFuture.setFailure(th);
                Channels.fireExceptionCaught(oioDatagramChannel, th);
            } finally {
                if (0 != 0) {
                    AbstractOioWorker.close(oioDatagramChannel, channelFuture);
                }
            }
        }
    }
}
