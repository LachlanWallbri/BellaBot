package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;
import kotlin.reflect.jvm.internal.KPropertyImpl;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: KProperties.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0002H\u0007\u001a/\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0003*\u0010\u0012\u0004\u0012\u0002H\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u0002H\u0003H\u0007¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, m3961d2 = {"getExtensionDelegate", "", "Lkotlin/reflect/KProperty1;", "D", "Lkotlin/reflect/KProperty2;", "receiver", "(Lkotlin/reflect/KProperty2;Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflection"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class KProperties {
    public static final Object getExtensionDelegate(KProperty1<?, ?> getExtensionDelegate) {
        Intrinsics.checkParameterIsNotNull(getExtensionDelegate, "$this$getExtensionDelegate");
        return getExtensionDelegate.getDelegate(KPropertyImpl.INSTANCE.getEXTENSION_PROPERTY_DELEGATE());
    }

    public static final <D> Object getExtensionDelegate(KProperty2<D, ?, ?> getExtensionDelegate, D d) {
        Intrinsics.checkParameterIsNotNull(getExtensionDelegate, "$this$getExtensionDelegate");
        return getExtensionDelegate.getDelegate(d, KPropertyImpl.INSTANCE.getEXTENSION_PROPERTY_DELEGATE());
    }
}
