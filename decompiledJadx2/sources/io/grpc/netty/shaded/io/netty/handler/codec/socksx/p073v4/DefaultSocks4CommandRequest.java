package io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4;

import io.grpc.netty.shaded.io.netty.handler.codec.DecoderResult;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.net.IDN;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultSocks4CommandRequest extends AbstractSocks4Message implements Socks4CommandRequest {
    private final String dstAddr;
    private final int dstPort;
    private final Socks4CommandType type;
    private final String userId;

    public DefaultSocks4CommandRequest(Socks4CommandType socks4CommandType, String str, int i) {
        this(socks4CommandType, str, i, "");
    }

    public DefaultSocks4CommandRequest(Socks4CommandType socks4CommandType, String str, int i, String str2) {
        if (i <= 0 || i >= 65536) {
            throw new IllegalArgumentException("dstPort: " + i + " (expected: 1~65535)");
        }
        this.type = (Socks4CommandType) ObjectUtil.checkNotNull(socks4CommandType, "type");
        this.dstAddr = IDN.toASCII((String) ObjectUtil.checkNotNull(str, "dstAddr"));
        this.userId = (String) ObjectUtil.checkNotNull(str2, "userId");
        this.dstPort = i;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4.Socks4CommandRequest
    public Socks4CommandType type() {
        return this.type;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4.Socks4CommandRequest
    public String dstAddr() {
        return this.dstAddr;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4.Socks4CommandRequest
    public int dstPort() {
        return this.dstPort;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4.Socks4CommandRequest
    public String userId() {
        return this.userId;
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
        sb.append(", dstAddr: ");
        sb.append(dstAddr());
        sb.append(", dstPort: ");
        sb.append(dstPort());
        sb.append(", userId: ");
        sb.append(userId());
        sb.append(')');
        return sb.toString();
    }
}
