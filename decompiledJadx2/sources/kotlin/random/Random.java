package kotlin.random;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Random.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b'\u0018\u0000 \u00182\u00020\u0001:\u0002\u0017\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J$\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0004H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0004H\u0016J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0016H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0016H\u0016¨\u0006\u0019"}, m3961d2 = {"Lkotlin/random/Random;", "", "()V", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", TmpConstant.TYPE_VALUE_ARRAY, "fromIndex", "toIndex", "size", "nextDouble", "", "until", "from", "nextFloat", "", "nextInt", "nextLong", "", "Companion", "Default", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public abstract class Random {

    /* renamed from: Default, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Random defaultRandom = PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();
    public static final Companion Companion = Companion.INSTANCE;

    public abstract int nextBits(int bitCount);

    public int nextInt() {
        return nextBits(32);
    }

    public int nextInt(int until) {
        return nextInt(0, until);
    }

    public int nextInt(int from, int until) {
        int nextInt;
        int i;
        int i2;
        RandomKt.checkRangeBounds(from, until);
        int i3 = until - from;
        if (i3 > 0 || i3 == Integer.MIN_VALUE) {
            if (((-i3) & i3) == i3) {
                i2 = nextBits(RandomKt.fastLog2(i3));
                return from + i2;
            }
            do {
                nextInt = nextInt() >>> 1;
                i = nextInt % i3;
            } while ((nextInt - i) + (i3 - 1) < 0);
            i2 = i;
            return from + i2;
        }
        while (true) {
            int nextInt2 = nextInt();
            if (from <= nextInt2 && until > nextInt2) {
                return nextInt2;
            }
        }
    }

    public long nextLong() {
        return (nextInt() << 32) + nextInt();
    }

    public long nextLong(long until) {
        return nextLong(0L, until);
    }

    public long nextLong(long from, long until) {
        long nextLong;
        long j;
        long j2;
        int nextInt;
        RandomKt.checkRangeBounds(from, until);
        long j3 = until - from;
        if (j3 > 0) {
            if (((-j3) & j3) == j3) {
                int i = (int) j3;
                int i2 = (int) (j3 >>> 32);
                if (i != 0) {
                    nextInt = nextBits(RandomKt.fastLog2(i));
                } else if (i2 == 1) {
                    nextInt = nextInt();
                } else {
                    j2 = (nextBits(RandomKt.fastLog2(i2)) << 32) + nextInt();
                    return from + j2;
                }
                j2 = nextInt & 4294967295L;
                return from + j2;
            }
            do {
                nextLong = nextLong() >>> 1;
                j = nextLong % j3;
            } while ((nextLong - j) + (j3 - 1) < 0);
            j2 = j;
            return from + j2;
        }
        while (true) {
            long nextLong2 = nextLong();
            if (from <= nextLong2 && until > nextLong2) {
                return nextLong2;
            }
        }
    }

    public boolean nextBoolean() {
        return nextBits(1) != 0;
    }

    public double nextDouble() {
        return PlatformRandomKt.doubleFromParts(nextBits(26), nextBits(27));
    }

    public double nextDouble(double until) {
        return nextDouble(0.0d, until);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public double nextDouble(double from, double until) {
        double nextDouble;
        RandomKt.checkRangeBounds(from, until);
        double d = until - from;
        if (Double.isInfinite(d)) {
            if ((Double.isInfinite(from) || Double.isNaN(from)) ? false : true) {
                if ((Double.isInfinite(until) || Double.isNaN(until)) ? false : true) {
                    double d2 = 2;
                    double nextDouble2 = nextDouble() * ((until / d2) - (from / d2));
                    nextDouble = from + nextDouble2 + nextDouble2;
                    return nextDouble < until ? Math.nextAfter(until, DoubleCompanionObject.INSTANCE.getNEGATIVE_INFINITY()) : nextDouble;
                }
            }
        }
        nextDouble = from + (nextDouble() * d);
        if (nextDouble < until) {
        }
    }

    public float nextFloat() {
        return nextBits(24) / 16777216;
    }

    public static /* synthetic */ byte[] nextBytes$default(Random random, byte[] bArr, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: nextBytes");
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return random.nextBytes(bArr, i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public byte[] nextBytes(byte[] array, int fromIndex, int toIndex) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(array, "array");
        int length = array.length;
        if (fromIndex >= 0 && length >= fromIndex) {
            int length2 = array.length;
            if (toIndex >= 0 && length2 >= toIndex) {
                z = true;
                if (z) {
                    throw new IllegalArgumentException(("fromIndex (" + fromIndex + ") or toIndex (" + toIndex + ") are out of range: 0.." + array.length + FilenameUtils.EXTENSION_SEPARATOR).toString());
                }
                if (!(fromIndex <= toIndex)) {
                    throw new IllegalArgumentException(("fromIndex (" + fromIndex + ") must be not greater than toIndex (" + toIndex + ").").toString());
                }
                int i = (toIndex - fromIndex) / 4;
                for (int i2 = 0; i2 < i; i2++) {
                    int nextInt = nextInt();
                    array[fromIndex] = (byte) nextInt;
                    array[fromIndex + 1] = (byte) (nextInt >>> 8);
                    array[fromIndex + 2] = (byte) (nextInt >>> 16);
                    array[fromIndex + 3] = (byte) (nextInt >>> 24);
                    fromIndex += 4;
                }
                int i3 = toIndex - fromIndex;
                int nextBits = nextBits(i3 * 8);
                for (int i4 = 0; i4 < i3; i4++) {
                    array[fromIndex + i4] = (byte) (nextBits >>> (i4 * 8));
                }
                return array;
            }
        }
        z = false;
        if (z) {
        }
    }

    public byte[] nextBytes(byte[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return nextBytes(array, 0, array.length);
    }

    public byte[] nextBytes(int size) {
        return nextBytes(new byte[size]);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    /* compiled from: Random.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\bH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\bH\u0016J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0016J\u0018\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u001aH\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u001aH\u0016R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lkotlin/random/Random$Default;", "Lkotlin/random/Random;", "()V", "Companion", "Lkotlin/random/Random$Companion;", "Companion$annotations", "defaultRandom", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", TmpConstant.TYPE_VALUE_ARRAY, "fromIndex", "toIndex", "size", "nextDouble", "", "until", "from", "nextFloat", "", "nextInt", "nextLong", "", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: kotlin.random.Random$Default, reason: from kotlin metadata */
    /* loaded from: classes2.dex */
    public static final class Companion extends Random {
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use Default companion object instead")
        public static /* synthetic */ void Companion$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // kotlin.random.Random
        public int nextBits(int bitCount) {
            return Random.defaultRandom.nextBits(bitCount);
        }

        @Override // kotlin.random.Random
        public int nextInt() {
            return Random.defaultRandom.nextInt();
        }

        @Override // kotlin.random.Random
        public int nextInt(int until) {
            return Random.defaultRandom.nextInt(until);
        }

        @Override // kotlin.random.Random
        public int nextInt(int from, int until) {
            return Random.defaultRandom.nextInt(from, until);
        }

        @Override // kotlin.random.Random
        public long nextLong() {
            return Random.defaultRandom.nextLong();
        }

        @Override // kotlin.random.Random
        public long nextLong(long until) {
            return Random.defaultRandom.nextLong(until);
        }

        @Override // kotlin.random.Random
        public long nextLong(long from, long until) {
            return Random.defaultRandom.nextLong(from, until);
        }

        @Override // kotlin.random.Random
        public boolean nextBoolean() {
            return Random.defaultRandom.nextBoolean();
        }

        @Override // kotlin.random.Random
        public double nextDouble() {
            return Random.defaultRandom.nextDouble();
        }

        @Override // kotlin.random.Random
        public double nextDouble(double until) {
            return Random.defaultRandom.nextDouble(until);
        }

        @Override // kotlin.random.Random
        public double nextDouble(double from, double until) {
            return Random.defaultRandom.nextDouble(from, until);
        }

        @Override // kotlin.random.Random
        public float nextFloat() {
            return Random.defaultRandom.nextFloat();
        }

        @Override // kotlin.random.Random
        public byte[] nextBytes(byte[] array) {
            Intrinsics.checkParameterIsNotNull(array, "array");
            return Random.defaultRandom.nextBytes(array);
        }

        @Override // kotlin.random.Random
        public byte[] nextBytes(int size) {
            return Random.defaultRandom.nextBytes(size);
        }

        @Override // kotlin.random.Random
        public byte[] nextBytes(byte[] array, int fromIndex, int toIndex) {
            Intrinsics.checkParameterIsNotNull(array, "array");
            return Random.defaultRandom.nextBytes(array, fromIndex, toIndex);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    /* compiled from: Random.kt */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use Default companion object instead")
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, m3961d2 = {"Lkotlin/random/Random$Companion;", "Lkotlin/random/Random;", "()V", "nextBits", "", "bitCount", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion extends Random {
        public static final Companion INSTANCE = new Companion();

        private Companion() {
        }

        @Override // kotlin.random.Random
        public int nextBits(int bitCount) {
            return Random.INSTANCE.nextBits(bitCount);
        }
    }
}
