package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: descriptorUtil.kt */
/* loaded from: classes2.dex */
public final class DescriptorUtilKt {
    public static final ClassDescriptor resolveClassByFqName(ModuleDescriptor resolveClassByFqName, FqName fqName, LookupLocation lookupLocation) {
        ClassifierDescriptor classifierDescriptor;
        MemberScope unsubstitutedInnerClassesScope;
        Intrinsics.checkParameterIsNotNull(resolveClassByFqName, "$this$resolveClassByFqName");
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "lookupLocation");
        if (fqName.isRoot()) {
            return null;
        }
        FqName parent = fqName.parent();
        Intrinsics.checkExpressionValueIsNotNull(parent, "fqName.parent()");
        MemberScope memberScope = resolveClassByFqName.getPackage(parent).getMemberScope();
        Name shortName = fqName.shortName();
        Intrinsics.checkExpressionValueIsNotNull(shortName, "fqName.shortName()");
        ClassifierDescriptor contributedClassifier = memberScope.mo5462getContributedClassifier(shortName, lookupLocation);
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier;
        if (classDescriptor != null) {
            return classDescriptor;
        }
        FqName parent2 = fqName.parent();
        Intrinsics.checkExpressionValueIsNotNull(parent2, "fqName.parent()");
        ClassDescriptor resolveClassByFqName2 = resolveClassByFqName(resolveClassByFqName, parent2, lookupLocation);
        if (resolveClassByFqName2 == null || (unsubstitutedInnerClassesScope = resolveClassByFqName2.getUnsubstitutedInnerClassesScope()) == null) {
            classifierDescriptor = null;
        } else {
            Name shortName2 = fqName.shortName();
            Intrinsics.checkExpressionValueIsNotNull(shortName2, "fqName.shortName()");
            classifierDescriptor = unsubstitutedInnerClassesScope.mo5462getContributedClassifier(shortName2, lookupLocation);
        }
        return (ClassDescriptor) (classifierDescriptor instanceof ClassDescriptor ? classifierDescriptor : null);
    }
}
