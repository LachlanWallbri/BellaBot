package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

/* compiled from: SpecialTypes.kt */
/* loaded from: classes8.dex */
final class LazyWrappedType$refine$1 extends Lambda implements Function0<KotlinType> {
    final /* synthetic */ KotlinTypeRefiner $kotlinTypeRefiner;
    final /* synthetic */ LazyWrappedType this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LazyWrappedType$refine$1(LazyWrappedType lazyWrappedType, KotlinTypeRefiner kotlinTypeRefiner) {
        super(0);
        this.this$0 = lazyWrappedType;
        this.$kotlinTypeRefiner = kotlinTypeRefiner;
    }

    @Override // kotlin.jvm.functions.Function0
    public final KotlinType invoke() {
        return this.$kotlinTypeRefiner.refineType((KotlinType) LazyWrappedType.access$getComputation$p(this.this$0).invoke());
    }
}
