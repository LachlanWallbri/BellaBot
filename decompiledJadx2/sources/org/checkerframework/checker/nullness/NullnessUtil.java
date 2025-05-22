package org.checkerframework.checker.nullness;

import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* loaded from: classes9.dex */
public final class NullnessUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    @EnsuresNonNull({"#1"})
    public static <T> T castNonNull(T t) {
        return t;
    }

    @EnsuresNonNull({"#1"})
    public static <T> T castNonNull(T t, String str) {
        return t;
    }

    private NullnessUtil() {
        throw new AssertionError("shouldn't be instantiated");
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[] castNonNullDeep(T[] tArr) {
        return (T[]) castNonNullArray(tArr, null);
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[] castNonNullDeep(T[] tArr, String str) {
        return (T[]) castNonNullArray(tArr, str);
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][] castNonNullDeep(T[][] tArr) {
        return (T[][]) ((Object[][]) castNonNullArray(tArr, null));
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][] castNonNullDeep(T[][] tArr, String str) {
        return (T[][]) ((Object[][]) castNonNullArray(tArr, str));
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][][] castNonNullDeep(T[][][] tArr) {
        return (T[][][]) ((Object[][][]) castNonNullArray(tArr, null));
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][][] castNonNullDeep(T[][][] tArr, String str) {
        return (T[][][]) ((Object[][][]) castNonNullArray(tArr, str));
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][][][] castNonNullDeep(T[][][][] tArr) {
        return (T[][][][]) ((Object[][][][]) castNonNullArray(tArr, null));
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][][][] castNonNullDeep(T[][][][] tArr, String str) {
        return (T[][][][]) ((Object[][][][]) castNonNullArray(tArr, str));
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][][][][] castNonNullDeep(T[][][][][] tArr) {
        return (T[][][][][]) ((Object[][][][][]) castNonNullArray(tArr, null));
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][][][][] castNonNullDeep(T[][][][][] tArr, String str) {
        return (T[][][][][]) ((Object[][][][][]) castNonNullArray(tArr, str));
    }

    private static <T> T[] castNonNullArray(T[] tArr, String str) {
        for (T t : tArr) {
            checkIfArray(t, str);
        }
        return tArr;
    }

    private static void checkIfArray(Object obj, String str) {
        Class<?> componentType = obj.getClass().getComponentType();
        if (componentType == null || componentType.isPrimitive()) {
            return;
        }
        castNonNullArray((Object[]) obj, str);
    }
}
