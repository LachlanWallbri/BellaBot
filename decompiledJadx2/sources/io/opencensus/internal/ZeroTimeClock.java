package io.opencensus.internal;

import io.opencensus.common.Clock;
import io.opencensus.common.Timestamp;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class ZeroTimeClock extends Clock {
    private static final ZeroTimeClock INSTANCE = new ZeroTimeClock();
    private static final Timestamp ZERO_TIMESTAMP = Timestamp.create(0, 0);

    @Override // io.opencensus.common.Clock
    public long nowNanos() {
        return 0L;
    }

    private ZeroTimeClock() {
    }

    public static ZeroTimeClock getInstance() {
        return INSTANCE;
    }

    @Override // io.opencensus.common.Clock
    public Timestamp now() {
        return ZERO_TIMESTAMP;
    }
}
