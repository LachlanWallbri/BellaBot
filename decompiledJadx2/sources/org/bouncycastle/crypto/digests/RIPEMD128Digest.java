package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;

/* loaded from: classes9.dex */
public class RIPEMD128Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 16;

    /* renamed from: H0 */
    private int f9229H0;

    /* renamed from: H1 */
    private int f9230H1;

    /* renamed from: H2 */
    private int f9231H2;

    /* renamed from: H3 */
    private int f9232H3;

    /* renamed from: X */
    private int[] f9233X;
    private int xOff;

    public RIPEMD128Digest() {
        this.f9233X = new int[16];
        reset();
    }

    public RIPEMD128Digest(RIPEMD128Digest rIPEMD128Digest) {
        super(rIPEMD128Digest);
        this.f9233X = new int[16];
        copyIn(rIPEMD128Digest);
    }

    /* renamed from: F1 */
    private int m4030F1(int i, int i2, int i3, int i4, int i5, int i6) {
        return m4034RL(i + m4035f1(i2, i3, i4) + i5, i6);
    }

    /* renamed from: F2 */
    private int m4031F2(int i, int i2, int i3, int i4, int i5, int i6) {
        return m4034RL(i + m4036f2(i2, i3, i4) + i5 + 1518500249, i6);
    }

    /* renamed from: F3 */
    private int m4032F3(int i, int i2, int i3, int i4, int i5, int i6) {
        return m4034RL(i + m4037f3(i2, i3, i4) + i5 + 1859775393, i6);
    }

    /* renamed from: F4 */
    private int m4033F4(int i, int i2, int i3, int i4, int i5, int i6) {
        return m4034RL(((i + m4038f4(i2, i3, i4)) + i5) - 1894007588, i6);
    }

    private int FF1(int i, int i2, int i3, int i4, int i5, int i6) {
        return m4034RL(i + m4035f1(i2, i3, i4) + i5, i6);
    }

    private int FF2(int i, int i2, int i3, int i4, int i5, int i6) {
        return m4034RL(i + m4036f2(i2, i3, i4) + i5 + 1836072691, i6);
    }

    private int FF3(int i, int i2, int i3, int i4, int i5, int i6) {
        return m4034RL(i + m4037f3(i2, i3, i4) + i5 + 1548603684, i6);
    }

    private int FF4(int i, int i2, int i3, int i4, int i5, int i6) {
        return m4034RL(i + m4038f4(i2, i3, i4) + i5 + 1352829926, i6);
    }

    /* renamed from: RL */
    private int m4034RL(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    private void copyIn(RIPEMD128Digest rIPEMD128Digest) {
        super.copyIn((GeneralDigest) rIPEMD128Digest);
        this.f9229H0 = rIPEMD128Digest.f9229H0;
        this.f9230H1 = rIPEMD128Digest.f9230H1;
        this.f9231H2 = rIPEMD128Digest.f9231H2;
        this.f9232H3 = rIPEMD128Digest.f9232H3;
        int[] iArr = rIPEMD128Digest.f9233X;
        System.arraycopy(iArr, 0, this.f9233X, 0, iArr.length);
        this.xOff = rIPEMD128Digest.xOff;
    }

    /* renamed from: f1 */
    private int m4035f1(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: f2 */
    private int m4036f2(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: f3 */
    private int m4037f3(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    /* renamed from: f4 */
    private int m4038f4(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    private void unpackWord(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new RIPEMD128Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        unpackWord(this.f9229H0, bArr, i);
        unpackWord(this.f9230H1, bArr, i + 4);
        unpackWord(this.f9231H2, bArr, i + 8);
        unpackWord(this.f9232H3, bArr, i + 12);
        reset();
        return 16;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD128";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int i = this.f9229H0;
        int i2 = this.f9230H1;
        int i3 = this.f9231H2;
        int i4 = this.f9232H3;
        int m4030F1 = m4030F1(i, i2, i3, i4, this.f9233X[0], 11);
        int m4030F12 = m4030F1(i4, m4030F1, i2, i3, this.f9233X[1], 14);
        int m4030F13 = m4030F1(i3, m4030F12, m4030F1, i2, this.f9233X[2], 15);
        int m4030F14 = m4030F1(i2, m4030F13, m4030F12, m4030F1, this.f9233X[3], 12);
        int m4030F15 = m4030F1(m4030F1, m4030F14, m4030F13, m4030F12, this.f9233X[4], 5);
        int m4030F16 = m4030F1(m4030F12, m4030F15, m4030F14, m4030F13, this.f9233X[5], 8);
        int m4030F17 = m4030F1(m4030F13, m4030F16, m4030F15, m4030F14, this.f9233X[6], 7);
        int m4030F18 = m4030F1(m4030F14, m4030F17, m4030F16, m4030F15, this.f9233X[7], 9);
        int m4030F19 = m4030F1(m4030F15, m4030F18, m4030F17, m4030F16, this.f9233X[8], 11);
        int m4030F110 = m4030F1(m4030F16, m4030F19, m4030F18, m4030F17, this.f9233X[9], 13);
        int m4030F111 = m4030F1(m4030F17, m4030F110, m4030F19, m4030F18, this.f9233X[10], 14);
        int m4030F112 = m4030F1(m4030F18, m4030F111, m4030F110, m4030F19, this.f9233X[11], 15);
        int m4030F113 = m4030F1(m4030F19, m4030F112, m4030F111, m4030F110, this.f9233X[12], 6);
        int m4030F114 = m4030F1(m4030F110, m4030F113, m4030F112, m4030F111, this.f9233X[13], 7);
        int m4030F115 = m4030F1(m4030F111, m4030F114, m4030F113, m4030F112, this.f9233X[14], 9);
        int m4030F116 = m4030F1(m4030F112, m4030F115, m4030F114, m4030F113, this.f9233X[15], 8);
        int m4031F2 = m4031F2(m4030F113, m4030F116, m4030F115, m4030F114, this.f9233X[7], 7);
        int m4031F22 = m4031F2(m4030F114, m4031F2, m4030F116, m4030F115, this.f9233X[4], 6);
        int m4031F23 = m4031F2(m4030F115, m4031F22, m4031F2, m4030F116, this.f9233X[13], 8);
        int m4031F24 = m4031F2(m4030F116, m4031F23, m4031F22, m4031F2, this.f9233X[1], 13);
        int m4031F25 = m4031F2(m4031F2, m4031F24, m4031F23, m4031F22, this.f9233X[10], 11);
        int m4031F26 = m4031F2(m4031F22, m4031F25, m4031F24, m4031F23, this.f9233X[6], 9);
        int m4031F27 = m4031F2(m4031F23, m4031F26, m4031F25, m4031F24, this.f9233X[15], 7);
        int m4031F28 = m4031F2(m4031F24, m4031F27, m4031F26, m4031F25, this.f9233X[3], 15);
        int m4031F29 = m4031F2(m4031F25, m4031F28, m4031F27, m4031F26, this.f9233X[12], 7);
        int m4031F210 = m4031F2(m4031F26, m4031F29, m4031F28, m4031F27, this.f9233X[0], 12);
        int m4031F211 = m4031F2(m4031F27, m4031F210, m4031F29, m4031F28, this.f9233X[9], 15);
        int m4031F212 = m4031F2(m4031F28, m4031F211, m4031F210, m4031F29, this.f9233X[5], 9);
        int m4031F213 = m4031F2(m4031F29, m4031F212, m4031F211, m4031F210, this.f9233X[2], 11);
        int m4031F214 = m4031F2(m4031F210, m4031F213, m4031F212, m4031F211, this.f9233X[14], 7);
        int m4031F215 = m4031F2(m4031F211, m4031F214, m4031F213, m4031F212, this.f9233X[11], 13);
        int m4031F216 = m4031F2(m4031F212, m4031F215, m4031F214, m4031F213, this.f9233X[8], 12);
        int m4032F3 = m4032F3(m4031F213, m4031F216, m4031F215, m4031F214, this.f9233X[3], 11);
        int m4032F32 = m4032F3(m4031F214, m4032F3, m4031F216, m4031F215, this.f9233X[10], 13);
        int m4032F33 = m4032F3(m4031F215, m4032F32, m4032F3, m4031F216, this.f9233X[14], 6);
        int m4032F34 = m4032F3(m4031F216, m4032F33, m4032F32, m4032F3, this.f9233X[4], 7);
        int m4032F35 = m4032F3(m4032F3, m4032F34, m4032F33, m4032F32, this.f9233X[9], 14);
        int m4032F36 = m4032F3(m4032F32, m4032F35, m4032F34, m4032F33, this.f9233X[15], 9);
        int m4032F37 = m4032F3(m4032F33, m4032F36, m4032F35, m4032F34, this.f9233X[8], 13);
        int m4032F38 = m4032F3(m4032F34, m4032F37, m4032F36, m4032F35, this.f9233X[1], 15);
        int m4032F39 = m4032F3(m4032F35, m4032F38, m4032F37, m4032F36, this.f9233X[2], 14);
        int m4032F310 = m4032F3(m4032F36, m4032F39, m4032F38, m4032F37, this.f9233X[7], 8);
        int m4032F311 = m4032F3(m4032F37, m4032F310, m4032F39, m4032F38, this.f9233X[0], 13);
        int m4032F312 = m4032F3(m4032F38, m4032F311, m4032F310, m4032F39, this.f9233X[6], 6);
        int m4032F313 = m4032F3(m4032F39, m4032F312, m4032F311, m4032F310, this.f9233X[13], 5);
        int m4032F314 = m4032F3(m4032F310, m4032F313, m4032F312, m4032F311, this.f9233X[11], 12);
        int m4032F315 = m4032F3(m4032F311, m4032F314, m4032F313, m4032F312, this.f9233X[5], 7);
        int m4032F316 = m4032F3(m4032F312, m4032F315, m4032F314, m4032F313, this.f9233X[12], 5);
        int m4033F4 = m4033F4(m4032F313, m4032F316, m4032F315, m4032F314, this.f9233X[1], 11);
        int m4033F42 = m4033F4(m4032F314, m4033F4, m4032F316, m4032F315, this.f9233X[9], 12);
        int m4033F43 = m4033F4(m4032F315, m4033F42, m4033F4, m4032F316, this.f9233X[11], 14);
        int m4033F44 = m4033F4(m4032F316, m4033F43, m4033F42, m4033F4, this.f9233X[10], 15);
        int m4033F45 = m4033F4(m4033F4, m4033F44, m4033F43, m4033F42, this.f9233X[0], 14);
        int m4033F46 = m4033F4(m4033F42, m4033F45, m4033F44, m4033F43, this.f9233X[8], 15);
        int m4033F47 = m4033F4(m4033F43, m4033F46, m4033F45, m4033F44, this.f9233X[12], 9);
        int m4033F48 = m4033F4(m4033F44, m4033F47, m4033F46, m4033F45, this.f9233X[4], 8);
        int m4033F49 = m4033F4(m4033F45, m4033F48, m4033F47, m4033F46, this.f9233X[13], 9);
        int m4033F410 = m4033F4(m4033F46, m4033F49, m4033F48, m4033F47, this.f9233X[3], 14);
        int m4033F411 = m4033F4(m4033F47, m4033F410, m4033F49, m4033F48, this.f9233X[7], 5);
        int m4033F412 = m4033F4(m4033F48, m4033F411, m4033F410, m4033F49, this.f9233X[15], 6);
        int m4033F413 = m4033F4(m4033F49, m4033F412, m4033F411, m4033F410, this.f9233X[14], 8);
        int m4033F414 = m4033F4(m4033F410, m4033F413, m4033F412, m4033F411, this.f9233X[5], 6);
        int m4033F415 = m4033F4(m4033F411, m4033F414, m4033F413, m4033F412, this.f9233X[6], 5);
        int m4033F416 = m4033F4(m4033F412, m4033F415, m4033F414, m4033F413, this.f9233X[2], 12);
        int FF4 = FF4(i, i2, i3, i4, this.f9233X[5], 8);
        int FF42 = FF4(i4, FF4, i2, i3, this.f9233X[14], 9);
        int FF43 = FF4(i3, FF42, FF4, i2, this.f9233X[7], 9);
        int FF44 = FF4(i2, FF43, FF42, FF4, this.f9233X[0], 11);
        int FF45 = FF4(FF4, FF44, FF43, FF42, this.f9233X[9], 13);
        int FF46 = FF4(FF42, FF45, FF44, FF43, this.f9233X[2], 15);
        int FF47 = FF4(FF43, FF46, FF45, FF44, this.f9233X[11], 15);
        int FF48 = FF4(FF44, FF47, FF46, FF45, this.f9233X[4], 5);
        int FF49 = FF4(FF45, FF48, FF47, FF46, this.f9233X[13], 7);
        int FF410 = FF4(FF46, FF49, FF48, FF47, this.f9233X[6], 7);
        int FF411 = FF4(FF47, FF410, FF49, FF48, this.f9233X[15], 8);
        int FF412 = FF4(FF48, FF411, FF410, FF49, this.f9233X[8], 11);
        int FF413 = FF4(FF49, FF412, FF411, FF410, this.f9233X[1], 14);
        int FF414 = FF4(FF410, FF413, FF412, FF411, this.f9233X[10], 14);
        int FF415 = FF4(FF411, FF414, FF413, FF412, this.f9233X[3], 12);
        int FF416 = FF4(FF412, FF415, FF414, FF413, this.f9233X[12], 6);
        int FF3 = FF3(FF413, FF416, FF415, FF414, this.f9233X[6], 9);
        int FF32 = FF3(FF414, FF3, FF416, FF415, this.f9233X[11], 13);
        int FF33 = FF3(FF415, FF32, FF3, FF416, this.f9233X[3], 15);
        int FF34 = FF3(FF416, FF33, FF32, FF3, this.f9233X[7], 7);
        int FF35 = FF3(FF3, FF34, FF33, FF32, this.f9233X[0], 12);
        int FF36 = FF3(FF32, FF35, FF34, FF33, this.f9233X[13], 8);
        int FF37 = FF3(FF33, FF36, FF35, FF34, this.f9233X[5], 9);
        int FF38 = FF3(FF34, FF37, FF36, FF35, this.f9233X[10], 11);
        int FF39 = FF3(FF35, FF38, FF37, FF36, this.f9233X[14], 7);
        int FF310 = FF3(FF36, FF39, FF38, FF37, this.f9233X[15], 7);
        int FF311 = FF3(FF37, FF310, FF39, FF38, this.f9233X[8], 12);
        int FF312 = FF3(FF38, FF311, FF310, FF39, this.f9233X[12], 7);
        int FF313 = FF3(FF39, FF312, FF311, FF310, this.f9233X[4], 6);
        int FF314 = FF3(FF310, FF313, FF312, FF311, this.f9233X[9], 15);
        int FF315 = FF3(FF311, FF314, FF313, FF312, this.f9233X[1], 13);
        int FF316 = FF3(FF312, FF315, FF314, FF313, this.f9233X[2], 11);
        int FF2 = FF2(FF313, FF316, FF315, FF314, this.f9233X[15], 9);
        int FF22 = FF2(FF314, FF2, FF316, FF315, this.f9233X[5], 7);
        int FF23 = FF2(FF315, FF22, FF2, FF316, this.f9233X[1], 15);
        int FF24 = FF2(FF316, FF23, FF22, FF2, this.f9233X[3], 11);
        int FF25 = FF2(FF2, FF24, FF23, FF22, this.f9233X[7], 8);
        int FF26 = FF2(FF22, FF25, FF24, FF23, this.f9233X[14], 6);
        int FF27 = FF2(FF23, FF26, FF25, FF24, this.f9233X[6], 6);
        int FF28 = FF2(FF24, FF27, FF26, FF25, this.f9233X[9], 14);
        int FF29 = FF2(FF25, FF28, FF27, FF26, this.f9233X[11], 12);
        int FF210 = FF2(FF26, FF29, FF28, FF27, this.f9233X[8], 13);
        int FF211 = FF2(FF27, FF210, FF29, FF28, this.f9233X[12], 5);
        int FF212 = FF2(FF28, FF211, FF210, FF29, this.f9233X[2], 14);
        int FF213 = FF2(FF29, FF212, FF211, FF210, this.f9233X[10], 13);
        int FF214 = FF2(FF210, FF213, FF212, FF211, this.f9233X[0], 13);
        int FF215 = FF2(FF211, FF214, FF213, FF212, this.f9233X[4], 7);
        int FF216 = FF2(FF212, FF215, FF214, FF213, this.f9233X[13], 5);
        int FF1 = FF1(FF213, FF216, FF215, FF214, this.f9233X[8], 15);
        int FF12 = FF1(FF214, FF1, FF216, FF215, this.f9233X[6], 5);
        int FF13 = FF1(FF215, FF12, FF1, FF216, this.f9233X[4], 8);
        int FF14 = FF1(FF216, FF13, FF12, FF1, this.f9233X[1], 11);
        int FF15 = FF1(FF1, FF14, FF13, FF12, this.f9233X[3], 14);
        int FF16 = FF1(FF12, FF15, FF14, FF13, this.f9233X[11], 14);
        int FF17 = FF1(FF13, FF16, FF15, FF14, this.f9233X[15], 6);
        int FF18 = FF1(FF14, FF17, FF16, FF15, this.f9233X[0], 14);
        int FF19 = FF1(FF15, FF18, FF17, FF16, this.f9233X[5], 6);
        int FF110 = FF1(FF16, FF19, FF18, FF17, this.f9233X[12], 9);
        int FF111 = FF1(FF17, FF110, FF19, FF18, this.f9233X[2], 12);
        int FF112 = FF1(FF18, FF111, FF110, FF19, this.f9233X[13], 9);
        int FF113 = FF1(FF19, FF112, FF111, FF110, this.f9233X[9], 12);
        int FF114 = FF1(FF110, FF113, FF112, FF111, this.f9233X[7], 5);
        int FF115 = FF1(FF111, FF114, FF113, FF112, this.f9233X[10], 15);
        int FF116 = FF1(FF112, FF115, FF114, FF113, this.f9233X[14], 8);
        int i5 = FF114 + m4033F415 + this.f9230H1;
        this.f9230H1 = this.f9231H2 + m4033F414 + FF113;
        this.f9231H2 = this.f9232H3 + m4033F413 + FF116;
        this.f9232H3 = this.f9229H0 + m4033F416 + FF115;
        this.f9229H0 = i5;
        this.xOff = 0;
        int i6 = 0;
        while (true) {
            int[] iArr = this.f9233X;
            if (i6 == iArr.length) {
                return;
            }
            iArr[i6] = 0;
            i6++;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f9233X;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int[] iArr = this.f9233X;
        int i2 = this.xOff;
        this.xOff = i2 + 1;
        iArr[i2] = ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        if (this.xOff == 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f9229H0 = 1732584193;
        this.f9230H1 = -271733879;
        this.f9231H2 = -1732584194;
        this.f9232H3 = 271733878;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f9233X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((RIPEMD128Digest) memoable);
    }
}
