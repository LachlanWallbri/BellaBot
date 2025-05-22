package io.opencensus.stats;

import io.opencensus.stats.AggregationData;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_AggregationData_SumDataLong extends AggregationData.SumDataLong {
    private final long sum;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AggregationData_SumDataLong(long j) {
        this.sum = j;
    }

    @Override // io.opencensus.stats.AggregationData.SumDataLong
    public long getSum() {
        return this.sum;
    }

    public String toString() {
        return "SumDataLong{sum=" + this.sum + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof AggregationData.SumDataLong) && this.sum == ((AggregationData.SumDataLong) obj).getSum();
    }

    public int hashCode() {
        long j = this.sum;
        return (int) (1000003 ^ (j ^ (j >>> 32)));
    }
}
