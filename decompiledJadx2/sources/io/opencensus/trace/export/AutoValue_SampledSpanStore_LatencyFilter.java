package io.opencensus.trace.export;

import io.opencensus.trace.export.SampledSpanStore;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_SampledSpanStore_LatencyFilter extends SampledSpanStore.LatencyFilter {
    private final long latencyLowerNs;
    private final long latencyUpperNs;
    private final int maxSpansToReturn;
    private final String spanName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SampledSpanStore_LatencyFilter(String str, long j, long j2, int i) {
        if (str == null) {
            throw new NullPointerException("Null spanName");
        }
        this.spanName = str;
        this.latencyLowerNs = j;
        this.latencyUpperNs = j2;
        this.maxSpansToReturn = i;
    }

    @Override // io.opencensus.trace.export.SampledSpanStore.LatencyFilter
    public String getSpanName() {
        return this.spanName;
    }

    @Override // io.opencensus.trace.export.SampledSpanStore.LatencyFilter
    public long getLatencyLowerNs() {
        return this.latencyLowerNs;
    }

    @Override // io.opencensus.trace.export.SampledSpanStore.LatencyFilter
    public long getLatencyUpperNs() {
        return this.latencyUpperNs;
    }

    @Override // io.opencensus.trace.export.SampledSpanStore.LatencyFilter
    public int getMaxSpansToReturn() {
        return this.maxSpansToReturn;
    }

    public String toString() {
        return "LatencyFilter{spanName=" + this.spanName + ", latencyLowerNs=" + this.latencyLowerNs + ", latencyUpperNs=" + this.latencyUpperNs + ", maxSpansToReturn=" + this.maxSpansToReturn + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SampledSpanStore.LatencyFilter)) {
            return false;
        }
        SampledSpanStore.LatencyFilter latencyFilter = (SampledSpanStore.LatencyFilter) obj;
        return this.spanName.equals(latencyFilter.getSpanName()) && this.latencyLowerNs == latencyFilter.getLatencyLowerNs() && this.latencyUpperNs == latencyFilter.getLatencyUpperNs() && this.maxSpansToReturn == latencyFilter.getMaxSpansToReturn();
    }

    public int hashCode() {
        long hashCode = (this.spanName.hashCode() ^ 1000003) * 1000003;
        long j = this.latencyLowerNs;
        long j2 = ((int) (hashCode ^ (j ^ (j >>> 32)))) * 1000003;
        long j3 = this.latencyUpperNs;
        return (((int) (j2 ^ (j3 ^ (j3 >>> 32)))) * 1000003) ^ this.maxSpansToReturn;
    }
}
