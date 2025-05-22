package com.google.api.gax.core;

import com.google.api.core.BetaApi;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes2.dex */
public class Distribution {
    private final AtomicLongArray buckets;
    private final AtomicInteger count = new AtomicInteger(0);

    public Distribution(int i) {
        Preconditions.checkArgument(i > 0);
        this.buckets = new AtomicLongArray(i);
    }

    @Deprecated
    public long getNthPercentile(double d) {
        return getPercentile(d);
    }

    public int getPercentile(double d) {
        Preconditions.checkArgument(d > 0.0d);
        Preconditions.checkArgument(d <= 100.0d);
        long ceil = (long) Math.ceil((d * this.count.get()) / 100.0d);
        long j = 0;
        for (int i = 0; i < this.buckets.length(); i++) {
            j += this.buckets.get(i);
            if (j >= ceil) {
                return i;
            }
        }
        return this.buckets.length();
    }

    public void record(int i) {
        Preconditions.checkArgument(i >= 0);
        if (i >= this.buckets.length()) {
            i = this.buckets.length() - 1;
        }
        this.buckets.incrementAndGet(i);
        this.count.incrementAndGet();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("endValue", this.buckets.length()).add("count", this.count.get()).toString();
    }
}
