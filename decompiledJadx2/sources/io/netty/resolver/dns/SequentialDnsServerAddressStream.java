package io.netty.resolver.dns;

import java.net.InetSocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
final class SequentialDnsServerAddressStream implements DnsServerAddressStream {
    private final InetSocketAddress[] addresses;

    /* renamed from: i */
    private int f8574i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SequentialDnsServerAddressStream(InetSocketAddress[] inetSocketAddressArr, int i) {
        this.addresses = inetSocketAddressArr;
        this.f8574i = i;
    }

    @Override // io.netty.resolver.dns.DnsServerAddressStream
    public InetSocketAddress next() {
        int i = this.f8574i;
        InetSocketAddress[] inetSocketAddressArr = this.addresses;
        InetSocketAddress inetSocketAddress = inetSocketAddressArr[i];
        int i2 = i + 1;
        if (i2 < inetSocketAddressArr.length) {
            this.f8574i = i2;
        } else {
            this.f8574i = 0;
        }
        return inetSocketAddress;
    }

    @Override // io.netty.resolver.dns.DnsServerAddressStream
    public int size() {
        return this.addresses.length;
    }

    @Override // io.netty.resolver.dns.DnsServerAddressStream
    public SequentialDnsServerAddressStream duplicate() {
        return new SequentialDnsServerAddressStream(this.addresses, this.f8574i);
    }

    public String toString() {
        return toString("sequential", this.f8574i, this.addresses);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toString(String str, int i, InetSocketAddress[] inetSocketAddressArr) {
        StringBuilder sb = new StringBuilder(str.length() + 2 + (inetSocketAddressArr.length * 16));
        sb.append(str);
        sb.append("(index: ");
        sb.append(i);
        sb.append(", addrs: (");
        for (InetSocketAddress inetSocketAddress : inetSocketAddressArr) {
            sb.append(inetSocketAddress);
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("))");
        return sb.toString();
    }
}
