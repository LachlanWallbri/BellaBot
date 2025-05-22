package io.netty.handler.codec.socks;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public enum SocksAuthStatus {
    SUCCESS((byte) 0),
    FAILURE((byte) -1);


    /* renamed from: b */
    private final byte f8522b;

    SocksAuthStatus(byte b) {
        this.f8522b = b;
    }

    @Deprecated
    public static SocksAuthStatus fromByte(byte b) {
        return valueOf(b);
    }

    public static SocksAuthStatus valueOf(byte b) {
        for (SocksAuthStatus socksAuthStatus : values()) {
            if (socksAuthStatus.f8522b == b) {
                return socksAuthStatus;
            }
        }
        return FAILURE;
    }

    public byte byteValue() {
        return this.f8522b;
    }
}
