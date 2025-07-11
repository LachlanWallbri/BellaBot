package io.opencensus.stats;

import io.opencensus.common.Function;
import io.opencensus.internal.Utils;
import io.opencensus.metrics.data.Exemplar;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class AggregationData {
    public abstract <T> T match(Function<? super SumDataDouble, T> function, Function<? super SumDataLong, T> function2, Function<? super CountData, T> function3, Function<? super DistributionData, T> function4, Function<? super LastValueDataDouble, T> function5, Function<? super LastValueDataLong, T> function6, Function<? super AggregationData, T> function7);

    private AggregationData() {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class SumDataDouble extends AggregationData {
        public abstract double getSum();

        /* JADX INFO: Access modifiers changed from: package-private */
        public SumDataDouble() {
            super();
        }

        public static SumDataDouble create(double d) {
            return new AutoValue_AggregationData_SumDataDouble(d);
        }

        @Override // io.opencensus.stats.AggregationData
        public final <T> T match(Function<? super SumDataDouble, T> function, Function<? super SumDataLong, T> function2, Function<? super CountData, T> function3, Function<? super DistributionData, T> function4, Function<? super LastValueDataDouble, T> function5, Function<? super LastValueDataLong, T> function6, Function<? super AggregationData, T> function7) {
            return function.apply(this);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class SumDataLong extends AggregationData {
        public abstract long getSum();

        /* JADX INFO: Access modifiers changed from: package-private */
        public SumDataLong() {
            super();
        }

        public static SumDataLong create(long j) {
            return new AutoValue_AggregationData_SumDataLong(j);
        }

        @Override // io.opencensus.stats.AggregationData
        public final <T> T match(Function<? super SumDataDouble, T> function, Function<? super SumDataLong, T> function2, Function<? super CountData, T> function3, Function<? super DistributionData, T> function4, Function<? super LastValueDataDouble, T> function5, Function<? super LastValueDataLong, T> function6, Function<? super AggregationData, T> function7) {
            return function2.apply(this);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class CountData extends AggregationData {
        public abstract long getCount();

        /* JADX INFO: Access modifiers changed from: package-private */
        public CountData() {
            super();
        }

        public static CountData create(long j) {
            return new AutoValue_AggregationData_CountData(j);
        }

        @Override // io.opencensus.stats.AggregationData
        public final <T> T match(Function<? super SumDataDouble, T> function, Function<? super SumDataLong, T> function2, Function<? super CountData, T> function3, Function<? super DistributionData, T> function4, Function<? super LastValueDataDouble, T> function5, Function<? super LastValueDataLong, T> function6, Function<? super AggregationData, T> function7) {
            return function3.apply(this);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    @Deprecated
    /* loaded from: classes8.dex */
    public static abstract class MeanData extends AggregationData {
        public abstract long getCount();

        public abstract double getMean();

        /* JADX INFO: Access modifiers changed from: package-private */
        public MeanData() {
            super();
        }

        public static MeanData create(double d, long j) {
            return new AutoValue_AggregationData_MeanData(d, j);
        }

        @Override // io.opencensus.stats.AggregationData
        public final <T> T match(Function<? super SumDataDouble, T> function, Function<? super SumDataLong, T> function2, Function<? super CountData, T> function3, Function<? super DistributionData, T> function4, Function<? super LastValueDataDouble, T> function5, Function<? super LastValueDataLong, T> function6, Function<? super AggregationData, T> function7) {
            return function7.apply(this);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class DistributionData extends AggregationData {
        public abstract List<Long> getBucketCounts();

        public abstract long getCount();

        public abstract List<Exemplar> getExemplars();

        @Deprecated
        public double getMax() {
            return 0.0d;
        }

        public abstract double getMean();

        @Deprecated
        public double getMin() {
            return 0.0d;
        }

        public abstract double getSumOfSquaredDeviations();

        /* JADX INFO: Access modifiers changed from: package-private */
        public DistributionData() {
            super();
        }

        @Deprecated
        public static DistributionData create(double d, long j, double d2, double d3, double d4, List<Long> list, List<Exemplar> list2) {
            return create(d, j, d4, list, list2);
        }

        public static DistributionData create(double d, long j, double d2, List<Long> list, List<Exemplar> list2) {
            List unmodifiableList = Collections.unmodifiableList(new ArrayList((Collection) Utils.checkNotNull(list, "bucketCounts")));
            Iterator it = unmodifiableList.iterator();
            while (it.hasNext()) {
                Utils.checkNotNull((Long) it.next(), "bucketCount");
            }
            Utils.checkNotNull(list2, "exemplars");
            Iterator<Exemplar> it2 = list2.iterator();
            while (it2.hasNext()) {
                Utils.checkNotNull(it2.next(), "exemplar");
            }
            return new AutoValue_AggregationData_DistributionData(d, j, d2, unmodifiableList, Collections.unmodifiableList(new ArrayList(list2)));
        }

        @Deprecated
        public static DistributionData create(double d, long j, double d2, double d3, double d4, List<Long> list) {
            return create(d, j, d4, list, Collections.emptyList());
        }

        public static DistributionData create(double d, long j, double d2, List<Long> list) {
            return create(d, j, d2, list, Collections.emptyList());
        }

        @Override // io.opencensus.stats.AggregationData
        public final <T> T match(Function<? super SumDataDouble, T> function, Function<? super SumDataLong, T> function2, Function<? super CountData, T> function3, Function<? super DistributionData, T> function4, Function<? super LastValueDataDouble, T> function5, Function<? super LastValueDataLong, T> function6, Function<? super AggregationData, T> function7) {
            return function4.apply(this);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class LastValueDataDouble extends AggregationData {
        public abstract double getLastValue();

        /* JADX INFO: Access modifiers changed from: package-private */
        public LastValueDataDouble() {
            super();
        }

        public static LastValueDataDouble create(double d) {
            return new AutoValue_AggregationData_LastValueDataDouble(d);
        }

        @Override // io.opencensus.stats.AggregationData
        public final <T> T match(Function<? super SumDataDouble, T> function, Function<? super SumDataLong, T> function2, Function<? super CountData, T> function3, Function<? super DistributionData, T> function4, Function<? super LastValueDataDouble, T> function5, Function<? super LastValueDataLong, T> function6, Function<? super AggregationData, T> function7) {
            return function5.apply(this);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class LastValueDataLong extends AggregationData {
        public abstract long getLastValue();

        /* JADX INFO: Access modifiers changed from: package-private */
        public LastValueDataLong() {
            super();
        }

        public static LastValueDataLong create(long j) {
            return new AutoValue_AggregationData_LastValueDataLong(j);
        }

        @Override // io.opencensus.stats.AggregationData
        public final <T> T match(Function<? super SumDataDouble, T> function, Function<? super SumDataLong, T> function2, Function<? super CountData, T> function3, Function<? super DistributionData, T> function4, Function<? super LastValueDataDouble, T> function5, Function<? super LastValueDataLong, T> function6, Function<? super AggregationData, T> function7) {
            return function6.apply(this);
        }
    }
}
