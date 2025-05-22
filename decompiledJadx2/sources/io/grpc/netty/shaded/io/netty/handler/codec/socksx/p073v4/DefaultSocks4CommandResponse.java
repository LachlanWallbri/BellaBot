package io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4;

import io.grpc.netty.shaded.io.netty.handler.codec.DecoderResult;
import io.grpc.netty.shaded.io.netty.util.NetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultSocks4CommandResponse extends AbstractSocks4Message implements Socks4CommandResponse {
    private final String dstAddr;
    private final int dstPort;
    private final Socks4CommandStatus status;

    public DefaultSocks4CommandResponse(Socks4CommandStatus socks4CommandStatus) {
        this(socks4CommandStatus, null, 0);
    }

    public DefaultSocks4CommandResponse(Socks4CommandStatus socks4CommandStatus, String str, int i) {
        if (str != null && !NetUtil.isValidIpV4Address(str)) {
            throw new IllegalArgumentException("dstAddr: " + str + " (expected: a valid IPv4 address)");
        }
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("dstPort: " + i + " (expected: 0~65535)");
        }
        this.status = (Socks4CommandStatus) ObjectUtil.checkNotNull(socks4CommandStatus, "cmdStatus");
        this.dstAddr = str;
        this.dstPort = i;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4.Socks4CommandResponse
    public Socks4CommandStatus status() {
        return this.status;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4.Socks4CommandResponse
    public String dstAddr() {
        return this.dstAddr;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4.Socks4CommandResponse
    public int dstPort() {
        return this.dstPort;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(96);
        sb.append(StringUtil.simpleClassName(this));
        DecoderResult decoderResult = decoderResult();
        if (!decoderResult.isSuccess()) {
            sb.append("(decoderResult: ");
            sb.append(decoderResult);
            sb.append(", dstAddr: ");
        } else {
            sb.append("(dstAddr: ");
        }
        sb.append(dstAddr());
        sb.append(", dstPort: ");
        sb.append(dstPort());
        sb.append(')');
        return sb.toString();
    }
}
