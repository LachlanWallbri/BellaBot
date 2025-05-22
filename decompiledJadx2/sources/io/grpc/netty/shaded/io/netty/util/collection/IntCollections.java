package io.grpc.netty.shaded.io.netty.util.collection;

import io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class IntCollections {
    private static final IntObjectMap<Object> EMPTY_MAP = new EmptyMap();

    private IntCollections() {
    }

    public static <V> IntObjectMap<V> emptyMap() {
        return (IntObjectMap<V>) EMPTY_MAP;
    }

    public static <V> IntObjectMap<V> unmodifiableMap(IntObjectMap<V> intObjectMap) {
        return new UnmodifiableMap(intObjectMap);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class EmptyMap implements IntObjectMap<Object> {
        @Override // java.util.Map
        public void clear() {
        }

        @Override // io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap
        public boolean containsKey(int i) {
            return false;
        }

        @Override // java.util.Map
        public boolean containsKey(Object obj) {
            return false;
        }

        @Override // java.util.Map
        public boolean containsValue(Object obj) {
            return false;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap
        public Object get(int i) {
            return null;
        }

        @Override // java.util.Map
        public Object get(Object obj) {
            return null;
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            return true;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap
        public Object remove(int i) {
            return null;
        }

        @Override // java.util.Map
        public Object remove(Object obj) {
            return null;
        }

        @Override // java.util.Map
        public int size() {
            return 0;
        }

        private EmptyMap() {
        }

        @Override // io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap
        public Object put(int i, Object obj) {
            throw new UnsupportedOperationException("put");
        }

        @Override // java.util.Map
        public Set<Integer> keySet() {
            return Collections.emptySet();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap
        public Iterable<IntObjectMap.PrimitiveEntry<Object>> entries() {
            return Collections.emptySet();
        }

        @Override // java.util.Map
        public Object put(Integer num, Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public void putAll(Map<? extends Integer, ?> map) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public Collection<Object> values() {
            return Collections.emptyList();
        }

        @Override // java.util.Map
        public Set<Map.Entry<Integer, Object>> entrySet() {
            return Collections.emptySet();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class UnmodifiableMap<V> implements IntObjectMap<V> {
        private Iterable<IntObjectMap.PrimitiveEntry<V>> entries;
        private Set<Map.Entry<Integer, V>> entrySet;
        private Set<Integer> keySet;
        private final IntObjectMap<V> map;
        private Collection<V> values;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Map
        public /* bridge */ /* synthetic */ Object put(Integer num, Object obj) {
            return put2(num, (Integer) obj);
        }

        UnmodifiableMap(IntObjectMap<V> intObjectMap) {
            this.map = intObjectMap;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap
        public V get(int i) {
            return this.map.get(i);
        }

        @Override // io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap
        public V put(int i, V v) {
            throw new UnsupportedOperationException("put");
        }

        @Override // io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap
        public V remove(int i) {
            throw new UnsupportedOperationException("remove");
        }

        @Override // java.util.Map
        public int size() {
            return this.map.size();
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override // java.util.Map
        public void clear() {
            throw new UnsupportedOperationException("clear");
        }

        @Override // io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap
        public boolean containsKey(int i) {
            return this.map.containsKey(i);
        }

        @Override // java.util.Map
        public boolean containsValue(Object obj) {
            return this.map.containsValue(obj);
        }

        @Override // java.util.Map
        public boolean containsKey(Object obj) {
            return this.map.containsKey(obj);
        }

        @Override // java.util.Map
        public V get(Object obj) {
            return this.map.get(obj);
        }

        /* renamed from: put, reason: avoid collision after fix types in other method */
        public V put2(Integer num, V v) {
            throw new UnsupportedOperationException("put");
        }

        @Override // java.util.Map
        public V remove(Object obj) {
            throw new UnsupportedOperationException("remove");
        }

        @Override // java.util.Map
        public void putAll(Map<? extends Integer, ? extends V> map) {
            throw new UnsupportedOperationException("putAll");
        }

        @Override // io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap
        public Iterable<IntObjectMap.PrimitiveEntry<V>> entries() {
            if (this.entries == null) {
                this.entries = new Iterable<IntObjectMap.PrimitiveEntry<V>>() { // from class: io.grpc.netty.shaded.io.netty.util.collection.IntCollections.UnmodifiableMap.1
                    @Override // java.lang.Iterable
                    public Iterator<IntObjectMap.PrimitiveEntry<V>> iterator() {
                        UnmodifiableMap unmodifiableMap = UnmodifiableMap.this;
                        return new IteratorImpl(unmodifiableMap.map.entries().iterator());
                    }
                };
            }
            return this.entries;
        }

        @Override // java.util.Map
        public Set<Integer> keySet() {
            if (this.keySet == null) {
                this.keySet = Collections.unmodifiableSet(this.map.keySet());
            }
            return this.keySet;
        }

        @Override // java.util.Map
        public Set<Map.Entry<Integer, V>> entrySet() {
            if (this.entrySet == null) {
                this.entrySet = Collections.unmodifiableSet(this.map.entrySet());
            }
            return this.entrySet;
        }

        @Override // java.util.Map
        public Collection<V> values() {
            if (this.values == null) {
                this.values = Collections.unmodifiableCollection(this.map.values());
            }
            return this.values;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        private class IteratorImpl implements Iterator<IntObjectMap.PrimitiveEntry<V>> {
            final Iterator<IntObjectMap.PrimitiveEntry<V>> iter;

            IteratorImpl(Iterator<IntObjectMap.PrimitiveEntry<V>> it) {
                this.iter = it;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.iter.hasNext();
            }

            @Override // java.util.Iterator
            public IntObjectMap.PrimitiveEntry<V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return new EntryImpl(this.iter.next());
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        public class EntryImpl implements IntObjectMap.PrimitiveEntry<V> {
            private final IntObjectMap.PrimitiveEntry<V> entry;

            EntryImpl(IntObjectMap.PrimitiveEntry<V> primitiveEntry) {
                this.entry = primitiveEntry;
            }

            @Override // io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap.PrimitiveEntry
            public int key() {
                return this.entry.key();
            }

            @Override // io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap.PrimitiveEntry
            public V value() {
                return this.entry.value();
            }

            @Override // io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap.PrimitiveEntry
            public void setValue(V v) {
                throw new UnsupportedOperationException("setValue");
            }
        }
    }
}
