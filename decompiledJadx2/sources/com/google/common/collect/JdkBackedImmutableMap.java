package com.google.common.collect;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMapEntrySet;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class JdkBackedImmutableMap<K, V> extends ImmutableMap<K, V> {
    private final transient Map<K, V> delegateMap;
    private final transient ImmutableList<Map.Entry<K, V>> entries;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> ImmutableMap<K, V> create(int i, Map.Entry<K, V>[] entryArr) {
        HashMap newHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(i);
        for (int i2 = 0; i2 < i; i2++) {
            entryArr[i2] = RegularImmutableMap.makeImmutable(entryArr[i2]);
            Object putIfAbsent = newHashMapWithExpectedSize.putIfAbsent(entryArr[i2].getKey(), entryArr[i2].getValue());
            if (putIfAbsent != null) {
                Map.Entry<K, V> entry = entryArr[i2];
                String valueOf = String.valueOf(entryArr[i2].getKey());
                String valueOf2 = String.valueOf(putIfAbsent);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length());
                sb.append(valueOf);
                sb.append("=");
                sb.append(valueOf2);
                throw conflictException(TransferTable.COLUMN_KEY, entry, sb.toString());
            }
        }
        return new JdkBackedImmutableMap(newHashMapWithExpectedSize, ImmutableList.asImmutableList(entryArr, i));
    }

    JdkBackedImmutableMap(Map<K, V> map, ImmutableList<Map.Entry<K, V>> immutableList) {
        this.delegateMap = map;
        this.entries = immutableList;
    }

    @Override // java.util.Map
    public int size() {
        return this.entries.size();
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(Object obj) {
        return this.delegateMap.get(obj);
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new ImmutableMapEntrySet.RegularEntrySet(this, this.entries);
    }

    @Override // java.util.Map
    public void forEach(final BiConsumer<? super K, ? super V> biConsumer) {
        Preconditions.checkNotNull(biConsumer);
        this.entries.forEach(new Consumer() { // from class: com.google.common.collect.-$$Lambda$JdkBackedImmutableMap$ThhB9akQzP54Zy7ZoDbOMOzR7V8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                biConsumer.accept(r2.getKey(), ((Map.Entry) obj).getValue());
            }
        });
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<K> createKeySet() {
        return new ImmutableMapKeySet(this);
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableCollection<V> createValues() {
        return new ImmutableMapValues(this);
    }
}
