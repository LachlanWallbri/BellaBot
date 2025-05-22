package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

/* compiled from: NewCapturedType.kt */
/* loaded from: classes8.dex */
final class NewCapturedTypeConstructor$_supertypes$2 extends Lambda implements Function0<List<? extends UnwrappedType>> {
    final /* synthetic */ NewCapturedTypeConstructor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    NewCapturedTypeConstructor$_supertypes$2(NewCapturedTypeConstructor newCapturedTypeConstructor) {
        super(0);
        this.this$0 = newCapturedTypeConstructor;
    }

    @Override // kotlin.jvm.functions.Function0
    public final List<? extends UnwrappedType> invoke() {
        Function0 access$getSupertypesComputation$p = NewCapturedTypeConstructor.access$getSupertypesComputation$p(this.this$0);
        if (access$getSupertypesComputation$p != null) {
            return (List) access$getSupertypesComputation$p.invoke();
        }
        return null;
    }
}
