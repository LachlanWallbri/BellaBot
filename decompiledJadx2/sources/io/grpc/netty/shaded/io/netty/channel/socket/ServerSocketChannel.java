package io.grpc.netty.shaded.io.netty.channel.socket;

import io.grpc.netty.shaded.io.netty.channel.ServerChannel;
import java.net.InetSocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ServerSocketChannel extends ServerChannel {
    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    ServerSocketChannelConfig config();

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    InetSocketAddress localAddress();

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    InetSocketAddress remoteAddress();
}
