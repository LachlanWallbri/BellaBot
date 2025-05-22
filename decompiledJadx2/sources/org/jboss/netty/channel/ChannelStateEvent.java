package org.jboss.netty.channel;

/* loaded from: classes7.dex */
public interface ChannelStateEvent extends ChannelEvent {
    ChannelState getState();

    Object getValue();
}
