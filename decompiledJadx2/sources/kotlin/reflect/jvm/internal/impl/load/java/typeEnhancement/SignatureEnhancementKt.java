package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: signatureEnhancement.kt */
/* loaded from: classes2.dex */
public final class SignatureEnhancementKt {
    public static final JavaTypeQualifiers createJavaTypeQualifiers(NullabilityQualifier nullabilityQualifier, MutabilityQualifier mutabilityQualifier, boolean z, boolean z2) {
        if (!z2 || nullabilityQualifier != NullabilityQualifier.NOT_NULL) {
            return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, false, z);
        }
        return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, true, z);
    }

    public static final <T> T select(Set<? extends T> select, T low, T high, T t, boolean z) {
        Set<? extends T> set;
        Intrinsics.checkParameterIsNotNull(select, "$this$select");
        Intrinsics.checkParameterIsNotNull(low, "low");
        Intrinsics.checkParameterIsNotNull(high, "high");
        if (z) {
            T t2 = select.contains(low) ? low : select.contains(high) ? high : null;
            if (Intrinsics.areEqual(t2, low) && Intrinsics.areEqual(t, high)) {
                return null;
            }
            return t != null ? t : t2;
        }
        if (t != null && (set = CollectionsKt.toSet(SetsKt.plus(select, t))) != null) {
            select = set;
        }
        return (T) CollectionsKt.singleOrNull(select);
    }

    public static final NullabilityQualifier select(Set<? extends NullabilityQualifier> select, NullabilityQualifier nullabilityQualifier, boolean z) {
        Intrinsics.checkParameterIsNotNull(select, "$this$select");
        if (nullabilityQualifier == NullabilityQualifier.FORCE_FLEXIBILITY) {
            return NullabilityQualifier.FORCE_FLEXIBILITY;
        }
        return (NullabilityQualifier) select(select, NullabilityQualifier.NOT_NULL, NullabilityQualifier.NULLABLE, nullabilityQualifier, z);
    }
}
