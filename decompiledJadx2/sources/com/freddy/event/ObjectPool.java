package com.freddy.event;

import com.freddy.event.PooledObject;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public abstract class ObjectPool<T extends PooledObject> {
    private final Object LOCK = new Object();
    private int length;
    private T[] mContainer;

    protected abstract T createNewObj();

    protected abstract T[] createObjPool(int i);

    public ObjectPool(int i) {
        this.mContainer = createObjPool(i);
    }

    public final T get() {
        T findFreeObject = findFreeObject();
        if (findFreeObject == null) {
            return createNewObj();
        }
        findFreeObject.reset();
        return findFreeObject;
    }

    private T findFreeObject() {
        T t;
        synchronized (this.LOCK) {
            if (this.length > 0) {
                this.length--;
                t = this.mContainer[this.length];
                this.mContainer[this.length] = null;
            } else {
                t = null;
            }
        }
        return t;
    }

    public final void returnObj(T t) {
        synchronized (this.LOCK) {
            if (this.length < this.mContainer.length) {
                this.mContainer[this.length] = t;
                this.length++;
            }
        }
    }
}
