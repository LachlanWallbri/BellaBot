package kotlin.reflect.jvm.internal.impl.types.typeUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedType;
import kotlin.reflect.jvm.internal.impl.types.checker.NewTypeVariableConstructor;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: TypeUtils.kt */
/* loaded from: classes2.dex */
public final class TypeUtilsKt {
    public static final KotlinBuiltIns getBuiltIns(KotlinType builtIns) {
        Intrinsics.checkParameterIsNotNull(builtIns, "$this$builtIns");
        KotlinBuiltIns builtIns2 = builtIns.getConstructor().getBuiltIns();
        Intrinsics.checkExpressionValueIsNotNull(builtIns2, "constructor.builtIns");
        return builtIns2;
    }

    public static final KotlinType makeNullable(KotlinType makeNullable) {
        Intrinsics.checkParameterIsNotNull(makeNullable, "$this$makeNullable");
        KotlinType makeNullable2 = TypeUtils.makeNullable(makeNullable);
        Intrinsics.checkExpressionValueIsNotNull(makeNullable2, "TypeUtils.makeNullable(this)");
        return makeNullable2;
    }

    public static final KotlinType makeNotNullable(KotlinType makeNotNullable) {
        Intrinsics.checkParameterIsNotNull(makeNotNullable, "$this$makeNotNullable");
        KotlinType makeNotNullable2 = TypeUtils.makeNotNullable(makeNotNullable);
        Intrinsics.checkExpressionValueIsNotNull(makeNotNullable2, "TypeUtils.makeNotNullable(this)");
        return makeNotNullable2;
    }

    public static final boolean isTypeParameter(KotlinType isTypeParameter) {
        Intrinsics.checkParameterIsNotNull(isTypeParameter, "$this$isTypeParameter");
        return TypeUtils.isTypeParameter(isTypeParameter);
    }

    public static final boolean isSubtypeOf(KotlinType isSubtypeOf, KotlinType superType) {
        Intrinsics.checkParameterIsNotNull(isSubtypeOf, "$this$isSubtypeOf");
        Intrinsics.checkParameterIsNotNull(superType, "superType");
        return KotlinTypeChecker.DEFAULT.isSubtypeOf(isSubtypeOf, superType);
    }

    public static final KotlinType replaceAnnotations(KotlinType replaceAnnotations, Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(replaceAnnotations, "$this$replaceAnnotations");
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        return (replaceAnnotations.getAnnotations().isEmpty() && newAnnotations.isEmpty()) ? replaceAnnotations : replaceAnnotations.unwrap().replaceAnnotations(newAnnotations);
    }

    public static final TypeProjection createProjection(KotlinType type, Variance projectionKind, TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(projectionKind, "projectionKind");
        if ((typeParameterDescriptor != null ? typeParameterDescriptor.getVariance() : null) == projectionKind) {
            projectionKind = Variance.INVARIANT;
        }
        return new TypeProjectionImpl(projectionKind, type);
    }

    public static final TypeProjection asTypeProjection(KotlinType asTypeProjection) {
        Intrinsics.checkParameterIsNotNull(asTypeProjection, "$this$asTypeProjection");
        return new TypeProjectionImpl(asTypeProjection);
    }

    public static final boolean contains(KotlinType contains, Function1<? super UnwrappedType, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(contains, "$this$contains");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return TypeUtils.contains(contains, predicate);
    }

    public static final boolean canHaveUndefinedNullability(UnwrappedType canHaveUndefinedNullability) {
        Intrinsics.checkParameterIsNotNull(canHaveUndefinedNullability, "$this$canHaveUndefinedNullability");
        return (canHaveUndefinedNullability.getConstructor() instanceof NewTypeVariableConstructor) || (canHaveUndefinedNullability.getConstructor().mo5460getDeclarationDescriptor() instanceof TypeParameterDescriptor) || (canHaveUndefinedNullability instanceof NewCapturedType);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0077, code lost:
    
        r4 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final KotlinType getRepresentativeUpperBound(TypeParameterDescriptor representativeUpperBound) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(representativeUpperBound, "$this$representativeUpperBound");
        List<KotlinType> upperBounds = representativeUpperBound.getUpperBounds();
        Intrinsics.checkExpressionValueIsNotNull(upperBounds, "upperBounds");
        boolean z = !upperBounds.isEmpty();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Upper bounds should not be empty: " + representativeUpperBound);
        }
        List<KotlinType> upperBounds2 = representativeUpperBound.getUpperBounds();
        Intrinsics.checkExpressionValueIsNotNull(upperBounds2, "upperBounds");
        Iterator<T> it = upperBounds2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            ClassifierDescriptor mo5460getDeclarationDescriptor = ((KotlinType) next).getConstructor().mo5460getDeclarationDescriptor();
            ClassDescriptor classDescriptor = (ClassDescriptor) (mo5460getDeclarationDescriptor instanceof ClassDescriptor ? mo5460getDeclarationDescriptor : null);
            boolean z2 = false;
            if (classDescriptor != null && classDescriptor.getKind() != ClassKind.INTERFACE && classDescriptor.getKind() != ClassKind.ANNOTATION_CLASS) {
                z2 = true;
            }
        }
        KotlinType kotlinType = (KotlinType) obj;
        if (kotlinType != null) {
            return kotlinType;
        }
        List<KotlinType> upperBounds3 = representativeUpperBound.getUpperBounds();
        Intrinsics.checkExpressionValueIsNotNull(upperBounds3, "upperBounds");
        Object first = CollectionsKt.first((List<? extends Object>) upperBounds3);
        Intrinsics.checkExpressionValueIsNotNull(first, "upperBounds.first()");
        return (KotlinType) first;
    }

    public static final KotlinType replaceArgumentsWithStarProjections(KotlinType replaceArgumentsWithStarProjections) {
        SimpleType simpleType;
        Intrinsics.checkParameterIsNotNull(replaceArgumentsWithStarProjections, "$this$replaceArgumentsWithStarProjections");
        UnwrappedType unwrap = replaceArgumentsWithStarProjections.unwrap();
        if (unwrap instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrap;
            SimpleType lowerBound = flexibleType.getLowerBound();
            if (!lowerBound.getConstructor().getParameters().isEmpty() && lowerBound.getConstructor().mo5460getDeclarationDescriptor() != null) {
                List<TypeParameterDescriptor> parameters = lowerBound.getConstructor().getParameters();
                Intrinsics.checkExpressionValueIsNotNull(parameters, "constructor.parameters");
                List<TypeParameterDescriptor> list = parameters;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(new StarProjectionImpl((TypeParameterDescriptor) it.next()));
                }
                lowerBound = TypeSubstitutionKt.replace$default(lowerBound, (List) arrayList, (Annotations) null, 2, (Object) null);
            }
            SimpleType upperBound = flexibleType.getUpperBound();
            if (!upperBound.getConstructor().getParameters().isEmpty() && upperBound.getConstructor().mo5460getDeclarationDescriptor() != null) {
                List<TypeParameterDescriptor> parameters2 = upperBound.getConstructor().getParameters();
                Intrinsics.checkExpressionValueIsNotNull(parameters2, "constructor.parameters");
                List<TypeParameterDescriptor> list2 = parameters2;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it2 = list2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new StarProjectionImpl((TypeParameterDescriptor) it2.next()));
                }
                upperBound = TypeSubstitutionKt.replace$default(upperBound, (List) arrayList2, (Annotations) null, 2, (Object) null);
            }
            simpleType = KotlinTypeFactory.flexibleType(lowerBound, upperBound);
        } else if (unwrap instanceof SimpleType) {
            SimpleType simpleType2 = (SimpleType) unwrap;
            if (!simpleType2.getConstructor().getParameters().isEmpty() && simpleType2.getConstructor().mo5460getDeclarationDescriptor() != null) {
                List<TypeParameterDescriptor> parameters3 = simpleType2.getConstructor().getParameters();
                Intrinsics.checkExpressionValueIsNotNull(parameters3, "constructor.parameters");
                List<TypeParameterDescriptor> list3 = parameters3;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
                Iterator<T> it3 = list3.iterator();
                while (it3.hasNext()) {
                    arrayList3.add(new StarProjectionImpl((TypeParameterDescriptor) it3.next()));
                }
                simpleType2 = TypeSubstitutionKt.replace$default(simpleType2, (List) arrayList3, (Annotations) null, 2, (Object) null);
            }
            simpleType = simpleType2;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return TypeWithEnhancementKt.inheritEnhancement(simpleType, unwrap);
    }
}
