package io.netty.resolver.dns;

import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import java.net.InetSocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class DnsNameResolverException extends RuntimeException {
    private static final long serialVersionUID = -8826717909627131850L;
    private final DnsQuestion question;
    private final InetSocketAddress remoteAddress;

    public DnsNameResolverException(InetSocketAddress inetSocketAddress, DnsQuestion dnsQuestion, String str) {
        super(str);
        this.remoteAddress = validateRemoteAddress(inetSocketAddress);
        this.question = validateQuestion(dnsQuestion);
    }

    public DnsNameResolverException(InetSocketAddress inetSocketAddress, DnsQuestion dnsQuestion, String str, Throwable th) {
        super(str, th);
        this.remoteAddress = validateRemoteAddress(inetSocketAddress);
        this.question = validateQuestion(dnsQuestion);
    }

    private static InetSocketAddress validateRemoteAddress(InetSocketAddress inetSocketAddress) {
        return (InetSocketAddress) ObjectUtil.checkNotNull(inetSocketAddress, "remoteAddress");
    }

    private static DnsQuestion validateQuestion(DnsQuestion dnsQuestion) {
        return (DnsQuestion) ObjectUtil.checkNotNull(dnsQuestion, "question");
    }

    public InetSocketAddress remoteAddress() {
        return this.remoteAddress;
    }

    public DnsQuestion question() {
        return this.question;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(EmptyArrays.EMPTY_STACK_TRACE);
        return this;
    }
}
