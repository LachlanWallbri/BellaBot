package io.opencensus.trace.export;

import io.opencensus.trace.export.RunningSpanStore;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class AutoValue_RunningSpanStore_Summary extends RunningSpanStore.Summary {
    private final Map<String, RunningSpanStore.PerSpanNameSummary> perSpanNameSummary;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_RunningSpanStore_Summary(Map<String, RunningSpanStore.PerSpanNameSummary> map) {
        if (map == null) {
            throw new NullPointerException("Null perSpanNameSummary");
        }
        this.perSpanNameSummary = map;
    }

    @Override // io.opencensus.trace.export.RunningSpanStore.Summary
    public Map<String, RunningSpanStore.PerSpanNameSummary> getPerSpanNameSummary() {
        return this.perSpanNameSummary;
    }

    public String toString() {
        return "Summary{perSpanNameSummary=" + this.perSpanNameSummary + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RunningSpanStore.Summary) {
            return this.perSpanNameSummary.equals(((RunningSpanStore.Summary) obj).getPerSpanNameSummary());
        }
        return false;
    }

    public int hashCode() {
        return this.perSpanNameSummary.hashCode() ^ 1000003;
    }
}
