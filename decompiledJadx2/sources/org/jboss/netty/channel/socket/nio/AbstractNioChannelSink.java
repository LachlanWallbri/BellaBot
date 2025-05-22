package org.jboss.netty.channel.socket.nio;

import org.jboss.netty.channel.AbstractChannelSink;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.socket.ChannelRunnableWrapper;

/* loaded from: classes7.dex */
public abstract class AbstractNioChannelSink extends AbstractChannelSink {
    @Override // org.jboss.netty.channel.AbstractChannelSink, org.jboss.netty.channel.ChannelSink
    public ChannelFuture execute(ChannelPipeline channelPipeline, Runnable runnable) {
        Channel channel = channelPipeline.getChannel();
        if (channel instanceof AbstractNioChannel) {
            ChannelRunnableWrapper channelRunnableWrapper = new ChannelRunnableWrapper(channelPipeline.getChannel(), runnable);
            ((AbstractNioChannel) channel).worker.executeInIoThread(channelRunnableWrapper);
            return channelRunnableWrapper;
        }
        return super.execute(channelPipeline, runnable);
    }

    @Override // org.jboss.netty.channel.AbstractChannelSink
    protected boolean isFireExceptionCaughtLater(ChannelEvent channelEvent, Throwable th) {
        if (channelEvent.getChannel() instanceof AbstractNioChannel) {
            return !AbstractNioWorker.isIoThread((AbstractNioChannel) r1);
        }
        return false;
    }
}
