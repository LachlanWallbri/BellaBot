package io.opencensus.trace.export;

import io.opencensus.trace.export.SampledSpanStore;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class AutoValue_SampledSpanStore_Summary extends SampledSpanStore.Summary {
    private final Map<String, SampledSpanStore.PerSpanNameSummary> perSpanNameSummary;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SampledSpanStore_Summary(Map<String, SampledSpanStore.PerSpanNameSummary> map) {
        if (map == null) {
            throw new NullPointerException("Null perSpanNameSummary");
        }
        this.perSpanNameSummary = map;
    }

    @Override // io.opencensus.trace.export.SampledSpanStore.Summary
    public Map<String, SampledSpanStore.PerSpanNameSummary> getPerSpanNameSummary() {
        return this.perSpanNameSummary;
    }

    public String toString() {
        return "Summary{perSpanNameSummary=" + this.perSpanNameSummary + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SampledSpanStore.Summary) {
            return this.perSpanNameSummary.equals(((SampledSpanStore.Summary) obj).getPerSpanNameSummary());
        }
        return false;
    }

    public int hashCode() {
        return this.perSpanNameSummary.hashCode() ^ 1000003;
    }
}
