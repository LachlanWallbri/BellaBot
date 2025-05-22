package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: dynamicTypes.kt */
/* loaded from: classes2.dex */
public final class DynamicTypesKt {
    public static final boolean isDynamic(KotlinType isDynamic) {
        Intrinsics.checkParameterIsNotNull(isDynamic, "$this$isDynamic");
        return isDynamic.unwrap() instanceof DynamicType;
    }
}
