package com.google.protobuf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class MapField<K, V> implements MutabilityOracle {
    private final Converter<K, V> converter;
    private volatile boolean isMutable;
    private List<Message> listData;
    private MutatabilityAwareMap<K, V> mapData;
    private volatile StorageMode mode;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    public interface Converter<K, V> {
        Message convertKeyAndValueToMessage(K k, V v);

        void convertMessageToKeyAndValue(Message message, Map<K, V> map);

        Message getMessageDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    public enum StorageMode {
        MAP,
        LIST,
        BOTH
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    private static class ImmutableMessageConverter<K, V> implements Converter<K, V> {
        private final MapEntry<K, V> defaultEntry;

        public ImmutableMessageConverter(MapEntry<K, V> mapEntry) {
            this.defaultEntry = mapEntry;
        }

        @Override // com.google.protobuf.MapField.Converter
        public Message convertKeyAndValueToMessage(K k, V v) {
            return this.defaultEntry.newBuilderForType().setKey(k).setValue(v).buildPartial();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.MapField.Converter
        public void convertMessageToKeyAndValue(Message message, Map<K, V> map) {
            MapEntry mapEntry = (MapEntry) message;
            map.put(mapEntry.getKey(), mapEntry.getValue());
        }

        @Override // com.google.protobuf.MapField.Converter
        public Message getMessageDefaultInstance() {
            return this.defaultEntry;
        }
    }

    private MapField(Converter<K, V> converter, StorageMode storageMode, Map<K, V> map) {
        this.converter = converter;
        this.isMutable = true;
        this.mode = storageMode;
        this.mapData = new MutatabilityAwareMap<>(this, map);
        this.listData = null;
    }

    private MapField(MapEntry<K, V> mapEntry, StorageMode storageMode, Map<K, V> map) {
        this(new ImmutableMessageConverter(mapEntry), storageMode, map);
    }

    public static <K, V> MapField<K, V> emptyMapField(MapEntry<K, V> mapEntry) {
        return new MapField<>(mapEntry, StorageMode.MAP, Collections.emptyMap());
    }

    public static <K, V> MapField<K, V> newMapField(MapEntry<K, V> mapEntry) {
        return new MapField<>(mapEntry, StorageMode.MAP, new LinkedHashMap());
    }

    private Message convertKeyAndValueToMessage(K k, V v) {
        return this.converter.convertKeyAndValueToMessage(k, v);
    }

    private void convertMessageToKeyAndValue(Message message, Map<K, V> map) {
        this.converter.convertMessageToKeyAndValue(message, map);
    }

    private List<Message> convertMapToList(MutatabilityAwareMap<K, V> mutatabilityAwareMap) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<K, V> entry : mutatabilityAwareMap.entrySet()) {
            arrayList.add(convertKeyAndValueToMessage(entry.getKey(), entry.getValue()));
        }
        return arrayList;
    }

    private MutatabilityAwareMap<K, V> convertListToMap(List<Message> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<Message> it = list.iterator();
        while (it.hasNext()) {
            convertMessageToKeyAndValue(it.next(), linkedHashMap);
        }
        return new MutatabilityAwareMap<>(this, linkedHashMap);
    }

    public Map<K, V> getMap() {
        if (this.mode == StorageMode.LIST) {
            synchronized (this) {
                if (this.mode == StorageMode.LIST) {
                    this.mapData = convertListToMap(this.listData);
                    this.mode = StorageMode.BOTH;
                }
            }
        }
        return Collections.unmodifiableMap(this.mapData);
    }

    public Map<K, V> getMutableMap() {
        if (this.mode != StorageMode.MAP) {
            if (this.mode == StorageMode.LIST) {
                this.mapData = convertListToMap(this.listData);
            }
            this.listData = null;
            this.mode = StorageMode.MAP;
        }
        return this.mapData;
    }

    public void mergeFrom(MapField<K, V> mapField) {
        getMutableMap().putAll(MapFieldLite.copy((Map) mapField.getMap()));
    }

    public void clear() {
        this.mapData = new MutatabilityAwareMap<>(this, new LinkedHashMap());
        this.mode = StorageMode.MAP;
    }

    public boolean equals(Object obj) {
        if (obj instanceof MapField) {
            return MapFieldLite.equals((Map) getMap(), (Map) ((MapField) obj).getMap());
        }
        return false;
    }

    public int hashCode() {
        return MapFieldLite.calculateHashCodeForMap(getMap());
    }

    public MapField<K, V> copy() {
        return new MapField<>(this.converter, StorageMode.MAP, MapFieldLite.copy((Map) getMap()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Message> getList() {
        if (this.mode == StorageMode.MAP) {
            synchronized (this) {
                if (this.mode == StorageMode.MAP) {
                    this.listData = convertMapToList(this.mapData);
                    this.mode = StorageMode.BOTH;
                }
            }
        }
        return Collections.unmodifiableList(this.listData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Message> getMutableList() {
        if (this.mode != StorageMode.LIST) {
            if (this.mode == StorageMode.MAP) {
                this.listData = convertMapToList(this.mapData);
            }
            this.mapData = null;
            this.mode = StorageMode.LIST;
        }
        return this.listData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Message getMapEntryMessageDefaultInstance() {
        return this.converter.getMessageDefaultInstance();
    }

    public void makeImmutable() {
        this.isMutable = false;
    }

    public boolean isMutable() {
        return this.isMutable;
    }

    @Override // com.google.protobuf.MutabilityOracle
    public void ensureMutable() {
        if (!isMutable()) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    public static class MutatabilityAwareMap<K, V> implements Map<K, V> {
        private final Map<K, V> delegate;
        private final MutabilityOracle mutabilityOracle;

        MutatabilityAwareMap(MutabilityOracle mutabilityOracle, Map<K, V> map) {
            this.mutabilityOracle = mutabilityOracle;
            this.delegate = map;
        }

        @Override // java.util.Map
        public int size() {
            return this.delegate.size();
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            return this.delegate.isEmpty();
        }

        @Override // java.util.Map
        public boolean containsKey(Object obj) {
            return this.delegate.containsKey(obj);
        }

        @Override // java.util.Map
        public boolean containsValue(Object obj) {
            return this.delegate.containsValue(obj);
        }

        @Override // java.util.Map
        public V get(Object obj) {
            return this.delegate.get(obj);
        }

        @Override // java.util.Map
        public V put(K k, V v) {
            this.mutabilityOracle.ensureMutable();
            Internal.checkNotNull(k);
            Internal.checkNotNull(v);
            return this.delegate.put(k, v);
        }

        @Override // java.util.Map
        public V remove(Object obj) {
            this.mutabilityOracle.ensureMutable();
            return this.delegate.remove(obj);
        }

        @Override // java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            this.mutabilityOracle.ensureMutable();
            for (K k : map.keySet()) {
                Internal.checkNotNull(k);
                Internal.checkNotNull(map.get(k));
            }
            this.delegate.putAll(map);
        }

        @Override // java.util.Map
        public void clear() {
            this.mutabilityOracle.ensureMutable();
            this.delegate.clear();
        }

        @Override // java.util.Map
        public Set<K> keySet() {
            return new MutatabilityAwareSet(this.mutabilityOracle, this.delegate.keySet());
        }

        @Override // java.util.Map
        public Collection<V> values() {
            return new MutatabilityAwareCollection(this.mutabilityOracle, this.delegate.values());
        }

        @Override // java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            return new MutatabilityAwareSet(this.mutabilityOracle, this.delegate.entrySet());
        }

        @Override // java.util.Map
        public boolean equals(Object obj) {
            return this.delegate.equals(obj);
        }

        @Override // java.util.Map
        public int hashCode() {
            return this.delegate.hashCode();
        }

        public String toString() {
            return this.delegate.toString();
        }

        /* JADX WARN: Classes with same name are omitted:
          classes2.dex
          classes4.dex
         */
        /* loaded from: classes.dex */
        private static class MutatabilityAwareCollection<E> implements Collection<E> {
            private final Collection<E> delegate;
            private final MutabilityOracle mutabilityOracle;

            MutatabilityAwareCollection(MutabilityOracle mutabilityOracle, Collection<E> collection) {
                this.mutabilityOracle = mutabilityOracle;
                this.delegate = collection;
            }

            @Override // java.util.Collection
            public int size() {
                return this.delegate.size();
            }

            @Override // java.util.Collection
            public boolean isEmpty() {
                return this.delegate.isEmpty();
            }

            @Override // java.util.Collection
            public boolean contains(Object obj) {
                return this.delegate.contains(obj);
            }

            @Override // java.util.Collection, java.lang.Iterable
            public Iterator<E> iterator() {
                return new MutatabilityAwareIterator(this.mutabilityOracle, this.delegate.iterator());
            }

            @Override // java.util.Collection
            public Object[] toArray() {
                return this.delegate.toArray();
            }

            @Override // java.util.Collection
            public <T> T[] toArray(T[] tArr) {
                return (T[]) this.delegate.toArray(tArr);
            }

            @Override // java.util.Collection
            public boolean add(E e) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Collection
            public boolean remove(Object obj) {
                this.mutabilityOracle.ensureMutable();
                return this.delegate.remove(obj);
            }

            @Override // java.util.Collection
            public boolean containsAll(Collection<?> collection) {
                return this.delegate.containsAll(collection);
            }

            @Override // java.util.Collection
            public boolean addAll(Collection<? extends E> collection) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Collection
            public boolean removeAll(Collection<?> collection) {
                this.mutabilityOracle.ensureMutable();
                return this.delegate.removeAll(collection);
            }

            @Override // java.util.Collection
            public boolean retainAll(Collection<?> collection) {
                this.mutabilityOracle.ensureMutable();
                return this.delegate.retainAll(collection);
            }

            @Override // java.util.Collection
            public void clear() {
                this.mutabilityOracle.ensureMutable();
                this.delegate.clear();
            }

            @Override // java.util.Collection
            public boolean equals(Object obj) {
                return this.delegate.equals(obj);
            }

            @Override // java.util.Collection
            public int hashCode() {
                return this.delegate.hashCode();
            }

            public String toString() {
                return this.delegate.toString();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes2.dex
          classes4.dex
         */
        /* loaded from: classes.dex */
        public static class MutatabilityAwareSet<E> implements Set<E> {
            private final Set<E> delegate;
            private final MutabilityOracle mutabilityOracle;

            MutatabilityAwareSet(MutabilityOracle mutabilityOracle, Set<E> set) {
                this.mutabilityOracle = mutabilityOracle;
                this.delegate = set;
            }

            @Override // java.util.Set, java.util.Collection
            public int size() {
                return this.delegate.size();
            }

            @Override // java.util.Set, java.util.Collection
            public boolean isEmpty() {
                return this.delegate.isEmpty();
            }

            @Override // java.util.Set, java.util.Collection
            public boolean contains(Object obj) {
                return this.delegate.contains(obj);
            }

            @Override // java.util.Set, java.util.Collection, java.lang.Iterable
            public Iterator<E> iterator() {
                return new MutatabilityAwareIterator(this.mutabilityOracle, this.delegate.iterator());
            }

            @Override // java.util.Set, java.util.Collection
            public Object[] toArray() {
                return this.delegate.toArray();
            }

            @Override // java.util.Set, java.util.Collection
            public <T> T[] toArray(T[] tArr) {
                return (T[]) this.delegate.toArray(tArr);
            }

            @Override // java.util.Set, java.util.Collection
            public boolean add(E e) {
                this.mutabilityOracle.ensureMutable();
                return this.delegate.add(e);
            }

            @Override // java.util.Set, java.util.Collection
            public boolean remove(Object obj) {
                this.mutabilityOracle.ensureMutable();
                return this.delegate.remove(obj);
            }

            @Override // java.util.Set, java.util.Collection
            public boolean containsAll(Collection<?> collection) {
                return this.delegate.containsAll(collection);
            }

            @Override // java.util.Set, java.util.Collection
            public boolean addAll(Collection<? extends E> collection) {
                this.mutabilityOracle.ensureMutable();
                return this.delegate.addAll(collection);
            }

            @Override // java.util.Set, java.util.Collection
            public boolean retainAll(Collection<?> collection) {
                this.mutabilityOracle.ensureMutable();
                return this.delegate.retainAll(collection);
            }

            @Override // java.util.Set, java.util.Collection
            public boolean removeAll(Collection<?> collection) {
                this.mutabilityOracle.ensureMutable();
                return this.delegate.removeAll(collection);
            }

            @Override // java.util.Set, java.util.Collection
            public void clear() {
                this.mutabilityOracle.ensureMutable();
                this.delegate.clear();
            }

            @Override // java.util.Set, java.util.Collection
            public boolean equals(Object obj) {
                return this.delegate.equals(obj);
            }

            @Override // java.util.Set, java.util.Collection
            public int hashCode() {
                return this.delegate.hashCode();
            }

            public String toString() {
                return this.delegate.toString();
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes2.dex
          classes4.dex
         */
        /* loaded from: classes.dex */
        private static class MutatabilityAwareIterator<E> implements Iterator<E> {
            private final Iterator<E> delegate;
            private final MutabilityOracle mutabilityOracle;

            MutatabilityAwareIterator(MutabilityOracle mutabilityOracle, Iterator<E> it) {
                this.mutabilityOracle = mutabilityOracle;
                this.delegate = it;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.delegate.hasNext();
            }

            @Override // java.util.Iterator
            public E next() {
                return this.delegate.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                this.mutabilityOracle.ensureMutable();
                this.delegate.remove();
            }

            public boolean equals(Object obj) {
                return this.delegate.equals(obj);
            }

            public int hashCode() {
                return this.delegate.hashCode();
            }

            public String toString() {
                return this.delegate.toString();
            }
        }
    }
}
