package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.List;
import kotlin.TuplesKt;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.functions.BuiltInFictitiousFunctionClassFactory;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.BuiltInAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: functionTypes.kt */
/* loaded from: classes2.dex */
public final class FunctionTypesKt {
    public static final boolean isFunctionType(KotlinType isFunctionType) {
        Intrinsics.checkParameterIsNotNull(isFunctionType, "$this$isFunctionType");
        ClassifierDescriptor mo5460getDeclarationDescriptor = isFunctionType.getConstructor().mo5460getDeclarationDescriptor();
        return (mo5460getDeclarationDescriptor != null ? getFunctionalClassKind(mo5460getDeclarationDescriptor) : null) == FunctionClassDescriptor.Kind.Function;
    }

    public static final boolean isSuspendFunctionType(KotlinType isSuspendFunctionType) {
        Intrinsics.checkParameterIsNotNull(isSuspendFunctionType, "$this$isSuspendFunctionType");
        ClassifierDescriptor mo5460getDeclarationDescriptor = isSuspendFunctionType.getConstructor().mo5460getDeclarationDescriptor();
        return (mo5460getDeclarationDescriptor != null ? getFunctionalClassKind(mo5460getDeclarationDescriptor) : null) == FunctionClassDescriptor.Kind.SuspendFunction;
    }

    public static final boolean isBuiltinFunctionalType(KotlinType isBuiltinFunctionalType) {
        Intrinsics.checkParameterIsNotNull(isBuiltinFunctionalType, "$this$isBuiltinFunctionalType");
        ClassifierDescriptor mo5460getDeclarationDescriptor = isBuiltinFunctionalType.getConstructor().mo5460getDeclarationDescriptor();
        FunctionClassDescriptor.Kind functionalClassKind = mo5460getDeclarationDescriptor != null ? getFunctionalClassKind(mo5460getDeclarationDescriptor) : null;
        return functionalClassKind == FunctionClassDescriptor.Kind.Function || functionalClassKind == FunctionClassDescriptor.Kind.SuspendFunction;
    }

    public static final boolean isBuiltinExtensionFunctionalType(KotlinType isBuiltinExtensionFunctionalType) {
        Intrinsics.checkParameterIsNotNull(isBuiltinExtensionFunctionalType, "$this$isBuiltinExtensionFunctionalType");
        return isBuiltinFunctionalType(isBuiltinExtensionFunctionalType) && isTypeAnnotatedWithExtensionFunctionType(isBuiltinExtensionFunctionalType);
    }

    private static final boolean isTypeAnnotatedWithExtensionFunctionType(KotlinType kotlinType) {
        Annotations annotations = kotlinType.getAnnotations();
        FqName fqName = KotlinBuiltIns.FQ_NAMES.extensionFunctionType;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.extensionFunctionType");
        return annotations.mo5455findAnnotation(fqName) != null;
    }

    public static final FunctionClassDescriptor.Kind getFunctionalClassKind(DeclarationDescriptor getFunctionalClassKind) {
        Intrinsics.checkParameterIsNotNull(getFunctionalClassKind, "$this$getFunctionalClassKind");
        if ((getFunctionalClassKind instanceof ClassDescriptor) && KotlinBuiltIns.isUnderKotlinPackage(getFunctionalClassKind)) {
            return getFunctionalClassKind(DescriptorUtilsKt.getFqNameUnsafe(getFunctionalClassKind));
        }
        return null;
    }

    private static final FunctionClassDescriptor.Kind getFunctionalClassKind(FqNameUnsafe fqNameUnsafe) {
        if (!fqNameUnsafe.isSafe() || fqNameUnsafe.isRoot()) {
            return null;
        }
        BuiltInFictitiousFunctionClassFactory.Companion companion = BuiltInFictitiousFunctionClassFactory.Companion;
        String asString = fqNameUnsafe.shortName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "shortName().asString()");
        FqName parent = fqNameUnsafe.toSafe().parent();
        Intrinsics.checkExpressionValueIsNotNull(parent, "toSafe().parent()");
        return companion.getFunctionalClassKind(asString, parent);
    }

    public static final KotlinType getReceiverTypeFromFunctionType(KotlinType getReceiverTypeFromFunctionType) {
        Intrinsics.checkParameterIsNotNull(getReceiverTypeFromFunctionType, "$this$getReceiverTypeFromFunctionType");
        boolean isBuiltinFunctionalType = isBuiltinFunctionalType(getReceiverTypeFromFunctionType);
        if (!_Assertions.ENABLED || isBuiltinFunctionalType) {
            if (isTypeAnnotatedWithExtensionFunctionType(getReceiverTypeFromFunctionType)) {
                return ((TypeProjection) CollectionsKt.first((List) getReceiverTypeFromFunctionType.getArguments())).getType();
            }
            return null;
        }
        throw new AssertionError("Not a function type: " + getReceiverTypeFromFunctionType);
    }

    public static final KotlinType getReturnTypeFromFunctionType(KotlinType getReturnTypeFromFunctionType) {
        Intrinsics.checkParameterIsNotNull(getReturnTypeFromFunctionType, "$this$getReturnTypeFromFunctionType");
        boolean isBuiltinFunctionalType = isBuiltinFunctionalType(getReturnTypeFromFunctionType);
        if (!_Assertions.ENABLED || isBuiltinFunctionalType) {
            KotlinType type = ((TypeProjection) CollectionsKt.last((List) getReturnTypeFromFunctionType.getArguments())).getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "arguments.last().type");
            return type;
        }
        throw new AssertionError("Not a function type: " + getReturnTypeFromFunctionType);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [boolean] */
    public static final List<TypeProjection> getValueParameterTypesFromFunctionType(KotlinType getValueParameterTypesFromFunctionType) {
        Intrinsics.checkParameterIsNotNull(getValueParameterTypesFromFunctionType, "$this$getValueParameterTypesFromFunctionType");
        boolean isBuiltinFunctionalType = isBuiltinFunctionalType(getValueParameterTypesFromFunctionType);
        if (_Assertions.ENABLED && !isBuiltinFunctionalType) {
            throw new AssertionError("Not a function type: " + getValueParameterTypesFromFunctionType);
        }
        List<TypeProjection> arguments = getValueParameterTypesFromFunctionType.getArguments();
        ?? isBuiltinExtensionFunctionalType = isBuiltinExtensionFunctionalType(getValueParameterTypesFromFunctionType);
        int size = arguments.size() - 1;
        boolean z = isBuiltinExtensionFunctionalType <= size;
        if (!_Assertions.ENABLED || z) {
            return arguments.subList(isBuiltinExtensionFunctionalType == true ? 1 : 0, size);
        }
        throw new AssertionError("Not an exact function type: " + getValueParameterTypesFromFunctionType);
    }

    public static final Name extractParameterNameFromFunctionTypeArgument(KotlinType extractParameterNameFromFunctionTypeArgument) {
        String value;
        Intrinsics.checkParameterIsNotNull(extractParameterNameFromFunctionTypeArgument, "$this$extractParameterNameFromFunctionTypeArgument");
        Annotations annotations = extractParameterNameFromFunctionTypeArgument.getAnnotations();
        FqName fqName = KotlinBuiltIns.FQ_NAMES.parameterName;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.parameterName");
        AnnotationDescriptor mo5455findAnnotation = annotations.mo5455findAnnotation(fqName);
        if (mo5455findAnnotation != null) {
            Object singleOrNull = CollectionsKt.singleOrNull(mo5455findAnnotation.getAllValueArguments().values());
            if (!(singleOrNull instanceof StringValue)) {
                singleOrNull = null;
            }
            StringValue stringValue = (StringValue) singleOrNull;
            if (stringValue != null && (value = stringValue.getValue()) != null) {
                if (!Name.isValidIdentifier(value)) {
                    value = null;
                }
                if (value != null) {
                    return Name.identifier(value);
                }
            }
        }
        return null;
    }

    public static final List<TypeProjection> getFunctionTypeArgumentProjections(KotlinType kotlinType, List<? extends KotlinType> parameterTypes, List<Name> list, KotlinType returnType, KotlinBuiltIns builtIns) {
        Name name;
        Intrinsics.checkParameterIsNotNull(parameterTypes, "parameterTypes");
        Intrinsics.checkParameterIsNotNull(returnType, "returnType");
        Intrinsics.checkParameterIsNotNull(builtIns, "builtIns");
        int i = 0;
        ArrayList arrayList = new ArrayList(parameterTypes.size() + (kotlinType != null ? 1 : 0) + 1);
        ArrayList arrayList2 = arrayList;
        kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arrayList2, kotlinType != null ? TypeUtilsKt.asTypeProjection(kotlinType) : null);
        for (Object obj : parameterTypes) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            KotlinType kotlinType2 = (KotlinType) obj;
            if (list == null || (name = list.get(i)) == null || name.isSpecial()) {
                name = null;
            }
            if (name != null) {
                FqName fqName = KotlinBuiltIns.FQ_NAMES.parameterName;
                Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.parameterName");
                Name identifier = Name.identifier("name");
                String asString = name.asString();
                Intrinsics.checkExpressionValueIsNotNull(asString, "name.asString()");
                kotlinType2 = TypeUtilsKt.replaceAnnotations(kotlinType2, Annotations.Companion.create(CollectionsKt.plus(kotlinType2.getAnnotations(), new BuiltInAnnotationDescriptor(builtIns, fqName, MapsKt.mapOf(TuplesKt.m3968to(identifier, new StringValue(asString)))))));
            }
            arrayList2.add(TypeUtilsKt.asTypeProjection(kotlinType2));
            i = i2;
        }
        arrayList.add(TypeUtilsKt.asTypeProjection(returnType));
        return arrayList;
    }

    public static /* synthetic */ SimpleType createFunctionType$default(KotlinBuiltIns kotlinBuiltIns, Annotations annotations, KotlinType kotlinType, List list, List list2, KotlinType kotlinType2, boolean z, int i, Object obj) {
        if ((i & 64) != 0) {
            z = false;
        }
        return createFunctionType(kotlinBuiltIns, annotations, kotlinType, list, list2, kotlinType2, z);
    }

    public static final SimpleType createFunctionType(KotlinBuiltIns builtIns, Annotations annotations, KotlinType kotlinType, List<? extends KotlinType> parameterTypes, List<Name> list, KotlinType returnType, boolean z) {
        Intrinsics.checkParameterIsNotNull(builtIns, "builtIns");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(parameterTypes, "parameterTypes");
        Intrinsics.checkParameterIsNotNull(returnType, "returnType");
        List<TypeProjection> functionTypeArgumentProjections = getFunctionTypeArgumentProjections(kotlinType, parameterTypes, list, returnType, builtIns);
        int size = parameterTypes.size();
        if (kotlinType != null) {
            size++;
        }
        ClassDescriptor suspendFunction = z ? builtIns.getSuspendFunction(size) : builtIns.getFunction(size);
        Intrinsics.checkExpressionValueIsNotNull(suspendFunction, "if (suspendFunction) bui…tFunction(parameterCount)");
        if (kotlinType != null) {
            FqName fqName = KotlinBuiltIns.FQ_NAMES.extensionFunctionType;
            Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.extensionFunctionType");
            if (annotations.mo5455findAnnotation(fqName) == null) {
                Annotations.Companion companion = Annotations.Companion;
                FqName fqName2 = KotlinBuiltIns.FQ_NAMES.extensionFunctionType;
                Intrinsics.checkExpressionValueIsNotNull(fqName2, "KotlinBuiltIns.FQ_NAMES.extensionFunctionType");
                annotations = companion.create(CollectionsKt.plus(annotations, new BuiltInAnnotationDescriptor(builtIns, fqName2, MapsKt.emptyMap())));
            }
        }
        return KotlinTypeFactory.simpleNotNullType(annotations, suspendFunction, functionTypeArgumentProjections);
    }
}
