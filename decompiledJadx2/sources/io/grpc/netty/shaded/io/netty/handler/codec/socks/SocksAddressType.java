package io.grpc.netty.shaded.io.netty.handler.codec.socks;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public enum SocksAddressType {
    IPv4((byte) 1),
    DOMAIN((byte) 3),
    IPv6((byte) 4),
    UNKNOWN((byte) -1);


    /* renamed from: b */
    private final byte f8385b;

    SocksAddressType(byte b) {
        this.f8385b = b;
    }

    @Deprecated
    public static SocksAddressType fromByte(byte b) {
        return valueOf(b);
    }

    public static SocksAddressType valueOf(byte b) {
        for (SocksAddressType socksAddressType : values()) {
            if (socksAddressType.f8385b == b) {
                return socksAddressType;
            }
        }
        return UNKNOWN;
    }

    public byte byteValue() {
        return this.f8385b;
    }
}
