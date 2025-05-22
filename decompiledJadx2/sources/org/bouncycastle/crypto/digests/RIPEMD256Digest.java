package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;

/* loaded from: classes9.dex */
public class RIPEMD256Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 32;

    /* renamed from: H0 */
    private int f9240H0;

    /* renamed from: H1 */
    private int f9241H1;

    /* renamed from: H2 */
    private int f9242H2;

    /* renamed from: H3 */
    private int f9243H3;

    /* renamed from: H4 */
    private int f9244H4;

    /* renamed from: H5 */
    private int f9245H5;

    /* renamed from: H6 */
    private int f9246H6;

    /* renamed from: H7 */
    private int f9247H7;

    /* renamed from: X */
    private int[] f9248X;
    private int xOff;

    public RIPEMD256Digest() {
        this.f9248X = new int[16];
        reset();
    }

    public RIPEMD256Digest(RIPEMD256Digest rIPEMD256Digest) {
        super(rIPEMD256Digest);
        this.f9248X = new int[16];
        copyIn(rIPEMD256Digest);
    }

    /* renamed from: F1 */
    private int m4045F1(int i, int i2, int i3, int i4, int i5, int i6) {
        return m4049RL(i + m4050f1(i2, i3, i4) + i5, i6);
    }

    /* renamed from: F2 */
    private int m4046F2(int i, int i2, int i3, int i4, int i5, int i6) {
        return m4049RL(i + m4051f2(i2, i3, i4) + i5 + 1518500249, i6);
    }

    /* renamed from: F3 */
    private int m4047F3(int i, int i2, int i3, int i4, int i5, int i6) {
        return m4049RL(i + m4052f3(i2, i3, i4) + i5 + 1859775393, i6);
    }

    /* renamed from: F4 */
    private int m4048F4(int i, int i2, int i3, int i4, int i5, int i6) {
        return m4049RL(((i + m4053f4(i2, i3, i4)) + i5) - 1894007588, i6);
    }

    private int FF1(int i, int i2, int i3, int i4, int i5, int i6) {
        return m4049RL(i + m4050f1(i2, i3, i4) + i5, i6);
    }

    private int FF2(int i, int i2, int i3, int i4, int i5, int i6) {
        return m4049RL(i + m4051f2(i2, i3, i4) + i5 + 1836072691, i6);
    }

    private int FF3(int i, int i2, int i3, int i4, int i5, int i6) {
        return m4049RL(i + m4052f3(i2, i3, i4) + i5 + 1548603684, i6);
    }

    private int FF4(int i, int i2, int i3, int i4, int i5, int i6) {
        return m4049RL(i + m4053f4(i2, i3, i4) + i5 + 1352829926, i6);
    }

    /* renamed from: RL */
    private int m4049RL(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    private void copyIn(RIPEMD256Digest rIPEMD256Digest) {
        super.copyIn((GeneralDigest) rIPEMD256Digest);
        this.f9240H0 = rIPEMD256Digest.f9240H0;
        this.f9241H1 = rIPEMD256Digest.f9241H1;
        this.f9242H2 = rIPEMD256Digest.f9242H2;
        this.f9243H3 = rIPEMD256Digest.f9243H3;
        this.f9244H4 = rIPEMD256Digest.f9244H4;
        this.f9245H5 = rIPEMD256Digest.f9245H5;
        this.f9246H6 = rIPEMD256Digest.f9246H6;
        this.f9247H7 = rIPEMD256Digest.f9247H7;
        int[] iArr = rIPEMD256Digest.f9248X;
        System.arraycopy(iArr, 0, this.f9248X, 0, iArr.length);
        this.xOff = rIPEMD256Digest.xOff;
    }

    /* renamed from: f1 */
    private int m4050f1(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: f2 */
    private int m4051f2(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: f3 */
    private int m4052f3(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    /* renamed from: f4 */
    private int m4053f4(int i, int i2, int i3) {
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
        return new RIPEMD256Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        unpackWord(this.f9240H0, bArr, i);
        unpackWord(this.f9241H1, bArr, i + 4);
        unpackWord(this.f9242H2, bArr, i + 8);
        unpackWord(this.f9243H3, bArr, i + 12);
        unpackWord(this.f9244H4, bArr, i + 16);
        unpackWord(this.f9245H5, bArr, i + 20);
        unpackWord(this.f9246H6, bArr, i + 24);
        unpackWord(this.f9247H7, bArr, i + 28);
        reset();
        return 32;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD256";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int i = this.f9240H0;
        int i2 = this.f9241H1;
        int i3 = this.f9242H2;
        int i4 = this.f9243H3;
        int i5 = this.f9244H4;
        int i6 = this.f9245H5;
        int i7 = this.f9246H6;
        int i8 = this.f9247H7;
        int m4045F1 = m4045F1(i, i2, i3, i4, this.f9248X[0], 11);
        int m4045F12 = m4045F1(i4, m4045F1, i2, i3, this.f9248X[1], 14);
        int m4045F13 = m4045F1(i3, m4045F12, m4045F1, i2, this.f9248X[2], 15);
        int m4045F14 = m4045F1(i2, m4045F13, m4045F12, m4045F1, this.f9248X[3], 12);
        int m4045F15 = m4045F1(m4045F1, m4045F14, m4045F13, m4045F12, this.f9248X[4], 5);
        int m4045F16 = m4045F1(m4045F12, m4045F15, m4045F14, m4045F13, this.f9248X[5], 8);
        int m4045F17 = m4045F1(m4045F13, m4045F16, m4045F15, m4045F14, this.f9248X[6], 7);
        int m4045F18 = m4045F1(m4045F14, m4045F17, m4045F16, m4045F15, this.f9248X[7], 9);
        int m4045F19 = m4045F1(m4045F15, m4045F18, m4045F17, m4045F16, this.f9248X[8], 11);
        int m4045F110 = m4045F1(m4045F16, m4045F19, m4045F18, m4045F17, this.f9248X[9], 13);
        int m4045F111 = m4045F1(m4045F17, m4045F110, m4045F19, m4045F18, this.f9248X[10], 14);
        int m4045F112 = m4045F1(m4045F18, m4045F111, m4045F110, m4045F19, this.f9248X[11], 15);
        int m4045F113 = m4045F1(m4045F19, m4045F112, m4045F111, m4045F110, this.f9248X[12], 6);
        int m4045F114 = m4045F1(m4045F110, m4045F113, m4045F112, m4045F111, this.f9248X[13], 7);
        int m4045F115 = m4045F1(m4045F111, m4045F114, m4045F113, m4045F112, this.f9248X[14], 9);
        int m4045F116 = m4045F1(m4045F112, m4045F115, m4045F114, m4045F113, this.f9248X[15], 8);
        int FF4 = FF4(i5, i6, i7, i8, this.f9248X[5], 8);
        int FF42 = FF4(i8, FF4, i6, i7, this.f9248X[14], 9);
        int FF43 = FF4(i7, FF42, FF4, i6, this.f9248X[7], 9);
        int FF44 = FF4(i6, FF43, FF42, FF4, this.f9248X[0], 11);
        int FF45 = FF4(FF4, FF44, FF43, FF42, this.f9248X[9], 13);
        int FF46 = FF4(FF42, FF45, FF44, FF43, this.f9248X[2], 15);
        int FF47 = FF4(FF43, FF46, FF45, FF44, this.f9248X[11], 15);
        int FF48 = FF4(FF44, FF47, FF46, FF45, this.f9248X[4], 5);
        int FF49 = FF4(FF45, FF48, FF47, FF46, this.f9248X[13], 7);
        int FF410 = FF4(FF46, FF49, FF48, FF47, this.f9248X[6], 7);
        int FF411 = FF4(FF47, FF410, FF49, FF48, this.f9248X[15], 8);
        int FF412 = FF4(FF48, FF411, FF410, FF49, this.f9248X[8], 11);
        int FF413 = FF4(FF49, FF412, FF411, FF410, this.f9248X[1], 14);
        int FF414 = FF4(FF410, FF413, FF412, FF411, this.f9248X[10], 14);
        int FF415 = FF4(FF411, FF414, FF413, FF412, this.f9248X[3], 12);
        int FF416 = FF4(FF412, FF415, FF414, FF413, this.f9248X[12], 6);
        int m4046F2 = m4046F2(FF413, m4045F116, m4045F115, m4045F114, this.f9248X[7], 7);
        int m4046F22 = m4046F2(m4045F114, m4046F2, m4045F116, m4045F115, this.f9248X[4], 6);
        int m4046F23 = m4046F2(m4045F115, m4046F22, m4046F2, m4045F116, this.f9248X[13], 8);
        int m4046F24 = m4046F2(m4045F116, m4046F23, m4046F22, m4046F2, this.f9248X[1], 13);
        int m4046F25 = m4046F2(m4046F2, m4046F24, m4046F23, m4046F22, this.f9248X[10], 11);
        int m4046F26 = m4046F2(m4046F22, m4046F25, m4046F24, m4046F23, this.f9248X[6], 9);
        int m4046F27 = m4046F2(m4046F23, m4046F26, m4046F25, m4046F24, this.f9248X[15], 7);
        int m4046F28 = m4046F2(m4046F24, m4046F27, m4046F26, m4046F25, this.f9248X[3], 15);
        int m4046F29 = m4046F2(m4046F25, m4046F28, m4046F27, m4046F26, this.f9248X[12], 7);
        int m4046F210 = m4046F2(m4046F26, m4046F29, m4046F28, m4046F27, this.f9248X[0], 12);
        int m4046F211 = m4046F2(m4046F27, m4046F210, m4046F29, m4046F28, this.f9248X[9], 15);
        int m4046F212 = m4046F2(m4046F28, m4046F211, m4046F210, m4046F29, this.f9248X[5], 9);
        int m4046F213 = m4046F2(m4046F29, m4046F212, m4046F211, m4046F210, this.f9248X[2], 11);
        int m4046F214 = m4046F2(m4046F210, m4046F213, m4046F212, m4046F211, this.f9248X[14], 7);
        int m4046F215 = m4046F2(m4046F211, m4046F214, m4046F213, m4046F212, this.f9248X[11], 13);
        int m4046F216 = m4046F2(m4046F212, m4046F215, m4046F214, m4046F213, this.f9248X[8], 12);
        int FF3 = FF3(m4045F113, FF416, FF415, FF414, this.f9248X[6], 9);
        int FF32 = FF3(FF414, FF3, FF416, FF415, this.f9248X[11], 13);
        int FF33 = FF3(FF415, FF32, FF3, FF416, this.f9248X[3], 15);
        int FF34 = FF3(FF416, FF33, FF32, FF3, this.f9248X[7], 7);
        int FF35 = FF3(FF3, FF34, FF33, FF32, this.f9248X[0], 12);
        int FF36 = FF3(FF32, FF35, FF34, FF33, this.f9248X[13], 8);
        int FF37 = FF3(FF33, FF36, FF35, FF34, this.f9248X[5], 9);
        int FF38 = FF3(FF34, FF37, FF36, FF35, this.f9248X[10], 11);
        int FF39 = FF3(FF35, FF38, FF37, FF36, this.f9248X[14], 7);
        int FF310 = FF3(FF36, FF39, FF38, FF37, this.f9248X[15], 7);
        int FF311 = FF3(FF37, FF310, FF39, FF38, this.f9248X[8], 12);
        int FF312 = FF3(FF38, FF311, FF310, FF39, this.f9248X[12], 7);
        int FF313 = FF3(FF39, FF312, FF311, FF310, this.f9248X[4], 6);
        int FF314 = FF3(FF310, FF313, FF312, FF311, this.f9248X[9], 15);
        int FF315 = FF3(FF311, FF314, FF313, FF312, this.f9248X[1], 13);
        int FF316 = FF3(FF312, FF315, FF314, FF313, this.f9248X[2], 11);
        int m4047F3 = m4047F3(m4046F213, FF316, m4046F215, m4046F214, this.f9248X[3], 11);
        int m4047F32 = m4047F3(m4046F214, m4047F3, FF316, m4046F215, this.f9248X[10], 13);
        int m4047F33 = m4047F3(m4046F215, m4047F32, m4047F3, FF316, this.f9248X[14], 6);
        int m4047F34 = m4047F3(FF316, m4047F33, m4047F32, m4047F3, this.f9248X[4], 7);
        int m4047F35 = m4047F3(m4047F3, m4047F34, m4047F33, m4047F32, this.f9248X[9], 14);
        int m4047F36 = m4047F3(m4047F32, m4047F35, m4047F34, m4047F33, this.f9248X[15], 9);
        int m4047F37 = m4047F3(m4047F33, m4047F36, m4047F35, m4047F34, this.f9248X[8], 13);
        int m4047F38 = m4047F3(m4047F34, m4047F37, m4047F36, m4047F35, this.f9248X[1], 15);
        int m4047F39 = m4047F3(m4047F35, m4047F38, m4047F37, m4047F36, this.f9248X[2], 14);
        int m4047F310 = m4047F3(m4047F36, m4047F39, m4047F38, m4047F37, this.f9248X[7], 8);
        int m4047F311 = m4047F3(m4047F37, m4047F310, m4047F39, m4047F38, this.f9248X[0], 13);
        int m4047F312 = m4047F3(m4047F38, m4047F311, m4047F310, m4047F39, this.f9248X[6], 6);
        int m4047F313 = m4047F3(m4047F39, m4047F312, m4047F311, m4047F310, this.f9248X[13], 5);
        int m4047F314 = m4047F3(m4047F310, m4047F313, m4047F312, m4047F311, this.f9248X[11], 12);
        int m4047F315 = m4047F3(m4047F311, m4047F314, m4047F313, m4047F312, this.f9248X[5], 7);
        int m4047F316 = m4047F3(m4047F312, m4047F315, m4047F314, m4047F313, this.f9248X[12], 5);
        int FF2 = FF2(FF313, m4046F216, FF315, FF314, this.f9248X[15], 9);
        int FF22 = FF2(FF314, FF2, m4046F216, FF315, this.f9248X[5], 7);
        int FF23 = FF2(FF315, FF22, FF2, m4046F216, this.f9248X[1], 15);
        int FF24 = FF2(m4046F216, FF23, FF22, FF2, this.f9248X[3], 11);
        int FF25 = FF2(FF2, FF24, FF23, FF22, this.f9248X[7], 8);
        int FF26 = FF2(FF22, FF25, FF24, FF23, this.f9248X[14], 6);
        int FF27 = FF2(FF23, FF26, FF25, FF24, this.f9248X[6], 6);
        int FF28 = FF2(FF24, FF27, FF26, FF25, this.f9248X[9], 14);
        int FF29 = FF2(FF25, FF28, FF27, FF26, this.f9248X[11], 12);
        int FF210 = FF2(FF26, FF29, FF28, FF27, this.f9248X[8], 13);
        int FF211 = FF2(FF27, FF210, FF29, FF28, this.f9248X[12], 5);
        int FF212 = FF2(FF28, FF211, FF210, FF29, this.f9248X[2], 14);
        int FF213 = FF2(FF29, FF212, FF211, FF210, this.f9248X[10], 13);
        int FF214 = FF2(FF210, FF213, FF212, FF211, this.f9248X[0], 13);
        int FF215 = FF2(FF211, FF214, FF213, FF212, this.f9248X[4], 7);
        int FF216 = FF2(FF212, FF215, FF214, FF213, this.f9248X[13], 5);
        int m4048F4 = m4048F4(m4047F313, m4047F316, FF215, m4047F314, this.f9248X[1], 11);
        int m4048F42 = m4048F4(m4047F314, m4048F4, m4047F316, FF215, this.f9248X[9], 12);
        int m4048F43 = m4048F4(FF215, m4048F42, m4048F4, m4047F316, this.f9248X[11], 14);
        int m4048F44 = m4048F4(m4047F316, m4048F43, m4048F42, m4048F4, this.f9248X[10], 15);
        int m4048F45 = m4048F4(m4048F4, m4048F44, m4048F43, m4048F42, this.f9248X[0], 14);
        int m4048F46 = m4048F4(m4048F42, m4048F45, m4048F44, m4048F43, this.f9248X[8], 15);
        int m4048F47 = m4048F4(m4048F43, m4048F46, m4048F45, m4048F44, this.f9248X[12], 9);
        int m4048F48 = m4048F4(m4048F44, m4048F47, m4048F46, m4048F45, this.f9248X[4], 8);
        int m4048F49 = m4048F4(m4048F45, m4048F48, m4048F47, m4048F46, this.f9248X[13], 9);
        int m4048F410 = m4048F4(m4048F46, m4048F49, m4048F48, m4048F47, this.f9248X[3], 14);
        int m4048F411 = m4048F4(m4048F47, m4048F410, m4048F49, m4048F48, this.f9248X[7], 5);
        int m4048F412 = m4048F4(m4048F48, m4048F411, m4048F410, m4048F49, this.f9248X[15], 6);
        int m4048F413 = m4048F4(m4048F49, m4048F412, m4048F411, m4048F410, this.f9248X[14], 8);
        int m4048F414 = m4048F4(m4048F410, m4048F413, m4048F412, m4048F411, this.f9248X[5], 6);
        int m4048F415 = m4048F4(m4048F411, m4048F414, m4048F413, m4048F412, this.f9248X[6], 5);
        int m4048F416 = m4048F4(m4048F412, m4048F415, m4048F414, m4048F413, this.f9248X[2], 12);
        int FF1 = FF1(FF213, FF216, m4047F315, FF214, this.f9248X[8], 15);
        int FF12 = FF1(FF214, FF1, FF216, m4047F315, this.f9248X[6], 5);
        int FF13 = FF1(m4047F315, FF12, FF1, FF216, this.f9248X[4], 8);
        int FF14 = FF1(FF216, FF13, FF12, FF1, this.f9248X[1], 11);
        int FF15 = FF1(FF1, FF14, FF13, FF12, this.f9248X[3], 14);
        int FF16 = FF1(FF12, FF15, FF14, FF13, this.f9248X[11], 14);
        int FF17 = FF1(FF13, FF16, FF15, FF14, this.f9248X[15], 6);
        int FF18 = FF1(FF14, FF17, FF16, FF15, this.f9248X[0], 14);
        int FF19 = FF1(FF15, FF18, FF17, FF16, this.f9248X[5], 6);
        int FF110 = FF1(FF16, FF19, FF18, FF17, this.f9248X[12], 9);
        int FF111 = FF1(FF17, FF110, FF19, FF18, this.f9248X[2], 12);
        int FF112 = FF1(FF18, FF111, FF110, FF19, this.f9248X[13], 9);
        int FF113 = FF1(FF19, FF112, FF111, FF110, this.f9248X[9], 12);
        int FF114 = FF1(FF110, FF113, FF112, FF111, this.f9248X[7], 5);
        int FF115 = FF1(FF111, FF114, FF113, FF112, this.f9248X[10], 15);
        int FF116 = FF1(FF112, FF115, FF114, FF113, this.f9248X[14], 8);
        this.f9240H0 += m4048F413;
        this.f9241H1 += m4048F416;
        this.f9242H2 += m4048F415;
        this.f9243H3 += FF114;
        this.f9244H4 += FF113;
        this.f9245H5 += FF116;
        this.f9246H6 += FF115;
        this.f9247H7 += m4048F414;
        this.xOff = 0;
        int i9 = 0;
        while (true) {
            int[] iArr = this.f9248X;
            if (i9 == iArr.length) {
                return;
            }
            iArr[i9] = 0;
            i9++;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f9248X;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int[] iArr = this.f9248X;
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
        this.f9240H0 = 1732584193;
        this.f9241H1 = -271733879;
        this.f9242H2 = -1732584194;
        this.f9243H3 = 271733878;
        this.f9244H4 = 1985229328;
        this.f9245H5 = -19088744;
        this.f9246H6 = -1985229329;
        this.f9247H7 = 19088743;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f9248X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((RIPEMD256Digest) memoable);
    }
}
