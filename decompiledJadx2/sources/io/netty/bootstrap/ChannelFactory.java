package io.netty.bootstrap;

import io.netty.channel.Channel;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
@Deprecated
/* loaded from: classes.dex */
public interface ChannelFactory<T extends Channel> {
    T newChannel();
}
