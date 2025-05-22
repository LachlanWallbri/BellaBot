package org.jboss.netty.handler.ipfilter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.Vector;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

/* loaded from: classes7.dex */
public class IpV4Subnet implements IpSet, Comparable<IpV4Subnet> {
    private static final int BYTE_ADDRESS_MASK = 255;
    private static final int SUBNET_MASK = Integer.MIN_VALUE;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) IpV4Subnet.class);
    private int cidrMask;
    private InetAddress inetAddress;
    private int mask;
    private int subnet;

    public IpV4Subnet() {
        this.mask = -1;
        this.inetAddress = null;
        this.subnet = 0;
        this.cidrMask = 0;
    }

    public IpV4Subnet(String str) throws UnknownHostException {
        setNetAddress(str);
    }

    public IpV4Subnet(InetAddress inetAddress, int i) {
        setNetAddress(inetAddress, i);
    }

    public IpV4Subnet(InetAddress inetAddress, String str) {
        setNetAddress(inetAddress, str);
    }

    private void setNetAddress(String str) throws UnknownHostException {
        Vector vector = new Vector();
        StringTokenizer stringTokenizer = new StringTokenizer(str, "/");
        while (stringTokenizer.hasMoreTokens()) {
            vector.add(stringTokenizer.nextElement());
        }
        if (vector.get(1).toString().length() < 3) {
            setNetId(vector.get(0).toString());
            setCidrNetMask(Integer.parseInt(vector.get(1).toString()));
        } else {
            setNetId(vector.get(0).toString());
            setNetMask(vector.get(1).toString());
        }
    }

    private void setNetAddress(InetAddress inetAddress, int i) {
        setNetId(inetAddress);
        setCidrNetMask(i);
    }

    private void setNetAddress(InetAddress inetAddress, String str) {
        setNetId(inetAddress);
        setNetMask(str);
    }

    private void setNetId(String str) throws UnknownHostException {
        setNetId(InetAddress.getByName(str));
    }

    private static int toInt(InetAddress inetAddress) {
        int i = 0;
        for (byte b : inetAddress.getAddress()) {
            i = (i << 8) | (b & 255);
        }
        return i;
    }

    private void setNetId(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
        this.subnet = toInt(inetAddress);
    }

    private void setNetMask(String str) {
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
        setCidrNetMask(i2);
    }

    private void setCidrNetMask(int i) {
        this.cidrMask = i;
        this.mask = Integer.MIN_VALUE >> (this.cidrMask - 1);
    }

    public boolean contains(String str) throws UnknownHostException {
        return contains(InetAddress.getByName(str));
    }

    @Override // org.jboss.netty.handler.ipfilter.IpSet
    public boolean contains(InetAddress inetAddress) {
        return this.mask == -1 || (toInt(inetAddress) & this.mask) == this.subnet;
    }

    public String toString() {
        return this.inetAddress.getHostAddress() + "/" + this.cidrMask;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IpV4Subnet)) {
            return false;
        }
        IpV4Subnet ipV4Subnet = (IpV4Subnet) obj;
        return ipV4Subnet.subnet == this.subnet && ipV4Subnet.cidrMask == this.cidrMask;
    }

    public int hashCode() {
        return this.subnet;
    }

    @Override // java.lang.Comparable
    public int compareTo(IpV4Subnet ipV4Subnet) {
        if (ipV4Subnet.subnet == this.subnet && ipV4Subnet.cidrMask == this.cidrMask) {
            return 0;
        }
        int i = ipV4Subnet.subnet;
        int i2 = this.subnet;
        if (i < i2) {
            return 1;
        }
        return (i <= i2 && ipV4Subnet.cidrMask >= this.cidrMask) ? 1 : -1;
    }
}
