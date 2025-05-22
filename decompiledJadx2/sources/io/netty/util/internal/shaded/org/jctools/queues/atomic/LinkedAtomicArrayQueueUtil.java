package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
final class LinkedAtomicArrayQueueUtil {
    static int calcElementOffset(long j, long j2) {
        return (int) (j & j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int modifiedCalcElementOffset(long j, long j2) {
        return ((int) (j & j2)) >> 1;
    }

    private LinkedAtomicArrayQueueUtil() {
    }

    public static <E> E lvElement(AtomicReferenceArray<E> atomicReferenceArray, int i) {
        return (E) AtomicReferenceArrayQueue.lvElement(atomicReferenceArray, i);
    }

    public static <E> E lpElement(AtomicReferenceArray<E> atomicReferenceArray, int i) {
        return (E) AtomicReferenceArrayQueue.lpElement(atomicReferenceArray, i);
    }

    public static <E> void spElement(AtomicReferenceArray<E> atomicReferenceArray, int i, E e) {
        AtomicReferenceArrayQueue.spElement(atomicReferenceArray, i, e);
    }

    public static <E> void svElement(AtomicReferenceArray<E> atomicReferenceArray, int i, E e) {
        AtomicReferenceArrayQueue.svElement(atomicReferenceArray, i, e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> void soElement(AtomicReferenceArray atomicReferenceArray, int i, Object obj) {
        atomicReferenceArray.lazySet(i, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> AtomicReferenceArray<E> allocate(int i) {
        return new AtomicReferenceArray<>(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int length(AtomicReferenceArray<?> atomicReferenceArray) {
        return atomicReferenceArray.length();
    }

    static int nextArrayOffset(AtomicReferenceArray<?> atomicReferenceArray) {
        return length(atomicReferenceArray) - 1;
    }
}
