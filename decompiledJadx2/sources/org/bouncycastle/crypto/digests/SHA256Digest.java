package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* loaded from: classes9.dex */
public class SHA256Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 32;

    /* renamed from: K */
    static final int[] f9280K = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};

    /* renamed from: H1 */
    private int f9281H1;

    /* renamed from: H2 */
    private int f9282H2;

    /* renamed from: H3 */
    private int f9283H3;

    /* renamed from: H4 */
    private int f9284H4;

    /* renamed from: H5 */
    private int f9285H5;

    /* renamed from: H6 */
    private int f9286H6;

    /* renamed from: H7 */
    private int f9287H7;

    /* renamed from: H8 */
    private int f9288H8;

    /* renamed from: X */
    private int[] f9289X;
    private int xOff;

    public SHA256Digest() {
        this.f9289X = new int[64];
        reset();
    }

    public SHA256Digest(SHA256Digest sHA256Digest) {
        super(sHA256Digest);
        this.f9289X = new int[64];
        copyIn(sHA256Digest);
    }

    public SHA256Digest(byte[] bArr) {
        super(bArr);
        this.f9289X = new int[64];
        this.f9281H1 = Pack.bigEndianToInt(bArr, 16);
        this.f9282H2 = Pack.bigEndianToInt(bArr, 20);
        this.f9283H3 = Pack.bigEndianToInt(bArr, 24);
        this.f9284H4 = Pack.bigEndianToInt(bArr, 28);
        this.f9285H5 = Pack.bigEndianToInt(bArr, 32);
        this.f9286H6 = Pack.bigEndianToInt(bArr, 36);
        this.f9287H7 = Pack.bigEndianToInt(bArr, 40);
        this.f9288H8 = Pack.bigEndianToInt(bArr, 44);
        this.xOff = Pack.bigEndianToInt(bArr, 48);
        for (int i = 0; i != this.xOff; i++) {
            this.f9289X[i] = Pack.bigEndianToInt(bArr, (i * 4) + 52);
        }
    }

    /* renamed from: Ch */
    private static int m4064Ch(int i, int i2, int i3) {
        return ((~i) & i3) ^ (i2 & i);
    }

    private static int Maj(int i, int i2, int i3) {
        return ((i ^ i2) & i3) | (i & i2);
    }

    private static int Sum0(int i) {
        return ((i << 10) | (i >>> 22)) ^ (((i >>> 2) | (i << 30)) ^ ((i >>> 13) | (i << 19)));
    }

    private static int Sum1(int i) {
        return ((i << 7) | (i >>> 25)) ^ (((i >>> 6) | (i << 26)) ^ ((i >>> 11) | (i << 21)));
    }

    private static int Theta0(int i) {
        return (i >>> 3) ^ (((i >>> 7) | (i << 25)) ^ ((i >>> 18) | (i << 14)));
    }

    private static int Theta1(int i) {
        return (i >>> 10) ^ (((i >>> 17) | (i << 15)) ^ ((i >>> 19) | (i << 13)));
    }

    private void copyIn(SHA256Digest sHA256Digest) {
        super.copyIn((GeneralDigest) sHA256Digest);
        this.f9281H1 = sHA256Digest.f9281H1;
        this.f9282H2 = sHA256Digest.f9282H2;
        this.f9283H3 = sHA256Digest.f9283H3;
        this.f9284H4 = sHA256Digest.f9284H4;
        this.f9285H5 = sHA256Digest.f9285H5;
        this.f9286H6 = sHA256Digest.f9286H6;
        this.f9287H7 = sHA256Digest.f9287H7;
        this.f9288H8 = sHA256Digest.f9288H8;
        int[] iArr = sHA256Digest.f9289X;
        System.arraycopy(iArr, 0, this.f9289X, 0, iArr.length);
        this.xOff = sHA256Digest.xOff;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SHA256Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.f9281H1, bArr, i);
        Pack.intToBigEndian(this.f9282H2, bArr, i + 4);
        Pack.intToBigEndian(this.f9283H3, bArr, i + 8);
        Pack.intToBigEndian(this.f9284H4, bArr, i + 12);
        Pack.intToBigEndian(this.f9285H5, bArr, i + 16);
        Pack.intToBigEndian(this.f9286H6, bArr, i + 20);
        Pack.intToBigEndian(this.f9287H7, bArr, i + 24);
        Pack.intToBigEndian(this.f9288H8, bArr, i + 28);
        reset();
        return 32;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA-256";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    @Override // org.bouncycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[(this.xOff * 4) + 52];
        super.populateState(bArr);
        Pack.intToBigEndian(this.f9281H1, bArr, 16);
        Pack.intToBigEndian(this.f9282H2, bArr, 20);
        Pack.intToBigEndian(this.f9283H3, bArr, 24);
        Pack.intToBigEndian(this.f9284H4, bArr, 28);
        Pack.intToBigEndian(this.f9285H5, bArr, 32);
        Pack.intToBigEndian(this.f9286H6, bArr, 36);
        Pack.intToBigEndian(this.f9287H7, bArr, 40);
        Pack.intToBigEndian(this.f9288H8, bArr, 44);
        Pack.intToBigEndian(this.xOff, bArr, 48);
        for (int i = 0; i != this.xOff; i++) {
            Pack.intToBigEndian(this.f9289X[i], bArr, (i * 4) + 52);
        }
        return bArr;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        for (int i = 16; i <= 63; i++) {
            int[] iArr = this.f9289X;
            int Theta1 = Theta1(iArr[i - 2]);
            int[] iArr2 = this.f9289X;
            iArr[i] = Theta1 + iArr2[i - 7] + Theta0(iArr2[i - 15]) + this.f9289X[i - 16];
        }
        int i2 = this.f9281H1;
        int i3 = this.f9282H2;
        int i4 = this.f9283H3;
        int i5 = this.f9284H4;
        int i6 = this.f9285H5;
        int i7 = this.f9286H6;
        int i8 = this.f9287H7;
        int i9 = this.f9288H8;
        int i10 = 0;
        int i11 = i8;
        int i12 = i7;
        int i13 = i6;
        int i14 = i5;
        int i15 = i4;
        int i16 = i3;
        int i17 = i2;
        for (int i18 = 0; i18 < 8; i18++) {
            int Sum1 = i9 + Sum1(i13) + m4064Ch(i13, i12, i11) + f9280K[i10] + this.f9289X[i10];
            int i19 = i14 + Sum1;
            int Sum0 = Sum1 + Sum0(i17) + Maj(i17, i16, i15);
            int i20 = i10 + 1;
            int Sum12 = i11 + Sum1(i19) + m4064Ch(i19, i13, i12) + f9280K[i20] + this.f9289X[i20];
            int i21 = i15 + Sum12;
            int Sum02 = Sum12 + Sum0(Sum0) + Maj(Sum0, i17, i16);
            int i22 = i20 + 1;
            int Sum13 = i12 + Sum1(i21) + m4064Ch(i21, i19, i13) + f9280K[i22] + this.f9289X[i22];
            int i23 = i16 + Sum13;
            int Sum03 = Sum13 + Sum0(Sum02) + Maj(Sum02, Sum0, i17);
            int i24 = i22 + 1;
            int Sum14 = i13 + Sum1(i23) + m4064Ch(i23, i21, i19) + f9280K[i24] + this.f9289X[i24];
            int i25 = i17 + Sum14;
            int Sum04 = Sum14 + Sum0(Sum03) + Maj(Sum03, Sum02, Sum0);
            int i26 = i24 + 1;
            int Sum15 = i19 + Sum1(i25) + m4064Ch(i25, i23, i21) + f9280K[i26] + this.f9289X[i26];
            i9 = Sum0 + Sum15;
            i14 = Sum15 + Sum0(Sum04) + Maj(Sum04, Sum03, Sum02);
            int i27 = i26 + 1;
            int Sum16 = i21 + Sum1(i9) + m4064Ch(i9, i25, i23) + f9280K[i27] + this.f9289X[i27];
            i11 = Sum02 + Sum16;
            i15 = Sum16 + Sum0(i14) + Maj(i14, Sum04, Sum03);
            int i28 = i27 + 1;
            int Sum17 = i23 + Sum1(i11) + m4064Ch(i11, i9, i25) + f9280K[i28] + this.f9289X[i28];
            i12 = Sum03 + Sum17;
            i16 = Sum17 + Sum0(i15) + Maj(i15, i14, Sum04);
            int i29 = i28 + 1;
            int Sum18 = i25 + Sum1(i12) + m4064Ch(i12, i11, i9) + f9280K[i29] + this.f9289X[i29];
            i13 = Sum04 + Sum18;
            i17 = Sum18 + Sum0(i16) + Maj(i16, i15, i14);
            i10 = i29 + 1;
        }
        this.f9281H1 += i17;
        this.f9282H2 += i16;
        this.f9283H3 += i15;
        this.f9284H4 += i14;
        this.f9285H5 += i13;
        this.f9286H6 += i12;
        this.f9287H7 += i11;
        this.f9288H8 += i9;
        this.xOff = 0;
        for (int i30 = 0; i30 < 16; i30++) {
            this.f9289X[i30] = 0;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f9289X;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) (j & (-1));
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int i2 = bArr[i] << 24;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & 255) << 16);
        int i5 = i3 + 1;
        int i6 = (bArr[i5 + 1] & 255) | i4 | ((bArr[i5] & 255) << 8);
        int[] iArr = this.f9289X;
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
        this.f9281H1 = 1779033703;
        this.f9282H2 = -1150833019;
        this.f9283H3 = 1013904242;
        this.f9284H4 = -1521486534;
        this.f9285H5 = 1359893119;
        this.f9286H6 = -1694144372;
        this.f9287H7 = 528734635;
        this.f9288H8 = 1541459225;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f9289X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((SHA256Digest) memoable);
    }
}
