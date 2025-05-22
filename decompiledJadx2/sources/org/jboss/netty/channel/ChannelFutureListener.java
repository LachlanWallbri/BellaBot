package org.jboss.netty.channel;

import java.util.EventListener;

/* loaded from: classes7.dex */
public interface ChannelFutureListener extends EventListener {
    public static final ChannelFutureListener CLOSE = new ChannelFutureListener() { // from class: org.jboss.netty.channel.ChannelFutureListener.1
        @Override // org.jboss.netty.channel.ChannelFutureListener
        public void operationComplete(ChannelFuture channelFuture) {
            channelFuture.getChannel().close();
        }
    };
    public static final ChannelFutureListener CLOSE_ON_FAILURE = new ChannelFutureListener() { // from class: org.jboss.netty.channel.ChannelFutureListener.2
        @Override // org.jboss.netty.channel.ChannelFutureListener
        public void operationComplete(ChannelFuture channelFuture) {
            if (channelFuture.isSuccess()) {
                return;
            }
            channelFuture.getChannel().close();
        }
    };

    void operationComplete(ChannelFuture channelFuture) throws Exception;
}
