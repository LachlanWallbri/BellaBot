package io.netty.channel.epoll;

import io.netty.channel.unix.FileDescriptor;
import io.netty.util.internal.PlatformDependent;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class Epoll {
    private static final Throwable UNAVAILABILITY_CAUSE;

    /* JADX WARN: Removed duplicated region for block: B:10:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0023  */
    static {
        FileDescriptor fileDescriptor;
        try {
            fileDescriptor = Native.newEpollCreate();
        } catch (Throwable th) {
            th = th;
            fileDescriptor = null;
        }
        try {
            FileDescriptor newEventFd = Native.newEventFd();
            if (fileDescriptor != null) {
                try {
                    fileDescriptor.close();
                } catch (Exception unused) {
                }
            }
            if (newEventFd != null) {
                try {
                    newEventFd.close();
                } catch (Exception unused2) {
                }
            }
            th = null;
        } catch (Throwable th2) {
            th = th2;
            if (fileDescriptor != null) {
                try {
                    fileDescriptor.close();
                } catch (Exception unused3) {
                }
            }
            if (th == null) {
            }
        }
        if (th == null) {
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

    private Epoll() {
    }
}
