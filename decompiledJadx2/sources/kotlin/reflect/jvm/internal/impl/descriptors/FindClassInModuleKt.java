package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.sequences.SequencesKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: findClassInModule.kt */
/* loaded from: classes2.dex */
public final class FindClassInModuleKt {
    public static final ClassDescriptor findClassAcrossModuleDependencies(ModuleDescriptor findClassAcrossModuleDependencies, ClassId classId) {
        Intrinsics.checkParameterIsNotNull(findClassAcrossModuleDependencies, "$this$findClassAcrossModuleDependencies");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        FqName packageFqName = classId.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName, "classId.packageFqName");
        PackageViewDescriptor packageViewDescriptor = findClassAcrossModuleDependencies.getPackage(packageFqName);
        List<Name> pathSegments = classId.getRelativeClassName().pathSegments();
        Intrinsics.checkExpressionValueIsNotNull(pathSegments, "classId.relativeClassName.pathSegments()");
        MemberScope memberScope = packageViewDescriptor.getMemberScope();
        Object first = CollectionsKt.first((List<? extends Object>) pathSegments);
        Intrinsics.checkExpressionValueIsNotNull(first, "segments.first()");
        ClassifierDescriptor contributedClassifier = memberScope.mo5462getContributedClassifier((Name) first, NoLookupLocation.FROM_DESERIALIZATION);
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier;
        if (classDescriptor == null) {
            return null;
        }
        for (Name name : pathSegments.subList(1, pathSegments.size())) {
            MemberScope unsubstitutedInnerClassesScope = classDescriptor.getUnsubstitutedInnerClassesScope();
            Intrinsics.checkExpressionValueIsNotNull(name, "name");
            ClassifierDescriptor contributedClassifier2 = unsubstitutedInnerClassesScope.mo5462getContributedClassifier(name, NoLookupLocation.FROM_DESERIALIZATION);
            if (!(contributedClassifier2 instanceof ClassDescriptor)) {
                contributedClassifier2 = null;
            }
            classDescriptor = (ClassDescriptor) contributedClassifier2;
            if (classDescriptor == null) {
                return null;
            }
        }
        return classDescriptor;
    }

    public static final ClassDescriptor findNonGenericClassAcrossDependencies(ModuleDescriptor findNonGenericClassAcrossDependencies, ClassId classId, NotFoundClasses notFoundClasses) {
        Intrinsics.checkParameterIsNotNull(findNonGenericClassAcrossDependencies, "$this$findNonGenericClassAcrossDependencies");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        Intrinsics.checkParameterIsNotNull(notFoundClasses, "notFoundClasses");
        ClassDescriptor findClassAcrossModuleDependencies = findClassAcrossModuleDependencies(findNonGenericClassAcrossDependencies, classId);
        return findClassAcrossModuleDependencies != null ? findClassAcrossModuleDependencies : notFoundClasses.getClass(classId, SequencesKt.toList(SequencesKt.map(SequencesKt.generateSequence(classId, C7626x24bfe126.INSTANCE), new Function1<ClassId, Integer>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final int invoke2(ClassId it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return 0;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(ClassId classId2) {
                return Integer.valueOf(invoke2(classId2));
            }
        })));
    }

    public static final TypeAliasDescriptor findTypeAliasAcrossModuleDependencies(ModuleDescriptor findTypeAliasAcrossModuleDependencies, ClassId classId) {
        Intrinsics.checkParameterIsNotNull(findTypeAliasAcrossModuleDependencies, "$this$findTypeAliasAcrossModuleDependencies");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        FqName packageFqName = classId.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName, "classId.packageFqName");
        PackageViewDescriptor packageViewDescriptor = findTypeAliasAcrossModuleDependencies.getPackage(packageFqName);
        List<Name> pathSegments = classId.getRelativeClassName().pathSegments();
        Intrinsics.checkExpressionValueIsNotNull(pathSegments, "classId.relativeClassName.pathSegments()");
        int size = pathSegments.size() - 1;
        MemberScope memberScope = packageViewDescriptor.getMemberScope();
        Object first = CollectionsKt.first((List<? extends Object>) pathSegments);
        Intrinsics.checkExpressionValueIsNotNull(first, "segments.first()");
        ClassifierDescriptor contributedClassifier = memberScope.mo5462getContributedClassifier((Name) first, NoLookupLocation.FROM_DESERIALIZATION);
        if (size == 0) {
            if (!(contributedClassifier instanceof TypeAliasDescriptor)) {
                contributedClassifier = null;
            }
            return (TypeAliasDescriptor) contributedClassifier;
        }
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier;
        if (classDescriptor == null) {
            return null;
        }
        for (Name name : pathSegments.subList(1, size)) {
            MemberScope unsubstitutedInnerClassesScope = classDescriptor.getUnsubstitutedInnerClassesScope();
            Intrinsics.checkExpressionValueIsNotNull(name, "name");
            ClassifierDescriptor contributedClassifier2 = unsubstitutedInnerClassesScope.mo5462getContributedClassifier(name, NoLookupLocation.FROM_DESERIALIZATION);
            if (!(contributedClassifier2 instanceof ClassDescriptor)) {
                contributedClassifier2 = null;
            }
            classDescriptor = (ClassDescriptor) contributedClassifier2;
            if (classDescriptor == null) {
                return null;
            }
        }
        Name lastName = pathSegments.get(size);
        MemberScope unsubstitutedMemberScope = classDescriptor.getUnsubstitutedMemberScope();
        Intrinsics.checkExpressionValueIsNotNull(lastName, "lastName");
        ClassifierDescriptor contributedClassifier3 = unsubstitutedMemberScope.mo5462getContributedClassifier(lastName, NoLookupLocation.FROM_DESERIALIZATION);
        return (TypeAliasDescriptor) (contributedClassifier3 instanceof TypeAliasDescriptor ? contributedClassifier3 : null);
    }
}
