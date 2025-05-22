package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: exceptionUtils.kt */
/* loaded from: classes2.dex */
public final class ExceptionUtilsKt {
    public static final RuntimeException rethrow(Throwable e) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        throw e;
    }

    public static final boolean isProcessCanceledException(Throwable isProcessCanceledException) {
        Intrinsics.checkParameterIsNotNull(isProcessCanceledException, "$this$isProcessCanceledException");
        Class<?> cls = isProcessCanceledException.getClass();
        while (!Intrinsics.areEqual(cls.getCanonicalName(), "com.intellij.openapi.progress.ProcessCanceledException")) {
            cls = cls.getSuperclass();
            if (cls == null) {
                return false;
            }
        }
        return true;
    }
}
