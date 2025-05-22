package io.netty.resolver.dns;

import io.netty.channel.EventLoop;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes8.dex */
public class DefaultAuthoritativeDnsServerCache implements AuthoritativeDnsServerCache {
    private final Comparator<InetSocketAddress> comparator;
    private final int maxTtl;
    private final int minTtl;
    private final Cache<InetSocketAddress> resolveCache;

    public DefaultAuthoritativeDnsServerCache() {
        this(0, Cache.MAX_SUPPORTED_TTL_SECS, null);
    }

    public DefaultAuthoritativeDnsServerCache(int i, int i2, Comparator<InetSocketAddress> comparator) {
        this.resolveCache = new Cache<InetSocketAddress>() { // from class: io.netty.resolver.dns.DefaultAuthoritativeDnsServerCache.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.netty.resolver.dns.Cache
            public boolean shouldReplaceAll(InetSocketAddress inetSocketAddress) {
                return false;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.netty.resolver.dns.Cache
            public boolean equals(InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2) {
                if (PlatformDependent.javaVersion() >= 7) {
                    return inetSocketAddress.getHostString().equalsIgnoreCase(inetSocketAddress2.getHostString());
                }
                return inetSocketAddress.getHostName().equalsIgnoreCase(inetSocketAddress2.getHostName());
            }

            @Override // io.netty.resolver.dns.Cache
            protected void sortEntries(String str, List<InetSocketAddress> list) {
                if (DefaultAuthoritativeDnsServerCache.this.comparator != null) {
                    Collections.sort(list, DefaultAuthoritativeDnsServerCache.this.comparator);
                }
            }
        };
        this.minTtl = Math.min(Cache.MAX_SUPPORTED_TTL_SECS, ObjectUtil.checkPositiveOrZero(i, "minTtl"));
        this.maxTtl = Math.min(Cache.MAX_SUPPORTED_TTL_SECS, ObjectUtil.checkPositive(i2, "maxTtl"));
        if (i > i2) {
            throw new IllegalArgumentException("minTtl: " + i + ", maxTtl: " + i2 + " (expected: 0 <= minTtl <= maxTtl)");
        }
        this.comparator = comparator;
    }

    @Override // io.netty.resolver.dns.AuthoritativeDnsServerCache
    public DnsServerAddressStream get(String str) {
        ObjectUtil.checkNotNull(str, "hostname");
        List<? extends InetSocketAddress> list = this.resolveCache.get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return new SequentialDnsServerAddressStream(list, 0);
    }

    @Override // io.netty.resolver.dns.AuthoritativeDnsServerCache
    public void cache(String str, InetSocketAddress inetSocketAddress, long j, EventLoop eventLoop) {
        ObjectUtil.checkNotNull(str, "hostname");
        ObjectUtil.checkNotNull(inetSocketAddress, "address");
        ObjectUtil.checkNotNull(eventLoop, "loop");
        if (PlatformDependent.javaVersion() < 7 || inetSocketAddress.getHostString() != null) {
            this.resolveCache.cache(str, inetSocketAddress, Math.max(this.minTtl, (int) Math.min(this.maxTtl, j)), eventLoop);
        }
    }

    @Override // io.netty.resolver.dns.AuthoritativeDnsServerCache
    public void clear() {
        this.resolveCache.clear();
    }

    @Override // io.netty.resolver.dns.AuthoritativeDnsServerCache
    public boolean clear(String str) {
        ObjectUtil.checkNotNull(str, "hostname");
        return this.resolveCache.clear(str);
    }

    public String toString() {
        return "DefaultAuthoritativeDnsServerCache(minTtl=" + this.minTtl + ", maxTtl=" + this.maxTtl + ", cached nameservers=" + this.resolveCache.size() + ')';
    }
}
