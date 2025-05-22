package org.jboss.netty.channel;

import java.net.SocketAddress;
import org.jboss.netty.util.internal.StringUtil;

/* loaded from: classes7.dex */
public class DownstreamMessageEvent implements MessageEvent {
    private final Channel channel;
    private final ChannelFuture future;
    private final Object message;
    private final SocketAddress remoteAddress;

    public DownstreamMessageEvent(Channel channel, ChannelFuture channelFuture, Object obj, SocketAddress socketAddress) {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        if (channelFuture == null) {
            throw new NullPointerException("future");
        }
        if (obj == null) {
            throw new NullPointerException("message");
        }
        this.channel = channel;
        this.future = channelFuture;
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
        return this.future;
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
            return getChannel().toString() + " WRITE: " + StringUtil.stripControlCharacters(getMessage());
        }
        return getChannel().toString() + " WRITE: " + StringUtil.stripControlCharacters(getMessage()) + " to " + getRemoteAddress();
    }
}
