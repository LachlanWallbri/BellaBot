package io.opencensus.stats;

import io.opencensus.stats.Measure;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class AutoValue_Measure_MeasureDouble extends Measure.MeasureDouble {
    private final String description;
    private final String name;
    private final String unit;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Measure_MeasureDouble(String str, String str2, String str3) {
        if (str == null) {
            throw new NullPointerException("Null name");
        }
        this.name = str;
        if (str2 == null) {
            throw new NullPointerException("Null description");
        }
        this.description = str2;
        if (str3 == null) {
            throw new NullPointerException("Null unit");
        }
        this.unit = str3;
    }

    @Override // io.opencensus.stats.Measure.MeasureDouble, io.opencensus.stats.Measure
    public String getName() {
        return this.name;
    }

    @Override // io.opencensus.stats.Measure.MeasureDouble, io.opencensus.stats.Measure
    public String getDescription() {
        return this.description;
    }

    @Override // io.opencensus.stats.Measure.MeasureDouble, io.opencensus.stats.Measure
    public String getUnit() {
        return this.unit;
    }

    public String toString() {
        return "MeasureDouble{name=" + this.name + ", description=" + this.description + ", unit=" + this.unit + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Measure.MeasureDouble)) {
            return false;
        }
        Measure.MeasureDouble measureDouble = (Measure.MeasureDouble) obj;
        return this.name.equals(measureDouble.getName()) && this.description.equals(measureDouble.getDescription()) && this.unit.equals(measureDouble.getUnit());
    }

    public int hashCode() {
        return ((((this.name.hashCode() ^ 1000003) * 1000003) ^ this.description.hashCode()) * 1000003) ^ this.unit.hashCode();
    }
}
