package com.google.common.collect;

import java.util.Map;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface BiMap<K, V> extends Map<K, V> {
    V forcePut(K k, V v);

    BiMap<V, K> inverse();

    V put(K k, V v);

    void putAll(Map<? extends K, ? extends V> map);

    @Override // com.google.common.collect.BiMap
    Set<V> values();
}
