package io.netty.util.internal;

import io.netty.util.Recycler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class RecyclableArrayList extends ArrayList<Object> {
    private static final int DEFAULT_INITIAL_CAPACITY = 8;
    private static final Recycler<RecyclableArrayList> RECYCLER = new Recycler<RecyclableArrayList>() { // from class: io.netty.util.internal.RecyclableArrayList.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.netty.util.Recycler
        public RecyclableArrayList newObject(Recycler.Handle<RecyclableArrayList> handle) {
            return new RecyclableArrayList(handle);
        }
    };
    private static final long serialVersionUID = -8605125654176467947L;
    private final Recycler.Handle<RecyclableArrayList> handle;
    private boolean insertSinceRecycled;

    public static RecyclableArrayList newInstance() {
        return newInstance(8);
    }

    public static RecyclableArrayList newInstance(int i) {
        RecyclableArrayList recyclableArrayList = RECYCLER.get();
        recyclableArrayList.ensureCapacity(i);
        return recyclableArrayList;
    }

    private RecyclableArrayList(Recycler.Handle<RecyclableArrayList> handle) {
        this(handle, 8);
    }

    private RecyclableArrayList(Recycler.Handle<RecyclableArrayList> handle, int i) {
        super(i);
        this.handle = handle;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<?> collection) {
        checkNullElements(collection);
        if (!super.addAll(collection)) {
            return false;
        }
        this.insertSinceRecycled = true;
        return true;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public boolean addAll(int i, Collection<?> collection) {
        checkNullElements(collection);
        if (!super.addAll(i, collection)) {
            return false;
        }
        this.insertSinceRecycled = true;
        return true;
    }

    private static void checkNullElements(Collection<?> collection) {
        if ((collection instanceof RandomAccess) && (collection instanceof List)) {
            List list = (List) collection;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (list.get(i) == null) {
                    throw new IllegalArgumentException("c contains null values");
                }
            }
            return;
        }
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                throw new IllegalArgumentException("c contains null values");
            }
        }
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Object obj) {
        if (obj == null) {
            throw new NullPointerException("element");
        }
        if (!super.add(obj)) {
            return false;
        }
        this.insertSinceRecycled = true;
        return true;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public void add(int i, Object obj) {
        if (obj == null) {
            throw new NullPointerException("element");
        }
        super.add(i, obj);
        this.insertSinceRecycled = true;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public Object set(int i, Object obj) {
        if (obj == null) {
            throw new NullPointerException("element");
        }
        Object obj2 = super.set(i, obj);
        this.insertSinceRecycled = true;
        return obj2;
    }

    public boolean insertSinceRecycled() {
        return this.insertSinceRecycled;
    }

    public boolean recycle() {
        clear();
        this.insertSinceRecycled = false;
        this.handle.recycle(this);
        return true;
    }
}
