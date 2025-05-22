package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public class MapIteratorCache<K, V> {
    private final Map<K, V> backingMap;
    private volatile transient Map.Entry<K, V> cacheEntry;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MapIteratorCache(Map<K, V> map) {
        this.backingMap = (Map) Preconditions.checkNotNull(map);
    }

    public final V put(K k, V v) {
        clearCache();
        return this.backingMap.put(k, v);
    }

    public final V remove(Object obj) {
        clearCache();
        return this.backingMap.remove(obj);
    }

    public final void clear() {
        clearCache();
        this.backingMap.clear();
    }

    public V get(Object obj) {
        V ifCached = getIfCached(obj);
        return ifCached != null ? ifCached : getWithoutCaching(obj);
    }

    public final V getWithoutCaching(Object obj) {
        return this.backingMap.get(obj);
    }

    public final boolean containsKey(Object obj) {
        return getIfCached(obj) != null || this.backingMap.containsKey(obj);
    }

    public final Set<K> unmodifiableKeySet() {
        return new AbstractSet<K>() { // from class: com.google.common.graph.MapIteratorCache.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<K> iterator() {
                final Iterator<Map.Entry<K, V>> it = MapIteratorCache.this.backingMap.entrySet().iterator();
                return new UnmodifiableIterator<K>() { // from class: com.google.common.graph.MapIteratorCache.1.1
                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    @Override // java.util.Iterator
                    public K next() {
                        Map.Entry entry = (Map.Entry) it.next();
                        MapIteratorCache.this.cacheEntry = entry;
                        return (K) entry.getKey();
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return MapIteratorCache.this.backingMap.size();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return MapIteratorCache.this.containsKey(obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public V getIfCached(Object obj) {
        Map.Entry<K, V> entry = this.cacheEntry;
        if (entry == null || entry.getKey() != obj) {
            return null;
        }
        return entry.getValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void clearCache() {
        this.cacheEntry = null;
    }
}
