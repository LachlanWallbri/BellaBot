package org.jboss.netty.handler.ipfilter;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

/* loaded from: classes7.dex */
public abstract class CIDR implements Comparable<CIDR> {
    protected InetAddress baseAddress;
    protected int cidrMask;

    public abstract boolean contains(InetAddress inetAddress);

    public abstract InetAddress getEndAddress();

    public static CIDR newCIDR(InetAddress inetAddress, int i) throws UnknownHostException {
        if (i < 0) {
            throw new UnknownHostException("Invalid mask length used: " + i);
        }
        if (inetAddress instanceof Inet4Address) {
            if (i > 32) {
                throw new UnknownHostException("Invalid mask length used: " + i);
            }
            return new CIDR4((Inet4Address) inetAddress, i);
        }
        if (i > 128) {
            throw new UnknownHostException("Invalid mask length used: " + i);
        }
        return new CIDR6((Inet6Address) inetAddress, i);
    }

    public static CIDR newCIDR(InetAddress inetAddress, String str) throws UnknownHostException {
        int netMask = getNetMask(str);
        if (netMask < 0) {
            throw new UnknownHostException("Invalid mask length used: " + netMask);
        }
        if (inetAddress instanceof Inet4Address) {
            if (netMask > 32) {
                throw new UnknownHostException("Invalid mask length used: " + netMask);
            }
            return new CIDR4((Inet4Address) inetAddress, netMask);
        }
        int i = netMask + 96;
        if (i > 128) {
            throw new UnknownHostException("Invalid mask length used: " + i);
        }
        return new CIDR6((Inet6Address) inetAddress, i);
    }

    public static CIDR newCIDR(String str) throws UnknownHostException {
        int netMask;
        int indexOf = str.indexOf("/");
        if (indexOf < 0) {
            throw new UnknownHostException("Invalid CIDR notation used: " + str);
        }
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf + 1);
        InetAddress addressStringToInet = addressStringToInet(substring);
        if (substring2.indexOf(".") < 0) {
            netMask = parseInt(substring2, -1);
        } else {
            netMask = getNetMask(substring2);
            if (addressStringToInet instanceof Inet6Address) {
                netMask += 96;
            }
        }
        if (netMask < 0) {
            throw new UnknownHostException("Invalid mask length used: " + substring2);
        }
        return newCIDR(addressStringToInet, netMask);
    }

    public InetAddress getBaseAddress() {
        return this.baseAddress;
    }

    public int getMask() {
        return this.cidrMask;
    }

    public String toString() {
        return this.baseAddress.getHostAddress() + "/" + this.cidrMask;
    }

    public boolean equals(Object obj) {
        return (obj instanceof CIDR) && compareTo((CIDR) obj) == 0;
    }

    public int hashCode() {
        return this.baseAddress.hashCode();
    }

    private static InetAddress addressStringToInet(String str) throws UnknownHostException {
        return InetAddress.getByName(str);
    }

    private static int getNetMask(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
        int[] iArr = new int[4];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            iArr[i] = Integer.parseInt(stringTokenizer.nextToken());
            i++;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            i2 += Integer.bitCount(iArr[i3]);
        }
        return i2;
    }

    private static int parseInt(String str, int i) {
        Integer num;
        if (str == null) {
            return i;
        }
        try {
            num = Integer.decode(str);
        } catch (Exception unused) {
            num = new Integer(i);
        }
        return num.intValue();
    }

    public static byte[] getIpV4FromIpV6(Inet6Address inet6Address) {
        byte[] address = inet6Address.getAddress();
        for (int i = 0; i < 9; i++) {
            if (address[i] != 0) {
                throw new IllegalArgumentException("This IPv6 address cannot be used in IPv4 context");
            }
        }
        if ((address[10] == 0 || address[10] == 255) && (address[11] == 0 || address[11] == 255)) {
            return new byte[]{address[12], address[13], address[14], address[15]};
        }
        throw new IllegalArgumentException("This IPv6 address cannot be used in IPv4 context");
    }

    public static byte[] getIpV6FromIpV4(Inet4Address inet4Address) {
        byte[] address = inet4Address.getAddress();
        return new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, address[0], address[1], address[2], address[3]};
    }
}
