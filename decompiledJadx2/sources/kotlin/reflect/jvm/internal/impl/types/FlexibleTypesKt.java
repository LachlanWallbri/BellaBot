package kotlin.reflect.jvm.internal.impl.types;

import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: flexibleTypes.kt */
/* loaded from: classes2.dex */
public final class FlexibleTypesKt {
    public static final boolean isFlexible(KotlinType isFlexible) {
        Intrinsics.checkParameterIsNotNull(isFlexible, "$this$isFlexible");
        return isFlexible.unwrap() instanceof FlexibleType;
    }

    public static final FlexibleType asFlexibleType(KotlinType asFlexibleType) {
        Intrinsics.checkParameterIsNotNull(asFlexibleType, "$this$asFlexibleType");
        UnwrappedType unwrap = asFlexibleType.unwrap();
        if (unwrap != null) {
            return (FlexibleType) unwrap;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.FlexibleType");
    }

    public static final SimpleType lowerIfFlexible(KotlinType lowerIfFlexible) {
        Intrinsics.checkParameterIsNotNull(lowerIfFlexible, "$this$lowerIfFlexible");
        UnwrappedType unwrap = lowerIfFlexible.unwrap();
        if (unwrap instanceof FlexibleType) {
            return ((FlexibleType) unwrap).getLowerBound();
        }
        if (unwrap instanceof SimpleType) {
            return (SimpleType) unwrap;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final SimpleType upperIfFlexible(KotlinType upperIfFlexible) {
        Intrinsics.checkParameterIsNotNull(upperIfFlexible, "$this$upperIfFlexible");
        UnwrappedType unwrap = upperIfFlexible.unwrap();
        if (unwrap instanceof FlexibleType) {
            return ((FlexibleType) unwrap).getUpperBound();
        }
        if (unwrap instanceof SimpleType) {
            return (SimpleType) unwrap;
        }
        throw new NoWhenBranchMatchedException();
    }
}
