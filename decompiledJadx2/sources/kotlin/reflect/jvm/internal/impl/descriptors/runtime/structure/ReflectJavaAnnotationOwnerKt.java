package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* compiled from: ReflectJavaAnnotationOwner.kt */
/* loaded from: classes8.dex */
public final class ReflectJavaAnnotationOwnerKt {
    public static final List<ReflectJavaAnnotation> getAnnotations(Annotation[] getAnnotations) {
        Intrinsics.checkParameterIsNotNull(getAnnotations, "$this$getAnnotations");
        ArrayList arrayList = new ArrayList(getAnnotations.length);
        for (Annotation annotation : getAnnotations) {
            arrayList.add(new ReflectJavaAnnotation(annotation));
        }
        return arrayList;
    }

    public static final ReflectJavaAnnotation findAnnotation(Annotation[] findAnnotation, FqName fqName) {
        Annotation annotation;
        Intrinsics.checkParameterIsNotNull(findAnnotation, "$this$findAnnotation");
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        int length = findAnnotation.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                annotation = null;
                break;
            }
            annotation = findAnnotation[i];
            if (Intrinsics.areEqual(ReflectClassUtilKt.getClassId(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation))).asSingleFqName(), fqName)) {
                break;
            }
            i++;
        }
        if (annotation != null) {
            return new ReflectJavaAnnotation(annotation);
        }
        return null;
    }
}
