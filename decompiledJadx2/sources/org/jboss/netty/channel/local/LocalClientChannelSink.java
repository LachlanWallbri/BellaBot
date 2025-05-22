package org.jboss.netty.channel.local;

import java.io.IOException;
import java.net.ConnectException;
import org.jboss.netty.channel.AbstractChannelSink;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

/* loaded from: classes7.dex */
final class LocalClientChannelSink extends AbstractChannelSink {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) LocalClientChannelSink.class);

    @Override // org.jboss.netty.channel.ChannelSink
    public void eventSunk(ChannelPipeline channelPipeline, ChannelEvent channelEvent) throws Exception {
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            DefaultLocalChannel defaultLocalChannel = (DefaultLocalChannel) channelStateEvent.getChannel();
            ChannelFuture future = channelStateEvent.getFuture();
            ChannelState state = channelStateEvent.getState();
            Object value = channelStateEvent.getValue();
            int i = C86831.$SwitchMap$org$jboss$netty$channel$ChannelState[state.ordinal()];
            if (i == 1) {
                if (Boolean.FALSE.equals(value)) {
                    defaultLocalChannel.closeNow(future);
                    return;
                }
                return;
            }
            if (i == 2) {
                if (value != null) {
                    bind(defaultLocalChannel, future, (LocalAddress) value);
                    return;
                } else {
                    defaultLocalChannel.closeNow(future);
                    return;
                }
            }
            if (i != 3) {
                if (i != 4) {
                    return;
                }
                future.setSuccess();
                return;
            } else if (value != null) {
                connect(defaultLocalChannel, future, (LocalAddress) value);
                return;
            } else {
                defaultLocalChannel.closeNow(future);
                return;
            }
        }
        if (channelEvent instanceof MessageEvent) {
            MessageEvent messageEvent = (MessageEvent) channelEvent;
            DefaultLocalChannel defaultLocalChannel2 = (DefaultLocalChannel) messageEvent.getChannel();
            defaultLocalChannel2.writeBuffer.offer(messageEvent);
            defaultLocalChannel2.flushWriteBuffer();
        }
    }

    /* renamed from: org.jboss.netty.channel.local.LocalClientChannelSink$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C86831 {
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

    private static void bind(DefaultLocalChannel defaultLocalChannel, ChannelFuture channelFuture, LocalAddress localAddress) {
        try {
            if (!LocalChannelRegistry.register(localAddress, defaultLocalChannel)) {
                throw new ChannelException("address already in use: " + localAddress);
            }
            defaultLocalChannel.setBound();
            defaultLocalChannel.localAddress = localAddress;
            channelFuture.setSuccess();
            Channels.fireChannelBound(defaultLocalChannel, localAddress);
        } catch (Throwable th) {
            LocalChannelRegistry.unregister(localAddress);
            channelFuture.setFailure(th);
            Channels.fireExceptionCaught(defaultLocalChannel, th);
        }
    }

    private void connect(DefaultLocalChannel defaultLocalChannel, ChannelFuture channelFuture, LocalAddress localAddress) {
        Channel channel = LocalChannelRegistry.getChannel(localAddress);
        if (!(channel instanceof DefaultLocalServerChannel)) {
            channelFuture.setFailure(new ConnectException("connection refused"));
            return;
        }
        DefaultLocalServerChannel defaultLocalServerChannel = (DefaultLocalServerChannel) channel;
        try {
            ChannelPipeline pipeline = defaultLocalServerChannel.getConfig().getPipelineFactory().getPipeline();
            channelFuture.setSuccess();
            DefaultLocalChannel defaultLocalChannel2 = new DefaultLocalChannel(defaultLocalServerChannel, defaultLocalServerChannel.getFactory(), pipeline, this, defaultLocalChannel);
            defaultLocalChannel.pairedChannel = defaultLocalChannel2;
            if (!defaultLocalChannel.isBound()) {
                bind(defaultLocalChannel, Channels.succeededFuture(defaultLocalChannel), new LocalAddress(LocalAddress.EPHEMERAL));
            }
            defaultLocalChannel.remoteAddress = defaultLocalServerChannel.getLocalAddress();
            defaultLocalChannel.setConnected();
            Channels.fireChannelConnected(defaultLocalChannel, defaultLocalServerChannel.getLocalAddress());
            defaultLocalChannel2.localAddress = defaultLocalServerChannel.getLocalAddress();
            try {
                defaultLocalChannel2.setBound();
                Channels.fireChannelBound(defaultLocalChannel2, defaultLocalChannel.getRemoteAddress());
                defaultLocalChannel2.remoteAddress = defaultLocalChannel.getLocalAddress();
                defaultLocalChannel2.setConnected();
                Channels.fireChannelConnected(defaultLocalChannel2, defaultLocalChannel.getLocalAddress());
                defaultLocalChannel.flushWriteBuffer();
                defaultLocalChannel2.flushWriteBuffer();
            } catch (IOException e) {
                throw new Error(e);
            }
        } catch (Exception e2) {
            channelFuture.setFailure(e2);
            Channels.fireExceptionCaught(defaultLocalChannel, e2);
            if (logger.isWarnEnabled()) {
                logger.warn("Failed to initialize an accepted socket.", e2);
            }
        }
    }
}
