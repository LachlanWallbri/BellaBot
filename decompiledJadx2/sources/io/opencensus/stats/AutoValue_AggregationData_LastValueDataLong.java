package io.opencensus.stats;

import io.opencensus.stats.AggregationData;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_AggregationData_LastValueDataLong extends AggregationData.LastValueDataLong {
    private final long lastValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AggregationData_LastValueDataLong(long j) {
        this.lastValue = j;
    }

    @Override // io.opencensus.stats.AggregationData.LastValueDataLong
    public long getLastValue() {
        return this.lastValue;
    }

    public String toString() {
        return "LastValueDataLong{lastValue=" + this.lastValue + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof AggregationData.LastValueDataLong) && this.lastValue == ((AggregationData.LastValueDataLong) obj).getLastValue();
    }

    public int hashCode() {
        long j = this.lastValue;
        return (int) (1000003 ^ (j ^ (j >>> 32)));
    }
}
