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
/* compiled from: TypeTable.kt */
/* loaded from: classes2.dex */
public final class TypeTable {
    private final List<ProtoBuf.Type> types;

    public TypeTable(ProtoBuf.TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        ArrayList originalTypes = typeTable.getTypeList();
        if (typeTable.hasFirstNullable()) {
            int firstNullable = typeTable.getFirstNullable();
            List<ProtoBuf.Type> typeList = typeTable.getTypeList();
            Intrinsics.checkExpressionValueIsNotNull(typeList, "typeTable.typeList");
            List<ProtoBuf.Type> list = typeList;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            int i = 0;
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                ProtoBuf.Type type = (ProtoBuf.Type) obj;
                if (i >= firstNullable) {
                    type = type.toBuilder().setNullable(true).build();
                }
                arrayList.add(type);
                i = i2;
            }
            originalTypes = arrayList;
        } else {
            Intrinsics.checkExpressionValueIsNotNull(originalTypes, "originalTypes");
        }
        this.types = originalTypes;
    }

    public final ProtoBuf.Type get(int i) {
        return this.types.get(i);
    }
}
