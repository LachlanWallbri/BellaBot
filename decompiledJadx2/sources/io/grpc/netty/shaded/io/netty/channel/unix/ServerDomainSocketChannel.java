package io.grpc.netty.shaded.io.netty.channel.unix;

import io.grpc.netty.shaded.io.netty.channel.ServerChannel;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ServerDomainSocketChannel extends ServerChannel, UnixChannel {
    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    DomainSocketAddress localAddress();

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    DomainSocketAddress remoteAddress();
}
