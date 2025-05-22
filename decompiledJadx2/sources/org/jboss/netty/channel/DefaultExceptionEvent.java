package org.jboss.netty.channel;

import org.jboss.netty.util.internal.StackTraceSimplifier;

/* loaded from: classes7.dex */
public class DefaultExceptionEvent implements ExceptionEvent {
    private final Throwable cause;
    private final Channel channel;

    public DefaultExceptionEvent(Channel channel, Throwable th) {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        if (th == null) {
            throw new NullPointerException("cause");
        }
        this.channel = channel;
        this.cause = th;
        StackTraceSimplifier.simplify(th);
    }

    @Override // org.jboss.netty.channel.ChannelEvent
    public Channel getChannel() {
        return this.channel;
    }

    @Override // org.jboss.netty.channel.ChannelEvent
    public ChannelFuture getFuture() {
        return Channels.succeededFuture(getChannel());
    }

    @Override // org.jboss.netty.channel.ExceptionEvent
    public Throwable getCause() {
        return this.cause;
    }

    public String toString() {
        return getChannel().toString() + " EXCEPTION: " + this.cause;
    }
}
