package org.bouncycastle.pqc.crypto.gmss.util;

import org.bouncycastle.crypto.Digest;

/* loaded from: classes9.dex */
public class WinternitzOTSVerify {
    private Digest messDigestOTS;

    /* renamed from: w */
    private int f9882w;

    public WinternitzOTSVerify(Digest digest, int i) {
        this.f9882w = i;
        this.messDigestOTS = digest;
    }

    public byte[] Verify(byte[] bArr, byte[] bArr2) {
        int i;
        int i2;
        byte[] bArr3 = bArr2;
        int digestSize = this.messDigestOTS.getDigestSize();
        byte[] bArr4 = new byte[digestSize];
        int i3 = 0;
        this.messDigestOTS.update(bArr, 0, bArr.length);
        byte[] bArr5 = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(bArr5, 0);
        int i4 = digestSize << 3;
        int i5 = this.f9882w;
        int i6 = ((i5 - 1) + i4) / i5;
        int log = getLog((i6 << i5) + 1);
        int i7 = this.f9882w;
        int i8 = ((((log + i7) - 1) / i7) + i6) * digestSize;
        if (i8 != bArr3.length) {
            return null;
        }
        byte[] bArr6 = new byte[i8];
        int i9 = 8;
        if (8 % i7 == 0) {
            int i10 = 8 / i7;
            int i11 = (1 << i7) - 1;
            int i12 = 0;
            int i13 = 0;
            byte[] bArr7 = new byte[digestSize];
            int i14 = 0;
            while (i14 < bArr5.length) {
                byte[] bArr8 = bArr7;
                int i15 = i13;
                int i16 = i12;
                int i17 = 0;
                while (i17 < i10) {
                    int i18 = bArr5[i14] & i11;
                    i16 += i18;
                    int i19 = i10;
                    int i20 = i15 * digestSize;
                    System.arraycopy(bArr3, i20, bArr8, 0, digestSize);
                    while (true) {
                        int i21 = i16;
                        if (i18 < i11) {
                            this.messDigestOTS.update(bArr8, 0, bArr8.length);
                            bArr8 = new byte[this.messDigestOTS.getDigestSize()];
                            this.messDigestOTS.doFinal(bArr8, 0);
                            i18++;
                            i16 = i21;
                        }
                    }
                    System.arraycopy(bArr8, 0, bArr6, i20, digestSize);
                    bArr5[i14] = (byte) (bArr5[i14] >>> this.f9882w);
                    i15++;
                    i17++;
                    i10 = i19;
                    bArr3 = bArr2;
                }
                i14++;
                bArr3 = bArr2;
                i12 = i16;
                i13 = i15;
                bArr7 = bArr8;
            }
            int i22 = (i6 << this.f9882w) - i12;
            int i23 = 0;
            while (i23 < log) {
                int i24 = i13 * digestSize;
                System.arraycopy(bArr2, i24, bArr7, 0, digestSize);
                for (int i25 = i22 & i11; i25 < i11; i25++) {
                    this.messDigestOTS.update(bArr7, 0, bArr7.length);
                    bArr7 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr7, 0);
                }
                System.arraycopy(bArr7, 0, bArr6, i24, digestSize);
                int i26 = this.f9882w;
                i22 >>>= i26;
                i13++;
                i23 += i26;
            }
        } else if (i7 < 8) {
            int i27 = digestSize / i7;
            int i28 = (1 << i7) - 1;
            int i29 = 0;
            int i30 = 0;
            int i31 = 0;
            byte[] bArr9 = new byte[digestSize];
            int i32 = 0;
            while (i32 < i27) {
                int i33 = i29;
                long j = 0;
                for (int i34 = 0; i34 < this.f9882w; i34++) {
                    j ^= (bArr5[i33] & 255) << (i34 << 3);
                    i33++;
                }
                int i35 = 0;
                byte[] bArr10 = bArr9;
                while (true) {
                    i2 = i32;
                    if (i35 < i9) {
                        int i36 = (int) (j & i28);
                        i30 += i36;
                        int i37 = i31 * digestSize;
                        System.arraycopy(bArr3, i37, bArr10, 0, digestSize);
                        while (i36 < i28) {
                            this.messDigestOTS.update(bArr10, 0, bArr10.length);
                            bArr10 = new byte[this.messDigestOTS.getDigestSize()];
                            this.messDigestOTS.doFinal(bArr10, 0);
                            i36++;
                        }
                        System.arraycopy(bArr10, 0, bArr6, i37, digestSize);
                        j >>>= this.f9882w;
                        i31++;
                        i35++;
                        i32 = i2;
                        i9 = 8;
                    }
                }
                i32 = i2 + 1;
                bArr9 = bArr10;
                i29 = i33;
                i9 = 8;
            }
            int i38 = digestSize % this.f9882w;
            long j2 = 0;
            for (int i39 = 0; i39 < i38; i39++) {
                j2 ^= (bArr5[i29] & 255) << (i39 << 3);
                i29++;
            }
            int i40 = i38 << 3;
            int i41 = 0;
            byte[] bArr11 = bArr9;
            while (i41 < i40) {
                int i42 = (int) (j2 & i28);
                i30 += i42;
                int i43 = i31 * digestSize;
                System.arraycopy(bArr3, i43, bArr11, 0, digestSize);
                while (i42 < i28) {
                    this.messDigestOTS.update(bArr11, 0, bArr11.length);
                    bArr11 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr11, 0);
                    i42++;
                }
                System.arraycopy(bArr11, 0, bArr6, i43, digestSize);
                int i44 = this.f9882w;
                j2 >>>= i44;
                i31++;
                i41 += i44;
            }
            int i45 = (i6 << this.f9882w) - i30;
            int i46 = 0;
            while (i46 < log) {
                int i47 = i31 * digestSize;
                System.arraycopy(bArr3, i47, bArr11, 0, digestSize);
                for (int i48 = i45 & i28; i48 < i28; i48++) {
                    this.messDigestOTS.update(bArr11, 0, bArr11.length);
                    bArr11 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr11, 0);
                }
                System.arraycopy(bArr11, 0, bArr6, i47, digestSize);
                int i49 = this.f9882w;
                i45 >>>= i49;
                i31++;
                i46 += i49;
            }
        } else if (i7 < 57) {
            int i50 = i4 - i7;
            int i51 = (1 << i7) - 1;
            byte[] bArr12 = new byte[digestSize];
            int i52 = 0;
            int i53 = 0;
            int i54 = 0;
            while (i52 <= i50) {
                int i55 = i52 >>> 3;
                int i56 = i52 % 8;
                int i57 = i52 + this.f9882w;
                int i58 = i3;
                long j3 = 0;
                while (i55 < ((i57 + 7) >>> 3)) {
                    j3 ^= (bArr5[i55] & 255) << (i58 << 3);
                    i58++;
                    i55++;
                    log = log;
                    i50 = i50;
                }
                int i59 = i50;
                int i60 = log;
                int i61 = i6;
                long j4 = i51;
                long j5 = (j3 >>> i56) & j4;
                int i62 = i51;
                i53 = (int) (i53 + j5);
                int i63 = i54 * digestSize;
                System.arraycopy(bArr3, i63, bArr12, 0, digestSize);
                while (j5 < j4) {
                    this.messDigestOTS.update(bArr12, 0, bArr12.length);
                    bArr12 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr12, 0);
                    j5++;
                    j4 = j4;
                }
                System.arraycopy(bArr12, 0, bArr6, i63, digestSize);
                i54++;
                i6 = i61;
                i51 = i62;
                i52 = i57;
                log = i60;
                i50 = i59;
                i3 = 0;
            }
            int i64 = log;
            int i65 = i6;
            int i66 = i51;
            int i67 = i52 >>> 3;
            if (i67 < digestSize) {
                int i68 = i52 % 8;
                int i69 = 0;
                long j6 = 0;
                while (i67 < digestSize) {
                    j6 ^= (bArr5[i67] & 255) << (i69 << 3);
                    i69++;
                    i67++;
                }
                i = i66;
                long j7 = i;
                long j8 = (j6 >>> i68) & j7;
                i53 = (int) (i53 + j8);
                int i70 = i54 * digestSize;
                System.arraycopy(bArr3, i70, bArr12, 0, digestSize);
                while (j8 < j7) {
                    this.messDigestOTS.update(bArr12, 0, bArr12.length);
                    bArr12 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr12, 0);
                    j8++;
                    j7 = j7;
                }
                System.arraycopy(bArr12, 0, bArr6, i70, digestSize);
                i54++;
            } else {
                i = i66;
            }
            int i71 = (i65 << this.f9882w) - i53;
            int i72 = 0;
            while (i72 < i64) {
                int i73 = i54 * digestSize;
                System.arraycopy(bArr3, i73, bArr12, 0, digestSize);
                byte[] bArr13 = bArr6;
                for (long j9 = i71 & i; j9 < i; j9++) {
                    this.messDigestOTS.update(bArr12, 0, bArr12.length);
                    bArr12 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr12, 0);
                }
                System.arraycopy(bArr12, 0, bArr13, i73, digestSize);
                int i74 = this.f9882w;
                i71 >>>= i74;
                i54++;
                i72 += i74;
                bArr6 = bArr13;
            }
        }
        byte[] bArr14 = bArr6;
        byte[] bArr15 = new byte[digestSize];
        this.messDigestOTS.update(bArr14, 0, bArr14.length);
        byte[] bArr16 = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(bArr16, 0);
        return bArr16;
    }

    public int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public int getSignatureLength() {
        int digestSize = this.messDigestOTS.getDigestSize();
        int i = this.f9882w;
        int i2 = ((digestSize << 3) + (i - 1)) / i;
        int log = getLog((i2 << i) + 1);
        return digestSize * (i2 + (((log + r3) - 1) / this.f9882w));
    }
}
