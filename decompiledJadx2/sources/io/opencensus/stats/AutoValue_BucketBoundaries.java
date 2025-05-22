package io.opencensus.stats;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_BucketBoundaries extends BucketBoundaries {
    private final List<Double> boundaries;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_BucketBoundaries(List<Double> list) {
        if (list == null) {
            throw new NullPointerException("Null boundaries");
        }
        this.boundaries = list;
    }

    @Override // io.opencensus.stats.BucketBoundaries
    public List<Double> getBoundaries() {
        return this.boundaries;
    }

    public String toString() {
        return "BucketBoundaries{boundaries=" + this.boundaries + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BucketBoundaries) {
            return this.boundaries.equals(((BucketBoundaries) obj).getBoundaries());
        }
        return false;
    }

    public int hashCode() {
        return this.boundaries.hashCode() ^ 1000003;
    }
}
