package io.netty.resolver.dns;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class MultiDnsServerAddressStreamProvider implements DnsServerAddressStreamProvider {
    private final DnsServerAddressStreamProvider[] providers;

    public MultiDnsServerAddressStreamProvider(List<DnsServerAddressStreamProvider> list) {
        this.providers = (DnsServerAddressStreamProvider[]) list.toArray(new DnsServerAddressStreamProvider[0]);
    }

    public MultiDnsServerAddressStreamProvider(DnsServerAddressStreamProvider... dnsServerAddressStreamProviderArr) {
        this.providers = (DnsServerAddressStreamProvider[]) dnsServerAddressStreamProviderArr.clone();
    }

    @Override // io.netty.resolver.dns.DnsServerAddressStreamProvider
    public DnsServerAddressStream nameServerAddressStream(String str) {
        for (DnsServerAddressStreamProvider dnsServerAddressStreamProvider : this.providers) {
            DnsServerAddressStream nameServerAddressStream = dnsServerAddressStreamProvider.nameServerAddressStream(str);
            if (nameServerAddressStream != null) {
                return nameServerAddressStream;
            }
        }
        return null;
    }
}
