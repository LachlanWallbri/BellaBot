package me.jessyan.retrofiturlmanager.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public class LruCache<K, V> implements Cache<K, V> {
    private final LinkedHashMap<K, V> cache = new LinkedHashMap<>(100, 0.75f, true);
    private int currentSize = 0;
    private final int initialMaxSize;
    private int maxSize;

    protected int getItemSize(V v) {
        return 1;
    }

    protected void onItemEvicted(K k, V v) {
    }

    public LruCache(int i) {
        this.initialMaxSize = i;
        this.maxSize = i;
    }

    public synchronized void setSizeMultiplier(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("Multiplier must be >= 0");
        }
        this.maxSize = Math.round(this.initialMaxSize * f);
        evict();
    }

    @Override // me.jessyan.retrofiturlmanager.cache.Cache
    public synchronized int getMaxSize() {
        return this.maxSize;
    }

    @Override // me.jessyan.retrofiturlmanager.cache.Cache
    public synchronized int size() {
        return this.currentSize;
    }

    @Override // me.jessyan.retrofiturlmanager.cache.Cache
    public synchronized boolean containsKey(K k) {
        return this.cache.containsKey(k);
    }

    @Override // me.jessyan.retrofiturlmanager.cache.Cache
    public synchronized Set<K> keySet() {
        return this.cache.keySet();
    }

    @Override // me.jessyan.retrofiturlmanager.cache.Cache
    public synchronized V get(K k) {
        return this.cache.get(k);
    }

    @Override // me.jessyan.retrofiturlmanager.cache.Cache
    public synchronized V put(K k, V v) {
        if (getItemSize(v) >= this.maxSize) {
            onItemEvicted(k, v);
            return null;
        }
        V put = this.cache.put(k, v);
        if (v != null) {
            this.currentSize += getItemSize(v);
        }
        if (put != null) {
            this.currentSize -= getItemSize(put);
        }
        evict();
        return put;
    }

    @Override // me.jessyan.retrofiturlmanager.cache.Cache
    public synchronized V remove(K k) {
        V remove;
        remove = this.cache.remove(k);
        if (remove != null) {
            this.currentSize -= getItemSize(remove);
        }
        return remove;
    }

    @Override // me.jessyan.retrofiturlmanager.cache.Cache
    public void clear() {
        trimToSize(0);
    }

    protected synchronized void trimToSize(int i) {
        while (this.currentSize > i) {
            Map.Entry<K, V> next = this.cache.entrySet().iterator().next();
            V value = next.getValue();
            this.currentSize -= getItemSize(value);
            K key = next.getKey();
            this.cache.remove(key);
            onItemEvicted(key, value);
        }
    }

    private void evict() {
        trimToSize(this.maxSize);
    }
}
