package io.netty.channel.kqueue;

import io.netty.channel.unix.FileDescriptor;
import io.netty.util.internal.PlatformDependent;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class KQueue {
    private static final Throwable UNAVAILABILITY_CAUSE;

    static {
        try {
            FileDescriptor newKQueue = Native.newKQueue();
            if (newKQueue != null) {
                try {
                    newKQueue.close();
                } catch (Exception unused) {
                }
            }
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        if (th != null) {
            UNAVAILABILITY_CAUSE = th;
        } else {
            UNAVAILABILITY_CAUSE = PlatformDependent.hasUnsafe() ? null : new IllegalStateException("sun.misc.Unsafe not available", PlatformDependent.getUnsafeUnavailabilityCause());
        }
    }

    public static boolean isAvailable() {
        return UNAVAILABILITY_CAUSE == null;
    }

    public static void ensureAvailability() {
        if (UNAVAILABILITY_CAUSE != null) {
            throw ((Error) new UnsatisfiedLinkError("failed to load the required native library").initCause(UNAVAILABILITY_CAUSE));
        }
    }

    public static Throwable unavailabilityCause() {
        return UNAVAILABILITY_CAUSE;
    }

    private KQueue() {
    }
}
