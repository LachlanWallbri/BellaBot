package com.google.common.collect;

import com.google.errorprone.annotations.DoNotMock;
import java.lang.Comparable;
import java.util.Map;
import java.util.function.BiFunction;

/* JADX WARN: Classes with same name are omitted:
  
 */
@DoNotMock("Use ImmutableRangeMap or TreeRangeMap")
/* loaded from: classes3.dex */
public interface RangeMap<K extends Comparable, V> {
    Map<Range<K>, V> asDescendingMapOfRanges();

    Map<Range<K>, V> asMapOfRanges();

    void clear();

    boolean equals(Object obj);

    V get(K k);

    Map.Entry<Range<K>, V> getEntry(K k);

    int hashCode();

    void merge(Range<K> range, V v, BiFunction<? super V, ? super V, ? extends V> biFunction);

    void put(Range<K> range, V v);

    void putAll(RangeMap<K, V> rangeMap);

    void putCoalescing(Range<K> range, V v);

    void remove(Range<K> range);

    Range<K> span();

    RangeMap<K, V> subRangeMap(Range<K> range);

    String toString();
}
