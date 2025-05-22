package org.jboss.netty.channel.socket;

import java.net.InetSocketAddress;
import org.jboss.netty.channel.Channel;

/* loaded from: classes7.dex */
public interface SocketChannel extends Channel {
    @Override // org.jboss.netty.channel.Channel
    SocketChannelConfig getConfig();

    @Override // org.jboss.netty.channel.Channel
    InetSocketAddress getLocalAddress();

    @Override // org.jboss.netty.channel.Channel
    InetSocketAddress getRemoteAddress();
}
