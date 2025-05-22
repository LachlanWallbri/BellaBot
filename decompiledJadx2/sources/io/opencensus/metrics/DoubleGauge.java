package io.opencensus.metrics;

import io.opencensus.internal.Utils;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class DoubleGauge {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class DoublePoint {
        public abstract void add(double d);

        public abstract void set(double d);
    }

    public abstract void clear();

    public abstract DoublePoint getDefaultTimeSeries();

    public abstract DoublePoint getOrCreateTimeSeries(List<LabelValue> list);

    public abstract void removeTimeSeries(List<LabelValue> list);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DoubleGauge newNoopDoubleGauge(String str, String str2, String str3, List<LabelKey> list) {
        return NoopDoubleGauge.create(str, str2, str3, list);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class NoopDoubleGauge extends DoubleGauge {
        private final int labelKeysSize;

        @Override // io.opencensus.metrics.DoubleGauge
        public void clear() {
        }

        @Override // io.opencensus.metrics.DoubleGauge
        public /* bridge */ /* synthetic */ DoublePoint getOrCreateTimeSeries(List list) {
            return getOrCreateTimeSeries((List<LabelValue>) list);
        }

        static NoopDoubleGauge create(String str, String str2, String str3, List<LabelKey> list) {
            return new NoopDoubleGauge(str, str2, str3, list);
        }

        NoopDoubleGauge(String str, String str2, String str3, List<LabelKey> list) {
            Utils.checkNotNull(str, "name");
            Utils.checkNotNull(str2, "description");
            Utils.checkNotNull(str3, "unit");
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelKeys"), "labelKey");
            this.labelKeysSize = list.size();
        }

        @Override // io.opencensus.metrics.DoubleGauge
        public NoopDoublePoint getOrCreateTimeSeries(List<LabelValue> list) {
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelValues"), "labelValue");
            Utils.checkArgument(this.labelKeysSize == list.size(), "Label Keys and Label Values don't have same size.");
            return NoopDoublePoint.INSTANCE;
        }

        @Override // io.opencensus.metrics.DoubleGauge
        public NoopDoublePoint getDefaultTimeSeries() {
            return NoopDoublePoint.INSTANCE;
        }

        @Override // io.opencensus.metrics.DoubleGauge
        public void removeTimeSeries(List<LabelValue> list) {
            Utils.checkNotNull(list, "labelValues");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes8.dex */
        public static final class NoopDoublePoint extends DoublePoint {
            private static final NoopDoublePoint INSTANCE = new NoopDoublePoint();

            @Override // io.opencensus.metrics.DoubleGauge.DoublePoint
            public void add(double d) {
            }

            @Override // io.opencensus.metrics.DoubleGauge.DoublePoint
            public void set(double d) {
            }

            private NoopDoublePoint() {
            }
        }
    }
}
