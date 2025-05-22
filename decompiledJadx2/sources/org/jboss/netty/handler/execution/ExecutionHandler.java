package org.jboss.netty.handler.execution;

import java.util.concurrent.Executor;
import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ChannelUpstreamHandler;
import org.jboss.netty.util.ExternalResourceReleasable;
import org.jboss.netty.util.internal.ExecutorUtil;

@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class ExecutionHandler implements ChannelUpstreamHandler, ChannelDownstreamHandler, ExternalResourceReleasable {
    private final Executor executor;
    private final boolean handleDownstream;
    private final boolean handleUpstream;

    public ExecutionHandler(Executor executor) {
        this(executor, false, true);
    }

    @Deprecated
    public ExecutionHandler(Executor executor, boolean z) {
        this(executor, z, true);
    }

    public ExecutionHandler(Executor executor, boolean z, boolean z2) {
        if (executor == null) {
            throw new NullPointerException("executor");
        }
        if (!z && !z2) {
            throw new IllegalArgumentException("You must handle at least handle one event type");
        }
        this.executor = executor;
        this.handleDownstream = z;
        this.handleUpstream = z2;
    }

    public Executor getExecutor() {
        return this.executor;
    }

    @Override // org.jboss.netty.util.ExternalResourceReleasable
    public void releaseExternalResources() {
        Executor executor = getExecutor();
        ExecutorUtil.terminate(ChannelEventRunnable.PARENT, executor);
        if (executor instanceof ExternalResourceReleasable) {
            ((ExternalResourceReleasable) executor).releaseExternalResources();
        }
    }

    @Override // org.jboss.netty.channel.ChannelUpstreamHandler
    public void handleUpstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        if (this.handleUpstream) {
            Executor executor = this.executor;
            executor.execute(new ChannelUpstreamEventRunnable(channelHandlerContext, channelEvent, executor));
        } else {
            channelHandlerContext.sendUpstream(channelEvent);
        }
    }

    @Override // org.jboss.netty.channel.ChannelDownstreamHandler
    public void handleDownstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        if (handleReadSuspend(channelHandlerContext, channelEvent)) {
            return;
        }
        if (this.handleDownstream) {
            Executor executor = this.executor;
            executor.execute(new ChannelDownstreamEventRunnable(channelHandlerContext, channelEvent, executor));
        } else {
            channelHandlerContext.sendDownstream(channelEvent);
        }
    }

    protected boolean handleReadSuspend(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) {
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            if (channelStateEvent.getState() == ChannelState.INTEREST_OPS && (((Integer) channelStateEvent.getValue()).intValue() & 1) != 0) {
                if (channelHandlerContext.getAttachment() != null) {
                    channelEvent.getFuture().setSuccess();
                    return true;
                }
            }
        }
        return false;
    }
}
