package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class CircularArrayOffsetCalculator {
    public static <E> E[] allocate(int i) {
        return (E[]) new Object[i];
    }

    public static long calcElementOffset(long j, long j2) {
        return UnsafeRefArrayAccess.REF_ARRAY_BASE + ((j & j2) << UnsafeRefArrayAccess.REF_ELEMENT_SHIFT);
    }
}
