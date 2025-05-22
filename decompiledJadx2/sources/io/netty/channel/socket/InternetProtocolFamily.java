package io.netty.channel.socket;

import io.netty.util.NetUtil;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public enum InternetProtocolFamily {
    IPv4(Inet4Address.class, 1, NetUtil.LOCALHOST4),
    IPv6(Inet6Address.class, 2, NetUtil.LOCALHOST6);

    private final int addressNumber;
    private final Class<? extends InetAddress> addressType;
    private final InetAddress localHost;

    InternetProtocolFamily(Class cls, int i, InetAddress inetAddress) {
        this.addressType = cls;
        this.addressNumber = i;
        this.localHost = inetAddress;
    }

    public Class<? extends InetAddress> addressType() {
        return this.addressType;
    }

    public int addressNumber() {
        return this.addressNumber;
    }

    public InetAddress localhost() {
        return this.localHost;
    }

    /* renamed from: of */
    public static InternetProtocolFamily m3936of(InetAddress inetAddress) {
        if (inetAddress instanceof Inet4Address) {
            return IPv4;
        }
        if (inetAddress instanceof Inet6Address) {
            return IPv6;
        }
        throw new IllegalArgumentException("address " + inetAddress + " not supported");
    }
}
