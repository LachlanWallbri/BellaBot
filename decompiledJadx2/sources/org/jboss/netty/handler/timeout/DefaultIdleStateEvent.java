package org.jboss.netty.handler.timeout;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.Channels;

/* loaded from: classes7.dex */
public class DefaultIdleStateEvent implements IdleStateEvent {
    private final Channel channel;
    private final long lastActivityTimeMillis;
    private final IdleState state;

    public DefaultIdleStateEvent(Channel channel, IdleState idleState, long j) {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        if (idleState == null) {
            throw new NullPointerException("state");
        }
        this.channel = channel;
        this.state = idleState;
        this.lastActivityTimeMillis = j;
    }

    @Override // org.jboss.netty.channel.ChannelEvent
    public Channel getChannel() {
        return this.channel;
    }

    @Override // org.jboss.netty.channel.ChannelEvent
    public ChannelFuture getFuture() {
        return Channels.succeededFuture(getChannel());
    }

    @Override // org.jboss.netty.handler.timeout.IdleStateEvent
    public IdleState getState() {
        return this.state;
    }

    @Override // org.jboss.netty.handler.timeout.IdleStateEvent
    public long getLastActivityTimeMillis() {
        return this.lastActivityTimeMillis;
    }

    public String toString() {
        return getChannel().toString() + ' ' + getState() + " since " + DateFormat.getDateTimeInstance(3, 3, Locale.US).format(new Date(getLastActivityTimeMillis()));
    }
}
