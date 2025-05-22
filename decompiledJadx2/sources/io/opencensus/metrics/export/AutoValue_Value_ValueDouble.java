package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Value;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class AutoValue_Value_ValueDouble extends Value.ValueDouble {
    private final double value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Value_ValueDouble(double d) {
        this.value = d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.opencensus.metrics.export.Value.ValueDouble
    public double getValue() {
        return this.value;
    }

    public String toString() {
        return "ValueDouble{value=" + this.value + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof Value.ValueDouble) && Double.doubleToLongBits(this.value) == Double.doubleToLongBits(((Value.ValueDouble) obj).getValue());
    }

    public int hashCode() {
        return (int) (1000003 ^ ((Double.doubleToLongBits(this.value) >>> 32) ^ Double.doubleToLongBits(this.value)));
    }
}
