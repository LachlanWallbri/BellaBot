package io.netty.resolver.dns;

import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class BiDnsQueryLifecycleObserverFactory implements DnsQueryLifecycleObserverFactory {

    /* renamed from: a */
    private final DnsQueryLifecycleObserverFactory f8569a;

    /* renamed from: b */
    private final DnsQueryLifecycleObserverFactory f8570b;

    public BiDnsQueryLifecycleObserverFactory(DnsQueryLifecycleObserverFactory dnsQueryLifecycleObserverFactory, DnsQueryLifecycleObserverFactory dnsQueryLifecycleObserverFactory2) {
        this.f8569a = (DnsQueryLifecycleObserverFactory) ObjectUtil.checkNotNull(dnsQueryLifecycleObserverFactory, "a");
        this.f8570b = (DnsQueryLifecycleObserverFactory) ObjectUtil.checkNotNull(dnsQueryLifecycleObserverFactory2, "b");
    }

    @Override // io.netty.resolver.dns.DnsQueryLifecycleObserverFactory
    public DnsQueryLifecycleObserver newDnsQueryLifecycleObserver(DnsQuestion dnsQuestion) {
        return new BiDnsQueryLifecycleObserver(this.f8569a.newDnsQueryLifecycleObserver(dnsQuestion), this.f8570b.newDnsQueryLifecycleObserver(dnsQuestion));
    }
}
