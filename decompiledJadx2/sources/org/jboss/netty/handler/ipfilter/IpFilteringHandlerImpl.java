package org.jboss.netty.handler.ipfilter;

import java.net.InetSocketAddress;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ChannelUpstreamHandler;
import org.jboss.netty.channel.Channels;

/* loaded from: classes7.dex */
public abstract class IpFilteringHandlerImpl implements ChannelUpstreamHandler, IpFilteringHandler {
    private IpFilterListener listener;

    protected abstract boolean accept(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent, InetSocketAddress inetSocketAddress) throws Exception;

    protected ChannelFuture handleRefusedChannel(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent, InetSocketAddress inetSocketAddress) throws Exception {
        IpFilterListener ipFilterListener = this.listener;
        if (ipFilterListener == null) {
            return null;
        }
        return ipFilterListener.refused(channelHandlerContext, channelEvent, inetSocketAddress);
    }

    protected ChannelFuture handleAllowedChannel(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent, InetSocketAddress inetSocketAddress) throws Exception {
        IpFilterListener ipFilterListener = this.listener;
        if (ipFilterListener == null) {
            return null;
        }
        return ipFilterListener.allowed(channelHandlerContext, channelEvent, inetSocketAddress);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isBlocked(ChannelHandlerContext channelHandlerContext) {
        return channelHandlerContext.getAttachment() != null;
    }

    protected boolean continues(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        IpFilterListener ipFilterListener = this.listener;
        if (ipFilterListener != null) {
            return ipFilterListener.continues(channelHandlerContext, channelEvent);
        }
        return false;
    }

    @Override // org.jboss.netty.channel.ChannelUpstreamHandler
    public void handleUpstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            int i = C87241.$SwitchMap$org$jboss$netty$channel$ChannelState[channelStateEvent.getState().ordinal()];
            if (i == 1 || i == 2) {
                if (!isBlocked(channelHandlerContext) || continues(channelHandlerContext, channelStateEvent)) {
                    channelHandlerContext.sendUpstream(channelEvent);
                    return;
                }
                return;
            }
            if (i == 3) {
                if (channelStateEvent.getValue() != null) {
                    InetSocketAddress inetSocketAddress = (InetSocketAddress) channelEvent.getChannel().getRemoteAddress();
                    if (!accept(channelHandlerContext, channelEvent, inetSocketAddress)) {
                        channelHandlerContext.setAttachment(Boolean.TRUE);
                        ChannelFuture handleRefusedChannel = handleRefusedChannel(channelHandlerContext, channelEvent, inetSocketAddress);
                        if (handleRefusedChannel != null) {
                            handleRefusedChannel.addListener(ChannelFutureListener.CLOSE);
                        } else {
                            Channels.close(channelEvent.getChannel());
                        }
                        if (isBlocked(channelHandlerContext) && !continues(channelHandlerContext, channelStateEvent)) {
                            return;
                        }
                    } else {
                        handleAllowedChannel(channelHandlerContext, channelEvent, inetSocketAddress);
                    }
                    channelHandlerContext.setAttachment(null);
                } else if (isBlocked(channelHandlerContext) && !continues(channelHandlerContext, channelStateEvent)) {
                    return;
                }
            }
        }
        if (!isBlocked(channelHandlerContext) || continues(channelHandlerContext, channelEvent)) {
            channelHandlerContext.sendUpstream(channelEvent);
        }
    }

    /* renamed from: org.jboss.netty.handler.ipfilter.IpFilteringHandlerImpl$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C87241 {
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
        }
    }

    @Override // org.jboss.netty.handler.ipfilter.IpFilteringHandler
    public void setIpFilterListener(IpFilterListener ipFilterListener) {
        this.listener = ipFilterListener;
    }

    @Override // org.jboss.netty.handler.ipfilter.IpFilteringHandler
    public void removeIpFilterListener() {
        this.listener = null;
    }
}
