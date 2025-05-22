package io.netty.handler.codec.haproxy;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.haproxy.HAProxyProxiedProtocol;
import io.netty.handler.codec.haproxy.HAProxyTLV;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;
import io.netty.util.NetUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public final class HAProxyMessage {
    private final HAProxyCommand command;
    private final String destinationAddress;
    private final int destinationPort;
    private final HAProxyProtocolVersion protocolVersion;
    private final HAProxyProxiedProtocol proxiedProtocol;
    private final String sourceAddress;
    private final int sourcePort;
    private final List<HAProxyTLV> tlvs;
    private static final HAProxyMessage V1_UNKNOWN_MSG = new HAProxyMessage(HAProxyProtocolVersion.V1, HAProxyCommand.PROXY, HAProxyProxiedProtocol.UNKNOWN, (String) null, (String) null, 0, 0);
    private static final HAProxyMessage V2_UNKNOWN_MSG = new HAProxyMessage(HAProxyProtocolVersion.V2, HAProxyCommand.PROXY, HAProxyProxiedProtocol.UNKNOWN, (String) null, (String) null, 0, 0);
    private static final HAProxyMessage V2_LOCAL_MSG = new HAProxyMessage(HAProxyProtocolVersion.V2, HAProxyCommand.LOCAL, HAProxyProxiedProtocol.UNKNOWN, (String) null, (String) null, 0, 0);

    private HAProxyMessage(HAProxyProtocolVersion hAProxyProtocolVersion, HAProxyCommand hAProxyCommand, HAProxyProxiedProtocol hAProxyProxiedProtocol, String str, String str2, String str3, String str4) {
        this(hAProxyProtocolVersion, hAProxyCommand, hAProxyProxiedProtocol, str, str2, portStringToInt(str3), portStringToInt(str4));
    }

    private HAProxyMessage(HAProxyProtocolVersion hAProxyProtocolVersion, HAProxyCommand hAProxyCommand, HAProxyProxiedProtocol hAProxyProxiedProtocol, String str, String str2, int i, int i2) {
        this(hAProxyProtocolVersion, hAProxyCommand, hAProxyProxiedProtocol, str, str2, i, i2, Collections.emptyList());
    }

    private HAProxyMessage(HAProxyProtocolVersion hAProxyProtocolVersion, HAProxyCommand hAProxyCommand, HAProxyProxiedProtocol hAProxyProxiedProtocol, String str, String str2, int i, int i2, List<HAProxyTLV> list) {
        if (hAProxyProxiedProtocol == null) {
            throw new NullPointerException("proxiedProtocol");
        }
        HAProxyProxiedProtocol.AddressFamily addressFamily = hAProxyProxiedProtocol.addressFamily();
        checkAddress(str, addressFamily);
        checkAddress(str2, addressFamily);
        checkPort(i);
        checkPort(i2);
        this.protocolVersion = hAProxyProtocolVersion;
        this.command = hAProxyCommand;
        this.proxiedProtocol = hAProxyProxiedProtocol;
        this.sourceAddress = str;
        this.destinationAddress = str2;
        this.sourcePort = i;
        this.destinationPort = i2;
        this.tlvs = Collections.unmodifiableList(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HAProxyMessage decodeHeader(ByteBuf byteBuf) {
        String ipBytesToString;
        String ipBytesToString2;
        int readUnsignedShort;
        int readUnsignedShort2;
        if (byteBuf == null) {
            throw new NullPointerException("header");
        }
        int i = 16;
        if (byteBuf.readableBytes() < 16) {
            throw new HAProxyProtocolException("incomplete header: " + byteBuf.readableBytes() + " bytes (expected: 16+ bytes)");
        }
        byteBuf.skipBytes(12);
        byte readByte = byteBuf.readByte();
        try {
            HAProxyProtocolVersion valueOf = HAProxyProtocolVersion.valueOf(readByte);
            if (valueOf != HAProxyProtocolVersion.V2) {
                throw new HAProxyProtocolException("version 1 unsupported: 0x" + Integer.toHexString(readByte));
            }
            try {
                HAProxyCommand valueOf2 = HAProxyCommand.valueOf(readByte);
                if (valueOf2 == HAProxyCommand.LOCAL) {
                    return V2_LOCAL_MSG;
                }
                try {
                    HAProxyProxiedProtocol valueOf3 = HAProxyProxiedProtocol.valueOf(byteBuf.readByte());
                    if (valueOf3 == HAProxyProxiedProtocol.UNKNOWN) {
                        return V2_UNKNOWN_MSG;
                    }
                    int readUnsignedShort3 = byteBuf.readUnsignedShort();
                    HAProxyProxiedProtocol.AddressFamily addressFamily = valueOf3.addressFamily();
                    if (addressFamily == HAProxyProxiedProtocol.AddressFamily.AF_UNIX) {
                        if (readUnsignedShort3 < 216 || byteBuf.readableBytes() < 216) {
                            throw new HAProxyProtocolException("incomplete UNIX socket address information: " + Math.min(readUnsignedShort3, byteBuf.readableBytes()) + " bytes (expected: 216+ bytes)");
                        }
                        int readerIndex = byteBuf.readerIndex();
                        int forEachByte = byteBuf.forEachByte(readerIndex, 108, ByteProcessor.FIND_NUL);
                        String byteBuf2 = byteBuf.toString(readerIndex, forEachByte == -1 ? 108 : forEachByte - readerIndex, CharsetUtil.US_ASCII);
                        int i2 = readerIndex + 108;
                        int forEachByte2 = byteBuf.forEachByte(i2, 108, ByteProcessor.FIND_NUL);
                        String byteBuf3 = byteBuf.toString(i2, forEachByte2 == -1 ? 108 : forEachByte2 - i2, CharsetUtil.US_ASCII);
                        byteBuf.readerIndex(i2 + 108);
                        ipBytesToString = byteBuf2;
                        readUnsignedShort = 0;
                        readUnsignedShort2 = 0;
                        ipBytesToString2 = byteBuf3;
                    } else {
                        if (addressFamily == HAProxyProxiedProtocol.AddressFamily.AF_IPv4) {
                            if (readUnsignedShort3 < 12 || byteBuf.readableBytes() < 12) {
                                throw new HAProxyProtocolException("incomplete IPv4 address information: " + Math.min(readUnsignedShort3, byteBuf.readableBytes()) + " bytes (expected: 12+ bytes)");
                            }
                            i = 4;
                        } else if (addressFamily == HAProxyProxiedProtocol.AddressFamily.AF_IPv6) {
                            if (readUnsignedShort3 < 36 || byteBuf.readableBytes() < 36) {
                                throw new HAProxyProtocolException("incomplete IPv6 address information: " + Math.min(readUnsignedShort3, byteBuf.readableBytes()) + " bytes (expected: 36+ bytes)");
                            }
                        } else {
                            throw new HAProxyProtocolException("unable to parse address information (unknown address family: " + addressFamily + ')');
                        }
                        ipBytesToString = ipBytesToString(byteBuf, i);
                        ipBytesToString2 = ipBytesToString(byteBuf, i);
                        readUnsignedShort = byteBuf.readUnsignedShort();
                        readUnsignedShort2 = byteBuf.readUnsignedShort();
                    }
                    return new HAProxyMessage(valueOf, valueOf2, valueOf3, ipBytesToString, ipBytesToString2, readUnsignedShort, readUnsignedShort2, readTlvs(byteBuf));
                } catch (IllegalArgumentException e) {
                    throw new HAProxyProtocolException(e);
                }
            } catch (IllegalArgumentException e2) {
                throw new HAProxyProtocolException(e2);
            }
        } catch (IllegalArgumentException e3) {
            throw new HAProxyProtocolException(e3);
        }
    }

    private static List<HAProxyTLV> readTlvs(ByteBuf byteBuf) {
        HAProxyTLV readNextTLV = readNextTLV(byteBuf);
        if (readNextTLV == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(4);
        do {
            arrayList.add(readNextTLV);
            if (readNextTLV instanceof HAProxySSLTLV) {
                arrayList.addAll(((HAProxySSLTLV) readNextTLV).encapsulatedTLVs());
            }
            readNextTLV = readNextTLV(byteBuf);
        } while (readNextTLV != null);
        return arrayList;
    }

    private static HAProxyTLV readNextTLV(ByteBuf byteBuf) {
        if (byteBuf.readableBytes() < 4) {
            return null;
        }
        byte readByte = byteBuf.readByte();
        HAProxyTLV.Type typeForByteValue = HAProxyTLV.Type.typeForByteValue(readByte);
        int readUnsignedShort = byteBuf.readUnsignedShort();
        switch (C71331.$SwitchMap$io$netty$handler$codec$haproxy$HAProxyTLV$Type[typeForByteValue.ordinal()]) {
            case 1:
                ByteBuf retainedSlice = byteBuf.retainedSlice(byteBuf.readerIndex(), readUnsignedShort);
                ByteBuf readSlice = byteBuf.readSlice(readUnsignedShort);
                byte readByte2 = readSlice.readByte();
                int readInt = readSlice.readInt();
                if (readSlice.readableBytes() >= 4) {
                    ArrayList arrayList = new ArrayList(4);
                    do {
                        HAProxyTLV readNextTLV = readNextTLV(readSlice);
                        if (readNextTLV != null) {
                            arrayList.add(readNextTLV);
                        }
                        return new HAProxySSLTLV(readInt, readByte2, arrayList, retainedSlice);
                    } while (readSlice.readableBytes() >= 4);
                    return new HAProxySSLTLV(readInt, readByte2, arrayList, retainedSlice);
                }
                return new HAProxySSLTLV(readInt, readByte2, Collections.emptyList(), retainedSlice);
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return new HAProxyTLV(typeForByteValue, readByte, byteBuf.readRetainedSlice(readUnsignedShort));
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HAProxyMessage decodeHeader(String str) {
        if (str == null) {
            throw new HAProxyProtocolException("header");
        }
        String[] split = str.split(" ");
        int length = split.length;
        if (length < 2) {
            throw new HAProxyProtocolException("invalid header: " + str + " (expected: 'PROXY' and proxied protocol values)");
        }
        if (!"PROXY".equals(split[0])) {
            throw new HAProxyProtocolException("unknown identifier: " + split[0]);
        }
        try {
            HAProxyProxiedProtocol valueOf = HAProxyProxiedProtocol.valueOf(split[1]);
            if (valueOf != HAProxyProxiedProtocol.TCP4 && valueOf != HAProxyProxiedProtocol.TCP6 && valueOf != HAProxyProxiedProtocol.UNKNOWN) {
                throw new HAProxyProtocolException("unsupported v1 proxied protocol: " + split[1]);
            }
            if (valueOf == HAProxyProxiedProtocol.UNKNOWN) {
                return V1_UNKNOWN_MSG;
            }
            if (length != 6) {
                throw new HAProxyProtocolException("invalid TCP4/6 header: " + str + " (expected: 6 parts)");
            }
            return new HAProxyMessage(HAProxyProtocolVersion.V1, HAProxyCommand.PROXY, valueOf, split[2], split[3], split[4], split[5]);
        } catch (IllegalArgumentException e) {
            throw new HAProxyProtocolException(e);
        }
    }

    private static String ipBytesToString(ByteBuf byteBuf, int i) {
        StringBuilder sb = new StringBuilder();
        if (i == 4) {
            sb.append(byteBuf.readByte() & 255);
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
            sb.append(byteBuf.readByte() & 255);
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
            sb.append(byteBuf.readByte() & 255);
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
            sb.append(byteBuf.readByte() & 255);
        } else {
            sb.append(Integer.toHexString(byteBuf.readUnsignedShort()));
            sb.append(':');
            sb.append(Integer.toHexString(byteBuf.readUnsignedShort()));
            sb.append(':');
            sb.append(Integer.toHexString(byteBuf.readUnsignedShort()));
            sb.append(':');
            sb.append(Integer.toHexString(byteBuf.readUnsignedShort()));
            sb.append(':');
            sb.append(Integer.toHexString(byteBuf.readUnsignedShort()));
            sb.append(':');
            sb.append(Integer.toHexString(byteBuf.readUnsignedShort()));
            sb.append(':');
            sb.append(Integer.toHexString(byteBuf.readUnsignedShort()));
            sb.append(':');
            sb.append(Integer.toHexString(byteBuf.readUnsignedShort()));
        }
        return sb.toString();
    }

    private static int portStringToInt(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt > 0 && parseInt <= 65535) {
                return parseInt;
            }
            throw new HAProxyProtocolException("invalid port: " + str + " (expected: 1 ~ 65535)");
        } catch (NumberFormatException e) {
            throw new HAProxyProtocolException("invalid port: " + str, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes7.dex
     */
    /* renamed from: io.netty.handler.codec.haproxy.HAProxyMessage$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C71331 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$haproxy$HAProxyProxiedProtocol$AddressFamily */
        static final /* synthetic */ int[] f8491x3a3897ab;
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$haproxy$HAProxyTLV$Type;

        static {
            int[] iArr = new int[HAProxyProxiedProtocol.AddressFamily.values().length];
            f8491x3a3897ab = iArr;
            try {
                iArr[HAProxyProxiedProtocol.AddressFamily.AF_UNSPEC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8491x3a3897ab[HAProxyProxiedProtocol.AddressFamily.AF_UNIX.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8491x3a3897ab[HAProxyProxiedProtocol.AddressFamily.AF_IPv4.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8491x3a3897ab[HAProxyProxiedProtocol.AddressFamily.AF_IPv6.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[HAProxyTLV.Type.values().length];
            $SwitchMap$io$netty$handler$codec$haproxy$HAProxyTLV$Type = iArr2;
            try {
                iArr2[HAProxyTLV.Type.PP2_TYPE_SSL.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$haproxy$HAProxyTLV$Type[HAProxyTLV.Type.PP2_TYPE_ALPN.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$haproxy$HAProxyTLV$Type[HAProxyTLV.Type.PP2_TYPE_AUTHORITY.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$haproxy$HAProxyTLV$Type[HAProxyTLV.Type.PP2_TYPE_SSL_VERSION.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$haproxy$HAProxyTLV$Type[HAProxyTLV.Type.PP2_TYPE_SSL_CN.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$haproxy$HAProxyTLV$Type[HAProxyTLV.Type.PP2_TYPE_NETNS.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$haproxy$HAProxyTLV$Type[HAProxyTLV.Type.OTHER.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    private static void checkAddress(String str, HAProxyProxiedProtocol.AddressFamily addressFamily) {
        if (addressFamily == null) {
            throw new NullPointerException("addrFamily");
        }
        int i = C71331.f8491x3a3897ab[addressFamily.ordinal()];
        if (i == 1) {
            if (str == null) {
                return;
            }
            throw new HAProxyProtocolException("unable to validate an AF_UNSPEC address: " + str);
        }
        if (i != 2) {
            if (str == null) {
                throw new NullPointerException("address");
            }
            int i2 = C71331.f8491x3a3897ab[addressFamily.ordinal()];
            if (i2 == 3) {
                if (NetUtil.isValidIpV4Address(str)) {
                    return;
                }
                throw new HAProxyProtocolException("invalid IPv4 address: " + str);
            }
            if (i2 == 4) {
                if (NetUtil.isValidIpV6Address(str)) {
                    return;
                }
                throw new HAProxyProtocolException("invalid IPv6 address: " + str);
            }
            throw new Error();
        }
    }

    private static void checkPort(int i) {
        if (i < 0 || i > 65535) {
            throw new HAProxyProtocolException("invalid port: " + i + " (expected: 1 ~ 65535)");
        }
    }

    public HAProxyProtocolVersion protocolVersion() {
        return this.protocolVersion;
    }

    public HAProxyCommand command() {
        return this.command;
    }

    public HAProxyProxiedProtocol proxiedProtocol() {
        return this.proxiedProtocol;
    }

    public String sourceAddress() {
        return this.sourceAddress;
    }

    public String destinationAddress() {
        return this.destinationAddress;
    }

    public int sourcePort() {
        return this.sourcePort;
    }

    public int destinationPort() {
        return this.destinationPort;
    }

    public List<HAProxyTLV> tlvs() {
        return this.tlvs;
    }
}
