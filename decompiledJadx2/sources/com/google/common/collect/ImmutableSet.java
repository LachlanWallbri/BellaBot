package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.Collector;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {
    private static final int CUTOFF = 751619276;
    private static final double DESIRED_LOAD_FACTOR = 0.7d;
    static final double HASH_FLOODING_FPP = 0.001d;
    static final int MAX_RUN_MULTIPLIER = 13;
    static final int MAX_TABLE_SIZE = 1073741824;
    static final int SPLITERATOR_CHARACTERISTICS = 1297;

    @LazyInit
    private transient ImmutableList<E> asList;

    boolean isHashCodeFast() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public abstract UnmodifiableIterator<E> iterator();

    public static <E> Collector<E, ?, ImmutableSet<E>> toImmutableSet() {
        return CollectCollectors.toImmutableSet();
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m676of() {
        return RegularImmutableSet.EMPTY;
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m677of(E e) {
        return new SingletonImmutableSet(e);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m678of(E e, E e2) {
        return construct(2, 2, e, e2);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m679of(E e, E e2, E e3) {
        return construct(3, 3, e, e2, e3);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m680of(E e, E e2, E e3, E e4) {
        return construct(4, 4, e, e2, e3, e4);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m681of(E e, E e2, E e3, E e4, E e5) {
        return construct(5, 5, e, e2, e3, e4, e5);
    }

    @SafeVarargs
    /* renamed from: of */
    public static <E> ImmutableSet<E> m682of(E e, E e2, E e3, E e4, E e5, E e6, E... eArr) {
        Preconditions.checkArgument(eArr.length <= 2147483641, "the total number of elements must fit in an int");
        Object[] objArr = new Object[eArr.length + 6];
        objArr[0] = e;
        objArr[1] = e2;
        objArr[2] = e3;
        objArr[3] = e4;
        objArr[4] = e5;
        objArr[5] = e6;
        System.arraycopy(eArr, 0, objArr, 6, eArr.length);
        return construct(objArr.length, objArr.length, objArr);
    }

    private static <E> ImmutableSet<E> constructUnknownDuplication(int i, Object... objArr) {
        return construct(i, Math.max(4, IntMath.sqrt(i, RoundingMode.CEILING)), objArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <E> ImmutableSet<E> construct(int i, int i2, Object... objArr) {
        if (i == 0) {
            return m676of();
        }
        int i3 = 0;
        if (i == 1) {
            return m677of(objArr[0]);
        }
        SetBuilderImpl setBuilderImpl = new RegularSetBuilderImpl(i2);
        while (i3 < i) {
            SetBuilderImpl add = setBuilderImpl.add(Preconditions.checkNotNull(objArr[i3]));
            i3++;
            setBuilderImpl = add;
        }
        return setBuilderImpl.review().build();
    }

    public static <E> ImmutableSet<E> copyOf(Collection<? extends E> collection) {
        if ((collection instanceof ImmutableSet) && !(collection instanceof SortedSet)) {
            ImmutableSet<E> immutableSet = (ImmutableSet) collection;
            if (!immutableSet.isPartialView()) {
                return immutableSet;
            }
        } else if (collection instanceof EnumSet) {
            return copyOfEnumSet((EnumSet) collection);
        }
        Object[] array = collection.toArray();
        if (collection instanceof Set) {
            return construct(array.length, array.length, array);
        }
        return constructUnknownDuplication(array.length, array);
    }

    public static <E> ImmutableSet<E> copyOf(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection) iterable);
        }
        return copyOf(iterable.iterator());
    }

    public static <E> ImmutableSet<E> copyOf(Iterator<? extends E> it) {
        if (!it.hasNext()) {
            return m676of();
        }
        E next = it.next();
        if (!it.hasNext()) {
            return m677of((Object) next);
        }
        return new Builder().add((Builder) next).addAll((Iterator) it).build();
    }

    public static <E> ImmutableSet<E> copyOf(E[] eArr) {
        int length = eArr.length;
        if (length == 0) {
            return m676of();
        }
        if (length == 1) {
            return m677of((Object) eArr[0]);
        }
        return constructUnknownDuplication(eArr.length, (Object[]) eArr.clone());
    }

    private static ImmutableSet copyOfEnumSet(EnumSet enumSet) {
        return ImmutableEnumSet.asImmutable(EnumSet.copyOf(enumSet));
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof ImmutableSet) && isHashCodeFast() && ((ImmutableSet) obj).isHashCodeFast() && hashCode() != obj.hashCode()) {
            return false;
        }
        return Sets.equalsImpl(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.asList;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> createAsList = createAsList();
        this.asList = createAsList;
        return createAsList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableList<E> createAsList() {
        return new RegularImmutableAsList(this, toArray());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static abstract class Indexed<E> extends ImmutableSet<E> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract E get(int i);

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<E> iterator() {
            return asList().iterator();
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.Collection, java.lang.Iterable
        public Spliterator<E> spliterator() {
            return CollectSpliterators.indexed(size(), ImmutableSet.SPLITERATOR_CHARACTERISTICS, new IntFunction() { // from class: com.google.common.collect.-$$Lambda$3BLy52QWdLAhlRrhtUvnf2tWQ0U
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return ImmutableSet.Indexed.this.get(i);
                }
            });
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super E> consumer) {
            Preconditions.checkNotNull(consumer);
            int size = size();
            for (int i = 0; i < size; i++) {
                consumer.accept(get(i));
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public int copyIntoArray(Object[] objArr, int i) {
            return asList().copyIntoArray(objArr, i);
        }

        @Override // com.google.common.collect.ImmutableSet
        ImmutableList<E> createAsList() {
            return new ImmutableAsList<E>() { // from class: com.google.common.collect.ImmutableSet.Indexed.1
                @Override // java.util.List
                public E get(int i) {
                    return (E) Indexed.this.get(i);
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.google.common.collect.ImmutableAsList
                public Indexed<E> delegateCollection() {
                    return Indexed.this;
                }
            };
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    private static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        Object readResolve() {
            return ImmutableSet.copyOf(this.elements);
        }
    }

    @Override // com.google.common.collect.ImmutableCollection
    Object writeReplace() {
        return new SerializedForm(toArray());
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    public static <E> Builder<E> builderWithExpectedSize(int i) {
        CollectPreconditions.checkNonnegative(i, "expectedSize");
        return new Builder<>(i);
    }

    static Object[] rebuildHashTable(int i, Object[] objArr, int i2) {
        int i3;
        Object[] objArr2 = new Object[i];
        int length = objArr2.length - 1;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            int smear = Hashing.smear(obj.hashCode());
            while (true) {
                i3 = smear & length;
                if (objArr2[i3] == null) {
                    break;
                }
                smear++;
            }
            objArr2[i3] = obj;
        }
        return objArr2;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder<E> extends ImmutableCollection.Builder<E> {
        boolean forceCopy;
        private SetBuilderImpl<E> impl;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableCollection.Builder
        public /* bridge */ /* synthetic */ ImmutableCollection.Builder add(Object obj) {
            return add((Builder<E>) obj);
        }

        public Builder() {
            this(4);
        }

        Builder(int i) {
            this.impl = new RegularSetBuilderImpl(i);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(boolean z) {
            this.impl = null;
        }

        void forceJdk() {
            this.impl = new JdkBackedSetBuilderImpl(this.impl);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void copyIfNecessary() {
            if (this.forceCopy) {
                copy();
                this.forceCopy = false;
            }
        }

        void copy() {
            this.impl = this.impl.copy();
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> add(E e) {
            Preconditions.checkNotNull(e);
            copyIfNecessary();
            this.impl = this.impl.add(e);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> add(E... eArr) {
            super.add((Object[]) eArr);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            super.addAll((Iterable) iterable);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> addAll(Iterator<? extends E> it) {
            super.addAll((Iterator) it);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder<E> combine(Builder<E> builder) {
            copyIfNecessary();
            this.impl = this.impl.combine(builder.impl);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public ImmutableSet<E> build() {
            this.forceCopy = true;
            this.impl = this.impl.review();
            return this.impl.build();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static abstract class SetBuilderImpl<E> {
        E[] dedupedElements;
        int distinct;

        abstract SetBuilderImpl<E> add(E e);

        abstract ImmutableSet<E> build();

        abstract SetBuilderImpl<E> copy();

        SetBuilderImpl<E> review() {
            return this;
        }

        SetBuilderImpl(int i) {
            this.dedupedElements = (E[]) new Object[i];
            this.distinct = 0;
        }

        SetBuilderImpl(SetBuilderImpl<E> setBuilderImpl) {
            E[] eArr = setBuilderImpl.dedupedElements;
            this.dedupedElements = (E[]) Arrays.copyOf(eArr, eArr.length);
            this.distinct = setBuilderImpl.distinct;
        }

        private void ensureCapacity(int i) {
            E[] eArr = this.dedupedElements;
            if (i > eArr.length) {
                this.dedupedElements = (E[]) Arrays.copyOf(this.dedupedElements, ImmutableCollection.Builder.expandedCapacity(eArr.length, i));
            }
        }

        final void addDedupedElement(E e) {
            ensureCapacity(this.distinct + 1);
            E[] eArr = this.dedupedElements;
            int i = this.distinct;
            this.distinct = i + 1;
            eArr[i] = e;
        }

        final SetBuilderImpl<E> combine(SetBuilderImpl<E> setBuilderImpl) {
            SetBuilderImpl<E> setBuilderImpl2 = this;
            for (int i = 0; i < setBuilderImpl.distinct; i++) {
                setBuilderImpl2 = setBuilderImpl2.add(setBuilderImpl.dedupedElements[i]);
            }
            return setBuilderImpl2;
        }
    }

    static int chooseTableSize(int i) {
        int max = Math.max(i, 2);
        if (max < CUTOFF) {
            int highestOneBit = Integer.highestOneBit(max - 1) << 1;
            while (highestOneBit * DESIRED_LOAD_FACTOR < max) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        Preconditions.checkArgument(max < 1073741824, "collection too large");
        return 1073741824;
    }

    static boolean hashFloodingDetected(Object[] objArr) {
        int maxRunBeforeFallback = maxRunBeforeFallback(objArr.length);
        int i = 0;
        while (i < objArr.length && objArr[i] != null) {
            i++;
            if (i > maxRunBeforeFallback) {
                return true;
            }
        }
        int length = objArr.length - 1;
        while (length > i && objArr[length] != null) {
            if (((objArr.length - 1) - length) + i > maxRunBeforeFallback) {
                return true;
            }
            length--;
        }
        int i2 = maxRunBeforeFallback / 2;
        int i3 = i + 1;
        while (true) {
            int i4 = i3 + i2;
            if (i4 > length) {
                return false;
            }
            for (int i5 = 0; i5 < i2; i5++) {
                if (objArr[i3 + i5] == null) {
                    break;
                }
            }
            return true;
            i3 = i4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int maxRunBeforeFallback(int i) {
        return IntMath.log2(i, RoundingMode.UNNECESSARY) * 13;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class RegularSetBuilderImpl<E> extends SetBuilderImpl<E> {
        private int expandTableThreshold;
        private int hashCode;
        private Object[] hashTable;
        private int maxRunBeforeFallback;

        RegularSetBuilderImpl(int i) {
            super(i);
            int chooseTableSize = ImmutableSet.chooseTableSize(i);
            this.hashTable = new Object[chooseTableSize];
            this.maxRunBeforeFallback = ImmutableSet.maxRunBeforeFallback(chooseTableSize);
            this.expandTableThreshold = (int) (chooseTableSize * ImmutableSet.DESIRED_LOAD_FACTOR);
        }

        RegularSetBuilderImpl(RegularSetBuilderImpl<E> regularSetBuilderImpl) {
            super(regularSetBuilderImpl);
            Object[] objArr = regularSetBuilderImpl.hashTable;
            this.hashTable = Arrays.copyOf(objArr, objArr.length);
            this.maxRunBeforeFallback = regularSetBuilderImpl.maxRunBeforeFallback;
            this.expandTableThreshold = regularSetBuilderImpl.expandTableThreshold;
            this.hashCode = regularSetBuilderImpl.hashCode;
        }

        void ensureTableCapacity(int i) {
            if (i > this.expandTableThreshold) {
                Object[] objArr = this.hashTable;
                if (objArr.length < 1073741824) {
                    int length = objArr.length * 2;
                    this.hashTable = ImmutableSet.rebuildHashTable(length, this.dedupedElements, this.distinct);
                    this.maxRunBeforeFallback = ImmutableSet.maxRunBeforeFallback(length);
                    this.expandTableThreshold = (int) (length * ImmutableSet.DESIRED_LOAD_FACTOR);
                }
            }
        }

        @Override // com.google.common.collect.ImmutableSet.SetBuilderImpl
        SetBuilderImpl<E> add(E e) {
            Preconditions.checkNotNull(e);
            int hashCode = e.hashCode();
            int smear = Hashing.smear(hashCode);
            int length = this.hashTable.length - 1;
            for (int i = smear; i - smear < this.maxRunBeforeFallback; i++) {
                int i2 = i & length;
                Object obj = this.hashTable[i2];
                if (obj == null) {
                    addDedupedElement(e);
                    this.hashTable[i2] = e;
                    this.hashCode += hashCode;
                    ensureTableCapacity(this.distinct);
                    return this;
                }
                if (obj.equals(e)) {
                    return this;
                }
            }
            return new JdkBackedSetBuilderImpl(this).add(e);
        }

        @Override // com.google.common.collect.ImmutableSet.SetBuilderImpl
        SetBuilderImpl<E> copy() {
            return new RegularSetBuilderImpl(this);
        }

        @Override // com.google.common.collect.ImmutableSet.SetBuilderImpl
        SetBuilderImpl<E> review() {
            int chooseTableSize = ImmutableSet.chooseTableSize(this.distinct);
            if (chooseTableSize * 2 < this.hashTable.length) {
                this.hashTable = ImmutableSet.rebuildHashTable(chooseTableSize, this.dedupedElements, this.distinct);
                this.maxRunBeforeFallback = ImmutableSet.maxRunBeforeFallback(chooseTableSize);
                this.expandTableThreshold = (int) (chooseTableSize * ImmutableSet.DESIRED_LOAD_FACTOR);
            }
            return ImmutableSet.hashFloodingDetected(this.hashTable) ? new JdkBackedSetBuilderImpl(this) : this;
        }

        @Override // com.google.common.collect.ImmutableSet.SetBuilderImpl
        ImmutableSet<E> build() {
            Object[] copyOf;
            int i = this.distinct;
            if (i == 0) {
                return ImmutableSet.m676of();
            }
            if (i == 1) {
                return ImmutableSet.m677of((Object) this.dedupedElements[0]);
            }
            if (this.distinct == this.dedupedElements.length) {
                copyOf = this.dedupedElements;
            } else {
                copyOf = Arrays.copyOf(this.dedupedElements, this.distinct);
            }
            int i2 = this.hashCode;
            Object[] objArr = this.hashTable;
            return new RegularImmutableSet(copyOf, i2, objArr, objArr.length - 1);
        }
    }

    /* loaded from: classes3.dex */
    private static final class JdkBackedSetBuilderImpl<E> extends SetBuilderImpl<E> {
        private final Set<Object> delegate;

        JdkBackedSetBuilderImpl(SetBuilderImpl<E> setBuilderImpl) {
            super(setBuilderImpl);
            this.delegate = Sets.newHashSetWithExpectedSize(this.distinct);
            for (int i = 0; i < this.distinct; i++) {
                this.delegate.add(this.dedupedElements[i]);
            }
        }

        @Override // com.google.common.collect.ImmutableSet.SetBuilderImpl
        SetBuilderImpl<E> add(E e) {
            Preconditions.checkNotNull(e);
            if (this.delegate.add(e)) {
                addDedupedElement(e);
            }
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.SetBuilderImpl
        SetBuilderImpl<E> copy() {
            return new JdkBackedSetBuilderImpl(this);
        }

        @Override // com.google.common.collect.ImmutableSet.SetBuilderImpl
        ImmutableSet<E> build() {
            int i = this.distinct;
            if (i == 0) {
                return ImmutableSet.m676of();
            }
            if (i == 1) {
                return ImmutableSet.m677of((Object) this.dedupedElements[0]);
            }
            return new JdkBackedImmutableSet(this.delegate, ImmutableList.asImmutableList(this.dedupedElements, this.distinct));
        }
    }
}
