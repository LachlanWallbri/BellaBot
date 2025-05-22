package io.netty.util.collection;

import io.netty.util.collection.IntObjectMap;
import io.netty.util.internal.MathUtil;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class IntObjectHashMap<V> implements IntObjectMap<V> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_CAPACITY = 8;
    public static final float DEFAULT_LOAD_FACTOR = 0.5f;
    private static final Object NULL_VALUE = new Object();
    private final Iterable<IntObjectMap.PrimitiveEntry<V>> entries;
    private final Set<Map.Entry<Integer, V>> entrySet;
    private final Set<Integer> keySet;
    private int[] keys;
    private final float loadFactor;
    private int mask;
    private int maxSize;
    private int size;
    private V[] values;

    private static int hashCode(int i) {
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Object put(Integer num, Object obj) {
        return put2(num, (Integer) obj);
    }

    public IntObjectHashMap() {
        this(8, 0.5f);
    }

    public IntObjectHashMap(int i) {
        this(i, 0.5f);
    }

    public IntObjectHashMap(int i, float f) {
        this.keySet = new KeySet();
        this.entrySet = new EntrySet();
        this.entries = new Iterable<IntObjectMap.PrimitiveEntry<V>>() { // from class: io.netty.util.collection.IntObjectHashMap.1
            @Override // java.lang.Iterable
            public Iterator<IntObjectMap.PrimitiveEntry<V>> iterator() {
                return new PrimitiveIterator();
            }
        };
        if (f <= 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and <= 1");
        }
        this.loadFactor = f;
        int safeFindNextPositivePowerOfTwo = MathUtil.safeFindNextPositivePowerOfTwo(i);
        this.mask = safeFindNextPositivePowerOfTwo - 1;
        this.keys = new int[safeFindNextPositivePowerOfTwo];
        this.values = (V[]) new Object[safeFindNextPositivePowerOfTwo];
        this.maxSize = calcMaxSize(safeFindNextPositivePowerOfTwo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T toExternal(T t) {
        if (t == NULL_VALUE) {
            return null;
        }
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T toInternal(T t) {
        return t == null ? (T) NULL_VALUE : t;
    }

    @Override // io.netty.util.collection.IntObjectMap
    public V get(int i) {
        int indexOf = indexOf(i);
        if (indexOf == -1) {
            return null;
        }
        return (V) toExternal(this.values[indexOf]);
    }

    @Override // io.netty.util.collection.IntObjectMap
    public V put(int i, V v) {
        int hashIndex = hashIndex(i);
        int i2 = hashIndex;
        do {
            Object[] objArr = this.values;
            if (objArr[i2] == null) {
                this.keys[i2] = i;
                objArr[i2] = toInternal(v);
                growSize();
                return null;
            }
            if (this.keys[i2] == i) {
                Object obj = objArr[i2];
                objArr[i2] = toInternal(v);
                return (V) toExternal(obj);
            }
            i2 = probeNext(i2);
        } while (i2 != hashIndex);
        throw new IllegalStateException("Unable to insert");
    }

    @Override // java.util.Map
    public void putAll(Map<? extends Integer, ? extends V> map) {
        if (map instanceof IntObjectHashMap) {
            IntObjectHashMap intObjectHashMap = (IntObjectHashMap) map;
            int i = 0;
            while (true) {
                V[] vArr = intObjectHashMap.values;
                if (i >= vArr.length) {
                    return;
                }
                V v = vArr[i];
                if (v != null) {
                    put(intObjectHashMap.keys[i], (int) v);
                }
                i++;
            }
        } else {
            for (Map.Entry<? extends Integer, ? extends V> entry : map.entrySet()) {
                put2(entry.getKey(), (Integer) entry.getValue());
            }
        }
    }

    @Override // io.netty.util.collection.IntObjectMap
    public V remove(int i) {
        int indexOf = indexOf(i);
        if (indexOf == -1) {
            return null;
        }
        V v = this.values[indexOf];
        removeAt(indexOf);
        return (V) toExternal(v);
    }

    @Override // java.util.Map
    public int size() {
        return this.size;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // java.util.Map
    public void clear() {
        Arrays.fill(this.keys, 0);
        Arrays.fill(this.values, (Object) null);
        this.size = 0;
    }

    @Override // io.netty.util.collection.IntObjectMap
    public boolean containsKey(int i) {
        return indexOf(i) >= 0;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        Object internal = toInternal(obj);
        for (V v : this.values) {
            if (v != null && v.equals(internal)) {
                return true;
            }
        }
        return false;
    }

    @Override // io.netty.util.collection.IntObjectMap
    public Iterable<IntObjectMap.PrimitiveEntry<V>> entries() {
        return this.entries;
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return new AbstractCollection<V>() { // from class: io.netty.util.collection.IntObjectHashMap.2
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
            public Iterator<V> iterator() {
                return new Iterator<V>() { // from class: io.netty.util.collection.IntObjectHashMap.2.1
                    final IntObjectHashMap<V>.PrimitiveIterator iter;

                    {
                        this.iter = new PrimitiveIterator();
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.iter.hasNext();
                    }

                    @Override // java.util.Iterator
                    public V next() {
                        return this.iter.next().value();
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection
            public int size() {
                return IntObjectHashMap.this.size;
            }
        };
    }

    @Override // java.util.Map
    public int hashCode() {
        int i = this.size;
        for (int i2 : this.keys) {
            i ^= hashCode(i2);
        }
        return i;
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IntObjectMap)) {
            return false;
        }
        IntObjectMap intObjectMap = (IntObjectMap) obj;
        if (this.size != intObjectMap.size()) {
            return false;
        }
        int i = 0;
        while (true) {
            V[] vArr = this.values;
            if (i >= vArr.length) {
                return true;
            }
            V v = vArr[i];
            if (v != null) {
                Object obj2 = intObjectMap.get(this.keys[i]);
                if (v == NULL_VALUE) {
                    if (obj2 != null) {
                        return false;
                    }
                } else if (!v.equals(obj2)) {
                    return false;
                }
            }
            i++;
        }
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return containsKey(objectToKey(obj));
    }

    @Override // java.util.Map
    public V get(Object obj) {
        return get(objectToKey(obj));
    }

    /* renamed from: put, reason: avoid collision after fix types in other method */
    public V put2(Integer num, V v) {
        return put(objectToKey(num), (int) v);
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        return remove(objectToKey(obj));
    }

    @Override // java.util.Map
    public Set<Integer> keySet() {
        return this.keySet;
    }

    @Override // java.util.Map
    public Set<Map.Entry<Integer, V>> entrySet() {
        return this.entrySet;
    }

    private int objectToKey(Object obj) {
        return ((Integer) obj).intValue();
    }

    private int indexOf(int i) {
        int hashIndex = hashIndex(i);
        int i2 = hashIndex;
        while (this.values[i2] != null) {
            if (i == this.keys[i2]) {
                return i2;
            }
            i2 = probeNext(i2);
            if (i2 == hashIndex) {
                return -1;
            }
        }
        return -1;
    }

    private int hashIndex(int i) {
        return hashCode(i) & this.mask;
    }

    private int probeNext(int i) {
        return (i + 1) & this.mask;
    }

    private void growSize() {
        int i = this.size + 1;
        this.size = i;
        if (i > this.maxSize) {
            int[] iArr = this.keys;
            if (iArr.length == Integer.MAX_VALUE) {
                throw new IllegalStateException("Max capacity reached at size=" + this.size);
            }
            rehash(iArr.length << 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean removeAt(int i) {
        this.size--;
        this.keys[i] = 0;
        this.values[i] = null;
        int probeNext = probeNext(i);
        V v = this.values[probeNext];
        int i2 = i;
        while (v != null) {
            int i3 = this.keys[probeNext];
            int hashIndex = hashIndex(i3);
            if ((probeNext < hashIndex && (hashIndex <= i2 || i2 <= probeNext)) || (hashIndex <= i2 && i2 <= probeNext)) {
                int[] iArr = this.keys;
                iArr[i2] = i3;
                V[] vArr = this.values;
                vArr[i2] = v;
                iArr[probeNext] = 0;
                vArr[probeNext] = null;
                i2 = probeNext;
            }
            V[] vArr2 = this.values;
            probeNext = probeNext(probeNext);
            v = vArr2[probeNext];
        }
        return i2 != i;
    }

    private int calcMaxSize(int i) {
        return Math.min(i - 1, (int) (i * this.loadFactor));
    }

    private void rehash(int i) {
        V[] vArr;
        int[] iArr = this.keys;
        V[] vArr2 = this.values;
        this.keys = new int[i];
        this.values = (V[]) new Object[i];
        this.maxSize = calcMaxSize(i);
        this.mask = i - 1;
        for (int i2 = 0; i2 < vArr2.length; i2++) {
            V v = vArr2[i2];
            if (v != null) {
                int i3 = iArr[i2];
                int hashIndex = hashIndex(i3);
                while (true) {
                    vArr = this.values;
                    if (vArr[hashIndex] == null) {
                        break;
                    } else {
                        hashIndex = probeNext(hashIndex);
                    }
                }
                this.keys[hashIndex] = i3;
                vArr[hashIndex] = v;
            }
        }
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.size * 4);
        sb.append('{');
        boolean z = true;
        int i = 0;
        while (true) {
            V[] vArr = this.values;
            if (i < vArr.length) {
                V v = vArr[i];
                if (v != null) {
                    if (!z) {
                        sb.append(", ");
                    }
                    sb.append(keyToString(this.keys[i]));
                    sb.append('=');
                    sb.append(v == this ? "(this Map)" : toExternal(v));
                    z = false;
                }
                i++;
            } else {
                sb.append('}');
                return sb.toString();
            }
        }
    }

    protected String keyToString(int i) {
        return Integer.toString(i);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private final class EntrySet extends AbstractSet<Map.Entry<Integer, V>> {
        private EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<Integer, V>> iterator() {
            return new MapIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return IntObjectHashMap.this.size();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private final class KeySet extends AbstractSet<Integer> {
        private KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return IntObjectHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return IntObjectHashMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return IntObjectHashMap.this.remove(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            Iterator<IntObjectMap.PrimitiveEntry<V>> it = IntObjectHashMap.this.entries().iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (!collection.contains(Integer.valueOf(it.next().key()))) {
                    z = true;
                    it.remove();
                }
            }
            return z;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            IntObjectHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() { // from class: io.netty.util.collection.IntObjectHashMap.KeySet.1
                private final Iterator<Map.Entry<Integer, V>> iter;

                {
                    this.iter = IntObjectHashMap.this.entrySet.iterator();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.iter.hasNext();
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Iterator
                public Integer next() {
                    return this.iter.next().getKey();
                }

                @Override // java.util.Iterator
                public void remove() {
                    this.iter.remove();
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public final class PrimitiveIterator implements Iterator<IntObjectMap.PrimitiveEntry<V>>, IntObjectMap.PrimitiveEntry<V> {
        private int entryIndex;
        private int nextIndex;
        private int prevIndex;

        private PrimitiveIterator() {
            this.prevIndex = -1;
            this.nextIndex = -1;
            this.entryIndex = -1;
        }

        private void scanNext() {
            do {
                int i = this.nextIndex + 1;
                this.nextIndex = i;
                if (i == IntObjectHashMap.this.values.length) {
                    return;
                }
            } while (IntObjectHashMap.this.values[this.nextIndex] == null);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.nextIndex == -1) {
                scanNext();
            }
            return this.nextIndex != IntObjectHashMap.this.values.length;
        }

        @Override // java.util.Iterator
        public IntObjectMap.PrimitiveEntry<V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.prevIndex = this.nextIndex;
            scanNext();
            this.entryIndex = this.prevIndex;
            return this;
        }

        @Override // java.util.Iterator
        public void remove() {
            int i = this.prevIndex;
            if (i != -1) {
                if (IntObjectHashMap.this.removeAt(i)) {
                    this.nextIndex = this.prevIndex;
                }
                this.prevIndex = -1;
                return;
            }
            throw new IllegalStateException("next must be called before each remove.");
        }

        @Override // io.netty.util.collection.IntObjectMap.PrimitiveEntry
        public int key() {
            return IntObjectHashMap.this.keys[this.entryIndex];
        }

        @Override // io.netty.util.collection.IntObjectMap.PrimitiveEntry
        public V value() {
            return (V) IntObjectHashMap.toExternal(IntObjectHashMap.this.values[this.entryIndex]);
        }

        @Override // io.netty.util.collection.IntObjectMap.PrimitiveEntry
        public void setValue(V v) {
            IntObjectHashMap.this.values[this.entryIndex] = IntObjectHashMap.toInternal(v);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private final class MapIterator implements Iterator<Map.Entry<Integer, V>> {
        private final IntObjectHashMap<V>.PrimitiveIterator iter;

        private MapIterator() {
            this.iter = new PrimitiveIterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iter.hasNext();
        }

        @Override // java.util.Iterator
        public Map.Entry<Integer, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.iter.next();
            return new MapEntry(((PrimitiveIterator) this.iter).entryIndex);
        }

        @Override // java.util.Iterator
        public void remove() {
            this.iter.remove();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public final class MapEntry implements Map.Entry<Integer, V> {
        private final int entryIndex;

        MapEntry(int i) {
            this.entryIndex = i;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        public Integer getKey() {
            verifyExists();
            return Integer.valueOf(IntObjectHashMap.this.keys[this.entryIndex]);
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            verifyExists();
            return (V) IntObjectHashMap.toExternal(IntObjectHashMap.this.values[this.entryIndex]);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            verifyExists();
            V v2 = (V) IntObjectHashMap.toExternal(IntObjectHashMap.this.values[this.entryIndex]);
            IntObjectHashMap.this.values[this.entryIndex] = IntObjectHashMap.toInternal(v);
            return v2;
        }

        private void verifyExists() {
            if (IntObjectHashMap.this.values[this.entryIndex] == null) {
                throw new IllegalStateException("The map entry has been removed");
            }
        }
    }
}
