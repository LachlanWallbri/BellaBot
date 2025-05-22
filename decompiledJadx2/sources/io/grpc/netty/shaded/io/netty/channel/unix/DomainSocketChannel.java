package io.grpc.netty.shaded.io.netty.channel.unix;

import io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface DomainSocketChannel extends UnixChannel, DuplexChannel {
    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    DomainSocketChannelConfig config();

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    DomainSocketAddress localAddress();

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    DomainSocketAddress remoteAddress();
}
