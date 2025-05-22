package io.opencensus.trace;

import io.opencensus.internal.Utils;
import java.util.Random;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class TraceId implements Comparable<TraceId> {
    private static final int BASE16_SIZE = 32;
    public static final TraceId INVALID = new TraceId(0, 0);
    private static final long INVALID_ID = 0;
    public static final int SIZE = 16;
    private final long idHi;
    private final long idLo;

    private TraceId(long j, long j2) {
        this.idHi = j;
        this.idLo = j2;
    }

    public static TraceId fromBytes(byte[] bArr) {
        Utils.checkNotNull(bArr, "src");
        Utils.checkArgument(bArr.length == 16, "Invalid size: expected %s, got %s", 16, Integer.valueOf(bArr.length));
        return fromBytes(bArr, 0);
    }

    public static TraceId fromBytes(byte[] bArr, int i) {
        Utils.checkNotNull(bArr, "src");
        return new TraceId(BigendianEncoding.longFromByteArray(bArr, i), BigendianEncoding.longFromByteArray(bArr, i + 8));
    }

    public static TraceId fromLowerBase16(CharSequence charSequence) {
        Utils.checkNotNull(charSequence, "src");
        Utils.checkArgument(charSequence.length() == 32, "Invalid size: expected %s, got %s", 32, Integer.valueOf(charSequence.length()));
        return fromLowerBase16(charSequence, 0);
    }

    public static TraceId fromLowerBase16(CharSequence charSequence, int i) {
        Utils.checkNotNull(charSequence, "src");
        return new TraceId(BigendianEncoding.longFromBase16String(charSequence, i), BigendianEncoding.longFromBase16String(charSequence, i + 16));
    }

    public static TraceId generateRandomId(Random random) {
        long nextLong;
        long nextLong2;
        do {
            nextLong = random.nextLong();
            nextLong2 = random.nextLong();
            if (nextLong != 0) {
                break;
            }
        } while (nextLong2 == 0);
        return new TraceId(nextLong, nextLong2);
    }

    public byte[] getBytes() {
        byte[] bArr = new byte[16];
        BigendianEncoding.longToByteArray(this.idHi, bArr, 0);
        BigendianEncoding.longToByteArray(this.idLo, bArr, 8);
        return bArr;
    }

    public void copyBytesTo(byte[] bArr, int i) {
        BigendianEncoding.longToByteArray(this.idHi, bArr, i);
        BigendianEncoding.longToByteArray(this.idLo, bArr, i + 8);
    }

    public void copyLowerBase16To(char[] cArr, int i) {
        BigendianEncoding.longToBase16String(this.idHi, cArr, i);
        BigendianEncoding.longToBase16String(this.idLo, cArr, i + 16);
    }

    public boolean isValid() {
        return (this.idHi == 0 && this.idLo == 0) ? false : true;
    }

    public String toLowerBase16() {
        char[] cArr = new char[32];
        copyLowerBase16To(cArr, 0);
        return new String(cArr);
    }

    public long getLowerLong() {
        long j = this.idHi;
        return j < 0 ? -j : j;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TraceId)) {
            return false;
        }
        TraceId traceId = (TraceId) obj;
        return this.idHi == traceId.idHi && this.idLo == traceId.idLo;
    }

    public int hashCode() {
        long j = this.idHi;
        int i = (((int) (j ^ (j >>> 32))) + 31) * 31;
        long j2 = this.idLo;
        return i + ((int) ((j2 >>> 32) ^ j2));
    }

    public String toString() {
        return "TraceId{traceId=" + toLowerBase16() + "}";
    }

    @Override // java.lang.Comparable
    public int compareTo(TraceId traceId) {
        long j = this.idHi;
        long j2 = traceId.idHi;
        if (j != j2) {
            return j < j2 ? -1 : 1;
        }
        long j3 = this.idLo;
        long j4 = traceId.idLo;
        if (j3 == j4) {
            return 0;
        }
        return j3 < j4 ? -1 : 1;
    }
}
