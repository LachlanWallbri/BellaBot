package io.grpc.netty.shaded.io.netty.util.internal;

import io.grpc.netty.shaded.io.netty.util.NetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class MacAddressUtil {
    private static final int EUI48_MAC_ADDRESS_LENGTH = 6;
    private static final int EUI64_MAC_ADDRESS_LENGTH = 8;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) MacAddressUtil.class);

    public static byte[] bestAvailableMac() {
        int compareAddresses;
        byte[] bArr = EmptyArrays.EMPTY_BYTES;
        InetAddress inetAddress = NetUtil.LOCALHOST4;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface nextElement = networkInterfaces.nextElement();
                    Enumeration<InetAddress> addressesFromNetworkInterface = SocketUtils.addressesFromNetworkInterface(nextElement);
                    if (addressesFromNetworkInterface.hasMoreElements()) {
                        InetAddress nextElement2 = addressesFromNetworkInterface.nextElement();
                        if (!nextElement2.isLoopbackAddress()) {
                            linkedHashMap.put(nextElement, nextElement2);
                        }
                    }
                }
            }
        } catch (SocketException e) {
            logger.warn("Failed to retrieve the list of available network interfaces", (Throwable) e);
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            NetworkInterface networkInterface = (NetworkInterface) entry.getKey();
            InetAddress inetAddress2 = (InetAddress) entry.getValue();
            if (!networkInterface.isVirtual()) {
                try {
                    byte[] hardwareAddressFromNetworkInterface = SocketUtils.hardwareAddressFromNetworkInterface(networkInterface);
                    int compareAddresses2 = compareAddresses(bArr, hardwareAddressFromNetworkInterface);
                    boolean z = true;
                    if (compareAddresses2 >= 0 && (compareAddresses2 != 0 || ((compareAddresses = compareAddresses(inetAddress, inetAddress2)) >= 0 && (compareAddresses != 0 || bArr.length >= hardwareAddressFromNetworkInterface.length)))) {
                        z = false;
                    }
                    if (z) {
                        inetAddress = inetAddress2;
                        bArr = hardwareAddressFromNetworkInterface;
                    }
                } catch (SocketException e2) {
                    logger.debug("Failed to get the hardware address of a network interface: {}", networkInterface, e2);
                }
            }
        }
        if (bArr == EmptyArrays.EMPTY_BYTES) {
            return null;
        }
        if (bArr.length == 6) {
            byte[] bArr2 = new byte[8];
            System.arraycopy(bArr, 0, bArr2, 0, 3);
            bArr2[3] = -1;
            bArr2[4] = -2;
            System.arraycopy(bArr, 3, bArr2, 5, 3);
            return bArr2;
        }
        return Arrays.copyOf(bArr, 8);
    }

    public static byte[] defaultMachineId() {
        byte[] bestAvailableMac = bestAvailableMac();
        if (bestAvailableMac != null) {
            return bestAvailableMac;
        }
        byte[] bArr = new byte[8];
        PlatformDependent.threadLocalRandom().nextBytes(bArr);
        logger.warn("Failed to find a usable hardware address from the network interfaces; using random bytes: {}", formatAddress(bArr));
        return bArr;
    }

    public static byte[] parseMAC(String str) {
        char charAt;
        byte[] bArr;
        int length = str.length();
        if (length == 17) {
            charAt = str.charAt(2);
            validateMacSeparator(charAt);
            bArr = new byte[6];
        } else if (length == 23) {
            charAt = str.charAt(2);
            validateMacSeparator(charAt);
            bArr = new byte[8];
        } else {
            throw new IllegalArgumentException("value is not supported [MAC-48, EUI-48, EUI-64]");
        }
        int length2 = bArr.length - 1;
        int i = 0;
        int i2 = 0;
        while (i < length2) {
            int i3 = i2 + 2;
            bArr[i] = StringUtil.decodeHexByte(str, i2);
            if (str.charAt(i3) != charAt) {
                throw new IllegalArgumentException("expected separator '" + charAt + " but got '" + str.charAt(i3) + "' at index: " + i3);
            }
            i++;
            i2 += 3;
        }
        bArr[length2] = StringUtil.decodeHexByte(str, i2);
        return bArr;
    }

    private static void validateMacSeparator(char c) {
        if (c == ':' || c == '-') {
            return;
        }
        throw new IllegalArgumentException("unsupported separator: " + c + " (expected: [:-])");
    }

    public static String formatAddress(byte[] bArr) {
        StringBuilder sb = new StringBuilder(24);
        for (byte b : bArr) {
            sb.append(String.format("%02x:", Integer.valueOf(b & 255)));
        }
        return sb.substring(0, sb.length() - 1);
    }

    static int compareAddresses(byte[] bArr, byte[] bArr2) {
        boolean z;
        if (bArr2 == null || bArr2.length < 6) {
            return 1;
        }
        int length = bArr2.length;
        int i = 0;
        while (true) {
            if (i < length) {
                byte b = bArr2[i];
                if (b != 0 && b != 1) {
                    z = false;
                    break;
                }
                i++;
            } else {
                z = true;
                break;
            }
        }
        if (!z && (bArr2[0] & 1) == 0) {
            return (bArr2[0] & 2) == 0 ? (bArr.length == 0 || (bArr[0] & 2) != 0) ? -1 : 0 : (bArr.length == 0 || (bArr[0] & 2) != 0) ? 0 : 1;
        }
        return 1;
    }

    private static int compareAddresses(InetAddress inetAddress, InetAddress inetAddress2) {
        return scoreAddress(inetAddress) - scoreAddress(inetAddress2);
    }

    private static int scoreAddress(InetAddress inetAddress) {
        if (inetAddress.isAnyLocalAddress() || inetAddress.isLoopbackAddress()) {
            return 0;
        }
        if (inetAddress.isMulticastAddress()) {
            return 1;
        }
        if (inetAddress.isLinkLocalAddress()) {
            return 2;
        }
        return inetAddress.isSiteLocalAddress() ? 3 : 4;
    }

    private MacAddressUtil() {
    }
}
