package io.netty.resolver.dns;

import io.netty.channel.EventLoop;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.handler.codec.dns.DnsRecordType;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class DnsAddressResolveContext extends DnsResolveContext<InetAddress> {
    private final DnsCache resolveCache;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DnsAddressResolveContext(DnsNameResolver dnsNameResolver, String str, DnsRecord[] dnsRecordArr, DnsServerAddressStream dnsServerAddressStream, DnsCache dnsCache) {
        super(dnsNameResolver, str, 1, dnsNameResolver.resolveRecordTypes(), dnsRecordArr, dnsServerAddressStream);
        this.resolveCache = dnsCache;
    }

    @Override // io.netty.resolver.dns.DnsResolveContext
    DnsResolveContext<InetAddress> newResolverContext(DnsNameResolver dnsNameResolver, String str, int i, DnsRecordType[] dnsRecordTypeArr, DnsRecord[] dnsRecordArr, DnsServerAddressStream dnsServerAddressStream) {
        return new DnsAddressResolveContext(dnsNameResolver, str, dnsRecordArr, dnsServerAddressStream, this.resolveCache);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.netty.resolver.dns.DnsResolveContext
    public InetAddress convertRecord(DnsRecord dnsRecord, String str, DnsRecord[] dnsRecordArr, EventLoop eventLoop) {
        return DnsAddressDecoder.decodeAddress(dnsRecord, str, this.parent.isDecodeIdn());
    }

    @Override // io.netty.resolver.dns.DnsResolveContext
    boolean containsExpectedResult(List<InetAddress> list) {
        int size = list.size();
        Class<? extends InetAddress> addressType = this.parent.preferredAddressType().addressType();
        for (int i = 0; i < size; i++) {
            if (addressType.isInstance(list.get(i))) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.netty.resolver.dns.DnsResolveContext
    public void cache(String str, DnsRecord[] dnsRecordArr, DnsRecord dnsRecord, InetAddress inetAddress) {
        this.resolveCache.cache(str, dnsRecordArr, inetAddress, dnsRecord.timeToLive(), this.parent.f8571ch.eventLoop());
    }

    @Override // io.netty.resolver.dns.DnsResolveContext
    void cache(String str, DnsRecord[] dnsRecordArr, UnknownHostException unknownHostException) {
        this.resolveCache.cache(str, dnsRecordArr, unknownHostException, this.parent.f8571ch.eventLoop());
    }
}
