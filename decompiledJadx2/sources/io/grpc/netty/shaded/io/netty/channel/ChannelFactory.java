package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.channel.Channel;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ChannelFactory<T extends Channel> extends io.grpc.netty.shaded.io.netty.bootstrap.ChannelFactory<T> {
    @Override // io.grpc.netty.shaded.io.netty.bootstrap.ChannelFactory
    T newChannel();
}
