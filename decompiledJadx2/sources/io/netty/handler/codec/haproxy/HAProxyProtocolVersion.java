package io.netty.handler.codec.haproxy;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public enum HAProxyProtocolVersion {
    V1((byte) 16),
    V2((byte) 32);

    private static final byte VERSION_MASK = -16;
    private final byte byteValue;

    HAProxyProtocolVersion(byte b) {
        this.byteValue = b;
    }

    public static HAProxyProtocolVersion valueOf(byte b) {
        int i = b & VERSION_MASK;
        byte b2 = (byte) i;
        if (b2 == 16) {
            return V1;
        }
        if (b2 == 32) {
            return V2;
        }
        throw new IllegalArgumentException("unknown version: " + i);
    }

    public byte byteValue() {
        return this.byteValue;
    }
}
