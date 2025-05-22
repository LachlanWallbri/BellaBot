package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;

/* compiled from: TypeSubstitution.kt */
/* loaded from: classes2.dex */
public final class TypeSubstitutionKt {
    public static /* synthetic */ KotlinType replace$default(KotlinType kotlinType, List list, Annotations annotations, int i, Object obj) {
        if ((i & 1) != 0) {
            list = kotlinType.getArguments();
        }
        if ((i & 2) != 0) {
            annotations = kotlinType.getAnnotations();
        }
        return replace(kotlinType, (List<? extends TypeProjection>) list, annotations);
    }

    public static final KotlinType replace(KotlinType replace, List<? extends TypeProjection> newArguments, Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(replace, "$this$replace");
        Intrinsics.checkParameterIsNotNull(newArguments, "newArguments");
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        if ((newArguments.isEmpty() || newArguments == replace.getArguments()) && newAnnotations == replace.getAnnotations()) {
            return replace;
        }
        UnwrappedType unwrap = replace.unwrap();
        if (unwrap instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrap;
            return KotlinTypeFactory.flexibleType(replace(flexibleType.getLowerBound(), newArguments, newAnnotations), replace(flexibleType.getUpperBound(), newArguments, newAnnotations));
        }
        if (unwrap instanceof SimpleType) {
            return replace((SimpleType) unwrap, newArguments, newAnnotations);
        }
        throw new NoWhenBranchMatchedException();
    }

    public static /* synthetic */ SimpleType replace$default(SimpleType simpleType, List list, Annotations annotations, int i, Object obj) {
        if ((i & 1) != 0) {
            list = simpleType.getArguments();
        }
        if ((i & 2) != 0) {
            annotations = simpleType.getAnnotations();
        }
        return replace(simpleType, (List<? extends TypeProjection>) list, annotations);
    }

    public static final SimpleType replace(SimpleType replace, List<? extends TypeProjection> newArguments, Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(replace, "$this$replace");
        Intrinsics.checkParameterIsNotNull(newArguments, "newArguments");
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        if (newArguments.isEmpty() && newAnnotations == replace.getAnnotations()) {
            return replace;
        }
        if (newArguments.isEmpty()) {
            return replace.replaceAnnotations(newAnnotations);
        }
        return KotlinTypeFactory.simpleType(newAnnotations, replace.getConstructor(), newArguments, replace.isMarkedNullable());
    }

    public static final SimpleType asSimpleType(KotlinType asSimpleType) {
        Intrinsics.checkParameterIsNotNull(asSimpleType, "$this$asSimpleType");
        UnwrappedType unwrap = asSimpleType.unwrap();
        if (!(unwrap instanceof SimpleType)) {
            unwrap = null;
        }
        SimpleType simpleType = (SimpleType) unwrap;
        if (simpleType != null) {
            return simpleType;
        }
        throw new IllegalStateException(("This is should be simple type: " + asSimpleType).toString());
    }
}
