package io.grpc.netty.shaded.io.netty.util.internal;

import java.lang.reflect.AccessibleObject;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ReflectionUtil {
    private ReflectionUtil() {
    }

    public static Throwable trySetAccessible(AccessibleObject accessibleObject, boolean z) {
        if (z && !PlatformDependent0.isExplicitTryReflectionSetAccessible()) {
            return new UnsupportedOperationException("Reflective setAccessible(true) disabled");
        }
        try {
            accessibleObject.setAccessible(true);
            return null;
        } catch (SecurityException e) {
            return e;
        } catch (RuntimeException e2) {
            return handleInaccessibleObjectException(e2);
        }
    }

    private static RuntimeException handleInaccessibleObjectException(RuntimeException runtimeException) {
        if ("java.lang.reflect.InaccessibleObjectException".equals(runtimeException.getClass().getName())) {
            return runtimeException;
        }
        throw runtimeException;
    }
}
