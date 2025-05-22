package io.netty.resolver.dns;

import java.net.InetSocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class SequentialDnsServerAddressStreamProvider extends UniSequentialDnsServerAddressStreamProvider {
    public SequentialDnsServerAddressStreamProvider(InetSocketAddress... inetSocketAddressArr) {
        super(DnsServerAddresses.sequential(inetSocketAddressArr));
    }

    public SequentialDnsServerAddressStreamProvider(Iterable<? extends InetSocketAddress> iterable) {
        super(DnsServerAddresses.sequential(iterable));
    }
}
