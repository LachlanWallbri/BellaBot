package io.netty.resolver.dns;

import io.netty.channel.EventLoop;
import java.net.InetSocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes8.dex */
public interface AuthoritativeDnsServerCache {
    void cache(String str, InetSocketAddress inetSocketAddress, long j, EventLoop eventLoop);

    void clear();

    boolean clear(String str);

    DnsServerAddressStream get(String str);
}
