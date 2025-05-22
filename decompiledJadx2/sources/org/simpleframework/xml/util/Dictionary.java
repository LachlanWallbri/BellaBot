package org.simpleframework.xml.util;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.simpleframework.xml.util.Entry;

/* loaded from: classes9.dex */
public class Dictionary<T extends Entry> extends AbstractSet<T> {
    protected final Table<T> map = new Table<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class Table<T> extends LinkedHashMap<String, T> {
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(T t) {
        return this.map.put(t.getName(), t) != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.map.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<T> iterator() {
        return this.map.values().iterator();
    }

    public T get(String str) {
        return this.map.get(str);
    }

    public T remove(String str) {
        return (T) this.map.remove(str);
    }
}
