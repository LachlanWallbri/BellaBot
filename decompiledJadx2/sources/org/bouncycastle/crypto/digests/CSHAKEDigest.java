package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Arrays;

/* loaded from: classes9.dex */
public class CSHAKEDigest extends SHAKEDigest {
    private static final byte[] padding = new byte[100];
    private final byte[] diff;

    public CSHAKEDigest(int i, byte[] bArr, byte[] bArr2) {
        super(i);
        if ((bArr == null || bArr.length == 0) && (bArr2 == null || bArr2.length == 0)) {
            this.diff = null;
        } else {
            this.diff = Arrays.concatenate(leftEncode(this.rate / 8), encodeString(bArr), encodeString(bArr2));
            diffPadAndAbsorb();
        }
    }

    private void diffPadAndAbsorb() {
        int i = this.rate / 8;
        byte[] bArr = this.diff;
        absorb(bArr, 0, bArr.length);
        int length = this.diff.length % i;
        while (true) {
            i -= length;
            byte[] bArr2 = padding;
            if (i <= bArr2.length) {
                absorb(bArr2, 0, i);
                return;
            } else {
                absorb(bArr2, 0, bArr2.length);
                length = padding.length;
            }
        }
    }

    private byte[] encodeString(byte[] bArr) {
        return (bArr == null || bArr.length == 0) ? leftEncode(0L) : Arrays.concatenate(leftEncode(bArr.length * 8), bArr);
    }

    private static byte[] leftEncode(long j) {
        long j2 = j;
        byte b = 1;
        while (true) {
            j2 >>= 8;
            if (j2 == 0) {
                break;
            }
            b = (byte) (b + 1);
        }
        byte[] bArr = new byte[b + 1];
        bArr[0] = b;
        for (int i = 1; i <= b; i++) {
            bArr[i] = (byte) (j >> ((b - i) * 8));
        }
        return bArr;
    }

    @Override // org.bouncycastle.crypto.digests.SHAKEDigest, org.bouncycastle.crypto.Xof
    public int doOutput(byte[] bArr, int i, int i2) {
        if (this.diff == null) {
            return super.doOutput(bArr, i, i2);
        }
        if (!this.squeezing) {
            absorbBits(0, 2);
        }
        squeeze(bArr, i, i2 * 8);
        return i2;
    }

    @Override // org.bouncycastle.crypto.digests.KeccakDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        if (this.diff != null) {
            diffPadAndAbsorb();
        }
    }
}
