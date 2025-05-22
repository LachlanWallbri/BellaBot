package io.netty.util.internal;

import java.util.concurrent.atomic.LongAdder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
final class LongAdderCounter extends LongAdder implements LongCounter {
    @Override // io.netty.util.internal.LongCounter
    public long value() {
        return longValue();
    }
}
