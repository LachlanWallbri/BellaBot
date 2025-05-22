package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.sequences.SequencesKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: TypeDeserializer.kt */
/* loaded from: classes2.dex */
public final class TypeDeserializer$typeConstructor$1 extends Lambda implements Function1<Integer, ClassDescriptor> {
    final /* synthetic */ ProtoBuf.Type $proto;
    final /* synthetic */ TypeDeserializer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TypeDeserializer$typeConstructor$1(TypeDeserializer typeDeserializer, ProtoBuf.Type type) {
        super(1);
        this.this$0 = typeDeserializer;
        this.$proto = type;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ ClassDescriptor invoke(Integer num) {
        return invoke(num.intValue());
    }

    public final ClassDescriptor invoke(int i) {
        DeserializationContext deserializationContext;
        DeserializationContext deserializationContext2;
        deserializationContext = this.this$0.f8782c;
        ClassId classId = NameResolverUtilKt.getClassId(deserializationContext.getNameResolver(), i);
        List<Integer> mutableList = SequencesKt.toMutableList(SequencesKt.map(SequencesKt.generateSequence(this.$proto, new Function1<ProtoBuf.Type, ProtoBuf.Type>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$typeConstructor$1$typeParametersCount$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ProtoBuf.Type invoke(ProtoBuf.Type it) {
                DeserializationContext deserializationContext3;
                Intrinsics.checkParameterIsNotNull(it, "it");
                deserializationContext3 = TypeDeserializer$typeConstructor$1.this.this$0.f8782c;
                return ProtoTypeTableUtilKt.outerType(it, deserializationContext3.getTypeTable());
            }
        }), new Function1<ProtoBuf.Type, Integer>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$typeConstructor$1$typeParametersCount$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(ProtoBuf.Type type) {
                return Integer.valueOf(invoke2(type));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final int invoke2(ProtoBuf.Type it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getArgumentCount();
            }
        }));
        int count = SequencesKt.count(SequencesKt.generateSequence(classId, TypeDeserializer$typeConstructor$1$classNestingLevel$1.INSTANCE));
        while (mutableList.size() < count) {
            mutableList.add(0);
        }
        deserializationContext2 = this.this$0.f8782c;
        return deserializationContext2.getComponents().getNotFoundClasses().getClass(classId, mutableList);
    }
}
