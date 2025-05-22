package org.jboss.netty.util.internal;

import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes7.dex */
public final class ConcurrentHashMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V> {
    static final int DEFAULT_CONCURRENCY_LEVEL = 16;
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int MAXIMUM_CAPACITY = 1073741824;
    static final int MAX_SEGMENTS = 65536;
    static final int RETRIES_BEFORE_LOCK = 2;
    Set<Map.Entry<K, V>> entrySet;
    Set<K> keySet;
    final int segmentMask;
    final int segmentShift;
    final Segment<K, V>[] segments;
    Collection<V> values;

    private static int hash(int i) {
        int i2 = i + ((i << 15) ^ (-12931));
        int i3 = i2 ^ (i2 >>> 10);
        int i4 = i3 + (i3 << 3);
        int i5 = i4 ^ (i4 >>> 6);
        int i6 = i5 + (i5 << 2) + (i5 << 14);
        return i6 ^ (i6 >>> 16);
    }

    Segment<K, V> segmentFor(int i) {
        return this.segments[(i >>> this.segmentShift) & this.segmentMask];
    }

    private static int hashOf(Object obj) {
        return hash(obj.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static final class HashEntry<K, V> {
        final int hash;
        final Object key;
        final HashEntry<K, V> next;
        volatile Object value;

        HashEntry(K k, int i, HashEntry<K, V> hashEntry, V v) {
            this.hash = i;
            this.next = hashEntry;
            this.key = k;
            this.value = v;
        }

        K key() {
            return (K) this.key;
        }

        V value() {
            return (V) this.value;
        }

        void setValue(V v) {
            this.value = v;
        }

        static <K, V> HashEntry<K, V>[] newArray(int i) {
            return new HashEntry[i];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static final class Segment<K, V> extends ReentrantLock {
        private static final long serialVersionUID = -2001752926705396395L;
        volatile transient int count;
        final float loadFactor;
        int modCount;
        volatile transient HashEntry<K, V>[] table;
        int threshold;

        Segment(int i, float f) {
            this.loadFactor = f;
            setTable(HashEntry.newArray(i));
        }

        static <K, V> Segment<K, V>[] newArray(int i) {
            return new Segment[i];
        }

        private static boolean keyEq(Object obj, Object obj2) {
            return obj.equals(obj2);
        }

        void setTable(HashEntry<K, V>[] hashEntryArr) {
            this.threshold = (int) (hashEntryArr.length * this.loadFactor);
            this.table = hashEntryArr;
        }

        HashEntry<K, V> getFirst(int i) {
            return this.table[i & (r0.length - 1)];
        }

        HashEntry<K, V> newHashEntry(K k, int i, HashEntry<K, V> hashEntry, V v) {
            return new HashEntry<>(k, i, hashEntry, v);
        }

        V readValueUnderLock(HashEntry<K, V> hashEntry) {
            lock();
            try {
                return hashEntry.value();
            } finally {
                unlock();
            }
        }

        V get(Object obj, int i) {
            if (this.count == 0) {
                return null;
            }
            for (HashEntry<K, V> first = getFirst(i); first != null; first = first.next) {
                if (first.hash == i && keyEq(obj, first.key())) {
                    V value = first.value();
                    return value != null ? value : readValueUnderLock(first);
                }
            }
            return null;
        }

        boolean containsKey(Object obj, int i) {
            if (this.count == 0) {
                return false;
            }
            for (HashEntry<K, V> first = getFirst(i); first != null; first = first.next) {
                if (first.hash == i && keyEq(obj, first.key())) {
                    return true;
                }
            }
            return false;
        }

        boolean containsValue(Object obj) {
            if (this.count != 0) {
                for (HashEntry<K, V> hashEntry : this.table) {
                    for (; hashEntry != null; hashEntry = hashEntry.next) {
                        V value = hashEntry.value();
                        if (value == null) {
                            value = readValueUnderLock(hashEntry);
                        }
                        if (obj.equals(value)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        boolean replace(K k, int i, V v, V v2) {
            lock();
            try {
                HashEntry<K, V> first = getFirst(i);
                while (first != null && (first.hash != i || !keyEq(k, first.key()))) {
                    first = first.next;
                }
                boolean z = false;
                if (first != null && v.equals(first.value())) {
                    z = true;
                    first.setValue(v2);
                }
                return z;
            } finally {
                unlock();
            }
        }

        V replace(K k, int i, V v) {
            lock();
            try {
                HashEntry<K, V> first = getFirst(i);
                while (first != null && (first.hash != i || !keyEq(k, first.key()))) {
                    first = first.next;
                }
                V v2 = null;
                if (first != null) {
                    v2 = first.value();
                    first.setValue(v);
                }
                return v2;
            } finally {
                unlock();
            }
        }

        V put(K k, int i, V v, boolean z) {
            V v2;
            int rehash;
            lock();
            try {
                int i2 = this.count;
                int i3 = i2 + 1;
                if (i2 > this.threshold && (rehash = rehash()) > 0) {
                    i3 -= rehash;
                    this.count = i3 - 1;
                }
                HashEntry<K, V>[] hashEntryArr = this.table;
                int length = (hashEntryArr.length - 1) & i;
                HashEntry<K, V> hashEntry = hashEntryArr[length];
                HashEntry<K, V> hashEntry2 = hashEntry;
                while (hashEntry2 != null && (hashEntry2.hash != i || !keyEq(k, hashEntry2.key()))) {
                    hashEntry2 = hashEntry2.next;
                }
                if (hashEntry2 != null) {
                    v2 = hashEntry2.value();
                    if (!z) {
                        hashEntry2.setValue(v);
                    }
                } else {
                    this.modCount++;
                    hashEntryArr[length] = newHashEntry(k, i, hashEntry, v);
                    this.count = i3;
                    v2 = null;
                }
                return v2;
            } finally {
                unlock();
            }
        }

        int rehash() {
            HashEntry<K, V>[] hashEntryArr = this.table;
            int length = hashEntryArr.length;
            if (length >= 1073741824) {
                return 0;
            }
            HashEntry<K, V>[] newArray = HashEntry.newArray(length << 1);
            this.threshold = (int) (newArray.length * this.loadFactor);
            int length2 = newArray.length - 1;
            int i = 0;
            for (HashEntry<K, V> hashEntry : hashEntryArr) {
                if (hashEntry != null) {
                    HashEntry<K, V> hashEntry2 = hashEntry.next;
                    int i2 = hashEntry.hash & length2;
                    if (hashEntry2 == null) {
                        newArray[i2] = hashEntry;
                    } else {
                        HashEntry<K, V> hashEntry3 = hashEntry;
                        while (hashEntry2 != null) {
                            int i3 = hashEntry2.hash & length2;
                            if (i3 != i2) {
                                hashEntry3 = hashEntry2;
                                i2 = i3;
                            }
                            hashEntry2 = hashEntry2.next;
                        }
                        newArray[i2] = hashEntry3;
                        while (hashEntry != hashEntry3) {
                            K key = hashEntry.key();
                            if (key == null) {
                                i++;
                            } else {
                                int i4 = hashEntry.hash & length2;
                                newArray[i4] = newHashEntry(key, hashEntry.hash, newArray[i4], hashEntry.value());
                            }
                            hashEntry = hashEntry.next;
                        }
                    }
                }
            }
            this.table = newArray;
            return i;
        }

        V remove(Object obj, int i, Object obj2, boolean z) {
            lock();
            try {
                int i2 = this.count - 1;
                HashEntry<K, V>[] hashEntryArr = this.table;
                int length = (hashEntryArr.length - 1) & i;
                HashEntry<K, V> hashEntry = hashEntryArr[length];
                HashEntry<K, V> hashEntry2 = hashEntry;
                while (hashEntry2 != null && obj != hashEntry2.key && (z || i != hashEntry2.hash || !keyEq(obj, hashEntry2.key()))) {
                    hashEntry2 = hashEntry2.next;
                }
                V v = null;
                if (hashEntry2 != null) {
                    V value = hashEntry2.value();
                    if (obj2 == null || obj2.equals(value)) {
                        this.modCount++;
                        HashEntry<K, V> hashEntry3 = hashEntry2.next;
                        while (hashEntry != hashEntry2) {
                            K key = hashEntry.key();
                            if (key == null) {
                                i2--;
                            } else {
                                hashEntry3 = newHashEntry(key, hashEntry.hash, hashEntry3, hashEntry.value());
                            }
                            hashEntry = hashEntry.next;
                        }
                        hashEntryArr[length] = hashEntry3;
                        this.count = i2;
                        v = value;
                    }
                }
                return v;
            } finally {
                unlock();
            }
        }

        void clear() {
            if (this.count != 0) {
                lock();
                try {
                    HashEntry<K, V>[] hashEntryArr = this.table;
                    for (int i = 0; i < hashEntryArr.length; i++) {
                        hashEntryArr[i] = null;
                    }
                    this.modCount++;
                    this.count = 0;
                } finally {
                    unlock();
                }
            }
        }
    }

    public ConcurrentHashMap(int i, float f, int i2) {
        if (f <= 0.0f || i < 0 || i2 <= 0) {
            throw new IllegalArgumentException();
        }
        int i3 = 0;
        int i4 = 1;
        int i5 = 0;
        int i6 = 1;
        while (i6 < (i2 > 65536 ? 65536 : i2)) {
            i5++;
            i6 <<= 1;
        }
        this.segmentShift = 32 - i5;
        this.segmentMask = i6 - 1;
        this.segments = Segment.newArray(i6);
        i = i > 1073741824 ? 1073741824 : i;
        int i7 = i / i6;
        while (i4 < (i6 * i7 < i ? i7 + 1 : i7)) {
            i4 <<= 1;
        }
        while (true) {
            Segment<K, V>[] segmentArr = this.segments;
            if (i3 >= segmentArr.length) {
                return;
            }
            segmentArr[i3] = new Segment<>(i4, f);
            i3++;
        }
    }

    public ConcurrentHashMap(int i, float f) {
        this(i, f, 16);
    }

    public ConcurrentHashMap(int i) {
        this(i, 0.75f, 16);
    }

    public ConcurrentHashMap() {
        this(16, 0.75f, 16);
    }

    public ConcurrentHashMap(Map<? extends K, ? extends V> map) {
        this(Math.max(((int) (map.size() / 0.75f)) + 1, 16), 0.75f, 16);
        putAll(map);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        Segment<K, V>[] segmentArr = this.segments;
        int[] iArr = new int[segmentArr.length];
        int i = 0;
        for (int i2 = 0; i2 < segmentArr.length; i2++) {
            if (segmentArr[i2].count != 0) {
                return false;
            }
            int i3 = segmentArr[i2].modCount;
            iArr[i2] = i3;
            i += i3;
        }
        if (i == 0) {
            return true;
        }
        for (int i4 = 0; i4 < segmentArr.length; i4++) {
            if (segmentArr[i4].count != 0 || iArr[i4] != segmentArr[i4].modCount) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        long j;
        long j2;
        Segment<K, V>[] segmentArr = this.segments;
        int[] iArr = new int[segmentArr.length];
        long j3 = 0;
        long j4 = 0;
        int i = 0;
        while (true) {
            if (i >= 2) {
                long j5 = j3;
                j = j4;
                j2 = j5;
                break;
            }
            j2 = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < segmentArr.length; i3++) {
                j2 += segmentArr[i3].count;
                int i4 = segmentArr[i3].modCount;
                iArr[i3] = i4;
                i2 += i4;
            }
            if (i2 != 0) {
                long j6 = 0;
                int i5 = 0;
                while (true) {
                    if (i5 >= segmentArr.length) {
                        j = j6;
                        break;
                    }
                    j6 += segmentArr[i5].count;
                    if (iArr[i5] != segmentArr[i5].modCount) {
                        j = -1;
                        break;
                    }
                    i5++;
                }
            } else {
                j = 0;
            }
            if (j == j2) {
                break;
            }
            i++;
            long j7 = j;
            j3 = j2;
            j4 = j7;
        }
        if (j != j2) {
            for (Segment<K, V> segment : segmentArr) {
                segment.lock();
            }
            j2 = 0;
            for (Segment<K, V> segment2 : segmentArr) {
                j2 += segment2.count;
            }
            for (Segment<K, V> segment3 : segmentArr) {
                segment3.unlock();
            }
        }
        if (j2 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        int hashOf = hashOf(obj);
        return segmentFor(hashOf).get(obj, hashOf);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        int hashOf = hashOf(obj);
        return segmentFor(hashOf).containsKey(obj, hashOf);
    }

    /* JADX WARN: Finally extract failed */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        Segment<K, V>[] segmentArr = this.segments;
        int[] iArr = new int[segmentArr.length];
        int i = 0;
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (i2 < 2) {
                int i3 = 0;
                for (int i4 = 0; i4 < segmentArr.length; i4++) {
                    int i5 = segmentArr[i4].modCount;
                    iArr[i4] = i5;
                    i3 += i5;
                    if (segmentArr[i4].containsValue(obj)) {
                        return true;
                    }
                }
                if (i3 != 0) {
                    int i6 = 0;
                    while (true) {
                        if (i6 >= segmentArr.length) {
                            break;
                        }
                        if (iArr[i6] != segmentArr[i6].modCount) {
                            z = false;
                            break;
                        }
                        i6++;
                    }
                }
                if (z) {
                    return false;
                }
                i2++;
            } else {
                for (Segment<K, V> segment : segmentArr) {
                    segment.lock();
                }
                int i7 = 0;
                while (true) {
                    try {
                        if (i7 >= segmentArr.length) {
                            z = false;
                            break;
                        }
                        if (segmentArr[i7].containsValue(obj)) {
                            break;
                        }
                        i7++;
                    } catch (Throwable th) {
                        while (i < segmentArr.length) {
                            segmentArr[i].unlock();
                            i++;
                        }
                        throw th;
                    }
                }
                while (i < segmentArr.length) {
                    segmentArr[i].unlock();
                    i++;
                }
                return z;
            }
        }
    }

    public boolean contains(Object obj) {
        return containsValue(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (v == null) {
            throw new NullPointerException();
        }
        int hashOf = hashOf(k);
        return segmentFor(hashOf).put(k, hashOf, v, false);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V putIfAbsent(K k, V v) {
        if (v == null) {
            throw new NullPointerException();
        }
        int hashOf = hashOf(k);
        return segmentFor(hashOf).put(k, hashOf, v, true);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        int hashOf = hashOf(obj);
        return segmentFor(hashOf).remove(obj, hashOf, null, false);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean remove(Object obj, Object obj2) {
        int hashOf = hashOf(obj);
        return (obj2 == null || segmentFor(hashOf).remove(obj, hashOf, obj2, false) == null) ? false : true;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean replace(K k, V v, V v2) {
        if (v == null || v2 == null) {
            throw new NullPointerException();
        }
        int hashOf = hashOf(k);
        return segmentFor(hashOf).replace(k, hashOf, v, v2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V replace(K k, V v) {
        if (v == null) {
            throw new NullPointerException();
        }
        int hashOf = hashOf(k);
        return segmentFor(hashOf).replace(k, hashOf, v);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        int i = 0;
        while (true) {
            Segment<K, V>[] segmentArr = this.segments;
            if (i >= segmentArr.length) {
                return;
            }
            segmentArr[i].clear();
            i++;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        KeySet keySet = new KeySet();
        this.keySet = keySet;
        return keySet;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        Values values = new Values();
        this.values = values;
        return values;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet = new EntrySet();
        this.entrySet = entrySet;
        return entrySet;
    }

    public Enumeration<K> keys() {
        return new KeyIterator();
    }

    public Enumeration<V> elements() {
        return new ValueIterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public abstract class HashIterator {
        K currentKey;
        HashEntry<K, V>[] currentTable;
        HashEntry<K, V> lastReturned;
        HashEntry<K, V> nextEntry;
        int nextSegmentIndex;
        int nextTableIndex = -1;

        HashIterator() {
            this.nextSegmentIndex = ConcurrentHashMap.this.segments.length - 1;
            advance();
        }

        public void rewind() {
            this.nextSegmentIndex = ConcurrentHashMap.this.segments.length - 1;
            this.nextTableIndex = -1;
            this.currentTable = null;
            this.nextEntry = null;
            this.lastReturned = null;
            this.currentKey = null;
            advance();
        }

        public boolean hasMoreElements() {
            return hasNext();
        }

        final void advance() {
            HashEntry<K, V> hashEntry;
            HashEntry<K, V> hashEntry2 = this.nextEntry;
            if (hashEntry2 != null) {
                HashEntry<K, V> hashEntry3 = hashEntry2.next;
                this.nextEntry = hashEntry3;
                if (hashEntry3 != null) {
                    return;
                }
            }
            do {
                int i = this.nextTableIndex;
                if (i >= 0) {
                    HashEntry<K, V>[] hashEntryArr = this.currentTable;
                    this.nextTableIndex = i - 1;
                    hashEntry = hashEntryArr[i];
                    this.nextEntry = hashEntry;
                } else {
                    while (this.nextSegmentIndex >= 0) {
                        Segment<K, V>[] segmentArr = ConcurrentHashMap.this.segments;
                        int i2 = this.nextSegmentIndex;
                        this.nextSegmentIndex = i2 - 1;
                        Segment<K, V> segment = segmentArr[i2];
                        if (segment.count != 0) {
                            this.currentTable = segment.table;
                            for (int length = this.currentTable.length - 1; length >= 0; length--) {
                                HashEntry<K, V> hashEntry4 = this.currentTable[length];
                                this.nextEntry = hashEntry4;
                                if (hashEntry4 != null) {
                                    this.nextTableIndex = length - 1;
                                    return;
                                }
                            }
                        }
                    }
                    return;
                }
            } while (hashEntry == null);
        }

        public boolean hasNext() {
            while (true) {
                HashEntry<K, V> hashEntry = this.nextEntry;
                if (hashEntry == null) {
                    return false;
                }
                if (hashEntry.key() != null) {
                    return true;
                }
                advance();
            }
        }

        HashEntry<K, V> nextEntry() {
            do {
                HashEntry<K, V> hashEntry = this.nextEntry;
                if (hashEntry == null) {
                    throw new NoSuchElementException();
                }
                this.lastReturned = hashEntry;
                this.currentKey = this.lastReturned.key();
                advance();
            } while (this.currentKey == null);
            return this.lastReturned;
        }

        public void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            }
            ConcurrentHashMap.this.remove(this.currentKey);
            this.lastReturned = null;
        }
    }

    /* loaded from: classes7.dex */
    final class KeyIterator extends ConcurrentHashMap<K, V>.HashIterator implements ReusableIterator<K>, Enumeration<K> {
        KeyIterator() {
            super();
        }

        @Override // java.util.Iterator
        public K next() {
            return super.nextEntry().key();
        }

        @Override // java.util.Enumeration
        public K nextElement() {
            return super.nextEntry().key();
        }
    }

    /* loaded from: classes7.dex */
    final class ValueIterator extends ConcurrentHashMap<K, V>.HashIterator implements ReusableIterator<V>, Enumeration<V> {
        ValueIterator() {
            super();
        }

        @Override // java.util.Iterator
        public V next() {
            return super.nextEntry().value();
        }

        @Override // java.util.Enumeration
        public V nextElement() {
            return super.nextEntry().value();
        }
    }

    /* loaded from: classes7.dex */
    static class SimpleEntry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        public SimpleEntry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public SimpleEntry(Map.Entry<? extends K, ? extends V> entry) {
            this.key = entry.getKey();
            this.value = entry.getValue();
        }

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
            V v2 = this.value;
            this.value = v;
            return v2;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return m4160eq(this.key, entry.getKey()) && m4160eq(this.value, entry.getValue());
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.key;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.value;
            return hashCode ^ (v != null ? v.hashCode() : 0);
        }

        public String toString() {
            return this.key + "=" + this.value;
        }

        /* renamed from: eq */
        private static boolean m4160eq(Object obj, Object obj2) {
            if (obj == null) {
                return obj2 == null;
            }
            return obj.equals(obj2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public final class WriteThroughEntry extends SimpleEntry<K, V> {
        WriteThroughEntry(K k, V v) {
            super(k, v);
        }

        @Override // org.jboss.netty.util.internal.ConcurrentHashMap.SimpleEntry, java.util.Map.Entry
        public V setValue(V v) {
            if (v == null) {
                throw new NullPointerException();
            }
            V v2 = (V) super.setValue(v);
            ConcurrentHashMap.this.put(getKey(), v);
            return v2;
        }
    }

    /* loaded from: classes7.dex */
    final class EntryIterator extends ConcurrentHashMap<K, V>.HashIterator implements ReusableIterator<Map.Entry<K, V>> {
        EntryIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            HashEntry<K, V> nextEntry = super.nextEntry();
            return new WriteThroughEntry(nextEntry.key(), nextEntry.value());
        }
    }

    /* loaded from: classes7.dex */
    final class KeySet extends AbstractSet<K> {
        KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return ConcurrentHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return ConcurrentHashMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return ConcurrentHashMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return ConcurrentHashMap.this.remove(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            ConcurrentHashMap.this.clear();
        }
    }

    /* loaded from: classes7.dex */
    final class Values extends AbstractCollection<V> {
        Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return ConcurrentHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return ConcurrentHashMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return ConcurrentHashMap.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            ConcurrentHashMap.this.clear();
        }
    }

    /* loaded from: classes7.dex */
    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = ConcurrentHashMap.this.get(entry.getKey());
            return obj2 != null && obj2.equals(entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return ConcurrentHashMap.this.remove(entry.getKey(), entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return ConcurrentHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return ConcurrentHashMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            ConcurrentHashMap.this.clear();
        }
    }
}
