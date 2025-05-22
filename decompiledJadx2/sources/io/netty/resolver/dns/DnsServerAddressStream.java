package io.netty.resolver.dns;

import java.net.InetSocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface DnsServerAddressStream {
    DnsServerAddressStream duplicate();

    InetSocketAddress next();

    int size();
}
