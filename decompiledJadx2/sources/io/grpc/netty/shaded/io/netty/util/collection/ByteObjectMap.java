package io.grpc.netty.shaded.io.netty.util.collection;

import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ByteObjectMap<V> extends Map<Byte, V> {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface PrimitiveEntry<V> {
        byte key();

        void setValue(V v);

        V value();
    }

    boolean containsKey(byte b);

    Iterable<PrimitiveEntry<V>> entries();

    V get(byte b);

    V put(byte b, V v);

    V remove(byte b);
}
