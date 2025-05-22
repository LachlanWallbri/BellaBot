package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: utils.kt */
/* loaded from: classes2.dex */
public final class EnumEntry extends JavaDefaultValue {
    private final ClassDescriptor descriptor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnumEntry(ClassDescriptor descriptor) {
        super(null);
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        this.descriptor = descriptor;
    }
}
