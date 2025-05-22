package io.netty.resolver.dns;

import io.netty.handler.codec.dns.DnsQuestion;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface DnsQueryLifecycleObserverFactory {
    DnsQueryLifecycleObserver newDnsQueryLifecycleObserver(DnsQuestion dnsQuestion);
}
