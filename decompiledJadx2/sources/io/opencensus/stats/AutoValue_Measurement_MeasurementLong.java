package io.opencensus.stats;

import io.opencensus.stats.Measure;
import io.opencensus.stats.Measurement;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_Measurement_MeasurementLong extends Measurement.MeasurementLong {
    private final Measure.MeasureLong measure;
    private final long value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Measurement_MeasurementLong(Measure.MeasureLong measureLong, long j) {
        if (measureLong == null) {
            throw new NullPointerException("Null measure");
        }
        this.measure = measureLong;
        this.value = j;
    }

    @Override // io.opencensus.stats.Measurement.MeasurementLong, io.opencensus.stats.Measurement
    public Measure.MeasureLong getMeasure() {
        return this.measure;
    }

    @Override // io.opencensus.stats.Measurement.MeasurementLong
    public long getValue() {
        return this.value;
    }

    public String toString() {
        return "MeasurementLong{measure=" + this.measure + ", value=" + this.value + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Measurement.MeasurementLong)) {
            return false;
        }
        Measurement.MeasurementLong measurementLong = (Measurement.MeasurementLong) obj;
        return this.measure.equals(measurementLong.getMeasure()) && this.value == measurementLong.getValue();
    }

    public int hashCode() {
        long hashCode = (this.measure.hashCode() ^ 1000003) * 1000003;
        long j = this.value;
        return (int) (hashCode ^ (j ^ (j >>> 32)));
    }
}
