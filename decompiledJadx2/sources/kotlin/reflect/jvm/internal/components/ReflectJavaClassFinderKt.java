package kotlin.reflect.jvm.internal.components;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: ReflectJavaClassFinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0018\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, m3961d2 = {"tryLoadClass", "Ljava/lang/Class;", "Ljava/lang/ClassLoader;", "fqName", "", "descriptors.runtime"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class ReflectJavaClassFinderKt {
    public static final Class<?> tryLoadClass(ClassLoader tryLoadClass, String fqName) {
        Intrinsics.checkParameterIsNotNull(tryLoadClass, "$this$tryLoadClass");
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        try {
            return tryLoadClass.loadClass(fqName);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
