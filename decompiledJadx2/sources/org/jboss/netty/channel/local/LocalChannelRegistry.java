package org.jboss.netty.channel.local;

import java.util.concurrent.ConcurrentMap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.util.internal.ConcurrentHashMap;

/* loaded from: classes7.dex */
final class LocalChannelRegistry {
    private static final ConcurrentMap<LocalAddress, Channel> map = new ConcurrentHashMap();

    static boolean isRegistered(LocalAddress localAddress) {
        return map.containsKey(localAddress);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Channel getChannel(LocalAddress localAddress) {
        return map.get(localAddress);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean register(LocalAddress localAddress, Channel channel) {
        return map.putIfAbsent(localAddress, channel) == null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean unregister(LocalAddress localAddress) {
        return map.remove(localAddress) != null;
    }

    private LocalChannelRegistry() {
    }
}
