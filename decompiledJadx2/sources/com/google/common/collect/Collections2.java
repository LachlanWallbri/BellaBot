package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.math.IntMath;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class Collections2 {
    private Collections2() {
    }

    public static <E> Collection<E> filter(Collection<E> collection, Predicate<? super E> predicate) {
        if (collection instanceof FilteredCollection) {
            return ((FilteredCollection) collection).createCombined(predicate);
        }
        return new FilteredCollection((Collection) Preconditions.checkNotNull(collection), (Predicate) Preconditions.checkNotNull(predicate));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean safeContains(Collection<?> collection, Object obj) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean safeRemove(Collection<?> collection, Object obj) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class FilteredCollection<E> extends AbstractCollection<E> {
        final Predicate<? super E> predicate;
        final Collection<E> unfiltered;

        /* JADX INFO: Access modifiers changed from: package-private */
        public FilteredCollection(Collection<E> collection, Predicate<? super E> predicate) {
            this.unfiltered = collection;
            this.predicate = predicate;
        }

        FilteredCollection<E> createCombined(Predicate<? super E> predicate) {
            return new FilteredCollection<>(this.unfiltered, Predicates.and(this.predicate, predicate));
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(E e) {
            Preconditions.checkArgument(this.predicate.apply(e));
            return this.unfiltered.add(e);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            Iterator<? extends E> it = collection.iterator();
            while (it.hasNext()) {
                Preconditions.checkArgument(this.predicate.apply(it.next()));
            }
            return this.unfiltered.addAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            Iterables.removeIf(this.unfiltered, this.predicate);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            if (Collections2.safeContains(this.unfiltered, obj)) {
                return this.predicate.apply(obj);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            return Collections2.containsAllImpl(this, collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return !Iterables.any(this.unfiltered, this.predicate);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return Iterators.filter(this.unfiltered.iterator(), this.predicate);
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Spliterator<E> spliterator() {
            return CollectSpliterators.filter(this.unfiltered.spliterator(), this.predicate);
        }

        @Override // java.lang.Iterable
        public void forEach(final Consumer<? super E> consumer) {
            Preconditions.checkNotNull(consumer);
            this.unfiltered.forEach(new Consumer() { // from class: com.google.common.collect.-$$Lambda$Collections2$FilteredCollection$X1Nyc9qvsRSExuCY-e6M2sT39ns
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Collections2.FilteredCollection.this.lambda$forEach$0$Collections2$FilteredCollection(consumer, obj);
                }
            });
        }

        public /* synthetic */ void lambda$forEach$0$Collections2$FilteredCollection(Consumer consumer, Object obj) {
            if (this.predicate.test(obj)) {
                consumer.accept(obj);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            return contains(obj) && this.unfiltered.remove(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(final Collection<?> collection) {
            Objects.requireNonNull(collection);
            return removeIf(new java.util.function.Predicate() { // from class: com.google.common.collect.-$$Lambda$8ZUcrOvlPOJdkwaEa5sD1L7phC8
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return collection.contains(obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ boolean lambda$retainAll$1(Collection collection, Object obj) {
            return !collection.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(final Collection<?> collection) {
            return removeIf(new java.util.function.Predicate() { // from class: com.google.common.collect.-$$Lambda$Collections2$FilteredCollection$KZZADvVoxzl7X9QJ7avS6lZW_Tk
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Collections2.FilteredCollection.lambda$retainAll$1(collection, obj);
                }
            });
        }

        @Override // java.util.Collection
        public boolean removeIf(final java.util.function.Predicate<? super E> predicate) {
            Preconditions.checkNotNull(predicate);
            return this.unfiltered.removeIf(new java.util.function.Predicate() { // from class: com.google.common.collect.-$$Lambda$Collections2$FilteredCollection$vThViNohB0vFMHsaCtCTpMga69k
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Collections2.FilteredCollection.this.lambda$removeIf$2$Collections2$FilteredCollection(predicate, obj);
                }
            });
        }

        public /* synthetic */ boolean lambda$removeIf$2$Collections2$FilteredCollection(java.util.function.Predicate predicate, Object obj) {
            return this.predicate.apply(obj) && predicate.test(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            Iterator<E> it = this.unfiltered.iterator();
            int i = 0;
            while (it.hasNext()) {
                if (this.predicate.apply(it.next())) {
                    i++;
                }
            }
            return i;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) Lists.newArrayList(iterator()).toArray(tArr);
        }
    }

    public static <F, T> Collection<T> transform(Collection<F> collection, Function<? super F, T> function) {
        return new TransformedCollection(collection, function);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class TransformedCollection<F, T> extends AbstractCollection<T> {
        final Collection<F> fromCollection;
        final Function<? super F, ? extends T> function;

        TransformedCollection(Collection<F> collection, Function<? super F, ? extends T> function) {
            this.fromCollection = (Collection) Preconditions.checkNotNull(collection);
            this.function = (Function) Preconditions.checkNotNull(function);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.fromCollection.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.fromCollection.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.transform(this.fromCollection.iterator(), this.function);
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Spliterator<T> spliterator() {
            return CollectSpliterators.map(this.fromCollection.spliterator(), this.function);
        }

        @Override // java.lang.Iterable
        public void forEach(final Consumer<? super T> consumer) {
            Preconditions.checkNotNull(consumer);
            this.fromCollection.forEach(new Consumer() { // from class: com.google.common.collect.-$$Lambda$Collections2$TransformedCollection$SMfKSGJa7dRiZjB8gNQiI0i-w9E
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Collections2.TransformedCollection.this.lambda$forEach$0$Collections2$TransformedCollection(consumer, obj);
                }
            });
        }

        public /* synthetic */ void lambda$forEach$0$Collections2$TransformedCollection(Consumer consumer, Object obj) {
            consumer.accept(this.function.apply(obj));
        }

        @Override // java.util.Collection
        public boolean removeIf(final java.util.function.Predicate<? super T> predicate) {
            Preconditions.checkNotNull(predicate);
            return this.fromCollection.removeIf(new java.util.function.Predicate() { // from class: com.google.common.collect.-$$Lambda$Collections2$TransformedCollection$a_DOTlahUQQQHIq0OaR7TqZrp_c
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Collections2.TransformedCollection.this.lambda$removeIf$1$Collections2$TransformedCollection(predicate, obj);
                }
            });
        }

        public /* synthetic */ boolean lambda$removeIf$1$Collections2$TransformedCollection(java.util.function.Predicate predicate, Object obj) {
            return predicate.test(this.function.apply(obj));
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.fromCollection.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean containsAllImpl(Collection<?> collection, Collection<?> collection2) {
        Iterator<?> it = collection2.iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toStringImpl(Collection<?> collection) {
        StringBuilder newStringBuilderForCollection = newStringBuilderForCollection(collection.size());
        newStringBuilderForCollection.append('[');
        boolean z = true;
        for (Object obj : collection) {
            if (!z) {
                newStringBuilderForCollection.append(", ");
            }
            z = false;
            if (obj == collection) {
                newStringBuilderForCollection.append("(this Collection)");
            } else {
                newStringBuilderForCollection.append(obj);
            }
        }
        newStringBuilderForCollection.append(']');
        return newStringBuilderForCollection.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static StringBuilder newStringBuilderForCollection(int i) {
        CollectPreconditions.checkNonnegative(i, "size");
        return new StringBuilder((int) Math.min(i * 8, 1073741824L));
    }

    public static <E extends Comparable<? super E>> Collection<List<E>> orderedPermutations(Iterable<E> iterable) {
        return orderedPermutations(iterable, Ordering.natural());
    }

    public static <E> Collection<List<E>> orderedPermutations(Iterable<E> iterable, Comparator<? super E> comparator) {
        return new OrderedPermutationCollection(iterable, comparator);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static final class OrderedPermutationCollection<E> extends AbstractCollection<List<E>> {
        final Comparator<? super E> comparator;
        final ImmutableList<E> inputList;
        final int size;

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return false;
        }

        OrderedPermutationCollection(Iterable<E> iterable, Comparator<? super E> comparator) {
            this.inputList = ImmutableList.sortedCopyOf(comparator, iterable);
            this.comparator = comparator;
            this.size = calculateSize(this.inputList, comparator);
        }

        private static <E> int calculateSize(List<E> list, Comparator<? super E> comparator) {
            int i = 1;
            int i2 = 1;
            int i3 = 1;
            while (i < list.size()) {
                if (comparator.compare(list.get(i - 1), list.get(i)) < 0) {
                    i2 = IntMath.saturatedMultiply(i2, IntMath.binomial(i, i3));
                    i3 = 0;
                    if (i2 == Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                }
                i++;
                i3++;
            }
            return IntMath.saturatedMultiply(i2, IntMath.binomial(i, i3));
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<List<E>> iterator() {
            return new OrderedPermutationIterator(this.inputList, this.comparator);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            if (!(obj instanceof List)) {
                return false;
            }
            return Collections2.isPermutation(this.inputList, (List) obj);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            String valueOf = String.valueOf(this.inputList);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 30);
            sb.append("orderedPermutationCollection(");
            sb.append(valueOf);
            sb.append(")");
            return sb.toString();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    private static final class OrderedPermutationIterator<E> extends AbstractIterator<List<E>> {
        final Comparator<? super E> comparator;
        List<E> nextPermutation;

        OrderedPermutationIterator(List<E> list, Comparator<? super E> comparator) {
            this.nextPermutation = Lists.newArrayList(list);
            this.comparator = comparator;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractIterator
        public List<E> computeNext() {
            List<E> list = this.nextPermutation;
            if (list == null) {
                return endOfData();
            }
            ImmutableList copyOf = ImmutableList.copyOf((Collection) list);
            calculateNextPermutation();
            return copyOf;
        }

        void calculateNextPermutation() {
            int findNextJ = findNextJ();
            if (findNextJ == -1) {
                this.nextPermutation = null;
                return;
            }
            Collections.swap(this.nextPermutation, findNextJ, findNextL(findNextJ));
            Collections.reverse(this.nextPermutation.subList(findNextJ + 1, this.nextPermutation.size()));
        }

        int findNextJ() {
            for (int size = this.nextPermutation.size() - 2; size >= 0; size--) {
                if (this.comparator.compare(this.nextPermutation.get(size), this.nextPermutation.get(size + 1)) < 0) {
                    return size;
                }
            }
            return -1;
        }

        int findNextL(int i) {
            E e = this.nextPermutation.get(i);
            for (int size = this.nextPermutation.size() - 1; size > i; size--) {
                if (this.comparator.compare(e, this.nextPermutation.get(size)) < 0) {
                    return size;
                }
            }
            throw new AssertionError("this statement should be unreachable");
        }
    }

    public static <E> Collection<List<E>> permutations(Collection<E> collection) {
        return new PermutationCollection(ImmutableList.copyOf((Collection) collection));
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    private static final class PermutationCollection<E> extends AbstractCollection<List<E>> {
        final ImmutableList<E> inputList;

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return false;
        }

        PermutationCollection(ImmutableList<E> immutableList) {
            this.inputList = immutableList;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return IntMath.factorial(this.inputList.size());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<List<E>> iterator() {
            return new PermutationIterator(this.inputList);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            if (!(obj instanceof List)) {
                return false;
            }
            return Collections2.isPermutation(this.inputList, (List) obj);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            String valueOf = String.valueOf(this.inputList);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14);
            sb.append("permutations(");
            sb.append(valueOf);
            sb.append(")");
            return sb.toString();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    private static class PermutationIterator<E> extends AbstractIterator<List<E>> {

        /* renamed from: c */
        final int[] f1948c;

        /* renamed from: j */
        int f1949j;
        final List<E> list;

        /* renamed from: o */
        final int[] f1950o;

        PermutationIterator(List<E> list) {
            this.list = new ArrayList(list);
            int size = list.size();
            this.f1948c = new int[size];
            this.f1950o = new int[size];
            Arrays.fill(this.f1948c, 0);
            Arrays.fill(this.f1950o, 1);
            this.f1949j = Integer.MAX_VALUE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractIterator
        public List<E> computeNext() {
            if (this.f1949j <= 0) {
                return endOfData();
            }
            ImmutableList copyOf = ImmutableList.copyOf((Collection) this.list);
            calculateNextPermutation();
            return copyOf;
        }

        void calculateNextPermutation() {
            this.f1949j = this.list.size() - 1;
            if (this.f1949j == -1) {
                return;
            }
            int i = 0;
            while (true) {
                int[] iArr = this.f1948c;
                int i2 = this.f1949j;
                int i3 = iArr[i2] + this.f1950o[i2];
                if (i3 < 0) {
                    switchDirection();
                } else if (i3 != i2 + 1) {
                    Collections.swap(this.list, (i2 - iArr[i2]) + i, (i2 - i3) + i);
                    this.f1948c[this.f1949j] = i3;
                    return;
                } else {
                    if (i2 == 0) {
                        return;
                    }
                    i++;
                    switchDirection();
                }
            }
        }

        void switchDirection() {
            int[] iArr = this.f1950o;
            int i = this.f1949j;
            iArr[i] = -iArr[i];
            this.f1949j = i - 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPermutation(List<?> list, List<?> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        return HashMultiset.create(list).equals(HashMultiset.create(list2));
    }
}
