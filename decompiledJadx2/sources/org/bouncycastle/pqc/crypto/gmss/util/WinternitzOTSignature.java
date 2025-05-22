package org.bouncycastle.pqc.crypto.gmss.util;

import java.lang.reflect.Array;
import org.bouncycastle.crypto.Digest;

/* loaded from: classes9.dex */
public class WinternitzOTSignature {
    private int checksumsize;
    private GMSSRandom gmssRandom;
    private int keysize;
    private int mdsize;
    private Digest messDigestOTS;
    private int messagesize;
    private byte[][] privateKeyOTS;

    /* renamed from: w */
    private int f9883w;

    public WinternitzOTSignature(byte[] bArr, Digest digest, int i) {
        this.f9883w = i;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        double d = i;
        this.messagesize = (int) Math.ceil((this.mdsize << 3) / d);
        this.checksumsize = getLog((this.messagesize << i) + 1);
        this.keysize = this.messagesize + ((int) Math.ceil(this.checksumsize / d));
        this.privateKeyOTS = (byte[][]) Array.newInstance((Class<?>) byte.class, this.keysize, this.mdsize);
        byte[] bArr2 = new byte[this.mdsize];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        for (int i2 = 0; i2 < this.keysize; i2++) {
            this.privateKeyOTS[i2] = this.gmssRandom.nextSeed(bArr2);
        }
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

    public byte[][] getPrivateKey() {
        return this.privateKeyOTS;
    }

    public byte[] getPublicKey() {
        int i = this.keysize;
        int i2 = this.mdsize;
        byte[] bArr = new byte[i * i2];
        byte[] bArr2 = new byte[i2];
        int i3 = 1 << this.f9883w;
        for (int i4 = 0; i4 < this.keysize; i4++) {
            Digest digest = this.messDigestOTS;
            byte[][] bArr3 = this.privateKeyOTS;
            digest.update(bArr3[i4], 0, bArr3[i4].length);
            byte[] bArr4 = new byte[this.messDigestOTS.getDigestSize()];
            this.messDigestOTS.doFinal(bArr4, 0);
            for (int i5 = 2; i5 < i3; i5++) {
                this.messDigestOTS.update(bArr4, 0, bArr4.length);
                bArr4 = new byte[this.messDigestOTS.getDigestSize()];
                this.messDigestOTS.doFinal(bArr4, 0);
            }
            int i6 = this.mdsize;
            System.arraycopy(bArr4, 0, bArr, i6 * i4, i6);
        }
        this.messDigestOTS.update(bArr, 0, bArr.length);
        byte[] bArr5 = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(bArr5, 0);
        return bArr5;
    }

    public byte[] getSignature(byte[] bArr) {
        int i;
        int i2 = this.keysize;
        int i3 = this.mdsize;
        byte[] bArr2 = new byte[i2 * i3];
        byte[] bArr3 = new byte[i3];
        this.messDigestOTS.update(bArr, 0, bArr.length);
        byte[] bArr4 = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(bArr4, 0);
        int i4 = this.f9883w;
        if (8 % i4 == 0) {
            int i5 = 8 / i4;
            int i6 = (1 << i4) - 1;
            int i7 = 0;
            int i8 = 0;
            byte[] bArr5 = new byte[this.mdsize];
            int i9 = 0;
            while (i9 < bArr4.length) {
                byte[] bArr6 = bArr5;
                int i10 = i8;
                int i11 = i7;
                for (int i12 = 0; i12 < i5; i12++) {
                    int i13 = bArr4[i9] & i6;
                    i11 += i13;
                    System.arraycopy(this.privateKeyOTS[i10], 0, bArr6, 0, this.mdsize);
                    while (i13 > 0) {
                        this.messDigestOTS.update(bArr6, 0, bArr6.length);
                        bArr6 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr6, 0);
                        i13--;
                    }
                    int i14 = this.mdsize;
                    System.arraycopy(bArr6, 0, bArr2, i10 * i14, i14);
                    bArr4[i9] = (byte) (bArr4[i9] >>> this.f9883w);
                    i10++;
                }
                i9++;
                i7 = i11;
                i8 = i10;
                bArr5 = bArr6;
            }
            int i15 = (this.messagesize << this.f9883w) - i7;
            int i16 = 0;
            while (i16 < this.checksumsize) {
                System.arraycopy(this.privateKeyOTS[i8], 0, bArr5, 0, this.mdsize);
                for (int i17 = i15 & i6; i17 > 0; i17--) {
                    this.messDigestOTS.update(bArr5, 0, bArr5.length);
                    bArr5 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr5, 0);
                }
                int i18 = this.mdsize;
                System.arraycopy(bArr5, 0, bArr2, i8 * i18, i18);
                int i19 = this.f9883w;
                i15 >>>= i19;
                i8++;
                i16 += i19;
            }
        } else if (i4 < 8) {
            int i20 = this.mdsize;
            int i21 = i20 / i4;
            int i22 = (1 << i4) - 1;
            int i23 = 0;
            int i24 = 0;
            int i25 = 0;
            byte[] bArr7 = new byte[i20];
            int i26 = 0;
            while (i26 < i21) {
                int i27 = i23;
                long j = 0;
                for (int i28 = 0; i28 < this.f9883w; i28++) {
                    j ^= (bArr4[i27] & 255) << (i28 << 3);
                    i27++;
                }
                int i29 = 0;
                while (i29 < 8) {
                    int i30 = i29;
                    int i31 = (int) (i22 & j);
                    i25 += i31;
                    System.arraycopy(this.privateKeyOTS[i24], 0, bArr7, 0, this.mdsize);
                    while (i31 > 0) {
                        this.messDigestOTS.update(bArr7, 0, bArr7.length);
                        bArr7 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr7, 0);
                        i31--;
                    }
                    int i32 = this.mdsize;
                    System.arraycopy(bArr7, 0, bArr2, i24 * i32, i32);
                    j >>>= this.f9883w;
                    i24++;
                    i29 = i30 + 1;
                }
                i26++;
                i23 = i27;
            }
            int i33 = this.mdsize % this.f9883w;
            long j2 = 0;
            for (int i34 = 0; i34 < i33; i34++) {
                j2 ^= (bArr4[i23] & 255) << (i34 << 3);
                i23++;
            }
            int i35 = i33 << 3;
            int i36 = 0;
            while (i36 < i35) {
                int i37 = (int) (j2 & i22);
                i25 += i37;
                System.arraycopy(this.privateKeyOTS[i24], 0, bArr7, 0, this.mdsize);
                while (i37 > 0) {
                    this.messDigestOTS.update(bArr7, 0, bArr7.length);
                    bArr7 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr7, 0);
                    i37--;
                }
                int i38 = this.mdsize;
                System.arraycopy(bArr7, 0, bArr2, i24 * i38, i38);
                int i39 = this.f9883w;
                j2 >>>= i39;
                i24++;
                i36 += i39;
            }
            int i40 = (this.messagesize << this.f9883w) - i25;
            int i41 = 0;
            while (i41 < this.checksumsize) {
                System.arraycopy(this.privateKeyOTS[i24], 0, bArr7, 0, this.mdsize);
                for (int i42 = i40 & i22; i42 > 0; i42--) {
                    this.messDigestOTS.update(bArr7, 0, bArr7.length);
                    bArr7 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr7, 0);
                }
                int i43 = this.mdsize;
                System.arraycopy(bArr7, 0, bArr2, i24 * i43, i43);
                int i44 = this.f9883w;
                i40 >>>= i44;
                i24++;
                i41 += i44;
            }
        } else if (i4 < 57) {
            int i45 = this.mdsize;
            int i46 = (i45 << 3) - i4;
            int i47 = (1 << i4) - 1;
            int i48 = 0;
            int i49 = 0;
            byte[] bArr8 = new byte[i45];
            int i50 = 0;
            while (i50 <= i46) {
                int i51 = i50 % 8;
                i50 += this.f9883w;
                int i52 = 0;
                long j3 = 0;
                for (int i53 = i50 >>> 3; i53 < ((i50 + 7) >>> 3); i53++) {
                    j3 ^= (bArr4[i53] & 255) << (i52 << 3);
                    i52++;
                }
                long j4 = (j3 >>> i51) & i47;
                i49 = (int) (i49 + j4);
                System.arraycopy(this.privateKeyOTS[i48], 0, bArr8, 0, this.mdsize);
                while (j4 > 0) {
                    this.messDigestOTS.update(bArr8, 0, bArr8.length);
                    bArr8 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr8, 0);
                    j4--;
                }
                int i54 = this.mdsize;
                System.arraycopy(bArr8, 0, bArr2, i48 * i54, i54);
                i48++;
            }
            int i55 = i50 >>> 3;
            if (i55 < this.mdsize) {
                int i56 = i50 % 8;
                int i57 = 0;
                long j5 = 0;
                while (true) {
                    i = this.mdsize;
                    if (i55 >= i) {
                        break;
                    }
                    j5 ^= (bArr4[i55] & 255) << (i57 << 3);
                    i57++;
                    i55++;
                }
                long j6 = (j5 >>> i56) & i47;
                i49 = (int) (i49 + j6);
                System.arraycopy(this.privateKeyOTS[i48], 0, bArr8, 0, i);
                while (j6 > 0) {
                    this.messDigestOTS.update(bArr8, 0, bArr8.length);
                    bArr8 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr8, 0);
                    j6--;
                }
                int i58 = this.mdsize;
                System.arraycopy(bArr8, 0, bArr2, i48 * i58, i58);
                i48++;
            }
            int i59 = (this.messagesize << this.f9883w) - i49;
            int i60 = 0;
            while (i60 < this.checksumsize) {
                System.arraycopy(this.privateKeyOTS[i48], 0, bArr8, 0, this.mdsize);
                for (long j7 = i59 & i47; j7 > 0; j7--) {
                    this.messDigestOTS.update(bArr8, 0, bArr8.length);
                    bArr8 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr8, 0);
                }
                int i61 = this.mdsize;
                System.arraycopy(bArr8, 0, bArr2, i48 * i61, i61);
                int i62 = this.f9883w;
                i59 >>>= i62;
                i48++;
                i60 += i62;
            }
        }
        return bArr2;
    }
}
