package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.DeprecationCausedByFunctionN;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNamesKt;
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.AnnotationDefaultValue;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.NullDefaultValue;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.PossiblyExternalAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.StringDefaultValue;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.UtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.ValueParameterData;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.JavaDescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.deprecation.DeprecationKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: signatureEnhancement.kt */
/* loaded from: classes2.dex */
public final class SignatureEnhancement {
    private final AnnotationTypeQualifierResolver annotationTypeQualifierResolver;
    private final Jsr305State jsr305State;

    public SignatureEnhancement(AnnotationTypeQualifierResolver annotationTypeQualifierResolver, Jsr305State jsr305State) {
        Intrinsics.checkParameterIsNotNull(annotationTypeQualifierResolver, "annotationTypeQualifierResolver");
        Intrinsics.checkParameterIsNotNull(jsr305State, "jsr305State");
        this.annotationTypeQualifierResolver = annotationTypeQualifierResolver;
        this.jsr305State = jsr305State;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x001c. Please report as an issue. */
    private final NullabilityQualifierWithMigrationStatus extractNullabilityTypeFromArgument(AnnotationDescriptor annotationDescriptor) {
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus;
        ConstantValue<?> firstArgument = DescriptorUtilsKt.firstArgument(annotationDescriptor);
        if (!(firstArgument instanceof EnumValue)) {
            firstArgument = null;
        }
        EnumValue enumValue = (EnumValue) firstArgument;
        if (enumValue == null) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null);
        }
        String asString = enumValue.getEnumEntryName().asString();
        switch (asString.hashCode()) {
            case 73135176:
                if (!asString.equals("MAYBE")) {
                    return null;
                }
                nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null);
                return nullabilityQualifierWithMigrationStatus;
            case 74175084:
                if (!asString.equals("NEVER")) {
                    return null;
                }
                nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null);
                return nullabilityQualifierWithMigrationStatus;
            case 433141802:
                if (!asString.equals("UNKNOWN")) {
                    return null;
                }
                nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.FORCE_FLEXIBILITY, false, 2, null);
                return nullabilityQualifierWithMigrationStatus;
            case 1933739535:
                if (!asString.equals("ALWAYS")) {
                    return null;
                }
                nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null);
                return nullabilityQualifierWithMigrationStatus;
            default:
                return null;
        }
    }

    public final NullabilityQualifierWithMigrationStatus extractNullability(AnnotationDescriptor annotationDescriptor) {
        NullabilityQualifierWithMigrationStatus extractNullabilityFromKnownAnnotations;
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        NullabilityQualifierWithMigrationStatus extractNullabilityFromKnownAnnotations2 = extractNullabilityFromKnownAnnotations(annotationDescriptor);
        if (extractNullabilityFromKnownAnnotations2 != null) {
            return extractNullabilityFromKnownAnnotations2;
        }
        AnnotationDescriptor resolveTypeQualifierAnnotation = this.annotationTypeQualifierResolver.resolveTypeQualifierAnnotation(annotationDescriptor);
        if (resolveTypeQualifierAnnotation == null) {
            return null;
        }
        ReportLevel resolveJsr305AnnotationState = this.annotationTypeQualifierResolver.resolveJsr305AnnotationState(annotationDescriptor);
        if (resolveJsr305AnnotationState.isIgnore() || (extractNullabilityFromKnownAnnotations = extractNullabilityFromKnownAnnotations(resolveTypeQualifierAnnotation)) == null) {
            return null;
        }
        return NullabilityQualifierWithMigrationStatus.copy$default(extractNullabilityFromKnownAnnotations, null, resolveJsr305AnnotationState.isWarning(), 1, null);
    }

    private final NullabilityQualifierWithMigrationStatus extractNullabilityFromKnownAnnotations(AnnotationDescriptor annotationDescriptor) {
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus;
        FqName fqName = annotationDescriptor.getFqName();
        if (fqName == null) {
            return null;
        }
        if (JvmAnnotationNamesKt.getNULLABLE_ANNOTATIONS().contains(fqName)) {
            nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null);
        } else if (JvmAnnotationNamesKt.getNOT_NULL_ANNOTATIONS().contains(fqName)) {
            nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null);
        } else if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getJAVAX_NONNULL_ANNOTATION())) {
            nullabilityQualifierWithMigrationStatus = extractNullabilityTypeFromArgument(annotationDescriptor);
        } else if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getCOMPATQUAL_NULLABLE_ANNOTATION()) && this.jsr305State.getEnableCompatqualCheckerFrameworkAnnotations()) {
            nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null);
        } else if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getCOMPATQUAL_NONNULL_ANNOTATION()) && this.jsr305State.getEnableCompatqualCheckerFrameworkAnnotations()) {
            nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null);
        } else if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getANDROIDX_RECENTLY_NON_NULL_ANNOTATION())) {
            nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, true);
        } else {
            nullabilityQualifierWithMigrationStatus = Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getANDROIDX_RECENTLY_NULLABLE_ANNOTATION()) ? new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, true) : null;
        }
        if (nullabilityQualifierWithMigrationStatus != null) {
            return (!nullabilityQualifierWithMigrationStatus.isForWarningOnly() && (annotationDescriptor instanceof PossiblyExternalAnnotationDescriptor) && ((PossiblyExternalAnnotationDescriptor) annotationDescriptor).isIdeExternalAnnotation()) ? NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatus, null, true, 1, null) : nullabilityQualifierWithMigrationStatus;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <D extends CallableMemberDescriptor> Collection<D> enhanceSignatures(LazyJavaResolverContext c, Collection<? extends D> platformSignatures) {
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(platformSignatures, "platformSignatures");
        Collection<? extends D> collection = platformSignatures;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(enhanceSignature((CallableMemberDescriptor) it.next(), c));
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0251 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0288 A[LOOP:1: B:96:0x0282->B:98:0x0288, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final <D extends CallableMemberDescriptor> D enhanceSignature(D d, LazyJavaResolverContext lazyJavaResolverContext) {
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl;
        PartEnhancementResult partEnhancementResult;
        PredefinedFunctionEnhancementInfo predefinedFunctionEnhancementInfo;
        PropertyDescriptor propertyDescriptor;
        AnnotationTypeQualifierResolver.QualifierApplicabilityType qualifierApplicabilityType;
        ArrayList arrayList;
        Iterator it;
        boolean z;
        boolean z2;
        ArrayList arrayList2;
        Iterator it2;
        JavaCallableMemberDescriptor enhance;
        KotlinType type;
        List<TypeEnhancementInfo> parametersInfo;
        JavaPropertyDescriptor javaPropertyDescriptor;
        PropertyGetterDescriptorImpl getter;
        if (!(d instanceof JavaCallableMemberDescriptor)) {
            return d;
        }
        JavaCallableMemberDescriptor javaCallableMemberDescriptor = (JavaCallableMemberDescriptor) d;
        boolean z3 = true;
        if (javaCallableMemberDescriptor.getKind() == CallableMemberDescriptor.Kind.FAKE_OVERRIDE) {
            CallableMemberDescriptor original = javaCallableMemberDescriptor.getOriginal();
            Intrinsics.checkExpressionValueIsNotNull(original, "original");
            if (original.getOverriddenDescriptors().size() == 1) {
                return d;
            }
        }
        LazyJavaResolverContext copyWithNewDefaultTypeQualifiers = ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, d.getAnnotations());
        if (!(d instanceof JavaPropertyDescriptor) || (getter = (javaPropertyDescriptor = (JavaPropertyDescriptor) d).getGetter()) == null || getter.isDefault()) {
            propertyGetterDescriptorImpl = d;
        } else {
            PropertyGetterDescriptorImpl getter2 = javaPropertyDescriptor.getGetter();
            if (getter2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(getter2, "getter!!");
            propertyGetterDescriptorImpl = getter2;
        }
        if (javaCallableMemberDescriptor.getExtensionReceiverParameter() != null) {
            FunctionDescriptor functionDescriptor = (FunctionDescriptor) (!(propertyGetterDescriptorImpl instanceof FunctionDescriptor) ? null : propertyGetterDescriptorImpl);
            partEnhancementResult = SignatureParts.enhance$default(partsForValueParameter(d, functionDescriptor != null ? (ValueParameterDescriptor) functionDescriptor.getUserData(JavaMethodDescriptor.ORIGINAL_VALUE_PARAMETER_FOR_EXTENSION_RECEIVER) : null, copyWithNewDefaultTypeQualifiers, new Function1<CallableMemberDescriptor, KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$enhanceSignature$receiverTypeEnhancement$1
                @Override // kotlin.jvm.functions.Function1
                public final KotlinType invoke(CallableMemberDescriptor it3) {
                    Intrinsics.checkParameterIsNotNull(it3, "it");
                    ReceiverParameterDescriptor extensionReceiverParameter = it3.getExtensionReceiverParameter();
                    if (extensionReceiverParameter == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(extensionReceiverParameter, "it.extensionReceiverParameter!!");
                    KotlinType type2 = extensionReceiverParameter.getType();
                    Intrinsics.checkExpressionValueIsNotNull(type2, "it.extensionReceiverParameter!!.type");
                    return type2;
                }
            }), null, 1, null);
        } else {
            partEnhancementResult = null;
        }
        JavaMethodDescriptor javaMethodDescriptor = (JavaMethodDescriptor) (!(d instanceof JavaMethodDescriptor) ? null : d);
        if (javaMethodDescriptor != null) {
            SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
            DeclarationDescriptor containingDeclaration = javaMethodDescriptor.getContainingDeclaration();
            if (containingDeclaration == null) {
                throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            }
            String signature = signatureBuildingComponents.signature((ClassDescriptor) containingDeclaration, MethodSignatureMappingKt.computeJvmDescriptor$default(javaMethodDescriptor, false, false, 3, null));
            if (signature != null) {
                predefinedFunctionEnhancementInfo = PredefinedEnhancementInfoKt.getPREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE().get(signature);
                if (predefinedFunctionEnhancementInfo != null) {
                    boolean z4 = predefinedFunctionEnhancementInfo.getParametersInfo().size() == javaCallableMemberDescriptor.getValueParameters().size();
                    if (_Assertions.ENABLED && !z4) {
                        throw new AssertionError("Predefined enhancement info for " + d + " has " + predefinedFunctionEnhancementInfo.getParametersInfo().size() + ", but " + javaCallableMemberDescriptor.getValueParameters().size() + " expected");
                    }
                }
                List<ValueParameterDescriptor> valueParameters = propertyGetterDescriptorImpl.getValueParameters();
                Intrinsics.checkExpressionValueIsNotNull(valueParameters, "annotationOwnerForMember.valueParameters");
                List<ValueParameterDescriptor> list = valueParameters;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                for (final ValueParameterDescriptor p : list) {
                    PartEnhancementResult enhance2 = partsForValueParameter(d, p, copyWithNewDefaultTypeQualifiers, new Function1<CallableMemberDescriptor, KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$enhanceSignature$valueParameterEnhancements$1$enhancementResult$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final KotlinType invoke(CallableMemberDescriptor it3) {
                            Intrinsics.checkParameterIsNotNull(it3, "it");
                            ValueParameterDescriptor valueParameterDescriptor = it3.getValueParameters().get(ValueParameterDescriptor.this.getIndex());
                            Intrinsics.checkExpressionValueIsNotNull(valueParameterDescriptor, "it.valueParameters[p.index]");
                            KotlinType type2 = valueParameterDescriptor.getType();
                            Intrinsics.checkExpressionValueIsNotNull(type2, "it.valueParameters[p.index].type");
                            return type2;
                        }
                    }).enhance((predefinedFunctionEnhancementInfo == null || (parametersInfo = predefinedFunctionEnhancementInfo.getParametersInfo()) == null) ? null : (TypeEnhancementInfo) CollectionsKt.getOrNull(parametersInfo, p.getIndex()));
                    if (enhance2.getWereChanges()) {
                        type = enhance2.getType();
                    } else {
                        Intrinsics.checkExpressionValueIsNotNull(p, "p");
                        type = p.getType();
                        Intrinsics.checkExpressionValueIsNotNull(type, "p.type");
                    }
                    Intrinsics.checkExpressionValueIsNotNull(p, "p");
                    boolean hasDefaultValueInAnnotation = hasDefaultValueInAnnotation(p, type);
                    arrayList3.add(new ValueParameterEnhancementResult(enhance2.getType(), hasDefaultValueInAnnotation, enhance2.getWereChanges() || hasDefaultValueInAnnotation != p.declaresDefaultValue(), enhance2.getContainsFunctionN()));
                }
                ArrayList arrayList4 = arrayList3;
                CallableMemberDescriptor callableMemberDescriptor = propertyGetterDescriptorImpl;
                propertyDescriptor = (PropertyDescriptor) (d instanceof PropertyDescriptor ? null : d);
                if (propertyDescriptor == null && JavaDescriptorUtilKt.isJavaField(propertyDescriptor)) {
                    qualifierApplicabilityType = AnnotationTypeQualifierResolver.QualifierApplicabilityType.FIELD;
                } else {
                    qualifierApplicabilityType = AnnotationTypeQualifierResolver.QualifierApplicabilityType.METHOD_RETURN_TYPE;
                }
                PartEnhancementResult enhance3 = parts(d, callableMemberDescriptor, true, copyWithNewDefaultTypeQualifiers, qualifierApplicabilityType, new Function1<CallableMemberDescriptor, KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$enhanceSignature$returnTypeEnhancement$1
                    @Override // kotlin.jvm.functions.Function1
                    public final KotlinType invoke(CallableMemberDescriptor it3) {
                        Intrinsics.checkParameterIsNotNull(it3, "it");
                        KotlinType returnType = it3.getReturnType();
                        if (returnType == null) {
                            Intrinsics.throwNpe();
                        }
                        return returnType;
                    }
                }).enhance(predefinedFunctionEnhancementInfo == null ? predefinedFunctionEnhancementInfo.getReturnTypeInfo() : null);
                if ((partEnhancementResult != null || !partEnhancementResult.getContainsFunctionN()) && !enhance3.getContainsFunctionN()) {
                    arrayList = arrayList4;
                    if ((arrayList instanceof Collection) || !arrayList.isEmpty()) {
                        it = arrayList.iterator();
                        while (it.hasNext()) {
                            if (((ValueParameterEnhancementResult) it.next()).getContainsFunctionN()) {
                                z = true;
                                break;
                            }
                        }
                    }
                    z = false;
                    if (!z) {
                        z2 = false;
                        if ((partEnhancementResult != null || !partEnhancementResult.getWereChanges()) && !enhance3.getWereChanges()) {
                            arrayList2 = arrayList4;
                            if ((arrayList2 instanceof Collection) || !arrayList2.isEmpty()) {
                                it2 = arrayList2.iterator();
                                while (it2.hasNext()) {
                                    if (((ValueParameterEnhancementResult) it2.next()).getWereChanges()) {
                                        break;
                                    }
                                }
                            }
                            z3 = false;
                            if (!z3 && !z2) {
                                return d;
                            }
                        }
                        Pair<CallableDescriptor.UserDataKey<?>, ?> m3968to = z2 ? TuplesKt.m3968to(DeprecationKt.getDEPRECATED_FUNCTION_KEY(), new DeprecationCausedByFunctionN(d)) : null;
                        KotlinType type2 = partEnhancementResult != null ? partEnhancementResult.getType() : null;
                        ArrayList<ValueParameterEnhancementResult> arrayList5 = arrayList4;
                        ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList5, 10));
                        for (ValueParameterEnhancementResult valueParameterEnhancementResult : arrayList5) {
                            arrayList6.add(new ValueParameterData(valueParameterEnhancementResult.getType(), valueParameterEnhancementResult.getHasDefaultValue()));
                        }
                        enhance = javaCallableMemberDescriptor.enhance(type2, arrayList6, enhance3.getType(), m3968to);
                        if (enhance != null) {
                            return enhance;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type D");
                    }
                }
                z2 = true;
                if (partEnhancementResult != null) {
                }
                arrayList2 = arrayList4;
                if (arrayList2 instanceof Collection) {
                }
                it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                }
                z3 = false;
                if (!z3) {
                    return d;
                }
                if (z2) {
                }
                if (partEnhancementResult != null) {
                }
                ArrayList<ValueParameterEnhancementResult> arrayList52 = arrayList4;
                ArrayList arrayList62 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList52, 10));
                while (r4.hasNext()) {
                }
                enhance = javaCallableMemberDescriptor.enhance(type2, arrayList62, enhance3.getType(), m3968to);
                if (enhance != null) {
                }
            }
        }
        predefinedFunctionEnhancementInfo = null;
        if (predefinedFunctionEnhancementInfo != null) {
        }
        List<ValueParameterDescriptor> valueParameters2 = propertyGetterDescriptorImpl.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters2, "annotationOwnerForMember.valueParameters");
        List<ValueParameterDescriptor> list2 = valueParameters2;
        ArrayList arrayList32 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        while (r1.hasNext()) {
        }
        ArrayList arrayList42 = arrayList32;
        CallableMemberDescriptor callableMemberDescriptor2 = propertyGetterDescriptorImpl;
        propertyDescriptor = (PropertyDescriptor) (d instanceof PropertyDescriptor ? null : d);
        if (propertyDescriptor == null) {
        }
        qualifierApplicabilityType = AnnotationTypeQualifierResolver.QualifierApplicabilityType.METHOD_RETURN_TYPE;
        PartEnhancementResult enhance32 = parts(d, callableMemberDescriptor2, true, copyWithNewDefaultTypeQualifiers, qualifierApplicabilityType, new Function1<CallableMemberDescriptor, KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$enhanceSignature$returnTypeEnhancement$1
            @Override // kotlin.jvm.functions.Function1
            public final KotlinType invoke(CallableMemberDescriptor it3) {
                Intrinsics.checkParameterIsNotNull(it3, "it");
                KotlinType returnType = it3.getReturnType();
                if (returnType == null) {
                    Intrinsics.throwNpe();
                }
                return returnType;
            }
        }).enhance(predefinedFunctionEnhancementInfo == null ? predefinedFunctionEnhancementInfo.getReturnTypeInfo() : null);
        if (partEnhancementResult != null) {
        }
        arrayList = arrayList42;
        if (arrayList instanceof Collection) {
        }
        it = arrayList.iterator();
        while (it.hasNext()) {
        }
        z = false;
        if (!z) {
        }
        z2 = true;
        if (partEnhancementResult != null) {
        }
        arrayList2 = arrayList42;
        if (arrayList2 instanceof Collection) {
        }
        it2 = arrayList2.iterator();
        while (it2.hasNext()) {
        }
        z3 = false;
        if (!z3) {
        }
        if (z2) {
        }
        if (partEnhancementResult != null) {
        }
        ArrayList<ValueParameterEnhancementResult> arrayList522 = arrayList42;
        ArrayList arrayList622 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList522, 10));
        while (r4.hasNext()) {
        }
        enhance = javaCallableMemberDescriptor.enhance(type2, arrayList622, enhance32.getType(), m3968to);
        if (enhance != null) {
        }
    }

    private final boolean hasDefaultValueInAnnotation(ValueParameterDescriptor valueParameterDescriptor, KotlinType kotlinType) {
        boolean declaresDefaultValue;
        AnnotationDefaultValue defaultValueFromAnnotation = UtilKt.getDefaultValueFromAnnotation(valueParameterDescriptor);
        if (defaultValueFromAnnotation instanceof StringDefaultValue) {
            declaresDefaultValue = UtilsKt.lexicalCastFrom(kotlinType, ((StringDefaultValue) defaultValueFromAnnotation).getValue()) != null;
        } else if (Intrinsics.areEqual(defaultValueFromAnnotation, NullDefaultValue.INSTANCE)) {
            declaresDefaultValue = TypeUtils.acceptsNullable(kotlinType);
        } else if (defaultValueFromAnnotation == null) {
            declaresDefaultValue = valueParameterDescriptor.declaresDefaultValue();
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return declaresDefaultValue && valueParameterDescriptor.getOverriddenDescriptors().isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: signatureEnhancement.kt */
    /* loaded from: classes2.dex */
    public final class SignatureParts {
        private final AnnotationTypeQualifierResolver.QualifierApplicabilityType containerApplicabilityType;
        private final LazyJavaResolverContext containerContext;
        private final Collection<KotlinType> fromOverridden;
        private final KotlinType fromOverride;
        private final boolean isCovariant;
        final /* synthetic */ SignatureEnhancement this$0;
        private final Annotated typeContainer;

        /* JADX WARN: Multi-variable type inference failed */
        public SignatureParts(SignatureEnhancement signatureEnhancement, Annotated annotated, KotlinType fromOverride, Collection<? extends KotlinType> fromOverridden, boolean z, LazyJavaResolverContext containerContext, AnnotationTypeQualifierResolver.QualifierApplicabilityType containerApplicabilityType) {
            Intrinsics.checkParameterIsNotNull(fromOverride, "fromOverride");
            Intrinsics.checkParameterIsNotNull(fromOverridden, "fromOverridden");
            Intrinsics.checkParameterIsNotNull(containerContext, "containerContext");
            Intrinsics.checkParameterIsNotNull(containerApplicabilityType, "containerApplicabilityType");
            this.this$0 = signatureEnhancement;
            this.typeContainer = annotated;
            this.fromOverride = fromOverride;
            this.fromOverridden = fromOverridden;
            this.isCovariant = z;
            this.containerContext = containerContext;
            this.containerApplicabilityType = containerApplicabilityType;
        }

        private final boolean isForVarargParameter() {
            Annotated annotated = this.typeContainer;
            if (!(annotated instanceof ValueParameterDescriptor)) {
                annotated = null;
            }
            ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) annotated;
            return (valueParameterDescriptor != null ? valueParameterDescriptor.getVarargElementType() : null) != null;
        }

        public static /* synthetic */ PartEnhancementResult enhance$default(SignatureParts signatureParts, TypeEnhancementInfo typeEnhancementInfo, int i, Object obj) {
            if ((i & 1) != 0) {
                typeEnhancementInfo = (TypeEnhancementInfo) null;
            }
            return signatureParts.enhance(typeEnhancementInfo);
        }

        public final PartEnhancementResult enhance(final TypeEnhancementInfo typeEnhancementInfo) {
            final Function1<Integer, JavaTypeQualifiers> computeIndexedQualifiersForOverride = computeIndexedQualifiersForOverride();
            Function1<Integer, JavaTypeQualifiers> function1 = typeEnhancementInfo != null ? new Function1<Integer, JavaTypeQualifiers>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ JavaTypeQualifiers invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final JavaTypeQualifiers invoke(int i) {
                    JavaTypeQualifiers javaTypeQualifiers = TypeEnhancementInfo.this.getMap().get(Integer.valueOf(i));
                    return javaTypeQualifiers != null ? javaTypeQualifiers : (JavaTypeQualifiers) computeIndexedQualifiersForOverride.invoke(Integer.valueOf(i));
                }
            } : null;
            boolean contains = TypeUtils.contains(this.fromOverride, new Function1<UnwrappedType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$enhance$containsFunctionN$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(UnwrappedType unwrappedType) {
                    return Boolean.valueOf(invoke2(unwrappedType));
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(UnwrappedType unwrappedType) {
                    ClassifierDescriptor mo5460getDeclarationDescriptor = unwrappedType.getConstructor().mo5460getDeclarationDescriptor();
                    if (mo5460getDeclarationDescriptor == null) {
                        return false;
                    }
                    Intrinsics.checkExpressionValueIsNotNull(mo5460getDeclarationDescriptor, "it.constructor.declarati… ?: return@contains false");
                    return Intrinsics.areEqual(mo5460getDeclarationDescriptor.getName(), JavaToKotlinClassMap.INSTANCE.getFUNCTION_N_FQ_NAME().shortName()) && Intrinsics.areEqual(DescriptorUtilsKt.fqNameOrNull(mo5460getDeclarationDescriptor), JavaToKotlinClassMap.INSTANCE.getFUNCTION_N_FQ_NAME());
                }
            });
            KotlinType kotlinType = this.fromOverride;
            if (function1 != null) {
                computeIndexedQualifiersForOverride = function1;
            }
            KotlinType enhance = TypeEnhancementKt.enhance(kotlinType, computeIndexedQualifiersForOverride);
            if (enhance != null) {
                return new PartEnhancementResult(enhance, true, contains);
            }
            return new PartEnhancementResult(this.fromOverride, false, contains);
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0048  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x004b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final JavaTypeQualifiers extractQualifiers(KotlinType kotlinType) {
            Pair pair;
            NullabilityQualifier nullabilityQualifier;
            NullabilityQualifier nullabilityQualifier2;
            MutabilityQualifier mutabilityQualifier;
            if (FlexibleTypesKt.isFlexible(kotlinType)) {
                FlexibleType asFlexibleType = FlexibleTypesKt.asFlexibleType(kotlinType);
                pair = new Pair(asFlexibleType.getLowerBound(), asFlexibleType.getUpperBound());
            } else {
                pair = new Pair(kotlinType, kotlinType);
            }
            KotlinType kotlinType2 = (KotlinType) pair.component1();
            KotlinType kotlinType3 = (KotlinType) pair.component2();
            JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
            if (kotlinType2.isMarkedNullable()) {
                nullabilityQualifier2 = NullabilityQualifier.NULLABLE;
            } else {
                if (kotlinType3.isMarkedNullable()) {
                    nullabilityQualifier = null;
                    if (javaToKotlinClassMap.isReadOnly(kotlinType2)) {
                        mutabilityQualifier = javaToKotlinClassMap.isMutable(kotlinType3) ? MutabilityQualifier.MUTABLE : null;
                    } else {
                        mutabilityQualifier = MutabilityQualifier.READ_ONLY;
                    }
                    return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, kotlinType.unwrap() instanceof NotNullTypeParameter, false, 8, null);
                }
                nullabilityQualifier2 = NullabilityQualifier.NOT_NULL;
            }
            nullabilityQualifier = nullabilityQualifier2;
            if (javaToKotlinClassMap.isReadOnly(kotlinType2)) {
            }
            return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, kotlinType.unwrap() instanceof NotNullTypeParameter, false, 8, null);
        }

        private final JavaTypeQualifiers extractQualifiersFromAnnotations(KotlinType kotlinType, boolean z, JavaTypeQualifiers javaTypeQualifiers) {
            final Annotations annotations;
            Annotated annotated;
            if (z && (annotated = this.typeContainer) != null) {
                annotations = AnnotationsKt.composeAnnotations(annotated.getAnnotations(), kotlinType.getAnnotations());
            } else {
                annotations = kotlinType.getAnnotations();
            }
            Function2<List<? extends FqName>, T, T> function2 = new Function2<List<? extends FqName>, T, T>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(2);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(List<? extends FqName> list, Object obj) {
                    return invoke2((List<FqName>) list, (List<? extends FqName>) obj);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final <T> T invoke2(List<FqName> ifPresent, T qualifier) {
                    Intrinsics.checkParameterIsNotNull(ifPresent, "$this$ifPresent");
                    Intrinsics.checkParameterIsNotNull(qualifier, "qualifier");
                    List<FqName> list = ifPresent;
                    boolean z2 = true;
                    if (!(list instanceof Collection) || !list.isEmpty()) {
                        Iterator<T> it = list.iterator();
                        while (it.hasNext()) {
                            if (Annotations.this.mo5455findAnnotation((FqName) it.next()) != null) {
                                break;
                            }
                        }
                    }
                    z2 = false;
                    if (z2) {
                        return qualifier;
                    }
                    return null;
                }
            };
            C7706x4a76798a c7706x4a76798a = C7706x4a76798a.INSTANCE;
            if (z) {
                JavaTypeQualifiersByElementType defaultTypeQualifiers = this.containerContext.getDefaultTypeQualifiers();
                javaTypeQualifiers = defaultTypeQualifiers != null ? defaultTypeQualifiers.get(this.containerApplicabilityType) : null;
            }
            NullabilityQualifierWithMigrationStatus extractNullability = extractNullability(annotations);
            if (extractNullability == null) {
                extractNullability = (javaTypeQualifiers == null || javaTypeQualifiers.getNullability() == null) ? null : new NullabilityQualifierWithMigrationStatus(javaTypeQualifiers.getNullability(), javaTypeQualifiers.isNullabilityQualifierForWarning());
            }
            NullabilityQualifier qualifier = extractNullability != null ? extractNullability.getQualifier() : null;
            MutabilityQualifier mutabilityQualifier = (MutabilityQualifier) c7706x4a76798a.invoke(function2.invoke2(JvmAnnotationNamesKt.getREAD_ONLY_ANNOTATIONS(), (List<FqName>) MutabilityQualifier.READ_ONLY), function2.invoke2(JvmAnnotationNamesKt.getMUTABLE_ANNOTATIONS(), (List<FqName>) MutabilityQualifier.MUTABLE));
            boolean z2 = false;
            boolean z3 = (extractNullability != null ? extractNullability.getQualifier() : null) == NullabilityQualifier.NOT_NULL && TypeUtilsKt.isTypeParameter(kotlinType);
            if (extractNullability != null && extractNullability.isForWarningOnly()) {
                z2 = true;
            }
            return new JavaTypeQualifiers(qualifier, mutabilityQualifier, z3, z2);
        }

        private final NullabilityQualifierWithMigrationStatus extractNullability(Annotations annotations) {
            SignatureEnhancement signatureEnhancement = this.this$0;
            Iterator<AnnotationDescriptor> it = annotations.iterator();
            while (it.hasNext()) {
                NullabilityQualifierWithMigrationStatus extractNullability = signatureEnhancement.extractNullability(it.next());
                if (extractNullability != null) {
                    return extractNullability;
                }
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x006d  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0078  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x006f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final Function1<Integer, JavaTypeQualifiers> computeIndexedQualifiersForOverride() {
            boolean z;
            int size;
            int i;
            boolean z2;
            Collection<KotlinType> collection = this.fromOverridden;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                arrayList.add(toIndexed((KotlinType) it.next()));
            }
            ArrayList arrayList2 = arrayList;
            List<TypeAndDefaultQualifiers> indexed = toIndexed(this.fromOverride);
            if (this.isCovariant) {
                Collection<KotlinType> collection2 = this.fromOverridden;
                if (!(collection2 instanceof Collection) || !collection2.isEmpty()) {
                    Iterator<T> it2 = collection2.iterator();
                    while (it2.hasNext()) {
                        if (!KotlinTypeChecker.DEFAULT.equalTypes((KotlinType) it2.next(), this.fromOverride)) {
                            z2 = true;
                            break;
                        }
                    }
                }
                z2 = false;
                if (z2) {
                    z = true;
                    size = !z ? 1 : indexed.size();
                    final JavaTypeQualifiers[] javaTypeQualifiersArr = new JavaTypeQualifiers[size];
                    i = 0;
                    while (i < size) {
                        boolean z3 = i == 0;
                        boolean z4 = z3 || !z;
                        if (_Assertions.ENABLED && !z4) {
                            throw new AssertionError("Only head type constructors should be computed");
                        }
                        TypeAndDefaultQualifiers typeAndDefaultQualifiers = indexed.get(i);
                        KotlinType component1 = typeAndDefaultQualifiers.component1();
                        JavaTypeQualifiers component2 = typeAndDefaultQualifiers.component2();
                        ArrayList arrayList3 = new ArrayList();
                        Iterator it3 = arrayList2.iterator();
                        while (it3.hasNext()) {
                            TypeAndDefaultQualifiers typeAndDefaultQualifiers2 = (TypeAndDefaultQualifiers) CollectionsKt.getOrNull((List) it3.next(), i);
                            KotlinType type = typeAndDefaultQualifiers2 != null ? typeAndDefaultQualifiers2.getType() : null;
                            if (type != null) {
                                arrayList3.add(type);
                            }
                        }
                        javaTypeQualifiersArr[i] = computeQualifiersForOverride(component1, arrayList3, component2, z3);
                        i++;
                    }
                    return new Function1<Integer, JavaTypeQualifiers>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$computeIndexedQualifiersForOverride$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ JavaTypeQualifiers invoke(Integer num) {
                            return invoke(num.intValue());
                        }

                        public final JavaTypeQualifiers invoke(int i2) {
                            JavaTypeQualifiers[] javaTypeQualifiersArr2 = javaTypeQualifiersArr;
                            return (i2 < 0 || i2 > ArraysKt.getLastIndex(javaTypeQualifiersArr2)) ? JavaTypeQualifiers.Companion.getNONE() : javaTypeQualifiersArr2[i2];
                        }
                    };
                }
            }
            z = false;
            if (!z) {
            }
            final JavaTypeQualifiers[] javaTypeQualifiersArr2 = new JavaTypeQualifiers[size];
            i = 0;
            while (i < size) {
            }
            return new Function1<Integer, JavaTypeQualifiers>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$computeIndexedQualifiersForOverride$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ JavaTypeQualifiers invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final JavaTypeQualifiers invoke(int i2) {
                    JavaTypeQualifiers[] javaTypeQualifiersArr22 = javaTypeQualifiersArr2;
                    return (i2 < 0 || i2 > ArraysKt.getLastIndex(javaTypeQualifiersArr22)) ? JavaTypeQualifiers.Companion.getNONE() : javaTypeQualifiersArr22[i2];
                }
            };
        }

        private final List<TypeAndDefaultQualifiers> toIndexed(KotlinType kotlinType) {
            final ArrayList arrayList = new ArrayList(1);
            new Function2<KotlinType, LazyJavaResolverContext, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$toIndexed$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(KotlinType kotlinType2, LazyJavaResolverContext lazyJavaResolverContext) {
                    invoke2(kotlinType2, lazyJavaResolverContext);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KotlinType type, LazyJavaResolverContext ownerContext) {
                    Intrinsics.checkParameterIsNotNull(type, "type");
                    Intrinsics.checkParameterIsNotNull(ownerContext, "ownerContext");
                    LazyJavaResolverContext copyWithNewDefaultTypeQualifiers = ContextKt.copyWithNewDefaultTypeQualifiers(ownerContext, type.getAnnotations());
                    ArrayList arrayList2 = arrayList;
                    JavaTypeQualifiersByElementType defaultTypeQualifiers = copyWithNewDefaultTypeQualifiers.getDefaultTypeQualifiers();
                    arrayList2.add(new TypeAndDefaultQualifiers(type, defaultTypeQualifiers != null ? defaultTypeQualifiers.get(AnnotationTypeQualifierResolver.QualifierApplicabilityType.TYPE_USE) : null));
                    for (TypeProjection typeProjection : type.getArguments()) {
                        if (typeProjection.isStarProjection()) {
                            ArrayList arrayList3 = arrayList;
                            KotlinType type2 = typeProjection.getType();
                            Intrinsics.checkExpressionValueIsNotNull(type2, "arg.type");
                            arrayList3.add(new TypeAndDefaultQualifiers(type2, null));
                        } else {
                            KotlinType type3 = typeProjection.getType();
                            Intrinsics.checkExpressionValueIsNotNull(type3, "arg.type");
                            invoke2(type3, copyWithNewDefaultTypeQualifiers);
                        }
                    }
                }
            }.invoke2(kotlinType, this.containerContext);
            return arrayList;
        }

        /* JADX WARN: Removed duplicated region for block: B:79:0x0149  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final JavaTypeQualifiers computeQualifiersForOverride(KotlinType kotlinType, Collection<? extends KotlinType> collection, JavaTypeQualifiers javaTypeQualifiers, boolean z) {
            boolean z2;
            boolean z3;
            Collection<? extends KotlinType> collection2 = collection;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection2, 10));
            Iterator<T> it = collection2.iterator();
            while (it.hasNext()) {
                arrayList.add(extractQualifiers((KotlinType) it.next()));
            }
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList();
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                MutabilityQualifier mutability = ((JavaTypeQualifiers) it2.next()).getMutability();
                if (mutability != null) {
                    arrayList3.add(mutability);
                }
            }
            Set set = CollectionsKt.toSet(arrayList3);
            ArrayList arrayList4 = new ArrayList();
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                NullabilityQualifier nullability = ((JavaTypeQualifiers) it3.next()).getNullability();
                if (nullability != null) {
                    arrayList4.add(nullability);
                }
            }
            Set set2 = CollectionsKt.toSet(arrayList4);
            ArrayList arrayList5 = new ArrayList();
            Iterator<T> it4 = collection2.iterator();
            while (it4.hasNext()) {
                NullabilityQualifier nullability2 = extractQualifiers(TypeWithEnhancementKt.unwrapEnhancement((KotlinType) it4.next())).getNullability();
                if (nullability2 != null) {
                    arrayList5.add(nullability2);
                }
            }
            Set set3 = CollectionsKt.toSet(arrayList5);
            JavaTypeQualifiers extractQualifiersFromAnnotations = extractQualifiersFromAnnotations(kotlinType, z, javaTypeQualifiers);
            NullabilityQualifier nullabilityQualifier = null;
            JavaTypeQualifiers javaTypeQualifiers2 = extractQualifiersFromAnnotations.isNullabilityQualifierForWarning() ^ true ? extractQualifiersFromAnnotations : null;
            NullabilityQualifier nullability3 = javaTypeQualifiers2 != null ? javaTypeQualifiers2.getNullability() : null;
            NullabilityQualifier nullability4 = extractQualifiersFromAnnotations.getNullability();
            boolean z4 = this.isCovariant && z;
            NullabilityQualifier select = SignatureEnhancementKt.select(set2, nullability3, z4);
            if (select != null) {
                if (!(isForVarargParameter() && z && select == NullabilityQualifier.NULLABLE)) {
                    nullabilityQualifier = select;
                }
            }
            MutabilityQualifier mutabilityQualifier = (MutabilityQualifier) SignatureEnhancementKt.select(set, MutabilityQualifier.MUTABLE, MutabilityQualifier.READ_ONLY, extractQualifiersFromAnnotations.getMutability(), z4);
            boolean z5 = nullability4 != nullability3 || (Intrinsics.areEqual(set3, set2) ^ true);
            if (!extractQualifiersFromAnnotations.isNotNullTypeParameter()) {
                if (!(arrayList2 instanceof Collection) || !arrayList2.isEmpty()) {
                    Iterator it5 = arrayList2.iterator();
                    while (it5.hasNext()) {
                        if (((JavaTypeQualifiers) it5.next()).isNotNullTypeParameter()) {
                            z3 = true;
                            break;
                        }
                    }
                }
                z3 = false;
                if (!z3) {
                    z2 = false;
                    if (nullabilityQualifier != null && z5) {
                        return SignatureEnhancementKt.createJavaTypeQualifiers(SignatureEnhancementKt.select(set3, nullability4, z4), mutabilityQualifier, true, z2);
                    }
                    return SignatureEnhancementKt.createJavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, nullabilityQualifier == null, z2);
                }
            }
            z2 = true;
            if (nullabilityQualifier != null) {
            }
            return SignatureEnhancementKt.createJavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, nullabilityQualifier == null, z2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: signatureEnhancement.kt */
    /* loaded from: classes2.dex */
    public static class PartEnhancementResult {
        private final boolean containsFunctionN;
        private final KotlinType type;
        private final boolean wereChanges;

        public PartEnhancementResult(KotlinType type, boolean z, boolean z2) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            this.type = type;
            this.wereChanges = z;
            this.containsFunctionN = z2;
        }

        public final KotlinType getType() {
            return this.type;
        }

        public final boolean getWereChanges() {
            return this.wereChanges;
        }

        public final boolean getContainsFunctionN() {
            return this.containsFunctionN;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: signatureEnhancement.kt */
    /* loaded from: classes2.dex */
    public static final class ValueParameterEnhancementResult extends PartEnhancementResult {
        private final boolean hasDefaultValue;

        public final boolean getHasDefaultValue() {
            return this.hasDefaultValue;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ValueParameterEnhancementResult(KotlinType type, boolean z, boolean z2, boolean z3) {
            super(type, z2, z3);
            Intrinsics.checkParameterIsNotNull(type, "type");
            this.hasDefaultValue = z;
        }
    }

    private final SignatureParts partsForValueParameter(CallableMemberDescriptor callableMemberDescriptor, ValueParameterDescriptor valueParameterDescriptor, LazyJavaResolverContext lazyJavaResolverContext, Function1<? super CallableMemberDescriptor, ? extends KotlinType> function1) {
        LazyJavaResolverContext copyWithNewDefaultTypeQualifiers;
        return parts(callableMemberDescriptor, valueParameterDescriptor, false, (valueParameterDescriptor == null || (copyWithNewDefaultTypeQualifiers = ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, valueParameterDescriptor.getAnnotations())) == null) ? lazyJavaResolverContext : copyWithNewDefaultTypeQualifiers, AnnotationTypeQualifierResolver.QualifierApplicabilityType.VALUE_PARAMETER, function1);
    }

    private final SignatureParts parts(CallableMemberDescriptor callableMemberDescriptor, Annotated annotated, boolean z, LazyJavaResolverContext lazyJavaResolverContext, AnnotationTypeQualifierResolver.QualifierApplicabilityType qualifierApplicabilityType, Function1<? super CallableMemberDescriptor, ? extends KotlinType> function1) {
        KotlinType invoke = function1.invoke(callableMemberDescriptor);
        Collection<? extends CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
        Intrinsics.checkExpressionValueIsNotNull(overriddenDescriptors, "this.overriddenDescriptors");
        Collection<? extends CallableMemberDescriptor> collection = overriddenDescriptors;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        for (CallableMemberDescriptor it : collection) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            arrayList.add(function1.invoke(it));
        }
        return new SignatureParts(this, annotated, invoke, arrayList, z, ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, function1.invoke(callableMemberDescriptor).getAnnotations()), qualifierApplicabilityType);
    }
}
