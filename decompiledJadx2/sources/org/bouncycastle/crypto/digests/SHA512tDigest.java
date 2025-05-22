package org.bouncycastle.crypto.digests;

import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.MemoableResetException;
import org.bouncycastle.util.Pack;

/* loaded from: classes9.dex */
public class SHA512tDigest extends LongDigest {
    private long H1t;
    private long H2t;
    private long H3t;
    private long H4t;
    private long H5t;
    private long H6t;
    private long H7t;
    private long H8t;
    private int digestLength;

    public SHA512tDigest(int i) {
        if (i >= 512) {
            throw new IllegalArgumentException("bitLength cannot be >= 512");
        }
        if (i % 8 != 0) {
            throw new IllegalArgumentException("bitLength needs to be a multiple of 8");
        }
        if (i == 384) {
            throw new IllegalArgumentException("bitLength cannot be 384 use SHA384 instead");
        }
        this.digestLength = i / 8;
        tIvGenerate(this.digestLength * 8);
        reset();
    }

    public SHA512tDigest(SHA512tDigest sHA512tDigest) {
        super(sHA512tDigest);
        this.digestLength = sHA512tDigest.digestLength;
        reset(sHA512tDigest);
    }

    public SHA512tDigest(byte[] bArr) {
        this(readDigestLength(bArr));
        restoreState(bArr);
    }

    private static void intToBigEndian(int i, byte[] bArr, int i2, int i3) {
        int min = Math.min(4, i3);
        while (true) {
            min--;
            if (min < 0) {
                return;
            } else {
                bArr[i2 + min] = (byte) (i >>> ((3 - min) * 8));
            }
        }
    }

    private static void longToBigEndian(long j, byte[] bArr, int i, int i2) {
        if (i2 > 0) {
            intToBigEndian((int) (j >>> 32), bArr, i, i2);
            if (i2 > 4) {
                intToBigEndian((int) (j & 4294967295L), bArr, i + 4, i2 - 4);
            }
        }
    }

    private static int readDigestLength(byte[] bArr) {
        return Pack.bigEndianToInt(bArr, bArr.length - 4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x007f, code lost:
    
        if (r4 > 10) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void tIvGenerate(int i) {
        this.f9206H1 = -3482333909917012819L;
        this.f9207H2 = 2216346199247487646L;
        this.f9208H3 = -7364697282686394994L;
        this.f9209H4 = 65953792586715988L;
        this.f9210H5 = -816286391624063116L;
        this.f9211H6 = 4512832404995164602L;
        this.f9212H7 = -5033199132376557362L;
        this.f9213H8 = -124578254951840548L;
        update(TarConstants.LF_GNUTYPE_SPARSE);
        update(PrinterUtils.BarCode.CODE93);
        update((byte) 65);
        update((byte) 45);
        update(TarConstants.LF_DIR);
        update(TarConstants.LF_LINK);
        update(TarConstants.LF_SYMLINK);
        update((byte) 47);
        if (i > 100) {
            update((byte) ((i / 100) + 48));
            i %= 100;
        }
        update((byte) ((i / 10) + 48));
        i %= 10;
        update((byte) (i + 48));
        finish();
        this.H1t = this.f9206H1;
        this.H2t = this.f9207H2;
        this.H3t = this.f9208H3;
        this.H4t = this.f9209H4;
        this.H5t = this.f9210H5;
        this.H6t = this.f9211H6;
        this.H7t = this.f9212H7;
        this.H8t = this.f9213H8;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SHA512tDigest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        longToBigEndian(this.f9206H1, bArr, i, this.digestLength);
        longToBigEndian(this.f9207H2, bArr, i + 8, this.digestLength - 8);
        longToBigEndian(this.f9208H3, bArr, i + 16, this.digestLength - 16);
        longToBigEndian(this.f9209H4, bArr, i + 24, this.digestLength - 24);
        longToBigEndian(this.f9210H5, bArr, i + 32, this.digestLength - 32);
        longToBigEndian(this.f9211H6, bArr, i + 40, this.digestLength - 40);
        longToBigEndian(this.f9212H7, bArr, i + 48, this.digestLength - 48);
        longToBigEndian(this.f9213H8, bArr, i + 56, this.digestLength - 56);
        reset();
        return this.digestLength;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA-512/" + Integer.toString(this.digestLength * 8);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.digestLength;
    }

    @Override // org.bouncycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        int encodedStateSize = getEncodedStateSize();
        byte[] bArr = new byte[encodedStateSize + 4];
        populateState(bArr);
        Pack.intToBigEndian(this.digestLength * 8, bArr, encodedStateSize);
        return bArr;
    }

    @Override // org.bouncycastle.crypto.digests.LongDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f9206H1 = this.H1t;
        this.f9207H2 = this.H2t;
        this.f9208H3 = this.H3t;
        this.f9209H4 = this.H4t;
        this.f9210H5 = this.H5t;
        this.f9211H6 = this.H6t;
        this.f9212H7 = this.H7t;
        this.f9213H8 = this.H8t;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        SHA512tDigest sHA512tDigest = (SHA512tDigest) memoable;
        if (this.digestLength != sHA512tDigest.digestLength) {
            throw new MemoableResetException("digestLength inappropriate in other");
        }
        super.copyIn(sHA512tDigest);
        this.H1t = sHA512tDigest.H1t;
        this.H2t = sHA512tDigest.H2t;
        this.H3t = sHA512tDigest.H3t;
        this.H4t = sHA512tDigest.H4t;
        this.H5t = sHA512tDigest.H5t;
        this.H6t = sHA512tDigest.H6t;
        this.H7t = sHA512tDigest.H7t;
        this.H8t = sHA512tDigest.H8t;
    }
}
