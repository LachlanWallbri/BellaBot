package org.jboss.netty.channel.socket.oio;

import org.jboss.netty.channel.AbstractChannelSink;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.socket.ChannelRunnableWrapper;

/* loaded from: classes7.dex */
public abstract class AbstractOioChannelSink extends AbstractChannelSink {
    @Override // org.jboss.netty.channel.AbstractChannelSink, org.jboss.netty.channel.ChannelSink
    public ChannelFuture execute(ChannelPipeline channelPipeline, Runnable runnable) {
        Channel channel = channelPipeline.getChannel();
        if (channel instanceof AbstractOioChannel) {
            AbstractOioChannel abstractOioChannel = (AbstractOioChannel) channel;
            if (abstractOioChannel.worker != null) {
                ChannelRunnableWrapper channelRunnableWrapper = new ChannelRunnableWrapper(channelPipeline.getChannel(), runnable);
                abstractOioChannel.worker.executeInIoThread(channelRunnableWrapper);
                return channelRunnableWrapper;
            }
        }
        return super.execute(channelPipeline, runnable);
    }

    @Override // org.jboss.netty.channel.AbstractChannelSink
    protected boolean isFireExceptionCaughtLater(ChannelEvent channelEvent, Throwable th) {
        if (channelEvent.getChannel() instanceof AbstractOioChannel) {
            return !AbstractOioWorker.isIoThread((AbstractOioChannel) r1);
        }
        return false;
    }
}
