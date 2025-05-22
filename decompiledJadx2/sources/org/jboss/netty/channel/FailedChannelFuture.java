package org.jboss.netty.channel;

/* loaded from: classes7.dex */
public class FailedChannelFuture extends CompleteChannelFuture {
    private final Throwable cause;

    @Override // org.jboss.netty.channel.ChannelFuture
    public boolean isSuccess() {
        return false;
    }

    public FailedChannelFuture(Channel channel, Throwable th) {
        super(channel);
        if (th == null) {
            throw new NullPointerException("cause");
        }
        this.cause = th;
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public Throwable getCause() {
        return this.cause;
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public ChannelFuture rethrowIfFailed() throws Exception {
        Throwable th = this.cause;
        if (th instanceof Exception) {
            throw ((Exception) th);
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        throw new RuntimeException(th);
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public ChannelFuture sync() throws InterruptedException {
        rethrow();
        return this;
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public ChannelFuture syncUninterruptibly() {
        rethrow();
        return this;
    }

    private void rethrow() {
        Throwable th = this.cause;
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        throw new ChannelException(th);
    }
}
