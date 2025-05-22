package io.netty.resolver.dns;

import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
abstract class UniSequentialDnsServerAddressStreamProvider implements DnsServerAddressStreamProvider {
    private final DnsServerAddresses addresses;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UniSequentialDnsServerAddressStreamProvider(DnsServerAddresses dnsServerAddresses) {
        this.addresses = (DnsServerAddresses) ObjectUtil.checkNotNull(dnsServerAddresses, "addresses");
    }

    @Override // io.netty.resolver.dns.DnsServerAddressStreamProvider
    public final DnsServerAddressStream nameServerAddressStream(String str) {
        return this.addresses.stream();
    }
}
