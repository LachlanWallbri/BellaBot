package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class UnknownSocksResponse extends SocksResponse {
    @Override // io.netty.handler.codec.socks.SocksMessage
    public void encodeAsByteBuf(ByteBuf byteBuf) {
    }

    public UnknownSocksResponse() {
        super(SocksResponseType.UNKNOWN);
    }
}
