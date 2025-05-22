package io.netty.util.internal;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface LongCounter {
    void add(long j);

    void decrement();

    void increment();

    long value();
}
