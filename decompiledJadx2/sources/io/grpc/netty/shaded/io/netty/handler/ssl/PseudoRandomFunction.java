package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class PseudoRandomFunction {
    private PseudoRandomFunction() {
    }

    static byte[] hash(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, String str) {
        if (i < 0) {
            throw new IllegalArgumentException("You must provide a length greater than zero.");
        }
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(bArr, str));
            int ceil = (int) Math.ceil(i / mac.getMacLength());
            byte[] bArr4 = EmptyArrays.EMPTY_BYTES;
            byte[] concat = concat(bArr2, bArr3);
            byte[] bArr5 = concat;
            for (int i2 = 0; i2 < ceil; i2++) {
                bArr5 = mac.doFinal(bArr5);
                bArr4 = concat(bArr4, mac.doFinal(concat(bArr5, concat)));
            }
            return Arrays.copyOf(bArr4, i);
        } catch (GeneralSecurityException e) {
            throw new IllegalArgumentException("Could not find algo: " + str, e);
        }
    }

    private static byte[] concat(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length + bArr2.length);
        System.arraycopy(bArr2, 0, copyOf, bArr.length, bArr2.length);
        return copyOf;
    }
}
