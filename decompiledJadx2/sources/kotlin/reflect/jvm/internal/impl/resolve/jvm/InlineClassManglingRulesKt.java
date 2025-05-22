package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: inlineClassManglingRules.kt */
/* loaded from: classes2.dex */
public final class InlineClassManglingRulesKt {
    public static final boolean shouldHideConstructorDueToInlineClassTypeValueParameters(CallableMemberDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (!(descriptor instanceof ClassConstructorDescriptor)) {
            descriptor = null;
        }
        ClassConstructorDescriptor classConstructorDescriptor = (ClassConstructorDescriptor) descriptor;
        if (classConstructorDescriptor == null || Visibilities.isPrivate(classConstructorDescriptor.getVisibility())) {
            return false;
        }
        ClassDescriptor constructedClass = classConstructorDescriptor.getConstructedClass();
        Intrinsics.checkExpressionValueIsNotNull(constructedClass, "constructorDescriptor.constructedClass");
        if (constructedClass.isInline() || DescriptorUtils.isSealedClass(classConstructorDescriptor.getConstructedClass())) {
            return false;
        }
        List<ValueParameterDescriptor> valueParameters = classConstructorDescriptor.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "constructorDescriptor.valueParameters");
        List<ValueParameterDescriptor> list = valueParameters;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        for (ValueParameterDescriptor it : list) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            KotlinType type = it.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "it.type");
            if (requiresFunctionNameMangling(type)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isInlineClassThatRequiresMangling(DeclarationDescriptor isInlineClassThatRequiresMangling) {
        Intrinsics.checkParameterIsNotNull(isInlineClassThatRequiresMangling, "$this$isInlineClassThatRequiresMangling");
        return InlineClassesUtilsKt.isInlineClass(isInlineClassThatRequiresMangling) && !isDontMangleClass((ClassDescriptor) isInlineClassThatRequiresMangling);
    }

    public static final boolean isInlineClassThatRequiresMangling(KotlinType isInlineClassThatRequiresMangling) {
        Intrinsics.checkParameterIsNotNull(isInlineClassThatRequiresMangling, "$this$isInlineClassThatRequiresMangling");
        ClassifierDescriptor mo5460getDeclarationDescriptor = isInlineClassThatRequiresMangling.getConstructor().mo5460getDeclarationDescriptor();
        return mo5460getDeclarationDescriptor != null && isInlineClassThatRequiresMangling(mo5460getDeclarationDescriptor);
    }

    private static final boolean requiresFunctionNameMangling(KotlinType kotlinType) {
        return isInlineClassThatRequiresMangling(kotlinType) || isTypeParameterWithUpperBoundThatRequiresMangling(kotlinType);
    }

    private static final boolean isDontMangleClass(ClassDescriptor classDescriptor) {
        return Intrinsics.areEqual(DescriptorUtilsKt.getFqNameSafe(classDescriptor), DescriptorUtils.RESULT_FQ_NAME);
    }

    private static final boolean isTypeParameterWithUpperBoundThatRequiresMangling(KotlinType kotlinType) {
        ClassifierDescriptor mo5460getDeclarationDescriptor = kotlinType.getConstructor().mo5460getDeclarationDescriptor();
        if (!(mo5460getDeclarationDescriptor instanceof TypeParameterDescriptor)) {
            mo5460getDeclarationDescriptor = null;
        }
        TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) mo5460getDeclarationDescriptor;
        if (typeParameterDescriptor != null) {
            return requiresFunctionNameMangling(TypeUtilsKt.getRepresentativeUpperBound(typeParameterDescriptor));
        }
        return false;
    }
}
