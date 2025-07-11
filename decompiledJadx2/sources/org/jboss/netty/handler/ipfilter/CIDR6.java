package org.jboss.netty.handler.ipfilter;

import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

/* loaded from: classes7.dex */
public class CIDR6 extends CIDR {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) CIDR6.class);
    private BigInteger addressBigInt;
    private final BigInteger addressEndBigInt;

    /* JADX INFO: Access modifiers changed from: protected */
    public CIDR6(Inet6Address inet6Address, int i) {
        this.cidrMask = i;
        this.addressBigInt = ipv6AddressToBigInteger(inet6Address);
        try {
            this.addressBigInt = this.addressBigInt.and(ipv6CidrMaskToMask(i));
            this.baseAddress = bigIntToIPv6Address(this.addressBigInt);
        } catch (UnknownHostException unused) {
        }
        this.addressEndBigInt = this.addressBigInt.add(ipv6CidrMaskToBaseAddress(this.cidrMask)).subtract(BigInteger.ONE);
    }

    @Override // org.jboss.netty.handler.ipfilter.CIDR
    public InetAddress getEndAddress() {
        try {
            return bigIntToIPv6Address(this.addressEndBigInt);
        } catch (UnknownHostException unused) {
            if (!logger.isErrorEnabled()) {
                return null;
            }
            logger.error("invalid ip address calculated as an end address");
            return null;
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(CIDR cidr) {
        if (cidr instanceof CIDR4) {
            int compareTo = ipv6AddressToBigInteger(cidr.baseAddress).compareTo(this.addressBigInt);
            if (compareTo != 0) {
                return compareTo;
            }
            if (cidr.cidrMask == this.cidrMask) {
                return 0;
            }
            return cidr.cidrMask < this.cidrMask ? -1 : 1;
        }
        CIDR6 cidr6 = (CIDR6) cidr;
        if (cidr6.addressBigInt.equals(this.addressBigInt) && cidr6.cidrMask == this.cidrMask) {
            return 0;
        }
        int compareTo2 = cidr6.addressBigInt.compareTo(this.addressBigInt);
        return compareTo2 == 0 ? cidr6.cidrMask < this.cidrMask ? -1 : 1 : compareTo2;
    }

    @Override // org.jboss.netty.handler.ipfilter.CIDR
    public boolean contains(InetAddress inetAddress) {
        BigInteger ipv6AddressToBigInteger = ipv6AddressToBigInteger(inetAddress);
        return ipv6AddressToBigInteger.compareTo(this.addressBigInt) >= 0 && ipv6AddressToBigInteger.compareTo(this.addressEndBigInt) <= 0;
    }

    private static BigInteger ipv6CidrMaskToBaseAddress(int i) {
        return BigInteger.ONE.shiftLeft(128 - i);
    }

    private static BigInteger ipv6CidrMaskToMask(int i) {
        return BigInteger.ONE.shiftLeft(128 - i).subtract(BigInteger.ONE).not();
    }

    private static BigInteger ipv6AddressToBigInteger(InetAddress inetAddress) {
        byte[] address;
        if (inetAddress instanceof Inet4Address) {
            address = getIpV6FromIpV4((Inet4Address) inetAddress);
        } else {
            address = inetAddress.getAddress();
        }
        if (address[0] == -1) {
            return new BigInteger(1, address);
        }
        return new BigInteger(address);
    }

    private static InetAddress bigIntToIPv6Address(BigInteger bigInteger) throws UnknownHostException {
        byte[] bArr = new byte[16];
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length > 16 && (byteArray.length != 17 || byteArray[0] != 0)) {
            throw new UnknownHostException("invalid IPv6 address (too big)");
        }
        if (byteArray.length == 16) {
            return InetAddress.getByAddress(byteArray);
        }
        if (byteArray.length == 17) {
            System.arraycopy(byteArray, 1, bArr, 0, 16);
        } else {
            int length = 16 - byteArray.length;
            for (int i = 0; i < byteArray.length; i++) {
                bArr[length + i] = byteArray[i];
            }
        }
        return InetAddress.getByAddress(bArr);
    }
}
