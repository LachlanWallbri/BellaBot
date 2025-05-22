package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: ProtoBufUtil.kt */
/* loaded from: classes2.dex */
public final class ProtoBufUtilKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <M extends GeneratedMessageLite.ExtendableMessage<M>, T> T getExtensionOrNull(GeneratedMessageLite.ExtendableMessage<M> getExtensionOrNull, GeneratedMessageLite.GeneratedExtension<M, T> extension) {
        Intrinsics.checkParameterIsNotNull(getExtensionOrNull, "$this$getExtensionOrNull");
        Intrinsics.checkParameterIsNotNull(extension, "extension");
        if (getExtensionOrNull.hasExtension(extension)) {
            return (T) getExtensionOrNull.getExtension(extension);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <M extends GeneratedMessageLite.ExtendableMessage<M>, T> T getExtensionOrNull(GeneratedMessageLite.ExtendableMessage<M> getExtensionOrNull, GeneratedMessageLite.GeneratedExtension<M, List<T>> extension, int i) {
        Intrinsics.checkParameterIsNotNull(getExtensionOrNull, "$this$getExtensionOrNull");
        Intrinsics.checkParameterIsNotNull(extension, "extension");
        if (i < getExtensionOrNull.getExtensionCount(extension)) {
            return (T) getExtensionOrNull.getExtension(extension, i);
        }
        return null;
    }
}
