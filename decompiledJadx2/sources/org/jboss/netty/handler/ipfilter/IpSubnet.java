package org.jboss.netty.handler.ipfilter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

/* loaded from: classes7.dex */
public class IpSubnet implements IpSet, Comparable<IpSubnet> {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) IpSubnet.class);
    private final CIDR cidr;

    public IpSubnet() {
        this.cidr = null;
    }

    public IpSubnet(String str) throws UnknownHostException {
        this.cidr = CIDR.newCIDR(str);
    }

    public IpSubnet(InetAddress inetAddress, int i) throws UnknownHostException {
        this.cidr = CIDR.newCIDR(inetAddress, i);
    }

    public IpSubnet(InetAddress inetAddress, String str) throws UnknownHostException {
        this.cidr = CIDR.newCIDR(inetAddress, str);
    }

    public boolean contains(String str) throws UnknownHostException {
        return contains(InetAddress.getByName(str));
    }

    @Override // org.jboss.netty.handler.ipfilter.IpSet
    public boolean contains(InetAddress inetAddress) {
        CIDR cidr = this.cidr;
        if (cidr == null) {
            return true;
        }
        return cidr.contains(inetAddress);
    }

    public String toString() {
        return this.cidr.toString();
    }

    public boolean equals(Object obj) {
        if (obj instanceof IpSubnet) {
            return ((IpSubnet) obj).cidr.equals(this.cidr);
        }
        return false;
    }

    public int hashCode() {
        return this.cidr.hashCode();
    }

    @Override // java.lang.Comparable
    public int compareTo(IpSubnet ipSubnet) {
        return this.cidr.toString().compareTo(ipSubnet.cidr.toString());
    }
}
