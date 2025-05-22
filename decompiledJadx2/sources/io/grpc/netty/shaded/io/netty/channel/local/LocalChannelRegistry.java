package io.grpc.netty.shaded.io.netty.channel.local;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.net.SocketAddress;
import java.util.concurrent.ConcurrentMap;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class LocalChannelRegistry {
    private static final ConcurrentMap<LocalAddress, Channel> boundChannels = PlatformDependent.newConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LocalAddress register(Channel channel, LocalAddress localAddress, SocketAddress socketAddress) {
        if (localAddress != null) {
            throw new ChannelException("already bound");
        }
        if (!(socketAddress instanceof LocalAddress)) {
            throw new ChannelException("unsupported address type: " + StringUtil.simpleClassName(socketAddress));
        }
        LocalAddress localAddress2 = (LocalAddress) socketAddress;
        if (LocalAddress.ANY.equals(localAddress2)) {
            localAddress2 = new LocalAddress(channel);
        }
        Channel putIfAbsent = boundChannels.putIfAbsent(localAddress2, channel);
        if (putIfAbsent == null) {
            return localAddress2;
        }
        throw new ChannelException("address already in use by: " + putIfAbsent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Channel get(SocketAddress socketAddress) {
        return boundChannels.get(socketAddress);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void unregister(LocalAddress localAddress) {
        boundChannels.remove(localAddress);
    }

    private LocalChannelRegistry() {
    }
}
