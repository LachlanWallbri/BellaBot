package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassOrPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;

/* compiled from: context.kt */
/* loaded from: classes2.dex */
public final class ContextKt {
    public static final LazyJavaResolverContext child(LazyJavaResolverContext child, TypeParameterResolver typeParameterResolver) {
        Intrinsics.checkParameterIsNotNull(child, "$this$child");
        Intrinsics.checkParameterIsNotNull(typeParameterResolver, "typeParameterResolver");
        return new LazyJavaResolverContext(child.getComponents(), typeParameterResolver, child.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    public static final JavaTypeQualifiersByElementType computeNewDefaultTypeQualifiers(LazyJavaResolverContext computeNewDefaultTypeQualifiers, Annotations additionalAnnotations) {
        EnumMap<AnnotationTypeQualifierResolver.QualifierApplicabilityType, NullabilityQualifierWithMigrationStatus> nullabilityQualifiers;
        Intrinsics.checkParameterIsNotNull(computeNewDefaultTypeQualifiers, "$this$computeNewDefaultTypeQualifiers");
        Intrinsics.checkParameterIsNotNull(additionalAnnotations, "additionalAnnotations");
        if (computeNewDefaultTypeQualifiers.getComponents().getAnnotationTypeQualifierResolver().getDisabled()) {
            return computeNewDefaultTypeQualifiers.getDefaultTypeQualifiers();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<AnnotationDescriptor> it = additionalAnnotations.iterator();
        while (it.hasNext()) {
            NullabilityQualifierWithApplicability extractDefaultNullabilityQualifier = extractDefaultNullabilityQualifier(computeNewDefaultTypeQualifiers, it.next());
            if (extractDefaultNullabilityQualifier != null) {
                arrayList.add(extractDefaultNullabilityQualifier);
            }
        }
        ArrayList<NullabilityQualifierWithApplicability> arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            return computeNewDefaultTypeQualifiers.getDefaultTypeQualifiers();
        }
        JavaTypeQualifiersByElementType defaultTypeQualifiers = computeNewDefaultTypeQualifiers.getDefaultTypeQualifiers();
        EnumMap enumMap = (defaultTypeQualifiers == null || (nullabilityQualifiers = defaultTypeQualifiers.getNullabilityQualifiers()) == null) ? new EnumMap(AnnotationTypeQualifierResolver.QualifierApplicabilityType.class) : new EnumMap((EnumMap) nullabilityQualifiers);
        boolean z = false;
        for (NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability : arrayList2) {
            NullabilityQualifierWithMigrationStatus component1 = nullabilityQualifierWithApplicability.component1();
            Iterator<AnnotationTypeQualifierResolver.QualifierApplicabilityType> it2 = nullabilityQualifierWithApplicability.component2().iterator();
            while (it2.hasNext()) {
                enumMap.put((EnumMap) it2.next(), (AnnotationTypeQualifierResolver.QualifierApplicabilityType) component1);
                z = true;
            }
        }
        return !z ? computeNewDefaultTypeQualifiers.getDefaultTypeQualifiers() : new JavaTypeQualifiersByElementType(enumMap);
    }

    private static final NullabilityQualifierWithApplicability extractDefaultNullabilityQualifier(LazyJavaResolverContext lazyJavaResolverContext, AnnotationDescriptor annotationDescriptor) {
        NullabilityQualifierWithMigrationStatus extractNullability;
        NullabilityQualifierWithMigrationStatus copy$default;
        AnnotationTypeQualifierResolver annotationTypeQualifierResolver = lazyJavaResolverContext.getComponents().getAnnotationTypeQualifierResolver();
        NullabilityQualifierWithApplicability resolveQualifierBuiltInDefaultAnnotation = annotationTypeQualifierResolver.resolveQualifierBuiltInDefaultAnnotation(annotationDescriptor);
        if (resolveQualifierBuiltInDefaultAnnotation != null) {
            return resolveQualifierBuiltInDefaultAnnotation;
        }
        AnnotationTypeQualifierResolver.TypeQualifierWithApplicability resolveTypeQualifierDefaultAnnotation = annotationTypeQualifierResolver.resolveTypeQualifierDefaultAnnotation(annotationDescriptor);
        if (resolveTypeQualifierDefaultAnnotation != null) {
            AnnotationDescriptor component1 = resolveTypeQualifierDefaultAnnotation.component1();
            List<AnnotationTypeQualifierResolver.QualifierApplicabilityType> component2 = resolveTypeQualifierDefaultAnnotation.component2();
            ReportLevel resolveJsr305CustomState = annotationTypeQualifierResolver.resolveJsr305CustomState(annotationDescriptor);
            if (resolveJsr305CustomState == null) {
                resolveJsr305CustomState = annotationTypeQualifierResolver.resolveJsr305AnnotationState(component1);
            }
            if (!resolveJsr305CustomState.isIgnore() && (extractNullability = lazyJavaResolverContext.getComponents().getSignatureEnhancement().extractNullability(component1)) != null && (copy$default = NullabilityQualifierWithMigrationStatus.copy$default(extractNullability, null, resolveJsr305CustomState.isWarning(), 1, null)) != null) {
                return new NullabilityQualifierWithApplicability(copy$default, component2);
            }
        }
        return null;
    }

    public static final LazyJavaResolverContext replaceComponents(LazyJavaResolverContext replaceComponents, JavaResolverComponents components) {
        Intrinsics.checkParameterIsNotNull(replaceComponents, "$this$replaceComponents");
        Intrinsics.checkParameterIsNotNull(components, "components");
        return new LazyJavaResolverContext(components, replaceComponents.getTypeParameterResolver(), replaceComponents.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    private static final LazyJavaResolverContext child(LazyJavaResolverContext lazyJavaResolverContext, DeclarationDescriptor declarationDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, Lazy<JavaTypeQualifiersByElementType> lazy) {
        LazyJavaTypeParameterResolver typeParameterResolver;
        JavaResolverComponents components = lazyJavaResolverContext.getComponents();
        if (javaTypeParameterListOwner != null) {
            typeParameterResolver = new LazyJavaTypeParameterResolver(lazyJavaResolverContext, declarationDescriptor, javaTypeParameterListOwner, i);
        } else {
            typeParameterResolver = lazyJavaResolverContext.getTypeParameterResolver();
        }
        return new LazyJavaResolverContext(components, typeParameterResolver, lazy);
    }

    public static /* synthetic */ LazyJavaResolverContext childForMethod$default(LazyJavaResolverContext lazyJavaResolverContext, DeclarationDescriptor declarationDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return childForMethod(lazyJavaResolverContext, declarationDescriptor, javaTypeParameterListOwner, i);
    }

    public static final LazyJavaResolverContext childForMethod(LazyJavaResolverContext childForMethod, DeclarationDescriptor containingDeclaration, JavaTypeParameterListOwner typeParameterOwner, int i) {
        Intrinsics.checkParameterIsNotNull(childForMethod, "$this$childForMethod");
        Intrinsics.checkParameterIsNotNull(containingDeclaration, "containingDeclaration");
        Intrinsics.checkParameterIsNotNull(typeParameterOwner, "typeParameterOwner");
        return child(childForMethod, containingDeclaration, typeParameterOwner, i, childForMethod.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    public static /* synthetic */ LazyJavaResolverContext childForClassOrPackage$default(LazyJavaResolverContext lazyJavaResolverContext, ClassOrPackageFragmentDescriptor classOrPackageFragmentDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            javaTypeParameterListOwner = (JavaTypeParameterListOwner) null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return childForClassOrPackage(lazyJavaResolverContext, classOrPackageFragmentDescriptor, javaTypeParameterListOwner, i);
    }

    public static final LazyJavaResolverContext childForClassOrPackage(final LazyJavaResolverContext childForClassOrPackage, final ClassOrPackageFragmentDescriptor containingDeclaration, JavaTypeParameterListOwner javaTypeParameterListOwner, int i) {
        Intrinsics.checkParameterIsNotNull(childForClassOrPackage, "$this$childForClassOrPackage");
        Intrinsics.checkParameterIsNotNull(containingDeclaration, "containingDeclaration");
        return child(childForClassOrPackage, containingDeclaration, javaTypeParameterListOwner, i, LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<JavaTypeQualifiersByElementType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt$childForClassOrPackage$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final JavaTypeQualifiersByElementType invoke() {
                return ContextKt.computeNewDefaultTypeQualifiers(LazyJavaResolverContext.this, containingDeclaration.getAnnotations());
            }
        }));
    }

    public static final LazyJavaResolverContext copyWithNewDefaultTypeQualifiers(final LazyJavaResolverContext copyWithNewDefaultTypeQualifiers, final Annotations additionalAnnotations) {
        Intrinsics.checkParameterIsNotNull(copyWithNewDefaultTypeQualifiers, "$this$copyWithNewDefaultTypeQualifiers");
        Intrinsics.checkParameterIsNotNull(additionalAnnotations, "additionalAnnotations");
        return additionalAnnotations.isEmpty() ? copyWithNewDefaultTypeQualifiers : new LazyJavaResolverContext(copyWithNewDefaultTypeQualifiers.getComponents(), copyWithNewDefaultTypeQualifiers.getTypeParameterResolver(), LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<JavaTypeQualifiersByElementType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt$copyWithNewDefaultTypeQualifiers$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final JavaTypeQualifiersByElementType invoke() {
                return ContextKt.computeNewDefaultTypeQualifiers(LazyJavaResolverContext.this, additionalAnnotations);
            }
        }));
    }
}
