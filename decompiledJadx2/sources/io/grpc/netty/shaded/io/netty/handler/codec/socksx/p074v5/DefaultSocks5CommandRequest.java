package io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5;

import io.grpc.netty.shaded.io.netty.handler.codec.DecoderResult;
import io.grpc.netty.shaded.io.netty.util.NetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.net.IDN;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class DefaultSocks5CommandRequest extends AbstractSocks5Message implements Socks5CommandRequest {
    private final String dstAddr;
    private final Socks5AddressType dstAddrType;
    private final int dstPort;
    private final Socks5CommandType type;

    public DefaultSocks5CommandRequest(Socks5CommandType socks5CommandType, Socks5AddressType socks5AddressType, String str, int i) {
        this.type = (Socks5CommandType) ObjectUtil.checkNotNull(socks5CommandType, "type");
        ObjectUtil.checkNotNull(socks5AddressType, "dstAddrType");
        ObjectUtil.checkNotNull(str, "dstAddr");
        if (socks5AddressType == Socks5AddressType.IPv4) {
            if (!NetUtil.isValidIpV4Address(str)) {
                throw new IllegalArgumentException("dstAddr: " + str + " (expected: a valid IPv4 address)");
            }
        } else if (socks5AddressType == Socks5AddressType.DOMAIN) {
            str = IDN.toASCII(str);
            if (str.length() > 255) {
                throw new IllegalArgumentException("dstAddr: " + str + " (expected: less than 256 chars)");
            }
        } else if (socks5AddressType == Socks5AddressType.IPv6 && !NetUtil.isValidIpV6Address(str)) {
            throw new IllegalArgumentException("dstAddr: " + str + " (expected: a valid IPv6 address");
        }
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("dstPort: " + i + " (expected: 0~65535)");
        }
        this.dstAddrType = socks5AddressType;
        this.dstAddr = str;
        this.dstPort = i;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5.Socks5CommandRequest
    public Socks5CommandType type() {
        return this.type;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5.Socks5CommandRequest
    public Socks5AddressType dstAddrType() {
        return this.dstAddrType;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5.Socks5CommandRequest
    public String dstAddr() {
        return this.dstAddr;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5.Socks5CommandRequest
    public int dstPort() {
        return this.dstPort;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(StringUtil.simpleClassName(this));
        DecoderResult decoderResult = decoderResult();
        if (!decoderResult.isSuccess()) {
            sb.append("(decoderResult: ");
            sb.append(decoderResult);
            sb.append(", type: ");
        } else {
            sb.append("(type: ");
        }
        sb.append(type());
        sb.append(", dstAddrType: ");
        sb.append(dstAddrType());
        sb.append(", dstAddr: ");
        sb.append(dstAddr());
        sb.append(", dstPort: ");
        sb.append(dstPort());
        sb.append(')');
        return sb.toString();
    }
}
