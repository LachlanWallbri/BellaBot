package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: inlineClassesUtils.kt */
/* loaded from: classes2.dex */
public final class InlineClassesUtilsKt {
    public static final ValueParameterDescriptor underlyingRepresentation(ClassDescriptor underlyingRepresentation) {
        ClassConstructorDescriptor mo5454getUnsubstitutedPrimaryConstructor;
        List<ValueParameterDescriptor> valueParameters;
        Intrinsics.checkParameterIsNotNull(underlyingRepresentation, "$this$underlyingRepresentation");
        if (!underlyingRepresentation.isInline() || (mo5454getUnsubstitutedPrimaryConstructor = underlyingRepresentation.mo5454getUnsubstitutedPrimaryConstructor()) == null || (valueParameters = mo5454getUnsubstitutedPrimaryConstructor.getValueParameters()) == null) {
            return null;
        }
        return (ValueParameterDescriptor) CollectionsKt.singleOrNull((List) valueParameters);
    }

    public static final boolean isInlineClass(DeclarationDescriptor isInlineClass) {
        Intrinsics.checkParameterIsNotNull(isInlineClass, "$this$isInlineClass");
        return (isInlineClass instanceof ClassDescriptor) && ((ClassDescriptor) isInlineClass).isInline();
    }

    public static final ValueParameterDescriptor unsubstitutedUnderlyingParameter(KotlinType unsubstitutedUnderlyingParameter) {
        Intrinsics.checkParameterIsNotNull(unsubstitutedUnderlyingParameter, "$this$unsubstitutedUnderlyingParameter");
        ClassifierDescriptor mo5460getDeclarationDescriptor = unsubstitutedUnderlyingParameter.getConstructor().mo5460getDeclarationDescriptor();
        if (!(mo5460getDeclarationDescriptor instanceof ClassDescriptor)) {
            mo5460getDeclarationDescriptor = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) mo5460getDeclarationDescriptor;
        if (classDescriptor != null) {
            return underlyingRepresentation(classDescriptor);
        }
        return null;
    }

    public static final boolean isInlineClassType(KotlinType isInlineClassType) {
        Intrinsics.checkParameterIsNotNull(isInlineClassType, "$this$isInlineClassType");
        ClassifierDescriptor mo5460getDeclarationDescriptor = isInlineClassType.getConstructor().mo5460getDeclarationDescriptor();
        if (mo5460getDeclarationDescriptor != null) {
            return isInlineClass(mo5460getDeclarationDescriptor);
        }
        return false;
    }

    public static final KotlinType substitutedUnderlyingType(KotlinType substitutedUnderlyingType) {
        Intrinsics.checkParameterIsNotNull(substitutedUnderlyingType, "$this$substitutedUnderlyingType");
        ValueParameterDescriptor unsubstitutedUnderlyingParameter = unsubstitutedUnderlyingParameter(substitutedUnderlyingType);
        if (unsubstitutedUnderlyingParameter == null) {
            return null;
        }
        MemberScope memberScope = substitutedUnderlyingType.getMemberScope();
        Name name = unsubstitutedUnderlyingParameter.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "parameter.name");
        PropertyDescriptor propertyDescriptor = (PropertyDescriptor) CollectionsKt.singleOrNull(memberScope.getContributedVariables(name, NoLookupLocation.FOR_ALREADY_TRACKED));
        if (propertyDescriptor != null) {
            return propertyDescriptor.getType();
        }
        return null;
    }

    public static final boolean isGetterOfUnderlyingPropertyOfInlineClass(CallableDescriptor isGetterOfUnderlyingPropertyOfInlineClass) {
        Intrinsics.checkParameterIsNotNull(isGetterOfUnderlyingPropertyOfInlineClass, "$this$isGetterOfUnderlyingPropertyOfInlineClass");
        if (isGetterOfUnderlyingPropertyOfInlineClass instanceof PropertyGetterDescriptor) {
            PropertyDescriptor correspondingProperty = ((PropertyGetterDescriptor) isGetterOfUnderlyingPropertyOfInlineClass).getCorrespondingProperty();
            Intrinsics.checkExpressionValueIsNotNull(correspondingProperty, "correspondingProperty");
            if (isUnderlyingPropertyOfInlineClass(correspondingProperty)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isUnderlyingPropertyOfInlineClass(VariableDescriptor isUnderlyingPropertyOfInlineClass) {
        Intrinsics.checkParameterIsNotNull(isUnderlyingPropertyOfInlineClass, "$this$isUnderlyingPropertyOfInlineClass");
        DeclarationDescriptor containingDeclaration = isUnderlyingPropertyOfInlineClass.getContainingDeclaration();
        Intrinsics.checkExpressionValueIsNotNull(containingDeclaration, "this.containingDeclaration");
        if (!isInlineClass(containingDeclaration)) {
            return false;
        }
        if (containingDeclaration == null) {
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        }
        ValueParameterDescriptor underlyingRepresentation = underlyingRepresentation((ClassDescriptor) containingDeclaration);
        return Intrinsics.areEqual(underlyingRepresentation != null ? underlyingRepresentation.getName() : null, isUnderlyingPropertyOfInlineClass.getName());
    }
}
