package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class SocksMessage {
    private final SocksProtocolVersion protocolVersion = SocksProtocolVersion.SOCKS5;
    private final SocksMessageType type;

    @Deprecated
    public abstract void encodeAsByteBuf(ByteBuf byteBuf);

    /* JADX INFO: Access modifiers changed from: protected */
    public SocksMessage(SocksMessageType socksMessageType) {
        if (socksMessageType == null) {
            throw new NullPointerException("type");
        }
        this.type = socksMessageType;
    }

    public SocksMessageType type() {
        return this.type;
    }

    public SocksProtocolVersion protocolVersion() {
        return this.protocolVersion;
    }
}
