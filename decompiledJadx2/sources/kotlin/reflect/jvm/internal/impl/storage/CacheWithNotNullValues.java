package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.jvm.functions.Function0;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: storage.kt */
/* loaded from: classes2.dex */
public interface CacheWithNotNullValues<K, V> {
    V computeIfAbsent(K k, Function0<? extends V> function0);
}
