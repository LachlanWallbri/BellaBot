package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class HashBiMap<K, V> extends Maps.IteratorBasedAbstractMap<K, V> implements BiMap<K, V>, Serializable {
    private static final double LOAD_FACTOR = 1.0d;
    private static final long serialVersionUID = 0;
    private transient BiEntry<K, V> firstInKeyInsertionOrder;
    private transient BiEntry<K, V>[] hashTableKToV;
    private transient BiEntry<K, V>[] hashTableVToK;

    @LazyInit
    private transient BiMap<V, K> inverse;
    private transient BiEntry<K, V> lastInKeyInsertionOrder;
    private transient int mask;
    private transient int modCount;
    private transient int size;

    @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    public static <K, V> HashBiMap<K, V> create() {
        return create(16);
    }

    public static <K, V> HashBiMap<K, V> create(int i) {
        return new HashBiMap<>(i);
    }

    public static <K, V> HashBiMap<K, V> create(Map<? extends K, ? extends V> map) {
        HashBiMap<K, V> create = create(map.size());
        create.putAll(map);
        return create;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class BiEntry<K, V> extends ImmutableEntry<K, V> {
        final int keyHash;
        BiEntry<K, V> nextInKToVBucket;
        BiEntry<K, V> nextInKeyInsertionOrder;
        BiEntry<K, V> nextInVToKBucket;
        BiEntry<K, V> prevInKeyInsertionOrder;
        final int valueHash;

        BiEntry(K k, int i, V v, int i2) {
            super(k, v);
            this.keyHash = i;
            this.valueHash = i2;
        }
    }

    private HashBiMap(int i) {
        init(i);
    }

    private void init(int i) {
        CollectPreconditions.checkNonnegative(i, "expectedSize");
        int closedTableSize = Hashing.closedTableSize(i, 1.0d);
        this.hashTableKToV = createTable(closedTableSize);
        this.hashTableVToK = createTable(closedTableSize);
        this.firstInKeyInsertionOrder = null;
        this.lastInKeyInsertionOrder = null;
        this.size = 0;
        this.mask = closedTableSize - 1;
        this.modCount = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void delete(BiEntry<K, V> biEntry) {
        BiEntry<K, V> biEntry2;
        int i = biEntry.keyHash & this.mask;
        BiEntry<K, V> biEntry3 = null;
        BiEntry<K, V> biEntry4 = null;
        for (BiEntry<K, V> biEntry5 = this.hashTableKToV[i]; biEntry5 != biEntry; biEntry5 = biEntry5.nextInKToVBucket) {
            biEntry4 = biEntry5;
        }
        if (biEntry4 == null) {
            this.hashTableKToV[i] = biEntry.nextInKToVBucket;
        } else {
            biEntry4.nextInKToVBucket = biEntry.nextInKToVBucket;
        }
        int i2 = biEntry.valueHash & this.mask;
        BiEntry<K, V> biEntry6 = this.hashTableVToK[i2];
        while (true) {
            biEntry2 = biEntry3;
            biEntry3 = biEntry6;
            if (biEntry3 == biEntry) {
                break;
            } else {
                biEntry6 = biEntry3.nextInVToKBucket;
            }
        }
        if (biEntry2 == null) {
            this.hashTableVToK[i2] = biEntry.nextInVToKBucket;
        } else {
            biEntry2.nextInVToKBucket = biEntry.nextInVToKBucket;
        }
        if (biEntry.prevInKeyInsertionOrder == null) {
            this.firstInKeyInsertionOrder = biEntry.nextInKeyInsertionOrder;
        } else {
            biEntry.prevInKeyInsertionOrder.nextInKeyInsertionOrder = biEntry.nextInKeyInsertionOrder;
        }
        if (biEntry.nextInKeyInsertionOrder == null) {
            this.lastInKeyInsertionOrder = biEntry.prevInKeyInsertionOrder;
        } else {
            biEntry.nextInKeyInsertionOrder.prevInKeyInsertionOrder = biEntry.prevInKeyInsertionOrder;
        }
        this.size--;
        this.modCount++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void insert(BiEntry<K, V> biEntry, BiEntry<K, V> biEntry2) {
        int i = biEntry.keyHash & this.mask;
        BiEntry<K, V>[] biEntryArr = this.hashTableKToV;
        biEntry.nextInKToVBucket = biEntryArr[i];
        biEntryArr[i] = biEntry;
        int i2 = biEntry.valueHash & this.mask;
        BiEntry<K, V>[] biEntryArr2 = this.hashTableVToK;
        biEntry.nextInVToKBucket = biEntryArr2[i2];
        biEntryArr2[i2] = biEntry;
        if (biEntry2 == null) {
            BiEntry<K, V> biEntry3 = this.lastInKeyInsertionOrder;
            biEntry.prevInKeyInsertionOrder = biEntry3;
            biEntry.nextInKeyInsertionOrder = null;
            if (biEntry3 == null) {
                this.firstInKeyInsertionOrder = biEntry;
            } else {
                biEntry3.nextInKeyInsertionOrder = biEntry;
            }
            this.lastInKeyInsertionOrder = biEntry;
        } else {
            biEntry.prevInKeyInsertionOrder = biEntry2.prevInKeyInsertionOrder;
            if (biEntry.prevInKeyInsertionOrder == null) {
                this.firstInKeyInsertionOrder = biEntry;
            } else {
                biEntry.prevInKeyInsertionOrder.nextInKeyInsertionOrder = biEntry;
            }
            biEntry.nextInKeyInsertionOrder = biEntry2.nextInKeyInsertionOrder;
            if (biEntry.nextInKeyInsertionOrder == null) {
                this.lastInKeyInsertionOrder = biEntry;
            } else {
                biEntry.nextInKeyInsertionOrder.prevInKeyInsertionOrder = biEntry;
            }
        }
        this.size++;
        this.modCount++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BiEntry<K, V> seekByKey(Object obj, int i) {
        for (BiEntry<K, V> biEntry = this.hashTableKToV[this.mask & i]; biEntry != null; biEntry = biEntry.nextInKToVBucket) {
            if (i == biEntry.keyHash && Objects.equal(obj, biEntry.key)) {
                return biEntry;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BiEntry<K, V> seekByValue(Object obj, int i) {
        for (BiEntry<K, V> biEntry = this.hashTableVToK[this.mask & i]; biEntry != null; biEntry = biEntry.nextInVToKBucket) {
            if (i == biEntry.valueHash && Objects.equal(obj, biEntry.value)) {
                return biEntry;
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return seekByKey(obj, Hashing.smearedHash(obj)) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) {
        return seekByValue(obj, Hashing.smearedHash(obj)) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        return (V) Maps.valueOrNull(seekByKey(obj, Hashing.smearedHash(obj)));
    }

    @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
    public V put(K k, V v) {
        return put(k, v, false);
    }

    private V put(K k, V v, boolean z) {
        int smearedHash = Hashing.smearedHash(k);
        int smearedHash2 = Hashing.smearedHash(v);
        BiEntry<K, V> seekByKey = seekByKey(k, smearedHash);
        if (seekByKey != null && smearedHash2 == seekByKey.valueHash && Objects.equal(v, seekByKey.value)) {
            return v;
        }
        BiEntry<K, V> seekByValue = seekByValue(v, smearedHash2);
        if (seekByValue != null) {
            if (z) {
                delete(seekByValue);
            } else {
                String valueOf = String.valueOf(v);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
                sb.append("value already present: ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        BiEntry<K, V> biEntry = new BiEntry<>(k, smearedHash, v, smearedHash2);
        if (seekByKey != null) {
            delete(seekByKey);
            insert(biEntry, seekByKey);
            seekByKey.prevInKeyInsertionOrder = null;
            seekByKey.nextInKeyInsertionOrder = null;
            return seekByKey.value;
        }
        insert(biEntry, null);
        rehashIfNecessary();
        return null;
    }

    @Override // com.google.common.collect.BiMap
    public V forcePut(K k, V v) {
        return put(k, v, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public K putInverse(V v, K k, boolean z) {
        int smearedHash = Hashing.smearedHash(v);
        int smearedHash2 = Hashing.smearedHash(k);
        BiEntry<K, V> seekByValue = seekByValue(v, smearedHash);
        BiEntry<K, V> seekByKey = seekByKey(k, smearedHash2);
        if (seekByValue != null && smearedHash2 == seekByValue.keyHash && Objects.equal(k, seekByValue.key)) {
            return k;
        }
        if (seekByKey != null && !z) {
            String valueOf = String.valueOf(k);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 21);
            sb.append("key already present: ");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString());
        }
        if (seekByValue != null) {
            delete(seekByValue);
        }
        if (seekByKey != null) {
            delete(seekByKey);
        }
        insert(new BiEntry<>(k, smearedHash2, v, smearedHash), seekByKey);
        if (seekByKey != null) {
            seekByKey.prevInKeyInsertionOrder = null;
            seekByKey.nextInKeyInsertionOrder = null;
        }
        if (seekByValue != null) {
            seekByValue.prevInKeyInsertionOrder = null;
            seekByValue.nextInKeyInsertionOrder = null;
        }
        rehashIfNecessary();
        return (K) Maps.keyOrNull(seekByValue);
    }

    private void rehashIfNecessary() {
        BiEntry<K, V>[] biEntryArr = this.hashTableKToV;
        if (Hashing.needsResizing(this.size, biEntryArr.length, 1.0d)) {
            int length = biEntryArr.length * 2;
            this.hashTableKToV = createTable(length);
            this.hashTableVToK = createTable(length);
            this.mask = length - 1;
            this.size = 0;
            for (BiEntry<K, V> biEntry = this.firstInKeyInsertionOrder; biEntry != null; biEntry = biEntry.nextInKeyInsertionOrder) {
                insert(biEntry, biEntry);
            }
            this.modCount++;
        }
    }

    private BiEntry<K, V>[] createTable(int i) {
        return new BiEntry[i];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        BiEntry<K, V> seekByKey = seekByKey(obj, Hashing.smearedHash(obj));
        if (seekByKey == null) {
            return null;
        }
        delete(seekByKey);
        seekByKey.prevInKeyInsertionOrder = null;
        seekByKey.nextInKeyInsertionOrder = null;
        return seekByKey.value;
    }

    @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
    public void clear() {
        this.size = 0;
        Arrays.fill(this.hashTableKToV, (Object) null);
        Arrays.fill(this.hashTableVToK, (Object) null);
        this.firstInKeyInsertionOrder = null;
        this.lastInKeyInsertionOrder = null;
        this.modCount++;
    }

    @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    /* loaded from: classes3.dex */
    abstract class Itr<T> implements Iterator<T> {
        int expectedModCount;
        BiEntry<K, V> next;
        int remaining;
        BiEntry<K, V> toRemove = null;

        abstract T output(BiEntry<K, V> biEntry);

        Itr() {
            this.next = HashBiMap.this.firstInKeyInsertionOrder;
            this.expectedModCount = HashBiMap.this.modCount;
            this.remaining = HashBiMap.this.size();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (HashBiMap.this.modCount == this.expectedModCount) {
                return this.next != null && this.remaining > 0;
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.Iterator
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            BiEntry<K, V> biEntry = this.next;
            this.next = biEntry.nextInKeyInsertionOrder;
            this.toRemove = biEntry;
            this.remaining--;
            return output(biEntry);
        }

        @Override // java.util.Iterator
        public void remove() {
            if (HashBiMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            CollectPreconditions.checkRemove(this.toRemove != null);
            HashBiMap.this.delete(this.toRemove);
            this.expectedModCount = HashBiMap.this.modCount;
            this.toRemove = null;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        return new KeySet();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    private final class KeySet extends Maps.KeySet<K, V> {
        KeySet() {
            super(HashBiMap.this);
        }

        @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new HashBiMap<K, V>.Itr<K>(this) { // from class: com.google.common.collect.HashBiMap.KeySet.1
                {
                    HashBiMap hashBiMap = HashBiMap.this;
                }

                @Override // com.google.common.collect.HashBiMap.Itr
                K output(BiEntry<K, V> biEntry) {
                    return biEntry.key;
                }
            };
        }

        @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            BiEntry seekByKey = HashBiMap.this.seekByKey(obj, Hashing.smearedHash(obj));
            if (seekByKey == null) {
                return false;
            }
            HashBiMap.this.delete(seekByKey);
            seekByKey.prevInKeyInsertionOrder = null;
            seekByKey.nextInKeyInsertionOrder = null;
            return true;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
    public Set<V> values() {
        return inverse().keySet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
    public Iterator<Map.Entry<K, V>> entryIterator() {
        return new HashBiMap<K, V>.Itr<Map.Entry<K, V>>() { // from class: com.google.common.collect.HashBiMap.1
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.collect.HashBiMap.Itr
            public Map.Entry<K, V> output(BiEntry<K, V> biEntry) {
                return new MapEntry(biEntry);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.google.common.collect.HashBiMap$1$MapEntry */
            /* loaded from: classes3.dex */
            public class MapEntry extends AbstractMapEntry<K, V> {
                BiEntry<K, V> delegate;

                MapEntry(BiEntry<K, V> biEntry) {
                    this.delegate = biEntry;
                }

                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                public K getKey() {
                    return this.delegate.key;
                }

                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                public V getValue() {
                    return this.delegate.value;
                }

                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                public V setValue(V v) {
                    V v2 = this.delegate.value;
                    int smearedHash = Hashing.smearedHash(v);
                    if (smearedHash == this.delegate.valueHash && Objects.equal(v, v2)) {
                        return v;
                    }
                    Preconditions.checkArgument(HashBiMap.this.seekByValue(v, smearedHash) == null, "value already present: %s", v);
                    HashBiMap.this.delete(this.delegate);
                    BiEntry<K, V> biEntry = new BiEntry<>(this.delegate.key, this.delegate.keyHash, v, smearedHash);
                    HashBiMap.this.insert(biEntry, this.delegate);
                    BiEntry<K, V> biEntry2 = this.delegate;
                    biEntry2.prevInKeyInsertionOrder = null;
                    biEntry2.nextInKeyInsertionOrder = null;
                    C27761 c27761 = C27761.this;
                    c27761.expectedModCount = HashBiMap.this.modCount;
                    if (C27761.this.toRemove == this.delegate) {
                        C27761.this.toRemove = biEntry;
                    }
                    this.delegate = biEntry;
                    return v2;
                }
            }
        };
    }

    @Override // java.util.Map
    public void forEach(BiConsumer<? super K, ? super V> biConsumer) {
        Preconditions.checkNotNull(biConsumer);
        for (BiEntry<K, V> biEntry = this.firstInKeyInsertionOrder; biEntry != null; biEntry = biEntry.nextInKeyInsertionOrder) {
            biConsumer.accept(biEntry.key, biEntry.value);
        }
    }

    @Override // java.util.Map
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> biFunction) {
        Preconditions.checkNotNull(biFunction);
        clear();
        for (BiEntry<K, V> biEntry = this.firstInKeyInsertionOrder; biEntry != null; biEntry = biEntry.nextInKeyInsertionOrder) {
            put(biEntry.key, biFunction.apply(biEntry.key, biEntry.value));
        }
    }

    @Override // com.google.common.collect.BiMap
    public BiMap<V, K> inverse() {
        BiMap<V, K> biMap = this.inverse;
        if (biMap != null) {
            return biMap;
        }
        Inverse inverse = new Inverse();
        this.inverse = inverse;
        return inverse;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public final class Inverse extends Maps.IteratorBasedAbstractMap<V, K> implements BiMap<V, K>, Serializable {
        private Inverse() {
        }

        BiMap<K, V> forward() {
            return HashBiMap.this;
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return HashBiMap.this.size;
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public void clear() {
            forward().clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return forward().containsValue(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public K get(Object obj) {
            return (K) Maps.keyOrNull(HashBiMap.this.seekByValue(obj, Hashing.smearedHash(obj)));
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
        public K put(V v, K k) {
            return (K) HashBiMap.this.putInverse(v, k, false);
        }

        @Override // com.google.common.collect.BiMap
        public K forcePut(V v, K k) {
            return (K) HashBiMap.this.putInverse(v, k, true);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public K remove(Object obj) {
            BiEntry seekByValue = HashBiMap.this.seekByValue(obj, Hashing.smearedHash(obj));
            if (seekByValue == null) {
                return null;
            }
            HashBiMap.this.delete(seekByValue);
            seekByValue.prevInKeyInsertionOrder = null;
            seekByValue.nextInKeyInsertionOrder = null;
            return seekByValue.key;
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<K, V> inverse() {
            return forward();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<V> keySet() {
            return new InverseKeySet();
        }

        /* loaded from: classes3.dex */
        private final class InverseKeySet extends Maps.KeySet<V, K> {
            InverseKeySet() {
                super(Inverse.this);
            }

            @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                BiEntry seekByValue = HashBiMap.this.seekByValue(obj, Hashing.smearedHash(obj));
                if (seekByValue == null) {
                    return false;
                }
                HashBiMap.this.delete(seekByValue);
                return true;
            }

            @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<V> iterator() {
                return new HashBiMap<K, V>.Itr<V>(this) { // from class: com.google.common.collect.HashBiMap.Inverse.InverseKeySet.1
                    {
                        HashBiMap hashBiMap = HashBiMap.this;
                    }

                    @Override // com.google.common.collect.HashBiMap.Itr
                    V output(BiEntry<K, V> biEntry) {
                        return biEntry.value;
                    }
                };
            }
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
        public Set<K> values() {
            return forward().keySet();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<V, K>> entryIterator() {
            return new HashBiMap<K, V>.Itr<Map.Entry<V, K>>() { // from class: com.google.common.collect.HashBiMap.Inverse.1
                {
                    HashBiMap hashBiMap = HashBiMap.this;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.google.common.collect.HashBiMap.Itr
                public Map.Entry<V, K> output(BiEntry<K, V> biEntry) {
                    return new InverseEntry(biEntry);
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* renamed from: com.google.common.collect.HashBiMap$Inverse$1$InverseEntry */
                /* loaded from: classes3.dex */
                public class InverseEntry extends AbstractMapEntry<V, K> {
                    BiEntry<K, V> delegate;

                    InverseEntry(BiEntry<K, V> biEntry) {
                        this.delegate = biEntry;
                    }

                    @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                    public V getKey() {
                        return this.delegate.value;
                    }

                    @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                    public K getValue() {
                        return this.delegate.key;
                    }

                    @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                    public K setValue(K k) {
                        K k2 = this.delegate.key;
                        int smearedHash = Hashing.smearedHash(k);
                        if (smearedHash == this.delegate.keyHash && Objects.equal(k, k2)) {
                            return k;
                        }
                        Preconditions.checkArgument(HashBiMap.this.seekByKey(k, smearedHash) == null, "value already present: %s", k);
                        HashBiMap.this.delete(this.delegate);
                        BiEntry<K, V> biEntry = new BiEntry<>(k, smearedHash, this.delegate.value, this.delegate.valueHash);
                        this.delegate = biEntry;
                        HashBiMap.this.insert(biEntry, null);
                        C27771 c27771 = C27771.this;
                        c27771.expectedModCount = HashBiMap.this.modCount;
                        return k2;
                    }
                }
            };
        }

        @Override // java.util.Map
        public void forEach(final BiConsumer<? super V, ? super K> biConsumer) {
            Preconditions.checkNotNull(biConsumer);
            HashBiMap.this.forEach(new BiConsumer() { // from class: com.google.common.collect.-$$Lambda$HashBiMap$Inverse$RpeIn_Fo-wtHkkM0eHEOuMSgmjY
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    biConsumer.accept(obj2, obj);
                }
            });
        }

        @Override // java.util.Map
        public void replaceAll(BiFunction<? super V, ? super K, ? extends K> biFunction) {
            Preconditions.checkNotNull(biFunction);
            clear();
            for (BiEntry<K, V> biEntry = HashBiMap.this.firstInKeyInsertionOrder; biEntry != null; biEntry = biEntry.nextInKeyInsertionOrder) {
                put(biEntry.value, biFunction.apply(biEntry.value, biEntry.key));
            }
        }

        Object writeReplace() {
            return new InverseSerializedForm(HashBiMap.this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static abstract class View<K, V, T> extends AbstractSet<T> {
        final HashBiMap<K, V> biMap;

        abstract T forEntry(int i);

        View(HashBiMap<K, V> hashBiMap) {
            this.biMap = hashBiMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<T> iterator() {
            return new Iterator<T>() { // from class: com.google.common.collect.HashBiMap.View.1
                private int expectedModCount;
                private int index;
                private int indexToRemove = -1;
                private int remaining;

                {
                    this.index = ((HashBiMap) View.this.biMap).firstInKeyInsertionOrder;
                    this.expectedModCount = ((HashBiMap) View.this.biMap).modCount;
                    this.remaining = ((HashBiMap) View.this.biMap).size;
                }

                private void checkForComodification() {
                    if (((HashBiMap) View.this.biMap).modCount != this.expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    checkForComodification();
                    return this.index != -2 && this.remaining > 0;
                }

                @Override // java.util.Iterator
                public T next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    T t = (T) View.this.forEntry(this.index);
                    this.indexToRemove = this.index;
                    this.index = ((HashBiMap) View.this.biMap).modCount[this.index];
                    this.remaining--;
                    return t;
                }

                @Override // java.util.Iterator
                public void remove() {
                    checkForComodification();
                    CollectPreconditions.checkRemove(this.indexToRemove != -1);
                    View.this.biMap.removeEntry(this.indexToRemove);
                    if (this.index == ((HashBiMap) View.this.biMap).size) {
                        this.index = this.indexToRemove;
                    }
                    this.indexToRemove = -1;
                    this.expectedModCount = ((HashBiMap) View.this.biMap).modCount;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return ((HashBiMap) this.biMap).size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.biMap.clear();
        }
    }

    /* loaded from: classes3.dex */
    private static final class InverseSerializedForm<K, V> implements Serializable {
        private final HashBiMap<K, V> bimap;

        InverseSerializedForm(HashBiMap<K, V> hashBiMap) {
            this.bimap = hashBiMap;
        }

        Object readResolve() {
            return this.bimap.inverse();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Serialization.writeMap(this, objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readCount = Serialization.readCount(objectInputStream);
        init(16);
        Serialization.populateMap(this, objectInputStream, readCount);
    }

    /* loaded from: classes3.dex */
    final class ValueSet extends View<K, V, V> {
        ValueSet() {
            super(HashBiMap.this);
        }

        @Override // com.google.common.collect.HashBiMap.View
        V forEntry(int i) {
            return (V) HashBiMap.this.values[i];
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            return HashBiMap.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@NullableDecl Object obj) {
            int smearedHash = Hashing.smearedHash(obj);
            int findEntryByValue = HashBiMap.this.findEntryByValue(obj, smearedHash);
            if (findEntryByValue == -1) {
                return false;
            }
            HashBiMap.this.removeEntryValueHashKnown(findEntryByValue, smearedHash);
            return true;
        }
    }

    /* loaded from: classes3.dex */
    final class EntrySet extends View<K, V, Map.Entry<K, V>> {
        EntrySet() {
            super(HashBiMap.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            int findEntryByKey = HashBiMap.this.findEntryByKey(key);
            return findEntryByKey != -1 && Objects.equal(value, HashBiMap.this.values[findEntryByKey]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@NullableDecl Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            int smearedHash = Hashing.smearedHash(key);
            int findEntryByKey = HashBiMap.this.findEntryByKey(key, smearedHash);
            if (findEntryByKey == -1 || !Objects.equal(value, HashBiMap.this.values[findEntryByKey])) {
                return false;
            }
            HashBiMap.this.removeEntryKeyHashKnown(findEntryByKey, smearedHash);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.HashBiMap.View
        public Map.Entry<K, V> forEntry(int i) {
            return new EntryForKey(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class EntryForKey extends AbstractMapEntry<K, V> {
        int index;

        @NullableDecl
        final K key;

        EntryForKey(int i) {
            this.key = (K) HashBiMap.this.keys[i];
            this.index = i;
        }

        void updateIndex() {
            int i = this.index;
            if (i == -1 || i > HashBiMap.this.size || !Objects.equal(HashBiMap.this.keys[this.index], this.key)) {
                this.index = HashBiMap.this.findEntryByKey(this.key);
            }
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @NullableDecl
        public V getValue() {
            updateIndex();
            if (this.index == -1) {
                return null;
            }
            return (V) HashBiMap.this.values[this.index];
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V setValue(V v) {
            updateIndex();
            if (this.index == -1) {
                return (V) HashBiMap.this.put(this.key, v);
            }
            V v2 = (V) HashBiMap.this.values[this.index];
            if (Objects.equal(v2, v)) {
                return v;
            }
            HashBiMap.access$200(HashBiMap.this, this.index, v, false);
            return v2;
        }
    }

    /* loaded from: classes3.dex */
    static class InverseEntrySet<K, V> extends View<K, V, Map.Entry<V, K>> {
        InverseEntrySet(HashBiMap<K, V> hashBiMap) {
            super(hashBiMap);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            int findEntryByValue = this.biMap.findEntryByValue(key);
            return findEntryByValue != -1 && Objects.equal(this.biMap.keys[findEntryByValue], value);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            int smearedHash = Hashing.smearedHash(key);
            int findEntryByValue = this.biMap.findEntryByValue(key, smearedHash);
            if (findEntryByValue == -1 || !Objects.equal(this.biMap.keys[findEntryByValue], value)) {
                return false;
            }
            this.biMap.removeEntryValueHashKnown(findEntryByValue, smearedHash);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.HashBiMap.View
        public Map.Entry<V, K> forEntry(int i) {
            return new EntryForValue(this.biMap, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class EntryForValue<K, V> extends AbstractMapEntry<V, K> {
        final HashBiMap<K, V> biMap;
        int index;
        final V value;

        EntryForValue(HashBiMap<K, V> hashBiMap, int i) {
            this.biMap = hashBiMap;
            this.value = (V) hashBiMap.values[i];
            this.index = i;
        }

        private void updateIndex() {
            int i = this.index;
            if (i == -1 || i > ((HashBiMap) this.biMap).size || !Objects.equal(this.value, this.biMap.values[this.index])) {
                this.index = this.biMap.findEntryByValue(this.value);
            }
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V getKey() {
            return this.value;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K getValue() {
            updateIndex();
            if (this.index == -1) {
                return null;
            }
            return (K) this.biMap.keys[this.index];
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K setValue(K k) {
            updateIndex();
            if (this.index == -1) {
                return this.biMap.putInverse(this.value, k, false);
            }
            K k2 = (K) this.biMap.keys[this.index];
            if (Objects.equal(k2, k)) {
                return k;
            }
            HashBiMap.access$400(this.biMap, this.index, k, false);
            return k2;
        }
    }
}
