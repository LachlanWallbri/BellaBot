package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Distribution;
import java.util.List;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_Distribution extends Distribution {
    private final Distribution.BucketOptions bucketOptions;
    private final List<Distribution.Bucket> buckets;
    private final long count;
    private final double sum;
    private final double sumOfSquaredDeviations;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Distribution(long j, double d, double d2, @Nullable Distribution.BucketOptions bucketOptions, List<Distribution.Bucket> list) {
        this.count = j;
        this.sum = d;
        this.sumOfSquaredDeviations = d2;
        this.bucketOptions = bucketOptions;
        if (list == null) {
            throw new NullPointerException("Null buckets");
        }
        this.buckets = list;
    }

    @Override // io.opencensus.metrics.export.Distribution
    public long getCount() {
        return this.count;
    }

    @Override // io.opencensus.metrics.export.Distribution
    public double getSum() {
        return this.sum;
    }

    @Override // io.opencensus.metrics.export.Distribution
    public double getSumOfSquaredDeviations() {
        return this.sumOfSquaredDeviations;
    }

    @Override // io.opencensus.metrics.export.Distribution
    @Nullable
    public Distribution.BucketOptions getBucketOptions() {
        return this.bucketOptions;
    }

    @Override // io.opencensus.metrics.export.Distribution
    public List<Distribution.Bucket> getBuckets() {
        return this.buckets;
    }

    public String toString() {
        return "Distribution{count=" + this.count + ", sum=" + this.sum + ", sumOfSquaredDeviations=" + this.sumOfSquaredDeviations + ", bucketOptions=" + this.bucketOptions + ", buckets=" + this.buckets + "}";
    }

    public boolean equals(Object obj) {
        Distribution.BucketOptions bucketOptions;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Distribution)) {
            return false;
        }
        Distribution distribution = (Distribution) obj;
        return this.count == distribution.getCount() && Double.doubleToLongBits(this.sum) == Double.doubleToLongBits(distribution.getSum()) && Double.doubleToLongBits(this.sumOfSquaredDeviations) == Double.doubleToLongBits(distribution.getSumOfSquaredDeviations()) && ((bucketOptions = this.bucketOptions) != null ? bucketOptions.equals(distribution.getBucketOptions()) : distribution.getBucketOptions() == null) && this.buckets.equals(distribution.getBuckets());
    }

    public int hashCode() {
        long j = this.count;
        int doubleToLongBits = ((int) ((((int) ((((int) (1000003 ^ (j ^ (j >>> 32)))) * 1000003) ^ ((Double.doubleToLongBits(this.sum) >>> 32) ^ Double.doubleToLongBits(this.sum)))) * 1000003) ^ ((Double.doubleToLongBits(this.sumOfSquaredDeviations) >>> 32) ^ Double.doubleToLongBits(this.sumOfSquaredDeviations)))) * 1000003;
        Distribution.BucketOptions bucketOptions = this.bucketOptions;
        return this.buckets.hashCode() ^ ((doubleToLongBits ^ (bucketOptions == null ? 0 : bucketOptions.hashCode())) * 1000003);
    }
}
