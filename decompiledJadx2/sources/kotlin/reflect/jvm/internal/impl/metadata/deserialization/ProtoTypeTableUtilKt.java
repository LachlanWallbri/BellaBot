package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: protoTypeTableUtil.kt */
/* loaded from: classes2.dex */
public final class ProtoTypeTableUtilKt {
    public static final List<ProtoBuf.Type> supertypes(ProtoBuf.Class supertypes, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(supertypes, "$this$supertypes");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        List<ProtoBuf.Type> supertypeList = supertypes.getSupertypeList();
        if (!(!supertypeList.isEmpty())) {
            supertypeList = null;
        }
        if (supertypeList != null) {
            return supertypeList;
        }
        List<Integer> supertypeIdList = supertypes.getSupertypeIdList();
        Intrinsics.checkExpressionValueIsNotNull(supertypeIdList, "supertypeIdList");
        List<Integer> list = supertypeIdList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Integer it : list) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            arrayList.add(typeTable.get(it.intValue()));
        }
        return arrayList;
    }

    public static final ProtoBuf.Type type(ProtoBuf.Type.Argument type, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(type, "$this$type");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (type.hasType()) {
            return type.getType();
        }
        if (type.hasTypeId()) {
            return typeTable.get(type.getTypeId());
        }
        return null;
    }

    public static final ProtoBuf.Type flexibleUpperBound(ProtoBuf.Type flexibleUpperBound, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(flexibleUpperBound, "$this$flexibleUpperBound");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (flexibleUpperBound.hasFlexibleUpperBound()) {
            return flexibleUpperBound.getFlexibleUpperBound();
        }
        if (flexibleUpperBound.hasFlexibleUpperBoundId()) {
            return typeTable.get(flexibleUpperBound.getFlexibleUpperBoundId());
        }
        return null;
    }

    public static final List<ProtoBuf.Type> upperBounds(ProtoBuf.TypeParameter upperBounds, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(upperBounds, "$this$upperBounds");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        List<ProtoBuf.Type> upperBoundList = upperBounds.getUpperBoundList();
        if (!(!upperBoundList.isEmpty())) {
            upperBoundList = null;
        }
        if (upperBoundList != null) {
            return upperBoundList;
        }
        List<Integer> upperBoundIdList = upperBounds.getUpperBoundIdList();
        Intrinsics.checkExpressionValueIsNotNull(upperBoundIdList, "upperBoundIdList");
        List<Integer> list = upperBoundIdList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Integer it : list) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            arrayList.add(typeTable.get(it.intValue()));
        }
        return arrayList;
    }

    public static final ProtoBuf.Type returnType(ProtoBuf.Function returnType, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(returnType, "$this$returnType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (returnType.hasReturnType()) {
            ProtoBuf.Type returnType2 = returnType.getReturnType();
            Intrinsics.checkExpressionValueIsNotNull(returnType2, "returnType");
            return returnType2;
        }
        if (returnType.hasReturnTypeId()) {
            return typeTable.get(returnType.getReturnTypeId());
        }
        throw new IllegalStateException("No returnType in ProtoBuf.Function".toString());
    }

    public static final boolean hasReceiver(ProtoBuf.Function hasReceiver) {
        Intrinsics.checkParameterIsNotNull(hasReceiver, "$this$hasReceiver");
        return hasReceiver.hasReceiverType() || hasReceiver.hasReceiverTypeId();
    }

    public static final ProtoBuf.Type receiverType(ProtoBuf.Function receiverType, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiverType, "$this$receiverType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (receiverType.hasReceiverType()) {
            return receiverType.getReceiverType();
        }
        if (receiverType.hasReceiverTypeId()) {
            return typeTable.get(receiverType.getReceiverTypeId());
        }
        return null;
    }

    public static final ProtoBuf.Type returnType(ProtoBuf.Property returnType, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(returnType, "$this$returnType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (returnType.hasReturnType()) {
            ProtoBuf.Type returnType2 = returnType.getReturnType();
            Intrinsics.checkExpressionValueIsNotNull(returnType2, "returnType");
            return returnType2;
        }
        if (returnType.hasReturnTypeId()) {
            return typeTable.get(returnType.getReturnTypeId());
        }
        throw new IllegalStateException("No returnType in ProtoBuf.Property".toString());
    }

    public static final boolean hasReceiver(ProtoBuf.Property hasReceiver) {
        Intrinsics.checkParameterIsNotNull(hasReceiver, "$this$hasReceiver");
        return hasReceiver.hasReceiverType() || hasReceiver.hasReceiverTypeId();
    }

    public static final ProtoBuf.Type receiverType(ProtoBuf.Property receiverType, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiverType, "$this$receiverType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (receiverType.hasReceiverType()) {
            return receiverType.getReceiverType();
        }
        if (receiverType.hasReceiverTypeId()) {
            return typeTable.get(receiverType.getReceiverTypeId());
        }
        return null;
    }

    public static final ProtoBuf.Type type(ProtoBuf.ValueParameter type, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(type, "$this$type");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (type.hasType()) {
            ProtoBuf.Type type2 = type.getType();
            Intrinsics.checkExpressionValueIsNotNull(type2, "type");
            return type2;
        }
        if (type.hasTypeId()) {
            return typeTable.get(type.getTypeId());
        }
        throw new IllegalStateException("No type in ProtoBuf.ValueParameter".toString());
    }

    public static final ProtoBuf.Type varargElementType(ProtoBuf.ValueParameter varargElementType, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(varargElementType, "$this$varargElementType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (varargElementType.hasVarargElementType()) {
            return varargElementType.getVarargElementType();
        }
        if (varargElementType.hasVarargElementTypeId()) {
            return typeTable.get(varargElementType.getVarargElementTypeId());
        }
        return null;
    }

    public static final ProtoBuf.Type outerType(ProtoBuf.Type outerType, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(outerType, "$this$outerType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (outerType.hasOuterType()) {
            return outerType.getOuterType();
        }
        if (outerType.hasOuterTypeId()) {
            return typeTable.get(outerType.getOuterTypeId());
        }
        return null;
    }

    public static final ProtoBuf.Type abbreviatedType(ProtoBuf.Type abbreviatedType, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(abbreviatedType, "$this$abbreviatedType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (abbreviatedType.hasAbbreviatedType()) {
            return abbreviatedType.getAbbreviatedType();
        }
        if (abbreviatedType.hasAbbreviatedTypeId()) {
            return typeTable.get(abbreviatedType.getAbbreviatedTypeId());
        }
        return null;
    }

    public static final ProtoBuf.Type underlyingType(ProtoBuf.TypeAlias underlyingType, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(underlyingType, "$this$underlyingType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (underlyingType.hasUnderlyingType()) {
            ProtoBuf.Type underlyingType2 = underlyingType.getUnderlyingType();
            Intrinsics.checkExpressionValueIsNotNull(underlyingType2, "underlyingType");
            return underlyingType2;
        }
        if (underlyingType.hasUnderlyingTypeId()) {
            return typeTable.get(underlyingType.getUnderlyingTypeId());
        }
        throw new IllegalStateException("No underlyingType in ProtoBuf.TypeAlias".toString());
    }

    public static final ProtoBuf.Type expandedType(ProtoBuf.TypeAlias expandedType, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(expandedType, "$this$expandedType");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (expandedType.hasExpandedType()) {
            ProtoBuf.Type expandedType2 = expandedType.getExpandedType();
            Intrinsics.checkExpressionValueIsNotNull(expandedType2, "expandedType");
            return expandedType2;
        }
        if (expandedType.hasExpandedTypeId()) {
            return typeTable.get(expandedType.getExpandedTypeId());
        }
        throw new IllegalStateException("No expandedType in ProtoBuf.TypeAlias".toString());
    }
}
