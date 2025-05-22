package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: SpecialTypes.kt */
/* loaded from: classes2.dex */
public final class SpecialTypesKt {
    public static final AbbreviatedType getAbbreviatedType(KotlinType getAbbreviatedType) {
        Intrinsics.checkParameterIsNotNull(getAbbreviatedType, "$this$getAbbreviatedType");
        UnwrappedType unwrap = getAbbreviatedType.unwrap();
        if (!(unwrap instanceof AbbreviatedType)) {
            unwrap = null;
        }
        return (AbbreviatedType) unwrap;
    }

    public static final SimpleType getAbbreviation(KotlinType getAbbreviation) {
        Intrinsics.checkParameterIsNotNull(getAbbreviation, "$this$getAbbreviation");
        AbbreviatedType abbreviatedType = getAbbreviatedType(getAbbreviation);
        if (abbreviatedType != null) {
            return abbreviatedType.getAbbreviation();
        }
        return null;
    }

    public static final SimpleType withAbbreviation(SimpleType withAbbreviation, SimpleType abbreviatedType) {
        Intrinsics.checkParameterIsNotNull(withAbbreviation, "$this$withAbbreviation");
        Intrinsics.checkParameterIsNotNull(abbreviatedType, "abbreviatedType");
        return KotlinTypeKt.isError(withAbbreviation) ? withAbbreviation : new AbbreviatedType(withAbbreviation, abbreviatedType);
    }

    public static final boolean isDefinitelyNotNullType(KotlinType isDefinitelyNotNullType) {
        Intrinsics.checkParameterIsNotNull(isDefinitelyNotNullType, "$this$isDefinitelyNotNullType");
        return isDefinitelyNotNullType.unwrap() instanceof DefinitelyNotNullType;
    }

    public static final SimpleType makeSimpleTypeDefinitelyNotNullOrNotNull(SimpleType makeSimpleTypeDefinitelyNotNullOrNotNull) {
        Intrinsics.checkParameterIsNotNull(makeSimpleTypeDefinitelyNotNullOrNotNull, "$this$makeSimpleTypeDefinitelyNotNullOrNotNull");
        DefinitelyNotNullType makeDefinitelyNotNull$descriptors = DefinitelyNotNullType.Companion.makeDefinitelyNotNull$descriptors(makeSimpleTypeDefinitelyNotNullOrNotNull);
        DefinitelyNotNullType makeIntersectionTypeDefinitelyNotNullOrNotNull = makeDefinitelyNotNull$descriptors != null ? makeDefinitelyNotNull$descriptors : makeIntersectionTypeDefinitelyNotNullOrNotNull(makeSimpleTypeDefinitelyNotNullOrNotNull);
        return makeIntersectionTypeDefinitelyNotNullOrNotNull != null ? makeIntersectionTypeDefinitelyNotNullOrNotNull : makeSimpleTypeDefinitelyNotNullOrNotNull.makeNullableAsSpecified(false);
    }

    public static final UnwrappedType makeDefinitelyNotNullOrNotNull(UnwrappedType makeDefinitelyNotNullOrNotNull) {
        Intrinsics.checkParameterIsNotNull(makeDefinitelyNotNullOrNotNull, "$this$makeDefinitelyNotNullOrNotNull");
        DefinitelyNotNullType makeDefinitelyNotNull$descriptors = DefinitelyNotNullType.Companion.makeDefinitelyNotNull$descriptors(makeDefinitelyNotNullOrNotNull);
        SimpleType makeIntersectionTypeDefinitelyNotNullOrNotNull = makeDefinitelyNotNull$descriptors != null ? makeDefinitelyNotNull$descriptors : makeIntersectionTypeDefinitelyNotNullOrNotNull(makeDefinitelyNotNullOrNotNull);
        return makeIntersectionTypeDefinitelyNotNullOrNotNull != null ? makeIntersectionTypeDefinitelyNotNullOrNotNull : makeDefinitelyNotNullOrNotNull.makeNullableAsSpecified(false);
    }

    private static final SimpleType makeIntersectionTypeDefinitelyNotNullOrNotNull(KotlinType kotlinType) {
        IntersectionTypeConstructor makeDefinitelyNotNullOrNotNull;
        TypeConstructor constructor = kotlinType.getConstructor();
        if (!(constructor instanceof IntersectionTypeConstructor)) {
            constructor = null;
        }
        IntersectionTypeConstructor intersectionTypeConstructor = (IntersectionTypeConstructor) constructor;
        if (intersectionTypeConstructor == null || (makeDefinitelyNotNullOrNotNull = makeDefinitelyNotNullOrNotNull(intersectionTypeConstructor)) == null) {
            return null;
        }
        return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(kotlinType.getAnnotations(), makeDefinitelyNotNullOrNotNull, CollectionsKt.emptyList(), false, makeDefinitelyNotNullOrNotNull.createScopeForKotlinType());
    }

    private static final IntersectionTypeConstructor makeDefinitelyNotNullOrNotNull(IntersectionTypeConstructor intersectionTypeConstructor) {
        Collection<KotlinType> mo5461getSupertypes = intersectionTypeConstructor.mo5461getSupertypes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(mo5461getSupertypes, 10));
        boolean z = false;
        for (UnwrappedType unwrappedType : mo5461getSupertypes) {
            if (TypeUtils.isNullableType(unwrappedType)) {
                z = true;
                unwrappedType = makeDefinitelyNotNullOrNotNull(unwrappedType.unwrap());
            }
            arrayList.add(unwrappedType);
        }
        ArrayList arrayList2 = arrayList;
        if (z) {
            return new IntersectionTypeConstructor(arrayList2);
        }
        return null;
    }
}
