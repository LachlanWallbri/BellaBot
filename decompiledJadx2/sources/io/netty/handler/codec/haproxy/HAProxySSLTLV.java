package io.netty.handler.codec.haproxy;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.haproxy.HAProxyTLV;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public final class HAProxySSLTLV extends HAProxyTLV {
    private final byte clientBitField;
    private final List<HAProxyTLV> tlvs;
    private final int verify;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HAProxySSLTLV(int i, byte b, List<HAProxyTLV> list, ByteBuf byteBuf) {
        super(HAProxyTLV.Type.PP2_TYPE_SSL, (byte) 32, byteBuf);
        this.verify = i;
        this.tlvs = Collections.unmodifiableList(list);
        this.clientBitField = b;
    }

    public boolean isPP2ClientCertConn() {
        return (this.clientBitField & 2) != 0;
    }

    public boolean isPP2ClientSSL() {
        return (this.clientBitField & 1) != 0;
    }

    public boolean isPP2ClientCertSess() {
        return (this.clientBitField & 4) != 0;
    }

    public int verify() {
        return this.verify;
    }

    public List<HAProxyTLV> encapsulatedTLVs() {
        return this.tlvs;
    }
}
