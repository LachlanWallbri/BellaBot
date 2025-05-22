package kotlin.reflect.jvm;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: KTypesJvm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u001c\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\"\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001*\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0003\u0010\b¨\u0006\t"}, m3961d2 = {"jvmErasure", "Lkotlin/reflect/KClass;", "Lkotlin/reflect/KClassifier;", "getJvmErasure", "(Lkotlin/reflect/KClassifier;)Lkotlin/reflect/KClass;", "Lkotlin/reflect/KType;", "jvmErasure$annotations", "(Lkotlin/reflect/KType;)V", "(Lkotlin/reflect/KType;)Lkotlin/reflect/KClass;", "kotlin-reflection"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class KTypesJvm {
    public static /* synthetic */ void jvmErasure$annotations(KType kType) {
    }

    public static final KClass<?> getJvmErasure(KType jvmErasure) {
        KClass<?> jvmErasure2;
        Intrinsics.checkParameterIsNotNull(jvmErasure, "$this$jvmErasure");
        KClassifier classifier = jvmErasure.getClassifier();
        if (classifier != null && (jvmErasure2 = getJvmErasure(classifier)) != null) {
            return jvmErasure2;
        }
        throw new KotlinReflectionInternalError("Cannot calculate JVM erasure for type: " + jvmErasure);
    }

    public static final KClass<?> getJvmErasure(KClassifier jvmErasure) {
        Object obj;
        KClass<?> jvmErasure2;
        Intrinsics.checkParameterIsNotNull(jvmErasure, "$this$jvmErasure");
        if (jvmErasure instanceof KClass) {
            return (KClass) jvmErasure;
        }
        if (jvmErasure instanceof KTypeParameter) {
            List<KType> upperBounds = ((KTypeParameter) jvmErasure).getUpperBounds();
            Iterator<T> it = upperBounds.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                KType kType = (KType) next;
                if (kType != null) {
                    Object mo5460getDeclarationDescriptor = ((KTypeImpl) kType).getType().getConstructor().mo5460getDeclarationDescriptor();
                    ClassDescriptor classDescriptor = (ClassDescriptor) (mo5460getDeclarationDescriptor instanceof ClassDescriptor ? mo5460getDeclarationDescriptor : null);
                    if ((classDescriptor == null || classDescriptor.getKind() == ClassKind.INTERFACE || classDescriptor.getKind() == ClassKind.ANNOTATION_CLASS) ? false : true) {
                        obj = next;
                        break;
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.jvm.internal.KTypeImpl");
                }
            }
            KType kType2 = (KType) obj;
            if (kType2 == null) {
                kType2 = (KType) CollectionsKt.firstOrNull((List) upperBounds);
            }
            return (kType2 == null || (jvmErasure2 = getJvmErasure(kType2)) == null) ? Reflection.getOrCreateKotlinClass(Object.class) : jvmErasure2;
        }
        throw new KotlinReflectionInternalError("Cannot calculate JVM erasure for type: " + jvmErasure);
    }
}
