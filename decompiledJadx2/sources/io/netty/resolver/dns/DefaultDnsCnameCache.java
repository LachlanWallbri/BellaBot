package io.netty.resolver.dns;

import io.netty.channel.EventLoop;
import io.netty.util.AsciiString;
import io.netty.util.internal.ObjectUtil;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes8.dex */
public final class DefaultDnsCnameCache implements DnsCnameCache {
    private final Cache<String> cache;
    private final int maxTtl;
    private final int minTtl;

    public DefaultDnsCnameCache() {
        this(0, Cache.MAX_SUPPORTED_TTL_SECS);
    }

    public DefaultDnsCnameCache(int i, int i2) {
        this.cache = new Cache<String>() { // from class: io.netty.resolver.dns.DefaultDnsCnameCache.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.netty.resolver.dns.Cache
            public boolean shouldReplaceAll(String str) {
                return true;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.netty.resolver.dns.Cache
            public boolean equals(String str, String str2) {
                return AsciiString.contentEqualsIgnoreCase(str, str2);
            }
        };
        this.minTtl = Math.min(Cache.MAX_SUPPORTED_TTL_SECS, ObjectUtil.checkPositiveOrZero(i, "minTtl"));
        this.maxTtl = Math.min(Cache.MAX_SUPPORTED_TTL_SECS, ObjectUtil.checkPositive(i2, "maxTtl"));
        if (i <= i2) {
            return;
        }
        throw new IllegalArgumentException("minTtl: " + i + ", maxTtl: " + i2 + " (expected: 0 <= minTtl <= maxTtl)");
    }

    @Override // io.netty.resolver.dns.DnsCnameCache
    public String get(String str) {
        ObjectUtil.checkNotNull(str, "hostname");
        List<? extends String> list = this.cache.get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override // io.netty.resolver.dns.DnsCnameCache
    public void cache(String str, String str2, long j, EventLoop eventLoop) {
        ObjectUtil.checkNotNull(str, "hostname");
        ObjectUtil.checkNotNull(str2, "cname");
        ObjectUtil.checkNotNull(eventLoop, "loop");
        this.cache.cache(str, str2, Math.max(this.minTtl, (int) Math.min(this.maxTtl, j)), eventLoop);
    }

    @Override // io.netty.resolver.dns.DnsCnameCache
    public void clear() {
        this.cache.clear();
    }

    @Override // io.netty.resolver.dns.DnsCnameCache
    public boolean clear(String str) {
        ObjectUtil.checkNotNull(str, "hostname");
        return this.cache.clear(str);
    }
}
