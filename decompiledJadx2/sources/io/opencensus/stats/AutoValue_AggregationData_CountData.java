package io.opencensus.stats;

import io.opencensus.stats.AggregationData;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_AggregationData_CountData extends AggregationData.CountData {
    private final long count;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AggregationData_CountData(long j) {
        this.count = j;
    }

    @Override // io.opencensus.stats.AggregationData.CountData
    public long getCount() {
        return this.count;
    }

    public String toString() {
        return "CountData{count=" + this.count + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof AggregationData.CountData) && this.count == ((AggregationData.CountData) obj).getCount();
    }

    public int hashCode() {
        long j = this.count;
        return (int) (1000003 ^ (j ^ (j >>> 32)));
    }
}
