package io.grpc.netty.shaded.io.netty.handler.codec.serialization;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class SoftReferenceMap<K, V> extends ReferenceMap<K, V> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SoftReferenceMap(Map<K, Reference<V>> map) {
        super(map);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.serialization.ReferenceMap
    Reference<V> fold(V v) {
        return new SoftReference(v);
    }
}
