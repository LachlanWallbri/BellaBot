package io.opencensus.metrics;

import io.opencensus.internal.Utils;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class DoubleCumulative {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class DoublePoint {
        public abstract void add(double d);
    }

    public abstract void clear();

    public abstract DoublePoint getDefaultTimeSeries();

    public abstract DoublePoint getOrCreateTimeSeries(List<LabelValue> list);

    public abstract void removeTimeSeries(List<LabelValue> list);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DoubleCumulative newNoopDoubleCumulative(String str, String str2, String str3, List<LabelKey> list) {
        return NoopDoubleCumulative.create(str, str2, str3, list);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class NoopDoubleCumulative extends DoubleCumulative {
        private final int labelKeysSize;

        @Override // io.opencensus.metrics.DoubleCumulative
        public void clear() {
        }

        @Override // io.opencensus.metrics.DoubleCumulative
        public /* bridge */ /* synthetic */ DoublePoint getOrCreateTimeSeries(List list) {
            return getOrCreateTimeSeries((List<LabelValue>) list);
        }

        static NoopDoubleCumulative create(String str, String str2, String str3, List<LabelKey> list) {
            return new NoopDoubleCumulative(str, str2, str3, list);
        }

        NoopDoubleCumulative(String str, String str2, String str3, List<LabelKey> list) {
            Utils.checkNotNull(str, "name");
            Utils.checkNotNull(str2, "description");
            Utils.checkNotNull(str3, "unit");
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelKeys"), "labelKey");
            this.labelKeysSize = list.size();
        }

        @Override // io.opencensus.metrics.DoubleCumulative
        public NoopDoublePoint getOrCreateTimeSeries(List<LabelValue> list) {
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelValues"), "labelValue");
            Utils.checkArgument(this.labelKeysSize == list.size(), "Label Keys and Label Values don't have same size.");
            return NoopDoublePoint.INSTANCE;
        }

        @Override // io.opencensus.metrics.DoubleCumulative
        public NoopDoublePoint getDefaultTimeSeries() {
            return NoopDoublePoint.INSTANCE;
        }

        @Override // io.opencensus.metrics.DoubleCumulative
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

            @Override // io.opencensus.metrics.DoubleCumulative.DoublePoint
            public void add(double d) {
            }

            private NoopDoublePoint() {
            }
        }
    }
}
