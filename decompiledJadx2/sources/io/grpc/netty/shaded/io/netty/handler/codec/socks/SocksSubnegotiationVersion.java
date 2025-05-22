package io.grpc.netty.shaded.io.netty.handler.codec.socks;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public enum SocksSubnegotiationVersion {
    AUTH_PASSWORD((byte) 1),
    UNKNOWN((byte) -1);


    /* renamed from: b */
    private final byte f8397b;

    SocksSubnegotiationVersion(byte b) {
        this.f8397b = b;
    }

    @Deprecated
    public static SocksSubnegotiationVersion fromByte(byte b) {
        return valueOf(b);
    }

    public static SocksSubnegotiationVersion valueOf(byte b) {
        for (SocksSubnegotiationVersion socksSubnegotiationVersion : values()) {
            if (socksSubnegotiationVersion.f8397b == b) {
                return socksSubnegotiationVersion;
            }
        }
        return UNKNOWN;
    }

    public byte byteValue() {
        return this.f8397b;
    }
}
