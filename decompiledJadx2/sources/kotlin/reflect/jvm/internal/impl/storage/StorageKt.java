package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: storage.kt */
/* loaded from: classes2.dex */
public final class StorageKt {
    public static final <T> T getValue(NotNullLazyValue<? extends T> getValue, Object obj, KProperty<?> p) {
        Intrinsics.checkParameterIsNotNull(getValue, "$this$getValue");
        Intrinsics.checkParameterIsNotNull(p, "p");
        return getValue.invoke();
    }

    public static final <T> T getValue(NullableLazyValue<? extends T> getValue, Object obj, KProperty<?> p) {
        Intrinsics.checkParameterIsNotNull(getValue, "$this$getValue");
        Intrinsics.checkParameterIsNotNull(p, "p");
        return getValue.invoke();
    }
}
