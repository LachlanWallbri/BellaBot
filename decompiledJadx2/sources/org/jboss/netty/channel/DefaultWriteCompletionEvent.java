package org.jboss.netty.channel;

/* loaded from: classes7.dex */
public class DefaultWriteCompletionEvent implements WriteCompletionEvent {
    private final Channel channel;
    private final long writtenAmount;

    public DefaultWriteCompletionEvent(Channel channel, long j) {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        if (j <= 0) {
            throw new IllegalArgumentException("writtenAmount must be a positive integer: " + j);
        }
        this.channel = channel;
        this.writtenAmount = j;
    }

    @Override // org.jboss.netty.channel.ChannelEvent
    public Channel getChannel() {
        return this.channel;
    }

    @Override // org.jboss.netty.channel.ChannelEvent
    public ChannelFuture getFuture() {
        return Channels.succeededFuture(getChannel());
    }

    @Override // org.jboss.netty.channel.WriteCompletionEvent
    public long getWrittenAmount() {
        return this.writtenAmount;
    }

    public String toString() {
        String obj = getChannel().toString();
        StringBuilder sb = new StringBuilder(obj.length() + 32);
        sb.append(obj);
        sb.append(" WRITTEN_AMOUNT: ");
        sb.append(getWrittenAmount());
        return sb.toString();
    }
}
