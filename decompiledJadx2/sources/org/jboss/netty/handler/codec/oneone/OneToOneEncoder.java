package org.jboss.netty.handler.codec.oneone;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;

/* loaded from: classes7.dex */
public abstract class OneToOneEncoder implements ChannelDownstreamHandler {
    protected abstract Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object obj) throws Exception;

    @Override // org.jboss.netty.channel.ChannelDownstreamHandler
    public void handleDownstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        if (!(channelEvent instanceof MessageEvent)) {
            channelHandlerContext.sendDownstream(channelEvent);
            return;
        }
        MessageEvent messageEvent = (MessageEvent) channelEvent;
        Object message = messageEvent.getMessage();
        Object encode = encode(channelHandlerContext, messageEvent.getChannel(), message);
        if (message == encode) {
            channelHandlerContext.sendDownstream(channelEvent);
        } else if (encode != null) {
            Channels.write(channelHandlerContext, messageEvent.getFuture(), encode, messageEvent.getRemoteAddress());
        }
    }
}
