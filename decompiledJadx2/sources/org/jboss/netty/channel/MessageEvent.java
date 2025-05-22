package org.jboss.netty.channel;

import java.net.SocketAddress;

/* loaded from: classes7.dex */
public interface MessageEvent extends ChannelEvent {
    Object getMessage();

    SocketAddress getRemoteAddress();
}
