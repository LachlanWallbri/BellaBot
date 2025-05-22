package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerValueTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* compiled from: NewKotlinTypeChecker.kt */
/* loaded from: classes8.dex */
public final class NewKotlinTypeCheckerImpl implements NewKotlinTypeChecker {
    private final KotlinTypeRefiner kotlinTypeRefiner;
    private final OverridingUtil overridingUtil;

    public NewKotlinTypeCheckerImpl(KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        this.kotlinTypeRefiner = kotlinTypeRefiner;
        OverridingUtil createWithTypeRefiner = OverridingUtil.createWithTypeRefiner(getKotlinTypeRefiner());
        Intrinsics.checkExpressionValueIsNotNull(createWithTypeRefiner, "OverridingUtil.createWit…efiner(kotlinTypeRefiner)");
        this.overridingUtil = createWithTypeRefiner;
    }

    public KotlinTypeRefiner getKotlinTypeRefiner() {
        return this.kotlinTypeRefiner;
    }

    public OverridingUtil getOverridingUtil() {
        return this.overridingUtil;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker, kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker
    public boolean isSubtypeOf(KotlinType subtype, KotlinType supertype) {
        Intrinsics.checkParameterIsNotNull(subtype, "subtype");
        Intrinsics.checkParameterIsNotNull(supertype, "supertype");
        return isSubtypeOf(new ClassicTypeCheckerContext(true, false, false, getKotlinTypeRefiner(), 6, null), subtype.unwrap(), supertype.unwrap());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker, kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker
    public boolean equalTypes(KotlinType a, KotlinType b) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        return equalTypes(new ClassicTypeCheckerContext(false, false, false, getKotlinTypeRefiner(), 6, null), a.unwrap(), b.unwrap());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker
    public final boolean equalTypes(ClassicTypeCheckerContext equalTypes, UnwrappedType a, UnwrappedType b) {
        Intrinsics.checkParameterIsNotNull(equalTypes, "$this$equalTypes");
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        return AbstractTypeChecker.INSTANCE.equalTypes(equalTypes, a, b);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker
    public final boolean isSubtypeOf(ClassicTypeCheckerContext isSubtypeOf, UnwrappedType subType, UnwrappedType superType) {
        Intrinsics.checkParameterIsNotNull(isSubtypeOf, "$this$isSubtypeOf");
        Intrinsics.checkParameterIsNotNull(subType, "subType");
        Intrinsics.checkParameterIsNotNull(superType, "superType");
        return AbstractTypeChecker.INSTANCE.isSubtypeOf(isSubtypeOf, subType, superType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker
    public final SimpleType transformToNewType(SimpleType type) {
        KotlinType type2;
        Intrinsics.checkParameterIsNotNull(type, "type");
        TypeConstructor constructor = type.getConstructor();
        r4 = null;
        UnwrappedType unwrappedType = null;
        boolean z = false;
        if (constructor instanceof CapturedTypeConstructorImpl) {
            CapturedTypeConstructorImpl capturedTypeConstructorImpl = (CapturedTypeConstructorImpl) constructor;
            TypeProjection projection = capturedTypeConstructorImpl.getProjection();
            if (!(projection.getProjectionKind() == Variance.IN_VARIANCE)) {
                projection = null;
            }
            if (projection != null && (type2 = projection.getType()) != null) {
                unwrappedType = type2.unwrap();
            }
            UnwrappedType unwrappedType2 = unwrappedType;
            if (capturedTypeConstructorImpl.getNewTypeConstructor() == null) {
                TypeProjection projection2 = capturedTypeConstructorImpl.getProjection();
                Collection<KotlinType> mo5461getSupertypes = capturedTypeConstructorImpl.mo5461getSupertypes();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(mo5461getSupertypes, 10));
                Iterator<T> it = mo5461getSupertypes.iterator();
                while (it.hasNext()) {
                    arrayList.add(((KotlinType) it.next()).unwrap());
                }
                capturedTypeConstructorImpl.setNewTypeConstructor(new NewCapturedTypeConstructor(projection2, arrayList, null, 4, null));
            }
            CaptureStatus captureStatus = CaptureStatus.FOR_SUBTYPING;
            NewCapturedTypeConstructor newTypeConstructor = capturedTypeConstructorImpl.getNewTypeConstructor();
            if (newTypeConstructor == null) {
                Intrinsics.throwNpe();
            }
            return new NewCapturedType(captureStatus, newTypeConstructor, unwrappedType2, type.getAnnotations(), type.isMarkedNullable());
        }
        if (constructor instanceof IntegerValueTypeConstructor) {
            Collection<KotlinType> mo5461getSupertypes2 = ((IntegerValueTypeConstructor) constructor).mo5461getSupertypes();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(mo5461getSupertypes2, 10));
            Iterator<T> it2 = mo5461getSupertypes2.iterator();
            while (it2.hasNext()) {
                arrayList2.add(TypeUtils.makeNullableAsSpecified((KotlinType) it2.next(), type.isMarkedNullable()));
            }
            return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(type.getAnnotations(), new IntersectionTypeConstructor(arrayList2), CollectionsKt.emptyList(), false, type.getMemberScope());
        }
        if (!(constructor instanceof IntersectionTypeConstructor) || !type.isMarkedNullable()) {
            return type;
        }
        IntersectionTypeConstructor intersectionTypeConstructor = (IntersectionTypeConstructor) constructor;
        Collection<KotlinType> mo5461getSupertypes3 = intersectionTypeConstructor.mo5461getSupertypes();
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(mo5461getSupertypes3, 10));
        Iterator<T> it3 = mo5461getSupertypes3.iterator();
        while (it3.hasNext()) {
            arrayList3.add(TypeUtilsKt.makeNullable((KotlinType) it3.next()));
            z = true;
        }
        IntersectionTypeConstructor intersectionTypeConstructor2 = z ? new IntersectionTypeConstructor(arrayList3) : null;
        if (intersectionTypeConstructor2 != null) {
            intersectionTypeConstructor = intersectionTypeConstructor2;
        }
        return intersectionTypeConstructor.createType();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker
    public UnwrappedType transformToNewType(UnwrappedType type) {
        SimpleType flexibleType;
        Intrinsics.checkParameterIsNotNull(type, "type");
        if (type instanceof SimpleType) {
            flexibleType = transformToNewType((SimpleType) type);
        } else if (type instanceof FlexibleType) {
            FlexibleType flexibleType2 = (FlexibleType) type;
            SimpleType transformToNewType = transformToNewType(flexibleType2.getLowerBound());
            SimpleType transformToNewType2 = transformToNewType(flexibleType2.getUpperBound());
            flexibleType = (transformToNewType == flexibleType2.getLowerBound() && transformToNewType2 == flexibleType2.getUpperBound()) ? type : KotlinTypeFactory.flexibleType(transformToNewType, transformToNewType2);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return TypeWithEnhancementKt.inheritEnhancement(flexibleType, type);
    }
}
