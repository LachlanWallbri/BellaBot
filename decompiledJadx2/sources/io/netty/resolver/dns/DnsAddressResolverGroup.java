package io.netty.resolver.dns;

import io.netty.channel.ChannelFactory;
import io.netty.channel.EventLoop;
import io.netty.channel.ReflectiveChannelFactory;
import io.netty.channel.socket.DatagramChannel;
import io.netty.resolver.AddressResolver;
import io.netty.resolver.AddressResolverGroup;
import io.netty.resolver.InetSocketAddressResolver;
import io.netty.resolver.NameResolver;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class DnsAddressResolverGroup extends AddressResolverGroup<InetSocketAddress> {
    private final ChannelFactory<? extends DatagramChannel> channelFactory;
    private final DnsServerAddressStreamProvider nameServerProvider;
    private final ConcurrentMap<String, Promise<List<InetAddress>>> resolveAllsInProgress;
    private final ConcurrentMap<String, Promise<InetAddress>> resolvesInProgress;

    public DnsAddressResolverGroup(Class<? extends DatagramChannel> cls, DnsServerAddressStreamProvider dnsServerAddressStreamProvider) {
        this(new ReflectiveChannelFactory(cls), dnsServerAddressStreamProvider);
    }

    public DnsAddressResolverGroup(ChannelFactory<? extends DatagramChannel> channelFactory, DnsServerAddressStreamProvider dnsServerAddressStreamProvider) {
        this.resolvesInProgress = PlatformDependent.newConcurrentHashMap();
        this.resolveAllsInProgress = PlatformDependent.newConcurrentHashMap();
        this.channelFactory = channelFactory;
        this.nameServerProvider = dnsServerAddressStreamProvider;
    }

    @Override // io.netty.resolver.AddressResolverGroup
    protected final AddressResolver<InetSocketAddress> newResolver(EventExecutor eventExecutor) throws Exception {
        if (!(eventExecutor instanceof EventLoop)) {
            throw new IllegalStateException("unsupported executor type: " + StringUtil.simpleClassName(eventExecutor) + " (expected: " + StringUtil.simpleClassName((Class<?>) EventLoop.class));
        }
        return newResolver((EventLoop) eventExecutor, this.channelFactory, this.nameServerProvider);
    }

    @Deprecated
    protected AddressResolver<InetSocketAddress> newResolver(EventLoop eventLoop, ChannelFactory<? extends DatagramChannel> channelFactory, DnsServerAddressStreamProvider dnsServerAddressStreamProvider) throws Exception {
        return newAddressResolver(eventLoop, new InflightNameResolver(eventLoop, newNameResolver(eventLoop, channelFactory, dnsServerAddressStreamProvider), this.resolvesInProgress, this.resolveAllsInProgress));
    }

    protected NameResolver<InetAddress> newNameResolver(EventLoop eventLoop, ChannelFactory<? extends DatagramChannel> channelFactory, DnsServerAddressStreamProvider dnsServerAddressStreamProvider) throws Exception {
        return new DnsNameResolverBuilder(eventLoop).channelFactory(channelFactory).nameServerProvider(dnsServerAddressStreamProvider).build();
    }

    protected AddressResolver<InetSocketAddress> newAddressResolver(EventLoop eventLoop, NameResolver<InetAddress> nameResolver) throws Exception {
        return new InetSocketAddressResolver(eventLoop, nameResolver);
    }
}
