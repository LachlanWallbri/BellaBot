package org.apache.commons.compress.harmony.pack200;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.mozilla.javascript.typedarrays.Conversions;

/* loaded from: classes9.dex */
public final class BHSDCodec extends Codec {

    /* renamed from: b */
    private final int f8949b;
    private long cardinality;

    /* renamed from: d */
    private final int f8950d;

    /* renamed from: h */
    private final int f8951h;

    /* renamed from: l */
    private final int f8952l;
    private final long largest;
    private final long[] powers;

    /* renamed from: s */
    private final int f8953s;
    private final long smallest;

    public BHSDCodec(int i, int i2) {
        this(i, i2, 0, 0);
    }

    public BHSDCodec(int i, int i2, int i3) {
        this(i, i2, i3, 0);
    }

    public BHSDCodec(int i, int i2, int i3, int i4) {
        if (i < 1 || i > 5) {
            throw new IllegalArgumentException("1<=b<=5");
        }
        if (i2 < 1 || i2 > 256) {
            throw new IllegalArgumentException("1<=h<=256");
        }
        if (i3 < 0 || i3 > 2) {
            throw new IllegalArgumentException("0<=s<=2");
        }
        if (i4 < 0 || i4 > 1) {
            throw new IllegalArgumentException("0<=d<=1");
        }
        if (i == 1 && i2 != 256) {
            throw new IllegalArgumentException("b=1 -> h=256");
        }
        if (i2 == 256 && i == 5) {
            throw new IllegalArgumentException("h=256 -> b!=5");
        }
        this.f8949b = i;
        this.f8951h = i2;
        this.f8953s = i3;
        this.f8950d = i4;
        this.f8952l = 256 - i2;
        if (i2 == 1) {
            this.cardinality = (i * 255) + 1;
        } else {
            this.cardinality = (long) (((long) ((this.f8952l * (1.0d - Math.pow(r3, r5))) / (1 - i2))) + Math.pow(i2, i));
        }
        this.smallest = calculateSmallest();
        this.largest = calculateLargest();
        this.powers = new long[i];
        for (int i5 = 0; i5 < i; i5++) {
            this.powers[i5] = (long) Math.pow(i2, i5);
        }
    }

    public long cardinality() {
        return this.cardinality;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int decode(InputStream inputStream) throws IOException, Pack200Exception {
        if (this.f8950d != 0) {
            throw new Pack200Exception("Delta encoding used without passing in last value; this is a coding error");
        }
        return decode(inputStream, 0L);
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int decode(InputStream inputStream, long j) throws IOException, Pack200Exception {
        long read;
        int i = 0;
        long j2 = 0;
        do {
            read = inputStream.read();
            this.lastBandLength++;
            j2 += this.powers[i] * read;
            i++;
            if (read < this.f8952l) {
                break;
            }
        } while (i < this.f8949b);
        if (read == -1) {
            throw new EOFException("End of stream reached whilst decoding");
        }
        if (isSigned()) {
            int i2 = this.f8953s;
            long j3 = (1 << i2) - 1;
            j2 = (j2 & j3) == j3 ? ~(j2 >>> i2) : j2 - (j2 >>> i2);
        }
        if (isDelta()) {
            j2 += j;
        }
        return (int) j2;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int[] decodeInts(int i, InputStream inputStream) throws IOException, Pack200Exception {
        int[] decodeInts = super.decodeInts(i, inputStream);
        if (isDelta()) {
            for (int i2 = 0; i2 < decodeInts.length; i2++) {
                while (decodeInts[i2] > this.largest) {
                    decodeInts[i2] = (int) (decodeInts[i2] - this.cardinality);
                }
                while (decodeInts[i2] < this.smallest) {
                    decodeInts[i2] = (int) (decodeInts[i2] + this.cardinality);
                }
            }
        }
        return decodeInts;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int[] decodeInts(int i, InputStream inputStream, int i2) throws IOException, Pack200Exception {
        int[] decodeInts = super.decodeInts(i, inputStream, i2);
        if (isDelta()) {
            for (int i3 = 0; i3 < decodeInts.length; i3++) {
                while (decodeInts[i3] > this.largest) {
                    decodeInts[i3] = (int) (decodeInts[i3] - this.cardinality);
                }
                while (decodeInts[i3] < this.smallest) {
                    decodeInts[i3] = (int) (decodeInts[i3] + this.cardinality);
                }
            }
        }
        return decodeInts;
    }

    public boolean encodes(long j) {
        return j >= this.smallest && j <= this.largest;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public byte[] encode(int i, int i2) throws Pack200Exception {
        long j;
        long j2 = i;
        if (!encodes(j2)) {
            throw new Pack200Exception("The codec " + toString() + " does not encode the value " + i);
        }
        if (isDelta()) {
            j2 -= i2;
        }
        boolean isSigned = isSigned();
        long j3 = Conversions.THIRTYTWO_BIT;
        if (isSigned) {
            if (j2 < -2147483648L) {
                j2 += Conversions.THIRTYTWO_BIT;
            } else if (j2 > 2147483647L) {
                j2 -= Conversions.THIRTYTWO_BIT;
            }
            if (j2 < 0) {
                j2 = ((-j2) << this.f8953s) - 1;
            } else {
                int i3 = this.f8953s;
                if (i3 == 1) {
                    j2 <<= i3;
                } else {
                    j3 = (j2 - (j2 % 3)) / 3;
                    j2 += j3;
                }
            }
        } else if (j2 < 0) {
            long j4 = this.cardinality;
            if (j4 < Conversions.THIRTYTWO_BIT) {
                j2 += j4;
            }
            j2 += j3;
        }
        if (j2 < 0) {
            throw new Pack200Exception("unable to encode");
        }
        ArrayList arrayList = new ArrayList();
        long j5 = j2;
        for (int i4 = 0; i4 < this.f8949b; i4++) {
            if (j5 < this.f8952l) {
                j = j5;
            } else {
                j = j5 % this.f8951h;
                while (j < this.f8952l) {
                    j += this.f8951h;
                }
            }
            arrayList.add(Byte.valueOf((byte) j));
            if (j < this.f8952l) {
                break;
            }
            j5 = (j5 - j) / this.f8951h;
        }
        byte[] bArr = new byte[arrayList.size()];
        for (int i5 = 0; i5 < bArr.length; i5++) {
            bArr[i5] = ((Byte) arrayList.get(i5)).byteValue();
        }
        return bArr;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public byte[] encode(int i) throws Pack200Exception {
        return encode(i, 0);
    }

    public boolean isDelta() {
        return this.f8950d != 0;
    }

    public boolean isSigned() {
        return this.f8953s != 0;
    }

    public long largest() {
        return this.largest;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private long calculateLargest() {
        long cardinality;
        long cardinality2;
        if (this.f8950d == 1) {
            return new BHSDCodec(this.f8949b, this.f8951h).largest();
        }
        int i = this.f8953s;
        if (i == 0) {
            cardinality2 = cardinality();
        } else {
            if (i != 1) {
                if (i == 2) {
                    cardinality = ((cardinality() * 3) / 4) - 1;
                    return Math.min((this.f8953s != 0 ? 4294967294L : 2147483647L) - 1, cardinality);
                }
                throw new Error("Unknown s value");
            }
            cardinality2 = cardinality() / 2;
        }
        cardinality = cardinality2 - 1;
        return Math.min((this.f8953s != 0 ? 4294967294L : 2147483647L) - 1, cardinality);
    }

    public long smallest() {
        return this.smallest;
    }

    private long calculateSmallest() {
        if (this.f8950d == 1 || !isSigned()) {
            return this.cardinality >= Conversions.THIRTYTWO_BIT ? -2147483648L : 0L;
        }
        return Math.max(-2147483648L, (-cardinality()) / (1 << this.f8953s));
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(11);
        stringBuffer.append('(');
        stringBuffer.append(this.f8949b);
        stringBuffer.append(',');
        stringBuffer.append(this.f8951h);
        if (this.f8953s != 0 || this.f8950d != 0) {
            stringBuffer.append(',');
            stringBuffer.append(this.f8953s);
        }
        if (this.f8950d != 0) {
            stringBuffer.append(',');
            stringBuffer.append(this.f8950d);
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public int getB() {
        return this.f8949b;
    }

    public int getH() {
        return this.f8951h;
    }

    public int getS() {
        return this.f8953s;
    }

    public int getL() {
        return this.f8952l;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BHSDCodec)) {
            return false;
        }
        BHSDCodec bHSDCodec = (BHSDCodec) obj;
        return bHSDCodec.f8949b == this.f8949b && bHSDCodec.f8951h == this.f8951h && bHSDCodec.f8953s == this.f8953s && bHSDCodec.f8950d == this.f8950d;
    }

    public int hashCode() {
        return (((((this.f8949b * 37) + this.f8951h) * 37) + this.f8953s) * 37) + this.f8950d;
    }
}
