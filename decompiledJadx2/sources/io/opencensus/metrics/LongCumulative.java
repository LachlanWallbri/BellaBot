package io.opencensus.metrics;

import io.opencensus.internal.Utils;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class LongCumulative {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class LongPoint {
        public abstract void add(long j);
    }

    public abstract void clear();

    public abstract LongPoint getDefaultTimeSeries();

    public abstract LongPoint getOrCreateTimeSeries(List<LabelValue> list);

    public abstract void removeTimeSeries(List<LabelValue> list);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LongCumulative newNoopLongCumulative(String str, String str2, String str3, List<LabelKey> list) {
        return NoopLongCumulative.create(str, str2, str3, list);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class NoopLongCumulative extends LongCumulative {
        private final int labelKeysSize;

        @Override // io.opencensus.metrics.LongCumulative
        public void clear() {
        }

        @Override // io.opencensus.metrics.LongCumulative
        public /* bridge */ /* synthetic */ LongPoint getOrCreateTimeSeries(List list) {
            return getOrCreateTimeSeries((List<LabelValue>) list);
        }

        static NoopLongCumulative create(String str, String str2, String str3, List<LabelKey> list) {
            return new NoopLongCumulative(str, str2, str3, list);
        }

        NoopLongCumulative(String str, String str2, String str3, List<LabelKey> list) {
            this.labelKeysSize = list.size();
        }

        @Override // io.opencensus.metrics.LongCumulative
        public NoopLongPoint getOrCreateTimeSeries(List<LabelValue> list) {
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelValues"), "labelValue");
            Utils.checkArgument(this.labelKeysSize == list.size(), "Label Keys and Label Values don't have same size.");
            return NoopLongPoint.INSTANCE;
        }

        @Override // io.opencensus.metrics.LongCumulative
        public NoopLongPoint getDefaultTimeSeries() {
            return NoopLongPoint.INSTANCE;
        }

        @Override // io.opencensus.metrics.LongCumulative
        public void removeTimeSeries(List<LabelValue> list) {
            Utils.checkNotNull(list, "labelValues");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes8.dex */
        public static final class NoopLongPoint extends LongPoint {
            private static final NoopLongPoint INSTANCE = new NoopLongPoint();

            @Override // io.opencensus.metrics.LongCumulative.LongPoint
            public void add(long j) {
            }

            private NoopLongPoint() {
            }
        }
    }
}
