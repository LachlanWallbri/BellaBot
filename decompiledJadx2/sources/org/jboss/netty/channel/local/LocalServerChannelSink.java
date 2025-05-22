package org.jboss.netty.channel.local;

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

/* loaded from: classes7.dex */
final class LocalServerChannelSink extends AbstractChannelSink {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    @Override // org.jboss.netty.channel.ChannelSink
    public void eventSunk(ChannelPipeline channelPipeline, ChannelEvent channelEvent) throws Exception {
        Channel channel = channelEvent.getChannel();
        if (channel instanceof DefaultLocalServerChannel) {
            handleServerChannel(channelEvent);
        } else if (channel instanceof DefaultLocalChannel) {
            handleAcceptedChannel(channelEvent);
        }
    }

    private static void handleServerChannel(ChannelEvent channelEvent) {
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            DefaultLocalServerChannel defaultLocalServerChannel = (DefaultLocalServerChannel) channelStateEvent.getChannel();
            ChannelFuture future = channelStateEvent.getFuture();
            ChannelState state = channelStateEvent.getState();
            Object value = channelStateEvent.getValue();
            int i = C86841.$SwitchMap$org$jboss$netty$channel$ChannelState[state.ordinal()];
            if (i == 1) {
                if (Boolean.FALSE.equals(value)) {
                    close(defaultLocalServerChannel, future);
                }
            } else {
                if (i != 2) {
                    return;
                }
                if (value != null) {
                    bind(defaultLocalServerChannel, future, (LocalAddress) value);
                } else {
                    close(defaultLocalServerChannel, future);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.jboss.netty.channel.local.LocalServerChannelSink$1 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C86841 {
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

    private static void handleAcceptedChannel(ChannelEvent channelEvent) {
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            DefaultLocalChannel defaultLocalChannel = (DefaultLocalChannel) channelStateEvent.getChannel();
            ChannelFuture future = channelStateEvent.getFuture();
            ChannelState state = channelStateEvent.getState();
            Object value = channelStateEvent.getValue();
            int i = C86841.$SwitchMap$org$jboss$netty$channel$ChannelState[state.ordinal()];
            if (i == 1) {
                if (Boolean.FALSE.equals(value)) {
                    defaultLocalChannel.closeNow(future);
                    return;
                }
                return;
            } else if (i == 2 || i == 3) {
                if (value == null) {
                    defaultLocalChannel.closeNow(future);
                    return;
                }
                return;
            } else {
                if (i != 4) {
                    return;
                }
                future.setSuccess();
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

    private static void bind(DefaultLocalServerChannel defaultLocalServerChannel, ChannelFuture channelFuture, LocalAddress localAddress) {
        try {
            if (!LocalChannelRegistry.register(localAddress, defaultLocalServerChannel)) {
                throw new ChannelException("address already in use: " + localAddress);
            }
            if (!defaultLocalServerChannel.bound.compareAndSet(false, true)) {
                throw new ChannelException("already bound");
            }
            defaultLocalServerChannel.localAddress = localAddress;
            channelFuture.setSuccess();
            Channels.fireChannelBound(defaultLocalServerChannel, localAddress);
        } catch (Throwable th) {
            LocalChannelRegistry.unregister(localAddress);
            channelFuture.setFailure(th);
            Channels.fireExceptionCaught(defaultLocalServerChannel, th);
        }
    }

    private static void close(DefaultLocalServerChannel defaultLocalServerChannel, ChannelFuture channelFuture) {
        try {
            if (defaultLocalServerChannel.setClosed()) {
                channelFuture.setSuccess();
                LocalAddress localAddress = defaultLocalServerChannel.localAddress;
                if (defaultLocalServerChannel.bound.compareAndSet(true, false)) {
                    defaultLocalServerChannel.localAddress = null;
                    LocalChannelRegistry.unregister(localAddress);
                    Channels.fireChannelUnbound(defaultLocalServerChannel);
                }
                Channels.fireChannelClosed(defaultLocalServerChannel);
                return;
            }
            channelFuture.setSuccess();
        } catch (Throwable th) {
            channelFuture.setFailure(th);
            Channels.fireExceptionCaught(defaultLocalServerChannel, th);
        }
    }
}
