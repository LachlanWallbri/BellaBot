package io.grpc.netty.shaded.io.netty.util.collection;

import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface IntObjectMap<V> extends Map<Integer, V> {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface PrimitiveEntry<V> {
        int key();

        void setValue(V v);

        V value();
    }

    boolean containsKey(int i);

    Iterable<PrimitiveEntry<V>> entries();

    V get(int i);

    V put(int i, V v);

    V remove(int i);
}
