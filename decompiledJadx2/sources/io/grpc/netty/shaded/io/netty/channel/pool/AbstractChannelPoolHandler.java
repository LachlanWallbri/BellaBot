package io.grpc.netty.shaded.io.netty.channel.pool;

import io.grpc.netty.shaded.io.netty.channel.Channel;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractChannelPoolHandler implements ChannelPoolHandler {
    @Override // io.grpc.netty.shaded.io.netty.channel.pool.ChannelPoolHandler
    public void channelAcquired(Channel channel) throws Exception {
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.pool.ChannelPoolHandler
    public void channelReleased(Channel channel) throws Exception {
    }
}
