package io.grpc.netty.shaded.io.netty.util.internal;

import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class CleanerJava6 implements Cleaner {
    private static final Field CLEANER_FIELD;
    private static final long CLEANER_FIELD_OFFSET;
    private static final Method CLEAN_METHOD;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) CleanerJava6.class);

    static {
        Method method;
        Object doPrivileged;
        Object obj;
        long j;
        final ByteBuffer allocateDirect = ByteBuffer.allocateDirect(1);
        long j2 = -1;
        Field field = null;
        try {
            doPrivileged = AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: io.grpc.netty.shaded.io.netty.util.internal.CleanerJava6.1
                @Override // java.security.PrivilegedAction
                public Object run() {
                    try {
                        Field declaredField = allocateDirect.getClass().getDeclaredField("cleaner");
                        if (!PlatformDependent.hasUnsafe()) {
                            declaredField.setAccessible(true);
                        }
                        return declaredField;
                    } catch (Throwable th) {
                        return th;
                    }
                }
            });
        } catch (Throwable th) {
            th = th;
            method = null;
        }
        if (doPrivileged instanceof Throwable) {
            throw ((Throwable) doPrivileged);
        }
        Field field2 = (Field) doPrivileged;
        if (PlatformDependent.hasUnsafe()) {
            j = PlatformDependent0.objectFieldOffset(field2);
            obj = PlatformDependent0.getObject(allocateDirect, j);
        } else {
            obj = field2.get(allocateDirect);
            j = -1;
        }
        method = obj.getClass().getDeclaredMethod("clean", new Class[0]);
        method.invoke(obj, new Object[0]);
        th = null;
        field = field2;
        j2 = j;
        if (th == null) {
            logger.debug("java.nio.ByteBuffer.cleaner(): available");
        } else {
            logger.debug("java.nio.ByteBuffer.cleaner(): unavailable", th);
        }
        CLEANER_FIELD = field;
        CLEANER_FIELD_OFFSET = j2;
        CLEAN_METHOD = method;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSupported() {
        return (CLEANER_FIELD_OFFSET == -1 && CLEANER_FIELD == null) ? false : true;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.Cleaner
    public void freeDirectBuffer(ByteBuffer byteBuffer) {
        if (byteBuffer.isDirect()) {
            if (System.getSecurityManager() == null) {
                try {
                    freeDirectBuffer0(byteBuffer);
                    return;
                } catch (Throwable th) {
                    PlatformDependent0.throwException(th);
                    return;
                }
            }
            freeDirectBufferPrivileged(byteBuffer);
        }
    }

    private static void freeDirectBufferPrivileged(final ByteBuffer byteBuffer) {
        Throwable th = (Throwable) AccessController.doPrivileged(new PrivilegedAction<Throwable>() { // from class: io.grpc.netty.shaded.io.netty.util.internal.CleanerJava6.2
            @Override // java.security.PrivilegedAction
            public Throwable run() {
                try {
                    CleanerJava6.freeDirectBuffer0(byteBuffer);
                    return null;
                } catch (Throwable th2) {
                    return th2;
                }
            }
        });
        if (th != null) {
            PlatformDependent0.throwException(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void freeDirectBuffer0(ByteBuffer byteBuffer) throws Exception {
        Object object;
        long j = CLEANER_FIELD_OFFSET;
        if (j == -1) {
            object = CLEANER_FIELD.get(byteBuffer);
        } else {
            object = PlatformDependent0.getObject(byteBuffer, j);
        }
        if (object != null) {
            CLEAN_METHOD.invoke(object, new Object[0]);
        }
    }
}
