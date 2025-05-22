package io.netty.handler.codec.socks;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public enum SocksAuthScheme {
    NO_AUTH((byte) 0),
    AUTH_GSSAPI((byte) 1),
    AUTH_PASSWORD((byte) 2),
    UNKNOWN((byte) -1);


    /* renamed from: b */
    private final byte f8521b;

    SocksAuthScheme(byte b) {
        this.f8521b = b;
    }

    @Deprecated
    public static SocksAuthScheme fromByte(byte b) {
        return valueOf(b);
    }

    public static SocksAuthScheme valueOf(byte b) {
        for (SocksAuthScheme socksAuthScheme : values()) {
            if (socksAuthScheme.f8521b == b) {
                return socksAuthScheme;
            }
        }
        return UNKNOWN;
    }

    public byte byteValue() {
        return this.f8521b;
    }
}
