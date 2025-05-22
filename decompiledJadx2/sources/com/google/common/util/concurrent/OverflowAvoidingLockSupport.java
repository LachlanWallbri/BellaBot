package com.google.common.util.concurrent;

import java.util.concurrent.locks.LockSupport;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
final class OverflowAvoidingLockSupport {
    static final long MAX_NANOSECONDS_THRESHOLD = 2147483647999999999L;

    private OverflowAvoidingLockSupport() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void parkNanos(Object obj, long j) {
        LockSupport.parkNanos(obj, Math.min(j, MAX_NANOSECONDS_THRESHOLD));
    }
}
