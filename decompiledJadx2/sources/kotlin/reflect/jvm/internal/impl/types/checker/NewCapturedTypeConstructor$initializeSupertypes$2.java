package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

/* compiled from: NewCapturedType.kt */
/* loaded from: classes8.dex */
final class NewCapturedTypeConstructor$initializeSupertypes$2 extends Lambda implements Function0<List<? extends UnwrappedType>> {
    final /* synthetic */ List $supertypes;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    NewCapturedTypeConstructor$initializeSupertypes$2(List list) {
        super(0);
        this.$supertypes = list;
    }

    @Override // kotlin.jvm.functions.Function0
    public final List<? extends UnwrappedType> invoke() {
        return this.$supertypes;
    }
}
