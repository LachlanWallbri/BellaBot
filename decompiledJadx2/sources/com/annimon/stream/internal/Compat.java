package com.annimon.stream.internal;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class Compat {
    private static final String BAD_SIZE = "Stream size exceeds max array size";
    static final long MAX_ARRAY_SIZE = 2147483639;

    public static <T> Queue<T> queue() {
        try {
            return new ArrayDeque();
        } catch (NoClassDefFoundError unused) {
            return new LinkedList();
        }
    }

    @SafeVarargs
    public static <E> E[] newArray(int i, E... eArr) {
        try {
            return (E[]) Arrays.copyOf(eArr, i);
        } catch (NoSuchMethodError unused) {
            return (E[]) newArrayCompat(eArr, i);
        }
    }

    public static <E> E[] newArrayCompat(E[] eArr, int i) {
        E[] eArr2 = (E[]) ((Object[]) Array.newInstance(eArr.getClass().getComponentType(), i));
        System.arraycopy(eArr, 0, eArr2, 0, Math.min(i, eArr.length));
        return eArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkMaxArraySize(long j) {
        if (j >= MAX_ARRAY_SIZE) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
    }
}
