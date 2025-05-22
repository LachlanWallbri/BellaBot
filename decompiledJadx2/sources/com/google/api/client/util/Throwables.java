package com.google.api.client.util;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class Throwables {
    public static RuntimeException propagate(Throwable th) {
        return com.google.common.base.Throwables.propagate(th);
    }

    public static void propagateIfPossible(Throwable th) {
        if (th != null) {
            com.google.common.base.Throwables.throwIfUnchecked(th);
        }
    }

    public static <X extends Throwable> void propagateIfPossible(Throwable th, Class<X> cls) throws Throwable {
        com.google.common.base.Throwables.propagateIfPossible(th, cls);
    }

    private Throwables() {
    }
}
