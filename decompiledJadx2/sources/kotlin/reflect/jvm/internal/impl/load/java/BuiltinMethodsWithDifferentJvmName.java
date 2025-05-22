package kotlin.reflect.jvm.internal.impl.load.java;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: specialBuiltinMembers.kt */
/* loaded from: classes2.dex */
public final class BuiltinMethodsWithDifferentJvmName {
    public static final BuiltinMethodsWithDifferentJvmName INSTANCE = new BuiltinMethodsWithDifferentJvmName();
    private static final Map<Name, List<Name>> JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP;
    private static final Map<NameAndSignature, Name> NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP;
    private static final List<Name> ORIGINAL_SHORT_NAMES;
    private static final NameAndSignature REMOVE_AT_NAME_AND_SIGNATURE;
    private static final Map<String, Name> SIGNATURE_TO_JVM_REPRESENTATION_NAME;

    static {
        NameAndSignature method;
        NameAndSignature method2;
        NameAndSignature method3;
        NameAndSignature method4;
        NameAndSignature method5;
        NameAndSignature method6;
        NameAndSignature method7;
        NameAndSignature method8;
        String desc = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc, "JvmPrimitiveType.INT.desc");
        method = SpecialBuiltinMembers.method("java/util/List", "removeAt", desc, "Ljava/lang/Object;");
        REMOVE_AT_NAME_AND_SIGNATURE = method;
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        String javaLang = signatureBuildingComponents.javaLang("Number");
        String desc2 = JvmPrimitiveType.BYTE.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc2, "JvmPrimitiveType.BYTE.desc");
        method2 = SpecialBuiltinMembers.method(javaLang, "toByte", "", desc2);
        String javaLang2 = signatureBuildingComponents.javaLang("Number");
        String desc3 = JvmPrimitiveType.SHORT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc3, "JvmPrimitiveType.SHORT.desc");
        method3 = SpecialBuiltinMembers.method(javaLang2, "toShort", "", desc3);
        String javaLang3 = signatureBuildingComponents.javaLang("Number");
        String desc4 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc4, "JvmPrimitiveType.INT.desc");
        method4 = SpecialBuiltinMembers.method(javaLang3, "toInt", "", desc4);
        String javaLang4 = signatureBuildingComponents.javaLang("Number");
        String desc5 = JvmPrimitiveType.LONG.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc5, "JvmPrimitiveType.LONG.desc");
        method5 = SpecialBuiltinMembers.method(javaLang4, "toLong", "", desc5);
        String javaLang5 = signatureBuildingComponents.javaLang("Number");
        String desc6 = JvmPrimitiveType.FLOAT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc6, "JvmPrimitiveType.FLOAT.desc");
        method6 = SpecialBuiltinMembers.method(javaLang5, "toFloat", "", desc6);
        String javaLang6 = signatureBuildingComponents.javaLang("Number");
        String desc7 = JvmPrimitiveType.DOUBLE.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc7, "JvmPrimitiveType.DOUBLE.desc");
        method7 = SpecialBuiltinMembers.method(javaLang6, "toDouble", "", desc7);
        String javaLang7 = signatureBuildingComponents.javaLang("CharSequence");
        String desc8 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc8, "JvmPrimitiveType.INT.desc");
        String desc9 = JvmPrimitiveType.CHAR.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc9, "JvmPrimitiveType.CHAR.desc");
        method8 = SpecialBuiltinMembers.method(javaLang7, TmpConstant.PROPERTY_IDENTIFIER_GET, desc8, desc9);
        Map<NameAndSignature, Name> mapOf = MapsKt.mapOf(TuplesKt.m3968to(method2, Name.identifier("byteValue")), TuplesKt.m3968to(method3, Name.identifier("shortValue")), TuplesKt.m3968to(method4, Name.identifier("intValue")), TuplesKt.m3968to(method5, Name.identifier("longValue")), TuplesKt.m3968to(method6, Name.identifier("floatValue")), TuplesKt.m3968to(method7, Name.identifier("doubleValue")), TuplesKt.m3968to(REMOVE_AT_NAME_AND_SIGNATURE, Name.identifier("remove")), TuplesKt.m3968to(method8, Name.identifier("charAt")));
        NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP = mapOf;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(mapOf.size()));
        Iterator<T> it = mapOf.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(((NameAndSignature) entry.getKey()).getSignature(), entry.getValue());
        }
        SIGNATURE_TO_JVM_REPRESENTATION_NAME = linkedHashMap;
        Set<NameAndSignature> keySet = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP.keySet();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(keySet, 10));
        Iterator<T> it2 = keySet.iterator();
        while (it2.hasNext()) {
            arrayList.add(((NameAndSignature) it2.next()).getName());
        }
        ORIGINAL_SHORT_NAMES = arrayList;
        Set<Map.Entry<NameAndSignature, Name>> entrySet = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP.entrySet();
        ArrayList<Pair> arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(entrySet, 10));
        Iterator<T> it3 = entrySet.iterator();
        while (it3.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it3.next();
            arrayList2.add(new Pair(((NameAndSignature) entry2.getKey()).getName(), entry2.getValue()));
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Pair pair : arrayList2) {
            Name name = (Name) pair.getSecond();
            Object obj = linkedHashMap2.get(name);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap2.put(name, obj);
            }
            ((List) obj).add((Name) pair.getFirst());
        }
        JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP = linkedHashMap2;
    }

    private BuiltinMethodsWithDifferentJvmName() {
    }

    public final List<Name> getORIGINAL_SHORT_NAMES() {
        return ORIGINAL_SHORT_NAMES;
    }

    public final boolean getSameAsRenamedInJvmBuiltin(Name sameAsRenamedInJvmBuiltin) {
        Intrinsics.checkParameterIsNotNull(sameAsRenamedInJvmBuiltin, "$this$sameAsRenamedInJvmBuiltin");
        return ORIGINAL_SHORT_NAMES.contains(sameAsRenamedInJvmBuiltin);
    }

    public final Name getJvmName(SimpleFunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        Map<String, Name> map = SIGNATURE_TO_JVM_REPRESENTATION_NAME;
        String computeJvmSignature = MethodSignatureMappingKt.computeJvmSignature(functionDescriptor);
        if (computeJvmSignature != null) {
            return map.get(computeJvmSignature);
        }
        return null;
    }

    public final boolean isBuiltinFunctionWithDifferentNameInJvm(final SimpleFunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        return KotlinBuiltIns.isBuiltIn(functionDescriptor) && DescriptorUtilsKt.firstOverridden$default(functionDescriptor, false, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithDifferentJvmName$isBuiltinFunctionWithDifferentNameInJvm$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                return Boolean.valueOf(invoke2(callableMemberDescriptor));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(CallableMemberDescriptor it) {
                Map map;
                Intrinsics.checkParameterIsNotNull(it, "it");
                BuiltinMethodsWithDifferentJvmName builtinMethodsWithDifferentJvmName = BuiltinMethodsWithDifferentJvmName.INSTANCE;
                map = BuiltinMethodsWithDifferentJvmName.SIGNATURE_TO_JVM_REPRESENTATION_NAME;
                String computeJvmSignature = MethodSignatureMappingKt.computeJvmSignature(SimpleFunctionDescriptor.this);
                if (map != null) {
                    return map.containsKey(computeJvmSignature);
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, *>");
            }
        }, 1, null) != null;
    }

    public final List<Name> getBuiltinFunctionNamesByJvmName(Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        List<Name> list = JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP.get(name);
        return list != null ? list : CollectionsKt.emptyList();
    }

    public final boolean isRemoveAtByIndex(SimpleFunctionDescriptor isRemoveAtByIndex) {
        Intrinsics.checkParameterIsNotNull(isRemoveAtByIndex, "$this$isRemoveAtByIndex");
        return Intrinsics.areEqual(isRemoveAtByIndex.getName().asString(), "removeAt") && Intrinsics.areEqual(MethodSignatureMappingKt.computeJvmSignature(isRemoveAtByIndex), REMOVE_AT_NAME_AND_SIGNATURE.getSignature());
    }
}
