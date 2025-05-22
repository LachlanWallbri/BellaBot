package org.jboss.netty.handler.execution;

import java.util.concurrent.Executor;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.util.EstimatableObjectWrapper;

/* loaded from: classes7.dex */
public abstract class ChannelEventRunnable implements Runnable, EstimatableObjectWrapper {
    protected static final ThreadLocal<Executor> PARENT = new ThreadLocal<>();
    protected final ChannelHandlerContext ctx;

    /* renamed from: e */
    protected final ChannelEvent f10046e;
    int estimatedSize;
    private final Executor executor;

    protected abstract void doRun();

    public ChannelEventRunnable(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent, Executor executor) {
        this.ctx = channelHandlerContext;
        this.f10046e = channelEvent;
        this.executor = executor;
    }

    public ChannelHandlerContext getContext() {
        return this.ctx;
    }

    public ChannelEvent getEvent() {
        return this.f10046e;
    }

    @Override // org.jboss.netty.util.EstimatableObjectWrapper
    public Object unwrap() {
        return this.f10046e;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            PARENT.set(this.executor);
            doRun();
        } finally {
            PARENT.remove();
        }
    }
}
