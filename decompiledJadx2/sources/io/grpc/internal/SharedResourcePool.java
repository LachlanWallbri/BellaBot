package io.grpc.internal;

import io.grpc.internal.SharedResourceHolder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class SharedResourcePool<T> implements ObjectPool<T> {
    private final SharedResourceHolder.Resource<T> resource;

    private SharedResourcePool(SharedResourceHolder.Resource<T> resource) {
        this.resource = resource;
    }

    public static <T> SharedResourcePool<T> forResource(SharedResourceHolder.Resource<T> resource) {
        return new SharedResourcePool<>(resource);
    }

    @Override // io.grpc.internal.ObjectPool
    public T getObject() {
        return (T) SharedResourceHolder.get(this.resource);
    }

    @Override // io.grpc.internal.ObjectPool
    public T returnObject(Object obj) {
        SharedResourceHolder.release(this.resource, obj);
        return null;
    }
}
