package io.netty.channel.socket;

import java.net.InetSocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public interface SocketChannel extends DuplexChannel {
    @Override // io.netty.channel.Channel
    SocketChannelConfig config();

    @Override // io.netty.channel.Channel
    InetSocketAddress localAddress();

    @Override // io.netty.channel.Channel
    ServerSocketChannel parent();

    @Override // io.netty.channel.Channel
    InetSocketAddress remoteAddress();
}
