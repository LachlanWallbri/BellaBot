package io.grpc.netty.shaded.io.netty.internal.tcnative;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class SessionTicketKey {
    public static final int AES_KEY_SIZE = 16;
    public static final int HMAC_KEY_SIZE = 16;
    public static final int NAME_SIZE = 16;
    public static final int TICKET_KEY_SIZE = 48;
    final byte[] aesKey;
    final byte[] hmacKey;
    final byte[] name;

    public SessionTicketKey(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null || bArr.length != 16) {
            throw new IllegalArgumentException("Length of name should be 16");
        }
        if (bArr2 == null || bArr2.length != 16) {
            throw new IllegalArgumentException("Length of hmacKey should be 16");
        }
        if (bArr3 == null || bArr3.length != 16) {
            throw new IllegalArgumentException("Length of aesKey should be 16");
        }
        this.name = bArr;
        this.hmacKey = bArr2;
        this.aesKey = bArr3;
    }

    public byte[] getName() {
        return (byte[]) this.name.clone();
    }

    public byte[] getHmacKey() {
        return (byte[]) this.hmacKey.clone();
    }

    public byte[] getAesKey() {
        return (byte[]) this.aesKey.clone();
    }
}
