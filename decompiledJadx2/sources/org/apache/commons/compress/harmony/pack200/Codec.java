package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes9.dex */
public abstract class Codec {
    public static final BHSDCodec BCI5 = new BHSDCodec(5, 4);
    public static final BHSDCodec BRANCH5 = new BHSDCodec(5, 4, 2);
    public static final BHSDCodec BYTE1 = new BHSDCodec(1, 256);
    public static final BHSDCodec CHAR3 = new BHSDCodec(3, 128);
    public static final BHSDCodec DELTA5 = new BHSDCodec(5, 64, 1, 1);
    public static final BHSDCodec MDELTA5 = new BHSDCodec(5, 64, 2, 1);
    public static final BHSDCodec SIGNED5 = new BHSDCodec(5, 64, 1);
    public static final BHSDCodec UDELTA5 = new BHSDCodec(5, 64, 0, 1);
    public static final BHSDCodec UNSIGNED5 = new BHSDCodec(5, 64);
    public int lastBandLength;

    public abstract int decode(InputStream inputStream) throws IOException, Pack200Exception;

    public abstract int decode(InputStream inputStream, long j) throws IOException, Pack200Exception;

    public abstract byte[] encode(int i) throws Pack200Exception;

    public abstract byte[] encode(int i, int i2) throws Pack200Exception;

    public int[] decodeInts(int i, InputStream inputStream) throws IOException, Pack200Exception {
        this.lastBandLength = 0;
        int[] iArr = new int[i];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = decode(inputStream, i2);
            iArr[i3] = i2;
        }
        return iArr;
    }

    public int[] decodeInts(int i, InputStream inputStream, int i2) throws IOException, Pack200Exception {
        int i3 = i + 1;
        int[] iArr = new int[i3];
        iArr[0] = i2;
        for (int i4 = 1; i4 < i3; i4++) {
            i2 = decode(inputStream, i2);
            iArr[i4] = i2;
        }
        return iArr;
    }

    public byte[] encode(int[] iArr) throws Pack200Exception {
        byte[][] bArr = new byte[iArr.length];
        int i = 0;
        int i2 = 0;
        while (i < iArr.length) {
            bArr[i] = encode(iArr[i], i > 0 ? iArr[i - 1] : 0);
            i2 += bArr[i].length;
            i++;
        }
        byte[] bArr2 = new byte[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < bArr.length; i4++) {
            System.arraycopy(bArr[i4], 0, bArr2, i3, bArr[i4].length);
            i3 += bArr[i4].length;
        }
        return bArr2;
    }
}
