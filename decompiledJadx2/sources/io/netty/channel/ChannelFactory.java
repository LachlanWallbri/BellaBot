package io.netty.channel;

import io.netty.channel.Channel;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public interface ChannelFactory<T extends Channel> extends io.netty.bootstrap.ChannelFactory<T> {
    @Override // io.netty.bootstrap.ChannelFactory
    T newChannel();
}
