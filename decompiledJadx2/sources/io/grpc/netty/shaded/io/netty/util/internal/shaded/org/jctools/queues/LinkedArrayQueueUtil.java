package io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues;

import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class LinkedArrayQueueUtil {
    LinkedArrayQueueUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int length(Object[] objArr) {
        return objArr.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long modifiedCalcCircularRefElementOffset(long j, long j2) {
        return UnsafeRefArrayAccess.REF_ARRAY_BASE + ((j & j2) << (UnsafeRefArrayAccess.REF_ELEMENT_SHIFT - 1));
    }

    static long nextArrayOffset(Object[] objArr) {
        return UnsafeRefArrayAccess.REF_ARRAY_BASE + ((length(objArr) - 1) << UnsafeRefArrayAccess.REF_ELEMENT_SHIFT);
    }
}
