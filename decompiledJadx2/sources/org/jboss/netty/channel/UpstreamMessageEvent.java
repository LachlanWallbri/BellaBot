package org.jboss.netty.channel;

import java.net.SocketAddress;
import org.jboss.netty.util.internal.StringUtil;

/* loaded from: classes7.dex */
public class UpstreamMessageEvent implements MessageEvent {
    private final Channel channel;
    private final Object message;
    private final SocketAddress remoteAddress;

    public UpstreamMessageEvent(Channel channel, Object obj, SocketAddress socketAddress) {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        if (obj == null) {
            throw new NullPointerException("message");
        }
        this.channel = channel;
        this.message = obj;
        if (socketAddress != null) {
            this.remoteAddress = socketAddress;
        } else {
            this.remoteAddress = channel.getRemoteAddress();
        }
    }

    @Override // org.jboss.netty.channel.ChannelEvent
    public Channel getChannel() {
        return this.channel;
    }

    @Override // org.jboss.netty.channel.ChannelEvent
    public ChannelFuture getFuture() {
        return Channels.succeededFuture(getChannel());
    }

    @Override // org.jboss.netty.channel.MessageEvent
    public Object getMessage() {
        return this.message;
    }

    @Override // org.jboss.netty.channel.MessageEvent
    public SocketAddress getRemoteAddress() {
        return this.remoteAddress;
    }

    public String toString() {
        if (getRemoteAddress() == getChannel().getRemoteAddress()) {
            return getChannel().toString() + " RECEIVED: " + StringUtil.stripControlCharacters(getMessage());
        }
        return getChannel().toString() + " RECEIVED: " + StringUtil.stripControlCharacters(getMessage()) + " from " + getRemoteAddress();
    }
}
