package io.netty.channel.group;

import io.netty.channel.Channel;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public interface ChannelMatcher {
    boolean matches(Channel channel);
}
