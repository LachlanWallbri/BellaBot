package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: NameResolverUtil.kt */
/* loaded from: classes2.dex */
public final class NameResolverUtilKt {
    public static final ClassId getClassId(NameResolver getClassId, int i) {
        Intrinsics.checkParameterIsNotNull(getClassId, "$this$getClassId");
        ClassId fromString = ClassId.fromString(getClassId.getQualifiedClassName(i), getClassId.isLocalClassName(i));
        Intrinsics.checkExpressionValueIsNotNull(fromString, "ClassId.fromString(getQu… isLocalClassName(index))");
        return fromString;
    }

    public static final Name getName(NameResolver getName, int i) {
        Intrinsics.checkParameterIsNotNull(getName, "$this$getName");
        Name guessByFirstCharacter = Name.guessByFirstCharacter(getName.getString(i));
        Intrinsics.checkExpressionValueIsNotNull(guessByFirstCharacter, "Name.guessByFirstCharacter(getString(index))");
        return guessByFirstCharacter;
    }
}
