package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Parser;
import kotlin.reflect.jvm.internal.impl.resolve.MemberComparator;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.sequences.SequencesKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: DeserializedMemberScope.kt */
/* loaded from: classes2.dex */
public abstract class DeserializedMemberScope extends MemberScopeImpl {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedMemberScope.class), "functionNamesLazy", "getFunctionNamesLazy()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedMemberScope.class), "variableNamesLazy", "getVariableNamesLazy()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedMemberScope.class), "classNames", "getClassNames$deserialization()Ljava/util/Set;"))};

    /* renamed from: c */
    private final DeserializationContext f8784c;
    private final NotNullLazyValue classNames$delegate;
    private final NotNullLazyValue functionNamesLazy$delegate;
    private final Map<Name, byte[]> functionProtosBytes;
    private final MemoizedFunctionToNotNull<Name, Collection<SimpleFunctionDescriptor>> functions;
    private final MemoizedFunctionToNotNull<Name, Collection<PropertyDescriptor>> properties;
    private final Map<Name, byte[]> propertyProtosBytes;
    private final MemoizedFunctionToNullable<Name, TypeAliasDescriptor> typeAliasByName;
    private final Map<Name, byte[]> typeAliasBytes;
    private final NotNullLazyValue variableNamesLazy$delegate;

    private final Set<Name> getFunctionNamesLazy() {
        return (Set) StorageKt.getValue(this.functionNamesLazy$delegate, this, (KProperty<?>) $$delegatedProperties[0]);
    }

    private final Set<Name> getVariableNamesLazy() {
        return (Set) StorageKt.getValue(this.variableNamesLazy$delegate, this, (KProperty<?>) $$delegatedProperties[1]);
    }

    protected abstract void addEnumEntryDescriptors(Collection<DeclarationDescriptor> collection, Function1<? super Name, Boolean> function1);

    protected void computeNonDeclaredFunctions(Name name, Collection<SimpleFunctionDescriptor> functions) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(functions, "functions");
    }

    protected void computeNonDeclaredProperties(Name name, Collection<PropertyDescriptor> descriptors) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(descriptors, "descriptors");
    }

    protected abstract ClassId createClassId(Name name);

    public final Set<Name> getClassNames$deserialization() {
        return (Set) StorageKt.getValue(this.classNames$delegate, this, (KProperty<?>) $$delegatedProperties[2]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Set<Name> getNonDeclaredFunctionNames();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Set<Name> getNonDeclaredVariableNames();

    /* JADX INFO: Access modifiers changed from: protected */
    public final DeserializationContext getC() {
        return this.f8784c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DeserializedMemberScope(DeserializationContext c, Collection<ProtoBuf.Function> functionList, Collection<ProtoBuf.Property> propertyList, Collection<ProtoBuf.TypeAlias> typeAliasList, final Function0<? extends Collection<Name>> classNames) {
        Map<Name, byte[]> emptyMap;
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(functionList, "functionList");
        Intrinsics.checkParameterIsNotNull(propertyList, "propertyList");
        Intrinsics.checkParameterIsNotNull(typeAliasList, "typeAliasList");
        Intrinsics.checkParameterIsNotNull(classNames, "classNames");
        this.f8784c = c;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : functionList) {
            Name name = NameResolverUtilKt.getName(this.f8784c.getNameResolver(), ((ProtoBuf.Function) ((MessageLite) obj)).getName());
            Object obj2 = linkedHashMap.get(name);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(name, obj2);
            }
            ((List) obj2).add(obj);
        }
        this.functionProtosBytes = packToByteArray(linkedHashMap);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Object obj3 : propertyList) {
            Name name2 = NameResolverUtilKt.getName(this.f8784c.getNameResolver(), ((ProtoBuf.Property) ((MessageLite) obj3)).getName());
            Object obj4 = linkedHashMap2.get(name2);
            if (obj4 == null) {
                obj4 = new ArrayList();
                linkedHashMap2.put(name2, obj4);
            }
            ((List) obj4).add(obj3);
        }
        this.propertyProtosBytes = packToByteArray(linkedHashMap2);
        if (!this.f8784c.getComponents().getConfiguration().getTypeAliasesAllowed()) {
            emptyMap = MapsKt.emptyMap();
        } else {
            LinkedHashMap linkedHashMap3 = new LinkedHashMap();
            for (Object obj5 : typeAliasList) {
                Name name3 = NameResolverUtilKt.getName(this.f8784c.getNameResolver(), ((ProtoBuf.TypeAlias) ((MessageLite) obj5)).getName());
                Object obj6 = linkedHashMap3.get(name3);
                if (obj6 == null) {
                    obj6 = new ArrayList();
                    linkedHashMap3.put(name3, obj6);
                }
                ((List) obj6).add(obj5);
            }
            emptyMap = packToByteArray(linkedHashMap3);
        }
        this.typeAliasBytes = emptyMap;
        this.functions = this.f8784c.getStorageManager().createMemoizedFunction(new Function1<Name, Collection<? extends SimpleFunctionDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$functions$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Collection<SimpleFunctionDescriptor> invoke(Name it) {
                Collection<SimpleFunctionDescriptor> computeFunctions;
                Intrinsics.checkParameterIsNotNull(it, "it");
                computeFunctions = DeserializedMemberScope.this.computeFunctions(it);
                return computeFunctions;
            }
        });
        this.properties = this.f8784c.getStorageManager().createMemoizedFunction(new Function1<Name, Collection<? extends PropertyDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$properties$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Collection<PropertyDescriptor> invoke(Name it) {
                Collection<PropertyDescriptor> computeProperties;
                Intrinsics.checkParameterIsNotNull(it, "it");
                computeProperties = DeserializedMemberScope.this.computeProperties(it);
                return computeProperties;
            }
        });
        this.typeAliasByName = this.f8784c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<Name, TypeAliasDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$typeAliasByName$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final TypeAliasDescriptor invoke(Name it) {
                TypeAliasDescriptor createTypeAlias;
                Intrinsics.checkParameterIsNotNull(it, "it");
                createTypeAlias = DeserializedMemberScope.this.createTypeAlias(it);
                return createTypeAlias;
            }
        });
        this.functionNamesLazy$delegate = this.f8784c.getStorageManager().createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$functionNamesLazy$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Set<? extends Name> invoke() {
                Map map;
                map = DeserializedMemberScope.this.functionProtosBytes;
                return SetsKt.plus(map.keySet(), (Iterable) DeserializedMemberScope.this.getNonDeclaredFunctionNames());
            }
        });
        this.variableNamesLazy$delegate = this.f8784c.getStorageManager().createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$variableNamesLazy$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Set<? extends Name> invoke() {
                Map map;
                map = DeserializedMemberScope.this.propertyProtosBytes;
                return SetsKt.plus(map.keySet(), (Iterable) DeserializedMemberScope.this.getNonDeclaredVariableNames());
            }
        });
        this.classNames$delegate = this.f8784c.getStorageManager().createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$classNames$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Set<? extends Name> invoke() {
                return CollectionsKt.toSet((Iterable) Function0.this.invoke());
            }
        });
    }

    private final Set<Name> getTypeAliasNames() {
        return this.typeAliasBytes.keySet();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set<Name> getFunctionNames() {
        return getFunctionNamesLazy();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set<Name> getVariableNames() {
        return getVariableNamesLazy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0043 A[LOOP:0: B:7:0x003d->B:9:0x0043, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Collection<SimpleFunctionDescriptor> computeFunctions(Name name) {
        List<ProtoBuf.Function> emptyList;
        Map<Name, byte[]> map = this.functionProtosBytes;
        final Parser<ProtoBuf.Function> parser = ProtoBuf.Function.PARSER;
        Intrinsics.checkExpressionValueIsNotNull(parser, "ProtoBuf.Function.PARSER");
        byte[] bArr = map.get(name);
        if (bArr != null) {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            List list = SequencesKt.toList(SequencesKt.generateSequence(new Function0<M>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$computeDescriptors$$inlined$let$lambda$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Incorrect return type in method signature: ()TM; */
                @Override // kotlin.jvm.functions.Function0
                public final MessageLite invoke() {
                    return (MessageLite) parser.parseDelimitedFrom(byteArrayInputStream, this.getC().getComponents().getExtensionRegistryLite());
                }
            }));
            if (list != null) {
                emptyList = list;
                ArrayList arrayList = new ArrayList();
                for (ProtoBuf.Function it : emptyList) {
                    MemberDeserializer memberDeserializer = this.f8784c.getMemberDeserializer();
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    arrayList.add(memberDeserializer.loadFunction(it));
                }
                ArrayList arrayList2 = arrayList;
                computeNonDeclaredFunctions(name, arrayList2);
                return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList2);
            }
        }
        emptyList = CollectionsKt.emptyList();
        ArrayList arrayList3 = new ArrayList();
        while (r0.hasNext()) {
        }
        ArrayList arrayList22 = arrayList3;
        computeNonDeclaredFunctions(name, arrayList22);
        return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList22);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public Collection<SimpleFunctionDescriptor> getContributedFunctions(Name name, LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        return !getFunctionNames().contains(name) ? CollectionsKt.emptyList() : this.functions.invoke(name);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0043 A[LOOP:0: B:7:0x003d->B:9:0x0043, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Collection<PropertyDescriptor> computeProperties(Name name) {
        List<ProtoBuf.Property> emptyList;
        Map<Name, byte[]> map = this.propertyProtosBytes;
        final Parser<ProtoBuf.Property> parser = ProtoBuf.Property.PARSER;
        Intrinsics.checkExpressionValueIsNotNull(parser, "ProtoBuf.Property.PARSER");
        byte[] bArr = map.get(name);
        if (bArr != null) {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            List list = SequencesKt.toList(SequencesKt.generateSequence(new Function0<M>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$computeDescriptors$$inlined$let$lambda$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Incorrect return type in method signature: ()TM; */
                @Override // kotlin.jvm.functions.Function0
                public final MessageLite invoke() {
                    return (MessageLite) parser.parseDelimitedFrom(byteArrayInputStream, this.getC().getComponents().getExtensionRegistryLite());
                }
            }));
            if (list != null) {
                emptyList = list;
                ArrayList arrayList = new ArrayList();
                for (ProtoBuf.Property it : emptyList) {
                    MemberDeserializer memberDeserializer = this.f8784c.getMemberDeserializer();
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    arrayList.add(memberDeserializer.loadProperty(it));
                }
                ArrayList arrayList2 = arrayList;
                computeNonDeclaredProperties(name, arrayList2);
                return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList2);
            }
        }
        emptyList = CollectionsKt.emptyList();
        ArrayList arrayList3 = new ArrayList();
        while (r0.hasNext()) {
        }
        ArrayList arrayList22 = arrayList3;
        computeNonDeclaredProperties(name, arrayList22);
        return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList22);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TypeAliasDescriptor createTypeAlias(Name name) {
        ProtoBuf.TypeAlias parseDelimitedFrom;
        byte[] bArr = this.typeAliasBytes.get(name);
        if (bArr == null || (parseDelimitedFrom = ProtoBuf.TypeAlias.parseDelimitedFrom(new ByteArrayInputStream(bArr), this.f8784c.getComponents().getExtensionRegistryLite())) == null) {
            return null;
        }
        return this.f8784c.getMemberDeserializer().loadTypeAlias(parseDelimitedFrom);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection<PropertyDescriptor> getContributedVariables(Name name, LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        return !getVariableNames().contains(name) ? CollectionsKt.emptyList() : this.properties.invoke(name);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Collection<DeclarationDescriptor> computeDescriptors(DescriptorKindFilter kindFilter, Function1<? super Name, Boolean> nameFilter, LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(kindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
        Intrinsics.checkParameterIsNotNull(location, "location");
        ArrayList arrayList = new ArrayList(0);
        if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getSINGLETON_CLASSIFIERS_MASK())) {
            addEnumEntryDescriptors(arrayList, nameFilter);
        }
        ArrayList arrayList2 = arrayList;
        addFunctionsAndProperties(arrayList2, kindFilter, nameFilter, location);
        if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getCLASSIFIERS_MASK())) {
            for (Name name : getClassNames$deserialization()) {
                if (nameFilter.invoke(name).booleanValue()) {
                    kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arrayList2, deserializeClass(name));
                }
            }
        }
        if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getTYPE_ALIASES_MASK())) {
            for (Name name2 : getTypeAliasNames()) {
                if (nameFilter.invoke(name2).booleanValue()) {
                    kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arrayList2, this.typeAliasByName.invoke(name2));
                }
            }
        }
        return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList);
    }

    private final void addFunctionsAndProperties(Collection<DeclarationDescriptor> collection, DescriptorKindFilter descriptorKindFilter, Function1<? super Name, Boolean> function1, LookupLocation lookupLocation) {
        if (descriptorKindFilter.acceptsKinds(DescriptorKindFilter.Companion.getVARIABLES_MASK())) {
            Set<Name> variableNames = getVariableNames();
            ArrayList arrayList = new ArrayList();
            for (Name name : variableNames) {
                if (function1.invoke(name).booleanValue()) {
                    arrayList.addAll(getContributedVariables(name, lookupLocation));
                }
            }
            MemberComparator.NameAndTypeMemberComparator nameAndTypeMemberComparator = MemberComparator.NameAndTypeMemberComparator.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(nameAndTypeMemberComparator, "MemberComparator.NameAnd…MemberComparator.INSTANCE");
            CollectionsKt.sortWith(arrayList, nameAndTypeMemberComparator);
            collection.addAll(arrayList);
        }
        if (descriptorKindFilter.acceptsKinds(DescriptorKindFilter.Companion.getFUNCTIONS_MASK())) {
            Set<Name> functionNames = getFunctionNames();
            ArrayList arrayList2 = new ArrayList();
            for (Name name2 : functionNames) {
                if (function1.invoke(name2).booleanValue()) {
                    arrayList2.addAll(getContributedFunctions(name2, lookupLocation));
                }
            }
            MemberComparator.NameAndTypeMemberComparator nameAndTypeMemberComparator2 = MemberComparator.NameAndTypeMemberComparator.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(nameAndTypeMemberComparator2, "MemberComparator.NameAnd…MemberComparator.INSTANCE");
            CollectionsKt.sortWith(arrayList2, nameAndTypeMemberComparator2);
            collection.addAll(arrayList2);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    /* renamed from: getContributedClassifier */
    public ClassifierDescriptor mo5462getContributedClassifier(Name name, LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        if (hasClass(name)) {
            return deserializeClass(name);
        }
        if (getTypeAliasNames().contains(name)) {
            return this.typeAliasByName.invoke(name);
        }
        return null;
    }

    private final ClassDescriptor deserializeClass(Name name) {
        return this.f8784c.getComponents().deserializeClass(createClassId(name));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasClass(Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return getClassNames$deserialization().contains(name);
    }

    private final Map<Name, byte[]> packToByteArray(Map<Name, ? extends Collection<? extends AbstractMessageLite>> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        Iterator<T> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Iterable iterable = (Iterable) entry.getValue();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            Iterator it2 = iterable.iterator();
            while (it2.hasNext()) {
                ((AbstractMessageLite) it2.next()).writeDelimitedTo(byteArrayOutputStream);
                arrayList.add(Unit.INSTANCE);
            }
            linkedHashMap.put(key, byteArrayOutputStream.toByteArray());
        }
        return linkedHashMap;
    }
}
