package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: StarProjectionImpl.kt */
/* loaded from: classes2.dex */
public final class StarProjectionImplKt {
    public static final KotlinType starProjectionType(TypeParameterDescriptor starProjectionType) {
        Intrinsics.checkParameterIsNotNull(starProjectionType, "$this$starProjectionType");
        DeclarationDescriptor containingDeclaration = starProjectionType.getContainingDeclaration();
        if (containingDeclaration == null) {
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassifierDescriptorWithTypeParameters");
        }
        TypeConstructor typeConstructor = ((ClassifierDescriptorWithTypeParameters) containingDeclaration).getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "classDescriptor.typeConstructor");
        List<TypeParameterDescriptor> parameters = typeConstructor.getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "classDescriptor.typeConstructor.parameters");
        List<TypeParameterDescriptor> list = parameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (TypeParameterDescriptor it : list) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            arrayList.add(it.getTypeConstructor());
        }
        final ArrayList arrayList2 = arrayList;
        TypeSubstitutor create = TypeSubstitutor.create(new TypeConstructorSubstitution() { // from class: kotlin.reflect.jvm.internal.impl.types.StarProjectionImplKt$starProjectionType$1
            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution
            public TypeProjection get(TypeConstructor key) {
                Intrinsics.checkParameterIsNotNull(key, "key");
                if (!arrayList2.contains(key)) {
                    return null;
                }
                ClassifierDescriptor mo5460getDeclarationDescriptor = key.mo5460getDeclarationDescriptor();
                if (mo5460getDeclarationDescriptor != null) {
                    return TypeUtils.makeStarProjection((TypeParameterDescriptor) mo5460getDeclarationDescriptor);
                }
                throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
            }
        });
        List<KotlinType> upperBounds = starProjectionType.getUpperBounds();
        Intrinsics.checkExpressionValueIsNotNull(upperBounds, "this.upperBounds");
        KotlinType substitute = create.substitute((KotlinType) CollectionsKt.first((List) upperBounds), Variance.OUT_VARIANCE);
        if (substitute != null) {
            return substitute;
        }
        SimpleType defaultBound = DescriptorUtilsKt.getBuiltIns(starProjectionType).getDefaultBound();
        Intrinsics.checkExpressionValueIsNotNull(defaultBound, "builtIns.defaultBound");
        return defaultBound;
    }
}
