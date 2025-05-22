package io.netty.util.collection;

import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface ShortObjectMap<V> extends Map<Short, V> {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public interface PrimitiveEntry<V> {
        short key();

        void setValue(V v);

        V value();
    }

    boolean containsKey(short s);

    Iterable<PrimitiveEntry<V>> entries();

    V get(short s);

    V put(short s, V v);

    V remove(short s);
}
