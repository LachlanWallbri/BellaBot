package io.grpc.netty.shaded.io.netty.util.internal;

import io.grpc.netty.shaded.io.netty.util.IllegalReferenceCountException;
import io.grpc.netty.shaded.io.netty.util.ReferenceCounted;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ReferenceCountUpdater<T extends ReferenceCounted> {
    private static int realRefCnt(int i) {
        if (i == 2 || i == 4 || (i & 1) == 0) {
            return i >>> 1;
        }
        return 0;
    }

    public final int initialValue() {
        return 2;
    }

    protected abstract long unsafeOffset();

    protected abstract AtomicIntegerFieldUpdater<T> updater();

    public static long getUnsafeOffset(Class<? extends ReferenceCounted> cls, String str) {
        try {
            if (PlatformDependent.hasUnsafe()) {
                return PlatformDependent.objectFieldOffset(cls.getDeclaredField(str));
            }
            return -1L;
        } catch (Throwable unused) {
            return -1L;
        }
    }

    private static int toLiveRealRefCnt(int i, int i2) {
        if (i == 2 || i == 4 || (i & 1) == 0) {
            return i >>> 1;
        }
        throw new IllegalReferenceCountException(0, -i2);
    }

    private int nonVolatileRawCnt(T t) {
        long unsafeOffset = unsafeOffset();
        return unsafeOffset != -1 ? PlatformDependent.getInt(t, unsafeOffset) : updater().get(t);
    }

    public final int refCnt(T t) {
        return realRefCnt(updater().get(t));
    }

    public final boolean isLiveNonVolatile(T t) {
        long unsafeOffset = unsafeOffset();
        int i = unsafeOffset != -1 ? PlatformDependent.getInt(t, unsafeOffset) : updater().get(t);
        return i == 2 || i == 4 || i == 6 || i == 8 || (i & 1) == 0;
    }

    public final void setRefCnt(T t, int i) {
        updater().set(t, i > 0 ? i << 1 : 1);
    }

    public final void resetRefCnt(T t) {
        updater().set(t, initialValue());
    }

    public final T retain(T t) {
        return retain0(t, 1, 2);
    }

    public final T retain(T t, int i) {
        return retain0(t, i, ObjectUtil.checkPositive(i, "increment") << 1);
    }

    private T retain0(T t, int i, int i2) {
        int andAdd = updater().getAndAdd(t, i2);
        if (andAdd != 2 && andAdd != 4 && (andAdd & 1) != 0) {
            throw new IllegalReferenceCountException(0, i);
        }
        if ((andAdd > 0 || andAdd + i2 < 0) && (andAdd < 0 || andAdd + i2 >= andAdd)) {
            return t;
        }
        updater().getAndAdd(t, -i2);
        throw new IllegalReferenceCountException(realRefCnt(andAdd), i);
    }

    public final boolean release(T t) {
        int nonVolatileRawCnt = nonVolatileRawCnt(t);
        if (nonVolatileRawCnt == 2) {
            return tryFinalRelease0(t, 2) || retryRelease0(t, 1);
        }
        return nonFinalRelease0(t, 1, nonVolatileRawCnt, toLiveRealRefCnt(nonVolatileRawCnt, 1));
    }

    public final boolean release(T t, int i) {
        int nonVolatileRawCnt = nonVolatileRawCnt(t);
        int liveRealRefCnt = toLiveRealRefCnt(nonVolatileRawCnt, ObjectUtil.checkPositive(i, "decrement"));
        if (i == liveRealRefCnt) {
            return tryFinalRelease0(t, nonVolatileRawCnt) || retryRelease0(t, i);
        }
        return nonFinalRelease0(t, i, nonVolatileRawCnt, liveRealRefCnt);
    }

    private boolean tryFinalRelease0(T t, int i) {
        return updater().compareAndSet(t, i, 1);
    }

    private boolean nonFinalRelease0(T t, int i, int i2, int i3) {
        if (i >= i3 || !updater().compareAndSet(t, i2, i2 - (i << 1))) {
            return retryRelease0(t, i);
        }
        return false;
    }

    private boolean retryRelease0(T t, int i) {
        while (true) {
            int i2 = updater().get(t);
            int liveRealRefCnt = toLiveRealRefCnt(i2, i);
            if (i == liveRealRefCnt) {
                if (tryFinalRelease0(t, i2)) {
                    return true;
                }
            } else if (i < liveRealRefCnt) {
                if (updater().compareAndSet(t, i2, i2 - (i << 1))) {
                    return false;
                }
            } else {
                throw new IllegalReferenceCountException(liveRealRefCnt, -i);
            }
            Thread.yield();
        }
    }
}
