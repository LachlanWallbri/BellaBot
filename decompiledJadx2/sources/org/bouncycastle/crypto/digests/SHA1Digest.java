package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* loaded from: classes9.dex */
public class SHA1Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 20;

    /* renamed from: Y1 */
    private static final int f9260Y1 = 1518500249;

    /* renamed from: Y2 */
    private static final int f9261Y2 = 1859775393;

    /* renamed from: Y3 */
    private static final int f9262Y3 = -1894007588;

    /* renamed from: Y4 */
    private static final int f9263Y4 = -899497514;

    /* renamed from: H1 */
    private int f9264H1;

    /* renamed from: H2 */
    private int f9265H2;

    /* renamed from: H3 */
    private int f9266H3;

    /* renamed from: H4 */
    private int f9267H4;

    /* renamed from: H5 */
    private int f9268H5;

    /* renamed from: X */
    private int[] f9269X;
    private int xOff;

    public SHA1Digest() {
        this.f9269X = new int[80];
        reset();
    }

    public SHA1Digest(SHA1Digest sHA1Digest) {
        super(sHA1Digest);
        this.f9269X = new int[80];
        copyIn(sHA1Digest);
    }

    public SHA1Digest(byte[] bArr) {
        super(bArr);
        this.f9269X = new int[80];
        this.f9264H1 = Pack.bigEndianToInt(bArr, 16);
        this.f9265H2 = Pack.bigEndianToInt(bArr, 20);
        this.f9266H3 = Pack.bigEndianToInt(bArr, 24);
        this.f9267H4 = Pack.bigEndianToInt(bArr, 28);
        this.f9268H5 = Pack.bigEndianToInt(bArr, 32);
        this.xOff = Pack.bigEndianToInt(bArr, 36);
        for (int i = 0; i != this.xOff; i++) {
            this.f9269X[i] = Pack.bigEndianToInt(bArr, (i * 4) + 40);
        }
    }

    private void copyIn(SHA1Digest sHA1Digest) {
        this.f9264H1 = sHA1Digest.f9264H1;
        this.f9265H2 = sHA1Digest.f9265H2;
        this.f9266H3 = sHA1Digest.f9266H3;
        this.f9267H4 = sHA1Digest.f9267H4;
        this.f9268H5 = sHA1Digest.f9268H5;
        int[] iArr = sHA1Digest.f9269X;
        System.arraycopy(iArr, 0, this.f9269X, 0, iArr.length);
        this.xOff = sHA1Digest.xOff;
    }

    /* renamed from: f */
    private int m4060f(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: g */
    private int m4061g(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    /* renamed from: h */
    private int m4062h(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SHA1Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.f9264H1, bArr, i);
        Pack.intToBigEndian(this.f9265H2, bArr, i + 4);
        Pack.intToBigEndian(this.f9266H3, bArr, i + 8);
        Pack.intToBigEndian(this.f9267H4, bArr, i + 12);
        Pack.intToBigEndian(this.f9268H5, bArr, i + 16);
        reset();
        return 20;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA-1";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 20;
    }

    @Override // org.bouncycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[(this.xOff * 4) + 40];
        super.populateState(bArr);
        Pack.intToBigEndian(this.f9264H1, bArr, 16);
        Pack.intToBigEndian(this.f9265H2, bArr, 20);
        Pack.intToBigEndian(this.f9266H3, bArr, 24);
        Pack.intToBigEndian(this.f9267H4, bArr, 28);
        Pack.intToBigEndian(this.f9268H5, bArr, 32);
        Pack.intToBigEndian(this.xOff, bArr, 36);
        for (int i = 0; i != this.xOff; i++) {
            Pack.intToBigEndian(this.f9269X[i], bArr, (i * 4) + 40);
        }
        return bArr;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        for (int i = 16; i < 80; i++) {
            int[] iArr = this.f9269X;
            int i2 = ((iArr[i - 3] ^ iArr[i - 8]) ^ iArr[i - 14]) ^ iArr[i - 16];
            iArr[i] = (i2 >>> 31) | (i2 << 1);
        }
        int i3 = this.f9264H1;
        int i4 = this.f9265H2;
        int i5 = this.f9266H3;
        int i6 = this.f9267H4;
        int i7 = this.f9268H5;
        int i8 = i6;
        int i9 = 0;
        int i10 = i5;
        int i11 = i4;
        int i12 = i3;
        int i13 = 0;
        while (i13 < 4) {
            int i14 = i9 + 1;
            int m4060f = i7 + ((i12 << 5) | (i12 >>> 27)) + m4060f(i11, i10, i8) + this.f9269X[i9] + f9260Y1;
            int i15 = (i11 >>> 2) | (i11 << 30);
            int i16 = i14 + 1;
            int m4060f2 = i8 + ((m4060f << 5) | (m4060f >>> 27)) + m4060f(i12, i15, i10) + this.f9269X[i14] + f9260Y1;
            int i17 = (i12 >>> 2) | (i12 << 30);
            int i18 = i16 + 1;
            int m4060f3 = i10 + ((m4060f2 << 5) | (m4060f2 >>> 27)) + m4060f(m4060f, i17, i15) + this.f9269X[i16] + f9260Y1;
            i7 = (m4060f >>> 2) | (m4060f << 30);
            int i19 = i18 + 1;
            i11 = i15 + ((m4060f3 << 5) | (m4060f3 >>> 27)) + m4060f(m4060f2, i7, i17) + this.f9269X[i18] + f9260Y1;
            i8 = (m4060f2 >>> 2) | (m4060f2 << 30);
            i12 = i17 + ((i11 << 5) | (i11 >>> 27)) + m4060f(m4060f3, i8, i7) + this.f9269X[i19] + f9260Y1;
            i10 = (m4060f3 >>> 2) | (m4060f3 << 30);
            i13++;
            i9 = i19 + 1;
        }
        int i20 = 0;
        while (i20 < 4) {
            int i21 = i9 + 1;
            int m4062h = i7 + ((i12 << 5) | (i12 >>> 27)) + m4062h(i11, i10, i8) + this.f9269X[i9] + f9261Y2;
            int i22 = (i11 >>> 2) | (i11 << 30);
            int i23 = i21 + 1;
            int m4062h2 = i8 + ((m4062h << 5) | (m4062h >>> 27)) + m4062h(i12, i22, i10) + this.f9269X[i21] + f9261Y2;
            int i24 = (i12 >>> 2) | (i12 << 30);
            int i25 = i23 + 1;
            int m4062h3 = i10 + ((m4062h2 << 5) | (m4062h2 >>> 27)) + m4062h(m4062h, i24, i22) + this.f9269X[i23] + f9261Y2;
            i7 = (m4062h >>> 2) | (m4062h << 30);
            int i26 = i25 + 1;
            i11 = i22 + ((m4062h3 << 5) | (m4062h3 >>> 27)) + m4062h(m4062h2, i7, i24) + this.f9269X[i25] + f9261Y2;
            i8 = (m4062h2 >>> 2) | (m4062h2 << 30);
            i12 = i24 + ((i11 << 5) | (i11 >>> 27)) + m4062h(m4062h3, i8, i7) + this.f9269X[i26] + f9261Y2;
            i10 = (m4062h3 >>> 2) | (m4062h3 << 30);
            i20++;
            i9 = i26 + 1;
        }
        int i27 = 0;
        while (i27 < 4) {
            int i28 = i9 + 1;
            int m4061g = i7 + ((i12 << 5) | (i12 >>> 27)) + m4061g(i11, i10, i8) + this.f9269X[i9] + f9262Y3;
            int i29 = (i11 >>> 2) | (i11 << 30);
            int i30 = i28 + 1;
            int m4061g2 = i8 + ((m4061g << 5) | (m4061g >>> 27)) + m4061g(i12, i29, i10) + this.f9269X[i28] + f9262Y3;
            int i31 = (i12 >>> 2) | (i12 << 30);
            int i32 = i30 + 1;
            int m4061g3 = i10 + ((m4061g2 << 5) | (m4061g2 >>> 27)) + m4061g(m4061g, i31, i29) + this.f9269X[i30] + f9262Y3;
            i7 = (m4061g >>> 2) | (m4061g << 30);
            int i33 = i32 + 1;
            i11 = i29 + ((m4061g3 << 5) | (m4061g3 >>> 27)) + m4061g(m4061g2, i7, i31) + this.f9269X[i32] + f9262Y3;
            i8 = (m4061g2 >>> 2) | (m4061g2 << 30);
            i12 = i31 + ((i11 << 5) | (i11 >>> 27)) + m4061g(m4061g3, i8, i7) + this.f9269X[i33] + f9262Y3;
            i10 = (m4061g3 >>> 2) | (m4061g3 << 30);
            i27++;
            i9 = i33 + 1;
        }
        int i34 = 0;
        while (i34 <= 3) {
            int i35 = i9 + 1;
            int m4062h4 = i7 + ((i12 << 5) | (i12 >>> 27)) + m4062h(i11, i10, i8) + this.f9269X[i9] + f9263Y4;
            int i36 = (i11 >>> 2) | (i11 << 30);
            int i37 = i35 + 1;
            int m4062h5 = i8 + ((m4062h4 << 5) | (m4062h4 >>> 27)) + m4062h(i12, i36, i10) + this.f9269X[i35] + f9263Y4;
            int i38 = (i12 >>> 2) | (i12 << 30);
            int i39 = i37 + 1;
            int m4062h6 = i10 + ((m4062h5 << 5) | (m4062h5 >>> 27)) + m4062h(m4062h4, i38, i36) + this.f9269X[i37] + f9263Y4;
            i7 = (m4062h4 >>> 2) | (m4062h4 << 30);
            int i40 = i39 + 1;
            i11 = i36 + ((m4062h6 << 5) | (m4062h6 >>> 27)) + m4062h(m4062h5, i7, i38) + this.f9269X[i39] + f9263Y4;
            i8 = (m4062h5 >>> 2) | (m4062h5 << 30);
            i12 = i38 + ((i11 << 5) | (i11 >>> 27)) + m4062h(m4062h6, i8, i7) + this.f9269X[i40] + f9263Y4;
            i10 = (m4062h6 >>> 2) | (m4062h6 << 30);
            i34++;
            i9 = i40 + 1;
        }
        this.f9264H1 += i12;
        this.f9265H2 += i11;
        this.f9266H3 += i10;
        this.f9267H4 += i8;
        this.f9268H5 += i7;
        this.xOff = 0;
        for (int i41 = 0; i41 < 16; i41++) {
            this.f9269X[i41] = 0;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f9269X;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) j;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int i2 = bArr[i] << 24;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & 255) << 16);
        int i5 = i3 + 1;
        int i6 = (bArr[i5 + 1] & 255) | i4 | ((bArr[i5] & 255) << 8);
        int[] iArr = this.f9269X;
        int i7 = this.xOff;
        iArr[i7] = i6;
        int i8 = i7 + 1;
        this.xOff = i8;
        if (i8 == 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f9264H1 = 1732584193;
        this.f9265H2 = -271733879;
        this.f9266H3 = -1732584194;
        this.f9267H4 = 271733878;
        this.f9268H5 = -1009589776;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f9269X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        SHA1Digest sHA1Digest = (SHA1Digest) memoable;
        super.copyIn((GeneralDigest) sHA1Digest);
        copyIn(sHA1Digest);
    }
}
