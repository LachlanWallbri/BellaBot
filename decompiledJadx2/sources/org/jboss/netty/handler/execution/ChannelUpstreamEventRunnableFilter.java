package org.jboss.netty.handler.execution;

/* loaded from: classes7.dex */
public class ChannelUpstreamEventRunnableFilter implements ChannelEventRunnableFilter {
    @Override // org.jboss.netty.handler.execution.ChannelEventRunnableFilter
    public boolean filter(ChannelEventRunnable channelEventRunnable) {
        return channelEventRunnable instanceof ChannelDownstreamEventRunnable;
    }
}
