package me.jessyan.retrofiturlmanager.cache;

import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface Cache<K, V> {
    void clear();

    boolean containsKey(K k);

    V get(K k);

    int getMaxSize();

    Set<K> keySet();

    V put(K k, V v);

    V remove(K k);

    int size();
}
