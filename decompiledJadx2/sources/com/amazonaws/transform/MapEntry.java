package com.amazonaws.transform;

import java.util.Map;

/* loaded from: classes.dex */
public class MapEntry<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;

    @Override // java.util.Map.Entry
    public K getKey() {
        return this.key;
    }

    @Override // java.util.Map.Entry
    public V getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public V setValue(V v) {
        this.value = v;
        return this.value;
    }

    public K setKey(K k) {
        this.key = k;
        return this.key;
    }
}
