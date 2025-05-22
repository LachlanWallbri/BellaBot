package kotlin.reflect.jvm.internal.impl.types;

import com.iflytek.cloud.SpeechUtility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.NewTypeVariableConstructor;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public class TypeUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final SimpleType DONT_CARE = ErrorUtils.createErrorTypeWithCustomDebugName("DONT_CARE");
    public static final SimpleType CANT_INFER_FUNCTION_PARAM_TYPE = ErrorUtils.createErrorType("Cannot be inferred");
    public static final SimpleType NO_EXPECTED_TYPE = new SpecialType("NO_EXPECTED_TYPE");
    public static final SimpleType UNIT_EXPECTED_TYPE = new SpecialType("UNIT_EXPECTED_TYPE");

    /* JADX WARN: Removed duplicated region for block: B:106:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01a9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        if (i != 4 && i != 9 && i != 14 && i != 16 && i != 23 && i != 32 && i != 44 && i != 49 && i != 6 && i != 7 && i != 11 && i != 12) {
            switch (i) {
                case 52:
                case 53:
                case 54:
                case 55:
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
            if (i != 4 && i != 9 && i != 14 && i != 16 && i != 23 && i != 32 && i != 44 && i != 49 && i != 6 && i != 7 && i != 11 && i != 12) {
                switch (i) {
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                        break;
                    default:
                        i2 = 3;
                        break;
                }
                Object[] objArr = new Object[i2];
                switch (i) {
                    case 4:
                    case 6:
                    case 7:
                    case 9:
                    case 11:
                    case 12:
                    case 14:
                    case 16:
                    case 23:
                    case 32:
                    case 44:
                    case 49:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                        objArr[0] = "kotlin/reflect/jvm/internal/impl/types/TypeUtils";
                        break;
                    case 5:
                    case 8:
                    case 10:
                    case 15:
                    case 20:
                    case 22:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 35:
                    case 37:
                    default:
                        objArr[0] = "type";
                        break;
                    case 13:
                        objArr[0] = "parameters";
                        break;
                    case 17:
                        objArr[0] = "subType";
                        break;
                    case 18:
                        objArr[0] = "superType";
                        break;
                    case 19:
                        objArr[0] = "substitutor";
                        break;
                    case 21:
                        objArr[0] = SpeechUtility.TAG_RESOURCE_RESULT;
                        break;
                    case 28:
                    case 30:
                        objArr[0] = "clazz";
                        break;
                    case 29:
                        objArr[0] = "typeArguments";
                        break;
                    case 31:
                        objArr[0] = "projections";
                        break;
                    case 33:
                        objArr[0] = "a";
                        break;
                    case 34:
                        objArr[0] = "b";
                        break;
                    case 36:
                        objArr[0] = "typeParameters";
                        break;
                    case 38:
                        objArr[0] = "typeParameterConstructors";
                        break;
                    case 39:
                        objArr[0] = "specialType";
                        break;
                    case 40:
                    case 41:
                        objArr[0] = "isSpecialType";
                        break;
                    case 42:
                        objArr[0] = "parameterDescriptor";
                        break;
                    case 43:
                    case 47:
                        objArr[0] = "numberValueTypeConstructor";
                        break;
                    case 45:
                    case 46:
                        objArr[0] = "supertypes";
                        break;
                    case 48:
                    case 51:
                        objArr[0] = "expectedType";
                        break;
                    case 50:
                        objArr[0] = "literalTypeConstructor";
                        break;
                }
                if (i == 4) {
                    if (i != 9) {
                        if (i == 14) {
                            objArr[1] = "getDefaultTypeProjections";
                        } else if (i == 16) {
                            objArr[1] = "getImmediateSupertypes";
                        } else if (i == 23) {
                            objArr[1] = "getAllSupertypes";
                        } else if (i == 32) {
                            objArr[1] = "substituteProjectionsForParameters";
                        } else if (i != 44) {
                            if (i != 49) {
                                if (i != 6 && i != 7) {
                                    if (i != 11 && i != 12) {
                                        switch (i) {
                                            case 52:
                                            case 53:
                                            case 54:
                                            case 55:
                                                break;
                                            default:
                                                objArr[1] = "kotlin/reflect/jvm/internal/impl/types/TypeUtils";
                                                break;
                                        }
                                    } else {
                                        objArr[1] = "makeUnsubstitutedType";
                                    }
                                }
                            }
                            objArr[1] = "getPrimitiveNumberType";
                        } else {
                            objArr[1] = "getDefaultPrimitiveNumberType";
                        }
                    }
                    objArr[1] = "makeNullableIfNeeded";
                } else {
                    objArr[1] = "makeNullableAsSpecified";
                }
                switch (i) {
                    case 1:
                        objArr[2] = "makeNullable";
                        break;
                    case 2:
                        objArr[2] = "makeNotNullable";
                        break;
                    case 3:
                        objArr[2] = "makeNullableAsSpecified";
                        break;
                    case 4:
                    case 6:
                    case 7:
                    case 9:
                    case 11:
                    case 12:
                    case 14:
                    case 16:
                    case 23:
                    case 32:
                    case 44:
                    case 49:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                        break;
                    case 5:
                    case 8:
                        objArr[2] = "makeNullableIfNeeded";
                        break;
                    case 10:
                        objArr[2] = "canHaveSubtypes";
                        break;
                    case 13:
                        objArr[2] = "getDefaultTypeProjections";
                        break;
                    case 15:
                        objArr[2] = "getImmediateSupertypes";
                        break;
                    case 17:
                    case 18:
                    case 19:
                        objArr[2] = "createSubstitutedSupertype";
                        break;
                    case 20:
                    case 21:
                        objArr[2] = "collectAllSupertypes";
                        break;
                    case 22:
                        objArr[2] = "getAllSupertypes";
                        break;
                    case 24:
                        objArr[2] = "isNullableType";
                        break;
                    case 25:
                        objArr[2] = "acceptsNullable";
                        break;
                    case 26:
                        objArr[2] = "hasNullableSuperType";
                        break;
                    case 27:
                        objArr[2] = "getClassDescriptor";
                        break;
                    case 28:
                    case 29:
                        objArr[2] = "substituteParameters";
                        break;
                    case 30:
                    case 31:
                        objArr[2] = "substituteProjectionsForParameters";
                        break;
                    case 33:
                    case 34:
                        objArr[2] = "equalTypes";
                        break;
                    case 35:
                    case 36:
                        objArr[2] = "dependsOnTypeParameters";
                        break;
                    case 37:
                    case 38:
                        objArr[2] = "dependsOnTypeConstructors";
                        break;
                    case 39:
                    case 40:
                    case 41:
                        objArr[2] = "contains";
                        break;
                    case 42:
                        objArr[2] = "makeStarProjection";
                        break;
                    case 43:
                    case 45:
                        objArr[2] = "getDefaultPrimitiveNumberType";
                        break;
                    case 46:
                        objArr[2] = "findByFqName";
                        break;
                    case 47:
                    case 48:
                    case 50:
                    case 51:
                        objArr[2] = "getPrimitiveNumberType";
                        break;
                    case 56:
                        objArr[2] = "isTypeParameter";
                        break;
                    case 57:
                        objArr[2] = "isReifiedTypeParameter";
                        break;
                    case 58:
                        objArr[2] = "isNonReifiedTypeParameter";
                        break;
                    case 59:
                        objArr[2] = "getTypeParameterDescriptorOrNull";
                        break;
                    default:
                        objArr[2] = "noExpectedType";
                        break;
                }
                String format = String.format(str, objArr);
                if (i != 4 && i != 9 && i != 14 && i != 16 && i != 23 && i != 32 && i != 44 && i != 49 && i != 6 && i != 7 && i != 11 && i != 12) {
                    switch (i) {
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                            break;
                        default:
                            throw new IllegalArgumentException(format);
                    }
                }
                throw new IllegalStateException(format);
            }
            i2 = 2;
            Object[] objArr2 = new Object[i2];
            switch (i) {
            }
            if (i == 4) {
            }
            switch (i) {
            }
            String format2 = String.format(str, objArr2);
            if (i != 4) {
                switch (i) {
                }
            }
            throw new IllegalStateException(format2);
        }
        str = "@NotNull method %s.%s must not return null";
        if (i != 4) {
            switch (i) {
            }
            Object[] objArr22 = new Object[i2];
            switch (i) {
            }
            if (i == 4) {
            }
            switch (i) {
            }
            String format22 = String.format(str, objArr22);
            if (i != 4) {
            }
            throw new IllegalStateException(format22);
        }
        i2 = 2;
        Object[] objArr222 = new Object[i2];
        switch (i) {
        }
        if (i == 4) {
        }
        switch (i) {
        }
        String format222 = String.format(str, objArr222);
        if (i != 4) {
        }
        throw new IllegalStateException(format222);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static class SpecialType extends DelegatingSimpleType {
        private final String name;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 1 ? 3 : 2];
            if (i != 1) {
                objArr[0] = "newAnnotations";
            } else {
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/TypeUtils$SpecialType";
            }
            if (i != 1) {
                objArr[1] = "kotlin/reflect/jvm/internal/impl/types/TypeUtils$SpecialType";
            } else {
                objArr[1] = "toString";
            }
            if (i != 1) {
                objArr[2] = "replaceAnnotations";
            }
            String format = String.format(str, objArr);
            if (i == 1) {
                throw new IllegalStateException(format);
            }
        }

        public SpecialType(String str) {
            this.name = str;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
        protected SimpleType getDelegate() {
            throw new IllegalStateException(this.name);
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
        public SimpleType replaceAnnotations(Annotations annotations) {
            if (annotations == null) {
                $$$reportNull$$$0(0);
            }
            throw new IllegalStateException(this.name);
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
        public SimpleType makeNullableAsSpecified(boolean z) {
            throw new IllegalStateException(this.name);
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.SimpleType
        public String toString() {
            String str = this.name;
            if (str == null) {
                $$$reportNull$$$0(1);
            }
            return str;
        }
    }

    public static boolean noExpectedType(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(0);
        }
        return kotlinType == NO_EXPECTED_TYPE || kotlinType == UNIT_EXPECTED_TYPE;
    }

    public static boolean isDontCarePlaceholder(KotlinType kotlinType) {
        return kotlinType != null && kotlinType.getConstructor() == DONT_CARE.getConstructor();
    }

    public static KotlinType makeNullable(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(1);
        }
        return makeNullableAsSpecified(kotlinType, true);
    }

    public static KotlinType makeNotNullable(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(2);
        }
        return makeNullableAsSpecified(kotlinType, false);
    }

    public static KotlinType makeNullableAsSpecified(KotlinType kotlinType, boolean z) {
        if (kotlinType == null) {
            $$$reportNull$$$0(3);
        }
        UnwrappedType makeNullableAsSpecified = kotlinType.unwrap().makeNullableAsSpecified(z);
        if (makeNullableAsSpecified == null) {
            $$$reportNull$$$0(4);
        }
        return makeNullableAsSpecified;
    }

    public static KotlinType makeNullableIfNeeded(KotlinType kotlinType, boolean z) {
        if (kotlinType == null) {
            $$$reportNull$$$0(8);
        }
        if (z) {
            return makeNullable(kotlinType);
        }
        if (kotlinType == null) {
            $$$reportNull$$$0(9);
        }
        return kotlinType;
    }

    public static SimpleType makeUnsubstitutedType(ClassifierDescriptor classifierDescriptor, MemberScope memberScope) {
        if (ErrorUtils.isError(classifierDescriptor)) {
            SimpleType createErrorType = ErrorUtils.createErrorType("Unsubstituted type for " + classifierDescriptor);
            if (createErrorType == null) {
                $$$reportNull$$$0(11);
            }
            return createErrorType;
        }
        TypeConstructor typeConstructor = classifierDescriptor.getTypeConstructor();
        SimpleType simpleTypeWithNonTrivialMemberScope = KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(Annotations.Companion.getEMPTY(), typeConstructor, getDefaultTypeProjections(typeConstructor.getParameters()), false, memberScope);
        if (simpleTypeWithNonTrivialMemberScope == null) {
            $$$reportNull$$$0(12);
        }
        return simpleTypeWithNonTrivialMemberScope;
    }

    public static List<TypeProjection> getDefaultTypeProjections(List<TypeParameterDescriptor> list) {
        if (list == null) {
            $$$reportNull$$$0(13);
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<TypeParameterDescriptor> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new TypeProjectionImpl(it.next().getDefaultType()));
        }
        List<TypeProjection> list2 = CollectionsKt.toList(arrayList);
        if (list2 == null) {
            $$$reportNull$$$0(14);
        }
        return list2;
    }

    public static List<KotlinType> getImmediateSupertypes(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(15);
        }
        TypeSubstitutor create = TypeSubstitutor.create(kotlinType);
        Collection<KotlinType> mo5461getSupertypes = kotlinType.getConstructor().mo5461getSupertypes();
        ArrayList arrayList = new ArrayList(mo5461getSupertypes.size());
        Iterator<KotlinType> it = mo5461getSupertypes.iterator();
        while (it.hasNext()) {
            KotlinType createSubstitutedSupertype = createSubstitutedSupertype(kotlinType, it.next(), create);
            if (createSubstitutedSupertype != null) {
                arrayList.add(createSubstitutedSupertype);
            }
        }
        return arrayList;
    }

    public static KotlinType createSubstitutedSupertype(KotlinType kotlinType, KotlinType kotlinType2, TypeSubstitutor typeSubstitutor) {
        if (kotlinType == null) {
            $$$reportNull$$$0(17);
        }
        if (kotlinType2 == null) {
            $$$reportNull$$$0(18);
        }
        if (typeSubstitutor == null) {
            $$$reportNull$$$0(19);
        }
        KotlinType substitute = typeSubstitutor.substitute(kotlinType2, Variance.INVARIANT);
        if (substitute != null) {
            return makeNullableIfNeeded(substitute, kotlinType.isMarkedNullable());
        }
        return null;
    }

    public static boolean isNullableType(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(24);
        }
        if (kotlinType.isMarkedNullable()) {
            return true;
        }
        if (FlexibleTypesKt.isFlexible(kotlinType) && isNullableType(FlexibleTypesKt.asFlexibleType(kotlinType).getUpperBound())) {
            return true;
        }
        if (isTypeParameter(kotlinType)) {
            return hasNullableSuperType(kotlinType);
        }
        TypeConstructor constructor = kotlinType.getConstructor();
        if (!(constructor instanceof IntersectionTypeConstructor)) {
            return false;
        }
        Iterator<KotlinType> it = constructor.mo5461getSupertypes().iterator();
        while (it.hasNext()) {
            if (isNullableType(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean acceptsNullable(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(25);
        }
        if (kotlinType.isMarkedNullable()) {
            return true;
        }
        return FlexibleTypesKt.isFlexible(kotlinType) && acceptsNullable(FlexibleTypesKt.asFlexibleType(kotlinType).getUpperBound());
    }

    public static boolean hasNullableSuperType(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(26);
        }
        if (kotlinType.getConstructor().mo5460getDeclarationDescriptor() instanceof ClassDescriptor) {
            return false;
        }
        Iterator<KotlinType> it = getImmediateSupertypes(kotlinType).iterator();
        while (it.hasNext()) {
            if (isNullableType(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static ClassDescriptor getClassDescriptor(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(27);
        }
        ClassifierDescriptor mo5460getDeclarationDescriptor = kotlinType.getConstructor().mo5460getDeclarationDescriptor();
        if (mo5460getDeclarationDescriptor instanceof ClassDescriptor) {
            return (ClassDescriptor) mo5460getDeclarationDescriptor;
        }
        return null;
    }

    public static boolean contains(KotlinType kotlinType, Function1<UnwrappedType, Boolean> function1) {
        if (function1 == null) {
            $$$reportNull$$$0(40);
        }
        return contains(kotlinType, function1, new HashSet());
    }

    private static boolean contains(KotlinType kotlinType, Function1<UnwrappedType, Boolean> function1, HashSet<KotlinType> hashSet) {
        if (function1 == null) {
            $$$reportNull$$$0(41);
        }
        if (kotlinType == null || hashSet.contains(kotlinType)) {
            return false;
        }
        hashSet.add(kotlinType);
        UnwrappedType unwrap = kotlinType.unwrap();
        if (function1.invoke(unwrap).booleanValue()) {
            return true;
        }
        FlexibleType flexibleType = unwrap instanceof FlexibleType ? (FlexibleType) unwrap : null;
        if (flexibleType != null && (contains(flexibleType.getLowerBound(), function1, hashSet) || contains(flexibleType.getUpperBound(), function1, hashSet))) {
            return true;
        }
        if ((unwrap instanceof DefinitelyNotNullType) && contains(((DefinitelyNotNullType) unwrap).getOriginal(), function1, hashSet)) {
            return true;
        }
        TypeConstructor constructor = kotlinType.getConstructor();
        if (constructor instanceof IntersectionTypeConstructor) {
            Iterator<KotlinType> it = ((IntersectionTypeConstructor) constructor).mo5461getSupertypes().iterator();
            while (it.hasNext()) {
                if (contains(it.next(), function1, hashSet)) {
                    return true;
                }
            }
            return false;
        }
        for (TypeProjection typeProjection : kotlinType.getArguments()) {
            if (!typeProjection.isStarProjection()) {
                if (contains(typeProjection.getType(), function1, hashSet)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static TypeProjection makeStarProjection(TypeParameterDescriptor typeParameterDescriptor) {
        if (typeParameterDescriptor == null) {
            $$$reportNull$$$0(42);
        }
        return new StarProjectionImpl(typeParameterDescriptor);
    }

    public static boolean isTypeParameter(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(56);
        }
        return getTypeParameterDescriptorOrNull(kotlinType) != null || (kotlinType.getConstructor() instanceof NewTypeVariableConstructor);
    }

    public static TypeParameterDescriptor getTypeParameterDescriptorOrNull(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(59);
        }
        if (kotlinType.getConstructor().mo5460getDeclarationDescriptor() instanceof TypeParameterDescriptor) {
            return (TypeParameterDescriptor) kotlinType.getConstructor().mo5460getDeclarationDescriptor();
        }
        return null;
    }
}
