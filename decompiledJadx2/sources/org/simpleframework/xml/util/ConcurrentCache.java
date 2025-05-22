package org.simpleframework.xml.util;

import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class ConcurrentCache<T> extends ConcurrentHashMap<Object, T> implements Cache<T> {
    @Override // org.simpleframework.xml.util.Cache
    public void cache(Object obj, T t) {
        put(obj, t);
    }

    @Override // org.simpleframework.xml.util.Cache
    public T take(Object obj) {
        return remove(obj);
    }

    @Override // org.simpleframework.xml.util.Cache
    public T fetch(Object obj) {
        return get(obj);
    }

    @Override // java.util.concurrent.ConcurrentHashMap, org.simpleframework.xml.util.Cache
    public boolean contains(Object obj) {
        return containsKey(obj);
    }
}
