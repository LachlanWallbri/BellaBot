package io.netty.resolver.dns;

import io.netty.handler.codec.dns.DnsQuestion;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class NoopDnsQueryLifecycleObserverFactory implements DnsQueryLifecycleObserverFactory {
    public static final NoopDnsQueryLifecycleObserverFactory INSTANCE = new NoopDnsQueryLifecycleObserverFactory();

    private NoopDnsQueryLifecycleObserverFactory() {
    }

    @Override // io.netty.resolver.dns.DnsQueryLifecycleObserverFactory
    public DnsQueryLifecycleObserver newDnsQueryLifecycleObserver(DnsQuestion dnsQuestion) {
        return NoopDnsQueryLifecycleObserver.INSTANCE;
    }
}
