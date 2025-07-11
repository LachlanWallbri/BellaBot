package io.opencensus.metrics.export;

import io.opencensus.common.Function;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class Value {
    public abstract <T> T match(Function<? super Double, T> function, Function<? super Long, T> function2, Function<? super Distribution, T> function3, Function<? super Summary, T> function4, Function<? super Value, T> function5);

    Value() {
    }

    public static Value doubleValue(double d) {
        return ValueDouble.create(d);
    }

    public static Value longValue(long j) {
        return ValueLong.create(j);
    }

    public static Value distributionValue(Distribution distribution) {
        return ValueDistribution.create(distribution);
    }

    public static Value summaryValue(Summary summary) {
        return ValueSummary.create(summary);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    static abstract class ValueDouble extends Value {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract double getValue();

        @Override // io.opencensus.metrics.export.Value
        public final <T> T match(Function<? super Double, T> function, Function<? super Long, T> function2, Function<? super Distribution, T> function3, Function<? super Summary, T> function4, Function<? super Value, T> function5) {
            return function.apply(Double.valueOf(getValue()));
        }

        static ValueDouble create(double d) {
            return new AutoValue_Value_ValueDouble(d);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    static abstract class ValueLong extends Value {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract long getValue();

        @Override // io.opencensus.metrics.export.Value
        public final <T> T match(Function<? super Double, T> function, Function<? super Long, T> function2, Function<? super Distribution, T> function3, Function<? super Summary, T> function4, Function<? super Value, T> function5) {
            return function2.apply(Long.valueOf(getValue()));
        }

        static ValueLong create(long j) {
            return new AutoValue_Value_ValueLong(j);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    static abstract class ValueDistribution extends Value {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Distribution getValue();

        @Override // io.opencensus.metrics.export.Value
        public final <T> T match(Function<? super Double, T> function, Function<? super Long, T> function2, Function<? super Distribution, T> function3, Function<? super Summary, T> function4, Function<? super Value, T> function5) {
            return function3.apply(getValue());
        }

        static ValueDistribution create(Distribution distribution) {
            return new AutoValue_Value_ValueDistribution(distribution);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    static abstract class ValueSummary extends Value {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Summary getValue();

        @Override // io.opencensus.metrics.export.Value
        public final <T> T match(Function<? super Double, T> function, Function<? super Long, T> function2, Function<? super Distribution, T> function3, Function<? super Summary, T> function4, Function<? super Value, T> function5) {
            return function4.apply(getValue());
        }

        static ValueSummary create(Summary summary) {
            return new AutoValue_Value_ValueSummary(summary);
        }
    }
}
