package io.netty.channel.unix;

import io.netty.channel.socket.DuplexChannel;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public interface DomainSocketChannel extends UnixChannel, DuplexChannel {
    @Override // io.netty.channel.Channel
    DomainSocketChannelConfig config();

    @Override // io.netty.channel.Channel
    DomainSocketAddress localAddress();

    @Override // io.netty.channel.Channel
    DomainSocketAddress remoteAddress();
}
