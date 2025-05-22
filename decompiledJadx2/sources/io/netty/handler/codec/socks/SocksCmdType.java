package io.netty.handler.codec.socks;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public enum SocksCmdType {
    CONNECT((byte) 1),
    BIND((byte) 2),
    UDP((byte) 3),
    UNKNOWN((byte) -1);


    /* renamed from: b */
    private final byte f8526b;

    SocksCmdType(byte b) {
        this.f8526b = b;
    }

    @Deprecated
    public static SocksCmdType fromByte(byte b) {
        return valueOf(b);
    }

    public static SocksCmdType valueOf(byte b) {
        for (SocksCmdType socksCmdType : values()) {
            if (socksCmdType.f8526b == b) {
                return socksCmdType;
            }
        }
        return UNKNOWN;
    }

    public byte byteValue() {
        return this.f8526b;
    }
}
