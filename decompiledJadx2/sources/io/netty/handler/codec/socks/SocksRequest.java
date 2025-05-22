package io.netty.handler.codec.socks;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class SocksRequest extends SocksMessage {
    private final SocksRequestType requestType;

    /* JADX INFO: Access modifiers changed from: protected */
    public SocksRequest(SocksRequestType socksRequestType) {
        super(SocksMessageType.REQUEST);
        if (socksRequestType == null) {
            throw new NullPointerException("requestType");
        }
        this.requestType = socksRequestType;
    }

    public SocksRequestType requestType() {
        return this.requestType;
    }
}
