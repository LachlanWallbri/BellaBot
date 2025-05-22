package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: SupertypeLoopChecker.kt */
/* loaded from: classes2.dex */
public interface SupertypeLoopChecker {
    Collection<KotlinType> findLoopsInSupertypesAndDisconnect(TypeConstructor typeConstructor, Collection<? extends KotlinType> collection, Function1<? super TypeConstructor, ? extends Iterable<? extends KotlinType>> function1, Function1<? super KotlinType, Unit> function12);

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: SupertypeLoopChecker.kt */
    /* loaded from: classes2.dex */
    public static final class EMPTY implements SupertypeLoopChecker {
        public static final EMPTY INSTANCE = new EMPTY();

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker
        public Collection<KotlinType> findLoopsInSupertypesAndDisconnect(TypeConstructor currentTypeConstructor, Collection<? extends KotlinType> superTypes, Function1<? super TypeConstructor, ? extends Iterable<? extends KotlinType>> neighbors, Function1<? super KotlinType, Unit> reportLoop) {
            Intrinsics.checkParameterIsNotNull(currentTypeConstructor, "currentTypeConstructor");
            Intrinsics.checkParameterIsNotNull(superTypes, "superTypes");
            Intrinsics.checkParameterIsNotNull(neighbors, "neighbors");
            Intrinsics.checkParameterIsNotNull(reportLoop, "reportLoop");
            return superTypes;
        }

        private EMPTY() {
        }
    }
}
