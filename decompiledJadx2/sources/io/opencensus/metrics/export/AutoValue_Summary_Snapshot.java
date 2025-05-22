package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Summary;
import java.util.List;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_Summary_Snapshot extends Summary.Snapshot {
    private final Long count;
    private final Double sum;
    private final List<Summary.Snapshot.ValueAtPercentile> valueAtPercentiles;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Summary_Snapshot(@Nullable Long l, @Nullable Double d, List<Summary.Snapshot.ValueAtPercentile> list) {
        this.count = l;
        this.sum = d;
        if (list == null) {
            throw new NullPointerException("Null valueAtPercentiles");
        }
        this.valueAtPercentiles = list;
    }

    @Override // io.opencensus.metrics.export.Summary.Snapshot
    @Nullable
    public Long getCount() {
        return this.count;
    }

    @Override // io.opencensus.metrics.export.Summary.Snapshot
    @Nullable
    public Double getSum() {
        return this.sum;
    }

    @Override // io.opencensus.metrics.export.Summary.Snapshot
    public List<Summary.Snapshot.ValueAtPercentile> getValueAtPercentiles() {
        return this.valueAtPercentiles;
    }

    public String toString() {
        return "Snapshot{count=" + this.count + ", sum=" + this.sum + ", valueAtPercentiles=" + this.valueAtPercentiles + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Summary.Snapshot)) {
            return false;
        }
        Summary.Snapshot snapshot = (Summary.Snapshot) obj;
        Long l = this.count;
        if (l != null ? l.equals(snapshot.getCount()) : snapshot.getCount() == null) {
            Double d = this.sum;
            if (d != null ? d.equals(snapshot.getSum()) : snapshot.getSum() == null) {
                if (this.valueAtPercentiles.equals(snapshot.getValueAtPercentiles())) {
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
        return ((hashCode ^ (d != null ? d.hashCode() : 0)) * 1000003) ^ this.valueAtPercentiles.hashCode();
    }
}
