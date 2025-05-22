package io.netty.resolver.dns;

import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.handler.codec.dns.DefaultDnsQuery;
import io.netty.handler.codec.dns.DnsQuery;
import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.handler.codec.dns.DnsResponse;
import io.netty.util.concurrent.Promise;
import java.net.InetSocketAddress;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes8.dex */
public final class TcpDnsQueryContext extends DnsQueryContext {
    private final Channel channel;

    protected String protocol() {
        return "TCP";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TcpDnsQueryContext(DnsNameResolver dnsNameResolver, Channel channel, InetSocketAddress inetSocketAddress, DnsQuestion dnsQuestion, DnsRecord[] dnsRecordArr, Promise<AddressedEnvelope<DnsResponse, InetSocketAddress>> promise) {
        super(dnsNameResolver, inetSocketAddress, dnsQuestion, dnsRecordArr, promise);
        this.channel = channel;
    }

    protected DnsQuery newQuery(int i) {
        return new DefaultDnsQuery(i);
    }

    protected Channel channel() {
        return this.channel;
    }
}
