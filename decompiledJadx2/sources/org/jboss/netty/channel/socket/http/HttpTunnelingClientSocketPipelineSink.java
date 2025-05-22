package org.jboss.netty.channel.socket.http;

import java.net.SocketAddress;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.AbstractChannelSink;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;

/* loaded from: classes7.dex */
final class HttpTunnelingClientSocketPipelineSink extends AbstractChannelSink {
    @Override // org.jboss.netty.channel.ChannelSink
    public void eventSunk(ChannelPipeline channelPipeline, ChannelEvent channelEvent) throws Exception {
        HttpTunnelingClientSocketChannel httpTunnelingClientSocketChannel = (HttpTunnelingClientSocketChannel) channelEvent.getChannel();
        ChannelFuture future = channelEvent.getFuture();
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            ChannelState state = channelStateEvent.getState();
            Object value = channelStateEvent.getValue();
            int i = C86921.$SwitchMap$org$jboss$netty$channel$ChannelState[state.ordinal()];
            if (i == 1) {
                if (Boolean.FALSE.equals(value)) {
                    httpTunnelingClientSocketChannel.closeReal(future);
                    return;
                }
                return;
            }
            if (i == 2) {
                if (value != null) {
                    httpTunnelingClientSocketChannel.bindReal((SocketAddress) value, future);
                    return;
                } else {
                    httpTunnelingClientSocketChannel.unbindReal(future);
                    return;
                }
            }
            if (i != 3) {
                if (i != 4) {
                    return;
                }
                httpTunnelingClientSocketChannel.setInterestOpsReal(((Integer) value).intValue(), future);
                return;
            } else if (value != null) {
                httpTunnelingClientSocketChannel.connectReal((SocketAddress) value, future);
                return;
            } else {
                httpTunnelingClientSocketChannel.closeReal(future);
                return;
            }
        }
        if (channelEvent instanceof MessageEvent) {
            httpTunnelingClientSocketChannel.writeReal((ChannelBuffer) ((MessageEvent) channelEvent).getMessage(), future);
        }
    }

    /* renamed from: org.jboss.netty.channel.socket.http.HttpTunnelingClientSocketPipelineSink$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C86921 {
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
}
