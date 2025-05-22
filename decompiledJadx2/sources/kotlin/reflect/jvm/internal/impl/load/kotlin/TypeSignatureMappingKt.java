package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.SuspendFunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeEnhancementKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.io.FilenameUtils;

/* compiled from: typeSignatureMapping.kt */
/* loaded from: classes2.dex */
public final class TypeSignatureMappingKt {
    private static final <T> T boxTypeIfNeeded(JvmTypeFactory<T> jvmTypeFactory, T t, boolean z) {
        return z ? jvmTypeFactory.boxType(t) : t;
    }

    public static /* synthetic */ Object mapType$default(KotlinType kotlinType, JvmTypeFactory jvmTypeFactory, TypeMappingMode typeMappingMode, TypeMappingConfiguration typeMappingConfiguration, JvmDescriptorTypeWriter jvmDescriptorTypeWriter, Function3 function3, boolean z, int i, Object obj) {
        if ((i & 32) != 0) {
            function3 = FunctionsKt.getDO_NOTHING_3();
        }
        return mapType(kotlinType, jvmTypeFactory, typeMappingMode, typeMappingConfiguration, jvmDescriptorTypeWriter, function3, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v48, types: [T, java.lang.Object] */
    public static final <T> T mapType(KotlinType kotlinType, JvmTypeFactory<T> factory, TypeMappingMode mode, TypeMappingConfiguration<? extends T> typeMappingConfiguration, JvmDescriptorTypeWriter<T> jvmDescriptorTypeWriter, Function3<? super KotlinType, ? super T, ? super TypeMappingMode, Unit> writeGenericType, boolean z) {
        T predefinedTypeForClass;
        KotlinType computeExpandedTypeForInlineClass;
        Object mapType;
        Intrinsics.checkParameterIsNotNull(kotlinType, "kotlinType");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(typeMappingConfiguration, "typeMappingConfiguration");
        Intrinsics.checkParameterIsNotNull(writeGenericType, "writeGenericType");
        KotlinType preprocessType = typeMappingConfiguration.preprocessType(kotlinType);
        if (preprocessType != null) {
            return (T) mapType(preprocessType, factory, mode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType, z);
        }
        if (FunctionTypesKt.isSuspendFunctionType(kotlinType)) {
            return (T) mapType(SuspendFunctionTypesKt.transformSuspendFunctionToRuntimeFunctionType(kotlinType, typeMappingConfiguration.releaseCoroutines()), factory, mode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType, z);
        }
        Object mapBuiltInType = mapBuiltInType(kotlinType, factory, mode);
        if (mapBuiltInType != null) {
            ?? r1 = (Object) boxTypeIfNeeded(factory, mapBuiltInType, mode.getNeedPrimitiveBoxing());
            writeGenericType.invoke(kotlinType, r1, mode);
            return r1;
        }
        TypeConstructor constructor = kotlinType.getConstructor();
        if (constructor instanceof IntersectionTypeConstructor) {
            return (T) mapType(TypeUtilsKt.replaceArgumentsWithStarProjections(typeMappingConfiguration.commonSupertype(((IntersectionTypeConstructor) constructor).mo5461getSupertypes())), factory, mode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType, z);
        }
        ClassifierDescriptor mo5460getDeclarationDescriptor = constructor.mo5460getDeclarationDescriptor();
        if (mo5460getDeclarationDescriptor != null) {
            Intrinsics.checkExpressionValueIsNotNull(mo5460getDeclarationDescriptor, "constructor.declarationD…structor of $kotlinType\")");
            if (ErrorUtils.isError(mo5460getDeclarationDescriptor)) {
                T t = (T) factory.createObjectType("error/NonExistentClass");
                if (mo5460getDeclarationDescriptor != null) {
                    typeMappingConfiguration.processErrorType(kotlinType, (ClassDescriptor) mo5460getDeclarationDescriptor);
                    if (jvmDescriptorTypeWriter != 0) {
                        jvmDescriptorTypeWriter.writeClass(t);
                    }
                    return t;
                }
                throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            }
            boolean z2 = mo5460getDeclarationDescriptor instanceof ClassDescriptor;
            if (z2 && KotlinBuiltIns.isArray(kotlinType)) {
                if (kotlinType.getArguments().size() != 1) {
                    throw new UnsupportedOperationException("arrays must have one type argument");
                }
                TypeProjection typeProjection = kotlinType.getArguments().get(0);
                KotlinType type = typeProjection.getType();
                Intrinsics.checkExpressionValueIsNotNull(type, "memberProjection.type");
                if (typeProjection.getProjectionKind() == Variance.IN_VARIANCE) {
                    mapType = factory.createObjectType("java/lang/Object");
                    if (jvmDescriptorTypeWriter != 0) {
                        jvmDescriptorTypeWriter.writeArrayType();
                        jvmDescriptorTypeWriter.writeClass(mapType);
                        jvmDescriptorTypeWriter.writeArrayEnd();
                    }
                } else {
                    if (jvmDescriptorTypeWriter != 0) {
                        jvmDescriptorTypeWriter.writeArrayType();
                    }
                    Variance projectionKind = typeProjection.getProjectionKind();
                    Intrinsics.checkExpressionValueIsNotNull(projectionKind, "memberProjection.projectionKind");
                    mapType = mapType(type, factory, mode.toGenericArgumentMode(projectionKind), typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType, z);
                    if (jvmDescriptorTypeWriter != 0) {
                        jvmDescriptorTypeWriter.writeArrayEnd();
                    }
                }
                return (T) factory.createFromString("[" + factory.toString(mapType));
            }
            if (z2) {
                ClassDescriptor classDescriptor = (ClassDescriptor) mo5460getDeclarationDescriptor;
                if (classDescriptor.isInline() && !mode.getNeedInlineClassWrapping() && (computeExpandedTypeForInlineClass = computeExpandedTypeForInlineClass(kotlinType)) != null) {
                    return (T) mapType(computeExpandedTypeForInlineClass, factory, mode.wrapInlineClassesMode(), typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType, z);
                }
                if (mode.isForAnnotationParameter() && KotlinBuiltIns.isKClass(classDescriptor)) {
                    predefinedTypeForClass = (Object) factory.getJavaLangClassType();
                } else {
                    ClassDescriptor original = classDescriptor.getOriginal();
                    Intrinsics.checkExpressionValueIsNotNull(original, "descriptor.original");
                    predefinedTypeForClass = typeMappingConfiguration.getPredefinedTypeForClass(original);
                    if (predefinedTypeForClass == null) {
                        if (classDescriptor.getKind() == ClassKind.ENUM_ENTRY) {
                            DeclarationDescriptor containingDeclaration = classDescriptor.getContainingDeclaration();
                            if (containingDeclaration == null) {
                                throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                            }
                            classDescriptor = (ClassDescriptor) containingDeclaration;
                        }
                        ClassDescriptor original2 = classDescriptor.getOriginal();
                        Intrinsics.checkExpressionValueIsNotNull(original2, "enumClassIfEnumEntry.original");
                        predefinedTypeForClass = (Object) factory.createObjectType(computeInternalName(original2, typeMappingConfiguration, z));
                    }
                }
                writeGenericType.invoke(kotlinType, predefinedTypeForClass, mode);
                return predefinedTypeForClass;
            }
            if (mo5460getDeclarationDescriptor instanceof TypeParameterDescriptor) {
                T t2 = (T) mapType(TypeUtilsKt.getRepresentativeUpperBound((TypeParameterDescriptor) mo5460getDeclarationDescriptor), factory, mode, typeMappingConfiguration, null, FunctionsKt.getDO_NOTHING_3(), z);
                if (jvmDescriptorTypeWriter != 0) {
                    Name name = mo5460getDeclarationDescriptor.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name, "descriptor.getName()");
                    jvmDescriptorTypeWriter.writeTypeVariable(name, t2);
                }
                return t2;
            }
            throw new UnsupportedOperationException("Unknown type " + kotlinType);
        }
        throw new UnsupportedOperationException("no descriptor for type constructor of " + kotlinType);
    }

    public static final boolean hasVoidReturnType(CallableDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (descriptor instanceof ConstructorDescriptor) {
            return true;
        }
        KotlinType returnType = descriptor.getReturnType();
        if (returnType == null) {
            Intrinsics.throwNpe();
        }
        if (KotlinBuiltIns.isUnit(returnType)) {
            KotlinType returnType2 = descriptor.getReturnType();
            if (returnType2 == null) {
                Intrinsics.throwNpe();
            }
            if (!TypeUtils.isNullableType(returnType2) && !(descriptor instanceof PropertyGetterDescriptor)) {
                return true;
            }
        }
        return false;
    }

    private static final String continuationInternalName(boolean z) {
        FqName fqName;
        if (z) {
            fqName = DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_RELEASE;
        } else {
            fqName = DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL;
        }
        JvmClassName byClassId = JvmClassName.byClassId(ClassId.topLevel(fqName));
        Intrinsics.checkExpressionValueIsNotNull(byClassId, "JvmClassName.byClassId(ClassId.topLevel(fqName))");
        String internalName = byClassId.getInternalName();
        Intrinsics.checkExpressionValueIsNotNull(internalName, "JvmClassName.byClassId(C…vel(fqName)).internalName");
        return internalName;
    }

    private static final <T> T mapBuiltInType(KotlinType kotlinType, JvmTypeFactory<T> jvmTypeFactory, TypeMappingMode typeMappingMode) {
        ClassId mapKotlinToJava;
        ClassifierDescriptor mo5460getDeclarationDescriptor = kotlinType.getConstructor().mo5460getDeclarationDescriptor();
        if (!(mo5460getDeclarationDescriptor instanceof ClassDescriptor)) {
            mo5460getDeclarationDescriptor = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) mo5460getDeclarationDescriptor;
        if (classDescriptor != null) {
            if (classDescriptor == SuspendFunctionTypesKt.getFAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL()) {
                return jvmTypeFactory.createObjectType(continuationInternalName(false));
            }
            if (Intrinsics.areEqual(classDescriptor, SuspendFunctionTypesKt.getFAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE())) {
                return jvmTypeFactory.createObjectType(continuationInternalName(true));
            }
            ClassDescriptor classDescriptor2 = classDescriptor;
            PrimitiveType primitiveType = KotlinBuiltIns.getPrimitiveType(classDescriptor2);
            if (primitiveType != null) {
                JvmPrimitiveType jvmPrimitiveType = JvmPrimitiveType.get(primitiveType);
                Intrinsics.checkExpressionValueIsNotNull(jvmPrimitiveType, "JvmPrimitiveType.get(primitiveType)");
                String desc = jvmPrimitiveType.getDesc();
                Intrinsics.checkExpressionValueIsNotNull(desc, "JvmPrimitiveType.get(primitiveType).desc");
                return (T) boxTypeIfNeeded(jvmTypeFactory, jvmTypeFactory.createFromString(desc), TypeUtils.isNullableType(kotlinType) || TypeEnhancementKt.hasEnhancedNullability(kotlinType));
            }
            PrimitiveType primitiveArrayType = KotlinBuiltIns.getPrimitiveArrayType(classDescriptor2);
            if (primitiveArrayType != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                JvmPrimitiveType jvmPrimitiveType2 = JvmPrimitiveType.get(primitiveArrayType);
                Intrinsics.checkExpressionValueIsNotNull(jvmPrimitiveType2, "JvmPrimitiveType.get(arrayElementType)");
                sb.append(jvmPrimitiveType2.getDesc());
                return jvmTypeFactory.createFromString(sb.toString());
            }
            if (KotlinBuiltIns.isUnderKotlinPackage(classDescriptor2) && (mapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(DescriptorUtilsKt.getFqNameUnsafe(classDescriptor2))) != null) {
                if (!typeMappingMode.getKotlinCollectionsToJavaCollections()) {
                    List<JavaToKotlinClassMap.PlatformMutabilityMapping> mutabilityMappings = JavaToKotlinClassMap.INSTANCE.getMutabilityMappings();
                    if (!(mutabilityMappings instanceof Collection) || !mutabilityMappings.isEmpty()) {
                        Iterator<T> it = mutabilityMappings.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            if (Intrinsics.areEqual(((JavaToKotlinClassMap.PlatformMutabilityMapping) it.next()).getJavaClass(), mapKotlinToJava)) {
                                r3 = true;
                                break;
                            }
                        }
                    }
                    if (r3) {
                        return null;
                    }
                }
                JvmClassName byClassId = JvmClassName.byClassId(mapKotlinToJava);
                Intrinsics.checkExpressionValueIsNotNull(byClassId, "JvmClassName.byClassId(classId)");
                String internalName = byClassId.getInternalName();
                Intrinsics.checkExpressionValueIsNotNull(internalName, "JvmClassName.byClassId(classId).internalName");
                return jvmTypeFactory.createObjectType(internalName);
            }
        }
        return null;
    }

    public static final KotlinType computeExpandedTypeForInlineClass(KotlinType inlineClassType) {
        Intrinsics.checkParameterIsNotNull(inlineClassType, "inlineClassType");
        return computeExpandedTypeInner(inlineClassType, new HashSet());
    }

    public static final KotlinType computeExpandedTypeInner(KotlinType kotlinType, HashSet<ClassifierDescriptor> visitedClassifiers) {
        KotlinType computeExpandedTypeInner;
        Intrinsics.checkParameterIsNotNull(kotlinType, "kotlinType");
        Intrinsics.checkParameterIsNotNull(visitedClassifiers, "visitedClassifiers");
        ClassifierDescriptor mo5460getDeclarationDescriptor = kotlinType.getConstructor().mo5460getDeclarationDescriptor();
        if (mo5460getDeclarationDescriptor != null) {
            Intrinsics.checkExpressionValueIsNotNull(mo5460getDeclarationDescriptor, "kotlinType.constructor.d…n expected: $kotlinType\")");
            if (!visitedClassifiers.add(mo5460getDeclarationDescriptor)) {
                return null;
            }
            if (mo5460getDeclarationDescriptor instanceof TypeParameterDescriptor) {
                computeExpandedTypeInner = computeExpandedTypeInner(TypeUtilsKt.getRepresentativeUpperBound((TypeParameterDescriptor) mo5460getDeclarationDescriptor), visitedClassifiers);
                if (computeExpandedTypeInner == null) {
                    return null;
                }
                if (!KotlinTypeKt.isNullable(computeExpandedTypeInner) && kotlinType.isMarkedNullable()) {
                    return TypeUtilsKt.makeNullable(computeExpandedTypeInner);
                }
            } else {
                if (!(mo5460getDeclarationDescriptor instanceof ClassDescriptor) || !((ClassDescriptor) mo5460getDeclarationDescriptor).isInline()) {
                    return kotlinType;
                }
                KotlinType substitutedUnderlyingType = InlineClassesUtilsKt.substitutedUnderlyingType(kotlinType);
                if (substitutedUnderlyingType == null || (computeExpandedTypeInner = computeExpandedTypeInner(substitutedUnderlyingType, visitedClassifiers)) == null) {
                    return null;
                }
                if (KotlinTypeKt.isNullable(kotlinType)) {
                    return (KotlinTypeKt.isNullable(computeExpandedTypeInner) || KotlinBuiltIns.isPrimitiveType(computeExpandedTypeInner)) ? kotlinType : TypeUtilsKt.makeNullable(computeExpandedTypeInner);
                }
            }
            return computeExpandedTypeInner;
        }
        throw new AssertionError("Type with a declaration expected: " + kotlinType);
    }

    public static /* synthetic */ String computeInternalName$default(ClassDescriptor classDescriptor, TypeMappingConfiguration typeMappingConfiguration, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            typeMappingConfiguration = TypeMappingConfigurationImpl.INSTANCE;
        }
        return computeInternalName(classDescriptor, typeMappingConfiguration, z);
    }

    public static final String computeInternalName(ClassDescriptor klass, TypeMappingConfiguration<?> typeMappingConfiguration, boolean z) {
        Intrinsics.checkParameterIsNotNull(klass, "klass");
        Intrinsics.checkParameterIsNotNull(typeMappingConfiguration, "typeMappingConfiguration");
        DeclarationDescriptor containingDeclaration = klass.getContainingDeclaration();
        if (z) {
            containingDeclaration = getContainer(containingDeclaration);
        }
        Name safeIdentifier = SpecialNames.safeIdentifier(klass.getName());
        Intrinsics.checkExpressionValueIsNotNull(safeIdentifier, "SpecialNames.safeIdentifier(klass.name)");
        String identifier = safeIdentifier.getIdentifier();
        Intrinsics.checkExpressionValueIsNotNull(identifier, "SpecialNames.safeIdentifier(klass.name).identifier");
        if (containingDeclaration instanceof PackageFragmentDescriptor) {
            FqName fqName = ((PackageFragmentDescriptor) containingDeclaration).getFqName();
            if (fqName.isRoot()) {
                return identifier;
            }
            StringBuilder sb = new StringBuilder();
            String asString = fqName.asString();
            Intrinsics.checkExpressionValueIsNotNull(asString, "fqName.asString()");
            sb.append(StringsKt.replace$default(asString, FilenameUtils.EXTENSION_SEPARATOR, '/', false, 4, (Object) null));
            sb.append('/');
            sb.append(identifier);
            return sb.toString();
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) (!(containingDeclaration instanceof ClassDescriptor) ? null : containingDeclaration);
        if (classDescriptor == null) {
            throw new IllegalArgumentException("Unexpected container: " + containingDeclaration + " for " + klass);
        }
        String predefinedInternalNameForClass = typeMappingConfiguration.getPredefinedInternalNameForClass(classDescriptor);
        if (predefinedInternalNameForClass == null) {
            predefinedInternalNameForClass = computeInternalName(classDescriptor, typeMappingConfiguration, z);
        }
        return predefinedInternalNameForClass + Typography.dollar + identifier;
    }

    private static final DeclarationDescriptor getContainer(DeclarationDescriptor declarationDescriptor) {
        DeclarationDescriptor declarationDescriptor2 = (ClassDescriptor) (!(declarationDescriptor instanceof ClassDescriptor) ? null : declarationDescriptor);
        if (declarationDescriptor2 == null) {
            declarationDescriptor2 = (PackageFragmentDescriptor) (!(declarationDescriptor instanceof PackageFragmentDescriptor) ? null : declarationDescriptor);
        }
        DeclarationDescriptor declarationDescriptor3 = declarationDescriptor2;
        if (declarationDescriptor3 != null) {
            return declarationDescriptor3;
        }
        if (declarationDescriptor != null) {
            return getContainer(declarationDescriptor.getContainingDeclaration());
        }
        return null;
    }
}
