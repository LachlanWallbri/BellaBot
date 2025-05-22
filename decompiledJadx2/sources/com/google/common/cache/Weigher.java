package com.google.common.cache;

/* JADX WARN: Classes with same name are omitted:
  
 */
@FunctionalInterface
/* loaded from: classes3.dex */
public interface Weigher<K, V> {
    int weigh(K k, V v);
}
