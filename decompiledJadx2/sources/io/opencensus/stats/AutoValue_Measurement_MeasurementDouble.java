package io.opencensus.stats;

import io.opencensus.stats.Measure;
import io.opencensus.stats.Measurement;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_Measurement_MeasurementDouble extends Measurement.MeasurementDouble {
    private final Measure.MeasureDouble measure;
    private final double value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Measurement_MeasurementDouble(Measure.MeasureDouble measureDouble, double d) {
        if (measureDouble == null) {
            throw new NullPointerException("Null measure");
        }
        this.measure = measureDouble;
        this.value = d;
    }

    @Override // io.opencensus.stats.Measurement.MeasurementDouble, io.opencensus.stats.Measurement
    public Measure.MeasureDouble getMeasure() {
        return this.measure;
    }

    @Override // io.opencensus.stats.Measurement.MeasurementDouble
    public double getValue() {
        return this.value;
    }

    public String toString() {
        return "MeasurementDouble{measure=" + this.measure + ", value=" + this.value + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Measurement.MeasurementDouble)) {
            return false;
        }
        Measurement.MeasurementDouble measurementDouble = (Measurement.MeasurementDouble) obj;
        return this.measure.equals(measurementDouble.getMeasure()) && Double.doubleToLongBits(this.value) == Double.doubleToLongBits(measurementDouble.getValue());
    }

    public int hashCode() {
        return (int) (((this.measure.hashCode() ^ 1000003) * 1000003) ^ ((Double.doubleToLongBits(this.value) >>> 32) ^ Double.doubleToLongBits(this.value)));
    }
}
