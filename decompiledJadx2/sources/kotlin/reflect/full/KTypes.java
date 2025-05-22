package kotlin.reflect.full;

import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: KTypes.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u001a\u0014\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0001H\u0007¨\u0006\u0007"}, m3961d2 = {"isSubtypeOf", "", "Lkotlin/reflect/KType;", "other", "isSupertypeOf", "withNullability", "nullable", "kotlin-reflection"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class KTypes {
    public static final KType withNullability(final KType withNullability, boolean z) {
        Intrinsics.checkParameterIsNotNull(withNullability, "$this$withNullability");
        if (withNullability.getIsMarkedNullable()) {
            if (z) {
                return withNullability;
            }
            KotlinType makeNotNullable = TypeUtils.makeNotNullable(((KTypeImpl) withNullability).getType());
            Intrinsics.checkExpressionValueIsNotNull(makeNotNullable, "TypeUtils.makeNotNullabl…(this as KTypeImpl).type)");
            return new KTypeImpl(makeNotNullable, new Function0<Type>() { // from class: kotlin.reflect.full.KTypes$withNullability$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Type invoke() {
                    return ((KTypeImpl) KType.this).getJavaType$kotlin_reflection();
                }
            });
        }
        KotlinType type = ((KTypeImpl) withNullability).getType();
        if (FlexibleTypesKt.isFlexible(type)) {
            KotlinType makeNullableAsSpecified = TypeUtils.makeNullableAsSpecified(type, z);
            Intrinsics.checkExpressionValueIsNotNull(makeNullableAsSpecified, "TypeUtils.makeNullableAs…ied(kotlinType, nullable)");
            return new KTypeImpl(makeNullableAsSpecified, new Function0<Type>() { // from class: kotlin.reflect.full.KTypes$withNullability$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Type invoke() {
                    return ((KTypeImpl) KType.this).getJavaType$kotlin_reflection();
                }
            });
        }
        if (!z) {
            return withNullability;
        }
        KotlinType makeNullable = TypeUtils.makeNullable(type);
        Intrinsics.checkExpressionValueIsNotNull(makeNullable, "TypeUtils.makeNullable(kotlinType)");
        return new KTypeImpl(makeNullable, new Function0<Type>() { // from class: kotlin.reflect.full.KTypes$withNullability$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Type invoke() {
                return ((KTypeImpl) KType.this).getJavaType$kotlin_reflection();
            }
        });
    }

    public static final boolean isSubtypeOf(KType isSubtypeOf, KType other) {
        Intrinsics.checkParameterIsNotNull(isSubtypeOf, "$this$isSubtypeOf");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return TypeUtilsKt.isSubtypeOf(((KTypeImpl) isSubtypeOf).getType(), ((KTypeImpl) other).getType());
    }

    public static final boolean isSupertypeOf(KType isSupertypeOf, KType other) {
        Intrinsics.checkParameterIsNotNull(isSupertypeOf, "$this$isSupertypeOf");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return isSubtypeOf(other, isSupertypeOf);
    }
}
