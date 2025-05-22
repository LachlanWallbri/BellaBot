package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public class RegularImmutableMultiset<E> extends ImmutableMultiset<E> {
    static final ImmutableMultiset<Object> EMPTY = create(ImmutableList.m634of());
    static final double HASH_FLOODING_FPP = 0.001d;
    static final int MAX_HASH_BUCKET_LENGTH = 9;
    static final double MAX_LOAD_FACTOR = 1.0d;

    @LazyInit
    private transient ImmutableSet<E> elementSet;
    private final transient Multisets.ImmutableEntry<E>[] entries;
    private final transient int hashCode;
    private final transient Multisets.ImmutableEntry<E>[] hashTable;
    private final transient int size;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> ImmutableMultiset<E> create(Collection<? extends Multiset.Entry<? extends E>> collection) {
        Multisets.ImmutableEntry nonTerminalEntry;
        int size = collection.size();
        Multisets.ImmutableEntry[] immutableEntryArr = new Multisets.ImmutableEntry[size];
        if (size == 0) {
            return new RegularImmutableMultiset(immutableEntryArr, null, 0, 0, ImmutableSet.m676of());
        }
        int closedTableSize = Hashing.closedTableSize(size, 1.0d);
        int i = closedTableSize - 1;
        Multisets.ImmutableEntry[] immutableEntryArr2 = new Multisets.ImmutableEntry[closedTableSize];
        long j = 0;
        int i2 = 0;
        int i3 = 0;
        for (Multiset.Entry<? extends E> entry : collection) {
            Object checkNotNull = Preconditions.checkNotNull(entry.getElement());
            int count = entry.getCount();
            int hashCode = checkNotNull.hashCode();
            int smear = Hashing.smear(hashCode) & i;
            Multisets.ImmutableEntry immutableEntry = immutableEntryArr2[smear];
            if (immutableEntry == null) {
                if ((entry instanceof Multisets.ImmutableEntry) && !(entry instanceof NonTerminalEntry)) {
                    nonTerminalEntry = (Multisets.ImmutableEntry) entry;
                } else {
                    nonTerminalEntry = new Multisets.ImmutableEntry(checkNotNull, count);
                }
            } else {
                nonTerminalEntry = new NonTerminalEntry(checkNotNull, count, immutableEntry);
            }
            i2 += hashCode ^ count;
            immutableEntryArr[i3] = nonTerminalEntry;
            immutableEntryArr2[smear] = nonTerminalEntry;
            j += count;
            i3++;
        }
        if (hashFloodingDetected(immutableEntryArr2)) {
            return JdkBackedImmutableMultiset.create(ImmutableList.asImmutableList(immutableEntryArr));
        }
        return new RegularImmutableMultiset(immutableEntryArr, immutableEntryArr2, Ints.saturatedCast(j), i2, null);
    }

    /* loaded from: classes3.dex */
    private final class ElementSet extends IndexedImmutableSet<E> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        private ElementSet() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.IndexedImmutableSet
        public E get(int i) {
            return (E) RegularImmutableMultiset.this.contents.getKey(i);
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            return RegularImmutableMultiset.this.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return RegularImmutableMultiset.this.contents.size();
        }
    }

    private static boolean hashFloodingDetected(Multisets.ImmutableEntry<?>[] immutableEntryArr) {
        for (Multisets.ImmutableEntry<?> immutableEntry : immutableEntryArr) {
            int i = 0;
            for (; immutableEntry != null; immutableEntry = immutableEntry.nextInBucket()) {
                i++;
                if (i > 9) {
                    return true;
                }
            }
        }
        return false;
    }

    /* loaded from: classes3.dex */
    private static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final int[] counts;
        final Object[] elements;

        SerializedForm(Multiset<?> multiset) {
            int size = multiset.entrySet().size();
            this.elements = new Object[size];
            this.counts = new int[size];
            int i = 0;
            for (Multiset.Entry<?> entry : multiset.entrySet()) {
                this.elements[i] = entry.getElement();
                this.counts[i] = entry.getCount();
                i++;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        Object readResolve() {
            ImmutableMultiset.Builder builder = new ImmutableMultiset.Builder(this.elements.length);
            int i = 0;
            while (true) {
                Object[] objArr = this.elements;
                if (i < objArr.length) {
                    builder.addCopies(objArr[i], this.counts[i]);
                    i++;
                } else {
                    return builder.build();
                }
            }
        }
    }

    private RegularImmutableMultiset(Multisets.ImmutableEntry<E>[] immutableEntryArr, Multisets.ImmutableEntry<E>[] immutableEntryArr2, int i, int i2, ImmutableSet<E> immutableSet) {
        this.entries = immutableEntryArr;
        this.hashTable = immutableEntryArr2;
        this.size = i;
        this.hashCode = i2;
        this.elementSet = immutableSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class NonTerminalEntry<E> extends Multisets.ImmutableEntry<E> {
        private final Multisets.ImmutableEntry<E> nextInBucket;

        NonTerminalEntry(E e, int i, Multisets.ImmutableEntry<E> immutableEntry) {
            super(e, i);
            this.nextInBucket = immutableEntry;
        }

        @Override // com.google.common.collect.Multisets.ImmutableEntry
        public Multisets.ImmutableEntry<E> nextInBucket() {
            return this.nextInBucket;
        }
    }

    @Override // com.google.common.collect.Multiset
    public int count(Object obj) {
        Multisets.ImmutableEntry<E>[] immutableEntryArr = this.hashTable;
        if (obj != null && immutableEntryArr != null) {
            for (Multisets.ImmutableEntry<E> immutableEntry = immutableEntryArr[Hashing.smearedHash(obj) & (immutableEntryArr.length - 1)]; immutableEntry != null; immutableEntry = immutableEntry.nextInBucket()) {
                if (Objects.equal(obj, immutableEntry.getElement())) {
                    return immutableEntry.getCount();
                }
            }
        }
        return 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public ImmutableSet<E> elementSet() {
        ImmutableSet<E> immutableSet = this.elementSet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableMultiset.ElementSet elementSet = new ImmutableMultiset.ElementSet(Arrays.asList(this.entries), this);
        this.elementSet = elementSet;
        return elementSet;
    }

    @Override // com.google.common.collect.ImmutableMultiset
    Multiset.Entry<E> getEntry(int i) {
        return this.entries[i];
    }

    @Override // com.google.common.collect.ImmutableMultiset, java.util.Collection, com.google.common.collect.Multiset
    public int hashCode() {
        return this.hashCode;
    }
}
