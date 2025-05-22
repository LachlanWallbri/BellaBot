package io.grpc.internal;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface BackoffPolicy {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface Provider {
        BackoffPolicy get();
    }

    long nextBackoffNanos();
}
