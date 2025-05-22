package io.reactivex.observables;

import io.reactivex.Observable;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public abstract class GroupedObservable<K, T> extends Observable<T> {
    final K key;

    /* JADX INFO: Access modifiers changed from: protected */
    public GroupedObservable(K k) {
        this.key = k;
    }

    public K getKey() {
        return this.key;
    }
}
