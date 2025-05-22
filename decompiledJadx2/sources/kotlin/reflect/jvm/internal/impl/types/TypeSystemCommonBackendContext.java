package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;

/* compiled from: TypeSystemCommonBackendContext.kt */
/* loaded from: classes8.dex */
public interface TypeSystemCommonBackendContext extends TypeSystemContext {
    FqNameUnsafe getClassFqNameUnsafe(TypeConstructorMarker typeConstructorMarker);

    PrimitiveType getPrimitiveArrayType(TypeConstructorMarker typeConstructorMarker);

    PrimitiveType getPrimitiveType(TypeConstructorMarker typeConstructorMarker);

    KotlinTypeMarker getRepresentativeUpperBound(TypeParameterMarker typeParameterMarker);

    KotlinTypeMarker getSubstitutedUnderlyingType(KotlinTypeMarker kotlinTypeMarker);

    TypeParameterMarker getTypeParameterClassifier(TypeConstructorMarker typeConstructorMarker);

    boolean hasAnnotation(KotlinTypeMarker kotlinTypeMarker, FqName fqName);

    boolean isInlineClass(TypeConstructorMarker typeConstructorMarker);

    boolean isMarkedNullable(KotlinTypeMarker kotlinTypeMarker);

    boolean isUnderKotlinPackage(TypeConstructorMarker typeConstructorMarker);

    KotlinTypeMarker makeNullable(KotlinTypeMarker kotlinTypeMarker);

    /* compiled from: TypeSystemCommonBackendContext.kt */
    /* loaded from: classes8.dex */
    public static final class DefaultImpls {
        public static boolean isMarkedNullable(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker isMarkedNullable) {
            Intrinsics.checkParameterIsNotNull(isMarkedNullable, "$this$isMarkedNullable");
            return (isMarkedNullable instanceof SimpleTypeMarker) && typeSystemCommonBackendContext.isMarkedNullable((SimpleTypeMarker) isMarkedNullable);
        }

        public static KotlinTypeMarker makeNullable(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker makeNullable) {
            SimpleTypeMarker withNullability;
            Intrinsics.checkParameterIsNotNull(makeNullable, "$this$makeNullable");
            SimpleTypeMarker asSimpleType = typeSystemCommonBackendContext.asSimpleType(makeNullable);
            return (asSimpleType == null || (withNullability = typeSystemCommonBackendContext.withNullability(asSimpleType, true)) == null) ? makeNullable : withNullability;
        }
    }
}
