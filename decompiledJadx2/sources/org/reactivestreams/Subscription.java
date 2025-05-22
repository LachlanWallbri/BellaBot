package org.reactivestreams;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public interface Subscription {
    void cancel();

    void request(long j);
}
