package io.grpc.netty.shaded.io.netty.handler.codec.socks;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public enum SocksAuthScheme {
    NO_AUTH((byte) 0),
    AUTH_GSSAPI((byte) 1),
    AUTH_PASSWORD((byte) 2),
    UNKNOWN((byte) -1);


    /* renamed from: b */
    private final byte f8388b;

    SocksAuthScheme(byte b) {
        this.f8388b = b;
    }

    @Deprecated
    public static SocksAuthScheme fromByte(byte b) {
        return valueOf(b);
    }

    public static SocksAuthScheme valueOf(byte b) {
        for (SocksAuthScheme socksAuthScheme : values()) {
            if (socksAuthScheme.f8388b == b) {
                return socksAuthScheme;
            }
        }
        return UNKNOWN;
    }

    public byte byteValue() {
        return this.f8388b;
    }
}
