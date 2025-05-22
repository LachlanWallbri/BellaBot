package io.grpc.netty.shaded.io.netty.util.internal;

import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class CleanerJava9 implements Cleaner {
    private static final Method INVOKE_CLEANER;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) CleanerJava9.class);

    static {
        Throwable unsupportedOperationException;
        Method method = null;
        if (PlatformDependent0.hasUnsafe()) {
            final ByteBuffer allocateDirect = ByteBuffer.allocateDirect(1);
            Object doPrivileged = AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: io.grpc.netty.shaded.io.netty.util.internal.CleanerJava9.1
                @Override // java.security.PrivilegedAction
                public Object run() {
                    try {
                        Method declaredMethod = PlatformDependent0.UNSAFE.getClass().getDeclaredMethod("invokeCleaner", ByteBuffer.class);
                        declaredMethod.invoke(PlatformDependent0.UNSAFE, allocateDirect);
                        return declaredMethod;
                    } catch (IllegalAccessException e) {
                        return e;
                    } catch (NoSuchMethodException e2) {
                        return e2;
                    } catch (InvocationTargetException e3) {
                        return e3;
                    }
                }
            });
            if (doPrivileged instanceof Throwable) {
                unsupportedOperationException = (Throwable) doPrivileged;
            } else {
                method = (Method) doPrivileged;
                unsupportedOperationException = null;
            }
        } else {
            unsupportedOperationException = new UnsupportedOperationException("sun.misc.Unsafe unavailable");
        }
        if (unsupportedOperationException == null) {
            logger.debug("java.nio.ByteBuffer.cleaner(): available");
        } else {
            logger.debug("java.nio.ByteBuffer.cleaner(): unavailable", unsupportedOperationException);
        }
        INVOKE_CLEANER = method;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSupported() {
        return INVOKE_CLEANER != null;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.Cleaner
    public void freeDirectBuffer(ByteBuffer byteBuffer) {
        if (System.getSecurityManager() == null) {
            try {
                INVOKE_CLEANER.invoke(PlatformDependent0.UNSAFE, byteBuffer);
                return;
            } catch (Throwable th) {
                PlatformDependent0.throwException(th);
                return;
            }
        }
        freeDirectBufferPrivileged(byteBuffer);
    }

    private static void freeDirectBufferPrivileged(final ByteBuffer byteBuffer) {
        Exception exc = (Exception) AccessController.doPrivileged(new PrivilegedAction<Exception>() { // from class: io.grpc.netty.shaded.io.netty.util.internal.CleanerJava9.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public Exception run() {
                try {
                    CleanerJava9.INVOKE_CLEANER.invoke(PlatformDependent0.UNSAFE, byteBuffer);
                    return null;
                } catch (IllegalAccessException e) {
                    return e;
                } catch (InvocationTargetException e2) {
                    return e2;
                }
            }
        });
        if (exc != null) {
            PlatformDependent0.throwException(exc);
        }
    }
}
