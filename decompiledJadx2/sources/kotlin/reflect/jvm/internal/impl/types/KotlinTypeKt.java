package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: KotlinType.kt */
/* loaded from: classes2.dex */
public final class KotlinTypeKt {
    public static final boolean isNullable(KotlinType isNullable) {
        Intrinsics.checkParameterIsNotNull(isNullable, "$this$isNullable");
        return TypeUtils.isNullableType(isNullable);
    }

    public static final boolean isError(KotlinType isError) {
        Intrinsics.checkParameterIsNotNull(isError, "$this$isError");
        UnwrappedType unwrap = isError.unwrap();
        return (unwrap instanceof ErrorType) || ((unwrap instanceof FlexibleType) && (((FlexibleType) unwrap).getDelegate() instanceof ErrorType));
    }
}
