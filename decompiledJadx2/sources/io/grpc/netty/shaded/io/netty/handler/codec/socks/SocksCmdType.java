package io.grpc.netty.shaded.io.netty.handler.codec.socks;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public enum SocksCmdType {
    CONNECT((byte) 1),
    BIND((byte) 2),
    UDP((byte) 3),
    UNKNOWN((byte) -1);


    /* renamed from: b */
    private final byte f8393b;

    SocksCmdType(byte b) {
        this.f8393b = b;
    }

    @Deprecated
    public static SocksCmdType fromByte(byte b) {
        return valueOf(b);
    }

    public static SocksCmdType valueOf(byte b) {
        for (SocksCmdType socksCmdType : values()) {
            if (socksCmdType.f8393b == b) {
                return socksCmdType;
            }
        }
        return UNKNOWN;
    }

    public byte byteValue() {
        return this.f8393b;
    }
}
