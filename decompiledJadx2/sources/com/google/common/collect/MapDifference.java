package com.google.common.collect;

import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
@DoNotMock("Use Maps.difference")
/* loaded from: classes3.dex */
public interface MapDifference<K, V> {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    @DoNotMock("Use Maps.difference")
    /* loaded from: classes3.dex */
    public interface ValueDifference<V> {
        boolean equals(Object obj);

        int hashCode();

        V leftValue();

        V rightValue();
    }

    boolean areEqual();

    Map<K, ValueDifference<V>> entriesDiffering();

    Map<K, V> entriesInCommon();

    Map<K, V> entriesOnlyOnLeft();

    Map<K, V> entriesOnlyOnRight();

    boolean equals(Object obj);

    int hashCode();
}
