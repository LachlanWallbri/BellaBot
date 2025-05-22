package io.netty.channel;

import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public interface EventLoopTaskQueueFactory {
    Queue<Runnable> newTaskQueue(int i);
}
