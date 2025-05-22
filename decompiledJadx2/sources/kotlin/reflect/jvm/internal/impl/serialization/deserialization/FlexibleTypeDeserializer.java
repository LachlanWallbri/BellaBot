package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: FlexibleTypeDeserializer.kt */
/* loaded from: classes2.dex */
public interface FlexibleTypeDeserializer {
    KotlinType create(ProtoBuf.Type type, String str, SimpleType simpleType, SimpleType simpleType2);

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: FlexibleTypeDeserializer.kt */
    /* loaded from: classes2.dex */
    public static final class ThrowException implements FlexibleTypeDeserializer {
        public static final ThrowException INSTANCE = new ThrowException();

        private ThrowException() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer
        public KotlinType create(ProtoBuf.Type proto, String flexibleId, SimpleType lowerBound, SimpleType upperBound) {
            Intrinsics.checkParameterIsNotNull(proto, "proto");
            Intrinsics.checkParameterIsNotNull(flexibleId, "flexibleId");
            Intrinsics.checkParameterIsNotNull(lowerBound, "lowerBound");
            Intrinsics.checkParameterIsNotNull(upperBound, "upperBound");
            throw new IllegalArgumentException("This method should not be used.");
        }
    }
}
