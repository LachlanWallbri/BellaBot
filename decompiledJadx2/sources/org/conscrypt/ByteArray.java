package org.conscrypt;

import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
final class ByteArray {
    private final byte[] bytes;
    private final int hashCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteArray(byte[] bArr) {
        this.bytes = bArr;
        this.hashCode = Arrays.hashCode(bArr);
    }

    public int hashCode() {
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ByteArray) {
            return Arrays.equals(this.bytes, ((ByteArray) obj).bytes);
        }
        return false;
    }
}
