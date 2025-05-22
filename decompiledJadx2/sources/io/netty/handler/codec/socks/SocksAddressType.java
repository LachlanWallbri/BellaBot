package io.netty.handler.codec.socks;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public enum SocksAddressType {
    IPv4((byte) 1),
    DOMAIN((byte) 3),
    IPv6((byte) 4),
    UNKNOWN((byte) -1);


    /* renamed from: b */
    private final byte f8518b;

    SocksAddressType(byte b) {
        this.f8518b = b;
    }

    @Deprecated
    public static SocksAddressType fromByte(byte b) {
        return valueOf(b);
    }

    public static SocksAddressType valueOf(byte b) {
        for (SocksAddressType socksAddressType : values()) {
            if (socksAddressType.f8518b == b) {
                return socksAddressType;
            }
        }
        return UNKNOWN;
    }

    public byte byteValue() {
        return this.f8518b;
    }
}
