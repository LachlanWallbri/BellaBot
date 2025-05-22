package io.netty.handler.codec.socks;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class SocksResponse extends SocksMessage {
    private final SocksResponseType responseType;

    /* JADX INFO: Access modifiers changed from: protected */
    public SocksResponse(SocksResponseType socksResponseType) {
        super(SocksMessageType.RESPONSE);
        if (socksResponseType == null) {
            throw new NullPointerException("responseType");
        }
        this.responseType = socksResponseType;
    }

    public SocksResponseType responseType() {
        return this.responseType;
    }
}
