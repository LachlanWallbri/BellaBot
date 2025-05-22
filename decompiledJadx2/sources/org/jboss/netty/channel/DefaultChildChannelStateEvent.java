package org.jboss.netty.channel;

/* loaded from: classes7.dex */
public class DefaultChildChannelStateEvent implements ChildChannelStateEvent {
    private final Channel childChannel;
    private final Channel parentChannel;

    public DefaultChildChannelStateEvent(Channel channel, Channel channel2) {
        if (channel == null) {
            throw new NullPointerException("parentChannel");
        }
        if (channel2 == null) {
            throw new NullPointerException("childChannel");
        }
        this.parentChannel = channel;
        this.childChannel = channel2;
    }

    @Override // org.jboss.netty.channel.ChildChannelStateEvent, org.jboss.netty.channel.ChannelEvent
    public Channel getChannel() {
        return this.parentChannel;
    }

    @Override // org.jboss.netty.channel.ChannelEvent
    public ChannelFuture getFuture() {
        return Channels.succeededFuture(getChannel());
    }

    @Override // org.jboss.netty.channel.ChildChannelStateEvent
    public Channel getChildChannel() {
        return this.childChannel;
    }

    public String toString() {
        String obj = getChannel().toString();
        StringBuilder sb = new StringBuilder(obj.length() + 32);
        sb.append(obj);
        sb.append(getChildChannel().isOpen() ? " CHILD_OPEN: " : " CHILD_CLOSED: ");
        sb.append(getChildChannel().getId());
        return sb.toString();
    }
}
