package io.netty.resolver.dns;

import io.netty.util.internal.PlatformDependent;
import java.net.InetSocketAddress;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
final class ShuffledDnsServerAddressStream implements DnsServerAddressStream {
    private final InetSocketAddress[] addresses;

    /* renamed from: i */
    private int f8575i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ShuffledDnsServerAddressStream(InetSocketAddress[] inetSocketAddressArr) {
        this.addresses = inetSocketAddressArr;
        shuffle();
    }

    private ShuffledDnsServerAddressStream(InetSocketAddress[] inetSocketAddressArr, int i) {
        this.addresses = inetSocketAddressArr;
        this.f8575i = i;
    }

    private void shuffle() {
        InetSocketAddress[] inetSocketAddressArr = this.addresses;
        Random threadLocalRandom = PlatformDependent.threadLocalRandom();
        for (int length = inetSocketAddressArr.length - 1; length >= 0; length--) {
            InetSocketAddress inetSocketAddress = inetSocketAddressArr[length];
            int nextInt = threadLocalRandom.nextInt(length + 1);
            inetSocketAddressArr[length] = inetSocketAddressArr[nextInt];
            inetSocketAddressArr[nextInt] = inetSocketAddress;
        }
    }

    @Override // io.netty.resolver.dns.DnsServerAddressStream
    public InetSocketAddress next() {
        int i = this.f8575i;
        InetSocketAddress[] inetSocketAddressArr = this.addresses;
        InetSocketAddress inetSocketAddress = inetSocketAddressArr[i];
        int i2 = i + 1;
        if (i2 < inetSocketAddressArr.length) {
            this.f8575i = i2;
        } else {
            this.f8575i = 0;
            shuffle();
        }
        return inetSocketAddress;
    }

    @Override // io.netty.resolver.dns.DnsServerAddressStream
    public int size() {
        return this.addresses.length;
    }

    @Override // io.netty.resolver.dns.DnsServerAddressStream
    public ShuffledDnsServerAddressStream duplicate() {
        return new ShuffledDnsServerAddressStream(this.addresses, this.f8575i);
    }

    public String toString() {
        return SequentialDnsServerAddressStream.toString("shuffled", this.f8575i, this.addresses);
    }
}
