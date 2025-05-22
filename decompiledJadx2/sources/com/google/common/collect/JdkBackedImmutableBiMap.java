package com.google.common.collect;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.google.common.collect.ImmutableMapEntrySet;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.HashMap;
import java.util.Map;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class JdkBackedImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    private final Map<V, K> backwardDelegate;
    private final transient ImmutableList<Map.Entry<K, V>> entries;
    private final Map<K, V> forwardDelegate;

    @LazyInit
    private transient JdkBackedImmutableBiMap<V, K> inverse;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> ImmutableBiMap<K, V> create(int i, Map.Entry<K, V>[] entryArr) {
        HashMap newHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(i);
        HashMap newHashMapWithExpectedSize2 = Maps.newHashMapWithExpectedSize(i);
        for (int i2 = 0; i2 < i; i2++) {
            ImmutableMapEntry makeImmutable = RegularImmutableMap.makeImmutable(entryArr[i2]);
            entryArr[i2] = makeImmutable;
            Object putIfAbsent = newHashMapWithExpectedSize.putIfAbsent(makeImmutable.getKey(), makeImmutable.getValue());
            if (putIfAbsent != null) {
                String valueOf = String.valueOf(makeImmutable.getKey());
                String valueOf2 = String.valueOf(putIfAbsent);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length());
                sb.append(valueOf);
                sb.append("=");
                sb.append(valueOf2);
                throw conflictException(TransferTable.COLUMN_KEY, sb.toString(), entryArr[i2]);
            }
            Object putIfAbsent2 = newHashMapWithExpectedSize2.putIfAbsent(makeImmutable.getValue(), makeImmutable.getKey());
            if (putIfAbsent2 != null) {
                String valueOf3 = String.valueOf(putIfAbsent2);
                String valueOf4 = String.valueOf(makeImmutable.getValue());
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf3).length() + 1 + String.valueOf(valueOf4).length());
                sb2.append(valueOf3);
                sb2.append("=");
                sb2.append(valueOf4);
                throw conflictException(ES6Iterator.VALUE_PROPERTY, sb2.toString(), entryArr[i2]);
            }
        }
        return new JdkBackedImmutableBiMap(ImmutableList.asImmutableList(entryArr, i), newHashMapWithExpectedSize, newHashMapWithExpectedSize2);
    }

    private JdkBackedImmutableBiMap(ImmutableList<Map.Entry<K, V>> immutableList, Map<K, V> map, Map<V, K> map2) {
        this.entries = immutableList;
        this.forwardDelegate = map;
        this.backwardDelegate = map2;
    }

    @Override // java.util.Map
    public int size() {
        return this.entries.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableBiMap, com.google.common.collect.BiMap
    public ImmutableBiMap<V, K> inverse() {
        JdkBackedImmutableBiMap<V, K> jdkBackedImmutableBiMap = this.inverse;
        if (jdkBackedImmutableBiMap != null) {
            return jdkBackedImmutableBiMap;
        }
        JdkBackedImmutableBiMap<V, K> jdkBackedImmutableBiMap2 = new JdkBackedImmutableBiMap<>(new InverseEntries(), this.backwardDelegate, this.forwardDelegate);
        this.inverse = jdkBackedImmutableBiMap2;
        jdkBackedImmutableBiMap2.inverse = this;
        return jdkBackedImmutableBiMap2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public final class InverseEntries extends ImmutableList<Map.Entry<V, K>> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return false;
        }

        private InverseEntries() {
        }

        @Override // java.util.List
        public Map.Entry<V, K> get(int i) {
            Map.Entry entry = (Map.Entry) JdkBackedImmutableBiMap.this.entries.get(i);
            return Maps.immutableEntry(entry.getValue(), entry.getKey());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return JdkBackedImmutableBiMap.this.entries.size();
        }
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(Object obj) {
        return this.forwardDelegate.get(obj);
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new ImmutableMapEntrySet.RegularEntrySet(this, this.entries);
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<K> createKeySet() {
        return new ImmutableMapKeySet(this);
    }
}
