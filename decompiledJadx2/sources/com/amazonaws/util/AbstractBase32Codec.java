package com.amazonaws.util;

import com.google.common.base.Ascii;

/* loaded from: classes.dex */
abstract class AbstractBase32Codec implements Codec {
    private static final int BITS_3 = 3;
    private static final int BITS_4 = 4;
    private static final int BITS_5 = 5;
    private static final int BIT_MULTIPLIER = 8;
    private static final int MASK_2BITS = 3;
    private static final int MASK_3BITS = 7;
    private static final int MASK_4BITS = 15;
    private static final int MASK_5BITS = 31;
    private static final byte PAD = 61;
    private final byte[] alpahbets;

    protected abstract int pos(byte b);

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractBase32Codec(byte[] bArr) {
        this.alpahbets = bArr;
    }

    @Override // com.amazonaws.util.Codec
    public final byte[] encode(byte[] bArr) {
        int length = bArr.length / 5;
        int length2 = bArr.length % 5;
        int i = 0;
        if (length2 == 0) {
            byte[] bArr2 = new byte[length * 8];
            int i2 = 0;
            while (i < bArr.length) {
                encode5bytes(bArr, i, bArr2, i2);
                i += 5;
                i2 += 8;
            }
            return bArr2;
        }
        byte[] bArr3 = new byte[(length + 1) * 8];
        int i3 = 0;
        while (i < bArr.length - length2) {
            encode5bytes(bArr, i, bArr3, i3);
            i += 5;
            i3 += 8;
        }
        if (length2 == 1) {
            encode1byte(bArr, i, bArr3, i3);
        } else if (length2 == 2) {
            encode2bytes(bArr, i, bArr3, i3);
        } else if (length2 == 3) {
            encode3bytes(bArr, i, bArr3, i3);
        } else if (length2 == 4) {
            encode4bytes(bArr, i, bArr3, i3);
        }
        return bArr3;
    }

    private final void encode5bytes(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = i2 + 1;
        byte[] bArr3 = this.alpahbets;
        int i4 = i + 1;
        byte b = bArr[i];
        bArr2[i2] = bArr3[(b >>> 3) & 31];
        int i5 = i3 + 1;
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        bArr2[i3] = bArr3[((b & 7) << 2) | ((b2 >>> 6) & 3)];
        int i7 = i5 + 1;
        bArr2[i5] = bArr3[(b2 >>> 1) & 31];
        int i8 = i7 + 1;
        int i9 = (b2 & 1) << 4;
        int i10 = i6 + 1;
        byte b3 = bArr[i6];
        bArr2[i7] = bArr3[i9 | ((b3 >>> 4) & 15)];
        int i11 = i8 + 1;
        int i12 = (b3 & 15) << 1;
        int i13 = i10 + 1;
        byte b4 = bArr[i10];
        bArr2[i8] = bArr3[i12 | ((b4 >>> 7) & 1)];
        int i14 = i11 + 1;
        bArr2[i11] = bArr3[(b4 >>> 2) & 31];
        byte b5 = bArr[i13];
        bArr2[i14] = bArr3[((b4 & 3) << 3) | ((b5 >>> 5) & 7)];
        bArr2[i14 + 1] = bArr3[b5 & Ascii.f1926US];
    }

    private final void encode4bytes(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = i2 + 1;
        byte[] bArr3 = this.alpahbets;
        int i4 = i + 1;
        byte b = bArr[i];
        bArr2[i2] = bArr3[(b >>> 3) & 31];
        int i5 = i3 + 1;
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        bArr2[i3] = bArr3[((b & 7) << 2) | ((b2 >>> 6) & 3)];
        int i7 = i5 + 1;
        bArr2[i5] = bArr3[(b2 >>> 1) & 31];
        int i8 = i7 + 1;
        int i9 = (b2 & 1) << 4;
        int i10 = i6 + 1;
        byte b3 = bArr[i6];
        bArr2[i7] = bArr3[i9 | ((b3 >>> 4) & 15)];
        int i11 = i8 + 1;
        byte b4 = bArr[i10];
        bArr2[i8] = bArr3[((b3 & 15) << 1) | ((b4 >>> 7) & 1)];
        int i12 = i11 + 1;
        bArr2[i11] = bArr3[(b4 >>> 2) & 31];
        bArr2[i12] = bArr3[(b4 & 3) << 3];
        bArr2[i12 + 1] = 61;
    }

    private final void encode3bytes(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = i2 + 1;
        byte[] bArr3 = this.alpahbets;
        int i4 = i + 1;
        byte b = bArr[i];
        bArr2[i2] = bArr3[(b >>> 3) & 31];
        int i5 = i3 + 1;
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        bArr2[i3] = bArr3[((b & 7) << 2) | ((b2 >>> 6) & 3)];
        int i7 = i5 + 1;
        bArr2[i5] = bArr3[(b2 >>> 1) & 31];
        int i8 = i7 + 1;
        byte b3 = bArr[i6];
        bArr2[i7] = bArr3[((b2 & 1) << 4) | ((b3 >>> 4) & 15)];
        int i9 = i8 + 1;
        bArr2[i8] = bArr3[(b3 & 15) << 1];
        int i10 = 0;
        while (i10 < 3) {
            bArr2[i9] = 61;
            i10++;
            i9++;
        }
    }

    private final void encode2bytes(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = i2 + 1;
        byte[] bArr3 = this.alpahbets;
        int i4 = i + 1;
        byte b = bArr[i];
        bArr2[i2] = bArr3[(b >>> 3) & 31];
        int i5 = i3 + 1;
        byte b2 = bArr[i4];
        bArr2[i3] = bArr3[((b & 7) << 2) | ((b2 >>> 6) & 3)];
        int i6 = i5 + 1;
        bArr2[i5] = bArr3[(b2 >>> 1) & 31];
        int i7 = i6 + 1;
        bArr2[i6] = bArr3[(b2 & 1) << 4];
        int i8 = 0;
        while (i8 < 4) {
            bArr2[i7] = 61;
            i8++;
            i7++;
        }
    }

    private final void encode1byte(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = i2 + 1;
        byte[] bArr3 = this.alpahbets;
        byte b = bArr[i];
        bArr2[i2] = bArr3[(b >>> 3) & 31];
        int i4 = i3 + 1;
        bArr2[i3] = bArr3[(b & 7) << 2];
        int i5 = 0;
        while (i5 < 6) {
            bArr2[i4] = 61;
            i5++;
            i4++;
        }
    }

    private final void decode5bytes(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = i2 + 1;
        int i4 = i + 1;
        int pos = pos(bArr[i]) << 3;
        int i5 = i4 + 1;
        int pos2 = pos(bArr[i4]);
        bArr2[i2] = (byte) (pos | ((pos2 >>> 2) & 7));
        int i6 = i3 + 1;
        int i7 = (pos2 & 3) << 6;
        int i8 = i5 + 1;
        int pos3 = i7 | (pos(bArr[i5]) << 1);
        int i9 = i8 + 1;
        int pos4 = pos(bArr[i8]);
        bArr2[i3] = (byte) (pos3 | ((pos4 >>> 4) & 1));
        int i10 = i6 + 1;
        int i11 = (pos4 & 15) << 4;
        int i12 = i9 + 1;
        int pos5 = pos(bArr[i9]);
        bArr2[i6] = (byte) (i11 | ((pos5 >>> 1) & 15));
        int i13 = (pos5 & 1) << 7;
        int i14 = i12 + 1;
        int pos6 = i13 | (pos(bArr[i12]) << 2);
        int i15 = i14 + 1;
        int pos7 = pos(bArr[i14]);
        bArr2[i10] = (byte) (pos6 | ((pos7 >>> 3) & 3));
        bArr2[i10 + 1] = (byte) (pos(bArr[i15]) | ((pos7 & 7) << 5));
    }

    private final void decode1to4bytes(int i, byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4 = i3 + 1;
        int i5 = i2 + 1;
        int pos = pos(bArr[i2]) << 3;
        int i6 = i5 + 1;
        int pos2 = pos(bArr[i5]);
        bArr2[i3] = (byte) (pos | ((pos2 >>> 2) & 7));
        if (i == 1) {
            CodecUtils.sanityCheckLastPos(pos2, 3);
            return;
        }
        int i7 = i4 + 1;
        int i8 = i6 + 1;
        int pos3 = ((pos2 & 3) << 6) | (pos(bArr[i6]) << 1);
        int i9 = i8 + 1;
        int pos4 = pos(bArr[i8]);
        bArr2[i4] = (byte) (pos3 | ((pos4 >>> 4) & 1));
        if (i == 2) {
            CodecUtils.sanityCheckLastPos(pos4, 15);
            return;
        }
        int i10 = i7 + 1;
        int i11 = i9 + 1;
        int pos5 = pos(bArr[i9]);
        bArr2[i7] = (byte) ((15 & (pos5 >>> 1)) | ((pos4 & 15) << 4));
        if (i == 3) {
            CodecUtils.sanityCheckLastPos(pos5, 1);
            return;
        }
        int pos6 = ((pos5 & 1) << 7) | (pos(bArr[i11]) << 2);
        int pos7 = pos(bArr[i11 + 1]);
        bArr2[i10] = (byte) (pos6 | ((pos7 >>> 3) & 3));
        CodecUtils.sanityCheckLastPos(pos7, 7);
    }

    @Override // com.amazonaws.util.Codec
    public final byte[] decode(byte[] bArr, int i) {
        int i2;
        if (i % 8 != 0) {
            throw new IllegalArgumentException("Input is expected to be encoded in multiple of 8 bytes but found: " + i);
        }
        int i3 = i - 1;
        int i4 = 0;
        while (i4 < 6 && i3 > -1 && bArr[i3] == 61) {
            i3--;
            i4++;
        }
        int i5 = 4;
        if (i4 != 0) {
            if (i4 != 1) {
                if (i4 == 3) {
                    i2 = 3;
                } else if (i4 == 4) {
                    i5 = 2;
                } else {
                    if (i4 != 6) {
                        throw new IllegalArgumentException("Invalid number of paddings " + i4);
                    }
                    i2 = 1;
                }
            }
            i2 = i5;
        } else {
            i2 = 5;
        }
        byte[] bArr2 = new byte[((i / 8) * 5) - (5 - i2)];
        int i6 = 0;
        int i7 = 0;
        while (i7 < bArr2.length - (i2 % 5)) {
            decode5bytes(bArr, i6, bArr2, i7);
            i6 += 8;
            i7 += 5;
        }
        if (i2 < 5) {
            decode1to4bytes(i2, bArr, i6, bArr2, i7);
        }
        return bArr2;
    }
}
