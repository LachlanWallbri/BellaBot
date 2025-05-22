package io.netty.resolver.dns;

import java.net.InetSocketAddress;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class DefaultDnsServerAddresses extends DnsServerAddresses {
    protected final InetSocketAddress[] addresses;
    private final String strVal;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultDnsServerAddresses(String str, InetSocketAddress[] inetSocketAddressArr) {
        this.addresses = inetSocketAddressArr;
        StringBuilder sb = new StringBuilder(str.length() + 2 + (inetSocketAddressArr.length * 16));
        sb.append(str);
        sb.append('(');
        for (InetSocketAddress inetSocketAddress : inetSocketAddressArr) {
            sb.append(inetSocketAddress);
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(')');
        this.strVal = sb.toString();
    }

    public String toString() {
        return this.strVal;
    }
}
