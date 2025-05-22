package io.opencensus.stats;

import io.opencensus.common.Function;
import io.opencensus.stats.Measure;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class Measurement {
    public abstract Measure getMeasure();

    public abstract <T> T match(Function<? super MeasurementDouble, T> function, Function<? super MeasurementLong, T> function2, Function<? super Measurement, T> function3);

    private Measurement() {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class MeasurementDouble extends Measurement {
        @Override // io.opencensus.stats.Measurement
        public abstract Measure.MeasureDouble getMeasure();

        public abstract double getValue();

        /* JADX INFO: Access modifiers changed from: package-private */
        public MeasurementDouble() {
            super();
        }

        public static MeasurementDouble create(Measure.MeasureDouble measureDouble, double d) {
            return new AutoValue_Measurement_MeasurementDouble(measureDouble, d);
        }

        @Override // io.opencensus.stats.Measurement
        public <T> T match(Function<? super MeasurementDouble, T> function, Function<? super MeasurementLong, T> function2, Function<? super Measurement, T> function3) {
            return function.apply(this);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class MeasurementLong extends Measurement {
        @Override // io.opencensus.stats.Measurement
        public abstract Measure.MeasureLong getMeasure();

        public abstract long getValue();

        /* JADX INFO: Access modifiers changed from: package-private */
        public MeasurementLong() {
            super();
        }

        public static MeasurementLong create(Measure.MeasureLong measureLong, long j) {
            return new AutoValue_Measurement_MeasurementLong(measureLong, j);
        }

        @Override // io.opencensus.stats.Measurement
        public <T> T match(Function<? super MeasurementDouble, T> function, Function<? super MeasurementLong, T> function2, Function<? super Measurement, T> function3) {
            return function2.apply(this);
        }
    }
}
