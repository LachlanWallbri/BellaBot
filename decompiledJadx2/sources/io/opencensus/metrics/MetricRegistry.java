package io.opencensus.metrics;

import io.opencensus.internal.Utils;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class MetricRegistry {
    public abstract DerivedDoubleCumulative addDerivedDoubleCumulative(String str, MetricOptions metricOptions);

    public abstract DerivedDoubleGauge addDerivedDoubleGauge(String str, MetricOptions metricOptions);

    public abstract DerivedLongCumulative addDerivedLongCumulative(String str, MetricOptions metricOptions);

    public abstract DerivedLongGauge addDerivedLongGauge(String str, MetricOptions metricOptions);

    public abstract DoubleCumulative addDoubleCumulative(String str, MetricOptions metricOptions);

    public abstract DoubleGauge addDoubleGauge(String str, MetricOptions metricOptions);

    public abstract LongCumulative addLongCumulative(String str, MetricOptions metricOptions);

    public abstract LongGauge addLongGauge(String str, MetricOptions metricOptions);

    @Deprecated
    public LongGauge addLongGauge(String str, String str2, String str3, List<LabelKey> list) {
        return addLongGauge(str, MetricOptions.builder().setDescription(str2).setUnit(str3).setLabelKeys(list).build());
    }

    @Deprecated
    public DoubleGauge addDoubleGauge(String str, String str2, String str3, List<LabelKey> list) {
        return addDoubleGauge(str, MetricOptions.builder().setDescription(str2).setUnit(str3).setLabelKeys(list).build());
    }

    @Deprecated
    public DerivedLongGauge addDerivedLongGauge(String str, String str2, String str3, List<LabelKey> list) {
        return addDerivedLongGauge(str, MetricOptions.builder().setDescription(str2).setUnit(str3).setLabelKeys(list).build());
    }

    @Deprecated
    public DerivedDoubleGauge addDerivedDoubleGauge(String str, String str2, String str3, List<LabelKey> list) {
        return addDerivedDoubleGauge(str, MetricOptions.builder().setDescription(str2).setUnit(str3).setLabelKeys(list).build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MetricRegistry newNoopMetricRegistry() {
        return new NoopMetricRegistry();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class NoopMetricRegistry extends MetricRegistry {
        private NoopMetricRegistry() {
        }

        @Override // io.opencensus.metrics.MetricRegistry
        public LongGauge addLongGauge(String str, MetricOptions metricOptions) {
            return LongGauge.newNoopLongGauge((String) Utils.checkNotNull(str, "name"), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        @Override // io.opencensus.metrics.MetricRegistry
        public DoubleGauge addDoubleGauge(String str, MetricOptions metricOptions) {
            return DoubleGauge.newNoopDoubleGauge((String) Utils.checkNotNull(str, "name"), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        @Override // io.opencensus.metrics.MetricRegistry
        public DerivedLongGauge addDerivedLongGauge(String str, MetricOptions metricOptions) {
            return DerivedLongGauge.newNoopDerivedLongGauge((String) Utils.checkNotNull(str, "name"), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        @Override // io.opencensus.metrics.MetricRegistry
        public DerivedDoubleGauge addDerivedDoubleGauge(String str, MetricOptions metricOptions) {
            return DerivedDoubleGauge.newNoopDerivedDoubleGauge((String) Utils.checkNotNull(str, "name"), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        @Override // io.opencensus.metrics.MetricRegistry
        public LongCumulative addLongCumulative(String str, MetricOptions metricOptions) {
            return LongCumulative.newNoopLongCumulative((String) Utils.checkNotNull(str, "name"), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        @Override // io.opencensus.metrics.MetricRegistry
        public DoubleCumulative addDoubleCumulative(String str, MetricOptions metricOptions) {
            return DoubleCumulative.newNoopDoubleCumulative((String) Utils.checkNotNull(str, "name"), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        @Override // io.opencensus.metrics.MetricRegistry
        public DerivedLongCumulative addDerivedLongCumulative(String str, MetricOptions metricOptions) {
            return DerivedLongCumulative.newNoopDerivedLongCumulative((String) Utils.checkNotNull(str, "name"), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        @Override // io.opencensus.metrics.MetricRegistry
        public DerivedDoubleCumulative addDerivedDoubleCumulative(String str, MetricOptions metricOptions) {
            return DerivedDoubleCumulative.newNoopDerivedDoubleCumulative((String) Utils.checkNotNull(str, "name"), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }
    }
}
