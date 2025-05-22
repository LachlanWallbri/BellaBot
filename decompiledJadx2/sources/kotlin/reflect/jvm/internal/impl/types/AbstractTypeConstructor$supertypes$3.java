package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: AbstractTypeConstructor.kt */
/* loaded from: classes2.dex */
public final class AbstractTypeConstructor$supertypes$3 extends Lambda implements Function1<AbstractTypeConstructor.Supertypes, Unit> {
    final /* synthetic */ AbstractTypeConstructor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractTypeConstructor$supertypes$3(AbstractTypeConstructor abstractTypeConstructor) {
        super(1);
        this.this$0 = abstractTypeConstructor;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(AbstractTypeConstructor.Supertypes supertypes) {
        invoke2(supertypes);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(AbstractTypeConstructor.Supertypes supertypes) {
        Intrinsics.checkParameterIsNotNull(supertypes, "supertypes");
        Collection<? extends KotlinType> findLoopsInSupertypesAndDisconnect = this.this$0.getSupertypeLoopChecker().findLoopsInSupertypesAndDisconnect(this.this$0, supertypes.getAllSupertypes(), new Function1<TypeConstructor, Collection<? extends KotlinType>>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3$resultWithoutCycles$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Collection<KotlinType> invoke(TypeConstructor it) {
                Collection<KotlinType> computeNeighbours;
                Intrinsics.checkParameterIsNotNull(it, "it");
                computeNeighbours = AbstractTypeConstructor$supertypes$3.this.this$0.computeNeighbours(it, false);
                return computeNeighbours;
            }
        }, new Function1<KotlinType, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3$resultWithoutCycles$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(KotlinType kotlinType) {
                invoke2(kotlinType);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(KotlinType it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                AbstractTypeConstructor$supertypes$3.this.this$0.reportSupertypeLoopError(it);
            }
        });
        if (findLoopsInSupertypesAndDisconnect.isEmpty()) {
            KotlinType defaultSupertypeIfEmpty = this.this$0.defaultSupertypeIfEmpty();
            Collection<? extends KotlinType> listOf = defaultSupertypeIfEmpty != null ? CollectionsKt.listOf(defaultSupertypeIfEmpty) : null;
            if (listOf == null) {
                listOf = CollectionsKt.emptyList();
            }
            findLoopsInSupertypesAndDisconnect = listOf;
        }
        this.this$0.getSupertypeLoopChecker().findLoopsInSupertypesAndDisconnect(this.this$0, findLoopsInSupertypesAndDisconnect, new Function1<TypeConstructor, Collection<? extends KotlinType>>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Collection<KotlinType> invoke(TypeConstructor it) {
                Collection<KotlinType> computeNeighbours;
                Intrinsics.checkParameterIsNotNull(it, "it");
                computeNeighbours = AbstractTypeConstructor$supertypes$3.this.this$0.computeNeighbours(it, true);
                return computeNeighbours;
            }
        }, new Function1<KotlinType, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(KotlinType kotlinType) {
                invoke2(kotlinType);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(KotlinType it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                AbstractTypeConstructor$supertypes$3.this.this$0.reportScopesLoopError(it);
            }
        });
        List<? extends KotlinType> list = (List) (findLoopsInSupertypesAndDisconnect instanceof List ? findLoopsInSupertypesAndDisconnect : null);
        if (list == null) {
            list = CollectionsKt.toList(findLoopsInSupertypesAndDisconnect);
        }
        supertypes.setSupertypesWithoutCycles(list);
    }
}
