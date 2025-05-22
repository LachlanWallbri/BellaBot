package org.jboss.netty.channel;

/* loaded from: classes7.dex */
public interface ChannelUpstreamHandler extends ChannelHandler {
    void handleUpstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception;
}
