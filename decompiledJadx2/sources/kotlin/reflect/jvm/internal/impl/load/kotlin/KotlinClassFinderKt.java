package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: KotlinClassFinder.kt */
/* loaded from: classes2.dex */
public final class KotlinClassFinderKt {
    public static final KotlinJvmBinaryClass findKotlinClass(KotlinClassFinder findKotlinClass, ClassId classId) {
        Intrinsics.checkParameterIsNotNull(findKotlinClass, "$this$findKotlinClass");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        KotlinClassFinder.Result findKotlinClassOrContent = findKotlinClass.findKotlinClassOrContent(classId);
        if (findKotlinClassOrContent != null) {
            return findKotlinClassOrContent.toKotlinJvmBinaryClass();
        }
        return null;
    }

    public static final KotlinJvmBinaryClass findKotlinClass(KotlinClassFinder findKotlinClass, JavaClass javaClass) {
        Intrinsics.checkParameterIsNotNull(findKotlinClass, "$this$findKotlinClass");
        Intrinsics.checkParameterIsNotNull(javaClass, "javaClass");
        KotlinClassFinder.Result findKotlinClassOrContent = findKotlinClass.findKotlinClassOrContent(javaClass);
        if (findKotlinClassOrContent != null) {
            return findKotlinClassOrContent.toKotlinJvmBinaryClass();
        }
        return null;
    }
}
