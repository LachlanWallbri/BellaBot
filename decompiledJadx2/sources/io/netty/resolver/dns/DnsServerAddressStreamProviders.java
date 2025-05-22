package io.netty.resolver.dns;

import io.netty.util.internal.PlatformDependent;
import java.util.concurrent.atomic.AtomicLong;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class DnsServerAddressStreamProviders {
    private static final DnsServerAddressStreamProvider DEFAULT_DNS_SERVER_ADDRESS_STREAM_PROVIDER;

    static {
        DEFAULT_DNS_SERVER_ADDRESS_STREAM_PROVIDER = PlatformDependent.isWindows() ? DefaultDnsServerAddressStreamProvider.INSTANCE : UnixResolverDnsServerAddressStreamProvider.parseSilently();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* renamed from: io.netty.resolver.dns.DnsServerAddressStreamProviders$1 */
    /* loaded from: classes8.dex */
    static class C74041 implements DnsServerAddressStreamProvider {
        private volatile DnsServerAddressStreamProvider currentProvider = provider();
        private final AtomicLong lastRefresh = new AtomicLong(System.nanoTime());

        C74041() {
        }

        @Override // io.netty.resolver.dns.DnsServerAddressStreamProvider
        public DnsServerAddressStream nameServerAddressStream(String str) {
            long j = this.lastRefresh.get();
            DnsServerAddressStreamProvider dnsServerAddressStreamProvider = this.currentProvider;
            if (System.nanoTime() - j > DnsServerAddressStreamProviders.access$000() && this.lastRefresh.compareAndSet(j, System.nanoTime())) {
                dnsServerAddressStreamProvider = provider();
                this.currentProvider = dnsServerAddressStreamProvider;
            }
            return dnsServerAddressStreamProvider.nameServerAddressStream(str);
        }

        private DnsServerAddressStreamProvider provider() {
            return PlatformDependent.isWindows() ? DefaultDnsServerAddressStreamProvider.INSTANCE : UnixResolverDnsServerAddressStreamProvider.parseSilently();
        }
    }

    private DnsServerAddressStreamProviders() {
    }

    public static DnsServerAddressStreamProvider platformDefault() {
        return DEFAULT_DNS_SERVER_ADDRESS_STREAM_PROVIDER;
    }
}
