package kotlin.reflect.jvm.internal.impl.types.model;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemOptimizationContext;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: TypeSystemContext.kt */
/* loaded from: classes2.dex */
public interface TypeSystemContext extends TypeSystemOptimizationContext {
    int argumentsCount(KotlinTypeMarker kotlinTypeMarker);

    TypeArgumentListMarker asArgumentList(SimpleTypeMarker simpleTypeMarker);

    CapturedTypeMarker asCapturedType(SimpleTypeMarker simpleTypeMarker);

    DefinitelyNotNullTypeMarker asDefinitelyNotNullType(SimpleTypeMarker simpleTypeMarker);

    DynamicTypeMarker asDynamicType(FlexibleTypeMarker flexibleTypeMarker);

    FlexibleTypeMarker asFlexibleType(KotlinTypeMarker kotlinTypeMarker);

    SimpleTypeMarker asSimpleType(KotlinTypeMarker kotlinTypeMarker);

    TypeArgumentMarker asTypeArgument(KotlinTypeMarker kotlinTypeMarker);

    SimpleTypeMarker captureFromArguments(SimpleTypeMarker simpleTypeMarker, CaptureStatus captureStatus);

    TypeArgumentMarker get(TypeArgumentListMarker typeArgumentListMarker, int i);

    TypeArgumentMarker getArgument(KotlinTypeMarker kotlinTypeMarker, int i);

    TypeParameterMarker getParameter(TypeConstructorMarker typeConstructorMarker, int i);

    KotlinTypeMarker getType(TypeArgumentMarker typeArgumentMarker);

    TypeVariance getVariance(TypeArgumentMarker typeArgumentMarker);

    TypeVariance getVariance(TypeParameterMarker typeParameterMarker);

    KotlinTypeMarker intersectTypes(List<? extends KotlinTypeMarker> list);

    boolean isAnyConstructor(TypeConstructorMarker typeConstructorMarker);

    boolean isClassTypeConstructor(TypeConstructorMarker typeConstructorMarker);

    boolean isCommonFinalClassConstructor(TypeConstructorMarker typeConstructorMarker);

    boolean isDenotable(TypeConstructorMarker typeConstructorMarker);

    boolean isEqualTypeConstructors(TypeConstructorMarker typeConstructorMarker, TypeConstructorMarker typeConstructorMarker2);

    boolean isError(KotlinTypeMarker kotlinTypeMarker);

    boolean isIntegerLiteralTypeConstructor(TypeConstructorMarker typeConstructorMarker);

    boolean isIntersection(TypeConstructorMarker typeConstructorMarker);

    boolean isMarkedNullable(SimpleTypeMarker simpleTypeMarker);

    boolean isNotNullNothing(KotlinTypeMarker kotlinTypeMarker);

    boolean isNothingConstructor(TypeConstructorMarker typeConstructorMarker);

    boolean isSingleClassifierType(SimpleTypeMarker simpleTypeMarker);

    boolean isStarProjection(TypeArgumentMarker typeArgumentMarker);

    boolean isStubType(SimpleTypeMarker simpleTypeMarker);

    SimpleTypeMarker lowerBound(FlexibleTypeMarker flexibleTypeMarker);

    SimpleTypeMarker lowerBoundIfFlexible(KotlinTypeMarker kotlinTypeMarker);

    KotlinTypeMarker lowerType(CapturedTypeMarker capturedTypeMarker);

    int parametersCount(TypeConstructorMarker typeConstructorMarker);

    Collection<KotlinTypeMarker> possibleIntegerTypes(SimpleTypeMarker simpleTypeMarker);

    int size(TypeArgumentListMarker typeArgumentListMarker);

    Collection<KotlinTypeMarker> supertypes(TypeConstructorMarker typeConstructorMarker);

    TypeConstructorMarker typeConstructor(KotlinTypeMarker kotlinTypeMarker);

    TypeConstructorMarker typeConstructor(SimpleTypeMarker simpleTypeMarker);

    SimpleTypeMarker upperBound(FlexibleTypeMarker flexibleTypeMarker);

    SimpleTypeMarker upperBoundIfFlexible(KotlinTypeMarker kotlinTypeMarker);

    SimpleTypeMarker withNullability(SimpleTypeMarker simpleTypeMarker, boolean z);

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: TypeSystemContext.kt */
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static boolean identicalArguments(TypeSystemContext typeSystemContext, SimpleTypeMarker a, SimpleTypeMarker b) {
            Intrinsics.checkParameterIsNotNull(a, "a");
            Intrinsics.checkParameterIsNotNull(b, "b");
            return TypeSystemOptimizationContext.DefaultImpls.identicalArguments(typeSystemContext, a, b);
        }

        public static TypeArgumentMarker getArgumentOrNull(TypeSystemContext typeSystemContext, SimpleTypeMarker getArgumentOrNull, int i) {
            Intrinsics.checkParameterIsNotNull(getArgumentOrNull, "$this$getArgumentOrNull");
            SimpleTypeMarker simpleTypeMarker = getArgumentOrNull;
            int argumentsCount = typeSystemContext.argumentsCount(simpleTypeMarker);
            if (i >= 0 && argumentsCount > i) {
                return typeSystemContext.getArgument(simpleTypeMarker, i);
            }
            return null;
        }

        public static SimpleTypeMarker lowerBoundIfFlexible(TypeSystemContext typeSystemContext, KotlinTypeMarker lowerBoundIfFlexible) {
            SimpleTypeMarker asSimpleType;
            Intrinsics.checkParameterIsNotNull(lowerBoundIfFlexible, "$this$lowerBoundIfFlexible");
            FlexibleTypeMarker asFlexibleType = typeSystemContext.asFlexibleType(lowerBoundIfFlexible);
            if ((asFlexibleType == null || (asSimpleType = typeSystemContext.lowerBound(asFlexibleType)) == null) && (asSimpleType = typeSystemContext.asSimpleType(lowerBoundIfFlexible)) == null) {
                Intrinsics.throwNpe();
            }
            return asSimpleType;
        }

        public static SimpleTypeMarker upperBoundIfFlexible(TypeSystemContext typeSystemContext, KotlinTypeMarker upperBoundIfFlexible) {
            SimpleTypeMarker asSimpleType;
            Intrinsics.checkParameterIsNotNull(upperBoundIfFlexible, "$this$upperBoundIfFlexible");
            FlexibleTypeMarker asFlexibleType = typeSystemContext.asFlexibleType(upperBoundIfFlexible);
            if ((asFlexibleType == null || (asSimpleType = typeSystemContext.upperBound(asFlexibleType)) == null) && (asSimpleType = typeSystemContext.asSimpleType(upperBoundIfFlexible)) == null) {
                Intrinsics.throwNpe();
            }
            return asSimpleType;
        }

        public static boolean isDynamic(TypeSystemContext typeSystemContext, KotlinTypeMarker isDynamic) {
            Intrinsics.checkParameterIsNotNull(isDynamic, "$this$isDynamic");
            FlexibleTypeMarker asFlexibleType = typeSystemContext.asFlexibleType(isDynamic);
            return (asFlexibleType != null ? typeSystemContext.asDynamicType(asFlexibleType) : null) != null;
        }

        public static boolean isDefinitelyNotNullType(TypeSystemContext typeSystemContext, KotlinTypeMarker isDefinitelyNotNullType) {
            Intrinsics.checkParameterIsNotNull(isDefinitelyNotNullType, "$this$isDefinitelyNotNullType");
            SimpleTypeMarker asSimpleType = typeSystemContext.asSimpleType(isDefinitelyNotNullType);
            return (asSimpleType != null ? typeSystemContext.asDefinitelyNotNullType(asSimpleType) : null) != null;
        }

        public static boolean hasFlexibleNullability(TypeSystemContext typeSystemContext, KotlinTypeMarker hasFlexibleNullability) {
            Intrinsics.checkParameterIsNotNull(hasFlexibleNullability, "$this$hasFlexibleNullability");
            return typeSystemContext.isMarkedNullable(typeSystemContext.lowerBoundIfFlexible(hasFlexibleNullability)) != typeSystemContext.isMarkedNullable(typeSystemContext.upperBoundIfFlexible(hasFlexibleNullability));
        }

        public static TypeConstructorMarker typeConstructor(TypeSystemContext typeSystemContext, KotlinTypeMarker typeConstructor) {
            Intrinsics.checkParameterIsNotNull(typeConstructor, "$this$typeConstructor");
            SimpleTypeMarker asSimpleType = typeSystemContext.asSimpleType(typeConstructor);
            if (asSimpleType == null) {
                asSimpleType = typeSystemContext.lowerBoundIfFlexible(typeConstructor);
            }
            return typeSystemContext.typeConstructor(asSimpleType);
        }

        public static boolean isClassType(TypeSystemContext typeSystemContext, SimpleTypeMarker isClassType) {
            Intrinsics.checkParameterIsNotNull(isClassType, "$this$isClassType");
            return typeSystemContext.isClassTypeConstructor(typeSystemContext.typeConstructor(isClassType));
        }

        public static boolean isIntegerLiteralType(TypeSystemContext typeSystemContext, SimpleTypeMarker isIntegerLiteralType) {
            Intrinsics.checkParameterIsNotNull(isIntegerLiteralType, "$this$isIntegerLiteralType");
            return typeSystemContext.isIntegerLiteralTypeConstructor(typeSystemContext.typeConstructor(isIntegerLiteralType));
        }

        public static TypeArgumentMarker get(TypeSystemContext typeSystemContext, TypeArgumentListMarker get, int i) {
            Intrinsics.checkParameterIsNotNull(get, "$this$get");
            if (get instanceof SimpleTypeMarker) {
                return typeSystemContext.getArgument((KotlinTypeMarker) get, i);
            }
            if (get instanceof ArgumentList) {
                TypeArgumentMarker typeArgumentMarker = ((ArgumentList) get).get(i);
                Intrinsics.checkExpressionValueIsNotNull(typeArgumentMarker, "get(index)");
                return typeArgumentMarker;
            }
            throw new IllegalStateException(("unknown type argument list type: " + get + ", " + Reflection.getOrCreateKotlinClass(get.getClass())).toString());
        }

        public static int size(TypeSystemContext typeSystemContext, TypeArgumentListMarker size) {
            Intrinsics.checkParameterIsNotNull(size, "$this$size");
            if (size instanceof SimpleTypeMarker) {
                return typeSystemContext.argumentsCount((KotlinTypeMarker) size);
            }
            if (size instanceof ArgumentList) {
                return ((ArgumentList) size).size();
            }
            throw new IllegalStateException(("unknown type argument list type: " + size + ", " + Reflection.getOrCreateKotlinClass(size.getClass())).toString());
        }
    }
}
