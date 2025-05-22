package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX WARN: Classes with same name are omitted:
  
 */
@DoNotMock("Use ImmutableMultimap, HashMultimap, or another implementation")
/* loaded from: classes3.dex */
public interface Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    void clear();

    boolean containsEntry(Object obj, Object obj2);

    boolean containsKey(Object obj);

    boolean containsValue(Object obj);

    Collection<Map.Entry<K, V>> entries();

    boolean equals(Object obj);

    Collection<V> get(K k);

    int hashCode();

    boolean isEmpty();

    Set<K> keySet();

    Multiset<K> keys();

    boolean put(K k, V v);

    boolean putAll(Multimap<? extends K, ? extends V> multimap);

    boolean putAll(K k, Iterable<? extends V> iterable);

    boolean remove(Object obj, Object obj2);

    Collection<V> removeAll(Object obj);

    Collection<V> replaceValues(K k, Iterable<? extends V> iterable);

    int size();

    Collection<V> values();

    default void forEach(final BiConsumer<? super K, ? super V> biConsumer) {
        Preconditions.checkNotNull(biConsumer);
        entries().forEach(new Consumer() { // from class: com.google.common.collect.-$$Lambda$Multimap$m1XizenlITg_-G0gDTmsYEs2Ra8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                biConsumer.accept(r2.getKey(), ((Map.Entry) obj).getValue());
            }
        });
    }
}
