package org.jboss.netty.channel;

/* loaded from: classes7.dex */
public abstract class AbstractChannelSink implements ChannelSink {
    protected boolean isFireExceptionCaughtLater(ChannelEvent channelEvent, Throwable th) {
        return false;
    }

    @Override // org.jboss.netty.channel.ChannelSink
    public void exceptionCaught(ChannelPipeline channelPipeline, ChannelEvent channelEvent, ChannelPipelineException channelPipelineException) throws Exception {
        Throwable cause = channelPipelineException.getCause();
        if (cause == null) {
            cause = channelPipelineException;
        }
        if (isFireExceptionCaughtLater(channelEvent, cause)) {
            Channels.fireExceptionCaughtLater(channelEvent.getChannel(), cause);
        } else {
            Channels.fireExceptionCaught(channelEvent.getChannel(), cause);
        }
    }

    @Override // org.jboss.netty.channel.ChannelSink
    public ChannelFuture execute(ChannelPipeline channelPipeline, Runnable runnable) {
        try {
            runnable.run();
            return Channels.succeededFuture(channelPipeline.getChannel());
        } catch (Throwable th) {
            return Channels.failedFuture(channelPipeline.getChannel(), th);
        }
    }
}
