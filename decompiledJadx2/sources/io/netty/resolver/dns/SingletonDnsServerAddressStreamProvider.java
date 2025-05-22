package io.netty.resolver.dns;

import java.net.InetSocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class SingletonDnsServerAddressStreamProvider extends UniSequentialDnsServerAddressStreamProvider {
    public SingletonDnsServerAddressStreamProvider(InetSocketAddress inetSocketAddress) {
        super(DnsServerAddresses.singleton(inetSocketAddress));
    }
}
