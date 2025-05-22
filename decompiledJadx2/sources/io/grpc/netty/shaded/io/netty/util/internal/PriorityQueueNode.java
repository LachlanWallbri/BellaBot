package io.grpc.netty.shaded.io.netty.util.internal;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface PriorityQueueNode {
    public static final int INDEX_NOT_IN_QUEUE = -1;

    int priorityQueueIndex(DefaultPriorityQueue<?> defaultPriorityQueue);

    void priorityQueueIndex(DefaultPriorityQueue<?> defaultPriorityQueue, int i);
}
