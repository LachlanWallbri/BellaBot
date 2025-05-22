package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: JavaDescriptorUtil.kt */
/* loaded from: classes2.dex */
public final class JavaDescriptorUtilKt {
    public static final boolean isJavaField(PropertyDescriptor isJavaField) {
        Intrinsics.checkParameterIsNotNull(isJavaField, "$this$isJavaField");
        return isJavaField.getGetter() == null;
    }
}
