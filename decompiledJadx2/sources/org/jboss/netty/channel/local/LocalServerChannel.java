package org.jboss.netty.channel.local;

import org.jboss.netty.channel.ServerChannel;

/* loaded from: classes7.dex */
public interface LocalServerChannel extends ServerChannel {
    @Override // org.jboss.netty.channel.Channel
    LocalAddress getLocalAddress();

    @Override // org.jboss.netty.channel.Channel
    LocalAddress getRemoteAddress();
}
