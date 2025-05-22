package io.netty.resolver.dns;

import io.netty.channel.ChannelFactory;
import io.netty.channel.EventLoop;
import io.netty.channel.ReflectiveChannelFactory;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.resolver.HostsFileEntriesResolver;
import io.netty.resolver.ResolvedAddressTypes;
import io.netty.util.internal.ObjectUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class DnsNameResolverBuilder {
    private DnsCache authoritativeDnsServerCache;
    private ChannelFactory<? extends DatagramChannel> channelFactory;
    private final EventLoop eventLoop;
    private Integer maxTtl;
    private Integer minTtl;
    private Integer negativeTtl;
    private DnsCache resolveCache;
    private String[] searchDomains;
    private boolean traceEnabled;
    private long queryTimeoutMillis = 5000;
    private ResolvedAddressTypes resolvedAddressTypes = DnsNameResolver.DEFAULT_RESOLVE_ADDRESS_TYPES;
    private boolean recursionDesired = true;
    private int maxQueriesPerResolve = 16;
    private int maxPayloadSize = 4096;
    private boolean optResourceEnabled = true;
    private HostsFileEntriesResolver hostsFileEntriesResolver = HostsFileEntriesResolver.DEFAULT;
    private DnsServerAddressStreamProvider dnsServerAddressStreamProvider = DnsServerAddressStreamProviders.platformDefault();
    private DnsQueryLifecycleObserverFactory dnsQueryLifecycleObserverFactory = NoopDnsQueryLifecycleObserverFactory.INSTANCE;
    private int ndots = -1;
    private boolean decodeIdn = true;

    public DnsNameResolverBuilder(EventLoop eventLoop) {
        this.eventLoop = eventLoop;
    }

    public DnsNameResolverBuilder channelFactory(ChannelFactory<? extends DatagramChannel> channelFactory) {
        this.channelFactory = channelFactory;
        return this;
    }

    public DnsNameResolverBuilder channelType(Class<? extends DatagramChannel> cls) {
        return channelFactory(new ReflectiveChannelFactory(cls));
    }

    public DnsNameResolverBuilder resolveCache(DnsCache dnsCache) {
        this.resolveCache = dnsCache;
        return this;
    }

    public DnsNameResolverBuilder dnsQueryLifecycleObserverFactory(DnsQueryLifecycleObserverFactory dnsQueryLifecycleObserverFactory) {
        this.dnsQueryLifecycleObserverFactory = (DnsQueryLifecycleObserverFactory) ObjectUtil.checkNotNull(dnsQueryLifecycleObserverFactory, "lifecycleObserverFactory");
        return this;
    }

    public DnsNameResolverBuilder authoritativeDnsServerCache(DnsCache dnsCache) {
        this.authoritativeDnsServerCache = dnsCache;
        return this;
    }

    public DnsNameResolverBuilder ttl(int i, int i2) {
        this.maxTtl = Integer.valueOf(i2);
        this.minTtl = Integer.valueOf(i);
        return this;
    }

    public DnsNameResolverBuilder negativeTtl(int i) {
        this.negativeTtl = Integer.valueOf(i);
        return this;
    }

    public DnsNameResolverBuilder queryTimeoutMillis(long j) {
        this.queryTimeoutMillis = j;
        return this;
    }

    public static ResolvedAddressTypes computeResolvedAddressTypes(InternetProtocolFamily... internetProtocolFamilyArr) {
        if (internetProtocolFamilyArr == null || internetProtocolFamilyArr.length == 0) {
            return DnsNameResolver.DEFAULT_RESOLVE_ADDRESS_TYPES;
        }
        if (internetProtocolFamilyArr.length > 2) {
            throw new IllegalArgumentException("No more than 2 InternetProtocolFamilies");
        }
        int i = C73931.$SwitchMap$io$netty$channel$socket$InternetProtocolFamily[internetProtocolFamilyArr[0].ordinal()];
        if (i == 1) {
            return (internetProtocolFamilyArr.length < 2 || internetProtocolFamilyArr[1] != InternetProtocolFamily.IPv6) ? ResolvedAddressTypes.IPV4_ONLY : ResolvedAddressTypes.IPV4_PREFERRED;
        }
        if (i == 2) {
            return (internetProtocolFamilyArr.length < 2 || internetProtocolFamilyArr[1] != InternetProtocolFamily.IPv4) ? ResolvedAddressTypes.IPV6_ONLY : ResolvedAddressTypes.IPV6_PREFERRED;
        }
        throw new IllegalArgumentException("Couldn't resolve ResolvedAddressTypes from InternetProtocolFamily array");
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.resolver.dns.DnsNameResolverBuilder$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C73931 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$channel$socket$InternetProtocolFamily;

        static {
            int[] iArr = new int[InternetProtocolFamily.values().length];
            $SwitchMap$io$netty$channel$socket$InternetProtocolFamily = iArr;
            try {
                iArr[InternetProtocolFamily.IPv4.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$channel$socket$InternetProtocolFamily[InternetProtocolFamily.IPv6.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public DnsNameResolverBuilder resolvedAddressTypes(ResolvedAddressTypes resolvedAddressTypes) {
        this.resolvedAddressTypes = resolvedAddressTypes;
        return this;
    }

    public DnsNameResolverBuilder recursionDesired(boolean z) {
        this.recursionDesired = z;
        return this;
    }

    public DnsNameResolverBuilder maxQueriesPerResolve(int i) {
        this.maxQueriesPerResolve = i;
        return this;
    }

    public DnsNameResolverBuilder traceEnabled(boolean z) {
        this.traceEnabled = z;
        return this;
    }

    public DnsNameResolverBuilder maxPayloadSize(int i) {
        this.maxPayloadSize = i;
        return this;
    }

    public DnsNameResolverBuilder optResourceEnabled(boolean z) {
        this.optResourceEnabled = z;
        return this;
    }

    public DnsNameResolverBuilder hostsFileEntriesResolver(HostsFileEntriesResolver hostsFileEntriesResolver) {
        this.hostsFileEntriesResolver = hostsFileEntriesResolver;
        return this;
    }

    public DnsNameResolverBuilder nameServerProvider(DnsServerAddressStreamProvider dnsServerAddressStreamProvider) {
        this.dnsServerAddressStreamProvider = (DnsServerAddressStreamProvider) ObjectUtil.checkNotNull(dnsServerAddressStreamProvider, "dnsServerAddressStreamProvider");
        return this;
    }

    public DnsNameResolverBuilder searchDomains(Iterable<String> iterable) {
        String next;
        ObjectUtil.checkNotNull(iterable, "searchDomains");
        ArrayList arrayList = new ArrayList(4);
        Iterator<String> it = iterable.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            if (!arrayList.contains(next)) {
                arrayList.add(next);
            }
        }
        this.searchDomains = (String[]) arrayList.toArray(new String[arrayList.size()]);
        return this;
    }

    public DnsNameResolverBuilder ndots(int i) {
        this.ndots = i;
        return this;
    }

    private DnsCache newCache() {
        return new DefaultDnsCache(ObjectUtil.intValue(this.minTtl, 0), ObjectUtil.intValue(this.maxTtl, Integer.MAX_VALUE), ObjectUtil.intValue(this.negativeTtl, 0));
    }

    public DnsNameResolverBuilder decodeIdn(boolean z) {
        this.decodeIdn = z;
        return this;
    }

    public DnsNameResolver build() {
        if (this.resolveCache != null && (this.minTtl != null || this.maxTtl != null || this.negativeTtl != null)) {
            throw new IllegalStateException("resolveCache and TTLs are mutually exclusive");
        }
        if (this.authoritativeDnsServerCache != null && (this.minTtl != null || this.maxTtl != null || this.negativeTtl != null)) {
            throw new IllegalStateException("authoritativeDnsServerCache and TTLs are mutually exclusive");
        }
        DnsCache dnsCache = this.resolveCache;
        if (dnsCache == null) {
            dnsCache = newCache();
        }
        DnsCache dnsCache2 = dnsCache;
        DnsCache dnsCache3 = this.authoritativeDnsServerCache;
        if (dnsCache3 == null) {
            dnsCache3 = newCache();
        }
        return new DnsNameResolver(this.eventLoop, this.channelFactory, dnsCache2, dnsCache3, this.dnsQueryLifecycleObserverFactory, this.queryTimeoutMillis, this.resolvedAddressTypes, this.recursionDesired, this.maxQueriesPerResolve, this.traceEnabled, this.maxPayloadSize, this.optResourceEnabled, this.hostsFileEntriesResolver, this.dnsServerAddressStreamProvider, this.searchDomains, this.ndots, this.decodeIdn);
    }
}
