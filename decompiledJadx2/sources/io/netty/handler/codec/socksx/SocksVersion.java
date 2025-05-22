package io.netty.handler.codec.socksx;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public enum SocksVersion {
    SOCKS4a((byte) 4),
    SOCKS5((byte) 5),
    UNKNOWN((byte) -1);


    /* renamed from: b */
    private final byte f8531b;

    public static SocksVersion valueOf(byte b) {
        if (b == SOCKS4a.byteValue()) {
            return SOCKS4a;
        }
        if (b == SOCKS5.byteValue()) {
            return SOCKS5;
        }
        return UNKNOWN;
    }

    SocksVersion(byte b) {
        this.f8531b = b;
    }

    public byte byteValue() {
        return this.f8531b;
    }
}
