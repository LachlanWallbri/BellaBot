package io.netty.resolver.dns;

import io.netty.channel.EventLoop;
import io.netty.handler.codec.dns.DnsRecord;
import java.net.InetAddress;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class NoopDnsCache implements DnsCache {
    public static final NoopDnsCache INSTANCE = new NoopDnsCache();

    @Override // io.netty.resolver.dns.DnsCache
    public DnsCacheEntry cache(String str, DnsRecord[] dnsRecordArr, Throwable th, EventLoop eventLoop) {
        return null;
    }

    @Override // io.netty.resolver.dns.DnsCache
    public void clear() {
    }

    @Override // io.netty.resolver.dns.DnsCache
    public boolean clear(String str) {
        return false;
    }

    private NoopDnsCache() {
    }

    @Override // io.netty.resolver.dns.DnsCache
    public List<? extends DnsCacheEntry> get(String str, DnsRecord[] dnsRecordArr) {
        return Collections.emptyList();
    }

    @Override // io.netty.resolver.dns.DnsCache
    public DnsCacheEntry cache(String str, DnsRecord[] dnsRecordArr, InetAddress inetAddress, long j, EventLoop eventLoop) {
        return new NoopDnsCacheEntry(inetAddress);
    }

    public String toString() {
        return NoopDnsCache.class.getSimpleName();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private static final class NoopDnsCacheEntry implements DnsCacheEntry {
        private final InetAddress address;

        @Override // io.netty.resolver.dns.DnsCacheEntry
        public Throwable cause() {
            return null;
        }

        NoopDnsCacheEntry(InetAddress inetAddress) {
            this.address = inetAddress;
        }

        @Override // io.netty.resolver.dns.DnsCacheEntry
        public InetAddress address() {
            return this.address;
        }

        public String toString() {
            return this.address.toString();
        }
    }
}
