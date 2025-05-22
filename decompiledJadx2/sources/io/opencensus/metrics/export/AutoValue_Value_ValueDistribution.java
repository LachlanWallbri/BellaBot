package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Value;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class AutoValue_Value_ValueDistribution extends Value.ValueDistribution {
    private final Distribution value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Value_ValueDistribution(Distribution distribution) {
        if (distribution == null) {
            throw new NullPointerException("Null value");
        }
        this.value = distribution;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.opencensus.metrics.export.Value.ValueDistribution
    public Distribution getValue() {
        return this.value;
    }

    public String toString() {
        return "ValueDistribution{value=" + this.value + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Value.ValueDistribution) {
            return this.value.equals(((Value.ValueDistribution) obj).getValue());
        }
        return false;
    }

    public int hashCode() {
        return this.value.hashCode() ^ 1000003;
    }
}
