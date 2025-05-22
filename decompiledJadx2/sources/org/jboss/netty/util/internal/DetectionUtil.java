package org.jboss.netty.util.internal;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.Deflater;

/* loaded from: classes7.dex */
public final class DetectionUtil {
    private static final boolean IS_WINDOWS;
    private static final int JAVA_VERSION = javaVersion0();
    private static final boolean HAS_UNSAFE = hasUnsafe(AtomicInteger.class.getClassLoader());

    static {
        IS_WINDOWS = System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
    }

    public static boolean isWindows() {
        return IS_WINDOWS;
    }

    public static boolean hasUnsafe() {
        return HAS_UNSAFE;
    }

    public static int javaVersion() {
        return JAVA_VERSION;
    }

    private static boolean hasUnsafe(ClassLoader classLoader) {
        if (!Boolean.valueOf(SystemPropertyUtil.get("org.jboss.netty.tryUnsafe", "true")).booleanValue()) {
            return false;
        }
        try {
            return hasUnsafeField(Class.forName("sun.misc.Unsafe", true, classLoader));
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean hasUnsafeField(final Class<?> cls) throws PrivilegedActionException {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedExceptionAction<Boolean>() { // from class: org.jboss.netty.util.internal.DetectionUtil.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedExceptionAction
            public Boolean run() throws Exception {
                cls.getDeclaredField("theUnsafe");
                return true;
            }
        })).booleanValue();
    }

    private static int javaVersion0() {
        try {
            try {
                try {
                    Class.forName("android.app.Application");
                    return 6;
                } catch (Exception unused) {
                    return 5;
                }
            } catch (ClassNotFoundException unused2) {
                Deflater.class.getDeclaredField("SYNC_FLUSH");
                return 7;
            }
        } catch (Exception unused3) {
            Double.class.getDeclaredField("MIN_NORMAL");
            return 6;
        }
    }

    private DetectionUtil() {
    }
}
