package io.grpc.netty.shaded.io.netty.util.internal;

import io.grpc.netty.shaded.io.netty.util.Recycler;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ObjectPool<T> {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface Handle<T> {
        void recycle(T t);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface ObjectCreator<T> {
        T newObject(Handle<T> handle);
    }

    public abstract T get();

    ObjectPool() {
    }

    public static <T> ObjectPool<T> newPool(ObjectCreator<T> objectCreator) {
        return new RecyclerObjectPool((ObjectCreator) ObjectUtil.checkNotNull(objectCreator, "creator"));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class RecyclerObjectPool<T> extends ObjectPool<T> {
        private final Recycler<T> recycler;

        RecyclerObjectPool(final ObjectCreator<T> objectCreator) {
            this.recycler = new Recycler<T>() { // from class: io.grpc.netty.shaded.io.netty.util.internal.ObjectPool.RecyclerObjectPool.1
                @Override // io.grpc.netty.shaded.io.netty.util.Recycler
                protected T newObject(Recycler.Handle<T> handle) {
                    return (T) objectCreator.newObject(handle);
                }
            };
        }

        @Override // io.grpc.netty.shaded.io.netty.util.internal.ObjectPool
        public T get() {
            return this.recycler.get();
        }
    }
}
