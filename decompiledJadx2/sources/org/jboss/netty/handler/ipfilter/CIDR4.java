package org.jboss.netty.handler.ipfilter;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* loaded from: classes7.dex */
public class CIDR4 extends CIDR {
    private final int addressEndInt;
    private int addressInt;

    private static int ipv4PrefixLengthToLength(int i) {
        return 1 << (32 - i);
    }

    private static int ipv4PrefixLengthToMask(int i) {
        return ~((1 << (32 - i)) - 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CIDR4(Inet4Address inet4Address, int i) {
        this.cidrMask = i;
        this.addressInt = ipv4AddressToInt(inet4Address);
        this.addressInt = ipv4PrefixLengthToMask(i) & this.addressInt;
        try {
            this.baseAddress = intToIPv4Address(this.addressInt);
        } catch (UnknownHostException unused) {
        }
        this.addressEndInt = (this.addressInt + ipv4PrefixLengthToLength(this.cidrMask)) - 1;
    }

    @Override // org.jboss.netty.handler.ipfilter.CIDR
    public InetAddress getEndAddress() {
        try {
            return intToIPv4Address(this.addressEndInt);
        } catch (UnknownHostException unused) {
            return null;
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(CIDR cidr) {
        if (cidr instanceof CIDR6) {
            int ipv4AddressToInt = ipv4AddressToInt(getIpV4FromIpV6((Inet6Address) cidr.baseAddress));
            if (ipv4AddressToInt == this.addressInt && cidr.cidrMask == this.cidrMask) {
                return 0;
            }
            int i = this.addressInt;
            if (ipv4AddressToInt < i) {
                return 1;
            }
            return (ipv4AddressToInt <= i && cidr.cidrMask >= this.cidrMask) ? 1 : -1;
        }
        CIDR4 cidr4 = (CIDR4) cidr;
        if (cidr4.addressInt == this.addressInt && cidr4.cidrMask == this.cidrMask) {
            return 0;
        }
        int i2 = cidr4.addressInt;
        int i3 = this.addressInt;
        if (i2 < i3) {
            return 1;
        }
        return (i2 <= i3 && cidr4.cidrMask >= this.cidrMask) ? 1 : -1;
    }

    @Override // org.jboss.netty.handler.ipfilter.CIDR
    public boolean contains(InetAddress inetAddress) {
        int ipv4AddressToInt = ipv4AddressToInt(inetAddress);
        return ipv4AddressToInt >= this.addressInt && ipv4AddressToInt <= this.addressEndInt;
    }

    private static InetAddress intToIPv4Address(int i) throws UnknownHostException {
        return InetAddress.getByAddress(new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)});
    }

    private static int ipv4AddressToInt(InetAddress inetAddress) {
        byte[] address;
        if (inetAddress instanceof Inet6Address) {
            address = getIpV4FromIpV6((Inet6Address) inetAddress);
        } else {
            address = inetAddress.getAddress();
        }
        return ipv4AddressToInt(address);
    }

    private static int ipv4AddressToInt(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            i = (i << 8) | (b & 255);
        }
        return i;
    }
}
