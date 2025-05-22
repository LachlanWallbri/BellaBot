package io.netty.handler.codec.socks;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public enum SocksSubnegotiationVersion {
    AUTH_PASSWORD((byte) 1),
    UNKNOWN((byte) -1);


    /* renamed from: b */
    private final byte f8530b;

    SocksSubnegotiationVersion(byte b) {
        this.f8530b = b;
    }

    @Deprecated
    public static SocksSubnegotiationVersion fromByte(byte b) {
        return valueOf(b);
    }

    public static SocksSubnegotiationVersion valueOf(byte b) {
        for (SocksSubnegotiationVersion socksSubnegotiationVersion : values()) {
            if (socksSubnegotiationVersion.f8530b == b) {
                return socksSubnegotiationVersion;
            }
        }
        return UNKNOWN;
    }

    public byte byteValue() {
        return this.f8530b;
    }
}
