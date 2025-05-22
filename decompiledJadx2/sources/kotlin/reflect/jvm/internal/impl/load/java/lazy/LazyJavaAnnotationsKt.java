package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: LazyJavaAnnotations.kt */
/* loaded from: classes2.dex */
public final class LazyJavaAnnotationsKt {
    public static final Annotations resolveAnnotations(LazyJavaResolverContext resolveAnnotations, JavaAnnotationOwner annotationsOwner) {
        Intrinsics.checkParameterIsNotNull(resolveAnnotations, "$this$resolveAnnotations");
        Intrinsics.checkParameterIsNotNull(annotationsOwner, "annotationsOwner");
        return new LazyJavaAnnotations(resolveAnnotations, annotationsOwner);
    }
}
