package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Value;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class AutoValue_Value_ValueLong extends Value.ValueLong {
    private final long value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Value_ValueLong(long j) {
        this.value = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.opencensus.metrics.export.Value.ValueLong
    public long getValue() {
        return this.value;
    }

    public String toString() {
        return "ValueLong{value=" + this.value + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof Value.ValueLong) && this.value == ((Value.ValueLong) obj).getValue();
    }

    public int hashCode() {
        long j = this.value;
        return (int) (1000003 ^ (j ^ (j >>> 32)));
    }
}
