package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: RawType.kt */
/* loaded from: classes2.dex */
public final class RawSubstitution extends TypeSubstitution {
    public static final RawSubstitution INSTANCE = new RawSubstitution();
    private static final JavaTypeAttributes lowerTypeAttr = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null).withFlexibility(JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND);
    private static final JavaTypeAttributes upperTypeAttr = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null).withFlexibility(JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND);

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[JavaTypeFlexibility.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND.ordinal()] = 1;
            $EnumSwitchMapping$0[JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND.ordinal()] = 2;
            $EnumSwitchMapping$0[JavaTypeFlexibility.INFLEXIBLE.ordinal()] = 3;
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean isEmpty() {
        return false;
    }

    private RawSubstitution() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    /* renamed from: get */
    public TypeProjectionImpl mo5465get(KotlinType key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return new TypeProjectionImpl(eraseType(key));
    }

    private final KotlinType eraseType(KotlinType kotlinType) {
        RawTypeImpl rawTypeImpl;
        ClassifierDescriptor mo5460getDeclarationDescriptor = kotlinType.getConstructor().mo5460getDeclarationDescriptor();
        if (mo5460getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            return eraseType(JavaTypeResolverKt.getErasedUpperBound$default((TypeParameterDescriptor) mo5460getDeclarationDescriptor, null, null, 3, null));
        }
        if (mo5460getDeclarationDescriptor instanceof ClassDescriptor) {
            ClassDescriptor classDescriptor = (ClassDescriptor) mo5460getDeclarationDescriptor;
            Pair<SimpleType, Boolean> eraseInflexibleBasedOnClassDescriptor = eraseInflexibleBasedOnClassDescriptor(FlexibleTypesKt.lowerIfFlexible(kotlinType), classDescriptor, lowerTypeAttr);
            SimpleType component1 = eraseInflexibleBasedOnClassDescriptor.component1();
            boolean booleanValue = eraseInflexibleBasedOnClassDescriptor.component2().booleanValue();
            Pair<SimpleType, Boolean> eraseInflexibleBasedOnClassDescriptor2 = eraseInflexibleBasedOnClassDescriptor(FlexibleTypesKt.upperIfFlexible(kotlinType), classDescriptor, upperTypeAttr);
            SimpleType component12 = eraseInflexibleBasedOnClassDescriptor2.component1();
            boolean booleanValue2 = eraseInflexibleBasedOnClassDescriptor2.component2().booleanValue();
            if (booleanValue || booleanValue2) {
                rawTypeImpl = new RawTypeImpl(component1, component12);
            } else {
                rawTypeImpl = KotlinTypeFactory.flexibleType(component1, component12);
            }
            return rawTypeImpl;
        }
        throw new IllegalStateException(("Unexpected declaration kind: " + mo5460getDeclarationDescriptor).toString());
    }

    private final Pair<SimpleType, Boolean> eraseInflexibleBasedOnClassDescriptor(SimpleType simpleType, ClassDescriptor classDescriptor, JavaTypeAttributes javaTypeAttributes) {
        if (simpleType.getConstructor().getParameters().isEmpty()) {
            return TuplesKt.m3968to(simpleType, false);
        }
        SimpleType simpleType2 = simpleType;
        if (KotlinBuiltIns.isArray(simpleType2)) {
            TypeProjection typeProjection = simpleType.getArguments().get(0);
            Variance projectionKind = typeProjection.getProjectionKind();
            KotlinType type = typeProjection.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "componentTypeProjection.type");
            return TuplesKt.m3968to(KotlinTypeFactory.simpleType(simpleType.getAnnotations(), simpleType.getConstructor(), CollectionsKt.listOf(new TypeProjectionImpl(projectionKind, eraseType(type))), simpleType.isMarkedNullable()), false);
        }
        if (KotlinTypeKt.isError(simpleType2)) {
            return TuplesKt.m3968to(ErrorUtils.createErrorType("Raw error type: " + simpleType.getConstructor()), false);
        }
        Annotations annotations = simpleType.getAnnotations();
        TypeConstructor constructor = simpleType.getConstructor();
        List<TypeParameterDescriptor> parameters = simpleType.getConstructor().getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "type.constructor.parameters");
        List<TypeParameterDescriptor> list = parameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (TypeParameterDescriptor parameter : list) {
            RawSubstitution rawSubstitution = INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(parameter, "parameter");
            arrayList.add(computeProjection$default(rawSubstitution, parameter, javaTypeAttributes, null, 4, null));
        }
        boolean isMarkedNullable = simpleType.isMarkedNullable();
        MemberScope memberScope = classDescriptor.getMemberScope(INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(memberScope, "declaration.getMemberScope(RawSubstitution)");
        return TuplesKt.m3968to(KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(annotations, constructor, arrayList, isMarkedNullable, memberScope), true);
    }

    public static /* synthetic */ TypeProjection computeProjection$default(RawSubstitution rawSubstitution, TypeParameterDescriptor typeParameterDescriptor, JavaTypeAttributes javaTypeAttributes, KotlinType kotlinType, int i, Object obj) {
        if ((i & 4) != 0) {
            kotlinType = JavaTypeResolverKt.getErasedUpperBound$default(typeParameterDescriptor, null, null, 3, null);
        }
        return rawSubstitution.computeProjection(typeParameterDescriptor, javaTypeAttributes, kotlinType);
    }

    public final TypeProjection computeProjection(TypeParameterDescriptor parameter, JavaTypeAttributes attr, KotlinType erasedUpperBound) {
        TypeProjectionImpl makeStarProjection;
        Intrinsics.checkParameterIsNotNull(parameter, "parameter");
        Intrinsics.checkParameterIsNotNull(attr, "attr");
        Intrinsics.checkParameterIsNotNull(erasedUpperBound, "erasedUpperBound");
        int i = WhenMappings.$EnumSwitchMapping$0[attr.getFlexibility().ordinal()];
        if (i == 1) {
            return new TypeProjectionImpl(Variance.INVARIANT, erasedUpperBound);
        }
        if (i != 2 && i != 3) {
            throw new NoWhenBranchMatchedException();
        }
        if (!parameter.getVariance().getAllowsOutPosition()) {
            return new TypeProjectionImpl(Variance.INVARIANT, DescriptorUtilsKt.getBuiltIns(parameter).getNothingType());
        }
        Intrinsics.checkExpressionValueIsNotNull(erasedUpperBound.getConstructor().getParameters(), "erasedUpperBound.constructor.parameters");
        if (!r0.isEmpty()) {
            makeStarProjection = new TypeProjectionImpl(Variance.OUT_VARIANCE, erasedUpperBound);
        } else {
            makeStarProjection = JavaTypeResolverKt.makeStarProjection(parameter, attr);
        }
        return makeStarProjection;
    }
}
