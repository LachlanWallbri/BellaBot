package io.grpc.netty.shaded.io.netty.util;

import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.SocketUtils;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class NetUtil {
    private static final int IPV4_MAX_CHAR_BETWEEN_SEPARATOR = 3;
    private static final int IPV4_SEPARATORS = 3;
    private static final int IPV6_BYTE_COUNT = 16;
    private static final int IPV6_MAX_CHAR_BETWEEN_SEPARATOR = 4;
    private static final int IPV6_MAX_CHAR_COUNT = 39;
    private static final int IPV6_MAX_SEPARATORS = 8;
    private static final int IPV6_MIN_SEPARATORS = 2;
    private static final int IPV6_WORD_COUNT = 8;
    public static final InetAddress LOCALHOST;
    public static final Inet4Address LOCALHOST4;
    public static final Inet6Address LOCALHOST6;
    public static final NetworkInterface LOOPBACK_IF;
    public static final int SOMAXCONN;
    private static final boolean IPV4_PREFERRED = SystemPropertyUtil.getBoolean("java.net.preferIPv4Stack", false);
    private static final boolean IPV6_ADDRESSES_PREFERRED = SystemPropertyUtil.getBoolean("java.net.preferIPv6Addresses", false);
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) NetUtil.class);

    private static boolean inRangeEndExclusive(int i, int i2, int i3) {
        return i >= i2 && i < i3;
    }

    private static boolean isValidHexChar(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }

    private static boolean isValidIPv4MappedChar(char c) {
        return c == 'f' || c == 'F';
    }

    private static boolean isValidIPv4MappedSeparators(byte b, byte b2, boolean z) {
        return b == b2 && (b == 0 || (!z && b2 == -1));
    }

    private static boolean isValidNumericChar(char c) {
        return c >= '0' && c <= '9';
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0129, code lost:
    
        if (r1 == null) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0137, code lost:
    
        io.grpc.netty.shaded.io.netty.util.NetUtil.logger.debug("Using hard-coded IPv4 localhost address: {}", r4);
        r1 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0135, code lost:
    
        if (r10 == null) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00e1, code lost:
    
        r10 = r8.nextElement();
        r6 = r7;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0116  */
    /* JADX WARN: Type inference failed for: r0v5, types: [io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger] */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6, types: [java.net.InetAddress] */
    /* JADX WARN: Type inference failed for: r10v9, types: [java.net.InetAddress] */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.net.Inet6Address, java.lang.Object] */
    static {
        Inet4Address inet4Address;
        ?? r1;
        InetAddress inetAddress;
        Inet4Address inet4Address2;
        logger.debug("-Djava.net.preferIPv4Stack: {}", Boolean.valueOf(IPV4_PREFERRED));
        logger.debug("-Djava.net.preferIPv6Addresses: {}", Boolean.valueOf(IPV6_ADDRESSES_PREFERRED));
        byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        NetworkInterface networkInterface = null;
        try {
            inet4Address = (Inet4Address) InetAddress.getByAddress("localhost", new byte[]{Byte.MAX_VALUE, 0, 0, 1});
        } catch (Exception e) {
            PlatformDependent.throwException(e);
            inet4Address = null;
        }
        LOCALHOST4 = inet4Address;
        try {
            r1 = (Inet6Address) InetAddress.getByAddress("localhost", bArr);
        } catch (Exception e2) {
            PlatformDependent.throwException(e2);
            r1 = 0;
        }
        LOCALHOST6 = r1;
        ArrayList arrayList = new ArrayList();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface nextElement = networkInterfaces.nextElement();
                    if (SocketUtils.addressesFromNetworkInterface(nextElement).hasMoreElements()) {
                        arrayList.add(nextElement);
                    }
                }
            }
        } catch (SocketException e3) {
            logger.warn("Failed to retrieve the list of available network interfaces", (Throwable) e3);
        }
        Iterator it = arrayList.iterator();
        loop1: while (true) {
            if (!it.hasNext()) {
                inetAddress = null;
                break;
            }
            NetworkInterface networkInterface2 = (NetworkInterface) it.next();
            Enumeration<InetAddress> addressesFromNetworkInterface = SocketUtils.addressesFromNetworkInterface(networkInterface2);
            while (addressesFromNetworkInterface.hasMoreElements()) {
                inetAddress = addressesFromNetworkInterface.nextElement();
                if (inetAddress.isLoopbackAddress()) {
                    networkInterface = networkInterface2;
                    break loop1;
                }
            }
        }
        if (networkInterface == null) {
            try {
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    NetworkInterface networkInterface3 = (NetworkInterface) it2.next();
                    if (networkInterface3.isLoopback()) {
                        Enumeration<InetAddress> addressesFromNetworkInterface2 = SocketUtils.addressesFromNetworkInterface(networkInterface3);
                        if (addressesFromNetworkInterface2.hasMoreElements()) {
                            try {
                                break;
                            } catch (SocketException e4) {
                                e = e4;
                                networkInterface = networkInterface3;
                                logger.warn("Failed to find the loopback interface", (Throwable) e);
                                if (networkInterface == null) {
                                }
                                inet4Address2 = inetAddress;
                                LOOPBACK_IF = networkInterface;
                                LOCALHOST = inet4Address2;
                                SOMAXCONN = ((Integer) AccessController.doPrivileged(new PrivilegedAction<Integer>() { // from class: io.grpc.netty.shaded.io.netty.util.NetUtil.1
                                    /* JADX WARN: Can't rename method to resolve collision */
                                    /* JADX WARN: Code restructure failed: missing block: B:15:0x0080, code lost:
                                    
                                        if (r4 != null) goto L47;
                                     */
                                    /* JADX WARN: Code restructure failed: missing block: B:17:0x00b0, code lost:
                                    
                                        return java.lang.Integer.valueOf(r1);
                                     */
                                    /* JADX WARN: Code restructure failed: missing block: B:20:0x0082, code lost:
                                    
                                        r4.close();
                                     */
                                    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a9, code lost:
                                    
                                        if (r4 != null) goto L47;
                                     */
                                    @Override // java.security.PrivilegedAction
                                    /*
                                        Code decompiled incorrectly, please refer to instructions dump.
                                    */
                                    public Integer run() {
                                        Integer num;
                                        int i = PlatformDependent.isWindows() ? 200 : 128;
                                        File file = new File("/proc/sys/net/core/somaxconn");
                                        BufferedReader bufferedReader = null;
                                        try {
                                            try {
                                                if (file.exists()) {
                                                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                                                    try {
                                                        i = Integer.parseInt(bufferedReader2.readLine());
                                                        if (NetUtil.logger.isDebugEnabled()) {
                                                            NetUtil.logger.debug("{}: {}", file, Integer.valueOf(i));
                                                        }
                                                        bufferedReader = bufferedReader2;
                                                    } catch (Exception e5) {
                                                        e = e5;
                                                        bufferedReader = bufferedReader2;
                                                        if (NetUtil.logger.isDebugEnabled()) {
                                                            NetUtil.logger.debug("Failed to get SOMAXCONN from sysctl and file {}. Default: {}", file, Integer.valueOf(i), e);
                                                        }
                                                    } catch (Throwable th) {
                                                        th = th;
                                                        bufferedReader = bufferedReader2;
                                                        if (bufferedReader != null) {
                                                            try {
                                                                bufferedReader.close();
                                                            } catch (Exception unused) {
                                                            }
                                                        }
                                                        throw th;
                                                    }
                                                } else {
                                                    if (SystemPropertyUtil.getBoolean("io.grpc.netty.shaded.io.netty.net.somaxconn.trySysctl", false)) {
                                                        num = NetUtil.sysctlGetInt("kern.ipc.somaxconn");
                                                        if (num == null) {
                                                            num = NetUtil.sysctlGetInt("kern.ipc.soacceptqueue");
                                                            if (num != null) {
                                                                i = num.intValue();
                                                            }
                                                        } else {
                                                            i = num.intValue();
                                                        }
                                                    } else {
                                                        num = null;
                                                    }
                                                    if (num == null) {
                                                        NetUtil.logger.debug("Failed to get SOMAXCONN from sysctl and file {}. Default: {}", file, Integer.valueOf(i));
                                                    }
                                                }
                                            } catch (Exception e6) {
                                                e = e6;
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                        }
                                    }
                                })).intValue();
                            }
                        }
                    }
                }
                if (networkInterface == null) {
                    logger.warn("Failed to find the loopback interface");
                }
            } catch (SocketException e5) {
                e = e5;
            }
        }
        if (networkInterface == null) {
            logger.debug("Loopback interface: {} ({}, {})", networkInterface.getName(), networkInterface.getDisplayName(), inetAddress.getHostAddress());
        } else if (inetAddress == null) {
            try {
                if (NetworkInterface.getByInetAddress(LOCALHOST6) != null) {
                    logger.debug("Using hard-coded IPv6 localhost address: {}", r1);
                    inet4Address2 = r1;
                } else {
                    inet4Address2 = inetAddress;
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                if (inetAddress == null) {
                    logger.debug("Using hard-coded IPv4 localhost address: {}", inet4Address);
                }
                throw th;
            }
        }
        inet4Address2 = inetAddress;
        LOOPBACK_IF = networkInterface;
        LOCALHOST = inet4Address2;
        SOMAXCONN = ((Integer) AccessController.doPrivileged(new PrivilegedAction<Integer>() { // from class: io.grpc.netty.shaded.io.netty.util.NetUtil.1
            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Code restructure failed: missing block: B:15:0x0080, code lost:
            
                if (r4 != null) goto L47;
             */
            /* JADX WARN: Code restructure failed: missing block: B:17:0x00b0, code lost:
            
                return java.lang.Integer.valueOf(r1);
             */
            /* JADX WARN: Code restructure failed: missing block: B:20:0x0082, code lost:
            
                r4.close();
             */
            /* JADX WARN: Code restructure failed: missing block: B:28:0x00a9, code lost:
            
                if (r4 != null) goto L47;
             */
            @Override // java.security.PrivilegedAction
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Integer run() {
                Integer num;
                int i = PlatformDependent.isWindows() ? 200 : 128;
                File file = new File("/proc/sys/net/core/somaxconn");
                BufferedReader bufferedReader = null;
                try {
                    try {
                        if (file.exists()) {
                            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                            try {
                                i = Integer.parseInt(bufferedReader2.readLine());
                                if (NetUtil.logger.isDebugEnabled()) {
                                    NetUtil.logger.debug("{}: {}", file, Integer.valueOf(i));
                                }
                                bufferedReader = bufferedReader2;
                            } catch (Exception e52) {
                                e = e52;
                                bufferedReader = bufferedReader2;
                                if (NetUtil.logger.isDebugEnabled()) {
                                    NetUtil.logger.debug("Failed to get SOMAXCONN from sysctl and file {}. Default: {}", file, Integer.valueOf(i), e);
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedReader = bufferedReader2;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Exception unused2) {
                                    }
                                }
                                throw th;
                            }
                        } else {
                            if (SystemPropertyUtil.getBoolean("io.grpc.netty.shaded.io.netty.net.somaxconn.trySysctl", false)) {
                                num = NetUtil.sysctlGetInt("kern.ipc.somaxconn");
                                if (num == null) {
                                    num = NetUtil.sysctlGetInt("kern.ipc.soacceptqueue");
                                    if (num != null) {
                                        i = num.intValue();
                                    }
                                } else {
                                    i = num.intValue();
                                }
                            } else {
                                num = null;
                            }
                            if (num == null) {
                                NetUtil.logger.debug("Failed to get SOMAXCONN from sysctl and file {}. Default: {}", file, Integer.valueOf(i));
                            }
                        }
                    } catch (Exception e6) {
                        e = e6;
                    }
                } catch (Throwable th22) {
                    th = th22;
                }
            }
        })).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer sysctlGetInt(String str) throws IOException {
        Process start = new ProcessBuilder("sysctl", str).start();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream()));
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null && readLine.startsWith(str)) {
                    for (int length = readLine.length() - 1; length > str.length(); length--) {
                        if (!Character.isDigit(readLine.charAt(length))) {
                            return Integer.valueOf(readLine.substring(length + 1));
                        }
                    }
                }
                if (start != null) {
                    start.destroy();
                }
                return null;
            } finally {
                bufferedReader.close();
            }
        } finally {
            if (start != null) {
                start.destroy();
            }
        }
    }

    public static boolean isIpV4StackPreferred() {
        return IPV4_PREFERRED;
    }

    public static boolean isIpV6AddressesPreferred() {
        return IPV6_ADDRESSES_PREFERRED;
    }

    public static byte[] createByteArrayFromIpAddressString(String str) {
        if (isValidIpV4Address(str)) {
            return validIpV4ToBytes(str);
        }
        if (!isValidIpV6Address(str)) {
            return null;
        }
        if (str.charAt(0) == '[') {
            str = str.substring(1, str.length() - 1);
        }
        int indexOf = str.indexOf(37);
        if (indexOf >= 0) {
            str = str.substring(0, indexOf);
        }
        return getIPv6ByName(str, true);
    }

    private static int decimalDigit(String str, int i) {
        return str.charAt(i) - '0';
    }

    private static byte ipv4WordToByte(String str, int i, int i2) {
        int decimalDigit = decimalDigit(str, i);
        int i3 = i + 1;
        if (i3 == i2) {
            return (byte) decimalDigit;
        }
        int decimalDigit2 = (decimalDigit * 10) + decimalDigit(str, i3);
        int i4 = i3 + 1;
        return i4 == i2 ? (byte) decimalDigit2 : (byte) ((decimalDigit2 * 10) + decimalDigit(str, i4));
    }

    static byte[] validIpV4ToBytes(String str) {
        int indexOf = str.indexOf(46, 1);
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(46, indexOf + 2);
        int indexOf3 = str.indexOf(46, indexOf2 + 2);
        return new byte[]{ipv4WordToByte(str, 0, indexOf), ipv4WordToByte(str, i, indexOf2), ipv4WordToByte(str, indexOf2 + 1, indexOf3), ipv4WordToByte(str, indexOf3 + 1, str.length())};
    }

    public static int ipv4AddressToInt(Inet4Address inet4Address) {
        byte[] address = inet4Address.getAddress();
        return (address[3] & 255) | ((address[0] & 255) << 24) | ((address[1] & 255) << 16) | ((address[2] & 255) << 8);
    }

    public static String intToIpAddress(int i) {
        StringBuilder sb = new StringBuilder(15);
        sb.append((i >> 24) & 255);
        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        sb.append((i >> 16) & 255);
        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        sb.append((i >> 8) & 255);
        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        sb.append(i & 255);
        return sb.toString();
    }

    public static String bytesToIpAddress(byte[] bArr) {
        return bytesToIpAddress(bArr, 0, bArr.length);
    }

    public static String bytesToIpAddress(byte[] bArr, int i, int i2) {
        if (i2 != 4) {
            if (i2 == 16) {
                return toAddressString(bArr, i, false);
            }
            throw new IllegalArgumentException("length: " + i2 + " (expected: 4 or 16)");
        }
        StringBuilder sb = new StringBuilder(15);
        sb.append(bArr[i] & 255);
        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        sb.append(bArr[i + 1] & 255);
        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        sb.append(bArr[i + 2] & 255);
        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        sb.append(bArr[i + 3] & 255);
        return sb.toString();
    }

    public static boolean isValidIpV6Address(String str) {
        return isValidIpV6Address((CharSequence) str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:83:0x00d6, code lost:
    
        if (r7 >= 0) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x00d8, code lost:
    
        if (r8 != 7) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x00da, code lost:
    
        if (r3 <= 0) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00dd, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x00e0, code lost:
    
        if ((r7 + 2) == r1) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x00e2, code lost:
    
        if (r3 <= 0) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x00e6, code lost:
    
        if (r8 < 8) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x00e8, code lost:
    
        if (r7 > r0) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x00eb, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean isValidIpV6Address(CharSequence charSequence) {
        int i;
        int i2;
        int i3;
        int i4;
        int length = charSequence.length();
        if (length >= 2) {
            char charAt = charSequence.charAt(0);
            if (charAt == '[') {
                int i5 = length - 1;
                if (charSequence.charAt(i5) != ']') {
                    return false;
                }
                charAt = charSequence.charAt(1);
                i = i5;
                i2 = 1;
            } else {
                i = length;
                i2 = 0;
            }
            if (charAt != ':') {
                i3 = -1;
                i4 = 0;
            } else {
                if (charSequence.charAt(i2 + 1) != ':') {
                    return false;
                }
                i3 = i2;
                i2 += 2;
                i4 = 2;
            }
            int i6 = i3;
            int i7 = i4;
            int i8 = i2;
            int i9 = 0;
            while (true) {
                if (i8 >= i) {
                    i8 = i;
                    break;
                }
                char charAt2 = charSequence.charAt(i8);
                if (!isValidHexChar(charAt2)) {
                    if (charAt2 == '%') {
                        break;
                    }
                    if (charAt2 == '.') {
                        if ((i6 < 0 && i7 != 6) || ((i7 == 7 && i6 >= i2) || i7 > 7)) {
                            return false;
                        }
                        int i10 = i8 - i9;
                        int i11 = i10 - 2;
                        if (isValidIPv4MappedChar(charSequence.charAt(i11))) {
                            if (!isValidIPv4MappedChar(charSequence.charAt(i11 - 1)) || !isValidIPv4MappedChar(charSequence.charAt(i11 - 2)) || !isValidIPv4MappedChar(charSequence.charAt(i11 - 3))) {
                                return false;
                            }
                            i11 -= 5;
                        }
                        while (i11 >= i2) {
                            char charAt3 = charSequence.charAt(i11);
                            if (charAt3 != '0' && charAt3 != ':') {
                                return false;
                            }
                            i11--;
                        }
                        int indexOf = AsciiString.indexOf(charSequence, '%', i10 + 7);
                        if (indexOf < 0) {
                            indexOf = i;
                        }
                        return isValidIpV4Address(charSequence, i10, indexOf);
                    }
                    if (charAt2 != ':' || i7 > 7) {
                        return false;
                    }
                    int i12 = i8 - 1;
                    if (charSequence.charAt(i12) != ':') {
                        i9 = 0;
                    } else {
                        if (i6 >= 0) {
                            return false;
                        }
                        i6 = i12;
                    }
                    i7++;
                } else {
                    if (i9 >= 4) {
                        return false;
                    }
                    i9++;
                }
                i8++;
            }
        } else {
            return false;
        }
    }

    private static boolean isValidIpV4Word(CharSequence charSequence, int i, int i2) {
        char charAt;
        char charAt2;
        int i3 = i2 - i;
        if (i3 < 1 || i3 > 3 || (charAt = charSequence.charAt(i)) < '0') {
            return false;
        }
        if (i3 != 3) {
            if (charAt <= '9') {
                return i3 == 1 || isValidNumericChar(charSequence.charAt(i + 1));
            }
            return false;
        }
        char charAt3 = charSequence.charAt(i + 1);
        if (charAt3 < '0' || (charAt2 = charSequence.charAt(i + 2)) < '0') {
            return false;
        }
        if (charAt > '1' || charAt3 > '9' || charAt2 > '9') {
            if (charAt != '2' || charAt3 > '5') {
                return false;
            }
            if (charAt2 > '5' && (charAt3 >= '5' || charAt2 > '9')) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidIPv4Mapped(byte[] bArr, int i, int i2, int i3) {
        boolean z = i3 + i2 >= 14;
        return i <= 12 && i >= 2 && (!z || i2 < 12) && isValidIPv4MappedSeparators(bArr[i + (-1)], bArr[i + (-2)], z) && PlatformDependent.isZero(bArr, 0, i + (-3));
    }

    public static boolean isValidIpV4Address(CharSequence charSequence) {
        return isValidIpV4Address(charSequence, 0, charSequence.length());
    }

    public static boolean isValidIpV4Address(String str) {
        return isValidIpV4Address(str, 0, str.length());
    }

    private static boolean isValidIpV4Address(CharSequence charSequence, int i, int i2) {
        if (charSequence instanceof String) {
            return isValidIpV4Address((String) charSequence, i, i2);
        }
        if (charSequence instanceof AsciiString) {
            return isValidIpV4Address((AsciiString) charSequence, i, i2);
        }
        return isValidIpV4Address0(charSequence, i, i2);
    }

    private static boolean isValidIpV4Address(String str, int i, int i2) {
        int indexOf;
        int i3;
        int indexOf2;
        int i4;
        int indexOf3;
        int i5 = i2 - i;
        return i5 <= 15 && i5 >= 7 && (indexOf = str.indexOf(46, i + 1)) > 0 && isValidIpV4Word(str, i, indexOf) && (indexOf2 = str.indexOf(46, (i3 = indexOf + 2))) > 0 && isValidIpV4Word(str, i3 - 1, indexOf2) && (indexOf3 = str.indexOf(46, (i4 = indexOf2 + 2))) > 0 && isValidIpV4Word(str, i4 - 1, indexOf3) && isValidIpV4Word(str, indexOf3 + 1, i2);
    }

    private static boolean isValidIpV4Address(AsciiString asciiString, int i, int i2) {
        int indexOf;
        int i3;
        int indexOf2;
        int i4;
        int indexOf3;
        int i5 = i2 - i;
        return i5 <= 15 && i5 >= 7 && (indexOf = asciiString.indexOf(FilenameUtils.EXTENSION_SEPARATOR, i + 1)) > 0 && isValidIpV4Word(asciiString, i, indexOf) && (indexOf2 = asciiString.indexOf(FilenameUtils.EXTENSION_SEPARATOR, (i3 = indexOf + 2))) > 0 && isValidIpV4Word(asciiString, i3 - 1, indexOf2) && (indexOf3 = asciiString.indexOf(FilenameUtils.EXTENSION_SEPARATOR, (i4 = indexOf2 + 2))) > 0 && isValidIpV4Word(asciiString, i4 - 1, indexOf3) && isValidIpV4Word(asciiString, indexOf3 + 1, i2);
    }

    private static boolean isValidIpV4Address0(CharSequence charSequence, int i, int i2) {
        int indexOf;
        int i3;
        int indexOf2;
        int i4;
        int indexOf3;
        int i5 = i2 - i;
        return i5 <= 15 && i5 >= 7 && (indexOf = AsciiString.indexOf(charSequence, FilenameUtils.EXTENSION_SEPARATOR, i + 1)) > 0 && isValidIpV4Word(charSequence, i, indexOf) && (indexOf2 = AsciiString.indexOf(charSequence, FilenameUtils.EXTENSION_SEPARATOR, (i3 = indexOf + 2))) > 0 && isValidIpV4Word(charSequence, i3 - 1, indexOf2) && (indexOf3 = AsciiString.indexOf(charSequence, FilenameUtils.EXTENSION_SEPARATOR, (i4 = indexOf2 + 2))) > 0 && isValidIpV4Word(charSequence, i4 - 1, indexOf3) && isValidIpV4Word(charSequence, indexOf3 + 1, i2);
    }

    public static Inet6Address getByName(CharSequence charSequence) {
        return getByName(charSequence, true);
    }

    public static Inet6Address getByName(CharSequence charSequence, boolean z) {
        byte[] iPv6ByName = getIPv6ByName(charSequence, z);
        if (iPv6ByName == null) {
            return null;
        }
        try {
            return Inet6Address.getByAddress((String) null, iPv6ByName, -1);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:123:0x0161, code lost:
    
        if ((r5 - r8) <= 3) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x017e, code lost:
    
        if (r17.charAt(0) == ':') goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x0191, code lost:
    
        if (r6 <= 2) goto L127;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static byte[] getIPv6ByName(CharSequence charSequence, boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        byte[] bArr = new byte[16];
        int length = charSequence.length();
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = -1;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        boolean z2 = false;
        while (i6 < length) {
            char charAt = charSequence.charAt(i6);
            if (charAt == '.') {
                i8++;
                int i14 = i6 - i9;
                if (i14 <= 3 && i9 >= 0 && i8 <= 3 && ((i10 <= 0 || i11 + i13 >= 12) && i6 + 1 < length && i11 < bArr.length && (i8 != 1 || (z && ((i11 == 0 || isValidIPv4Mapped(bArr, i11, i7, i13)) && ((i14 != 3 || (isValidNumericChar(charSequence.charAt(i6 - 1)) && isValidNumericChar(charSequence.charAt(i6 - 2)) && isValidNumericChar(charSequence.charAt(i6 - 3)))) && ((i14 != 2 || (isValidNumericChar(charSequence.charAt(i6 - 1)) && isValidNumericChar(charSequence.charAt(i6 - 2)))) && (i14 != 1 || isValidNumericChar(charSequence.charAt(i6 - 1)))))))))) {
                    int i15 = i12 << ((3 - i14) << 2);
                    int i16 = ((i15 & 15) * 100) + (((i15 >> 4) & 15) * 10) + ((i15 >> 8) & 15);
                    if (i16 >= 0 && i16 <= 255) {
                        bArr[i11] = (byte) i16;
                        i11++;
                    }
                }
                return null;
            }
            if (charAt == ':') {
                i10++;
                int i17 = i6 - i9;
                if (i17 > 4 || i8 > 0 || i10 > 8 || (i5 = i11 + 1) >= bArr.length) {
                    return null;
                }
                int i18 = i12 << ((4 - i17) << 2);
                if (i13 > 0) {
                    i13 -= 2;
                }
                bArr[i11] = (byte) (((i18 & 15) << 4) | ((i18 >> 4) & 15));
                i11 = i5 + 1;
                bArr[i5] = (byte) ((((i18 >> 8) & 15) << 4) | ((i18 >> 12) & 15));
                int i19 = i6 + 1;
                if (i19 < length && charSequence.charAt(i19) == ':') {
                    int i20 = i19 + 1;
                    if (i7 != 0 || (i20 < length && charSequence.charAt(i20) == ':')) {
                        return null;
                    }
                    i10++;
                    z2 = i10 == 2 && i18 == 0;
                    i13 = (bArr.length - i11) - 2;
                    i6 = i19;
                    i7 = i11;
                }
            } else {
                if (!isValidHexChar(charAt) || (i8 > 0 && !isValidNumericChar(charAt))) {
                    return null;
                }
                if (i9 < 0) {
                    i9 = i6;
                } else if (i6 - i9 > 4) {
                    return null;
                }
                i12 += StringUtil.decodeHexNibble(charAt) << ((i6 - i9) << 2);
                i4 = 1;
                i6 += i4;
            }
            i4 = 1;
            i9 = -1;
            i12 = 0;
            i6 += i4;
        }
        boolean z3 = i7 > 0;
        if (i8 > 0) {
            int i21 = i9 > 0 ? 3 : 3;
            if (i8 == i21 && i11 < bArr.length) {
                if (i10 == 0) {
                    i13 = 12;
                } else if (i10 >= 2) {
                    int i22 = (z3 || i10 != 6) ? 0 : 0;
                    if (z3 && i10 < 8) {
                        if (charSequence.charAt(i22) == ':') {
                        }
                        i13 -= 2;
                    }
                }
                int i23 = i12 << ((3 - (i6 - i9)) << 2);
                int i24 = ((i23 & 15) * 100) + (((i23 >> 4) & 15) * 10) + ((i23 >> 8) & 15);
                if (i24 >= 0 && i24 <= 255) {
                    i3 = i11 + 1;
                    bArr[i11] = (byte) i24;
                }
            }
            return null;
        }
        int i25 = length - 1;
        if ((i9 > 0 && i6 - i9 > 4) || i10 < 2 || ((!z3 && (i10 + 1 != 8 || charSequence.charAt(0) == ':' || charSequence.charAt(i25) == ':')) || ((z3 && (i10 > 8 || (i10 == 8 && ((i7 <= 2 && charSequence.charAt(0) != ':') || (i7 >= 14 && charSequence.charAt(i25) != ':'))))) || (i = i11 + 1) >= bArr.length || ((i9 < 0 && charSequence.charAt(i25 - 1) != ':') || (i7 > 2 && charSequence.charAt(0) == ':'))))) {
            return null;
        }
        if (i9 >= 0) {
            int i26 = i6 - i9;
            i2 = 4;
            if (i26 <= 4) {
                i12 <<= (4 - i26) << 2;
            }
        } else {
            i2 = 4;
        }
        bArr[i11] = (byte) (((i12 >> 4) & 15) | ((i12 & 15) << i2));
        i3 = i + 1;
        bArr[i] = (byte) ((((i12 >> 8) & 15) << 4) | ((i12 >> 12) & 15));
        int i27 = i3 + i13;
        if (!z2 && i27 < bArr.length) {
            for (int i28 = 0; i28 < i13; i28++) {
                int i29 = i28 + i7;
                int i30 = i29 + i13;
                if (i30 >= bArr.length) {
                    break;
                }
                bArr[i30] = bArr[i29];
                bArr[i29] = 0;
            }
        } else {
            if (i27 >= bArr.length) {
                i7++;
            }
            while (i3 < bArr.length) {
                int length2 = bArr.length - 1;
                while (length2 >= i7) {
                    bArr[length2] = bArr[length2 - 1];
                    length2--;
                }
                bArr[length2] = 0;
                i7++;
                i3++;
            }
        }
        if (i8 > 0) {
            bArr[11] = -1;
            bArr[10] = -1;
        }
        return bArr;
    }

    public static String toSocketAddressString(InetSocketAddress inetSocketAddress) {
        StringBuilder newSocketAddressStringBuilder;
        String valueOf = String.valueOf(inetSocketAddress.getPort());
        if (inetSocketAddress.isUnresolved()) {
            newSocketAddressStringBuilder = newSocketAddressStringBuilder(getHostname(inetSocketAddress), valueOf, !isValidIpV6Address(r2));
        } else {
            InetAddress address = inetSocketAddress.getAddress();
            newSocketAddressStringBuilder = newSocketAddressStringBuilder(toAddressString(address), valueOf, address instanceof Inet4Address);
        }
        newSocketAddressStringBuilder.append(':');
        newSocketAddressStringBuilder.append(valueOf);
        return newSocketAddressStringBuilder.toString();
    }

    public static String toSocketAddressString(String str, int i) {
        String valueOf = String.valueOf(i);
        StringBuilder newSocketAddressStringBuilder = newSocketAddressStringBuilder(str, valueOf, !isValidIpV6Address(str));
        newSocketAddressStringBuilder.append(':');
        newSocketAddressStringBuilder.append(valueOf);
        return newSocketAddressStringBuilder.toString();
    }

    private static StringBuilder newSocketAddressStringBuilder(String str, String str2, boolean z) {
        int length = str.length();
        if (z) {
            StringBuilder sb = new StringBuilder(length + 1 + str2.length());
            sb.append(str);
            return sb;
        }
        StringBuilder sb2 = new StringBuilder(length + 3 + str2.length());
        if (length > 1 && str.charAt(0) == '[' && str.charAt(length - 1) == ']') {
            sb2.append(str);
            return sb2;
        }
        sb2.append('[');
        sb2.append(str);
        sb2.append(']');
        return sb2;
    }

    public static String toAddressString(InetAddress inetAddress) {
        return toAddressString(inetAddress, false);
    }

    public static String toAddressString(InetAddress inetAddress, boolean z) {
        if (inetAddress instanceof Inet4Address) {
            return inetAddress.getHostAddress();
        }
        if (!(inetAddress instanceof Inet6Address)) {
            throw new IllegalArgumentException("Unhandled type: " + inetAddress);
        }
        return toAddressString(inetAddress.getAddress(), 0, z);
    }

    private static String toAddressString(byte[] bArr, int i, boolean z) {
        int i2;
        int i3;
        int[] iArr = new int[8];
        int length = iArr.length + i;
        while (true) {
            i2 = 1;
            if (i >= length) {
                break;
            }
            int i4 = i << 1;
            iArr[i] = (bArr[i4 + 1] & 255) | ((bArr[i4] & 255) << 8);
            i++;
        }
        int i5 = -1;
        boolean z2 = false;
        int i6 = -1;
        int i7 = -1;
        int i8 = 0;
        int i9 = 0;
        while (i8 < iArr.length) {
            if (iArr[i8] == 0) {
                if (i6 < 0) {
                    i6 = i8;
                }
            } else if (i6 >= 0) {
                int i10 = i8 - i6;
                if (i10 > i9) {
                    i9 = i10;
                } else {
                    i6 = i7;
                }
                i7 = i6;
                i6 = -1;
            }
            i8++;
        }
        if (i6 < 0 || (i3 = i8 - i6) <= i9) {
            i3 = i9;
            i6 = i7;
        }
        if (i3 == 1) {
            i3 = 0;
        } else {
            i5 = i6;
        }
        int i11 = i3 + i5;
        StringBuilder sb = new StringBuilder(39);
        if (i11 < 0) {
            sb.append(Integer.toHexString(iArr[0]));
            while (i2 < iArr.length) {
                sb.append(':');
                sb.append(Integer.toHexString(iArr[i2]));
                i2++;
            }
        } else {
            if (inRangeEndExclusive(0, i5, i11)) {
                sb.append("::");
                if (z && i11 == 5 && iArr[5] == 65535) {
                    z2 = true;
                }
            } else {
                sb.append(Integer.toHexString(iArr[0]));
            }
            while (i2 < iArr.length) {
                if (!inRangeEndExclusive(i2, i5, i11)) {
                    if (!inRangeEndExclusive(i2 - 1, i5, i11)) {
                        if (!z2 || i2 == 6) {
                            sb.append(':');
                        } else {
                            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                        }
                    }
                    if (z2 && i2 > 5) {
                        sb.append(iArr[i2] >> 8);
                        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                        sb.append(iArr[i2] & 255);
                    } else {
                        sb.append(Integer.toHexString(iArr[i2]));
                    }
                } else if (!inRangeEndExclusive(i2 - 1, i5, i11)) {
                    sb.append("::");
                }
                i2++;
            }
        }
        return sb.toString();
    }

    public static String getHostname(InetSocketAddress inetSocketAddress) {
        return PlatformDependent.javaVersion() >= 7 ? inetSocketAddress.getHostString() : inetSocketAddress.getHostName();
    }

    private NetUtil() {
    }
}
