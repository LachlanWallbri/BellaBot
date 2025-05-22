package io.netty.resolver.dns;

import io.netty.channel.ChannelFactory;
import io.netty.channel.EventLoop;
import io.netty.channel.socket.DatagramChannel;
import io.netty.resolver.AddressResolver;
import io.netty.resolver.NameResolver;
import io.netty.resolver.RoundRobinInetAddressResolver;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class RoundRobinDnsAddressResolverGroup extends DnsAddressResolverGroup {
    public RoundRobinDnsAddressResolverGroup(Class<? extends DatagramChannel> cls, DnsServerAddressStreamProvider dnsServerAddressStreamProvider) {
        super(cls, dnsServerAddressStreamProvider);
    }

    public RoundRobinDnsAddressResolverGroup(ChannelFactory<? extends DatagramChannel> channelFactory, DnsServerAddressStreamProvider dnsServerAddressStreamProvider) {
        super(channelFactory, dnsServerAddressStreamProvider);
    }

    @Override // io.netty.resolver.dns.DnsAddressResolverGroup
    protected final AddressResolver<InetSocketAddress> newAddressResolver(EventLoop eventLoop, NameResolver<InetAddress> nameResolver) throws Exception {
        return new RoundRobinInetAddressResolver(eventLoop, nameResolver).asAddressResolver();
    }
}
