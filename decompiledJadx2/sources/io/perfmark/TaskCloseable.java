package io.perfmark;

import java.io.Closeable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class TaskCloseable implements Closeable {
    static final TaskCloseable INSTANCE = new TaskCloseable();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        PerfMark.stopTask();
    }

    private TaskCloseable() {
    }
}
