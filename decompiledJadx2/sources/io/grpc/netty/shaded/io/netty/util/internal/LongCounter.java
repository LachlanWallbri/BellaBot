package io.grpc.netty.shaded.io.netty.util.internal;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface LongCounter {
    void add(long j);

    void decrement();

    void increment();

    long value();
}
