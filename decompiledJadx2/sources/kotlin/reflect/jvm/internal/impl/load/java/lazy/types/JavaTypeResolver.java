package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.ArrayList;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.TypeParameterResolver;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifier;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPrimitiveType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.LazyWrappedType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: JavaTypeResolver.kt */
/* loaded from: classes2.dex */
public final class JavaTypeResolver {

    /* renamed from: c */
    private final LazyJavaResolverContext f8771c;
    private final TypeParameterResolver typeParameterResolver;

    public JavaTypeResolver(LazyJavaResolverContext c, TypeParameterResolver typeParameterResolver) {
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(typeParameterResolver, "typeParameterResolver");
        this.f8771c = c;
        this.typeParameterResolver = typeParameterResolver;
    }

    public final KotlinType transformJavaType(JavaType javaType, JavaTypeAttributes attr) {
        KotlinType transformJavaType;
        SimpleType unitType;
        Intrinsics.checkParameterIsNotNull(attr, "attr");
        if (javaType instanceof JavaPrimitiveType) {
            PrimitiveType type = ((JavaPrimitiveType) javaType).getType();
            if (type != null) {
                unitType = this.f8771c.getModule().getBuiltIns().getPrimitiveKotlinType(type);
            } else {
                unitType = this.f8771c.getModule().getBuiltIns().getUnitType();
            }
            Intrinsics.checkExpressionValueIsNotNull(unitType, "if (primitiveType != nul….module.builtIns.unitType");
            return unitType;
        }
        if (javaType instanceof JavaClassifierType) {
            return transformJavaClassifierType((JavaClassifierType) javaType, attr);
        }
        if (javaType instanceof JavaArrayType) {
            return transformArrayType$default(this, (JavaArrayType) javaType, attr, false, 4, null);
        }
        if (javaType instanceof JavaWildcardType) {
            JavaType bound = ((JavaWildcardType) javaType).getBound();
            if (bound != null && (transformJavaType = transformJavaType(bound, attr)) != null) {
                return transformJavaType;
            }
            SimpleType defaultBound = this.f8771c.getModule().getBuiltIns().getDefaultBound();
            Intrinsics.checkExpressionValueIsNotNull(defaultBound, "c.module.builtIns.defaultBound");
            return defaultBound;
        }
        if (javaType == null) {
            SimpleType defaultBound2 = this.f8771c.getModule().getBuiltIns().getDefaultBound();
            Intrinsics.checkExpressionValueIsNotNull(defaultBound2, "c.module.builtIns.defaultBound");
            return defaultBound2;
        }
        throw new UnsupportedOperationException("Unsupported type: " + javaType);
    }

    public static /* synthetic */ KotlinType transformArrayType$default(JavaTypeResolver javaTypeResolver, JavaArrayType javaArrayType, JavaTypeAttributes javaTypeAttributes, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return javaTypeResolver.transformArrayType(javaArrayType, javaTypeAttributes, z);
    }

    public final KotlinType transformArrayType(JavaArrayType arrayType, JavaTypeAttributes attr, boolean z) {
        Intrinsics.checkParameterIsNotNull(arrayType, "arrayType");
        Intrinsics.checkParameterIsNotNull(attr, "attr");
        JavaType componentType = arrayType.getComponentType();
        JavaPrimitiveType javaPrimitiveType = (JavaPrimitiveType) (!(componentType instanceof JavaPrimitiveType) ? null : componentType);
        PrimitiveType type = javaPrimitiveType != null ? javaPrimitiveType.getType() : null;
        if (type != null) {
            SimpleType primitiveArrayKotlinType = this.f8771c.getModule().getBuiltIns().getPrimitiveArrayKotlinType(type);
            Intrinsics.checkExpressionValueIsNotNull(primitiveArrayKotlinType, "c.module.builtIns.getPri…KotlinType(primitiveType)");
            if (attr.isForAnnotationParameter()) {
                return primitiveArrayKotlinType;
            }
            return KotlinTypeFactory.flexibleType(primitiveArrayKotlinType, primitiveArrayKotlinType.makeNullableAsSpecified(true));
        }
        KotlinType transformJavaType = transformJavaType(componentType, JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, attr.isForAnnotationParameter(), null, 2, null));
        if (attr.isForAnnotationParameter()) {
            SimpleType arrayType2 = this.f8771c.getModule().getBuiltIns().getArrayType(z ? Variance.OUT_VARIANCE : Variance.INVARIANT, transformJavaType);
            Intrinsics.checkExpressionValueIsNotNull(arrayType2, "c.module.builtIns.getArr…ctionKind, componentType)");
            return arrayType2;
        }
        SimpleType arrayType3 = this.f8771c.getModule().getBuiltIns().getArrayType(Variance.INVARIANT, transformJavaType);
        Intrinsics.checkExpressionValueIsNotNull(arrayType3, "c.module.builtIns.getArr…INVARIANT, componentType)");
        return KotlinTypeFactory.flexibleType(arrayType3, this.f8771c.getModule().getBuiltIns().getArrayType(Variance.OUT_VARIANCE, transformJavaType).makeNullableAsSpecified(true));
    }

    private final KotlinType transformJavaClassifierType(final JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes) {
        Function0<SimpleType> function0 = new Function0<SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver$transformJavaClassifierType$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final SimpleType invoke() {
                SimpleType createErrorType = ErrorUtils.createErrorType("Unresolved java class " + JavaClassifierType.this.getPresentableText());
                Intrinsics.checkExpressionValueIsNotNull(createErrorType, "ErrorUtils.createErrorTy…vaType.presentableText}\")");
                return createErrorType;
            }
        };
        boolean z = (javaTypeAttributes.isForAnnotationParameter() || javaTypeAttributes.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE) ? false : true;
        boolean isRaw = javaClassifierType.isRaw();
        if (!isRaw && !z) {
            SimpleType computeSimpleJavaClassifierType = computeSimpleJavaClassifierType(javaClassifierType, javaTypeAttributes, null);
            if (computeSimpleJavaClassifierType == null) {
                computeSimpleJavaClassifierType = function0.invoke();
            }
            return computeSimpleJavaClassifierType;
        }
        SimpleType computeSimpleJavaClassifierType2 = computeSimpleJavaClassifierType(javaClassifierType, javaTypeAttributes.withFlexibility(JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND), null);
        if (computeSimpleJavaClassifierType2 == null) {
            return function0.invoke();
        }
        SimpleType computeSimpleJavaClassifierType3 = computeSimpleJavaClassifierType(javaClassifierType, javaTypeAttributes.withFlexibility(JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND), computeSimpleJavaClassifierType2);
        if (computeSimpleJavaClassifierType3 == null) {
            return function0.invoke();
        }
        if (isRaw) {
            return new RawTypeImpl(computeSimpleJavaClassifierType2, computeSimpleJavaClassifierType3);
        }
        return KotlinTypeFactory.flexibleType(computeSimpleJavaClassifierType2, computeSimpleJavaClassifierType3);
    }

    private final SimpleType computeSimpleJavaClassifierType(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes, SimpleType simpleType) {
        LazyJavaAnnotations lazyJavaAnnotations;
        if (simpleType == null || (lazyJavaAnnotations = simpleType.getAnnotations()) == null) {
            lazyJavaAnnotations = new LazyJavaAnnotations(this.f8771c, javaClassifierType);
        }
        TypeConstructor computeTypeConstructor = computeTypeConstructor(javaClassifierType, javaTypeAttributes);
        if (computeTypeConstructor == null) {
            return null;
        }
        boolean isNullable = isNullable(javaTypeAttributes);
        if (Intrinsics.areEqual(simpleType != null ? simpleType.getConstructor() : null, computeTypeConstructor) && !javaClassifierType.isRaw() && isNullable) {
            return simpleType.makeNullableAsSpecified(true);
        }
        return KotlinTypeFactory.simpleType(lazyJavaAnnotations, computeTypeConstructor, computeArguments(javaClassifierType, javaTypeAttributes, computeTypeConstructor), isNullable);
    }

    private final TypeConstructor computeTypeConstructor(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes) {
        TypeConstructor typeConstructor;
        JavaClassifier classifier = javaClassifierType.getClassifier();
        if (classifier == null) {
            return createNotFoundClass(javaClassifierType);
        }
        if (classifier instanceof JavaClass) {
            JavaClass javaClass = (JavaClass) classifier;
            FqName fqName = javaClass.getFqName();
            if (fqName == null) {
                throw new AssertionError("Class type should have a FQ name: " + classifier);
            }
            ClassDescriptor mapKotlinClass = mapKotlinClass(javaClassifierType, javaTypeAttributes, fqName);
            if (mapKotlinClass == null) {
                mapKotlinClass = this.f8771c.getComponents().getModuleClassResolver().resolveClass(javaClass);
            }
            return (mapKotlinClass == null || (typeConstructor = mapKotlinClass.getTypeConstructor()) == null) ? createNotFoundClass(javaClassifierType) : typeConstructor;
        }
        if (classifier instanceof JavaTypeParameter) {
            TypeParameterDescriptor resolveTypeParameter = this.typeParameterResolver.resolveTypeParameter((JavaTypeParameter) classifier);
            if (resolveTypeParameter != null) {
                return resolveTypeParameter.getTypeConstructor();
            }
            return null;
        }
        throw new IllegalStateException("Unknown classifier kind: " + classifier);
    }

    private final TypeConstructor createNotFoundClass(JavaClassifierType javaClassifierType) {
        ClassId classId = ClassId.topLevel(new FqName(javaClassifierType.getClassifierQualifiedName()));
        Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(FqName(…classifierQualifiedName))");
        TypeConstructor typeConstructor = this.f8771c.getComponents().getDeserializedDescriptorResolver().getComponents().getNotFoundClasses().getClass(classId, CollectionsKt.listOf(0)).getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "c.components.deserialize…istOf(0)).typeConstructor");
        return typeConstructor;
    }

    private final ClassDescriptor mapKotlinClass(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes, FqName fqName) {
        if (javaTypeAttributes.isForAnnotationParameter() && Intrinsics.areEqual(fqName, JavaTypeResolverKt.access$getJAVA_LANG_CLASS_FQ_NAME$p())) {
            return this.f8771c.getComponents().getReflectionTypes().getKClass();
        }
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        ClassDescriptor mapJavaToKotlin$default = JavaToKotlinClassMap.mapJavaToKotlin$default(javaToKotlinClassMap, fqName, this.f8771c.getModule().getBuiltIns(), null, 4, null);
        if (mapJavaToKotlin$default != null) {
            return (javaToKotlinClassMap.isReadOnly(mapJavaToKotlin$default) && (javaTypeAttributes.getFlexibility() == JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND || javaTypeAttributes.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE || argumentsMakeSenseOnlyForMutableContainer(javaClassifierType, mapJavaToKotlin$default))) ? javaToKotlinClassMap.convertReadOnlyToMutable(mapJavaToKotlin$default) : mapJavaToKotlin$default;
        }
        return null;
    }

    private final boolean argumentsMakeSenseOnlyForMutableContainer(JavaClassifierType javaClassifierType, ClassDescriptor classDescriptor) {
        Variance variance;
        if (!JavaTypeResolver$argumentsMakeSenseOnlyForMutableContainer$1.INSTANCE.invoke2((JavaType) CollectionsKt.lastOrNull((List) javaClassifierType.getTypeArguments()))) {
            return false;
        }
        TypeConstructor typeConstructor = JavaToKotlinClassMap.INSTANCE.convertReadOnlyToMutable(classDescriptor).getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "JavaToKotlinClassMap.con…         .typeConstructor");
        List<TypeParameterDescriptor> parameters = typeConstructor.getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "JavaToKotlinClassMap.con…ypeConstructor.parameters");
        TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) CollectionsKt.lastOrNull((List) parameters);
        if (typeParameterDescriptor == null || (variance = typeParameterDescriptor.getVariance()) == null) {
            return false;
        }
        Intrinsics.checkExpressionValueIsNotNull(variance, "JavaToKotlinClassMap.con….variance ?: return false");
        return variance != Variance.OUT_VARIANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0023, code lost:
    
        if ((!r3.isEmpty()) != false) goto L10;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0095  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final List<TypeProjection> computeArguments(JavaClassifierType javaClassifierType, final JavaTypeAttributes javaTypeAttributes, final TypeConstructor typeConstructor) {
        boolean z;
        final boolean isRaw = javaClassifierType.isRaw();
        if (!isRaw) {
            if (javaClassifierType.getTypeArguments().isEmpty()) {
                Intrinsics.checkExpressionValueIsNotNull(typeConstructor.getParameters(), "constructor.parameters");
            }
            z = false;
            List<TypeParameterDescriptor> parameters = typeConstructor.getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "constructor.parameters");
            if (!z) {
                List<TypeParameterDescriptor> list = parameters;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                for (final TypeParameterDescriptor parameter : list) {
                    LazyWrappedType lazyWrappedType = new LazyWrappedType(this.f8771c.getStorageManager(), new Function0<KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver$computeArguments$$inlined$map$lambda$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final KotlinType invoke() {
                            TypeParameterDescriptor parameter2 = TypeParameterDescriptor.this;
                            Intrinsics.checkExpressionValueIsNotNull(parameter2, "parameter");
                            return JavaTypeResolverKt.getErasedUpperBound(parameter2, javaTypeAttributes.getUpperBoundOfTypeParameter(), new Function0<KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver$computeArguments$$inlined$map$lambda$1.1
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final KotlinType invoke() {
                                    ClassifierDescriptor mo5460getDeclarationDescriptor = typeConstructor.mo5460getDeclarationDescriptor();
                                    if (mo5460getDeclarationDescriptor == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    Intrinsics.checkExpressionValueIsNotNull(mo5460getDeclarationDescriptor, "constructor.declarationDescriptor!!");
                                    SimpleType defaultType = mo5460getDeclarationDescriptor.getDefaultType();
                                    Intrinsics.checkExpressionValueIsNotNull(defaultType, "constructor.declarationDescriptor!!.defaultType");
                                    return TypeUtilsKt.replaceArgumentsWithStarProjections(defaultType);
                                }
                            });
                        }
                    });
                    RawSubstitution rawSubstitution = RawSubstitution.INSTANCE;
                    Intrinsics.checkExpressionValueIsNotNull(parameter, "parameter");
                    arrayList.add(rawSubstitution.computeProjection(parameter, isRaw ? javaTypeAttributes : javaTypeAttributes.withFlexibility(JavaTypeFlexibility.INFLEXIBLE), lazyWrappedType));
                }
                return CollectionsKt.toList(arrayList);
            }
            if (parameters.size() != javaClassifierType.getTypeArguments().size()) {
                List<TypeParameterDescriptor> list2 = parameters;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                for (TypeParameterDescriptor p : list2) {
                    Intrinsics.checkExpressionValueIsNotNull(p, "p");
                    arrayList2.add(new TypeProjectionImpl(ErrorUtils.createErrorType(p.getName().asString())));
                }
                return CollectionsKt.toList(arrayList2);
            }
            Iterable<IndexedValue> withIndex = CollectionsKt.withIndex(javaClassifierType.getTypeArguments());
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(withIndex, 10));
            for (IndexedValue indexedValue : withIndex) {
                int index = indexedValue.getIndex();
                JavaType javaType = (JavaType) indexedValue.component2();
                boolean z2 = index < parameters.size();
                if (_Assertions.ENABLED && !z2) {
                    throw new AssertionError("Argument index should be less then type parameters count, but " + index + " > " + parameters.size());
                }
                TypeParameterDescriptor parameter2 = parameters.get(index);
                JavaTypeAttributes attributes$default = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null);
                Intrinsics.checkExpressionValueIsNotNull(parameter2, "parameter");
                arrayList3.add(transformToTypeProjection(javaType, attributes$default, parameter2));
            }
            return CollectionsKt.toList(arrayList3);
        }
        z = true;
        List<TypeParameterDescriptor> parameters2 = typeConstructor.getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters2, "constructor.parameters");
        if (!z) {
        }
    }

    private final TypeProjection transformToTypeProjection(JavaType javaType, JavaTypeAttributes javaTypeAttributes, TypeParameterDescriptor typeParameterDescriptor) {
        if (javaType instanceof JavaWildcardType) {
            JavaWildcardType javaWildcardType = (JavaWildcardType) javaType;
            JavaType bound = javaWildcardType.getBound();
            Variance variance = javaWildcardType.isExtends() ? Variance.OUT_VARIANCE : Variance.IN_VARIANCE;
            if (bound == null || isConflictingArgumentFor(variance, typeParameterDescriptor)) {
                return JavaTypeResolverKt.makeStarProjection(typeParameterDescriptor, javaTypeAttributes);
            }
            return TypeUtilsKt.createProjection(transformJavaType(bound, JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null)), variance, typeParameterDescriptor);
        }
        return new TypeProjectionImpl(Variance.INVARIANT, transformJavaType(javaType, javaTypeAttributes));
    }

    private final boolean isConflictingArgumentFor(Variance variance, TypeParameterDescriptor typeParameterDescriptor) {
        return (typeParameterDescriptor.getVariance() == Variance.INVARIANT || variance == typeParameterDescriptor.getVariance()) ? false : true;
    }

    private final boolean isNullable(JavaTypeAttributes javaTypeAttributes) {
        return (javaTypeAttributes.getFlexibility() == JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND || javaTypeAttributes.isForAnnotationParameter() || javaTypeAttributes.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE) ? false : true;
    }
}
