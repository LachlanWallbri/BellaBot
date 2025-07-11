package org.jboss.netty.channel.group;

import java.net.SocketAddress;
import java.util.Set;
import org.jboss.netty.channel.Channel;

/* loaded from: classes7.dex */
public interface ChannelGroup extends Set<Channel>, Comparable<ChannelGroup> {
    ChannelGroupFuture close();

    ChannelGroupFuture disconnect();

    Channel find(Integer num);

    String getName();

    ChannelGroupFuture setInterestOps(int i);

    ChannelGroupFuture setReadable(boolean z);

    ChannelGroupFuture unbind();

    ChannelGroupFuture write(Object obj);

    ChannelGroupFuture write(Object obj, SocketAddress socketAddress);
}
