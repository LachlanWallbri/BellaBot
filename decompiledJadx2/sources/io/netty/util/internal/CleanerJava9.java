package io.netty.util.internal;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.PrivilegedAction;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
final class CleanerJava9 implements Cleaner {
    private static final Method INVOKE_CLEANER;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) CleanerJava9.class);

    static {
        Throwable unsupportedOperationException;
        Object obj;
        Method method = null;
        if (PlatformDependent0.hasUnsafe()) {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(1);
            try {
                Method declaredMethod = PlatformDependent0.UNSAFE.getClass().getDeclaredMethod("invokeCleaner", ByteBuffer.class);
                declaredMethod.invoke(PlatformDependent0.UNSAFE, allocateDirect);
                obj = declaredMethod;
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                obj = e;
            }
            if (obj instanceof Throwable) {
                unsupportedOperationException = (Throwable) obj;
            } else {
                Method method2 = (Method) obj;
                unsupportedOperationException = null;
                method = method2;
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

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.netty.util.internal.CleanerJava9$1 */
    /* loaded from: classes8.dex */
    static class C74831 implements PrivilegedAction<Object> {
        final /* synthetic */ ByteBuffer val$buffer;

        C74831(ByteBuffer byteBuffer) {
            this.val$buffer = byteBuffer;
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            try {
                Method declaredMethod = PlatformDependent0.UNSAFE.getClass().getDeclaredMethod("invokeCleaner", ByteBuffer.class);
                declaredMethod.invoke(PlatformDependent0.UNSAFE, this.val$buffer);
                return declaredMethod;
            } catch (IllegalAccessException e) {
                return e;
            } catch (NoSuchMethodException e2) {
                return e2;
            } catch (InvocationTargetException e3) {
                return e3;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSupported() {
        return INVOKE_CLEANER != null;
    }

    @Override // io.netty.util.internal.Cleaner
    public void freeDirectBuffer(ByteBuffer byteBuffer) {
        try {
            INVOKE_CLEANER.invoke(PlatformDependent0.UNSAFE, byteBuffer);
        } catch (Throwable th) {
            PlatformDependent0.throwException(th);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.netty.util.internal.CleanerJava9$2 */
    /* loaded from: classes8.dex */
    static class C74842 implements PrivilegedAction<Exception> {
        final /* synthetic */ ByteBuffer val$buffer;

        C74842(ByteBuffer byteBuffer) {
            this.val$buffer = byteBuffer;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.security.PrivilegedAction
        public Exception run() {
            try {
                CleanerJava9.access$000().invoke(PlatformDependent0.UNSAFE, this.val$buffer);
                return null;
            } catch (IllegalAccessException e) {
                return e;
            } catch (InvocationTargetException e2) {
                return e2;
            }
        }
    }
}
