package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModalityKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedType;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.DefinitelyNotNullType;
import kotlin.reflect.jvm.internal.impl.types.DynamicType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StubType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DynamicTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.FlexibleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemInferenceExtensionContext;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: ClassicTypeSystemContext.kt */
/* loaded from: classes2.dex */
public interface ClassicTypeSystemContext extends TypeSystemInferenceExtensionContext {
    SimpleTypeMarker asSimpleType(KotlinTypeMarker kotlinTypeMarker);

    boolean isNothingConstructor(TypeConstructorMarker typeConstructorMarker);

    TypeConstructorMarker typeConstructor(SimpleTypeMarker simpleTypeMarker);

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: ClassicTypeSystemContext.kt */
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static TypeArgumentMarker get(ClassicTypeSystemContext classicTypeSystemContext, TypeArgumentListMarker get, int i) {
            Intrinsics.checkParameterIsNotNull(get, "$this$get");
            return TypeSystemInferenceExtensionContext.DefaultImpls.get(classicTypeSystemContext, get, i);
        }

        public static TypeArgumentMarker getArgumentOrNull(ClassicTypeSystemContext classicTypeSystemContext, SimpleTypeMarker getArgumentOrNull, int i) {
            Intrinsics.checkParameterIsNotNull(getArgumentOrNull, "$this$getArgumentOrNull");
            return TypeSystemInferenceExtensionContext.DefaultImpls.getArgumentOrNull(classicTypeSystemContext, getArgumentOrNull, i);
        }

        public static boolean hasFlexibleNullability(ClassicTypeSystemContext classicTypeSystemContext, KotlinTypeMarker hasFlexibleNullability) {
            Intrinsics.checkParameterIsNotNull(hasFlexibleNullability, "$this$hasFlexibleNullability");
            return TypeSystemInferenceExtensionContext.DefaultImpls.hasFlexibleNullability(classicTypeSystemContext, hasFlexibleNullability);
        }

        public static boolean isClassType(ClassicTypeSystemContext classicTypeSystemContext, SimpleTypeMarker isClassType) {
            Intrinsics.checkParameterIsNotNull(isClassType, "$this$isClassType");
            return TypeSystemInferenceExtensionContext.DefaultImpls.isClassType(classicTypeSystemContext, isClassType);
        }

        public static boolean isDefinitelyNotNullType(ClassicTypeSystemContext classicTypeSystemContext, KotlinTypeMarker isDefinitelyNotNullType) {
            Intrinsics.checkParameterIsNotNull(isDefinitelyNotNullType, "$this$isDefinitelyNotNullType");
            return TypeSystemInferenceExtensionContext.DefaultImpls.isDefinitelyNotNullType(classicTypeSystemContext, isDefinitelyNotNullType);
        }

        public static boolean isDynamic(ClassicTypeSystemContext classicTypeSystemContext, KotlinTypeMarker isDynamic) {
            Intrinsics.checkParameterIsNotNull(isDynamic, "$this$isDynamic");
            return TypeSystemInferenceExtensionContext.DefaultImpls.isDynamic(classicTypeSystemContext, isDynamic);
        }

        public static boolean isIntegerLiteralType(ClassicTypeSystemContext classicTypeSystemContext, SimpleTypeMarker isIntegerLiteralType) {
            Intrinsics.checkParameterIsNotNull(isIntegerLiteralType, "$this$isIntegerLiteralType");
            return TypeSystemInferenceExtensionContext.DefaultImpls.isIntegerLiteralType(classicTypeSystemContext, isIntegerLiteralType);
        }

        public static SimpleTypeMarker lowerBoundIfFlexible(ClassicTypeSystemContext classicTypeSystemContext, KotlinTypeMarker lowerBoundIfFlexible) {
            Intrinsics.checkParameterIsNotNull(lowerBoundIfFlexible, "$this$lowerBoundIfFlexible");
            return TypeSystemInferenceExtensionContext.DefaultImpls.lowerBoundIfFlexible(classicTypeSystemContext, lowerBoundIfFlexible);
        }

        public static int size(ClassicTypeSystemContext classicTypeSystemContext, TypeArgumentListMarker size) {
            Intrinsics.checkParameterIsNotNull(size, "$this$size");
            return TypeSystemInferenceExtensionContext.DefaultImpls.size(classicTypeSystemContext, size);
        }

        public static TypeConstructorMarker typeConstructor(ClassicTypeSystemContext classicTypeSystemContext, KotlinTypeMarker typeConstructor) {
            Intrinsics.checkParameterIsNotNull(typeConstructor, "$this$typeConstructor");
            return TypeSystemInferenceExtensionContext.DefaultImpls.typeConstructor(classicTypeSystemContext, typeConstructor);
        }

        public static SimpleTypeMarker upperBoundIfFlexible(ClassicTypeSystemContext classicTypeSystemContext, KotlinTypeMarker upperBoundIfFlexible) {
            Intrinsics.checkParameterIsNotNull(upperBoundIfFlexible, "$this$upperBoundIfFlexible");
            return TypeSystemInferenceExtensionContext.DefaultImpls.upperBoundIfFlexible(classicTypeSystemContext, upperBoundIfFlexible);
        }

        public static boolean isDenotable(ClassicTypeSystemContext classicTypeSystemContext, TypeConstructorMarker isDenotable) {
            Intrinsics.checkParameterIsNotNull(isDenotable, "$this$isDenotable");
            if (isDenotable instanceof TypeConstructor) {
                return ((TypeConstructor) isDenotable).isDenotable();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isDenotable + ", " + Reflection.getOrCreateKotlinClass(isDenotable.getClass())).toString());
        }

        public static boolean isIntegerLiteralTypeConstructor(ClassicTypeSystemContext classicTypeSystemContext, TypeConstructorMarker isIntegerLiteralTypeConstructor) {
            Intrinsics.checkParameterIsNotNull(isIntegerLiteralTypeConstructor, "$this$isIntegerLiteralTypeConstructor");
            if (isIntegerLiteralTypeConstructor instanceof TypeConstructor) {
                return isIntegerLiteralTypeConstructor instanceof IntegerLiteralTypeConstructor;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isIntegerLiteralTypeConstructor + ", " + Reflection.getOrCreateKotlinClass(isIntegerLiteralTypeConstructor.getClass())).toString());
        }

        public static Collection<KotlinTypeMarker> possibleIntegerTypes(ClassicTypeSystemContext classicTypeSystemContext, SimpleTypeMarker possibleIntegerTypes) {
            Intrinsics.checkParameterIsNotNull(possibleIntegerTypes, "$this$possibleIntegerTypes");
            TypeConstructorMarker typeConstructor = classicTypeSystemContext.typeConstructor(possibleIntegerTypes);
            if (typeConstructor instanceof IntegerLiteralTypeConstructor) {
                return ((IntegerLiteralTypeConstructor) typeConstructor).getPossibleTypes();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + possibleIntegerTypes + ", " + Reflection.getOrCreateKotlinClass(possibleIntegerTypes.getClass())).toString());
        }

        public static SimpleTypeMarker withNullability(ClassicTypeSystemContext classicTypeSystemContext, SimpleTypeMarker withNullability, boolean z) {
            Intrinsics.checkParameterIsNotNull(withNullability, "$this$withNullability");
            if (withNullability instanceof SimpleType) {
                return ((SimpleType) withNullability).makeNullableAsSpecified(z);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + withNullability + ", " + Reflection.getOrCreateKotlinClass(withNullability.getClass())).toString());
        }

        public static boolean isError(ClassicTypeSystemContext classicTypeSystemContext, KotlinTypeMarker isError) {
            Intrinsics.checkParameterIsNotNull(isError, "$this$isError");
            if (isError instanceof KotlinType) {
                return KotlinTypeKt.isError((KotlinType) isError);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isError + ", " + Reflection.getOrCreateKotlinClass(isError.getClass())).toString());
        }

        public static boolean isStubType(ClassicTypeSystemContext classicTypeSystemContext, SimpleTypeMarker isStubType) {
            Intrinsics.checkParameterIsNotNull(isStubType, "$this$isStubType");
            if (isStubType instanceof SimpleType) {
                return isStubType instanceof StubType;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isStubType + ", " + Reflection.getOrCreateKotlinClass(isStubType.getClass())).toString());
        }

        public static KotlinTypeMarker lowerType(ClassicTypeSystemContext classicTypeSystemContext, CapturedTypeMarker lowerType) {
            Intrinsics.checkParameterIsNotNull(lowerType, "$this$lowerType");
            if (lowerType instanceof NewCapturedType) {
                return ((NewCapturedType) lowerType).getLowerType();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + lowerType + ", " + Reflection.getOrCreateKotlinClass(lowerType.getClass())).toString());
        }

        public static boolean isIntersection(ClassicTypeSystemContext classicTypeSystemContext, TypeConstructorMarker isIntersection) {
            Intrinsics.checkParameterIsNotNull(isIntersection, "$this$isIntersection");
            if (isIntersection instanceof TypeConstructor) {
                return isIntersection instanceof IntersectionTypeConstructor;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isIntersection + ", " + Reflection.getOrCreateKotlinClass(isIntersection.getClass())).toString());
        }

        public static boolean identicalArguments(ClassicTypeSystemContext classicTypeSystemContext, SimpleTypeMarker a, SimpleTypeMarker b) {
            Intrinsics.checkParameterIsNotNull(a, "a");
            Intrinsics.checkParameterIsNotNull(b, "b");
            if (a instanceof SimpleType) {
                if (b instanceof SimpleType) {
                    return ((SimpleType) a).getArguments() == ((SimpleType) b).getArguments();
                }
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + b + ", " + Reflection.getOrCreateKotlinClass(b.getClass())).toString());
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + a + ", " + Reflection.getOrCreateKotlinClass(a.getClass())).toString());
        }

        public static SimpleTypeMarker asSimpleType(ClassicTypeSystemContext classicTypeSystemContext, KotlinTypeMarker asSimpleType) {
            Intrinsics.checkParameterIsNotNull(asSimpleType, "$this$asSimpleType");
            if (asSimpleType instanceof KotlinType) {
                UnwrappedType unwrap = ((KotlinType) asSimpleType).unwrap();
                if (!(unwrap instanceof SimpleType)) {
                    unwrap = null;
                }
                return (SimpleType) unwrap;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + asSimpleType + ", " + Reflection.getOrCreateKotlinClass(asSimpleType.getClass())).toString());
        }

        public static FlexibleTypeMarker asFlexibleType(ClassicTypeSystemContext classicTypeSystemContext, KotlinTypeMarker asFlexibleType) {
            Intrinsics.checkParameterIsNotNull(asFlexibleType, "$this$asFlexibleType");
            if (asFlexibleType instanceof KotlinType) {
                UnwrappedType unwrap = ((KotlinType) asFlexibleType).unwrap();
                if (!(unwrap instanceof FlexibleType)) {
                    unwrap = null;
                }
                return (FlexibleType) unwrap;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + asFlexibleType + ", " + Reflection.getOrCreateKotlinClass(asFlexibleType.getClass())).toString());
        }

        public static DynamicTypeMarker asDynamicType(ClassicTypeSystemContext classicTypeSystemContext, FlexibleTypeMarker asDynamicType) {
            Intrinsics.checkParameterIsNotNull(asDynamicType, "$this$asDynamicType");
            if (asDynamicType instanceof FlexibleType) {
                if (!(asDynamicType instanceof DynamicType)) {
                    asDynamicType = null;
                }
                return (DynamicType) asDynamicType;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + asDynamicType + ", " + Reflection.getOrCreateKotlinClass(asDynamicType.getClass())).toString());
        }

        public static SimpleTypeMarker upperBound(ClassicTypeSystemContext classicTypeSystemContext, FlexibleTypeMarker upperBound) {
            Intrinsics.checkParameterIsNotNull(upperBound, "$this$upperBound");
            if (upperBound instanceof FlexibleType) {
                return ((FlexibleType) upperBound).getUpperBound();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + upperBound + ", " + Reflection.getOrCreateKotlinClass(upperBound.getClass())).toString());
        }

        public static SimpleTypeMarker lowerBound(ClassicTypeSystemContext classicTypeSystemContext, FlexibleTypeMarker lowerBound) {
            Intrinsics.checkParameterIsNotNull(lowerBound, "$this$lowerBound");
            if (lowerBound instanceof FlexibleType) {
                return ((FlexibleType) lowerBound).getLowerBound();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + lowerBound + ", " + Reflection.getOrCreateKotlinClass(lowerBound.getClass())).toString());
        }

        public static CapturedTypeMarker asCapturedType(ClassicTypeSystemContext classicTypeSystemContext, SimpleTypeMarker asCapturedType) {
            Intrinsics.checkParameterIsNotNull(asCapturedType, "$this$asCapturedType");
            if (asCapturedType instanceof SimpleType) {
                if (!(asCapturedType instanceof NewCapturedType)) {
                    asCapturedType = null;
                }
                return (NewCapturedType) asCapturedType;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + asCapturedType + ", " + Reflection.getOrCreateKotlinClass(asCapturedType.getClass())).toString());
        }

        public static DefinitelyNotNullTypeMarker asDefinitelyNotNullType(ClassicTypeSystemContext classicTypeSystemContext, SimpleTypeMarker asDefinitelyNotNullType) {
            Intrinsics.checkParameterIsNotNull(asDefinitelyNotNullType, "$this$asDefinitelyNotNullType");
            if (asDefinitelyNotNullType instanceof SimpleType) {
                if (!(asDefinitelyNotNullType instanceof DefinitelyNotNullType)) {
                    asDefinitelyNotNullType = null;
                }
                return (DefinitelyNotNullType) asDefinitelyNotNullType;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + asDefinitelyNotNullType + ", " + Reflection.getOrCreateKotlinClass(asDefinitelyNotNullType.getClass())).toString());
        }

        public static boolean isMarkedNullable(ClassicTypeSystemContext classicTypeSystemContext, SimpleTypeMarker isMarkedNullable) {
            Intrinsics.checkParameterIsNotNull(isMarkedNullable, "$this$isMarkedNullable");
            if (isMarkedNullable instanceof SimpleType) {
                return ((SimpleType) isMarkedNullable).isMarkedNullable();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isMarkedNullable + ", " + Reflection.getOrCreateKotlinClass(isMarkedNullable.getClass())).toString());
        }

        public static TypeConstructorMarker typeConstructor(ClassicTypeSystemContext classicTypeSystemContext, SimpleTypeMarker typeConstructor) {
            Intrinsics.checkParameterIsNotNull(typeConstructor, "$this$typeConstructor");
            if (typeConstructor instanceof SimpleType) {
                return ((SimpleType) typeConstructor).getConstructor();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + typeConstructor + ", " + Reflection.getOrCreateKotlinClass(typeConstructor.getClass())).toString());
        }

        public static int argumentsCount(ClassicTypeSystemContext classicTypeSystemContext, KotlinTypeMarker argumentsCount) {
            Intrinsics.checkParameterIsNotNull(argumentsCount, "$this$argumentsCount");
            if (argumentsCount instanceof KotlinType) {
                return ((KotlinType) argumentsCount).getArguments().size();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + argumentsCount + ", " + Reflection.getOrCreateKotlinClass(argumentsCount.getClass())).toString());
        }

        public static TypeArgumentMarker getArgument(ClassicTypeSystemContext classicTypeSystemContext, KotlinTypeMarker getArgument, int i) {
            Intrinsics.checkParameterIsNotNull(getArgument, "$this$getArgument");
            if (getArgument instanceof KotlinType) {
                return ((KotlinType) getArgument).getArguments().get(i);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + getArgument + ", " + Reflection.getOrCreateKotlinClass(getArgument.getClass())).toString());
        }

        public static boolean isStarProjection(ClassicTypeSystemContext classicTypeSystemContext, TypeArgumentMarker isStarProjection) {
            Intrinsics.checkParameterIsNotNull(isStarProjection, "$this$isStarProjection");
            if (isStarProjection instanceof TypeProjection) {
                return ((TypeProjection) isStarProjection).isStarProjection();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isStarProjection + ", " + Reflection.getOrCreateKotlinClass(isStarProjection.getClass())).toString());
        }

        public static TypeVariance getVariance(ClassicTypeSystemContext classicTypeSystemContext, TypeArgumentMarker getVariance) {
            Intrinsics.checkParameterIsNotNull(getVariance, "$this$getVariance");
            if (getVariance instanceof TypeProjection) {
                Variance projectionKind = ((TypeProjection) getVariance).getProjectionKind();
                Intrinsics.checkExpressionValueIsNotNull(projectionKind, "this.projectionKind");
                return ClassicTypeSystemContextKt.convertVariance(projectionKind);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + getVariance + ", " + Reflection.getOrCreateKotlinClass(getVariance.getClass())).toString());
        }

        public static KotlinTypeMarker getType(ClassicTypeSystemContext classicTypeSystemContext, TypeArgumentMarker getType) {
            Intrinsics.checkParameterIsNotNull(getType, "$this$getType");
            if (getType instanceof TypeProjection) {
                return ((TypeProjection) getType).getType().unwrap();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + getType + ", " + Reflection.getOrCreateKotlinClass(getType.getClass())).toString());
        }

        public static int parametersCount(ClassicTypeSystemContext classicTypeSystemContext, TypeConstructorMarker parametersCount) {
            Intrinsics.checkParameterIsNotNull(parametersCount, "$this$parametersCount");
            if (parametersCount instanceof TypeConstructor) {
                return ((TypeConstructor) parametersCount).getParameters().size();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + parametersCount + ", " + Reflection.getOrCreateKotlinClass(parametersCount.getClass())).toString());
        }

        public static TypeParameterMarker getParameter(ClassicTypeSystemContext classicTypeSystemContext, TypeConstructorMarker getParameter, int i) {
            Intrinsics.checkParameterIsNotNull(getParameter, "$this$getParameter");
            if (getParameter instanceof TypeConstructor) {
                TypeParameterDescriptor typeParameterDescriptor = ((TypeConstructor) getParameter).getParameters().get(i);
                Intrinsics.checkExpressionValueIsNotNull(typeParameterDescriptor, "this.parameters[index]");
                return typeParameterDescriptor;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + getParameter + ", " + Reflection.getOrCreateKotlinClass(getParameter.getClass())).toString());
        }

        public static Collection<KotlinTypeMarker> supertypes(ClassicTypeSystemContext classicTypeSystemContext, TypeConstructorMarker supertypes) {
            Intrinsics.checkParameterIsNotNull(supertypes, "$this$supertypes");
            if (supertypes instanceof TypeConstructor) {
                Collection<KotlinType> mo5461getSupertypes = ((TypeConstructor) supertypes).mo5461getSupertypes();
                Intrinsics.checkExpressionValueIsNotNull(mo5461getSupertypes, "this.supertypes");
                return mo5461getSupertypes;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + supertypes + ", " + Reflection.getOrCreateKotlinClass(supertypes.getClass())).toString());
        }

        public static TypeVariance getVariance(ClassicTypeSystemContext classicTypeSystemContext, TypeParameterMarker getVariance) {
            Intrinsics.checkParameterIsNotNull(getVariance, "$this$getVariance");
            if (getVariance instanceof TypeParameterDescriptor) {
                Variance variance = ((TypeParameterDescriptor) getVariance).getVariance();
                Intrinsics.checkExpressionValueIsNotNull(variance, "this.variance");
                return ClassicTypeSystemContextKt.convertVariance(variance);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + getVariance + ", " + Reflection.getOrCreateKotlinClass(getVariance.getClass())).toString());
        }

        public static boolean isEqualTypeConstructors(ClassicTypeSystemContext classicTypeSystemContext, TypeConstructorMarker c1, TypeConstructorMarker c2) {
            Intrinsics.checkParameterIsNotNull(c1, "c1");
            Intrinsics.checkParameterIsNotNull(c2, "c2");
            if (c1 instanceof TypeConstructor) {
                if (c2 instanceof TypeConstructor) {
                    return Intrinsics.areEqual(c1, c2);
                }
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + c2 + ", " + Reflection.getOrCreateKotlinClass(c2.getClass())).toString());
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + c1 + ", " + Reflection.getOrCreateKotlinClass(c1.getClass())).toString());
        }

        public static boolean isClassTypeConstructor(ClassicTypeSystemContext classicTypeSystemContext, TypeConstructorMarker isClassTypeConstructor) {
            Intrinsics.checkParameterIsNotNull(isClassTypeConstructor, "$this$isClassTypeConstructor");
            if (isClassTypeConstructor instanceof TypeConstructor) {
                return ((TypeConstructor) isClassTypeConstructor).mo5460getDeclarationDescriptor() instanceof ClassDescriptor;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isClassTypeConstructor + ", " + Reflection.getOrCreateKotlinClass(isClassTypeConstructor.getClass())).toString());
        }

        public static boolean isCommonFinalClassConstructor(ClassicTypeSystemContext classicTypeSystemContext, TypeConstructorMarker isCommonFinalClassConstructor) {
            Intrinsics.checkParameterIsNotNull(isCommonFinalClassConstructor, "$this$isCommonFinalClassConstructor");
            if (isCommonFinalClassConstructor instanceof TypeConstructor) {
                ClassifierDescriptor mo5460getDeclarationDescriptor = ((TypeConstructor) isCommonFinalClassConstructor).mo5460getDeclarationDescriptor();
                if (!(mo5460getDeclarationDescriptor instanceof ClassDescriptor)) {
                    mo5460getDeclarationDescriptor = null;
                }
                ClassDescriptor classDescriptor = (ClassDescriptor) mo5460getDeclarationDescriptor;
                return (classDescriptor == null || !ModalityKt.isFinalClass(classDescriptor) || classDescriptor.getKind() == ClassKind.ENUM_ENTRY || classDescriptor.getKind() == ClassKind.ANNOTATION_CLASS) ? false : true;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isCommonFinalClassConstructor + ", " + Reflection.getOrCreateKotlinClass(isCommonFinalClassConstructor.getClass())).toString());
        }

        public static TypeArgumentListMarker asArgumentList(ClassicTypeSystemContext classicTypeSystemContext, SimpleTypeMarker asArgumentList) {
            Intrinsics.checkParameterIsNotNull(asArgumentList, "$this$asArgumentList");
            if (asArgumentList instanceof SimpleType) {
                return (TypeArgumentListMarker) asArgumentList;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + asArgumentList + ", " + Reflection.getOrCreateKotlinClass(asArgumentList.getClass())).toString());
        }

        public static SimpleTypeMarker captureFromArguments(ClassicTypeSystemContext classicTypeSystemContext, SimpleTypeMarker type, CaptureStatus status) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(status, "status");
            if (type instanceof SimpleType) {
                return NewCapturedTypeKt.captureFromArguments$default((SimpleType) type, status, null, 4, null);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + type + ", " + Reflection.getOrCreateKotlinClass(type.getClass())).toString());
        }

        public static boolean isAnyConstructor(ClassicTypeSystemContext classicTypeSystemContext, TypeConstructorMarker isAnyConstructor) {
            Intrinsics.checkParameterIsNotNull(isAnyConstructor, "$this$isAnyConstructor");
            if (isAnyConstructor instanceof TypeConstructor) {
                return KotlinBuiltIns.isTypeConstructorForGivenClass((TypeConstructor) isAnyConstructor, KotlinBuiltIns.FQ_NAMES.any);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isAnyConstructor + ", " + Reflection.getOrCreateKotlinClass(isAnyConstructor.getClass())).toString());
        }

        public static boolean isNothingConstructor(ClassicTypeSystemContext classicTypeSystemContext, TypeConstructorMarker isNothingConstructor) {
            Intrinsics.checkParameterIsNotNull(isNothingConstructor, "$this$isNothingConstructor");
            if (isNothingConstructor instanceof TypeConstructor) {
                return KotlinBuiltIns.isTypeConstructorForGivenClass((TypeConstructor) isNothingConstructor, KotlinBuiltIns.FQ_NAMES.nothing);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isNothingConstructor + ", " + Reflection.getOrCreateKotlinClass(isNothingConstructor.getClass())).toString());
        }

        public static TypeArgumentMarker asTypeArgument(ClassicTypeSystemContext classicTypeSystemContext, KotlinTypeMarker asTypeArgument) {
            Intrinsics.checkParameterIsNotNull(asTypeArgument, "$this$asTypeArgument");
            if (asTypeArgument instanceof KotlinType) {
                return TypeUtilsKt.asTypeProjection((KotlinType) asTypeArgument);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + asTypeArgument + ", " + Reflection.getOrCreateKotlinClass(asTypeArgument.getClass())).toString());
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static boolean isSingleClassifierType(ClassicTypeSystemContext classicTypeSystemContext, SimpleTypeMarker isSingleClassifierType) {
            Intrinsics.checkParameterIsNotNull(isSingleClassifierType, "$this$isSingleClassifierType");
            if (isSingleClassifierType instanceof SimpleType) {
                if (!KotlinTypeKt.isError((KotlinType) isSingleClassifierType)) {
                    SimpleType simpleType = (SimpleType) isSingleClassifierType;
                    if (!(simpleType.getConstructor().mo5460getDeclarationDescriptor() instanceof TypeAliasDescriptor) && (simpleType.getConstructor().mo5460getDeclarationDescriptor() != null || (isSingleClassifierType instanceof CapturedType) || (isSingleClassifierType instanceof NewCapturedType) || (isSingleClassifierType instanceof DefinitelyNotNullType) || (simpleType.getConstructor() instanceof IntegerLiteralTypeConstructor))) {
                        return true;
                    }
                }
                return false;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isSingleClassifierType + ", " + Reflection.getOrCreateKotlinClass(isSingleClassifierType.getClass())).toString());
        }

        public static boolean isNotNullNothing(ClassicTypeSystemContext classicTypeSystemContext, KotlinTypeMarker isNotNullNothing) {
            Intrinsics.checkParameterIsNotNull(isNotNullNothing, "$this$isNotNullNothing");
            if (isNotNullNothing instanceof KotlinType) {
                return classicTypeSystemContext.isNothingConstructor(classicTypeSystemContext.typeConstructor(isNotNullNothing)) && !TypeUtils.isNullableType((KotlinType) isNotNullNothing);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isNotNullNothing + ", " + Reflection.getOrCreateKotlinClass(isNotNullNothing.getClass())).toString());
        }

        public static KotlinTypeMarker intersectTypes(ClassicTypeSystemContext classicTypeSystemContext, List<? extends KotlinTypeMarker> types) {
            Intrinsics.checkParameterIsNotNull(types, "types");
            return IntersectionTypeKt.intersectTypes(types);
        }

        public static AbstractTypeCheckerContext newBaseTypeCheckerContext(ClassicTypeSystemContext classicTypeSystemContext, boolean z) {
            return new ClassicTypeCheckerContext(z, false, 2, null);
        }
    }
}
