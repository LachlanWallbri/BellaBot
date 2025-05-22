package org.jboss.netty.channel;

/* loaded from: classes7.dex */
public interface ChildChannelStateEvent extends ChannelEvent {
    @Override // org.jboss.netty.channel.ChannelEvent
    Channel getChannel();

    Channel getChildChannel();
}
