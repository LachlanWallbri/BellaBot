package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface Multiset<E> extends Collection<E> {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public interface Entry<E> {
        boolean equals(Object obj);

        int getCount();

        E getElement();

        int hashCode();

        String toString();
    }

    int add(E e, int i);

    boolean add(E e);

    boolean contains(Object obj);

    @Override // java.util.Collection
    boolean containsAll(Collection<?> collection);

    int count(Object obj);

    Set<E> elementSet();

    Set<Entry<E>> entrySet();

    boolean equals(Object obj);

    int hashCode();

    Iterator<E> iterator();

    int remove(Object obj, int i);

    boolean remove(Object obj);

    boolean removeAll(Collection<?> collection);

    boolean retainAll(Collection<?> collection);

    int setCount(E e, int i);

    boolean setCount(E e, int i, int i2);

    int size();

    String toString();

    default void forEachEntry(final ObjIntConsumer<? super E> objIntConsumer) {
        Preconditions.checkNotNull(objIntConsumer);
        entrySet().forEach(new Consumer() { // from class: com.google.common.collect.-$$Lambda$Multiset$y63SmnatRR1Ke2x1fqDeFclRelQ
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                objIntConsumer.accept(r2.getElement(), ((Multiset.Entry) obj).getCount());
            }
        });
    }

    default void forEach(final Consumer<? super E> consumer) {
        Preconditions.checkNotNull(consumer);
        entrySet().forEach(new Consumer() { // from class: com.google.common.collect.-$$Lambda$Multiset$yTxkVZMQHaYOoZD4ry_up4hmmvA
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Multiset.lambda$forEach$1(consumer, (Multiset.Entry) obj);
            }
        });
    }

    static /* synthetic */ void lambda$forEach$1(Consumer consumer, Entry entry) {
        Object element = entry.getElement();
        int count = entry.getCount();
        for (int i = 0; i < count; i++) {
            consumer.accept(element);
        }
    }

    default Spliterator<E> spliterator() {
        return Multisets.spliteratorImpl(this);
    }
}
