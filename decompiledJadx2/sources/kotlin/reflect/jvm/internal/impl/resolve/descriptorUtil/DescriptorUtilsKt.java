package kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: DescriptorUtils.kt */
/* loaded from: classes2.dex */
public final class DescriptorUtilsKt {
    private static final Name RETENTION_PARAMETER_NAME;

    static {
        Name identifier = Name.identifier(ES6Iterator.VALUE_PROPERTY);
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(\"value\")");
        RETENTION_PARAMETER_NAME = identifier;
    }

    public static final FqNameUnsafe getFqNameUnsafe(DeclarationDescriptor fqNameUnsafe) {
        Intrinsics.checkParameterIsNotNull(fqNameUnsafe, "$this$fqNameUnsafe");
        FqNameUnsafe fqName = DescriptorUtils.getFqName(fqNameUnsafe);
        Intrinsics.checkExpressionValueIsNotNull(fqName, "DescriptorUtils.getFqName(this)");
        return fqName;
    }

    public static final FqName getFqNameSafe(DeclarationDescriptor fqNameSafe) {
        Intrinsics.checkParameterIsNotNull(fqNameSafe, "$this$fqNameSafe");
        FqName fqNameSafe2 = DescriptorUtils.getFqNameSafe(fqNameSafe);
        Intrinsics.checkExpressionValueIsNotNull(fqNameSafe2, "DescriptorUtils.getFqNameSafe(this)");
        return fqNameSafe2;
    }

    public static final ModuleDescriptor getModule(DeclarationDescriptor module) {
        Intrinsics.checkParameterIsNotNull(module, "$this$module");
        ModuleDescriptor containingModule = DescriptorUtils.getContainingModule(module);
        Intrinsics.checkExpressionValueIsNotNull(containingModule, "DescriptorUtils.getContainingModule(this)");
        return containingModule;
    }

    public static final ClassDescriptor resolveTopLevelClass(ModuleDescriptor resolveTopLevelClass, FqName topLevelClassFqName, LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(resolveTopLevelClass, "$this$resolveTopLevelClass");
        Intrinsics.checkParameterIsNotNull(topLevelClassFqName, "topLevelClassFqName");
        Intrinsics.checkParameterIsNotNull(location, "location");
        boolean z = !topLevelClassFqName.isRoot();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Assertion failed");
        }
        FqName parent = topLevelClassFqName.parent();
        Intrinsics.checkExpressionValueIsNotNull(parent, "topLevelClassFqName.parent()");
        MemberScope memberScope = resolveTopLevelClass.getPackage(parent).getMemberScope();
        Name shortName = topLevelClassFqName.shortName();
        Intrinsics.checkExpressionValueIsNotNull(shortName, "topLevelClassFqName.shortName()");
        ClassifierDescriptor contributedClassifier = memberScope.mo5462getContributedClassifier(shortName, location);
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        return (ClassDescriptor) contributedClassifier;
    }

    public static final ClassId getClassId(ClassifierDescriptor classifierDescriptor) {
        DeclarationDescriptor containingDeclaration;
        ClassId classId;
        if (classifierDescriptor == null || (containingDeclaration = classifierDescriptor.getContainingDeclaration()) == null) {
            return null;
        }
        if (containingDeclaration instanceof PackageFragmentDescriptor) {
            return new ClassId(((PackageFragmentDescriptor) containingDeclaration).getFqName(), classifierDescriptor.getName());
        }
        if (!(containingDeclaration instanceof ClassifierDescriptorWithTypeParameters) || (classId = getClassId((ClassifierDescriptor) containingDeclaration)) == null) {
            return null;
        }
        return classId.createNestedClassId(classifierDescriptor.getName());
    }

    public static final ClassDescriptor getSuperClassNotAny(ClassDescriptor getSuperClassNotAny) {
        Intrinsics.checkParameterIsNotNull(getSuperClassNotAny, "$this$getSuperClassNotAny");
        for (KotlinType kotlinType : getSuperClassNotAny.getDefaultType().getConstructor().mo5461getSupertypes()) {
            if (!KotlinBuiltIns.isAnyOrNullableAny(kotlinType)) {
                ClassifierDescriptor mo5460getDeclarationDescriptor = kotlinType.getConstructor().mo5460getDeclarationDescriptor();
                if (DescriptorUtils.isClassOrEnumClass(mo5460getDeclarationDescriptor)) {
                    if (mo5460getDeclarationDescriptor != null) {
                        return (ClassDescriptor) mo5460getDeclarationDescriptor;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                }
            }
        }
        return null;
    }

    public static final KotlinBuiltIns getBuiltIns(DeclarationDescriptor builtIns) {
        Intrinsics.checkParameterIsNotNull(builtIns, "$this$builtIns");
        return getModule(builtIns).getBuiltIns();
    }

    public static final boolean declaresOrInheritsDefaultValue(ValueParameterDescriptor declaresOrInheritsDefaultValue) {
        Intrinsics.checkParameterIsNotNull(declaresOrInheritsDefaultValue, "$this$declaresOrInheritsDefaultValue");
        Boolean ifAny = DFS.ifAny(CollectionsKt.listOf(declaresOrInheritsDefaultValue), new DFS.Neighbors<N>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$declaresOrInheritsDefaultValue$1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            public final List<ValueParameterDescriptor> getNeighbors(ValueParameterDescriptor current) {
                Intrinsics.checkExpressionValueIsNotNull(current, "current");
                Collection<ValueParameterDescriptor> overriddenDescriptors = current.getOverriddenDescriptors();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(overriddenDescriptors, 10));
                Iterator<T> it = overriddenDescriptors.iterator();
                while (it.hasNext()) {
                    arrayList.add(((ValueParameterDescriptor) it.next()).getOriginal());
                }
                return arrayList;
            }
        }, DescriptorUtilsKt$declaresOrInheritsDefaultValue$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(ifAny, "DFS.ifAny(\n        listO…eclaresDefaultValue\n    )");
        return ifAny.booleanValue();
    }

    public static final Sequence<DeclarationDescriptor> getParentsWithSelf(DeclarationDescriptor parentsWithSelf) {
        Intrinsics.checkParameterIsNotNull(parentsWithSelf, "$this$parentsWithSelf");
        return SequencesKt.generateSequence(parentsWithSelf, new Function1<DeclarationDescriptor, DeclarationDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$parentsWithSelf$1
            @Override // kotlin.jvm.functions.Function1
            public final DeclarationDescriptor invoke(DeclarationDescriptor it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getContainingDeclaration();
            }
        });
    }

    public static final Sequence<DeclarationDescriptor> getParents(DeclarationDescriptor parents) {
        Intrinsics.checkParameterIsNotNull(parents, "$this$parents");
        return SequencesKt.drop(getParentsWithSelf(parents), 1);
    }

    public static final CallableMemberDescriptor getPropertyIfAccessor(CallableMemberDescriptor propertyIfAccessor) {
        Intrinsics.checkParameterIsNotNull(propertyIfAccessor, "$this$propertyIfAccessor");
        if (!(propertyIfAccessor instanceof PropertyAccessorDescriptor)) {
            return propertyIfAccessor;
        }
        PropertyDescriptor correspondingProperty = ((PropertyAccessorDescriptor) propertyIfAccessor).getCorrespondingProperty();
        Intrinsics.checkExpressionValueIsNotNull(correspondingProperty, "correspondingProperty");
        return correspondingProperty;
    }

    public static final FqName fqNameOrNull(DeclarationDescriptor fqNameOrNull) {
        Intrinsics.checkParameterIsNotNull(fqNameOrNull, "$this$fqNameOrNull");
        FqNameUnsafe fqNameUnsafe = getFqNameUnsafe(fqNameOrNull);
        if (!fqNameUnsafe.isSafe()) {
            fqNameUnsafe = null;
        }
        if (fqNameUnsafe != null) {
            return fqNameUnsafe.toSafe();
        }
        return null;
    }

    public static /* synthetic */ CallableMemberDescriptor firstOverridden$default(CallableMemberDescriptor callableMemberDescriptor, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return firstOverridden(callableMemberDescriptor, z, function1);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor, T] */
    public static final CallableMemberDescriptor firstOverridden(CallableMemberDescriptor firstOverridden, final boolean z, final Function1<? super CallableMemberDescriptor, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(firstOverridden, "$this$firstOverridden");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (CallableMemberDescriptor) 0;
        return (CallableMemberDescriptor) DFS.dfs(CollectionsKt.listOf(firstOverridden), new DFS.Neighbors<N>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$firstOverridden$1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            public final Iterable<CallableMemberDescriptor> getNeighbors(CallableMemberDescriptor callableMemberDescriptor) {
                Collection<? extends CallableMemberDescriptor> emptyList;
                if (z) {
                    callableMemberDescriptor = callableMemberDescriptor != null ? callableMemberDescriptor.getOriginal() : null;
                }
                if (callableMemberDescriptor == null || (emptyList = callableMemberDescriptor.getOverriddenDescriptors()) == null) {
                    emptyList = CollectionsKt.emptyList();
                }
                return emptyList;
            }
        }, new DFS.AbstractNodeHandler<CallableMemberDescriptor, CallableMemberDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$firstOverridden$2
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public boolean beforeChildren(CallableMemberDescriptor current) {
                Intrinsics.checkParameterIsNotNull(current, "current");
                return ((CallableMemberDescriptor) Ref.ObjectRef.this.element) == null;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public void afterChildren(CallableMemberDescriptor current) {
                Intrinsics.checkParameterIsNotNull(current, "current");
                if (((CallableMemberDescriptor) Ref.ObjectRef.this.element) == null && ((Boolean) predicate.invoke(current)).booleanValue()) {
                    Ref.ObjectRef.this.element = current;
                }
            }

            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public CallableMemberDescriptor result() {
                return (CallableMemberDescriptor) Ref.ObjectRef.this.element;
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$computeSealedSubclasses$1] */
    public static final Collection<ClassDescriptor> computeSealedSubclasses(final ClassDescriptor sealedClass) {
        Intrinsics.checkParameterIsNotNull(sealedClass, "sealedClass");
        if (sealedClass.getModality() != Modality.SEALED) {
            return CollectionsKt.emptyList();
        }
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        ?? r1 = new Function2<MemberScope, Boolean, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$computeSealedSubclasses$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(MemberScope memberScope, Boolean bool) {
                invoke(memberScope, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(MemberScope scope, boolean z) {
                Intrinsics.checkParameterIsNotNull(scope, "scope");
                for (DeclarationDescriptor declarationDescriptor : ResolutionScope.DefaultImpls.getContributedDescriptors$default(scope, DescriptorKindFilter.CLASSIFIERS, null, 2, null)) {
                    if (declarationDescriptor instanceof ClassDescriptor) {
                        ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
                        if (DescriptorUtils.isDirectSubclass(classDescriptor, ClassDescriptor.this)) {
                            linkedHashSet.add(declarationDescriptor);
                        }
                        if (z) {
                            MemberScope unsubstitutedInnerClassesScope = classDescriptor.getUnsubstitutedInnerClassesScope();
                            Intrinsics.checkExpressionValueIsNotNull(unsubstitutedInnerClassesScope, "descriptor.unsubstitutedInnerClassesScope");
                            invoke(unsubstitutedInnerClassesScope, z);
                        }
                    }
                }
            }
        };
        DeclarationDescriptor containingDeclaration = sealedClass.getContainingDeclaration();
        Intrinsics.checkExpressionValueIsNotNull(containingDeclaration, "sealedClass.containingDeclaration");
        if (containingDeclaration instanceof PackageFragmentDescriptor) {
            r1.invoke(((PackageFragmentDescriptor) containingDeclaration).getMemberScope(), false);
        }
        MemberScope unsubstitutedInnerClassesScope = sealedClass.getUnsubstitutedInnerClassesScope();
        Intrinsics.checkExpressionValueIsNotNull(unsubstitutedInnerClassesScope, "sealedClass.unsubstitutedInnerClassesScope");
        r1.invoke(unsubstitutedInnerClassesScope, true);
        return linkedHashSet;
    }

    public static final ClassDescriptor getAnnotationClass(AnnotationDescriptor annotationClass) {
        Intrinsics.checkParameterIsNotNull(annotationClass, "$this$annotationClass");
        ClassifierDescriptor mo5460getDeclarationDescriptor = annotationClass.getType().getConstructor().mo5460getDeclarationDescriptor();
        if (!(mo5460getDeclarationDescriptor instanceof ClassDescriptor)) {
            mo5460getDeclarationDescriptor = null;
        }
        return (ClassDescriptor) mo5460getDeclarationDescriptor;
    }

    public static final ConstantValue<?> firstArgument(AnnotationDescriptor firstArgument) {
        Intrinsics.checkParameterIsNotNull(firstArgument, "$this$firstArgument");
        return (ConstantValue) CollectionsKt.firstOrNull(firstArgument.getAllValueArguments().values());
    }
}
