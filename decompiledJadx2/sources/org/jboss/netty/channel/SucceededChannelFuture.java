package org.jboss.netty.channel;

/* loaded from: classes7.dex */
public class SucceededChannelFuture extends CompleteChannelFuture {
    @Override // org.jboss.netty.channel.ChannelFuture
    public Throwable getCause() {
        return null;
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public boolean isSuccess() {
        return true;
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public ChannelFuture rethrowIfFailed() throws Exception {
        return this;
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public ChannelFuture sync() throws InterruptedException {
        return this;
    }

    @Override // org.jboss.netty.channel.ChannelFuture
    public ChannelFuture syncUninterruptibly() {
        return this;
    }

    public SucceededChannelFuture(Channel channel) {
        super(channel);
    }
}
