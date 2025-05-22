package io.grpc.netty.shaded.io.netty.channel.epoll;

import io.grpc.netty.shaded.io.netty.channel.unix.FileDescriptor;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Epoll {
    private static final Throwable UNAVAILABILITY_CAUSE;

    static {
        FileDescriptor fileDescriptor;
        FileDescriptor newEventFd;
        UnsupportedOperationException th = null;
        try {
            if (SystemPropertyUtil.getBoolean("io.grpc.netty.shaded.io.netty.transport.noNative", false)) {
                th = new UnsupportedOperationException("Native transport was explicit disabled with -Dio.netty.transport.noNative=true");
            } else {
                try {
                    fileDescriptor = Native.newEpollCreate();
                } catch (Throwable th2) {
                    th = th2;
                    fileDescriptor = null;
                }
                try {
                    newEventFd = Native.newEventFd();
                    if (fileDescriptor != null) {
                        try {
                            fileDescriptor.close();
                        } catch (Exception unused) {
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (fileDescriptor != null) {
                        fileDescriptor.close();
                    }
                    UNAVAILABILITY_CAUSE = th;
                }
                if (newEventFd != null) {
                    newEventFd.close();
                }
            }
        } catch (Exception unused2) {
        }
        UNAVAILABILITY_CAUSE = th;
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
