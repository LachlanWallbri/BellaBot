package io.grpc.netty.shaded.io.netty.handler.codec.socks;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public enum SocksProtocolVersion {
    SOCKS4a((byte) 4),
    SOCKS5((byte) 5),
    UNKNOWN((byte) -1);


    /* renamed from: b */
    private final byte f8396b;

    SocksProtocolVersion(byte b) {
        this.f8396b = b;
    }

    @Deprecated
    public static SocksProtocolVersion fromByte(byte b) {
        return valueOf(b);
    }

    public static SocksProtocolVersion valueOf(byte b) {
        for (SocksProtocolVersion socksProtocolVersion : values()) {
            if (socksProtocolVersion.f8396b == b) {
                return socksProtocolVersion;
            }
        }
        return UNKNOWN;
    }

    public byte byteValue() {
        return this.f8396b;
    }
}
