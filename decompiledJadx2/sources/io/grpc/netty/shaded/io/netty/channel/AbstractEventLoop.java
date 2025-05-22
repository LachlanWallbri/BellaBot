package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractEventLoop extends AbstractEventExecutor implements EventLoop {
    protected AbstractEventLoop() {
    }

    protected AbstractEventLoop(EventLoopGroup eventLoopGroup) {
        super(eventLoopGroup);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor, io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor, io.grpc.netty.shaded.io.netty.channel.EventLoop
    public EventLoopGroup parent() {
        return (EventLoopGroup) super.parent();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor, io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor, io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup, io.grpc.netty.shaded.io.netty.channel.EventLoopGroup
    public EventLoop next() {
        return (EventLoop) super.next();
    }
}
