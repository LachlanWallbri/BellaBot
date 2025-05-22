package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FieldDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirement;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedSimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedTypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.NonEmptyDeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: MemberDeserializer.kt */
/* loaded from: classes2.dex */
public final class MemberDeserializer {
    private final AnnotationDeserializer annotationDeserializer;

    /* renamed from: c */
    private final DeserializationContext f8780c;

    private final int loadOldFlags(int i) {
        return (i & 63) + ((i >> 8) << 6);
    }

    public MemberDeserializer(DeserializationContext c) {
        Intrinsics.checkParameterIsNotNull(c, "c");
        this.f8780c = c;
        this.annotationDeserializer = new AnnotationDeserializer(this.f8780c.getComponents().getModuleDescriptor(), this.f8780c.getComponents().getNotFoundClasses());
    }

    public final PropertyDescriptor loadProperty(final ProtoBuf.Property proto) {
        ProtoBuf.Property property;
        Annotations empty;
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl;
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl2;
        boolean z;
        PropertyGetterDescriptorImpl createDefaultGetter;
        KotlinType type;
        Intrinsics.checkParameterIsNotNull(proto, "proto");
        int flags = proto.hasFlags() ? proto.getFlags() : loadOldFlags(proto.getOldFlags());
        DeclarationDescriptor containingDeclaration = this.f8780c.getContainingDeclaration();
        ProtoBuf.Property property2 = proto;
        Annotations annotations = getAnnotations(property2, flags, AnnotatedCallableKind.PROPERTY);
        Modality modality = ProtoEnumFlags.INSTANCE.modality(Flags.MODALITY.get(flags));
        Visibility visibility = ProtoEnumFlags.INSTANCE.visibility(Flags.VISIBILITY.get(flags));
        Boolean bool = Flags.IS_VAR.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool, "Flags.IS_VAR.get(flags)");
        boolean booleanValue = bool.booleanValue();
        Name name = NameResolverUtilKt.getName(this.f8780c.getNameResolver(), proto.getName());
        CallableMemberDescriptor.Kind memberKind = ProtoEnumFlags.INSTANCE.memberKind(Flags.MEMBER_KIND.get(flags));
        Boolean bool2 = Flags.IS_LATEINIT.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool2, "Flags.IS_LATEINIT.get(flags)");
        boolean booleanValue2 = bool2.booleanValue();
        Boolean bool3 = Flags.IS_CONST.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool3, "Flags.IS_CONST.get(flags)");
        boolean booleanValue3 = bool3.booleanValue();
        Boolean bool4 = Flags.IS_EXTERNAL_PROPERTY.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool4, "Flags.IS_EXTERNAL_PROPERTY.get(flags)");
        boolean booleanValue4 = bool4.booleanValue();
        Boolean bool5 = Flags.IS_DELEGATED.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool5, "Flags.IS_DELEGATED.get(flags)");
        boolean booleanValue5 = bool5.booleanValue();
        Boolean bool6 = Flags.IS_EXPECT_PROPERTY.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool6, "Flags.IS_EXPECT_PROPERTY.get(flags)");
        final DeserializedPropertyDescriptor deserializedPropertyDescriptor = new DeserializedPropertyDescriptor(containingDeclaration, null, annotations, modality, visibility, booleanValue, name, memberKind, booleanValue2, booleanValue3, booleanValue4, booleanValue5, bool6.booleanValue(), proto, this.f8780c.getNameResolver(), this.f8780c.getTypeTable(), this.f8780c.getVersionRequirementTable(), this.f8780c.getContainerSource());
        List<ProtoBuf.TypeParameter> typeParameterList = proto.getTypeParameterList();
        Intrinsics.checkExpressionValueIsNotNull(typeParameterList, "proto.typeParameterList");
        DeserializationContext childContext$default = DeserializationContext.childContext$default(this.f8780c, deserializedPropertyDescriptor, typeParameterList, null, null, null, null, 60, null);
        Boolean bool7 = Flags.HAS_GETTER.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool7, "Flags.HAS_GETTER.get(flags)");
        boolean booleanValue6 = bool7.booleanValue();
        if (booleanValue6 && ProtoTypeTableUtilKt.hasReceiver(proto)) {
            property = property2;
            empty = getReceiverParameterAnnotations(property, AnnotatedCallableKind.PROPERTY_GETTER);
        } else {
            property = property2;
            empty = Annotations.Companion.getEMPTY();
        }
        KotlinType type2 = childContext$default.getTypeDeserializer().type(ProtoTypeTableUtilKt.returnType(proto, this.f8780c.getTypeTable()));
        List<TypeParameterDescriptor> ownTypeParameters = childContext$default.getTypeDeserializer().getOwnTypeParameters();
        ReceiverParameterDescriptor dispatchReceiverParameter = getDispatchReceiverParameter();
        ProtoBuf.Type receiverType = ProtoTypeTableUtilKt.receiverType(proto, this.f8780c.getTypeTable());
        PropertySetterDescriptorImpl propertySetterDescriptorImpl = null;
        deserializedPropertyDescriptor.setType(type2, ownTypeParameters, dispatchReceiverParameter, (receiverType == null || (type = childContext$default.getTypeDeserializer().type(receiverType)) == null) ? null : DescriptorFactory.createExtensionReceiverParameterForCallable(deserializedPropertyDescriptor, type, empty));
        Boolean bool8 = Flags.HAS_ANNOTATIONS.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool8, "Flags.HAS_ANNOTATIONS.get(flags)");
        int accessorFlags = Flags.getAccessorFlags(bool8.booleanValue(), Flags.VISIBILITY.get(flags), Flags.MODALITY.get(flags), false, false, false);
        if (booleanValue6) {
            int getterFlags = proto.hasGetterFlags() ? proto.getGetterFlags() : accessorFlags;
            Boolean bool9 = Flags.IS_NOT_DEFAULT.get(getterFlags);
            Intrinsics.checkExpressionValueIsNotNull(bool9, "Flags.IS_NOT_DEFAULT.get(getterFlags)");
            boolean booleanValue7 = bool9.booleanValue();
            Boolean bool10 = Flags.IS_EXTERNAL_ACCESSOR.get(getterFlags);
            Intrinsics.checkExpressionValueIsNotNull(bool10, "Flags.IS_EXTERNAL_ACCESSOR.get(getterFlags)");
            boolean booleanValue8 = bool10.booleanValue();
            Boolean bool11 = Flags.IS_INLINE_ACCESSOR.get(getterFlags);
            Intrinsics.checkExpressionValueIsNotNull(bool11, "Flags.IS_INLINE_ACCESSOR.get(getterFlags)");
            boolean booleanValue9 = bool11.booleanValue();
            Annotations annotations2 = getAnnotations(property, getterFlags, AnnotatedCallableKind.PROPERTY_GETTER);
            if (booleanValue7) {
                createDefaultGetter = new PropertyGetterDescriptorImpl(deserializedPropertyDescriptor, annotations2, ProtoEnumFlags.INSTANCE.modality(Flags.MODALITY.get(getterFlags)), ProtoEnumFlags.INSTANCE.visibility(Flags.VISIBILITY.get(getterFlags)), !booleanValue7, booleanValue8, booleanValue9, deserializedPropertyDescriptor.getKind(), null, SourceElement.NO_SOURCE);
            } else {
                createDefaultGetter = DescriptorFactory.createDefaultGetter(deserializedPropertyDescriptor, annotations2);
                Intrinsics.checkExpressionValueIsNotNull(createDefaultGetter, "DescriptorFactory.create…er(property, annotations)");
            }
            createDefaultGetter.initialize(deserializedPropertyDescriptor.getReturnType());
            propertyGetterDescriptorImpl = createDefaultGetter;
        } else {
            propertyGetterDescriptorImpl = null;
        }
        Boolean bool12 = Flags.HAS_SETTER.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool12, "Flags.HAS_SETTER.get(flags)");
        if (bool12.booleanValue()) {
            if (proto.hasSetterFlags()) {
                accessorFlags = proto.getSetterFlags();
            }
            Boolean bool13 = Flags.IS_NOT_DEFAULT.get(accessorFlags);
            Intrinsics.checkExpressionValueIsNotNull(bool13, "Flags.IS_NOT_DEFAULT.get(setterFlags)");
            boolean booleanValue10 = bool13.booleanValue();
            Boolean bool14 = Flags.IS_EXTERNAL_ACCESSOR.get(accessorFlags);
            Intrinsics.checkExpressionValueIsNotNull(bool14, "Flags.IS_EXTERNAL_ACCESSOR.get(setterFlags)");
            boolean booleanValue11 = bool14.booleanValue();
            Boolean bool15 = Flags.IS_INLINE_ACCESSOR.get(accessorFlags);
            Intrinsics.checkExpressionValueIsNotNull(bool15, "Flags.IS_INLINE_ACCESSOR.get(setterFlags)");
            boolean booleanValue12 = bool15.booleanValue();
            Annotations annotations3 = getAnnotations(property, accessorFlags, AnnotatedCallableKind.PROPERTY_SETTER);
            if (booleanValue10) {
                PropertySetterDescriptorImpl propertySetterDescriptorImpl2 = new PropertySetterDescriptorImpl(deserializedPropertyDescriptor, annotations3, ProtoEnumFlags.INSTANCE.modality(Flags.MODALITY.get(accessorFlags)), ProtoEnumFlags.INSTANCE.visibility(Flags.VISIBILITY.get(accessorFlags)), !booleanValue10, booleanValue11, booleanValue12, deserializedPropertyDescriptor.getKind(), null, SourceElement.NO_SOURCE);
                propertyGetterDescriptorImpl2 = propertyGetterDescriptorImpl;
                z = true;
                propertySetterDescriptorImpl2.initialize((ValueParameterDescriptor) CollectionsKt.single((List) DeserializationContext.childContext$default(childContext$default, propertySetterDescriptorImpl2, CollectionsKt.emptyList(), null, null, null, null, 60, null).getMemberDeserializer().valueParameters(CollectionsKt.listOf(proto.getSetterValueParameter()), property, AnnotatedCallableKind.PROPERTY_SETTER)));
                propertySetterDescriptorImpl = propertySetterDescriptorImpl2;
            } else {
                propertyGetterDescriptorImpl2 = propertyGetterDescriptorImpl;
                z = true;
                propertySetterDescriptorImpl = DescriptorFactory.createDefaultSetter(deserializedPropertyDescriptor, annotations3, Annotations.Companion.getEMPTY());
                Intrinsics.checkExpressionValueIsNotNull(propertySetterDescriptorImpl, "DescriptorFactory.create…ptor */\n                )");
            }
        } else {
            propertyGetterDescriptorImpl2 = propertyGetterDescriptorImpl;
            z = true;
        }
        Boolean bool16 = Flags.HAS_CONSTANT.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool16, "Flags.HAS_CONSTANT.get(flags)");
        if (bool16.booleanValue()) {
            deserializedPropertyDescriptor.setCompileTimeInitializer(this.f8780c.getStorageManager().createNullableLazyValue(new Function0<ConstantValue<?>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer$loadProperty$3
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final ConstantValue<?> invoke() {
                    DeserializationContext deserializationContext;
                    ProtoContainer asProtoContainer;
                    DeserializationContext deserializationContext2;
                    MemberDeserializer memberDeserializer = MemberDeserializer.this;
                    deserializationContext = memberDeserializer.f8780c;
                    asProtoContainer = memberDeserializer.asProtoContainer(deserializationContext.getContainingDeclaration());
                    if (asProtoContainer == null) {
                        Intrinsics.throwNpe();
                    }
                    deserializationContext2 = MemberDeserializer.this.f8780c;
                    AnnotationAndConstantLoader<AnnotationDescriptor, ConstantValue<?>> annotationAndConstantLoader = deserializationContext2.getComponents().getAnnotationAndConstantLoader();
                    ProtoBuf.Property property3 = proto;
                    KotlinType returnType = deserializedPropertyDescriptor.getReturnType();
                    Intrinsics.checkExpressionValueIsNotNull(returnType, "property.returnType");
                    return annotationAndConstantLoader.loadPropertyConstant(asProtoContainer, property3, returnType);
                }
            }));
        }
        DeserializedPropertyDescriptor deserializedPropertyDescriptor2 = deserializedPropertyDescriptor;
        deserializedPropertyDescriptor.initialize(propertyGetterDescriptorImpl2, propertySetterDescriptorImpl, new FieldDescriptorImpl(getPropertyFieldAnnotations(proto, false), deserializedPropertyDescriptor2), new FieldDescriptorImpl(getPropertyFieldAnnotations(proto, z), deserializedPropertyDescriptor2), checkExperimentalCoroutine(deserializedPropertyDescriptor, childContext$default.getTypeDeserializer()));
        return deserializedPropertyDescriptor2;
    }

    private final DeserializedMemberDescriptor.CoroutinesCompatibilityMode checkExperimentalCoroutine(DeserializedMemberDescriptor deserializedMemberDescriptor, TypeDeserializer typeDeserializer) {
        if (!versionAndReleaseCoroutinesMismatch(deserializedMemberDescriptor)) {
            return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE;
        }
        forceUpperBoundsComputation(typeDeserializer);
        if (typeDeserializer.getExperimentalSuspendFunctionTypeEncountered()) {
            return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE;
        }
        return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE;
    }

    private final void forceUpperBoundsComputation(TypeDeserializer typeDeserializer) {
        Iterator<T> it = typeDeserializer.getOwnTypeParameters().iterator();
        while (it.hasNext()) {
            ((TypeParameterDescriptor) it.next()).getUpperBounds();
        }
    }

    private final void initializeWithCoroutinesExperimentalityStatus(DeserializedSimpleFunctionDescriptor deserializedSimpleFunctionDescriptor, ReceiverParameterDescriptor receiverParameterDescriptor, ReceiverParameterDescriptor receiverParameterDescriptor2, List<? extends TypeParameterDescriptor> list, List<? extends ValueParameterDescriptor> list2, KotlinType kotlinType, Modality modality, Visibility visibility, Map<? extends CallableDescriptor.UserDataKey<?>, ?> map, boolean z) {
        deserializedSimpleFunctionDescriptor.initialize(receiverParameterDescriptor, receiverParameterDescriptor2, list, list2, kotlinType, modality, visibility, map, computeExperimentalityModeForFunctions(deserializedSimpleFunctionDescriptor, receiverParameterDescriptor, list2, list, kotlinType, z));
    }

    private final DeserializedMemberDescriptor.CoroutinesCompatibilityMode computeExperimentalityModeForFunctions(DeserializedCallableMemberDescriptor deserializedCallableMemberDescriptor, ReceiverParameterDescriptor receiverParameterDescriptor, Collection<? extends ValueParameterDescriptor> collection, Collection<? extends TypeParameterDescriptor> collection2, KotlinType kotlinType, boolean z) {
        boolean z2;
        boolean z3;
        DeserializedMemberDescriptor.CoroutinesCompatibilityMode coroutinesCompatibilityMode;
        DeserializedMemberDescriptor.CoroutinesCompatibilityMode coroutinesCompatibilityMode2;
        boolean z4;
        if (versionAndReleaseCoroutinesMismatch(deserializedCallableMemberDescriptor) && !Intrinsics.areEqual(DescriptorUtilsKt.fqNameOrNull(deserializedCallableMemberDescriptor), SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME)) {
            Collection<? extends ValueParameterDescriptor> collection3 = collection;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection3, 10));
            Iterator<T> it = collection3.iterator();
            while (it.hasNext()) {
                arrayList.add(((ValueParameterDescriptor) it.next()).getType());
            }
            List plus = CollectionsKt.plus((Collection) arrayList, (Iterable) CollectionsKt.listOfNotNull(receiverParameterDescriptor != null ? receiverParameterDescriptor.getType() : null));
            if (kotlinType != null && containsSuspendFunctionType(kotlinType)) {
                return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE;
            }
            Collection<? extends TypeParameterDescriptor> collection4 = collection2;
            if (!(collection4 instanceof Collection) || !collection4.isEmpty()) {
                Iterator<T> it2 = collection4.iterator();
                while (it2.hasNext()) {
                    List<KotlinType> upperBounds = ((TypeParameterDescriptor) it2.next()).getUpperBounds();
                    Intrinsics.checkExpressionValueIsNotNull(upperBounds, "typeParameter.upperBounds");
                    List<KotlinType> list = upperBounds;
                    if (!(list instanceof Collection) || !list.isEmpty()) {
                        for (KotlinType it3 : list) {
                            Intrinsics.checkExpressionValueIsNotNull(it3, "it");
                            if (containsSuspendFunctionType(it3)) {
                                z2 = true;
                                break;
                            }
                        }
                    }
                    z2 = false;
                    if (z2) {
                        z3 = true;
                        break;
                    }
                }
            }
            z3 = false;
            if (z3) {
                return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE;
            }
            List<KotlinType> list2 = plus;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (KotlinType type : list2) {
                Intrinsics.checkExpressionValueIsNotNull(type, "type");
                if (FunctionTypesKt.isSuspendFunctionType(type) && type.getArguments().size() <= 3) {
                    List<TypeProjection> arguments = type.getArguments();
                    if (!(arguments instanceof Collection) || !arguments.isEmpty()) {
                        Iterator<T> it4 = arguments.iterator();
                        while (it4.hasNext()) {
                            KotlinType type2 = ((TypeProjection) it4.next()).getType();
                            Intrinsics.checkExpressionValueIsNotNull(type2, "it.type");
                            if (containsSuspendFunctionType(type2)) {
                                z4 = true;
                                break;
                            }
                        }
                    }
                    z4 = false;
                    if (z4) {
                        coroutinesCompatibilityMode2 = DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE;
                    } else {
                        coroutinesCompatibilityMode2 = DeserializedMemberDescriptor.CoroutinesCompatibilityMode.NEEDS_WRAPPER;
                    }
                } else {
                    coroutinesCompatibilityMode2 = containsSuspendFunctionType(type) ? DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE : DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE;
                }
                arrayList2.add(coroutinesCompatibilityMode2);
            }
            DeserializedMemberDescriptor.CoroutinesCompatibilityMode coroutinesCompatibilityMode3 = (DeserializedMemberDescriptor.CoroutinesCompatibilityMode) CollectionsKt.max((Iterable) arrayList2);
            if (coroutinesCompatibilityMode3 == null) {
                coroutinesCompatibilityMode3 = DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE;
            }
            if (z) {
                coroutinesCompatibilityMode = DeserializedMemberDescriptor.CoroutinesCompatibilityMode.NEEDS_WRAPPER;
            } else {
                coroutinesCompatibilityMode = DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE;
            }
            return (DeserializedMemberDescriptor.CoroutinesCompatibilityMode) ComparisonsKt.maxOf(coroutinesCompatibilityMode, coroutinesCompatibilityMode3);
        }
        return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE;
    }

    private final boolean containsSuspendFunctionType(KotlinType kotlinType) {
        return TypeUtilsKt.contains(kotlinType, MemberDeserializer$containsSuspendFunctionType$1.INSTANCE);
    }

    private final boolean versionAndReleaseCoroutinesMismatch(DeserializedMemberDescriptor deserializedMemberDescriptor) {
        boolean z;
        if (!this.f8780c.getComponents().getConfiguration().getReleaseCoroutines()) {
            return false;
        }
        List<VersionRequirement> versionRequirements = deserializedMemberDescriptor.getVersionRequirements();
        if (!(versionRequirements instanceof Collection) || !versionRequirements.isEmpty()) {
            for (VersionRequirement versionRequirement : versionRequirements) {
                if (Intrinsics.areEqual(versionRequirement.getVersion(), new VersionRequirement.Version(1, 3, 0, 4, null)) && versionRequirement.getKind() == ProtoBuf.VersionRequirement.VersionKind.LANGUAGE_VERSION) {
                    z = false;
                    break;
                }
            }
        }
        z = true;
        return z;
    }

    public final SimpleFunctionDescriptor loadFunction(ProtoBuf.Function proto) {
        Annotations empty;
        VersionRequirementTable versionRequirementTable;
        KotlinType type;
        Intrinsics.checkParameterIsNotNull(proto, "proto");
        int flags = proto.hasFlags() ? proto.getFlags() : loadOldFlags(proto.getOldFlags());
        ProtoBuf.Function function = proto;
        Annotations annotations = getAnnotations(function, flags, AnnotatedCallableKind.FUNCTION);
        if (ProtoTypeTableUtilKt.hasReceiver(proto)) {
            empty = getReceiverParameterAnnotations(function, AnnotatedCallableKind.FUNCTION);
        } else {
            empty = Annotations.Companion.getEMPTY();
        }
        if (Intrinsics.areEqual(DescriptorUtilsKt.getFqNameSafe(this.f8780c.getContainingDeclaration()).child(NameResolverUtilKt.getName(this.f8780c.getNameResolver(), proto.getName())), SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME)) {
            versionRequirementTable = VersionRequirementTable.Companion.getEMPTY();
        } else {
            versionRequirementTable = this.f8780c.getVersionRequirementTable();
        }
        DeserializedSimpleFunctionDescriptor deserializedSimpleFunctionDescriptor = new DeserializedSimpleFunctionDescriptor(this.f8780c.getContainingDeclaration(), null, annotations, NameResolverUtilKt.getName(this.f8780c.getNameResolver(), proto.getName()), ProtoEnumFlags.INSTANCE.memberKind(Flags.MEMBER_KIND.get(flags)), proto, this.f8780c.getNameResolver(), this.f8780c.getTypeTable(), versionRequirementTable, this.f8780c.getContainerSource(), null, 1024, null);
        List<ProtoBuf.TypeParameter> typeParameterList = proto.getTypeParameterList();
        Intrinsics.checkExpressionValueIsNotNull(typeParameterList, "proto.typeParameterList");
        DeserializationContext childContext$default = DeserializationContext.childContext$default(this.f8780c, deserializedSimpleFunctionDescriptor, typeParameterList, null, null, null, null, 60, null);
        ProtoBuf.Type receiverType = ProtoTypeTableUtilKt.receiverType(proto, this.f8780c.getTypeTable());
        ReceiverParameterDescriptor createExtensionReceiverParameterForCallable = (receiverType == null || (type = childContext$default.getTypeDeserializer().type(receiverType)) == null) ? null : DescriptorFactory.createExtensionReceiverParameterForCallable(deserializedSimpleFunctionDescriptor, type, empty);
        ReceiverParameterDescriptor dispatchReceiverParameter = getDispatchReceiverParameter();
        List<TypeParameterDescriptor> ownTypeParameters = childContext$default.getTypeDeserializer().getOwnTypeParameters();
        MemberDeserializer memberDeserializer = childContext$default.getMemberDeserializer();
        List<ProtoBuf.ValueParameter> valueParameterList = proto.getValueParameterList();
        Intrinsics.checkExpressionValueIsNotNull(valueParameterList, "proto.valueParameterList");
        List<ValueParameterDescriptor> valueParameters = memberDeserializer.valueParameters(valueParameterList, function, AnnotatedCallableKind.FUNCTION);
        KotlinType type2 = childContext$default.getTypeDeserializer().type(ProtoTypeTableUtilKt.returnType(proto, this.f8780c.getTypeTable()));
        Modality modality = ProtoEnumFlags.INSTANCE.modality(Flags.MODALITY.get(flags));
        Visibility visibility = ProtoEnumFlags.INSTANCE.visibility(Flags.VISIBILITY.get(flags));
        Map<? extends CallableDescriptor.UserDataKey<?>, ?> emptyMap = MapsKt.emptyMap();
        Boolean bool = Flags.IS_SUSPEND.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool, "Flags.IS_SUSPEND.get(flags)");
        initializeWithCoroutinesExperimentalityStatus(deserializedSimpleFunctionDescriptor, createExtensionReceiverParameterForCallable, dispatchReceiverParameter, ownTypeParameters, valueParameters, type2, modality, visibility, emptyMap, bool.booleanValue());
        Boolean bool2 = Flags.IS_OPERATOR.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool2, "Flags.IS_OPERATOR.get(flags)");
        deserializedSimpleFunctionDescriptor.setOperator(bool2.booleanValue());
        Boolean bool3 = Flags.IS_INFIX.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool3, "Flags.IS_INFIX.get(flags)");
        deserializedSimpleFunctionDescriptor.setInfix(bool3.booleanValue());
        Boolean bool4 = Flags.IS_EXTERNAL_FUNCTION.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool4, "Flags.IS_EXTERNAL_FUNCTION.get(flags)");
        deserializedSimpleFunctionDescriptor.setExternal(bool4.booleanValue());
        Boolean bool5 = Flags.IS_INLINE.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool5, "Flags.IS_INLINE.get(flags)");
        deserializedSimpleFunctionDescriptor.setInline(bool5.booleanValue());
        Boolean bool6 = Flags.IS_TAILREC.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool6, "Flags.IS_TAILREC.get(flags)");
        deserializedSimpleFunctionDescriptor.setTailrec(bool6.booleanValue());
        Boolean bool7 = Flags.IS_SUSPEND.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool7, "Flags.IS_SUSPEND.get(flags)");
        deserializedSimpleFunctionDescriptor.setSuspend(bool7.booleanValue());
        Boolean bool8 = Flags.IS_EXPECT_FUNCTION.get(flags);
        Intrinsics.checkExpressionValueIsNotNull(bool8, "Flags.IS_EXPECT_FUNCTION.get(flags)");
        deserializedSimpleFunctionDescriptor.setExpect(bool8.booleanValue());
        Pair<CallableDescriptor.UserDataKey<?>, Object> deserializeContractFromFunction = this.f8780c.getComponents().getContractDeserializer().deserializeContractFromFunction(proto, deserializedSimpleFunctionDescriptor, this.f8780c.getTypeTable(), this.f8780c.getTypeDeserializer());
        if (deserializeContractFromFunction != null) {
            deserializedSimpleFunctionDescriptor.putInUserDataMap(deserializeContractFromFunction.getFirst(), deserializeContractFromFunction.getSecond());
        }
        return deserializedSimpleFunctionDescriptor;
    }

    public final TypeAliasDescriptor loadTypeAlias(ProtoBuf.TypeAlias proto) {
        Intrinsics.checkParameterIsNotNull(proto, "proto");
        Annotations.Companion companion = Annotations.Companion;
        List<ProtoBuf.Annotation> annotationList = proto.getAnnotationList();
        Intrinsics.checkExpressionValueIsNotNull(annotationList, "proto.annotationList");
        List<ProtoBuf.Annotation> list = annotationList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (ProtoBuf.Annotation it : list) {
            AnnotationDeserializer annotationDeserializer = this.annotationDeserializer;
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            arrayList.add(annotationDeserializer.deserializeAnnotation(it, this.f8780c.getNameResolver()));
        }
        DeserializedTypeAliasDescriptor deserializedTypeAliasDescriptor = new DeserializedTypeAliasDescriptor(this.f8780c.getStorageManager(), this.f8780c.getContainingDeclaration(), companion.create(arrayList), NameResolverUtilKt.getName(this.f8780c.getNameResolver(), proto.getName()), ProtoEnumFlags.INSTANCE.visibility(Flags.VISIBILITY.get(proto.getFlags())), proto, this.f8780c.getNameResolver(), this.f8780c.getTypeTable(), this.f8780c.getVersionRequirementTable(), this.f8780c.getContainerSource());
        List<ProtoBuf.TypeParameter> typeParameterList = proto.getTypeParameterList();
        Intrinsics.checkExpressionValueIsNotNull(typeParameterList, "proto.typeParameterList");
        DeserializationContext childContext$default = DeserializationContext.childContext$default(this.f8780c, deserializedTypeAliasDescriptor, typeParameterList, null, null, null, null, 60, null);
        deserializedTypeAliasDescriptor.initialize(childContext$default.getTypeDeserializer().getOwnTypeParameters(), childContext$default.getTypeDeserializer().simpleType(ProtoTypeTableUtilKt.underlyingType(proto, this.f8780c.getTypeTable())), childContext$default.getTypeDeserializer().simpleType(ProtoTypeTableUtilKt.expandedType(proto, this.f8780c.getTypeTable())), checkExperimentalCoroutine(deserializedTypeAliasDescriptor, childContext$default.getTypeDeserializer()));
        return deserializedTypeAliasDescriptor;
    }

    private final ReceiverParameterDescriptor getDispatchReceiverParameter() {
        DeclarationDescriptor containingDeclaration = this.f8780c.getContainingDeclaration();
        if (!(containingDeclaration instanceof ClassDescriptor)) {
            containingDeclaration = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
        if (classDescriptor != null) {
            return classDescriptor.getThisAsReceiverParameter();
        }
        return null;
    }

    public final ClassConstructorDescriptor loadConstructor(ProtoBuf.Constructor proto, boolean z) {
        DeserializedClassConstructorDescriptor deserializedClassConstructorDescriptor;
        DeserializedMemberDescriptor.CoroutinesCompatibilityMode computeExperimentalityModeForFunctions;
        DeserializationContext c;
        TypeDeserializer typeDeserializer;
        Intrinsics.checkParameterIsNotNull(proto, "proto");
        DeclarationDescriptor containingDeclaration = this.f8780c.getContainingDeclaration();
        if (containingDeclaration == null) {
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
        ProtoBuf.Constructor constructor = proto;
        DeserializedClassConstructorDescriptor deserializedClassConstructorDescriptor2 = new DeserializedClassConstructorDescriptor(classDescriptor, null, getAnnotations(constructor, proto.getFlags(), AnnotatedCallableKind.FUNCTION), z, CallableMemberDescriptor.Kind.DECLARATION, proto, this.f8780c.getNameResolver(), this.f8780c.getTypeTable(), this.f8780c.getVersionRequirementTable(), this.f8780c.getContainerSource(), null, 1024, null);
        MemberDeserializer memberDeserializer = DeserializationContext.childContext$default(this.f8780c, deserializedClassConstructorDescriptor2, CollectionsKt.emptyList(), null, null, null, null, 60, null).getMemberDeserializer();
        List<ProtoBuf.ValueParameter> valueParameterList = proto.getValueParameterList();
        Intrinsics.checkExpressionValueIsNotNull(valueParameterList, "proto.valueParameterList");
        deserializedClassConstructorDescriptor2.initialize(memberDeserializer.valueParameters(valueParameterList, constructor, AnnotatedCallableKind.FUNCTION), ProtoEnumFlags.INSTANCE.visibility(Flags.VISIBILITY.get(proto.getFlags())));
        deserializedClassConstructorDescriptor2.setReturnType(classDescriptor.getDefaultType());
        DeclarationDescriptor containingDeclaration2 = this.f8780c.getContainingDeclaration();
        if (!(containingDeclaration2 instanceof DeserializedClassDescriptor)) {
            containingDeclaration2 = null;
        }
        DeserializedClassDescriptor deserializedClassDescriptor = (DeserializedClassDescriptor) containingDeclaration2;
        if ((deserializedClassDescriptor == null || (c = deserializedClassDescriptor.getC()) == null || (typeDeserializer = c.getTypeDeserializer()) == null || !typeDeserializer.getExperimentalSuspendFunctionTypeEncountered() || !versionAndReleaseCoroutinesMismatch(deserializedClassConstructorDescriptor2)) ? false : true) {
            computeExperimentalityModeForFunctions = DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE;
            deserializedClassConstructorDescriptor = deserializedClassConstructorDescriptor2;
        } else {
            List<ValueParameterDescriptor> valueParameters = deserializedClassConstructorDescriptor2.getValueParameters();
            Intrinsics.checkExpressionValueIsNotNull(valueParameters, "descriptor.valueParameters");
            List<ValueParameterDescriptor> list = valueParameters;
            List<TypeParameterDescriptor> typeParameters = deserializedClassConstructorDescriptor2.getTypeParameters();
            Intrinsics.checkExpressionValueIsNotNull(typeParameters, "descriptor.typeParameters");
            deserializedClassConstructorDescriptor = deserializedClassConstructorDescriptor2;
            computeExperimentalityModeForFunctions = computeExperimentalityModeForFunctions(deserializedClassConstructorDescriptor2, null, list, typeParameters, deserializedClassConstructorDescriptor2.getReturnType(), false);
        }
        deserializedClassConstructorDescriptor.setCoroutinesExperimentalCompatibilityMode$deserialization(computeExperimentalityModeForFunctions);
        return deserializedClassConstructorDescriptor;
    }

    private final Annotations getAnnotations(final MessageLite messageLite, int i, final AnnotatedCallableKind annotatedCallableKind) {
        if (!Flags.HAS_ANNOTATIONS.get(i).booleanValue()) {
            return Annotations.Companion.getEMPTY();
        }
        return new NonEmptyDeserializedAnnotations(this.f8780c.getStorageManager(), new Function0<List<? extends AnnotationDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer$getAnnotations$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends AnnotationDescriptor> invoke() {
                DeserializationContext deserializationContext;
                ProtoContainer asProtoContainer;
                List<? extends AnnotationDescriptor> list;
                DeserializationContext deserializationContext2;
                MemberDeserializer memberDeserializer = MemberDeserializer.this;
                deserializationContext = memberDeserializer.f8780c;
                asProtoContainer = memberDeserializer.asProtoContainer(deserializationContext.getContainingDeclaration());
                if (asProtoContainer != null) {
                    deserializationContext2 = MemberDeserializer.this.f8780c;
                    list = CollectionsKt.toList(deserializationContext2.getComponents().getAnnotationAndConstantLoader().loadCallableAnnotations(asProtoContainer, messageLite, annotatedCallableKind));
                } else {
                    list = null;
                }
                return list != null ? list : CollectionsKt.emptyList();
            }
        });
    }

    private final Annotations getPropertyFieldAnnotations(final ProtoBuf.Property property, final boolean z) {
        if (!Flags.HAS_ANNOTATIONS.get(property.getFlags()).booleanValue()) {
            return Annotations.Companion.getEMPTY();
        }
        return new NonEmptyDeserializedAnnotations(this.f8780c.getStorageManager(), new Function0<List<? extends AnnotationDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer$getPropertyFieldAnnotations$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends AnnotationDescriptor> invoke() {
                DeserializationContext deserializationContext;
                ProtoContainer asProtoContainer;
                List<? extends AnnotationDescriptor> list;
                DeserializationContext deserializationContext2;
                DeserializationContext deserializationContext3;
                MemberDeserializer memberDeserializer = MemberDeserializer.this;
                deserializationContext = memberDeserializer.f8780c;
                asProtoContainer = memberDeserializer.asProtoContainer(deserializationContext.getContainingDeclaration());
                if (asProtoContainer == null) {
                    list = null;
                } else if (z) {
                    deserializationContext3 = MemberDeserializer.this.f8780c;
                    list = CollectionsKt.toList(deserializationContext3.getComponents().getAnnotationAndConstantLoader().loadPropertyDelegateFieldAnnotations(asProtoContainer, property));
                } else {
                    deserializationContext2 = MemberDeserializer.this.f8780c;
                    list = CollectionsKt.toList(deserializationContext2.getComponents().getAnnotationAndConstantLoader().loadPropertyBackingFieldAnnotations(asProtoContainer, property));
                }
                return list != null ? list : CollectionsKt.emptyList();
            }
        });
    }

    private final Annotations getReceiverParameterAnnotations(final MessageLite messageLite, final AnnotatedCallableKind annotatedCallableKind) {
        return new DeserializedAnnotations(this.f8780c.getStorageManager(), new Function0<List<? extends AnnotationDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer$getReceiverParameterAnnotations$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends AnnotationDescriptor> invoke() {
                DeserializationContext deserializationContext;
                ProtoContainer asProtoContainer;
                List<AnnotationDescriptor> list;
                DeserializationContext deserializationContext2;
                MemberDeserializer memberDeserializer = MemberDeserializer.this;
                deserializationContext = memberDeserializer.f8780c;
                asProtoContainer = memberDeserializer.asProtoContainer(deserializationContext.getContainingDeclaration());
                if (asProtoContainer != null) {
                    deserializationContext2 = MemberDeserializer.this.f8780c;
                    list = deserializationContext2.getComponents().getAnnotationAndConstantLoader().loadExtensionReceiverParameterAnnotations(asProtoContainer, messageLite, annotatedCallableKind);
                } else {
                    list = null;
                }
                return list != null ? list : CollectionsKt.emptyList();
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final List<ValueParameterDescriptor> valueParameters(List<ProtoBuf.ValueParameter> list, final MessageLite messageLite, final AnnotatedCallableKind annotatedCallableKind) {
        Annotations empty;
        DeclarationDescriptor containingDeclaration = this.f8780c.getContainingDeclaration();
        if (containingDeclaration == null) {
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.CallableDescriptor");
        }
        final CallableDescriptor callableDescriptor = (CallableDescriptor) containingDeclaration;
        DeclarationDescriptor containingDeclaration2 = callableDescriptor.getContainingDeclaration();
        Intrinsics.checkExpressionValueIsNotNull(containingDeclaration2, "callableDescriptor.containingDeclaration");
        final ProtoContainer asProtoContainer = asProtoContainer(containingDeclaration2);
        List<ProtoBuf.ValueParameter> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        int i = 0;
        for (Object obj : list2) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            final ProtoBuf.ValueParameter valueParameter = (ProtoBuf.ValueParameter) obj;
            int flags = valueParameter.hasFlags() ? valueParameter.getFlags() : 0;
            if (asProtoContainer != null) {
                Boolean bool = Flags.HAS_ANNOTATIONS.get(flags);
                Intrinsics.checkExpressionValueIsNotNull(bool, "Flags.HAS_ANNOTATIONS.get(flags)");
                if (bool.booleanValue()) {
                    final int i3 = i;
                    empty = new NonEmptyDeserializedAnnotations(this.f8780c.getStorageManager(), new Function0<List<? extends AnnotationDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer$valueParameters$$inlined$mapIndexed$lambda$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final List<? extends AnnotationDescriptor> invoke() {
                            DeserializationContext deserializationContext;
                            deserializationContext = this.f8780c;
                            return CollectionsKt.toList(deserializationContext.getComponents().getAnnotationAndConstantLoader().loadValueParameterAnnotations(asProtoContainer, messageLite, annotatedCallableKind, i3, valueParameter));
                        }
                    });
                    Name name = NameResolverUtilKt.getName(this.f8780c.getNameResolver(), valueParameter.getName());
                    KotlinType type = this.f8780c.getTypeDeserializer().type(ProtoTypeTableUtilKt.type(valueParameter, this.f8780c.getTypeTable()));
                    Boolean bool2 = Flags.DECLARES_DEFAULT_VALUE.get(flags);
                    Intrinsics.checkExpressionValueIsNotNull(bool2, "Flags.DECLARES_DEFAULT_VALUE.get(flags)");
                    boolean booleanValue = bool2.booleanValue();
                    Boolean bool3 = Flags.IS_CROSSINLINE.get(flags);
                    Intrinsics.checkExpressionValueIsNotNull(bool3, "Flags.IS_CROSSINLINE.get(flags)");
                    boolean booleanValue2 = bool3.booleanValue();
                    Boolean bool4 = Flags.IS_NOINLINE.get(flags);
                    Intrinsics.checkExpressionValueIsNotNull(bool4, "Flags.IS_NOINLINE.get(flags)");
                    boolean booleanValue3 = bool4.booleanValue();
                    ProtoBuf.Type varargElementType = ProtoTypeTableUtilKt.varargElementType(valueParameter, this.f8780c.getTypeTable());
                    KotlinType type2 = varargElementType == null ? this.f8780c.getTypeDeserializer().type(varargElementType) : null;
                    SourceElement sourceElement = SourceElement.NO_SOURCE;
                    Intrinsics.checkExpressionValueIsNotNull(sourceElement, "SourceElement.NO_SOURCE");
                    ArrayList arrayList2 = arrayList;
                    arrayList2.add(new ValueParameterDescriptorImpl(callableDescriptor, null, i, empty, name, type, booleanValue, booleanValue2, booleanValue3, type2, sourceElement));
                    arrayList = arrayList2;
                    i = i2;
                }
            }
            empty = Annotations.Companion.getEMPTY();
            Name name2 = NameResolverUtilKt.getName(this.f8780c.getNameResolver(), valueParameter.getName());
            KotlinType type3 = this.f8780c.getTypeDeserializer().type(ProtoTypeTableUtilKt.type(valueParameter, this.f8780c.getTypeTable()));
            Boolean bool22 = Flags.DECLARES_DEFAULT_VALUE.get(flags);
            Intrinsics.checkExpressionValueIsNotNull(bool22, "Flags.DECLARES_DEFAULT_VALUE.get(flags)");
            boolean booleanValue4 = bool22.booleanValue();
            Boolean bool32 = Flags.IS_CROSSINLINE.get(flags);
            Intrinsics.checkExpressionValueIsNotNull(bool32, "Flags.IS_CROSSINLINE.get(flags)");
            boolean booleanValue22 = bool32.booleanValue();
            Boolean bool42 = Flags.IS_NOINLINE.get(flags);
            Intrinsics.checkExpressionValueIsNotNull(bool42, "Flags.IS_NOINLINE.get(flags)");
            boolean booleanValue32 = bool42.booleanValue();
            ProtoBuf.Type varargElementType2 = ProtoTypeTableUtilKt.varargElementType(valueParameter, this.f8780c.getTypeTable());
            KotlinType type22 = varargElementType2 == null ? this.f8780c.getTypeDeserializer().type(varargElementType2) : null;
            SourceElement sourceElement2 = SourceElement.NO_SOURCE;
            Intrinsics.checkExpressionValueIsNotNull(sourceElement2, "SourceElement.NO_SOURCE");
            ArrayList arrayList22 = arrayList;
            arrayList22.add(new ValueParameterDescriptorImpl(callableDescriptor, null, i, empty, name2, type3, booleanValue4, booleanValue22, booleanValue32, type22, sourceElement2));
            arrayList = arrayList22;
            i = i2;
        }
        return CollectionsKt.toList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ProtoContainer asProtoContainer(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor instanceof PackageFragmentDescriptor) {
            return new ProtoContainer.Package(((PackageFragmentDescriptor) declarationDescriptor).getFqName(), this.f8780c.getNameResolver(), this.f8780c.getTypeTable(), this.f8780c.getContainerSource());
        }
        if (declarationDescriptor instanceof DeserializedClassDescriptor) {
            return ((DeserializedClassDescriptor) declarationDescriptor).getThisAsProtoContainer$deserialization();
        }
        return null;
    }
}
