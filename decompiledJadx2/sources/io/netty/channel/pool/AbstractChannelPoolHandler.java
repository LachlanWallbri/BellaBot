package io.netty.channel.pool;

import io.netty.channel.Channel;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractChannelPoolHandler implements ChannelPoolHandler {
    @Override // io.netty.channel.pool.ChannelPoolHandler
    public void channelAcquired(Channel channel) throws Exception {
    }

    @Override // io.netty.channel.pool.ChannelPoolHandler
    public void channelReleased(Channel channel) throws Exception {
    }
}
