package org.jboss.netty.handler.timeout;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.SimpleChannelHandler;

/* loaded from: classes7.dex */
public class IdleStateAwareChannelHandler extends SimpleChannelHandler {
    @Override // org.jboss.netty.channel.SimpleChannelHandler, org.jboss.netty.channel.ChannelUpstreamHandler
    public void handleUpstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        if (channelEvent instanceof IdleStateEvent) {
            channelIdle(channelHandlerContext, (IdleStateEvent) channelEvent);
        } else {
            super.handleUpstream(channelHandlerContext, channelEvent);
        }
    }

    public void channelIdle(ChannelHandlerContext channelHandlerContext, IdleStateEvent idleStateEvent) throws Exception {
        channelHandlerContext.sendUpstream(idleStateEvent);
    }
}
