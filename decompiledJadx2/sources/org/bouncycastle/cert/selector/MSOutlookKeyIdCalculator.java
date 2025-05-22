package org.bouncycastle.cert.selector;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.util.Pack;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class MSOutlookKeyIdCalculator {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static abstract class GeneralDigest {
        private static final int BYTE_LENGTH = 64;
        private long byteCount;
        private byte[] xBuf;
        private int xBufOff;

        protected GeneralDigest() {
            this.xBuf = new byte[4];
            this.xBufOff = 0;
        }

        protected GeneralDigest(GeneralDigest generalDigest) {
            this.xBuf = new byte[generalDigest.xBuf.length];
            copyIn(generalDigest);
        }

        protected void copyIn(GeneralDigest generalDigest) {
            byte[] bArr = generalDigest.xBuf;
            System.arraycopy(bArr, 0, this.xBuf, 0, bArr.length);
            this.xBufOff = generalDigest.xBufOff;
            this.byteCount = generalDigest.byteCount;
        }

        public void finish() {
            long j = this.byteCount << 3;
            byte b = Byte.MIN_VALUE;
            while (true) {
                update(b);
                if (this.xBufOff == 0) {
                    processLength(j);
                    processBlock();
                    return;
                }
                b = 0;
            }
        }

        protected abstract void processBlock();

        protected abstract void processLength(long j);

        protected abstract void processWord(byte[] bArr, int i);

        public void reset() {
            this.byteCount = 0L;
            this.xBufOff = 0;
            int i = 0;
            while (true) {
                byte[] bArr = this.xBuf;
                if (i >= bArr.length) {
                    return;
                }
                bArr[i] = 0;
                i++;
            }
        }

        public void update(byte b) {
            byte[] bArr = this.xBuf;
            int i = this.xBufOff;
            this.xBufOff = i + 1;
            bArr[i] = b;
            if (this.xBufOff == bArr.length) {
                processWord(bArr, 0);
                this.xBufOff = 0;
            }
            this.byteCount++;
        }

        public void update(byte[] bArr, int i, int i2) {
            while (this.xBufOff != 0 && i2 > 0) {
                update(bArr[i]);
                i++;
                i2--;
            }
            while (i2 > this.xBuf.length) {
                processWord(bArr, i);
                byte[] bArr2 = this.xBuf;
                i += bArr2.length;
                i2 -= bArr2.length;
                this.byteCount += bArr2.length;
            }
            while (i2 > 0) {
                update(bArr[i]);
                i++;
                i2--;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class SHA1Digest extends GeneralDigest {
        private static final int DIGEST_LENGTH = 20;

        /* renamed from: Y1 */
        private static final int f9118Y1 = 1518500249;

        /* renamed from: Y2 */
        private static final int f9119Y2 = 1859775393;

        /* renamed from: Y3 */
        private static final int f9120Y3 = -1894007588;

        /* renamed from: Y4 */
        private static final int f9121Y4 = -899497514;

        /* renamed from: H1 */
        private int f9122H1;

        /* renamed from: H2 */
        private int f9123H2;

        /* renamed from: H3 */
        private int f9124H3;

        /* renamed from: H4 */
        private int f9125H4;

        /* renamed from: H5 */
        private int f9126H5;

        /* renamed from: X */
        private int[] f9127X = new int[80];
        private int xOff;

        public SHA1Digest() {
            reset();
        }

        /* renamed from: f */
        private int m4007f(int i, int i2, int i3) {
            return ((~i) & i3) | (i2 & i);
        }

        /* renamed from: g */
        private int m4008g(int i, int i2, int i3) {
            return (i & i3) | (i & i2) | (i2 & i3);
        }

        /* renamed from: h */
        private int m4009h(int i, int i2, int i3) {
            return (i ^ i2) ^ i3;
        }

        public int doFinal(byte[] bArr, int i) {
            finish();
            Pack.intToBigEndian(this.f9122H1, bArr, i);
            Pack.intToBigEndian(this.f9123H2, bArr, i + 4);
            Pack.intToBigEndian(this.f9124H3, bArr, i + 8);
            Pack.intToBigEndian(this.f9125H4, bArr, i + 12);
            Pack.intToBigEndian(this.f9126H5, bArr, i + 16);
            reset();
            return 20;
        }

        public String getAlgorithmName() {
            return "SHA-1";
        }

        public int getDigestSize() {
            return 20;
        }

        @Override // org.bouncycastle.cert.selector.MSOutlookKeyIdCalculator.GeneralDigest
        protected void processBlock() {
            for (int i = 16; i < 80; i++) {
                int[] iArr = this.f9127X;
                int i2 = ((iArr[i - 3] ^ iArr[i - 8]) ^ iArr[i - 14]) ^ iArr[i - 16];
                iArr[i] = (i2 >>> 31) | (i2 << 1);
            }
            int i3 = this.f9122H1;
            int i4 = this.f9123H2;
            int i5 = this.f9124H3;
            int i6 = this.f9125H4;
            int i7 = this.f9126H5;
            int i8 = i6;
            int i9 = 0;
            int i10 = i5;
            int i11 = i4;
            int i12 = i3;
            int i13 = 0;
            while (i13 < 4) {
                int i14 = i9 + 1;
                int m4007f = i7 + ((i12 << 5) | (i12 >>> 27)) + m4007f(i11, i10, i8) + this.f9127X[i9] + f9118Y1;
                int i15 = (i11 >>> 2) | (i11 << 30);
                int i16 = i14 + 1;
                int m4007f2 = i8 + ((m4007f << 5) | (m4007f >>> 27)) + m4007f(i12, i15, i10) + this.f9127X[i14] + f9118Y1;
                int i17 = (i12 >>> 2) | (i12 << 30);
                int i18 = i16 + 1;
                int m4007f3 = i10 + ((m4007f2 << 5) | (m4007f2 >>> 27)) + m4007f(m4007f, i17, i15) + this.f9127X[i16] + f9118Y1;
                i7 = (m4007f >>> 2) | (m4007f << 30);
                int i19 = i18 + 1;
                i11 = i15 + ((m4007f3 << 5) | (m4007f3 >>> 27)) + m4007f(m4007f2, i7, i17) + this.f9127X[i18] + f9118Y1;
                i8 = (m4007f2 >>> 2) | (m4007f2 << 30);
                i12 = i17 + ((i11 << 5) | (i11 >>> 27)) + m4007f(m4007f3, i8, i7) + this.f9127X[i19] + f9118Y1;
                i10 = (m4007f3 >>> 2) | (m4007f3 << 30);
                i13++;
                i9 = i19 + 1;
            }
            int i20 = 0;
            while (i20 < 4) {
                int i21 = i9 + 1;
                int m4009h = i7 + ((i12 << 5) | (i12 >>> 27)) + m4009h(i11, i10, i8) + this.f9127X[i9] + f9119Y2;
                int i22 = (i11 >>> 2) | (i11 << 30);
                int i23 = i21 + 1;
                int m4009h2 = i8 + ((m4009h << 5) | (m4009h >>> 27)) + m4009h(i12, i22, i10) + this.f9127X[i21] + f9119Y2;
                int i24 = (i12 >>> 2) | (i12 << 30);
                int i25 = i23 + 1;
                int m4009h3 = i10 + ((m4009h2 << 5) | (m4009h2 >>> 27)) + m4009h(m4009h, i24, i22) + this.f9127X[i23] + f9119Y2;
                i7 = (m4009h >>> 2) | (m4009h << 30);
                int i26 = i25 + 1;
                i11 = i22 + ((m4009h3 << 5) | (m4009h3 >>> 27)) + m4009h(m4009h2, i7, i24) + this.f9127X[i25] + f9119Y2;
                i8 = (m4009h2 >>> 2) | (m4009h2 << 30);
                i12 = i24 + ((i11 << 5) | (i11 >>> 27)) + m4009h(m4009h3, i8, i7) + this.f9127X[i26] + f9119Y2;
                i10 = (m4009h3 >>> 2) | (m4009h3 << 30);
                i20++;
                i9 = i26 + 1;
            }
            int i27 = 0;
            while (i27 < 4) {
                int i28 = i9 + 1;
                int m4008g = i7 + ((i12 << 5) | (i12 >>> 27)) + m4008g(i11, i10, i8) + this.f9127X[i9] + f9120Y3;
                int i29 = (i11 >>> 2) | (i11 << 30);
                int i30 = i28 + 1;
                int m4008g2 = i8 + ((m4008g << 5) | (m4008g >>> 27)) + m4008g(i12, i29, i10) + this.f9127X[i28] + f9120Y3;
                int i31 = (i12 >>> 2) | (i12 << 30);
                int i32 = i30 + 1;
                int m4008g3 = i10 + ((m4008g2 << 5) | (m4008g2 >>> 27)) + m4008g(m4008g, i31, i29) + this.f9127X[i30] + f9120Y3;
                i7 = (m4008g >>> 2) | (m4008g << 30);
                int i33 = i32 + 1;
                i11 = i29 + ((m4008g3 << 5) | (m4008g3 >>> 27)) + m4008g(m4008g2, i7, i31) + this.f9127X[i32] + f9120Y3;
                i8 = (m4008g2 >>> 2) | (m4008g2 << 30);
                i12 = i31 + ((i11 << 5) | (i11 >>> 27)) + m4008g(m4008g3, i8, i7) + this.f9127X[i33] + f9120Y3;
                i10 = (m4008g3 >>> 2) | (m4008g3 << 30);
                i27++;
                i9 = i33 + 1;
            }
            int i34 = 0;
            while (i34 <= 3) {
                int i35 = i9 + 1;
                int m4009h4 = i7 + ((i12 << 5) | (i12 >>> 27)) + m4009h(i11, i10, i8) + this.f9127X[i9] + f9121Y4;
                int i36 = (i11 >>> 2) | (i11 << 30);
                int i37 = i35 + 1;
                int m4009h5 = i8 + ((m4009h4 << 5) | (m4009h4 >>> 27)) + m4009h(i12, i36, i10) + this.f9127X[i35] + f9121Y4;
                int i38 = (i12 >>> 2) | (i12 << 30);
                int i39 = i37 + 1;
                int m4009h6 = i10 + ((m4009h5 << 5) | (m4009h5 >>> 27)) + m4009h(m4009h4, i38, i36) + this.f9127X[i37] + f9121Y4;
                i7 = (m4009h4 >>> 2) | (m4009h4 << 30);
                int i40 = i39 + 1;
                i11 = i36 + ((m4009h6 << 5) | (m4009h6 >>> 27)) + m4009h(m4009h5, i7, i38) + this.f9127X[i39] + f9121Y4;
                i8 = (m4009h5 >>> 2) | (m4009h5 << 30);
                i12 = i38 + ((i11 << 5) | (i11 >>> 27)) + m4009h(m4009h6, i8, i7) + this.f9127X[i40] + f9121Y4;
                i10 = (m4009h6 >>> 2) | (m4009h6 << 30);
                i34++;
                i9 = i40 + 1;
            }
            this.f9122H1 += i12;
            this.f9123H2 += i11;
            this.f9124H3 += i10;
            this.f9125H4 += i8;
            this.f9126H5 += i7;
            this.xOff = 0;
            for (int i41 = 0; i41 < 16; i41++) {
                this.f9127X[i41] = 0;
            }
        }

        @Override // org.bouncycastle.cert.selector.MSOutlookKeyIdCalculator.GeneralDigest
        protected void processLength(long j) {
            if (this.xOff > 14) {
                processBlock();
            }
            int[] iArr = this.f9127X;
            iArr[14] = (int) (j >>> 32);
            iArr[15] = (int) (j & (-1));
        }

        @Override // org.bouncycastle.cert.selector.MSOutlookKeyIdCalculator.GeneralDigest
        protected void processWord(byte[] bArr, int i) {
            int i2 = bArr[i] << 24;
            int i3 = i + 1;
            int i4 = i2 | ((bArr[i3] & 255) << 16);
            int i5 = i3 + 1;
            int i6 = (bArr[i5 + 1] & 255) | i4 | ((bArr[i5] & 255) << 8);
            int[] iArr = this.f9127X;
            int i7 = this.xOff;
            iArr[i7] = i6;
            int i8 = i7 + 1;
            this.xOff = i8;
            if (i8 == 16) {
                processBlock();
            }
        }

        @Override // org.bouncycastle.cert.selector.MSOutlookKeyIdCalculator.GeneralDigest
        public void reset() {
            super.reset();
            this.f9122H1 = 1732584193;
            this.f9123H2 = -271733879;
            this.f9124H3 = -1732584194;
            this.f9125H4 = 271733878;
            this.f9126H5 = -1009589776;
            this.xOff = 0;
            int i = 0;
            while (true) {
                int[] iArr = this.f9127X;
                if (i == iArr.length) {
                    return;
                }
                iArr[i] = 0;
                i++;
            }
        }
    }

    MSOutlookKeyIdCalculator() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] calculateKeyId(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        SHA1Digest sHA1Digest = new SHA1Digest();
        byte[] bArr = new byte[sHA1Digest.getDigestSize()];
        try {
            byte[] encoded = subjectPublicKeyInfo.getEncoded(ASN1Encoding.DER);
            sHA1Digest.update(encoded, 0, encoded.length);
            sHA1Digest.doFinal(bArr, 0);
            return bArr;
        } catch (IOException unused) {
            return new byte[0];
        }
    }
}
