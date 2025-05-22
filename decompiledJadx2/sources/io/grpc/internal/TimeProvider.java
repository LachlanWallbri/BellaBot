package io.grpc.internal;

import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface TimeProvider {
    public static final TimeProvider SYSTEM_TIME_PROVIDER = new TimeProvider() { // from class: io.grpc.internal.TimeProvider.1
        @Override // io.grpc.internal.TimeProvider
        public long currentTimeNanos() {
            return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
        }
    };

    long currentTimeNanos();
}
