package org.apache.http.util;

import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class Args {
    public static void check(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void check(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static void check(boolean z, String str, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, obj));
        }
    }

    public static <T> T notNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(str + " may not be null");
    }

    public static <T extends CharSequence> T notEmpty(T t, String str) {
        if (t == null) {
            throw new IllegalArgumentException(str + " may not be null");
        }
        if (!TextUtils.isEmpty(t)) {
            return t;
        }
        throw new IllegalArgumentException(str + " may not be empty");
    }

    public static <T extends CharSequence> T notBlank(T t, String str) {
        if (t == null) {
            throw new IllegalArgumentException(str + " may not be null");
        }
        if (!TextUtils.isBlank(t)) {
            return t;
        }
        throw new IllegalArgumentException(str + " may not be blank");
    }

    public static <T extends CharSequence> T containsNoBlanks(T t, String str) {
        if (t == null) {
            throw new IllegalArgumentException(str + " may not be null");
        }
        if (t.length() == 0) {
            throw new IllegalArgumentException(str + " may not be empty");
        }
        if (!TextUtils.containsBlanks(t)) {
            return t;
        }
        throw new IllegalArgumentException(str + " may not contain blanks");
    }

    public static <E, T extends Collection<E>> T notEmpty(T t, String str) {
        if (t == null) {
            throw new IllegalArgumentException(str + " may not be null");
        }
        if (!t.isEmpty()) {
            return t;
        }
        throw new IllegalArgumentException(str + " may not be empty");
    }

    public static int positive(int i, String str) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " may not be negative or zero");
    }

    public static long positive(long j, String str) {
        if (j > 0) {
            return j;
        }
        throw new IllegalArgumentException(str + " may not be negative or zero");
    }

    public static int notNegative(int i, String str) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " may not be negative");
    }

    public static long notNegative(long j, String str) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException(str + " may not be negative");
    }
}
