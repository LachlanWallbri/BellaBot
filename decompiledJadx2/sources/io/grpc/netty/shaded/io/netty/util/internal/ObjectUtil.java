package io.grpc.netty.shaded.io.netty.util.internal;

import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ObjectUtil {
    private ObjectUtil() {
    }

    public static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static int checkPositive(int i, String str) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str + ": " + i + " (expected: > 0)");
    }

    public static long checkPositive(long j, String str) {
        if (j > 0) {
            return j;
        }
        throw new IllegalArgumentException(str + ": " + j + " (expected: > 0)");
    }

    public static int checkPositiveOrZero(int i, String str) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str + ": " + i + " (expected: >= 0)");
    }

    public static long checkPositiveOrZero(long j, String str) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException(str + ": " + j + " (expected: >= 0)");
    }

    public static <T> T[] checkNonEmpty(T[] tArr, String str) {
        checkNotNull(tArr, str);
        checkPositive(tArr.length, str + ".length");
        return tArr;
    }

    public static <T extends Collection<?>> T checkNonEmpty(T t, String str) {
        checkNotNull(t, str);
        checkPositive(t.size(), str + ".size");
        return t;
    }

    public static int intValue(Integer num, int i) {
        return num != null ? num.intValue() : i;
    }

    public static long longValue(Long l, long j) {
        return l != null ? l.longValue() : j;
    }
}
