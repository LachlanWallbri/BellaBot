package io.netty.resolver.dns;

import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.handler.codec.dns.DnsResponseCode;
import io.netty.util.internal.ObjectUtil;
import java.net.InetSocketAddress;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class BiDnsQueryLifecycleObserver implements DnsQueryLifecycleObserver {

    /* renamed from: a */
    private final DnsQueryLifecycleObserver f8567a;

    /* renamed from: b */
    private final DnsQueryLifecycleObserver f8568b;

    public BiDnsQueryLifecycleObserver(DnsQueryLifecycleObserver dnsQueryLifecycleObserver, DnsQueryLifecycleObserver dnsQueryLifecycleObserver2) {
        this.f8567a = (DnsQueryLifecycleObserver) ObjectUtil.checkNotNull(dnsQueryLifecycleObserver, "a");
        this.f8568b = (DnsQueryLifecycleObserver) ObjectUtil.checkNotNull(dnsQueryLifecycleObserver2, "b");
    }

    @Override // io.netty.resolver.dns.DnsQueryLifecycleObserver
    public void queryWritten(InetSocketAddress inetSocketAddress, ChannelFuture channelFuture) {
        try {
            this.f8567a.queryWritten(inetSocketAddress, channelFuture);
        } finally {
            this.f8568b.queryWritten(inetSocketAddress, channelFuture);
        }
    }

    @Override // io.netty.resolver.dns.DnsQueryLifecycleObserver
    public void queryCancelled(int i) {
        try {
            this.f8567a.queryCancelled(i);
        } finally {
            this.f8568b.queryCancelled(i);
        }
    }

    @Override // io.netty.resolver.dns.DnsQueryLifecycleObserver
    public DnsQueryLifecycleObserver queryRedirected(List<InetSocketAddress> list) {
        try {
            this.f8567a.queryRedirected(list);
            return this;
        } finally {
            this.f8568b.queryRedirected(list);
        }
    }

    @Override // io.netty.resolver.dns.DnsQueryLifecycleObserver
    public DnsQueryLifecycleObserver queryCNAMEd(DnsQuestion dnsQuestion) {
        try {
            this.f8567a.queryCNAMEd(dnsQuestion);
            return this;
        } finally {
            this.f8568b.queryCNAMEd(dnsQuestion);
        }
    }

    @Override // io.netty.resolver.dns.DnsQueryLifecycleObserver
    public DnsQueryLifecycleObserver queryNoAnswer(DnsResponseCode dnsResponseCode) {
        try {
            this.f8567a.queryNoAnswer(dnsResponseCode);
            return this;
        } finally {
            this.f8568b.queryNoAnswer(dnsResponseCode);
        }
    }

    @Override // io.netty.resolver.dns.DnsQueryLifecycleObserver
    public void queryFailed(Throwable th) {
        try {
            this.f8567a.queryFailed(th);
        } finally {
            this.f8568b.queryFailed(th);
        }
    }

    @Override // io.netty.resolver.dns.DnsQueryLifecycleObserver
    public void querySucceed() {
        try {
            this.f8567a.querySucceed();
        } finally {
            this.f8568b.querySucceed();
        }
    }
}
