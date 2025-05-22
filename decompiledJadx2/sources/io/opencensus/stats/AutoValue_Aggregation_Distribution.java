package io.opencensus.stats;

import io.opencensus.stats.Aggregation;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_Aggregation_Distribution extends Aggregation.Distribution {
    private final BucketBoundaries bucketBoundaries;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Aggregation_Distribution(BucketBoundaries bucketBoundaries) {
        if (bucketBoundaries == null) {
            throw new NullPointerException("Null bucketBoundaries");
        }
        this.bucketBoundaries = bucketBoundaries;
    }

    @Override // io.opencensus.stats.Aggregation.Distribution
    public BucketBoundaries getBucketBoundaries() {
        return this.bucketBoundaries;
    }

    public String toString() {
        return "Distribution{bucketBoundaries=" + this.bucketBoundaries + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Aggregation.Distribution) {
            return this.bucketBoundaries.equals(((Aggregation.Distribution) obj).getBucketBoundaries());
        }
        return false;
    }

    public int hashCode() {
        return this.bucketBoundaries.hashCode() ^ 1000003;
    }
}
