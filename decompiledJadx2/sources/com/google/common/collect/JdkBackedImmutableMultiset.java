package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class JdkBackedImmutableMultiset<E> extends ImmutableMultiset<E> {
    private final Map<E, Integer> delegateMap;
    private transient ImmutableSet<E> elementSet;
    private final ImmutableList<Multiset.Entry<E>> entries;
    private final long size;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> ImmutableMultiset<E> create(Collection<? extends Multiset.Entry<? extends E>> collection) {
        Multiset.Entry[] entryArr = (Multiset.Entry[]) collection.toArray(new Multiset.Entry[0]);
        HashMap newHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(entryArr.length);
        long j = 0;
        for (int i = 0; i < entryArr.length; i++) {
            Multiset.Entry entry = entryArr[i];
            int count = entry.getCount();
            j += count;
            Object checkNotNull = Preconditions.checkNotNull(entry.getElement());
            newHashMapWithExpectedSize.put(checkNotNull, Integer.valueOf(count));
            if (!(entry instanceof Multisets.ImmutableEntry)) {
                entryArr[i] = Multisets.immutableEntry(checkNotNull, count);
            }
        }
        return new JdkBackedImmutableMultiset(newHashMapWithExpectedSize, ImmutableList.asImmutableList(entryArr), j);
    }

    private JdkBackedImmutableMultiset(Map<E, Integer> map, ImmutableList<Multiset.Entry<E>> immutableList, long j) {
        this.delegateMap = map;
        this.entries = immutableList;
        this.size = j;
    }

    @Override // com.google.common.collect.Multiset
    public int count(Object obj) {
        return this.delegateMap.getOrDefault(obj, 0).intValue();
    }

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public ImmutableSet<E> elementSet() {
        ImmutableSet<E> immutableSet = this.elementSet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableMultiset.ElementSet elementSet = new ImmutableMultiset.ElementSet(this.entries, this);
        this.elementSet = elementSet;
        return elementSet;
    }

    @Override // com.google.common.collect.ImmutableMultiset
    Multiset.Entry<E> getEntry(int i) {
        return this.entries.get(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        return Ints.saturatedCast(this.size);
    }
}
