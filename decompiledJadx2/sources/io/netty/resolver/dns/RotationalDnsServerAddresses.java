package io.netty.resolver.dns;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class RotationalDnsServerAddresses extends DefaultDnsServerAddresses {
    private static final AtomicIntegerFieldUpdater<RotationalDnsServerAddresses> startIdxUpdater = AtomicIntegerFieldUpdater.newUpdater(RotationalDnsServerAddresses.class, "startIdx");
    private volatile int startIdx;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RotationalDnsServerAddresses(InetSocketAddress[] inetSocketAddressArr) {
        super("rotational", inetSocketAddressArr);
    }

    @Override // io.netty.resolver.dns.DnsServerAddresses
    public DnsServerAddressStream stream() {
        int i;
        int i2;
        do {
            i = this.startIdx;
            i2 = i + 1;
            if (i2 >= this.addresses.length) {
                i2 = 0;
            }
        } while (!startIdxUpdater.compareAndSet(this, i, i2));
        return new SequentialDnsServerAddressStream(this.addresses, i);
    }
}
