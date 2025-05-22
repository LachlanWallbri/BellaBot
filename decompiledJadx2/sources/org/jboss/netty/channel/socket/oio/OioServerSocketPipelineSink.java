package org.jboss.netty.channel.socket.oio;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executor;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.ThreadRenamingRunnable;
import org.jboss.netty.util.internal.DeadLockProofWorker;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class OioServerSocketPipelineSink extends AbstractOioChannelSink {
    static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) OioServerSocketPipelineSink.class);
    final Executor workerExecutor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OioServerSocketPipelineSink(Executor executor) {
        this.workerExecutor = executor;
    }

    @Override // org.jboss.netty.channel.ChannelSink
    public void eventSunk(ChannelPipeline channelPipeline, ChannelEvent channelEvent) throws Exception {
        Channel channel = channelEvent.getChannel();
        if (channel instanceof OioServerSocketChannel) {
            handleServerSocket(channelEvent);
        } else if (channel instanceof OioAcceptedSocketChannel) {
            handleAcceptedSocket(channelEvent);
        }
    }

    private void handleServerSocket(ChannelEvent channelEvent) {
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            OioServerSocketChannel oioServerSocketChannel = (OioServerSocketChannel) channelStateEvent.getChannel();
            ChannelFuture future = channelStateEvent.getFuture();
            ChannelState state = channelStateEvent.getState();
            Object value = channelStateEvent.getValue();
            int i = C87011.$SwitchMap$org$jboss$netty$channel$ChannelState[state.ordinal()];
            if (i == 1) {
                if (Boolean.FALSE.equals(value)) {
                    close(oioServerSocketChannel, future);
                }
            } else {
                if (i != 2) {
                    return;
                }
                if (value != null) {
                    bind(oioServerSocketChannel, future, (SocketAddress) value);
                } else {
                    close(oioServerSocketChannel, future);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.jboss.netty.channel.socket.oio.OioServerSocketPipelineSink$1 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C87011 {
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

    private static void handleAcceptedSocket(ChannelEvent channelEvent) {
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            OioAcceptedSocketChannel oioAcceptedSocketChannel = (OioAcceptedSocketChannel) channelStateEvent.getChannel();
            ChannelFuture future = channelStateEvent.getFuture();
            ChannelState state = channelStateEvent.getState();
            Object value = channelStateEvent.getValue();
            int i = C87011.$SwitchMap$org$jboss$netty$channel$ChannelState[state.ordinal()];
            if (i == 1) {
                if (Boolean.FALSE.equals(value)) {
                    AbstractOioWorker.close(oioAcceptedSocketChannel, future);
                    return;
                }
                return;
            } else if (i == 2 || i == 3) {
                if (value == null) {
                    AbstractOioWorker.close(oioAcceptedSocketChannel, future);
                    return;
                }
                return;
            } else {
                if (i != 4) {
                    return;
                }
                AbstractOioWorker.setInterestOps(oioAcceptedSocketChannel, future, ((Integer) value).intValue());
                return;
            }
        }
        if (channelEvent instanceof MessageEvent) {
            MessageEvent messageEvent = (MessageEvent) channelEvent;
            OioWorker.write((OioSocketChannel) messageEvent.getChannel(), messageEvent.getFuture(), messageEvent.getMessage());
        }
    }

    private void bind(OioServerSocketChannel oioServerSocketChannel, ChannelFuture channelFuture, SocketAddress socketAddress) {
        boolean z = false;
        try {
            oioServerSocketChannel.socket.bind(socketAddress, oioServerSocketChannel.getConfig().getBacklog());
            z = true;
            channelFuture.setSuccess();
            Channels.fireChannelBound(oioServerSocketChannel, oioServerSocketChannel.getLocalAddress());
            DeadLockProofWorker.start(((OioServerSocketChannelFactory) oioServerSocketChannel.getFactory()).bossExecutor, new ThreadRenamingRunnable(new Boss(oioServerSocketChannel), "Old I/O server boss (" + oioServerSocketChannel + ')'));
        } catch (Throwable th) {
            try {
                channelFuture.setFailure(th);
                Channels.fireExceptionCaught(oioServerSocketChannel, th);
            } finally {
                if (z) {
                    close(oioServerSocketChannel, channelFuture);
                }
            }
        }
    }

    private static void close(OioServerSocketChannel oioServerSocketChannel, ChannelFuture channelFuture) {
        boolean isBound = oioServerSocketChannel.isBound();
        try {
            oioServerSocketChannel.socket.close();
            oioServerSocketChannel.shutdownLock.lock();
            try {
                if (oioServerSocketChannel.setClosed()) {
                    channelFuture.setSuccess();
                    if (isBound) {
                        Channels.fireChannelUnbound(oioServerSocketChannel);
                    }
                    Channels.fireChannelClosed(oioServerSocketChannel);
                } else {
                    channelFuture.setSuccess();
                }
                oioServerSocketChannel.shutdownLock.unlock();
            } catch (Throwable th) {
                oioServerSocketChannel.shutdownLock.unlock();
                throw th;
            }
        } catch (Throwable th2) {
            channelFuture.setFailure(th2);
            Channels.fireExceptionCaught(oioServerSocketChannel, th2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public final class Boss implements Runnable {
        private final OioServerSocketChannel channel;

        Boss(OioServerSocketChannel oioServerSocketChannel) {
            this.channel = oioServerSocketChannel;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.channel.shutdownLock.lock();
            while (this.channel.isBound()) {
                try {
                    try {
                        Socket accept = this.channel.socket.accept();
                        try {
                            OioAcceptedSocketChannel oioAcceptedSocketChannel = new OioAcceptedSocketChannel(this.channel, this.channel.getFactory(), this.channel.getConfig().getPipelineFactory().getPipeline(), OioServerSocketPipelineSink.this, accept);
                            DeadLockProofWorker.start(OioServerSocketPipelineSink.this.workerExecutor, new ThreadRenamingRunnable(new OioWorker(oioAcceptedSocketChannel), "Old I/O server worker (parentId: " + this.channel.getId() + ", " + this.channel + ')'));
                        } catch (Exception e) {
                            if (OioServerSocketPipelineSink.logger.isWarnEnabled()) {
                                OioServerSocketPipelineSink.logger.warn("Failed to initialize an accepted socket.", e);
                            }
                            try {
                                accept.close();
                            } catch (IOException e2) {
                                if (OioServerSocketPipelineSink.logger.isWarnEnabled()) {
                                    OioServerSocketPipelineSink.logger.warn("Failed to close a partially accepted socket.", e2);
                                }
                            }
                        }
                    } catch (InterruptedException | SocketTimeoutException unused) {
                    } catch (Throwable th) {
                        if (!this.channel.socket.isBound() || this.channel.socket.isClosed()) {
                            break;
                        }
                        if (OioServerSocketPipelineSink.logger.isWarnEnabled()) {
                            OioServerSocketPipelineSink.logger.warn("Failed to accept a connection.", th);
                        }
                        Thread.sleep(1000L);
                    }
                } finally {
                    this.channel.shutdownLock.unlock();
                }
            }
        }
    }
}
