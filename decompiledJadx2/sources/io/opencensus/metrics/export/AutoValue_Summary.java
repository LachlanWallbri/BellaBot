package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Summary;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_Summary extends Summary {
    private final Long count;
    private final Summary.Snapshot snapshot;
    private final Double sum;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Summary(@Nullable Long l, @Nullable Double d, Summary.Snapshot snapshot) {
        this.count = l;
        this.sum = d;
        if (snapshot == null) {
            throw new NullPointerException("Null snapshot");
        }
        this.snapshot = snapshot;
    }

    @Override // io.opencensus.metrics.export.Summary
    @Nullable
    public Long getCount() {
        return this.count;
    }

    @Override // io.opencensus.metrics.export.Summary
    @Nullable
    public Double getSum() {
        return this.sum;
    }

    @Override // io.opencensus.metrics.export.Summary
    public Summary.Snapshot getSnapshot() {
        return this.snapshot;
    }

    public String toString() {
        return "Summary{count=" + this.count + ", sum=" + this.sum + ", snapshot=" + this.snapshot + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Summary)) {
            return false;
        }
        Summary summary = (Summary) obj;
        Long l = this.count;
        if (l != null ? l.equals(summary.getCount()) : summary.getCount() == null) {
            Double d = this.sum;
            if (d != null ? d.equals(summary.getSum()) : summary.getSum() == null) {
                if (this.snapshot.equals(summary.getSnapshot())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        Long l = this.count;
        int hashCode = ((l == null ? 0 : l.hashCode()) ^ 1000003) * 1000003;
        Double d = this.sum;
        return ((hashCode ^ (d != null ? d.hashCode() : 0)) * 1000003) ^ this.snapshot.hashCode();
    }
}
