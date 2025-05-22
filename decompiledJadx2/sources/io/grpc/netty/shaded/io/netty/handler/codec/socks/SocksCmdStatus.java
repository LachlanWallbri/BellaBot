package io.grpc.netty.shaded.io.netty.handler.codec.socks;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public enum SocksCmdStatus {
    SUCCESS((byte) 0),
    FAILURE((byte) 1),
    FORBIDDEN((byte) 2),
    NETWORK_UNREACHABLE((byte) 3),
    HOST_UNREACHABLE((byte) 4),
    REFUSED((byte) 5),
    TTL_EXPIRED((byte) 6),
    COMMAND_NOT_SUPPORTED((byte) 7),
    ADDRESS_NOT_SUPPORTED((byte) 8),
    UNASSIGNED((byte) -1);


    /* renamed from: b */
    private final byte f8392b;

    SocksCmdStatus(byte b) {
        this.f8392b = b;
    }

    @Deprecated
    public static SocksCmdStatus fromByte(byte b) {
        return valueOf(b);
    }

    public static SocksCmdStatus valueOf(byte b) {
        for (SocksCmdStatus socksCmdStatus : values()) {
            if (socksCmdStatus.f8392b == b) {
                return socksCmdStatus;
            }
        }
        return UNASSIGNED;
    }

    public byte byteValue() {
        return this.f8392b;
    }
}
