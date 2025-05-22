package org.jboss.netty.handler.execution;

import java.util.concurrent.Executor;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;

/* loaded from: classes7.dex */
public class ChannelUpstreamEventRunnable extends ChannelEventRunnable {
    public ChannelUpstreamEventRunnable(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent, Executor executor) {
        super(channelHandlerContext, channelEvent, executor);
    }

    @Override // org.jboss.netty.handler.execution.ChannelEventRunnable
    protected void doRun() {
        this.ctx.sendUpstream(this.f10046e);
    }
}
