package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReflectJavaClassFinder.kt */
/* loaded from: classes8.dex */
public final class ReflectJavaClassFinderKt {
    public static final Class<?> tryLoadClass(ClassLoader tryLoadClass, String fqName) {
        Intrinsics.checkParameterIsNotNull(tryLoadClass, "$this$tryLoadClass");
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        try {
            return Class.forName(fqName, false, tryLoadClass);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
