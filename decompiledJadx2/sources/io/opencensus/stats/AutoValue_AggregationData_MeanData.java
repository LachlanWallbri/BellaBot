package io.opencensus.stats;

import io.opencensus.stats.AggregationData;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes8.dex */
final class AutoValue_AggregationData_MeanData extends AggregationData.MeanData {
    private final long count;
    private final double mean;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AggregationData_MeanData(double d, long j) {
        this.mean = d;
        this.count = j;
    }

    @Override // io.opencensus.stats.AggregationData.MeanData
    public double getMean() {
        return this.mean;
    }

    @Override // io.opencensus.stats.AggregationData.MeanData
    public long getCount() {
        return this.count;
    }

    public String toString() {
        return "MeanData{mean=" + this.mean + ", count=" + this.count + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AggregationData.MeanData)) {
            return false;
        }
        AggregationData.MeanData meanData = (AggregationData.MeanData) obj;
        return Double.doubleToLongBits(this.mean) == Double.doubleToLongBits(meanData.getMean()) && this.count == meanData.getCount();
    }

    public int hashCode() {
        long doubleToLongBits = ((int) (1000003 ^ ((Double.doubleToLongBits(this.mean) >>> 32) ^ Double.doubleToLongBits(this.mean)))) * 1000003;
        long j = this.count;
        return (int) (doubleToLongBits ^ (j ^ (j >>> 32)));
    }
}
