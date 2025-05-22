package org.bouncycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes9.dex */
public class GMSSRootSig {
    private long big8;
    private int checksum;
    private int counter;
    private GMSSRandom gmssRandom;
    private byte[] hash;
    private int height;

    /* renamed from: ii */
    private int f9878ii;

    /* renamed from: k */
    private int f9879k;
    private int keysize;
    private int mdsize;
    private Digest messDigestOTS;
    private int messagesize;
    private byte[] privateKeyOTS;

    /* renamed from: r */
    private int f9880r;
    private byte[] seed;
    private byte[] sign;
    private int steps;
    private int test;
    private long test8;

    /* renamed from: w */
    private int f9881w;

    public GMSSRootSig(Digest digest, int i, int i2) {
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        this.f9881w = i;
        this.height = i2;
        this.f9879k = (1 << i) - 1;
        this.messagesize = (int) Math.ceil((this.mdsize << 3) / i);
    }

    public GMSSRootSig(Digest digest, byte[][] bArr, int[] iArr) {
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.counter = iArr[0];
        this.test = iArr[1];
        this.f9878ii = iArr[2];
        this.f9880r = iArr[3];
        this.steps = iArr[4];
        this.keysize = iArr[5];
        this.height = iArr[6];
        this.f9881w = iArr[7];
        this.checksum = iArr[8];
        this.mdsize = this.messDigestOTS.getDigestSize();
        int i = this.f9881w;
        this.f9879k = (1 << i) - 1;
        this.messagesize = (int) Math.ceil((this.mdsize << 3) / i);
        this.privateKeyOTS = bArr[0];
        this.seed = bArr[1];
        this.hash = bArr[2];
        this.sign = bArr[3];
        this.test8 = ((bArr[4][2] & 255) << 16) | (bArr[4][0] & 255) | ((bArr[4][1] & 255) << 8) | ((bArr[4][3] & 255) << 24) | ((bArr[4][4] & 255) << 32) | ((bArr[4][5] & 255) << 40) | ((bArr[4][6] & 255) << 48) | ((bArr[4][7] & 255) << 56);
        this.big8 = ((bArr[4][15] & 255) << 56) | (bArr[4][8] & 255) | ((bArr[4][9] & 255) << 8) | ((bArr[4][10] & 255) << 16) | ((bArr[4][11] & 255) << 24) | ((bArr[4][12] & 255) << 32) | ((bArr[4][13] & 255) << 40) | ((bArr[4][14] & 255) << 48);
    }

    private void oneStep() {
        long j;
        int i = this.f9881w;
        if (8 % i == 0) {
            int i2 = this.test;
            if (i2 == 0) {
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
                int i3 = this.f9878ii;
                if (i3 < this.mdsize) {
                    byte[] bArr = this.hash;
                    this.test = bArr[i3] & this.f9879k;
                    bArr[i3] = (byte) (bArr[i3] >>> this.f9881w);
                } else {
                    int i4 = this.checksum;
                    this.test = this.f9879k & i4;
                    this.checksum = i4 >>> this.f9881w;
                }
            } else if (i2 > 0) {
                Digest digest = this.messDigestOTS;
                byte[] bArr2 = this.privateKeyOTS;
                digest.update(bArr2, 0, bArr2.length);
                this.privateKeyOTS = new byte[this.messDigestOTS.getDigestSize()];
                this.messDigestOTS.doFinal(this.privateKeyOTS, 0);
                this.test--;
            }
            if (this.test == 0) {
                byte[] bArr3 = this.privateKeyOTS;
                byte[] bArr4 = this.sign;
                int i5 = this.counter;
                int i6 = this.mdsize;
                System.arraycopy(bArr3, 0, bArr4, i5 * i6, i6);
                this.counter++;
                if (this.counter % (8 / this.f9881w) == 0) {
                    this.f9878ii++;
                    return;
                }
                return;
            }
            return;
        }
        if (i < 8) {
            int i7 = this.test;
            if (i7 == 0) {
                int i8 = this.counter;
                if (i8 % 8 == 0) {
                    int i9 = this.f9878ii;
                    int i10 = this.mdsize;
                    if (i9 < i10) {
                        this.big8 = 0L;
                        if (i8 < ((i10 / i) << 3)) {
                            for (int i11 = 0; i11 < this.f9881w; i11++) {
                                long j2 = this.big8;
                                byte[] bArr5 = this.hash;
                                int i12 = this.f9878ii;
                                this.big8 = j2 ^ ((bArr5[i12] & 255) << (i11 << 3));
                                this.f9878ii = i12 + 1;
                            }
                        } else {
                            for (int i13 = 0; i13 < this.mdsize % this.f9881w; i13++) {
                                long j3 = this.big8;
                                byte[] bArr6 = this.hash;
                                int i14 = this.f9878ii;
                                this.big8 = j3 ^ ((bArr6[i14] & 255) << (i13 << 3));
                                this.f9878ii = i14 + 1;
                            }
                        }
                    }
                }
                if (this.counter == this.messagesize) {
                    this.big8 = this.checksum;
                }
                this.test = (int) (this.big8 & this.f9879k);
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else if (i7 > 0) {
                Digest digest2 = this.messDigestOTS;
                byte[] bArr7 = this.privateKeyOTS;
                digest2.update(bArr7, 0, bArr7.length);
                this.privateKeyOTS = new byte[this.messDigestOTS.getDigestSize()];
                this.messDigestOTS.doFinal(this.privateKeyOTS, 0);
                this.test--;
            }
            if (this.test != 0) {
                return;
            }
            byte[] bArr8 = this.privateKeyOTS;
            byte[] bArr9 = this.sign;
            int i15 = this.counter;
            int i16 = this.mdsize;
            System.arraycopy(bArr8, 0, bArr9, i15 * i16, i16);
            this.big8 >>>= this.f9881w;
        } else {
            if (i >= 57) {
                return;
            }
            long j4 = this.test8;
            if (j4 == 0) {
                this.big8 = 0L;
                this.f9878ii = 0;
                int i17 = this.f9880r;
                int i18 = i17 % 8;
                int i19 = i17 >>> 3;
                int i20 = this.mdsize;
                if (i19 < i20) {
                    if (i17 <= (i20 << 3) - i) {
                        this.f9880r = i17 + i;
                        i20 = (this.f9880r + 7) >>> 3;
                    } else {
                        this.f9880r = i17 + i;
                    }
                    while (true) {
                        j = this.big8;
                        if (i19 >= i20) {
                            break;
                        }
                        int i21 = this.hash[i19] & 255;
                        int i22 = this.f9878ii;
                        this.big8 = j ^ (i21 << (i22 << 3));
                        this.f9878ii = i22 + 1;
                        i19++;
                    }
                    this.big8 = j >>> i18;
                    this.test8 = this.big8 & this.f9879k;
                } else {
                    int i23 = this.checksum;
                    this.test8 = this.f9879k & i23;
                    this.checksum = i23 >>> i;
                }
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else if (j4 > 0) {
                Digest digest3 = this.messDigestOTS;
                byte[] bArr10 = this.privateKeyOTS;
                digest3.update(bArr10, 0, bArr10.length);
                this.privateKeyOTS = new byte[this.messDigestOTS.getDigestSize()];
                this.messDigestOTS.doFinal(this.privateKeyOTS, 0);
                this.test8--;
            }
            if (this.test8 != 0) {
                return;
            }
            byte[] bArr11 = this.privateKeyOTS;
            byte[] bArr12 = this.sign;
            int i24 = this.counter;
            int i25 = this.mdsize;
            System.arraycopy(bArr11, 0, bArr12, i24 * i25, i25);
        }
        this.counter++;
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

    public byte[] getSig() {
        return this.sign;
    }

    public byte[][] getStatByte() {
        byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) byte.class, 5, this.mdsize);
        bArr[0] = this.privateKeyOTS;
        bArr[1] = this.seed;
        bArr[2] = this.hash;
        bArr[3] = this.sign;
        bArr[4] = getStatLong();
        return bArr;
    }

    public int[] getStatInt() {
        return new int[]{this.counter, this.test, this.f9878ii, this.f9880r, this.steps, this.keysize, this.height, this.f9881w, this.checksum};
    }

    public byte[] getStatLong() {
        long j = this.test8;
        long j2 = this.big8;
        return new byte[]{(byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255), (byte) ((j >> 32) & 255), (byte) ((j >> 40) & 255), (byte) ((j >> 48) & 255), (byte) ((j >> 56) & 255), (byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 24) & 255), (byte) ((j2 >> 32) & 255), (byte) ((j2 >> 40) & 255), (byte) ((j2 >> 48) & 255), (byte) ((j2 >> 56) & 255)};
    }

    public void initSign(byte[] bArr, byte[] bArr2) {
        int i;
        int i2;
        this.hash = new byte[this.mdsize];
        this.messDigestOTS.update(bArr2, 0, bArr2.length);
        this.hash = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(this.hash, 0);
        int i3 = this.mdsize;
        byte[] bArr3 = new byte[i3];
        System.arraycopy(this.hash, 0, bArr3, 0, i3);
        int log = getLog((this.messagesize << this.f9881w) + 1);
        int i4 = this.f9881w;
        if (8 % i4 == 0) {
            int i5 = 8 / i4;
            int i6 = 0;
            i = 0;
            while (i6 < this.mdsize) {
                int i7 = i;
                for (int i8 = 0; i8 < i5; i8++) {
                    i7 += bArr3[i6] & this.f9879k;
                    bArr3[i6] = (byte) (bArr3[i6] >>> this.f9881w);
                }
                i6++;
                i = i7;
            }
            this.checksum = (this.messagesize << this.f9881w) - i;
            int i9 = this.checksum;
            int i10 = 0;
            while (i10 < log) {
                i += this.f9879k & i9;
                int i11 = this.f9881w;
                i9 >>>= i11;
                i10 += i11;
            }
        } else if (i4 < 8) {
            int i12 = this.mdsize / i4;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            while (i13 < i12) {
                int i16 = i14;
                long j = 0;
                for (int i17 = 0; i17 < this.f9881w; i17++) {
                    j ^= (bArr3[i16] & 255) << (i17 << 3);
                    i16++;
                }
                for (int i18 = 0; i18 < 8; i18++) {
                    i15 += (int) (this.f9879k & j);
                    j >>>= this.f9881w;
                }
                i13++;
                i14 = i16;
            }
            int i19 = this.mdsize % this.f9881w;
            long j2 = 0;
            for (int i20 = 0; i20 < i19; i20++) {
                j2 ^= (bArr3[i14] & 255) << (i20 << 3);
                i14++;
            }
            int i21 = i19 << 3;
            int i22 = 0;
            while (i22 < i21) {
                i15 += (int) (this.f9879k & j2);
                int i23 = this.f9881w;
                j2 >>>= i23;
                i22 += i23;
            }
            this.checksum = (this.messagesize << this.f9881w) - i15;
            int i24 = this.checksum;
            int i25 = 0;
            while (i25 < log) {
                i15 += this.f9879k & i24;
                int i26 = this.f9881w;
                i24 >>>= i26;
                i25 += i26;
            }
            i = i15;
        } else if (i4 < 57) {
            int i27 = 0;
            i = 0;
            while (true) {
                i2 = this.mdsize;
                int i28 = this.f9881w;
                if (i27 > (i2 << 3) - i28) {
                    break;
                }
                int i29 = i27 % 8;
                i27 += i28;
                int i30 = 0;
                long j3 = 0;
                for (int i31 = i27 >>> 3; i31 < ((i27 + 7) >>> 3); i31++) {
                    j3 ^= (bArr3[i31] & 255) << (i30 << 3);
                    i30++;
                }
                i = (int) (i + ((j3 >>> i29) & this.f9879k));
            }
            int i32 = i27 >>> 3;
            if (i32 < i2) {
                int i33 = i27 % 8;
                int i34 = 0;
                long j4 = 0;
                while (i32 < this.mdsize) {
                    j4 ^= (bArr3[i32] & 255) << (i34 << 3);
                    i34++;
                    i32++;
                }
                i = (int) (i + ((j4 >>> i33) & this.f9879k));
            }
            this.checksum = (this.messagesize << this.f9881w) - i;
            int i35 = this.checksum;
            int i36 = 0;
            while (i36 < log) {
                i += this.f9879k & i35;
                int i37 = this.f9881w;
                i35 >>>= i37;
                i36 += i37;
            }
        } else {
            i = 0;
        }
        this.keysize = this.messagesize + ((int) Math.ceil(log / this.f9881w));
        this.steps = (int) Math.ceil((this.keysize + i) / (1 << this.height));
        int i38 = this.keysize;
        int i39 = this.mdsize;
        this.sign = new byte[i38 * i39];
        this.counter = 0;
        this.test = 0;
        this.f9878ii = 0;
        this.test8 = 0L;
        this.f9880r = 0;
        this.privateKeyOTS = new byte[i39];
        this.seed = new byte[i39];
        System.arraycopy(bArr, 0, this.seed, 0, i39);
    }

    public String toString() {
        String str = "" + this.big8 + "  ";
        int[] statInt = getStatInt();
        byte[][] statByte = getStatByte();
        String str2 = str;
        for (int i = 0; i < 9; i++) {
            str2 = str2 + statInt[i] + " ";
        }
        for (int i2 = 0; i2 < 5; i2++) {
            str2 = str2 + new String(Hex.encode(statByte[i2])) + " ";
        }
        return str2;
    }

    public boolean updateSign() {
        for (int i = 0; i < this.steps; i++) {
            if (this.counter < this.keysize) {
                oneStep();
            }
            if (this.counter == this.keysize) {
                return true;
            }
        }
        return false;
    }
}
