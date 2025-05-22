package org.jboss.netty.handler.execution;

import java.util.concurrent.Executor;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;

/* loaded from: classes7.dex */
public class ChannelDownstreamEventRunnable extends ChannelEventRunnable {
    public ChannelDownstreamEventRunnable(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent, Executor executor) {
        super(channelHandlerContext, channelEvent, executor);
    }

    @Override // org.jboss.netty.handler.execution.ChannelEventRunnable
    protected void doRun() {
        this.ctx.sendDownstream(this.f10046e);
    }
}
