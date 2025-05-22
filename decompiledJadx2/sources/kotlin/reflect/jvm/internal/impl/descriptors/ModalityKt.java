package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: Modality.kt */
/* loaded from: classes2.dex */
public final class ModalityKt {
    public static final boolean isFinalClass(ClassDescriptor isFinalClass) {
        Intrinsics.checkParameterIsNotNull(isFinalClass, "$this$isFinalClass");
        return isFinalClass.getModality() == Modality.FINAL && isFinalClass.getKind() != ClassKind.ENUM_CLASS;
    }
}
