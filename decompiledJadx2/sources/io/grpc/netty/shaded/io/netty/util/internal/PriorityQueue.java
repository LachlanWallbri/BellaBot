package io.grpc.netty.shaded.io.netty.util.internal;

import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface PriorityQueue<T> extends Queue<T> {
    void clearIgnoringIndexes();

    boolean containsTyped(T t);

    void priorityChanged(T t);

    boolean removeTyped(T t);
}
