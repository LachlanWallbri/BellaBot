package org.jboss.netty.channel.group;

import java.util.EventListener;

/* loaded from: classes7.dex */
public interface ChannelGroupFutureListener extends EventListener {
    void operationComplete(ChannelGroupFuture channelGroupFuture) throws Exception;
}
