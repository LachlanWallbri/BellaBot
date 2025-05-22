package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.FilteredAnnotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt;
import kotlin.reflect.jvm.internal.impl.utils.ExceptionUtilsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public class TypeSubstitutor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final TypeSubstitutor EMPTY = create(TypeSubstitution.EMPTY);
    private final TypeSubstitution substitution;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public enum VarianceConflictType {
        NO_CONFLICT,
        IN_IN_OUT_POSITION,
        OUT_IN_IN_POSITION
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:43:0x00d2. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:44:0x00d5. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x009f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ce A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e1 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x002b A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0017 A[FALL_THROUGH] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        Object[] objArr;
        if (i != 6 && i != 24 && i != 27) {
            switch (i) {
                default:
                    switch (i) {
                        default:
                            switch (i) {
                                case 30:
                                case 31:
                                case 32:
                                    break;
                                default:
                                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                                    break;
                            }
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                            str = "@NotNull method %s.%s must not return null";
                            break;
                    }
                case 9:
                case 10:
                case 11:
                    break;
            }
            if (i != 6 && i != 24 && i != 27) {
                switch (i) {
                    default:
                        switch (i) {
                            default:
                                switch (i) {
                                    case 30:
                                    case 31:
                                    case 32:
                                        break;
                                    default:
                                        i2 = 3;
                                        break;
                                }
                            case 17:
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                                i2 = 2;
                                break;
                        }
                    case 9:
                    case 10:
                    case 11:
                        break;
                }
                objArr = new Object[i2];
                switch (i) {
                    case 1:
                        objArr[0] = "first";
                        break;
                    case 2:
                        objArr[0] = "second";
                        break;
                    case 3:
                        objArr[0] = "substitutionContext";
                        break;
                    case 4:
                        objArr[0] = "context";
                        break;
                    case 5:
                    default:
                        objArr[0] = "substitution";
                        break;
                    case 6:
                    case 9:
                    case 10:
                    case 11:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 24:
                    case 27:
                    case 30:
                    case 31:
                    case 32:
                        objArr[0] = "kotlin/reflect/jvm/internal/impl/types/TypeSubstitutor";
                        break;
                    case 7:
                    case 12:
                        objArr[0] = "type";
                        break;
                    case 8:
                    case 13:
                        objArr[0] = "howThisTypeIsUsed";
                        break;
                    case 14:
                    case 15:
                    case 26:
                        objArr[0] = "typeProjection";
                        break;
                    case 16:
                        objArr[0] = "originalProjection";
                        break;
                    case 23:
                        objArr[0] = "annotations";
                        break;
                    case 25:
                    case 28:
                        objArr[0] = "typeParameterVariance";
                        break;
                    case 29:
                        objArr[0] = "projectionKind";
                        break;
                }
                if (i != 6) {
                    objArr[1] = "getSubstitution";
                } else if (i != 24) {
                    if (i != 27) {
                        switch (i) {
                            case 9:
                            case 10:
                            case 11:
                                objArr[1] = "safeSubstitute";
                                break;
                            default:
                                switch (i) {
                                    case 17:
                                    case 18:
                                    case 19:
                                    case 20:
                                    case 21:
                                    case 22:
                                        objArr[1] = "unsafeSubstitute";
                                        break;
                                    default:
                                        switch (i) {
                                            case 30:
                                            case 31:
                                            case 32:
                                                break;
                                            default:
                                                objArr[1] = "kotlin/reflect/jvm/internal/impl/types/TypeSubstitutor";
                                                break;
                                        }
                                }
                        }
                    }
                    objArr[1] = "combine";
                } else {
                    objArr[1] = "filterOutUnsafeVariance";
                }
                if (i == 1 && i != 2) {
                    switch (i) {
                        case 5:
                            objArr[2] = "<init>";
                            break;
                        case 6:
                        case 9:
                        case 10:
                        case 11:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 24:
                        case 27:
                        case 30:
                        case 31:
                        case 32:
                            break;
                        case 7:
                        case 8:
                            objArr[2] = "safeSubstitute";
                            break;
                        case 12:
                        case 13:
                        case 14:
                            objArr[2] = "substitute";
                            break;
                        case 15:
                            objArr[2] = "substituteWithoutApproximation";
                            break;
                        case 16:
                            objArr[2] = "unsafeSubstitute";
                            break;
                        case 23:
                            objArr[2] = "filterOutUnsafeVariance";
                            break;
                        case 25:
                        case 26:
                        case 28:
                        case 29:
                            objArr[2] = "combine";
                            break;
                        default:
                            objArr[2] = "create";
                            break;
                    }
                } else {
                    objArr[2] = "createChainedSubstitutor";
                }
                String format = String.format(str, objArr);
                if (i != 6 && i != 24 && i != 27) {
                    switch (i) {
                        default:
                            switch (i) {
                                default:
                                    switch (i) {
                                        case 30:
                                        case 31:
                                        case 32:
                                            break;
                                        default:
                                            throw new IllegalArgumentException(format);
                                    }
                                case 17:
                                case 18:
                                case 19:
                                case 20:
                                case 21:
                                case 22:
                                    throw new IllegalStateException(format);
                            }
                        case 9:
                        case 10:
                        case 11:
                            break;
                    }
                }
                throw new IllegalStateException(format);
            }
            i2 = 2;
            objArr = new Object[i2];
            switch (i) {
            }
            if (i != 6) {
            }
            if (i == 1) {
            }
            objArr[2] = "createChainedSubstitutor";
            String format2 = String.format(str, objArr);
            if (i != 6) {
                switch (i) {
                }
            }
            throw new IllegalStateException(format2);
        }
        str = "@NotNull method %s.%s must not return null";
        if (i != 6) {
            switch (i) {
            }
            objArr = new Object[i2];
            switch (i) {
            }
            if (i != 6) {
            }
            if (i == 1) {
            }
            objArr[2] = "createChainedSubstitutor";
            String format22 = String.format(str, objArr);
            if (i != 6) {
            }
            throw new IllegalStateException(format22);
        }
        i2 = 2;
        objArr = new Object[i2];
        switch (i) {
        }
        if (i != 6) {
        }
        if (i == 1) {
        }
        objArr[2] = "createChainedSubstitutor";
        String format222 = String.format(str, objArr);
        if (i != 6) {
        }
        throw new IllegalStateException(format222);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static final class SubstitutionException extends Exception {
        public SubstitutionException(String str) {
            super(str);
        }
    }

    public static TypeSubstitutor create(TypeSubstitution typeSubstitution) {
        if (typeSubstitution == null) {
            $$$reportNull$$$0(0);
        }
        return new TypeSubstitutor(typeSubstitution);
    }

    public static TypeSubstitutor createChainedSubstitutor(TypeSubstitution typeSubstitution, TypeSubstitution typeSubstitution2) {
        if (typeSubstitution == null) {
            $$$reportNull$$$0(1);
        }
        if (typeSubstitution2 == null) {
            $$$reportNull$$$0(2);
        }
        return create(DisjointKeysUnionTypeSubstitution.create(typeSubstitution, typeSubstitution2));
    }

    public static TypeSubstitutor create(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(4);
        }
        return create(TypeConstructorSubstitution.create(kotlinType.getConstructor(), kotlinType.getArguments()));
    }

    protected TypeSubstitutor(TypeSubstitution typeSubstitution) {
        if (typeSubstitution == null) {
            $$$reportNull$$$0(5);
        }
        this.substitution = typeSubstitution;
    }

    public boolean isEmpty() {
        return this.substitution.isEmpty();
    }

    public TypeSubstitution getSubstitution() {
        TypeSubstitution typeSubstitution = this.substitution;
        if (typeSubstitution == null) {
            $$$reportNull$$$0(6);
        }
        return typeSubstitution;
    }

    public KotlinType safeSubstitute(KotlinType kotlinType, Variance variance) {
        if (kotlinType == null) {
            $$$reportNull$$$0(7);
        }
        if (variance == null) {
            $$$reportNull$$$0(8);
        }
        if (isEmpty()) {
            if (kotlinType == null) {
                $$$reportNull$$$0(9);
            }
            return kotlinType;
        }
        try {
            KotlinType type = unsafeSubstitute(new TypeProjectionImpl(variance, kotlinType), 0).getType();
            if (type == null) {
                $$$reportNull$$$0(10);
            }
            return type;
        } catch (SubstitutionException e) {
            SimpleType createErrorType = ErrorUtils.createErrorType(e.getMessage());
            if (createErrorType == null) {
                $$$reportNull$$$0(11);
            }
            return createErrorType;
        }
    }

    public KotlinType substitute(KotlinType kotlinType, Variance variance) {
        if (kotlinType == null) {
            $$$reportNull$$$0(12);
        }
        if (variance == null) {
            $$$reportNull$$$0(13);
        }
        TypeProjection substitute = substitute(new TypeProjectionImpl(variance, getSubstitution().prepareTopLevelType(kotlinType, variance)));
        if (substitute == null) {
            return null;
        }
        return substitute.getType();
    }

    public TypeProjection substitute(TypeProjection typeProjection) {
        if (typeProjection == null) {
            $$$reportNull$$$0(14);
        }
        TypeProjection substituteWithoutApproximation = substituteWithoutApproximation(typeProjection);
        return (this.substitution.approximateCapturedTypes() || this.substitution.approximateContravariantCapturedTypes()) ? CapturedTypeApproximationKt.approximateCapturedTypesIfNecessary(substituteWithoutApproximation, this.substitution.approximateContravariantCapturedTypes()) : substituteWithoutApproximation;
    }

    public TypeProjection substituteWithoutApproximation(TypeProjection typeProjection) {
        if (typeProjection == null) {
            $$$reportNull$$$0(15);
        }
        if (isEmpty()) {
            return typeProjection;
        }
        try {
            return unsafeSubstitute(typeProjection, 0);
        } catch (SubstitutionException unused) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TypeProjection unsafeSubstitute(TypeProjection typeProjection, int i) throws SubstitutionException {
        KotlinType makeNullableIfNeeded;
        if (typeProjection == null) {
            $$$reportNull$$$0(16);
        }
        assertRecursionDepth(i, typeProjection, this.substitution);
        if (typeProjection.isStarProjection()) {
            if (typeProjection == null) {
                $$$reportNull$$$0(17);
            }
            return typeProjection;
        }
        KotlinType type = typeProjection.getType();
        if (type instanceof TypeWithEnhancement) {
            TypeWithEnhancement typeWithEnhancement = (TypeWithEnhancement) type;
            UnwrappedType origin = typeWithEnhancement.getOrigin();
            KotlinType enhancement = typeWithEnhancement.getEnhancement();
            TypeProjection unsafeSubstitute = unsafeSubstitute(new TypeProjectionImpl(typeProjection.getProjectionKind(), origin), i + 1);
            return new TypeProjectionImpl(unsafeSubstitute.getProjectionKind(), TypeWithEnhancementKt.wrapEnhancement(unsafeSubstitute.getType().unwrap(), substitute(enhancement, typeProjection.getProjectionKind())));
        }
        if (DynamicTypesKt.isDynamic(type) || (type.unwrap() instanceof RawType)) {
            if (typeProjection == null) {
                $$$reportNull$$$0(18);
            }
            return typeProjection;
        }
        TypeProjection mo5465get = this.substitution.mo5465get(type);
        Variance projectionKind = typeProjection.getProjectionKind();
        if (mo5465get == null && FlexibleTypesKt.isFlexible(type) && !TypeCapabilitiesKt.isCustomTypeVariable(type)) {
            FlexibleType asFlexibleType = FlexibleTypesKt.asFlexibleType(type);
            int i2 = i + 1;
            TypeProjection unsafeSubstitute2 = unsafeSubstitute(new TypeProjectionImpl(projectionKind, asFlexibleType.getLowerBound()), i2);
            TypeProjection unsafeSubstitute3 = unsafeSubstitute(new TypeProjectionImpl(projectionKind, asFlexibleType.getUpperBound()), i2);
            Variance projectionKind2 = unsafeSubstitute2.getProjectionKind();
            if (unsafeSubstitute2.getType() != asFlexibleType.getLowerBound() || unsafeSubstitute3.getType() != asFlexibleType.getUpperBound()) {
                return new TypeProjectionImpl(projectionKind2, KotlinTypeFactory.flexibleType(TypeSubstitutionKt.asSimpleType(unsafeSubstitute2.getType()), TypeSubstitutionKt.asSimpleType(unsafeSubstitute3.getType())));
            }
            if (typeProjection == null) {
                $$$reportNull$$$0(19);
            }
            return typeProjection;
        }
        if (KotlinBuiltIns.isNothing(type) || KotlinTypeKt.isError(type)) {
            if (typeProjection == null) {
                $$$reportNull$$$0(20);
            }
            return typeProjection;
        }
        if (mo5465get != null) {
            VarianceConflictType conflictType = conflictType(projectionKind, mo5465get.getProjectionKind());
            if (!CapturedTypeConstructorKt.isCaptured(type)) {
                int i3 = C78192.f8786x4790888d[conflictType.ordinal()];
                if (i3 == 1) {
                    throw new SubstitutionException("Out-projection in in-position");
                }
                if (i3 == 2) {
                    return new TypeProjectionImpl(Variance.OUT_VARIANCE, type.getConstructor().getBuiltIns().getNullableAnyType());
                }
            }
            CustomTypeVariable customTypeVariable = TypeCapabilitiesKt.getCustomTypeVariable(type);
            if (mo5465get.isStarProjection()) {
                if (mo5465get == null) {
                    $$$reportNull$$$0(21);
                }
                return mo5465get;
            }
            if (customTypeVariable != null) {
                makeNullableIfNeeded = customTypeVariable.substitutionResult(mo5465get.getType());
            } else {
                makeNullableIfNeeded = TypeUtils.makeNullableIfNeeded(mo5465get.getType(), type.isMarkedNullable());
            }
            if (!type.getAnnotations().isEmpty()) {
                makeNullableIfNeeded = TypeUtilsKt.replaceAnnotations(makeNullableIfNeeded, new CompositeAnnotations(makeNullableIfNeeded.getAnnotations(), filterOutUnsafeVariance(this.substitution.filterAnnotations(type.getAnnotations()))));
            }
            if (conflictType == VarianceConflictType.NO_CONFLICT) {
                projectionKind = combine(projectionKind, mo5465get.getProjectionKind());
            }
            return new TypeProjectionImpl(projectionKind, makeNullableIfNeeded);
        }
        TypeProjection substituteCompoundType = substituteCompoundType(typeProjection, i);
        if (substituteCompoundType == null) {
            $$$reportNull$$$0(22);
        }
        return substituteCompoundType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* renamed from: kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor$2 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C78192 {

        /* renamed from: $SwitchMap$org$jetbrains$kotlin$types$TypeSubstitutor$VarianceConflictType */
        static final /* synthetic */ int[] f8786x4790888d;

        static {
            int[] iArr = new int[VarianceConflictType.values().length];
            f8786x4790888d = iArr;
            try {
                iArr[VarianceConflictType.OUT_IN_IN_POSITION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8786x4790888d[VarianceConflictType.IN_IN_OUT_POSITION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8786x4790888d[VarianceConflictType.NO_CONFLICT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static Annotations filterOutUnsafeVariance(Annotations annotations) {
        if (annotations == null) {
            $$$reportNull$$$0(23);
        }
        if (annotations.hasAnnotation(KotlinBuiltIns.FQ_NAMES.unsafeVariance)) {
            return new FilteredAnnotations(annotations, new Function1<FqName, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.1
                private static /* synthetic */ void $$$reportNull$$$0(int i) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "kotlin/reflect/jvm/internal/impl/types/TypeSubstitutor$1", "invoke"));
                }

                @Override // kotlin.jvm.functions.Function1
                public Boolean invoke(FqName fqName) {
                    if (fqName == null) {
                        $$$reportNull$$$0(0);
                    }
                    return Boolean.valueOf(!fqName.equals(KotlinBuiltIns.FQ_NAMES.unsafeVariance));
                }
            });
        }
        if (annotations == null) {
            $$$reportNull$$$0(24);
        }
        return annotations;
    }

    private TypeProjection substituteCompoundType(TypeProjection typeProjection, int i) throws SubstitutionException {
        KotlinType type = typeProjection.getType();
        Variance projectionKind = typeProjection.getProjectionKind();
        if (type.getConstructor().mo5460getDeclarationDescriptor() instanceof TypeParameterDescriptor) {
            return typeProjection;
        }
        SimpleType abbreviation = SpecialTypesKt.getAbbreviation(type);
        KotlinType substitute = abbreviation != null ? substitute(abbreviation, Variance.INVARIANT) : null;
        KotlinType replace = TypeSubstitutionKt.replace(type, substituteTypeArguments(type.getConstructor().getParameters(), type.getArguments(), i), this.substitution.filterAnnotations(type.getAnnotations()));
        if ((replace instanceof SimpleType) && (substitute instanceof SimpleType)) {
            replace = SpecialTypesKt.withAbbreviation((SimpleType) replace, (SimpleType) substitute);
        }
        return new TypeProjectionImpl(projectionKind, replace);
    }

    private List<TypeProjection> substituteTypeArguments(List<TypeParameterDescriptor> list, List<TypeProjection> list2, int i) throws SubstitutionException {
        ArrayList arrayList = new ArrayList(list.size());
        boolean z = false;
        for (int i2 = 0; i2 < list.size(); i2++) {
            TypeParameterDescriptor typeParameterDescriptor = list.get(i2);
            TypeProjection typeProjection = list2.get(i2);
            TypeProjection unsafeSubstitute = unsafeSubstitute(typeProjection, i + 1);
            int i3 = C78192.f8786x4790888d[conflictType(typeParameterDescriptor.getVariance(), unsafeSubstitute.getProjectionKind()).ordinal()];
            if (i3 != 1 && i3 != 2) {
                if (i3 == 3 && typeParameterDescriptor.getVariance() != Variance.INVARIANT && !unsafeSubstitute.isStarProjection()) {
                    unsafeSubstitute = new TypeProjectionImpl(Variance.INVARIANT, unsafeSubstitute.getType());
                }
            } else {
                unsafeSubstitute = TypeUtils.makeStarProjection(typeParameterDescriptor);
            }
            if (unsafeSubstitute != typeProjection) {
                z = true;
            }
            arrayList.add(unsafeSubstitute);
        }
        return !z ? list2 : arrayList;
    }

    public static Variance combine(Variance variance, TypeProjection typeProjection) {
        if (variance == null) {
            $$$reportNull$$$0(25);
        }
        if (typeProjection == null) {
            $$$reportNull$$$0(26);
        }
        if (!typeProjection.isStarProjection()) {
            return combine(variance, typeProjection.getProjectionKind());
        }
        Variance variance2 = Variance.OUT_VARIANCE;
        if (variance2 == null) {
            $$$reportNull$$$0(27);
        }
        return variance2;
    }

    public static Variance combine(Variance variance, Variance variance2) {
        if (variance == null) {
            $$$reportNull$$$0(28);
        }
        if (variance2 == null) {
            $$$reportNull$$$0(29);
        }
        if (variance == Variance.INVARIANT) {
            if (variance2 == null) {
                $$$reportNull$$$0(30);
            }
            return variance2;
        }
        if (variance2 == Variance.INVARIANT) {
            if (variance == null) {
                $$$reportNull$$$0(31);
            }
            return variance;
        }
        if (variance == variance2) {
            if (variance2 == null) {
                $$$reportNull$$$0(32);
            }
            return variance2;
        }
        throw new AssertionError("Variance conflict: type parameter variance '" + variance + "' and projection kind '" + variance2 + "' cannot be combined");
    }

    private static VarianceConflictType conflictType(Variance variance, Variance variance2) {
        if (variance == Variance.IN_VARIANCE && variance2 == Variance.OUT_VARIANCE) {
            return VarianceConflictType.OUT_IN_IN_POSITION;
        }
        if (variance == Variance.OUT_VARIANCE && variance2 == Variance.IN_VARIANCE) {
            return VarianceConflictType.IN_IN_OUT_POSITION;
        }
        return VarianceConflictType.NO_CONFLICT;
    }

    private static void assertRecursionDepth(int i, TypeProjection typeProjection, TypeSubstitution typeSubstitution) {
        if (i <= 100) {
            return;
        }
        throw new IllegalStateException("Recursion too deep. Most likely infinite loop while substituting " + safeToString(typeProjection) + "; substitution: " + safeToString(typeSubstitution));
    }

    private static String safeToString(Object obj) {
        try {
            return obj.toString();
        } catch (Throwable th) {
            if (ExceptionUtilsKt.isProcessCanceledException(th)) {
                throw th;
            }
            return "[Exception while computing toString(): " + th + "]";
        }
    }
}
