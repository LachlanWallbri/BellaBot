package io.opencensus.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class Utils {
    private Utils() {
    }

    public static void checkArgument(boolean z, @Nullable Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void checkArgument(boolean z, String str, @Nullable Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(format(str, objArr));
        }
    }

    public static void checkState(boolean z, @Nullable Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static void checkIndex(int i, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Negative size: " + i2);
        }
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException("Index out of bounds: size=" + i2 + ", index=" + i);
        }
    }

    public static <T> T checkNotNull(T t, @Nullable Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static <T> void checkListElementNotNull(List<T> list, @Nullable Object obj) {
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                throw new NullPointerException(String.valueOf(obj));
            }
        }
    }

    public static <K, V> void checkMapElementNotNull(Map<K, V> map, @Nullable Object obj) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new NullPointerException(String.valueOf(obj));
            }
        }
    }

    public static boolean equalsObjects(@Nullable Object obj, @Nullable Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    private static String format(String str, @Nullable Object... objArr) {
        int indexOf;
        if (objArr == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length() + (objArr.length * 16));
        int i = 0;
        int i2 = 0;
        while (i < objArr.length && (indexOf = str.indexOf("%s", i2)) != -1) {
            sb.append((CharSequence) str, i2, indexOf);
            sb.append(objArr[i]);
            i2 = indexOf + 2;
            i++;
        }
        sb.append((CharSequence) str, i2, str.length());
        if (i < objArr.length) {
            sb.append(" [");
            sb.append(objArr[i]);
            for (int i3 = i + 1; i3 < objArr.length; i3++) {
                sb.append(", ");
                sb.append(objArr[i3]);
            }
            sb.append(']');
        }
        return sb.toString();
    }
}
