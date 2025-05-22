package io.grpc.internal;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ObjectPool<T> {
    T getObject();

    T returnObject(Object obj);
}
