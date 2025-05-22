package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: typeParameterUtils.kt */
/* loaded from: classes2.dex */
public final class TypeParameterUtilsKt {
    public static final List<TypeParameterDescriptor> computeConstructorTypeParameters(ClassifierDescriptorWithTypeParameters computeConstructorTypeParameters) {
        List<TypeParameterDescriptor> list;
        DeclarationDescriptor declarationDescriptor;
        TypeConstructor typeConstructor;
        Intrinsics.checkParameterIsNotNull(computeConstructorTypeParameters, "$this$computeConstructorTypeParameters");
        List<TypeParameterDescriptor> declaredTypeParameters = computeConstructorTypeParameters.getDeclaredTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(declaredTypeParameters, "declaredTypeParameters");
        if (!computeConstructorTypeParameters.isInner() && !(computeConstructorTypeParameters.getContainingDeclaration() instanceof CallableDescriptor)) {
            return declaredTypeParameters;
        }
        ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters = computeConstructorTypeParameters;
        List list2 = SequencesKt.toList(SequencesKt.flatMap(SequencesKt.takeWhile(DescriptorUtilsKt.getParents(classifierDescriptorWithTypeParameters), new Function1<DeclarationDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(DeclarationDescriptor declarationDescriptor2) {
                return Boolean.valueOf(invoke2(declarationDescriptor2));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(DeclarationDescriptor it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it instanceof CallableDescriptor;
            }
        }), new Function1<DeclarationDescriptor, Sequence<? extends TypeParameterDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$2
            @Override // kotlin.jvm.functions.Function1
            public final Sequence<TypeParameterDescriptor> invoke(DeclarationDescriptor it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                List<TypeParameterDescriptor> typeParameters = ((CallableDescriptor) it).getTypeParameters();
                Intrinsics.checkExpressionValueIsNotNull(typeParameters, "(it as CallableDescriptor).typeParameters");
                return CollectionsKt.asSequence(typeParameters);
            }
        }));
        Iterator<DeclarationDescriptor> it = DescriptorUtilsKt.getParents(classifierDescriptorWithTypeParameters).iterator();
        while (true) {
            list = null;
            if (!it.hasNext()) {
                declarationDescriptor = null;
                break;
            }
            declarationDescriptor = it.next();
            if (declarationDescriptor instanceof ClassDescriptor) {
                break;
            }
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
        if (classDescriptor != null && (typeConstructor = classDescriptor.getTypeConstructor()) != null) {
            list = typeConstructor.getParameters();
        }
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        if (list2.isEmpty() && list.isEmpty()) {
            List<TypeParameterDescriptor> declaredTypeParameters2 = computeConstructorTypeParameters.getDeclaredTypeParameters();
            Intrinsics.checkExpressionValueIsNotNull(declaredTypeParameters2, "declaredTypeParameters");
            return declaredTypeParameters2;
        }
        List<TypeParameterDescriptor> plus = CollectionsKt.plus((Collection) list2, (Iterable) list);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(plus, 10));
        for (TypeParameterDescriptor it2 : plus) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            arrayList.add(capturedCopyForInnerDeclaration(it2, classifierDescriptorWithTypeParameters, declaredTypeParameters.size()));
        }
        return CollectionsKt.plus((Collection) declaredTypeParameters, (Iterable) arrayList);
    }

    private static final CapturedTypeParameterDescriptor capturedCopyForInnerDeclaration(TypeParameterDescriptor typeParameterDescriptor, DeclarationDescriptor declarationDescriptor, int i) {
        return new CapturedTypeParameterDescriptor(typeParameterDescriptor, declarationDescriptor, i);
    }

    public static final PossiblyInnerType buildPossiblyInnerType(KotlinType buildPossiblyInnerType) {
        Intrinsics.checkParameterIsNotNull(buildPossiblyInnerType, "$this$buildPossiblyInnerType");
        ClassifierDescriptor mo5460getDeclarationDescriptor = buildPossiblyInnerType.getConstructor().mo5460getDeclarationDescriptor();
        if (!(mo5460getDeclarationDescriptor instanceof ClassifierDescriptorWithTypeParameters)) {
            mo5460getDeclarationDescriptor = null;
        }
        return buildPossiblyInnerType(buildPossiblyInnerType, (ClassifierDescriptorWithTypeParameters) mo5460getDeclarationDescriptor, 0);
    }

    private static final PossiblyInnerType buildPossiblyInnerType(KotlinType kotlinType, ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters, int i) {
        if (classifierDescriptorWithTypeParameters != null) {
            ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters2 = classifierDescriptorWithTypeParameters;
            if (!ErrorUtils.isError(classifierDescriptorWithTypeParameters2)) {
                int size = classifierDescriptorWithTypeParameters.getDeclaredTypeParameters().size() + i;
                if (!classifierDescriptorWithTypeParameters.isInner()) {
                    boolean z = size == kotlinType.getArguments().size() || DescriptorUtils.isLocal(classifierDescriptorWithTypeParameters2);
                    if (_Assertions.ENABLED && !z) {
                        throw new AssertionError((kotlinType.getArguments().size() - size) + " trailing arguments were found in " + kotlinType + " type");
                    }
                    return new PossiblyInnerType(classifierDescriptorWithTypeParameters, kotlinType.getArguments().subList(i, kotlinType.getArguments().size()), null);
                }
                List<TypeProjection> subList = kotlinType.getArguments().subList(i, size);
                DeclarationDescriptor containingDeclaration = classifierDescriptorWithTypeParameters.getContainingDeclaration();
                return new PossiblyInnerType(classifierDescriptorWithTypeParameters, subList, buildPossiblyInnerType(kotlinType, (ClassifierDescriptorWithTypeParameters) (containingDeclaration instanceof ClassifierDescriptorWithTypeParameters ? containingDeclaration : null), size));
            }
        }
        return null;
    }
}
