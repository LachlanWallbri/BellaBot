package com.annimon.stream;

import com.annimon.stream.function.Supplier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class Objects {
    public static int compareInt(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public static int compareLong(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean nonNull(Object obj) {
        return obj != null;
    }

    private Objects() {
    }

    public static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static boolean deepEquals(Object obj, Object obj2) {
        return obj == obj2 || !(obj == null || obj2 == null || !Arrays.deepEquals(new Object[]{obj}, new Object[]{obj2}));
    }

    public static int hashCode(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public static int hash(Object... objArr) {
        if (objArr == null) {
            return 0;
        }
        int i = 1;
        for (Object obj : objArr) {
            i = (i * 31) + hashCode(obj);
        }
        return i;
    }

    public static String toString(Object obj, String str) {
        return obj != null ? obj.toString() : str;
    }

    public static <T> int compare(T t, T t2, Comparator<? super T> comparator) {
        if (t == t2) {
            return 0;
        }
        return comparator.compare(t, t2);
    }

    public static <T> T requireNonNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static <T> T requireNonNull(T t, Supplier<String> supplier) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(supplier.get());
    }

    public static <T> T requireNonNullElse(T t, T t2) {
        return t != null ? t : (T) requireNonNull(t2, "defaultObj");
    }

    public static <T> T requireNonNullElseGet(T t, Supplier<? extends T> supplier) {
        return t != null ? t : (T) requireNonNull(((Supplier) requireNonNull(supplier, "supplier")).get(), "supplier.get()");
    }

    public static <T> Collection<T> requireNonNullElements(Collection<T> collection) {
        requireNonNull(collection);
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            requireNonNull(it.next());
        }
        return collection;
    }
}
