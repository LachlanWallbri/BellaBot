package org.jboss.netty.channel;

/* loaded from: classes7.dex */
public interface ChannelSink {
    void eventSunk(ChannelPipeline channelPipeline, ChannelEvent channelEvent) throws Exception;

    void exceptionCaught(ChannelPipeline channelPipeline, ChannelEvent channelEvent, ChannelPipelineException channelPipelineException) throws Exception;

    ChannelFuture execute(ChannelPipeline channelPipeline, Runnable runnable);
}
