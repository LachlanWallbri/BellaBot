package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes9.dex */
public class PopulationCodec extends Codec {
    private int[] favoured;
    private final Codec favouredCodec;

    /* renamed from: l */
    private int f8960l;
    private Codec tokenCodec;
    private final Codec unfavouredCodec;

    public PopulationCodec(Codec codec, Codec codec2, Codec codec3) {
        this.favouredCodec = codec;
        this.tokenCodec = codec2;
        this.unfavouredCodec = codec3;
    }

    public PopulationCodec(Codec codec, int i, Codec codec2) {
        if (i >= 256 || i <= 0) {
            throw new IllegalArgumentException("L must be between 1..255");
        }
        this.favouredCodec = codec;
        this.f8960l = i;
        this.unfavouredCodec = codec2;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int decode(InputStream inputStream) throws IOException, Pack200Exception {
        throw new Pack200Exception("Population encoding does not work unless the number of elements are known");
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int decode(InputStream inputStream, long j) throws IOException, Pack200Exception {
        throw new Pack200Exception("Population encoding does not work unless the number of elements are known");
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int[] decodeInts(int i, InputStream inputStream) throws IOException, Pack200Exception {
        this.lastBandLength = 0;
        this.favoured = new int[i];
        int i2 = -1;
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        while (true) {
            int decode = this.favouredCodec.decode(inputStream, i4);
            if (i2 <= -1 || (decode != i3 && decode != i4)) {
                i2++;
                this.favoured[i2] = decode;
                int abs = Math.abs(i3);
                int abs2 = Math.abs(decode);
                if (abs > abs2) {
                    i3 = decode;
                } else if (abs == abs2) {
                    i3 = abs;
                }
                i4 = decode;
            }
        }
        this.lastBandLength += i2;
        if (this.tokenCodec == null) {
            if (i2 < 256) {
                this.tokenCodec = Codec.BYTE1;
            } else {
                int i5 = 1;
                while (true) {
                    i5++;
                    if (i5 >= 5) {
                        break;
                    }
                    BHSDCodec bHSDCodec = new BHSDCodec(i5, 256 - this.f8960l, 0);
                    if (bHSDCodec.encodes(i2)) {
                        this.tokenCodec = bHSDCodec;
                        break;
                    }
                }
                if (this.tokenCodec == null) {
                    throw new Pack200Exception("Cannot calculate token codec from " + i2 + " and " + this.f8960l);
                }
            }
        }
        this.lastBandLength += i;
        int[] decodeInts = this.tokenCodec.decodeInts(i, inputStream);
        int i6 = 0;
        for (int i7 = 0; i7 < i; i7++) {
            int i8 = decodeInts[i7];
            if (i8 == 0) {
                this.lastBandLength++;
                i6 = this.unfavouredCodec.decode(inputStream, i6);
                decodeInts[i7] = i6;
            } else {
                decodeInts[i7] = this.favoured[i8 - 1];
            }
        }
        return decodeInts;
    }

    public int[] getFavoured() {
        return this.favoured;
    }

    public Codec getFavouredCodec() {
        return this.favouredCodec;
    }

    public Codec getUnfavouredCodec() {
        return this.unfavouredCodec;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public byte[] encode(int i, int i2) throws Pack200Exception {
        throw new Pack200Exception("Population encoding does not work unless the number of elements are known");
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public byte[] encode(int i) throws Pack200Exception {
        throw new Pack200Exception("Population encoding does not work unless the number of elements are known");
    }

    public byte[] encode(int[] iArr, int[] iArr2, int[] iArr3) throws Pack200Exception {
        int[] iArr4 = new int[iArr.length + 1];
        System.arraycopy(iArr, 0, iArr4, 0, iArr.length);
        iArr4[iArr4.length - 1] = iArr[iArr.length - 1];
        byte[] encode = this.favouredCodec.encode(iArr4);
        byte[] encode2 = this.tokenCodec.encode(iArr2);
        byte[] encode3 = this.unfavouredCodec.encode(iArr3);
        byte[] bArr = new byte[encode.length + encode2.length + encode3.length];
        System.arraycopy(encode, 0, bArr, 0, encode.length);
        System.arraycopy(encode2, 0, bArr, encode.length, encode2.length);
        System.arraycopy(encode3, 0, bArr, encode.length + encode2.length, encode3.length);
        return bArr;
    }

    public Codec getTokenCodec() {
        return this.tokenCodec;
    }
}
