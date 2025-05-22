package io.netty.resolver.dns;

import io.netty.channel.EventLoop;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes8.dex */
public final class NoopDnsCnameCache implements DnsCnameCache {
    public static final NoopDnsCnameCache INSTANCE = new NoopDnsCnameCache();

    @Override // io.netty.resolver.dns.DnsCnameCache
    public void cache(String str, String str2, long j, EventLoop eventLoop) {
    }

    @Override // io.netty.resolver.dns.DnsCnameCache
    public void clear() {
    }

    @Override // io.netty.resolver.dns.DnsCnameCache
    public boolean clear(String str) {
        return false;
    }

    @Override // io.netty.resolver.dns.DnsCnameCache
    public String get(String str) {
        return null;
    }

    private NoopDnsCnameCache() {
    }
}
