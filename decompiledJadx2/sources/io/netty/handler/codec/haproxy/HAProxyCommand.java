package io.netty.handler.codec.haproxy;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public enum HAProxyCommand {
    LOCAL((byte) 0),
    PROXY((byte) 1);

    private static final byte COMMAND_MASK = 15;
    private final byte byteValue;

    HAProxyCommand(byte b) {
        this.byteValue = b;
    }

    public static HAProxyCommand valueOf(byte b) {
        int i = b & 15;
        byte b2 = (byte) i;
        if (b2 == 0) {
            return LOCAL;
        }
        if (b2 == 1) {
            return PROXY;
        }
        throw new IllegalArgumentException("unknown command: " + i);
    }

    public byte byteValue() {
        return this.byteValue;
    }
}
