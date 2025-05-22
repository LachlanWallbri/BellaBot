package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

/* compiled from: NewCapturedType.kt */
/* loaded from: classes8.dex */
final class NewCapturedTypeConstructor$refine$$inlined$let$lambda$1 extends Lambda implements Function0<List<? extends UnwrappedType>> {
    final /* synthetic */ KotlinTypeRefiner $kotlinTypeRefiner$inlined;
    final /* synthetic */ NewCapturedTypeConstructor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    NewCapturedTypeConstructor$refine$$inlined$let$lambda$1(NewCapturedTypeConstructor newCapturedTypeConstructor, KotlinTypeRefiner kotlinTypeRefiner) {
        super(0);
        this.this$0 = newCapturedTypeConstructor;
        this.$kotlinTypeRefiner$inlined = kotlinTypeRefiner;
    }

    @Override // kotlin.jvm.functions.Function0
    public final List<? extends UnwrappedType> invoke() {
        List<UnwrappedType> mo5461getSupertypes = this.this$0.mo5461getSupertypes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(mo5461getSupertypes, 10));
        Iterator<T> it = mo5461getSupertypes.iterator();
        while (it.hasNext()) {
            arrayList.add(((UnwrappedType) it.next()).refine(this.$kotlinTypeRefiner$inlined));
        }
        return arrayList;
    }
}
