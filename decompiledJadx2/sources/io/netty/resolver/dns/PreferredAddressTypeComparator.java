package io.netty.resolver.dns;

import io.netty.channel.socket.InternetProtocolFamily;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.Comparator;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes8.dex */
final class PreferredAddressTypeComparator implements Comparator<InetAddress> {
    private static final PreferredAddressTypeComparator IPv4 = new PreferredAddressTypeComparator(Inet4Address.class);
    private static final PreferredAddressTypeComparator IPv6 = new PreferredAddressTypeComparator(Inet6Address.class);
    private final Class<? extends InetAddress> preferredAddressType;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* renamed from: io.netty.resolver.dns.PreferredAddressTypeComparator$1 */
    /* loaded from: classes8.dex */
    static /* synthetic */ class C74091 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$channel$socket$InternetProtocolFamily = new int[InternetProtocolFamily.values().length];

        static {
            try {
                $SwitchMap$io$netty$channel$socket$InternetProtocolFamily[InternetProtocolFamily.IPv4.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$channel$socket$InternetProtocolFamily[InternetProtocolFamily.IPv6.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static PreferredAddressTypeComparator comparator(InternetProtocolFamily internetProtocolFamily) {
        int i = C74091.$SwitchMap$io$netty$channel$socket$InternetProtocolFamily[internetProtocolFamily.ordinal()];
        if (i == 1) {
            return IPv4;
        }
        if (i == 2) {
            return IPv6;
        }
        throw new IllegalArgumentException();
    }

    private PreferredAddressTypeComparator(Class<? extends InetAddress> cls) {
        this.preferredAddressType = cls;
    }

    @Override // java.util.Comparator
    public int compare(InetAddress inetAddress, InetAddress inetAddress2) {
        if (inetAddress.getClass() == inetAddress2.getClass()) {
            return 0;
        }
        return this.preferredAddressType.isAssignableFrom(inetAddress.getClass()) ? -1 : 1;
    }
}
