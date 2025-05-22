package org.jboss.netty.handler.ipfilter;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;

@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class OneIpFilterHandler extends IpFilteringHandlerImpl {
    private final ConcurrentMap<InetAddress, Boolean> connectedSet = new ConcurrentHashMap();

    @Override // org.jboss.netty.handler.ipfilter.IpFilteringHandlerImpl
    protected boolean accept(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent, InetSocketAddress inetSocketAddress) throws Exception {
        InetAddress address = inetSocketAddress.getAddress();
        if (this.connectedSet.containsKey(address)) {
            return false;
        }
        this.connectedSet.put(address, Boolean.TRUE);
        return true;
    }

    @Override // org.jboss.netty.handler.ipfilter.IpFilteringHandlerImpl, org.jboss.netty.channel.ChannelUpstreamHandler
    public void handleUpstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        super.handleUpstream(channelHandlerContext, channelEvent);
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            if (channelStateEvent.getState() == ChannelState.CONNECTED && channelStateEvent.getValue() == null && isBlocked(channelHandlerContext)) {
                this.connectedSet.remove(((InetSocketAddress) channelEvent.getChannel().getRemoteAddress()).getAddress());
            }
        }
    }
}
