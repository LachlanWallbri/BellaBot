package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface TypeCheckingProcedureCallbacks {
    boolean assertEqualTypeConstructors(TypeConstructor typeConstructor, TypeConstructor typeConstructor2);

    boolean assertEqualTypes(KotlinType kotlinType, KotlinType kotlinType2, TypeCheckingProcedure typeCheckingProcedure);

    boolean assertSubtype(KotlinType kotlinType, KotlinType kotlinType2, TypeCheckingProcedure typeCheckingProcedure);

    boolean capture(KotlinType kotlinType, TypeProjection typeProjection);

    boolean noCorrespondingSupertype(KotlinType kotlinType, KotlinType kotlinType2);
}
