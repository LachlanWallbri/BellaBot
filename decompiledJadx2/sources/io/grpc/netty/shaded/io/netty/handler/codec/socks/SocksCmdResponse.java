package io.grpc.netty.shaded.io.netty.handler.codec.socks;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.NetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.net.IDN;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class SocksCmdResponse extends SocksResponse {
    private static final byte[] DOMAIN_ZEROED = {0};
    private static final byte[] IPv4_HOSTNAME_ZEROED = {0, 0, 0, 0};
    private static final byte[] IPv6_HOSTNAME_ZEROED = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private final SocksAddressType addressType;
    private final SocksCmdStatus cmdStatus;
    private final String host;
    private final int port;

    public SocksCmdResponse(SocksCmdStatus socksCmdStatus, SocksAddressType socksAddressType) {
        this(socksCmdStatus, socksAddressType, null, 0);
    }

    public SocksCmdResponse(SocksCmdStatus socksCmdStatus, SocksAddressType socksAddressType, String str, int i) {
        super(SocksResponseType.CMD);
        ObjectUtil.checkNotNull(socksCmdStatus, "cmdStatus");
        ObjectUtil.checkNotNull(socksAddressType, "addressType");
        if (str != null) {
            int i2 = C65811.$SwitchMap$io$netty$handler$codec$socks$SocksAddressType[socksAddressType.ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    String ascii = IDN.toASCII(str);
                    if (ascii.length() > 255) {
                        throw new IllegalArgumentException(str + " IDN: " + ascii + " exceeds 255 char limit");
                    }
                    str = ascii;
                } else if (i2 == 3 && !NetUtil.isValidIpV6Address(str)) {
                    throw new IllegalArgumentException(str + " is not a valid IPv6 address");
                }
            } else if (!NetUtil.isValidIpV4Address(str)) {
                throw new IllegalArgumentException(str + " is not a valid IPv4 address");
            }
        }
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException(i + " is not in bounds 0 <= x <= 65535");
        }
        this.cmdStatus = socksCmdStatus;
        this.addressType = socksAddressType;
        this.host = str;
        this.port = i;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.socks.SocksCmdResponse$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C65811 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$socks$SocksAddressType = new int[SocksAddressType.values().length];

        static {
            try {
                $SwitchMap$io$netty$handler$codec$socks$SocksAddressType[SocksAddressType.IPv4.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$socks$SocksAddressType[SocksAddressType.DOMAIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$socks$SocksAddressType[SocksAddressType.IPv6.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$socks$SocksAddressType[SocksAddressType.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public SocksCmdStatus cmdStatus() {
        return this.cmdStatus;
    }

    public SocksAddressType addressType() {
        return this.addressType;
    }

    public String host() {
        return (this.host == null || this.addressType != SocksAddressType.DOMAIN) ? this.host : IDN.toUnicode(this.host);
    }

    public int port() {
        return this.port;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socks.SocksMessage
    public void encodeAsByteBuf(ByteBuf byteBuf) {
        byteBuf.writeByte(protocolVersion().byteValue());
        byteBuf.writeByte(this.cmdStatus.byteValue());
        byteBuf.writeByte(0);
        byteBuf.writeByte(this.addressType.byteValue());
        int i = C65811.$SwitchMap$io$netty$handler$codec$socks$SocksAddressType[this.addressType.ordinal()];
        if (i == 1) {
            String str = this.host;
            byteBuf.writeBytes(str == null ? IPv4_HOSTNAME_ZEROED : NetUtil.createByteArrayFromIpAddressString(str));
            byteBuf.writeShort(this.port);
        } else {
            if (i != 2) {
                if (i != 3) {
                    return;
                }
                String str2 = this.host;
                byteBuf.writeBytes(str2 == null ? IPv6_HOSTNAME_ZEROED : NetUtil.createByteArrayFromIpAddressString(str2));
                byteBuf.writeShort(this.port);
                return;
            }
            String str3 = this.host;
            if (str3 != null) {
                byteBuf.writeByte(str3.length());
                byteBuf.writeCharSequence(this.host, CharsetUtil.US_ASCII);
            } else {
                byteBuf.writeByte(DOMAIN_ZEROED.length);
                byteBuf.writeBytes(DOMAIN_ZEROED);
            }
            byteBuf.writeShort(this.port);
        }
    }
}
