package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* loaded from: classes9.dex */
public class SM3Digest extends GeneralDigest {
    private static final int BLOCK_SIZE = 16;
    private static final int DIGEST_LENGTH = 32;

    /* renamed from: T */
    private static final int[] f9290T = new int[64];

    /* renamed from: V */
    private int[] f9291V;

    /* renamed from: W */
    private int[] f9292W;
    private int[] inwords;
    private int xOff;

    static {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            f9290T[i2] = (2043430169 >>> (32 - i2)) | (2043430169 << i2);
            i2++;
        }
        for (i = 16; i < 64; i++) {
            int i3 = i % 32;
            f9290T[i] = (2055708042 >>> (32 - i3)) | (2055708042 << i3);
        }
    }

    public SM3Digest() {
        this.f9291V = new int[8];
        this.inwords = new int[16];
        this.f9292W = new int[68];
        reset();
    }

    public SM3Digest(SM3Digest sM3Digest) {
        super(sM3Digest);
        this.f9291V = new int[8];
        this.inwords = new int[16];
        this.f9292W = new int[68];
        copyIn(sM3Digest);
    }

    private int FF0(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int FF1(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    private int GG0(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int GG1(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: P0 */
    private int m4065P0(int i) {
        return (i ^ ((i << 9) | (i >>> 23))) ^ ((i << 17) | (i >>> 15));
    }

    /* renamed from: P1 */
    private int m4066P1(int i) {
        return (i ^ ((i << 15) | (i >>> 17))) ^ ((i << 23) | (i >>> 9));
    }

    private void copyIn(SM3Digest sM3Digest) {
        int[] iArr = sM3Digest.f9291V;
        int[] iArr2 = this.f9291V;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = sM3Digest.inwords;
        int[] iArr4 = this.inwords;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        this.xOff = sM3Digest.xOff;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SM3Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.f9291V, bArr, i);
        reset();
        return 32;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SM3";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            this.f9292W[i2] = this.inwords[i2];
            i2++;
        }
        for (int i3 = 16; i3 < 68; i3++) {
            int[] iArr = this.f9292W;
            int i4 = iArr[i3 - 3];
            int i5 = iArr[i3 - 13];
            iArr[i3] = (m4066P1(((i4 >>> 17) | (i4 << 15)) ^ (iArr[i3 - 16] ^ iArr[i3 - 9])) ^ ((i5 >>> 25) | (i5 << 7))) ^ this.f9292W[i3 - 6];
        }
        int[] iArr2 = this.f9291V;
        int i6 = iArr2[0];
        int i7 = iArr2[1];
        int i8 = iArr2[2];
        int i9 = iArr2[3];
        int i10 = iArr2[4];
        int i11 = iArr2[5];
        int i12 = iArr2[6];
        int i13 = iArr2[7];
        int i14 = i12;
        int i15 = 0;
        int i16 = i6;
        int i17 = i7;
        int i18 = i8;
        int i19 = i9;
        int i20 = i10;
        int i21 = i11;
        for (i = 16; i15 < i; i = 16) {
            int i22 = (i16 << 12) | (i16 >>> 20);
            int i23 = i22 + i20 + f9290T[i15];
            int i24 = (i23 << 7) | (i23 >>> 25);
            int[] iArr3 = this.f9292W;
            int i25 = iArr3[i15];
            int i26 = i25 ^ iArr3[i15 + 4];
            int FF0 = FF0(i16, i17, i18) + i19;
            int GG0 = GG0(i20, i21, i14) + i13 + i24 + i25;
            i15++;
            i13 = i14;
            i14 = (i21 << 19) | (i21 >>> 13);
            i21 = i20;
            i20 = m4065P0(GG0);
            i19 = i18;
            i18 = (i17 << 9) | (i17 >>> 23);
            i17 = i16;
            i16 = FF0 + (i24 ^ i22) + i26;
        }
        int i27 = i17;
        int i28 = i16;
        int i29 = i19;
        int i30 = i18;
        int i31 = i21;
        int i32 = i20;
        int i33 = 16;
        while (i33 < 64) {
            int i34 = (i28 << 12) | (i28 >>> 20);
            int i35 = i34 + i32 + f9290T[i33];
            int i36 = (i35 << 7) | (i35 >>> 25);
            int[] iArr4 = this.f9292W;
            int i37 = iArr4[i33];
            int FF1 = FF1(i28, i27, i30) + i29 + (i36 ^ i34) + (i37 ^ iArr4[i33 + 4]);
            int GG1 = GG1(i32, i31, i14) + i13 + i36 + i37;
            i33++;
            i13 = i14;
            i14 = (i31 >>> 13) | (i31 << 19);
            i31 = i32;
            i32 = m4065P0(GG1);
            int i38 = i30;
            i30 = (i27 >>> 23) | (i27 << 9);
            i27 = i28;
            i28 = FF1;
            i29 = i38;
        }
        int[] iArr5 = this.f9291V;
        iArr5[0] = i28 ^ iArr5[0];
        iArr5[1] = i27 ^ iArr5[1];
        iArr5[2] = iArr5[2] ^ i30;
        iArr5[3] = iArr5[3] ^ i29;
        iArr5[4] = iArr5[4] ^ i32;
        iArr5[5] = iArr5[5] ^ i31;
        iArr5[6] = iArr5[6] ^ i14;
        iArr5[7] = iArr5[7] ^ i13;
        this.xOff = 0;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        int i = this.xOff;
        if (i > 14) {
            this.inwords[i] = 0;
            this.xOff = i + 1;
            processBlock();
        }
        while (true) {
            int i2 = this.xOff;
            if (i2 >= 14) {
                int[] iArr = this.inwords;
                this.xOff = i2 + 1;
                iArr[i2] = (int) (j >>> 32);
                int i3 = this.xOff;
                this.xOff = i3 + 1;
                iArr[i3] = (int) j;
                return;
            }
            this.inwords[i2] = 0;
            this.xOff = i2 + 1;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int i2 = (bArr[i] & 255) << 24;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & 255) << 16);
        int i5 = i3 + 1;
        int i6 = (bArr[i5 + 1] & 255) | i4 | ((bArr[i5] & 255) << 8);
        int[] iArr = this.inwords;
        int i7 = this.xOff;
        iArr[i7] = i6;
        this.xOff = i7 + 1;
        if (this.xOff >= 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        int[] iArr = this.f9291V;
        iArr[0] = 1937774191;
        iArr[1] = 1226093241;
        iArr[2] = 388252375;
        iArr[3] = -628488704;
        iArr[4] = -1452330820;
        iArr[5] = 372324522;
        iArr[6] = -477237683;
        iArr[7] = -1325724082;
        this.xOff = 0;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        SM3Digest sM3Digest = (SM3Digest) memoable;
        super.copyIn((GeneralDigest) sM3Digest);
        copyIn(sM3Digest);
    }
}
