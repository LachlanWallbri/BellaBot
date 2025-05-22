package io.netty.resolver.dns;

import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.handler.codec.dns.DatagramDnsQuery;
import io.netty.handler.codec.dns.DnsQuery;
import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.handler.codec.dns.DnsResponse;
import io.netty.util.concurrent.Promise;
import java.net.InetSocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes8.dex */
final class DatagramDnsQueryContext extends DnsQueryContext {
    protected String protocol() {
        return "UDP";
    }

    DatagramDnsQueryContext(DnsNameResolver dnsNameResolver, InetSocketAddress inetSocketAddress, DnsQuestion dnsQuestion, DnsRecord[] dnsRecordArr, Promise<AddressedEnvelope<DnsResponse, InetSocketAddress>> promise) {
        super(dnsNameResolver, inetSocketAddress, dnsQuestion, dnsRecordArr, promise);
    }

    protected DnsQuery newQuery(int i) {
        return new DatagramDnsQuery(null, nameServerAddr(), i);
    }

    protected Channel channel() {
        return parent().ch;
    }
}
