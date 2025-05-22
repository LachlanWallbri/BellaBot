package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface KotlinTypeChecker {
    public static final KotlinTypeChecker DEFAULT = NewKotlinTypeChecker.INSTANCE;

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public interface TypeConstructorEquality {
        boolean equals(TypeConstructor typeConstructor, TypeConstructor typeConstructor2);
    }

    boolean equalTypes(KotlinType kotlinType, KotlinType kotlinType2);

    boolean isSubtypeOf(KotlinType kotlinType, KotlinType kotlinType2);
}
