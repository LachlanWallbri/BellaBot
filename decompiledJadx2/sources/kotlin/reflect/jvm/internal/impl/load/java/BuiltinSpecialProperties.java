package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: specialBuiltinMembers.kt */
/* loaded from: classes2.dex */
public final class BuiltinSpecialProperties {
    private static final Map<Name, List<Name>> GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP;
    public static final BuiltinSpecialProperties INSTANCE = new BuiltinSpecialProperties();
    private static final Map<FqName, Name> PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP;
    private static final Set<FqName> SPECIAL_FQ_NAMES;
    private static final Set<Name> SPECIAL_SHORT_NAMES;

    static {
        FqName childSafe;
        FqName childSafe2;
        FqName child;
        FqName child2;
        FqName childSafe3;
        FqName child3;
        FqName child4;
        FqName child5;
        FqNameUnsafe fqNameUnsafe = KotlinBuiltIns.FQ_NAMES._enum;
        Intrinsics.checkExpressionValueIsNotNull(fqNameUnsafe, "BUILTIN_NAMES._enum");
        childSafe = SpecialBuiltinMembers.childSafe(fqNameUnsafe, "name");
        FqNameUnsafe fqNameUnsafe2 = KotlinBuiltIns.FQ_NAMES._enum;
        Intrinsics.checkExpressionValueIsNotNull(fqNameUnsafe2, "BUILTIN_NAMES._enum");
        childSafe2 = SpecialBuiltinMembers.childSafe(fqNameUnsafe2, "ordinal");
        FqName fqName = KotlinBuiltIns.FQ_NAMES.collection;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "BUILTIN_NAMES.collection");
        child = SpecialBuiltinMembers.child(fqName, "size");
        FqName fqName2 = KotlinBuiltIns.FQ_NAMES.map;
        Intrinsics.checkExpressionValueIsNotNull(fqName2, "BUILTIN_NAMES.map");
        child2 = SpecialBuiltinMembers.child(fqName2, "size");
        FqNameUnsafe fqNameUnsafe3 = KotlinBuiltIns.FQ_NAMES.charSequence;
        Intrinsics.checkExpressionValueIsNotNull(fqNameUnsafe3, "BUILTIN_NAMES.charSequence");
        childSafe3 = SpecialBuiltinMembers.childSafe(fqNameUnsafe3, org.simpleframework.xml.strategy.Name.LENGTH);
        FqName fqName3 = KotlinBuiltIns.FQ_NAMES.map;
        Intrinsics.checkExpressionValueIsNotNull(fqName3, "BUILTIN_NAMES.map");
        child3 = SpecialBuiltinMembers.child(fqName3, "keys");
        FqName fqName4 = KotlinBuiltIns.FQ_NAMES.map;
        Intrinsics.checkExpressionValueIsNotNull(fqName4, "BUILTIN_NAMES.map");
        child4 = SpecialBuiltinMembers.child(fqName4, "values");
        FqName fqName5 = KotlinBuiltIns.FQ_NAMES.map;
        Intrinsics.checkExpressionValueIsNotNull(fqName5, "BUILTIN_NAMES.map");
        child5 = SpecialBuiltinMembers.child(fqName5, "entries");
        Map<FqName, Name> mapOf = MapsKt.mapOf(TuplesKt.m3968to(childSafe, Name.identifier("name")), TuplesKt.m3968to(childSafe2, Name.identifier("ordinal")), TuplesKt.m3968to(child, Name.identifier("size")), TuplesKt.m3968to(child2, Name.identifier("size")), TuplesKt.m3968to(childSafe3, Name.identifier(org.simpleframework.xml.strategy.Name.LENGTH)), TuplesKt.m3968to(child3, Name.identifier("keySet")), TuplesKt.m3968to(child4, Name.identifier("values")), TuplesKt.m3968to(child5, Name.identifier("entrySet")));
        PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP = mapOf;
        Set<Map.Entry<FqName, Name>> entrySet = mapOf.entrySet();
        ArrayList<Pair> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(entrySet, 10));
        Iterator<T> it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            arrayList.add(new Pair(((FqName) entry.getKey()).shortName(), entry.getValue()));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Pair pair : arrayList) {
            Name name = (Name) pair.getSecond();
            Object obj = linkedHashMap.get(name);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(name, obj);
            }
            ((List) obj).add((Name) pair.getFirst());
        }
        GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP = linkedHashMap;
        Set<FqName> keySet = PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP.keySet();
        SPECIAL_FQ_NAMES = keySet;
        Set<FqName> set = keySet;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
        Iterator<T> it2 = set.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((FqName) it2.next()).shortName());
        }
        SPECIAL_SHORT_NAMES = CollectionsKt.toSet(arrayList2);
    }

    private BuiltinSpecialProperties() {
    }

    public final Set<Name> getSPECIAL_SHORT_NAMES$descriptors_jvm() {
        return SPECIAL_SHORT_NAMES;
    }

    public final boolean hasBuiltinSpecialPropertyFqName(CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "callableMemberDescriptor");
        if (SPECIAL_SHORT_NAMES.contains(callableMemberDescriptor.getName())) {
            return hasBuiltinSpecialPropertyFqNameImpl(callableMemberDescriptor);
        }
        return false;
    }

    private final boolean hasBuiltinSpecialPropertyFqNameImpl(CallableMemberDescriptor callableMemberDescriptor) {
        CallableMemberDescriptor callableMemberDescriptor2 = callableMemberDescriptor;
        if (CollectionsKt.contains(SPECIAL_FQ_NAMES, DescriptorUtilsKt.fqNameOrNull(callableMemberDescriptor2)) && callableMemberDescriptor.getValueParameters().isEmpty()) {
            return true;
        }
        if (!KotlinBuiltIns.isBuiltIn(callableMemberDescriptor2)) {
            return false;
        }
        Collection<? extends CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
        Intrinsics.checkExpressionValueIsNotNull(overriddenDescriptors, "overriddenDescriptors");
        Collection<? extends CallableMemberDescriptor> collection = overriddenDescriptors;
        if (!(collection instanceof Collection) || !collection.isEmpty()) {
            for (CallableMemberDescriptor it : collection) {
                BuiltinSpecialProperties builtinSpecialProperties = INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (builtinSpecialProperties.hasBuiltinSpecialPropertyFqName(it)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final List<Name> getPropertyNameCandidatesBySpecialGetterName(Name name1) {
        Intrinsics.checkParameterIsNotNull(name1, "name1");
        List<Name> list = GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP.get(name1);
        return list != null ? list : CollectionsKt.emptyList();
    }

    public final String getBuiltinSpecialPropertyGetterName(CallableMemberDescriptor getBuiltinSpecialPropertyGetterName) {
        Name name;
        Intrinsics.checkParameterIsNotNull(getBuiltinSpecialPropertyGetterName, "$this$getBuiltinSpecialPropertyGetterName");
        boolean isBuiltIn = KotlinBuiltIns.isBuiltIn(getBuiltinSpecialPropertyGetterName);
        if (!_Assertions.ENABLED || isBuiltIn) {
            CallableMemberDescriptor firstOverridden$default = DescriptorUtilsKt.firstOverridden$default(DescriptorUtilsKt.getPropertyIfAccessor(getBuiltinSpecialPropertyGetterName), false, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.BuiltinSpecialProperties$getBuiltinSpecialPropertyGetterName$descriptor$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                    return Boolean.valueOf(invoke2(callableMemberDescriptor));
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(CallableMemberDescriptor it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return BuiltinSpecialProperties.INSTANCE.hasBuiltinSpecialPropertyFqName(it);
                }
            }, 1, null);
            if (firstOverridden$default == null || (name = PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP.get(DescriptorUtilsKt.getFqNameSafe(firstOverridden$default))) == null) {
                return null;
            }
            return name.asString();
        }
        throw new AssertionError("This method is defined only for builtin members, but " + getBuiltinSpecialPropertyGetterName + " found");
    }
}
