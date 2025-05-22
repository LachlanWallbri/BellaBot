package org.jboss.netty.channel.socket;

import java.net.InetSocketAddress;
import org.jboss.netty.channel.ServerChannel;

/* loaded from: classes7.dex */
public interface ServerSocketChannel extends ServerChannel {
    @Override // org.jboss.netty.channel.Channel
    ServerSocketChannelConfig getConfig();

    @Override // org.jboss.netty.channel.Channel
    InetSocketAddress getLocalAddress();

    @Override // org.jboss.netty.channel.Channel
    InetSocketAddress getRemoteAddress();
}
