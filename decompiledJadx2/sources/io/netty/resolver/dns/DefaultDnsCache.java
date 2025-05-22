package io.netty.resolver.dns;

import io.netty.channel.EventLoop;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.util.concurrent.ScheduledFuture;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class DefaultDnsCache implements DnsCache {
    private final int maxTtl;
    private final int minTtl;
    private final int negativeTtl;
    private final ConcurrentMap<String, Entries> resolveCache;

    public DefaultDnsCache() {
        this(0, Integer.MAX_VALUE, 0);
    }

    public DefaultDnsCache(int i, int i2, int i3) {
        this.resolveCache = PlatformDependent.newConcurrentHashMap();
        this.minTtl = ObjectUtil.checkPositiveOrZero(i, "minTtl");
        this.maxTtl = ObjectUtil.checkPositiveOrZero(i2, "maxTtl");
        if (i > i2) {
            throw new IllegalArgumentException("minTtl: " + i + ", maxTtl: " + i2 + " (expected: 0 <= minTtl <= maxTtl)");
        }
        this.negativeTtl = ObjectUtil.checkPositiveOrZero(i3, "negativeTtl");
    }

    public int minTtl() {
        return this.minTtl;
    }

    public int maxTtl() {
        return this.maxTtl;
    }

    public int negativeTtl() {
        return this.negativeTtl;
    }

    @Override // io.netty.resolver.dns.DnsCache
    public void clear() {
        while (!this.resolveCache.isEmpty()) {
            Iterator<Map.Entry<String, Entries>> it = this.resolveCache.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Entries> next = it.next();
                it.remove();
                next.getValue().clearAndCancel();
            }
        }
    }

    @Override // io.netty.resolver.dns.DnsCache
    public boolean clear(String str) {
        ObjectUtil.checkNotNull(str, "hostname");
        Entries remove = this.resolveCache.remove(str);
        return remove != null && remove.clearAndCancel();
    }

    private static boolean emptyAdditionals(DnsRecord[] dnsRecordArr) {
        return dnsRecordArr == null || dnsRecordArr.length == 0;
    }

    @Override // io.netty.resolver.dns.DnsCache
    public List<? extends DnsCacheEntry> get(String str, DnsRecord[] dnsRecordArr) {
        ObjectUtil.checkNotNull(str, "hostname");
        if (!emptyAdditionals(dnsRecordArr)) {
            return Collections.emptyList();
        }
        Entries entries = this.resolveCache.get(str);
        if (entries == null) {
            return null;
        }
        return entries.get();
    }

    @Override // io.netty.resolver.dns.DnsCache
    public DnsCacheEntry cache(String str, DnsRecord[] dnsRecordArr, InetAddress inetAddress, long j, EventLoop eventLoop) {
        ObjectUtil.checkNotNull(str, "hostname");
        ObjectUtil.checkNotNull(inetAddress, "address");
        ObjectUtil.checkNotNull(eventLoop, "loop");
        DefaultDnsCacheEntry defaultDnsCacheEntry = new DefaultDnsCacheEntry(str, inetAddress);
        if (this.maxTtl != 0 && emptyAdditionals(dnsRecordArr)) {
            cache0(defaultDnsCacheEntry, Math.max(this.minTtl, (int) Math.min(this.maxTtl, j)), eventLoop);
        }
        return defaultDnsCacheEntry;
    }

    @Override // io.netty.resolver.dns.DnsCache
    public DnsCacheEntry cache(String str, DnsRecord[] dnsRecordArr, Throwable th, EventLoop eventLoop) {
        ObjectUtil.checkNotNull(str, "hostname");
        ObjectUtil.checkNotNull(th, "cause");
        ObjectUtil.checkNotNull(eventLoop, "loop");
        DefaultDnsCacheEntry defaultDnsCacheEntry = new DefaultDnsCacheEntry(str, th);
        if (this.negativeTtl != 0 && emptyAdditionals(dnsRecordArr)) {
            cache0(defaultDnsCacheEntry, this.negativeTtl, eventLoop);
        }
        return defaultDnsCacheEntry;
    }

    private void cache0(DefaultDnsCacheEntry defaultDnsCacheEntry, int i, EventLoop eventLoop) {
        if (this.resolveCache.get(defaultDnsCacheEntry.hostname()) == null) {
            Entries putIfAbsent = this.resolveCache.putIfAbsent(defaultDnsCacheEntry.hostname(), new Entries(defaultDnsCacheEntry));
            if (putIfAbsent != null) {
                putIfAbsent.add(defaultDnsCacheEntry);
            }
        }
        scheduleCacheExpiration(defaultDnsCacheEntry, i, eventLoop);
    }

    private void scheduleCacheExpiration(final DefaultDnsCacheEntry defaultDnsCacheEntry, int i, EventLoop eventLoop) {
        defaultDnsCacheEntry.scheduleExpiration(eventLoop, new Runnable() { // from class: io.netty.resolver.dns.DefaultDnsCache.1
            @Override // java.lang.Runnable
            public void run() {
                Entries entries = (Entries) DefaultDnsCache.this.resolveCache.remove(defaultDnsCacheEntry.hostname);
                if (entries != null) {
                    entries.clearAndCancel();
                }
            }
        }, i, TimeUnit.SECONDS);
    }

    public String toString() {
        return "DefaultDnsCache(minTtl=" + this.minTtl + ", maxTtl=" + this.maxTtl + ", negativeTtl=" + this.negativeTtl + ", cached resolved hostname=" + this.resolveCache.size() + ")";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class DefaultDnsCacheEntry implements DnsCacheEntry {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final InetAddress address;
        private final Throwable cause;
        private volatile ScheduledFuture<?> expirationFuture;
        private final String hostname;

        DefaultDnsCacheEntry(String str, InetAddress inetAddress) {
            this.hostname = (String) ObjectUtil.checkNotNull(str, "hostname");
            this.address = (InetAddress) ObjectUtil.checkNotNull(inetAddress, "address");
            this.cause = null;
        }

        DefaultDnsCacheEntry(String str, Throwable th) {
            this.hostname = (String) ObjectUtil.checkNotNull(str, "hostname");
            this.cause = (Throwable) ObjectUtil.checkNotNull(th, "cause");
            this.address = null;
        }

        @Override // io.netty.resolver.dns.DnsCacheEntry
        public InetAddress address() {
            return this.address;
        }

        @Override // io.netty.resolver.dns.DnsCacheEntry
        public Throwable cause() {
            return this.cause;
        }

        String hostname() {
            return this.hostname;
        }

        void scheduleExpiration(EventLoop eventLoop, Runnable runnable, long j, TimeUnit timeUnit) {
            this.expirationFuture = eventLoop.schedule(runnable, j, timeUnit);
        }

        void cancelExpiration() {
            ScheduledFuture<?> scheduledFuture = this.expirationFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
        }

        public String toString() {
            if (this.cause != null) {
                return this.hostname + '/' + this.cause;
            }
            return this.address.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Entries extends AtomicReference<List<DefaultDnsCacheEntry>> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        Entries(DefaultDnsCacheEntry defaultDnsCacheEntry) {
            super(Collections.singletonList(defaultDnsCacheEntry));
        }

        void add(DefaultDnsCacheEntry defaultDnsCacheEntry) {
            if (defaultDnsCacheEntry.cause() != null) {
                cancelExpiration(getAndSet(Collections.singletonList(defaultDnsCacheEntry)));
                return;
            }
            while (true) {
                List<DefaultDnsCacheEntry> list = get();
                if (!list.isEmpty()) {
                    DefaultDnsCacheEntry defaultDnsCacheEntry2 = list.get(0);
                    if (defaultDnsCacheEntry2.cause() != null) {
                        if (compareAndSet(list, Collections.singletonList(defaultDnsCacheEntry))) {
                            defaultDnsCacheEntry2.cancelExpiration();
                            return;
                        }
                    } else {
                        ArrayList arrayList = new ArrayList(list.size() + 1);
                        arrayList.addAll(list);
                        arrayList.add(defaultDnsCacheEntry);
                        if (compareAndSet(list, arrayList)) {
                            return;
                        }
                    }
                } else if (compareAndSet(list, Collections.singletonList(defaultDnsCacheEntry))) {
                    return;
                }
            }
        }

        boolean clearAndCancel() {
            List<DefaultDnsCacheEntry> andSet = getAndSet(Collections.emptyList());
            if (andSet.isEmpty()) {
                return false;
            }
            cancelExpiration(andSet);
            return true;
        }

        private static void cancelExpiration(List<DefaultDnsCacheEntry> list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                list.get(i).cancelExpiration();
            }
        }
    }
}
