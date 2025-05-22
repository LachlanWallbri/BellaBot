package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Distribution;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class AutoValue_Distribution_BucketOptions_ExplicitOptions extends Distribution.BucketOptions.ExplicitOptions {
    private final List<Double> bucketBoundaries;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Distribution_BucketOptions_ExplicitOptions(List<Double> list) {
        if (list == null) {
            throw new NullPointerException("Null bucketBoundaries");
        }
        this.bucketBoundaries = list;
    }

    @Override // io.opencensus.metrics.export.Distribution.BucketOptions.ExplicitOptions
    public List<Double> getBucketBoundaries() {
        return this.bucketBoundaries;
    }

    public String toString() {
        return "ExplicitOptions{bucketBoundaries=" + this.bucketBoundaries + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Distribution.BucketOptions.ExplicitOptions) {
            return this.bucketBoundaries.equals(((Distribution.BucketOptions.ExplicitOptions) obj).getBucketBoundaries());
        }
        return false;
    }

    public int hashCode() {
        return this.bucketBoundaries.hashCode() ^ 1000003;
    }
}
