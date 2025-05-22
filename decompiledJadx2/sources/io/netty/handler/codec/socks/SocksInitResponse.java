package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class SocksInitResponse extends SocksResponse {
    private final SocksAuthScheme authScheme;

    public SocksInitResponse(SocksAuthScheme socksAuthScheme) {
        super(SocksResponseType.INIT);
        if (socksAuthScheme == null) {
            throw new NullPointerException("authScheme");
        }
        this.authScheme = socksAuthScheme;
    }

    public SocksAuthScheme authScheme() {
        return this.authScheme;
    }

    @Override // io.netty.handler.codec.socks.SocksMessage
    public void encodeAsByteBuf(ByteBuf byteBuf) {
        byteBuf.writeByte(protocolVersion().byteValue());
        byteBuf.writeByte(this.authScheme.byteValue());
    }
}
